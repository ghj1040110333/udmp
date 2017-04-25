package cn.com.git.udmp.component.batch.core.server.job;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 任务控制接口
 * @author liuliang 
 * @date 2015年7月7日 下午4:48:53
 */
public interface IJobController extends ILog{
    /**
     * @description 执行操作
     * @param jobSessionContext 上下文对象
     * @return 
     */
    public boolean control(JobSessionContext jobSessionContext);

}
