package cn.com.git.udmp.common.component.ftp.operator.impl;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.com.git.udmp.common.component.ftp.config.FtpServerConfig;
import cn.com.git.udmp.common.component.ftp.consts.FtpConstant;
import cn.com.git.udmp.common.component.ftp.deleteloader.Deleteloader;
import cn.com.git.udmp.common.component.ftp.downloader.Downloader;
import cn.com.git.udmp.common.component.ftp.operator.IFtpOperator;
import cn.com.git.udmp.common.component.ftp.uploader.Uploader;
import cn.com.git.udmp.common.component.ftp.validator.deleteloader.IDeleteloaderValidator;
import cn.com.git.udmp.common.component.ftp.validator.downloader.IDownloaderValidator;
import cn.com.git.udmp.common.component.ftp.validator.uploader.IUploaderValidator;
import cn.com.git.udmp.common.utils.FileUtils;
import cn.com.git.udmp.core.exception.FrameworkRuntimeException;

/**
 * ftp操作实现类
 * @author Spring Cao
 * @version v1.0
 */
public class FtpOperatorImpl implements IFtpOperator {

    private static final String SYSTEM_ENCODING = System.getProperty("file.encoding");
    private Logger logger = LoggerFactory.getLogger(getClass());
    private FTPClient ftpClient = new FTPClient();

    private IUploaderValidator uploaderValidator;
    private IDownloaderValidator downloaderValidator;
    private IDeleteloaderValidator deleteloaderValidator;

    public IUploaderValidator getUploaderValidator() {
        return uploaderValidator;
    }

    public void setUploaderValidator(IUploaderValidator uploaderValidator) {
        this.uploaderValidator = uploaderValidator;
    }

    public IDownloaderValidator getDownloaderValidator() {
        return downloaderValidator;
    }

    public void setDownloaderValidator(IDownloaderValidator downloaderValidator) {
        this.downloaderValidator = downloaderValidator;
    }

    public IDeleteloaderValidator getDeleteloaderValidator() {
        return deleteloaderValidator;
    }

    public void setDeleteloaderValidator(IDeleteloaderValidator deleteloaderValidator) {
        this.deleteloaderValidator = deleteloaderValidator;
    }

    /**
     * 通过路径判断ftp服务器上文件夹是否存在 changeWorkingDirectory方法为切换工作目录
     * 如果切换成功则表示该路径的文件夹存在，如果切换失败，则表示文件夹不存在
     * 
     * @param dirPath 文件夹路径
     * @return boolean true:该文件夹已经存在,false:该文件夹不存在
     */
    private boolean isFtpServerExistDir(String dirPath) {
        boolean result = false;
        if (!StringUtils.isNotBlank(dirPath)) {
            return result;
        }
        try {
            // ftp服务器路径
            String serverPath = new String(dirPath.getBytes(SYSTEM_ENCODING), FtpConstant.ISO_8859_1_ENCODE);
            if (ftpClient.changeWorkingDirectory(serverPath)) {
                result = true;
            } else {
                logger.debug("转换ftp会话的工作目录失败！可能不存在路径：" + dirPath);
            }
        } catch (IOException e) {
            result = false;
            throw new FrameworkRuntimeException("转换工作目录异常：" + e.getMessage());
        }
        return result;
    }

    /**
     * 通过路径判断ftp服务器上文件是否存在 通过FtpClient的listNames方法列出路径文件夹下的所有文件
     * 
     * @param filePath 文件路径
     * @return boolean true:该文件已经存在,false:该文件不存在
     */
    private boolean isFtpServerExistFile(String filePath) {
        boolean result = false;
        // 该路径为一定不为文件夹类型路径
        if (!isFtpServerExistDir(filePath)) {
            try {
                // ftp文件路径
                String ftpFilePath = new String(filePath.getBytes(SYSTEM_ENCODING), FtpConstant.ISO_8859_1_ENCODE);
                String[] fileNames = ftpClient.listNames(ftpFilePath);
                if (!ArrayUtils.isEmpty(fileNames)) {
                    logger.debug(" the files  on " + filePath + ":" + fileNames.toString());
                    result = true;
                } else {
                    logger.debug("no files found on " + filePath);
                }
            } catch (IOException e) {
                throw new FrameworkRuntimeException("  Directory is not found on ftp server : " + e.getMessage());
            }
        } else {
            logger.debug("Remote directory[" + filePath + "] is not filePath!");
        }
        return result;
    }

