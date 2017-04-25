<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>区域管理</title>
<meta name="decorator" content="bootstrap335" />
</head>
<body>
	<form:form id="inputForm" modelAttribute="area" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<div class="form-group">
			<label class="col-sm-2 control-label">上级区域:</label>
			<div class="col-sm-5">
				<sys:treeselect335 id="area" name="parent.id" value="${area.parent.id}" labelName="parent.name"
					labelValue="${area.parent.name}" title="区域" url="/sys/area/treeData" extId="${area.id}" cssClass="form-control"
					allowClear="true" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">
				<span class="red">*</span>区域名称:
			</label>
			<div class="col-sm-5">
				<form:input path="name" htmlEscape="false" maxlength="50" class="form-control" data-bv-notempty="true"
					data-bv-trigger="blur" data-bv-notempty-message="请输入区域名称" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">区域编码:</label>
			<div class="col-sm-5">
				<form:input path="code" htmlEscape="false" maxlength="50" class="form-control" data-bv-trigger="blur" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">区域类型:</label>
			<div class="col-sm-5">
				<form:select path="type" class="form-control">
					<form:options items="${fns:getDictList('sys_area_type')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">备注:</label>
			<div class="col-sm-5">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="form-control"
					data-bv-trigger="blur" />
			</div>
		</div>
	</form:form>

	<div class="my-buttons">
		<shiro:hasPermission name="sys:menu:edit">
			<button id="btnSubmit" class="btn i-btn-ok" type="button">保 存</button>
		</shiro:hasPermission>
		<button id="btnCancel" class="btn btn-default" type="button" data-dismiss="modal">返回</button>
	</div>

	<script type="text/javascript">
	$(document).ready(function() {
		$("#btnSubmit").on("click", function() {
			$.post("${ctx}/sys/area/save", git.serializeObject($("#inputForm")), function(data) {
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