package cn.com.git.udmp.component.batch.schedule.quartz.job.impl;

import cn.com.git.udmp.component.batch.context.JobSessionContext;

/**
 * @description quartz的停止任务
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年4月20日 下午3:52:04
 */
public class StopBatchQuartzJob extends BasicBatchQuartzJob {
    @Override
    protected boolean preCondition(JobSessionContext jobSessionContext) {
        // TODO 停止任务时需要校验的操作
        return super.preCondition(jobSessionContext);
    }
}
