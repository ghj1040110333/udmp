package cn.com.git.udmp.core.exception.app;

/**
 * 业务回滚异常
 * 
 * 业务应用所使用
 * 
 * <li>在事务边界UCC所在的层次上对BizException\ServiceIntegrationException封装为本类型，并Thrown出去，实现事务回滚 
 * <li>封装及抛出异常应遵守开发指导规范要求的异常抛出原则
 * 
 * @author Spring.Cao
 * @version v1.0 2015-12-22
 * 
 */
public class RollbackableBizException extends BizException {

    /** UUID */
    private static final long serialVersionUID = -3888078632354490459L;

    /** 异常编码 */
    private String errCode;

    /**
     * 构造一个异常信息
     * 
     * @param errCode 错误信息
     */
    public RollbackableBizException(String errCode) {
        super(errCode);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode 异常代码
     * @param message 异常信息
     */
    public RollbackableBizException(String errCode, String message) {
        super(errCode,message);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode 异常代码
     * @param cause 异常来源
     */
    public RollbackableBizException(String errCode, Throwable cause) {
        super(errCode, cause);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode 异常代码
     * @param params 要替换到异常常量信息中的自定义消息
     */
    public RollbackableBizException(String errCode, Object[] params) {
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
    public RollbackableBizException(String errCode, String message, Throwable cause) {
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
    public RollbackableBizException(String errCode, Object[] params, Throwable cause) {
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
