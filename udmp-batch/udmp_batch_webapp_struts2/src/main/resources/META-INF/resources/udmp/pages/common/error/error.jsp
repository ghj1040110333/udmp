<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<title>异常页面</title>  
<script type="text/javascript">
	function showDetail(){
		var elm = document.getElementById("detail_system_error_msg");
		if(elm.style.display == ''){
			elm.style.display = 'none';
		} else {
			elm.style.display = '';
		}
	}
</script>
</head>  
<body>  
    <br/>
    <b>错误原因：<s:property value="exception.message" /></b>
    <br>
    <s:submit type="button" onclick="history.back();">返回</s:submit>
    <br>
    <p><a href="#" onclick="showDetail();">点击这里查看错误消息</a>，报告以下错误信息给系统管理员，可以更加快速的解决问题</p>
    <div id="detail_system_error_msg" style="display:none">
    	<pre><s:property value="exceptionStack" /></pre>
    </div>
</body>  
</html>  
