<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=utf-8" %>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String id=request.getParameter("id");
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/um-page.css">
<title>jQuery EasyUI</title>

</head>
<body>
  <form action="<%=basePath %>/suspend.action?taskIdArr=<%=id%>" method="post" onsubmit="return isOr()">
	<input type="hidden" value="" name="id"/>
	<input type="hidden" id="btn" />
	<div class="dialog-box">
	<span class="d-span"><label>时长:</label><input type="text" id="timerValue" onBlur="isOr()" id="timerValue" name="timerValue" class="um-input"/></span>
	<span class="d-span"><label>时长类型:</label>
		<select name="timerType" id ="timerType" class="um-select">	
				<option value="hour">小时</option>
				<option value="day">天</option>				
   	 	</select>
	<input type="submit" value ="提交" class="um-btn mr15 mt15 mb15 fr"/>
	<input type="reset" value = "重置" class="um-btn mr15 mt15 mb15 fr" /></th>
	</div>
	</form>
<script type="text/javascript"> 
	function isOr(){
		var timerValue = document.getElementById("timerValue").value;
		if(timerValue!=null && timerValue!=""){
			if(/(?!0+$)^[\d]{1,10}$/.test(timerValue)){    /*正则表达式，判断所填信息是否为int类型*/ 
				return true; 
			}else{
				alert("请输入小于十位的正整数");
				return false;
			}
		}else{
				alert("请填写时长");
				return false;
		}		
	}
</script>	
</body>
</html>
