<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>分配角色</title>
</head>
<body>
	<div class="container-fluid form-inline">
		<div class="row-fluid">
			<span class="col-sm-4">角色名称: <b>${role.name}</b></span> 
			<span class="col-sm-4">归属机构: ${role.office.name}</span> 
			<span class="col-sm-4">英文名称: ${role.enname}</span>
		</div>
		<div class="row-fluid">
			<span class="col-sm-4">角色类型: ${role.roleType}</span>
			<c:set var="dictvalue" value="${role.dataScope}" scope="page" />
			<span class="col-sm-4">数据范围: ${fns:getDictLabel(dictvalue, 'sys_data_scope', '')}</span>
		</div>
	</div>
	<div class="form-inline">
		<form id="assignRoleForm" action="${ctx}/sys/role/assignrole" method="post" class="hide">
			<input type="hidden" id="id" name="id" value="${role.id}"/>
			<input id="idsArr" type="hidden" name="idsArr" value=""/>
		</form>
		<input id="assignButton" class="btn i-btn-ok" type="submit" value="分配角色"/>
	</div>
	<table id="assignTable">
		<thead>
			<tr>
				<th data-field="company.name" data-valign="middle">归属公司</th>
				<th data-field="office.name" data-valign="middle">归属部门</th>
				<th data-field="loginName" data-valign="middle">登录名</th>
				<th data-field="name" data-valign="middle">姓名</th>
				<th data-field="phone" data-valign="middle">电话</th>
				<th data-field="mobile" data-valign="middle">手机</th>
				<shiro:hasPermission name="sys:role:edit">
					<th data-formatter="assignBtnFormatter" data-valign="middle">操作</th>
				</shiro:hasPermission>
			</tr>
		</thead>
	</table>
	<div class="my-buttons">
		<button id="btnCancel" class="btn btn-default" type="button" data-dismiss="modal">返回</button>
	</div>
	<script src="${ctxStatic}/jquery/jquery-1.11.3.min.js" type="text/javascript"></script>
	<script src="${ctxStatic}/bootstrap-table/bootstrap-table.min.js" type="text/javascript"></script>
  	<script src="${ctxUdmp}/js/git-common.js" type="text/javascript"></script>
  	<script src="${ctxUdmp}/js/git-ui.js" type="text/javascript"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			var options = {
				url : "${ctx}/sys/role/listAssignUser?id=${role.id}",
				uniqueId : 'id',
				pagination : false
			};
			git.table.init($("#assignTable"), options);
		});
		
		// 格式化操作按钮
		function assignBtnFormatter(value, row, index) {
			var returnValue = "<button type='button' title='移除' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-delete table-btn-column'><i class='glyphicon glyphicon-share'></i></button>";
			return returnValue;
		}
		
		function deleteUser(id) {
			var row = git.table.getRowByUniqueId($('#assignTable'), id);
			top.window.showConfirm("确认要将用户<b>[" + row.name + "]</b>从<b>[${role.name}]</b>角色中移除吗？", function() {
				$.post("${ctx}/sys/role/outrole?userId=" + row.id + "&roleId=${role.id}", function(data) {
					if(data) {
						if(data.success) {
							top.window.showInfo("success", data.message);
							git.table.query($('#assignTable'));
						} else {
							top.window.showInfo("error", data.message);
						}
					}
				});
			});
		}
		
		$("#assignTable").on("click", ".table-btn-delete", function (e, value, row, index) {
			deleteUser($(this).data("value"));
        });
		
		$("#assignButton").click(function(){
			top.showModal("${ctx}/sys/role/usertorole?id=${role.id}", null, {title : '分配角色', width : 810 }, null, function() {
				git.table.query($('#assignTable'));
			});
		});
	</script>
</body>
</html>
