<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!--
	自定义CSS样式：
-->

<style type="text/css">

/*设置此页面中所有table宽度均为100%。*/
table {
	width: 100%;
}

/*设置检索词输入框的固定宽度为150px。*/
dd input[type="text"] {
	width: 150px;
}
</style>

<script type="text/javascript">
	$(document).ready(function() {
		auditTypeInit();
	});

	function auditTypeInit() {
		$('#auditTypeDummy').val($('#auditType').val());
	}

	function doQuery() {
		auditTypeAssign();
		$('#queryForm').submit();
	}

	function auditTypeAssign() {
		$('#auditType').val($('#auditTypeDummy').val());
	}

	/**
	 * 为了避免JQuery将“.”作为对象操作来处理（如batchAuditLogVO.auditType），故将.替换为\.。
	 * @method trans(str)
	 * @param {String} str 
	 * @return {String} str
	 */
	function trans(str) {
		return str.replace(".", "\\.");
	}
	 
</script>

<div class="pageFormContent">

	<!-- ------------------------------- 概览页面批处理审计查询内容开始 -------------------------------   -->

	<form id="queryForm"
		action="/ls/batch-audit/outline_batchAuditLogAction.action?leftFlag=1&amp;menuId=${menuId }&initPager=yes"
		method="post" onsubmit="return navTabSearch(this);">
		<div class="panel">
			<h1>批处理审计查询</h1>
			<div>
				<dl>
					<dt>审计对象类型</dt>
					<dd>
						<select name="batchAuditLogVO.auditTypeDummy" id="auditTypeDummy">
							<option value="">请选择</option>
							<option value="param">系统参数_param</option>
							<option value="agent">代理_agent</option>
							<option value="job">任务_job</option>
							<option value="task">作业_task</option>
						</select> <input type="hidden" name="batchAuditLogVO.auditType"
							id="auditType" value="${batchAuditLogVO.auditType}" />
					</dd>
				</dl>
				<dl>
					<dt>审计日志记录日期</dt>
					<dd>
						<input id="operationTime" type="text" class="date" name="batchAuditLogVO.operationTime"   
						value="<s:date format='yyyy-MM-dd' name='batchAuditLogVO.operationTime' />" />
					</dd>
				</dl>
				<dl>
					<dt>操作用户</dt>
					<dd>
						<input type="text" name="batchAuditLogVO.userId"
							value="${batchAuditLogVO.userId}" />
					</dd>
				</dl>
			</div>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" id="queryButton"
								onclick="javascript:doQuery();">查询</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>

	<!-- ------------------------------- 概览页面批处理审计查询内容结束 -------------------------------  -->


	<!-- ------------------------------- 概览页面批处理审计列表内容开始 ------------------------------   -->


	<div class="panel">
		<h1>批处理审计列表</h1>

		<div>
			<form>
				<table class="list">
					<thead>
						<tr>
							<th>审计对象类型</th>
							<th>对象ID</th>
							<th>操作类型</th>
							<th>审计上下文</th>
							<th>操作人员ID</th>
							<th>操作人员IP</th>
							<th>操作时间</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="currentPage.pageItems" status="st">
							<tr>
								<td>${auditType}</td>
								<td>${objectId}</td>
								<td>${operationType}</td>
								<td>${auditContext}</td>
								<td>${userId}</td>
								<td>${userIp}</td>
								<td><s:date format='yyyy-MM-dd hh:mm:ss' name='operationTime' /></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</form>
		</div>

		<!-- ------------------------------- 概览页面批处理审计列表内容结束 ------------------------------   -->

	</div>

	<!-- ------------------------------- 批处理审计页面分页内容开始            ------------------------------   -->

	<form id="pagerForm" method="post"
		action="/ls/batch-audit/outline_batchAuditLogAction.action?menuId=${menuId }">
		<input type="hidden" name="pageNum" value="${currentPage.pageNo}" />
		<input type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
	</form>

	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<s:select list="#{10:'10',20:'20',50:'50',100:'100',200:'200'}"
				name="select" onchange="navTabPageBreak({numPerPage:this.value})"
				value="currentPage.pageSize">
			</s:select>
			<span>条，共${currentPage.total}条</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${currentPage.total}"
			numPerPage="${currentPage.pageSize}" pageNumShown="10"
			currentPage="${currentPage.pageNo}"></div>
	</div>

	<!-- ------------------------------- 批处理审计页面分页内容结束            ------------------------------   -->
</div>
