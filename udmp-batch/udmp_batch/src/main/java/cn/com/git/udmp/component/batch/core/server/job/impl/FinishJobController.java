package cn.com.git.udmp.component.batch.core.server.job.impl;

import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.server.job.AbsJobController;

/**
 * @description 停止任务的控制器
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年4月8日
 * @version 1.0
 */
@Deprecated
public class FinishJobController extends AbsJobController {

    @Override
    public void execute(JobSessionContext jobSessionContext) {
        // TODO Auto-generated method stub

    }

    @Override
    public StatusEnum setCompleteStatus() {
        return StatusEnum.SUCCESS;
    }

    @Override
    public boolean isActiveOn() {
        return true;
    }

}
