<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	var serviceConsumeManage = {
		judgeOne : function(aEle) {
			var $checkbox = $("#serviceConsumeIndexTbody",
					navTab.getCurrentPanel()).find(":checkbox:checked");
			var length = $checkbox.length;
			//若长度是0，执行dwz默认操作
			if (length == 0) {
				return true;
			}
			//若长度大于0，则提示错误，只可选择一条记录
			if (length > 1) {
				alertMsg.warn("修改操作只可以选择一条记录！");
				return false;
			}
			//处理选中checkbox是1的情况
			//手动修改href，处理dwz行号对href属性替换bug
// 			debugger;
			var $a = $(aEle);
			var href = $a.attr("href").replace("uidVal",$checkbox.val());
// 			console.log(href);
// 			$a.attr("href",href);
// 			console.log($a.attr("href"));
			$.pdialog.open(href,'update','修改',{});
// 			return true;
			return false;
		},
		addPara : function(aEle) {
			//获取所有被选中的checkbox
			var checkboxs = $("#serviceConsumeIndexTbody",navTab.getCurrentPanel()).find(
					":checkbox:checked");
			//将被选中checkbox的值进行拼接，以逗号相隔
			var checkboxval = "";
			checkboxs.each(function() {
				checkboxval += $(this).val() + ",";
			});
			// 获取触发ajax操作的a标签
			var href = $(aEle).attr("href");
			// 将参数进行替换
			href = href.replace("uidVal", checkboxval.substring(0,
					checkboxval.length - 1));
			// 将触发的href属性进行替换
			$(aEle).attr("href", href);
			return true;
		}
	}
</script>
<form id="pagerForm" method="post"
	action="sys/toServiceConsumeManagePage_serviceConsumeManageAction.action?leftFlag=0&menuId=${menuId}">
	<input type="hidden" name="pageNum" value="${currentPage.pageNo}" /> <input
		type="hidden" name="numPerPage" value="${currentPage.pageSize}" />
</form>
<div class="pageHeader">
	<form
		action="sys/toServiceConsumeManagePage_serviceConsumeManageAction.action?leftFlag=0&menuId=${menuId}"
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
						value="${servicePublishAndConsumeVO.serviceName}" size="15"
						maxlength="20" /></td>
					<td>
						<div class="label">服务发布子系统:</div>
					</td>
					<td><Field:codeTable
							name="servicePublishAndConsumeVO.systemId"
							tableName="T_UDMP_SUB_SYSTEM_INFO" cssClass="combox"
							nullOption="true" value="${servicePublishAndConsumeVO.systemId }"
							id="systemIdOnServiceConPage" /></td>
				</tr>
			</table>
			<div class="subBar">
				<ul>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="submit" id="serviceConsumeManSubmit">查询</button>
							</div>
						</div>
					</li>
					<li>
						<div class="buttonActive">
							<div class="buttonContent">
								<button type="button" onclick="myUtil.resetAll(this);" id="serviceConsumeReset">重置</button>
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
			<li><a class="edit"
				href="sys/toServiceConsumeManageEdit_serviceConsumeManageAction.action?serviceConsumeAVO.serviceId=uidVal&menuId=${menuId}"
				maxable="false" resizable="false" minable="false" title="修改服务"
				myOption="serviceConsumeManage.judgeOne(this)" width="600" height="250"
				rel="serviceConsumeManEdit" target="dialog"><span>修改</span></a></li>
			<li class="line">line</li>
			<li><a class="delete"
				href="sys/toServiceConsumeManageDel_serviceConsumeManageAction.action?serviceConsumeAVO.ids=uidVal&menuId=${menuId}"
				target="ajaxTodo" title="确定要删除所有已申请的服务么？" rel="serviceConsumeDelete"
				myOption="serviceConsumeManage.addPara(this)"><span>删除</span></a></li>
		</ul>
	</div>
	<table class="table" width="100%" layoutH="145">
		<thead>
			<tr height="25" align="center">
				<th width="4%">选择</th>
				<th>服务编码</th>
				<th>服务名称</th>
				<th>申请服务类型</th>
				<th>申请/修改服务原因</th>
				<th>发布子系统简称</th>
			</tr>
		</thead>
		<tbody id="serviceConsumeIndexTbody">
			<s:iterator value="currentPage.pageItems" status="st">
				<tr align="center" height="25" target="uid">
					<td><input type="checkbox" name="serviceConsumeManager"
						value="${serviceId}" /></td>
					<td>
						<div align="center">${serviceId}</div>
					</td>
					<td>
						<div align="center">${serviceName}</div>
					</td>
					<td><div align="center">
							<c:choose>
								<c:when test="${serviceType == 'H'}">Hessian服务</c:when>
								<c:when test="${serviceType == 'W'}">WebService服务</c:when>
								<c:when test="${serviceType=='A'}">API服务</c:when>
							</c:choose>
						</div></td>
					<td>
						<div align="center">${serviceCause}</div>
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
		<div class="pagination" targetType="navTab" id="serviceConsumeManPagination"
			totalCount="${currentPage.total}"
			numPerPage="${currentPage.pageSize}" pageNumShown="10"
			currentPage="${currentPage.pageNo}"></div>
	</div>
</div>
