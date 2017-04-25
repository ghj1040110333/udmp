package cn.com.git.udmp.impl.batch.jobDepend.bo;

import java.math.BigDecimal;

import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchBO;
import cn.com.git.udmp.common.model.DataObject;

/**
 * @description BatchJobDependBO对象
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 11:08:09
 */
public class BatchJobDependBO extends DataObject implements IBaseBatchBO{
    private static final long serialVersionUID = 1L;
    /**
     * @Fields dependStatus : 依赖任务的状态，统一遵循参数表中数据字典定义的任务状态
     */
    private String dependStatus;
    /**
     * @Fields actionJobId : 触发执行任务ID。 外键关联T
     */
    private BigDecimal actionJobId;
    /**
     * @Fields isDeleted : 是否删除。值域：是-1/否-0 默认值：否
     */
    private String isDeleted;
    /**
     * @Fields dependJobId : 被依赖任务ID。 外键关联T
     */
    private BigDecimal dependJobId;
    /**
     * @Fields dependType : 依赖类型。 值域：前置-B/后置-A
     */
    private String dependType;
    /**
     * @Fields dependId : 任务依赖ID
     */
    private BigDecimal dependId;

    public void setDependStatus(String dependStatus) {
        this.dependStatus = dependStatus;
    }

    public String getDependStatus() {
        return dependStatus;
    }

    public void setActionJobId(BigDecimal actionJobId) {
        this.actionJobId = actionJobId;
    }

    public BigDecimal getActionJobId() {
        return actionJobId;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setDependJobId(BigDecimal dependJobId) {
        this.dependJobId = dependJobId;
    }

    public BigDecimal getDependJobId() {
        return dependJobId;
    }

    public void setDependType(String dependType) {
        this.dependType = dependType;
    }

    public String getDependType() {
        return dependType;
    }

    public void setDependId(BigDecimal dependId) {
        this.dependId = dependId;
    }

    public BigDecimal getDependId() {
        return dependId;
    }

    @Override
    public String toString() {
        return "BatchJobDependBO [" + "dependStatus=" + dependStatus + "," + "actionJobId=" + actionJobId + ","
                + "isDeleted=" + isDeleted + "," + "dependJobId=" + dependJobId + "," + "dependType=" + dependType
                + "," + "dependId=" + dependId + "]";
    }
}
