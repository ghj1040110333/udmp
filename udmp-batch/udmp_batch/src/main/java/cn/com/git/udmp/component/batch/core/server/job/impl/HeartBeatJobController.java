package cn.com.git.udmp.component.batch.core.server.job.impl;

import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.server.job.AbsJobController;

/**
 * @description 心跳监听任务的控制器
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年4月8日
 * @version 1.0
 */
public class HeartBeatJobController extends AbsJobController {

    @Override
    protected void execute(JobSessionContext jobSessionContext) {
        logger.debug("heartbeat the job");
        jobSessionContext.setCommand(CommandEnum.HEARTBEAT);
    }

    @Override
    public StatusEnum setCompleteStatus() {
        return null;
    }

    @Override
    public boolean isActiveOn() {
        return false;
    }

}
