package cn.com.git.udmp.common.component.ftp.config;

import org.apache.commons.lang.StringUtils;

import cn.com.git.udmp.common.component.ftp.consts.FtpConstant;
import cn.com.git.udmp.core.config.Global;

/**
 * @description ftp服务器配置类
 * @author Spring Cao
 * @version v1.0
 */
public class FtpServerConfig {

    private String ftpServerIp; // ftp服务器IP
    private int ftpServerPort; // ftp服务器端口
    private String ftpUserName; // ftp登录账号
    private String ftpPassword; // ftp密码
    private String ftpEncoding; // 编码
    private int ftpBufferSize; // 内部缓冲区大小
    private String ftpServerWorkingDirectory; // ftp会话当前工作目录

    public String getFtpServerIp() {
        return ftpServerIp;
    }

    public void setFtpServerIp(String ftpServerIp) {
        this.ftpServerIp = ftpServerIp;
    }

    public int getFtpServerPort() {
        return ftpServerPort;
    }

    public void setFtpServerPort(int ftpServerPort) {
        this.ftpServerPort = ftpServerPort;
    }

    public String getFtpUserName() {
        return ftpUserName;
    }

    public void setFtpUserName(String ftpUserName) {
        this.ftpUserName = ftpUserName;
    }

    public String getFtpPassword() {
        return ftpPassword;
    }

    public void setFtpPassword(String ftpPassword) {
        this.ftpPassword = ftpPassword;
    }

    /**
     * @description 获取服务器编码
     * @version
     * @title
     * @return 如果为空默认返回UTF-8
    */
    public String getFtpEncoding() {
        if (StringUtils.isNotBlank(ftpEncoding)) {
            return ftpEncoding;
        } else {
            return FtpConstant.ENCODE_UTF8;
        }
    }

    public void setFtpEncoding(String ftpEncoding) {
        this.ftpEncoding = ftpEncoding;
    }

    public int getFtpBufferSize() {
        return ftpBufferSize;
    }

    public void setFtpBufferSize(int ftpBufferSize) {
        this.ftpBufferSize = ftpBufferSize;
    }

    /**
     * @description 获取ftp工作目录
     * @version
     * @title
     * @return ftp工作目录
    */
    public String getFtpServerWorkingDirectory() {
        if (StringUtils.isNotBlank(ftpServerWorkingDirectory)) {
            return ftpServerWorkingDirectory;
        } else {
            return Global.getUserfilesBaseDir();
        }
    }

    public void setFtpServerWorkingDirectory(String ftpServerWorkingDirectory) {
        this.ftpServerWorkingDirectory = ftpServerWorkingDirectory;
    }

}
