package cn.com.git.udmp.impl.batch.jobDepend.dao;

import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.component.batch.common.base.IBaseDao;
import cn.com.git.udmp.impl.batch.jobDepend.po.BatchJobDependPO;

/**
 * @description IBatchJobDependDao接口
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 11:07:23
 */
@MyBatisDao
public interface IBatchJobDependDao extends IBaseDao<BatchJobDependPO>{}