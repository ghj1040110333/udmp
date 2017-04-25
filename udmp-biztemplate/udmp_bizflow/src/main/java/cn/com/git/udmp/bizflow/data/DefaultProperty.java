package cn.com.git.udmp.bizflow.data;

import cn.com.git.udmp.bizflow.Nameable;

/**
 * 数据对象的属性
 * @author Spring Cao
 *
 */
public class DefaultProperty implements Nameable,Property{

	Type type;

	String name;

	String value;

	String fieldName;

	boolean many = false;

	/**
	 * 属性对应的类型
	 * @return
	 */
	public Type getType(){
		return type;
	}

	/**
	 * 属性的值的来源
	 * @return
	 */
	public String getValue(){
		return value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public boolean isMany() {
		return many;
	}

	public void setMany(boolean many) {
		this.many = many;
	}
}
