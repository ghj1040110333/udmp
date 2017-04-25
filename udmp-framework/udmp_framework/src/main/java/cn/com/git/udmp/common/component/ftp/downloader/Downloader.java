package cn.com.git.udmp.common.component.ftp.downloader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 下载装载器
 * @author Spring Cao
 * @version v1.0
 */
public class Downloader {

    /** 远程文件完整路径 */
    private String remoteFilePath;
    
    /** 远程文件夹完整路径 */
    private String remoteCatalog;

    /** 本地目录 */
    private String localCatalog;

    /** 要保存到本地文件夹路径和从ftp服务器上下载的文件完整路径集合 */
    private Map<String, List<String>> remoteFilePaths = 
            new HashMap<String, List<String>>();
    
    /**  要保存到本地文件夹路径和要从ftp服务器上下载的文件夹完整路径集合 */
    private Map<String, List<String>> remoteCatalogs = 
            new HashMap<String, List<String>>();

    public String getRemoteFilePath() {
        return remoteFilePath;
    }

    public void setRemoteFilePath(String remoteFilePath) {
        this.remoteFilePath = remoteFilePath;
    }

    public String getRemoteCatalog() {
        return remoteCatalog;
    }

    public void setRemoteCatalog(String remoteCatalog) {
        this.remoteCatalog = remoteCatalog;
    }

    public String getLocalCatalog() {
        return localCatalog;
    }

    public void setLocalCatalog(String localCatalog) {
        this.localCatalog = localCatalog;
    }

    public Map<String, List<String>> getRemoteFilePaths() {
        return remoteFilePaths;
    }

    public void setRemoteFilePaths(Map<String, List<String>> remoteFilePaths) {
        this.remoteFilePaths = remoteFilePaths;
    }

    public Map<String, List<String>> getRemoteCatalogs() {
        return remoteCatalogs;
    }

    public void setRemoteCatalogs(Map<String, List<String>> remoteCatalogs) {
        this.remoteCatalogs = remoteCatalogs;
    }

}
