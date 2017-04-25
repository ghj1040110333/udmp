package cn.com.git.udmp.bizflow.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.beans.BeanUtils;

import cn.com.git.udmp.bizflow.FlowScope;
import cn.com.git.udmp.bizflow.FlowScopeAware;
import cn.com.git.udmp.bizflow.Nameable;
/**
 * 通过解析配置文件来定一个Bean
 * <p>定义活动需要以下几个要素
 * <li>bean的class
 * <li>注册到上下文中的名称
 * <li>class中的属性，这些属性也可以是一个Bean定义对象
 * @author updated by Spring Cao
 * @see FlowScope
 * @see FlowScopeAware
 * @see Nameable
 */
public class BeanDefinition implements Nameable{
	/***/
	private FlowScope flowScope;
	private String name;
	/**bin的class*/
	Class<?> clazz = null;
	/**创建出的bean*/
	Object bean = null;
	/**bean的属性定义*/
	Map<String, Object> propertyMap = new HashMap<String, Object>();
	/**
	 * 创建一个对象，创建的活动是在所有的配置文件解析后触发的
	 */
	public void buildObject(){
		bean = BeanUtils.instantiateClass(clazz);
		if(bean instanceof FlowScopeAware){
			((FlowScopeAware) bean).setFlowScope(flowScope);
		}
		flowScope.registerBean(name, bean);
	}
	/**
	 * 创建对象的依赖，这个活动是在创建所有的BeanDefinition对象创建活动完成后创建的
	 */
	public void buildDependency(){
		for(Entry<String, Object> entry : propertyMap.entrySet()){
			Object v = entry.getValue();
			String name = entry.getKey();
			if(v instanceof BeanDefinition){
				setProperty(name, ((BeanDefinition)v).getBean());
			} else if (v instanceof Collection){
				Collection<?> vs = (Collection<?>) v;
				Collection<Object> nv = new ArrayList<Object>();
				setProperty(name, nv);
				for(Object cv : vs){
					if(cv instanceof BeanDefinition){
						nv.add(((BeanDefinition)cv).getBean());
					}else{
						nv.add(cv);
					}
				}
			}else{
				setProperty(name, v);
			}
		}
	}
	/**
	 * 获得BeanDefinition创建的文件
	 * @return
	 */
	public Object getBean() {
		return bean;
	}

	private  void setProperty(String name,Object v){
		try {
			PropertyUtils.setProperty(bean, name, v);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
	}

	public void addProperty(String name,Object value){
		propertyMap.put(name, value);
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

	public Class<?> getClazz() {
		return clazz;
	}

	public void setClazz(Class<?> clazz) {
		this.clazz = clazz;
	}
}
