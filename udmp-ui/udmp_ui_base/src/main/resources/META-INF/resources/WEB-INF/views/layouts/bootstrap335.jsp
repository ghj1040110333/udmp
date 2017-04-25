<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="sitemesh" uri="http://www.opensymphony.com/sitemesh/decorator" %>
<!DOCTYPE html>
<%
	String xReq = request.getHeader("X-Requested-With");
	if (xReq == null) {
%>
<html lang="zh-CN">
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="renderer" content="webkit">
	<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
	<meta name="author" content="http://git.com.cn/"/>
	<meta http-equiv="Expires" content="0">
	<meta http-equiv="Cache-Control" content="no-cache">
	<meta http-equiv="Cache-Control" content="no-store">
	<title><sitemesh:title/></title>
	<%@include file="/WEB-INF/views/include/head335.jsp" %>
	<sitemesh:head/>
</head>
<body>
	<%@include file="/WEB-INF/views/include/common.jsp" %>
	<sitemesh:body/>
</body>
</html>
<% } else { %>
<sitemesh:head/>
<sitemesh:body/>
<% } %>