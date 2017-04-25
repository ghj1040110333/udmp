package cn.com.git.udmp.core.exception;

import java.io.PrintStream;
import java.io.PrintWriter;

import cn.com.git.udmp.common.exception.ExceptionMessageHelper;
import cn.com.git.udmp.common.exception.FrameworkException;

/**
 * 运行时异常(unchecked)
 * 
 * <li>未知的不确定的只有在运行时才会出现的异常需要使用此异常类 <li>封装及抛出异常应遵守开发指导规范要求的异常抛出原则
 * 
 * @author Spring.Cao
 * @version v1.0 2015-12-22
 * 
 */
public class FrameworkRuntimeException extends FrameworkException {

    private static final long serialVersionUID = 1L;

    /** 异常编码 */
    private String errCode;

    /** 原始的异常消息 */
    private String orgMessage;

    /** 输出参数 */
    private Object[] params;

    /**
     * 构造一个异常信息
     * 
     * @param errCode 错误信息
     */
    public FrameworkRuntimeException(String errCode) {
        super(ExceptionMessageHelper.getExMsg(errCode));
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode 异常代码
     * @param message 异常信息
     */
    public FrameworkRuntimeException(String errCode, String message) {
        super(ExceptionMessageHelper.getExMsg(errCode) + ":" + message);
        orgMessage = message;
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode 异常代码
     * @param cause 异常来源
     */
    public FrameworkRuntimeException(String errCode, Throwable cause) {
        super(ExceptionMessageHelper.getExMsg(errCode), cause);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode 异常代码
     * @param params 要替换到异常常量信息中的自定义消息
     */
    public FrameworkRuntimeException(String errCode, Object[] params) {
        super(ExceptionMessageHelper.getExMsg(errCode, params));
        this.params = params;
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
    public FrameworkRuntimeException(String errCode, String message, Throwable cause) {
        super(ExceptionMessageHelper.getExMsg(errCode) + ":" + message, cause);
        orgMessage = message;
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode 异常代码
     * @param params 要替换到异常常量信息中的自定义消息
     * @param cause 异常来源
     */
    public FrameworkRuntimeException(String errCode, Object[] params, Throwable cause) {
        super(ExceptionMessageHelper.getExMsg(errCode, params), cause);
        this.errCode = errCode;
        this.params = params;
        this.getMessage();
    }

    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    public String getErrCode() {
        return errCode;
    }

    public String getOrgMessage() {
        return orgMessage;
    }

    public void setOrgMessage(String orgMessage) {
        this.orgMessage = orgMessage;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    /**
     * @description 获取异常信息
     * @version
     * @title
     * @see com.nci.udmp.framework.exception.FrameworkException#toString()
     * @return 异常信息
    */
    public String toString() {
        String s = getClass().getName();
        String message = getLocalizedMessage();
        if (null != getCause() && getCause() instanceof FrameworkRuntimeException) {
            String causeMessage = getCause().getLocalizedMessage();
            if (causeMessage == null) {
                causeMessage = "";
            }
            return (message != null) ? message : "" + causeMessage;
        }
        return (message != null) ? (s + ":" + message) : s;
    }

    /**
     * @description 打印异常堆栈
     * @version
     * @title
     * @see com.nci.udmp.framework.exception.FrameworkException#printStackTrace(java.io.PrintStream)
     * @param s PrintStream
    */
    public void printStackTrace(PrintStream s) {
        synchronized (s) {
            if (null != getCause() && getCause() instanceof FrameworkRuntimeException) {
                s.println(this);
                return;
            }
        }
        super.printStackTrace(s);
    }

    /**
     * @description 打印异常堆栈
     * @version
     * @title
     * @see com.nci.udmp.framework.exception.FrameworkException#printStackTrace(java.io.PrintWriter)
     * @param s PrintWriter
    */
    public void printStackTrace(PrintWriter s) {
        synchronized (s) {
            if (null != getCause() && getCause() instanceof FrameworkRuntimeException) {
                s.println(this);
                return;
            }
        }
        super.printStackTrace(s);
    }

    /**
     * @description 获取异常信息
     * @version
     * @title
     * @see com.nci.udmp.framework.exception.FrameworkException#getMessage()
     * @return 异常信息
    */
    public String getMessage() {
        String causeMessage = "";
        if (null != getCause()) {
            causeMessage = getCause().getLocalizedMessage();
        }
        String messge = super.getMessage();
        if (null == messge) {
            messge = causeMessage;
        } else {
            if (null != causeMessage && !"".equals(causeMessage.trim())) {
                messge = messge + "-" + causeMessage;
            }
        }
        return messge;
    }
}
