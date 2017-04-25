<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
	function resetAll() {
		$('#permissiontypeid').attr('value', '');
		$('#permissiontypename').attr('value', '');
	}
</script>
<form id="pagerForm" method="post"
	action="sys/showPermissionType_permissionTypeAction.action?leftFlag=0&menuId=${menuId}">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" /> <input
		type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>
<div class="pageHeader">
	<form
		action="sys/showPermissionType_permissionTypeAction.action?leftFlag=0&menuId=${menuId}"
		method="post" name="form1" id="form1"
		class="pageForm required-validate"
		onsubmit="return navTabSearch(this);" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						<div class="label">权限名称:</div>
					</td>
					<td><input id="permissiontypename" type="text"
						name="permissionTypeTVO.permissionTypeName"
						value="${permissionTypeTVO.permissionTypeName }" size="15"
						maxlength="20" title="本文支持模糊查询" /></td>
						<td>
						<div class="label">权限描述:</div>
					</td>
					<td><input id="permissiontypedesc" type="text"
						name="permissionTypeTVO.permissionTypeDesc"
						value="${permissionTypeTVO.permissionTypeDesc }" size="15"
						maxlength="20" title="本文支持模糊查询" /></td>
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
	</form>
</div>
<div class="pageContent">
	<div class="panelBar">
		<ul class="toolBar">
			<li><a class="add"
				href="sys/showInsertPermissionTypePage_permissionTypeAction.action?menuId=${menuId}"
				title="新增权限类型" width="600" height="250" target="dialog" rel="add" maxable="false" resizable="false" minable="false"><span>新增</span></a></li>
			<li class="line">line</li>
			<li><a class="edit"
				href="sys/showUpdatePermissionTypePage_permissionTypeAction.action?permissionTypeVO.permissionTypeId={uid}&menuId=${menuId}"
				maxable="false" resizable="false" minable="false" title="权限类型修改"
				width="600" height="250" rel="update" target="dialog"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="delete"
				href="sys/deletePermissionType_permissionTypeAction.action?permissionTypeVO.permissionTypeId={uid}&menuId=${menuId}"
				target="ajaxTodo" title="是否确认要删除"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr height="25" align="center">
				<th width="4%">选择</th>
				<th>权限id</th>
				<th>权限类型</th>
				<th>权限类型描述</th>
				<th>权限处理类标识</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="currentPage.pageItems" status="st" id="permissionType">
				<tr align="center" height="25" target="uid"
					rel="${permissionTypeId}">
					<td><input type="radio" name="permissionTypeManager" value="" />
					</td>
					<td>
						<div align="center">${permissionTypeId}</div>
					</td>
					<td>
						<div align="center">${permissionTypeName}</div>
					</td>
					<td>
						<div align="center">${permissionTypeDesc}</div>
					</td>
					<td>
						<div align="center">${handlerFlag}</div>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
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
			currentPage="${currentPage.pageNo}"></div>
	</div>
</div>
