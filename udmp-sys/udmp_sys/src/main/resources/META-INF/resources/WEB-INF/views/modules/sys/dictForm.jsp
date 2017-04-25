<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form:form id="inputForm" modelAttribute="dict" method="post" class="form-horizontal">
	<form:hidden path="id" />
	<div class="form-group">
		<label class="col-sm-3 control-label">键值:</label>
		<div class="col-sm-5">
			<form:input path="value" htmlEscape="false" maxlength="50" class="form-control" data-bv-trigger="blur" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label">标签:</label>
		<div class="col-sm-5">
			<form:input path="label" htmlEscape="false" maxlength="50" class="form-control" data-bv-trigger="blur" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label">类型:</label>
		<div class="col-sm-5">
			<form:input path="type" htmlEscape="false" maxlength="50" class="form-control" data-bv-trigger="blur" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label">描述:</label>
		<div class="col-sm-5">
			<form:input path="description" htmlEscape="false" maxlength="50" class="form-control" data-bv-trigger="blur" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label">排序:</label>
		<div class="col-sm-5">
			<form:input path="sort" htmlEscape="false" maxlength="11" class="form-control" data-bv-trigger="blur" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-3 control-label">备注:</label>
		<div class="col-sm-5">
			<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="form-control"
				data-bv-trigger="blur" />
		</div>
	</div>
	<div class="my-buttons">
		<shiro:hasPermission name="sys:dict:edit">
			<button id="btnSubmit" class="btn i-btn-ok" type="button">保 存</button>
		</shiro:hasPermission>
		<button id="btnCancel" class="btn btn-default" type="button" data-dismiss="modal">返回</button>
	</div>
</form:form>
<script src="${ctxUdmp}/js/git-common.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnSubmit").on("click", function() {
			$.post("${ctx}/sys/dict/save", git.serializeObject($("#inputForm")), function(data) {
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