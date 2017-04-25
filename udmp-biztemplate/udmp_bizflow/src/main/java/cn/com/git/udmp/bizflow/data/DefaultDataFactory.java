package cn.com.git.udmp.bizflow.data;

import org.springframework.beans.BeanUtils;


/**
 * 数据工厂的默认实现
 * <p>用于生成DataObject
 * @author updated by Spring Cao
 *
 */
public class DefaultDataFactory implements DataFactory{

	public DataObject create(Type type) {
		if(type.getInstanceClass()!=null){
			DataObject d = (DataObject) BeanUtils.instantiateClass(type.getInstanceClass());
			d.setType(type);
			return d;
		}
		return new DataObject(type);
	}
}
