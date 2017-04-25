package cn.com.git.udmp.common.component.ftp.validator.downloader;

import cn.com.git.udmp.common.component.ftp.downloader.Downloader;

/**
 * 下载器校验类,只做必要属性校验
 * @author Spring Cao
 * @version v1.0
 */
public interface IDownloaderValidator {

    /**
     * 单个文件下载校验器
     * @param downloader Downloader
     * @return 校验结果
     */
    public int validateSingleFile(Downloader downloader);
    
    /**
     * 多文件下载校验器
     * @param downloader Downloader
     * @return 校验结果
     */
    public int validateMultiFiles(Downloader downloader);
    
    /**
     * 单文件夹下载校验器
     * @param downloader Downloader
     * @return 校验结果
     */
    public int validateSingleCatalog(Downloader downloader);
    
    /**
     * 多文件夹下载校验器
     * @param downloader Downloader
     * @return 校验结果
     */
    public int validateMultiCatalogs(Downloader downloader);
}
