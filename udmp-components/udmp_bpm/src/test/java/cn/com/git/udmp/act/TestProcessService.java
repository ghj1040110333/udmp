package cn.com.git.udmp.act;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.modules.act.service.ActTaskService;
import cn.com.git.udmp.modules.act.service.ProcessService;
import cn.com.git.udmp.modules.act.utils.ActUtils;


/**
 * 测试流程引擎
 * @author guosg
 *
 */
@ContextConfiguration(locations = {"classpath:META-INF/test-spring-context-test.xml","classpath:META-INF/spring-context-activiti.xml"})
public class TestProcessService extends AbstractTransactionalJUnit4SpringContextTests{
	private static final Log logger = LogFactory.getLog(TestProcessService.class);
	
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private TaskService taskService;
	@Autowired
	private ActTaskService actTaskService;
	@Autowired
	private ProcessService processService;
	@Autowired
	private HistoryService historyService;
	
	@Test
	@Transactional(readOnly=false)
	public void testMeeting(){
		String processId = processService.startProcessor("meetingShowcase", null, null);
		Task task = taskService.createTaskQuery().processInstanceId(processId).taskAssignee("name").singleResult();
		Assert.assertNotNull(task);
		Task task2 = taskService.createTaskQuery().processInstanceId(processId).taskAssignee("name2").singleResult();
		Assert.assertNotNull(task2);
		Task task3 = taskService.createTaskQuery().processInstanceId(processId).taskAssignee("name3").singleResult();
		Assert.assertNotNull(task3);
		
		Map<String, Object> vars = new HashMap<String, Object>();
		vars.put("ispass", true);
		processService.complete(task.getId(), null, vars);
		
		Task ptask = taskService.createTaskQuery().taskId(task3.getParentTaskId()).singleResult();
		
		processService.complete(task2.getId(), null, vars);
		
		task3 = taskService.createTaskQuery().processInstanceId(processId).taskAssignee("name3").singleResult();
		Assert.assertNull(task3);
		
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
		Assert.assertNull(pi);
		
		HistoricProcessInstance hpi = historyService.createHistoricProcessInstanceQuery().processInstanceId(processId).singleResult();	
		System.out.println(hpi.getEndTime());
		
	}
	
	/**
	 * 驳回
	 */
	@Test
	@Transactional(readOnly=false)
	public void testBackProcess(){
		String processId = processService.startProcessor("backProcess", null, null);
		Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		processService.complete(task.getId(), null, null);
		
		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		processService.backProcess(task.getId(), null);//驳回到上一个节点
		
		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		Assert.assertEquals("一级", task.getName());
		
		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		processService.complete(task.getId(), null, null);
		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		processService.complete(task.getId(), null, null);
		
		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		Assert.assertEquals("3级", task.getName());
		
		List<ActivityImpl> lis = processService.findBackAvtivity(task.getId());
		
		processService.backProcess(task.getId(), lis.get(0).getId(),null);//驳回到第一个节点
		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		Assert.assertEquals("一级", task.getName());
		
	}
	
	/**
	 * 测试取回
	 */
	@Test
	@Transactional(readOnly=false)
	public void testCallBackProcess(){
		String processId = processService.startProcessor("backProcess", null, null);
		Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		processService.complete(task.getId(), null, null);
		
		processService.callBackProcess(processId, "one");
		
		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		Assert.assertEquals("一级", task.getName());
	}
	
	/**
	 * 测试终止
	 */
	@Test
	@Transactional(readOnly=false)
	public void testEndProcess(){
		String processId = processService.startProcessor("backProcess", null, null);
		Task task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		processService.complete(task.getId(), null, null);
		
		task = taskService.createTaskQuery().processInstanceId(processId).singleResult();
		processService.endProcess(task.getId());
		
		ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceId(processId).singleResult();
		Assert.assertNull(pi);
	}
	
	
	@After
	public void after(){
		
	}

	
}
