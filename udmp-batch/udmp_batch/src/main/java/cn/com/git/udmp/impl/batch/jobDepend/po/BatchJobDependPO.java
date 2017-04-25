package cn.com.git.udmp.impl.batch.jobDepend.po;

import java.math.BigDecimal;

import cn.com.git.udmp.common.model.DataObject;

/**
 * @description BatchJobDependPO对象
 * @author yangfeiit@newchinalife.com
 * @date 2015-02-04 11:02:54
 */
public class BatchJobDependPO extends DataObject {

    private static final long serialVersionUID = 1L;

    /** 属性 --- java类型 --- oracle类型_数据长度_小数位长度_注释信息 */
    // dependStatus --- String --- VARCHAR2_2_0_依赖任务的状态，统一遵循参数表中数据字典定义的任务状态;
    // actionJobId --- BigDecimal ---
    // NUMBER_16_0_触发执行任务ID。外键关联T_UDMP_BATCH_JOB.JOB_ID;
    // isDeleted --- String --- CHAR_1_0_是否删除。值域：是-1/否-0 默认值：否;
    // dependJobId --- BigDecimal ---
    // NUMBER_16_0_被依赖任务ID。外键关联T_UDMP_BATCH_JOB.JOB_ID;
    // ver --- String --- VARCHAR2_11_0_预留版本号。默认值：1.0.0;
    // dependType --- String --- CHAR_1_0_依赖类型。值域：前置-B/后置-A;
    // dependId --- BigDecimal --- NUMBER_16_0_任务依赖ID;

    public void setDependStatus(String dependStatus) {
        setString("depend_status", dependStatus);
    }

    public String getDependStatus() {
        return getString("depend_status");
    }

    public void setActionJobId(BigDecimal actionJobId) {
        setBigDecimal("action_job_id", actionJobId);
    }

    public BigDecimal getActionJobId() {
        return getBigDecimal("action_job_id");
    }

    public void setIsDeleted(String isDeleted) {
        setString("is_deleted", isDeleted);
    }

    public String getIsDeleted() {
        return getString("is_deleted");
    }

    public void setDependJobId(BigDecimal dependJobId) {
        setBigDecimal("depend_job_id", dependJobId);
    }

    public BigDecimal getDependJobId() {
        return getBigDecimal("depend_job_id");
    }

    public void setVer(String ver) {
        setString("ver", ver);
    }

    public String getVer() {
        return getString("ver");
    }

    public void setDependType(String dependType) {
        setString("depend_type", dependType);
    }

    public String getDependType() {
        return getString("depend_type");
    }

    public void setDependId(BigDecimal dependId) {
        setBigDecimal("depend_id", dependId);
    }

    public BigDecimal getDependId() {
        return getBigDecimal("depend_id");
    }

    @Override
    public String toString() {
        return "BatchJobDependPO [" + "dependStatus=" + getDependStatus() + "," + "actionJobId=" + getActionJobId()
                + "," + "isDeleted=" + getIsDeleted() + "," + "dependJobId=" + getDependJobId() + "," + "ver="
                + getVer() + "," + "dependType=" + getDependType() + "," + "dependId=" + getDependId() + "]";
    }
}
