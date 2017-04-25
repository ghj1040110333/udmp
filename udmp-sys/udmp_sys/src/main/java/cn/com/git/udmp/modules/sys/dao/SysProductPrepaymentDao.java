

package cn.com.git.udmp.modules.sys.dao;

import cn.com.git.udmp.common.persistence.CrudDao;
import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.modules.sys.entity.SysProductPrepayment;

/**
 * 提前还款信息DAO接口
 * @author 赵明辉
 * @version 2015-11-18
 */
@MyBatisDao
public interface SysProductPrepaymentDao extends CrudDao<SysProductPrepayment> {
	
}