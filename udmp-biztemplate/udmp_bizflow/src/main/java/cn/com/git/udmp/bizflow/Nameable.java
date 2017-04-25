package cn.com.git.udmp.bizflow;
/**
 * 实现此接口说明可以获取和设置名称
 * @author updated by Spring Cao
 *
 */
public interface Nameable {
	/**
	 * 设置名称
	 * @param name
	 */
	public void setName(String name);
	/**
	 * 获得名称
	 * @return
	 */
	public String getName();
}
