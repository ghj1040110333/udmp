<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="http://www.git.com.cn/taglib/udmp/dict" prefix="dict" %>
<script type="text/javascript">
	function resetAll() {
		$('#agentname').attr('value', '');		
		$('#isEnable').val("");
		$("#agentSystem").val("");
	}
</script>
<script language="javascript" for="window" event="onload">

if (document.readyState == "complete") {
	$("#agentSystem").val($("#agentSystemHidden").val());
	$("#isEnable").val($("#isEnableHidden").val());
}
</script>
<form id="pagerForm" method="post"
	action="batchAgent/showBatchAgent_batchAgentAction.action?leftFlag=0&menuId=${menuId}">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" /> 
	<input type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>
<div class="pageHeader">
	<form
		action="batchAgent/showBatchAgent_batchAgentAction.action?leftFlag=0&menuId=${menuId}"
		method="post" name="batchAgentForm" id="batchAgentForm"
		class="pageForm required-validate"
		onsubmit="return navTabSearch(this);" rel="pagerForm">
		<div class="panel">
			<h1>参数查询条件</h1>
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label>执行代理名称:</label> 				
					<input id="agentname" name="batchAgentTVO.agentName" type="text" size="15" maxlength="200" value="${batchAgentTVO.agentName}" />
					<input type="hidden" id="deleteHidden" name="batchAgentTVO.isDeleted" value="0">
				</li>
				<li>
					<label>所属系统:</label> 
					<input type="hidden" id="agentSystemHidden" value="${batchAgentTVO.agentSystem}">					
<%-- 					<dict:select cssClass="combox" codeType="dept_positions" id="agentSystem" name="batchAgentTVO.agentSystem" /> --%>
					<input id="agentSystem" name="batchAgentTVO.agentSystem" type="text" size="15" maxlength="200" value="${batchAgentTVO.agentSystem}" />
				</li>
				<li>
					<label>是否启用：</label>
					<input type="hidden" id="isEnableHidden" value="${batchAgentTVO.isEnable}">
						<select cssClass="combox" id="isEnable" name="batchAgentTVO.isEnable" value="${batchAgentTVO.isEnable}">
							<option value="">全部</option>
							<option value="0">否</option>
							<option value="1">是</option>
						</select>
				</li>
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
	</form>
</div>
<div class="pageContent">
	<div class="panel collapse">
	<h1>查询结果</h1><div>
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="batchAgent/showInsertBatchAgentPage_batchAgentAction.action?menuId=${menuId}"
				maxable="false" minable="false" resizable="false" title="新增代理"
				width="600" height="250" target="dialog" rel="add"><span>新增</span></a></li>
			<li class="line">line</li>
			<li><a class="edit"	href="batchAgent/showUpdateBatchAgentPage_batchAgentAction.action?batchAgentVO.batchAgentId={uid}&menuId=${menuId}"
				maxable="false" resizable="false" minable="false" title="修改代理"
				width="600" height="250" rel="update" target="dialog"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" href="batchAgent/deleteBatchAgent_batchAgentAction.action?batchAgentVO.batchAgentId={uid}&menuId=${menuId}"
				target="ajaxTodo" title="是否确认要删除"><span>删除</span></a>
			</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="225">
		<thead>
			<tr height="25" align="center">
				<th width="5%">选择</th>
				<th>代理名称</th>
				<th>IP地址</th>
				<th>端口号</th>
				<th>线程阀值</th>
				<th>所属系统</th>
				<th>状态</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="currentPage.pageItems" status="st">
				<tr align="center" height="25" target="uid" rel="${agentId}">
					<td align="center">
						<input type="radio" name="agentId" value="${agentId}" />
					</td>
					<td align="center">${agentName}</td>
					<td align="center">${agentIp}</td>
					<td align="center">${agentPort}</td>					
					<td align="center">${agentThreadLimitCnt}</td>
					<td align="center">
						<s:if test="agentSystem==\"UDMP\"">UDMP</s:if>
						<s:elseif test="agentSystem==\"PA\"">保单管理</s:elseif>
						<s:elseif test="agentSystem==\"NB\"">新契约</s:elseif>
						<s:elseif test="agentSystem==\"UW\"">核保</s:elseif>
						<s:elseif test="agentSystem==\"CLM\"">理赔</s:elseif>
						<s:elseif test="agentSystem==\"CS\"">保全</s:elseif>
						<s:elseif test="agentSystem==\"CSS\"">柜面</s:elseif>
						<s:elseif test="agentSystem==\"CAP\"">收付费</s:elseif>
						<s:elseif test="agentSystem==\"MMS\"">营销支持</s:elseif>
						<s:elseif test="agentSystem==\"ECS\"">影像采集</s:elseif>	
						<s:elseif test="agentSystem==\"BPM\"">工作流</s:elseif>
						<s:else>${agentSystem}</s:else>
					</td>
					<td align="center">
						<s:if test="isEnable==0">停用中</s:if>
	       				<s:elseif test="isEnable==1">启用中</s:elseif>
					</td>				
			
				</tr>
			</s:iterator>
		</tbody>
	</table>
	</div>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<s:select list="#{20:'20',50:'50',100:'100',200:'200'}" name="select"
				onchange="navTabPageBreak({numPerPage:this.value})"
				value="currentPage.pageSize">
			</s:select>
			<span>条，共${currentPage.total}条</span>
		</div>
		<div class="pagination" targetType="navTab"
			totalCount="${currentPage.total}"
			numPerPage="${currentPage.pageSize}" pageNumShown="10"
			currentPage="${currentPage.pageNo}">
		</div>
	</div>
</div>
