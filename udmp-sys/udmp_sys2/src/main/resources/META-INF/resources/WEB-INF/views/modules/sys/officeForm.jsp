<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>机构管理</title>
<meta name="decorator" content="bootstrap335" />
</head>
<body>
	<form:form id="inputForm" modelAttribute="office" method="post" class="form-horizontal">
		<form:hidden path="id" />
		<div class="form-group">
			<label class="col-sm-3 control-label"> 上级机构:</label>
			<div class="col-sm-5">
				<sys:treeselect335 id="office" name="parent.id" value="${office.parent.id}" labelName="parent.name"
					labelValue="${office.parent.name}" title="机构" url="/sys/office/treeData" extId="${office.id}"
					cssClass="form-control" allowClear="${office.currentUser.admin}" dataMsgRequired="请选择上级机构" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 归属区域:</label>
			<div class="col-sm-5">
				<sys:treeselect335 id="area" name="area.id" value="${office.area.id}" labelName="area.name"
					labelValue="${office.area.name}" title="区域" url="/sys/area/treeData" cssClass="form-control"
					dataMsgRequired="请选择归属区域" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label">
				<span class="red">*</span> 机构名称:
			</label>
			<div class="col-sm-5">
				<form:input path="name" htmlEscape="false" maxlength="50" class="form-control" data-bv-notempty="true"
					data-bv-trigger="blur" data-bv-notempty-message="请输入机构名称" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 机构编码:</label>
			<div class="col-sm-5">
				<form:input path="code" htmlEscape="false" maxlength="50" class="form-control" data-bv-trigger="blur" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 机构类型:</label>
			<div class="col-sm-5">
				<form:select path="type" class="form-control">
					<form:options items="${fns:getDictList('sys_office_type')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 机构级别:</label>
			<div class="col-sm-5">
				<form:select path="grade" class="form-control">
					<form:options items="${fns:getDictList('sys_office_grade')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 是否可用:</label>
			<div class="col-sm-2">
				<form:select path="useable" class="form-control">
					<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
			<div class="col-sm-7">
				<p class="form-control-static">“是”代表此账号允许登陆，“否”则表示此账号不允许登陆</p>
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 主负责人:</label>
			<div class="col-sm-5">
				<sys:treeselect335 id="primaryPerson" name="primaryPerson.id" value="${office.primaryPerson.id}"
					labelName="office.primaryPerson.name" labelValue="${office.primaryPerson.name}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="form-control" allowClear="true" notAllowSelectParent="true" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 副负责人:</label>
			<div class="col-sm-5">
				<sys:treeselect335 id="deputyPerson" name="deputyPerson.id" value="${office.deputyPerson.id}"
					labelName="office.deputyPerson.name" labelValue="${office.deputyPerson.name}" title="用户"
					url="/sys/office/treeData?type=3" cssClass="form-control" allowClear="true" notAllowSelectParent="true" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 联系地址:</label>
			<div class="col-sm-5">
				<form:input path="address" htmlEscape="false" maxlength="50" class="form-control" data-bv-trigger="blur" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 邮政编码:</label>
			<div class="col-sm-5">
				<form:input path="zipCode" htmlEscape="false" maxlength="50" class="form-control" data-bv-trigger="blur" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 负责人:</label>
			<div class="col-sm-5">
				<form:input path="master" htmlEscape="false" maxlength="50" class="form-control" data-bv-trigger="blur" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 电话:</label>
			<div class="col-sm-5">
				<form:input path="phone" htmlEscape="false" maxlength="50" class="form-control" data-bv-trigger="blur" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 传真:</label>
			<div class="col-sm-5">
				<form:input path="fax" htmlEscape="false" maxlength="50" class="form-control" data-bv-trigger="blur" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 邮箱:</label>
			<div class="col-sm-5">
				<form:input path="email" htmlEscape="false" maxlength="100" class="form-control" data-bv-emailaddress="true"
					data-bv-emailaddress-message="请输入有效的电子邮箱" data-bv-trigger="blur" />
			</div>
		</div>
		<div class="form-group">
			<label class="col-sm-3 control-label"> 备注:</label>
			<div class="col-sm-5">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="form-control"
					data-bv-trigger="blur" />
			</div>
		</div>
		<c:if test="${empty office.id}">
			<div class="form-group">
				<label class="col-sm-3 control-label"> 快速添加下级部门:</label>
				<div class="col-sm-5">
					<form:checkboxes path="childDeptList" items="${fns:getDictList('sys_office_common')}" itemLabel="label"
						itemValue="value" htmlEscape="false" />
				</div>
			</div>
		</c:if>
	</form:form>

	<div class="my-buttons">
		<shiro:hasPermission name="sys:office:edit">
			<button id="btnSubmit" class="btn i-btn-ok" type="button">保 存</button>
		</shiro:hasPermission>
		<button id="btnCancel" class="btn btn-default" type="button" data-dismiss="modal">返回</button>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			$("#btnSubmit").on("click", function() {
				$.post("${ctx}/sys/office/save", git.serializeObject($("#inputForm")), function(data) {
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