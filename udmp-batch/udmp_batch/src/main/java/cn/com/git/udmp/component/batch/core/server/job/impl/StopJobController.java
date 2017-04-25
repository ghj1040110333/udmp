package cn.com.git.udmp.component.batch.core.server.job.impl;

import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.server.job.AbsJobController;

/**
 * @description 停止任务的控制器
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年4月8日
 * @version 1.0
 */
public class StopJobController extends AbsJobController {

    @Override
    public void execute(JobSessionContext jobSessionContext) {
        // 设置当前操作的执行命令式ABORT-停止任务命令
        jobSessionContext.setCommand(CommandEnum.ABORT);
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
