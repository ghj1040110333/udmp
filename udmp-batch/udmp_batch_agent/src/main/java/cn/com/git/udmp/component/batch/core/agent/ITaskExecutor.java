package cn.com.git.udmp.component.batch.core.agent;

import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 作业执行器
 * @author liuliang 
 * @date 2015年2月4日 上午10:07:23
 */
public interface ITaskExecutor extends ILog{

    /**
     * @description 启动作业
     */
    public void startTask();

    /**
     * @description 停止作业
     */
    public void stopTask();

    /**
     * @description 暂停作业
     */
    public void pauseTask();

    /**
     * @description 执行作业
     */
    public void executeTask();
    
    
    /**
     * @description 是否已终止
     * 
     * @return 
    */
    public boolean isTerminated();
    
    
    public void shutdown();
    
    public String getName();
    
    public boolean isFinished();
}
