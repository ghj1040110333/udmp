package cn.com.git.udmp.component.batch.core.server.manage;

import java.util.List;

import cn.com.git.udmp.component.batch.core.IBooter;
import cn.com.git.udmp.component.batch.model.JobConfig;
import cn.com.git.udmp.component.batch.model.JobParam;
import cn.com.git.udmp.impl.batch.jobRun.vo.BatchJobRunVO;

/**
 * @description 任务控制类接口
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年4月13日 下午3:45:38
 */
public interface IJobManager extends IBooter {

    /**
     * @description 获取任务
     * @author liuliang liuliang@newchinalife.com
     * @param jobId 任务ID
     * @return 任务信息
    */
    public JobConfig getJob(String jobId);

    /**
     * @description 获取任务参数
     * @author liuliang liuliang@newchinalife.com
     * @param jobId 任务ID
     * @return 任务参数集 
    */
    public List<JobParam> getJobParams(String jobId);
    
}
