package cn.com.git.udmp.impl.batch.task.dao;

import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.component.batch.common.base.IBaseDao;
import cn.com.git.udmp.impl.batch.task.po.BatchTaskParamPO;

/**
 * @description IBatchTaskParamDao接口
 * @author anthorName@newchinalife.com
 * @date 2015-02-10 10:46:42
 */
@MyBatisDao
public interface IBatchTaskParamDAO extends IBaseDao<BatchTaskParamPO>{}