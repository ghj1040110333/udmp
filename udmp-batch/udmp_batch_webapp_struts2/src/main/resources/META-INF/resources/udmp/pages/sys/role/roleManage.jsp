<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
function resetAll(){
	$("#roleName").attr('value','');
	selectMyComBox("roleType","0");
	selectMyComBox("combox_subRoleType","0"); 
}
$("#fpmenu").click(function(){
	if($("tr.selected td>div input[type='hidden']").val() == "2" ){
		alertMsg.warn("只有菜单角色才能够分配菜单!");
		return false;
	}
});
$("#fpdata").click(function(){
	if($("tr.selected td>div input[type='hidden']").val() == "1" ){
		alertMsg.warn("只有数据角色才能够分配数据!");
		return false;
	}
});
//级联回显
dwz_combox_myarray = new Array('roleType','combox_subRoleType');
dwz_combox_mybox = navTab.getCurrentPanel();
</script>
<form id="pagerForm" method="post"
	action="sys/queryRole_roleAction.action?leftFlag=0">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" />
	<input type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>
<div class="pageHeader">
	<form action="sys/queryRole_roleAction.action?leftFlag=0" rel="pagerForm" method="post" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
		<div class="panel">
		<h1>查询条件</h1><div>
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label for="roleName">角色名称:</label>
					<input id="roleName" name="roleTVO.roleName" type="text" size="15" maxlength="50" value="${roleTVO.roleName }" title="支持模糊查询" />
				</li>
				<li>
					<label>角色类型:</label>
					<s:select cssClass="combox" id="roleType" name="roleTVO.roleType" list="roleTypeMap" listKey="key" listValue="value" 
							headerKey="0" headerValue="全部" 
							 ref="combox_subRoleType"
							refUrl="sys/getSubRole_roleAction.action?roleTypeCode={value}" initVal="%{#parameters.combox_subRoleType[0]}">
					</s:select>
				</li>
				
<!-- 				<li> -->
<!-- 					<label>子角色类型:</label> -->
<%-- 					<s:select cssClass="combox" id="subRoleType" name="roleTVO.subRoleType" list="subRoleTypeMap" listKey="key" listValue="value"  --%>
<%-- 							headerKey="0" headerValue="全部"> --%>
<%-- 					</s:select> --%>
<!-- 				</li> -->
				
				<li>
					<label>子角色类型:</label>
					<select id="combox_subRoleType" name="roleTVO.subRoleType" class="combox">
						<option value="0">全部</option>
					</select>
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
								<button type="submit" id="roleManageQuery">查询</button>
							</div>
						</div>
					</li>
					<li>
						<div class="buttonActive" >
							<div class="buttonContent">
								<button type="button" onclick="resetAll();" id="roleManageReset">重置</button>
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
			<li><a class="add" href="sys/showInsertRolePage_roleAction.action?addRoleFlag=add&menuId=${menuId }" maxable="false" minable="false"  title="角色新增" id="roleManageAddRole" width="600" height="450" target="dialog"><span>新增</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="sys/showUpdateRolePage_roleAction.action?roleVo.roleId={rid}&updateRoleFlag=update&menuId=${menuId}" maxable="false" minable="false" resizable="false" title="角色修改" id="roleManageEditRole" width="600" height="400" rel="update" target="dialog"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" href="sys/deleteRole_roleAction.action?roleVo.roleId={rid}&menuId=${menuId}" target="ajaxTodo" title="是否确认要删除" id="roleManageDeleteRole"><span>删除</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="sys/updateRoleModule_roleAction.action?roleVo.roleId={rid}&updateRoleModuleFlag=updateRoleModule" target="dialog" title="分配菜单权限" id="roleManageAssignMenu" maxable="false" resizable="false" minable="false" width="710" height="610" rel="update"><span id="fpmenu">分配菜单权限</span></a></li>
			<li class="line">line</li>
			<li><a class="edit" href="sys/updateRolePermission_roleAction.action?roleVo.roleId={rid}&updateRolePermissionFlag=updateRolePermission" target="dialog" title="分配数据权限" id="roleManageAssignData" maxable="false" resizable="false" minable="false" width="700" height="600" rel="update"><span id="fpdata">分配数据权限</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="225">
		<thead>
			<tr align="center">
				<th width="10%">选择</th>
				<th width="15%">角色编号</th>
				<th width="15%">角色名称</th>
				<th width="10%">角色类型</th>
				<th width="15%">子角色类型</th>
				<th width="10%">禁用标志</th>
				<th>角色描述</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="currentPage.pageItems" status="st">
				<tr height="25" target="rid" rel="${roleId }" >
		     		<td align="center">
		     			<input name="rdRole" type="radio" value="${roleId }" />
		     		</td>
		       		<td class="name">
						<div align="center">
							${roleId}
						</div>
					</td>
					<td>
						<div align="center">
							${roleName }
						</div>
					</td>
					<td>
						<div align="center">
							<s:if test="roleType==1">菜单<input type="hidden" value="1"></s:if>
							<s:elseif test="roleType==2">数据<input type="hidden" value="2"></s:elseif>
<%-- 							${typeName } --%>
						</div>
					</td>
					<td>
						<div align="center">
<%-- 							<s:elseif test="subRoleType==50">DATA_ALL<input type="hidden" value="50"></s:elseif> --%>
<%-- 							<s:elseif test="subRoleType==55">MENU_ALL<input type="hidden" value="55"></s:elseif> --%>
							${subTypeCode }
						</div>
					</td>
					<td>
						<div align="center">
<%-- 							${disabled } --%>
							<s:if test='disabled=="Y"'>是</s:if>
							<s:elseif test='disabled=="N"'>否</s:elseif>
						</div>
					</td>
					<td>
						<div align="center">
							${roleDesc }
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
		<div class="pagination" id="roleManageRoleList" targetType="navTab" totalCount="${currentPage.total}" numPerPage="${currentPage.pageSize}" pageNumShown="10" currentPage="${currentPage.pageNo}"></div>
	</div></div></div>
</div>
