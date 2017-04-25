package cn.com.git.udmp.component.batch.core.server.job.impl;

import org.springframework.stereotype.Component;

import cn.com.git.udmp.common.utils.IdGen;
import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.server.job.AbsJobController;

/**
 * @description 启动任务的控制器
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年4月8日
 * @version 1.0
 */
@Component("runJobController")
public class RunJobController extends AbsJobController {

    @Override
    protected void execute(JobSessionContext jobSessionContext) {
        // 判断是否有jobGroupJobId，若没有则新建
        if(jobSessionContext.getJobChainRunId() == null){
            // 生成一个新的jobChainRunId
            jobSessionContext.setJobChainRunId(IdGen.uuid());
        }
        logger.debug("run the job");
        jobSessionContext.setCommand(CommandEnum.START);
    }
    
    @Override
    public StatusEnum setCompleteStatus() {
        return StatusEnum.RUNNING;
    }

    @Override
    public boolean isActiveOn() {
        return true;
    }
    
}
