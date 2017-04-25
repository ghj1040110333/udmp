package cn.com.git.udmp.bizflow.config.parser;

import org.dom4j.Element;

import cn.com.git.udmp.bizflow.config.BeanDefinition;
import cn.com.git.udmp.bizflow.config.BeanDefinitionBuilder;
import cn.com.git.udmp.bizflow.config.BeanDefinitionParser;
import cn.com.git.udmp.bizflow.config.ParserScope;
import cn.com.git.udmp.bizflow.line.Line;
/**
 * 连线的解析器
 * @author updated by Spring Cao
 *
 */
public class LineDefinitionParser extends BeanDefinitionParser{

	@Override
	public Class<?> getBeanClass(Element el) {
		return Line.class;
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
		if("to".equals(name)){
			beandefinition.addProperty(name, beanDefinitionBuilder.parseRefElement(value));
		}
	}
}
