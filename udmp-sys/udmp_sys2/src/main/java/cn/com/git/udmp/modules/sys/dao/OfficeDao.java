

package cn.com.git.udmp.modules.sys.dao;

import cn.com.git.udmp.common.persistence.TreeDao;
import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.modules.sys.entity.Office;

/**
 * 机构DAO接口
 * @author Spring Cao
 * @version 2014-05-16
 */
@MyBatisDao
public interface OfficeDao extends TreeDao<Office> {
	
}
