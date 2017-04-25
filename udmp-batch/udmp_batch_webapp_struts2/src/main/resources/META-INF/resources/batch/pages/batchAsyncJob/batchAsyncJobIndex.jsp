<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
	function resetAll() {
		$('#asyncJobName').attr('value', '');
	}
</script>
<form id="pagerForm" method="post"
	action="batchAsyncJob/showBatchAsyncJob_batchAsyncJobAction.action?leftFlag=0&menuId=${menuId}">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" /> <input
		type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>
<div class="pageHeader">
	<form
		action="batchAsyncJob/showBatchAsyncJob_batchAsyncJobAction.action?leftFlag=0&menuId=${menuId}"
		method="post" name="batchAsyncJobForm" id="batchAsyncJobForm"
		class="pageForm required-validate"
		onsubmit="return navTabSearch(this);" rel="pagerForm">
		<div class="panel">
			<h1>异步任务查询条件</h1>
			<div>
				<div class="searchBar">
					<ul class="searchContent">
						<input type="hidden" id="asyncJobTypeHidden" value="A"
							name="batchAsyncJobTVO.jobType">
							<input type="hidden" id="asyncJobDeletedHidden" value="0"
							name="batchAsyncJobTVO.isDeleted">
						<li><label>任务名称:</label> <input id="asyncJobName" type="text"
							name="batchAsyncJobTVO.jobName" size="15" maxlength="50"
							value="${batchAsyncJobTVO.jobName}" /></li>

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
					<li><a class="edit"
						href="batchAsyncJob/showBatchAsyncJobDetailPage_batchAsyncJobAction.action?batchAsyncJobVO.batchAsyncJobId={uid}&menuId=${menuId}"
						maxable="false" resizable="false" minable="false" title="异步任务启动"
						width="750" height="450" rel="update" target="dialog"><span>异步任务启动</span></a>
					</li>
				</ul>
			</div>
			<table class="table" width="100%" layoutH="225">
				<thead>
					<tr height="25" align="center">
						<th width="10%">选择</th>
						<th width="30%">任务名称</th>
						<th width="20%">任务状态</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="currentPage.pageItems" status="st">
						<tr align="center" height="25" target="uid" rel="${jobId}">
							<td><input type="radio" name="jobId" value="${jobId}" /></td>
							<td align="center">${jobName}</td>
							<td align="center"><s:if test="isEnable==0">停用</s:if> <s:elseif
									test="isEnable==1">启用</s:elseif></td>
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
