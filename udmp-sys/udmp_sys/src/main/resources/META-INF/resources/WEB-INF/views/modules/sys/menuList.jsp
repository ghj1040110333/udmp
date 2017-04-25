<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>菜单管理</title>
<meta name="decorator" content="bootstrap335" />
<link href="${ctxUdmp}/css/params/system/userlist.css" type="text/css" rel="stylesheet" />
<%@include file="/WEB-INF/views/include/treetable.jsp"%>
</head>
<body>
	<div class="container-fluid">
		<div id="toolbar">
			<shiro:hasPermission name="sys:menu:edit">
				<button type="button" class="btn btn-default table-btn" onclick="addMenu();">
					<span class="glyphicon glyphicon-plus"></span>&nbsp;添加菜单
				</button>
				<button type="button" class="btn btn-default table-btn" onclick="updateSort();">
					保存排序
				</button>
			</shiro:hasPermission>
		</div>
		<form id="listForm" method="post">
			<table id="contentTable">
				<thead>
					<tr>
						<th data-field="name" data-valign="middle" data-formatter="nameFormatter">名称</th>
						<th data-field="href" data-valign="middle" data-formatter="abbrFormatter">链接</th>
						<th data-field="sort" data-valign="middle" data-formatter="sortFormatter">排序</th>
						<th data-field="isShow" data-valign="middle" data-formatter="isShowFormatter">可见</th>
						<th data-field="permission" data-valign="middle" data-formatter="abbrFormatter">权限标识</th>
						<shiro:hasPermission name="sys:menu:edit">
							<th data-valign="middle" data-formatter="handleBtnFormatter">操作</th>
						</shiro:hasPermission>
					</tr>
				</thead>
			</table>
		</form>
	</div>
	<script type="text/javascript">
		$(document).ready(function() {
			var options = {
				url : "${ctx}/sys/menu/listData",
				pagination : false,
				toolbar : "#toolbar",
				rowAttributes : function(row, index) {
					// 隐藏根节点
					if(row.parentId == 0){
						return {
							id : row['id'],
							pId : row['parentId'],
							class : 'hide'
						};
					}
					// 配置上下级关联字段
					return {
						id : row['id'],
						pId : row['parentId']
					};
				}
			};
			git.table.initTree($("#contentTable"), options);
		});
		
		// 格式化名称
		function nameFormatter(value, row, index) {
			var returnValue =  "";
			if(row.icon) {
				returnValue += "<i class='glyphicon glyphicon-" + row.icon + "'></i>";
			} else {
				returnValue += "<i class='hide'></i>";
			}
			returnValue +=  "&nbsp;<a href='javascript:void(0);' onclick='editMenu(\"" + row.id + "\")'>";
			returnValue += row.name;
			returnValue += "</a>";
			return returnValue;
		}
		
		// 格式化排序
		function sortFormatter(value, row, index) {
			var returnValue =  "<shiro:hasPermission name='sys:menu:edit'>";
			returnValue += "<input type='hidden' name='ids' value='" + row.id + "'/>";
			returnValue += "<input name='sorts' class='form-control' type='text' value='" + row.sort + "' style='width: 50px; margin: 0; padding: 0; text-align: center;'>";
			returnValue += "</shiro:hasPermission>";
			returnValue += "<shiro:lacksPermission name='sys:menu:edit'>";
			returnValue += row.sort;
			returnValue += "</shiro:lacksPermission>";
			return returnValue;
		}
		
		// 格式化可见
		function isShowFormatter(value, row, index) {
			if(value == 1) {
				return "显示"
			} else {
				return "隐藏";
			}
		}
		
		// 缩写
		function abbrFormatter(value, row, index) {
			return git.abbr(value, 30);
		}
		
		// 格式化操作按钮
		function handleBtnFormatter(value, row, index) {
			var returnValue = "";
			var editBtn = "<button type='button' title='修改' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-edit table-btn-column'><i class='icon git'>&#xe600;</i></button>";
			var deleteBtn = "<button type='button' title='删除' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-delete table-btn-column'><i class='icon git'>&#xe601;</i></button>";
			var childBtn = "<button type='button' title='添加下级菜单' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-child table-btn-column'><i class='icon git'>&#xe603;</i></button>";
			returnValue += editBtn;
			returnValue += deleteBtn;
			returnValue += childBtn;
			return returnValue;
		}
		
		function addMenu(id) {
			top.showModal("${ctx}/sys/menu/form?parent.id=" + id, null, {
				title : "添加菜单",
				width : 1000
			}, null, function() {
				git.table.query($('#contentTable'));
			});
		}
		
		function editMenu(id) {
			top.showModal("${ctx}/sys/menu/form?id=" + id, null, {
				title : "修改菜单",
				width : 1000
			}, null, function() {
				git.table.query($('#contentTable'));
			});
		}
		
		function deleteMenu(id) {
			top.window.showConfirm("要删除该菜单及所有子菜单项吗？", function() {
				$.post("${ctx}/sys/menu/delete?id=" + id, function(data) {
					if(data) {
						if(data.success) {
							top.window.showInfo("success", data.message);
							git.table.query($("#contentTable"));
						} else {
							top.window.showInfo("error", data.message);
						}
					}
				});
			});
		}
		
		$("#contentTable").on("click", ".table-btn-edit", function (e, value, row, index) {
			editMenu($(this).data("value"));
        });
		
		$("#contentTable").on("click", ".table-btn-delete", function (e, value, row, index) {
			deleteMenu($(this).data("value"));
        });
		
		$("#contentTable").on("click", ".table-btn-child", function (e, value, row, index) {
			addMenu($(this).data("value"));
        });
		
		//保存排序
		function updateSort() {
			var params = git.serializeObject($("#listForm"));
			$.post("${ctx}/sys/menu/updateSort", params, function(data) {
				if (data) {
					if (data.success) {
						top.window.showInfo("success", data.message);
						git.table.query($("#contentTable"));
					} else {
						top.window.showInfo("error", data.message);
					}
				}
			});
    	}
	</script>
</body>
</html>