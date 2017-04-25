<%@page  language="java"  contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<script type="text/javascript">
	/**参数新增页面提交*/
	function resetAll() {
		$("input").attr("value", "");//input框置空
		$("#paramTypeSelect").val("");//删除标记恢复为全部
	}
</script>
<script language="javascript" for="window" event="onload">
	if (document.readyState == "complete") {
		$("#paramTypeSelect").val($("#paramTypeHidden").val());
	}
</script>

<form id="pagerForm" method="post"
	action="batchParam/showBatchParam_batchParamAction.action">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" />
	<input type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>

<div class="pageHeader">
	<form id="paramManageForm"
		action="batchParam/showBatchParam_batchParamAction.action?leftFlag=0&menuId=${menuId}"
		rel="pagerForm" method="post" class="pageForm required-validate"
		onsubmit="return navTabSearch(this);">
		<div class="panel">
			<h1>参数查询条件</h1>
			<div>
				<div class="searchBar">
					<ul class="searchContent">
						<li><label>参数名称:</label> <input id=""
							name="batchParamTVo.paramName" type="text" size="15"
							maxlength="10" value="${batchParamTVo.paramName}" /></li>
						<input type="hidden" id="deleteHidden" name="batchParamTVo.isDeleted" value="0">
						<li><label>参数类型:</label> <input type="hidden"
							id="paramTypeHidden" value="${batchParamTVo.paramType}">
								<select cssClass="combox" id="paramTypeSelect"
								name="batchParamTVo.paramType" value="${batchParamTVo.paramType}">
									<option value="">全部</option>
									<option value="S">系统参数</option>
									<option value="B">业务参数</option>
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
						href="batchParam/showInsertBatchParamPage_batchParamAction.action?menuId=${menuId}"
						maxable="false" minable="false" title="参数新增" width="800"
						height="450" target="dialog" rel="add"><span>新增</span></a></li>
					<li class="line">line</li>
					<li><a class="edit"
						href="batchParam/showUpdateBatchParamPage_batchParamAction.action?paramVo.paramId={pid}&menuId=${menuId}"
						maxable="false" minable="false" title="参数修改" width="800"
						height="450" rel="update" target="dialog"><span>修改</span></a></li>
					<li class="line">line</li>
					<li><a class="delete"
						href="batchParam/delete_batchParamAction.action?paramVo.paramId={pid}&menuId=${menuId}"
						target="ajaxTodo" title="是否确认要删除"><span>删除</span></a></li>
					<li class="line">line</li>
				</ul>
			</div>

			<table class="table" width="100%" layoutH="225">
				<thead>
					<tr align="center">
						<th width="5%">选择</th>
						<th width="15%">参数ID</th>
						<th width="15%">参数名称</th>
						<th width="15%">参数代码</th>
						<th width="15%">参数类型</th>
						<th width="15%">数据类型</th>
						<th width="20%">参数值</th>
					</tr>
				</thead>
				<tbody>
					<s:iterator value="currentPage.pageItems" status="st">
<%-- 					<s:iterator value="currentPage.valueList" status="st"> --%>
						<tr height="25" target="pid" rel="${paramId}">
							<td align="center"><input name="paramId" type="radio"
								value="${paramId}" /></td>
							<td align="center">${paramId}</td>
							<td align="center">${paramName}</td>
							<td align="center">${paramCode}</td>
							<td align="center">
								<s:if test="paramType==\"B\"">业务参数</s:if>
								<s:elseif test="paramType==\"S\"">系统参数</s:elseif>
							</td>
							<td align="center">
								<s:if test="paramDataType==\"date\"">日期</s:if>
								<s:elseif test="paramDataType==\"string\"">字符串</s:elseif>
								<s:elseif test="paramDataType==\"number\"">数值</s:elseif>
							</td>
							<td align="center">${paramValue}</td>
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