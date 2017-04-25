package cn.com.git.udmp.component.batch.core.server.manage;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.quartz.CronScheduleBuilder;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdScheduler;

import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.common.utils.BatchModelUtil;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.model.JobConfig;
import cn.com.git.udmp.component.batch.schedule.quartz.job.AbsBatchQuartzJob;
import cn.com.git.udmp.component.batch.schedule.quartz.job.impl.BasicBatchQuartzJob;
import cn.com.git.udmp.component.batch.schedule.quartz.job.impl.StartBatchQuartzJob;
import cn.com.git.udmp.core.exception.FrameworkRuntimeException;
import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 批处理的定时框架管理类
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年4月9日
 * @version 1.0
 */
public class BatchQuartzManager implements ILog{
    private static StdScheduler quartzScheduler;
    private static boolean initFlag;

    /**
     * @description 启动定时调度
     */
    public static void quartzInit() {
        quartzScheduler = SpringContextHolder.getBean(StdScheduler.class);
//        quartzScheduler = (StdScheduler) SpringContextHolder
//                .getBean(BatchCommonConst.BATCH_QUARTZ_SCHEDULER_FACTORY_NAME);
        // TODO batch--测试用 正式环境删除
        logger.debug("==============Quartz初始化===============");
        try {
//            quartzScheduler.clear();
            quartzScheduler.start();
        } catch (SchedulerException e1) {
            e1.printStackTrace();
            logger.error("清除任务异常");
        }
        initFlag = true;
        logger.debug("==============Quartz初始化完成=============");
        // 需要从数据库中加载所有的任务列表
    }

    /**
     * @description 停止定时调度模块
     * @author liuliang liuliang@newchinalife.com
     */
    public static void quartzStop() {
        logger.debug("正在停止定时调度模块...");
        quartzScheduler = (StdScheduler) SpringContextHolder
                .getBean(BatchCommonConst.BATCH_QUARTZ_SCHEDULER_FACTORY_NAME);
        if (initFlag) {
            // 如果启动标志initFlag为true，则可以调用停止逻辑
            quartzScheduler.standby();
            // quartzScheduler.shutdown(true);
            initFlag = false;
        }
        logger.info("定时调度模块的暂停状态:{}", quartzScheduler.isInStandbyMode());
    }

    /**
     * TODO batch-修改表操作需要添加审计操作
     * 
     * @description 删除quartz中的任务
     * @author liuliang liuliang@newchinalife.com
     * @param jobConfig 任务配置
     */
    public static void delete(JobConfig jobConfig) {
        try {
            if (StringUtils.isEmpty(jobConfig.getJobId()) || jobConfig.getJobGroupId() < 1) {
                throw new FrameworkRuntimeException("删除定时任务失败，未指定任务ID或任务组ID");
            }
            JobKey jobKey = new JobKey(jobConfig.getJobId(), String.valueOf(jobConfig.getJobGroupId()));
            quartzScheduler.deleteJob(jobKey);
        } catch (SchedulerException e) {
            // e.printStackTrace();
            logger.error("删除quartz任务{}-{}异常:{}", jobConfig.getJobGroupId(), jobConfig.getJobId(), e);
        }
    }

    /**
     * @description 设置定时启动任务
     * @param jobConfig 任务配置
     */
    public static void setRunJob(JobConfig jobConfig) {
        if (initFlag) {
            addJob(CommandEnum.DISPATCH, jobConfig, null);
        }
    }

    /**
     * @description 设置定时启动任务
     * @param jobConfig 任务配置
     * @param date 日期
     */
    public static void setTempRunJob(JobConfig jobConfig, Date date) {
        if (initFlag) {
            addJob(CommandEnum.DISPATCH, jobConfig, date);
        }
    }

    /**
     * @deprecated
     * @description 设置定时暂停任务
     * @param jobConfig 任务配置
     */
    public static void setPauseJob(JobConfig jobConfig) {
        if (initFlag) {
            addJob(CommandEnum.PAUSE, jobConfig, null);
        }
    }

    /**
     * @description 设置定时停止任务
     * @param jobConfig 任务配置
     */
    public static void setStopJob(JobConfig jobConfig) {
        if (initFlag) {
            addJob(CommandEnum.ABORT, jobConfig, null);
        }
    }

    /**
     * @description 设置定时停止任务
     * @param jobConfig 任务配置
     */
    public static void setStopJob(JobConfig jobConfig, Date date) {
        if (initFlag) {
            addJob(CommandEnum.ABORT, jobConfig, date);
        }
    }

    /**
     * @deprecated
     * @description 添加定时任务的监听器
     * @author liuliang liuliang@newchinalife.com
     * @param jobConfig
     */
    public static void addListener(JobConfig jobConfig) {
    }

