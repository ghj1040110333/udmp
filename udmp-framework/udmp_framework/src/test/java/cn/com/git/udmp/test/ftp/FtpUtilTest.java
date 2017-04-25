package cn.com.git.udmp.test.ftp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.net.ftp.FTPClient;
import org.junit.Before;
import org.junit.Test;

import cn.com.git.udmp.common.component.ftp.config.FtpServerConfig;
import cn.com.git.udmp.common.component.ftp.deleteloader.Deleteloader;
import cn.com.git.udmp.common.component.ftp.downloader.Downloader;
import cn.com.git.udmp.common.component.ftp.uploader.Uploader;
import cn.com.git.udmp.common.utils.FtpUtil;

/**
 * Ftp工具测试
 * @author Spring Cao
 * @version v1.0
 */
public class FtpUtilTest {
    
    private FtpServerConfig ftpServerConfig;
    
    @Before
    public void setUp(){
        ftpServerConfig = new FtpServerConfig();
        ftpServerConfig.setFtpServerIp("10.100.3.56");//必须
        ftpServerConfig.setFtpServerPort(21);//必须
        ftpServerConfig.setFtpUserName("root");//必须
        ftpServerConfig.setFtpPassword("gitroot");//必须
        ftpServerConfig.setFtpBufferSize(4096);//必须
        
        ftpServerConfig.setFtpEncoding("utf-8");//非必须
        ftpServerConfig.setFtpServerWorkingDirectory("");//非必须
    }
    
