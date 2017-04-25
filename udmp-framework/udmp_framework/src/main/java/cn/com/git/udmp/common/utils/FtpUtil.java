package cn.com.git.udmp.common.utils;

import org.apache.commons.net.ftp.FTPClient;

import cn.com.git.udmp.common.component.ftp.config.FtpServerConfig;
import cn.com.git.udmp.common.component.ftp.deleteloader.Deleteloader;
import cn.com.git.udmp.common.component.ftp.downloader.Downloader;
import cn.com.git.udmp.common.component.ftp.operator.IFtpOperator;
import cn.com.git.udmp.common.component.ftp.operator.impl.FtpOperatorImpl;
import cn.com.git.udmp.common.component.ftp.uploader.Uploader;

/**
 * Ftp工具类
 * @author Spring Cao
 * @version v1.0
 */
public class FtpUtil {

    private static IFtpOperator ftpOperator = new FtpOperatorImpl();// 实例化ftpOperator

    /**
     * 登陆ftpServer
     * @description 登陆ftpServer
     * @title 登陆ftpServer
     * @param ftpServerConfig ftp服务器配置包装类
     * @return false:登陆失败,true:登陆成功
     */
    public static FTPClient loginFtpServer(FtpServerConfig ftpServerConfig) {
        return ftpOperator.loginFtpServer(ftpServerConfig);
    }

    /**
     * 退出ftpServer
     * @description 退出ftpServer
     * @title 退出ftpServer
     * @param ftpClient ftp客户端
     * @return true:退出成功,false:退出失败
     */
    public static boolean logoutFtpServer(FTPClient ftpClient) {
        return ftpOperator.logoutFtpServer(ftpClient);
    }

    /**
     * 上传单个文件
     * @description 上传单个文件
     * @title 上传单个文件
     * @param uploader 上传装载器
     * @return true:上传成功,false:上传失败
     */
    public static boolean uploadFile(Uploader uploader) {
        return ftpOperator.uploadFile(uploader);
    }

    /**
     * 上传多个文件
     * @description 上传多个文件
     * @title 上传多个文件
     * @param uploader 上传装载器
     * @return true:上传成功,false:上传失败
     */
    public static boolean uploadFiles(Uploader uploader) {
        return ftpOperator.uploadFiles(uploader);
    }

    /**
     * 上传单个文件夹
     * @description 上传单个文件夹
     * @title 上传单个文件夹
     * @param uploader 上传装载器
     * @return true:上传成功,false:上传失败
     */
    public static boolean uploadCatalog(Uploader uploader) {
        return ftpOperator.uploadCatalog(uploader);
    }

    /**
     * 上传多个文件夹
     * @description 上传多个文件夹
     * @title 上传多个文件夹
     * @param uploader 上传装载器
     * @return true:上传成功,false:上传失败
     */
    public static boolean uploadMultiCatalogs(Uploader uploader) {
        return ftpOperator.uploadMultiCatalogs(uploader);
    }

    /**
     * 单个文件下载
     * @description 单个文件下载
     * @title 单个文件下载
     * @param downloader 下载装载器
     * @return true:下载成功,false:下载失败
     */
    public static boolean downloadFile(Downloader downloader) {
        return ftpOperator.downloadFile(downloader);
    }

    /**
     * 多文件下载
     * @description 多文件下载
     * @title 多文件下载
     * @param downloader 下载装载器
     * @return true:下载成功,false:下载失败
     */
    public static boolean downloadFiles(Downloader downloader) {
        return ftpOperator.downloadFiles(downloader);
    }

    /**
     * 单文件夹下载
     * @description 单文件夹下载
     * @title 单文件夹下载
     * @param downloader 下载装载器
     * @return true:下载成功,false:下载失败
     */
    public static boolean downloadCatalog(Downloader downloader) {
        return ftpOperator.downloadCatalog(downloader);
    }

    /**
     * 多文件夹下载
     * @description 多文件夹下载
     * @title 多文件夹下载
     * @param downloader 下载装载器
     * @return true:下载成功,false:下载失败
     */
    public static boolean downloadMultiCatalogs(Downloader downloader) {
        return ftpOperator.downloadMultiCatalogs(downloader);
    }

    /**
     *  删除单个文件
     * @description 删除单个文件
     * @title 删除单个文件
     * @param deleteloader 删除装载器
     * @return true:删除成功,false:删除失败
     */
    public static boolean deleteFile(Deleteloader deleteloader) {
        return ftpOperator.deleteFile(deleteloader);
    }

    /**
     * 删除多文件
     * @description 删除装载器
     * @title 删除装载器
     * @param deleteloader 删除装载器
     * @return true:删除成功,false:删除失败
     */
    public static boolean deleteFiles(Deleteloader deleteloader) {
        return ftpOperator.deleteFiles(deleteloader);
    }

    /**
     * 删除单文件夹
     * @description 删除单文件夹
     * @title 删除单文件夹
     * @param deleteloader 删除装载器
     * @return true:删除成功,false:删除失败
     */
    public static boolean deleteCatalog(Deleteloader deleteloader) {
        return ftpOperator.deleteCatalog(deleteloader);
    }

    /**
     * 删除多文件夹
     * @description 删除多文件夹
     * @title 删除多文件夹
     * @param deleteloader 删除装载器
     * @return true:删除成功,false:删除失败
     */
    public static boolean deleteMultiCatalogs(Deleteloader deleteloader) {
        return ftpOperator.deleteMultiCatalogs(deleteloader);
    }
}
