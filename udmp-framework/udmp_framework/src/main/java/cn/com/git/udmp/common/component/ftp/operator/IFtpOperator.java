package cn.com.git.udmp.common.component.ftp.operator;

import org.apache.commons.net.ftp.FTPClient;

import cn.com.git.udmp.common.component.ftp.config.FtpServerConfig;
import cn.com.git.udmp.common.component.ftp.deleteloader.Deleteloader;
import cn.com.git.udmp.common.component.ftp.downloader.Downloader;
import cn.com.git.udmp.common.component.ftp.uploader.Uploader;

/**
 * ftp操作接口
 * @author Spring Cao
 * @version v1.0
 */
public interface IFtpOperator {

    /**
     * 登陆ftpServer
     * 
     * @param ftpServerConfig ftp服务器配置包装类
     * @return null:登陆失败,否则:登陆成功
     */
    public FTPClient loginFtpServer(FtpServerConfig ftpServerConfig);

    /**
     * 退出ftpServer
     * 
     * @param ftpClient ftp客户端
     * @return true:退出成功,false:退出失败
     */
    public boolean logoutFtpServer(FTPClient ftpClient);
    
    /**
     * 上传单个文件
     * 
     * @param uploader 上传装载器
     * @return true:上传成功,false:上传失败
     */
    public boolean uploadFile(Uploader uploader);

    /**
     * 上传多个文件
     * 
     * @param uploader 上传装载器
     * @return true:上传成功,false:上传失败
     */
    public boolean uploadFiles(Uploader uploader);

    /**
     * 上传单个文件夹
     * 
     * @param uploader 上传装载器
     * @return true:上传成功,false:上传失败
     */
    public boolean uploadCatalog(Uploader uploader);

    /**
     * 上传多个文件夹
     * 
     * @param uploader 上传装载器
     * @return true:上传成功,false:上传失败
     */
    public boolean uploadMultiCatalogs(Uploader uploader);

    /**
     * 单个文件下载
     * 
     * @param downloader 下载装载器
     * @return true:下载成功,false:下载失败
     */
    public boolean downloadFile(Downloader downloader);

    /**
     * 多文件下载
     * 
     * @param downloader 下载装载器
     * @return true:下载成功,false:下载失败
     */
    public boolean downloadFiles(Downloader downloader);

    /**
     * 单文件夹下载
     * 
     * @param downloader 下载装载器
     * @return true:下载成功,false:下载失败
     */
    public boolean downloadCatalog(Downloader downloader);

    /**
     * 多文件夹下载
     * 
     * @param downloader 下载装载器
     * @return true:下载成功,false:下载失败
     */
    public boolean downloadMultiCatalogs(Downloader downloader);

    /**
     * 删除单个文件
     * 
     * @param deleteloader 删除装再器
     * @return true:删除成功,false:删除失败
     */
    public boolean deleteFile(Deleteloader deleteloader);

    /**
     * 删除多文件
     * 
     * @param deleteloader 删除装载器
     * @return true:删除成功,false:删除失败
     */
    public boolean deleteFiles(Deleteloader deleteloader);

    /**
     * 删除单文件夹
     * 
     * @param deleteloader 删除装载器
     * @return true:删除成功,false:删除失败
     */
    public boolean deleteCatalog(Deleteloader deleteloader);

    /**
     * 删除多文件夹
     * 
     * @param deleteloader 删除装载器
     * @return true:删除成功,false:删除失败
     */
    public boolean deleteMultiCatalogs(Deleteloader deleteloader);

}
