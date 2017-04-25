package cn.com.git.udmp.component.batch.core.server.manage.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.common.constants.JobRunStatus;
import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.common.utils.BatchJobUtil;
import cn.com.git.udmp.component.batch.common.utils.BatchModelUtil;
import cn.com.git.udmp.component.batch.common.utils.StatusUtil;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.server.job.IJobController;
import cn.com.git.udmp.component.batch.core.server.manage.IJobDependManager;
import cn.com.git.udmp.component.batch.core.server.manage.IJobManager;
import cn.com.git.udmp.component.batch.core.server.manage.JobStatusManager;
import cn.com.git.udmp.component.batch.model.JobConfig;
import cn.com.git.udmp.impl.batch.jobDepend.ucc.IBatchJobDependUCC;
import cn.com.git.udmp.impl.batch.jobDepend.vo.BatchJobDependVO;
import cn.com.git.udmp.impl.batch.jobRun.ucc.IBatchJobRunUCC;
import cn.com.git.udmp.impl.batch.jobRun.vo.BatchJobRunVO;

/**
 * @description 任务的依赖管理类(数据库实时通信版)
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年2月2日 下午1:57:32
 */
@Component("jobDependManager")
public class JobDependManagerInDB implements IJobDependManager {
    @Autowired
    @Qualifier("dispatchJobController")
    private IJobController runJobController;
    @Autowired
    @Qualifier("stopJobController")
    private IJobController stopJobController;
    @Autowired
    private IJobManager jobManager;
    @Autowired
    private IBatchJobDependUCC batchJobDependUCC;
    // updated by liang - 添加任务实例信息的操作类
    @Autowired
    private IBatchJobRunUCC batchJobRunUCC;

    public void setBatchJobRunUCC(IBatchJobRunUCC batchJobRunUCC) {
        this.batchJobRunUCC = batchJobRunUCC;
    }

    /**
     * @description 查询jobId对应的后续任务集
     * @author liuliang liuliang@newchinalife.com
     * @param jobId 任务ID
     * @param status 任务状态
     * @return 后续任务集
     */
    public List<String> findJobsAfter(String jobId, StatusEnum status) {
        logger.debug("查询以{}状态的任务{}为启动条件的任务", status, jobId);
        // 查询jobId对应的后续任务集
        BatchJobDependVO batchJobDependVO = new BatchJobDependVO();
        batchJobDependVO.setIsDeleted(BatchCommonConst.BATCH_FLAG_FALSE);
        batchJobDependVO.setDependJobId(new BigDecimal(jobId));
        // batchJobDependVO.setActionJobId(new BigDecimal(jobId));
        // 设置依赖状态
        batchJobDependVO.setDependStatus(BatchJobUtil.getStatusFlagByEnum(status));
        // TODO 设置前置依赖类型
        batchJobDependVO.setDependType(BatchCommonConst.BATCH_JOB_RELATION_PRE);
        List<BatchJobDependVO> jobsAfter = batchJobDependUCC.findAll(batchJobDependVO);
        // List<BatchJobDependVO> jobsAfter =
        // batchJobDependUCC.findAllBatchJobDependPreCond(batchJobDependVO);
        ArrayList<String> jobsAfterList = new ArrayList<String>();
        for (BatchJobDependVO key : jobsAfter) {
            jobsAfterList.add(key.getActionJobId().toString());
        }
        return jobsAfterList;
    }

