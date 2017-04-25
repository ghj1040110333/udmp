package cn.com.git.udmp.component.batch.core.server.duty.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.base.Preconditions;

import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.common.constants.JobRunStatus;
import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.BasicInfo;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.component.chain.duty.AbsJobDuty;
import cn.com.git.udmp.component.batch.core.server.manage.JobStatusManager;
import cn.com.git.udmp.impl.batch.agent.ucc.IBatchAgentUCC;
import cn.com.git.udmp.impl.batch.agent.vo.BatchAgentVO;
import cn.com.git.udmp.impl.batch.jobRun.ucc.IBatchJobRunUCC;
import cn.com.git.udmp.impl.batch.jobRun.vo.BatchJobRunVO;

/**
 * @description 完成任务的责任点
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年5月6日 下午3:39:58
 */
@Deprecated
@Component
public class CompleteJobDuty extends AbsJobDuty {

    /**
     * @Fields batchJobRunUCC : batch_job_run表的ucc
     */
    @Autowired
    private IBatchJobRunUCC batchJobRunUCC;
    
    @Autowired
    private IBatchAgentUCC batchAgentUCC;

    @Autowired
    private JobStatusManager jobStatusManager;

    /**
     * 任务结束状态更新，执行的前提条件，责任链中该责任的执行条件判断
     * 
     * @description 任务结束状态更新，执行的前提条件
     * @version 1.0
     * @title 任务结束状态更新，执行的前提条件
     * @author bihb bihb_wb@newchinalife.com
     * @see cn.com.git.udmp.component.batch.core.IDuty#isDuty(cn.com.git.udmp.component.batch.context.SessionContext)
     * @param context 责任链处理上下文
     * @return 是否符合执行条件
     */
    public boolean isDuty(JobSessionContext context) {
        // 执行的条件：
        // 1.COMMAND_TYPE = REPORT-代理端向上
        // 2.COMMAND = FINISH-执行完毕
        // TODO 注意关联BatchSocketServer-> jobSessionContext.set("message",
        // message);
        // protobuf定义的批处理server-agent交互消息协议格式
        logger.debug("判断完成任务的操作是否完成...");
        Message message = (Message) ((JobSessionContext) context).get(BatchCommonConst.MESSAGE_NAME_IN_CONTEXT);
        String commandType = message.getHeader().getCommandType();
        String command = message.getHeader().getCommand();

        if ((BatchCommonConst.BATCH_MESSAGE_COMMAND_TYPE_REPORT.equalsIgnoreCase(commandType))
                && (BatchCommonConst.BATCH_MESSAGE_COMMAND_FINISH.equalsIgnoreCase(command))) {
            return true;
        }
        return false;
    }

