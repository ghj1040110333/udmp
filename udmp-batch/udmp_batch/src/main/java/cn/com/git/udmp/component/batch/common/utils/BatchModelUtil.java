package cn.com.git.udmp.component.batch.common.utils;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.model.JobConfig;
import cn.com.git.udmp.impl.batch.job.vo.BatchJobVO;
import cn.com.git.udmp.impl.batch.task.vo.BatchTaskVO;

/**
 * @description 批处理的对象转换工具类
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年4月13日 下午1:08:45
 */
public class BatchModelUtil {
    /**
     * @description 根据jobConfig组装jobSessionContext对象
     * @author liuliang liuliang@newchinalife.com
     * @param jobConfig job配置信息对象
     * @return 批处理任务执行过程中的session对象
     */
    public static JobSessionContext patchJobSessionContext(JobConfig jobConfig) {
        // TODO 还需要加属性
        JobSessionContext jobSessionContext = new JobSessionContext();
        jobSessionContext.setJobId(jobConfig.getJobId());
        return jobSessionContext;
    }

    /**
     * @description 通过BatchJobVO对象组装任务配置信息JobConfig
     * @param jobConfig 任务配置信息
     * @param batchJobVO BatchJob的VO对象
     * @return 包含batchJobVO中信息的JobConfig
    */
    public static JobConfig patchJobConfig(JobConfig jobConfig, BatchJobVO batchJobVO) {
        // TODO 还需要加属性
        jobConfig.setJobId(batchJobVO.getJobId().toString());
        jobConfig.setIsAllowManu(batchJobVO.getIsAllowManu());
        jobConfig.setIsAllowSplit(batchJobVO.getIsAllowSplit());
        jobConfig.setIsEnable(batchJobVO.getIsEnable());
        jobConfig.setIsGroup(batchJobVO.getIsGroup());
        jobConfig.setJobAlertDuration(batchJobVO.getJobAlertDuration().intValue());
        jobConfig.setJobBatchSize(batchJobVO.getJobBatchSize().intValue());
        jobConfig.setJobEndWindow(batchJobVO.getJobEndWindow());
        jobConfig.setJobExpectDuration(batchJobVO.getJobExpectDuration().intValue());
        jobConfig.setJobFrequency(batchJobVO.getJobFrequency());
        jobConfig.setJobGroupId(batchJobVO.getJobGroupId().intValue());
        jobConfig.setJobName(batchJobVO.getJobName());
        jobConfig.setJobStartWindow(batchJobVO.getJobStartWindow());
        jobConfig.setJobThreadLimitCnt(batchJobVO.getJobThreadLimitCnt().intValue());
        jobConfig.setJobtype(batchJobVO.getJobType());
        jobConfig.setTaskId(batchJobVO.getTaskId().toString());
        jobConfig.setIsLoop(batchJobVO.getIsLoop());
        return jobConfig;
    }

    /**
     * @description 通过batchTaskVO的对象组装JobConfig信息
     * @author liuliang liuliang@newchinalife.com
     * @param jobConfig JobConfig对象
     * @param batchTaskVO 批处理作业的VO对象
     * @return 任务配置信息
    */
    public static JobConfig patchJobConfig(JobConfig jobConfig, BatchTaskVO batchTaskVO) {
        // TODO 还需要加属性
        jobConfig.setTaskId(batchTaskVO.getTaskId().toString());
        jobConfig.setTaskClazz(batchTaskVO.getTaskClazz());
        jobConfig.setIsSpringBean(batchTaskVO.getIsSpringBean());
        jobConfig.setTaskName(batchTaskVO.getTaskName());
        jobConfig.setTaskSystem(batchTaskVO.getTaskSystem());
        return jobConfig;
    }
}
