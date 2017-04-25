package cn.com.git.udmp.component.batch.core.server.manage.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cn.com.git.udmp.component.batch.model.JobConfig;
import cn.com.git.udmp.component.batch.model.JobParam;
import cn.com.git.udmp.component.batch.model.TaskConfig;

/**
 * @description 任务及其状态的管理类
 * @author liuliang 
 * @date 2015年1月28日 下午3:30:05
 */
public class JobManagerInCache extends AbsJobManager {
    private static Map<String, JobConfig> jobConfigs = new HashMap<String, JobConfig>();

    @Override
    public void boot() {
//        JobConfig jobConfig = new JobConfig();
//        jobConfig.setJobId("123");
//        jobConfig.setTaskSystem("test");
//        jobConfig.setTaskClazz("cn.com.git.udmp.component.batch.core.agent.MyJob1");
//        jobConfig.setIsSpringBean("true");
//        jobConfig.setJobBatchSize(20);
//        // TODO 从数据库中加载job信息
//        jobConfigs.put("123", jobConfig);
    }

    /**
     * @description 获取任务信息
     * @see cn.com.git.udmp.component.batch.core.server.manage.IJobManager#getJob(java.lang.String)
     * @param jobId 任务ID
     * @return 任务信息
    */
    public JobConfig getJob(String jobId) {
        // TODO 数据中获取或者从缓存中获取
        return jobConfigs.get(jobId);
    }

    /**
     * @description 获取任务的参数信息
     * @param jobId 任务ID
     * @return 任务参数信息
     */
    public List<JobParam> getJobParams(String jobId) {
        return new ArrayList<JobParam>();
    }

    /**
     * @description 根据作业ID获取作业配置
     * @param taskId 作业ID
     * @return 作业配置
     */
    private TaskConfig getTaskConfigById(BigDecimal taskId) {
        return null;
    }

    @Override
    public void close() {
        // TODO Auto-generated method stub
    }
}
