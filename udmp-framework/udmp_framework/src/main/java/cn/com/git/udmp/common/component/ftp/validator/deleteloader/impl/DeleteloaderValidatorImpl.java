package cn.com.git.udmp.common.component.ftp.validator.deleteloader.impl;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import cn.com.git.udmp.common.component.ftp.consts.FtpConstant;
import cn.com.git.udmp.common.component.ftp.deleteloader.Deleteloader;
import cn.com.git.udmp.common.component.ftp.validator.deleteloader.IDeleteloaderValidator;

/**
 * 删除装载器校验类
 * @author Spring Cao
 * @version v1.0
 */
public class DeleteloaderValidatorImpl implements IDeleteloaderValidator {

    @Override
    public int validateSingleFile(Deleteloader deleteloader) {
        if (null == deleteloader) {
            return FtpConstant.NULL_DELETELOADER;
        } else {
            if (StringUtils.isBlank(deleteloader.getRemoteFilePath())) {
                return FtpConstant.NULL_REMOTE_FILE_PATH;
            } else {
                return FtpConstant.DELETELOADER_SUCCESS;
            }
        }
    }

    @Override
    public int validateMultiFiles(Deleteloader deleteloader) {
        if (null == deleteloader) {
            return FtpConstant.NULL_DELETELOADER;
        } else {
            if (null == deleteloader.getRemoteFilePaths()) {
                return FtpConstant.NULL_DELETE_REMOTE_FILE_PATHS;
            } else {
                if (CollectionUtils.isEmpty(deleteloader.getRemoteFilePaths())) {
                    return FtpConstant.DELETE_REMOTE_FILE_PATHS_EMPTY;
                } else {
                    return FtpConstant.DELETELOADER_SUCCESS;
                }
            }
        }
    }

    @Override
    public int validateSingleCatalog(Deleteloader deleteloader) {
        if (null == deleteloader) {
            return FtpConstant.NULL_DELETELOADER;
        } else {
            if (StringUtils.isBlank(deleteloader.getRemoteCatalog())) {
                return FtpConstant.NULL_REMOTE_CATALOG;
            } else {
                return FtpConstant.DELETELOADER_SUCCESS;
            }
        }
    }

    @Override
    public int validateMultiCatalogs(Deleteloader deleteloader) {
        if (null == deleteloader) {
            return FtpConstant.NULL_DELETELOADER;
        } else {
            if (null == deleteloader.getRemoteCatalogs()) {
                return FtpConstant.NULL_DELETE_REMOTE_CATALOGS;
            } else {
                if (CollectionUtils.isEmpty(deleteloader.getRemoteCatalogs())) {
                    return FtpConstant.DELETE_REMOTE_CATALOGS_EMPTY;
                } else {
                    return FtpConstant.DELETELOADER_SUCCESS;
                }
            }
        }
    }

}
