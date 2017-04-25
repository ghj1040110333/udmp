<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>修改密码</title>
	<meta name="decorator" content="bootstrap335"/>
</head>
<body>
	<%-- <ul class="nav nav-tabs">
		<li><a href="${ctx}/sys/user/info" target="mainFrame">个人信息</a></li>
		<li class="active"><a href="${ctx}/sys/user/modifyPwd" target="mainFrame">修改密码</a></li>
	</ul> --%>
	<form:form id="frmModifyPwd" modelAttribute="user" action="${ctx}/sys/user/modifyPwd" 
		method="post" class="form-horizontal" target="mainFrame">
		<sys:newMessage content="${message}"/>
		<form:hidden path="id"/>
		<div class="form-group">
			<label class="col-sm-2 control-label"><span class="red">*</span> 旧密码：</label>
			<div class="col-sm-4">
				<input id="oldPassword" name="oldPassword" class="form-control" type="password" value="" maxlength="50" minlength="3"
					data-bv-notempty="true" data-bv-trigger="blur" data-bv-notempty-message="请输入旧密码" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label"><span class="red">*</span> 新密码：</label>
			<div class="col-sm-4">
				<input id="newPassword" name="newPassword" class="form-control" type="password" value="" maxlength="50" minlength="3"
					data-bv-notempty="true" data-bv-trigger="blur" data-bv-notempty-message="请输入新密码" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label"><span class="red">*</span> 确认新密码：</label>
			<div class="col-sm-4">
				<input id="confirmNewPassword" name="confirmNewPassword" class="form-control" type="password" value="" maxlength="50" minlength="3"
				  data-bv-notempty="true" data-bv-notempty-message="请再次输入新密码" data-bv-trigger="blur"
	              data-bv-identical="true" data-bv-identical-field="newPassword" data-bv-identical-message="两次输入密码不一致"/>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="submit" class="btn i-btn-ok">保存</button>
			</div>
		</div>
	</form:form>
	<script type="text/javascript">
	$(document).ready(function() {
		$("#frmModifyPwd").bootstrapValidator({});
	});
	</script>
</body>
</html>