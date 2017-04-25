package cn.com.git.udmp.impl.batch.jobRun.vo;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchVO;
import cn.com.git.udmp.common.model.DataObject;

/**
 * @description BatchJobRunVO对象
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 14:34:02
 */
/** 
 * @description 
 * @author git_man git_man@git.com.cn 
 * @date 2017年2月17日 下午2:49:43  
*/
public class BatchJobRunVO extends DataObject implements IBaseBatchVO {

    private static final long serialVersionUID = 1L;
    /**
     * @Fields successCnt : 成功记录数
     */
    private BigDecimal successCnt;
    /**
     * @Fields totalCnt : 总记录数
     */
    private BigDecimal totalCnt;
    /**
     * @Fields createTime : 创建时间
     */
    private Date createTime;
    /**
     * @Fields splitRelaGrpId : 拆分任务关系组
     */
    private String splitRelaGrpId;
    /**
     * @Fields dueDate : 业务时间
     */
    private Date dueDate;
    /**
     * @Fields pagingContext : 分页信息
     */
    private String pagingContext;
    /**
     * @Fields isSplit : 是否拆分执行。 值域：是-1/否-0 默认值：否
     */
    private String isSplit;
    /**
     * @Fields startTime : 开始时间
     */
    private Date startTime;
    /**
     * @Fields status : 状态
     */
    private String status;
    /**
     * @Fields jobId : 任务ID。外键关联 T
     */
    private BigDecimal jobId;
    /**
     * @Fields jobRunId : 任务运行实例ID
     */
    private BigDecimal jobRunId;
    /**
     * @Fields jobRunId : 任务链运行实例ID
     */
    private String jobChainRunId;
    /**
     * @Fields failedCnt : 失败记录数
     */
    private BigDecimal failedCnt;
    /**
     * @Fields ver : 预留版本号。默认值：1.0.0
     */
    private String ver;
    /**
     * @Fields agentId : 执行代理ID。 外键关联T
     */
    private BigDecimal agentId;
    /**
     * @Fields jobChainBatch : 任务链执行批次
     */
    private BigDecimal jobChainBatch;
    /**
     * @Fields processDate : 处理时间
     */
    private Date processDate;    
    /**
     * @Fields jobName : 任务名称
     */
    private String jobName;
    /**
     * @Fields jobExpectDuration : 预期执行时间，默认值60，单位分钟
     */
    private BigDecimal jobExpectDuration;
    /**
     * @Fields jobAlertDuration : 执行时间预警阀值，默认值60，单位分钟
     */
    private BigDecimal jobAlertDuration;
    /**
     * @Fields processDateStart : 处理区间开始
     */
    private Date processDateStart;    
    /**
     * @Fields processDateEnd : 处理区间结束
     */
    private Date processDateEnd;
    /** 
    * @Fields jobType : 任务类型 
    */ 
    private String jobType = "A";
    
    public BigDecimal getJobExpectDuration() {
        return jobExpectDuration;
    }

    public void setJobExpectDuration(BigDecimal jobExpectDuration) {
        this.jobExpectDuration = jobExpectDuration;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public BigDecimal getJobAlertDuration() {
        return jobAlertDuration;
    }

    public void setJobAlertDuration(BigDecimal jobAlertDuration) {
        this.jobAlertDuration = jobAlertDuration;
    }

    public void setSuccessCnt(BigDecimal successCnt) {
        this.successCnt = successCnt;
    }

    public BigDecimal getSuccessCnt() {
        return successCnt;
    }

    public void setTotalCnt(BigDecimal totalCnt) {
        this.totalCnt = totalCnt;
    }

    public BigDecimal getTotalCnt() {
        return totalCnt;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setSplitRelaGrpId(String splitRelaGrpId) {
        this.splitRelaGrpId = splitRelaGrpId;
    }

    public String getSplitRelaGrpId() {
        return splitRelaGrpId;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setPagingContext(String pagingContext) {
        this.pagingContext = pagingContext;
    }

    public String getPagingContext() {
        return pagingContext;
    }

    public void setIsSplit(String isSplit) {
        this.isSplit = isSplit;
    }

    public String getIsSplit() {
        return isSplit;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setJobId(BigDecimal jobId) {
        this.jobId = jobId;
    }

    public BigDecimal getJobId() {
        return jobId;
    }

    public void setJobRunId(BigDecimal jobRunId) {
        this.jobRunId = jobRunId;
    }

    public BigDecimal getJobRunId() {
        return jobRunId;
    }

    public void setFailedCnt(BigDecimal failedCnt) {
        this.failedCnt = failedCnt;
    }

    public BigDecimal getFailedCnt() {
        return failedCnt;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getVer() {
        return ver;
    }

    public void setAgentId(BigDecimal agentId) {
        this.agentId = agentId;
    }

    public BigDecimal getAgentId() {
        return agentId;
    }

    public void setProcessDate(Date processDate) {
        this.processDate = processDate;
    }

    public Date getProcessDate() {
        return processDate;
    }

    public Date getProcessDateStart() {
        return processDateStart;
    }

    public void setProcessDateStart(Date processDateStart) {
        this.processDateStart = processDateStart;
    }

    public Date getProcessDateEnd() {
        return processDateEnd;
    }

    public void setProcessDateEnd(Date processDateEnd) {
        this.processDateEnd = processDateEnd;
    }
    
    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    @Override
    public String toString() {
        return "BatchJobRunVO [" + "successCnt=" + successCnt + "," + "totalCnt=" + totalCnt + "," + "createTime="
                + createTime + "," + "splitRelaGrpId=" + splitRelaGrpId + "," + "dueDate=" + dueDate + ","
                + "pagingContext=" + pagingContext + "," + "isSplit=" + isSplit + "," + "startTime=" + startTime + ","
                + "status=" + status + "," + "jobId=" + jobId + "," + "jobRunId=" + jobRunId + "," + "failedCnt="
                + failedCnt + "," + "ver=" + ver + "," + "agentId=" + agentId + "," + "processDate=" + processDate
                + "jobExpectDuration=" + jobExpectDuration + "," + "jobAlertDuration=" + jobAlertDuration 
                + "," + "jobName" + jobName + "processDateStart" + processDateStart +"processDateEnd"
                + processDateEnd +"]";
    }

    public String getJobChainRunId() {
        return jobChainRunId;
    }

    public void setJobChainRunId(String jobChainRunId) {
        this.jobChainRunId = jobChainRunId;
    }

    public BigDecimal getJobChainBatch() {
        return jobChainBatch;
    }

    public void setJobChainBatch(BigDecimal jobChainBatch) {
        this.jobChainBatch = jobChainBatch;
    }



}
