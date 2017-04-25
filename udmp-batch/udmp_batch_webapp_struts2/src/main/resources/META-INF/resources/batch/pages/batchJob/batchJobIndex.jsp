<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
	function resetAll() {
		$('#jobName').attr('value', '');
		$('#jobTypeSelect').attr('value', '');
	}
</script>
<script language="javascript" for="window" event="onload">
	if (document.readyState == "complete") {
		$("#jobTypeSelect").val($("#jobTypeHidden").val());
	}
</script>
<form id="pagerForm" method="post"
	action="batchJob/showBatchJob_batchJobAction.action?leftFlag=0&menuId=${menuId}">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" /> <input
		type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>
<div class="pageHeader">
	<form
		action="batchJob/showBatchJob_batchJobAction.action?leftFlag=0&menuId=${menuId}"
		method="post" name="batchJobform" id="batchJobform"
		class="pageForm required-validate"
		onsubmit="return navTabSearch(this);" rel="pagerForm">
		<div class="panel">
			<h1>任务查询条件</h1>
			<div>
				<div class="searchBar">
					<ul class="searchContent">
						<li><label>任务名称:</label> <input id="jobName" type="text"
							name="batchJobTVO.jobName" size="15" maxlength="50"
							value="${batchJobTVO.jobName}" />
							<input type="hidden" name="batchJobTVO.isDeleted" value="0" />
						</li>
						<li><label>任务类型:</label> <input type="hidden"
							id="jobTypeHidden" value="${batchJobTVO.jobType}"> <select
							id="jobTypeSelect" name="batchJobTVO.jobType" cssClass="combox">
								<option value="">全部</option>
								<option value="S">定时任务</option>
								<option value="A">异步任务</option>
						</select></li>
					</ul>
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
					<li><a class="add"
						href="batchJob/showInsertBatchJobPage_batchJobAction.action?menuId=${menuId}"
						maxable="false" minable="false" resizable="false" minable="false"
						title="新增任务" width="800" height="450" target="dialog" rel="add"><span>新增</span></a>
					</li>
					<li class="line">line</li>
					<li><a class="edit"
						href="batchJob/showUpdateBatchJobPage_batchJobAction.action?batchJobVO.batchJobId={uid}&menuId=${menuId}"
						maxable="false" resizable="false" minable="false" title="修改任务"
						width="800" height="450" rel="update" target="dialog"><span>修改</span></a>
					</li>
					<li class="line">line</li>
					<li><a class="delete"
						href="batchJob/deleteBatchJob_batchJobAction.action?batchJobVO.batchJobId={uid}&menuId=${menuId}"
						target="ajaxTodo" title="是否确认要删除"><span>删除</span></a></li>
				</ul>
			</div>
			<table class="table" width="100%" layoutH="225">
				<thead>
					<tr height="25" align="center">
						<th width="10%">选择</th>
						<th width="30%">任务名称</th>
						<th width="20%">任务类型</th>
						<th width="20%">任务状态</th>
						<th width="20%">手工启动</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="currentPage.pageItems" status="st">
						<tr align="center" height="25" target="uid" rel="${jobId}">
							<td><input type="radio" name="jobId" value="${jobId}" /></td>
							<td align="center">${jobName}</td>
							<td align="center"><s:if test="jobType==\"S\"">定时任务</s:if> <s:elseif
									test="jobType==\"A\"">异步任务</s:elseif></td>
							<td align="center"><s:if test="isEnable==0">停用</s:if> <s:elseif
									test="isEnable==1">启用</s:elseif></td>
							<td align="center"><s:if test="isAllowManu==0">否</s:if> <s:elseif
									test="isAllowManu==1">是</s:elseif></td>
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
