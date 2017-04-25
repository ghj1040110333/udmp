<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">	
	function resetAll(){
		$("#roleGroupName").attr('value','');
	}
</script>
<form id="pagerForm" method="post"
	action="sys/queryRoleGroup_roleGroupAction.action?leftFlag=0">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" />
	<input type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>
<div class="pageHeader">
	<form action="sys/queryRoleGroup_roleGroupAction.action?leftFlag=0" rel="pagerForm" method="post" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
		<div class="panel">
		<h1>查询条件</h1><div>
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label for="roleGroupName">角色组名称:</label>
					<input id="roleGroupName" name="roleGroupTVO.roleGroupName" type="text" size="15" maxlength="50" value="${roleGroupTVO.roleGroupName }" />
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
								<button id="roleGroupManageQuery" type="submit" >查询</button>
							</div>
						</div>
					</li>
					<li>
						<div class="buttonActive" >
							<div class="buttonContent">
								<button type="button" id="roleGroupManageReset" onclick="resetAll();">重置</button>
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
			<li><a class="add" href="sys/showInsertRoleGroupPage_roleGroupAction.action?menuId=${menuId }" maxable="false" minable="false"  title="角色组新增" id="roleGroupAdd" width="600" height="450" target="dialog"><span>新增</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="sys/showUpdateRoleGroupPage_roleGroupAction.action?roleGroupVo.roleGroupId={rgid}&menuId=${menuId}" maxable="false" minable="false" resizable="false" title="角色组修改" id="roleGroupModify" width="600" height="400" rel="update" target="dialog"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" href="sys/deleteRoleGroup_roleGroupAction.action?roleGroupVo.roleGroupId={rgid}&menuId=${menuId}" target="ajaxTodo" title="是否确认要删除" id="roleGroupDelete"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="sys/updateRoleAndRoleGroup_roleGroupAction.action?roleGroupVo.roleGroupId={rgid}&updateRoleGroupFlag=updateRoleAndRoleGroup" target="dialog" title="角色组分配角色" id="roleGroupAssignRole" maxable="false" resizable="false" minable="false" width="1000" height="600" rel="update"><span>分配角色</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="225">
		<thead>
			<tr align="center">
				<th width="10%">选择</th>
				<th width="15%">角色组编号</th>
				<th width="15%">角色组名称</th>
				<th>角色组描述</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="currentPage.pageItems" status="st">
				<tr height="25" target="rgid" rel="${roleGroupId }" >
		     		<td align="center">
		     			<input name="rdRoleGroup" type="radio" value="${roleGroupId }" />
		     		</td>
		       		<td class="name">
						<div align="center">
							${roleGroupId}
						</div>
					</td>
					<td>
						<div align="center">
							${roleGroupName }
						</div>
					</td>
					<td>
						<div align="center">
							${roleGroupDesc }
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
		<div class="pagination" id="roleGroupManageList" targetType="navTab" totalCount="${currentPage.total}" numPerPage="${currentPage.pageSize}" pageNumShown="10" currentPage="${currentPage.pageNo}"></div>
	</div></div></div>
</div>
