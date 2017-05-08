package activiti_maven_project.com.git.controller.ws.impl;

import java.util.Map;

import javax.jws.WebMethod;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import activiti_maven_project.com.git.common.exception.BpmException;
import activiti_maven_project.com.git.common.vo.ActProcessInstanceVO;
import activiti_maven_project.com.git.controller.ws.ActProcessWebService;
import activiti_maven_project.com.git.service.ProcessService;
@Component("actProcessWebServiceImpl")
public class ActProcessWebServiceImpl extends BaseWebService implements ActProcessWebService {
	@Autowired(required = true)
	private ProcessService processService;

	@Override
	@WebMethod(operationName="startProcess")
	public String startProcess(String _variablesJson) throws BpmException {
		String requiredStr="userId,processKey,bizId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
		String userId=(String)map.get("userId");
		map.remove("userId");
		String processKey=(String)map.get("processKey");
		map.remove("processKey");
		String bizId=(String)map.get("bizId");
		map.remove("bizId");
		String pid=processService.startProcess(userId, processKey, bizId, map);
		return "{\"processId\":\""+pid+"\"}";
	}

	@Override
	@WebMethod(operationName="suspendProcess")
	public void suspendProcess(String _variablesJson) throws BpmException {
		String requiredStr="userId,processId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
//		String userId=(String)map.get("userId");
		map.remove("userId");
		String processId=(String)map.get("processId");
		map.remove("processId");
		processService.suspendProcess(processId, null);

	}

	@Override
	@WebMethod(operationName="resumeProcess")
	public void resumeProcess(String _variablesJson) throws BpmException {
		String requiredStr="userId,processId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
//		String userId=(String)map.get("userId");
		map.remove("userId");
		String processId=(String)map.get("processId");
		map.remove("processId");
		processService.resumeProcess(processId);
	}

	@Override
	@WebMethod(operationName="saveVariables")
	public void saveVariables(String _variablesJson) throws BpmException {
		String requiredStr="userId,processId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
//		String userId=(String)map.get("userId");
		map.remove("userId");
		String processId=(String)map.get("processId");
		map.remove("processId");
		processService.saveVariables(processId, map, true);

	}

	@Override
	@WebMethod(operationName="findProcessByPId")
	public String findProcessByPId(String _variablesJson) throws BpmException {
		String requiredStr="userId,processId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
//		String userId=(String)map.get("userId");
		map.remove("userId");
		String processId=(String)map.get("processId");
		map.remove("processId");
		ActProcessInstanceVO pi=processService.findProcessByPId(processId);
		String json=object2Jackson(pi);
		return json;
	}

	@Override
	@WebMethod(operationName="cancelProcessByProcessId")
	public void cancelProcessByProcessId(String _variablesJson) throws BpmException{
		String requiredStr="userId,processId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
//		String userId=(String)map.get("userId");
		map.remove("userId");
		String processId=(String)map.get("processId");
		map.remove("processId");
		processService.cancelProcessByProcessId(processId);

	}

	@Override
	@WebMethod(operationName="findProcessIdByTaskId")
	public String findProcessIdByTaskId(String _variablesJson) throws BpmException {
		String requiredStr="userId,taskId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
//		String userId=(String)map.get("userId");
		map.remove("userId");
		String taskId=(String)map.get("taskId");
		map.remove("taskId");
		String pid=processService.findProcessIdByTaskId(taskId);
		return "{\"processId\":\""+pid+"\"}";
	}

	@Override
	@WebMethod(operationName="findProcessIdByBizId")
	public String findProcessIdByBizId(String _variablesJson) throws BpmException {
		String requiredStr="userId,bizId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
//		String userId=(String)map.get("userId");
		map.remove("userId");
		String bizId=(String)map.get("bizId");
		map.remove("bizId");
		String pid=processService.findProcessIdByBizId(bizId);
		return "{\"processId\":\""+pid+"\"}";
	}

	@Override
	@WebMethod(operationName="findVariables")
	public String findVariables(String _variablesJson) throws BpmException {
		String requiredStr="userId,processId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
//		String userId=(String)map.get("userId");
		map.remove("userId");
		String processId=(String)map.get("processId");
		map.remove("processId");
		Map<String,Object> variables=processService.findVariables(processId);
		String json=map2Jackson(variables);
		return json;
	}

	@Override
	@WebMethod(operationName="signalProcess")
	public void signalProcess(String _variablesJson) throws BpmException {
		String requiredStr="userId,processId";
		Map<String, Object> map=getInputDate(_variablesJson,requiredStr);
//		String userId=(String)map.get("userId");
		map.remove("userId");
		String processId=(String)map.get("processId");
		map.remove("processId");
		processService.signalProcess(processId,map);
	}

}
