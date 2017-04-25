package cn.com.git.udmp.impl.batch.jobParam.dao;

import cn.com.git.udmp.common.persistence.annotation.MyBatisDao;
import cn.com.git.udmp.component.batch.common.base.IBaseDao;
import cn.com.git.udmp.impl.batch.jobParam.po.BatchJobParamPO;

/**
 * @description IBatchJobParamDao接口
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 13:58:04
 */
@MyBatisDao
public interface IBatchJobParamDao extends IBaseDao<BatchJobParamPO>{}