package cn.com.git.udmp.impl.batch.jobRunLog.dao;

import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.component.batch.common.base.IBaseDao;
import cn.com.git.udmp.impl.batch.jobRunLog.po.BatchJobRunLogPO;

/** 
 * @description IBatchJobRunLogDao接口
 * @author yangfeiit@newchinalife.com 
 * @date 2015-02-04 15:06:12  
 */
@MyBatisDao
 public interface IBatchJobRunLogDao extends IBaseDao<BatchJobRunLogPO>{}