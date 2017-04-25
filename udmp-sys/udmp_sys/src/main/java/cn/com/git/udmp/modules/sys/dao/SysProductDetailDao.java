

package cn.com.git.udmp.modules.sys.dao;

import cn.com.git.udmp.common.persistence.CrudDao;
import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.modules.sys.entity.SysProductDetail;

/**
 * 产品信息DAO接口
 * @author 赵明辉
 * @version 2015-11-17
 */
@MyBatisDao
public interface SysProductDetailDao extends CrudDao<SysProductDetail> {
	
}