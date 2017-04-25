<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	var serviceType = '<%=request.getAttribute("serviceType")%>';
	if (serviceType) {
		selectMyComBox('serviceType', serviceType);
	}
</script>
<form id="pagerForm" method="post"
	action="sys/toServicePage_serviceAction.action?leftFlag=0&menuId=${menuId}">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" /> <input
		type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>
<div class="pageHeader">
	<form
		action="sys/toServicePage_serviceAction.action?leftFlag=0&menuId=${menuId}"
		method="post" name="envQueryForm" id="form1"
		class="pageForm required-validate"
		onsubmit="return navTabSearch(this);" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent"
				style="margin-top: 10px; margin-left: 10px;">
				<tr>
					<td>
						<div class="label">子系统名称:</div>
					</td>
					<td><Field:codeTable name="serviceTVO.systemId"
							tableName="T_UDMP_SUB_SYSTEM_INFO" cssClass="combox"
							nullOption="true" value="${serviceTVO.systemId }" id="systemId" /></td>
					<td>
						<div class="label">环境类型:</div>
					</td>
					<td><Field:codeTable name="serviceTVO.serviceType"
					tableName="T_UDMP_ENV_TYPE" cssClass="combox" nullOption="true" value="${serviceTVO.serviceType }" id="selectEnvOnNav"/></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" id="serviceEnvQuery">查询</button>
							</div>
						</div>
					</li>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" onclick="myUtil.resetAll(this);" id="serviceEnvReset">重置</button>
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
				href="sys/showInsertServicePage_serviceAction.action?menuId=${menuId}"
				maxable="false" minable="false" resizable="false" title="新增环境"
				width="600" height="250" target="dialog" rel="serviceEnvAdd"><span>新增</span></a></li>
			<li class="line">line</li>
			<li><a class="edit"
				href="sys/showUpdateServicePage_serviceAction.action?serviceVO.systemIdAndType={uid}&menuId=${menuId}"
				maxable="false" resizable="false" minable="false" title="修改环境"
				width="600" height="250" rel="serviceEnvEdit" target="dialog"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="delete"
				href="sys/deleteService_serviceAction.action?serviceVO.systemIdAndType={uid}&menuId=${menuId}" rel="serviceEnvDelete"
				target="ajaxTodo" title="是否确认要删除"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr height="25" align="center">
				<th width="4%">选择</th>
				<th>子系统名称</th>
				<th>环境名称</th>
				<th>环境IP</th>
				<th>环境端口号</th>
				<th>环境上下文</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="currentPage.pageItems" status="st">
				<tr align="center" height="25" target="uid" rel="${systemId},${serviceType}">
					<td><input type="radio" name="serviceManager" value="" /></td>
					<td>
						<div align="center">${systemId}-
							<Field:codeValue tableName="T_UDMP_SUB_SYSTEM_INFO"
								value="${systemId}" />
						</div>
					</td>
					<td>
						<div align="center">
						<Field:codeValue tableName="T_UDMP_ENV_TYPE" value="${serviceType}"/>
						</div>
					</td>
					<td>
						<div align="center">${serviceIp}</div>
					</td>
					<td>
						<div align="center">${servicePort}</div>
					</td>
					<td>
						<div align="center">${serviceContext}</div>
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
		<div class="pagination" targetType="navTab" id="serviceEnvPagination"
			totalCount="${currentPage.total}"
			numPerPage="${currentPage.pageSize}" pageNumShown="10"
			currentPage="${currentPage.pageNo}"></div>
	</div>
</div>
