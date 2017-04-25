package cn.com.git.udmp.batch.common.tag;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.Tag;
import javax.servlet.jsp.tagext.TagSupport;

/**
 * @description 代码表标签
 * @author liang 2016年1月14日
 */
public class CodeTableTag extends TagSupport {
    private String tableName;
    private String whereClause;
    private String value;

    private String codeType;
    private String codeId;
    private String codeValue;

    /**
     * 
     */
    private static final long serialVersionUID = 5883363037684095774L;

    @Override
    public int doStartTag() throws JspException {
        try {
            System.out.println("================startTag==============");
            HttpServletRequest req = (HttpServletRequest) pageContext.getRequest();
            JspWriter out = this.pageContext.getOut();
            out.println("<p>" + codeType + "</p>");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return SKIP_BODY;
    }

    @Override
    public int doAfterBody() throws JspException {
        // TODO 自动生成的方法存根
        return super.doAfterBody();
    }

    @Override
    public int doEndTag() throws JspException {
        // TODO 自动生成的方法存根
        return Tag.EVAL_PAGE;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getWhereClause() {
        return whereClause;
    }

    public void setWhereClause(String whereClause) {
        this.whereClause = whereClause;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public String getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(String codeValue) {
        this.codeValue = codeValue;
    }
}
