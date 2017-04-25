package cn.com.git.udmp.test.commons.dubbox.rest;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
   *	@author  liang
   *	@since 2016年2月2日
**/
@ContextConfiguration(locations = { "classpath*:META-INF/spring/spring-context-dubbo-rest.xml" })
public class DubboxRestServerTest extends AbstractJUnit4SpringContextTests{
	@Test
	public void test1() throws InterruptedException{
		System.out.println("test1 start==========");
		Thread.sleep(6000000);
	}
}
