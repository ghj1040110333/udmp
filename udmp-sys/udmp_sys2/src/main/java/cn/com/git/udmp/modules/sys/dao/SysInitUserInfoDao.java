

package cn.com.git.udmp.modules.sys.dao;

import cn.com.git.udmp.common.persistence.CrudDao;
import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.modules.sys.entity.SysInitUserInfo;

/**
 * 客户初始化信息管理DAO接口
 * @author 孙毅
 * @version 2015-11-09
 */
@MyBatisDao
public interface SysInitUserInfoDao extends CrudDao<SysInitUserInfo> {
	
	public void updateNoSel();
	
	public void updateSelByDicts(SysInitUserInfo sysInitUserInfo);
}