    /**
     * 创建远程目录并进入到创建后的目录
     * 
     * @param dirPath String
     * @return true:创建成功,false:创建失败
     */
    private boolean createRemoteDirectory(String dirPath) {
        boolean success = false;
        try {
            logger.debug(" will be create the catalog named of :" + dirPath);
            // 通过路径获取操作系统目录符号(\ or /)
            String osDirSignal = FileUtils.getOsDirSignal(dirPath);
            // 如果以(\ or /)结尾 则去掉
            if (dirPath.endsWith(osDirSignal)) {
                dirPath = dirPath.substring(0, dirPath.length() - 1);
            }
            // 如果远程目录不存在且不为根目录，则递归创建远程服务器目录
            if (!isFtpServerExistDir(dirPath) && !osDirSignal.equalsIgnoreCase(dirPath)) {
                int start = 0;
                String dirPre = "";
                if (dirPath.startsWith(osDirSignal)) {
                    start = 1;
                    dirPre = osDirSignal;
                } else {
                    start = 0;
                    int pre = dirPath.indexOf(osDirSignal);
                    dirPre = dirPath.substring(0, pre);
                }
                int num = StringUtils.countMatches(dirPath, osDirSignal);
                String[] dirNames = dirPath.split(osDirSignal);
                StringBuffer sb = new StringBuffer(dirPre);
                for (int i = start; i <= num; i++) {
                    String path = new String(dirNames[i].getBytes(SYSTEM_ENCODING), FtpConstant.ISO_8859_1_ENCODE);
                    sb.append(path);
                    String subDir = sb.toString();
                    if (!isFtpServerExistDir(subDir)) {
                        logger.debug(subDir + "will be created!");
                        String subDirectory = new String(subDir);
                        if (ftpClient.makeDirectory(subDirectory)) {
                            sb.append(osDirSignal);
                            logger.debug(" create the catalog named of : " + subDirectory + " success !");
                            success = true;
                        } else {
                            logger.debug("create the catalog named of :" + subDirectory + "failed !");
                        }
                    } else {
                        sb.append(osDirSignal);
                    }
                }

            } else {
                logger.debug(" the catalog named of : " + dirPath + " is exists !");
                success = true;
            }
        } catch (IOException e) {
            success = false;
            throw new FrameworkRuntimeException("  create remote directory failed ,the error info is : "
                    + e.getMessage());
        }

        return success;
    }

    @Override
    public FTPClient loginFtpServer(FtpServerConfig ftpServerConfig) {

        try {
            if (ftpServerConfig.getFtpServerPort() > 0) {
                ftpClient.connect(ftpServerConfig.getFtpServerIp(), ftpServerConfig.getFtpServerPort());
            } else {
                ftpClient.connect(ftpServerConfig.getFtpServerIp());
            }
            // 登录
            ftpClient.login(ftpServerConfig.getFtpUserName(), ftpServerConfig.getFtpPassword());

            // 检验是否连接成功
            int reply = ftpClient.getReplyCode();
            ftpClient.setDataTimeout(120000);
            ftpClient.setControlEncoding(SYSTEM_ENCODING);

            if (!FTPReply.isPositiveCompletion(reply)) {
                return null;
            }
            // 设置文件类型为二进制文件类型
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            ftpClient.setBufferSize(ftpServerConfig.getFtpBufferSize());
        } catch (IOException e) {
            throw new FrameworkRuntimeException(" login ftp server failed , the error info is : " + e.getMessage());
        }
        return ftpClient;
    }

    @Override
    public boolean logoutFtpServer(FTPClient ftpClientlo) {
        if (null == ftpClientlo) {
            throw new FrameworkRuntimeException(" the ftpClient is null !");
        }

        try {
            ftpClientlo.logout();
        } catch (IOException e) {
            throw new FrameworkRuntimeException(
                    "  exit ftp login and close ftp connection failed ,the error info is : " + e.getMessage());
        } finally {
            if (ftpClientlo.isConnected()) {
                try {
                    ftpClientlo.disconnect();
                } catch (IOException ioe) {
                    throw new FrameworkRuntimeException(" close ftp connection failed ,the error info is : "
                            + ioe.getMessage());
                }
            }
        }
        return true;
    }

