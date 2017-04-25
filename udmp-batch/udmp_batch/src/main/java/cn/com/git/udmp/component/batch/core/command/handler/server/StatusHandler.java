package cn.com.git.udmp.component.batch.core.command.handler.server;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.BasicInfo;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.impl.batch.agent.ucc.IBatchAgentUCC;
import cn.com.git.udmp.impl.batch.agent.vo.BatchAgentVO;
import cn.com.git.udmp.impl.batch.jobRun.ucc.IBatchJobRunUCC;
import cn.com.git.udmp.impl.batch.jobRun.vo.BatchJobRunVO;


@Component
public class StatusHandler implements IServerCommandHandler{
    /**
     * @Fields batchJobRunUCC : batch_job_run表的ucc
     */
    @Autowired
    private IBatchJobRunUCC batchJobRunUCC;
    @Autowired
    private IBatchAgentUCC batchAgentUCC;
    
    @Override
    public JobSessionContext handle(JobSessionContext context) {
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

    @Override
    public CommandEnum getCommand() {
        return CommandEnum.STATUS;
    }

}
