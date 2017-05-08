<%@page import="activiti_maven_project.com.git.common.Page"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidTemplateConfigure"%>
<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidTemplateConfigureMapperImpl"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidActivityConfigure"%>
<%@page import="java.util.List"%>
<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidActivityConfigureMapperImpl"%>
<%@page contentType="text/html; charset=utf-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<% 
	String name="";
	String value="";
	String inOrOut="";
	String templateId="";
	String activeId="";
	String version="";
	if(request.getAttribute("name")!=null){
		name=request.getAttribute("name").toString();
	}
	if(request.getAttribute("value")!=null){
		value=request.getAttribute("value").toString();
	}
	if(request.getAttribute("inOrOut")!=null){
		inOrOut=request.getAttribute("inOrOut").toString();
	}
	if(request.getAttribute("templateId")!=null){
		templateId=request.getAttribute("templateId").toString();
	}
	if(request.getAttribute("activeId")!=null){
		activeId=request.getAttribute("activeId").toString();
	}
	if(request.getAttribute("version")!=null){
		version=request.getAttribute("version").toString();
	}
	List listAct = (List) request.getAttribute("listAct");
	List listTem = (List) request.getAttribute("listTem");
	
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
  <form action="<%=basePath %>/addWfmidActivityWsField.action" onsubmit="return isOr()" method="post">
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
						}else{
					%>
						<option value ="">--请选择</option>	
						<option value ="1">入参</option>								
						<option value ="0">出参</option>
					<%
						}
					%>
		</select>
	</span>
	<span class="d-span"><label>模板ID:</label>
		
			<% 
				if(request.getAttribute("templateId")!=null && !"".equals(request.getAttribute("templateId")) && request.getAttribute("activeId")!=null &&  !"".equals(request.getAttribute("activeId"))){
			%>
				<input type="text" onfocus=this.blur() value="<%=templateId %>" id="wfmidActivityWsField.templateId" name="wfmidActivityWsField.templateId" readonly="true" class="um-input"/>
			<%
				}else{
			%>
					<select name="wfmidActivityWsField.templateId" id ="wfmidActivityWsField.templateId" onChange="templateId()" class="um-select">	
			<%
						if(request.getAttribute("templateId")!=null && !"".equals(request.getAttribute("templateId"))){
				%>
							<option value="<%=templateId%>"><%=templateId%></option>
					<%	
						}else{
					%>
							<option value="">--请选择--</option>
					<%
						}
					 %>

						<%
							for (int i = 0; i < listTem.size(); i++) {
								WfmidTemplateConfigure con = (WfmidTemplateConfigure)listTem.get(i);
								if(templateId.equals(con.getProcessTemplateId())){
									continue;
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
			if(request.getAttribute("templateId")!=null && !"".equals(request.getAttribute("templateId")) && request.getAttribute("activeId")!=null &&  !"".equals(request.getAttribute("activeId"))){
		%>
				<input type="text" onfocus=this.blur() value="<%=activeId %>" id="wfmidActivityWsField.activeId" name="wfmidActivityWsField.activeId" readonly="true" class="um-input"/>
		<%
			}else{
		%>
				<select name="wfmidActivityWsField.activeId" id ="wfmidActivityWsField.activeId"  class="um-select">	
					<%
		   				for (int i = 0; i < listAct.size(); i++) {
		   					WfmidActivityConfigure vo = (WfmidActivityConfigure)listAct.get(i);
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
	<span class="d-span"><label>版本:</label>
		<% 
			if(version!=null && !"".equals(version)){
		%>
				<input type="text" onfocus=this.blur() value="<%=version %>" id="wfmidActivityWsField.version" name="wfmidActivityWsField.version" readonly="true" class="um-input"/>
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
	function templateId(){
		var templateId= document.getElementById("wfmidActivityWsField.templateId").value;
		var n= document.getElementById("wfmidActivityWsField.parameterName").value;
		var v= document.getElementById("wfmidActivityWsField.valueName").value;
		var i= document.getElementById("wfmidActivityWsField.inOrOut").value;
		window.location.href="<%=basePath%>/addSelectIDatwf.action?templateId="+templateId+"&name="+n+"&value="+v+"&inOrOut="+i+"";	
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
