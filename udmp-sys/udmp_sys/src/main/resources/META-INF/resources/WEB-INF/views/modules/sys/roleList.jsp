<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>角色管理</title>
<meta name="decorator" content="bootstrap335" />
<link href="${ctxUdmp}/css/params/system/userlist.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div class="container-fluid">
		<div id="toolbar">
			<shiro:hasPermission name="sys:role:edit">
				<button type="button" class="btn btn-default table-btn" onclick="addRole();">
					<span class="glyphicon glyphicon-plus"></span>&nbsp;添加角色
				</button>
			</shiro:hasPermission>
		</div>
		<table id="contentTable">
			<thead>
				<tr>
					<th data-field="name" data-valign="middle">角色名称</th>
					<th data-field="enname" data-valign="middle">英文名称</th>
					<!-- 由于非管理员无法查询机构信息, 这里默认显示name, 在格式化方法中判断返回归属机构 -->
					<th data-field="name" data-valign="middle" data-formatter="officeFormatter">归属机构</th>
					<th data-field="dataScope" data-valign="middle">数据范围</th>
					<shiro:hasPermission name="sys:role:edit">
						<th data-formatter="handleBtnFormatter" data-valign="middle">操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>
		</table>
	</div>
	
	<script type="text/javascript">
		$(document).ready(function() {
			var options = {
				url : "${ctx}/sys/role/listData",
				pagination : false,
				toolbar : "#toolbar"
			};
			git.table.init($("#contentTable"), options);
			
		});
		
		// 格式化机构名称
		function officeFormatter(value, row, index) {
			if(row.hasOwnProperty("office")) {
				return row.office.name;
			} else {
				return "";
			}
		}
		
		// 格式化操作按钮
		function handleBtnFormatter(value, row, index) {
			var returnValue = "";
			var assignBtn = "<button type='button' title='分配' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-assign table-btn-column'><i class='icon git'>&#xe602;</i></button>";
			var editBtn = "<button type='button' title='修改' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-edit table-btn-column'><i class='icon git'>&#xe600;</i></button>";
			var deleteBtn = "<button type='button' title='删除' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-delete table-btn-column'><i class='icon git'>&#xe601;</i></button>";
			returnValue += assignBtn;
			returnValue += editBtn;
			returnValue += deleteBtn;
			return returnValue;
		}
		
		function assignRole(id){
			top.showModal("${ctx}/sys/role/assign?id=" + id, null, {
				title : "分配角色",
				width : 1000
			}, null, function() {
				git.table.query($("#contentTable"));
			});
		}
		
		function addRole() {
			top.showModal("${ctx}/sys/role/form", null, {
				title : "添加角色",
				width : 1000
			}, null, function() {
				git.table.query($("#contentTable"));
			});
		}
		
		function editRole(id) {
			top.showModal("${ctx}/sys/role/form?id=" + id, null, {
				title : "修改角色",
				width : 1000
			}, null, function() {
				git.table.query($("#contentTable"));
			});
		}
		
		function deleteRole(id) {
			top.window.showConfirm("确认要删除该角色吗？", function() {
				$.post("${ctx}/sys/role/delete?id=" + id, function(data) {
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
		
		$("#contentTable").on("click", ".table-btn-assign", function (e, value, row, index) {
			assignRole($(this).data("value"));
        });
		
		$("#contentTable").on("click", ".table-btn-edit", function (e, value, row, index) {
			editRole($(this).data("value"));
        });
		
		$("#contentTable").on("click", ".table-btn-delete", function (e, value, row, index) {
			deleteRole($(this).data("value"));
        });
	</script>
</body>
</html>