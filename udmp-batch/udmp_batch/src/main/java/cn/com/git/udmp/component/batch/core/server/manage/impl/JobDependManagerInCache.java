//package cn.com.git.udmp.component.batch.core.server.manage.impl;
//
//import java.math.BigDecimal;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
//import cn.com.git.udmp.component.batch.common.utils.BatchJobUtil;
//import cn.com.git.udmp.component.batch.common.utils.BatchModelUtil;
//import cn.com.git.udmp.component.batch.context.JobSessionContext;
//import cn.com.git.udmp.component.batch.core.server.job.IJobController;
//import cn.com.git.udmp.component.batch.core.server.manage.IJobDependManager;
//import cn.com.git.udmp.component.batch.core.server.manage.IJobManager;
//import cn.com.git.udmp.component.batch.core.server.manage.JobStatusManager;
//import cn.com.git.udmp.component.batch.model.JobConfig;
//import cn.com.git.udmp.impl.batch.jobDepend.ucc.IBatchJobDependUCC;
//import cn.com.git.udmp.impl.batch.jobDepend.vo.BatchJobDependVO;
//
///**
// * @description 任务的依赖管理类(内存存储版)
// * @author liuliang liuliang_wb@newchina.live
// * @date 2015年2月2日 下午1:57:32
// */
//public class JobDependManagerInCache implements IJobDependManager {
//    private IJobController runJobController;
//    private IJobController pauseJobController;
//    private IJobController stopJobController;
//    private IJobManager jobManager;
//    private IBatchJobDependUCC batchJobDependUCC;
//
//    /**
//     * @description 查询jobId对应的后续任务集
//     * @author liuliang liuliang@newchinalife.com
//     * @param jobId 任务ID
//     * @param status 任务状态
//     * @return 后续任务集
//     */
//    public List<String> findJobsAfter(String jobId, StatusEnum status) {
//        // TODO 查询jobId对应的后续任务集
//        BatchJobDependVO batchJobDependVO = new BatchJobDependVO();
//        batchJobDependVO.setActionJobId(new BigDecimal(jobId));
//        batchJobDependVO.setDependStatus(BatchJobUtil.getStatusFlagByEnum(status));
//        //TODO 查询后续任务
//        List<BatchJobDependVO> jobsAfter = batchJobDependUCC.findAll(batchJobDependVO);
//        ArrayList<String> jobsAfterList = new ArrayList<String>();
//        for (BatchJobDependVO key : jobsAfter) {
//            jobsAfterList.add(key.getDependJobId().toString());
//        }
//        return jobsAfterList;
//    }
//
//    @Override
//    public List<String> findJobsStop(String jobId, StatusEnum status) {
//        // TODO 查询停止条件包含jobId的任务
//        JobSessionContext jobSessionContext = null;
//        return new ArrayList<String>();
//    }
//
//    @Override
//    public Map<String, StatusEnum> findPreCondition(String jobId) {
//        BatchJobDependVO batchJobDependVO = new BatchJobDependVO();
//        batchJobDependVO.setActionJobId(new BigDecimal(jobId));
//        //TODO 查询后置依赖是jobId的列表
//        List<BatchJobDependVO> jobDependVOs = batchJobDependUCC.findAll(batchJobDependVO);
//        Map<String, StatusEnum> resultCondition = new HashMap<String, StatusEnum>();
//        for (BatchJobDependVO key : jobDependVOs) {
//            String dependStatus = key.getDependStatus();
//            resultCondition.put(key.getDependJobId().toString(), BatchJobUtil.getStatusEnumByFlag(dependStatus));
//        }
//        return resultCondition;
//    }
//
//    @Override
//    public void activateAfter(String jobId,String jobChainRunId, StatusEnum status) {
//        // TODO 检查并激活有需要的后置任务
//        List<String> jobs = findJobsAfter(jobId, status);
//        for (String key : jobs) {
//            Map<String, StatusEnum> preCondition = findPreCondition(key);
//            if (checkConditions(preCondition)) {
//                JobConfig jobConfig = jobManager.getJob(jobId);
//                JobSessionContext context = BatchModelUtil.patchJobSessionContext(jobConfig);
//                context.setJobChainRunId(jobChainRunId);
//                runJobController.control(context);
//            }
//        }
//    }
//
//    @Override
//    public void stopDepend(String jobId,String jobChainRunId, StatusEnum status) {
//        List<String> stopJobIds = findJobsStop(jobId, status);
//        for (String key : stopJobIds) {
//            if (checkStopCondition(key)) {
//                JobConfig jobConfig = jobManager.getJob(jobId);
//                JobSessionContext context = BatchModelUtil.patchJobSessionContext(jobConfig);
//                context.setJobChainRunId(jobChainRunId);
//                stopJobController.control(BatchModelUtil.patchJobSessionContext(jobConfig));
//            }
//        }
//    }
//
//    /**
//     * @description 校验是否满足停止条件
//     * @author liuliang liuliang@newchinalife.com
//     * @param jobId 任务ID
//     * @return true or false
//     */
//    private boolean checkStopCondition(String jobId) {
//        Map<String, StatusEnum> stopConditions = findStopConditions(jobId);
//        return checkConditions(stopConditions);
//    }
//
//    /**
//     * @description 校验条件是否匹配
//     * @author liuliang liuliang@newchinalife.com
//     * @param conditions 条件
//     * @return true or false
//     */
//    private boolean checkConditions(Map<String, StatusEnum> conditions) {
//        for (String preJobId : conditions.keySet()) {
//            if (!JobStatusManager.getJobStatus(preJobId).equals(conditions.get(preJobId))) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    /**
//     * @description 查询停止条件
//     * @author liuliang liuliang_wb@newchinalife.com
//     * @see cn.com.git.udmp.component.batch.core.server.manage.IJobDependManager#findStopConditions(java.lang.String)
//     * @param jobId 任务ID
//     * @return 停止条件
//    */
//    public Map<String, StatusEnum> findStopConditions(String jobId) {
//        return null;
//    }
//
//    public void setRunJobController(IJobController runJobController) {
//        this.runJobController = runJobController;
//    }
//
//    public void setPauseJobController(IJobController pauseJobController) {
//        this.pauseJobController = pauseJobController;
//    }
//
//    public void setStopJobController(IJobController stopJobController) {
//        this.stopJobController = stopJobController;
//    }
//
//    public void setJobManager(IJobManager jobManager) {
//        this.jobManager = jobManager;
//    }
//
//    public void setBatchJobDependUCC(IBatchJobDependUCC batchJobDependUCC) {
//        this.batchJobDependUCC = batchJobDependUCC;
//    }
//
//    @Override
//    public void boot() {
//        // TODO Auto-generated method stub
//    }
//
//    @Override
//    public void close() {
//        // TODO Auto-generated method stub
//    }
//
//}
