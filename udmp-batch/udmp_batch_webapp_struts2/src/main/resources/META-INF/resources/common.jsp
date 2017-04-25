<%@page  language="java"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- <%@ taglib uri="http://www.git.com.cn/taglib/udmp/form" prefix="gitForm"%> --%>
<s:set var="ctx">${pageContext.request.contextPath}</s:set>

<!-- 引入所有css文件 --start -->
<link href="${ctx}/udmp/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/udmp/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
<link href="${ctx}/udmp/dwz/themes/other/other.css" rel="stylesheet" type="text/css" media="screen"/>
<link rel="stylesheet" href="${ctx }/udmp/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="${ctx }/udmp/plugins/ztree/css/demo.css" type="text/css">
<link rel="stylesheet" href="${ctx }/udmp/plugins/powerFloat/powerFloat.css" type="text/css">
<link rel="stylesheet" href="${ctx }/udmp/plugins/suggest/jquery.bigautocomplete.css" type="text/css">
<link rel="stylesheet" href="${ctx }/udmp/plugins/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
<link rel="stylesheet" href="${ctx }/udmp/plugins/powerFloat/common.css" type="text/css">
<link rel="stylesheet" href="${ctx }/udmp/plugins/powerFloat/powerFloat.css" type="text/css">
<link rel="stylesheet" href="${ctx }/udmp/plugins/dateutil/css/master.css" type="text/css" media="screen"/>
<link rel="stylesheet" href="${ctx }/udmp/plugins/jquery-treetable-master/stylesheets/jquery.treetable.css" media="screen"/>
<link rel="stylesheet" href="${ctx }/udmp/plugins/jquery-treetable-master/stylesheets/screen.css" media="screen"/>
<link rel="stylesheet" href="${ctx }/udmp/plugins/jquery-treetable-master/stylesheets/jquery.treetable.theme.default.css" media="screen"/>
<link href="${ctx}/udmp/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen" />
<link href="${ctx}/udmp/images/logo/favicon.ico" type="image/x-icon" rel="icon">
<link href="${ctx}/udmp/images/logo/favicon.ico" type="image/x-icon" rel="shortcut icon">

<!-- 请在此处引用新添加的样式文件 -->
<!-- 引入所有css文件 --start -->

<!-- 引入所有js文件 --start-->

<!-- 引入通用功能的js文件	start -->
<script type="text/javascript" src="${ctx }/udmp/scripts/common.js" ></script>
<!-- 引入通用功能的js文件	end -->

<!-- 引入dwz的js文件 --start -->
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
<script src="${ctx}/udmp/dwz/js/dwz.ui.js" type="text/javascript"></script>
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
<script src="${ctx}/udmp/plugins/ribbon/jquery.checkorgantree.js" type="text/javascript"></script>
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

<title>新华核心系统</title>