    @Override
    public boolean uploadFile(Uploader uploader) {
        boolean result = false;
        int validateResult = uploaderValidator.validateSingleFile(uploader);
        if (FtpConstant.UPLOADER_SUCCESS == validateResult) {

            if (null != ftpClient) {
                FileInputStream inputStream = null;
                BufferedInputStream bi = null;
                try {
                    // 上传文件处理
                    inputStream = new FileInputStream(uploader.getLocalFilePath());
                    bi = new BufferedInputStream(inputStream);
                    // ftpClient.setControlEncoding(SYSTEM_ENCODING);
                    String saveCatalog = uploader.getSaveCatalog();
                    if (!isFtpServerExistDir(saveCatalog)) {
                        createRemoteDirectory(saveCatalog);
                    }
                    // 转移工作目录
                    boolean change = ftpClient.changeWorkingDirectory(new String(saveCatalog.getBytes(SYSTEM_ENCODING),
                            FtpConstant.ISO_8859_1_ENCODE));
                    String saveFileName = FileUtils.getFileName(uploader.getLocalFilePath());
                    if (change) {
                        result = ftpClient.storeFile(new String(saveFileName.getBytes(SYSTEM_ENCODING),
                                FtpConstant.ISO_8859_1_ENCODE), bi);
                        if (result) {
                            logger.debug(" upload the file named of : " + saveFileName + " success !");
                        } else {
                            logger.debug(" upload the file named of : " + saveFileName + " failed !");
                        }
                    } else {
                        logger.debug(" 转移工作目录到： [" + saveCatalog + " ]失败!");
                    }

                } catch (FileNotFoundException ex) {
                    throw new FrameworkRuntimeException("upload failed ,file not found . the error info is : "
                            + ex.getMessage());
                } catch (IOException e) {
                    throw new FrameworkRuntimeException(" set upload catlog failed ,the error info is : "
                            + e.getMessage());
                } finally {
                    try {
                        inputStream.close();
                        bi.close();
                        result = true;
                    } catch (IOException e) {
                        throw new FrameworkRuntimeException("close ftp file stream failed ,the error info is :"
                                + e.getMessage());
                    }
                }
            } else {
                logger.debug("upload file failed , because ftpClient is null !");
            }
        } else {
            logger.debug("upload file failed , because " + FtpConstant.VALIDATE_RESULT.get(validateResult) + " !");
        }
        return result;
    }

    @Override
    public boolean uploadFiles(Uploader uploader) {
        boolean result = false;
        int validateResult = uploaderValidator.validateMultiFiles(uploader);
        if (FtpConstant.UPLOADER_SUCCESS != validateResult) {
            logger.debug("upload files failed , because " + FtpConstant.VALIDATE_RESULT.get(validateResult) + " !");
            return false;
        }
        if (null == ftpClient) {
            logger.debug("upload files failed , because ftpClient is null !");
            return false;
        }
        Map<String, List<String>> uploadFiles = uploader.getSaveCatalogAndLocalFiles();
        Iterator<Entry<String, List<String>>> iterator = uploadFiles.entrySet().iterator();

        while (iterator.hasNext()) {
            Entry<String, List<String>> entry = iterator.next();
            String saveCatalog = entry.getKey();
            if (createRemoteDirectory(saveCatalog)) {
                List<String> localFilePaths = entry.getValue();
                if (!CollectionUtils.isEmpty(localFilePaths)) {
                    for (String localFilePath : localFilePaths) {
                        Uploader upr = new Uploader();
                        upr.setSaveCatalog(saveCatalog);
                        upr.setLocalFilePath(localFilePath);
                        if (!uploadFile(upr)) {
                            logger.debug(" upload the file path of : " + localFilePath + " failed ,skiped !");
                            continue;
                        }
                    }
                } else {
                    logger.debug("upload skiped , because local file paths collection is empty !");
                    continue;
                }
            } else {
                logger.debug("upload files failed , because create the remote catalog named of : " + saveCatalog
                        + " failed ,skiped !");
                continue;
            }
        }
        result = true;
        return result;
    }

