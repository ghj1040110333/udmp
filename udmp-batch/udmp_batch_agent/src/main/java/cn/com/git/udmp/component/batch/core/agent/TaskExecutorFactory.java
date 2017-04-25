package cn.com.git.udmp.component.batch.core.agent;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.Maps;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 作业执行器工厂
 * @author liuliang 
 * @date 2015年2月4日 上午10:19:13
 */
public class TaskExecutorFactory implements ILog{
    // TODO bihb-002-静态变量的使用，保留第一层线程池的句柄， ---没有向map里放置或使用及清理
    private static Map<String, ITaskExecutor> executors = new ConcurrentHashMap<String, ITaskExecutor>();

    /**
     * @description 获取作业执行器
     * @author liuliang 
     * @param jsContext 上下文对象
     * @return 任务执行器
     */
    // TODO bihb-001-是否应该添加同步获取第一层线程池
    public static ITaskExecutor getTaskExecutor(JobSessionContext jsContext) {
        //清理已经结束的map
        clearMap();
        logger.debug("线程池工厂:{}",executors);
        logger.debug("从线程池工厂中获取实例ID{}对应的线程池", jsContext.getJobRunId());
        if (executors.get(jsContext.getJobRunId()) == null) {
            String taskExecutorName="task-executor-"+jsContext.getJobRunId();
            ITaskExecutor executor = new TaskExecutor(taskExecutorName,jsContext);
            executors.put(jsContext.getJobRunId(), executor);
            return executor;
        }
        return executors.get(jsContext.getJobRunId());
    }


    private static void clearMap() {
        Set<String> keys = executors.keySet();
        for (String key : keys) {
            if(executors.get(key).isTerminated()||executors.get(key).isFinished()){
                executors.remove(key);
                logger.debug("任务实例{}已经结束,移出任务实例集合",key);
            }
        }
    }


    /**
     * @description 获取任务池集合对象
     * @author liuliang 
     * @return 任务池集合对象
     */
    public static Map<String, ITaskExecutor> getExecutors() {
        return executors;
    }
    
    /**
     * @title 
     * @description 回收线程池.
     *  
    */
    public static void recycleExecutor(){
        for(ITaskExecutor executor:executors.values()){
            executor.shutdown();
        }
    }
    
    
    
}
