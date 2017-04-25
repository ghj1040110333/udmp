package cn.com.git.udmp.impl.batch.jobParam.vo;

import java.math.BigDecimal;
import java.util.List;

import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchVO;
import cn.com.git.udmp.common.model.DataObject;
import cn.com.git.udmp.impl.batch.param.vo.ParamManageVO;
import cn.com.git.udmp.impl.batch.param.vo.SubParamVO;

/**
 * @description BatchJobParamVO对象
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 13:57:51
 */
public class BatchJobParamVO extends DataObject implements IBaseBatchVO{
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

    /**
     * @Fields subParamList : 子参数值列表
     */
    private List<SubParamVO> subParamList;

    /**
     * @Fields paramName : 参数名
     */
    private String paramName;

    /**
     * @Fields isManu : 手工录入
     */
    private String isManu;

    /**
     * @Fields paramType : 参数类型：手工 —— 日期-date/数值-number/字符串-string
     */
    private String paramType;

    /**
     * @Fields isArray : 是否数组
     */
    private String isArray;

    /**
     * @Fields isRequired : 是否必须
     */
    private String isRequired;

    /**
     * @Fields paramOrder : 参数排序
     */
    private BigDecimal paramOrder;
    
    /** 
    * @Fields paramManageVo : 参数VO
    */ 
    private ParamManageVO paramManageVo;
    
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

    public ParamManageVO getParamManageVo() {
        return paramManageVo;
    }

    public void setParamManageVo(ParamManageVO paramManageVo) {
        this.paramManageVo = paramManageVo;
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

    public String getParamType() {
        return paramType;
    }

    public void setParamType(String paramType) {
        this.paramType = paramType;
    }

    public List<SubParamVO> getSubParamList() {
        return subParamList;
    }

    public void setSubParamList(List<SubParamVO> subParamList) {
        this.subParamList = subParamList;
    }

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    public String getIsManu() {
        return isManu;
    }

    public void setIsManu(String isManu) {
        this.isManu = isManu;
    }

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
        return "BatchJobParamVO [" + "taskParamId=" + taskParamId + "," + "arrayParamOrder=" + arrayParamOrder + ","
                + "jobParamId=" + jobParamId + "," + "jobId=" + jobId + "," + "isDeleted=" + isDeleted + "," + "ver="
                + ver + "," + "paramValue=" + paramValue + "]";
    }

    public String getTaskParamName() {
        return taskParamName;
    }

    public void setTaskParamName(String taskParamName) {
        this.taskParamName = taskParamName;
    }

    public String getTaskParamCode() {
        return taskParamCode;
    }

    public void setTaskParamCode(String taskParamCode) {
        this.taskParamCode = taskParamCode;
    }

    public String getTaskParamDataType() {
        return taskParamDataType;
    }

    public void setTaskParamDataType(String taskParamDataType) {
        this.taskParamDataType = taskParamDataType;
    }
}
