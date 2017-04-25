package cn.com.git.udmp.component.batch.schedule.quartz.job.impl;

import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.server.job.IJobController;
import cn.com.git.udmp.component.batch.core.server.job.JobControllerFactory;
import cn.com.git.udmp.component.batch.schedule.quartz.job.AbsBatchQuartzJob;

/**
 * @description 作业封装的实现类
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年4月20日 下午2:32:10
 */
public class BasicBatchQuartzJob extends AbsBatchQuartzJob {

    @Override
    protected boolean preCondition(JobSessionContext jobSessionContext) {
        // TODO 前置校验条件(如果定时任务的启动时间大于)
        return true;
    }

    @Override
    protected void afterOpertion(JobSessionContext jobSessionContext) {
        // TODO Auto-generated method stub

    }

    @Override
    protected void execute(JobSessionContext jobSessionContext) {
        // 通过指令操作从任务控制器工厂中获取一个对应的任务处理器
        if(jobSessionContext.getJobChainRunId()!=null){
            logger.debug("清空quartz定时任务之前保留的实例信息");
            jobSessionContext.setJobChainRunId(null);
            jobSessionContext.setJobRunId(null);
            jobSessionContext.setJobChainBatch(1);
        }
        IJobController jobController = JobControllerFactory.getJobController(jobSessionContext.getCommand());
        if (jobController == null) {
            throw new FrameworkException("定时任务调度控制类获取失败，请检查是否已装配");
        }
        logger.debug("开始执行定时任务{}的指令{}", jobSessionContext.getJobId(), jobSessionContext.getCommand());
        jobController.control(jobSessionContext);

    }

}
