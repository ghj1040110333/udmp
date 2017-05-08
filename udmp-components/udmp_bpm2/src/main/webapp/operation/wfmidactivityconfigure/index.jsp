<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'index.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    	<a href="#" target="winContent">编辑</a>
		<hr color=red>
		<a href="wfmidactivityconfigure/add.jsp">新增</a><br>
		<a href="wfmidactivityconfigure/delete.jsp">删除</a><br>
		<a href="wfmidactivityconfigure/update.jsp">修改</a><br>
		<a href="wfmidactivityconfigure/list.jsp">查询</a><br>
  </body>
</html>
