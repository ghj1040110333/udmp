package cn.com.git.udmp.common.component.ftp.validator.deleteloader;

import cn.com.git.udmp.common.component.ftp.deleteloader.Deleteloader;

/**
 * 删除装载器校验类,只做必要属性校验
 * @author Spring Cao
 * @version v1.0
 */
public interface IDeleteloaderValidator {

    /**
     * 单文件删除校验器
     * 
     * @param deleteloader Deleteloader
     * @return 校验结果
     */
    public int validateSingleFile(Deleteloader deleteloader);

    /**
     * 多文件删除校验器
     * 
     * @param deleteloader Deleteloader
     * @return 校验结果
     */
    public int validateMultiFiles(Deleteloader deleteloader);

    /**
     * 单文件夹删除校验器
     * 
     * @param deleteloader Deleteloader
     * @return 校验结果
     */
    public int validateSingleCatalog(Deleteloader deleteloader);

    /**
     * 多文件夹删除校验器
     * 
     * @param deleteloader Deleteloader
     * @return 校验结果
     */
    public int validateMultiCatalogs(Deleteloader deleteloader);

}
