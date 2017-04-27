package cn.com.git.udmp.impl.batch.jobRun.ucc;

import java.util.List;
import java.util.Map;

import cn.com.git.udmp.component.batch.common.base.IBaseBatchUCC;
import cn.com.git.udmp.component.model.CurrentPage;
import cn.com.git.udmp.impl.batch.jobRun.bo.BatchJobRunBO;
import cn.com.git.udmp.impl.batch.jobRun.po.BatchJobRunPO;
import cn.com.git.udmp.impl.batch.jobRun.vo.BatchJobRunVO;

 /** 
 * @description IBatchJobRunUCC接口
 * @author yangfeiit@newchinalife.com 
 * @date 2015-02-04 14:34:57  
 */
public interface IBatchJobRunUCC extends IBaseBatchUCC<BatchJobRunVO, BatchJobRunBO, BatchJobRunPO>{
    
    void addWithId(BatchJobRunVO batchAgentVO);
    
    int findLastBatch(String jobChainRunId);

    void failReBootJob(String jobRunId);
    
    
}
