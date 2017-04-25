package cn.com.git.udmp.bizflow;

import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.Element;

import cn.com.git.udmp.bizflow.config.BeanDefinitionBuilder;
import cn.com.git.udmp.bizflow.config.ParserScope;

/**
 * FlowScope工厂
 * <p>此工厂是线程安全的
 * <p>工厂通过配置文件的dom解析后，创建一个FlowScope
 * @author updated by Spring Cao
 * @see FlowScope
 * @see ParserScope
 * @see BeanDefinitionBuilder
 */
public class FlowScopeFactory {

	public FlowScope create(Document doc,FlowContext flowContext){
		FlowScope scope = new FlowScope();
		Element root = doc.getRootElement();
		scope.setName(getFlowName(root));
		scope.setFlowContext(flowContext);

		ParserScope parserScope = new ParserScope();
		parserScope.setFlowScope(scope);
		BeanDefinitionBuilder builder = new BeanDefinitionBuilder(parserScope);
		for(Iterator<?> it = root.elements().iterator();it.hasNext();){
			Element child = (Element) it.next();
			builder.parseElement(child);
		}

		parserScope.flush();

		return scope;
	}
	/**
	 * 获得一个
	 * @param ele
	 * @return
	 */
	protected String getFlowName(Element ele){
		String name = ele.attributeValue("name");
		if(StringUtils.isBlank(name)){
			throw new RuntimeException("flow.name不允许为空");
		}
		return name;
	}
}
