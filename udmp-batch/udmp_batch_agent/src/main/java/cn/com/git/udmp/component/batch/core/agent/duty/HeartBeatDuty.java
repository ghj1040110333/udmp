package cn.com.git.udmp.component.batch.core.agent.duty;

import java.util.Map;

import org.springframework.stereotype.Component;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.agent.ITaskExecutor;
import cn.com.git.udmp.component.batch.core.agent.TaskExecutor;
import cn.com.git.udmp.component.batch.core.agent.TaskExecutorFactory;
import cn.com.git.udmp.component.batch.core.component.chain.duty.AbsJobDuty;
import cn.com.git.udmp.component.batch.model.AgentInfo;
import io.netty.util.concurrent.Future;

/**
 * @description 心跳监听的duty
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年5月20日 下午5:55:47
 */
@Component("agentHeartBeatDuty")
public class HeartBeatDuty extends AbsJobDuty {

    @Override
    public boolean isDuty(JobSessionContext context) {
        return true;
    }

    @Override
    protected JobSessionContext execute(JobSessionContext context) {
        // TODO 将Agent服务器自身的状态信息装填进来(包括多少线程池，内存使用等等信息)
        logger.debug("正在执行心跳监听...");
        Map<String, ITaskExecutor> executors = TaskExecutorFactory.getExecutors();
        int liveCounts = 0;
        int livePoolCounts = 0;
        for (String key : executors.keySet()) {
            livePoolCounts++;
            TaskExecutor taskExecutor = (TaskExecutor) executors.get(key);
            Map<Integer, Future<?>> workMap = taskExecutor.getWorkMap();
            liveCounts += getLiveCounts(workMap);
        }
        AgentInfo info = new AgentInfo();
        //TODO batch--后续获取CPU参数
        info.setCpuLoad(liveCounts);
        info.setMemoryLoad(livePoolCounts);
        context.setAgentInfo(info);
        return context;
    }

    /**
     * @description 获取存活的线程数
     * @author liuliang liuliang@newchinalife.com
     * @param workMap 线程集合
     * @return 存活的线程数
     */
    private int getLiveCounts(Map<Integer, Future<?>> workMap) {
        int i = 0;
        for (Integer key : workMap.keySet()) {
            Future<?> future = workMap.get(key);
            if (future.isCancelled() || future.isDone()) {
                continue;
            }
            i++;
        }
        return i;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        
    }

}
