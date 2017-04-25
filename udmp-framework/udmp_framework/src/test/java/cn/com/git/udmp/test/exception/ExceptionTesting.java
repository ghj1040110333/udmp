package cn.com.git.udmp.test.exception;

import org.junit.Test;

import cn.com.git.udmp.common.exception.ExceptionMessageHelper;


/**
 * 异常相关测试的测试类
 * @author Spring Cao
 * @version v1.0 2015-12-22
 */
public class ExceptionTesting {
	
	/**
	 * 测试从properties文件中按照errCode获取MSG
	 */
	@Test
	public void testExMsg(){
		System.out.println(ExceptionMessageHelper.getExMsg("APP-RA-SYS-0001"));//第三方应用的errCode
		System.out.println(ExceptionMessageHelper.getExMsg("UDMP-FRM-IO-0001"));//UDMP框架的errCode
	}
}
