package cn.com.git.udmp.component.batch.core.agent;

import java.util.Map;

import com.google.common.base.Preconditions;

import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.exception.BatchBizException;
import cn.com.git.udmp.component.batch.template.job.AbstractPrePostJobTemplate;
import cn.com.git.udmp.core.exception.FrameworkRuntimeException;
import cn.com.git.udmp.utils.log.MDCLogUtil;

/**
 * @description 作业执行代理类
 * @author liuliang
 * @param <V>
 * @date 2015年2月4日 上午10:38:56
 */
public class Work implements IWork<Boolean> {
    private int workId;
    private AbstractPrePostJobTemplate job;
    private JobSessionContext jsContext;
    private TaskHandler taskHandler;
    private static ThreadLocal<Boolean> stopFlagThreadLocal = new ThreadLocal() {
        @Override
        protected Object initialValue() {
            return false;
        }
    };
    private boolean finishFlag = false;

    /**
     * <p>
     * Title: 批处理作业线程类
     * </p>
     * <p>
     * Description: 注入线程ID，上下文参数和作业处理器
     * </p>
     * 
     * @param workId 线程ID
     * @param jsContext 上下文参数
     * @param handler 作业处理器
     */
    public Work(int workId, JobSessionContext jsContext, TaskHandler handler) {
        this.workId = workId;
        this.jsContext = jsContext;
        this.taskHandler = handler;
        MDCLogUtil.setJobRunId(jsContext.getJobRunId());
    }

    @Override
    public Boolean call() throws Exception {
        MDCLogUtil.setJobContext(jsContext);
        // TODO
        // bhib-006-可以添加启动前和启动后的过滤器，添加在pre和after方法中就可以，能添加filter的list。更liuliang确认后再添加
        preWork();
        executeWork();
        afterWork();
        return this.isFinished() && !this.isStoped();
    }

    @Override
    public void preWork() {
        // TODO Auto-generated method stub
        logger.debug("线程{}正在执行作业", Thread.currentThread().getName());
        if (jsContext.isSpringBean()) {
            job = (AbstractPrePostJobTemplate) SpringContextHolder.getBean(jsContext.getJobClazzName());
            if (null == job) {
                throw new FrameworkRuntimeException("请检查{}是否使用spring声明", jsContext.getJobClazzName());
            }
        } else {
            try {
                job = (AbstractPrePostJobTemplate) Class.forName(jsContext.getJobClazzName()).newInstance();
            } catch (RuntimeException e) {
                e.printStackTrace();
                throw new FrameworkRuntimeException("job初始化失败");
            } catch (InstantiationException e) {
                e.printStackTrace();
                throw new FrameworkRuntimeException("job初始化失败");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new FrameworkRuntimeException("job初始化失败");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new FrameworkRuntimeException("job初始化失败");
            }
        }
        Preconditions.checkNotNull(job);
        job.setJobSessionContext(jsContext);
        // 执行预操作
        job.preExecute(jsContext);
        taskHandler.logStart(workId);
    }

    @Override
    public void executeWork() {
        job.doExecute(getJsContext(), taskHandler);
    }

    /**
     * @description 获取线程中的异常
     * @author liuliang liuliang@newchinalife.com
     * @return 异常信息
     */
    private Map<String, String> getExceptions() {
        // TODO
        return null;
    }

    @Override
    public void afterWork() {
        // 跑完了设置关闭标记
        this.finishFlag = true;
        // TODO 回执线程池告知此线程已完成
        // logger.debug("线程{}执行作业结束", Thread.currentThread().getName());
        // taskHandler.log(true, workId, null);
    }

    public AbstractPrePostJobTemplate getJob() {
        return job;
    }

    public void setJob(AbstractPrePostJobTemplate job) {
        this.job = job;
    }

    public JobSessionContext getJsContext() {
        return jsContext;
    }

    public void setJsContext(JobSessionContext jsContext) {
        this.jsContext = jsContext;
    }

    @Override
    public void stopWork() {
        stopFlagThreadLocal.set(true);
    }

    @Override
    public boolean isStoped() {
        return stopFlagThreadLocal.get();
    }

    public static boolean isCurrentJobStoped() {
        return stopFlagThreadLocal.get();
    }

    @Override
    public boolean isFinished() {
        return this.finishFlag;
    }

}
