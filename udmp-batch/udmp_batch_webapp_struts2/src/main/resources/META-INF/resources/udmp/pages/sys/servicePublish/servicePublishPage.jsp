<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="pagerForm" method="post"
	action="sys/toServicePublishPage_servicePublishAction.action?leftFlag=0&menuId=${menuId}">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" /> <input
		type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>
<div class="pageHeader">
	<form
		action="sys/toServicePublishPage_servicePublishAction.action?leftFlag=0&menuId=${menuId}"
		method="post" name="form1" id="form1"
		class="pageForm required-validate"
		onsubmit="return navTabSearch(this);" rel="pagerForm">
		<div class="searchBar" style="margin-top: 10px; margin-left: 5px;">
			<table class="searchContent">
				<tr>
					<td>
						<div class="label">服务名称:</div>
					</td>
					<td><input id="servicename" type="text"
						name="servicePublishTVO.serviceName"
						value="${servicePublishTVO.serviceName }" size="15" maxlength="20"
						title="本文支持模糊查询" /></td>
					<td>
						<div class="label">支持服务类型:</div>
					</td>
					<td>API方式<input type="checkbox"
						name="servicePublishTVO.allowApi" value="Y"
						<c:if test="${ servicePublishTVO.allowApi == 'Y'}">checked</c:if>>
						HS方式<input type="checkbox" name="servicePublishTVO.allowHessian"
						value="Y"
						<c:if test="${ servicePublishTVO.allowHessian == 'Y'}">checked</c:if>>
						WS方式<input type="checkbox"
						name="servicePublishTVO.allowWebservice" value="Y"
						<c:if test="${ servicePublishTVO.allowWebservice == 'Y'}">checked</c:if>>
					</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" id="servicePublishQuery">查询</button>
							</div>
						</div>
					</li>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" onClick="udmpUtil.resetAll(this);"
									id="servicePublishReset">重置</button>
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
				href="sys/toServicePublishAddPage_servicePublishAction.action?menuId=${menuId}"
				maxable="false" minable="false" resizable="false" title="新增服务"
				width="600" height="520" target="dialog" rel="servicePublishAdd"><span>新增</span></a></li>
			<li class="line">line</li>
			<li><a class="edit"
				href="sys/toServicePublishEditPage_servicePublishAction.action?servicePublishVO.serviceId={uid}&menuId=${menuId}"
				maxable="false" resizable="false" minable="false" title="修改服务"
				width="600" height="550" rel="servicePublishEdit" target="dialog"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="delete" rel="servicePublishDelete"
				href="sys/deleteServicePublish_servicePublishAction.action?servicePublishVO.serviceId={uid}&menuId=${menuId}"
				target="ajaxTodo" title="是否确认要删除"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr height="25" align="center">
				<th width="4%">选择</th>
				<th>服务编码</th>
				<th>服务名称</th>
				<th>是否支持HS服务</th>
				<th>是否支持WS服务</th>
				<th>是否支持API服务</th>
				<th>所属子系统</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="currentPage.pageItems" status="st">
				<tr align="center" height="25" target="uid" rel="${serviceId}">
					<td><input type="radio" name="serviceInfoManager" value="" />
					<td>
						<div align="center">${serviceId}</div>
					</td>
					<td>
						<div align="center">${serviceName}</div>
					</td>
					<td width="10%">
						<div align="center">
							<c:choose>
								<c:when test="${allowHessian == 'Y'}">是</c:when>
								<c:otherwise>否</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td width="10%">
						<div align="center">
							<c:choose>
								<c:when test="${allowWebservice == 'Y'}">是</c:when>
								<c:otherwise>否</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td width="10%">
						<div align="center">
							<c:choose>
								<c:when test="${allowApi == 'Y'}">是</c:when>
								<c:otherwise>否</c:otherwise>
							</c:choose>
						</div>
					</td>
					<td><Field:codeValue tableName="T_UDMP_SUB_SYSTEM_INFO"
							value="${nciHeadVo.manageCode}" /></td>
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
		<div class="pagination" targetType="navTab" id="servicePublishPagination"
			totalCount="${currentPage.total}"
			numPerPage="${currentPage.pageSize}" pageNumShown="10"
			currentPage="${currentPage.pageNo}"></div>
	</div>
</div>
