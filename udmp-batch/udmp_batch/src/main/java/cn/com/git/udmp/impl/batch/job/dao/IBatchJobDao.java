package cn.com.git.udmp.impl.batch.job.dao;

import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.component.batch.common.base.IBaseDao;
import cn.com.git.udmp.impl.batch.job.po.BatchJobPO;

/**
 * @description IBatchJobDao接口
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 09:54:12
 */
@MyBatisDao
public interface IBatchJobDao extends IBaseDao<BatchJobPO> {
}