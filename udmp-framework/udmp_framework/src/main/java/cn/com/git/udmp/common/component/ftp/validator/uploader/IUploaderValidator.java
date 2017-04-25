package cn.com.git.udmp.common.component.ftp.validator.uploader;

import cn.com.git.udmp.common.component.ftp.uploader.Uploader;

/**
 * 上传装载器校验器,只做必要属性校验
 * @author Spring Cao
 * @version v1.0
 */
public interface IUploaderValidator {

    /**
     * 单个文件上传校验器
     * @param uploader Uploader
     * @return 校验结果
     */
    public int validateSingleFile(Uploader uploader);
    
    /**
     * 多个文件上传校验器
     * @param uploader Uploader
     * @return 校验结果
     */
    public int validateMultiFiles(Uploader uploader);
    
    /**
     * 单个文件夹上传校验器
     * @param uploader Uploader
     * @return 校验结果
     */
    public int validateSingleCatalog(Uploader uploader);
    
    /**
     * 多个文件夹上传校验器
     * @param uploader Uploader
     * @return 校验结果
     */
    public int validateMultiCatalogs(Uploader uploader);
    
}
