<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>	
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
	function resetAll(){
		$('#username').attr('value','');
	}
// 	function queryAll(){
// 		alertMsg.warn($('#loglevel').find("option:selected").val());
// 		$('#loglevel').find("option:selected").attr("selected",true);
// 	}
</script>
<form id="pagerForm" method="post"
	action="sys/showUserLog_userLogLevelAction.action?leftFlag=0&menuId=${menuId}">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" /> <input
		type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>
<div class="pageHeader">
	<form
		action="sys/showUserLog_userLogLevelAction.action?leftFlag=0&menuId=${menuId}"
		method="post" name="form1" id="form1"
		class="pageForm required-validate"
		onsubmit="return navTabSearch(this);" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td>
						<div class="label">用户名:</div>
					</td>
					<td><input id="username" type="text"
						name="userLogAVO.userName" value="${userLogTVO.userName }"
						size="15" maxlength="20" title="本文支持模糊查询" /></td>
					<td>
						<div class="label">日志等级:</div>
					</td>
					<td>
<!-- 					    <input id="loglevel" type="text" -->
<%-- 						name="userLogAVO.loglevel" value="${userLogTVO.loglevel }" --%>
<!-- 						size="15" maxlength="20" title="本文支持模糊查询" /> -->
						<select id="loglevel" class="combox" name="userLogAVO.loglevel">
                        <!-- 目前用户日志表的日志等级只包括这五个，不会进行扩展 -->
                        	<option value="">全部</option>
							<option value="trace">trace</option>
							<option value="debug">debug</option>
							<option value="info">info</option>
							<option value="warn">warn</option>
							<option value="error">error</option>
						</select>
						</td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" onclick="queryAll();">查询</button>
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
				href="sys/showInsertUserLogPage_userLogLevelAction.action?menuId=${menuId}"
				maxable="false" minable="false" resizable="false" title="用户日志等级新增"
				width="600" height="250" target="dialog" rel="add"><span>新增</span></a></li>
			<li class="line">line</li>
			<li><a class="edit"
				href="sys/showUpdateUserLogPage_userLogLevelAction.action?userLogVO.userId={uid}&menuId=${menuId}"
				maxable="false" resizable="false" minable="false" title="用户日志等级修改"
				width="600" height="250" rel="update" target="dialog"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="delete"
				href="sys/deleteUserLog_userLogLevelAction.action?userLogVO.userId={uid}&menuId=${menuId}"
				target="ajaxTodo" title="是否确认要删除"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr height="25" align="center">
				<th width="4%">选择</th>
				<th>用户ID</th>
				<th>用户名</th>
				<th>日志等级</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="currentPage.pageItems" status="st">
				<tr align="center" height="25" target="uid" rel="${userId}">
					<td><input type="radio" name="userLogManager" value="" /></td>
					<td>
						<div align="center">${userId}</div>
					</td>
					<td>
						<div align="center">${userName}</div>
					</td>
					<td>
						<div align="center">${loglevel}</div>
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
