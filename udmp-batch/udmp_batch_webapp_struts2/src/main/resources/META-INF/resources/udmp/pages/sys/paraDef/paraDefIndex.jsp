<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">	
	function resetAll(){
		$("#paraName").attr('value','');
		selectMyComBox("scopeCode","0");
	}
</script>
<form id="pagerForm" method="post"
	action="sys/queryParaDef_paraDefAction.action?leftFlag=0">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" />
	<input type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>
<div class="pageHeader">
	<form action="sys/queryParaDef_paraDefAction.action?leftFlag=0" rel="pagerForm" method="post" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
		<div class="panel">
		<h1>查询条件</h1><div>
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label for="paraName">参数名称:</label>
					<input id="paraName" name="paraDefTVO.paraName" type="text" size="15" maxlength="50" value="${paraDefTVO.paraName }" title="支持模糊查询" />
				</li>
				<li>
					<label>参数范围:</label>
					<s:select cssClass="combox" id="scopeCode" name="paraDefTVO.scopeCode" list="paraScopeMap" listKey="key" listValue="value" 
							headerKey="0" headerValue="全部">
					</s:select>
				</li>
				<li>
					<input type="hidden" name="pageNum" value="1" />
					<input type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
				</li>
			</ul>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive" >
							<div class="buttonContent">
								<button type="submit" id="paraDefIndexQuery" >查询</button>
							</div>
						</div>
					</li>
					<li>
						<div class="buttonActive" >
							<div class="buttonContent">
								<button type="button" id="paraDefIndexReset" onclick="resetAll();">重置</button>
							</div>
						</div>
					</li>
				</ul>
			</div>
		</div>
		</div></div>
	</form>
</div>
<div class="pageContent">
<div class="panel collapse">
	<h1>查询结果</h1><div>
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add" href="sys/showInsertParaPage_paraDefAction.action?addParaFlag=add&menuId=${menuId }" 
			maxable="false" minable="false"  title="参数新增" id="paraDefIndexAdd" width="600" height="450" target="dialog"><span>新增</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="sys/showUpdateParaPage_paraDefAction.action?paraVo.paraId={paraid}&updateParaFlag=update&menuId=${menuId}"
			 maxable="false" minable="false" resizable="false" title="参数修改" id="paraDefIndexModify" width="600" height="400" rel="update" target="dialog"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" href="sys/deletePara_paraDefAction.action?paraVo.paraId={paraid}&menuId=${menuId}" 
			target="ajaxTodo" title="是否确认要删除？" id="paraDefIndexDelete" ><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="225">
		<thead>
			<tr align="center">
				<th width="5%">选择</th>
				<th width="5%">参数编号</th>
				<th width="10%">参数名称</th>
				<th width="15%">参数数值</th>
				<th width="15%">参数数值名称</th>
				<th width="15%">参数范围</th>
				<th width="35%">参数描述</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="currentPage.pageItems" status="st">
				<tr height="25" target="paraid" rel="${paraId }" >
		     		<td align="center">
		     			<input name="paradef" type="radio" value="${paraId }" />
		     		</td>
		       		<td class="name">
						<div align="center">
							${paraId}
						</div>
					</td>
					<td>
						<div align="center">
							${paraName }
						</div>
					</td>
					<td>
						<div align="center">
							${paraValue }
						</div>
					</td>
					<td>
						<div align="center">
							${paraValueName }
						</div>
					</td>
					<td>
						<div align="center">
<%-- 							${scopeCode } --%>
							<s:if test="scopeCode=='global'">全局级<input type="hidden" value="global"></s:if>
							<s:elseif test="scopeCode=='system'">系统级<input type="hidden" value="system"></s:elseif>
							<s:elseif test="scopeCode=='application'">应用级<input type="hidden" value="application"></s:elseif>
						</div>
					</td>
					<td>
						<div align="center">
							${paraDesc }
						</div>
					</td>
		     	</tr>
	      	</s:iterator>
		</tbody>
	</table>
	<div class="panelBar">
		<div class="pages">
			<span>显示</span>
			<s:select list="#{10:'10',20:'20',50:'50',100:'100',200:'200'}" name="select"
				onchange="navTabPageBreak({numPerPage:this.value})"
				value="currentPage.pageSize">
			</s:select>
			<span>条，共${currentPage.total}条</span>
		</div>
		<div class="pagination" id="paraDefIndexList" targetType="navTab" totalCount="${currentPage.total}" numPerPage="${currentPage.pageSize}" pageNumShown="10" currentPage="${currentPage.pageNo}"></div>
	</div></div></div>
</div>
