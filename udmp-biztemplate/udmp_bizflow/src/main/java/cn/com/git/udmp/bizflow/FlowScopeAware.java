package cn.com.git.udmp.bizflow;
/**
 * 实现此接口说明实现需要注入FlowScope
 * <p>flow的加载配置器在加载Bean时，Bean如果实现此接口，加载器自动将当前的流程范围上下文
 * 注入到当前实现中
 * @author updated by Spring Cao
 * @see FlowScope
 */
public interface FlowScopeAware {
	/**
	 * 设置FlowScope
	 * @param flowScope
	 */
	public void setFlowScope(FlowScope flowScope);
}
