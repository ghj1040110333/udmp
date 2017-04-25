package cn.com.git.udmp.impl.batch.jobRun.service;

import cn.com.git.udmp.component.batch.common.base.IBaseBatchService;
import cn.com.git.udmp.impl.batch.jobRun.bo.BatchJobRunBO;
import cn.com.git.udmp.impl.batch.jobRun.po.BatchJobRunPO;

public interface IBatchJobRunService extends IBaseBatchService<BatchJobRunBO,BatchJobRunPO>{
    void addWithId(BatchJobRunBO bo);
}
