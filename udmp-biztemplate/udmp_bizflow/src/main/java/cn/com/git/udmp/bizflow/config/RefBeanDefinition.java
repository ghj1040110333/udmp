package cn.com.git.udmp.bizflow.config;

/**
 * 引用一个BeanDefinition
 * @author updated by Spring Cao
 *
 */
public class RefBeanDefinition extends BeanDefinition{

	@Override
	public void buildObject() {
	}

	@Override
	public Object getBean() {
		return getFlowScope().getBean(getName());
	}
}
