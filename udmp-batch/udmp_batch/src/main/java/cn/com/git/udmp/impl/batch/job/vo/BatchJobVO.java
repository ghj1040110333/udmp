package cn.com.git.udmp.impl.batch.job.vo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchVO;
import cn.com.git.udmp.common.model.DataObject;
import cn.com.git.udmp.impl.batch.jobDepend.vo.BatchJobDependVO;
import cn.com.git.udmp.impl.batch.jobParam.vo.BatchJobParamVO;

/**
 * @description BatchJobVO对象
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 09:48:05
 */
public class BatchJobVO extends DataObject implements IBaseBatchVO {
	private static final long serialVersionUID = 1L;
	/**
	 * @Fields jobEndWindow : 任务结束时间窗口
	 */
	private Date jobEndWindow;
	/**
	 * @Fields jobStartWindow : 任务开始时间窗口
	 */
	private Date jobStartWindow;
	/**
	 * @Fields isAllowManu : 是否允许手工启动。 值域：是-1/否-0 默认值：否
	 */
	private String isAllowManu ;
	/**
	 * @Fields isEnable : 是否启用标记。 值域：是-1/否-0 默认值：否
	 */
	private String isEnable ;
	/**
	 * @Fields jobExpectDuration : 预期执行时间，默认值60，单位分钟
	 */
	private BigDecimal jobExpectDuration ;
	/**
	 * @Fields taskId : 作业ID
	 */
	private BigDecimal taskId;
	/**
	 * @Fields jobFrequency : 执行频率，cron表达式
	 */
	private String jobFrequency;
	/**
	 * @Fields jobId : 任务ID
	 */
	private BigDecimal jobId;
	/**
	 * @Fields isDeleted : 是否删除。值域：是-1/否-0 默认值：否
	 */
	private String isDeleted ;
	/**
	 * @Fields jobName : 任务名称
	 */
	private String jobName;
	/**
	 * @Fields jobBatchSize : 执行批次，默认100
	 */
	private BigDecimal jobBatchSize ;

	/**
	 * @Fields jobGroupId : 所属任务组ID
	 */
	private BigDecimal jobGroupId;
	/**
	 * @Fields isAllowSplit : 是否允许拆分执行。 值域：是-1/否-0 默认值：否
	 */
	private String isAllowSplit ;
	/**
	 * @Fields jobThreadLimitCnt : 线程阀值，默认值1
	 */
	private BigDecimal jobThreadLimitCnt ;
	/**
	 * @Fields jobType : 任务类型 值域：定时-S/异步-A/任务组-G
	 */
	private String jobType;
	/**
	 * @Fields isGroup : 是否是任务组。 值域：是-1/否-0 默认值：否
	 */
	private String isGroup;
	/**
	 * @Fields isLoop : 是否是轮询任务。 值域：是-1/否-0 默认值：否
	 */
	private String isLoop ;
	/**
	 * @Fields jobAlertDuration : 执行时间预警阀值，默认值60，单位分钟
	 */
	private BigDecimal jobAlertDuration ;
	/**
	 * @Fields ver : 预留版本号。默认值：1.0.0
	 */
	private String ver;

	/**
	 * @Fields batchJobPreDependList : 前置依赖条件list
	 */
	private List<BatchJobDependVO> batchJobPreDependList;

	/**
	 * @Fields batchJobPostDependList : 后置依赖条件list
	 */
	private List<BatchJobDependVO> batchJobPostDependList;

	/**
	 * @Fields batchJobParamList : 任务参数list
	 */
	private List<BatchJobParamVO> batchJobParamList;

	public void setJobEndWindow(Date jobEndWindow) {
		this.jobEndWindow = jobEndWindow;
	}

	public Date getJobEndWindow() {
		return jobEndWindow;
	}

	public void setJobStartWindow(Date jobStartWindow) {
		this.jobStartWindow = jobStartWindow;
	}

	public Date getJobStartWindow() {
		return jobStartWindow;
	}

	public void setIsAllowManu(String isAllowManu) {
		this.isAllowManu = isAllowManu;
	}

	public String getIsAllowManu() {
		return isAllowManu;
	}

