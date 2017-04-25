package cn.com.git.udmp.common.persistence.entity;

import javax.xml.bind.annotation.XmlElement;

import cn.com.git.udmp.common.persistence.TreeEntity;

public class DefaultOfficeEntity<T extends OfficeEntity<T>> extends TreeEntity<T> implements OfficeEntity<T>{

	public DefaultOfficeEntity(String id){
		super(id);
	}
	
	public DefaultOfficeEntity(){
		super();
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@XmlElement(type=DefaultOfficeEntity.class)
	public T getParent() {
		
		return parent;
	}

	@Override
	public void setParent(T parent) {
		this.parent = parent;
	}

}
