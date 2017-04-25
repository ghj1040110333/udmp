package cn.com.git.udmp.component.batch.core.server.duty.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.BasicInfo;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.component.chain.duty.AbsJobDuty;
import cn.com.git.udmp.impl.batch.jobRunLog.ucc.IBatchJobRunLogUCC;
import cn.com.git.udmp.impl.batch.jobRunLog.vo.BatchJobRunLogVO;
import cn.com.git.udmp.utils.lang.DateUtilsEx;

/**
 * 运行过程中汇报异常信息的消息处理类
 * 
 * @description 运行过程中汇报异常信息的消息处理类
 * @author bihb bihb_wb@newchinalife.com
 * @date 2015年4月29日 下午4:06:45
 */
@Deprecated
@Component
public class ExceptionReportDuty extends AbsJobDuty {
    /**
     * @Fields batchJobRunLogUCC : batch_job_run_log表的ucc
     */
    @Autowired
    private IBatchJobRunLogUCC batchJobRunLogUCC;

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
        // 2.COMMAND = EXCEPTION-异常信息
        if ((BatchCommonConst.BATCH_MESSAGE_COMMAND_TYPE_REPORT.equalsIgnoreCase(commandType))
                && (BatchCommonConst.BATCH_MESSAGE_COMMAND_EXCEPTION.equalsIgnoreCase(command))) {
            return true;
        }
        return false;
    }

    @Override
    protected JobSessionContext execute(JobSessionContext context) {
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

    /**
     * 返回batchJobRunLogUCC的属性值
     * 
     * @description 返回batchJobRunLogUCC的属性值
     * @version 1.0
     * @title 返回batchJobRunLogUCC的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取batchJobRunLogUCC的属性值
     */
    public IBatchJobRunLogUCC getBatchJobRunLogUCC() {
        return batchJobRunLogUCC;
    }

    /**
     * @description 给batchJobRunLogUCC属性赋值
     * @version 1.0
     * @title 给batchJobRunLogUCC属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param batchJobRunLogUCC batchJobRunLogUCC属性
     */
    public void setBatchJobRunLogUCC(IBatchJobRunLogUCC batchJobRunLogUCC) {
        this.batchJobRunLogUCC = batchJobRunLogUCC;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        
    }

}
