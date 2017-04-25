package cn.com.git.udmp.common.persistence.entity;

import java.util.List;

import com.google.common.collect.Lists;

import cn.com.git.udmp.common.persistence.DataEntity;

public class DefaultUserEntity<T> extends DataEntity<T> implements UserEntity{
	
	private List< ? extends RoleEntity> roleList = Lists.newArrayList(); // 拥有角色列表
	private String name;
	private String loginName;
	private DefaultOfficeEntity<?> office;
	private DefaultOfficeEntity<?> company;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DefaultUserEntity() {
	}
	
	public DefaultUserEntity(String id) {
		super(id);
	}

	public boolean isAdmin(){
		return isAdmin(this.id);
	}
	
	public static boolean isAdmin(String id){
		return id != null && "1".equals(id);
	}

	public List<? extends RoleEntity> getRoleList() {
		return roleList;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public DefaultOfficeEntity<?> getOffice() {
		return office;
	}

	public void setOffice(DefaultOfficeEntity<?> office) {
		this.office = office;
	}

	public DefaultOfficeEntity<?> getCompany() {
		return company;
	}

	public void setCompany(DefaultOfficeEntity<?> company) {
		this.company = company;
	}
	
	

}
