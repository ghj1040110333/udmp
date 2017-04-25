package cn.com.git.udmp.component.batch.core.command.handler.server;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import cn.com.git.udmp.common.utils.StringUtils;
import cn.com.git.udmp.component.batch.common.constants.BatchCommonConst;
import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Job;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.command.handler.ICommandHandler;
import cn.com.git.udmp.component.batch.core.server.job.IJobController;
import cn.com.git.udmp.core.logging.ILog;
import cn.com.git.udmp.impl.batch.jobRun.ucc.IBatchJobRunUCC;
import cn.com.git.udmp.impl.batch.jobRun.vo.BatchJobRunVO;

/** 
 * @description 分片结果处理器
 * @author Liang liuliang1@git.com.cn 
 * @date 2017年2月8日 上午9:55:48  
*/
@Component
public class DispatchHandler implements IServerCommandHandler,ILog{

    @Autowired
    @Qualifier("runJobController")
    private IJobController runJobController; // 执行任务的控制器
    
    @Autowired
    private IBatchJobRunUCC batchJobRunUCC;
    
    @Override
    public JobSessionContext handle(JobSessionContext context) {
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
        return context;
    }

    @Override
    public CommandEnum getCommand() {
        return CommandEnum.DISPATCH;
    }
    
}
