package cn.com.git.udmp.component.batch.context;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import cn.com.git.udmp.component.batch.common.constants.CommandEnum;
import cn.com.git.udmp.component.batch.communication.protobuf.BatchMessage.Message.Extension;
import cn.com.git.udmp.component.batch.exception.BatchBizException;
import cn.com.git.udmp.component.batch.model.AgentInfo;
import cn.com.git.udmp.component.batch.model.JobParam;

/**
 * @description 作业中共享的全局配置信息
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年1月21日 下午2:19:48
 */
public class JobSessionContext extends SessionContext {
    private int threadSize = 10;
    private long startNum; // 作业处理区间
    private long endNum; // 作业处理区间
    private int batchSize; // 批处理事务提交数
    private String jobClazzName; // 作业的类名
    private boolean isSpringBean; // 是否是springBean
    private String jobId; // 任务ID
    private String jobName; // 任务名称
    private CommandEnum command; // 执行命令
    private Date jobStartWindow; // 任务开始时间窗口
    private Date jobEndWindow; // 任务结束时间窗口
    // 运行实例编号-主键
    private String jobRunId;
    // 运行任务组实例编号
    private String jobChainRunId;
    // 任务链执行批次号
    private int jobChainBatch = 1;
    private List<JobParam> params;
    private List<Extension> extension;


    /**
     * @return extension
     */
    public List<Extension> getExtension() {
        return extension;
    }

    /**
     * @param extension 要设置的 extension
     */
    public void setExtension(List<Extension> extension) {
        this.extension = extension;
    }

    private boolean isLoop;
    /**
     * @Fields exceptionList : 回写远程的日志列表，不能抛出异常，因为有事物批次
     */
    private List<BatchBizException> exceptionList = new ArrayList<BatchBizException>();
    // agent的信息
    private AgentInfo agentInfo;
    // 运行数据统计，总记录数、成功记录数、失败记录数。
    private long totalCnt;
    private long successCnt;
    private long failCnt;

    public long getStartNum() {
        return startNum;
    }

    public void setStartNum(long startNum) {
        this.startNum = startNum;
    }

    public long getEndNum() {
        return endNum;
    }

    public void setEndNum(long endNum) {
        this.endNum = endNum;
    }

    public int getBatchSize() {
        return batchSize;
    }

    public void setBatchSize(int batchSize) {
        this.batchSize = batchSize;
    }

    public String getJobClazzName() {
        return jobClazzName;
    }

    public void setJobClazzName(String jobClazzName) {
        this.jobClazzName = jobClazzName;
    }

    public boolean isSpringBean() {
        return isSpringBean;
    }

    public void setSpringBean(boolean isSpringBean) {
        this.isSpringBean = isSpringBean;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public CommandEnum getCommand() {
        return command;
    }

    public void setCommand(CommandEnum command) {
        this.command = command;
    }

    public int getThreadSize() {
        return threadSize;
    }

    public void setThreadSize(int threadSize) {
        this.threadSize = threadSize;
    }

    public Date getJobStartWindow() {
        return jobStartWindow;
    }

    public void setJobStartWindow(Date jobStartWindow) {
        this.jobStartWindow = jobStartWindow;
    }

    public Date getJobEndWindow() {
        return jobEndWindow;
    }

    public void setJobEndWindow(Date jobEndWindow) {
        this.jobEndWindow = jobEndWindow;
    }

    /**
     * 返回totalCnt的属性值
     * 
     * @description 返回totalCnt的属性值
     * @version 1.0
     * @title 返回totalCnt的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取totalCnt的属性值
     */
    public long getTotalCnt() {
        return totalCnt;
    }

    /**
     * @description 给totalCnt属性赋值
     * @version 1.0
     * @title 给totalCnt属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param totalCnt totalCnt属性
     */
    public void setTotalCnt(long totalCnt) {
        this.totalCnt = totalCnt;
    }

    /**
     * 返回successCnt的属性值
     * 
     * @description 返回successCnt的属性值
     * @version 1.0
     * @title 返回successCnt的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取successCnt的属性值
     */
    public long getSuccessCnt() {
        return successCnt;
    }

    /**
     * @description 给successCnt属性赋值
     * @version 1.0
     * @title 给successCnt属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param successCnt successCnt属性
     */
    public void setSuccessCnt(long successCnt) {
        this.successCnt = successCnt;
    }

    /**
     * 返回failCnt的属性值
     * 
     * @description 返回failCnt的属性值
     * @version 1.0
     * @title 返回failCnt的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取failCnt的属性值
     */
    public long getFailCnt() {
        return failCnt;
    }

    /**
     * @description 给failCnt属性赋值
     * @version 1.0
     * @title 给failCnt属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param failCnt failCnt属性
     */
    public void setFailCnt(long failCnt) {
        this.failCnt = failCnt;
    }

    /**
     * 返回jobRunId的属性值
     * 
     * @description 返回jobRunId的属性值
     * @version 1.0
     * @title 返回jobRunId的属性值
     * @author bihb bihb_wb@newchinalife.com
     * @return 返回获取jobRunId的属性值
     */
    public String getJobRunId() {
        return jobRunId;
    }

    /**
     * @description 给jobRunId属性赋值
     * @version 1.0
     * @title 给jobRunId属性赋值
     * @author bihb bihb_wb@newchinalife.com
     * @param jobRunId jobRunId属性
     */
    public void setJobRunId(String jobRunId) {
        this.jobRunId = jobRunId;
    }

    public List<JobParam> getParams() {
        return params;
    }

    public void setParams(List<JobParam> params) {
        this.params = params;
    }

    public AgentInfo getAgentInfo() {
        return agentInfo;
    }

    public void setAgentInfo(AgentInfo agentInfo) {
        this.agentInfo = agentInfo;
    }

    public List<BatchBizException> getExceptionList() {
        return exceptionList;
    }

    public void setExceptionList(List<BatchBizException> exceptionList) {
        this.exceptionList = exceptionList;
    }

    /**
     * @description 添加异常集合
     * @author liuliang liuliang@newchinalife.com
     * @param e 批处理异常
     */
    public void addExceptionList(BatchBizException e) {
        this.exceptionList.add(e);
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }
    
    @Override
    public String toString() {
        StringBuffer string = new StringBuffer();
        string.append("任务名称:").append(jobName).append(";");
        string.append("任务ID:").append(jobId).append(";");
        string.append("启动时间:").append(jobStartWindow).append(";");
        string.append("停止时间:").append(jobEndWindow).append(";");
        return string.toString();
    }

    public boolean isLoop() {
        return isLoop;
    }

    public void setLoop(boolean isLoop) {
        this.isLoop = isLoop;
    }

    public String getJobChainRunId() {
        return jobChainRunId;
    }

    public void setJobChainRunId(String jobChainRunId) {
        this.jobChainRunId = jobChainRunId;
    }

    public int getJobChainBatch() {
        return jobChainBatch;
    }

    public void setJobChainBatch(int jobChainBatch) {
        this.jobChainBatch = jobChainBatch;
    }

}
