package cn.com.git.udmp.component.batch.template.job;

import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.agent.TaskHandler;
import cn.com.git.udmp.component.batch.template.ITemplate;
import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 作业的统一模板
 * @author liuliang 
 * @date 2015年1月21日 下午2:55:34
 */
public interface IJobTemplate extends ITemplate,ILog {
    /**
     * @description 补充的作业执行方法
     * @author liuliang 
     * @param jobSessionContext 批处理作业上下文对象
     * @param handler 作业处理器
     * @return 批处理作业上下文对象
     */
    JobSessionContext doExecute(JobSessionContext jobSessionContext, TaskHandler handler);
}
