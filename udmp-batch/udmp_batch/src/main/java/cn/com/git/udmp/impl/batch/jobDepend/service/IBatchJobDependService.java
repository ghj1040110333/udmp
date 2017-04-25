package cn.com.git.udmp.impl.batch.jobDepend.service;

import java.util.List;

import cn.com.git.udmp.component.batch.common.base.IBaseBatchService;
import cn.com.git.udmp.impl.batch.jobDepend.bo.BatchJobDependBO;
import cn.com.git.udmp.impl.batch.jobDepend.po.BatchJobDependPO;

public interface IBatchJobDependService extends IBaseBatchService<BatchJobDependBO, BatchJobDependPO>{

    List<BatchJobDependBO> findAllBatchJobDependPreCond(BatchJobDependBO batchJobDependBO);

    List<BatchJobDependBO> findAllBatchJobDependPostCond(BatchJobDependBO batchJobDependBO);

}
