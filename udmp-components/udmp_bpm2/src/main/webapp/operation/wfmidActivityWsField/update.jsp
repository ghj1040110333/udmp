<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidTemplateConfigure"%>
<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidTemplateConfigureMapperImpl"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidActivityConfigure"%>
<%@page import="java.util.List"%>
<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidActivityConfigureMapperImpl"%>
<%@page import="activiti_maven_project.com.git.mybatis.common.page.Page"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidActivityWsField"%>
<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidActivityWsFieldMapperImpl"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=utf-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<% 
	String id=request.getParameter("id");
	String templateId="";
	String name="";
	String value="";
	String inOrOut="";
	String activeId="";
	
	WfmidActivityWsField configure = (WfmidActivityWsField)request.getAttribute("configure");
	List list = (List) request.getAttribute("list");
	List listimpl = (List) request.getAttribute("listimpl");
	if(request.getAttribute("templateId")!=null){
		templateId=request.getAttribute("templateId").toString();
	}
	if(request.getAttribute("name")!=null){
		name=request.getAttribute("name").toString();
	}else{
		name=configure.getParameterName();
	}
	if(request.getAttribute("value")!=null){
		value=request.getAttribute("value").toString();
	}else{
		value=configure.getValueName();
	}
	if(request.getAttribute("inOrOut")!=null){
		inOrOut=request.getAttribute("inOrOut").toString();
	}else{
		inOrOut=configure.getInOrOut();
	}
	if(request.getAttribute("activeId")!=null){
		activeId=request.getAttribute("activeId").toString();
	}else{
		activeId=configure.getActiveId();
	}
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/um-page.css">
<title>jQuery EasyUI</title>
</head>
<body>
  <form action="<%=basePath %>/updateWfmidActivityWsField.action?id=<%=id %>" onsubmit="return isOr()" method="post">
	<input type="hidden" value="" name="id"/>
	<input type="hidden" id="btn" />
	<div class="dialog-box">
	<span class="d-span"><label>参数名称:</label><input type="text" value="<%=name %>" id="wfmidActivityWsField.parameterName" name="wfmidActivityWsField.parameterName" class="um-input"/></span>
	<span class="d-span"><label>值名称:</label><input type="text" value="<%=value %>" id="wfmidActivityWsField.valueName" name="wfmidActivityWsField.valueName" class="um-input"/></span>
	<span class="d-span"><label>入参出参:</label>
		<select name="wfmidActivityWsField.inOrOut" id="wfmidActivityWsField.inOrOut" class="um-input"/>
					<% 
						if(inOrOut!=null && inOrOut.equals("1")){
					%>	
						<option value ="1">入参</option>								
						<option value ="0">出参</option>
					<% 
						}else if(inOrOut!=null && inOrOut.equals("0")){
					%>
						<option value ="0">出参</option>
						<option value ="1">入参</option>	
					<%
						}
					%>
		</select>
	</span>
	<span class="d-span"><label>模板ID:</label>
			<% 
				if(request.getAttribute("msg")!=null && request.getAttribute("msg").equals("msg")){
			%>
					<input onfocus=this.blur() type="text" value="<%=configure.getTemplateId()%>" id="wfmidActivityWsField.templateId" name="wfmidActivityWsField.templateId" readonly class="um-input"/>
			<% 
				}else{
			%>
		 			<select name="wfmidActivityWsField.templateId" id ="wfmidActivityWsField.templateId" onChange="isMeeting()" class="um-select">	
					<%
						if(request.getAttribute("templateId")!=null){
					%>
							<option value="<%=templateId%>"><%=templateId%></option>
					<%	
						}else{
					%>
							<option value="<%=configure.getTemplateId()%>"><%=configure.getTemplateId()%></option>
					<%
						}
				 	%>
					<%
						for (int i = 0; i < listimpl.size(); i++) {
							WfmidTemplateConfigure con = (WfmidTemplateConfigure)listimpl.get(i);
							if(request.getAttribute("templateId")!=null)
							{
								if(con.getProcessTemplateId().equals(templateId)){
									continue;
								}
							}else
							{
								if(con.getProcessTemplateId().equals(configure.getTemplateId())){
									continue;
								}
							}
						%>
							<option value ="<%=con.getProcessTemplateId()%>"><%=con.getProcessTemplateId() %></option>
					<%
						}
 					%>
	 				</select>
	 		<%
	 			}
	 		%> 
	 </span>
	<span class="d-span"><label>节点ID:</label>
			<% 
				if(request.getAttribute("msg")!=null && request.getAttribute("msg").equals("msg")){
			%>
					<input onfocus=this.blur() type="text" value="<%=configure.getActiveId()%>" id="wfmidActivityWsField.activeId" name="wfmidActivityWsField.activeId" readonly class="um-input"/>
			<% 
				}else{
			%>
					<select name="wfmidActivityWsField.activeId" id ="wfmidActivityWsField.activeId"  class="um-select">	
					<% 
						if(templateId!=null && !"".equals(templateId)){
			
						}else{
			
					%>
							<option value="<%=configure.getActiveId()%>"><%=configure.getActiveId()%></option>
					<%
						}
					%>
					<%
		   				for (int i = 0; i < list.size(); i++) {
		   					WfmidActivityConfigure vo = (WfmidActivityConfigure)list.get(i);
		   					if(templateId!=null && !"".equals(templateId)){
		   				
		   					}else{
		   						if(vo.getActivityName().equals(configure.getActiveId())){
		   							continue;
		   						}
		   					}
					%>
		 					<option value ="<%=vo.getActivityName()%>"><%=vo.getActivityName() %></option>
					<%
					
						}
					%>
	 				</select>
	 		<%
	 			}
	 		%> 
	</span>
	 <input type="submit" value ="提交" class="um-btn mr15 mt15 mb15 fr"/>
	 <input type="reset" value = "重置" class="um-btn mr15 mt15 mb15 fr" /></th>
	</div>
	</form>
 	
</body>
<script type="text/javascript">
	function isMeeting(){
		var templateId= document.getElementById("wfmidActivityWsField.templateId").value;
		var n= document.getElementById("wfmidActivityWsField.parameterName").value;
		var v= document.getElementById("wfmidActivityWsField.valueName").value;
		var i= document.getElementById("wfmidActivityWsField.inOrOut").value;
		window.location.href="<%=basePath%>/updateSelectIDawfd.action?templateId="+templateId+"&id=<%=id%>&name="+n+"&value="+v+"&inOrOut="+i+"";	
	}
	function isOr(){
		var u= document.getElementById("wfmidActivityWsField.parameterName").value;
		var p= document.getElementById("wfmidActivityWsField.valueName").value;
		var f= document.getElementById("wfmidActivityWsField.inOrOut").value;
		var g= document.getElementById("wfmidActivityWsField.templateId").value;
		var e= document.getElementById("wfmidActivityWsField.activeId").value;
		
		if(u!=null && u!=""){
			if(p!=null && p!=""){
				if(f!=null && f!=""){
					if(g!=null && g!=""){
						if(e!=null && e!=""){
	        					return true;     
						}else{
							alert("请输入节点Id");
							return false;
						}
					}else{
						alert("请输入模板Id");
						return false;
					}
				}else{
					alert("请输入入参出参");
					return false;
				}
			}else{
				alert("请输入值名称");
				return false;
			}
		}else{
			alert("请输入参数名称");
			return false;
		}
	} 	
	
</script>
</html>
