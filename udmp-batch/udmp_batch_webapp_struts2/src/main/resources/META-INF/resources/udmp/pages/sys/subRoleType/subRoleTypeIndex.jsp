<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">	
	function resetAll(){
		$("#subRoleName").attr('value','');
		selectMyComBox("roleType","0");
	}
</script>
<form id="pagerForm" method="post"
	action="sys/querySubRoleType_subRoleTypeAction.action?leftFlag=0">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" />
	<input type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>
<div class="pageHeader">
	<form action="sys/querySubRoleType_subRoleTypeAction.action?leftFlag=0" rel="pagerForm" method="post" class="pageForm required-validate" onsubmit="return navTabSearch(this);">
		<div class="panel">
		<h1>查询条件</h1><div>
		<div class="searchBar">
			<ul class="searchContent">
				<li>
					<label for="subRoleName">子角色代码:</label>
					<input id="subRoleName" name="subRoleTypeTVO.subTypeCode" type="text" size="15" maxlength="50" value="${subRoleTypeTVO.subTypeCode }" />
				</li>
				<li>
					<label>角色类型:</label>
					<s:select cssClass="combox" id="roleType" name="subRoleTypeTVO.roleType" list="roleTypeMap" listKey="key" listValue="value" 
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
								<button type="submit" id="subRoleTypeQuery" >查询</button>
							</div>
						</div>
					</li>
					<li>
						<div class="buttonActive" >
							<div class="buttonContent">
								<button type="button" onclick="resetAll();" id="subRoleTypeReset">重置</button>
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
		</ul>
	</div>
	<table class="table" width="100%" layoutH="225">
		<thead>
			<tr align="center">
				<th width="5%">选择</th>
				<th width="10%">子角色编号</th>
				<th width="20%">子角色代码</th>
				<th width="20%">子角色名称</th>
				<th width="10%">所对应组件</th>
				<th width="25%">权限定义表</th>
				<th width="10%">角色类型</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="currentPage.pageItems" status="st">
				<tr height="25" target="srid" rel="${subTypeId }" >
		     		<td align="center">
		     			<input name="subRole" type="radio" value="${subTypeId }" />
		     		</td>
		       		<td class="name">
						<div align="center">
							${subTypeId}
						</div>
					</td>
					<td>
						<div align="center">
							${subTypeCode }
						</div>
					</td>
					<td>
						<div align="center">
							${subTypeName }
						</div>
					</td>
					<td>
						<div align="center">
							${componentId }
						</div>
					</td>
					<td>
						<div align="center">
							${defineTable }
						</div>
					</td>
					<td>
						<div align="center">
							<s:if test="roleType==1">菜单<input type="hidden" value="1"></s:if>
							<s:elseif test="roleType==2">数据<input type="hidden" value="2"></s:elseif>
							<s:elseif test="roleType==3">机构<input type="hidden" value="3"></s:elseif>
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
		<div class="pagination" id="subRoleTypeIndexList" targetType="navTab" totalCount="${currentPage.total}" numPerPage="${currentPage.pageSize}" pageNumShown="10" currentPage="${currentPage.pageNo}"></div>
	</div></div></div>
</div>
