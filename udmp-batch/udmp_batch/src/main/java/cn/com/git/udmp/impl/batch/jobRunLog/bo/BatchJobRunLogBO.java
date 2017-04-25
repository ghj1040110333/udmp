package cn.com.git.udmp.impl.batch.jobRunLog.bo;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchBO;
import cn.com.git.udmp.common.model.DataObject;

/**
 * @description BatchJobRunLogBO对象
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 14:59:07
 */
public class BatchJobRunLogBO extends DataObject implements IBaseBatchBO{
    private static final long serialVersionUID = 1L;
    /**
     * @Fields jobRunLogId : 流水号主键
     */
    private BigDecimal jobRunLogId;
    /**
     * @Fields logTime : 日志记录日期
     */
    private Date logTime;
    /**
     * @Fields logInfo : 日志信息，如exception完整信息等
     */
    private String logInfo;
    /**
     * @Fields logLevel : 日志级别，统一遵循参数表中数据字典定义的日志级别 执行类全路径或者spring bean id
     */
    private String logLevel;
    /**
     * @Fields logLevel : 日志类型，统一遵循参数表中数据字典定义的日志类型，用于批处理作业快速定位一类的问题
     */
    private String logType;
    /**
     * @Fields jobRunId : 任务运行实例ID。外键关联 T
     */
    private BigDecimal jobRunId;
    /**
     * @Fields logTimeStart : 日志记录开始日期
     */
    private Date logTimeStart;
    /**
     * @Fields logTimeEnd : 日志记录结束日期
     */
    private Date logTimeEnd;
    
    public void setJobRunLogId(BigDecimal jobRunLogId) {
        this.jobRunLogId = jobRunLogId;
    }

    public BigDecimal getJobRunLogId() {
        return jobRunLogId;
    }

    public void setLogTime(Date logTime) {
        this.logTime = logTime;
    }

    public Date getLogTime() {
        return logTime;
    }

    public void setLogInfo(String logInfo) {
        this.logInfo = logInfo;
    }

    public String getLogInfo() {
        return logInfo;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setJobRunId(BigDecimal jobRunId) {
        this.jobRunId = jobRunId;
    }

    public BigDecimal getJobRunId() {
        return jobRunId;
    }
    
    public Date getLogTimeStart() {
        return logTimeStart;
    }

    public void setLogTimeStart(Date logTimeStart) {
        this.logTimeStart = logTimeStart;
    }

    public Date getLogTimeEnd() {
        return logTimeEnd;
    }

    public void setLogTimeEnd(Date logTimeEnd) {
        this.logTimeEnd = logTimeEnd;
    }
    
    public String getLogType() {
        return logType;
    }

    public void setLogType(String logType) {
        this.logType = logType;
    }

    @Override
    public String toString() {
        return "BatchJobRunLogBO [" + "jobRunLogId=" + jobRunLogId + "," + "logTime=" + logTime + "," + "logInfo="
                + logInfo + "," + "logLevel=" + logLevel + "," + "jobRunId=" + jobRunId + "logType=" + logType
                + "logTimeStart=" + logTimeStart + "," + "logTimeEnd=" + logTimeEnd + "," + "]";
    }

    
}