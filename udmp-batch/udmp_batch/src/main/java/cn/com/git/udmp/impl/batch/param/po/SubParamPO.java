package cn.com.git.udmp.impl.batch.param.po;

import java.math.BigDecimal;

import cn.com.git.udmp.common.model.DataObject;

/**
 * 系统参数子表VO
 * 
 * @author yangfeiit@newchinalife.com
 */
public class SubParamPO extends DataObject {
	private static final long serialVersionUID = 1L;
	/** 属性 --- java类型 --- oracle类型_数据长度_小数位长度_注释信息 */
	// paramItemId --- BigDecimal --- NUMBER_16_0_子参数ID
    // paramId --- BigDecimal --- NUMBER_16_0_参数ID
    // paramItemName --- String --- VARCHAR2_200_0_参数项名称;
    // paramItemValue --- String --- VARCHAR2_3000_0_多值参数的每个参数项的参数值;
    // paramItemOrder --- BigDecimal --- NUMBER_4_0_参数下多个参数项的顺序;
	// isDeleted --- String --- CHAR_1_0_是否删除;
	// ver --- String --- VARCHAR2_11_0_预留版本号。默认值：1.0.0;
	
	public BigDecimal getParamItemId() {
		return getBigDecimal("param_item_id");
	}
	
	public void setParamItemId(BigDecimal paramItemId) {
		setBigDecimal("param_item_id", paramItemId);
	}
	
	public BigDecimal getParamId() {
		return getBigDecimal("param_id");
	}

	public void setParamId(BigDecimal paramId) {
		setBigDecimal("param_id", paramId);
	}

	public String getParamItemName() {
		return getString("param_item_name");
	}

	public void setParamItemName(String paramItemName) {
		setString("param_item_name", paramItemName);
	}

	public String getParamItemValue() {
		return getString("param_item_value");
	}

	public void setParamItemValue(String paramItemValue) {
		setString("param_item_value", paramItemValue);
	}

	public BigDecimal getParamItemOrder() {
		return getBigDecimal("param_item_order");
	}

	public void setParamItemOrder(BigDecimal paramItemOrder) {
		setBigDecimal("param_item_order", paramItemOrder);
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
    
    public String toString() {
        return "paramManagePo [" + "paramItemId=" + getParamItemId() + "," + "paramId=" + getParamId() 
        		+ "," + "paramItemName=" + getParamItemName() + "," + "paramItemValue=" + getParamItemValue()
        		+ "," + "paramItemOrder=" + getParamItemOrder() + "," + "isDeleted=" + getIsDeleted() + ","
        		+ "ver=" + getVer() 
        		+ "]";
    }

}
