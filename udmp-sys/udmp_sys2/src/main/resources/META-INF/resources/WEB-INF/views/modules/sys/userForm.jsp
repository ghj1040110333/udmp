<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form:form id="inputForm" modelAttribute="user" method="post" class="form-horizontal">
	<form:hidden path="id"/>
	<div class="form-group">
		<label class="col-sm-2 control-label">头像:</label>
		<div class="col-sm-5">
			<form:hidden id="nameImage" path="photo" htmlEscape="false" maxlength="255"/>
			<sys:ckfinder input="nameImage" type="images" uploadPath="/photo" selectMultiple="false" maxWidth="100" maxHeight="100"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label"><span class="red">*</span> 归属公司:</label>
		<div class="col-sm-5">
               <sys:treeselect335 id="company" name="company.id" value="${user.company.id}" labelName="company.name" labelValue="${user.company.name}"
				title="归属公司" url="/sys/office/treeData?type=1" cssClass="form-control" dataMsgRequired="请选择归属公司" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label"><span class="red">*</span> 归属部门:</label>
		<div class="col-sm-5">
               <sys:treeselect335 id="office" name="office.id" value="${user.office.id}" labelName="office.name" labelValue="${user.office.name}"
				title="归属部门" url="/sys/office/treeData?type=2" cssClass="form-control" notAllowSelectParent="true" dataMsgRequired="请选择归属部门" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label"><span class="red">*</span> 工号:</label>
		<div class="col-sm-5">
			<form:input path="no" htmlEscape="false" maxlength="50" class="form-control"
				data-bv-notempty="true" data-bv-trigger="blur" data-bv-notempty-message="请输入工号"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label"><span class="red">*</span> 姓名:</label>
		<div class="col-sm-5">
			<form:input path="name" htmlEscape="false" maxlength="50" class="form-control"
				data-bv-notempty="true" data-bv-trigger="blur" data-bv-notempty-message="请输入姓名" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label"><span class="red">*</span> 登录名:</label>
		<div class="col-sm-5">
			<input id="oldLoginName" name="oldLoginName" type="hidden" value="${user.loginName}">
			<form:input path="loginName" htmlEscape="false" maxlength="50" class="form-control"
				data-bv-notempty="true" data-bv-trigger="blur" data-bv-notempty-message="请输入用户名" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label"><c:if test="${empty user.id}"><span class="red">*</span></c:if> 密码:</label>
		<div class="col-sm-5">
			<input id="newPassword" name="newPassword" type="password" value="" maxlength="50" minlength="3" class="form-control"
				${empty user.id ? 'data-bv-notempty="true" data-bv-trigger="blur" data-bv-notempty-message="请输入密码"':''} />
		</div>
		<c:if test="${not empty user.id}">
		<div class="col-sm-5">
			<p class="form-control-static">若不修改密码，请留空。</p>
		</div>
		</c:if>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">
			<c:if test="${empty user.id}"><span class="red">*</span></c:if> 确认密码:
		</label>
		<div class="col-sm-5">
			<input id="confirmNewPassword" name="confirmNewPassword" type="password" value="" maxlength="50" minlength="3" class="form-control"
				data-bv-trigger="blur" data-bv-identical="true" data-bv-identical-field="newPassword" data-bv-identical-message="两次输入密码不一致"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">邮箱:</label>
		<div class="col-sm-5">
			<form:input path="email" htmlEscape="false" maxlength="100" class="form-control"
				data-bv-emailaddress="true" data-bv-emailaddress-message="请输入有效的电子邮箱" data-bv-trigger="blur" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">电话:</label>
		<div class="col-sm-5">
			<form:input path="phone" htmlEscape="false" maxlength="100" class="form-control" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">手机:</label>
		<div class="col-sm-5">
			<form:input path="mobile" htmlEscape="false" maxlength="100" class="form-control"
				data-bv-regexp="true" data-bv-regexp-regexp="^(\+86)?(0)?(1(([35][0-9])|(47)|(70)|[8][0-9]))\d{8}$"
				data-bv-regexp-message="请输入有效的手机号码" data-bv-trigger="blur" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">是否允许登录:</label>
		<div class="col-sm-2">
			<form:select path="loginFlag" class="form-control">
				<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
		</div>
		<div class="col-sm-8">
			<p class="form-control-static"><span class="red">*</span> “是”代表此账号允许登录，“否”表示此账号不允许登录</p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">用户类型:</label>
		<div class="col-sm-5">
			<form:select path="userType" class="form-control">
				<form:option value="" label="请选择"/>
				<form:options items="${fns:getDictList('sys_user_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">用户角色:</label>
		<div class="col-sm-10">
			<form:checkboxes path="roleIdList" items="${allRoles}" itemLabel="name" itemValue="id" htmlEscape="false"/>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">备注:</label>
		<div class="col-sm-5">
			<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="form-control"/>
		</div>
	</div>
	<c:if test="${not empty user.id}">
		<div class="form-group">
			<label class="col-sm-2 control-label">创建时间:</label>
			<div class="col-sm-10">
				<p class="form-control-static"><fmt:formatDate value="${user.createDate}" type="both" dateStyle="full"/></p>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-2 control-label">最后登陆:</label>
			<div class="col-sm-10">
				<p class="form-control-static">IP: ${user.loginIp}&nbsp;&nbsp;&nbsp;&nbsp;时间：<fmt:formatDate value="${user.loginDate}" type="both" dateStyle="full"/></p>
			</div>
		</div>
	</c:if>
</form:form>

<div class="my-buttons">
	<shiro:hasPermission name="sys:user:edit"><button id="btnSubmit" class="btn i-btn-ok" type="button">保 存</button></shiro:hasPermission>
	<button id="btnCancel" class="btn btn-default" type="button" data-dismiss="modal">返回</button>
</div>
<script type="text/javascript">
	idefine('userForm',function(require,exports,module){
		var tools = require('udmp/lib/util_tool');
		$('body').on('run.userForm', function() {
			$("#btnSubmit").on("click", function() {
				$.post("${ctx}/sys/user/save", tools.serializeObject($("#inputForm")), function(data) {
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
	});
	seajs.use('userForm',function(){
		$('body').trigger("run.userForm");
	});
</script>
