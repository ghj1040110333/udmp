<%@ page contentType="text/html; charset=utf-8" %>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@page import="java.util.*"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String processDefinitionKey = request.getParameter("processKey");
	String usercd = request.getParameter("usercd");
	//Map<String, Object> variables = new HashMap<String, Object>();

 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/um-page.css">
<title>add</title>

</head>
<body>
	 <form action="start.action" method="post">
	 	<input type="hidden" name="processDefinitionKey" value="<%=processDefinitionKey%>"/> 
	 	<input type="hidden" name="usercd" value="<%=usercd %>"/> 
	 	<div class="dialog-box">
	 	
	 		<span class="d-span"><label>客户id</label><input type="text"  value="" id="custManager"  name="cus_id" class="um-input"/></span>
	 		<span class="d-span"><label>客户名称</label><input type="text" value="" id="cus_name"  name="cus_name" class="um-input"/></span>
	 		<span class="d-span"><label>身份证号</label><input type="text" value="" id="cus_sfz"  name="cus_sfz" class="um-input"/></span>
	 		<span class="d-span"><label>银行卡号</label><input type="text"  value="" id="cus_yhk"  name="cus_yhk" class="um-input"/></span>
	 	
	 		<input type="submit" value ="提交" class="um-btn mr15 mt15 mb15 fr"/>
			<input type="reset" value = "重置" class="um-btn mr15 mt15 mb15 fr" />
	 	</div>
	 </form>	
</body>
</html>
