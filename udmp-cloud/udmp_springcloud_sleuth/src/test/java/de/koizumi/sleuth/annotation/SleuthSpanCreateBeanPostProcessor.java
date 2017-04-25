package de.koizumi.sleuth.annotation;

import java.lang.reflect.Method;

import org.springframework.aop.aspectj.annotation.AspectJProxyFactory;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class SleuthSpanCreateBeanPostProcessor implements BeanPostProcessor {
	
	private SleuthSpanCreatorAdvice advice;

	@Autowired
	public SleuthSpanCreateBeanPostProcessor(SleuthSpanCreatorAdvice advice) {
		this.advice = advice;
	}
	
	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		boolean atLeastOneMethodAnnotated = false;

		for (Method method : bean.getClass().getMethods()) {
			if (SleuthAnnotationUtils.isMethodAnnotated(method)) {
				atLeastOneMethodAnnotated = true;
				break;
			}
		}
		
		if (!atLeastOneMethodAnnotated && (AopUtils.isAopProxy(bean) || AopUtils.isCglibProxy(bean) || AopUtils.isJdkDynamicProxy(bean))) {
			Class<?> beanTargetClass = AopUtils.getTargetClass(bean);
			for (Method method : beanTargetClass.getMethods()) {
				if (SleuthAnnotationUtils.isMethodAnnotated(method)) {
					atLeastOneMethodAnnotated = true;
					break;
				}
			}
		}

		if (!atLeastOneMethodAnnotated) {
			return bean;
		}
		/**
		 * 前面判断是否包含我们自定义的sleuth注解，若存在这里就返回一个代理示例，添加我们的aop advice（SleuthSpanCreatorAdvice）
		 */
		AspectJProxyFactory factory = new AspectJProxyFactory(bean);
		factory.addAspect(advice);
		Object proxy = factory.getProxy();
		return proxy;
	}

}
