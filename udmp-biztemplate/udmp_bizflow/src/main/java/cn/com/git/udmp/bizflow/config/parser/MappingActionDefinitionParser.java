package cn.com.git.udmp.bizflow.config.parser;

import org.dom4j.Element;

import cn.com.git.udmp.bizflow.config.BeanDefinition;
import cn.com.git.udmp.bizflow.config.BeanDefinitionBuilder;
import cn.com.git.udmp.bizflow.config.BeanDefinitionParser;
import cn.com.git.udmp.bizflow.config.ParserScope;
import cn.com.git.udmp.bizflow.mapping.MappingAction;
/**
 * 映射活动解析器
 * @author updated by Spring Cao
 *
 */
public class MappingActionDefinitionParser extends BeanDefinitionParser{

	@Override
	public Class<?> getBeanClass(Element el) {
		return MappingAction.class;
	}

	@Override
	protected void parseElement(ParserScope parserScope,
			BeanDefinitionBuilder beanDefinitionBuilder,
			BeanDefinition beandefinition, String name, Element el) {
	}

	@Override
	protected void parseAtrribute(ParserScope parserScope,
			BeanDefinitionBuilder beanDefinitionBuilder,
			BeanDefinition beandefinition, String name, String value) {
		if("path".equals(name)){
			beandefinition.addProperty(name, value);
		}
	}
}
