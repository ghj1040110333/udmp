package cn.com.git.udmp.component.batch.core.server.duty.impl.check;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.component.chain.DutyEndException;
import cn.com.git.udmp.component.batch.core.component.chain.duty.AbsJobDuty;

/** 
 * @description 责任链检查条件抽象父类，若不满足条件中断责任链
 * @author Liang liuliang1@git.com.cn 
 * @date 2016年12月9日 下午2:28:02  
*/
public abstract class AbsCheckConditionDuty extends AbsJobDuty {
    @Override
    protected void check(JobSessionContext context) {
        if(!checkDutyCondition(context)){
            throw new DutyEndException("执行条件不满足，结束Duty链");
        }
    }

    protected abstract boolean checkDutyCondition(JobSessionContext context);
}
