package cn.com.git.udmp.component.batch.model;

/** 
 * @description 任务参数
 * @author liuliang liuliang_wb@newchina.live 
 * @date 2015年5月6日 下午5:25:47  
*/
public class JobParam {
    private String paramName;
    private String paramValue;
    private String paramType;
    private String paramCode;
    public String getParamName() {
        return paramName;
    }
    public void setParamName(String paramName) {
        this.paramName = paramName;
    }
    public String getParamValue() {
        return paramValue;
    }
    public void setParamValue(String paramValue) {
        this.paramValue = paramValue;
    }
    public String getParamType() {
        return paramType;
    }
    public void setParamType(String paramType) {
        this.paramType = paramType;
    }
    public String getParamCode() {
        return paramCode;
    }
    public void setParamCode(String paramCode) {
        this.paramCode = paramCode;
    }
}
