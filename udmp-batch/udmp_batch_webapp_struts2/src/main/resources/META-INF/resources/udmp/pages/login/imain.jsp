<%@page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	response.setHeader("P3P", "CP=CAO PSA OUR");
%>
<s:set var="ctx">${pageContext.request.contextPath}</s:set>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8"></meta>


<title>新华核心系统</title>
<!-- 引入所有css文件 --start -->
<link href="${ctx}/udmp/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/udmp/dwz/themes/css/icore.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/udmp/dwz/themes/other/iother.css" rel="stylesheet" type="text/css" media="screen"/>
<link rel="stylesheet" href="${ctx }/udmp/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<link rel="stylesheet" href="${ctx }/udmp/plugins/ztree/css/demo.css" type="text/css"/>
<link rel="stylesheet" href="${ctx }/udmp/plugins/powerFloat/powerFloat.css" type="text/css"/>
<link rel="stylesheet" href="${ctx }/udmp/plugins/suggest/jquery.bigautocomplete.css" type="text/css"/>
<link rel="stylesheet" href="${ctx }/udmp/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<link rel="stylesheet" href="${ctx }/udmp/plugins/powerFloat/common.css" type="text/css"/>
<link rel="stylesheet" href="${ctx }/udmp/plugins/powerFloat/powerFloat.css" type="text/css"/>
<link rel="stylesheet" href="${ctx }/udmp/plugins/dateutil/css/master.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="${ctx }/udmp/plugins/jquery-treetable-master/stylesheets/jquery.treetable.css" media="screen"/>
<link rel="stylesheet" href="${ctx }/udmp/plugins/jquery-treetable-master/stylesheets/screen.css" media="screen"/>
<link rel="stylesheet" href="${ctx }/udmp/plugins/jquery-treetable-master/stylesheets/jquery.treetable.theme.default.css" media="screen"/>
<link href="${ctx}/udmp/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen" />

<script src="${ctx}/udmp/dwz/js/speedup.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/jquery-1.7.1.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/jquery.cookie.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/jquery.validate.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/xheditor/xheditor-1.1.12-zh-cn.min.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.core.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.util.date.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.validate.method.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.barDrag.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.drag.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.tree.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.accordion.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.iframe.ui.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.theme.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.switchEnv.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.alertMsg.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.contextmenu.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.navTab.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.tab.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.resize.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.dialog.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.dialogDrag.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.sortDrag.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.cssTable.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.stable.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.taskBar.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.ajax.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.pagination.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.database.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.datepicker.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.effects.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.panel.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.checkbox.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.history.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.combox.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.print.js" type="text/javascript"></script>
<script src="${ctx}/udmp/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>
<%-- <script src="${ctx}/nb/scripts/common/test.js" type="text/javascript"></script> --%>
<%--<jsp:include page="/nb/scripts/common/test.jsp"></jsp:include>--%>
<script src="${ctx}/udmp/plugins/ribbon/jquery.organtree.js" type="text/javascript"></script>
<script src="${ctx}/udmp/plugins/ribbon/jquery.nciopenexurl.js" type="text/javascript"></script>

<!--
<script src="${ctx }/udmp/dwz/js/dwz.min.js" type="text/javascript"></script>
-->
<!-- 引入dwz的js文件 --end -->

<!-- 引入ztree的js文件  start-->
<script type="text/javascript" src="${ctx }/udmp/plugins/ztree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript" src="${ctx }/udmp/plugins/ztree/js/jquery.ztree.excheck-3.5.js"></script>
<script type="text/javascript" src="${ctx }/udmp/plugins/ztree/js/jquery.ztree.exedit-3.5.js"></script>
<!-- 引入ztree的js文件  end-->

<!-- 引入浮动显示的js文件	start -->
<script type="text/javascript" src="${ctx }/udmp/plugins/powerFloat/jquery-powerFloat.js"></script>
<!-- 引入浮动显示的js文件	end -->

<!-- 引入自动补全输入框控件的js文件	start -->
<script type="text/javascript" src="${ctx }/udmp/plugins/suggest/jquery.bigautocomplete.js"></script>
<!-- 引入自动补全输入框控件的js文件 	end -->
	
<!-- 引入通用功能的js文件	start -->
<script type="text/javascript" src="${ctx }/udmp/scripts/common.js" ></script>
<!-- 引入通用功能的js文件	end -->

<!-- 引入公共交易统一提交处理js文件	start -->
<script type="text/javascript" src="${ctx }/udmp/scripts/commonDeal.js" ></script>
<!-- 引入公共交易统一提交处理js文件	end -->

<!-- 大日历用到的js文件 --start-->
<script src="${ctx }/udmp/plugins/dateutil/js/coda.js" type="text/javascript"></script>
<!-- 大日历用到的js文件  --end-->

<!-- treetable使用的js文件 -- start -->
<script type="text/javascript" src="${ctx }/udmp/plugins/jquery-treetable-master/javascripts/src/jquery.treetable.js"></script>
<!-- treetable使用的js文件 -- end -->

<script type="text/javascript" src="${ctx }/udmp/plugins/ribbon/jquery.hplugins.js"></script>
<!-- 引入所有js文件 --end-->
<script src="${ctx}/udmp/dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>

<script type="text/javascript">
	var dwzEncode = "gbk";
	//定义上一次点击的时间
	var prevTime = new Date();
	//获取最后一次点击的时间
	var nextTime = 0;
	//获取session超时时间
	var sessionTimeOut = <%=(Integer) session.getMaxInactiveInterval()%> * 1000;
	//获取业务时间
	var bizTime = <%=(String) session.getAttribute("thisSysTime")%>;
	$(function() {
		DWZ.init("${ctx }/udmp/dwz/dwz.frag.xml", {
			loginUrl : "udmp/pages/login/openingLoginWindow.jsp",
			loginTitle : "登录", // 弹出登录对话框
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
		var toUrlparams = "<%=request.getParameter("toUrlParam")%>";
		var url;
		if(toUrlparams == 'null'){
			url = "<%=request.getParameter("toUrl")%>";
		} else {
			toUrlparams = toUrlparams.replaceAll("_","&");
			url = "<%=request.getParameter("toUrl")%>" + "?" + toUrlparams;
		}
		$.ajax({
			type : "get",
			async : false,
			url : url,
			cache : false,
			success : function(data) {
				$('#mainFrame').html(data);
				// 				$("table.table", document).jTable();
			}
		});
	});
</script>
</head>
<body scroll="no">
	<div id="layout">
		<div id="leftside" style="display: none">
			<div id="sidebar_s" style="width: 0px">
				<div class="collapse">
					<div class="toggleCollapse">
						<div></div>
					</div>
				</div>
			</div>
			<div id="sidebar" style="width: 0px">
				<div class="toggleCollapse">
					<h2>主菜单</h2>
					<div>收缩</div>
				</div>
				<div class="accordion" fillSpace="sidebar">${menuStr }</div>
			</div>
		</div>

		<div id="container">
			<div id="navTab" class="tabsPage">
				<div class="tabsPageHeader" style="display: none">
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
				<div class="navTab-panel tabsPageContent layoutBox">
					<div class="page unitBox" id="mainFrame">
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