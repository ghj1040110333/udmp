<%@page import="java.util.ArrayList"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidActivityConfigure"%>
<%@page import="activiti_maven_project.com.git.mybatis.common.page.Page"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidTemplateConfigure"%>
<%@page import="java.util.List"%>
<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidTemplateConfigureMapperImpl"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=utf-8" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String activityName="";
	String url="";
	String wsdl="";
	String methodName="";
	String templateId="";
	String isMeeting="";
	String meetingType="";
	String methodTag="";
	String processTemplateId="";
	String version="";
	if(request.getAttribute("activityName")!=null){
		activityName=request.getAttribute("activityName").toString();
		
	}
	if(request.getAttribute("url")!=null){
		url=request.getAttribute("url").toString();
	}
	if(request.getAttribute("wsdl")!=null){
		wsdl=request.getAttribute("wsdl").toString();
	}
	if(request.getAttribute("methodName")!=null){
		methodName=request.getAttribute("methodName").toString();
	}
	if(request.getAttribute("templateId")!=null){
		templateId=request.getAttribute("templateId").toString();
	}
	if(request.getAttribute("isMeeting")!=null){
		isMeeting=request.getAttribute("isMeeting").toString();
	}
	if(request.getAttribute("meetingType")!=null){
		meetingType=request.getAttribute("meetingType").toString();
	}
	if(request.getAttribute("methodTag")!=null){
		methodTag=request.getAttribute("methodTag").toString();
	}
	if(request.getAttribute("version")!=null){
		version=request.getAttribute("version").toString();
	}
	if(request.getAttribute("processTemplateId")!=null){
		processTemplateId=request.getAttribute("processTemplateId").toString();
	}
	List<String> lists = new ArrayList<String>();
	List list = (List) request.getAttribute("list");//模板集合
	List listVersion = (List) request.getAttribute("listVersion");//版本集合
	List addFailList = (List) request.getAttribute("addFailList");//新增失败后版本集合
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
	<% 
		String msg=null;
		if(request.getAttribute("msg")!=null){
		
			msg=request.getAttribute("msg").toString();
			msg = new String(msg.getBytes("iso-8859-1"),"utf-8");
	%>
		<div style="color:#F00;font-size:15px">※ <%=msg%></div>
	<% 
		}
	%>
 	<form action="<%=basePath %>/addWfmidActivityConfigure.action" onsubmit="return isOr()" method="post">
	<input type="hidden" value="" name="id"/>
	<input type="hidden" id="btn" />
	<div class="dialog-box">
		<span class="d-span"><label>节点名称:</label><input type="text" value="<%=activityName %>" id="wfmidActivityConfigure.activityName" name="wfmidActivityConfigure.activityName" class="um-input"/></span>
		<span class="d-span"><label>URL地址:</label><input type="text" value="<%=url %>" id="wfmidActivityConfigure.url" name="wfmidActivityConfigure.url" class="um-input"/></span>
		<span class="d-span"><label>WSDL地址:</label><input type="text" value="<%=wsdl %>" id="wfmidActivityConfigure.wsdl" name="wfmidActivityConfigure.wsdl" class="um-input"/></span>
		<span class="d-span"><label>方法名称:</label><input type="text" value="<%=methodName %>" id="wfmidActivityConfigure.methodName" name="wfmidActivityConfigure.methodName" class="um-input"/></span>
		<span class="d-span"><label>方法参数标签:</label><input type="text" value="<%=methodTag %>" id="wfmidActivityConfigure.methodTag" name="wfmidActivityConfigure.methodTag" class="um-input"/></span></span>
	 	<span class="d-span"><label>是否会签:</label>
	 		<select name="wfmidActivityConfigure.isMeeting" onChange="isMeeting()" id="wfmidActivityConfigure.isMeeting" class="um-input"/>
					<% if(isMeeting!=null && !"".equals(isMeeting)){
							if(isMeeting.equals("0")){
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
						}else{
					%> 	
							<option value ="0">否</option>								
							<option value ="1">是</option>
					<% 
						}
					%> 
			</select>											
	 	</span>
	 	<span class="d-span"><label>会签方式:</label>
	 		<select disabled="true"  name="wfmidActivityConfigure.meetingType" id ="wfmidActivityConfigure.meetingType" class="um-select">	
				 	<% 
						if(meetingType!=null){
							if(meetingType.equals("2")){
					%> 
								<option value="2">一票通过制</option>
								<option value="1">百分比通过制</option>
								<option value="3">一票否决制</option>				
					 <%	
							}else if(meetingType.equals("3")){
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
			if(processTemplateId!=null && !"".equals(processTemplateId)){
		%>
					<input type="text" onfocus=this.blur() value="<%=processTemplateId %>" id="wfmidActivityConfigure.templateId" name="wfmidActivityConfigure.templateId" readonly class="um-input"/>
		<% 
			}else if(listVersion!=null && listVersion.size()>0){
		%>
				<select name="wfmidActivityConfigure.templateId" id ="wfmidActivityConfigure.templateId" onChange="version()" class="um-select">	
						<option value ="<%=templateId %>"><%=templateId%></option>
						<%
			 				for (int i = 0; i < lists.size(); i++) { 
								String vo = lists.get(i); 
								if(vo.equals(templateId)){
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
			else{
		%>
				<select name="wfmidActivityConfigure.templateId" id ="wfmidActivityConfigure.templateId" onChange="version()" class="um-select">	
					<option value ="">--请选择--</option>
			<%
			 		for (int i = 0; i < lists.size(); i++) { 
						String vo = lists.get(i); 
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
				if(version!=null && !"".equals(version)){
			%>
					<input type="text" onfocus=this.blur() value="<%=version %>" id="wfmidActivityConfigure.version" name="wfmidActivityConfigure.version" readonly class="um-input"/>
			<% 
				}else if(listVersion!=null && listVersion.size()>0){
			%>
					<select name="wfmidActivityConfigure.version" id ="wfmidActivityConfigure.version" class="um-select">	
				<%
			 			for (int j = 0; j < listVersion.size(); j++) { 
							WfmidTemplateConfigure vo = (WfmidTemplateConfigure)listVersion.get(j); 
				%>
		 					<option value ="<%=vo.getVersion()%>"><%=vo.getVersion()%></option>
	 			<% 
	 					}
	 			%>
	 				</select>
			<% 
				}else{
			%>
					<input type="text" value="<%=version %>" id="wfmidActivityConfigure.version" name="wfmidActivityConfigure.version" disabled class="um-input"/>
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
							if(templateId!=null && templateId!=""){
								return true;
							}else{
								alert("请选择模板");
								return false;
							}
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
					
				