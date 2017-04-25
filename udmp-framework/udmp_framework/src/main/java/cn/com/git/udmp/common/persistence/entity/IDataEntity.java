package cn.com.git.udmp.common.persistence.entity;

import java.util.Date;

public interface IDataEntity extends IBaseEntity {

	/**
	 * 插入之前执行方法，需要手动调用
	 */
	void preInsert();

	/**
	 * 更新之前执行方法，需要手动调用
	 */
	void preUpdate();

	String getRemarks();

	void setRemarks(String remarks);

	DefaultUserEntity<?> getCreateBy();

	void setCreateBy(DefaultUserEntity<?> createBy);

	Date getCreateDate();

	void setCreateDate(Date createDate);

	DefaultUserEntity<?> getUpdateBy();

	void setUpdateBy(DefaultUserEntity<?> updateBy);

	Date getUpdateDate();

	void setUpdateDate(Date updateDate);

	String getDelFlag();

	void setDelFlag(String delFlag);

}