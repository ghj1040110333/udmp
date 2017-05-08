<%@ page contentType="text/html; charset=utf-8" %>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%@page import="org.hibernate.Session"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
  <form action="<%=basePath %>/addWfmidTemplateConfigure.action" method="post" onsubmit="return isOr()">
	<input type="hidden" value="" name="id"/>
	<input type="hidden" id="btn" />
	<div class="dialog-box">
	<span class="d-span"><label>业务类型:</label><input type="text" value="" id="bizType"  name="wfmidTemplateConfigure.bizType" class="um-input"/></span>
	<span class="d-span"><label>流程模板ID:</label><input type="text" value="" id="processTemplateId" name="wfmidTemplateConfigure.processTemplateId" class="um-input"/></span>
	<span class="d-span"><label>是否生效:</label>
		<select name="wfmidTemplateConfigure.isEffect" id ="wfmidTemplateConfigure.isEffect" class="um-select">	
			<option value="1">是</option>
			<option value="0">否</option>
   	 	</select>
   	 </span>
	<span class="d-span"><label>版本:</label><input type="text" value="" id="version" name="wfmidTemplateConfigure.version" class="um-input"/></span>
	<span class="d-span"><label>时长:</label><input type="text" id="timerValue" onBlur="timerType()" name="wfmidTemplateConfigure.timerValue" class="um-input"/><div id="wtc_timerValue"></div></span>
	<span class="d-span"><label>时长类型:</label>
		<select name="wfmidTemplateConfigure.timerType" id ="wfmidTemplateConfigure.timerType" class="um-select">	
			<option value="minute">分钟</option>
			<option value="hour">小时</option>
			<option value="day">天</option>
   	 	</select>
	 </span>
	<span class="d-span"><label>AUTO_TAKE:</label>
		<select name="wfmidTemplateConfigure.autoTake" id ="autoTake" class="um-select">	
			<option value="0">否</option>
			<option value="1">是</option>
   	 	</select>
	</span>
	<input type="submit" value ="提交" class="um-btn mr15 mt15 mb15 fr"/>
	<input type="reset" value = "重置" class="um-btn mr15 mt15 mb15 fr" /></th>
	</div>
	</form>
<script type="text/javascript"> 
	function timerType(){
		var path = document.getElementById("timerValue").value;
		if(path!=null && path!=""){
			if(/(?!0+$)^[\d]{1,10}$/.test(path)){    /*正则表达式，判断所填信息是否为int类型*/  
	        	return true;     
	    	}else{
	    		alert("请输入小于十位的正整数");
	    		return false;
	    	}	
		}
	}
	function isOr(){
		var bizType = document.getElementById("bizType").value;
		var processTemplateId= document.getElementById("processTemplateId").value;
		var version = document.getElementById("version").value;
		var timerValue = document.getElementById("timerValue").value;
		var autoTake = document.getElementById("autoTake").value;
		
		if(bizType!=null && bizType!=""){
			if(processTemplateId!=null && processTemplateId!=""){
				if(version!=null && version!=""){
					if(autoTake!=null && autoTake!=""){
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
					}else{
						alert("请填写autoTake");
						return false;
					}
				}else{
					alert("请填写版本号");
					return false;
				}
			}else{
				alert("请填写流程模板ID");
				return false;
			}
		}else{
			alert("请填写业务类型");
			return false;
		}
		
	}
</script>	
</body>
</html>
