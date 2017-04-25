package cn.com.git.udmp.common.component.ftp.consts;

import java.util.HashMap;
import java.util.Map;

/**
 * 常量类
 * @author Spring Cao
 * @version v1.0
 */
public final class FtpConstant {

    public static final Map<Integer, String> VALIDATE_RESULT = new HashMap<Integer, String>();

    static {
        VALIDATE_RESULT.put(new Integer(1), " uploader validate success !");
        VALIDATE_RESULT.put(new Integer(2), " downloader validate success !");
        VALIDATE_RESULT.put(new Integer(3), " deleteloader validate success !");
        VALIDATE_RESULT.put(new Integer(4), " uploader not assign value, is null !");
        VALIDATE_RESULT.put(new Integer(5), " downloader not assign value, is null !");
        VALIDATE_RESULT.put(new Integer(6), " deleteloader not assign value, is null !");
        VALIDATE_RESULT.put(new Integer(7), " remote file path not assign value , is null !");
        VALIDATE_RESULT.put(new Integer(8), " local catalog not assign value, is null !");
        VALIDATE_RESULT.put(new Integer(9), " save ftp server catalog not assign value, is null !");
        VALIDATE_RESULT.put(new Integer(10), " local file path not assign value, is null !");
        VALIDATE_RESULT.put(new Integer(11), " upload local files collection is empty !");
        VALIDATE_RESULT.put(new Integer(12), " download remote file paths collection is empty !");
        VALIDATE_RESULT.put(new Integer(13), " delete remote file paths collection is empty !");
        VALIDATE_RESULT.put(new Integer(14), " upload local catalogs collection is empty !");
        VALIDATE_RESULT.put(new Integer(15), " download remote catalogs collection is empty !");
        VALIDATE_RESULT.put(new Integer(16), " delete remote catalogs collection is empty !");
        VALIDATE_RESULT.put(new Integer(17), " upload local file paths collection not assign value, is null !");
        VALIDATE_RESULT.put(new Integer(18), " download remote file paths collection not assign value, is null !");
        VALIDATE_RESULT.put(new Integer(19), " delete remote file paths collection not assign value, is null !");
        VALIDATE_RESULT.put(new Integer(20), " upload local catalogs collection not assign value, is null !");
        VALIDATE_RESULT.put(new Integer(21), " download remote catalogs collection not assign value, is null !");
        VALIDATE_RESULT.put(new Integer(22), " delete remote catalogs collection not assign value, is null !");
        VALIDATE_RESULT.put(new Integer(23), " not catalog !");
        VALIDATE_RESULT.put(new Integer(24), " not file !");
        VALIDATE_RESULT.put(new Integer(25), " remote catalog  not assign value, is null !");
        VALIDATE_RESULT.put(new Integer(26), " ftpServerConfig  not assign value, is null  !");
        VALIDATE_RESULT.put(new Integer(27), " ftpServerConfig serverIp  not assign value, is null  !");
        VALIDATE_RESULT.put(new Integer(28), " ftpServerConfig userName  not assign value, is null  !");
        VALIDATE_RESULT.put(new Integer(29), " ftpServerConfig password  not assign value, is null  !");
        VALIDATE_RESULT.put(new Integer(30), " ftpServerConfig encoding  not assign value, is null  !");
        VALIDATE_RESULT.put(new Integer(31), " ftp server working directory not assign value, is null  !");
        VALIDATE_RESULT.put(new Integer(32), " server config validate success   !");
    }

    /** uploader校验成功 */
    public static final int UPLOADER_SUCCESS = 1;

    /** downloader校验成功 */
    public static final int DOWNLOADER_SUCCESS = 2;

    /** deleteloader校验成功 */
    public static final int DELETELOADER_SUCCESS = 3;

    /** 上传装载器为空 */
    public static final int NULL_UPLOADER = 4;

    /** 下载装载器为空 */
    public static final int NULL_DOWNLOADER = 5;

    /** 删除装载器为空 */
    public static final int NULL_DELETELOADER = 6;

    /** 远程文件路径为空 */
    public static final int NULL_REMOTE_FILE_PATH = 7;

    /** 本地目录为空 */
    public static final int NULL_LOCAL_CATALOG = 8;

    /** 上传ftp服务器目录为空 */
    public static final int NULL_SAVE_CATALOG = 9;

    /** 本地文件完整路径为空 */
    public static final int NULL_LOCAL_FILE_PATH = 10;

    /** 上传多文件保存路径和本地文件完整路径集合为空 */
    public static final int UPLOAD_LOCALFILES_EMPTY = 11;

    /** 下载多文件保存本地路径和远程文件完整路径集合为空 */
    public static final int DOWNLOAD_REMOTEFILEPATHS_EMPTY = 12;

    /** 删除多个远程文件路径集合为空 */
    public static final int DELETE_REMOTE_FILE_PATHS_EMPTY = 13;

    /** 上传多文件夹保存路径和本地文件完整路径集合为空 */
    public static final int UPLOAD_LOCAL_CATALOGS_EMPTY = 14;

    /** 下载多文件夹保存本地路径和远程文件完整路径集合为空 */
    public static final int DOWNLOAD_REMOTE_CATALOGS_EMPTY = 15;

    /** 删除多个远程文件夹路径集合为空 */
    public static final int DELETE_REMOTE_CATALOGS_EMPTY = 16;

    /** 上传多文件保存路径和本地文件完整路径集合为null */
    public static final int NULL_UPLOAD_LOCALFILES = 17;

    /** 下载多文件保存本地路径和远程文件完整路径集合为null */
    public static final int NULL_DOWNLOAD_REMOTEFILEPATHS = 18;

    /** 删除多个远程文件路径集合为null */
    public static final int NULL_DELETE_REMOTE_FILE_PATHS = 19;

    /** 上传多文件夹保存路径和本地文件完整路径集合为null */
    public static final int NULL_UPLOAD_LOCAL_CATALOGS = 20;

    /** 下载多文件夹保存本地路径和远程文件完整路径集合为null */
    public static final int NULL_DOWNLOAD_REMOTE_CATALOGS = 21;

    /** 删除多个远程文件夹路径集合为null */
    public static final int NULL_DELETE_REMOTE_CATALOGS = 22;

    /** 非目录 */
    public static final int NOT_CATALOG = 23;

    /** 非文件 */
    public static final int NOT_FILE = 24;

    /** 远程目录为 null */
    public static final int NULL_REMOTE_CATALOG = 25;

    /** ftpServer配置为空 */
    public static final int NULL_FTP_SERVER_CONFIG = 26;

    /** ftpServer ip为空 */
    public static final int NULL_FTP_SERVER_IP = 27;

    /** ftpServer username为空 */
    public static final int NULL_FTP_USERNAME = 28;

    /** ftpServer password为空 */
    public static final int NULL_FTP_PASSWORD = 29;

    /** ftpServer encoding为空 */
    public static final int NULL_FTP_ENCODING = 30;

    /** ftpServer working directory为空 */
    public static final int NULL_FTP_SERVER_WORKING_DIR = 31;

    /** FtpServer配置校验成功 */
    public static final int FTP_SERVER_CONFIG_SUCCESS = 32;
      
    /** FtpServer UTF8编码格式 */
    public static final String ENCODE_UTF8 = "UTF-8";
    
    /** FtpServer ISO8859-1编码格式 */
    public static final String ISO_8859_1_ENCODE = "iso-8859-1";
    
}
