<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
	<head>
		<title>机构管理</title>
		<meta name="decorator" content="bootstrap335" />
		<link href="${ctxUdmp}/css/params/system/userlist.css" type="text/css" rel="stylesheet" />
		<%@include file="/WEB-INF/views/include/treetable.jsp" %>
	</head>
	<body>
		<div class="container-fluid">
			<form:form id="searchForm" modelAttribute="office" method="post">
				<input id="id" name="id" type="hidden" value="${office.id}" />
				<input id="parentId" name="parentId" type="hidden" value="${office.parentId}" />
				<input id="parentIds" name="parentIds" type="hidden" value="${office.parentIds}" />
			</form:form>
			<div id="toolbar">
				<shiro:hasPermission name="sys:office:edit">
					<button type="button" class="btn btn-default table-btn" onclick="addOffice();">
						<span class="glyphicon glyphicon-plus"></span>&nbsp;添加机构
					</button>
				</shiro:hasPermission>
			</div>
			<table id="contentTable">
				<thead>
					<tr>
						<th data-field="name" data-valign="middle" data-formatter="nameFormatter">机构名称</th>
						<th data-field="area.name" data-valign="middle">归属区域</th>
						<th data-field="code" data-valign="middle">机构编码</th>
						<th data-field="type" data-valign="middle" data-formatter="typeFormatter">机构类型</th>
						<th data-field="remarks" data-valign="middle">备注</th>
						<shiro:hasPermission name="sys:office:edit">
							<th data-valign="middle" data-formatter="handleBtnFormatter">操作</th>
						</shiro:hasPermission>
					</tr>
				</thead>
			</table>
		</div>
		<script type="text/javascript">
			$(document).ready(function() {
				var options = {
					url : "${ctx}/sys/office/listData",
					pagination : false,
					toolbar : "#toolbar",
					rowAttributes : function(row, index) {
						//如果id不为空,则作为根节点
						if($("#id").val() && row.id == $("#id").val()){
							return {
								id : row['id'],
								pId : "0"
							};
						}
						// 配置上下级关联字段
						return {
							id : row['id'],
							pId : row['parentId']
						};
					}
				};
				git.table.initTree($("#contentTable"), options, $("#parentId").val() ? $("#parentId").val() : 0);
			});

			// 格式化机构名称
			function nameFormatter(value, row, index) {
				return "<a href='javascript:void(0);' onclick='editOffice(\"" + row.id + "\")'>" + row.name + "</a>";
			}

			// 格式化机构类型代码
			function typeFormatter(value, row, index) {
				return getDictLabel(${fns:toJson(fns:getDictList('sys_office_type'))}, row.type);
			}

			// 格式化操作按钮
			function handleBtnFormatter(value, row, index) {
				var returnValue = "";
				var editBtn = "<button type='button' title='修改' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-edit table-btn-column'><i class='icon git'>&#xe600;</i></button>";
				var deleteBtn = "<button type='button' title='删除' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-delete table-btn-column'><i class='icon git'>&#xe601;</i></button>";
				var childBtn = "<button type='button' title='添加下级机构' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-child table-btn-column'><i class='icon git'>&#xe603;</i></button>";
				returnValue += editBtn;
				returnValue += deleteBtn;
				returnValue += childBtn;
				return returnValue;
			}

			function addOffice(id) {
				top.window.showModal("${ctx}/sys/office/form?parent.id=" + id, null, {
					title : "添加机构",
					width : 800
				}, null, function() {
					git.table.query($('#contentTable'));
				});
			}

			function editOffice(id) {
				top.window.showModal("${ctx}/sys/office/form?id=" + id, null, {
					title : "修改机构",
					width : 800
				}, null, function() {
					git.table.query($('#contentTable'));
				});
			}

			function deleteOffice(id) {
				top.window.showConfirm("要删除该机构及所有子机构项吗？", function() {
					$.post("${ctx}/sys/office/delete?id=" + id, function(data) {
						if(data) {
							if(data.success) {
								top.window.showInfo("success", data.message);
								git.table.query($('#contentTable'));
							} else {
								top.window.showInfo("error", data.message);
							}
						}
					});
				});
			}

			$("#contentTable").on("click", ".table-btn-edit", function (e, value, row, index) {
				editOffice($(this).data("value"));
	        });

			$("#contentTable").on("click", ".table-btn-delete", function (e, value, row, index) {
				deleteOffice($(this).data("value"));
	        });

			$("#contentTable").on("click", ".table-btn-child", function (e, value, row, index) {
				addOffice($(this).data("value"));
	        });
		</script>
	</body>
</html>
