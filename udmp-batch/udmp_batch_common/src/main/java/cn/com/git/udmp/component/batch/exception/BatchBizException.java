package cn.com.git.udmp.component.batch.exception;

import cn.com.git.udmp.common.exception.ExceptionMessageHelper;
import cn.com.git.udmp.core.exception.app.BizException;

/**
 * 批处理业务应用所使用异常的父类(checked) 批处理业务应用所使用， 继承原有udmp框架中BizException，进行批处理异常获取
 * 
 * <li>应用系统中通过捕获第三方异常并包装为此类型异常向上层抛出
 * <li>封装及抛出异常应遵守开发指导规范要求的异常抛出原则 
 * <li>使用：new BatchBizException(
 * 1.errorCode：错误代码，各系统批作业自行规划,用来分类查询指定类型的异常信息,
 * 2.message:错误提示信息含错误内容及问题代码行，批次数据信息及主键等，用来批作业开发快速定位问题)
 * <li>批处理系统提供异常查询的功能。
 * 
 * @description
 * @author bihb bihb_wb@newchinalife.com
 * @date 2015年5月14日 下午6:14:36
 */
public class BatchBizException extends BizException {

    /** UUID */
    private static final long serialVersionUID = 1L;

    /** 异常编码 */
    private String errCode;

    /**
     * @Fields infoLevel : 信息级别
     */ 
    private String infoLevel;

    /**
     * 构造一个异常信息
     * 
     * @param errCode
     *            String错误信息
     */
    public BatchBizException(String errCode) {
        super(ExceptionMessageHelper.getExMsg(errCode));
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode
     *            异常代码
     * @param message
     *            异常信息
     */
    public BatchBizException(String errCode, String message) {
        super(ExceptionMessageHelper.getExMsg(errCode) + ":" + message);
        this.errCode = errCode;
    }
    
    /**
     * <p>Title: 构造批处理异常对象（错误码、错误消息、错误信息级别 3个参数的构造函数）</p> 
     * <p>Description: 构造批处理异常对象</p> 
     * @param errCode   错误码
     * @param message   错误消息
     * @param infoLevel 错误信息级别
     */
    public BatchBizException(String errCode, String message, String infoLevel) {
        super(ExceptionMessageHelper.getExMsg(errCode) + ":" + message);
        this.errCode = errCode;
        this.infoLevel = infoLevel;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode
     *            异常代码
     * @param cause
     *            异常来源
     */
    public BatchBizException(String errCode, Throwable cause) {
        super(ExceptionMessageHelper.getExMsg(errCode), cause);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode
     *            异常代码
     * @param params
     *            要替换到异常常量信息中的自定义消息
     */
    public BatchBizException(String errCode, Object[] params) {
        super(ExceptionMessageHelper.getExMsg(errCode, params));
        this.errCode = errCode;
        this.getMessage();
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode
     *            异常代码
     * @param message
     *            异常信息
     * @param cause
     *            异常来源
     */
    public BatchBizException(String errCode, String message, Throwable cause) {
        super(ExceptionMessageHelper.getExMsg(errCode) + ":" + message, cause);
        this.errCode = errCode;
    }

    /**
     * 构造一个异常信息
     * 
     * @param errCode
     *            异常代码
     * @param params
     *            要替换到异常常量信息中的自定义消息
     * @param cause
     *            异常来源
     */
    public BatchBizException(String errCode, Object[] params, Throwable cause) {
        super(ExceptionMessageHelper.getExMsg(errCode, params), cause);
        this.errCode = errCode;
        this.getMessage();
    }

    /**
     * 返回errCode的属性值
     * 
     * @description 返回errCode的属性值
     * @version 1.0
     * @title 返回errCode的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取errCode的属性值
     */
    public String getErrCode() {
        return errCode;
    }

    /**
     * @description 给errCode属性赋值
     * @version 1.0
     * @title 给errCode属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param errCode
     *            errCode属性
     */
    public void setErrCode(String errCode) {
        this.errCode = errCode;
    }

    /**
     * 返回infoLevel的属性值
     * @description 返回infoLevel的属性值
     * @version 1.0
     * @title 返回infoLevel的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取infoLevel的属性值
     */
    public String getInfoLevel() {
        return infoLevel;
    }

    /**
     * @description 给infoLevel属性赋值
     * @version 1.0
     * @title 给infoLevel属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param infoLevel infoLevel属性 
     */
    public void setInfoLevel(String infoLevel) {
        this.infoLevel = infoLevel;
    }
}
