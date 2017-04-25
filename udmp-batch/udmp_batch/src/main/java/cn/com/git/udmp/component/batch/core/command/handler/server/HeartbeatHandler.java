package cn.com.git.udmp.component.batch.core.command.handler.server;

import org.springframework.stereotype.Component;

import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.server.manage.AgentManager;
import cn.com.git.udmp.component.batch.model.AgentInfo;

@Component
public class HeartbeatHandler implements IServerCommandHandler{

    @Override
    public JobSessionContext handle(JobSessionContext context) {
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
    public CommandEnum getCommand() {
        return CommandEnum.HEARTBEAT;
    }

}
