package cn.com.git.udmp.impl.batch.param.dao;

import java.util.List;

import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.component.batch.common.base.IBaseDao;
import cn.com.git.udmp.component.model.PageInfo;
import cn.com.git.udmp.impl.batch.param.po.ParamManagePO;
/**
 * @description 参数管理Dao
 * @author yangfeiit@newchinalife.com
 */
@MyBatisDao
public interface IParamManageDao extends IBaseDao<ParamManagePO>{}