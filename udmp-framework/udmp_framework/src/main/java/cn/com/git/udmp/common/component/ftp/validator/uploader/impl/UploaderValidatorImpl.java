package cn.com.git.udmp.common.component.ftp.validator.uploader.impl;

import org.apache.commons.lang.StringUtils;

import cn.com.git.udmp.common.component.ftp.consts.FtpConstant;
import cn.com.git.udmp.common.component.ftp.uploader.Uploader;
import cn.com.git.udmp.common.component.ftp.validator.uploader.IUploaderValidator;
import cn.com.git.udmp.common.utils.FileUtils;


/**
 * 上传装载器校验器实现类，只做必要属性校验
 * @author Spring Cao
 * @version v1.0
 *
 */
public class UploaderValidatorImpl implements IUploaderValidator {

    @Override
    public int validateSingleFile(Uploader uploader) {
        if (null == uploader) {
            return FtpConstant.NULL_UPLOADER;
        } else {
            if (StringUtils.isBlank(uploader.getSaveCatalog())) {
                return FtpConstant.NULL_SAVE_CATALOG;
            } else {
                if (StringUtils.isBlank(uploader.getLocalFilePath())) {
                    return FtpConstant.NULL_LOCAL_FILE_PATH;
                } else {
                    if (FileUtils.isLocalDir(uploader.getLocalFilePath())) {
                        return FtpConstant.NOT_FILE;
                    } else {
                        return FtpConstant.UPLOADER_SUCCESS;
                    }
                }
            }
        }
    }

    @Override
    public int validateMultiFiles(Uploader uploader) {
        if (null == uploader) {
            return FtpConstant.NULL_UPLOADER;
        } else {
            if (null == uploader.getSaveCatalogAndLocalFiles()) {
                return FtpConstant.NULL_UPLOAD_LOCALFILES;
            } else {
                if (uploader.getSaveCatalogAndLocalFiles().isEmpty()) {
                    return FtpConstant.UPLOAD_LOCALFILES_EMPTY;
                } else {
                    return FtpConstant.UPLOADER_SUCCESS;
                }
            }
        }
    }

    @Override
    public int validateSingleCatalog(Uploader uploader) {
        if (null == uploader) {
            return FtpConstant.NULL_UPLOADER;
        } else {
            if (StringUtils.isBlank(uploader.getSaveCatalog())) {
                return FtpConstant.NULL_SAVE_CATALOG;
            } else {
                if (StringUtils.isBlank(uploader.getLocalCatalog())) {
                    return FtpConstant.NULL_LOCAL_CATALOG;
                } else {
                    if (!FileUtils.isLocalDir(uploader.getLocalCatalog())) {
                        return FtpConstant.NOT_CATALOG;
                    } else {
                        return FtpConstant.UPLOADER_SUCCESS;
                    }
                }
            }
        }
    }

    @Override
    public int validateMultiCatalogs(Uploader uploader) {
        if (null == uploader) {
            return FtpConstant.NULL_UPLOADER;
        } else {
            if (null == uploader.getUploadCatalogs()) {
                return FtpConstant.NULL_UPLOAD_LOCAL_CATALOGS;
            } else {
                if (uploader.getUploadCatalogs().isEmpty()) {
                    return FtpConstant.UPLOAD_LOCAL_CATALOGS_EMPTY;
                } else {
                    return FtpConstant.UPLOADER_SUCCESS;
                }
            }
        }
    }

}
