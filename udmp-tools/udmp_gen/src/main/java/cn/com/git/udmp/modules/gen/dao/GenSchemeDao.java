
package cn.com.git.udmp.modules.gen.dao;

import cn.com.git.udmp.common.persistence.CrudDao;
import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.modules.gen.entity.GenScheme;

/**
 * 生成方案DAO接口
 * @author Spring Cao
 * @version 2013-10-15
 */
@MyBatisDao
public interface GenSchemeDao extends CrudDao<GenScheme> {
	
}
