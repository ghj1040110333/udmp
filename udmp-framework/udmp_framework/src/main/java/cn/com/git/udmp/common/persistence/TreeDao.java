

package cn.com.git.udmp.common.persistence;

import java.util.List;

import cn.com.git.udmp.common.persistence.entity.ITreeEntity;

/**
 * DAO支持类实现
 * @author Spring Cao
 * @version 2014-05-16
 * @param <T>
 */
public interface TreeDao<T extends ITreeEntity<T>> extends CrudDao<T> {

	/**
	 * 找到所有子节点
	 * @param entity
	 * @return
	 */
	public List<T> findByParentIdsLike(T entity);

	/**
	 * 更新所有父节点字段
	 * @param entity
	 * @return
	 */
	public int updateParentIds(T entity);
	
}