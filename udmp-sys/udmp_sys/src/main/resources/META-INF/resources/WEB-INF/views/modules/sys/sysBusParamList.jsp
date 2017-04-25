<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>业务参数设置管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        };
        
        function onlyNum(){
        	if(!((event.keyCode>=48&&event.keyCode<=57)||(event.keyCode>=96&&event.keyCode<=105))){
	        	 alert("请输入数字！");
	         	 event.returnValue=false;
	          }else{
	        	 event.returnValue=true;
	         }
        };
	</script>
</head>
<body>
	
	<form:form id="inputForm" modelAttribute="sysBusParam" action="${ctx}/sys/sysBusParam/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">客户申请可使用的证件类型：</label>
			<div class="controls">
               <form:checkboxes path="certificateTypes" items="${fns:getDictList('sys_certificate_type')}" itemLabel="label" itemValue="value" htmlEscape="false" class="input-xlarge required"/>
               <span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户密码强度：</label>
			<div class="controls">
               <form:select path="custPasswordStrength">
					<form:options items="${fns:getDictList('sys_password_strength')}" itemLabel="label" itemValue="value" htmlEscape="false" class="input-xlarge required"/>					
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户密码更换间隔天数：</label>
			<div class="controls">
				<form:input path="custPasschangeIntDay" onkeydown="onlyNum();" htmlEscape="false" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>			
		</div>
		<div class="control-group">
			<label class="control-label">客户密码最大错误次数：</label>
			<div class="controls">
				<form:input path="custPasswordMaxrWrongTime"  onkeydown="onlyNum();" htmlEscape="false" class="input-xlarge required"/>              	
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户密码强度：</label>
			<div class="controls">
               <form:select path="userPasswordStrength">
					<form:options items="${fns:getDictList('sys_password_strength')}" itemLabel="label" itemValue="value" htmlEscape="false" class="input-xlarge required"/>					
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户密码更换间隔天数：</label>
			<div class="controls">
				<form:input path="userPasschangeIntDay"  onkeydown="onlyNum();" htmlEscape="false" class="input-xlarge required"/>              	
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">用户密码最大错误次数：</label>
			<div class="controls">
				<form:input path="userPasswordMaxrWrongTime" onkeydown="onlyNum();" htmlEscape="false" class="input-xlarge required"/>              	
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">循环信用产品最长有效期（年）：</label>
			<div class="controls">
				<form:input path="cycleCreditProIongestPeriod"  onkeydown="onlyNum();" htmlEscape="false" class="input-xlarge required"/>              	
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">循环信用产品默认有效期（年）：</label>
			<div class="controls">
				<form:input path="cycleCreditProDefaultPeriod"  onkeydown="onlyNum();" htmlEscape="false" class="input-xlarge required"/>              	
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">在用注册协议版本号：</label>
			<div class="controls">
				 <form:select path="regAgreOnlineVnum">
					<form:options items="${regAgreementList}" itemLabel="agreementVersionNo" itemValue="agreementVersionNo" htmlEscape="false" class="input-xlarge required"/>					
				</form:select>             	
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">单一客户最高授信限额：</label>
			<div class="controls">
				<form:input path="onlyoneCreditLimitStr"  htmlEscape="false" class="input-xlarge required"/>              	
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">白名单客户最高授信限额：</label>
			<div class="controls">
				<form:input path="whitelistLimitStr"   htmlEscape="false" class="input-xlarge required"/>              	
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="sys:sysBusParam:edit"><input id="btnSubmit" class="btn i-btn-ok" type="submit" value="确定"/>&nbsp;</shiro:hasPermission>
			<!--  <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>-->
		</div>
		
   </form:form>  
</body>
</html>