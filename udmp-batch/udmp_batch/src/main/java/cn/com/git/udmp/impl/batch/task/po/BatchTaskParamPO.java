package cn.com.git.udmp.impl.batch.task.po;

import java.math.BigDecimal;

import cn.com.git.udmp.common.model.DataObject;


/**
 * BatchTaskParamPO
 */
public class BatchTaskParamPO extends DataObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BigDecimal getTaskParamId() {
		return getBigDecimal("task_param_id");
	}

	public void setTaskParamId(BigDecimal taskParamId) {
		setBigDecimal("task_param_id", taskParamId);
	}

	public String getTaskParamName() {
		return getString("task_param_name");
	}

	public void setTaskParamName(String taskParamName) {
		setString("task_param_name", taskParamName);
	}
	public String getTaskParamCode() {
	    return getString("task_param_code");
	}
	
	public void setTaskParamCode(String taskParamCode) {
	    setString("task_param_code", taskParamCode);
	}

	public BigDecimal getTaskId() {
		return getBigDecimal("task_id");
	}

	public void setTaskId(BigDecimal taskId) {
		setBigDecimal("task_id", taskId);
	}

	public BigDecimal getParamId() {
		return getBigDecimal("param_id");
	}

	public void setParamId(BigDecimal paramId) {
		setBigDecimal("param_id", paramId);
	}

	public String getIsManu() {
		return getString("is_manu");
	}

	public void setIsManu(String isManu) {
		setString("is_manu", isManu);
	}

	public String getTaskParamDataType() {
		return getString("task_param_data_type");
	}

	public void setTaskParamDataType(String taskParamDataType) {
		setString("task_param_data_type", taskParamDataType);
	}

	public String getIsArray() {
		return getString("is_array");
	}

	public void setIsArray(String isArray) {
		setString("is_array", isArray);
	}

	public String getIsRequired() {
		return getString("is_required");
	}

	public void setIsRequired(String isRequired) {
		setString("is_required", isRequired);
	}

	public BigDecimal getParamOrder() {
		return getBigDecimal("param_order");
	}

	public void setParamOrder(BigDecimal paramOrder) {
		setBigDecimal("param_order", paramOrder);
	}

	public String getIsDeleted() {
		return getString("is_deleted");
	}

	public void setIsDeleted(String isDeleted) {
		setString("is_deleted", isDeleted);
	}

	public String getVer() {
		return getString("ver");
	}

	public void setVer(String ver) {
		setString("ver", ver);
	}

}