    @Override
    public boolean uploadCatalog(Uploader uploader) {
        boolean result = false;
        int validateResult = uploaderValidator.validateSingleCatalog(uploader);
        if (FtpConstant.UPLOADER_SUCCESS == validateResult) {

            if (null != ftpClient) {

                String localCatalog = uploader.getLocalCatalog();
                String saveCatalog = uploader.getSaveCatalog();

                File localDir = new File(localCatalog);
                try {
                    if (createRemoteDirectory(saveCatalog)) {
                        if (ftpClient.changeWorkingDirectory(new String(saveCatalog.getBytes(SYSTEM_ENCODING),
                                FtpConstant.ISO_8859_1_ENCODE))) {
                            File[] localfiles = localDir.listFiles();
                            if (!ArrayUtils.isEmpty(localfiles)) {
                                for (File localfile : localfiles) {
                                    // 如果是文件 上传文件
                                    if (!localfile.isDirectory()) {
                                        Uploader upr = new Uploader();
                                        upr.setSaveCatalog(saveCatalog);
                                        String localFilePath = FileUtils.getAppendDir(localCatalog)
                                                + localfile.getName();
                                        upr.setLocalFilePath(localFilePath);

                                        if (!uploadFile(upr)) {
                                            logger.debug(" upload the file named of : " + localfile.getName()
                                                    + " failed , skiped !");
                                            continue;
                                        }
                                    } else {
                                        // 如果是目录创建目录
                                        Uploader upr = new Uploader();
                                        String saveDir = FileUtils.getAppendDir(saveCatalog) + localfile.getName();
                                        upr.setSaveCatalog(saveDir);
                                        String localFileCatalog = FileUtils.getAppendDir(localCatalog)
                                                + localfile.getName();
                                        upr.setLocalCatalog(localFileCatalog);

                                        if (!uploadCatalog(upr)) {
                                            logger.debug(" upload the catalog named of : " + localfile.getName()
                                                    + " failed , skiped !");
                                            continue;
                                        }
                                    }
                                }
                            } else {
                                logger.debug("");
                            }
                            result = true;
                        } else {
                            logger.debug("upload catalog failed , because can't access the remote catalog named of : "
                                    + saveCatalog + " !");
                        }
                    } else {
                        logger.debug("upload catalog failed , because create the remote catalog named of : "
                                + saveCatalog + " failed !");
                    }
                } catch (IOException e) {
                    throw new FrameworkRuntimeException(" upload catalog failed ,the error info is : " + e.getMessage());
                }
            } else {
                logger.debug("upload catalog failed , because ftpClient is null !");
            }
        } else {
            logger.debug("upload catalog failed , because " + FtpConstant.VALIDATE_RESULT.get(validateResult) + " !");
        }
        return result;
    }

    @Override
    public boolean uploadMultiCatalogs(Uploader uploader) {
        boolean result = false;
        int validateResult = uploaderValidator.validateMultiCatalogs(uploader);
        if (FtpConstant.UPLOADER_SUCCESS == validateResult) {

            if (null != ftpClient) {

                Map<String, List<String>> uploadCatalogs = uploader.getUploadCatalogs();
                Iterator<Entry<String, List<String>>> iterator = uploadCatalogs.entrySet().iterator();

                while (iterator.hasNext()) {
                    Entry<String, List<String>> entry = iterator.next();
                    String saveCatalog = entry.getKey();
                    List<String> localCatalogs = entry.getValue();
                    if (!CollectionUtils.isEmpty(localCatalogs)) {
                        for (String localCatalog : localCatalogs) {
                            if (FileUtils.isLocalDir(localCatalog)) {
                                Uploader upr = new Uploader();
                                upr.setSaveCatalog(saveCatalog);
                                upr.setLocalCatalog(localCatalog);
                                if (!uploadCatalog(upr)) {
                                    logger.debug(" upload the catalog named of : " + localCatalog
                                            + " failed , skiped !");
                                    continue;
                                }
                            } else {
                                Uploader upr = new Uploader();
                                upr.setSaveCatalog(saveCatalog);
                                upr.setLocalFilePath(localCatalog);
                                if (!uploadFile(upr)) {
                                    logger.debug(" upload the file named of : " + localCatalog + " failed , skiped !");
                                    continue;
                                }
                            }
                        }
                    } else {
                        logger.debug(" the upload files collection is empty , skiped !");
                        continue;
                    }
                }
                result = true;
            } else {
                logger.debug("upload multi catalogs failed , because ftpClient is null !");
            }
        } else {
            logger.debug("upload multi catalogs failed , because " + FtpConstant.VALIDATE_RESULT.get(validateResult)
                    + " !");
        }
        return result;
    }

