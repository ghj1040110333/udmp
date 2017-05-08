<%@page import="activiti_maven_project.com.git.quartz.vo.ScheduleJob"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%	
	String jobName = (String) request.getParameter("jobName");	
	String groupId = (String) request.getParameter("groupId");	
	String cronSchedule = (String) request.getParameter("cronSchedule");
	
	System.out.println("cronSchedule【"+cronSchedule+"】");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/um-page.css">
  </head>
  
  <body>
  	 <form action="<%=basePath %>/updateplanjob.action?jobName=<%=jobName%>&groupId=<%=groupId%>" method="post" >
	 	<div class="dialog-box">
			<span class="d-span"><label>时间表达式:</label><input type="text" id="cronSchedule" value="<%=cronSchedule%>" name="cronSchedule" class="um-input"/></span>
			<input type="submit" value ="提交" class="um-btn mr15 mt15 mb15 fr"/>
			<input type="reset" value = "重置" class="um-btn mr15 mt15 mb15 fr" /></th>
		</div>	
	</form>
	
  </body>
  
</html>
