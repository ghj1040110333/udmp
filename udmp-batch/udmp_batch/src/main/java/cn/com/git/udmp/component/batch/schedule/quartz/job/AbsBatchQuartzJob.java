package cn.com.git.udmp.component.batch.schedule.quartz.job;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 定时调度框架的任务类
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年4月9日
 * @version 1.0
 */
public abstract class AbsBatchQuartzJob implements Job, ILog {
    /**
     * @Fields JOB_SESSION_CONTEXT : jobSessionContext的命名空间
     */
    public static final String JOB_SESSION_CONTEXT = "job_session_context";

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // batch--从context中构建jobSessionContext
        JobDataMap jobDataMap = context.getJobDetail().getJobDataMap();
        JobSessionContext jobSessionContext = (JobSessionContext) (jobDataMap.get(JOB_SESSION_CONTEXT));
        logger.debug("批处理任务{}执行", jobSessionContext.getJobId());
        try {
            if (preCondition(jobSessionContext)) {
                logger.debug("定时任务满足条件");
                execute(jobSessionContext);
                afterOpertion(jobSessionContext);
            } else {
                logger.debug("定时任务不满足条件，不执行定时任务");
            }
        } catch (FrameworkException e) {
            // 捕获任务操作时发生的异常
            logger.error("定时任务执行中发生异常:{}", e);
            throw e;
        }
    }

    /**
     * @description 前置条件及其操作
     * @author liuliang liuliang@newchinalife.com
     * @param jobSessionContext 上下文对象
     * @return 是否满足前置条件
     */
    protected abstract boolean preCondition(JobSessionContext jobSessionContext);

    /**
     * @description 后续操作
     * @author liuliang liuliang@newchinalife.com
     * @param jobSessionContext 上下文
     */
    protected abstract void afterOpertion(JobSessionContext jobSessionContext);

    /**
     * @description 执行操作
     * @author liuliang liuliang@newchinalife.com
     * @param jobSessionContext 上下文对象
     */
    protected abstract void execute(JobSessionContext jobSessionContext);
}
