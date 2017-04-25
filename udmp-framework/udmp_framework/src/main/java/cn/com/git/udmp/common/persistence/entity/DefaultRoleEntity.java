package cn.com.git.udmp.common.persistence.entity;

import cn.com.git.udmp.common.persistence.DataEntity;

public class DefaultRoleEntity<T>  extends DataEntity<T> implements cn.com.git.udmp.common.persistence.entity.RoleEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String dataScope;// 数据范围
	
	public DefaultRoleEntity(String id){
		super(id);
	}
	
	public DefaultRoleEntity(){
		super();
	}

	@Override
	public String getDataScope() {
		return dataScope;
	}
	
}
