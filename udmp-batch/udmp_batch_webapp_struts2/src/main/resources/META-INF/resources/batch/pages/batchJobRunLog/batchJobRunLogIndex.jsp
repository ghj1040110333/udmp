<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- <%@ taglib uri="http://www.git.com.cn/taglib/udmp/field" prefix="Field" %> --%>
<script type="text/javascript">
	function resetAll() {
		$("#logType").attr('value', '');
		$('#logLevel').attr('value', '');
		$('#logTimeStart').attr('value', '');
		$('#logTimeEnd').attr('value', '');

	}
</script>
<script language="javascript" for="window" event="onload">
	if (document.readyState == "complete") {
		$("#logLevel").val($("#levelInput").val());
		$("#logType").val($("#typeInput").val());
		$("#logTimeStart").val($("#startTime").val());
		$("#logTimeEnd").val($("#endTime").val());
	}
</script>
<form id="pagerForm" method="post"
	action="batchJobRunLog/showBatchJobRunLog_batchJobRunLogAction.action?leftFlag=0&menuId=${menuId}">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" /> <input
		type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>
<div class="pageHeader">
	<form
		action="batchJobRunLog/showBatchJobRunLog_batchJobRunLogAction.action?leftFlag=0&menuId=${menuId}"
		method="post" name="batchJobRunLogForm" id="batchJobRunLogForm"
		class="pageForm required-validate"
		onsubmit="return navTabSearch(this);" rel="pagerForm">
		<div class="panel">
			<h1>日志查询条件</h1>
			<div>
				<div class="searchBar">
					<table width="80%">
						<tr height="25">
							<td width="5%">日志类型:</td>
							<td width="10%">
								<input type="hidden" id="typeInput" value="${batchJobRunLogTVO.logType}"> 
<!-- 							<Field:codeTable id="logType" name="batchJobRunLogTVO.logType"  
									tableName="T_UDMP_BATCH_PARAM_SUB" whereClause="PARAM_ID=3" nullOption="true" disabled="false"/> -->
								<input type="text" name="batchJobRunLogTVO.logType" maxlength="30"/> 
							</td>
							<td width="5%">日志级别:</td>
							<td width="10%">
								<input type="hidden" id="levelInput" value="${batchJobRunLogTVO.logLevel}">
<%-- 								<Field:codeTable id="logLevel" name="batchJobRunLogTVO.logLevel"  value="${batchJobRunLogTVO.logLevel}"  
									tableName="T_UDMP_BATCH_PARAM_SUB" whereClause="PARAM_ID=2" nullOption="true" disabled="false"/> --%>
								<input type="text" name="batchJobRunLogTVO.logLevel" maxlength="30"/>
							</td>
							<td width="10%">记录时间:</td>
							<td width="15%"><input type="hidden" id="startTime" value="<s:date name="batchJobRunLogTVO.logTimeStart" format="yyyy-MM-dd HH:mm:ss" />">
								<input type="expandDateYMDHmsRO" id="logTimeStart" name="batchJobRunLogTVO.logTimeStart" /></td>
							<td width="5%">~</td>
							<td width="15%"><input type="hidden" id="endTime" value="<s:date name="batchJobRunLogTVO.logTimeEnd" format="yyyy-MM-dd HH:mm:ss" />">
							<input type="expandDateYMDHmsRO" id="logTimeEnd" name="batchJobRunLogTVO.logTimeEnd" /></td>
						</tr>
					</table>

					<div class="subBar">
						<ul>
							<li>
								<div class="buttonActive">
									<div class="buttonContent">
										<button type="submit">查询</button>
									</div>
								</div>
							</li>
							<li>
								<div class="buttonActive">
									<div class="buttonContent">
										<button type="button" onclick="resetAll();">重置</button>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>

<div class="pageContent">
	<div class="panel collapse">
		<h1>查询结果</h1>
		<div>
			<div class="panelBar">
				<ul class="toolBar">
					<li><a class="edit"
						href="batchJobRunLog/showBatchJobRunLogDetailPage_batchJobRunLogAction.action?batchJobRunLogVO.batchJobRunLogId={uid}&menuId=${menuId}"
						maxable="false" minable="false" title="详细信息" width="600"
						height="350" rel="update" target="dialog"><span>详细信息</span></a></li>
				</ul>
			</div>
			<table class="table" width="100%" layoutH="225">
				<thead>
					<tr height="25" align="center">
						<th width="5%">选择</th>
						<th width="20%">日志ID</th>
						<th width="20%">运行实例编号</th>
						<th width="20%">日志类型</th>
						<th width="20%">日志级别</th>
						<th width="15%">记录日期</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="currentPage.pageItems" status="st">
						<tr align="center" height="25" target="uid" rel="${jobRunLogId}">
							<td><input type="radio" name="batchJobRunLogManager"
								value="" /></td>
							<td align="center">${jobRunLogId}</td>
							<td align="center">${jobRunId}</td>
							<td align="center"><s:if test="logType==0">0</s:if> <s:elseif
									test="logType==1">1</s:elseif> <s:elseif test="logType==2">2</s:elseif></td>
							<td align="center"><s:if test="logLevel==0">Debug</s:if> <s:elseif
									test="logLevel==1">Info</s:elseif> <s:elseif test="logLevel==2">Error</s:elseif>
								<s:elseif test="logLevel==3">Fatal</s:elseif></td>
							<td align="center"><s:date name="logTime"
									format="yyyy-MM-dd HH:mm:ss" /></td>
						</tr>
					</s:iterator>
				</tbody>
			</table>
			<div class="panelBar">
				<div class="pages">
					<span>显示</span>
					<s:select list="#{20:'20',50:'50',100:'100',200:'200'}"
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
		</div>
	</div>
</div>

