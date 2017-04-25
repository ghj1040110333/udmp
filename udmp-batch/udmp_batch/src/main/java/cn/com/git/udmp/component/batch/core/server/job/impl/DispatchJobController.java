package cn.com.git.udmp.component.batch.core.server.job.impl;

import cn.com.git.udmp.common.utils.IdGen;
import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.server.job.AbsJobController;

/**
 * @description 仅包含责任链机制,无任何其他处理的任务控制器
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年4月8日
 * @version 1.0
 */
public class DispatchJobController extends AbsJobController {

    @Override
    public void execute(JobSessionContext jobSessionContext) {
     // 判断是否有jobGroupJobId，若没有则新建
        if(jobSessionContext.getJobChainRunId() == null){
            // 生成一个新的jobChainRunId
            jobSessionContext.setJobChainRunId(IdGen.uuid());
            logger.debug("获取不到任务链实例ID，新增任务链实例ID：{}",jobSessionContext.getJobChainRunId());
        }
        logger.debug("run the job");
        // 设置需要执行的命令式DISPATCH--分片信息查询
        jobSessionContext.setCommand(CommandEnum.DISPATCH);
    }

    @Override
    public StatusEnum setCompleteStatus() {
        return null;
    }

    @Override
    public boolean isActiveOn() {
        return true;
    }

}
