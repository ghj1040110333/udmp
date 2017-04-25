<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<form id="pagerForm" method="post"
	action="sys/toServiceConsumePage_serviceConsumeAction.action?leftFlag=0&menuId=${menuId}">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" /> <input
		type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>
<div class="pageHeader">
	<form
		action="sys/toServiceConsumePage_serviceConsumeAction.action?leftFlag=0&menuId=${menuId}"
		method="post" name="form1" id="form1"
		class="pageForm required-validate"
		onsubmit="return navTabSearch(this);" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent" style="margin-top: 10px;">
				<tr>
					<td>
						<div class="label">服务名称:</div>
					</td>
					<td><input id="serviceNameOnNav" type="text"
						name="servicePublishAndConsumeVO.serviceName"
						value="${servicePublishAndConsumeVO.serviceName }" size="15"
						maxlength="20" /></td>
					<td>
						<div class="label">服务发布子系统:</div>
					</td>
					<td><Field:codeTable
							name="servicePublishAndConsumeVO.systemId"
							tableName="T_UDMP_SUB_SYSTEM_INFO" cssClass="combox"
							nullOption="true" value="${servicePublishAndConsumeVO.systemId }"
							id="systemIdOnServiceConPageddd" /></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" id="serviceConsumeQuery">查询</button>
							</div>
						</div>
					</li>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" onclick="udmpUtil.resetAll(this);"
									id="serviceConsumeReset">重置</button>
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
				href="sys/askForService_serviceConsumeAction.action?servicePublishTVO.serviceId={uid}&menuId=${menuId}"
				maxable="false" resizable="false" minable="false" title="修改服务类型"
				width="600" height="300" rel="askForService" target="dialog"><span>申请服务</span></a></li>
			<li class="line">line</li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr height="25" align="center">
				<th width="4%">选择</th>
				<th width="8%">服务编码</th>
				<th width="8%">服务名称</th>
				<th>服务发布用途</th>
				<th width="8%">发布子系统简称</th>
			</tr>
		</thead>
		<tbody id="serviceConsumeIndexTbody">
			<s:iterator value="currentPage.pageItems" status="st">
				<tr align="center" height="25" target="uid" rel="${serviceId}">
					<td><input type="radio" name="serviceConsumeManager" value="" /></td>
					<td>
						<div align="center">${serviceId}</div>
					</td>
					<td>
						<div align="center">${serviceName}</div>
					</td>
					<td>
						<div align="center">${servicePurpose}</div>
					</td>
					<td>
						<div align="center">${systemName}</div>
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
		<div class="pagination" targetType="navTab" id="serviceConsumeIndex"
			totalCount="${currentPage.total}"
			numPerPage="${currentPage.pageSize}" pageNumShown="10"
			currentPage="${currentPage.pageNo}"></div>
	</div>
</div>
