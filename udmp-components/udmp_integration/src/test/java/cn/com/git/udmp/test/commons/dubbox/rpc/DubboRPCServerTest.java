package cn.com.git.udmp.test.commons.dubbox.rpc;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
   *	@author  liang
   *	@since 2016年2月2日
**/
@ContextConfiguration(locations = { "classpath*:META-INF/spring/spring-context-dubbo-rpc.xml" })
public class DubboRPCServerTest extends AbstractJUnit4SpringContextTests{
	@Test
	public void server() throws InterruptedException{
		System.out.println("rpc server start...");
		Thread.sleep(600000);
	}
	
}
