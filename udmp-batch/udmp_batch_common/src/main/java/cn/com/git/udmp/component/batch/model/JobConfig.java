package cn.com.git.udmp.component.batch.model;

import java.util.Date;

/**
 * @description 任务的配置信息
 * @author liuliang liuliang_wb@newchina.live
 * @date 2015年1月29日 上午10:22:57
 */
public class JobConfig extends TaskConfig {
    private String jobId;
    private String jobName;
    private int jobGroupId;
    private String isGroup;
    private String isLoop; //是否轮询任务
    private String jobtype;
    private String isEnable;
    private String isAllowManu; // 是否允许手工启动
    private String isAllowSplit; // 是否允许拆分
    private int jobBatchSize; // 执行批次
    private int jobThreadLimitCnt; // 现场阀值
    private int jobAlertDuration;   // 执行时间预警阀值
    private int jobExpectDuration;  // 预期执行时间
    private Date jobStartWindow; // 任务开始时间窗口
    private Date jobEndWindow; // 任务结束时间窗口
    private String jobFrequency; // 执行频率

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public int getJobGroupId() {
        return jobGroupId;
    }

    public void setJobGroupId(int jobGroupId) {
        this.jobGroupId = jobGroupId;
    }

    public String getIsGroup() {
        return isGroup;
    }

    public void setIsGroup(String isGroup) {
        this.isGroup = isGroup;
    }

    public String getJobtype() {
        return jobtype;
    }

    public void setJobtype(String jobtype) {
        this.jobtype = jobtype;
    }

    public String getIsEnable() {
        return isEnable;
    }

    public void setIsEnable(String isEnable) {
        this.isEnable = isEnable;
    }

    public String getIsAllowManu() {
        return isAllowManu;
    }

    public void setIsAllowManu(String isAllowManu) {
        this.isAllowManu = isAllowManu;
    }

    public String getIsAllowSplit() {
        return isAllowSplit;
    }

    public void setIsAllowSplit(String isAllowSplit) {
        this.isAllowSplit = isAllowSplit;
    }

    public int getJobBatchSize() {
        return jobBatchSize;
    }

    public void setJobBatchSize(int jobBatchSize) {
        this.jobBatchSize = jobBatchSize;
    }

    public int getJobThreadLimitCnt() {
        return jobThreadLimitCnt;
    }

    public void setJobThreadLimitCnt(int jobThreadLimitCnt) {
        this.jobThreadLimitCnt = jobThreadLimitCnt;
    }

    public int getJobAlertDuration() {
        return jobAlertDuration;
    }

    public void setJobAlertDuration(int jobAlertDuration) {
        this.jobAlertDuration = jobAlertDuration;
    }

    public int getJobExpectDuration() {
        return jobExpectDuration;
    }

    public void setJobExpectDuration(int jobExpectDuration) {
        this.jobExpectDuration = jobExpectDuration;
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

    public String getJobFrequency() {
        return jobFrequency;
    }

    public void setJobFrequency(String jobFrequency) {
        this.jobFrequency = jobFrequency;
    }

    public String getIsLoop() {
        return isLoop;
    }

    public void setIsLoop(String isLoop) {
        this.isLoop = isLoop;
    }

}
