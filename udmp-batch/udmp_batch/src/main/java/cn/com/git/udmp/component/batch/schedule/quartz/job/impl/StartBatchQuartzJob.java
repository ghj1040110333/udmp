package cn.com.git.udmp.component.batch.schedule.quartz.job.impl;

import java.util.Date;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.server.manage.BatchQuartzManager;
import cn.com.git.udmp.component.batch.core.server.manage.IJobManager;
import cn.com.git.udmp.component.batch.model.JobConfig;

/**
 * @description 启动
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年4月20日 下午3:51:45
 */
public class StartBatchQuartzJob extends BasicBatchQuartzJob {
    private IJobManager jobManager;

    @Override
    protected boolean preCondition(JobSessionContext jobSessionContext) {
        // 启动任务时需校验是否到达是否在存活时间区间内,若不在则将此延后到满足时间为止
        logger.debug("正在运行StartBatchQuartzJob...");
        Date date = new Date();
        if (jobSessionContext.getJobStartWindow() != null && date.compareTo(jobSessionContext.getJobStartWindow()) < 0) {
            // TODO(待验证)batch--新增一个定时任务 延时策略
            logger.debug("未达到启动时间，延时任务{}", jobSessionContext.getJobId());
            JobConfig jobConfig = jobManager.getJob(jobSessionContext.getJobId());
            BatchQuartzManager.setTempRunJob(jobConfig, jobSessionContext.getJobStartWindow());
            return false;
        }
        return super.preCondition(jobSessionContext);
    }
}