    /**
     * @description 查询停止条件包含jobId的任务
     * @author liuliang liuliang@newchinalife.com
     * @param jobId 任务ID
     * @param status 任务状态
     * @return 任务集合
     */
    public List<String> findJobsStop(String jobId, StatusEnum status) {
        logger.debug("查询以{}状态的任务{}为启动条件的任务", status, jobId);
        // 查询jobId对应的后续任务集
        BatchJobDependVO batchJobDependVO = new BatchJobDependVO();
        batchJobDependVO.setIsDeleted(BatchCommonConst.BATCH_FLAG_FALSE);
        batchJobDependVO.setDependJobId(new BigDecimal(jobId));
        // 设置依赖状态
        batchJobDependVO.setDependStatus(BatchJobUtil.getStatusFlagByEnum(status));
        // 设置后置依赖类型
        batchJobDependVO.setDependType(BatchCommonConst.BATCH_JOB_RELATION_AFT);
        List<BatchJobDependVO> jobsAfter = batchJobDependUCC.findAll(batchJobDependVO);
        // List<BatchJobDependVO> jobsAfter =
        // batchJobDependUCC.findAllBatchJobDependPreCond(batchJobDependVO);
        ArrayList<String> jobStopList = new ArrayList<String>();
        for (BatchJobDependVO key : jobsAfter) {
            jobStopList.add(key.getActionJobId().toString());
        }
        return jobStopList;
    }

    /**
     * @description 查询前置任务及其状态
     * @author liuliang liuliang@newchinalife.com
     * @param jobId 任务ID
     * @return 前置任务及其状态
     */
    public Map<String, StatusEnum> findPreCondition(String jobId) {
        BatchJobDependVO batchJobDependVO = new BatchJobDependVO();
        batchJobDependVO.setIsDeleted(BatchCommonConst.BATCH_FLAG_FALSE);
        batchJobDependVO.setActionJobId(new BigDecimal(jobId));
        batchJobDependVO.setDependType(BatchCommonConst.BATCH_JOB_RELATION_PRE);

        List<BatchJobDependVO> jobDependVOs = batchJobDependUCC.findAll(batchJobDependVO);
        Map<String, StatusEnum> resultCondition = new HashMap<String, StatusEnum>();
        for (BatchJobDependVO key : jobDependVOs) {
            String dependStatus = key.getDependStatus();
            resultCondition.put(key.getDependJobId().toString(), BatchJobUtil.getStatusEnumByFlag(dependStatus));
        }
        return resultCondition;
    }

    /**
     * @title
     * @description 根据任务ID和任务链实例ID校验前置条件
     * 
     * @param jobId 任务ID
     * @param jobChainRunId 任务链实例ID
     * @return
     */
    public boolean checkPreCondtion(String jobId, String jobChainRunId) {
        return checkPreCondtion(jobId, jobChainRunId, 1);
    }

    /**
     * @title
     * @description 根据任务ID和任务链实例ID校验前置条件
     * 
     * @param jobId 任务ID
     * @param jobChainRunId 任务链实例ID
     * @return
     */
    public boolean checkPreCondtion(String jobId, String jobChainRunId, int jobChainBatch) {
        Map<String, StatusEnum> preCondition = findPreCondition(jobId);
        logger.debug("{}的启动条件有{}", jobId, preCondition);
        return checkConditions(jobChainRunId, jobChainBatch, preCondition);
    }

    /**
     * @title
     * @description
     *
     * @see cn.com.git.udmp.component.batch.core.server.manage.IJobDependManager#activateAfter(java.lang.String,
     *      java.lang.String,
     *      cn.com.git.udmp.component.batch.common.constants.StatusEnum)
     * @param jobId
     * @param jobChainRunId
     * @param status updated by L.liang - add the param "jobChainRunId"
     */
    @Override
    public void activateAfter(String jobId, String jobChainRunId, StatusEnum status) {
        logger.debug("激活{}的后置任务", jobId);
        int lastBatch = batchJobRunUCC.findLastBatch(jobChainRunId);
        // 检查并激活有需要的后置任务
        List<String> jobs = findJobsAfter(jobId, status);
        logger.debug("{}在状态{}下会触发的任务有{}", jobId, status, jobs);
        for (String key : jobs) {
            Map<String, StatusEnum> preCondition = findPreCondition(key);
            // logger.debug("任务{}的启动条件是{}", jobId, preCondition);
            logger.debug("任务{}的启动条件有{}", key, preCondition);
            if (checkConditions(jobChainRunId,lastBatch, preCondition)) {
                logger.debug("任务{}满足启动条件，启动任务", key);
                JobConfig jobConfig = jobManager.getJob(key);
                JobSessionContext context = BatchModelUtil.patchJobSessionContext(jobConfig);
                // transfer the jobChainRunId to the next job
                context.setJobChainRunId(jobChainRunId);
                context.setJobChainBatch(lastBatch);
                runJobController.control(context);
            } else {
                logger.debug("任务{}不满足启动条件", key);
            }
        }
    }

