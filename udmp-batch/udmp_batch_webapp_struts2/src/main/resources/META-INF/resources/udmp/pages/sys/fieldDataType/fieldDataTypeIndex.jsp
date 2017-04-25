<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
	function resetAll() {
		$('#datatypedesc').attr('value', '');
		$('#datavalidateclass').attr('value', '');
		$('#datatype').attr('value', '');
	}
</script>
<form id="pagerForm" method="post"
	action="sys/showFieldDataType_fieldDataTypeAction.action?leftFlag=0&menuId=${menuId}">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" /> <input
		type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>
<div class="pageHeader">
	<form
		action="sys/showFieldDataType_fieldDataTypeAction.action?leftFlag=0&menuId=${menuId}"
		method="post" name="form1" id="form1"
		class="pageForm required-validate"
		onsubmit="return navTabSearch(this);" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
<!-- 					<td> -->
<!-- 						<div class="label">域段类型描述:</div> -->
<!-- 					</td> -->
<!-- 					<td><input id="datatypedesc" type="text" -->
<!-- 						name="fieldDataTypeAVO.dataTypeDesc" -->
<%-- 						value="${fieldDataTypeAVO.dataTypeDesc }" size="15" maxlength="20" --%>
<!-- 						title="本文支持模糊查询" /></td> -->
<!-- 					<td> -->
<!-- 						<div class="label">域段类型校验类:</div> -->
<!-- 					</td> -->
<!-- 					<td><input id="datavalidateclass" type="text" -->
<!-- 						name="fieldDataTypeAVO.dataValidateClass" -->
<%-- 						value="${fieldDataTypeAVO.dataValidateClass }" size="15" --%>
<!-- 						maxlength="20" title="本文支持模糊查询" /></td> -->
					<td>
						<div class="label">域段类型:</div>
					</td>
					<td><input id="datatype" type="text"
						name="fieldDataTypeAVO.dataType"
						value="${fieldDataTypeAVO.dataType }" size="15" maxlength="20"
						title="本文支持模糊查询" /></td>
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
				href="sys/showInsertFieldDataTypePage_fieldDataTypeAction.action?menuId=${menuId}"
				maxable="false" minable="false" resizable="false" title="测试新增"
				width="600" height="250" target="dialog" rel="add"><span>新增</span></a></li>
			<li class="line">line</li>
			<li><a class="edit"
				href="sys/showUpdateFieldDataTypePage_fieldDataTypeAction.action?fieldDataTypeVO.dataType={uid}&menuId=${menuId}"
				maxable="false" resizable="false" minable="false" title="测试修改"
				width="600" height="250" rel="update" target="dialog"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="delete"
				href="sys/deleteFieldDataType_fieldDataTypeAction.action?fieldDataTypeVO.dataType={uid}&menuId=${menuId}"
				target="ajaxTodo" title="是否确认要删除"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr height="25" align="center">
				<th width="4%">选择</th>
				<th>域段类型</th>
				<th>域段类型描述</th>
				<th>域段类型校验类</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="currentPage.pageItems" status="st">
				<tr align="center" height="25" target="uid" rel="${dataType}">
					<td><input type="radio" name="fieldDataTypeManager" value="" />
					</td>
					<td>
						<div align="center">${dataType}</div>
					</td>
					<td>
						<div align="center">${dataTypeDesc}</div>
					</td>
					<td>
						<div align="center">${dataValidateClass}</div>
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
