package cn.com.git.udmp.common.component.ftp.consts;

/**
 * 登陆ftp服务器操作类型
 * 
 * @author Spring Cao
 * @version v1.0
 */
public enum FtpOperateType {

    /** 上传 */
    UPLOAD(1),
    /** 下载 */
    DOWNLOAD(2),
    /** 连接 */
    CONNECTING(3),
    /** 断开连接 */
    DISCONNECT(4);

    /** 操作类型 */
    private int operateType;

    /**
     * 操作类型私有构造
     * 
     * @param operateType 操作类型
     */
    private FtpOperateType(int operateType) {
        this.operateType = operateType;
    }

    @Override
    public String toString() {
        return String.valueOf(this.operateType);
    }
}
