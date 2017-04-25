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
 * 字典单选框标签
 * @author admin
 * @date 2014年4月3日
 */
public class DictionaryRadioTag extends SimpleTagSupport {

	private String codeType;//类别码
	private String name; //标签页面name属性
	private String cssStyle; //页面style属性
	private String cssClass; //页面class属性
	private String checkedItem;	//选中项
	private String functionSet; //js事件及处理函数
	
	private boolean textShowFront=false; //内容文本是否显示在前面
	private List limitation;//集合中不包含该子集
	private boolean disabled;//标签控件是否可用，true:可用，false:不可用
	public boolean isDisabled() {
		return disabled;
	}

	public void setDisabled(boolean disabled) {
		this.disabled = disabled;
	}

	public String getCheckedItem() {
		return checkedItem;
	}

	public void setCheckedItem(String checkedItem) {
		this.checkedItem = checkedItem;
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

	public boolean isTextShowFront() {
		return textShowFront;
	}

	public void setTextShowFront(boolean textShowFront) {
		this.textShowFront = textShowFront;
	}

	
	
	public String getCodeType() {
		return codeType;
	}

	public void setCodeType(String codeType) {
		this.codeType = codeType;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public List getLimitation() {
		return limitation;
	}

	public void setLimitation(List limitation) {
		this.limitation = limitation;
	}
	
	public void doTag() throws JspException, IOException
	{
		ServletContext servletContext = ((PageContext) this.getJspContext()).getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        DictService dictionaryService=(DictService) wac.getBean(DictService.class);             
	
		JspWriter out=null;
		out=this.getJspContext().getOut();

		if(checkValid(codeType)==false){
			return;
		}
		Dict entity = new Dict();
        entity.setType(codeType);
        List<Dict> list = dictionaryService.findList(entity );
		if(null!=limitation && limitation.size()>0)
		{
			TagHelp.removeLimitation(list,limitation);
		}
		String disabledStr="";
		if(disabled)
		{
			disabledStr=" disabled='disabled' ";
		}
		if(null !=list && list.size()>0){
			if(null!=checkedItem && !"".equals(checkedItem)){				
				if(textShowFront){
					for(Dict obj:list){
						out.write("<span>"+obj.getLabel()+"</span>");
						out.write("<input type='radio' "+(checkValid(name)?" name="+name:"")+(checkValid(cssStyle)?" style="+cssStyle:"")+
								(checkValid(cssClass)?" class="+cssClass:"")+(checkValid(functionSet)?" "+
						functionSet.split(":")[0]+"="+functionSet.split(":")[1]:"")+
						" value='"+obj.getValue()+"'"+
						(checkValid(checkedItem) && obj.getValue().equals(checkedItem)?" checked=true":"")+disabledStr+
								"/>");										
					}
				}
				else{
					for(Dict obj:list){
						
						out.write("<input type='radio' "+(checkValid(name)?" name="+name:"")+(checkValid(cssStyle)?" style="+cssStyle:"")+
								(checkValid(cssClass)?" class="+cssClass:"")+(checkValid(functionSet)?" "+
						functionSet.split(":")[0]+"="+functionSet.split(":")[1]:"")+
						" value='"+obj.getValue()+"'"+
						(checkValid(checkedItem)  && obj.getValue().equals(checkedItem)?" checked=true":"")+disabledStr+
								"/>");		
						out.write("<span>"+obj.getLabel()+"</span>");
					}
					
				}
			
					
			}else{
				if(textShowFront){
					for(Dict obj:list){
						out.write("<span>"+obj.getLabel()+"</span>");
						out.write("<input type='radio' "+(checkValid(name)?" name="+name:"")+(checkValid(cssStyle)?" style="+cssStyle:"")+
								(checkValid(cssClass)?" class="+cssClass:"")+(checkValid(functionSet)?" "+
						functionSet.split(":")[0]+"="+functionSet.split(":")[1]:"")+
						" value='"+obj.getValue()+"'"+disabledStr+					
								"/>");										
					}
				}else{
					for(Dict obj:list){
						out.write("<input type='radio' "+(checkValid(name)?" name="+name:"")+(checkValid(cssStyle)?" style="+cssStyle:"")+
								(checkValid(cssClass)?" class="+cssClass:"")+(checkValid(functionSet)?" "+
						functionSet.split(":")[0]+"="+functionSet.split(":")[1]:"")+
						" value='"+obj.getValue()+"'"+disabledStr+					
								"/>");
						out.write("<span>"+obj.getLabel()+"</span>");
					}
				}
				
			}
		}
		
		
		
	}
	/***
	 * 检验字符串参数有效性
	 * @param value
	 * @return
	 */
	private boolean checkValid(String value)
	{
		if(null!=value && !"".equals(value))
		{
			return true;
		}else{
			return false;
		}
	}
}
