package cn.com.git.udmp.bizflow.config.parser;

import org.dom4j.Element;

import cn.com.git.udmp.bizflow.activity.LocalService;
import cn.com.git.udmp.bizflow.config.BeanDefinition;
import cn.com.git.udmp.bizflow.config.BeanDefinitionBuilder;
import cn.com.git.udmp.bizflow.config.ParserScope;
/**
 * 本地服务解析器
 * @author updated by Spring Cao
 *
 */
public class LocalServiceDefinitionParser extends ActivityDefinitionParser{

	@Override
	public Class<?> getBeanClass(Element el) {
		return LocalService.class;
	}

	@Override
	protected void parseAtrribute(ParserScope parserScope,
			BeanDefinitionBuilder beanDefinitionBuilder,
			BeanDefinition beandefinition, String name, String value) {
		if("beanName".equals(name)){
			beandefinition.addProperty(name, value);
		}else if("method".equals(name)){
			beandefinition.addProperty(name, value);
		}else{
			super.parseAtrribute(parserScope, beanDefinitionBuilder, beandefinition, name,
				value);
		}
	}
}
