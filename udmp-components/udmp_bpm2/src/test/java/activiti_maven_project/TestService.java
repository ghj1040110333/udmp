package activiti_maven_project;

import java.io.File;
import java.io.FileInputStream;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.activiti.engine.delegate.Expression;
import org.activiti.engine.identity.Group;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.task.Task;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import activiti_maven_project.com.git.bean.IssuedDistributionTaskBean;
import activiti_maven_project.com.git.common.Page;
import activiti_maven_project.com.git.common.PropertyUtil;
import activiti_maven_project.com.git.common.SerializeUtil;
import activiti_maven_project.com.git.common.exception.BpmException;
import activiti_maven_project.com.git.common.vo.ActHisProcessListPage;
import activiti_maven_project.com.git.common.vo.ActHisProcessListVO;
import activiti_maven_project.com.git.common.vo.ActWorkListPage;
import activiti_maven_project.com.git.common.vo.ActWorkListVO;
import activiti_maven_project.com.git.common.vo.ModelExportVO;
import activiti_maven_project.com.git.common.vo.OSInfoVO;
import activiti_maven_project.com.git.common.vo.SelectUserInfos;
import activiti_maven_project.com.git.ldap.LdapSpringService;
import activiti_maven_project.com.git.redis.impl.RedisServiceImpl;
import activiti_maven_project.com.git.service.ProcessDefinitionService;
import activiti_maven_project.com.git.service.ProcessService;
import activiti_maven_project.com.git.service.QueryWorkListService;
import activiti_maven_project.com.git.service.SysInfoService;
import activiti_maven_project.com.git.service.TaskService_;
import activiti_maven_project.com.git.service.impl.ActProcessService;
import activiti_maven_project.com.git.service.impl.ActQueryWorkListService;
import activiti_maven_project.com.git.service.impl.ActTaskService;
import activiti_maven_project.com.git.service.impl.ProcessCustomService;
import activiti_maven_project.com.git.user.User;
import activiti_maven_project.com.git.user.UserServiceImpl;
public class TestService {
	private static Logger logger = LogManager.getLogger(TestService.class);  
	static ApplicationContext ctx=null;
	
