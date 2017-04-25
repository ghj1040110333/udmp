package cn.com.git.udmp.bizflow.data;

/**
 * 数据工厂
 * @author updated by Spring Cao
 * @version v1.0 2013-4-28
 */
public interface DataFactory {
	public DataObject create(Type type);
}
