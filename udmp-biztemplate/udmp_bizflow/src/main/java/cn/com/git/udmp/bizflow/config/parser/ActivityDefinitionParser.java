package cn.com.git.udmp.bizflow.config.parser;

import java.util.List;

import org.dom4j.Element;

import cn.com.git.udmp.bizflow.config.BeanDefinition;
import cn.com.git.udmp.bizflow.config.BeanDefinitionBuilder;
import cn.com.git.udmp.bizflow.config.BeanDefinitionParser;
import cn.com.git.udmp.bizflow.config.ParserScope;
/**
 * 活动的解析器的基类
 * @author updated by Spring Cao
 *
 */
public abstract class ActivityDefinitionParser extends BeanDefinitionParser{

	@Override
	protected void parseElement(ParserScope parserScope,
			BeanDefinitionBuilder beanDefinitionBuilder,
			BeanDefinition beandefinition, String name, Element el) {
		if("connections".equals(name)){
			List<BeanDefinition> lis = beanDefinitionBuilder.parseElementList(el);
			beandefinition.addProperty(name, lis);
		}
		if("requestChain".equals(name)){
			List<BeanDefinition> lis = beanDefinitionBuilder.parseElementList(el);
			beandefinition.addProperty(name, lis);
		}
		if("responseChain".equals(name)){
			List<BeanDefinition> lis = beanDefinitionBuilder.parseElementList(el);
			beandefinition.addProperty(name, lis);
		}
	}

	@Override
	protected void parseAtrribute(ParserScope parserScope,
			BeanDefinitionBuilder beanDefinitionBuilder,
			BeanDefinition beandefinition, String name, String value) {
		if("name".equals(name)){
			beandefinition.addProperty(name, value);
		}
	}
}
