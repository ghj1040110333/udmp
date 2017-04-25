package cn.com.git.udmp.bizflow;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.BeanFactory;

import cn.com.git.udmp.bizflow.lifecycle.Initialisable;
/**
 * 流程上下文，其保存着流程中所有的配置信息，以及所有流程都需要使用的全局信息。
 * <p>此上下文，在系统中是单例的存在。
 * @author updated by Spring Cao
 *
 */
public class FlowContext implements Initialisable{
	/***
	 *
	 */
	Map<String, Object> properties = new HashMap<String, Object>();

	/**
	 * spring的bean工厂，此工厂在某些活动会用到，但并不使用其中载配置文件
	 */
	BeanFactory beanFactory;

	/**
	 * 获得一个全局bean
	 * @param name
	 * @return
	 */
	public Object getBean(String name){
		return properties.get(name);
	}
	/**
	 * 注册一个全局bean
	 * @param name
	 * @param value
	 */
	public void registerBean(String name,Object value){
		properties.put(name, value);
	}

	
	/**
	 * 此接口会循环所有的bean，查看是否有实现Initialisable的bean，如果有则进行回调
	 */
	@Override
	public void initialise() {
		for(Entry<String, Object> entry : properties.entrySet()){
			Object v = entry.getValue();
			if(v instanceof Initialisable){
				((Initialisable) v).initialise();
			}
		}
	}
	/**
	 * 获得消息工厂
	 * @return
	 */
	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	/**
	 * 设置消息工厂
	 * @param beanFactory
	 */
	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}
}
