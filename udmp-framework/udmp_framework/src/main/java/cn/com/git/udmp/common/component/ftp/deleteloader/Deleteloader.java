package cn.com.git.udmp.common.component.ftp.deleteloader;

import java.util.ArrayList;
import java.util.List;

/**
 * 删除装载器
 * @author Spring Cao
 * @version v1.0
 */
public class Deleteloader {
    
    /** 要从ftp服务器上删除的文件完整路径 */
    private String remoteFilePath;
    
    /** 要从ftp服务器上删除的文件夹完整路径 */
    private String remoteCatalog;
    
    /** 要从ftp服务器上删除的文件完整路径 */
    private List<String> remoteFilePaths = new ArrayList<String>();
    
    /** 要从ftp服务器上删除的文件夹完整路径 */
    private List<String> remoteCatalogs = new ArrayList<String>();

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

    public List<String> getRemoteFilePaths() {
        return remoteFilePaths;
    }

    public void setRemoteFilePaths(List<String> remoteFilePaths) {
        this.remoteFilePaths = remoteFilePaths;
    }

    public List<String> getRemoteCatalogs() {
        return remoteCatalogs;
    }

    public void setRemoteCatalogs(List<String> remoteCatalogs) {
        this.remoteCatalogs = remoteCatalogs;
    }
    
}
