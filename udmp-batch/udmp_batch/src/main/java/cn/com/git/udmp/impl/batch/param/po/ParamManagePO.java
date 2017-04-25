package cn.com.git.udmp.impl.batch.param.po;

import java.math.BigDecimal;

import cn.com.git.udmp.common.model.DataObject;

/**
 * @description 参数信息表PO 
 * @author yangfeiit@newchinalife.com
 */
public class ParamManagePO extends DataObject {
	private static final long serialVersionUID = 1L;

	/** 属性 --- java类型 --- oracle类型_数据长度_小数位长度_注释信息 */
    // paramId --- BigDecimal --- NUMBER_16_0_参数ID
    // paramType --- String --- CHAR_1_0_数据类型,值域：系统参数-S/业务参数-B;
    // paramDataType --- String --- VARCHAR2_20_0_数据类型,日期-date/数值-number/字符串-string;
    // isSingleValue --- String --- CHAR_1_0_是否单值,值域:是-1/否-0,默认值:否;
	// paramCode --- String --- VARCHAR2_200_0_参数代码;
    // paramName --- String --- VARCHAR2_200_0_参数名称;
    // paramDesc --- String --- VARCHAR2_3000_0_参数描述;
	// paramValue --- String --- VARCHAR2_3000_0_单值参数的参数值，多值时为空，明细值在参数子表;
	// isDeleted --- String --- CHAR_1_0_是否删除;
	// ver --- String --- VARCHAR2_11_0_预留版本号。默认值：1.0.0;

	public BigDecimal getParamId() {
		return getBigDecimal("param_id");
	}

	public void setParamId(BigDecimal paramId) {
		setBigDecimal("param_id", paramId);
	}

	public String getParamType() {
		return getString("param_type");
	}

	public void setParamType(String paramType) {
		setString("param_type", paramType);
	}

	public String getParamDataType() {
		return getString("param_data_type");
	}

	public void setParamDataType(String paramDataType) {
		setString("param_data_type", paramDataType);
	}

	public String getIsSingleValue() {
		return getString("is_single_value");
	}

	public void setIsSingleValue(String isSingleValue) {
		setString("is_single_value", isSingleValue);
	}

	public String getParamCode() {
		return getString("param_code");
	}

	public void setParamCode(String paramCode) {
		setString("param_code", paramCode);
	}

	public String getParamName() {
		return getString("param_name");
	}

	public void setParamName(String paramName) {
		setString("param_name", paramName);
	}

	public String getParamDesc() {
		return getString("param_desc");
	}

	public void setParamDesc(String paramDesc) {
		setString("param_desc", paramDesc);
	}

	public String getParamValue() {
		return getString("param_value");
	}

	public void setParamValue(String paramValue) {
		setString("param_value", paramValue);
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
        return "paramManagePo [" + "paramId=" + getParamId() + "," + "paramType=" + getParamType() 
        		+ "," + "paramDataType=" + getParamDataType() + "," + "isSingleValue=" + getIsSingleValue()
        		+ "," + "paramCode=" + getParamCode() + "," + "paramName=" + getParamName() + ","
        		+ "paramDesc=" + getParamDesc() + "," + "paramValue=" + getParamValue() + "," + "isDeleted=" + getIsDeleted()
        		+ "ver=" + getVer() 
        		+ "]";
    }
	
}