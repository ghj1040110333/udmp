package cn.com.git.udmp.impl.batch.task.po;

import java.math.BigDecimal;

import cn.com.git.udmp.common.model.DataObject;

/**
 * @description BatchTaskPO对象
 * @author anthorName@newchinalife.com
 * @date 2015-02-10 16:36:39
 */
public class BatchTaskPO extends DataObject {
	
	private BigDecimal taskId;

	public void setTaskId(BigDecimal taskId) {
		setBigDecimal("task_id", taskId);
	}

	public BigDecimal getTaskId() {
		return getBigDecimal("task_id");
	}

	public void setIsDeleted(String isDeleted) {
		setString("is_deleted", isDeleted);
	}

	public String getIsDeleted() {
		return getString("is_deleted");
	}

	public void setTaskName(String taskName) {
		setString("task_name", taskName);
	}

	public String getTaskName() {
		return getString("task_name");
	}

	public void setIsSpringBean(String isSpringBean) {
		setString("is_spring_bean", isSpringBean);
	}

	public String getIsSpringBean() {
		return getString("is_spring_bean");
	}

	public void setVer(String ver) {
		setString("ver", ver);
	}

	public String getVer() {
		return getString("ver");
	}

	public void setTaskSystem(String taskSystem) {
		setString("task_system", taskSystem);
	}

	public String getTaskSystem() {
		return getString("task_system");
	}

	public void setTaskClazz(String taskClazz) {
		setString("task_clazz", taskClazz);
	}

	public String getTaskClazz() {
		return getString("task_clazz");
	}

	@Override
	public String toString() {
		return "BatchTaskPO [" + "taskId=" + getTaskId() + "," + "isDeleted="
				+ getIsDeleted() + "," + "taskName=" + getTaskName() + ","
				+ "isSpringBean=" + getIsSpringBean() + "," + "ver=" + getVer()
				+ "," + "taskSystem=" + getTaskSystem() + "," + "taskClazz="
				+ getTaskClazz() + "]";
	}
}
