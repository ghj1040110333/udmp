

package cn.com.git.udmp.common.service;

import cn.com.git.udmp.common.exception.FrameworkException;

/**
 * Service层公用的Exception, 从由Spring管理事务的函数中抛出时会触发事务回滚.
 * @author Spring Cao
 */
public class ServiceException extends FrameworkException {

	private static final long serialVersionUID = 1L;

//	public ServiceException() {
//		super();
//	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public ServiceException(String errCode, String message, Throwable cause) {
        super(errCode,message, cause);
    }
}
