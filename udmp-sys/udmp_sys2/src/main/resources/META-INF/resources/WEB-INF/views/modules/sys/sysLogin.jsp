<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="org.apache.shiro.web.filter.authc.FormAuthenticationFilter"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <title>新一代零售信贷核算项目-登陆</title>
  <link rel="icon" href="${ctxStatic}/favicon.ico" type="image/x-icon" />
  <link rel="shortcut icon" href="${ctxStatic}/favicon.ico" type="image/x-icon" />
  <link rel="bookmark" href="${ctxStatic}/favicon.ico" type="image/x-icon" />
  <link href="${ctxStatic}/css/params/login/syslogin.css" type="text/css" rel="stylesheet" />
  <script>
    var cpath = "${pageContext.request.contextPath}",
      ctx = "${ctx}";
  </script>
</head>
<body>
  <div id="glass"></div>
  <div id="header" class="clearfix">
    <div id="messageBox" class="alert alert-error ${empty message ? 'hide' : ''}">
      <label id="loginError" class="error">${message}</label>
    </div>
    <div class="i-login-left"><img src="${ctxStatic}/images/logo.png" class="i-login-logo"/></div>
  </div>
  <div id="container">
    <div class="clearfix">
      <h3>新一代零售信贷核算项目</h3>
      <form id="loginForm" class="form-signin" action="${ctx}/login" method="post">
        <div class="i-form">
          <div class="i-user-name">
            <input type="text" id="username" name="username" placeholder="用户名" class="i-input">
          </div>
          <div class="i-user-pwd">
            <input type="password" id="password" name="password" placeholder="密码" class="i-input">
          </div>
          <c:if test="${isValidateCodeLogin}">
            <div class="validateCode">
              <sys:validateCode name="validateCode" inputCssStyle="margin-bottom:0;"/>
            </div>
          </c:if>
          <input id="i-submit" type="submit" value="登 录"/>
        </div>
      </form>
    </div>
  </div>
  <ul class="bg-bubbles">
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
    <li></li>
  </ul>
  <div id="footer">
    版权所有：高伟达软件股份有限公司
  </div>
  <script src="${ctxStatic}/lib/jquery/jquery.js" type="text/javascript"></script>
  <script src="${ctxStatic}/lib/jquery-validation/jquery.validate.js" type="text/javascript"></script>
  <script src="${ctxUdmp}/params/login/syslogin.js" type="text/javascript" /></script>
</body>
</html>
