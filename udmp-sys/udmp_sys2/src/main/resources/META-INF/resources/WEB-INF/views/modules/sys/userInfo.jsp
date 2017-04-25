<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>个人信息</title>
	<meta name="decorator" content="bootstrap335"/>
</head>
<body>
	<%-- <ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/sys/user/info" target="mainFrame">个人信息</a></li>
		<li><a href="${ctx}/sys/user/modifyPwd" target="mainFrame">修改密码</a></li>
	</ul> --%>
	<form:form id="frmUserInfo" modelAttribute="user" action="${ctx}/sys/user/info" 
		method="post" class="form-horizontal" target="mainFrame">
		<%--<form:hidden path="email" htmlEscape="false" maxlength="255"/>
		<sys:ckfinder input="email" type="files" uploadPath="/mytask" selectMultiple="false"/> --%>
		<sys:newMessage content="${message}"/>
		<div class="form-group">
			<label class="col-sm-2 control-label">头像：</label>
			<div class="col-sm-4">
				<form:hidden id="nameImage" path="photo" htmlEscape="false" maxlength="255"/>
				<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">归属公司：</label>
			<div class="col-sm-4">
				<p class="form-control-static">${user.company.name}</p>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">归属部门：</label>
			<div class="col-sm-4">
				<p class="form-control-static">${user.office.name}</p>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label"><span class="red">*</span> 姓名：</label>
			<div class="col-sm-4">
				<form:input path="name" htmlEscape="false" maxlength="50" class="form-control" readonly="true"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">邮箱：</label>
			<div class="col-sm-4">
				<form:input path="email" htmlEscape="false" maxlength="50" class="form-control" 
					data-bv-emailaddress="true" data-bv-emailaddress-message="请输入有效的电子邮箱" data-bv-trigger="blur" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">电话：</label>
			<div class="col-sm-4">
				<form:input path="phone" htmlEscape="false" maxlength="20" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">手机：</label>
			<div class="col-sm-4">
				<form:input path="mobile" htmlEscape="false" maxlength="20" class="form-control"
					data-bv-regexp="true" data-bv-regexp-regexp="^(\+86)?(0)?(1(([35][0-9])|(47)|(70)|[8][0-9]))\d{8}$"
					data-bv-regexp-message="请输入有效的手机号码" data-bv-trigger="blur"  />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">备注：</label>
			<div class="col-sm-4">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="form-control"/>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">用户类型:</label>
			<div class="col-sm-10">
				<p class="form-control-static">${fns:getDictLabel(user.userType, 'sys_user_type', '无')}</p>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">用户角色：</label>
			<div class="col-sm-10">
				<p class="form-control-static">${user.roleNames}</p>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">上次登录：</label>
			<div class="col-sm-10">
				<p class="form-control-static">IP: ${user.oldLoginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${user.oldLoginDate}" type="both" dateStyle="full"/></p>
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
			$("#frmUserInfo").bootstrapValidator({});
		});
	</script>
</body>
</html>