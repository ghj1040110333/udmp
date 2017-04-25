package cn.com.git.udmp.bizflow.data;
/**
 * 数据对象的属性
 * @author updated by Spring Cao
 *
 */
public interface Property {

	/**
	 * 属性对应的类型
	 * @return
	 */
	public Type getType();

	/**
	 * 属性的名称
	 * @return
	 */
	public String getName();

	/**
	 * 属性的值的来源
	 * @return
	 */
	public String getValue();

	/**
	 * 别名
	 * @return
	 */
	public String getFieldName();
	/**
	 * 是否是属性是一个列表
	 * @return
	 */
	public boolean isMany();
}
