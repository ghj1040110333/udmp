<%@ page  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="activiti_maven_project.com.git.common.vo.ActiveNodes"%>
<%@page import="activiti_maven_project.com.git.common.vo.ActiveNode"%>
<%@page import="java.util.List"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<style> 
.div-b{ background:#FFF;width:100px;height:50px;padding:1px;color:#F00; filter:alpha(Opacity=70);-moz-opacity:0.6;opacity: 0.6} } 
</style> 
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/temp_table.css">

  </head>
	<body>
		<%
			ActiveNodes vo = (ActiveNodes) request.getAttribute("vo");
			String taskId = vo.getTaskId();
			ActiveNode pp = vo.getActiveNode();
		%>
			<img alt="" src="picture.action?taskId=<%=taskId%>" style="position: absolute; left: 0px; top: 0px;"/>
		<%

			List ls = vo.getActiveNodes();
			if(ls != null && ls.size()> 0){
				for(int i =0;i<ls.size();i++){
					ActiveNode ppt = (ActiveNode)ls.get(i);
		%>
			<div
				style="-webkit-animation: flow-light .2s infinite linear;position:absolute;border:5px solid grey;left:<%=ppt.getX()-3%>px;top:<%=ppt.getY()-3%>px;width:<%=ppt.getWidth()-2%>px;height:<%=ppt.getHeight()-2%>px;"></div>
		<% 	
				}
			}
		%>

		<div
			style="-webkit-animation: flow-light .2s infinite linear;position:absolute;border:5px solid red;left:<%=pp.getX()-3 %>px;top:<%=pp.getY()-3%>px;width:<%=pp.getWidth()-2%>px;height:<%=pp.getHeight()-2%>px;"></div>
	</body>
</html>
