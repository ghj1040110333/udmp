package cn.com.git.udmp.component.batch.core.server.duty.impl.check;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.server.job.exception.CancelException;
import cn.com.git.udmp.component.batch.core.server.manage.impl.JobDependManagerInDB;
import cn.com.git.udmp.component.batch.core.server.manage.impl.JobManagerInDB;
import cn.com.git.udmp.component.batch.model.JobConfig;

/** 
 * @description 启动任务的前置条件检查
 * @author Liang liuliang1@git.com.cn 
 * @date 2016年12月9日 上午10:36:51  
*/
@Component("checkRunConditionDuty")
public class CheckRunConditionDuty extends AbsCheckConditionDuty {
    @Autowired
    JobManagerInDB jobManager;
    
    @Autowired
    JobDependManagerInDB JobDependManager;
    
    @Override
    public void close() {
        // TODO 自动生成的方法存根
        
    }

    @Override
    protected JobSessionContext execute(JobSessionContext context) {
        return context;
    }

    @Override
    public boolean isDuty(JobSessionContext context) {
        logger.debug("执行CheckRunConditionDuty,校验任务启动条件");
        return true;
    }

    @Override
    protected boolean checkDutyCondition(JobSessionContext context) {
      //TODO 是否执行责任链,若是任务组，则不执行责任链；若存在任务实例ID，且当前任务实例已经成功，则不执行责任链
        String jobId = context.getJobId();
        String jobChainRunId = context.getJobChainRunId();
        String jobRunId = context.getJobRunId();
        Preconditions.checkNotNull(jobId);
        JobConfig job = jobManager.getJob(jobId);
        if(job.getIsGroup().equals("1")){
            //若是任务组，则终止runJobController的责任链，依然会激活后续任务
           return false;
        }else{
            //若是任务，则判断是否达到启动条件，没达到则抛出终止操作异常
            if(!checkRunCondtion(jobId,jobChainRunId,context.getJobChainBatch())){
                logger.warn("任务链{}的任务{}不满足启动条件，终止启动操作",jobChainRunId,jobId);
                throw new CancelException("启动任务操作不满足");
            }else{
                logger.info("任务链{}的任务{}满足启动条件，执行启动任务操作",jobChainRunId,jobId);
            }
        }
        return true;
    }

    /**
     * @description 检查任务前置运行条件
     * 
     * @param jobId
     * @param jobChainRunID
     * @param jobChainBatch 
     * @return 
    */
    private boolean checkRunCondtion(String jobId, String jobChainRunID, int jobChainBatch) {
        return JobDependManager.checkPreCondtion(jobId, jobChainRunID,jobChainBatch);
    }


}
