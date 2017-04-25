package cn.com.git.udmp.bizflow.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.bizflow.FlowMessage;
import cn.com.git.udmp.bizflow.FlowProcessor;
import cn.com.git.udmp.bizflow.FlowScope;
import cn.com.git.udmp.bizflow.FlowScopeAware;
import cn.com.git.udmp.bizflow.Nameable;
import cn.com.git.udmp.bizflow.action.Action;
import cn.com.git.udmp.bizflow.lifecycle.Initialisable;
import cn.com.git.udmp.bizflow.line.Line;
/**
 * 活动是由共同目的联合起来并完成一定的动作的总和。
 * <p>一个流程由几个活动进行排编获得的，活动与活动之间通过Line进行连接
 * <p>一个活动由多个处理组成，活动的核心职能会把处理器分为requestChain和responseChain
 * <p>例：本地处理器的核心职能是调用本地服务，但这个活动下还有输入报文映射和输出报文的映射动作
 * 这两个映射都会被会配在requestChain和responseChain
 * <p>requestChain与responseChain是在执行职能之前和之后顺序调用的动作的集合
 * @author updated by Spring Cao
 * @see Line
 * @see Action
 *
 */
public abstract class Activity implements Nameable,FlowScopeAware,FlowProcessor,Initialisable{
	/**连线的集合*/
	private List<Line> connections;
	/**流动的名称*/
	private String name = null;

	/***/
	private FlowScope flowScope = null;
	/**请求动作动理链*/
	private List<FlowProcessor> requestChain = new ArrayList<FlowProcessor>();
	/**响应动作动理链*/
	private List<FlowProcessor> responseChain = new ArrayList<FlowProcessor>();
	/**
	 * 连线连接的下一个活动的名称与连线的集合
	 * <p>此集合只是为了更加方便的查找连线
	 */
	private Map<String, Line> lineMap = new HashMap<String, Line>();


	public FlowMessage process(FlowMessage flowMessage) throws Exception {
		beforeProcess(flowMessage);

		for(FlowProcessor processor : requestChain){
			flowMessage = processor.process(flowMessage);
		}
		flowMessage =  doProcess(flowMessage);

		for(FlowProcessor processor : responseChain){
			flowMessage = processor.process(flowMessage);
		}

		afterProcess(flowMessage);

		return flowMessage;
	}
	/**
	 * 通过连线连接的下一个活动名称来查找连线
	 * @param toName
	 * @return
	 */
	public Line findLine(String toName){
		return lineMap.get(toName);
	}
	/**
	 * 活动一被请求进行调用
	 * <p>此调用在请求链调用之前
	 * @param flowMessage
	 * @return
	 */
	public FlowMessage beforeProcess(FlowMessage flowMessage){
		return flowMessage;
	}
	/**
	/**
	 * 在活动进行响应时进行调用
	 * <p>响应链调用之后进行调用
	 * @param flowMessage
	 * @return
	 * @throws Exception
	 */
	public FlowMessage afterProcess(FlowMessage flowMessage) throws Exception{
		return flowMessage;
	}
	/**
	 * 活动的核心动作的实现
	 * @param flowMessage
	 * @return
	 * @throws Exception
	 */
	protected abstract FlowMessage doProcess(FlowMessage flowMessage) throws Exception;

	public List<Line> getConnections() {
		return connections;
	}
	public void setConnections(List<Line> connections) {
		this.connections = connections;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FlowScope getFlowScope() {
		return flowScope;
	}
	public void setFlowScope(FlowScope flowScope) {
		this.flowScope = flowScope;
	}

	public void initialise() {
		if(connections!=null){
			for(Line connection : connections){
				lineMap.put(connection.getTo().getName(), connection);
			}
		}
	}

	public List<FlowProcessor> getRequestChain() {
		return requestChain;
	}

	public void setRequestChain(List<FlowProcessor> requestChain) {
		this.requestChain = requestChain;
	}

	public List<FlowProcessor> getResponseChain() {
		return responseChain;
	}

	public void setResponseChain(List<FlowProcessor> responseChain) {
		this.responseChain = responseChain;
	}



}
