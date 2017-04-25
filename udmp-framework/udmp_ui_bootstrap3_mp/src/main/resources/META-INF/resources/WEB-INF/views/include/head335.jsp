<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<script src="${ctxStatic}/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/bootstrap/3.3.5/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap/3.3.5/js/bootstrap.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/bootstrap-table/bootstrap-table.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap-table/bootstrap-table.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap-table/locale/bootstrap-table-zh-CN.min.js" type="text/javascript"></script>
<link href="${ctxStatic}/bootstrap-validator/css/bootstrapValidator.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap-validator/js/bootstrapValidator.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap-validator/js/language/zh_CN.js" type="text/javascript"></script>
<link href="${ctxStatic}/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/bootstrap-datetimepicker/js/bootstrap-datetimepicker.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/bootstrap-datetimepicker/js/locales/bootstrap-datetimepicker.zh-CN.js" type="text/javascript"></script>
<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
	<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
	<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<link href="${ctxStatic}/jquery-select2/3.4/select2.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-select2/3.4/select2.min.js" type="text/javascript"></script>
<%-- <link href="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.css" type="text/css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-validation/1.11.0/jquery.validate.min.js" type="text/javascript"></script> --%>
<link href="${ctxStatic}/jquery-jbox/2.3/Skins/Bootstrap/jbox.min.css" rel="stylesheet" />
<script src="${ctxStatic}/jquery-jbox/2.3/jquery.jBox-2.3.min.js" type="text/javascript"></script>
<%--<script src="${ctxStatic}/My97DatePicker/WdatePicker.js" type="text/javascript"></script>--%>
<script src="${ctxStatic}/common/mustache.min.js" type="text/javascript"></script>
<script src="${ctxStatic}/common/jeesite.min.js" type="text/javascript"></script>
<link href="${ctxUdmp}/css/common.css" type="text/css" rel="stylesheet" />
<script src="${ctxUdmp}/js/common.js" type="text/javascript"></script>
<link href="${ctxUdmp}/css/git-style.css" type="text/css" rel="stylesheet" />
<script src="${ctxUdmp}/js/git-common.js" type="text/javascript"></script>
<script src="${ctxUdmp}/js/git-ui.js" type="text/javascript"></script>
<c:set var="tabmode" value="${empty cookie.tabmode.value ? '1' : cookie.tabmode.value}" />
<script type="text/javascript">
var ctx = '${ctx}', ctxStatic = '${ctxStatic}';
var enableTabPage = '${tabmode}' == '1';
</script>