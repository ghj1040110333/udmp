package cn.com.git.udmp.component.batch.core.server.manage;

import java.util.List;
import java.util.Map;

import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.core.IBooter;

/**
 * @description 任务依赖管理接口
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年7月7日 下午4:43:15
 */
public interface IJobDependManager extends IBooter {
    /**
     * @description 获取后续任务
     * @author liuliang liuliang@newchinalife.com
     * @param jobId 任务ID
     * @param status 任务状态
     * @return 后续任务集
     */
    public List<String> findJobsAfter(String jobId, StatusEnum status);

    /**
     * @description 获取停止任务
     * @author liuliang liuliang@newchinalife.com
     * @param jobId 任务ID
     * @param status 任务状态
     * @return 任务集
    */
    public List<String> findJobsStop(String jobId, StatusEnum status);

    /**
     * @description 获取任务的前置条件
     * @author liuliang liuliang@newchinalife.com
     * @param jobId 任务ID
     * @return 前置条件
    */
    public Map<String, StatusEnum> findPreCondition(String jobId);

    /**
     * @description 激活依赖当前任务当前状态的后续任务
     * @author liuliang liuliang@newchinalife.com
     * @param jobId 任务ID
     * @param jobChainRunId 
     * @param status 任务状态
     * @update by L.liang- Transfer the jobChainRunId to the next job for a whole chain
    */
    public void activateAfter(String jobId, String jobChainRunId, StatusEnum status);

    /**
     * @description 停止依赖于当前任务当前状态的任务
     * @author liuliang liuliang@newchinalife.com
     * @param jobId 任务ID
     * @param jobChainRunId -The instance id of the job chain
     * @param status 任务状态
     * @update by L.liang- Transfer the jobChainRunId to the next job for a whole chain
    */
    public void stopDepend(String jobId, String jobChainRunId, StatusEnum status);

    /**
     * @description 查询任务的停止条件
     * @author liuliang liuliang@newchinalife.com
     * @param jobId 任务ID
     * @return 任务的停止条件
    */
    public Map<String, StatusEnum> findStopConditions(String jobId);
}