    /**
     * @description 添加任务
     * @author liuliang liuliang@newchinalife.com
     * @param command 执行命令的枚举类
     * @param jobConfig 任务配置信息
     * @param setTime 设置的启动时间（如JovConfig中包含cron表达式或启动时间则此参数赋值为null）
     */
    private static void addJob(CommandEnum command, JobConfig jobConfig, Date setTime) {
        try {
            logger.debug("添加定时任务...");
            // 传递参数
            JobDataMap jobDataMap = new JobDataMap();
            JobSessionContext jobSessionContext = BatchModelUtil.patchJobSessionContext(jobConfig);
            jobSessionContext.setCommand(command);
            jobDataMap.put(AbsBatchQuartzJob.JOB_SESSION_CONTEXT, jobSessionContext);

            // 构建jobBuilder,根据指令获取对应类型的定时任务类,这里的类必须继承quartz的job接口
            // （启动指令则获取定时启动任务的类,停止命令则获取定时停止任务的类）
            JobBuilder jobBuilder = JobBuilder.newJob(getClazzByCommand(command));
            jobBuilder.withIdentity(jobConfig.getJobId(), String.valueOf(jobConfig.getJobGroupId()));
            jobBuilder.setJobData(jobDataMap);
            // 构建JobDetail
            JobDetail jobDetail = jobBuilder.build();
            Trigger trigger;
            TriggerBuilder<Trigger> triggerBuilder = TriggerBuilder.newTrigger().withIdentity(
                    getTriggerName(jobConfig.getJobId()), String.valueOf(jobConfig.getJobGroupId()));
            if (setTime != null) {
                logger.debug("设置任务{}的启动时间是{}", jobConfig.getJobName(), setTime);
                // 配置misfire策略为错过不执行
                trigger = triggerBuilder
                        .startAt(setTime)
                        .withSchedule(
                                SimpleScheduleBuilder.simpleSchedule()
                                        .withMisfireHandlingInstructionNextWithRemainingCount()).build();
            } else if ((null != jobConfig.getJobFrequency())
                    && (!"".equalsIgnoreCase(jobConfig.getJobFrequency().trim()))) {
                logger.debug("设置任务{}的trigger,频率为{}", jobConfig.getJobName(), jobConfig.getJobFrequency());
                // 配置misfire策略为do-nothing
                trigger = triggerBuilder.withSchedule(
                        CronScheduleBuilder.cronSchedule(jobConfig.getJobFrequency())
                                .withMisfireHandlingInstructionDoNothing())
                // TODO 测试用
                // .startAt(jobConfig.getJobStartWindow())
                        .build();
            } else if (jobConfig.getJobStartWindow() != null) {
                logger.debug("设置任务{}的启动时间是{}", jobConfig.getJobName(), jobConfig.getJobStartWindow());
                // 配置misfire策略为错过不执行
                trigger = triggerBuilder
                        .startAt(jobConfig.getJobStartWindow())
                        .withSchedule(
                                SimpleScheduleBuilder.simpleSchedule()
                                        .withMisfireHandlingInstructionNextWithRemainingCount()).build();
            } else {
                return;
            }
            // 校验是否已经存在对应的定时任务,若存在则删除库中的任务
            JobKey jobKey = new JobKey(jobConfig.getJobId(), String.valueOf(jobConfig.getJobGroupId()));
            if (quartzScheduler.checkExists(jobKey)) {
                quartzScheduler.deleteJob(jobKey);
            }
            // 添加定时任务
            quartzScheduler.scheduleJob(jobDetail, trigger);

            // //jobListener添加
            // JobKey jobKey = new
            // JobKey(jobConfig.getJobId(),String.valueOf(jobConfig.getJobGroupId()));
            // Matcher<JobKey> jobMatcher = KeyMatcher.keyEquals(jobKey);
            // quartzScheduler.getListenerManager().addJobListener(new
            // BasicBatchJobListener(),jobMatcher);

        } catch (RuntimeException e) {
            logger.error("添加定时任务失败{}", e);
            throw new FrameworkRuntimeException(e.getMessage());
        } catch (SchedulerException e) {
            e.printStackTrace();
            logger.error("添加定时任务失败{}", e);
        }
    }

    /**
     * @description 返回对应的任务类
     * @author liuliang liuliang@newchinalife.com
     * @param command 命令
     * @return 任务类
     */
    private static Class<? extends Job> getClazzByCommand(CommandEnum command) {
        switch (command) {
        case START:
            return StartBatchQuartzJob.class;
        default:
            break;
        }
        return BasicBatchQuartzJob.class;
    }

    /**
     * @description 生成trigger名字
     * @author liuliang liuliang@newchinalife.com
     * @param jobId 任务ID
     * @return trigger名称
     */
    private static String getTriggerName(String jobId) {
        return "trigger" + jobId;
    }

}
