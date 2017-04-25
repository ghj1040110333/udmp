package cn.com.git.udmp.common.persistence.entity;

import java.util.List;


public interface UserEntity extends IDataEntity{

	boolean isAdmin();

	List<? extends RoleEntity> getRoleList();


	public OfficeEntity<?> getOffice();
	
	OfficeEntity<?> getCompany();

	String getLoginName();

	String getName();
}
