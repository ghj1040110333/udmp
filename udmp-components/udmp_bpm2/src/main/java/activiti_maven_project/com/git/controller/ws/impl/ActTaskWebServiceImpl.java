package activiti_maven_project.com.git.controller.ws.impl;

import java.util.List;
import java.util.Map;

import javax.jws.WebMethod;

import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import activiti_maven_project.com.git.common.exception.BpmException;
import activiti_maven_project.com.git.common.vo.ActFromVO;
import activiti_maven_project.com.git.common.vo.ActProcessInstanceVO;
import activiti_maven_project.com.git.common.vo.SelectUserInfos;
import activiti_maven_project.com.git.controller.ws.ActTaskWebService;
import activiti_maven_project.com.git.service.TaskService_;

@Component("actTaskWebServiceImpl")
public class ActTaskWebServiceImpl extends BaseWebService implements ActTaskWebService {

	@Autowired(required = true)
	private TaskService_ taskService_;
	
	@Override
	@WebMethod(operationName="findTaskFormInfo")
	public String findTaskFormInfo(String _variablesJson) throws BpmException {
		String requiredStr="userId,taskId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String taskId=(String)map.get("taskId");
		map.remove("taskId");
		ActFromVO fromVO=taskService_.findTaskFormInfo(taskId);
		Map returnObject=objectToMap(fromVO);
		String json=map2Jackson(returnObject);
		return json;
	}

	@Override
	@WebMethod(operationName="findNextActivityUserInfo")
	public String findNextActivityUserInfo(String _variablesJson) throws BpmException {
		String requiredStr="userId,taskId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String taskId=(String)map.get("taskId");
		map.remove("taskId");
		SelectUserInfos  suis=taskService_.findNextActivityUserInfo(taskId);
		Map returnObject=objectToMap(suis);
		String json=map2Jackson(returnObject);
		return json;
	}

	@Override
	@WebMethod(operationName="suspendTask")
	public void suspendTask(String _variablesJson) throws BpmException {
		String requiredStr="userId,taskId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String taskId=(String)map.get("taskId");
		map.remove("taskId");
		taskService_.suspendTask(taskId,null);
	}

	@Override
	@WebMethod(operationName="resumeTask")
	public void resumeTask(String _variablesJson) throws BpmException {
		String requiredStr="userId,taskId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String taskId=(String)map.get("taskId");
		map.remove("taskId");
		taskService_.resumeTask(taskId);

	}

	@Override
	@WebMethod(operationName="handleTaskById")
	public String handleTaskById(String _variablesJson) throws BpmException {
		String requiredStr="userId,taskId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String taskId=(String)map.get("taskId");
		map.remove("taskId");
		Map<String, Object>  returnMap=taskService_.handleTaskById(taskId);
		String json=map2Jackson(returnMap);
		return json;
	}

	@Override
	@WebMethod(operationName="delegateTask")
	public void delegateTask(String _variablesJson) throws BpmException {
		String requiredStr="userId,taskId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String taskId=(String)map.get("taskId");
		map.remove("taskId");
		taskService_.delegateTask(taskId,map);
	}

	@Override
	@WebMethod(operationName="takeTask")
	public void takeTask(String _variablesJson) throws BpmException {
		String requiredStr="userId,taskId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String taskId=(String)map.get("taskId");
		map.remove("taskId");
		taskService_.takeTask(taskId,userId);

	}

	@Override
	@WebMethod(operationName="replaceTask")
	public void replaceTask(String _variablesJson) throws BpmException {
		String requiredStr="userId,taskId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String taskId=(String)map.get("taskId");
		map.remove("taskId");
		taskService_.replaceTask(taskId,map);

	}

	@Override
	@WebMethod(operationName="gotoTask")
	public void gotoTask(String _variablesJson) throws BpmException {
		
		String requiredStr="userId,taskId,desTaskDefinitionKey";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String taskId=(String)map.get("taskId");
		map.remove("taskId");
		String desTaskDefinitionKey=(String)map.get("desTaskDefinitionKey");
		map.remove("desTaskDefinitionKey");
		taskService_.gotoTask(taskId,desTaskDefinitionKey);

	}

	@Override
	@WebMethod(operationName="saveTask")
	public void saveTask(String _variablesJson) throws BpmException {
		String requiredStr="userId,taskId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String taskId=(String)map.get("taskId");
		map.remove("taskId");
		taskService_.saveTask(taskId, map);
	}

	@Override
	@WebMethod(operationName="findCommentsForTask")
	public String findCommentsForTask(String _variablesJson) throws BpmException {
		String requiredStr="userId,taskId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String taskId=(String)map.get("taskId");
		map.remove("taskId");
		String comment=taskService_.findCommentsForTask(taskId);
		return "{\"comment\":\""+comment+"\"}";
	}

	@Override
	@WebMethod(operationName="findProcessIdByTask")
	public String findProcessIdByTask(String _variablesJson) throws BpmException {
		String requiredStr="userId,taskId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String taskId=(String)map.get("taskId");
		map.remove("taskId");
		ActProcessInstanceVO pi=taskService_.findProcessIdByTask(taskId);
		return object2Jackson(pi);
	}

	@Override
	@WebMethod(operationName="submitByBack")
	public void submitByBack(String _variablesJson) throws BpmException {
		
		String requiredStr="userId,taskId,type";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String taskId=(String)map.get("taskId");
		map.remove("taskId");
		String type=(String)map.get("type");
		map.remove("type");
		taskService_.submitByBack(taskId, type, map);
	}

	@Override
	@WebMethod(operationName="findTasksForActivityId")
	public String findTasksForActivityId(String _variablesJson) throws BpmException {
		String requiredStr="userId,executionId,taskDefinitionKey";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String taskDefinitionKey=(String)map.get("taskDefinitionKey");
		map.remove("taskDefinitionKey");
		String executionId=(String)map.get("executionId");
		map.remove("executionId");
		List<Task>  tasks=taskService_.findTasksForActivityId(taskDefinitionKey, executionId);
		return object2Jackson(tasks);
	}

	@Override
	@WebMethod(operationName="findFirstUserTask")
	public String findFirstUserTask(String _variablesJson) throws BpmException {
		String requiredStr="userId,taskId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String taskId=(String)map.get("taskId");
		map.remove("taskId");
		PvmActivity pa=taskService_.findFirstUserTask(taskId);
		return object2Jackson(pa);
	}

	@Override
	@WebMethod(operationName="getVeriablesByTask")
	public String getVeriablesByTask(String _variablesJson) throws BpmException {
		String requiredStr="userId,taskId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String taskId=(String)map.get("taskId");
		map.remove("taskId");
		Map<String, Object>  returnMap=taskService_.getVeriablesByTask(taskId);
		return map2Jackson(returnMap);
	}

}
