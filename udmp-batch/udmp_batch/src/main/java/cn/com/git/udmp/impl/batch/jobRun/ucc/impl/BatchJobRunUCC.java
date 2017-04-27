package cn.com.git.udmp.impl.batch.jobRun.ucc.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.google.common.base.Preconditions;

import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.batch.common.base.BaseBatchUCC;
import cn.com.git.udmp.component.batch.common.constants.JobRunStatus;
import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.common.utils.StatusUtil;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.server.job.IJobController;
import cn.com.git.udmp.component.batch.core.server.manage.impl.JobDependManagerInDB;
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

    @Autowired
    @Qualifier("dispatchJobController")
    IJobController startJobController;

    @Autowired
    JobDependManagerInDB jobDependManager;

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

    /**
     * 
     * @param jobRunId
     * @title 任务失败重跑
     * @description
     *
     */
    @Override
    public void failReBootJob(String jobRunId) {
        // 若当前任务实例状态为成功、则往下找失败任务；若当前任务实例状态为失败，则往上找第一个失败任务
        BatchJobRunVO vo = new BatchJobRunVO();
        vo.setJobRunId(new BigDecimal(jobRunId));
        List<BatchJobRunVO> resultList = this.findAll(vo);
        if (resultList != null && resultList.size() != 0) {
            BatchJobRunVO result = resultList.get(0);
            if (result != null) {
                String jobId = result.getJobId().toPlainString();
                String jobChainRunId = result.getJobChainRunId();
                Preconditions.checkNotNull(jobChainRunId);
                if (!JobRunStatus.SUCCESS.equals(result.getStatus())) {
                    activePre(jobId, JobDependManagerInDB.getJobStatusFromResult(resultList), jobChainRunId);
                } else {
                    activeAfter(jobId, JobDependManagerInDB.getJobStatusFromResult(resultList), jobChainRunId);
                }
            } else {
                logger.error("{}任务实例不存在", jobRunId);
            }
        }
    }

    /**
     * @description 激活之前失败的任务
     * 
     * @param jobId
     * @param jobChainRunId
     */
    private void activePre(String jobId, StatusEnum status, String jobChainRunId) {
        int jobChainBatch = this.findLastBatch(jobChainRunId);
        if (!jobDependManager.checkPreCondtion(jobId, jobChainRunId, jobChainBatch)) {
            // 1.若前置条件不满足，则向上查找前置条件有哪些
            // 2.递归执行前置条件的任务的active方法
            Map<String, StatusEnum> preCondtion = jobDependManager.findPreCondition(jobId);
            logger.info("任务链{}下的任务{}不满足启动条件,开始查找是否从之前的任务{}开始失败重跑", jobChainRunId, jobId, preCondtion.keySet());
            for (String key : preCondtion.keySet()) {
                BatchJobRunVO batchJobRunVO = new BatchJobRunVO();
                batchJobRunVO.setJobId(new BigDecimal(key));
                batchJobRunVO.setJobChainRunId(jobChainRunId);
                BatchJobRunVO result = this.find(batchJobRunVO);
                if (result != null) {
                    activePre(key, StatusUtil.getStatusEnum(result.getStatus()), jobChainRunId);
                }
            }
        } else {
            // 若前置条件满足，则直接启动当前任务
            // 1.若当前任务为失败状态则重跑
            if (!status.equals(StatusEnum.SUCCESS)) {
                JobSessionContext jobSessionContext = new JobSessionContext();
                jobSessionContext.setJobId(jobId);
                jobSessionContext.setJobChainRunId(jobChainRunId);
                logger.info("任务{}当前状态任务链实例{}状态为不成功，开始失败重跑", jobId, jobChainRunId);
                // add by L.liang on 2017/1/12 初始化时获取当前任务实例执行批次，将批次号传入到执行链上下文中
                jobSessionContext.setJobChainBatch(jobChainBatch + 1);
                logger.debug("启动任务链实例{}下的任务{}的第{}批次", jobChainRunId, jobId, jobSessionContext.getJobChainBatch());
                startJobController.control(jobSessionContext);
            }
        }
    }

    /**
     * @title 激活后续失败的任务
     * @description 若当前任务{jobId}的
     * 
     * @param jobId
     * @param jobChainRunId
     */
    private void activeAfter(String jobId, StatusEnum status, String jobChainRunId) {
        int lastBatch = this.findLastBatch(jobChainRunId);
        if (status.equals(StatusEnum.SUCCESS)) {
            // 若当前任务为成功状态，查找以当前任务为成功状态作为前置的任务，然后往下激活后续任务
            List<String> jobIds = jobDependManager.findJobsAfter(jobId, status);
            logger.info("当前任务{}在任务链{}下的后续任务有:{}", jobId, jobChainRunId, jobIds);
            for (String key : jobIds) {
                BatchJobRunVO batchJobRunVO = new BatchJobRunVO();
                batchJobRunVO.setJobId(new BigDecimal(key));
                batchJobRunVO.setJobChainRunId(jobChainRunId);
                batchJobRunVO.setJobChainBatch(new BigDecimal(lastBatch));// 设置批次号
                List<BatchJobRunVO> resultList = this.findAll(batchJobRunVO);
                if (resultList != null && resultList.size() != 0) {
                    try {
                        activeAfter(key, JobDependManagerInDB.getJobStatusFromResult(resultList), jobChainRunId);
                    } catch (RuntimeException e) {
                        // logger.error("任务链{}激活后续任务{}发生异常:{}", jobChainRunId,
                        // key, e);
                        throw e;
                    }
                }
            }
        } else {
            // 若当前任务不成功，则直接启动当前任务
            JobSessionContext jobSessionContext = new JobSessionContext();
            jobSessionContext.setJobId(jobId);
            jobSessionContext.setJobChainRunId(jobChainRunId);
            logger.info("任务{}当前状态任务链实例{}状态为不成功，开始执行失败重跑", jobId, jobChainRunId);
            // add by L.liang on 2017/1/12 初始化时获取当前任务实例执行批次，将批次号传入到执行链上下文中
            jobSessionContext.setJobChainBatch(lastBatch + 1);
            logger.debug("启动任务链实例{}下的任务{}的第{}批次", jobChainRunId, jobId, jobSessionContext.getJobChainBatch());
            startJobController.control(jobSessionContext);
        }
    }

    public void setStartJobController(IJobController startJobController) {
        this.startJobController = startJobController;
    }

    public void setJobDependManager(JobDependManagerInDB jobDependManager) {
        this.jobDependManager = jobDependManager;
    }
}
