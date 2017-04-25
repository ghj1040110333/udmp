package cn.com.git.udmp.bizflow;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.springframework.beans.factory.BeanFactory;

import cn.com.git.udmp.bizflow.utils.XmlUtils;
/**
 * 流程上下文工厂
 * <p>通过此工厂来加载流程的配置文件，并创建一个全局的上下文工厂
 * <p>当配置文件加载完毕后，由此工厂来回调其Initialisable接口
 * @author updated by Spring Cao
 *
 */
public class FlowContextFactory {
	/**
	 * 流程加载文件的输入流
	 */
	List<InputStream> configs = new ArrayList<InputStream>();

	BeanFactory beanFactory = null;

	public FlowContextFactory(BeanFactory beanFactory){
		this.beanFactory =beanFactory;
	}
	
	/**
	 * 创建消息工厂
	 * <p>此方法会在配置文件已经全部添加到工厂后才能调用
	 * @return
	 */
	public FlowContext create(){
		FlowScopeFactory scopeFactory = new FlowScopeFactory();
		FlowContext context =new FlowContext();
		context.setBeanFactory(this.beanFactory);
		for(InputStream config : configs){
			Document doc = XmlUtils.read(config);
			FlowScope flowScope = scopeFactory.create(doc, context);
			context.registerBean(flowScope.getName(), flowScope);

		}
		context.initialise();
		return context;
	}
	/**
	 * 增加一个配置文件
	 * @param in
	 */
	public void addConfig(InputStream in){
		configs.add(in);
	}

	public BeanFactory getBeanFactory() {
		return beanFactory;
	}

	public void setBeanFactory(BeanFactory beanFactory) {
		this.beanFactory = beanFactory;
	}

}