    @Override
    public void stopDepend(String jobId, String jobChainRunId, StatusEnum status) {
        logger.debug("停止以{}作为停止条件且停止条件全部满足的任务", jobId);
        List<String> stopJobIds = findJobsStop(jobId, status);
        logger.debug("以{}状态的任务{}作为停止条的任务有{}", status, jobId, stopJobIds);
        for (String stopJobId : stopJobIds) {
            if (checkStopCondition(stopJobId, jobChainRunId)) {
                // 检查任务id为key的任务是否满足停止条件，若满足则激活停止流程
                JobConfig jobConfig = jobManager.getJob(stopJobId);
                JobSessionContext context = BatchModelUtil.patchJobSessionContext(jobConfig);
                // updated by L.liang - transf the jobGroupId to the next job
                context.setJobChainRunId(jobChainRunId);
                stopJobController.control(context);
            }
        }
    }

    /**
     * @description 校验是否满足停止条件
     * @author liuliang liuliang@newchinalife.com
     * @param jobId 任务ID
     * @return true or false
     */
    public boolean checkStopCondition(String jobId, String jobChainRunID) {
        Map<String, StatusEnum> stopConditions = findStopConditions(jobId);
        logger.debug("任务{}的停止条件是{}", jobId, stopConditions);
        return checkConditions(jobChainRunID, stopConditions);
    }

    /**
     * @description 校验是否满足停止条件
     * @author liuliang liuliang@newchinalife.com
     * @param jobId 任务ID
     * @param jobChainRunID 任务链实例ID
     * @param jobChainBatch 任务链运行批次号（第一次跑为1，失败重跑每次批次+1）
     * @return true or false
     */
    public boolean checkStopCondition(String jobId, String jobChainRunID, int jobChainBatch) {
        Map<String, StatusEnum> stopConditions = findStopConditions(jobId);
        logger.debug("任务{}的停止条件是{}", jobId, stopConditions);
        return checkConditions(jobChainRunID, stopConditions);
    }

    /**
     * @description 校验条件是否匹配
     * @author liuliang liuliang@newchinalife.com
     * @param jobChainRunId 任务组实例ID
     * @param conditions 条件
     * @return true or false
     * @updated by L.liang on 2016/12/12 - 修改任务条件匹配规则需要锁定是一个任务链实例下的任务
     */
    private boolean checkConditions(String jobChainRunId, Map<String, StatusEnum> conditions) {
        return checkConditions(jobChainRunId, 1, conditions);
    }

    /**
     * @title 根据任务ID和任务链ID查询对应的任务实例状态集合
     * @description
     * 
     * @param jobId
     * @param jobChainRunId
     * @param jobChainBatch
     * @return
     */
    private List<BatchJobRunVO> getJobRunList(String jobId, String jobChainRunId, int jobChainBatch) {
        BatchJobRunVO batchAgentVO = new BatchJobRunVO();
        batchAgentVO.setJobId(new BigDecimal(jobId));
        batchAgentVO.setJobChainRunId(jobChainRunId);
        batchAgentVO.setJobChainBatch(new BigDecimal(jobChainBatch));
        return batchJobRunUCC.findAll(batchAgentVO);
    }

