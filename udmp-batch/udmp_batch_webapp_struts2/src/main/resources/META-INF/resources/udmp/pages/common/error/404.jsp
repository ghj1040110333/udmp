<%@ page language="java" contentType="text/html; charset=UTF-8" import="java.util.*"
    pageEncoding="UTF-8"%>  
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>404</title>
<style type="text/css">
html, body, div, span, applet, object, iframe,
h1, h2, h3, h4, h5, h6, p, blockquote, pre,
a, abbr, acronym, address, big, cite, code,
del, dfn, em, font, img, ins, kbd, q, s, samp,
small, strike, strong, sub, sup, tt, var,
dl, dt, dd, ol, ul, li,
fieldset, form, label, legend,
table, caption, tbody, tfoot, thead, tr, th, td { padding:0; margin:0;  line-height:100%; }

ul, ol { list-style:none;}
img { border:0;}
body {
	background-color: #FFF;

}
.errbg {
	background-image: url(${ctx}/udmp/dwz/themes/default/images/err_bg.jpg);
	background-repeat: no-repeat;
	height: 570px;
	width: 1003px;
	position: absolute;
	left: 50%;
	top: 50%;
	margin-top: -285px;
	margin-left: -500px;<br />
}
.errbg h2 {
	font-family: "华文行楷";
	font-size: 32px;
	color: #000;
	font-weight: bold;
	position: absolute;
	left: 119px;
	top: 139px;
}
.errbg h2 em {
	font-size: 90px;
	font-style: normal;
	word-spacing: 0.5em;
}
.errbg p {
	font-family: "微软雅黑";
	font-size: 14px;
	color: #000;
	position: absolute;
	left: 96px;
	top: 243px;
	width: 316px;
	height: 77px;
	line-height: 22px;
}
.errbg a {
	font-family: "微软雅黑";
	font-size: 14px;
	color: #00F;
	position: absolute;
	left: 188px;
	top: 333px;
	width: 125px;
	height: 22px;
	line-height: 22px;
	text-align: center;
	text-decoration: none;
}
</style>
</head>

<body>
<div class="errbg">
<h2><em>404</em>,四零四了</h2>
<p>抱歉，出错了！请尝试刷新页面。维护人员，已经很辛苦的去修机器了。请耐心等待！</p>
<a href="<%=basePath %>udmp/pages/login/login.jsp">返回首页</a>

</div>
</body>
</html>
