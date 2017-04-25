package cn.com.git.udmp.common.persistence.entity;


public interface ITreeEntity<T> extends IDataEntity{

	/**
	 * 父对象，只能通过子类实现，父类实现mybatis无法读取
	 * @return
	 */
	T getParent();

	/**
	 * 父对象，只能通过子类实现，父类实现mybatis无法读取
	 * @return
	 */
	void setParent(T parent);

	String getParentIds();

	void setParentIds(String parentIds);

	String getName();

	void setName(String name);

	Integer getSort();

	void setSort(Integer sort);

	String getParentId();

}