	private static void init(){
		
		ConfigurationSource source; 
		String path1="/log4j2.xml";
		try{
		URL url1=TestService.class.getResource(path1);  
		source = new ConfigurationSource(new FileInputStream(new File(url1.getPath())),url1);
		Configurator.initialize(null, source);		
		ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	
	public static void main(String[] str) throws Exception {
		init();
		//test ProcessDefinitionService
//		test_depProcess();
//		test_findProcess();
//		test_countProcess();
//		test_delProcess();
//		test_readResourceStream();
		//test processService
//		test_startProcess();
//		test_saveVariables();
//		test_findProcessByPId();
//		test_findProcessIdByBizId();
//		test_cancelProcessByProcessId();
//		test_suspendProcess();
//		test_resumeProcess();
		
		//test TaskService_
//		test_findTaskById();
//		test_takeTask();
//		test_saveTask();
//		test_findProcessIdByTask();
//		test_submitNomalTask();
//		test_replaceTask();
//		test_endProcess();
//		test_findnextActity();
//		test_createSubTask();
		
		//test queryservice
//		test_queryTaskList();
//		test_countWorkListForTemplate();
		
		
		//test userservice
//		test_createUser();
//		test_updateUserPassword();
//		test_createGroup();
//		test_createMembership();
//		test_deleteMembership();
//		test_deleteGroup();
//		test_deleteUser();		
//		test_createUsers();
//		test_findGroupsByUser();
//		test_o2j();
//		 test_bsh() ;
//		test_getAllTemplateStream();
		
//		test_sysInfo();
//		test_json();
//		test_activityInfo();
//		test_distributionTask();
		
		//test ldap
		test_ldapsearch();
		System.out.println("ok");
		
	}

	
	private static void test_ldapsearch() throws BpmException{
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
		LdapSpringService lss =(LdapSpringService)ctx.getBean("ldapSpringService");
		User  user=lss.getUserById("admin001");
		System.out.println("user.getFamilyName()==="+user.getFamilyName());
		System.out.println("-------------------------------");
		
		User  user2=lss.getGroupById("group01");
		System.out.println("user2.getGroupId()==="+user2.getGroupId());
		
		System.out.println("-------------------------------");
		
		List<User>  users=lss.getUsersByGroupId("group01");
		for(User u:users){
			System.out.println("u.getFamilyName()==="+u.getFamilyName());
		}
		
	}
	
	
	private static void test_createSubTask() throws BpmException{
		
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
		TaskService_ ts =(TaskService_)ctx.getBean("actTaskService_");	
		ts.createSubTask("123");
		
	}
	private static void test_distributionTask() throws BpmException{
//		ProcessCustomService pc =(ProcessCustomService)ctx.getBean("processCustomService");	
//		IssuedDistributionTaskBean dt =(IssuedDistributionTaskBean)ctx.getBean("issuedDistributionTaskBean");
//		Set<Expression> groups=dt.findTaskGroupName(pc.getProcessEngine(), "actBatch:1:67518", "usertask8");
//		
//		for(Expression group :groups){
//			String groupId=group.getExpressionText();
//			System.out.println("groupId==="+groupId);
//		}
	}
	
	
	
	private static void test_activityInfo() throws BpmException{
		ProcessCustomService pu =(ProcessCustomService)ctx.getBean("processCustomService");
		pu.findActivityInfo("actBatch:1:77507");
	}
	
	
	private static void test_json()throws Exception{
		Map map=new HashMap();
		String tt="kkkk:dddd\"";
		System.out.println(tt);
		String tt1=string2Json(tt);
		System.out.println(tt1);
		map.put("a", "123");
		map.put("b", tt);
		String str="{\"aaa\":\"1\",\"bbb\":\""+tt1+"\"}";
		System.out.println(str);
		Map map2=new ObjectMapper().readValue(str, Map.class);
		
		System.out.println(map2);
		
	}
	
	static String string2Json(String s) {     
	    StringBuffer sb = new StringBuffer ();     
	    for (int i=0; i<s.length(); i++) {     
	        char c = s.charAt(i);     
	        switch (c) {     
	        case '\"':     
	            sb.append("\\\"");     
	            break;
	        case '\\':     
	            sb.append("\\\\");     
	            break;     
	        case '/':     
	            sb.append("\\/");     
	            break;     
	        case '\b':     
	            sb.append("\\b");     
	            break;     
	        case '\f':     
	            sb.append("\\f");     
	            break;     
	        case '\n':     
	            sb.append("\\n");     
	            break;     
	        case '\r':     
	            sb.append("\\r");     
	            break;     
	        case '\t':     
	            sb.append("\\t");     
	            break;     
	        default:     
	            sb.append(c);     
	        }
	    }
	    return sb.toString();
	 }  

	
	private static void test_sysInfo()throws Exception{
		SysInfoService sis =(SysInfoService)ctx.getBean("sysInfoServiceImpl");	
		OSInfoVO os=sis.getOSInfo();
		System.out.println(os);
	}	
	
	private static void test_countWorkListForTemplate()throws Exception{
		ActQueryWorkListService qq =(ActQueryWorkListService)ctx.getBean("actQueryWorkListServiceImpl");	
		String userId="admin001";
		qq.countCandiWorkByTemplate(userId);
	}	
	
	
	private static void test_getAllTemplateStream()throws Exception{
		ProcessCustomService pc =(ProcessCustomService)ctx.getBean("processCustomService");	
		SimpleDateFormat sf=new SimpleDateFormat("yyyyMMddHHmm");
		Date now=new Date();
		String time=sf.format(now);
		String fileName=time+".zip";
		byte[]  b=pc.getAllTemplateStream( "D:\\log",fileName);
		System.out.println(b);
	}
	
	private static void test_findnextActity() throws BpmException{
		String taskId="280016";
		ActTaskService ts =(ActTaskService)ctx.getBean("actTaskService");	
		SelectUserInfos sus=ts.findNextActivityUserInfo(taskId);
		System.out.println(sus);
	}
	
//	private static void test_bsh() throws EvalError {
//		Interpreter interpreter = new Interpreter();  
//		interpreter.set("abc", "1");  
//		interpreter.set("bbb", 5);
//		String s = "${abc>=\"2\"}"; 
//		s=s.replace("${", "(");
//		s=s.substring(0, s.indexOf("}"));
//		s=s+")";
//		System.out.println(s);
//		System.out.println(interpreter.eval(s));
//		
//		
//		
//	}
	
	
	private static void test_o2j() throws JsonProcessingException{
		Page page=new Page();
		page.setPageNo(0);
		page.setPageSize(10);
		ActWorkListPage alp=new ActWorkListPage();
		alp.setPage(page);
		ObjectMapper mo=new  ObjectMapper();
		String str=mo.writeValueAsString(alp);
		System.out.println(str);
	}
	
	
	private static void test_endProcess() throws BpmException{
		ProcessCustomService pu =(ProcessCustomService)ctx.getBean("processCustomService");
		pu.endProcess("6141");
	}
	
	private static void test_findGroupsByUser(){
		UserServiceImpl us =(UserServiceImpl)ctx.getBean("userServiceImpl");
		List<Group> gs=us.findGroupsByUser("admin001");
		for(Group g:gs){
			System.out.println("group is :"+g.getName());
		}
	}
	
	private static void test_selectGroupDBIDById(){
		UserServiceImpl us =(UserServiceImpl)ctx.getBean("userServiceImpl");
		us.selectGroupDBIDById("manager001");
	}
	
	private static void test_deleteUser(){
		UserServiceImpl us =(UserServiceImpl)ctx.getBean("userServiceImpl");
		us.deleteUser("admin001");
	}
	
	private static void test_deleteGroup(){
		UserServiceImpl us =(UserServiceImpl)ctx.getBean("userServiceImpl");
		us.deleteGroup("group001");
	}
	
	
	private static void test_deleteMembership(){
		UserServiceImpl us =(UserServiceImpl)ctx.getBean("userServiceImpl");
		us.deleteMembership("admin001", "group001");
	}
	
	private static void test_createMembership(){
		UserServiceImpl us =(UserServiceImpl)ctx.getBean("userServiceImpl");
		us.createMembership("admin001", "group001");
	}
	private static void  test_createGroup(){
		UserServiceImpl us =(UserServiceImpl)ctx.getBean("userServiceImpl");
		us.createGroup("group001", null);
	}
	
	private static void test_updateUserPassword(){
		UserServiceImpl us =(UserServiceImpl)ctx.getBean("userServiceImpl");
		us.updateUserPassword("admin001", "000000");
	}
	
	private static void test_createUsers(){
		UserServiceImpl us =(UserServiceImpl)ctx.getBean("userServiceImpl");
		
		for(int k=0;k<1;k++){
			for(int j=0;j<10;j++){
				us.createGroup("group"+new Integer(k).toString()+new Integer(j).toString(), null);
				for(int i=0;i<3;i++){
					us.createUser("admin"+new Integer(k).toString()+new Integer(j).toString()+new Integer(i).toString(), new Integer(i).toString(), new Integer(j).toString(), "manager"+i+"@163.com","123");
					us.createMembership("admin"+k+j+i, "group"+new Integer(k).toString()+new Integer(j).toString());
					System.out.println("createUser:"+ "admin"+new Integer(k).toString()+new Integer(j).toString()+new Integer(i).toString());
				}
			}
		}
			
			
	}
	
	private static void test_createUser(){
		UserServiceImpl us =(UserServiceImpl)ctx.getBean("userServiceImpl");
		us.createUser("admin001", "wzy", "gunjack", "wzy@mail.com", "123");
		
//		for(int k=0;k<1;k++){
//			for(int j=0;j<1;j++){
//				us.createGroup("manager"+new Integer(k).toString()+new Integer(j).toString(), null);
//				for(int i=0;i<3;i++){
//					us.createUser("manager"+new Integer(k).toString()+new Integer(j).toString()+new Integer(i).toString(), new Integer(i).toString(), new Integer(j).toString(), "manager"+i+"@163.com","123");
//					us.createMembership("manager"+k+j+i, "manager"+new Integer(k).toString()+new Integer(j).toString());
//					us.updateUserPassword("manager"+k+j+i, "123");
//					System.out.println("createUser:"+ "manager"+new Integer(k).toString()+new Integer(j).toString()+new Integer(i).toString());
//				}
//			}
//		}
			
			
	}
	private static void test_queryTaskList(){
		
		ActQueryWorkListService qw =(ActQueryWorkListService)ctx.getBean("actQueryWorkListServiceImpl");
		Map _variables=new HashMap();
//		_variables.put("name","wzy");
//		_variables.put("bizNo","BIZ-00001");
		Page<Map> page = new Page<Map>();  
		page.setPageNo(1);  
		page.setPageSize(5);  
		page.setStart_index(0);
		try {
			 ActWorkListPage alp1=qw.queryWorkList("admin001", _variables,page);
			
			
			System.out.println("queryWorkList==="+alp1);
			
			if(null!=alp1){
				for(ActWorkListVO task :alp1.getActWorkListVOs()){
					System.out.println("task==="+task.toString());
				}
			}
			System.out.println("__________________________");
			ActWorkListPage alp=qw.queryCandiWorkList("admin001", _variables, page);
			
			System.out.println("queryCandiWorkList==="+alp);
			if(null!=alp){
				for(ActWorkListVO task :alp.getActWorkListVOs()){
					System.out.println("task==="+task.toString());
				}
			}		
			
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
private static void test_queryHisProcessList(){
		
		ActQueryWorkListService qw =(ActQueryWorkListService)ctx.getBean("actQueryWorkListService");
		Map _variables=new HashMap();
//		_variables.put("name","wzy");
//		_variables.put("bizNo","BIZ-00001");
		Page<Map> page = new Page<Map>();  
		page.setPageNo(1);  
		page.setPageSize(5);  
		page.setStart_index(0);
		try {
			ActHisProcessListPage alp1=qw.queryHisList("admin001", _variables,page);
			
			
			System.out.println("queryWorkList==="+alp1);
			
			if(null!=alp1){
				for(ActHisProcessListVO task :alp1.getActHisProcessListVOs()){
					System.out.println("task==="+task.toString());
				}
			}
			System.out.println("__________________________");
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	private static void test_resumeProcess()throws BpmException{
		ProcessService psi =(ProcessService)ctx.getBean("actProcessService");
		psi.resumeProcess("1");
	}
	
	private static void test_suspendProcess() throws BpmException{
		ProcessService psi =(ProcessService)ctx.getBean("actProcessService");
		psi.suspendProcess("10752", "000");
	}
	
	private static void test_replaceTask() throws BpmException{
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
		TaskService_ ts =(TaskService_)ctx.getBean("actTaskService_");	
		Task task=ts.findTaskById("5862");	
//		ts.replaceTask(task, null, null);
	}
	
	private static void test_submitNomalTask(){
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
		TaskService_ ts =(TaskService_)ctx.getBean("actTaskService_");	
//		ts.takeTask("10751", "admin001");
		Task task=ts.findTaskById("99998");
		Map<String, Object> _variables =new HashMap<String, Object>();
		_variables.put("county2", "china");
		_variables.put("userId", "admin001");
		ts.submitNomalTask(task, _variables);
	}
	private static void test_findProcessIdByTask() throws BpmException{
//		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
//		TaskService_ ts =(TaskService_)ctx.getBean("actTaskService_");	
//		Task task=ts.findTaskById("12");
//		org.activiti.engine.runtime.ProcessInstance pi=ts.findProcessIdByTask(task);
//		System.out.println("***********************");
//		System.out.println("ActivityId==="+pi.getActivityId());
//		System.out.println("DeploymentId==="+pi.getDeploymentId());
//		System.out.println("BusinessKey==="+pi.getBusinessKey());
//		System.out.println("Id==="+pi.getId());
//		System.out.println("Name=="+pi.getName());
//		System.out.println("ProcessDefinitionVersion==="+pi.getProcessDefinitionVersion());
//		System.out.println("ProcessDefinitionId==="+pi.getProcessDefinitionId());
//		System.out.println("ProcessDefinitionKey==="+pi.getProcessDefinitionKey());	
	}
	
	
	private static void test_saveTask(){
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
		TaskService_ ts =(TaskService_)ctx.getBean("actTaskService_");	
		Task task=ts.findTaskById("6480");
		Map<String, Object> _variables =new HashMap<String, Object>();
		_variables.put("county2", "china");
		_variables.put("opinion", "意见意见意见意见意见意见意见意见");
		_variables.put("userId", "admin001");
		
//		ts.saveTask(task, _variables);
	}
	
	private static void test_takeTask(){
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
		TaskService_ ts =(TaskService_)ctx.getBean("actTaskService_");	
		Task task=ts.findTaskById("10843");
		Map<String, Object> _variables =new HashMap<String, Object>();
		_variables.put("userId", "admin003");
		ts.takeTask(task, "", _variables);
		
	}
	
	private static void test_findTaskById(){
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
		TaskService_ ts =(TaskService_)ctx.getBean("actTaskService_");	
		Task task=ts.findTaskById("12");
		System.out.println("***********************");
		System.out.println("Assignee==="+task.getAssignee());
		System.out.println("Description==="+task.getDescription());
		System.out.println("ExecutionId==="+task.getExecutionId());
		System.out.println("Id==="+task.getId());
		System.out.println("Name==="+task.getName());
		System.out.println("Owner==="+task.getOwner());
		System.out.println("ProcessDefinitionId==="+task.getProcessDefinitionId());
		System.out.println("ProcessInstanceId==="+task.getProcessInstanceId());
		System.out.println("TaskDefinitionKey==="+task.getTaskDefinitionKey());
		System.out.println("CreateTime==="+task.getCreateTime());
		System.out.println("DueDate==="+task.getDueDate());
		System.out.println("Category==="+task.getCategory());
		
		
		
	}
	
	
	
	private static void test_cancelProcessByProcessId(){
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
		ProcessService psi =(ProcessService)ctx.getBean("actProcessService");	
		psi.cancelProcessByProcessId("5865");
		
	}
	
	
	private static void test_findProcessIdByBizId() throws BpmException{
//		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
//		ProcessService psi =(ProcessService)ctx.getBean("actProcessService");		
//		org.activiti.engine.runtime.ProcessInstance pi=psi.findProcessIdByBizId("biz-001");
//		System.out.println("***********************");
//		System.out.println("ActivityId==="+pi.getActivityId());
//		System.out.println("DeploymentId==="+pi.getDeploymentId());
//		System.out.println("BusinessKey==="+pi.getBusinessKey());
//		System.out.println("Id==="+pi.getId());
//		System.out.println("Name=="+pi.getName());
//		System.out.println("ProcessDefinitionVersion==="+pi.getProcessDefinitionVersion());
//		System.out.println("ProcessDefinitionId==="+pi.getProcessDefinitionId());
//		System.out.println("ProcessDefinitionKey==="+pi.getProcessDefinitionKey());		
	}
	private static void test_findProcessByPId() throws BpmException{
//		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
//		ProcessService psi =(ProcessService)ctx.getBean("actProcessService");		
//		org.activiti.engine.runtime.ProcessInstance pi=psi.findProcessByPId("22501");
//		System.out.println("***********************");
//		System.out.println("ActivityId==="+pi.getActivityId());
//		System.out.println("DeploymentId==="+pi.getDeploymentId());
//		System.out.println("BusinessKey==="+pi.getBusinessKey());
//		System.out.println("Id==="+pi.getId());
//		System.out.println("Name=="+pi.getName());
//		System.out.println("ProcessDefinitionVersion==="+pi.getProcessDefinitionVersion());
//		System.out.println("ProcessDefinitionId==="+pi.getProcessDefinitionId());
//		System.out.println("ProcessDefinitionKey==="+pi.getProcessDefinitionKey());
		
		
	}
	
	private static void test_saveVariables() throws BpmException{
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
		ProcessService psi =(ProcessService)ctx.getBean("actProcessService");		
		Map<String, Object> _variables =new HashMap<String, Object>();
		_variables.put("age", 20);
		psi.saveVariables("22501", _variables, true);
	}
	
	
	private static void test_startProcess() throws BpmException{
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
		ProcessService psi =(ProcessService)ctx.getBean("actProcessService");
		Map<String, Object> _variables =new HashMap<String, Object>();
		_variables.put("customerName", "wzy");
		_variables.put("age", 18);
		_variables.put("sex", 1);
		_variables.put("group", "group00");
		_variables.put("name", "admin001");
		
		String pid=psi.startProcess("admin001","myProcess", "biz-001", _variables);
		System.out.println("processInstanceId==="+pid);
		
	}
	
	
	private static void  test_readResourceStream() throws BpmException{
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
		ProcessDefinitionService psi =(ProcessDefinitionService)ctx.getBean("actProcessDefinitionService");
		ModelExportVO is=psi.readResourceStream("myProcess2:1:10004", "bpmn");
		System.out.println("InputStream==="+is.toString());
		
	}
	
	private static void test_countProcess(){
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
		ProcessDefinitionService psi =(ProcessDefinitionService)ctx.getBean("actProcessDefinitionService");
		long count=psi.countDeployment("My process2");
		System.out.println("count==="+count);
	}
	
	
	
	private static void test_delProcess(){
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
		ProcessDefinitionService psi =(ProcessDefinitionService)ctx.getBean("actProcessDefinitionService");
		psi.deleteDeployment("15001", true);
		
		
	}
	
	private static void test_findProcess(){
		Page<Map> page = new Page<Map>();  
		page.setPageNo(1);  
		page.setPageSize(5);  
		page.setStart_index(0);
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
		ProcessDefinitionService psi =(ProcessDefinitionService)ctx.getBean("actProcessDefinitionService");
//		List<ProcessDefinition> pds=psi.findProcessDefinitons("My process2", page);
//		for(ProcessDefinition pd:pds){
//			System.out.println("***********************");
//			System.out.println("DeploymentId==="+pd.getDeploymentId());
//			System.out.println("Id==="+pd.getId());
//			System.out.println("Key==="+pd.getKey());
//			System.out.println("Name==="+pd.getName());
//			System.out.println("ResourceName==="+pd.getResourceName());
//			System.out.println("Version==="+pd.getVersion());
//			System.out.println("==pd==="+pd.toString());
//		}
		
		
	}
	
	private static void test_depProcess(){
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:spring-context.xml");
		ProcessDefinitionService psi =(ProcessDefinitionService)ctx.getBean("actProcessDefinitionService");
		String fileName="D:/workspace/activiti_maven_project/src/main/resources/template/act_test1/act_test1.bpmn";
		File file = new File(fileName);
		try {
			psi.deploymentProcess(file, "FILE");
		} catch (BpmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	
	
	
	
	
	
	
	private static void test_sss(){
		String ss="鍟婂晩鍟婂晩鍟婂晩";
		for(int i=0;i<20;i++){
		byte[] aa=SerializeUtil.serialize(ss);
		}
	}
	
	private static void test_clearid(){
		String id="abc.11111.2222.333.to c3.4444.to c4.55555";
		clearProcessId(id);
	}
	
	private static String clearProcessId(String id){
		String tmp="";
		tmp=id.substring(0,id.indexOf("."));
		id=id.substring(id.indexOf(".")+1,id.length());
		do{
			if(id.indexOf(".to ")>0){
				tmp=tmp+"."+id.substring(0,id.indexOf(".to "));
				id=id.substring(id.indexOf(".to ")+4,id.length());
			}else if(id.indexOf(".")>0){
				tmp=tmp+"."+id.substring(0,id.indexOf("."));
				id=id.substring(id.indexOf(".")+1,id.length());
			}
		}while(id.indexOf(".to ")>0 || id.indexOf(".")>0);
		
		return tmp;
	}

	private static void test_ramdom(){
		
		Random r = new Random();
		for(int i=0;i<10000;i++){
			int n2 = r.nextInt(1001);
			n2 = Math.abs(r.nextInt() % 1001);
			System.out.println(n2);
		}
	}
	
	private static void test_PropertyUtil(){
		String value=PropertyUtil.getPropertis("processServiceExecuteTryAgainTime");
		System.out.println("value=========="+value);
	}
	
	private static void test_his(){
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath*:applicationContext.xml");
//		WfmidTaskInstanceMapperImpl rs =(WfmidTaskInstanceMapperImpl)ctx.getBean("wfmidTaskInstanceMapperImpl");
//		
//		int i=rs.insertTaskHis("b0ce73bd77a315774b94ada8dcbf2a1c");
//		JbpmProcessDefinitionService rs =(JbpmProcessDefinitionService)ctx.getBean("jbpmProcessDefinitionService");
//		rs.createProcessHis("test_create.22260025");
		
		System.out.println("ok");
	}
	
	
	private static void test_redis(){
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		RedisServiceImpl rs =(RedisServiceImpl)ctx.getBean("redisServiceImpl");
		
//		Map map=new HashMap();
//		map.put("wzy", "123");
//		map.put("wang", 1);
//		
//		 byte[] bb=SerializeUtil.serialize(map);
//		 String a=new String(bb);
//		 System.out.println("a=="+a);
//		
//		 Object obj2=SerializeUtil.unserialize(bb);
		 
//		boolean b=rs.exists("process.fork.activity.track.test_sync_fork_join2.2000171");
//		System.out.println("b=="+b);
		 
//		rs.set("cache.processinstance.data".getBytes(), SerializeUtil.serialize(map), 0);
		
//		 byte[]  aa=rs.gets("process.fork.activity.track.test_sync_fork_join2.170001");
		
		Set ss=rs.keys("process.fork.activity*");
		java.util.Iterator ii=ss.iterator();
		do{
			System.out.println("ii=="+ii.next());
			
		}while(ii.hasNext());
			
			
		
//		
//		
//		Object obj=SerializeUtil.unserialize(aa);
//		
//		String map2=(String)obj;
//		
//		System.out.println("aa=="+map2);
		
	}		
		
	
	
	
	
	
	


	private static void test_resumeTask(){
//		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:applicationContext.xml");
//		JbpmTaskControllerImpl ti=(JbpmTaskControllerImpl)ctx.getBean("jbpmTaskControllerImpl");
//		try {
//			ti.resumeTask("1550030");
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
	}
	
	private static void test_suspendTask(){
//		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:applicationContext.xml");
//		JbpmTaskControllerImpl ti=(JbpmTaskControllerImpl)ctx.getBean("jbpmTaskControllerImpl");
//		try {
//			ti.suspendTask("1550030",null);
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
	}
	private static void test_cancleProcess(){
//		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:applicationContext.xml");
//		JbpmProcessServiceImpl psi =(JbpmProcessServiceImpl)ctx.getBean("jbpmProcessServiceImpl");
//		
//		psi.cancelProcessByProcessId("wzy_test1.580022");
	}
	
	private static void test_cancelProcessByBizId(){
//		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:applicationContext.xml");
//		JbpmProcessServiceImpl psi =(JbpmProcessServiceImpl)ctx.getBean("jbpmProcessServiceImpl");
		
//		try {
//			psi.cancelProcessByBizId("BIZ-00006");
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
	}
	
	private static void testSaveTask(){
//		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:applicationContext.xml");
//		JbpmTaskControllerImpl ti=(JbpmTaskControllerImpl)ctx.getBean("jbpmTaskControllerImpl");
//		Map _variables=new HashMap();
//		_variables.put("userId","wzy");
//		_variables.put("Comment", "HHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHHH");
//		_variables.put("conclusion","PASS1");
//		try {
//			ti.saveTask("580018", _variables);
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
	}	
	
	
	private static void testHandleTaskById(){
//		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:applicationContext.xml");
//		JbpmTaskControllerImpl jp=(JbpmTaskControllerImpl)ctx.getBean("jbpmTaskControllerImpl");
//		Map _variables=new HashMap();
//		_variables.put("userId","wzy");
//		try {
//			jp.handleTaskById("580018");
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
	}	
	
	
	private static void test_createProcess(){
		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:applicationContext.xml");
		ActProcessService psi =(ActProcessService)ctx.getBean("actProcessService");
		Map _variables=new HashMap();
		_variables.put("userNum","wzy");
		_variables.put("bizId","BIZ-00001");
		_variables.put("customerName","钖涘嘲");
		_variables.put("customerNum","250");
		_variables.put("orgCd","1");
		_variables.put("roleCd","A");
		_variables.put("userId","no.1");
		_variables.put("userName","wzy");
		_variables.put("wfuser","wzy");
		
		Map _variables2=new HashMap();
		_variables2.put("save","123");
		
		String wid="";
//		try {
////			String pid=psi.createProcessByKey("wzy_test2", _variables);
////			psi.saveVariables(pid, _variables2, true);
//		} catch (WorkFLowException e) {
//			
//			e.printStackTrace();
//		} catch (Exception e) {
//			
//			e.printStackTrace();
//		}
	}
}
