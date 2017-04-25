package cn.com.git.udmp.component.batch.core.agent.duty;

import org.springframework.stereotype.Component;

import cn.com.git.udmp.common.utils.SpringContextHolder;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.agent.job.AbstractBatchJob;
import cn.com.git.udmp.component.batch.core.component.chain.duty.AbsJobDuty;
import cn.com.git.udmp.core.exception.FrameworkRuntimeException;

/**
 * @description 远程通信报告的责任点
 * @author liuliang 
 * @date 2015年5月14日
 * @version 1.0
 */
@Component("agentQueryCountDuty")
public class QueryCountDuty extends AbsJobDuty {
    private AbstractBatchJob queryCount; // 查询作业总数的业务类

    @Override
    public boolean isDuty(JobSessionContext context) {
        return true;
    }

    @Override
    protected JobSessionContext execute(JobSessionContext jsContext) {
        queryCount = getQueryJob(jsContext);
        return queryCount.executeQueryCounts(jsContext);
    }

    /**
     * @description 获取查询总数作业的实例
     * @author liuliang liuliang@newchinalife.com
     * @param jsContext 全局参数
     * @return 查询总数作业的实例
     */
    private AbstractBatchJob getQueryJob(JobSessionContext jsContext) {
        if (jsContext.isSpringBean()) {
            queryCount = (AbstractBatchJob) SpringContextHolder.getBean(jsContext.getJobClazzName());
        } else {
            try {
                queryCount = (AbstractBatchJob) Class.forName(jsContext.getJobClazzName()).newInstance();
            } catch (RuntimeException e) {
                e.printStackTrace();
                throw new FrameworkRuntimeException("查询总数作业初始化失败");
            } catch (InstantiationException e) {
                e.printStackTrace();
                throw new FrameworkRuntimeException("查询总数作业初始化失败");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
                throw new FrameworkRuntimeException("查询总数作业初始化失败");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                throw new FrameworkRuntimeException("查询总数作业初始化失败");
            }
        }
        queryCount.setJobSessionContext(jsContext);
        return queryCount;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
        
    }

}
