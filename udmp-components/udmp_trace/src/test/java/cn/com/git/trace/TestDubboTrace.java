package cn.com.git.trace;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.com.git.trace.mock.DubboServiceClient;

public class TestDubboTrace {
	private ApplicationContext clientContext = null;
	
	private ApplicationContext serverContext = null;
	
	private DubboServiceClient dubboServiceClient = null;
	
	@Before
	public void before(){
		
		ApplicationContext registryContext = new ClassPathXmlApplicationContext(new String[] {"spring-context-dubbo-registry.xml"});
		serverContext = new ClassPathXmlApplicationContext(new String[] {"spring-context-dubbo-server.xml"});
		
		clientContext = new ClassPathXmlApplicationContext(new String[] {"spring-context-dubbo-client.xml"});
		dubboServiceClient = clientContext.getBean(DubboServiceClient.class);
	}
	
	@Test
	public void testSayHello(){
		String trance = dubboServiceClient.askServer("trace");
		System.out.println(trance);
	}
}
