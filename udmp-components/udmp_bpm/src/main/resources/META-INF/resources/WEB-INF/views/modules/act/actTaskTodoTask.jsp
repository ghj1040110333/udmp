<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>待办任务</title>
	<meta name="decorator" content="default"/>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/act/task/todo/">待办任务</a></li>
		<li><a href="${ctx}/act/task/historic/">已办任务</a></li>
		<li><a href="${ctx}/act/task/process/">新建任务</a></li>
	</ul>
	<div class="well well-lg">
		  <i class="icon-pencil"></i> 待审核的借款申请 ：${list.size()} &nbsp;&nbsp;&nbsp;&nbsp;  
		<a data-dismiss="alert" title="小提示" class="btn" href="${ctx}/act/task/todo">查看</a> 
	</div>
	<sys:message content="${message}"/>
	 
</body>
</html>
