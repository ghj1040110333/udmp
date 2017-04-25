package cn.com.git.udmp.impl.batch.jobRunLog.po;

import java.math.BigDecimal;
import java.util.Date;

import cn.com.git.udmp.common.model.DataObject;

/**
 * @description BatchJobRunLogPO对象
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 15:06:11
 */
public class BatchJobRunLogPO extends DataObject {

    private static final long serialVersionUID = 1L;

    /** 属性 --- java类型 --- oracle类型_数据长度_小数位长度_注释信息 */
    // jobRunLogId --- BigDecimal --- NUMBER_16_0_流水号主键;
    // logTime --- Date --- DATE_7_0_日志记录日期;
    // logInfo --- String --- VARCHAR2_3000_0_日志信息，如exception完整信息等;
    // logLevel --- String --- VARCHAR2_2_0_日志级别，统一遵循参数表中数据字典定义的日志级别执行类全路径或者spring bean id;
    // logType --- String --- VARCHAR2_50_0_日志类型，统一遵循参数表中数据字典定义的日志类型，用于批处理作业快速定位一类的问题
    // jobRunId --- BigDecimal --- NUMBER_16_0_任务运行实例ID。外键关联
    // T_UDMP_BATCH_JOB_RUN.JOB_RUN_ID;

    public void setJobRunLogId(BigDecimal jobRunLogId) {
        setBigDecimal("job_run_log_id", jobRunLogId);
    }

    public BigDecimal getJobRunLogId() {
        return getBigDecimal("job_run_log_id");
    }

    public void setLogTime(Date logTime) {
        setUtilDate("log_time", logTime);
    }

    public Date getLogTime() {
        return getUtilDate("log_time");
    }

    public void setLogInfo(String logInfo) {
        setString("log_info", logInfo);
    }

    public String getLogInfo() {
        return getString("log_info");
    }

    public void setLogLevel(String logLevel) {
        setString("log_level", logLevel);
    }

    public String getLogLevel() {
        return getString("log_level");
    }
    
    public void setLogType(String logType) {
        setString("log_type", logType);
    }

    public String getLogType() {
        return getString("log_type");
    }

    public void setJobRunId(BigDecimal jobRunId) {
        setBigDecimal("job_run_id", jobRunId);
    }

    public BigDecimal getJobRunId() {
        return getBigDecimal("job_run_id");
    }
    
    public void setLogTimeStart(Date logTimeStart) {
        setUtilDate("log_timeStart", logTimeStart);
    }

    public Date getLogTimeStart() {
        return getUtilDate("log_timeStart");
    }
    public void setLogTimeEnd(Date logTimeEnd) {
        setUtilDate("log_timeEnd", logTimeEnd);
    }

    public Date getLogTimeEnd() {
        return getUtilDate("log_timeEnd");
    }

    @Override
    public String toString() {
        return "BatchJobRunLogPO [" + "jobRunLogId=" + getJobRunLogId() + "," + "logTime=" + getLogTime() + ","
                + "logInfo=" + getLogInfo() + "," + "logLevel=" + getLogLevel() + "," 
                + "log_type" + getLogType() + "jobRunId=" + getJobRunId()
                + "logTimeStart=" + getLogTimeStart() + "," + "logTimeEnd=" + getLogTimeEnd() + ","+ "]";
    }
}
