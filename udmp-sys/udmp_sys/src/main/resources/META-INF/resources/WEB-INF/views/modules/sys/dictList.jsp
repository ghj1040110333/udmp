<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>字典管理</title>
<meta name="decorator" content="bootstrap335" />
<link href="${ctxUdmp}/css/params/system/userlist.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div class="container-fluid">
		<form:form id="searchForm" modelAttribute="dict" method="post" class="form-inline">
			<div class="form-group">
				<label for="type">类型:</label>
				<form:select id="type" path="type" style="width:200px;" >
					<form:option value="" label=" " />
				</form:select>
			</div>
			<div class="form-group">
				<label for="description">描述:</label>
				<input name="description" htmlEscape="false" maxlength="50" class="form-control" value="" />
			</div>
			<button id="btnSubmit" class="btn i-btn-ok" type="button" onclick="return git.table.query($('#contentTable'));">查询</button>
		</form:form>

		<div id="toolbar">
			<shiro:hasPermission name="sys:dict:edit">
				<button type="button" class="btn btn-default table-btn" onclick="addDict();">
					<span class="glyphicon glyphicon-plus"></span>&nbsp;添加字典
				</button>
			</shiro:hasPermission>
		</div>

		<table id="contentTable">
			<thead>
				<tr>
					<th data-field="value" data-valign="middle">键值</th>
					<th data-field="label" data-valign="middle" data-formatter="labelFormatter">标签</th>
					<th data-field="type" data-valign="middle" data-formatter="typeFormatter">类型</th>
					<th data-field="description" data-valign="middle">描述</th>
					<th data-field="sort" data-valign="middle">排序</th>
					<shiro:hasPermission name="sys:dict:edit">
						<th data-formatter="handleBtnFormatter" data-valign="middle">操作</th>
					</shiro:hasPermission>
				</tr>
			</thead>
		</table>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			var options = {
				url : "${ctx}/sys/dict/listPageData",
				uniqueId : 'id',
				toolbar : "#toolbar"
			};
			git.table.init($("#contentTable"), options);
			loadTypeList();
		});
		
		// 加载类型列表
		function loadTypeList() {
			$.post("${ctx}/sys/dict/listType", function(data) {
				if(data) {
					$("#type")[0].options.length = 0;
					$("#type")[0].options.add(new Option(" ", ""));
					$.each(data, function(index) {
						$("#type")[0].options.add(new Option(this, this));
					});
				}
			});
		} 

		// 格式化标签
		function labelFormatter(value, row, index) {
			return "<a href='javascript:void(0);' onclick='editDict(\"" + row.id
					+ "\")'>" + row.label + "</a>";
		}

		// 格式化类型
		function typeFormatter(value, row, index) {
			return "<a href='javascript:void(0);' onclick='searchByType(\""
					+ row.type + "\")'>" + row.type + "</a>";
		}

		// 按类型查找
		function searchByType(type) {
			$("#type").val(type);
			git.table.query($('#contentTable'));
		}
		
		// 格式化操作按钮
		function handleBtnFormatter(value, row, index) {
			var returnValue = "";
			var editBtn = "<button type='button' title='修改' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-edit table-btn-column'><i class='icon git'>&#xe600;</i></button>";
			var deleteBtn = "<button type='button' title='删除' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-delete table-btn-column'><i class='icon git'>&#xe601;</i></button>";
			var addKVBtn = "<button type='button' title='添加键值' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-child table-btn-column'><i class='icon git'>&#xe604;</i></button>";
			returnValue += editBtn;
			returnValue += deleteBtn;
			returnValue += addKVBtn;
			return returnValue;
		}

		function addDict() {
			top.showModal("${ctx}/sys/dict/form", null, {
				title : "添加字典",
				width : 600
			}, null, function() {
				loadTypeList();
				$("#btnSubmit").click();
			});
		}

		function editDict(id) {
			top.showModal("${ctx}/sys/dict/form?id=" + id, null, {
				title : "修改字典",
				width : 600
			}, null, function() {
				loadTypeList();
				$("#btnSubmit").click();
			});
		}

		function addDictKV(id) {
			var row = git.table.getRowByUniqueId($('#contentTable'), id);
			top.showModal("${ctx}/sys/dict/form?type=" + row.type + "&sort=" + row.sort + "&description=" + row.description, null, {
				title : "添加键值",
				width : 600
			}, null, function() {
				loadTypeList();
				$("#btnSubmit").click();
			});
		}

		function deleteDict(id) {
			top.window.showConfirm("确认要删除该字典吗？", function() {
				$.post("${ctx}/sys/dict/delete?id=" + id, function(data) {
					if(data) {
						if(data.success) {
							top.window.showInfo("success", data.message);
							loadTypeList();
							$("#btnSubmit").click();
						} else {
							top.window.showInfo("error", data.message);
						}
					}
				});
			});
		}

		$("#contentTable").on("click", ".table-btn-edit",
				function(e, value, row, index) {
					editDict($(this).data("value"));
				});

		$("#contentTable").on("click", ".table-btn-delete",
				function(e, value, row, index) {
					deleteDict($(this).data("value"));
				});
		
		$("#contentTable").on("click", ".table-btn-child",
				function(e, value, row, index) {
					addDictKV($(this).data("value"));
				});
	</script>
</body>
</html>