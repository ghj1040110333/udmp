package cn.com.git.udmp.bizflow.config;

import cn.com.git.udmp.bizflow.config.parser.ContentBaseRouterDefinitionParser;
import cn.com.git.udmp.bizflow.config.parser.LineDefinitionParser;
import cn.com.git.udmp.bizflow.config.parser.LocalServiceDefinitionParser;
import cn.com.git.udmp.bizflow.config.parser.MappingActionDefinitionParser;
import cn.com.git.udmp.bizflow.config.parser.StartDefinitionParser;
/**
 * 解析上下文工厂，
 * <p>当前实现写死了对应关系，在此类中配置节点名称与BeanDefinitionParser的对应关系
 * @author updated by Spring Cao
 *
 */
public class ParserContextFactory {
	static ParserContext parserContext = new ParserContext();

	static{
		parserContext.addParser("start", new StartDefinitionParser());
		parserContext.addParser("localService", new LocalServiceDefinitionParser());
		parserContext.addParser("connection", new LineDefinitionParser());
		parserContext.addParser("contentBase", new ContentBaseRouterDefinitionParser());
		parserContext.addParser("mapping", new MappingActionDefinitionParser());
	}

	public  static ParserContext getParserContext(){
		return parserContext;
	}
}
