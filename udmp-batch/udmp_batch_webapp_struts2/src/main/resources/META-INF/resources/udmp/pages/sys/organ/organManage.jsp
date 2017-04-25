<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>	
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
	//点击重置时清空查询条件
	function resetAll(){
			$('#organcode').attr('value','');
			$('#uporgancode').attr('value','');
			$('#uporganname').attr('value','');
			$('#organname').attr('value','');
	}
// 	$(function(){
// 		$("button").click(function(event) {
// 			var btnId = $(this).attr("id");
// 			//判断交易管理是否开启
// 			if(dealSwitch){
// 				//若该按钮有为id属性赋值
// 				if(btnId){
// 					// 取得点击按钮的按钮id属性
// 					clickBtnMark = $(this).attr("id");
// 				}else {
// 					alertMsg.warn("请为触发事件按钮的id属性赋值！");
// 					return false;
// 				}
// 			}
// 		});
// $("button").click(function(event){
// 	console.log();
// });
			
		/**表单提交*/
// 		$("#queryOrgBtn").click(function(){
// 			//如果提交时没有父菜单，则将表单提交时的父id设置为空
// 			if(isNulOrEmpty($("#modulepId").val())){
// 				$("#hiddenpId").attr("value","");
// 			}
// 			menuForm.attr("action","sys/updateMenu_menuAction.action?flag="+operateFlag);
// 			menuForm.submit();
// 		});
// 	});
</script>
<form id="pagerForm" method="post"
	action="sys/toManagePage_organAction.action?leftFlag=0&menuId=${menuId}">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" />
	<input type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>
<div class="pageHeader">
	<form action="sys/toManagePage_organAction.action?leftFlag=0&menuId=${menuId}"
		method="post" name="form1" id="form1"
		class="pageForm required-validate"
		onsubmit="return navTabSearch(this);" rel="pagerForm">
		<div class="searchBar">
			<table class="searchContent">
				<tr>
					<td><div class="label">机构名称:</div>	</td>
					<td><input id="organname" type="text" name="organTVO.organName"
						title="本文支持模糊查询" value="${organTVO.organName }" size="15" maxlength="20" /></td>
						
					<td><div class="label">机构代码:</div>	</td>
					<td><input id="organcode" type="text" name="organTVO.organCode"
						title="本文支持模糊查询" value="${organTVO.organCode }" size="15" maxlength="20" /></td>
						
					<td><div class="label">上级机构名称:</div></td>
					<td><input id="uporganname" type="text" name="organTVO.uporganName"
					title="本文支持模糊查询" value="${organTVO.uporganName }" size="15" maxlength="20" /></td>
						 
					<td><div class="label">上级机构代码:</div></td>
					<td><input id="uporgancode" type="text" name="organTVO.uporganCode"
						title="本文支持模糊查询" value="${organTVO.uporganCode }" size="15" maxlength="20" /></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" id="queryOrgBtn" ">查询</button>
							</div>
						</div>
					</li>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" id="resetAllBtn" onclick="resetAll();">重置</button>
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
			<li><a class="add" 	href="sys/showAddPage_organAction.action?menuId=${menuId}"
				maxable="false" minable="false" resizable="false" title="新增机构"
				 width="600" height="400"  target="dialog" rel="add"><span>新增</span></a></li>
			<li class="line">line</li>
			<li><a class="edit"	href="sys/showUpdatePage_organAction.action?organVO.organId={uid}&menuId=${menuId}"
				maxable="false" resizable="false" minable="false" title="修改机构"
				width="600" height="400" rel="update" target="dialog"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a id="deleteOrgBtn" class="delete" href="sys/deleteOrg_organAction.action?organVO.organId={uid}&menuId=${menuId}"
				target="ajaxTodo" title="是否确认要删除"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr height="25" align="center">
				<th  width="5%">选择</th>
				<th>机构Id</th>
				<th>机构名称</th>
				<th>机构代码</th>
				<th>机构所在城市</th>
				<th>机构等级</th>
				<th>上级机构ID</th>
				<th>上级机构代码</th>
			</tr>
		</thead>
		<tbody>
			<s:iterator value="currentPage.pageItems" status="st">
				<tr align="center" height="25" target="uid" rel="${organId}">
					<td><input type="radio" name="orgManager" value="" /></td>
					<td>
						<div align="center">${organId}</div>
					</td>
					<td>
						<div align="center">${organName}</div>
					</td>
					<td>
						<div align="center">${organCode}</div>
					</td>
					<td>
						<div align="center">${organCity}</div>
					</td>
					<td>
						<div align="center">${organGrade}</div>
					</td>
					<td>
						<div align="center">${uporganId}</div>
					</td>
					<td>
						<div align="center">${uporganCode}</div>
					</td>
				</tr>
			</s:iterator>
		</tbody>
	</table>
	<div class="panelBar">
		<div id="curruntPage" class="pages">
			<span>显示</span>
			<s:select list="#{20:'20',50:'50',100:'100',200:'200'}" name="select"
				onchange="navTabPageBreak({numPerPage:this.value})"
				value="currentPage.pageSize">
			</s:select>
			<span>条，共${currentPage.total}条</span>
		</div>
		<div id ="pageMsg" class="pagination" targetType="navTab"
			totalCount="${currentPage.total}"
			numPerPage="${currentPage.pageSize}" pageNumShown="10"
			currentPage="${currentPage.pageNo}"></div>
	</div>
</div>



