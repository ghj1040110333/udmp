package cn.com.git.udmp.component.batch.core.agent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

import cn.com.git.udmp.component.batch.common.constants.JobRunStatus;
import cn.com.git.udmp.component.batch.common.constants.StatusEnum;
import cn.com.git.udmp.component.batch.context.JobSessionContext;
import cn.com.git.udmp.component.batch.core.agent.communicate.AgentCommunicator;
import cn.com.git.udmp.component.batch.exception.BatchBizException;
import cn.com.git.udmp.core.logging.ILog;

/**
 * @description 作业控制器 (负责记录执行进度和日志信息)
 * @author liuliang
 * @date 2015年2月5日 下午4:16:56
 */
public class TaskHandler implements ILog {
    private Map<Integer, StatusEnum> statusMap = new ConcurrentHashMap<Integer, StatusEnum>();
    private AtomicBoolean sendFlag = new AtomicBoolean(false);
    private int cnt;
    // 线程数
    private int threadSize;
    private JobSessionContext jsContext;
    private AgentCommunicator communicator;

    /**
     * <p>
     * Title: 作业处理器
     * </p>
     * <p>
     * Description: 带上下文参数的构造方法
     * </p>
     * 
     * @param jsContext 上下文参数
     */
    public TaskHandler(JobSessionContext jsContext) {
        this.jsContext = jsContext;
        communicator = new AgentCommunicator(jsContext);
    }

    /**
     * @description 记录线程启动状态
     * @author liuliang
     * @param workId 线程ID
     */
    public void logStart(int workId) {
        statusMap.put(workId, StatusEnum.RUNNING);
    }

    public synchronized void log(boolean success, int workId, BatchBizException e) {
        if (success) {
            logSucceed(workId);
        } else {
            logFailure(workId, e);
        }
    }

    /**
     * @description 记录线程的成功的ID
     * @author liuliang
     * @param workId 线程ID
     */
    private void logSucceed(int workId) {
        // TODO 记录workId线程的完成进度（workId+执行区间）
        statusMap.put(workId, StatusEnum.SUCCESS);
        this.reportStatus();
    }

    /**
     * @description 记录有异常的线程情况
     * @author liuliang
     * @param workId 作业ID
     * @param results 有异常的区间值
     */
    private void logFailure(int workId, BatchBizException e) {
        // TODO 记录有异常的线程情况
        statusMap.put(workId, StatusEnum.FAIL);
        this.reportException(e);
    }

    // private void sendBack(Map<String, String> results) {
    // if (statusMap.size() == getSize()) {
    // if (!statusMap.values().contains(StatusEnum.RUNNING)) {
    // // TODO 回执信息
    // communicator.sendBack(results);
    // }
    // }
    // }

    public int getSize() {
        return threadSize;
    }

    public void setSize(int size) {
        this.threadSize = size;
    }

    /**
     * @description 回执结果给服务端
     * @author liuliang
     */
    public synchronized void sendResult() {
        if (statusMap.size() == getSize() && !statusMap.values().contains(StatusEnum.RUNNING) && !sendFlag.get()) {
            // TODO 回执信息
            sendFlag.set(true);
            communicator.sendBack(null);
        }
    }

    /**
     * 回写执行记录状态
     * 
     * @description 回写执行记录状态
     * @version 1.0
     * @title 回写执行记录状态
     * @author bihb
     */
    public synchronized void reportStatus() {
        if (statusMap.size() == getSize() && !statusMap.values().contains(StatusEnum.RUNNING) && !sendFlag.get()) {
            logger.debug("达到报告条件，状态集数据为:{}", statusMap);
            // if (statusMap.size() == getSize() &&
            // !statusMap.values().contains(StatusEnum.RUNNING) && !sendFlag) {
            sendFlag.set(true);
            // 得到执行过程中的执行记录信息
            // long totalCnt = jsContext.getTotalCnt();
            // 成功记录数
            long successCnt = jsContext.getSuccessCnt();
            // 失败记录数
            long failCnt = jsContext.getFailCnt();
            logger.info("------------------------successCnt:" + successCnt + " failCnt:" + failCnt);
            // 判断逻辑
            // a:失败记录数 == 0 记录为成功状态JobRunStatus.SUCCESS
            // b:成功记录数 == 0 记录为失败状态JobRunStatus.FAIL
            // c:else都不为0 记录为部分成功状态JobRunStatus.PART_SUCCESS
            String status = JobRunStatus.SUCCESS;
            if (0 == failCnt && !statusMap.values().contains(StatusEnum.FAIL)) {
                status = JobRunStatus.SUCCESS;
            } else if (0 == successCnt) {
                status = JobRunStatus.FAIL;
            } else {
                status = JobRunStatus.PART_SUCCESS;
            }
            // 状态回写
            communicator.reportStatus(jsContext.getJobRunId(), status);
        } else {
            logger.debug("未达到报告条件，状态集数据为:{},线程池大小:{}", statusMap, getSize());
        }
    }

    /**
     * @description 回写执行记录状态记录
     * @version 1.0
     * @title 回写执行记录状态记录
     * @author bihb
     * @param jobRunId 运行实例主键
     * @param totalCnt 总记录数
     * @param successCnt 成功记录数
     * @param failCnt 失败记录数
     */
    public synchronized void reportStatusCnt(String jobRunId, long totalCnt, long successCnt, long failCnt) {
        communicator.reportStatusCnt(jobRunId, totalCnt, successCnt, failCnt);
    }

    public JobSessionContext getJsContext() {
        return jsContext;
    }

    public void setJsContext(JobSessionContext jsContext) {
        this.jsContext = jsContext;
    }

    /**
     * 添加同步方法，将线程自己的session的成功失败条数累加到handler中的共享session
     * 
     * @description 添加同步方法，将线程自己的session的成功失败条数累加到handler中的共享session
     * @version 1.0
     * @title 添加同步方法，将线程自己的session的成功失败条数累加到handler中的共享session
     * @author liuliang
     * @param successCnt 某子线程成功记录数
     */
    public synchronized void sumSuccessCnt(long successCnt) {
        logger.info("*****************第" + (this.cnt + 1) + "次向共享session添加：" + successCnt);
        this.jsContext.setSuccessCnt(this.jsContext.getSuccessCnt() + successCnt);
        this.cnt = this.cnt + 1;
    }

    /**
     * 添加同步方法，将线程自己的session的成功失败条数累加到handler中的共享session
     * 
     * @description 添加同步方法，将线程自己的session的成功失败条数累加到handler中的共享session
     * @version 1.0
     * @title 添加同步方法，将线程自己的session的成功失败条数累加到handler中的共享session
     * @author liuliang
     * @param failCnt 某子线程失败记录数
     */
    public synchronized void sumFailCnt(long failCnt) {
        this.jsContext.setFailCnt(this.jsContext.getFailCnt() + failCnt);
    }

    /**
     * 回写异常信息
     * 
     * @description 回写异常信息
     * @version 1.0
     * @title 回写异常信息
     * @author bihb
     * @param e BatchBizException对象
     */
    public synchronized void reportException(BatchBizException e) {
        communicator.reportException(jsContext.getJobRunId(), e);
    }

    public AgentCommunicator getCommunicator() {
        return communicator;
    }

    public void setCommunicator(AgentCommunicator communicator) {
        this.communicator = communicator;
    }

}
