<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:set var="ctx">${pageContext.request.contextPath}</s:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8"></meta>
<%@include file="/common.jsp"%>
<script type="text/javascript">
	var dwzEncode = "gbk";
	//定义上一次点击的时间
	var prevTime = new Date();
	//获取最后一次点击的时间
	var nextTime = 0;
	//获取session超时时间
	var sessionTimeOut = <%=(Integer)session.getMaxInactiveInterval()%> * 1000;
	$(function() {
		DWZ.init("${ctx }/udmp/dwz/dwz.frag.xml", {
			loginUrl : "udmp/pages/login/openingLoginWindow.jsp",
			loginTitle : "登录", // 弹出登录对话框
			//		loginUrl:"login.html",	// 跳到登录页面
			statusCode : {
				ok : 200,
				error : 300,
				timeout : 301
			}, //【可选】
			pageInfo : {
				pageNum : "pageNum",
				numPerPage : "numPerPage",
				orderField : "orderField",
				orderDirection : "orderDirection"
			}, //【可选】
			debug : false, // 调试模式 【true|false】
			callback : function() {
				initEnv();
				$("#themeList").theme({
					themeBase : "themes"
				}); // themeBase 相对于index页面的主题base路径
			}
		});

		 $.ajax({
			type : "get",
			async : false,
			url : "udmp/pages/login/welcome.jsp",
			success : function(data) {
				$('#mainFrame').html(data);
				$("table.table", document).jTable();
			}
		}); 
	});
	//手动打开登录窗口
	function openLoginDialog() {
		DWZ.loadLogin();
		return false;
	}
	function closeAllPanel() {
		$(".collapsable").click();
	}
	//窗口重新登录后调用
	function showOptSuccess() {
		alertMsg.info('登录成功,请继续您的工作吧！')
		$(".warnPageSpan")
				.html(
						"<img src='udmp/images/opt_success.png' height='64' width='64' /> <br />"
								+ "<font style='color: red; font-size: 30px;'>亲！欢迎回来，请继续您的工作吧。</font>");
	}
</script>

<!-- <body scroll="no" onbeforeunload="clearUser();"> 此处功能是当浏览器大小小于一定范围后进行清理session中存放user信息操作，需要可去掉注释-->
</head>
<body scroll="no">
	<div id="layout">
		<!-- 头部 -->
		<%@include file="header.jsp"%>
		<%@include file="left.jsp"%>

		<div id="container" style="top:0px;">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader">
					<div class="tabsPageHeaderContent">
						<!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
						<ul class="navTab-tab">
							<li tabid="main" class="main"><a href="javascript:;"><span><span
								class="home_icon">我的主页</span></span></a></li>
						</ul>
					</div>
					<div class="tabsLeft">left</div>
					<!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
					<div class="tabsRight">right</div>
					<!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
					<div class="tabsMore">more</div>
				</div>
				<ul class="tabsMoreList">
					<li><a href="javascript:;" id="homePageTitle">我的主页</a></li>
				</ul>
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox" id="mainFrame" >
						<div class="pageContent"></div>
						<div class="tabsFooter">
							<div class="tabsFooterContent"></div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>