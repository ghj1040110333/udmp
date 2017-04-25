package cn.com.git.udmp.bizflow.mapping;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import cn.com.git.udmp.bizflow.data.DataFactory;
import cn.com.git.udmp.bizflow.data.DataObject;
import cn.com.git.udmp.bizflow.data.DefaultDataFactory;
import cn.com.git.udmp.bizflow.data.Property;
import cn.com.git.udmp.bizflow.data.Type;

/**
 * 映射处理
 * @author updated by Spring Cao
 * @version v1.0 2013-4-28
 */
public class MappingHandler {
	DataFactory factory = new DefaultDataFactory();

	public DataObject mapping(Type type, ValueObject vo) {
		DataObject data = factory.create(type);
		for (Property property : data.getType().getProperties()) {
			Object p = ValueExpressHandler.express(property.getValue(), vo);
			if (property.getType().isAtomic()) {
				if (property.isMany()) {
					List<Object> d = new ArrayList<Object>();
					if (p instanceof Collection) {
						for (Iterator<?> it = ((Collection<?>) p).iterator(); it
								.hasNext();) {
							Object o = it.next();
							d.add(buildSimpleObject(property, o));
						}
					} else {
						d.add(buildSimpleObject(property, p));
					}
					data.set(property.getName(), d);
				} else {
					data.set(property.getName(), buildSimpleObject(property, p));
				}
			} else {
				if (property.isMany()) {
					List<DataObject> lis = new ArrayList<DataObject>();
					if (p instanceof Collection) {
						for (Iterator<?> it = ((Collection<?>) p).iterator(); it
								.hasNext();) {
							ValueObject cvo = vo.clone();
							cvo.setCur((DataObject) it.next());
							lis.add(mapping(property.getType(), cvo));
						}
					} else {
						ValueObject cvo = vo.clone();
						cvo.setCur((DataObject) p);
						lis.add(mapping(property.getType(), cvo));
					}
					data.setList(property.getName(), lis);
				} else {
					ValueObject cvo = vo.clone();
					cvo.setCur((DataObject) p);
					data.set(property.getName(),
							mapping(property.getType(), cvo));
				}
			}
		}
		return data;
	}

	/**
	 * 创建单实例的对象
	 */
	private Object buildSimpleObject(Property property, Object obj) {
		if(obj == null)
			return null;
		Object data = obj;
		Type type = property.getType();
		if ("Double".equals(type.getName())) {
			data = obj.toString();
		} else if (type.isDataObject()) {
			DataObject d = null;
			if (obj instanceof DataObject) {
				d = factory.create(type);
				d.setData(((DataObject) obj).getData());
			} else {
				throw new RuntimeException(property.getName()
						+ "传入的参数类型不是dataObject而是" + obj);
			}
			data = d;
		}
		return data;
	}
}
