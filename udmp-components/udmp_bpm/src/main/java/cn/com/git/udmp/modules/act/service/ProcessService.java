
package cn.com.git.udmp.modules.act.service;

import java.util.List;
import java.util.Map;

import org.activiti.engine.impl.pvm.process.ActivityImpl;

/**
 * 任务相关的服务
 * @author guosg
 *
 */
public interface ProcessService {
	/**
	 * 启动一个流程
	 * @param procDefKey	流程定义的key
	 * @param businessId	流程的业务id
	 * @param todoListValue	流程待办和已办的列表数据
	 * @param vars	流程的参数
	 */
	public String startProcessor(String procDefKey, String businessId,Map<String, Object> vars);
	
	/**
	 * 完成一个任务
	 * @param taskId	任务id
	 * @param comment	备注
	 * @param todoListValue	流程待办和已办的列表数据
	 * @param vars	流程的参数
	 */
	public void complete(String taskId, String comment,Map<String, Object> vars);
	
	/** 
     * 根据当前任务ID，查询可以驳回的任务节点 
     * 注意：会签任务是无法进行驳回的
     *  
     * @param taskId 
     *            当前任务ID 
     */  
    public List<ActivityImpl> findBackAvtivity(String taskId);
	
	/** 
     * 驳回流程 
     * 注意：会签任务是无法进行驳回的
     *
     * @param taskId 
     *            当前任务ID 
     * @param activityId 
     *            驳回节点ID 
     * @param variables 
     *            流程存储参数 
     * @throws Exception 
     */  
    public void backProcess(String taskId, String activityId, Map<String, Object> variables);
    
    /**
     * 驳回 直接返回到上一个任务
     * @param taskId
     * @param variables
     */
    public void backProcess(String taskId, Map<String, Object> variables);
    
    
    /** 
     * 取回流程 
     *  
     * @param processId 
     *            当前实例的id 
     * @param taskDefId 
     * 			任务定义的id
     *             
     * @throws Exception 
     */
    public void callBackProcess(String processId, String taskDefId);
    
    
    /** 
     * 终止流程(特权人直接审批通过等) 
     *  
     * @param taskId 
     */  
    public void endProcess(String taskId);
    
    /** 
     * 转办流程 
     *  
     * @param taskId 
     *            当前任务节点ID 
     * @param userCode 
     *            被转办人Code 
     */  
    public void transferAssignee(String taskId, String userCode);
}