    @Override
    public boolean downloadFile(Downloader downloader) {
        boolean result = false;
        int validateResult = downloaderValidator.validateSingleFile(downloader);
        if (FtpConstant.DOWNLOADER_SUCCESS == validateResult) {
            if (null != ftpClient) {
                String saveLocalCatalog = downloader.getLocalCatalog();
                String remoteFilePath = downloader.getRemoteFilePath();
                if (FileUtils.createDirectory(saveLocalCatalog)) {
                    if (!isFtpServerExistDir(remoteFilePath)) {
                        String fileName = FileUtils.getFileName(remoteFilePath);
                        BufferedOutputStream bos = null;
                        try {
                            // 转移到FTP服务器目录至指定的目录下
                            String remoteCatalog = FileUtils.getFilePath(remoteFilePath);
                            if (ftpClient.changeWorkingDirectory(new String(remoteCatalog.getBytes(SYSTEM_ENCODING),
                                    FtpConstant.ISO_8859_1_ENCODE))) {
                                String localFileName = FileUtils.getAppendDir(saveLocalCatalog) + fileName;
                                File file = new File(localFileName);
                                bos = new BufferedOutputStream(new FileOutputStream(file));
                                String encodeName = new String(fileName.getBytes(SYSTEM_ENCODING),
                                        FtpConstant.ISO_8859_1_ENCODE);
                                result = ftpClient.retrieveFile(encodeName, bos);
                                if (result) {
                                    logger.debug(" download the remote file named of ["
                                            + FileUtils.getAppendDir(saveLocalCatalog) + fileName + "] success ! ");
                                } else {
                                    logger.debug(" download the remote file named of ["
                                            + FileUtils.getAppendDir(saveLocalCatalog) + fileName + "] failed ! ");
                                }
                            } else {
                                logger.debug(" download the remote file named of ["
                                        + FileUtils.getAppendDir(saveLocalCatalog) + fileName
                                        + "  ] failed . because can't not access the catalog named of : "
                                        + remoteCatalog + " ! ");
                            }
                        } catch (Exception e) {
                            logger.error(" download remote file failed ,the error info is :{} ", e.getMessage());
                            throw new FrameworkRuntimeException(" download remote file failed ,the error info is : "
                                    + e.getMessage());
                        } finally {
                            if (bos != null) {
                                try {
                                    bos.flush();
                                    bos.close();
                                    bos = null;
                                } catch (IOException e) {
                                    result = false;
                                    logger.error(" download remote file failed ,the error info is :{} ", e.getMessage());
                                    throw new FrameworkRuntimeException(
                                            " download remote file failed ,the error info is : " + e.getMessage());
                                }
                            }
                        }
                    } else {
                        Downloader dwr = new Downloader();
                        dwr.setLocalCatalog(saveLocalCatalog);
                        dwr.setRemoteCatalog(remoteFilePath);
                        result = downloadCatalog(dwr);
                    }
                } else {
                    result = false;
                    logger.debug(" download the remote file named of :" + remoteFilePath
                            + " , failed . because create the local catalog named of : " + saveLocalCatalog + " ! ");
                }
            } else {
                logger.debug("download file failed , because ftpClient is null !");
            }
        } else {
            logger.debug(" download failed , because " + FtpConstant.VALIDATE_RESULT.get(validateResult) + " !");
        }
        return result;
    }

    @Override
    public boolean downloadFiles(Downloader downloader) {
        boolean result = false;
        int validateResult = downloaderValidator.validateMultiFiles(downloader);
        if (FtpConstant.DOWNLOADER_SUCCESS == validateResult) {

            if (null != ftpClient) {

                Map<String, List<String>> remoteFiles = downloader.getRemoteFilePaths();
                Iterator<Entry<String, List<String>>> iterator = remoteFiles.entrySet().iterator();

                while (iterator.hasNext()) {
                    Entry<String, List<String>> entry = iterator.next();
                    String saveLocalCatalog = entry.getKey();
                    if (FileUtils.createDirectory(saveLocalCatalog)) {
                        List<String> remoteFilePaths = entry.getValue();
                        if (!CollectionUtils.isEmpty(remoteFilePaths)) {
                            for (String remoteFilePath : remoteFilePaths) {
                                if (!isFtpServerExistDir(remoteFilePath)) {
                                    Downloader dwr = new Downloader();
                                    dwr.setLocalCatalog(saveLocalCatalog);
                                    dwr.setRemoteFilePath(remoteFilePath);
                                    if (!downloadFile(dwr)) {
                                        logger.debug(" download the remote file named of : " + remoteFilePath
                                                + " failed , skiped ! ");
                                        continue;
                                    }
                                } else {
                                    Downloader dwr = new Downloader();
                                    dwr.setLocalCatalog(saveLocalCatalog);
                                    dwr.setRemoteCatalog(remoteFilePath);
                                    if (!downloadCatalog(dwr)) {
                                        logger.debug(" download the remote catalog named of : " + remoteFilePath
                                                + " failed , skiped ! ");
                                        continue;
                                    }
                                }
                            }
                        } else {
                            logger.debug("  download skiped , because the collection is empty ! ");
                            continue;
                        }
                    } else {
                        logger.debug("  create the local catalog named of : " + saveLocalCatalog
                                + " failed or saveLocalCatalog is not a catalog , skiped ! ");
                        continue;
                    }
                }
                result = true;
            } else {
                logger.debug("download files failed , because ftpClient is null !");
            }
        } else {
            logger.debug("download files failed , because " + FtpConstant.VALIDATE_RESULT.get(validateResult) + " !");
        }
        return result;
    }

