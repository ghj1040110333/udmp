<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>通知管理</title>
<meta name="decorator" content="bootstrap335"/>
</head>
<body>
	<form:form id="inputForm" modelAttribute="oaNotify"  method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<div class="form-group">
			<label class="col-sm-3 control-label"><span class="red">*</span>类型：</label>
			<div class="col-sm-5">
				<form:select path="type" class="form-control">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('oa_notify_type')}" itemLabel="label" itemValue="value" htmlEscape="false" data-bv-trigger="blur" data-bv-notempty="true"/>
				</form:select>
			</div>
		</div>	
		<div class="form-group">
			<label class="col-sm-3 control-label"><span class="red">*</span>标题：</label>
			<div class="col-sm-5">
				<form:input path="title" htmlEscape="false" maxlength="200" class="form-control" data-bv-notempty="true" data-bv-trigger="blur" data-bv-notempty-message="请输入标题" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"><span class="red">*</span>内容：</label>
			<div class="col-sm-5">
				<form:textarea path="content" htmlEscape="false" rows="6" maxlength="2000" class="form-control" data-bv-notempty="true" data-bv-trigger="blur" data-bv-notempty-message="请输入内容"/>
			</div>
		</div>
		<c:if test="${oaNotify.status ne '1'}">
			<div class="form-group">
				<label class="col-sm-3 control-label">附件：</label>
				<div class="col-sm-5">
					<form:hidden id="files" path="files" htmlEscape="false" maxlength="255"/>
					<sys:ckfinder input="files" type="files" uploadPath="/sys/oaNotify" selectMultiple="true"/>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><span class="red">*</span>状态：</label>
				<div class="col-sm-3">
					<form:radiobuttons path="status" items="${fns:getDictList('oa_notify_status')}" itemLabel="label" itemValue="value" htmlEscape="false" data-bv-notempty="true"/>
				</div>
				<div class="col-sm-6">
				<p class="form-control-static">发布后不能进行操作。</p>
			</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label"><font color="red">*</font>接受人：</label>
				<div class="col-sm-5">
	                <sys:treeselect335 id="oaNotifyRecord" name="oaNotifyRecordIds" value="${oaNotify.oaNotifyRecordIds}" labelName="oaNotifyRecordNames" labelValue="${oaNotify.oaNotifyRecordNames}"
						title="用户" url="/sys/office/treeData?type=3" notAllowSelectParent="true" checked="true" cssClass="form-control" dataMsgRequired="请选择接受人" />
				</div>
			</div>
		</c:if>
		<c:if test="${oaNotify.status eq '1'}">
			<div class="form-group">
				<label class="col-sm-3 control-label">附件：</label>
				<div class="col-sm-5">
					<form:hidden id="files" path="files" htmlEscape="false" maxlength="255"/>
					<sys:ckfinder input="files" type="files" uploadPath="/sys/oaNotify" selectMultiple="true" readonly="true" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">接受人：</label>
				<div class="col-sm-7">
					<table id="receiveUserTable" class="table">
						<thead>
							<tr>
								<th>接受人</th>
								<th>接受部门</th>
								<th>阅读状态</th>
								<th>阅读时间</th>
							</tr>
						</thead>
						<tbody>
						<c:forEach items="${oaNotify.oaNotifyRecordList}" var="oaNotifyRecord">
							<tr>
								<td>
									${oaNotifyRecord.user.name}
								</td>
								<td>
									${oaNotifyRecord.user.office.name}
								</td>
								<td>
									${fns:getDictLabel(oaNotifyRecord.readFlag, 'oa_notify_read', '')}
								</td>
								<td>
									<fmt:formatDate value="${oaNotifyRecord.readDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
					已查阅：<span class="label label-success">${oaNotify.readNum}</span> &nbsp; 未查阅：<span class="label label-default">${oaNotify.unReadNum}</span> &nbsp; 总共：<span class="label label-primary">${oaNotify.readNum + oaNotify.unReadNum}</span>
				</div>
			</div>
		</c:if>
		<div class="my-buttons">
			<c:if test="${oaNotify.status ne '1'}">
				<shiro:hasPermission name="sys:oaNotify:edit">
					<button id="btnSubmit" class="btn i-btn-ok" type="button">保 存</button>
				</shiro:hasPermission>
			</c:if>
			<button id="btnCancel" class="btn btn-default" type="button" data-dismiss="modal">返回</button>
		</div>
	</form:form>
	<script src="${ctxUdmp}/js/git-common.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").on("click", function() {
				$.post("${ctx}/sys/oaNotify/save", git.serializeObject($("#inputForm")), function(data) {
					if(data) {
						if(data.success) {
							window.showInfo("success", data.message);
							window.hideModal();
						} else {
							window.showInfo("error", data.message);
						}
					}
				});
			});
		});
	</script>
</body>
</html>