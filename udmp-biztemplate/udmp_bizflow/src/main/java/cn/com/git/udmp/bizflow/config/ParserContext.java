package cn.com.git.udmp.bizflow.config;

import java.util.HashMap;
import java.util.Map;

/**
 * 解析的上下文
 * <p>此上下文对应着所有节点名称与BeanDefinitionParser的对应关系
 * @author updated by Spring Cao
 * @see BeanDefinitionParser
 */
public class ParserContext {
	Map<String, BeanDefinitionParser> parserMap = new HashMap<String, BeanDefinitionParser>();

	public BeanDefinitionParser getParser(String tagName){
		return parserMap.get(tagName);
	}

	public void addParser(String tagName,BeanDefinitionParser parser){
		parserMap.put(tagName,parser);
	}
}
