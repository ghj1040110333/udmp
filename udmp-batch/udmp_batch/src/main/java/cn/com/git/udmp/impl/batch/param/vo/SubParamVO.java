package cn.com.git.udmp.impl.batch.param.vo;

import java.math.BigDecimal;

import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchVO;
import cn.com.git.udmp.common.model.DataObject;

/**
 * 系统参数子表VO
 * 
 * @author yangfeiit@newchinalife.com
 */
public class SubParamVO extends DataObject implements IBaseBatchVO{
	private static final long serialVersionUID = 1L;
	
	/**
     * @Fields paramItemId : 子参数ID
     */
    private BigDecimal paramItemId;
	
    /**
     * @Fields paramId : 参数ID
     */
    private BigDecimal paramId;
    
    /**
     * @Fields paramItemName : 参数项名称
     */
    private String paramItemName;
    
    /**
     * @Fields paramItemValue : 参项值
     */
    private String paramItemValue;
    
    /**
     * @Fields paramItemOrder : 排序值
     */
    private BigDecimal paramItemOrder;
    
    /**
     * @Fields isDeleted : 是否删除，是-1/否-0，默认否
     */
    private String isDeleted;    
    
	public BigDecimal getParamItemId() {
		return paramItemId;
	}
	
	public void setParamItemId(BigDecimal paramItemId) {
		this.paramItemId = paramItemId;
	}
	
	public BigDecimal getParamId() {
		return paramId;
	}

	public void setParamId(BigDecimal paramId) {
		this.paramId = paramId;
	}

	public String getParamItemName() {
		return paramItemName;
	}

	public void setParamItemName(String paramItemName) {
		this.paramItemName = paramItemName;
	}

	public String getParamItemValue() {
		return paramItemValue;
	}

	public void setParamItemValue(String paramItemValue) {
		this.paramItemValue = paramItemValue;
	}

	public BigDecimal getParamItemOrder() {
		return paramItemOrder;
	}

	public void setParamItemOrder(BigDecimal paramItemOrder) {
		this.paramItemOrder = paramItemOrder;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
	
	@Override
    public String toString() {
        return "subParamVO [" + "paramItemId=" + paramItemId + "," + "paramId=" + paramId 
        		+ "," + "paramItemName=" + paramItemName + "," + "paramItemValue=" + paramItemValue
        		+ "," + "paramItemOrder=" + paramItemOrder + "," + "isDeleted=" + isDeleted + ","
        		+ "]";
    }

}
