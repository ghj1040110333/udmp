<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<form id="pagerForm" method="post"
	action="sys/queryWikiInfo_wikiUrlConfigAction.action?leftFlag=0&menuId=${menuId}">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" /> <input
		type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>

<div class="pageHeader">
	<form
		action="sys/queryWikiInfo_wikiUrlConfigAction.action?leftFlag=0&menuId=${menuId}"
		method="post" name="form1" id="form1"
		class="pageForm required-validate"
		onsubmit="return navTabSearch(this);" rel="pagerForm">

		<div class="searchBar">
			<table class="searchContent"
				style="margin-left: 20px; margin-top: 10px;">
				<tr>
					<td>
						<div class="label">知识库名称:</div>
					</td>
					<td><input id="wikiname" type="text"
						name="wikiUrlConfigTVO.wikiName"
						value="${wikiUrlConfigTVO.wikiName }" size="15" maxlength="20"
						title="知识库名称" /></td>
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
								<button type="button" onclick="udmpUtil.resetAll(this);">重置</button>
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
				href="sys/addWikiInfo_wikiUrlConfigAction.action?menuId=${menuId}"
				maxable="false" minable="false" resizable="false" title="知识库新增"
				width="600" height="250" target="dialog" rel="add"><span>新增</span></a></li>
			<li class="line">line</li>
			<li><a class="edit"
				href="sys/editWikiInfo_wikiUrlConfigAction.action?wikiUrlConfigTVO.wikiId={uid}&menuId=${menuId}"
				maxable="false" resizable="false" minable="false" title="知识库修改"
				width="600" height="250" rel="update" target="dialog"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="delete"
				href="sys/deleteWikiInfo_wikiUrlConfigAction.action?wikiUrlConfigTVO.wikiId={uid}&menuId=${menuId}"
				target="ajaxTodo" title="是否确认要删除"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table sort" width="100%" layoutH="145">
		<thead>
			<tr height="25" align="center">
				<th width="4%">选择</th>
				<th>知识库ID</th>
				<th>知识库名称</th>
				<th>知识库URL</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="currentPage.pageItems" status="st">
				<tr align="center" height="25" target="uid" rel="${wikiId}">
					<td><input type="radio" name="wikiUrlConfigManager" /></td>
					<td>
						<div align="center">${wikiId}</div>
					</td>
					<td>
						<div align="center">${wikiName}</div>
					</td>
					<td>
						<div align="center">${wikiUrl}</div>
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
