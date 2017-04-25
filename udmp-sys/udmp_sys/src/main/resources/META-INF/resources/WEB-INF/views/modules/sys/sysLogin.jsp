<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
	<title>${fns:getConfig('productName')} 登录</title>
	<link rel="icon" href="${ctxUdmp}/css/favicon.ico" type="image/x-icon" />
	<link rel="shortcut icon" href="${ctxUdmp}/css/favicon.ico" type="image/x-icon" />
	<link rel="bookmark" href="${ctxUdmp}/css/favicon.ico" type="image/x-icon" />
	<link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
	<link href="${ctxUdmp}/css/params/syslogin.css" type="text/css" rel="stylesheet" />
	<script>
		var cpath = "${pageContext.request.contextPath}",
				ctx = "${ctx}";
	</script>
</head>
<body>
	<div id="header" class="clearfix">
		<div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}">
			<label id="loginError" class="error">${message}</label>
		</div>
		<div class="f-left"><img src="${ctxUdmp}/css/images/logo.png" class="i-login-logo"/></div>
		<h3 class="f-right">${fns:getConfig('productName')}</h3>
	</div>
	<div id="container">
		<div id="content" class="clearfix">
			<div class="f-left w-left" style="display:none">
				<img src="${ctxUdmp}/css/images/login_bg.png" class="login-bg"/>
			</div>
			<div class="f-right w-right">
				<form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
					<div class="c-form">
						<h5 class="c-title">登录</h5>
						<div class="c-uname">
							<label class="input-label" for="username">用户名</label><br />
							<input type="text" id="username" name="username" class="input-block-level required" value="${username}">
						</div>
						<div class="c-upwd">
							<label class="input-label" for="password">密码</label><br />
							<input type="password" id="password" name="password" class="input-block-level required">
						</div>
						<c:if test="${isValidateCodeLogin}"><div class="validateCode">
							<label class="input-label mid" for="validateCode">验证码</label><br />
							<sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
						</div></c:if>
						<label class="c-remem" for="rememberMe" title="下次不需要再登录"><input type="checkbox" id="rememberMe" name="rememberMe" ${rememberMe ? 'checked' : ''}/>记住密码</label><br />
						<input id="c-submit" type="submit" value="登 录"/>
					</div>
				</form>
			</div>
			<div id="i-element" style="display:none">
				<div class="o-circle">
					<div class="i-circle"></div>
				</div>
			</div>
		</div>
	</div>
	<div id="footer">
		版权所有：高伟达软件股份有限公司
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script>
	<script src="${ctxUdmp}/js/params/syslogin.js" type="text/javascript" /></script>
</body>
</html>