    @Override
    public boolean downloadCatalog(Downloader downloader) {
        boolean result = false;
        int validateResult = downloaderValidator.validateSingleCatalog(downloader);
        if (FtpConstant.DOWNLOADER_SUCCESS == validateResult) {

            if (null != ftpClient) {
                String remoteCatalog = downloader.getRemoteCatalog();
                String localCatalog = downloader.getLocalCatalog();
                if (isFtpServerExistDir(remoteCatalog)) {

                    try {
                        // 获取文件列表
                        FTPFile[] remoteFiles = ftpClient.listFiles(new String(remoteCatalog.getBytes(SYSTEM_ENCODING),
                                FtpConstant.ISO_8859_1_ENCODE));
                        String saveLocalCatalog = FileUtils.getAppendDir(localCatalog)
                                + new File(remoteCatalog).getName() + FileUtils.getOsDirSignal(localCatalog);
                        if (FileUtils.createDirectory(saveLocalCatalog)) {
                            if (!ArrayUtils.isEmpty(remoteFiles)) {
                                for (FTPFile remoteFile : remoteFiles) {
                                    if (!remoteFile.isDirectory()) {
                                        Downloader dwr = new Downloader();
                                        dwr.setLocalCatalog(saveLocalCatalog);
                                        String remoteFilePath = FileUtils.getAppendDir(remoteCatalog)
                                                + remoteFile.getName();
                                        dwr.setRemoteFilePath(remoteFilePath);
                                        if (!downloadFile(dwr)) {
                                            logger.debug(" download the remote file named of : " + remoteFilePath
                                                    + " failed , skiped ! ");
                                            continue;
                                        }
                                    } else {
                                        String remoteFileCatalog = FileUtils.getAppendDir(remoteCatalog)
                                                + remoteFile.getName();

                                        Downloader dwr = new Downloader();
                                        dwr.setLocalCatalog(saveLocalCatalog);
                                        dwr.setRemoteCatalog(new String(remoteFileCatalog.getBytes(SYSTEM_ENCODING),
                                                FtpConstant.ISO_8859_1_ENCODE));
                                        if (!downloadCatalog(dwr)) {
                                            logger.debug(" download the remote catalog named of : " + remoteFileCatalog
                                                    + " failed , skiped ! ");
                                            continue;
                                        }
                                    }
                                }
                                result = true;
                            } else {
                                logger.debug("要下载的为空文件夹！");
                                logger.error(" download Catalog failed , because the catalog named of :"
                                        + remoteCatalog + " , files collection is empty , skiped ! ");
                            }
                        } else {
                            logger.error(" create local directory :" + saveLocalCatalog + " failed , skiped ! ");
                        }
                    } catch (IOException e) {
                        throw new FrameworkRuntimeException(" download Catalog failed ,the error info is : "
                                + e.getMessage());
                    }
                } else {
                    Downloader dwr = new Downloader();
                    dwr.setLocalCatalog(localCatalog);
                    dwr.setRemoteFilePath(remoteCatalog);
                    if (downloadFile(dwr)) {
                        logger.debug(" download the remote file named of : " + remoteCatalog + " , success ! ");
                    } else {
                        logger.debug(" download the remote file named of : " + remoteCatalog + ", failed ! ");
                    }
                }
            } else {
                logger.debug("download catalog failed , because ftpClient is null !");
            }
        } else {
            logger.debug("download catalog failed , because " + FtpConstant.VALIDATE_RESULT.get(validateResult) + " !");
        }
        return result;
    }

    @Override
    public boolean downloadMultiCatalogs(Downloader downloader) {
        boolean result = false;
        int validateResult = downloaderValidator.validateMultiCatalogs(downloader);
        if (FtpConstant.DOWNLOADER_SUCCESS == validateResult) {

            if (null != ftpClient) {

                Map<String, List<String>> remoteCatalogs = downloader.getRemoteCatalogs();
                Iterator<Entry<String, List<String>>> iterator = remoteCatalogs.entrySet().iterator();

                while (iterator.hasNext()) {
                    Entry<String, List<String>> entry = iterator.next();
                    String saveLocalCatalog = entry.getKey();
                    if (FileUtils.createDirectory(saveLocalCatalog)) {
                        List<String> remoteFileCatalogs = entry.getValue();
                        if (!CollectionUtils.isEmpty(remoteFileCatalogs)) {
                            for (String remoteCatalog : remoteFileCatalogs) {
                                if (isFtpServerExistDir(remoteCatalog)) {
                                    Downloader dwr = new Downloader();
                                    dwr.setLocalCatalog(saveLocalCatalog);
                                    dwr.setRemoteCatalog(remoteCatalog);
                                    if (!downloadCatalog(dwr)) {
                                        logger.debug(" download the remote catalog named of : " + remoteCatalog
                                                + " failed , skiped ! ");
                                        continue;
                                    }
                                } else {
                                    Downloader dwr = new Downloader();
                                    dwr.setLocalCatalog(saveLocalCatalog);
                                    dwr.setRemoteFilePath(remoteCatalog);
                                    if (!downloadFile(dwr)) {
                                        logger.debug(" download the remote file named of : " + remoteCatalog
                                                + " failed , skiped ! ");
                                        continue;
                                    }
                                }
                            }
                        } else {
                            logger.error(" download Catalogs failed ,"
                                    + " because the catalogs collection is empty , skiped ! ");
                            continue;
                        }
                    } else {
                        logger.error(" download Catalog failed , because create the catalog named of :"
                                + saveLocalCatalog + " failed , skiped ! ");
                        continue;
                    }
                }
                result = true;
            } else {
                logger.debug("download catalog failed , because ftpClient is null !");
            }
        } else {
            logger.debug("download multi catalogs failed , because " + FtpConstant.VALIDATE_RESULT.get(validateResult)
                    + " !");
        }
        return result;
    }

