<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>用户管理</title>
<%@ include file="common/header.jsp" %>
</head>
<body>
	<div class="container-fluid">
		<form:form id="searchForm" modelAttribute="user" method="post" class="i-query-table form-inline">
			<input name="office.id" type="hidden" value="${user.company.id}" />
			<div class="form-group">
				<label for="company" class="sr-only">归属公司</label>
				<sys:treeselect335 id="company" name="company.id" value="${user.company.id}" labelName="company.name"
					labelValue="${user.company.name}" title="归属公司" url="/sys/office/treeData?type=1" cssClass="form-control"
					allowClear="true" />
			</div>
			<div class="form-group">
				<label for="loginName" class="sr-only">登录名</label>
				<input name="loginName" htmlEscape="false" maxlength="50" class="form-control" placeholder="登录名"
					value="${user.loginName}" />
			</div>
			<div class="form-group">
				<label for="name" class="sr-only">姓名</label>
				<input name="name" htmlEscape="false" maxlength="50" class="form-control" placeholder="姓名" value="${user.name}" />
			</div>
			
			<button id="btnSubmit" class="btn i-btn-ok" type="button">查询</button>
		</form:form>
		
		<div id="toolbar">
			<shiro:hasPermission name="sys:user:edit">
				<button type="button" class="btn btn-default table-btn" onclick="addUser();">
					<span class="glyphicon glyphicon-plus"></span>&nbsp;添加用户
				</button>
			</shiro:hasPermission>
			<button id="btnImport" type="button" class="btn btn-default table-btn">
				<span class="glyphicon glyphicon-save"></span>&nbsp;导入
			</button>
			<button id="btnExport" type="button" class="btn btn-default table-btn">
				<span class="glyphicon glyphicon-open"></span>&nbsp;导出
			</button>
		</div>
		
		<table id="contentTable">
			<thead>
				<tr>
					<th data-field="company.name" data-valign="middle">归属公司</th>
					<th data-field="office.name" data-valign="middle">归属部门</th>
					<th data-field="loginName" data-valign="middle">登录名</th>
					<th data-field="name" data-valign="middle">姓名</th>
					<th data-field="phone" data-valign="middle">电话</th>
					<th data-field="mobile" data-valign="middle">手机</th>
					<shiro:hasPermission name="sys:user:edit">
						<th data-formatter="handleBtnFormatter" data-valign="middle">操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>
		</table>
	</div>
	
	<%@ include file="common/footer.jsp" %>
	<script type="text/javascript">
	idefine('userList',function(require,exports,module){
		var tb = require('udmp/lib/bs_table'),
			wt = window.top,
			_show = 1;
		$(document).ready(function() {
			var options = {
				url : "${ctx}/sys/user/listData",
				toolbar : "#toolbar"
			};
			tb.init($("#contentTable"), options);
			// Fires after the table body is rendered and available in the DOM
			$('#contentTable').on('post-body.bs.table', function() {
				if (_show % 2 == 1) {
					setTimeout(wt.hideLoading, 1000);
					$("#contentTable tbody tr:first").is(".no-records-found") ? _show += 2 :
						_show++;
				} else {
					_show++;
				}
				$('body').resize();
			});
			$("input[name='loginName']").focus();
		});
		
		// 格式化操作按钮
		function handleBtnFormatter(value, row, index) {
			var returnValue = "";
			var editBtn = "<button type='button' title='修改' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-edit table-btn-column'><i class='icon git'>&#xe600;</i></button>";
			var deleteBtn = "<button type='button' title='删除' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-delete table-btn-column'><i class='icon git'>&#xe601;</i></button>";
			returnValue += editBtn;
			returnValue += deleteBtn;
			return returnValue;
		}
		
		function addUser() {
			top.window.showModal("${ctx}/sys/user/form", null, {
				title : "添加用户",
				width : 800
			}, null, function() {
				$("#btnSubmit").click();
			});
		}
		
		function editUser(uid) {
			top.window.showModal("${ctx}/sys/user/form?id=" + uid, null, {
				title : "修改用户",
				width : 800
			}, null, function() {
				$("#btnSubmit").click();
			});
		}
		
		function deleteUser(id) {
			top.window.showConfirm("确定要删除这个用户吗？", function() {
				$.post("${ctx}/sys/user/delete?id=" + id, function(data) {
					if(data) {
						if(data.success) {
							top.window.showInfo("success", data.message);
							$("#btnSubmit").click();
						} else {
							top.window.showInfo("error", data.message);
						}
					}
				});
			});
		}
		
		$("#contentTable").on("click", ".table-btn-edit", function (e, value, row, index) {
			editUser($(this).data("value"));
        });
		
		$("#contentTable").on("click", ".table-btn-delete", function (e, value, row, index) {
			deleteUser($(this).data("value"));
        });
		// 查询事件
		$('#btnSubmit').on('click', function(e) {
			e.preventDefault();
			wt.showLoading();
			$("#contentTable").bootstrapTable('removeAll').bootstrapTable('refresh')
				.hide();
			$('.page-first').trigger('click');
			$("#contentTable").show();
		});
	})
	seajs.use('userList');
	</script>
</body>
</html>