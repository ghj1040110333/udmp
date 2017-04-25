package cn.com.git.udmp.impl.batch.jobRun.po;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.git.udmp.common.model.DataObject;

/**
 * @description BatchJobRunPO对象
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 14:33:55
 */
public class BatchJobRunPO extends DataObject {
    private static final long serialVersionUID = 1L;

    /** 属性 --- java类型 --- oracle类型_数据长度_小数位长度_注释信息 */
    // successCnt --- BigDecimal --- NUMBER_16_0_成功记录数;
    // totalCnt --- BigDecimal --- NUMBER_16_0_总记录数;
    // createTime --- Date --- DATE_7_0_创建时间;
    // splitRelaGrpId --- String --- VARCHAR2_200_0_拆分任务关系组;
    // dueDate --- Date --- DATE_7_0_业务时间;
    // pagingContext --- String --- VARCHAR2_3000_0_分页信息;
    // isSplit --- String --- CHAR_1_0_是否拆分执行。值域：是-1/否-0 默认值：否;
    // startTime --- Date --- DATE_7_0_开始时间;
    // status --- String --- VARCHAR2_2_0_状态;
    // jobId --- BigDecimal --- NUMBER_16_0_任务ID。外键关联T_UDMP_BATCH_JOB.JOB_ID;
    // jobRunId --- BigDecimal --- NUMBER_16_0_任务运行实例ID;
    // failedCnt --- BigDecimal --- NUMBER_16_0_失败记录数;
    // ver --- String --- VARCHAR2_11_0_预留版本号。默认值：1.0.0;
    // agentId --- BigDecimal ---
    // NUMBER_16_0_执行代理ID。外键关联T_UDMP_BATCH_AGENT.AGENT_ID;
    // processDate --- Date --- DATE_7_0_处理时间;

    public void setSuccessCnt(BigDecimal successCnt) {
        setBigDecimal("success_cnt", successCnt);
    }

    public BigDecimal getSuccessCnt() {
        return getBigDecimal("success_cnt");
    }

    public void setTotalCnt(BigDecimal totalCnt) {
        setBigDecimal("total_cnt", totalCnt);
    }

    public BigDecimal getTotalCnt() {
        return getBigDecimal("total_cnt");
    }

    public void setCreateTime(Date createTime) {
        setUtilDate("create_time", createTime);
    }

    public Date getCreateTime() {
        return getUtilDate("create_time");
    }

    public void setSplitRelaGrpId(String splitRelaGrpId) {
        setString("split_rela_grp_id", splitRelaGrpId);
    }

    public String getSplitRelaGrpId() {
        return getString("split_rela_grp_id");
    }

    public void setDueDate(Date dueDate) {
        setUtilDate("due_date", dueDate);
    }

    public Date getDueDate() {
        return getUtilDate("due_date");
    }

    public void setPagingContext(String pagingContext) {
        setString("paging_context", pagingContext);
    }

    public String getPagingContext() {
        return getString("paging_context");
    }

    public void setIsSplit(String isSplit) {
        setString("is_split", isSplit);
    }

    public String getIsSplit() {
        return getString("is_split");
    }

    public void setStartTime(Date startTime) {
        setUtilDate("start_time", startTime);
    }

    public Date getStartTime() {
        return getUtilDate("start_time");
    }

    public void setStatus(String status) {
        setString("status", status);
    }

    public String getStatus() {
        return getString("status");
    }

    public void setJobId(BigDecimal jobId) {
        setBigDecimal("job_id", jobId);
    }

    public BigDecimal getJobId() {
        return getBigDecimal("job_id");
    }

    public void setJobRunId(BigDecimal jobRunId) {
        setBigDecimal("job_run_id", jobRunId);
    }

    public BigDecimal getJobRunId() {
        return getBigDecimal("job_run_id");
    }
    public void setJobChainRunId(String jobChainRunId) {
        setString("job_chain_run_id", jobChainRunId);
    }
    
    public String getJobChainRunId() {
        return getString("job_chain_run_id");
    }

    public void setFailedCnt(BigDecimal failedCnt) {
        setBigDecimal("failed_cnt", failedCnt);
    }

    public BigDecimal getFailedCnt() {
        return getBigDecimal("failed_cnt");
    }

    public void setVer(String ver) {
        setString("ver", ver);
    }

    public String getVer() {
        return getString("ver");
    }

    public void setAgentId(BigDecimal agentId) {
        setBigDecimal("agent_id", agentId);
    }

    public BigDecimal getAgentId() {
        return getBigDecimal("agent_id");
    }
    
    public void setJobChainBatch(BigDecimal jobChainBatch) {
        setBigDecimal("job_chain_batch", jobChainBatch);
    }
    
    public BigDecimal getJobChainBatch() {
        return getBigDecimal("job_chain_batch");
    }

    public void setProcessDate(Date processDate) {
        setUtilDate("process_date", processDate);
    }

    public Date getProcessDate() {
        return getUtilDate("process_date");
    }

    public void setJobName(String jobName) {
        setString("job_name", jobName);
    }

    public String getJobName() {
        return getString("job_name");
    }
    
    public void setJobType(String jobType) {
        setString("job_type", jobType);
    }
    
    public String getJobType() {
        return getString("job_type");
    }
    
    public void setJobAlertDuration(BigDecimal jobAlertDuration) {
        setBigDecimal("job_alert_duration", jobAlertDuration);
    }

    public BigDecimal getJobAlertDuration() {
        return getBigDecimal("job_alert_duration");
    }
    
    public void setJobExpectDuration(BigDecimal jobExpectDuration) {
        setBigDecimal("job_expect_duration", jobExpectDuration);
    }

    public BigDecimal getJobExpectDuration() {
        return getBigDecimal("job_expect_duration");
    }
    
    public void setProcessDateStart(Date processDateStart) {
        setUtilDate("process_dateStart", processDateStart);
    }
    
    public Date getProcessDateStart() {
        return getUtilDate("process_dateStart");
    }
    public void setProcessDateEnd(Date processDateEnd) {
        setUtilDate("process_dateEnd", processDateEnd);
    }

    public Date getProcessDateEnd() {
        return getUtilDate("process_dateEnd");
    }
    
    @Override
    public String toString() {
        return "BatchJobRunPO [" + "successCnt=" + getSuccessCnt() + "," + "totalCnt=" + getTotalCnt() + ","
                + "createTime=" + getCreateTime() + "," + "splitRelaGrpId=" + getSplitRelaGrpId() + "," + "dueDate="
                + getDueDate() + "," + "pagingContext=" + getPagingContext() + "," + "isSplit=" + getIsSplit() + ","
                + "startTime=" + getStartTime() + "," + "status=" + getStatus() + "," + "jobId=" + getJobId() + ","
                + "jobRunId=" + getJobRunId() + "," + "failedCnt=" + getFailedCnt() + "," + "ver=" + getVer() + ","
                + "agentId=" + getAgentId() + "," + "processDate=" + getProcessDate() + "," 
                +  "jobExpectDuration=" + getJobExpectDuration() + "," +"jobAlertDuration=" + getJobAlertDuration() 
                + "," +"jobName=" + getJobName() +"," + "processDateStart=" + getProcessDateStart()
                + "," +"processDateEnd=" + getProcessDateEnd() + "," +"jobType="+getJobType()+"]";
    }
}
