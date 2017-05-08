<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidTemplateConfigure"%>
<%@page import="activiti_maven_project.com.git.common.Page"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.springframework.context.support.FileSystemXmlApplicationContext"%>
<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidTemplateConfigureMapperImpl"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
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
<link rel="stylesheet" type="text/css" href="css/temp_form.css">
</head>
<body>
<td><a href="wfmidActivityWsField\update.jsp?id=6">修改</td>
	
</body>
</html>
