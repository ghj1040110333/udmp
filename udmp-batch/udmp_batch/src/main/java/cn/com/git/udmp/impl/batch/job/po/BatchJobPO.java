package cn.com.git.udmp.impl.batch.job.po;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.git.udmp.common.model.DataObject;

/**
 * @description BatchJobPO对象
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 09:41:45
 */
public class BatchJobPO extends DataObject {

    private static final long serialVersionUID = 1L;

    /** 属性 --- java类型 --- oracle类型_数据长度_小数位长度_注释信息 */
    // jobEndWindow --- Date --- DATE_7_0_任务结束时间窗口;
    // jobStartWindow --- Date --- DATE_7_0_任务开始时间窗口;
    // isAllowManu --- String --- CHAR_1_0_是否允许手工启动。值域：是-1/否-0 默认值：否;
    // isEnable --- String --- CHAR_1_0_是否启用标记。值域：是-1/否-0 默认值：否;
    // jobExpectDuration --- BigDecimal --- NUMBER_4_0_预期执行时间，默认值60，单位分钟;
    // taskId --- BigDecimal --- NUMBER_16_0_作业ID;
    // jobFrequency --- String --- VARCHAR2_30_0_执行频率，cron表达式;
    // jobId --- BigDecimal --- NUMBER_16_0_任务ID;
    // isDeleted --- String --- CHAR_1_0_是否删除。值域：是-1/否-0默认值：否;
    // jobName --- String --- VARCHAR2_200_0_任务名称;
    // jobBatchSize --- BigDecimal --- NUMBER_5_0_执行批次，默认100;
    // ver --- String --- VARCHAR2_11_0_预留版本号。默认值：1.0.0;
    // jobGroupId --- BigDecimal --- NUMBER_16_0_所属任务组ID;
    // isAllowSplit --- String --- CHAR_1_0_是否允许拆分执行。值域：是-1/否-0 默认值：否;
    // jobThreadLimitCnt --- BigDecimal --- NUMBER_4_0_线程阀值，默认值1;
    // jobType --- String --- CHAR_1_0_任务类型 值域：定时-S/异步-A;
    // isGroup --- String --- CHAR_1_0_是否是任务组。值域：是-1/否-0 默认值：否 ;
    // jobAlertDuration --- BigDecimal --- NUMBER_4_0_执行时间预警阀值，默认值60，单位分钟;
    // isLoop --- String --- CHAR_1_0_是否轮询任务。值域：是-1/否-0 默认值：否 ;

    public void setJobEndWindow(Date jobEndWindow) {
        setUtilDate("job_end_window", jobEndWindow);
    }

    public Date getJobEndWindow() {
        return getUtilDate("job_end_window");
    }

    public void setJobStartWindow(Date jobStartWindow) {
        setUtilDate("job_start_window", jobStartWindow);
    }

    public Date getJobStartWindow() {
        return getUtilDate("job_start_window");
    }

    public void setIsAllowManu(String isAllowManu) {
        setString("is_allow_manu", isAllowManu);
    }

    public String getIsAllowManu() {
        return getString("is_allow_manu");
    }

    public void setIsEnable(String isEnable) {
        setString("is_enable", isEnable);
    }

    public String getIsEnable() {
        return getString("is_enable");
    }

    public void setJobExpectDuration(BigDecimal jobExpectDuration) {
        setBigDecimal("job_expect_duration", jobExpectDuration);
    }

    public BigDecimal getJobExpectDuration() {
        return getBigDecimal("job_expect_duration");
    }

    public void setTaskId(BigDecimal taskId) {
        setBigDecimal("task_id", taskId);
    }

    public BigDecimal getTaskId() {
        return getBigDecimal("task_id");
    }

    public void setJobFrequency(String jobFrequency) {
        setString("job_frequency", jobFrequency);
    }

    public String getJobFrequency() {
        return getString("job_frequency");
    }

    public void setJobId(BigDecimal jobId) {
        setBigDecimal("job_id", jobId);
    }

    public BigDecimal getJobId() {
        return getBigDecimal("job_id");
    }

    public void setIsDeleted(String isDeleted) {
        setString("is_deleted", isDeleted);
    }

    public String getIsDeleted() {
        return getString("is_deleted");
    }

    public void setJobName(String jobName) {
        setString("job_name", jobName);
    }

    public String getJobName() {
        return getString("job_name");
    }

    public void setJobBatchSize(BigDecimal jobBatchSize) {
        setBigDecimal("job_batch_size", jobBatchSize);
    }

    public BigDecimal getJobBatchSize() {
        return getBigDecimal("job_batch_size");
    }

    public void setVer(String ver) {
        setString("ver", ver);
    }

    public String getVer() {
        return getString("ver");
    }

    public void setJobGroupId(BigDecimal jobGroupId) {
        setBigDecimal("job_group_id", jobGroupId);
    }

    public BigDecimal getJobGroupId() {
        return getBigDecimal("job_group_id");
    }

    public void setIsAllowSplit(String isAllowSplit) {
        setString("is_allow_split", isAllowSplit);
    }

    public String getIsAllowSplit() {
        return getString("is_allow_split");
    }

    public void setJobThreadLimitCnt(BigDecimal jobThreadLimitCnt) {
        setBigDecimal("job_thread_limit_cnt", jobThreadLimitCnt);
    }

    public BigDecimal getJobThreadLimitCnt() {
        return getBigDecimal("job_thread_limit_cnt");
    }

    public void setJobType(String jobType) {
        setString("job_type", jobType);
    }

    public String getJobType() {
        return getString("job_type");
    }

    public void setIsGroup(String isGroup) {
        setString("is_group", isGroup);
    }

    public String getIsGroup() {
        return getString("is_group");
    }

    public void setIsLoop(String isLoop) {
        setString("is_Loop", isLoop);
    }

    public String getIsLoop() {
        return getString("is_Loop");
    }

    public void setJobAlertDuration(BigDecimal jobAlertDuration) {
        setBigDecimal("job_alert_duration", jobAlertDuration);
    }

    public BigDecimal getJobAlertDuration() {
        return getBigDecimal("job_alert_duration");
    }

    @Override
    public String toString() {
        return "BatchJobPO [" + "jobEndWindow=" + getJobEndWindow() + "," + "jobStartWindow=" + getJobStartWindow()
                + "," + "isAllowManu=" + getIsAllowManu() + "," + "isEnable=" + getIsEnable() + ","
                + "jobExpectDuration=" + getJobExpectDuration() + "," + "taskId=" + getTaskId() + "," + "jobFrequency="
                + getJobFrequency() + "," + "jobId=" + getJobId() + "," + "isDeleted=" + getIsDeleted() + ","
                + "jobName=" + getJobName() + "," + "jobBatchSize=" + getJobBatchSize() + "," + "ver=" + getVer() + ","
                + "jobGroupId=" + getJobGroupId() + "," + "isAllowSplit=" + getIsAllowSplit() + ","
                + "jobThreadLimitCnt=" + getJobThreadLimitCnt() + "," + "jobType=" + getJobType() + "," + "isGroup="
                + getIsGroup() + "," + "isLoop=" + getIsLoop() + "," + "jobAlertDuration=" + getJobAlertDuration()
                + "]";
    }
}
