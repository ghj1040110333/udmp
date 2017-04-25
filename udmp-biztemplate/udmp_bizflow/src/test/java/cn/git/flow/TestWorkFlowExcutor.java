package cn.git.flow;

import static junit.framework.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.com.git.udmp.bizflow.WorkFlowExecutor;
import cn.git.ecif.flow.user.ucc.UserUCC;
import cn.git.ecif.flow.user.vo.CheckUserRequesterVO;
import cn.git.ecif.flow.user.vo.CheckUserResponseVO;
import cn.git.ecif.flow.user.vo.CreateUserRequesterVO;
import cn.git.ecif.flow.user.vo.CreateUserResponserVO;
import cn.git.flow.helper.TestLocalServiceRequester;
import cn.git.flow.helper.TestLocalServiceResponser;

@ContextConfiguration(locations = { "classpath*:/META-INF/**/applicationContext*.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class TestWorkFlowExcutor{
	@Autowired
	private ApplicationContext context;
	
	@Test
	public void testRun() throws Exception{
		WorkFlowExecutor workFlowExecutor = (WorkFlowExecutor) context.getBean("testServiceExecutor");
		TestLocalServiceRequester requester = new TestLocalServiceRequester();
		requester.setName("workflow");
		TestLocalServiceResponser response = (TestLocalServiceResponser) workFlowExecutor.runProcess(requester);
		System.out.println("AAAAAAAAAAA : "+response.getName());
		assertEquals("my god!", response.getName());
	}
	
	@Test
	public void testRun2() throws Exception{
		WorkFlowExecutor executor = (WorkFlowExecutor)context.getBean("createUserFlow");
		CheckUserRequesterVO requester = new CheckUserRequesterVO();
		requester.setName("xiaoming");
		CreateUserResponserVO response = (CreateUserResponserVO)executor.runProcess(requester);
		System.out.println("ecifId : "+response.getEcifId());
	}
}
