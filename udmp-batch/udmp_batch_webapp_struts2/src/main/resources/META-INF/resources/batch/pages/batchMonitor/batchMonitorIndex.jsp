<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- <%@ taglib uri="http://www.git.com.cn/taglib/udmp/field" prefix="Field" %> --%>
<script type="text/javascript">
	function resetAll() {
		$('#jobName').attr('value', '');
		$('#jobChainRunId').attr('value', '');
		$('#statusSelect').val("");
		$('#processDateStart').val("");
		$('#processDateEnd').val("");
	}
</script>
<script language="javascript" for="window" event="onload">
	if (document.readyState == "complete") {
		$("#processDateStart").val($("#processDateStartHidden").val());
		$("#processDateEnd").val($("#processDateEndHidden").val());
		$("#statusSelect").val($("#statusHidden").val());
		$("#jobTypeSelect").val($("#jobTypeHidden").val());
		
	}
</script>
<form id="pagerForm" method="post"
	action="batchMonitor/showBatchMonitor_batchMonitorAction.action?leftFlag=0&menuId=${menuId}">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" /> <input
		type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>
<div class="pageHeader">
	<form
		action="batchMonitor/showBatchMonitor_batchMonitorAction.action?leftFlag=0&menuId=${menuId}"
		method="post" name="batchMonitorForm" id="batchMonitorForm"
		class="pageForm required-validate"
		onsubmit="return navTabSearch(this);" rel="pagerForm">
		<div class="panel">
			<h1>监控查询条件</h1>
			<div>
				<div class="searchBar">
					<table width="80%">
						<tr height="25">
							<td width="10%">任务名称:</td>
							<td width="10%"><input id="jobName"
								name="batchJobRunTVO.jobName" type="text" size="15"
								maxlength="50" value="${batchJobRunTVO.jobName}" /></td>
							<td width="5%">状态:</td>
							<td width="10%">
							<%-- <input type="hidden" id="statusHidden" value="${batchJobRunTVO.status}" /> --%>
<!-- 								<Field:codeTable id="statusSelect" name="batchJobRunTVO.status"  
									tableName="T_UDMP_BATCH_PARAM_SUB" whereClause="PARAM_ID=1" nullOption="true" disabled="false"/> -->
								 <input type="text" name="batchJobRunTVO.status" maxlength="50"	id="batchJobRunTVO.status" value="${batchJobRunTVO.status}" />
								</td>
							<td width="10%">任务类型:</td>
							<td width="10%">
							<input type="hidden"
							id="jobTypeHidden" value="${batchJobRunTVO.jobType}">
							<select
							id="jobTypeSelect" value="${batchJobRunTVO.jobType}" name="batchJobRunTVO.jobType" cssClass="combox">
								<option value="A">手动任务</option>
								<option value="S">定时任务</option>
							</select>
							</td>
						</tr>
						<tr>
							<td width="10%">任务链ID:</td>
							<td width="10%"><input id="jobChainRunId"
								name="batchJobRunTVO.jobChainRunId" type="text" size="15"
								maxlength="50" value="${batchJobRunTVO.jobChainRunId}" /></td>
							<td width="10%">处理时间区间:</td>
							<td width="15%"><input type="hidden" id="processDateStartHidden"
								value="<s:date name="batchJobRunTVO.processDateStart" format="yyyy-MM-dd HH:mm:ss" />">
								<input type="expandDateYMDHmsRO" id="processDateStart"
								name="batchJobRunTVO.processDateStart" /></td>
							<td width="5%">~</td>
							<td width="15%"><input type="hidden" id="processDateEndHidden"
								value="<s:date name="batchJobRunTVO.processDateEnd" format="yyyy-MM-dd HH:mm:ss" />">
								<input type="expandDateYMDHmsRO" id="processDateEnd"
								name="batchJobRunTVO.processDateEnd" /></td>
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
						href="batchMonitor/showBatchMonitorDetailPage_batchMonitorAction.action?batchJobRunVO.jobRunId={uid}&menuId=${menuId}"
						maxable="false" minable="false" title="详细信息"
						width="1050" height="650" target="dialog"><span>详细信息</span></a>
					</li>
					<li><a class="delete"
						href="batchMonitor/failReboot_batchMonitorAction.action?batchJobRunVO.jobRunId={uid}&menuId=${menuId}"
						maxable="false" minable="false" title="是否确定失败重跑"
						width="1050" height="650" target="ajaxTodo"><span>失败重跑</span></a>
					</li>
				</ul>
			</div>
			<table class="table" width="100%" layoutH="250">
				<thead>
					<tr height="25" align="center">
						<th width="5%">选择</th>
						<th width="10%">任务链ID</th>						
						<th width="5%">批次</th>		
						<th width="5%">代理ID</th>		
						<th width="15%">任务名称</th>
						<th width="15%">处理时间</th>						
						<th width="10%">是否拆分</th>
						<th width="10%">成功记录数</th>
						<th width="10%">失败记录数</th>
						<th width="5%">总记录数</th>
						<th width="10%">状态</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="currentPage.pageItems" status="st">
						<tr align="center" height="25" target="uid" rel="${jobRunId}">
							<td align="center"><input type="radio" name="jobRunId"
								value="${jobRunId}" /></td>
							<td align="center">${jobChainRunId}</td>
							<td align="center">${jobChainBatch}</td>
							<td align="center">${agentId}</td>
							<td align="center">${jobName}</td>
							<td align="center"><s:date name="processDate"
									format="yyyy-MM-dd HH:mm:ss" /></td>
							<td align="center"><s:if test="isSplit==0">否</s:if> <s:elseif
									test="isSplit==1">是</s:elseif></td>
							<td align="center">${successCnt}</td>
							<td align="center">${failedCnt}</td>
							<td align="center">${totalCnt}</td>
							<td align="center">
								<s:if test="status==0">
									<div  style="color:#3c763d">成功</div>
								</s:if> 
								<s:elseif test="status==1">
									<div  style="color:#FF0000">失败</div>
								</s:elseif> 
								<s:elseif test="status==2">
									<div  style="color:#FFFF00">部分成功</div>
								</s:elseif>
								<s:elseif test="status==3">
									<div  style="color:#000000">未启动</div>
								</s:elseif>
								<s:elseif test="status==4">
									<div  style="color:#0066FF">运行中</div>
								</s:elseif></td>
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