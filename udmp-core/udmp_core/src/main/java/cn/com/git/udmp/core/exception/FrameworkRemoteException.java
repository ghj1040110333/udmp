package cn.com.git.udmp.core.exception;

import cn.com.git.udmp.common.exception.ExceptionMessageHelper;
import cn.com.git.udmp.common.exception.FrameworkException;

/**
 * 远程服务调用异常(unchecked)
 * 
 * 基础开发框架所使用
 * 
 * <li>用来封装服务调用时产生的异常 <li>封装及抛出异常应遵守开发指导规范要求的异常抛出原则
 * 
 * @author Spring.Cao
 * @version v1.0 2015-12-22
 * 
 */
public class FrameworkRemoteException extends FrameworkException {
    private static final long serialVersionUID = 1L;
    /** 异常编码 */
    private String errCode;

    /** 异常的报文 */
    private String report;

    /**
     * 构造一个异常信息
     * 
     * @param errCode String
     */
    public FrameworkRemoteException(String errCode) {
        super(ExceptionMessageHelper.getExMsg(errCode));
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode 异常代码 String
     * @param message 异常信息 String
     */
    public FrameworkRemoteException(String errCode, String message) {
        super(ExceptionMessageHelper.getExMsg(errCode) + ":" + message);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode 异常代码
     * @param message 异常信息
     * @param report String 异常报告
     */
    public FrameworkRemoteException(String errCode, String message, String report) {
        super(ExceptionMessageHelper.getExMsg(errCode) + ":" + message);
        this.errCode = errCode;
        this.report = report;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode 异常代码
     * @param cause 异常来源
     */
    public FrameworkRemoteException(String errCode, Throwable cause) {
        super(ExceptionMessageHelper.getExMsg(errCode), cause);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode 异常代码
     * @param params 要替换到异常常量信息中的自定义消息
     */
    public FrameworkRemoteException(String errCode, Object[] params) {
        super(ExceptionMessageHelper.getExMsg(errCode, params));
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
    public FrameworkRemoteException(String errCode, String message, Throwable cause) {
        super(ExceptionMessageHelper.getExMsg(errCode) + ":" + message, cause);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode 异常代码
     * @param params 要替换到异常常量信息中的自定义消息
     * @param cause 异常来源
     */
    public FrameworkRemoteException(String errCode, Object[] params, Throwable cause) {
        super(ExceptionMessageHelper.getExMsg(errCode, params), cause);
        this.errCode = errCode;
        this.getMessage();
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
}