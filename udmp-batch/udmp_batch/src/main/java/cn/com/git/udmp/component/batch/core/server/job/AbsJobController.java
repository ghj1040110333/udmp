package cn.com.git.udmp.component.batch.core.server.job;

import org.springframework.beans.factory.annotation.Autowired;

import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.IDuty;
import cn.com.git.udmp.component.batch.core.component.chain.ChainExecutor;
import cn.com.git.udmp.component.batch.core.server.job.exception.CancelException;
import cn.com.git.udmp.component.batch.core.server.manage.JobStatusManager;
import cn.com.git.udmp.impl.batch.jobRunLog.ucc.impl.BatchJobRunLogUCC;
import cn.com.git.udmp.impl.batch.jobRunLog.vo.BatchJobRunLogVO;

/**
 * @description 任务控制器的抽象父类
 * @author liuliang
 * @date 2015年3月30日
 * @version 1.0
 */
public abstract class AbsJobController extends ChainExecutor implements IJobController {
    
    @Autowired
    protected JobStatusManager jobStatusManager;
    
    
    /**
     * @description 任务控制的执行操作(在责任链之前执行的特性操作)
     * @author liuliang
     * @param jobSessionContext 上下文
     */
    protected abstract void execute(JobSessionContext jobSessionContext);

    public void setDutys(IDuty[] dutys) {
        this.dutys = dutys;
    }

    @Override
    public final boolean control(JobSessionContext jobSessionContext) {
        boolean flag = false;
        try {
            if (preconditions()) {
                // 1.执行各自脚本
                execute(jobSessionContext);
                // 2.执行责任链任务
                executeChain(jobSessionContext);
                // 3.激活后续的任务
                activate(jobSessionContext);
                flag = true;
            } else {
                logger.debug("不满足条件，不执行任务{}的{}", jobSessionContext.getJobId(), this.getClass().getName());
            }
        }catch (CancelException e) {
            logger.info("发现终止信号:{}，终止当前{}操作",e.getMessage(),this.getClass().getSimpleName());
        }catch (RuntimeException e) {
//            logger.error("执行controller发生异常：{}", e);
            throw e;
        }finally {
            
        }
        return flag;
    }

    protected boolean preconditions() {
        return true;
    }

    /**
     * @description 设置任务控制器执行完成后需要修改的任务状态
     * @author liuliang
     * @return 状态
     */
    public abstract StatusEnum setCompleteStatus();

    /**
     * @description 激活任务依赖操作，若依赖开关返回false时则不会触发对应任务的依赖关系
     * @author liuliang
     * @param jobSessionContext job的session对象
     */
    public final void activate(JobSessionContext jobSessionContext) {
        // 激活依赖关系中的任务(在任务改变的时候已经执行激活依赖)
        /**
         * 1.获取jobId和当前command下会激活的所有job 2.检查对应job的条件是否满足，若满足则重新执行control方法
         */
        if (isActiveOn()) {
            jobStatusManager.setJobStatus(jobSessionContext.getJobId(), jobSessionContext.getJobRunId(),jobSessionContext.getJobChainRunId(),setCompleteStatus());
        }
    }

    /**
     * @description 是否需要激活任务依赖
     * @author liuliang
     * @return true/false
     */
    public abstract boolean isActiveOn();

    public void setJobStatusManager(JobStatusManager jobStatusManager) {
        this.jobStatusManager = jobStatusManager;
    }

}
