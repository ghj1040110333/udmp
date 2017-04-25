package cn.com.git.udmp.batch.common.tag;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cn.com.git.udmp.modules.sys.entity.Dict;
import cn.com.git.udmp.modules.sys.service.DictService;

/***
 * 字典下拉列表标签
 * 
 * @author admin
 * @date 2014年4月3日
 */
public class DictionarySelectTag extends SimpleTagSupport {
    // @Resource
    // private DictService dictionaryService;
    private String codeType; // 类别码
    private String cssStyle; // 页面style属性
    private String cssClass; // 页面class属性
    private List selectedItems; // 所有选中项
    private String selectedValue; // 选中项

    private String functionSet; // js事件及处理函数
    private String id; // 页面id属性
    private String name; // 页面name属性
    private boolean multiSelect; // 是否支持多选
    private int size = 1; // 显示行数

    private List exclusion;// 集合限制在这个范围
    private List limitation;// 集合中不包含该子集
    private String exclusionstr;// 集合中不包含该字符串中的内容，字符串格式如"a;b"

    public String getExclusionstr() {
        return exclusionstr;
    }

    public void setExclusionstr(String exclusionstr) {
        this.exclusionstr = exclusionstr;
    }

    private boolean disabled;// 标签控件是否可用，true:可用，false:不可用

    public boolean isDisabled() {
        return disabled;
    }

    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSelectedValue() {
        return selectedValue;
    }

    public void setSelectedValue(String selectedValue) {
        this.selectedValue = selectedValue;
    }

    public List getSelectedItems() {
        return selectedItems;
    }

    public void setSelectedItems(List selectedItems) {
        this.selectedItems = selectedItems;
    }

    public String getCodeType() {
        return codeType;
    }

    public void setCodeType(String codeType) {
        this.codeType = codeType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCssStyle() {
        return cssStyle;
    }

    public void setCssStyle(String cssStyle) {
        this.cssStyle = cssStyle;
    }

    public String getCssClass() {
        return cssClass;
    }

    public void setCssClass(String cssClass) {
        this.cssClass = cssClass;
    }

    public String getFunctionSet() {
        return functionSet;
    }

    public void setFunctionSet(String functionSet) {
        this.functionSet = functionSet;
    }

    public boolean isMultiSelect() {
        return multiSelect;
    }

    public void setMultiSelect(boolean multiSelect) {
        this.multiSelect = multiSelect;
    }

    public List getExclusion() {
        return exclusion;
    }

    public void setExclusion(List exclusion) {
        this.exclusion = exclusion;
    }

    public List getLimitation() {
        return limitation;
    }

    public void setLimitation(List limitation) {
        this.limitation = limitation;
    }

    public void doTag() throws JspException, IOException {
        ServletContext servletContext = ((PageContext) this.getJspContext()).getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        DictService dictionaryService = (DictService) wac.getBean(DictService.class);

        JspWriter out = null;
        out = this.getJspContext().getOut();

        if (checkValid(codeType) == false) {
            return;
        }
        // Map argMap=new HashMap();
        // argMap.put("codeType", codeType.trim());
        Dict entity = new Dict();
        entity.setType(codeType);
        // if(null!=exclusionstr && exclusionstr.length()>0)
        // {
        // argMap.put("exclusionstr", exclusionstr);
        // }
        List<Dict> list = dictionaryService.findList(entity);
        if (null != limitation && limitation.size() > 0) {
            TagHelp.removeLimitation(list, limitation);
        }

        String disabledStr = "";
        if (disabled) {
            disabledStr = " disabled='disabled' ";
        }
        String outHtml = "<select" + (checkValid(id) ? " id=" + id : "") + (checkValid(name) ? " name=" + name : "")
        + (checkValid(cssStyle) ? " style=" + cssStyle : "")
        + (checkValid(cssClass) ? " cssclass=" + cssClass : "")
        + (checkValid(functionSet) ? " " + functionSet.split(":")[0] + "=" + functionSet.split(":")[1]
                : "")
        + (multiSelect == true ? " multiple=true" : "") + (size > 1 ? (" size=" + size) : "")
        + disabledStr + " placeholder>";
        System.out.println("outhtml================"+outHtml);
        out.write(outHtml);
        out.write("<option value=''>请选择</option>");
        if (null != list && list.size() > 0) {
            if (multiSelect == true) {
                if (null != selectedItems && selectedItems.size() > 0) {
                    for (Dict obj : list) {
                        out.write("<option value='" + obj.getValue() + "' "
                                + (selectedItems.contains(obj.getValue()) ? " selected='selected'" : "") + ">"
                                + obj.getLabel() + "</option>");

                    }

                } else {
                    for (Dict obj : list) {
                        out.write("<option value='" + obj.getValue() + "' >" + obj.getLabel() + "</option>");

                    }

                }
            } else {
                if (null != selectedValue && !"".equals(selectedValue)) {

                    for (Dict obj : list) {
                        out.write("<option value='" + obj.getValue() + "' "
                                + (selectedValue.equals(obj.getValue()) ? " selected='selected'" : "") + ">"
                                + obj.getLabel() + "</option>");

                    }

                } else {
                    for (Dict obj : list) {
                        out.write("<option value='" + obj.getValue() + "' >" + obj.getLabel() + "</option>");

                    }

                }

            }

        }

        out.write("</select>");

    }

    /***
     * 检验字符串参数有效性
     * 
     * @param value
     * @return
     */
    private boolean checkValid(String value) {
        if (null != value && !"".equals(value)) {
            return true;
        } else {
            return false;
        }
    }
}
