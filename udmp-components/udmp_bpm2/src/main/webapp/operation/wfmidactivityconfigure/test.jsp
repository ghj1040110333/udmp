<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml"> 
<head>
<meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
<meta name="author" content="有钱 - www.umoney.baidu.com" />
<meta name="copyright" content="有钱 - www.umoney.baidu.com" />
<meta name="description" content="" />
<meta name="keywords" content="" />
<%-- <link rel="stylesheet" href="<%=basePath %>/css/reset.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath %>/css/um-page.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath %>/css/default.css" type="text/css" /> --%>
<link rel="stylesheet" href="<%=basePath %>/css/reset.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath %>/css/um-page.css" type="text/css" />
<link rel="stylesheet" href="<%=basePath %>/css/default.css" type="text/css" />
<script src="<%=basePath %>/js/jquery.min.js" type="text/javascript"></script>
<script src="<%=basePath %>/js/lhgdialog.js" type="text/javascript"></script>

<title>工作流平台</title>
</head>

<body>
<div class="">
	
	<input type="text" class="um-input">
	<input type="text" class="um-input">
	<input type="text" class="um-input">
	<input type="text" class="um-input">
	<input id="addWindow" type="button" value="新增" class="um-btn">
	<input type="button" value="查询" class="um-btn">
</div>
<div class="table-wrapper">
	<table class="um-table">
		<thead>
			<tr>
				<th>标题</th>
				<th>标题</th>
				<th>标题</th>
			</tr>
		</thead>
		<thead>
			<tr>
				<td>1111</td>
				<td>1111</td>
				<td>1111</td>
			</tr>
			<tr>
				<td>1111</td>
				<td>1111</td>
				<td>1111</td>
			</tr>
			<tr>
				<td>1111</td>
				<td>1111</td>
				<td>1111</td>
			</tr>
		</thead>
	</table>
</div>
<script>
$(document).ready(function(){
	$("#addWindow").click(function(){
		$.dialog({
			lock: true,
			background: '#000', /* 背景色 */
			opacity: 0.5,       /* 透明度 */
			content: '中断用户在对话框以外的交互，展示重要操作与消息',
			icon: 'error.gif',
			ok: function () {
				/* 这里要注意多层锁屏一定要加parent参数 */
				$.dialog({content: '再来一个锁屏', lock: true, parent:this});
				return false;
			},
			cancel: true
		});
	})
	
})
</script>
</body>
</html>