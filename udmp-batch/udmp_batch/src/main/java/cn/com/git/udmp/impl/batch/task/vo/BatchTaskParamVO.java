package cn.com.git.udmp.impl.batch.task.vo;

import java.math.BigDecimal;

import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchVO;
import cn.com.git.udmp.common.model.DataObject;

public class BatchTaskParamVO extends DataObject implements IBaseBatchVO{

    /**
	 * 
	 */
    private static final long serialVersionUID = 1L;

    private BigDecimal taskParamId;

    private String taskParamName;
    
    private String taskParamCode;

    private BigDecimal taskId;

    private BigDecimal paramId;

    private String isManu;

    private String taskParamDataType;

    private String isArray;

    private String isRequired;

    private BigDecimal paramOrder;


    public BigDecimal getTaskParamId() {
        return taskParamId;
    }

    public void setTaskParamId(BigDecimal taskParamId) {
        this.taskParamId = taskParamId;
    }

    public String getTaskParamName() {
        return taskParamName;
    }

    public void setTaskParamName(String taskParamName) {
        this.taskParamName = taskParamName;
    }

    public BigDecimal getTaskId() {
        return taskId;
    }

    public void setTaskId(BigDecimal taskId) {
        this.taskId = taskId;
    }

    public BigDecimal getParamId() {
        return paramId;
    }

    public void setParamId(BigDecimal paramId) {
        this.paramId = paramId;
    }

    public String getIsManu() {
        return isManu;
    }

    public void setIsManu(String isManu) {
        this.isManu = isManu;
    }

    public String getTaskParamDataType() {
        return taskParamDataType;
    }

    public void setTaskParamDataType(String taskParamDataType) {
        this.taskParamDataType = taskParamDataType;
    }

    public String getIsArray() {
        return isArray;
    }

    public void setIsArray(String isArray) {
        this.isArray = isArray;
    }

    public String getIsRequired() {
        return isRequired;
    }

    public void setIsRequired(String isRequired) {
        this.isRequired = isRequired;
    }

    public BigDecimal getParamOrder() {
        return paramOrder;
    }

    public void setParamOrder(BigDecimal paramOrder) {
        this.paramOrder = paramOrder;
    }

    public String getTaskParamCode() {
        return taskParamCode;
    }

    public void setTaskParamCode(String taskParamCode) {
        this.taskParamCode = taskParamCode;
    }

}
