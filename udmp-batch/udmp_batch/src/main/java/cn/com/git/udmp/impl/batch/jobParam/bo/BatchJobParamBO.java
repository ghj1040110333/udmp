package cn.com.git.udmp.impl.batch.jobParam.bo;

import java.math.BigDecimal;

import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchBO;
import cn.com.git.udmp.common.model.DataObject;

/**
 * @description BatchJobParamBO对象
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 13:57:51
 */
public class BatchJobParamBO extends DataObject implements IBaseBatchBO{
    private static final long serialVersionUID = 1L;
    /**
     * @Fields taskParamId : 作业参数ID。 外键关联 T
     */
    private BigDecimal taskParamId;
    /**
     * @Fields arrayParamOrder : 数组参数排序值 如果是数组，要有下标顺序，默认值1
     */
    private BigDecimal arrayParamOrder;
    /**
     * @Fields jobParamId : 任务参数ID
     */
    private BigDecimal jobParamId;
    /**
     * @Fields jobId : 任务ID。 外键关联T
     */
    private BigDecimal jobId;
    /**
     * @Fields isDeleted : 是否删除。值域：是-1/否-0 默认值：否
     */
    private String isDeleted;
    /**
     * @Fields ver : 预留版本号。默认值：1.0.0
     */
    private String ver;
    /**
     * @Fields paramValue : 参数值
     */
    private String paramValue;

    public void setTaskParamId(BigDecimal taskParamId) {
        this.taskParamId = taskParamId;
    }

    public BigDecimal getTaskParamId() {
        return taskParamId;
    }

    public void setArrayParamOrder(BigDecimal arrayParamOrder) {
        this.arrayParamOrder = arrayParamOrder;
    }

    public BigDecimal getArrayParamOrder() {
        return arrayParamOrder;
    }

    public void setJobParamId(BigDecimal jobParamId) {
        this.jobParamId = jobParamId;
    }

    public BigDecimal getJobParamId() {
        return jobParamId;
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

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getVer() {
        return ver;
    }

    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }

    public String getParamValue() {
        return paramValue;
    }


    @Override
    public String toString() {
        return "BatchJobParamBO [" + "taskParamId=" + taskParamId + "," + "arrayParamOrder=" + arrayParamOrder + ","
                + "jobParamId=" + jobParamId + "," + "jobId=" + jobId + "," + "isDeleted=" + isDeleted + "," + "ver="
                + ver + "," + "paramValue=" + paramValue + "]";
    }
}
