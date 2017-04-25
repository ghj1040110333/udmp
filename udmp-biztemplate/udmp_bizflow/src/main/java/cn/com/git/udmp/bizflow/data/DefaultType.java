package cn.com.git.udmp.bizflow.data;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认标签类型
 * 用于支持可视化设计器
 * @author updated by Spring Cao
 * @version v1.0 2013-4-28
 */
public class DefaultType implements Type{
	private String name;
	private Class<?> instanceClass;

	private Map<String, Property> properties = new ConcurrentHashMap<String, Property>();
	private boolean dataObject;
	public DefaultType(){
	}

	public DefaultType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<Property> getProperties() {
		return properties.values();
	}

	public void addProperty(Property property){
		properties.put(property.getName(), property);
	}

	public Property getProperty(String name) {
		return properties.get(name);
	}

	public boolean isAtomic() {
		return properties.size()==0;
	}

	public Class<?> getInstanceClass() {
		return instanceClass;
	}

	public void setInstanceClass(Class<?> instanceClass) {
		this.instanceClass = instanceClass;
	}

	public boolean isDataObject() {
		return dataObject;
	}

	public void setDataObject(boolean dataObject) {
		this.dataObject = dataObject;
	}


}
