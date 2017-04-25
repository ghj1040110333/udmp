package cn.com.git.udmp.component.batch.core.server.job.exception;

import cn.com.git.udmp.common.exception.FrameworkException;

/** 
 * @description 操作结束异常，用于结束当前操作
 * @author Liang liuliang1@git.com.cn 
 * @date 2016年12月9日 下午2:46:11  
*/
public class CancelException extends FrameworkException {

    public CancelException(String errCode, Object[] params, Throwable cause) {
        super(errCode, params, cause);
        // TODO 自动生成的构造函数存根
    }

    public CancelException(String errCode, Object[] params) {
        super(errCode, params);
        // TODO 自动生成的构造函数存根
    }

    public CancelException(String errCode, String message, Throwable cause) {
        super(errCode, message, cause);
        // TODO 自动生成的构造函数存根
    }

    public CancelException(String errCode, String message) {
        super(errCode, message);
        // TODO 自动生成的构造函数存根
    }

    public CancelException(String errCode, Throwable cause) {
        super(errCode, cause);
        // TODO 自动生成的构造函数存根
    }

    public CancelException(String errCode) {
        super(errCode);
        // TODO 自动生成的构造函数存根
    }

    public CancelException(Throwable cause) {
        super(cause);
        // TODO 自动生成的构造函数存根
    }


}
