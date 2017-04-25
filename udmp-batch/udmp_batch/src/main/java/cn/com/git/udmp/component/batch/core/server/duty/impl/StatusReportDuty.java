package cn.com.git.udmp.component.batch.core.server.duty.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.BasicInfo;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.component.chain.duty.AbsJobDuty;
import cn.com.git.udmp.impl.batch.agent.ucc.IBatchAgentUCC;
import cn.com.git.udmp.impl.batch.agent.ucc.impl.BatchAgentUCC;
import cn.com.git.udmp.impl.batch.agent.vo.BatchAgentVO;
import cn.com.git.udmp.impl.batch.jobRun.ucc.IBatchJobRunUCC;
import cn.com.git.udmp.impl.batch.jobRun.vo.BatchJobRunVO;

/**
 * 运行过程中汇报执行数据的消息处理类
 * 
 * @description 运行过程中汇报执行数据的消息处理类
 * @author bihb bihb_wb@newchinalife.com
 * @date 2015年4月29日 下午4:06:45
 */
@Deprecated
@Component("serverStatusReportDuty")
public class StatusReportDuty extends AbsJobDuty {
    /**
     * @Fields batchJobRunUCC : batch_job_run表的ucc
     */
    @Autowired
    private IBatchJobRunUCC batchJobRunUCC;
    @Autowired
    private IBatchAgentUCC batchAgentUCC;
    // 由于接口重构，不实现原有消息处理接口，继承责任链抽象类,原代码注释掉

    // /**
    // * 运行过程中汇报执行数据的消息处理
    // *
    // * @description 运行过程中汇报执行数据的消息处理
    // * @version 1.0
    // * @title 运行过程中汇报执行数据的消息处理
    // * @author bihb bihb_wb@newchinalife.com
    // * @see
    // cn.com.git.udmp.component.batch.core.server.message.IMessageHandler#process
    // *
    // (cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message)
    // * @param message
    // * protobuf定义的批处理server-agent交互消息协议格式
    // */
    // @Override
    // public void process(Message message) {
    // // 执行的条件：
    // // 1.COMMAND_TYPE = REPORT-代理端向上
    // // 2.COMMAND = STATUS-执行过程中的信息反馈
    // String commandType = message.getHeader().getCommandType();
    // String command = message.getHeader().getCommand();
    // // 操作：
    // // 对JOB.BASIC_INFO.RUN_ID 运行实例id进行t_udmp_batch_job_run记录更新
    // // 只更新以下6个字段，其中审计信息由dao默认赋值
    // // SUCCESS_CNT = #{success_cnt, jdbcType=NUMERIC} ,
    // // TOTAL_CNT = #{total_cnt, jdbcType=NUMERIC} ,
    // // FAILED_CNT = #{failed_cnt, jdbcType=NUMERIC} ,
    // // UPDATE_TIME = SYSDATE ,
    // // UPDATE_BY = #{update_by, jdbcType=NUMERIC} ,
    // // UPDATE_TIMESTAMP = CURRENT_TIMESTAMP ,
    // if (("REPORT".equalsIgnoreCase(commandType)) &&
    // ("STATUS".equalsIgnoreCase(command))) {
    // BatchJobRunVO batchJobRunVO = new BatchJobRunVO();
    // // 对更新字段赋值
    // BasicInfo basicInfo = message.getJob().getBasicInfo();
    // // 主键runid
    // batchJobRunVO.setJobRunId(new BigDecimal(basicInfo.getRunId()));
    // // 待更新的数据记录数
    // batchJobRunVO.setTotalCnt(new BigDecimal(basicInfo.getTotalCnt()));
    // batchJobRunVO.setSuccessCnt(new BigDecimal(basicInfo.getSuccessCnt()));
    // batchJobRunVO.setFailedCnt(new BigDecimal(basicInfo.getFailedCnt()));
    // // 执行更新
    // batchJobRunUCC.updateBatchJobRunCntStatus(batchJobRunVO);
    // logger.info("任务实例：{},执行进度更新成功,时间：{},当前总记录数：{},当前成功记录数：{},当前失败记录数：{}",
    // basicInfo.getRunId(), new Date(),
    // basicInfo.getTotalCnt(), basicInfo.getSuccessCnt(),
    // basicInfo.getFailedCnt());
    // }
    //
    // }

