package cn.com.git.udmp.bizflow;

import cn.com.git.udmp.bizflow.data.DataObject;
/**
 * 流程的消息对象
 * <p>在流程中各个活动或处理器中都将使用此对象进行参数传递
 * <p>此对象并非一次请求只有一个消息传递对象，调用下一个处理器后，
 * 并不能直接使用其引用直接获得返回值
 *
 * @author updated by Spring Cao
 *
 */
public class FlowMessage {
	/**
	 * 消息负载对象，
	 * <p>此对象存放着当前处理器的输入参数或输出参数
	 *
	 */
	private Object payload = null;
	/**
	 * 此次流程请求会话
	 */
	private FlowSession session = null;

	public FlowMessage(FlowSession flowSession){
		this(flowSession, null);
	}

	public FlowMessage(FlowSession session,DataObject payload){
		this.session = session;
		this.payload = payload;
	}

	public Object getPayload() {
		return payload;
	}

	public void setPayload(Object payload) {
		this.payload = payload;
	}

	public FlowSession getSession() {
		return session;
	}

	public void setSession(FlowSession flowSession) {
		this.session = flowSession;
	}
}
