package cn.com.git.udmp.integration;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import com.alibaba.dubbo.config.annotation.Reference;

import cn.com.git.udmp.test.commons.dubbox.rpc.support.DemoService;

@ContextConfiguration(locations = {"/META-INF/spring-context-dubbo.xml"})
public class TestDubboClient extends AbstractJUnit4SpringContextTests {
	@Reference
	private DemoService demo;
	
	@Test
	public void testDemo(){
	    String str = demo.sayHello("hahah");
	    System.out.println("ok : " + str);
	}
}