    /**
     * @description 执行前提条件
     * @version 1.0
     * @title 执行前提条件
     * @author bihb bihb_wb@newchinalife.com
     * @see cn.com.git.udmp.component.batch.core.IDuty#isDuty(cn.com.git.udmp.component.batch.context.SessionContext)
     * @param context 责任链处理上下文
     * @return 是否符合执行条件
     */
    @Override
    public boolean isDuty(JobSessionContext context) {
        // TODO 注意关联BatchSocketServer-> jobSessionContext.set("message",
        // message);
        // protobuf定义的批处理server-agent交互消息协议格式
        Message message = (Message) ((JobSessionContext) context).get(BatchCommonConst.MESSAGE_NAME_IN_CONTEXT);
        String commandType = message.getHeader().getCommandType();
        String command = message.getHeader().getCommand();
        // 执行的条件：
        // 1.COMMAND_TYPE = REPORT-代理端向上
        // 2.COMMAND = STATUS-执行过程中的信息反馈
        if ((BatchCommonConst.BATCH_MESSAGE_COMMAND_TYPE_REPORT.equalsIgnoreCase(commandType))
                && (BatchCommonConst.BATCH_MESSAGE_COMMAND_STATUS.equalsIgnoreCase(command))) {
            return true;
        }
        return false;
    }

    @Override
    protected JobSessionContext execute(JobSessionContext context) {
        // 操作：
        // 对JOB.BASIC_INFO.RUN_ID 运行实例id进行t_udmp_batch_job_run记录更新
        // 只更新以下6个字段，其中审计信息由dao默认赋值
        // SUCCESS_CNT = #{success_cnt, jdbcType=NUMERIC} ,
        // TOTAL_CNT = #{total_cnt, jdbcType=NUMERIC} ,
        // FAILED_CNT = #{failed_cnt, jdbcType=NUMERIC} ,
        // UPDATE_TIME = SYSDATE ,
        // UPDATE_BY = #{update_by, jdbcType=NUMERIC} ,
        // UPDATE_TIMESTAMP = CURRENT_TIMESTAMP ,

        // TODO 注意关联BatchSocketServer-> jobSessionContext.set("message",
        // message);
        // protobuf定义的批处理server-agent交互消息协议格式
        Message message = (Message) ((JobSessionContext) context).get(BatchCommonConst.MESSAGE_NAME_IN_CONTEXT);

        BatchJobRunVO batchJobRunVO = new BatchJobRunVO();
        // 对更新字段赋值
        BasicInfo basicInfo = message.getJob().getBasicInfo();
        // 主键runid
        batchJobRunVO.setJobRunId(new BigDecimal(basicInfo.getRunId()));
        String fromIp = message.getSender().getFromIp();
        String fromPort = message.getSender().getFromPort();
        //updated by liang on 2017/1/3 更新任务状态需要跟上具体的执行代理AgentId
        batchJobRunVO.setAgentId(getAgentId(fromIp, fromPort));
        // 待更新的数据记录数
        batchJobRunVO.setTotalCnt(new BigDecimal(basicInfo.getTotalCnt()));
        batchJobRunVO.setSuccessCnt(new BigDecimal(basicInfo.getSuccessCnt()));
        batchJobRunVO.setFailedCnt(new BigDecimal(basicInfo.getFailedCnt()));
        // 执行更新
        batchJobRunUCC.update(batchJobRunVO);
        logger.info("任务实例：{},agentIp:{},agentPort:{},执行进度更新成功,时间：{},当前总记录数：{},当前成功记录数：{},当前失败记录数：{}", basicInfo.getRunId(), fromIp,fromPort,new Date(),
                basicInfo.getTotalCnt(), basicInfo.getSuccessCnt(), basicInfo.getFailedCnt());

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

}
