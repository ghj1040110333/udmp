package activiti_maven_project.com.git.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.activiti.engine.ActivitiException;
import org.activiti.engine.EngineServices;
import org.activiti.engine.delegate.DelegateExecution;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.JavaDelegate;
import org.activiti.engine.delegate.TaskListener;
import org.activiti.engine.history.HistoricTaskInstance;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("sameUserTaskCheckBean")
public class SameUserTaskCheckBean  {
	
	private static Logger log = LoggerFactory.getLogger(SameUserTaskCheckBean.class);
	
		
	/**
	 * 校验指定 节点操作人与当前节点领取人员是否为同一人
	 * @param task
	 * @param activitys
	 * @param checkType
	 * Y:当前任务处理人必须是activitys节点任务处理人之一
	 * N:当前任务处理人不能是activitys节点任务处理人之一
	 * @throws Exception
	 */
	public void checkUser(DelegateTask task,String activitys,String checkType){
		EngineServices engineServices=task.getExecution().getEngineServices();
		//按照指定用户id分发任务
		if(StringUtils.isNotBlank(activitys)){
			Map<String,String> map=assigneeFromHistory(engineServices,task.getProcessInstanceId(), activitys);
			if(map.size()<1){
				return;
			}
			String actNames="";
			for(String key:map.keySet()){
				actNames=map.get(key)+actNames;
			}
			if(checkType.equals("Y") && !map.containsKey(task.getAssignee())){
				throw new ActivitiException("任务:["+task.getName()+"]领取人员不能为:["+task.getAssignee()+"],任务处理人必须与节点:["+actNames+"]处理人相同");
			}else if(checkType.equals("N") && map.containsKey(task.getAssignee())){
				throw new ActivitiException("任务:["+task.getName()+"]领取人员不能为:["+task.getAssignee()+"],任务处理人不能与节点:["+actNames+"]处理人相同");
			}
		}
		
	}
	
	
	/**
	 * 按历史记录查询指定任务处理人
	 * @param engineServices
	 * @param task
	 * @param execution
	 * @return
	 */
	private Map<String,String> assigneeFromHistory(EngineServices engineServices,String processId,String activitys){
		Map<String,String> map=new HashMap<String,String>();
		String[] activityIds=activitys.split(",");
		for(String activityId:activityIds){
			List<HistoricTaskInstance> htasks = engineServices.getHistoryService()
					.createHistoricTaskInstanceQuery().processInstanceId(processId)
					.taskDefinitionKey(activityId).orderByTaskCreateTime().desc().list();
			if(null!=htasks && htasks.size()>0){
				map.put(htasks.get(0).getAssignee(), htasks.get(0).getName());
			}
		}
		return map;
	}


}
