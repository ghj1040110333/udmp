package cn.com.git.udmp.impl.batch.task.vo;

import java.math.BigDecimal;
import java.util.List;

import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchVO;
import cn.com.git.udmp.common.model.DataObject;

public class BatchTaskVO extends DataObject implements IBaseBatchVO{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
     * @Fields paramId : 作业ID
     */
	private BigDecimal taskId;
	
	/**
     * @Fields paramId : 作业名称
     */
	private String taskName;
	
	/**
     * @Fields paramId : 作业执行类
     */
	
	private String taskClazz;
	
	/**
     * @Fields paramId : 是否是Spring Bean
     */
	
	private String isSpringBean;
	
	/**
     * @Fields paramId : 所属系统
     */
	private String taskSystem;
	/**
     * @Fields isDeleted : 是否删除 值域：是-1/否-0 默认值：否
     */
    private String isDeleted;
	/**
     * @Fields batchTaskParamList : 作业参数列表
     */
	private List<BatchTaskParamVO> batchTaskParamVOList;

	public BigDecimal getTaskId() {
		return taskId;
	}

	public void setTaskId(BigDecimal taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getTaskClazz() {
		return taskClazz;
	}

	public void setTaskClazz(String taskClazz) {
		this.taskClazz = taskClazz;
	}

	public String getIsSpringBean() {
		return isSpringBean;
	}

	public void setIsSpringBean(String isSpringBean) {
		this.isSpringBean = isSpringBean;
	}

	public String getTaskSystem() {
		return taskSystem;
	}

	public void setTaskSystem(String taskSystem) {
		this.taskSystem = taskSystem;
	}
	
	public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

	public List<BatchTaskParamVO> getBatchTaskParamVOList() {
		return batchTaskParamVOList;
	}

	public void setBatchTaskParamVOList(List<BatchTaskParamVO> batchTaskParamVOList) {
		this.batchTaskParamVOList = batchTaskParamVOList;
	}
	
	
}
