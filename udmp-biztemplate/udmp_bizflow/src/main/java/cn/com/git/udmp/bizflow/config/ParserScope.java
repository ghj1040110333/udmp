package cn.com.git.udmp.bizflow.config;

import java.util.ArrayList;
import java.util.List;

import cn.com.git.udmp.bizflow.FlowScope;
/**
 * 流程范围内的解析上下文
 * <p>此上下文注册着当前流程中的所有BeanDefinition
 * @author updated by Spring Cao
 * @see ParserContextFactory
 * @see FlowScope
 * @see ParserContext
 * @see BeanDefinition
 */
public class ParserScope {
	FlowScope flowScope = null;
	ParserContext parserContext = null;
	List<BeanDefinition> beanDefinitions = new ArrayList<BeanDefinition>();

	public ParserScope() {
		parserContext = ParserContextFactory.getParserContext();
	}

	public void register(BeanDefinition beanDefinition){
		beanDefinitions.add(beanDefinition);
	}

	public void flush(){
		for(BeanDefinition definition: beanDefinitions){
			definition.buildObject();
		}

		for(BeanDefinition definition: beanDefinitions){
			definition.buildDependency();
		}
	}

	public FlowScope getFlowScope() {
		return flowScope;
	}

	public void setFlowScope(FlowScope flowScope) {
		this.flowScope = flowScope;
	}

	public ParserContext getParserContext() {
		return parserContext;
	}

	public void setParserContext(ParserContext parserContext) {
		this.parserContext = parserContext;
	}
}
