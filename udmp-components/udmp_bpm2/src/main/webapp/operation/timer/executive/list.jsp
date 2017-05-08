<%@page import="activiti_maven_project.com.git.quartz.vo.ScheduleJob"%>
<%@page import="org.activiti.engine.identity.Group" %>
<%@page import="activiti_maven_project.com.git.user.UserServiceImpl"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="activiti_maven_project.com.git.common.Page"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%	
	List<ScheduleJob> jobs = (List<ScheduleJob>) request.getAttribute("jobs");	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<base href="<%=basePath%>">
<title>My JSP 'list.jsp' starting page</title>	    
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/um-page.css">
<link rel="stylesheet" href="<%=basePath %>/css/default.css" type="text/css" />
<script src="<%=basePath %>/js/jquery.min.js" type="text/javascript"></script>
<script src="<%=basePath %>/js/lhgdialog.js" type="text/javascript"></script>
</head>
 <body>
	<% 
		String msg=null;
		if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	%>
		<script >
				alert('<%=msg%>');
		</script>
	<% 
		}
	%>
	<div class="table-wrapper-down">
			<table id="um-table-reload" class="um-table">
				<thead>
					<tr>
						<th>任务名称</th>
						<th>任务组</th>
						<th>时间表达式</th>
						<th>状态</th>
						<th>备注</th>									
					</tr>					
				</thead>
				<tbody>
					<%
					if (jobs != null && jobs.size()>0) {
						for(ScheduleJob job:jobs){
				%>
					<tr>
						<td><%=job.getJobName() %></td>
						<td><%=job.getJobGroup() %></td>
						<td><%=job.getJobCronExpression() %></td>
						<td><%=job.getJobStatus() %></td>
						<td><%=job.getDesc()%></td> 
					</tr>
					<%
							}
							
						}else{
					%>
					<div class="um-span-center" style="color:#F00;font-size:20px">※    没有找到相关信息</div>  
					<%
						}
					%>
				</tbody>
			</table>
		</div>

</body>
</html>