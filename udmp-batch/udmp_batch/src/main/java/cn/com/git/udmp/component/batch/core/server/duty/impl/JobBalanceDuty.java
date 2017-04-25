package cn.com.git.udmp.component.batch.core.server.duty.impl;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.component.chain.duty.AbsJobDuty;

/**
 * @deprecated
 * @description 负载均衡责任链
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年4月8日
 * @version 1.0
 */
public class JobBalanceDuty extends AbsJobDuty {

    @Override
    public boolean isDuty(JobSessionContext context) {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    protected JobSessionContext execute(JobSessionContext context) {
        // TODO 负载均衡
        logger.debug("正在执行负载均衡...");
        return context;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        
    }

}
