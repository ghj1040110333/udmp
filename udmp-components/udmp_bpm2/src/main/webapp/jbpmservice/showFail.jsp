<%@ page  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.List"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	<link rel="stylesheet" type="text/css" href="css/temp_table.css">
  </head>

	<body>
		<% 
		String msg = null;
		if(request.getAttribute("msg")!=null){
			msg =(String) request.getAttribute("msg");
		%>
			<div class="um-span-centerDown" style="color:#F00;font-size:20px;text-align:center;line-height:330px; ">※   <%=msg %></div>
		<% 
		}
		%>
	</body>
</html>
