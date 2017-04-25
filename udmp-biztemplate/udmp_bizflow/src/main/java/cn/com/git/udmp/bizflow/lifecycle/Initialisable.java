package cn.com.git.udmp.bizflow.lifecycle;

/**
 * 流程加载完所有的配置文件后回调的接口
 * <p>此接口的实现如果配置在流程中，流程的配置加载后，会自动回调initialise方法
 * @author updated by Spring Cao
 *
 */
public interface Initialisable {
	/**
	 * 流程加载完所有的配置文件后回调此方法
	 */
	void initialise();
}
