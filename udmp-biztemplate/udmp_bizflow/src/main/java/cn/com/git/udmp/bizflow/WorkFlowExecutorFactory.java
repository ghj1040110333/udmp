package cn.com.git.udmp.bizflow;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;
/**
 * 流程执行器工厂
 * <p>此类是一个spring集成的相关类，它需要spring上下文的支持
 * <p>此类通过注入flowConfigs路径来，创建一个flowContext，可以按要求进行易用性的改造
 * <p>工厂类已经保证当前工厂中创建的WorkFlowExecutor是唯一的
 *
 * @author updated by Spring Cao
 * @see WorkFlowExecutor
 * @see FlowContext
 * @see FlowScope
 * @see FlowContextFactory
 */
public class WorkFlowExecutorFactory implements BeanFactoryAware,InitializingBean{
	/**流程的配置文件*/
	private List<String> flowConfigs = new ArrayList<String>();
	/**流程的上下文*/
	FlowContext flowContext = null;

	BeanFactory beanFactory = null;
	/**流程执行器的cache，保证不重复的创建流程*/
	Map<String, WorkFlowExecutor> executorCache = new HashMap<String, WorkFlowExecutor>();

	/**
	 * 创建一个流程
	 * @param flowName 流程名称
	 * @return 返回一个流程处理器
	 */
	public WorkFlowExecutor createWorkFlowExecutor(String flowName){
		WorkFlowExecutor executor =getWorkflowExecutor(flowName);
		return executor;
	}
	/**
	 * 从cache中获得一个流程执行器，如果不存在则创建一个
	 * @param flowName
	 * @return
	 */
	private synchronized  WorkFlowExecutor getWorkflowExecutor(String flowName){
		WorkFlowExecutor executor = executorCache.get(flowName);
		if(executor == null){
			FlowScope flowScope = (FlowScope) flowContext.getBean(flowName);
			executor = new WorkFlowExecutor(flowScope);
		}
		return executor;
	}

	public List<String> getFlowConfigs() {
		return flowConfigs;
	}

	public void setFlowConfigs(List<String> flowConfigs) {
		this.flowConfigs = flowConfigs;
	}

	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		this.beanFactory = beanFactory;

	}

	
	/**
	 * 创建一个FlowContextFactory
	 */
	public void afterPropertiesSet() throws Exception {
		FlowContextFactory contextFactory = new FlowContextFactory(beanFactory);
		for(String flowConfig : flowConfigs){
			try {
				File file = ResourceUtils.getFile(flowConfig);
				contextFactory.addConfig(new FileInputStream(file));
			} catch (FileNotFoundException e) {
				throw new RuntimeException(flowConfig+"文件没有找到:"+e.getMessage(),e);
			}
		}
		flowContext = contextFactory.create();
	}


}
