package cn.com.git.udmp.component.batch.core.agent.duty;

import org.springframework.stereotype.Component;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.agent.communicate.AgentCommunicator;
import cn.com.git.udmp.component.batch.core.component.chain.duty.AbsJobDuty;

/**
 * @description 远程通信报告的责任点
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年5月14日
 * @version 1.0
 */
@Component("agentRemoteReportDuty")
public class RemoteReportDuty extends AbsJobDuty {

    
    
    @Override
    public boolean isDuty(JobSessionContext context) {
        return true;
    }

    @Override
    protected JobSessionContext execute(JobSessionContext context) {
        // TODO batch-将agent端处理的信息发送回server端
        AgentCommunicator communicator = new AgentCommunicator(context);
        communicator.sendBack(null);
        return context;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub

    }

}
