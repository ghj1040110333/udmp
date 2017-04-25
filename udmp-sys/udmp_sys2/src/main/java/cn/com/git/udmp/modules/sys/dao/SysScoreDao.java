

package cn.com.git.udmp.modules.sys.dao;

import cn.com.git.udmp.common.persistence.CrudDao;
import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.modules.sys.entity.SysScore;

/**
 * 积分参数管理DAO接口
 * @author 孙毅
 * @version 2015-11-06
 */
@MyBatisDao
public interface SysScoreDao extends CrudDao<SysScore> {
	
}