    @Override
    public boolean deleteFile(Deleteloader deleteloader) {
        boolean result = false;
        int validateResult = deleteloaderValidator.validateSingleFile(deleteloader);
        if (FtpConstant.DELETELOADER_SUCCESS == validateResult) {

            String remoteFilePath = deleteloader.getRemoteFilePath();
            if (null != ftpClient) {
                if (!isFtpServerExistDir(remoteFilePath)) {
                    if (isFtpServerExistFile(remoteFilePath)) {
                        try {
                            String filePath = new String(remoteFilePath.getBytes(SYSTEM_ENCODING),
                                    FtpConstant.ISO_8859_1_ENCODE);
                            result = ftpClient.deleteFile(filePath);
                            if (result) {
                                logger.debug(" delete the remote file path named of :" + remoteFilePath
                                        + " by ftp operate success !");
                            } else {
                                logger.debug(" delete the remote file path named of :" + remoteFilePath
                                        + " by ftp operate failed !");
                            }
                        } catch (IOException e) {
                            throw new FrameworkRuntimeException(
                                    " delete remote file by ftp operate failed ,the error info is : " + e.getMessage());
                        }
                    } else {
                        logger.debug(" delete failed , because the remote filePath named of :" + remoteFilePath
                                + " , not exist !");
                    }
                } else {
                    Deleteloader der = new Deleteloader();
                    der.setRemoteCatalog(remoteFilePath);
                    result = deleteCatalog(der);
                }
            } else {
                logger.debug(" delete file failed , because ftpClient is null !");
            }
        } else {
            logger.debug("delete file failed , because " + FtpConstant.VALIDATE_RESULT.get(validateResult) + " !");
        }
        return result;
    }

    @Override
    public boolean deleteFiles(Deleteloader deleteloader) {
        boolean result = false;
        int validateResult = deleteloaderValidator.validateMultiFiles(deleteloader);
        if (FtpConstant.DELETELOADER_SUCCESS == validateResult) {

            if (null != ftpClient) {
                List<String> remoteFilePaths = deleteloader.getRemoteFilePaths();
                for (String remoteFilePath : remoteFilePaths) {
                    if (!isFtpServerExistDir(remoteFilePath)) {
                        Deleteloader dtr = new Deleteloader();
                        dtr.setRemoteFilePath(remoteFilePath);
                        if (!deleteFile(dtr)) {
                            logger.debug(" delete the file named of : " + remoteFilePath + " failed , skiped !");
                            continue;
                        }
                    } else {
                        Deleteloader dtr = new Deleteloader();
                        dtr.setRemoteCatalog(remoteFilePath);
                        if (!deleteCatalog(dtr)) {
                            logger.debug(" delete the catalog named of : " + remoteFilePath + " failed , skiped !");
                            continue;
                        }
                    }
                }
                result = true;
            } else {
                logger.debug(" delete file failed , because ftpClient is null !");
            }
        } else {
            logger.debug("delete files failed , because " + FtpConstant.VALIDATE_RESULT.get(validateResult) + " !");
        }
        return result;
    }

