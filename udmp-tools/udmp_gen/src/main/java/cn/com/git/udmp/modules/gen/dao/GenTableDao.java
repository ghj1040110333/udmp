
package cn.com.git.udmp.modules.gen.dao;

import cn.com.git.udmp.common.persistence.CrudDao;
import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.modules.gen.entity.GenTable;

/**
 * 业务表DAO接口
 * @author Spring Cao
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenTableDao extends CrudDao<GenTable> {
	
}