    /**
     * @description 校验条件是否匹配
     * @author liuliang liuliang@newchinalife.com
     * @param jobChainRunId 任务组实例ID
     * @param jobChainBatch 任务链实例批次
     * @param conditions 条件
     * @return true or false
     * @updated by L.liang on 2016/12/12 - 修改任务条件匹配规则需要锁定是一个任务链实例下的任务
     */
    private boolean checkConditions(String jobChainRunId, int jobChainBatch, Map<String, StatusEnum> conditions) {
        for (String jobId : conditions.keySet()) {
            // 通过任务ID和任务链实例ID一级任务链批次号匹配查询任务实例的状态是否匹配
            List<BatchJobRunVO> jobRunVOList = getJobRunIdByJobId(jobId, jobChainRunId, jobChainBatch);
            StatusEnum status = getJobStatusFromResult(jobRunVOList);
            if (!status.equals(conditions.get(jobId))) {
                return false;
            }
        }
        return true;
    }

    /**
     * @description 比较多个任务实例状态，获取整体任务状态
     * @param batchJobRunVOList 整体任务集合
     * @return
     */
    public static StatusEnum getJobStatusFromResult(List<BatchJobRunVO> batchJobRunVOList) {
        StatusEnum status = StatusEnum.RUNNING;
        if (isAllJobRunWithStatusInDomain(batchJobRunVOList, new String[] { JobRunStatus.SUCCESS })) {
            status = StatusEnum.SUCCESS;
        } else if (isAllJobRunWithStatusInDomain(batchJobRunVOList, new String[] { JobRunStatus.FAIL })) {
            // 2.2 当前组下所有实例状态都是失败,返回StatusEnum.FAIL
            status = StatusEnum.FAIL;
        } else if (isAllJobRunWithStatusInDomain(batchJobRunVOList, new String[] { JobRunStatus.PART_SUCCESS })) {
            // 2.3 当前组下状态有成功或失败,返回StatusEnum.PART_SUCCESS
            status = StatusEnum.PART_SUCCESS;
        } else if (isAllJobRunWithStatusInDomain(batchJobRunVOList, new String[] { JobRunStatus.ABORTED })) {
            status = StatusEnum.ABORTED;
        }

        return status;
    }

    /**
     * 判断JobRun实例列表中，所有实例都有状态，并且在指定状态值域范围内。都符合返回true、否则返回false
     * 
     * @description 判断JobRun实例列表中，所有实例都有状态，并且在指定状态值域范围内。都符合返回true、否则返回false
     * @version 1.0
     * @title 判断JobRun实例列表中，所有实例都有状态，并且在指定状态值域范围内。都符合返回true、否则返回false
     * @author bihb bihb_wb@newchinalife.com
     * @param batchJobRunVOList JobRun实例的列表
     * @param statusDomain 状态集合
     * @return JobRun实例列表中，所有实例都有状态，并且在指定状态值域范围内。都符合返回true、否则返回false
     */
    private static boolean isAllJobRunWithStatusInDomain(List<BatchJobRunVO> batchJobRunVOList, String[] statusDomain) {
        // 默认是true都符合，逻辑判断检测不符合的场景来置为false
        boolean isAllJobRunWithStatusInDomain = true;
        if ((null != statusDomain) && (statusDomain.length > 0) && batchJobRunVOList.size() > 0) {
            // 状态集非空，先放入hash[key=status;
            // value=status]表进行快速定位，否则与第一个list参数结合会变成双重循环时间复杂度
            HashMap<String, String> statusMap = new HashMap<String, String>();
            for (int index = 0; index < statusDomain.length; index++) {
                String status = statusDomain[index];
                statusMap.put(status, status);
            } // end of statusDomain string array for-loop
              // 循环list进行状态判断，
            for (Iterator<BatchJobRunVO> iter = batchJobRunVOList.iterator(); iter.hasNext();) {
                BatchJobRunVO batchJobRunVO = iter.next();
                if (null == statusMap.get(batchJobRunVO.getStatus())) {
                    // 实例状态不在指定值域内，不管是没有状态，还是进行中，都返回false
                    isAllJobRunWithStatusInDomain = false;
                    break;
                } else if (JobRunStatus.RUNNING.equals(statusMap.get(batchJobRunVO.getStatus()))) {
                    // updated by Liang on 2016/12/30 若存在任务实例状态运行中的则不更改状态
                    isAllJobRunWithStatusInDomain = false;
                    break;
                } else {
                    // 循环一遍，所有实例状态都在指定值域内，默认继续，最终返回默认值true.
                } // end of in statusDomain judgement
            } // end of batchJobRunVOList for-loop
              // end of parameter: statusDomain & batchJobRunVOList not empty
        } else {
            // 参数为空，返回false
            isAllJobRunWithStatusInDomain = false;
        }
        return isAllJobRunWithStatusInDomain;
    }

