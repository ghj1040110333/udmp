<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="org.hibernate.Session"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.context.support.FileSystemXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="activiti_maven_project.com.git.common.Page"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidTemplateConfigure"%>
<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidTemplateConfigureMapperImpl"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
 	<form action="<%=basePath %>/addWfmidActivityConfigure.action" method="post">
	<input type="hidden" value="" name="id"/>
	<input type="hidden" id="btn" />
	<div class="dialog-box">
		<span class="d-span"><label>节点名称:</label><input type="text" value="" name="wfmidActivityConfigure.activityName" class="um-input"/></span>
		<span class="d-span"><label>URL地址:</label><input type="text" value="" name="wfmidActivityConfigure.url" class="um-input"/></span>
		<span class="d-span"><label>WSDL地址:</label><input type="text" value="" name="wfmidActivityConfigure.wsdl" class="um-input"/></span>
		<span class="d-span"><label>方法名称:</label><input type="text" value="" name="wfmidActivityConfigure.methodName" class="um-input"/></span>
		<span class="d-span"><label>模板ID:</label><select name="wfmidActivityConfigure.templateId" id ="wfmidActivityConfigure.templateId" class="um-select">	
				 <%
		   		ApplicationContext ctx=new  FileSystemXmlApplicationContext("classpath:applicationContext.xml");	
		   		WfmidTemplateConfigureMapperImpl temm =(WfmidTemplateConfigureMapperImpl)ctx.getBean("wfmidTemplateConfigureMapperImpl");					
				java.util.Map _map =new HashMap();
				Page _page = new Page();
				List<WfmidTemplateConfigure> list = temm.queryList(_map, _page);
			 	for (int i = 0; i < list.size(); i++) { 
				WfmidTemplateConfigure vo = list.get(i); 
				 %>
		 		<option value ="<%=vo.getProcessTemplateId()%>"><%=vo.getProcessTemplateId()%></option>
				 <%} %>
	 		</select>
	 	</span>
	 	<span class="d-span"><label>会签方式:</label><input type="text" value="" name="wfmidActivityConfigure.meetingType" class="um-input"/></span>
	 	<span class="d-span"><label>是否会签:</label><input type="text" value="" name="wfmidActivityConfigure.isMeeting" class="um-input"/></span>
	 	<span class="d-span"><label>方法参数标签:</label><input type="text" value="" name="wfmidActivityConfigure.methodName" class="um-input"/></span></span>
		<input type="submit" value ="提交" class="um-btn mr15 mt15 mb15 fr"/>
		<input type="reset" value = "重置" class="um-btn mr15 mt15 mb15 fr" /></th>
	</div>
	<!-- <table align="center">
	<tr>
		<th>activityName</th> <th><input type="text" value="" name="wfmidActivityConfigure.activityName" class="um-input"/></th>
	</tr>
	<tr>
		<th>url</th> <th><input type="text" value="" name="wfmidActivityConfigure.url" class="um-input"/></th>
	</tr>
	<tr>
		<th>wsdl</th> <th><input type="text" value="" name="wfmidActivityConfigure.wsdl" class="um-input"/></th>
	</tr>
	<tr>
		<th>methodName</th> <th><input type="text" value="" name="wfmidActivityConfigure.methodName" class="um-input"/></th>
	</tr>
		
	<tr>
		<th>templateId</th> 
		<th>
			
		</th>
	</tr>
	
	<tr>
		<th>isMeeting</th> <th><input type="text" value="" name="wfmidActivityConfigure.isMeeting"/></th>
	</tr>
	<tr>
		<th>meetingType</th> <th><input type="text" value="" name="wfmidActivityConfigure.meetingType"/></th>
	</tr>
	<tr>
		<th>methodTag</th> <th><input type="text" value="" name="wfmidActivityConfigure.methodTag"/></th>
	</tr>
	<tr>
		<th></th>
		<th>
		<input type="reset" value = "重置" class="um-btn" /></th>
	</tr>
	</table> -->
	</form>
</body>
</html>
					
				