

package cn.com.git.udmp.modules.sys.dao;

import cn.com.git.udmp.common.persistence.CrudDao;
import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.modules.sys.entity.SysAreaCode;

/**
 * 区域代码管理DAO接口
 * @author 赵明辉
 * @version 2015-11-10
 */
@MyBatisDao
public interface SysAreaCodeDao extends CrudDao<SysAreaCode> {
	
}