    @Test
    public void testUploadFile(){
        FTPClient ftpClient = FtpUtil.loginFtpServer(ftpServerConfig);//登录ftp服务器
        Uploader uploader = new Uploader();
        uploader.setSaveCatalog("/AppServer/java/jre/lib");//若不存在该文件夹则创建
        uploader.setLocalFilePath("C:\\Users\\tanzl_wb\\Desktop\\tools.jar");//本地文件路径
        FtpUtil.uploadFile(uploader);//上传操作
        FtpUtil.logoutFtpServer(ftpClient);//退出ftp服务器
    }
//    @Test
    public void testUploadFiles(){
        FTPClient ftpClient = FtpUtil.loginFtpServer(ftpServerConfig);//登录ftp服务器
        Uploader uploader = new Uploader();
        Map<String,List<String>> uploadFiles = new HashMap<String, List<String>>();
        List<String> localFiles = new ArrayList<String>();
        localFiles.add("C:\\Users\\tanzl_wb\\Desktop\\上传文件.PNG");
        localFiles.add("C:\\Users\\tanzl_wb\\Desktop\\多个文件.PNG");
        localFiles.add("C:\\Users\\tanzl_wb\\Desktop\\svn路径.txt");
        uploadFiles.put("/tanzl/多个文件", localFiles);
        uploader.setSaveCatalogAndLocalFiles(uploadFiles);
        FtpUtil.uploadFiles(uploader);//上传操作
        FtpUtil.logoutFtpServer(ftpClient);//退出ftp服务器
    }
//    @Test
    public void testUploadCatalog(){
        FTPClient ftpClient = FtpUtil.loginFtpServer(ftpServerConfig);
        Uploader uploader = new Uploader();
        uploader.setSaveCatalog("/tanzl/catalog");
        uploader.setLocalCatalog("C:\\Users\\tanzl_wb\\Desktop\\文档整理");
        FtpUtil.uploadCatalog(uploader);
        FtpUtil.logoutFtpServer(ftpClient);
    }
//    @Test
    public void testUploadMultiCatalogs(){
        FTPClient ftpClient = FtpUtil.loginFtpServer(ftpServerConfig);
        Uploader uploader = new Uploader();
        Map<String,List<String>> uploadCatalog = new HashMap<String, List<String>>();
        List<String> catalogs = new ArrayList<String>();
        catalogs.add("C:\\Users\\tanzl_wb\\Desktop\\业务主键");
        catalogs.add("C:\\Users\\tanzl_wb\\Desktop\\文档整理");
        uploadCatalog.put("/tanzl/catalogs", catalogs);
        uploader.setUploadCatalogs(uploadCatalog);
        FtpUtil.uploadMultiCatalogs(uploader);
        FtpUtil.logoutFtpServer(ftpClient);
    }
    
//    @Test
    public void testDownloadFile(){
        FTPClient ftpClient = FtpUtil.loginFtpServer(ftpServerConfig);//登录
        Downloader downLoader = new Downloader();
        downLoader.setLocalCatalog("D:\\tanzl\\singalFile");//创建本地文件夹
        downLoader.setRemoteFilePath("/tanzl/单个文件/上传文件.PNG");//远程路径
        FtpUtil.downloadFile(downLoader);//下载单个文件操作
        FtpUtil.logoutFtpServer(ftpClient);//退出
    }
//    @Test
    public void testDownloadFiles(){
        FTPClient ftpClient = FtpUtil.loginFtpServer(ftpServerConfig);
        Downloader downLoader = new Downloader();
        Map<String,List<String>> downloadFiles = new HashMap<String, List<String>>();
        List<String> remoteFiles = new ArrayList<String>();
        remoteFiles.add("/tanzl/多个文件/svn路径.txt");//ftp远程文件路径
        remoteFiles.add("/tanzl/多个文件/多个文件.PNG");
        downloadFiles.put("D:\\tanzl\\catalogs", remoteFiles);//本地路径
        downLoader.setRemoteFilePaths(downloadFiles);//
        FtpUtil.downloadFiles(downLoader);//下载多个文件操作
        FtpUtil.logoutFtpServer(ftpClient);
    }
//    @Test
    public void testDownloadCatalog(){
        FTPClient ftpClient = FtpUtil.loginFtpServer(ftpServerConfig);
        Downloader downLoader = new Downloader();
        downLoader.setLocalCatalog("D:\\tanzl\\test");
        downLoader.setRemoteCatalog("/tanzl/单个文件");
        FtpUtil.downloadCatalog(downLoader);//下载单个文件夹
        FtpUtil.logoutFtpServer(ftpClient);
    }
//    @Test
    public void testDownloadMultiCatalog(){
        FTPClient ftpClient = FtpUtil.loginFtpServer(ftpServerConfig);
        Downloader downLoader = new Downloader();
        Map<String,List<String>> downloadRemoteCatalogs = new HashMap<String, List<String>>();
        List<String> remoteCatalogs = new ArrayList<String>();
        remoteCatalogs.add("/tanzl/单个文件");
        remoteCatalogs.add("/tanzl/多个文件");
        downloadRemoteCatalogs.put("D:\\tanzl\\multiCatalogs", remoteCatalogs);
        downLoader.setRemoteCatalogs(downloadRemoteCatalogs);
        FtpUtil.downloadMultiCatalogs(downLoader);//下载多个文件夹
        FtpUtil.logoutFtpServer(ftpClient);
    }
    
    
//    @Test
    public void testDeleteFile(){
        FTPClient ftpClient = FtpUtil.loginFtpServer(ftpServerConfig);
        Deleteloader deleteLoader = new Deleteloader();
        deleteLoader.setRemoteFilePath("/tanzl/多个文件/上传文件.PNG");
        FtpUtil.deleteFile(deleteLoader);//删除单个文件
        FtpUtil.logoutFtpServer(ftpClient);
    }
//    @Test
    public void testDeleteFiles(){
        FTPClient ftpClient = FtpUtil.loginFtpServer(ftpServerConfig);
        Deleteloader deleteLoader = new Deleteloader();
        List<String> remoteFiles = new ArrayList<String>();
        remoteFiles.add("/tanzl/多个文件/多个文件.PNG");
        remoteFiles.add("/tanzl/多个文件/svn路径.txt");
        deleteLoader.setRemoteFilePaths(remoteFiles);
        FtpUtil.deleteFiles(deleteLoader);//删除多个文件
        FtpUtil.logoutFtpServer(ftpClient);
    }
//    @Test
    public void testDeleteCatalog(){
        FTPClient ftpClient = FtpUtil.loginFtpServer(ftpServerConfig);
        Deleteloader deleteLoader = new Deleteloader();
        deleteLoader.setRemoteCatalog("/tanzl/catalogs/系统号码生成流程");
        FtpUtil.deleteCatalog(deleteLoader);//删除单个文件夹,未考虑文件夹有子文件夹的情况
        FtpUtil.logoutFtpServer(ftpClient);
    }
//    @Test
    public void testDeleteMultiCatalog(){
        FTPClient ftpClient = FtpUtil.loginFtpServer(ftpServerConfig);
        Deleteloader deleteLoader = new Deleteloader();
        List<String> remoteCatalogs = new ArrayList<String>();
        remoteCatalogs.add("/tanzl/多个文件");
        remoteCatalogs.add("/tanzl/catalogs/bizkey");
        deleteLoader.setRemoteCatalogs(remoteCatalogs);
        FtpUtil.deleteMultiCatalogs(deleteLoader);//删除多个文件夹
        FtpUtil.logoutFtpServer(ftpClient);
    }
    
//    @After
    public void tearDown(){
        ftpServerConfig = null;
    }
    
//    @Test
//    public void testRenameDirectory(){
//        FTPClient ftpClient = FtpUtil.loginFtpServer(ftpServerConfig);
//        String from = "/tanzl";
//        String to = "/tanzhiliang";
//        boolean isOk =FtpUtil.renameDirectory(from, to);
//        System.out.println(isOk);
//        FtpUtil.logoutFtpServer(ftpClient);
//    }
}
