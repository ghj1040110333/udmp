

package cn.com.git.udmp.modules.sys.dao;

import java.util.List;

import cn.com.git.udmp.common.persistence.CrudDao;
import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.modules.sys.entity.SysBusParam;

/**
 * 业务参数管理DAO接口
 * @author xwj
 * @version 2015-11-10
 */
@MyBatisDao
public interface SysBusParamDao extends CrudDao<SysBusParam> {
	public List<SysBusParam> findRegAgreementList(SysBusParam sysBusParam);
}