	public void setIsEnable(String isEnable) {
		this.isEnable = isEnable;
	}

	public String getIsEnable() {
		return isEnable;
	}

	public void setJobExpectDuration(BigDecimal jobExpectDuration) {
		this.jobExpectDuration = jobExpectDuration;
	}

	public BigDecimal getJobExpectDuration() {
		return jobExpectDuration;
	}

	public void setTaskId(BigDecimal taskId) {
		this.taskId = taskId;
	}

	public BigDecimal getTaskId() {
		return taskId;
	}

	public void setJobFrequency(String jobFrequency) {
		this.jobFrequency = jobFrequency;
	}

	public String getJobFrequency() {
		return jobFrequency;
	}

	public void setJobId(BigDecimal jobId) {
		this.jobId = jobId;
	}

	public BigDecimal getJobId() {
		return jobId;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobBatchSize(BigDecimal jobBatchSize) {
		this.jobBatchSize = jobBatchSize;
	}

	public BigDecimal getJobBatchSize() {
		return jobBatchSize;
	}

	public void setJobGroupId(BigDecimal jobGroupId) {
		this.jobGroupId = jobGroupId;
	}

	public BigDecimal getJobGroupId() {
		return jobGroupId;
	}

	public void setIsAllowSplit(String isAllowSplit) {
		this.isAllowSplit = isAllowSplit;
	}

	public String getIsAllowSplit() {
		return isAllowSplit;
	}

	public void setJobThreadLimitCnt(BigDecimal jobThreadLimitCnt) {
		this.jobThreadLimitCnt = jobThreadLimitCnt;
	}

	public BigDecimal getJobThreadLimitCnt() {
		return jobThreadLimitCnt;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getJobType() {
		return jobType;
	}

	public void setIsGroup(String isGroup) {
		this.isGroup = isGroup;
	}

	public String getIsGroup() {
		return isGroup;
	}

	public void setIsLoop(String isLoop) {
		this.isLoop = isLoop;
	}

	public String getIsLoop() {
		return isLoop;
	}

	public void setJobAlertDuration(BigDecimal jobAlertDuration) {
		this.jobAlertDuration = jobAlertDuration;
	}

	public BigDecimal getJobAlertDuration() {
		return jobAlertDuration;
	}

	public String getVer() {
		return ver;
	}

	public void setVer(String ver) {
		this.ver = ver;
	}

	public List<BatchJobDependVO> getBatchJobPreDependList() {
		return batchJobPreDependList;
	}

	public void setBatchJobPreDependList(List<BatchJobDependVO> batchJobPreDependList) {
		this.batchJobPreDependList = batchJobPreDependList;
	}

	public List<BatchJobDependVO> getBatchJobPostDependList() {
		return batchJobPostDependList;
	}

	public void setBatchJobPostDependList(List<BatchJobDependVO> batchJobPostDependList) {
		this.batchJobPostDependList = batchJobPostDependList;
	}

	public List<BatchJobParamVO> getBatchJobParamList() {
		return batchJobParamList;
	}

	public void setBatchJobParamList(List<BatchJobParamVO> batchJobParamList) {
		this.batchJobParamList = batchJobParamList;
	}

	@Override
	public String toString() {
		return "BatchJobVO [" + "jobEndWindow=" + jobEndWindow + "," + "jobStartWindow=" + jobStartWindow + ","
				+ "isAllowManu=" + isAllowManu + "," + "isEnable=" + isEnable + "," + "jobExpectDuration="
				+ jobExpectDuration + "," + "taskId=" + taskId + "," + "jobFrequency=" + jobFrequency + "," + "jobId="
				+ jobId + "," + "isDeleted=" + isDeleted + "," + "jobName=" + jobName + "," + "jobBatchSize="
				+ jobBatchSize + "," + "jobGroupId=" + jobGroupId + "," + "isAllowSplit=" + isAllowSplit + ","
				+ "jobThreadLimitCnt=" + jobThreadLimitCnt + "," + "jobType=" + jobType + "," + "isGroup=" + isGroup
				+ "," + "isLoop=" + isLoop + "," + "jobAlertDuration=" + jobAlertDuration + "ver=" + ver + "]";
	}

}
