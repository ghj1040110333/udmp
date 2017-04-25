package cn.com.git.udmp.bizflow.data;

import java.util.Collection;

import cn.com.git.udmp.bizflow.Nameable;

/**
 * 数据对象的类型
 * <p>
 * @author updated by Spring Cao
 *
 */
public interface Type extends Nameable{
	/**
	 * 数据对象的所有属性
	 * @return
	 * @see Property
	 */
	Collection<Property> getProperties();

	/**
	 * 根据属性名称获得type下数据对象的属性
	 * @return
	 */
	Property getProperty(String name);
	/**
	 * 是否是原子的，如果被标定为原子，则说明没有子节点
	 * @return
	 */
	boolean isAtomic();
	/**
	 * 对应实例化的类
	 * @return
	 */
	public Class<?> getInstanceClass();

	/**
	 * 类型是一个dataObject对象
	 */
	public boolean isDataObject();

}
