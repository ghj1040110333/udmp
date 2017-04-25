

package cn.com.git.ncl.aplus.batch.entity;

import java.math.BigDecimal;

import cn.com.git.udmp.common.persistence.TreeEntity;

public class BatchTaskExecuteData extends TreeEntity<BatchTaskExecuteData> {
    
    /** 
    * @Fields serialVersionUID : TODO(用一句话描述这个变量含义) 
    */ 
    
    private static final long serialVersionUID = -6123354925988531290L;

    /** 
    * @Fields legPerCod : 法人代码
    */ 
    private String legPerCod;
    
    /** 
    * @Fields batchJobId : 任务ID
    */ 
    private BigDecimal batchJobId;

    public String getLegPerCod() {
        return legPerCod;
    }

    public void setLegPerCod(String legPerCod) {
        this.legPerCod = legPerCod;
    }

    public BigDecimal getBatchJobId() {
        return batchJobId;
    }

    public void setBatchJobId(BigDecimal batchJobId) {
        this.batchJobId = batchJobId;
    }

    @Override
    public BatchTaskExecuteData getParent() {
        return parent;
    }

    @Override
    public void setParent(BatchTaskExecuteData parent) {
        this.parent = parent;
    }
    
}
