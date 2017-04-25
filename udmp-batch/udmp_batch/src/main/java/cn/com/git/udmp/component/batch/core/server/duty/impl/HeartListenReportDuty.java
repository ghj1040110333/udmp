package cn.com.git.udmp.component.batch.core.server.duty.impl;

import org.springframework.stereotype.Component;

import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.component.chain.duty.AbsJobDuty;
import cn.com.git.udmp.component.batch.core.server.manage.AgentManager;
import cn.com.git.udmp.component.batch.model.AgentInfo;

/**
 * @description 心跳监听责任链
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年4月8日
 * @version 1.0
 */
@Deprecated
@Component("heartListenDuty")
public class HeartListenReportDuty extends AbsJobDuty {

    @Override
    public boolean isDuty(JobSessionContext context) {
        if (context.getCommand() == CommandEnum.HEARTBEAT) {
            // 如果执行命令是心跳监听，则开始执行心跳监听的逻辑
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected JobSessionContext execute(JobSessionContext context) {
        // TODO 心跳监听并且将agents信息存入context中
        logger.debug("正在处理心跳监听...");
        AgentInfo agentInfo = context.getAgentInfo();
        agentInfo.setActive(true);
        AgentManager.updateAgentInfo(agentInfo);
        logger.debug("内存中的agent信息有{}", AgentManager.getAgentInfo());
        // logger.debug("获取到的cpuLoad是:{}",agentInfo.getCpuLoad());
        return context;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        
    }

}
