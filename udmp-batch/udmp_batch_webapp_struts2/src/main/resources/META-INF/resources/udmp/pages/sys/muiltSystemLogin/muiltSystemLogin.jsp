<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.git.com.cn/taglib/udmp/field" prefix="Field"%>
<s:set var="ctx">${pageContext.request.contextPath}</s:set>
<link href="${ctx}/udmp/plugins/btn/button.css" rel="stylesheet"
	type="text/css" media="screen" />
<script>
	$(".btn40").click(
			function() {
				var href = $(this).attr("href") + "?userName="
						+ $("#userName").val() + "&&flag=auto";
				window.open(href);
			});
</script>
<div class="pageContent" layoutH="1px;">
	<div class="d1"
		style="text-align: center; background-color: #E7EEF0; width: 100%; height: 100%;">
		<h1>统一测试集成功能</h1>
		<p style="padding-top:20px;"><label for="userName" style="font-size: 18px;">用户名：</label><input type="text" id="userName"
			value="SYSADMIN" style="font-size: 20px;"/></p><br />
		<s:iterator value="serviceEnvVOs" status="st">
			<input type="button" class="btn40"
				href="http://${serviceIp}:${servicePort}/${serviceContext}/main.action"
				value="<Field:codeValue tableName="t_udmp_sub_system_info" value="${systemId }"/>" />
		</s:iterator>
	</div>
</div>