    /**
     * @title TODO 获取任务实例ID
     * @description 根据任务ID和任务链ID获取任务实例ID
     * @updated by L.liang on 2017/1/13 校验条件还需要匹配任务链批次号
     * @param preJobId
     * @param jobChainRunId
     * @param jobChainBatch 任务链批次
     * @return
     */
    private List<BatchJobRunVO> getJobRunIdByJobId(String preJobId, String jobChainRunId, int jobChainBatch) {
        int defalut_batch = 1;
        Preconditions.checkNotNull(jobChainRunId);
        BatchJobRunVO batchJobRunVO = new BatchJobRunVO();
        batchJobRunVO.setJobId(new BigDecimal(preJobId));
        batchJobRunVO.setJobChainRunId(jobChainRunId);
        batchJobRunVO.setJobChainBatch(
                jobChainBatch > defalut_batch ? new BigDecimal(jobChainBatch) : new BigDecimal(defalut_batch));
        List<BatchJobRunVO> result = batchJobRunUCC.findAll(batchJobRunVO);
        if (result == null || result.size() == 0) {
            if (jobChainBatch == 1) {
                logger.warn("任务{}在执行链实例{}的批次{}下没有找到实例信息", preJobId, jobChainRunId, jobChainBatch);
            } else {
                logger.debug("任务{}在执行链实例{}的批次{}下没有找到实例信息,执行批次减一查询", preJobId, jobChainRunId, jobChainBatch);
                return getJobRunIdByJobId(preJobId, jobChainRunId, jobChainBatch - 1);
            }
        }
        return result;
    }

    /**
     * @description 根据任务ID查询对应任务的停止 依赖条件
     * @author liuliang liuliang_wb@newchinalife.com
     * @see cn.com.git.udmp.component.batch.core.server.manage.IJobDependManager#findStopConditions(java.lang.String)
     * @param jobId 任务ID
     * @return 停止的依赖条件（任务ID和对应任务状态）
     */
    public Map<String, StatusEnum> findStopConditions(String jobId) {
        BatchJobDependVO batchJobDependVO = new BatchJobDependVO();
        batchJobDependVO.setIsDeleted(BatchCommonConst.BATCH_FLAG_FALSE);
        batchJobDependVO.setActionJobId(new BigDecimal(jobId));
        batchJobDependVO.setDependType(BatchCommonConst.BATCH_JOB_RELATION_AFT);
        List<BatchJobDependVO> jobDependVOs = batchJobDependUCC.findAllBatchJobDepend(batchJobDependVO);
        Map<String, StatusEnum> resultCondition = new HashMap<String, StatusEnum>();
        for (BatchJobDependVO key : jobDependVOs) {
            String dependStatus = key.getDependStatus();
            resultCondition.put(key.getDependJobId().toString(), BatchJobUtil.getStatusEnumByFlag(dependStatus));
        }
        return resultCondition;
    }

    public void setRunJobController(IJobController runJobController) {
        this.runJobController = runJobController;
    }

    public void setStopJobController(IJobController stopJobController) {
        this.stopJobController = stopJobController;
    }

    public void setJobManager(IJobManager jobManager) {
        this.jobManager = jobManager;
    }

    public void setBatchJobDependUCC(IBatchJobDependUCC batchJobDependUCC) {
        this.batchJobDependUCC = batchJobDependUCC;
    }

    @Override
    public void boot() {
    }

    @Override
    public void close() {
    }

}
