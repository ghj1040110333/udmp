package cn.com.git.udmp.common.component.ftp.uploader;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 上传装载器
 * @author Spring Cao
 * @version v1.0
 */
public class Uploader {

    /** 要上传到ftp服务器的目录 */
    private String saveCatalog;

    /** 要上传文件的本地文件完整路径 */
    private String localFilePath;

    /** 要上传文件的本地文件夹完整路径 */
    private String localCatalog;

    /** 要上传到ftp服务器的目录和要上传文件的本地文件完整路径集合 */
    private Map<String, List<String>> saveCatalogAndLocalFiles = 
            new HashMap<String, List<String>>();

    /** 要上传到ftp服务器的目录和要上传本地文件夹的集合 */
    private Map<String, List<String>> uploadCatalogs = 
            new HashMap<String, List<String>>();

    public String getSaveCatalog() {
        return saveCatalog;
    }

    public void setSaveCatalog(String saveCatalog) {
        this.saveCatalog = saveCatalog;
    }

    public String getLocalFilePath() {
        return localFilePath;
    }

    public void setLocalFilePath(String localFilePath) {
        this.localFilePath = localFilePath;
    }

    public String getLocalCatalog() {
        return localCatalog;
    }

    public void setLocalCatalog(String localCatalog) {
        this.localCatalog = localCatalog;
    }

    public Map<String, List<String>> getSaveCatalogAndLocalFiles() {
        return saveCatalogAndLocalFiles;
    }

    public void setSaveCatalogAndLocalFiles(Map<String, List<String>> saveCatalogAndLocalFiles) {
        this.saveCatalogAndLocalFiles = saveCatalogAndLocalFiles;
    }

    public Map<String, List<String>> getUploadCatalogs() {
        return uploadCatalogs;
    }

    public void setUploadCatalogs(Map<String, List<String>> uploadCatalogs) {
        this.uploadCatalogs = uploadCatalogs;
    }

}
