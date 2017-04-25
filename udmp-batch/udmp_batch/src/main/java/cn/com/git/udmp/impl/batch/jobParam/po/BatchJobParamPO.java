package cn.com.git.udmp.impl.batch.jobParam.po;

import java.math.BigDecimal;

import cn.com.git.udmp.common.model.DataObject;

/**
 * @description BatchJobParamPO对象
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 13:57:46
 */
public class BatchJobParamPO extends DataObject {
    private static final long serialVersionUID = 1L;

    /** 属性 --- java类型 --- oracle类型_数据长度_小数位长度_注释信息 */
    // taskParamId --- BigDecimal ---
    // NUMBER_16_0_作业参数ID。外键关联T_UDMP_BATCH_TASK_PARAM.TASK_PARAM_ID;
    // arrayParamOrder --- BigDecimal --- NUMBER_4_0_数组参数排序值 如果是数组，要有下标顺序，默认值1;
    // jobParamId --- BigDecimal --- NUMBER_16_0_任务参数ID;
    // jobId --- BigDecimal --- NUMBER_16_0_任务ID。外键关联T_UDMP_BATCH_JOB.JOB_ID;
    // isDeleted --- String --- CHAR_1_0_是否删除。值域：是-1/否-0默认值：否;
    // ver --- String --- VARCHAR2_11_0_预留版本号。默认值：1.0.0;
    // paramValue --- String --- VARCHAR2_3000_0_参数值;

    public void setTaskParamId(BigDecimal taskParamId) {
        setBigDecimal("task_param_id", taskParamId);
    }

    public BigDecimal getTaskParamId() {
        return getBigDecimal("task_param_id");
    }

    public void setArrayParamOrder(BigDecimal arrayParamOrder) {
        setBigDecimal("array_param_order", arrayParamOrder);
    }

    public BigDecimal getArrayParamOrder() {
        return getBigDecimal("array_param_order");
    }

    public void setJobParamId(BigDecimal jobParamId) {
        setBigDecimal("job_param_id", jobParamId);
    }

    public BigDecimal getJobParamId() {
        return getBigDecimal("job_param_id");
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

    public void setVer(String ver) {
        setString("ver", ver);
    }

    public String getVer() {
        return getString("ver");
    }

    public void setParamValue(String paramValue) {
        setString("param_value", paramValue);
    }

    public String getParamValue() {
        return getString("param_value");
    }

    @Override
    public String toString() {
        return "BatchJobParamPO [" + "taskParamId=" + getTaskParamId() + "," + "arrayParamOrder="
                + getArrayParamOrder() + "," + "jobParamId=" + getJobParamId() + "," + "jobId=" + getJobId() + ","
                + "isDeleted=" + getIsDeleted() + "," + "ver=" + getVer() + "," + "paramValue=" + getParamValue() + "]";
    }
}
