package cn.com.git.udmp.component.batch.core.server.manage;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 任务状态管理
 * @author liuliang
 * @date 2015年4月13日 上午10:08:57
 */
@Component
public class JobStatusManager implements ILog {
    /**
     * @Fields jobChainRunStatus : 任务实例对应的状态 <jobChainRunId,<jobRunId,status>>
     */
    private static ConcurrentHashMap<String, ConcurrentHashMap<String, StatusEnum>> jobChainRunStatus = new ConcurrentHashMap<String, ConcurrentHashMap<String, StatusEnum>>();

    @Autowired
    private  IJobDependManager jobDependManager;

    /**
     * @description 设置任务状态
     * @param jobId 任务ID
     * @param jobChainRunId 任务链实例ID
     * @param jobRunId 任务实例ID
     * @param status 任务状态
     */
    public void setJobStatus(String jobId, String jobRunId, String jobChainRunId, StatusEnum status) {
        if (status == null) {
            return;
        }
        // 设置当前任务链实例的任务实例集合-JobRunStatus
        ConcurrentHashMap<String, StatusEnum> jobRunstatus = getJobRunStatus(jobChainRunId);

        if (jobRunstatus.get(jobRunId) != null && jobRunstatus.get(jobRunId).equals(status)) {
            // 如果任务状态没变化，则不需要设置新的任务状态和激活依赖操作
            logger.debug("任务{}的状态无变化，仍是{}", jobId, status);
            // 2015-6-29 取消相同任务状态改变不激活依赖的机制
            // return;
        }
        logger.debug("任务{}的实例{}的状态由{}改变为{}，开始激活依赖任务", jobId, jobRunId, jobRunstatus.get(jobRunId), status);
        jobRunstatus.put(jobRunId, status);
        // 改变前置后置依赖
        jobDependManager.activateAfter(jobId, jobChainRunId, status);
        jobDependManager.stopDepend(jobId, jobChainRunId, status);
    }

    /**
     * @title
     * @description 获取jobChainRunId下的任务状态，加同步锁防止脏读
     * 
     * @param jobChainRunId
     * @return
     */
    private synchronized static ConcurrentHashMap<String, StatusEnum> getJobRunStatus(String jobChainRunId) {
        ConcurrentHashMap<String, StatusEnum> jobRunstatus;
        Preconditions.checkNotNull(jobChainRunId);
        if (jobChainRunStatus.get(jobChainRunId) != null) {
            jobRunstatus = jobChainRunStatus.get(jobChainRunId);
        } else {
            jobRunstatus = new ConcurrentHashMap<String, StatusEnum>();
            jobChainRunStatus.put(jobChainRunId, jobRunstatus);
        }
        return jobRunstatus;
    }

    public void setJobDependManager(IJobDependManager jobDependManager) {
        this.jobDependManager = jobDependManager;
    }

    public static ConcurrentHashMap<String, ConcurrentHashMap<String, StatusEnum>> getJobChainRunStatus() {
        return jobChainRunStatus;
    }

    public static ConcurrentHashMap<String, StatusEnum> getJobChainRunStatusById(String jobChainRunId) {
        return jobChainRunStatus.get(jobChainRunId);
    }

    public static void setJobChainRunStatus(
            ConcurrentHashMap<String, ConcurrentHashMap<String, StatusEnum>> jobChainRunStatus) {
        JobStatusManager.jobChainRunStatus = jobChainRunStatus;
    }
}
