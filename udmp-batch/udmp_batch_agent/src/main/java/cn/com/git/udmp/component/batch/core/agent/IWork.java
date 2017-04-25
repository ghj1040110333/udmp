package cn.com.git.udmp.component.batch.core.agent;

import java.util.concurrent.Callable;

import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 作业执行接口
 * @author liuliang
 * @date 2015年7月7日 下午2:50:42
 */
public interface IWork<V> extends Callable<V>, ILog {
    /**
     * @description 执行前操作
     * @author liuliang
     */
    public void preWork();

    /**
     * @description 作业执行操作
     * @author liuliang
     */
    public void executeWork();

    /**
     * @description 作业执行后操作
     * @author liuliang
     */
    public void afterWork();

    /**
     * @title 停止工作
     * @description
     * 
     */
    public void stopWork();

    /**
     * @return 
     * @title 判断是否已停止
     * @description
     * 
     */
    public boolean isStoped();
    
    
    /**
     * @title
     * @description 判断是否完成线程操作
     * 
     * @return 
    */
    public boolean isFinished();
}