    /**
     * 责任执行，基于job_run_id进行jobrun的状态更新
     * 
     * @description 责任执行，基于job_run_id进行jobrun的状态更新
     * @version 1.0
     * @title 责任执行，基于job_run_id进行jobrun的状态更新
     * @author bihb bihb_wb@newchinalife.com
     * @see cn.com.git.udmp.component.batch.core.server.duty.AbsJobDuty#execute(cn.com.git.udmp.component.batch.context.JobSessionContext)
     * @param context 责任链处理上下文
     * @return 责任链处理上下文
     */
    protected synchronized JobSessionContext execute(JobSessionContext context) {
        // 操作：
        // 对JOB.BASIC_INFO.RUN_ID 运行实例id进行t_udmp_batch_job_run记录状态更新
        // 只更新以下6个字段，其中审计信息由dao默认赋值
        // SUCCESS_CNT = #{success_cnt, jdbcType=NUMERIC} ,
        // UPDATE_TIME = SYSDATE ,
        // UPDATE_BY = #{update_by, jdbcType=NUMERIC} ,
        // UPDATE_TIMESTAMP = CURRENT_TIMESTAMP ,

        // TODO 注意关联BatchSocketServer-> jobSessionContext.set("message",
        // message);
        // protobuf定义的批处理server-agent交互消息协议格式
        Message message = (Message) ((JobSessionContext) context).get("message");

        // 状态常量 cn.com.git.udmp.component.batch.common.constants.JobRunStatus

        BatchJobRunVO batchJobRunVO = new BatchJobRunVO();
        // 对更新字段赋值
        BasicInfo basicInfo = message.getJob().getBasicInfo();
        // 主键runid
        batchJobRunVO.setJobRunId(new BigDecimal(basicInfo.getRunId()));
        // 待更新的数据记录状态
        batchJobRunVO.setStatus(basicInfo.getStatus());
        //updated by Liang on 2016/12/30  返回结果需要根据JobRunId和AgentId一起查询更新
        String fromIp = message.getSender().getFromIp();
        String fromPort = message.getSender().getFromPort();
        //updated by liang on 2017/1/3 更新任务状态需要跟上具体的执行代理AgentId
        batchJobRunVO.setAgentId(getAgentId(fromIp, fromPort));
        // 执行状态更新
        batchJobRunUCC.update(batchJobRunVO);
        logger.info("任务实例：{},agentIp:{},agentPort:{},执行状态更新成功,时间：{},更新后状态：{}", basicInfo.getRunId(), fromIp,fromPort,new Date(), basicInfo.getStatus());

        // 更改任务状态并激活任务依赖
        // 当前组下所有任务实例不全都在（成功、失败、部分成功）状态集中的状态，那么认为RUNNING状态，不进行依赖触发
        // 当前组下所有任务实例全都有在（成功、失败、部分成功）状态集中的状态，那么进行逻辑判断
        // 1.当前组下所有实例状态都是成功：JobStatusManager.setJobStatus(context.getJobId(),
        // StatusEnum.SUCCESS);
        // 2.当前组下所有实例状态都是失败：JobStatusManager.setJobStatus(context.getJobId(),
        // StatusEnum.FAIL);
        // 3.当前组下状态有成功或失败：JobStatusManager.setJobStatus(context.getJobId(),
        // StatusEnum.PART_SUCCESS);
        // 通过任务实例id找到这个任务组下所有实例，并判断出状态
         StatusEnum status = changeJobStatus(context,new BigDecimal(basicInfo.getRunId()));
        
        return context;
    }
    
    /**
     * @description 根据ip和端口获取agent的id
     * 
     * @param fromIp agent的IP
     * @param fromPort agent的PORT
     * @return 
    */
    private BigDecimal getAgentId(String fromIp, String fromPort) {
     // 根据fromIp和fromPort查询agentId号
        logger.debug("查询{}:{}的agentId",fromIp,fromPort);
        BatchAgentVO batchAgentVO = new BatchAgentVO();
        batchAgentVO.setAgentIp(fromIp);
        batchAgentVO.setAgentPort(new BigDecimal(fromPort));
        batchAgentVO.setIsEnable("1");
        List<BatchAgentVO> resultList = batchAgentUCC.findAll(batchAgentVO);
        if(resultList.size()>1){
            logger.warn("根据ip：{}和端口：{}查询到多个agent",fromIp,fromPort);
        }else if(resultList.size()==0){
            logger.error("当前接收{}:{}的Agent未注册，请检查当前Agent是否被临时删除",fromIp,fromPort);
            throw new RuntimeException("当前接收的Agent未注册，请检查当前Agent是否被临时删除");
        }
        BatchAgentVO result = resultList.get(0);
        return result.getAgentId();
    }

