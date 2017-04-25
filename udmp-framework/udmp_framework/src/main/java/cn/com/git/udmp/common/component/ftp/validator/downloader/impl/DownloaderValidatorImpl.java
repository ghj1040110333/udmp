package cn.com.git.udmp.common.component.ftp.validator.downloader.impl;

import org.apache.commons.lang.StringUtils;

import cn.com.git.udmp.common.component.ftp.consts.FtpConstant;
import cn.com.git.udmp.common.component.ftp.downloader.Downloader;
import cn.com.git.udmp.common.component.ftp.validator.downloader.IDownloaderValidator;

/**
 * DownloaderValidatorImpl校验类
 * @author Spring Cao
 * @version v1.0
 */
public class DownloaderValidatorImpl implements IDownloaderValidator {

    @Override
    public int validateSingleFile(Downloader downloader) {
        if (null == downloader) {
            return FtpConstant.NULL_DOWNLOADER;
        } else {
            if (StringUtils.isBlank(downloader.getLocalCatalog())) {
                return FtpConstant.NULL_DOWNLOADER;
            } else {
                if (StringUtils.isBlank(downloader.getRemoteFilePath())) {
                    return FtpConstant.NULL_REMOTE_FILE_PATH;
                } else {
                    return FtpConstant.DOWNLOADER_SUCCESS;
                }
            }
        }
    }

    @Override
    public int validateMultiFiles(Downloader downloader) {
        if (null == downloader) {
            return FtpConstant.NULL_DOWNLOADER;
        } else {
            if (null == downloader.getRemoteFilePaths()) {
                return FtpConstant.NULL_DOWNLOAD_REMOTEFILEPATHS;
            } else {
                if (downloader.getRemoteFilePaths().isEmpty()) {
                    return FtpConstant.DOWNLOAD_REMOTEFILEPATHS_EMPTY;
                } else {
                    return FtpConstant.DOWNLOADER_SUCCESS;
                }
            }
        }
    }

    @Override
    public int validateSingleCatalog(Downloader downloader) {
        if (null == downloader) {
            return FtpConstant.NULL_DOWNLOADER;
        } else {
            if (StringUtils.isBlank(downloader.getLocalCatalog())) {
                return FtpConstant.NULL_DOWNLOADER;
            } else {
                if (StringUtils.isBlank(downloader.getRemoteCatalog())) {
                    return FtpConstant.NULL_REMOTE_CATALOG;
                } else {
                    return FtpConstant.DOWNLOADER_SUCCESS;
                }
            }
        }
    }

    @Override
    public int validateMultiCatalogs(Downloader downloader) {
        if (null == downloader) {
            return FtpConstant.NULL_DOWNLOADER;
        } else {
            if (null == downloader.getRemoteCatalogs()) {
                return FtpConstant.NULL_DOWNLOAD_REMOTE_CATALOGS;
            } else {
                if (downloader.getRemoteCatalogs().isEmpty()) {
                    return FtpConstant.DOWNLOAD_REMOTE_CATALOGS_EMPTY;
                } else {
                    return FtpConstant.DOWNLOADER_SUCCESS;
                }
            }
        }
    }

}
