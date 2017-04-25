<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form:form id="inputForm" modelAttribute="menu" method="post" class="form-horizontal">
	<form:hidden path="id" />
	<div class="form-group">
		<label class="col-sm-2 control-label">
			<span class="red">*</span>上级菜单:
		</label>
		<div class="col-sm-3">
			<sys:treeselect335 id="menu" name="parent.id" value="${menu.parent.id}" labelName="parent.name" 
				labelValue="${menu.parent.name}" title="菜单" url="/sys/menu/treeData" extId="${menu.id}" cssClass="form-control"
				allowClear="true" dataMsgRequired="请选择上级菜单"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">
			<span class="red">*</span>名称:
		</label>
		<div class="col-sm-3">
			<form:input path="name" htmlEscape="false" maxlength="50" class="form-control" data-bv-notempty="true"
				data-bv-trigger="blur" data-bv-notempty-message="请输入名称" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">链接:</label>
		<div class="col-sm-4">
			<form:input path="href" htmlEscape="false" maxlength="2000" class="form-control" data-bv-trigger="blur" />
		</div>
		<div class="col-sm-6">
			<p class="form-control-static">点击菜单跳转的页面</p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">目标:</label>
		<div class="col-sm-2">
			<form:input path="target" htmlEscape="false" maxlength="10" class="form-control" data-bv-trigger="blur" />
		</div>
		<div class="col-sm-8">
			<p class="form-control-static">链接地址打开的目标窗口，默认：mainFrame</p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">图标:</label>
		<div class="col-sm-4">
			<sys:iconselect id="icon" name="icon" value="${menu.icon}"/>
		</div>
	</div>
		<div class="form-group">
		<label class="col-sm-2 control-label">排序:</label>
		<div class="col-sm-2">
			<form:input path="sort" htmlEscape="false" maxlength="10" class="form-control" data-bv-trigger="blur" />
		</div>
		<div class="col-sm-8">
			<p class="form-control-static">排列顺序，升序。</p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">可见:</label>
		<div class="col-sm-2">
			<form:radiobuttons path="isShow" items="${fns:getDictList('show_hide')}" itemLabel="label" itemValue="value" htmlEscape="false" data-bv-notempty="true"/>
		</div>
		<div class="col-sm-8">
			<p class="form-control-static">该菜单或操作是否显示到系统菜单中</p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">权限标识:</label>
		<div class="col-sm-4">
			<form:input path="permission" htmlEscape="false" maxlength="100" class="form-control" data-bv-trigger="blur" />
		</div>
		<div class="col-sm-6">
			<p class="form-control-static">控制器中定义的权限标识，如：@RequiresPermissions("权限标识")</p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">备注:</label>
		<div class="col-sm-4">
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

<script src="${ctxUdmp}/js/git-common.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$("#btnSubmit").on("click", function() {
			$.post("${ctx}/sys/menu/save", git.serializeObject($("#inputForm")), function(data) {
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