package cn.com.git.udmp.common.act;

import org.activiti.engine.impl.bpmn.parser.FieldDeclaration;

/**
 * 使用参数添加的功能的扩展
 * @author guosg
 *
 */
public interface ActParamAddExtension {
	/**
	 * 添加参数
	 * @param declaration
	 */
	public void add(FieldDeclaration declaration);
}
