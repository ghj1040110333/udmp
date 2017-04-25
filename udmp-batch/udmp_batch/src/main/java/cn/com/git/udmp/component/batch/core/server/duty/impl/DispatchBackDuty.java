package cn.com.git.udmp.component.batch.core.server.duty.impl;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cn.com.git.udmp.common.utils.StringUtils;
import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.common.utils.BatchJobUtil;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Job;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.component.chain.duty.AbsJobDuty;
import cn.com.git.udmp.component.batch.core.server.job.IJobController;
import cn.com.git.udmp.impl.batch.jobRun.ucc.IBatchJobRunUCC;
import cn.com.git.udmp.impl.batch.jobRun.vo.BatchJobRunVO;

/**
 * @description 分页信息回执后的责任点
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年5月6日 下午3:39:58
 */
@Deprecated
@Component
public class DispatchBackDuty extends AbsJobDuty {
    @Autowired
    @Qualifier("runJobController")
    private IJobController runJobController; // 执行任务的控制器
    
    @Autowired
    private IBatchJobRunUCC batchJobRunUCC;

    @Override
    public boolean isDuty(JobSessionContext context) {
        JobSessionContext jobSessionContext = (JobSessionContext) context;
        Message message = (Message) ((JobSessionContext) context).get(BatchCommonConst.MESSAGE_NAME_IN_CONTEXT);
        String commandType = message.getHeader().getCommandType();
        String command = message.getHeader().getCommand();

        if ((BatchCommonConst.BATCH_MESSAGE_COMMAND_TYPE_REPORT.equalsIgnoreCase(commandType))
                && (BatchJobUtil.getCommandEnumByName(command) == CommandEnum.DISPATCH)) {
            // 当返回的报文是DISPATCH命令的返回报文时，开始调度DISPATCH的后续操作
            logger.debug("任务{}的DISPATCH命令回执了", jobSessionContext.getJobId());
            return true;
        }
        return false;
    }

    @Override
    protected JobSessionContext execute(JobSessionContext context) {
        if (checkCondition(context)) {
            // 如果当前任务还满足操作条件，则开始执行任务分发
            logger.debug("DISPATCH工作已完成，开始执行运行任务命令");
            // 设置需要处理的区间值
            Message message = (Message) ((JobSessionContext) context).get(BatchCommonConst.MESSAGE_NAME_IN_CONTEXT);
            //TODO dispatch操作没返回jobRunId和jobChainRunId
            Job job = message.getJob();
            String chainRunId = job.getBasicInfo().getChainRunId();
            if(!StringUtils.isEmpty(chainRunId)){
                context.setJobChainRunId(chainRunId);
                int lastBatch = batchJobRunUCC.findLastBatch(chainRunId);
                logger.debug("dispatch返回的batch是{}",lastBatch);
                //设置批次号
                BatchJobRunVO batchJobRunVO = new BatchJobRunVO();
                batchJobRunVO.setJobChainBatch(new BigDecimal(lastBatch));
                batchJobRunVO.setJobId(new BigDecimal(job.getBasicInfo().getId()));
                batchJobRunVO.setJobChainRunId(chainRunId);
                List<BatchJobRunVO> result = batchJobRunUCC.findAll(batchJobRunVO );
                if(result==null||result.size()==0){
                    context.setJobChainBatch(lastBatch);
                }else{
                    context.setJobChainBatch(lastBatch+1);
                }
            }else{
                throw new NullPointerException("查询任务"+job.getBasicInfo().getId()+"的分片信息时未返回任务链实例ID");
            }
            
            context.setStartNum(job.getShardInfo().getStartRowNum());
            context.setEndNum(job.getShardInfo().getEndRowNum());
            // TODO !!!!!需要在运行的时候再次传递参数，否则查询总数的时候有参数，运行时没有参数，会造成数据错误。
            runJobController.control(context);
        }
        return context;
    }

    /**
     * @description 校验任务是否还满足启动条件(时间之类的..)
     * @author liuliang liuliang@newchinalife.com
     * @param context 上下文参数
     * @return 校验结果
     */
    private boolean checkCondition(JobSessionContext context) {
        // TODO batch--校验任务是否还满足启动条件(时间之类的..)
        return true;
    }

    public void setRunJobController(IJobController runJobController) {
        this.runJobController = runJobController;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        
    }

    public void setBatchJobRunUCC(IBatchJobRunUCC batchJobRunUCC) {
        this.batchJobRunUCC = batchJobRunUCC;
    }

}
