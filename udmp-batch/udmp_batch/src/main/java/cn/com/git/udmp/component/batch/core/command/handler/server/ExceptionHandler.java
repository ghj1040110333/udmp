package cn.com.git.udmp.component.batch.core.command.handler.server;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.BasicInfo;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.impl.batch.jobRunLog.ucc.IBatchJobRunLogUCC;
import cn.com.git.udmp.impl.batch.jobRunLog.vo.BatchJobRunLogVO;
import cn.com.git.udmp.utils.lang.DateUtilsEx;


@Component
public class ExceptionHandler  implements IServerCommandHandler {
    /**
     * @Fields batchJobRunLogUCC : batch_job_run_log表的ucc
     */
    @Autowired
    private IBatchJobRunLogUCC batchJobRunLogUCC;
    
    @Override
    public JobSessionContext handle(JobSessionContext context) {
        // 操作：
        // 对t_udmp_batch_job_run_log新增一条记录
        // TODO 注意关联BatchSocketServer-> jobSessionContext.set("message",
        // message);
        // protobuf定义的批处理server-agent交互消息协议格式
        Message message = (Message) ((JobSessionContext) context).get(BatchCommonConst.MESSAGE_NAME_IN_CONTEXT);

        // 对更新字段赋值
        BasicInfo basicInfo = message.getJob().getBasicInfo();
        BatchJobRunLogVO batchJobRunLogVO = new BatchJobRunLogVO();
        // 主键runid
        batchJobRunLogVO.setJobRunId(new BigDecimal(basicInfo.getRunId()));
        batchJobRunLogVO.setLogLevel(basicInfo.getLogLevel());
        batchJobRunLogVO.setLogInfo(basicInfo.getLogInfo());
        batchJobRunLogVO.setLogTime(DateUtilsEx.getTodayDate());
        batchJobRunLogVO.setLogType(basicInfo.getLogType());
        

        batchJobRunLogUCC.add(batchJobRunLogVO);
        logger.info("任务实例：{},时间：{},添加异常'{}':'{}'成功", basicInfo.getRunId(), DateUtilsEx.getTodayDate(),
                basicInfo.getLogType(), basicInfo.getLogInfo());
        
        return context;
    }


    @Override
    public CommandEnum getCommand() {
        return CommandEnum.EXCEPTION;
    }

    public IBatchJobRunLogUCC getBatchJobRunLogUCC() {
        return batchJobRunLogUCC;
    }

    public void setBatchJobRunLogUCC(IBatchJobRunLogUCC batchJobRunLogUCC) {
        this.batchJobRunLogUCC = batchJobRunLogUCC;
    }

}
