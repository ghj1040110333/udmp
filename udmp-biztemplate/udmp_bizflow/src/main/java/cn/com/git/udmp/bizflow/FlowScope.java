package cn.com.git.udmp.bizflow;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import cn.com.git.udmp.bizflow.activity.StartActivity;
import cn.com.git.udmp.bizflow.lifecycle.Initialisable;
/**
 * 一个流程范围内的上下文
 * <p>一个流程中所有变量都是在当前流程内可见的。
 * <br>如果放在FlowContext中，那么其范围就扩大很多, 活动和动作只能全局唯一，
 * 很容易出问题。
 * @author updated by Spring Cao
 * @see FlowContext
 * @see Nameable
 * @see Initialisable
 * @see StartActivity
 */
public class FlowScope implements Nameable,Initialisable{
	/**流程名称*/
	String name = "";
	/**流程内的定义的对象*/
	private Map<String, Object> beans = new HashMap<String, Object>();
	/**流程的起点*/
	StartActivity start = null;
	/**流程上下文*/
	FlowContext flowContext = null;

	public Object getBean(String name){
		return beans.get(name);
	}

	public void registerBean(String name,Object value){

		if(value instanceof StartActivity){
			start = (StartActivity) value;
		}

		beans.put(name, value);
	}

	public StartActivity getStart() {
		return start;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public FlowContext getFlowContext() {
		return flowContext;
	}

	public void setFlowContext(FlowContext flowContext) {
		this.flowContext = flowContext;
	}

	public void initialise() {
		for(Entry<String, Object> entry : beans.entrySet()){
			Object v = entry.getValue();
			if(v instanceof Initialisable){
				((Initialisable) v).initialise();
			}
		}
	}

}
