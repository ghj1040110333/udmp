package cn.com.git.udmp.bizflow;

import org.springframework.beans.factory.FactoryBean;
/**
 * 通过实现Spring的FactoryBean，为Bean自动注入一个WorkFlowExecutor
 * <p>此类只是为了更好的集成spring。
 * @author updated by Spring Cao
 * @see WorkFlowExecutorFactory
 */
public class SpringWorkFlowExecutorFactory implements FactoryBean{
	private String flowName;
	private WorkFlowExecutorFactory workFlowExecutorFactory;
	
	public Object getObject() throws Exception {
		return workFlowExecutorFactory.createWorkFlowExecutor(flowName);
	}

	public Class<?> getObjectType() {
		return WorkFlowExecutor.class;
	}

	public boolean isSingleton() {
		return true;
	}

	public String getFlowName() {
		return flowName;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public WorkFlowExecutorFactory getWorkFlowExecutorFactory() {
		return workFlowExecutorFactory;
	}

	public void setWorkFlowExecutorFactory(
			WorkFlowExecutorFactory workFlowExecutorFactory) {
		this.workFlowExecutorFactory = workFlowExecutorFactory;
	}

}
