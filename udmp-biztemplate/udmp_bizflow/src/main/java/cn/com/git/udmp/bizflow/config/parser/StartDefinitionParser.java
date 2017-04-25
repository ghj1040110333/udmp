package cn.com.git.udmp.bizflow.config.parser;

import org.dom4j.Element;

import cn.com.git.udmp.bizflow.activity.StartActivity;

/**
 * 起始活动解析器
 * @author updated by Spring Cao
 *
 */
public class StartDefinitionParser extends ActivityDefinitionParser{

	@Override
	public Class<?> getBeanClass(Element el) {
		return StartActivity.class;
	}
}
