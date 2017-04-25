package cn.com.git.udmp.component.batch.core.component.chain.duty;

import cn.com.git.udmp.common.exception.FrameworkException;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.context.SessionContext;
import cn.com.git.udmp.component.batch.core.IDuty;
import cn.com.git.udmp.component.batch.core.component.chain.DutyEndException;

/**
 * @description 任务责任链的抽象父类
 * @author liuliang
 * @date 2015年4月8日
 * @version 1.0
 */
public abstract class AbsJobDuty implements IDuty {

    @Override
    public final boolean isDuty(SessionContext context) {
        if (context instanceof JobSessionContext) {
            JobSessionContext jsContext = (JobSessionContext) context;
            return isDuty(jsContext);
        } else {
            throw new FrameworkException("任务中传递的上下文参数不是JobSessionContext类型");
        }
    }

    @Override
    public final SessionContext executeDuty(SessionContext context) {
        if (isDuty(context)) {
            if (context instanceof JobSessionContext) {
                doExecuteDuty((JobSessionContext) context);
            } else {
                // TODO batch-责任链的其他情况
                JobSessionContext jobSessionContext = new JobSessionContext();
                jobSessionContext.setJobId((String) context.get("jobId"));
                doExecuteDuty(jobSessionContext);
            }
        }
        return context;
    }

    private void doExecuteDuty(JobSessionContext context) {
        check(context);
        execute(context);
    }

    /**
     * @title
     * @description 校验duty条件，不满足抛出DutyEndException或者CancelException可以终止责任链或者停止当前任务
     * @param context 
    */
    protected void check(JobSessionContext context) {
    }

    /**
     * @description 执行责任
     * @author liuliang liuliang@newchinalife.com
     * @param context 上下文对象
     * @return 上下文对象
     */
    protected abstract JobSessionContext execute(JobSessionContext context);

    /**
     * @description 是否执行任务，若false则跳过当前任务
     * @author liuliang liuliang@newchinalife.com
     * @param context 上下文对象
     * @return 上下文对象
     */
    public abstract boolean isDuty(JobSessionContext context);
}
