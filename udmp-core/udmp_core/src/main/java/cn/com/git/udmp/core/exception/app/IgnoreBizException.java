package cn.com.git.udmp.core.exception.app;

/**
 * 提示信息所用的异常
 * 
 * 业务应用所使用
 * 
 * <li>前端提示信息需要封装IgnoreBizException 
 * <li>封装及抛出异常应遵守开发指导规范要求的异常抛出原则
 * 
 * @author Spring.Cao
 * @version v1.0 2015-12-22
 * 
 */
public class IgnoreBizException extends BizException {

    /** UUID */
    private static final long serialVersionUID = -1016673892093809434L;

    /** 异常编码 */
    private String errCode;

    /**
     * 构造一个异常信息
     * 
     * @param errCode 错误信息
     */
    public IgnoreBizException(String errCode) {
        super(errCode);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode 异常代码
     * @param message 异常信息
     */
    public IgnoreBizException(String errCode, String message) {
        super(errCode,message);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode 异常代码
     * @param cause 异常来源
     */
    public IgnoreBizException(String errCode, Throwable cause) {
        super(errCode, cause);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode 异常代码
     * @param params 要替换到异常常量信息中的自定义消息
     */
    public IgnoreBizException(String errCode, Object[] params) {
        super(errCode, params);
        this.errCode = errCode;
        this.getMessage();
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode 异常代码
     * @param message 异常信息
     * @param cause 异常来源
     */
    public IgnoreBizException(String errCode, String message, Throwable cause) {
        super(errCode,message, cause);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode 异常代码
     * @param params 要替换到异常常量信息中的自定义消息
     * @param cause 异常来源
     */
    public IgnoreBizException(String errCode, Object[] params, Throwable cause) {
        super(errCode, params, cause);
        this.errCode = errCode;
        this.getMessage();
    }

    public String getErrCode() {
        return errCode;
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }
}
