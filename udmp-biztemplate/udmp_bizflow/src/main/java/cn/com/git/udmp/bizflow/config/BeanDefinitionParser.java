package cn.com.git.udmp.bizflow.config;

import java.util.Iterator;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Attribute;
import org.dom4j.Element;

/**
 * bean定义的解析类，解析类通过解析当前的配置节点，
 * 解析出实现的BeanDefinition的所有信息
 *
 * @author updated by Spring Cao
 *
 */
public abstract class BeanDefinitionParser {

	/**
	 * bean对应的class
	 * @param el
	 * @return
	 */
	public abstract Class<?> getBeanClass(Element el);

	/**
	 * 注册到flowScope中的bean的名称
	 * @param el
	 * @return
	 */
	public String getName(Element el) {
		String name = el.attributeValue("name");
		if (StringUtils.isBlank(name)) {
			name = UUID.randomUUID().toString();
		}
		return name;
	}

	/**
	 * 解析配置节点的属性
	 * @param parserScope
	 * @param beanDefinitionBuilder
	 * @param beandefinition
	 * @param el 当前的配置节点
	 */
	public void parseAtrributes(ParserScope parserScope,
			BeanDefinitionBuilder beanDefinitionBuilder,
			BeanDefinition beandefinition, Element el){
		for(Iterator<?> it = el.attributeIterator();it.hasNext();){
			Attribute attribute = (Attribute) it.next();
			String name = attribute.getName();
			String value = attribute.getValue();
			parseAtrribute(parserScope, beanDefinitionBuilder, beandefinition, name, value);
		}
	}
	/**
	 * 解析配置节点的子节点
	 * @param parserScope
	 * @param beanDefinitionBuilder
	 * @param beandefinition
	 * @param el 当前配置节点
	 */
	public void parseElements(ParserScope parserScope,
			BeanDefinitionBuilder beanDefinitionBuilder,
			BeanDefinition beandefinition, Element el){

		for(Iterator<?> it = el.elementIterator();it.hasNext();){
			Element child = (Element) it.next();
			String name = child.getName();
			parseElement(parserScope, beanDefinitionBuilder, beandefinition, name, child);
		}

	}
	/**
	 * 解析配置节点的子节点
	 * @param parserScope
	 * @param beanDefinitionBuilder
	 * @param beandefinition
	 * @param name
	 * @param el 子节点
	 */
	protected abstract void parseElement(ParserScope parserScope,
			BeanDefinitionBuilder beanDefinitionBuilder,
			BeanDefinition beandefinition, String name, Element el);

	/**
	 * 解析配置节点的属性
	 * @param parserScope
	 * @param beanDefinitionBuilder
	 * @param beandefinition
	 * @param name
	 * @param value
	 */
	protected abstract void parseAtrribute(ParserScope parserScope,
			BeanDefinitionBuilder beanDefinitionBuilder,
			BeanDefinition beandefinition, String name,String value);
}
