package cn.com.git.udmp.impl.batch.task.bo;

import java.math.BigDecimal;

import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchBO;
import cn.com.git.udmp.common.model.DataObject;

/**
 * @description BatchTaskVO对象
 * @author anthorName@newchinalife.com
 * @date 2015-02-10 10:56:50
 */
public class BatchTaskBO extends DataObject implements IBaseBatchBO{
    private static final long serialVersionUID = 1L;
    /**
     * @Fields taskId : 作业ID
     */
    private BigDecimal taskId;
    /**
     * @Fields isDeleted : 是否删除 值域：是-1/否-0 默认值：否
     */
    private String isDeleted;
    /**
     * @Fields taskName : 作业名称
     */
    private String taskName;
    /**
     * @Fields isSpringBean : 是否是spring bean id 值域：是-1/否-0 默认值：否
     */
    private String isSpringBean;
    /**
     * @Fields ver : 版本号
     */
    private String ver;
    /**
     * @Fields taskSystem : 所属系统 统一遵循参数表中数据字典
     */
    private String taskSystem;
    /**
     * @Fields taskClazz : 作业执行类 执行类全路径或者spring bean id
     */
    private String taskClazz;

    public void setTaskId(BigDecimal taskId) {
        this.taskId = taskId;
    }

    public BigDecimal getTaskId() {
        return taskId;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setIsSpringBean(String isSpringBean) {
        this.isSpringBean = isSpringBean;
    }

    public String getIsSpringBean() {
        return isSpringBean;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }

    public String getVer() {
        return ver;
    }

    public void setTaskSystem(String taskSystem) {
        this.taskSystem = taskSystem;
    }

    public String getTaskSystem() {
        return taskSystem;
    }

    public void setTaskClazz(String taskClazz) {
        this.taskClazz = taskClazz;
    }

    public String getTaskClazz() {
        return taskClazz;
    }

    @Override
    public String toString() {
        return "BatchTaskVO [" + "taskId=" + taskId + "," + "isDeleted=" + isDeleted + "," + "taskName=" + taskName
                + "," + "isSpringBean=" + isSpringBean + "," + "ver=" + ver + "," + "taskSystem=" + taskSystem + ","
                + "taskClazz=" + taskClazz + "]";
    }
}