    /**
     * 通过任务实例id找到这个任务组下所有实例，并判断出状态
     * 
     * @description 通过任务实例id找到这个任务组下所有实例，并判断出状态
     * @version 1.0
     * @title 通过任务实例id找到这个任务组下所有实例，并判断出状态
     * @author bihb bihb_wb@newchinalife.com
     * @param context 
     * @param jobRunId 任务实例id
     * @return 判断的状态
     */
    private StatusEnum changeJobStatus(JobSessionContext context, BigDecimal jobRunId) {
        StatusEnum status = null;
        BatchJobRunVO batchJobRunVOQryByJobRunId = new BatchJobRunVO();
        batchJobRunVOQryByJobRunId.setJobRunId(jobRunId);
//        // 通过任务实例id找到这个任务实例，然后获取任务组编号
//        BatchJobRunVO currentBatchJobRunVO = batchJobRunUCC.find(batchJobRunVOQryByJobRunId);
//        String grpId = currentBatchJobRunVO.getSplitRelaGrpId();
//
//        BatchJobRunVO batchJobRunVOQryByGrpId = new BatchJobRunVO();
//        batchJobRunVOQryByGrpId.setSplitRelaGrpId(grpId);
        //updated by liang on 2016/12/30 通过jobRUnId查询任务实例集合
        List<BatchJobRunVO> batchJobRunVOList = batchJobRunUCC.findAll(batchJobRunVOQryByJobRunId);

        // 判断JobRun实例列表中，所有实例都有状态，并且在指定状态值域范围内。都符合返回true、否则返回false
        boolean isAllJobRunWithStatusInDomain = isAllJobRunWithStatusInDomain(batchJobRunVOList, new String[] {
                JobRunStatus.SUCCESS, JobRunStatus.FAIL, JobRunStatus.PART_SUCCESS, JobRunStatus.ABORTED });

        // 1 当前组下所有任务实例不全都在（成功、失败、部分成功）状态集中的状态，那么认为RUNNING状态，不进行依赖触发
        if (!isAllJobRunWithStatusInDomain) {
            // do nothing
            logger.info("----------------当前还未达到最终状态，等待更的整体任务{}的实例{}状态",context.getJobId(),context.getJobChainRunId() );
        } else {
            // 2 当前组下所有任务实例全都有在（成功、失败、部分成功）状态集中的状态，那么进行逻辑判断
            // 2.1 当前组下所有实例状态都是成功,返回StatusEnum.SUCCESS
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
            logger.info("----------------当前最终状态更新回执 得到的整体任务{}的任务链{}下实例{}状态是：{}" ,context.getJobId(),context.getJobChainRunId(),context.getJobRunId(),status);
            if ((StatusEnum.SUCCESS == status) || (StatusEnum.FAIL == status) || (StatusEnum.PART_SUCCESS == status)
                    || (StatusEnum.ABORTED == status)) {
                // 更改任务状态并激活任务依赖
                if(null==context.getJobChainRunId()){
                    //add by L.liang on 2017/1/9 若任务链实例ID为null,则通过任务实例ID反查任务实例表获取任务链实例ID
                    Preconditions.checkNotNull(context.getJobRunId());
                    BatchJobRunVO batchJobRunVO = new BatchJobRunVO();
                    batchJobRunVO.setJobRunId(new BigDecimal(context.getJobRunId()));
                    List<BatchJobRunVO> result = batchJobRunUCC.findAll(batchJobRunVO );
                    if(result.size()==0){
                        throw new FrameworkException("通过实例"+context.getJobRunId()+"查询不到对应的任务链ID信息");
                    }else{
                        context.setJobChainRunId(result.get(0).getJobChainRunId());
                    }
                }
                jobStatusManager.setJobStatus(context.getJobId(),context.getJobRunId(),context.getJobChainRunId(), status);
            }
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
    private boolean isAllJobRunWithStatusInDomain(List<BatchJobRunVO> batchJobRunVOList, String[] statusDomain) {
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
                }else if(JobRunStatus.RUNNING.equals(statusMap.get(batchJobRunVO.getStatus()))){
                    //updated by Liang on 2016/12/30 若存在任务实例状态运行中的则不更改状态
                    isAllJobRunWithStatusInDomain = false;
                    break;
                }else {
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
     * 返回batchJobRunUCC的属性值
     * 
     * @description 返回batchJobRunUCC的属性值
     * @version 1.0
     * @title 返回batchJobRunUCC的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取batchJobRunUCC的属性值
     */
    public IBatchJobRunUCC getBatchJobRunUCC() {
        return batchJobRunUCC;
    }

    /**
     * @description 给batchJobRunUCC属性赋值
     * @version 1.0
     * @title 给batchJobRunUCC属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param batchJobRunUCC batchJobRunUCC属性
     */
    public void setBatchJobRunUCC(IBatchJobRunUCC batchJobRunUCC) {
        this.batchJobRunUCC = batchJobRunUCC;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        
    }

    public void setBatchAgentUCC(IBatchAgentUCC batchAgentUCC) {
        this.batchAgentUCC = batchAgentUCC;
    }

    public void setJobStatusManager(JobStatusManager jobStatusManager) {
        this.jobStatusManager = jobStatusManager;
    }
}
