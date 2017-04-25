package cn.com.git.udmp.common.persistence.entity;

import java.util.Map;

import cn.com.git.udmp.common.persistence.Page;
import cn.com.git.udmp.core.config.Global;

public interface IBaseEntity {

	String getId();

	void setId(String id);

	UserEntity getCurrentUser();

	void setCurrentUser(UserEntity currentUser);

	Page<?> getPage();

	Page<?> setPage(Page<?> page);

	Map<String, String> getSqlMap();

	void setSqlMap(Map<String, String> sqlMap);

	/**
	 * 插入之前执行方法，子类实现
	 */
	void preInsert();

	/**
	 * 更新之前执行方法，子类实现
	 */
	void preUpdate();

	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 * @return
	 */
	boolean getIsNewRecord();

	/**
	 * 是否是新记录（默认：false），调用setIsNewRecord()设置新记录，使用自定义ID。
	 * 设置为true后强制执行插入语句，ID不会自动生成，需从手动传入。
	 */
	void setIsNewRecord(boolean isNewRecord);

	/**
	 * 全局变量对象
	 */
	Global getGlobal();

	/**
	 * 获取数据库名称
	 */
	String getDbName();

}