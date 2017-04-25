package cn.com.git.udmp.component.batch.core.server.manage.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.common.utils.StringUtils;
import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.common.utils.BatchModelUtil;
import cn.com.git.udmp.component.batch.model.JobConfig;
import cn.com.git.udmp.component.batch.model.JobParam;
import cn.com.git.udmp.core.exception.FrameworkRuntimeException;
import cn.com.git.udmp.impl.batch.job.ucc.IBatchJobUCC;
import cn.com.git.udmp.impl.batch.job.vo.BatchJobVO;
import cn.com.git.udmp.impl.batch.jobParam.ucc.IBatchJobParamUCC;
import cn.com.git.udmp.impl.batch.jobParam.vo.BatchJobParamVO;
import cn.com.git.udmp.impl.batch.jobRun.ucc.IBatchJobRunUCC;
import cn.com.git.udmp.impl.batch.jobRun.vo.BatchJobRunVO;
import cn.com.git.udmp.impl.batch.task.ucc.IBatchTaskUCC;
import cn.com.git.udmp.impl.batch.task.vo.BatchTaskVO;

/**
 * @description 任务及其状态的管理类
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年1月28日 下午3:30:05
 */
@Component("jobManager")
public class JobManagerInDB extends AbsJobManager {
    private static Map<String, JobConfig> jobConfigs = new HashMap<String, JobConfig>();
    @Autowired
    private IBatchJobUCC batchJobUCC;
    @Autowired
    private IBatchTaskUCC batchTaskUCC;
    @Autowired
    private IBatchJobParamUCC batchJobParamUCC;
    
    /**
     * @description 启动
     * @author liuliang liuliang_wb@newchinalife.com
     * @see cn.com.git.udmp.component.batch.core.IBooter#boot() 
    */
    public void boot() {
    }

    /**
     * @description 根据任务ID获取任务信息
     * @author liuliang liuliang_wb@newchinalife.com
     * @see cn.com.git.udmp.component.batch.core.server.manage.IJobManager#getJob(java.lang.String)
     * @param jobId 任务ID
     * @return 任务信息
     */
    public JobConfig getJob(String jobId) {
        if(StringUtils.isEmpty(jobId)){
            throw new FrameworkRuntimeException("查询任务对象时任务ID为空");
        }
        // 数据中获取
        BatchJobVO batchJobVO = new BatchJobVO();
        batchJobVO.setJobId(new BigDecimal(jobId));
        BatchJobVO batchJob = batchJobUCC.find(batchJobVO);
        if (batchJob.getJobId() == null) {
            throw new FrameworkException(jobId + "对应的任务ID不存在");
        }
        BatchTaskVO batchTaskVO = new BatchTaskVO();
        batchTaskVO.setTaskId(batchJob.getTaskId());
        BatchTaskVO batchTask = batchTaskUCC.find(batchTaskVO);
        JobConfig jobConfig = BatchModelUtil.patchJobConfig(new JobConfig(), batchJob);
        jobConfig = BatchModelUtil.patchJobConfig(jobConfig, batchTask);
        return jobConfig;

    }

    /**
     * @description 获取任务的参数信息
     * @author liuliang liuliang@newchinalife.com
     * @param jobId 任务ID
     * @return 任务参数信息
     */
    public List<JobParam> getJobParams(String jobId) {
        // 获取任务参数
        List<JobParam> jobParams = new ArrayList<JobParam>();
        BatchJobParamVO batchJobParamVO = new BatchJobParamVO();
        batchJobParamVO.setJobId(new BigDecimal(jobId));
        // 非删除标记的
        batchJobParamVO.setIsDeleted(BatchCommonConst.BATCH_FLAG_FALSE);
        List<BatchJobParamVO> allBatchJobParams = batchJobParamUCC.findAll(batchJobParamVO);
        for (BatchJobParamVO key : allBatchJobParams) {
            JobParam jobParam = new JobParam();
            jobParam.setParamCode(null==key.getTaskParamCode()?"":key.getTaskParamCode());
            jobParam.setParamName(null==key.getTaskParamName()?"":key.getTaskParamName());
            jobParam.setParamType(null==key.getTaskParamDataType()?"":key.getTaskParamDataType());
            jobParam.setParamValue(null==key.getParamValue()?"":key.getParamValue());
            jobParams.add(jobParam);
        }
        return jobParams;
    }

    public void setBatchJobUCC(IBatchJobUCC batchJobUCC) {
        this.batchJobUCC = batchJobUCC;
    }

    public void setBatchTaskUCC(IBatchTaskUCC batchTaskUCC) {
        this.batchTaskUCC = batchTaskUCC;
    }

    public void setBatchJobParamUCC(IBatchJobParamUCC batchJobParamUCC) {
        this.batchJobParamUCC = batchJobParamUCC;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }


}
