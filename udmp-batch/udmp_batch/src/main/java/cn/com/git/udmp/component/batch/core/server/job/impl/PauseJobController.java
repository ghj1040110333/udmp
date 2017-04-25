package cn.com.git.udmp.component.batch.core.server.job.impl;

import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.server.job.AbsJobController;

/**
 * @description 暂停任务的控制器
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年4月8日
 * @version 1.0
 */
public class PauseJobController extends AbsJobController {

    @Override
    public void execute(JobSessionContext jobSessionContext) {
        // TODO 组装session对象
    }

    @Override
    public StatusEnum setCompleteStatus() {
        return null;
    }

    @Override
    public boolean isActiveOn() {
        // TODO Auto-generated method stub
        return true;
    }

}
