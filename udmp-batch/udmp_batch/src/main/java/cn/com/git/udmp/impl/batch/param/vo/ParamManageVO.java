package cn.com.git.udmp.impl.batch.param.vo;

import java.math.BigDecimal;
import java.util.List;

import cn.com.git.udmp.component.batch.common.base.entity.IBaseBatchVO;
import cn.com.git.udmp.common.model.DataObject;

/**
 * 参数信息表VO
 * 
 * @author yangfeiit@newchinalife.com
 */
public class ParamManageVO extends DataObject implements IBaseBatchVO{
	private static final long serialVersionUID = 1L;
	
	/**
     * @Fields paramId : 参数ID
     */
    private BigDecimal paramId;
    
    /**
     * @Fields paramType : 参数类型，系统参数-S/业务参数-B
     */
    private String paramType;
    
    /**
     * @Fields paramDataType : 数据类型，日期-date/数值-number/字符串-string
     */
    private String paramDataType;

    /**
     * @Fields isSingleValue : 是否单值
     */
    private String isSingleValue;
    
    /**
     * @Fields paramCode : 参数代码
     */
    private String paramCode;
    
    /**
     * @Fields paramName : 参数名称
     */
    private String paramName;
    
    /**
     * @Fields paramDesc : 参数描述
     */
    private String paramDesc;    
    
    /**
     * @Fields paramValue : 参数值
     */
    private String paramValue;
    
    /**
     * @Fields isDeleted : 是否删除，是-1/否-0，默认否
     */
    private String isDeleted;

    /**
     * @Fields ver : 预留版本号。默认值：1.0.0 
     */
    private String ver;
    
    private List<SubParamVO> subParamList;

	public BigDecimal getParamId() {
		return paramId;
	}

	public void setParamId(BigDecimal paramId) {
		this.paramId = paramId;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getParamDataType() {
		return paramDataType;
	}

	public void setParamDataType(String paramDataType) {
		this.paramDataType = paramDataType;
	}

	public String getIsSingleValue() {
		return isSingleValue;
	}

	public void setIsSingleValue(String isSingleValue) {
		this.isSingleValue = isSingleValue;
	}

	public String getParamCode() {
		return paramCode;
	}

	public void setParamCode(String paramCode) {
		this.paramCode = paramCode;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamDesc() {
		return paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getIsDeleted() {
		return isDeleted;
	}

	public void setIsDeleted(String isDeleted) {
		this.isDeleted = isDeleted;
	}
    
    public String getVer() {
        return ver;
    }

    public void setVer(String ver) {
        this.ver = ver;
    }
    
    public List<SubParamVO> getSubParamList() {
        return subParamList;
    }

    public void setSubParamList(List<SubParamVO> subParamList) {
        this.subParamList = subParamList;
    }
	
	@Override
    public String toString() {
        return "paramManageVO [" + "paramId=" + paramId + "," + "paramType=" + paramType 
        		+ "," + "paramDataType=" + paramDataType + "," + "isSingleValue=" + isSingleValue
        		+ "," + "paramCode=" + paramCode + "," + "paramName=" + paramName + ","
        		+ "paramDesc=" + paramDesc + "," + "paramValue=" + paramValue + "," + "isDeleted=" + isDeleted
        		+ "ver=" + ver + "subParamList" + subParamList
        		+ "]";
    }

    
	
}