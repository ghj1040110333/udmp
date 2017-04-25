package cn.com.git.udmp.bizflow;

import java.util.HashMap;
import java.util.Map;
/**
 * 流程会话
 * <p>此会话在一次的流程调用是唯一的
 * @author updated by Spring Cao
 *
 */
public class FlowSession {
	private Map<String, Object> properties = new HashMap<String, Object>();

	public void addProperty(String name,Object value){
		properties.put(name, value);
	}

	public Object getProperty(String name){
		return properties.get(name);
	}
}
