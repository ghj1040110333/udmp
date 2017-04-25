package cn.com.git.udmp.impl.batch.task.bo;

import java.math.BigDecimal;

import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchBO;
import cn.com.git.udmp.common.model.DataObject;

/**
 * @description BatchTaskParamVO对象
 * @author anthorName@newchinalife.com
 * @date 2015-02-10 10:57:05
 */
public class BatchTaskParamBO extends DataObject implements IBaseBatchBO{
    private static final long serialVersionUID = 1L;
    /**
     * @Fields taskParamId : 作业参数ID
     */
    private BigDecimal taskParamId;
    /**
     * @Fields taskId : 作业ID。外键关联 T
     */
    private BigDecimal taskId;
    /**
     * @Fields paramOrder : 多参数排序值
     */
    private BigDecimal paramOrder;
    /**
     * @Fields isArray : 是否是数组类型。 值域：是-1/否-0 默认值：否
     */
    private String isArray;
    /**
     * @Fields isDeleted : 是否删除。值域：是-1/否-0 默认值：否
     */
    private String isDeleted;
    /**
     * @Fields taskParamName : 作业参数名称
     */
    private String taskParamName;
    /**
     * @Fields taskParamCode : 作业参数代码
     */
    private String taskParamCode;
    /**
     * @Fields taskParamDataType : 数据类型。 值域：日期-date/数值-number/字符串-string
     */
    private String taskParamDataType;
    /**
     * @Fields ver : 预留版本号。默认值：1.0.0
     */
    private String ver;
    /**
     * @Fields paramId : 参数ID 外键关联T
     */
    private BigDecimal paramId;
    /**
     * @Fields isManu : 是否手工录入。值域：是-1/否-0 默认值：否
     */
    private String isManu;
    /**
     * @Fields isRequired : 是否必须填写。值域：是-1/否-0 默认值：否
     */
    private String isRequired;

    public void setTaskParamId(BigDecimal taskParamId) {
        this.taskParamId = taskParamId;
    }

    public BigDecimal getTaskParamId() {
        return taskParamId;
    }

    public void setTaskId(BigDecimal taskId) {
        this.taskId = taskId;
    }

    public BigDecimal getTaskId() {
        return taskId;
    }

    public void setParamOrder(BigDecimal paramOrder) {
        this.paramOrder = paramOrder;
    }

    public BigDecimal getParamOrder() {
        return paramOrder;
    }

    public void setIsArray(String isArray) {
        this.isArray = isArray;
    }

    public String getIsArray() {
        return isArray;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setTaskParamName(String taskParamName) {
        this.taskParamName = taskParamName;
    }

    public String getTaskParamName() {
        return taskParamName;
    }

    public void setTaskParamDataType(String taskParamDataType) {
        this.taskParamDataType = taskParamDataType;
    }

    public String getTaskParamDataType() {
        return taskParamDataType;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getVer() {
        return ver;
    }

    public void setParamId(BigDecimal paramId) {
        this.paramId = paramId;
    }

    public BigDecimal getParamId() {
        return paramId;
    }

    public void setIsManu(String isManu) {
        this.isManu = isManu;
    }

    public String getIsManu() {
        return isManu;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public String getIsRequired() {
        return isRequired;
    }

    @Override
    public String toString() {
        return "BatchTaskParamVO [" + "taskParamId=" + taskParamId + "," + "taskId=" + taskId + "," + "paramOrder="
                + paramOrder + "," + "isArray=" + isArray + "," + "isDeleted=" + isDeleted + "," + "taskParamName="
                + taskParamName + "," + "taskParamDataType=" + taskParamDataType + "," + "ver=" + ver + ","
                + "paramId=" + paramId + "," + "isManu=" + isManu + "," + "isRequired=" + isRequired + "]";
    }

    public String getTaskParamCode() {
        return taskParamCode;
    }

    public void setTaskParamCode(String taskParamCode) {
        this.taskParamCode = taskParamCode;
    }
}
