package cn.com.git.udmp.bizflow;

import cn.com.git.udmp.bizflow.data.DataObject;
/**
 * 流程执行器，通过此类来调用一个流程
 * <p>一个流程执行器对应着一个流程
 * <p>此类是线程安全的
 * @author updated by Spring Cao
 * @see FlowSession
 * @see FlowMessage
 */
public class WorkFlowExecutor {
	FlowScope flowScope = null;
	public WorkFlowExecutor(FlowScope flowScope) {
		this.flowScope = flowScope;
	}
	public DataObject runProcess(DataObject data) throws Exception{
		FlowSession flowSession = new FlowSession();
		FlowMessage flowMessage = new FlowMessage(flowSession,data);
		flowMessage = flowScope.getStart().process(flowMessage);
		return (DataObject) flowMessage.getPayload();
	}
}
