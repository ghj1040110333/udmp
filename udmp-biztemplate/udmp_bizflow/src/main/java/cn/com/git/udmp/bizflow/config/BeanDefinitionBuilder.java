package cn.com.git.udmp.bizflow.config;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Element;

/**
 * builder的创建者
 * @author updated by Spring Cao
 * @see ParserScope
 * @see BeanDefinitionParser
 * @see RefBeanDefinition
 * @see BeanDefinition
 */
public class BeanDefinitionBuilder {
	private ParserScope parserScope = null;

	public BeanDefinitionBuilder(ParserScope parserScope){
		this.parserScope = parserScope;
	}
	/***
	 * 通过配置节点创建一个 BeanDefinition
	 * @param ele bean的配置节点
	 * @return
	 */
	public BeanDefinition parseElement(Element ele){

		BeanDefinitionParser beanDefinitionParser = parserScope.getParserContext().getParser(ele.getName());
		if(beanDefinitionParser == null){
			throw new RuntimeException(ele.getName()+"没有配置解析器");
		}

		BeanDefinition beanDefinition = new BeanDefinition();
		beanDefinition.setClazz(beanDefinitionParser.getBeanClass(ele));
		beanDefinition.setName(beanDefinitionParser.getName(ele));
		beanDefinition.setFlowScope(parserScope.getFlowScope());
		beanDefinitionParser.parseAtrributes(parserScope, this, beanDefinition, ele);
		beanDefinitionParser.parseElements(parserScope, this, beanDefinition, ele);

		parserScope.register(beanDefinition);

		return beanDefinition;
	}
	/***
	 * 通过配置节点创建一个 BeanDefinition的list
	 * @param ele bean的配置节点
	 * @return
	 */
	public List<BeanDefinition> parseElementList(Element ele){
		List<BeanDefinition> lis = new ArrayList<BeanDefinition>();
		for(Iterator<?> it = ele.elementIterator();it.hasNext();){
			Element child = (Element) it.next();
			lis.add(parseElement(child));
		}
		return lis;
	}
	/***
	 * 通过配置节点创建一个 RefBeanDefinition
	 * @param ele bean的配置节点
	 * @return
	 * @see RefBeanDefinition
	 */
	public BeanDefinition parseRefElement(String name){
		BeanDefinition bd = new RefBeanDefinition();
		bd.setFlowScope(parserScope.getFlowScope());
		bd.setName(name);
		return bd;
	}


	public ParserScope getParserScope() {
		return parserScope;
	}

	public void setParserScope(ParserScope parserScope) {
		this.parserScope = parserScope;
	}
}