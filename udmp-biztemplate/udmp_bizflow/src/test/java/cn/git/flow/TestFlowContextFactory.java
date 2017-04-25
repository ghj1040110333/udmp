package cn.git.flow;

import java.io.IOException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.git.udmp.bizflow.FlowContext;
import cn.com.git.udmp.bizflow.FlowContextFactory;
import cn.com.git.udmp.bizflow.FlowScope;
import cn.com.git.udmp.bizflow.activity.Activity;
import cn.com.git.udmp.bizflow.activity.LocalService;
import cn.com.git.udmp.bizflow.activity.StartActivity;
import cn.com.git.udmp.bizflow.line.Line;
import static org.junit.Assert.*;


@ContextConfiguration(locations = { "classpath*:/META-INF/**/applicationContext*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestFlowContextFactory{
	
	@Autowired
	private ApplicationContext context;
	
	@Test
	public void testBuild() throws IOException{
		FlowContextFactory builder = new FlowContextFactory(context);
		
		ClassPathResource resource = new ClassPathResource("helper/firstFlow.xml", this.getClass());
	
		builder.addConfig(resource.getInputStream());
		
		FlowContext flowContext =  builder.create();
		FlowScope scope = (FlowScope) flowContext.getBean("firstFlow");
		System.out.println(scope.getName());
		
		assertNotNull(scope);
		
		StartActivity start =  (StartActivity) scope.getBean("firstStart");
		assertNotNull(start);
		
		assertNotNull(start.getConnections());
		assertEquals(1, start.getConnections().size());
		Line line = start.getConnections().get(0);
		
		Activity activity = line.getTo();
		assertNotNull(activity);
		assertEquals("localService1", activity.getName());
		
		LocalService localService1 = (LocalService) scope.getBean("localService1");
		assertEquals("testLocalServiceBean", localService1.getBeanName());
		assertEquals("testService", localService1.getMethod());
		System.out.println("++++++++ "+activity.getName());
	}
}
