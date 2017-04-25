

package cn.com.git.udmp.tool.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 
 * @author liang
 *
 */
public class Type {
    private String typeCode;
    private String typeName;
    private List<Code> codeList = new ArrayList<Code>();
    public List<Code> getCodeList() {
        return codeList;
    }
    public void setCodeList(List<Code> codeList) {
        this.codeList = codeList;
    }
    public String getTypeCode() {
        return typeCode;
    }
    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }
    public String getTypeName() {
        return typeName;
    }
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
