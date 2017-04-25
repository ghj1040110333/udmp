package cn.com.git.udmp.test.commons.dubbox.rpc;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import cn.com.git.udmp.test.commons.dubbox.rpc.support.DemoService;

/**
   *	@author  liang
   *	@since 2016年2月2日
**/
@ContextConfiguration(locations = { "classpath*:META-INF/spring/spring-context-dubbo-client.xml" })
public class DubboRPCClientTest extends AbstractJUnit4SpringContextTests{
	@Test
	public void client(){
		DemoService demoService = (DemoService)applicationContext.getBean("demoService"); // 获取远程服务代理
        String hello = demoService.sayHello("world"); // 执行远程方法
        
        System.out.println( hello ); // 显示调用结果
	}
	
}