    @Override
    public boolean deleteCatalog(Deleteloader deleteloader) {
        boolean result = false;
        int validateResult = deleteloaderValidator.validateSingleCatalog(deleteloader);
        if (FtpConstant.DELETELOADER_SUCCESS == validateResult) {

            if (null != ftpClient) {
                String remoteCatalog = deleteloader.getRemoteCatalog();
                if (isFtpServerExistDir(remoteCatalog)) {
                    try {
                        // 获取文件列表
                        FTPFile[] remoteFiles = ftpClient.listFiles(new String(remoteCatalog.getBytes(SYSTEM_ENCODING),
                                FtpConstant.ISO_8859_1_ENCODE));
                        if (!ArrayUtils.isEmpty(remoteFiles)) {
                            for (FTPFile remoteFile : remoteFiles) {
                                logger.debug("文件列表的文件名：" + remoteFile.getName());
                                if (!remoteFile.isDirectory()) {
                                    Deleteloader dtr = new Deleteloader();
                                    String remoteFilePath = FileUtils.getAppendDir(remoteCatalog) + remoteFile.getName();
                                    dtr.setRemoteFilePath(remoteFilePath);
                                    logger.debug("文件类型设置远程路径：" + remoteFilePath);
                                    if (!deleteFile(dtr)) {
                                        logger.debug(" delete the file named of : " + remoteFilePath
                                                + " failed , skiped !");
                                        continue;
                                    }
                                } else {
                                    String remoteFileCatalog = FileUtils.getAppendDir(remoteCatalog)
                                            + remoteFile.getName();
                                    Deleteloader dtr = new Deleteloader();
                                    dtr.setRemoteCatalog(remoteFileCatalog);
                                    logger.debug("文件夹类型设置远程路径：" + remoteFileCatalog + "递归调用删除文件夹");
                                    if (!deleteCatalog(dtr)) {
                                        logger.debug(" delete the catalog named of : " + remoteFileCatalog
                                                + " failed , skiped !");
                                        continue;
                                    }
                                }
                            }
                            result = ftpClient.removeDirectory(new String(remoteCatalog.getBytes(SYSTEM_ENCODING),
                                    FtpConstant.ISO_8859_1_ENCODE));
                            if (result) {
                                logger.debug(" delete the catalog named of : " + remoteCatalog + " success !");
                            } else {
                                logger.debug(" delete the catalog named of : " + remoteCatalog + " failed  !");
                            }
                        } else {
                            logger.debug("删除空文件夹！" + remoteCatalog);
                            result = ftpClient.removeDirectory(new String(remoteCatalog.getBytes(SYSTEM_ENCODING),
                                    FtpConstant.ISO_8859_1_ENCODE));
                            if (result) {
                                logger.debug("删除空文件夹成功！");
                            }
                        }
                    } catch (IOException e) {
                        throw new FrameworkRuntimeException(" delete Catalog failed ,the error info is : "
                                + e.getMessage());
                    }
                } else {
                    Deleteloader dtr = new Deleteloader();
                    dtr.setRemoteFilePath(remoteCatalog);
                    if (!deleteFile(dtr)) {
                        logger.debug(" delete the file named of : " + remoteCatalog + " failed , skiped !");
                    }
                }
            } else {
                logger.debug(" delete catalog failed , because ftpClient is null !");
            }
        } else {
            logger.debug("delete catalog failed , because " + FtpConstant.VALIDATE_RESULT.get(validateResult) + " !");
        }
        return result;
    }

    @Override
    public boolean deleteMultiCatalogs(Deleteloader deleteloader) {
        boolean result = false;
        int validateResult = deleteloaderValidator.validateMultiCatalogs(deleteloader);
        if (FtpConstant.DELETELOADER_SUCCESS == validateResult) {

            if (null != ftpClient) {
                List<String> remoteCatalogs = deleteloader.getRemoteCatalogs();
                for (String remoteCatalog : remoteCatalogs) {
                    if (isFtpServerExistDir(remoteCatalog)) {
                        Deleteloader dtr = new Deleteloader();
                        dtr.setRemoteCatalog(remoteCatalog);
                        if (!deleteCatalog(dtr)) {
                            logger.debug(" delete the catalog named of : " + remoteCatalog + " failed , skiped !");
                            continue;
                        }
                    } else {
                        Deleteloader dtr = new Deleteloader();
                        dtr.setRemoteFilePath(remoteCatalog);
                        if (!deleteFile(dtr)) {
                            logger.debug(" delete the file named of : " + remoteCatalog + " failed , skiped !");
                            continue;
                        }
                    }
                }
                result = true;
            } else {
                logger.debug(" delete multi catalogs failed , because ftpClient is null !");
            }
        } else {
            logger.debug("delete multi catalogs failed , because " + FtpConstant.VALIDATE_RESULT.get(validateResult)
                    + " !");
        }
        return result;
    }

    /**
     * @description 重命名远程文件夹
     * @version
     * @param from 要修改的文件夹
     * @param to 修改后的文件名
     * @return true：修改成功，false：修改失败
     */
    public boolean renameDirectory(String from, String to) {
        //
        if (ftpClient == null) {
            throw new FrameworkRuntimeException("ftpClient is null");
        }
        if (isFtpServerExistDir(from)) {
            try {
                ftpClient.rename(from, to);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            logger.debug("Directory [" + from + "] is not exist");
        }
        return false;
    }

}
