package cn.com.git.udmp.bizflow.config.parser;

import org.dom4j.Element;

import cn.com.git.udmp.bizflow.activity.ContentBaseRouter;
import cn.com.git.udmp.bizflow.config.BeanDefinition;
import cn.com.git.udmp.bizflow.config.BeanDefinitionBuilder;
import cn.com.git.udmp.bizflow.config.ParserScope;
/**
 * 内容路由的解析器
 * @author updated by Spring Cao
 *
 */
public class ContentBaseRouterDefinitionParser extends ActivityDefinitionParser{

	@Override
	public Class<?> getBeanClass(Element el) {
		return ContentBaseRouter.class;
	}

	@Override
	protected void parseAtrribute(ParserScope parserScope,BeanDefinitionBuilder beanDefinitionBuilder,
			BeanDefinition beandefinition,String name,String value) {
		if("targetName".equals(name)){
			beandefinition.addProperty(name,value);
		}else
			super.parseAtrribute(parserScope,beanDefinitionBuilder,beandefinition,name,value);
	}
}
