package cn.com.git.udmp.impl.batch.jobRun.ucc.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import cn.com.git.udmp.component.batch.common.base.BaseBatchUCC;
import cn.com.git.udmp.component.batch.common.base.IBaseBatchService;
import cn.com.git.udmp.impl.batch.jobRun.bo.BatchJobRunBO;
import cn.com.git.udmp.impl.batch.jobRun.po.BatchJobRunPO;
import cn.com.git.udmp.impl.batch.jobRun.service.IBatchJobRunService;
import cn.com.git.udmp.impl.batch.jobRun.ucc.IBatchJobRunUCC;
import cn.com.git.udmp.impl.batch.jobRun.vo.BatchJobRunVO;
import cn.com.git.udmp.utils.bean.BeanUtils;

/**
 * @description BatchJobRunUCC实现类
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 11:07:49
 */
@Service
public class BatchJobRunUCC extends BaseBatchUCC<BatchJobRunVO, BatchJobRunBO, BatchJobRunPO>
        implements IBatchJobRunUCC {
    @Autowired
    private IBatchJobRunService service;

    @Override
    public IBatchJobRunService getService() {
        return service;
    }

    public void setService(IBatchJobRunService service) {
        this.service = service;
    }

    @Override
    public void addWithId(BatchJobRunVO batchVO) {
        logger.debug("<======BatchUCC--addBatchAgent======>");
        BatchJobRunBO batchBO = BeanUtils.copyProperties(BatchJobRunBO.class, batchVO);
        getService().addWithId(batchBO);
        BeanUtils.copyProperties(batchVO, batchBO);
    }

    /**
     * @title
     * @description 按照任务链实例ID查询最新的批次号
     * 
     * @param jobChainRunId
     */
    @Override
    public int findLastBatch(String jobChainRunId) {
        Preconditions.checkNotNull(jobChainRunId);
        BatchJobRunBO batchJobRunBO = new BatchJobRunBO();
        batchJobRunBO.setJobChainRunId(jobChainRunId);
        List<BatchJobRunBO> results = getService().findAll(batchJobRunBO);
        int batch = 1;
        for (BatchJobRunBO bo : results) {
            BigDecimal jobChainBatch = bo.getJobChainBatch();
            if (jobChainBatch != null) {
                Integer chainBatch = Integer.valueOf(jobChainBatch.toString());
                if (chainBatch > batch) {
                    batch = chainBatch;
                }
            }
        }
        return batch;

    }

}
