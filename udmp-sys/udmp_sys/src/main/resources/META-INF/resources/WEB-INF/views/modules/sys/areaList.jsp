<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>区域管理</title>
<meta name="decorator" content="bootstrap335" />
<link href="${ctxUdmp}/css/params/system/userlist.css" type="text/css" rel="stylesheet" />
<%@include file="/WEB-INF/views/include/treetable.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<div id="toolbar">
			<shiro:hasPermission name="sys:area:edit">
				<button type="button" class="btn btn-default table-btn" onclick="addArea();">
					<span class="glyphicon glyphicon-plus"></span>&nbsp;添加区域
				</button>
			</shiro:hasPermission>
		</div>
		<table id="contentTable">
			<thead>
				<tr>
					<th data-field="name" data-valign="middle" data-formatter="nameFormatter">区域名称</th>
					<th data-field="code" data-valign="middle">区域编码</th>
					<th data-field="type" data-valign="middle" data-formatter="typeFormatter">区域类型</th>
					<th data-field="remarks" data-valign="middle">备注</th>
					<shiro:hasPermission name="sys:area:edit">
						<th data-valign="middle" data-formatter="handleBtnFormatter">操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>
		</table>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			var options = {
				url : "${ctx}/sys/area/listData",
				pagination : false,
				toolbar : "#toolbar",
				rowAttributes : function(row, index) {
					var pIds = row['parentIds'].split(',');
					var pId = pIds[pIds.length - 1];
					// 配置上下级关联字段
					return {
						id : row['id'],
						pId : pId
					};
				}
			};
			git.table.initTree($("#contentTable"), options);
		});
		
		// 格式化机构名称
		function nameFormatter(value, row, index) {
			return "<a href='javascript:void(0);' onclick='editArea(\"" + row.id + "\")'>" + row.name + "</a>";
		}
		
		// 格式化机构类型代码
		function typeFormatter(value, row, index) {
			return getDictLabel(${fns:toJson(fns:getDictList('sys_area_type'))}, row.type);
		}
		
		// 格式化操作按钮
		function handleBtnFormatter(value, row, index) {
			var returnValue = "";
			var editBtn = "<button type='button' title='修改' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-edit table-btn-column'><i class='icon git'>&#xe600;</i></button>";
			var deleteBtn = "<button type='button' title='删除' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-delete table-btn-column'><i class='icon git'>&#xe601;</i></button>";
			var childBtn = "<button type='button' title='添加下级区域' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-child table-btn-column'><i class='icon git'>&#xe603;</i></button>";
			returnValue += editBtn;
			returnValue += deleteBtn;
			returnValue += childBtn;
			return returnValue;
		}
		
		function addArea(id) {
			top.showModal("${ctx}/sys/area/form?parent.id=" + id, null, {
				title : "添加区域",
				width : 800
			}, null, function() {
				git.table.query($('#contentTable'));
			});
		}
		
		function editArea(id) {
			top.showModal("${ctx}/sys/area/form?id=" + id, null, {
				title : "修改区域",
				width : 800
			}, null, function() {
				git.table.query($('#contentTable'));
			});
		}
		
		function deleteArea(id) {
			top.window.showConfirm("要删除该区域及所有子区域项吗？", function() {
				$.post("${ctx}/sys/area/delete?id=" + id, function(data) {
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
			editArea($(this).data("value"));
        });
		
		$("#contentTable").on("click", ".table-btn-delete", function (e, value, row, index) {
			deleteArea($(this).data("value"));
        });
		
		$("#contentTable").on("click", ".table-btn-child", function (e, value, row, index) {
			addArea($(this).data("value"));
        });
	</script>
</body>
</html>