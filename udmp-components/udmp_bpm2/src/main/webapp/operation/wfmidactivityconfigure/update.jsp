<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidTemplateConfigure"%>
<%@page import="activiti_maven_project.com.git.mybatis.common.page.Page"%>
<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidTemplateConfigureMapperImpl"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidActivityConfigure"%>
<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidActivityConfigureMapperImpl"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
 	String  id = request.getParameter("id");
 	String version="";
 	if(request.getAttribute("version")!=null){
		version=request.getAttribute("version").toString();
	}
	WfmidActivityConfigure con = (WfmidActivityConfigure) request.getAttribute("con");
	List<String> lists = new ArrayList<String>();
	List list = (List) request.getAttribute("list");
	List listVersion = (List) request.getAttribute("listVersion");
    for (int i = 0; i < list.size(); i++) { //去重
		WfmidTemplateConfigure vo = (WfmidTemplateConfigure)list.get(i); 
		String str=vo.getProcessTemplateId();
		if(!lists.contains(str)){   //查看新集合中是否有指定的元素，如果没有则加入
        	lists.add(str);
         }
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
 	<form action="<%=basePath %>/updateWfmidActivityConfigure.action?id=<%=id%>" onsubmit="return isOr()" method="post">
	<input type="hidden" value="" name="id"/>
	<input type="hidden" id="btn" />
	<div class="dialog-box">
		<span class="d-span"><label>节点名称:</label><input type="text" value="<%=con.getActivityName() %>" id="wfmidActivityConfigure.activityName" name="wfmidActivityConfigure.activityName" class="um-input"/></span>
		<span class="d-span"><label>URL地址:</label><input type="text" value="<%=con.getUrl() %>" id="wfmidActivityConfigure.url" name="wfmidActivityConfigure.url" class="um-input"/></span>
		<span class="d-span"><label>WSDL地址:</label><input type="text" value="<%=con.getWsdl()%>" id="wfmidActivityConfigure.wsdl" name="wfmidActivityConfigure.wsdl" class="um-input"/></span>
		<span class="d-span"><label>方法名称:</label><input type="text" value="<%=con.getMethodName() %>" id="wfmidActivityConfigure.methodName" name="wfmidActivityConfigure.methodName" class="um-input"/></span>
		<span class="d-span"><label>方法参数标签:</label><input type="text" value="<%=con.getMethodTag()%>" id="wfmidActivityConfigure.methodTag" name="wfmidActivityConfigure.methodTag" class="um-input"/></span></span>
	 	<span class="d-span"><label>是否会签:</label>
	 		<select name="wfmidActivityConfigure.isMeeting" onChange="isMeeting()" id="wfmidActivityConfigure.isMeeting" class="um-input"/>
					 <% 
						if(con.getIsMeeting().equals("0")){
					%> 
							<option value ="0">否</option>								
							<option value ="1">是</option>					
					 <%	
						}else{
					%> 
							<option value ="1">是</option>
							<option value ="0">否</option>						
					 <%	
						}
					%> 	 
			</select>											
	 	</span>
	 	<span class="d-span"><label>会签方式:</label>
	 		<select disabled="true"  name="wfmidActivityConfigure.meetingType" id ="wfmidActivityConfigure.meetingType" class="um-select">	
				 	<% 
						if(con.getMeetingType()!=null){
							if(con.getMeetingType().equals("2")){
					%> 
								<option value="2">一票通过制</option>
								<option value="1">百分比通过制</option>
								<option value="3">一票否决制</option>				
					 <%	
							}else if(con.getMeetingType().equals("3")){
					%> 
								<option value="3">一票否决制</option>	
								<option value="1">百分比通过制</option>
								<option value="2">一票通过制</option>						
					 <%	
							} else{
					%> 
								<option value="1">百分比通过制</option>
								<option value="2">一票通过制</option>
								<option value="3">一票否决制</option>
					 <%	
							}
						}else{
					%>
							<option value="1">百分比通过制</option>
							<option value="2">一票通过制</option>
							<option value="3">一票否决制</option>
					 <%	
							}
					%>						
   	 		</select>
	 	</span>
	 	<span class="d-span"><label>模板ID:</label>
			<% 
				if(request.getAttribute("msg")!=null && !"".equals(request.getAttribute("msg"))){
			%>
				<input onfocus=this.blur() type="text" value="<%=con.getTemplateId() %>" id="wfmidActivityConfigure.templateId" name="wfmidActivityConfigure.templateId" readonly class="um-input"/>
			<% 
				}else{
			%>
		 			<select name="wfmidActivityConfigure.templateId" id ="wfmidActivityConfigure.templateId" onChange="version()" class="um-select">	
						<option value ="<%=con.getTemplateId() %>"><%=con.getTemplateId()%></option>
						<%
			 				for (int i = 0; i < lists.size(); i++) { 
								String vo = lists.get(i); 
								if(vo.equals(con.getTemplateId())){
									continue;
								}
						%>
		 							<option value ="<%=vo%>"><%=vo%></option>
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
				if(request.getAttribute("msg")!=null && !"".equals(request.getAttribute("msg"))){
			%>
					<input type="text" onfocus=this.blur() value="<%=con.getVersion() %>" id="wfmidActivityConfigure.version" name="wfmidActivityConfigure.version" readonly class="um-input"/>
			<% 
				}else{
			%>
					<select name="wfmidActivityConfigure.version" id ="wfmidActivityConfigure.version" class="um-select">	
						<option value ="<%=con.getVersion()%>"><%=con.getVersion()%></option>
				<%
			 			for (int j = 0; j < listVersion.size(); j++) { 
							WfmidTemplateConfigure vo = (WfmidTemplateConfigure)listVersion.get(j); 
							if(vo.getVersion().equals(con.getVersion())){
								continue;
							}
				%>
		 					<option value ="<%=vo.getVersion()%>"><%=vo.getVersion()%></option>
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
	isMeeting(); 
	function isMeeting(){
		var path = document.getElementById("wfmidActivityConfigure.isMeeting").value; 
		if(path==1){
			 document.getElementById("wfmidActivityConfigure.meetingType").disabled="";
		}else{
			document.getElementById("wfmidActivityConfigure.meetingType").disabled=true;
		}
	}
	function version(){
		var activityName= document.getElementById("wfmidActivityConfigure.activityName").value;
		var url= document.getElementById("wfmidActivityConfigure.url").value;
		var wsdl= document.getElementById("wfmidActivityConfigure.wsdl").value;
		var methodName= document.getElementById("wfmidActivityConfigure.methodName").value;
		var methodTag= document.getElementById("wfmidActivityConfigure.methodTag").value;
		var isMeeting= document.getElementById("wfmidActivityConfigure.isMeeting").value;
		var meetingType= document.getElementById("wfmidActivityConfigure.meetingType").value;
		var templateId= document.getElementById("wfmidActivityConfigure.templateId").value;
		window.location.href="addSelectVersion.action?templateId="+templateId+"&activityName="+activityName+"&url="+url+"&wsdl="+wsdl+"&methodName="+methodName+"&methodTag="+methodTag+"&isMeeting="+isMeeting+"&meetingType="+meetingType+"";	
	}
	function isOr(){
		var activityName= document.getElementById("wfmidActivityConfigure.activityName").value;
		var url= document.getElementById("wfmidActivityConfigure.url").value;
		var wsdl= document.getElementById("wfmidActivityConfigure.wsdl").value;
		var methodName= document.getElementById("wfmidActivityConfigure.methodName").value;
		var methodTag= document.getElementById("wfmidActivityConfigure.methodTag").value;
		var templateId= document.getElementById("wfmidActivityConfigure.templateId").value;
		
		if(activityName!=null && activityName!=""){
			if(url!=null && url!=""){
				if(wsdl!=null && wsdl!=""){
					if(methodName!=null && methodName!=""){
						if(methodTag!=null && methodTag!=""){
							return true;
						}else{
							alert("请输入方法参数标签");
							return false;
						}
					}else{
						alert("请输入方法名称");
						return false;
					}
				}else{
					alert("请输入WSDL");
					return false;
				}
			}else{
				alert("请输入url");
				return false;
			}
		}else{
			alert("请输入节点名称");
			return false;
		}
	}   	
</script>
</html>
