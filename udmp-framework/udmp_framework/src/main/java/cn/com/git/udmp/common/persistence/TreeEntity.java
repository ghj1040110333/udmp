

package cn.com.git.udmp.common.persistence;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.fasterxml.jackson.annotation.JsonBackReference;

import cn.com.git.udmp.common.persistence.entity.ITreeEntity;
import cn.com.git.udmp.common.utils.Reflections;
import cn.com.git.udmp.common.utils.StringUtils;

/**
 * 数据Entity类
 * @author Spring Cao
 * @version 2014-05-16
 */
public abstract class TreeEntity<T> extends DataEntity<T> implements ITreeEntity<T> {

	private static final long serialVersionUID = 1L;

	protected T parent;	// 父级编号
	protected String parentIds; // 所有父级编号
	protected String name; 	// 机构名称
	protected Integer sort;		// 排序
	
	public TreeEntity() {
		super();
		this.sort = 30;
	}
	
	public TreeEntity(String id) {
		super(id);
	}
	
	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.ITreeEntity#getParent()
	 */
	@Override
	@JsonBackReference
	@NotNull
	public abstract T getParent();

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.ITreeEntity#setParent(T)
	 */
	@Override
	public abstract void setParent(T parent);

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.ITreeEntity#getParentIds()
	 */
	@Override
	@Length(min=1, max=2000)
	public String getParentIds() {
		return parentIds;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.ITreeEntity#setParentIds(java.lang.String)
	 */
	@Override
	public void setParentIds(String parentIds) {
		this.parentIds = parentIds;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.ITreeEntity#getName()
	 */
	@Override
	@Length(min=1, max=100)
	public String getName() {
		return name;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.ITreeEntity#setName(java.lang.String)
	 */
	@Override
	public void setName(String name) {
		this.name = name;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.ITreeEntity#getSort()
	 */
	@Override
	public Integer getSort() {
		return sort;
	}

	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.ITreeEntity#setSort(java.lang.Integer)
	 */
	@Override
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	
	/* (non-Javadoc)
	 * @see cn.com.git.udmp.common.persistence.ITreeEntity#getParentId()
	 */
	@Override
	public String getParentId() {
		String id = null;
		if (parent != null){
			id = (String)Reflections.getFieldValue(parent, "id");
		}
		return StringUtils.isNotBlank(id) ? id : "0";
	}
	
}
