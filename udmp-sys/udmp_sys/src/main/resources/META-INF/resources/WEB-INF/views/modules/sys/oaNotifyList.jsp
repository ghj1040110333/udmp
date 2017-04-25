<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>通知管理</title>
<meta name="decorator" content="bootstrap335" />
<link href="${ctxUdmp}/css/params/system/userlist.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div class="container-fluid">
		<form:form id="searchForm" modelAttribute="oaNotify" method="post" class="form-inline">
			<input id="self" name="self" type="hidden" value="${oaNotify.self}"/>
			<div class="form-group">
				<label for="title" class="sr-only">标题</label>
				<form:input path="title" htmlEscape="false" maxlength="200" class="form-control" placeholder="标题"/>
			</div>
			<div class="form-group">
				<label for="type" class="sr-only">类型</label>
				<form:select id="type" path="type" style="width:150px;" placeholder="类型">
					<form:option value="" label="" />
					<form:options items="${fns:getDictList('oa_notify_type')}" itemLabel="label" itemValue="value" htmlEscape="false" />
				</form:select>
			</div>
			<c:if test="${!oaNotify.self}">
				<div class="form-group">
					<label for="status" class="sr-only">状态</label>
					<form:radiobuttons path="status" items="${fns:getDictList('oa_notify_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</div>
			</c:if>
			<button id="btnSubmit" class="btn i-btn-ok" type="button" onclick="return git.table.query($('#contentTable'));">查询</button>
		</form:form>

		<div id="toolbar">
			<c:if test="${!oaNotify.self}">
				<shiro:hasPermission name="sys:oaNotify:edit">
					<button type="button" class="btn btn-default table-btn" onclick="addOaNotify();">
						<span class="glyphicon glyphicon-plus"></span>&nbsp;添加通知
					</button>
				</shiro:hasPermission>
			</c:if>
		</div>

		<table id="contentTable">
			<thead>
				<tr>
					<th data-field="title" data-valign="middle" data-formatter="titleFormatter">标题</th>
					<th data-field="type" data-valign="middle" data-formatter="typeFormatter">类型</th>
					<th data-field="status" data-valign="middle" data-formatter="statusFormatter">状态</th>
					<th data-field="readFlag" data-valign="middle" data-formatter="readFlagFormatter">查阅状态</th>
					<th data-field="updateDate" data-valign="middle">更新时间</th>
					<c:if test="${!oaNotify.self}">
						<shiro:hasPermission name="sys:oaNotify:edit">
							<th data-formatter="handleBtnFormatter" data-valign="middle">操作</th>
						</shiro:hasPermission>
					</c:if>
				</tr>
			</thead>
		</table>
	</div>

	<script type="text/javascript">
		$(document).ready(function() {
			var options = {
				url : "${ctx}/sys/oaNotify/listData",
				toolbar : "#toolbar"
			};
			git.table.init($("#contentTable"), options);
		});

		// 格式化标题
		function titleFormatter(value, row, index) {
			return "<a href='javascript:void(0);' onclick='editOaNotify(\"" + row.id
			+ "\",\"view\")'>" + row.title + "</a>";
		}

		// 格式化类型
		function typeFormatter(value, row, index) {
			return getDictLabel(${fns:toJson(fns:getDictList('oa_notify_type'))}, value);
		}

		// 格式化状态
		function statusFormatter(value, row, index) {
			return getDictLabel(${fns:toJson(fns:getDictList('oa_notify_status'))}, value);
		}

		// 格式化查阅状态
		function readFlagFormatter(value, row, index) {
			if(${oaNotify.self}){
				return getDictLabel(${fns:toJson(fns:getDictList('oa_notify_read'))}, value);
			} else {
				var count = Number(row.readNum) + Number(row.unReadNum);
				return row.readNum + "/" + count;
			}
		}

		// 格式化操作按钮
		function handleBtnFormatter(value, row, index) {
			var returnValue = "";
			var editBtn = "<button type='button' title='修改' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-edit table-btn-column'><i class='icon git'>&#xe600;</i></button>";
			var deleteBtn = "<button type='button' title='删除' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-delete table-btn-column'><i class='icon git'>&#xe601;</i></button>";
			returnValue += editBtn;
			returnValue += deleteBtn;
			return returnValue;
		}

		function addOaNotify() {
			top.window.showModal("${ctx}/sys/oaNotify/form", null, {
				title : "添加通知",
				width : 800
			}, null, function() {
				$("#btnSubmit").click();
			});
		}

		function editOaNotify(id, type) {
			top.window.showModal("${ctx}/sys/oaNotify/${oaNotify.self?'view':'form'}?id=" + id, null, {
				title : type == "view" ? "查看通知" : "修改通知",
				width : 800
			}, null, function() {
				$("#btnSubmit").click();
			});
		}

		function deleteOaNotify(id) {
			top.window.showConfirm("确认要删除该通知吗？", function() {
				$.post("${ctx}/sys/oaNotify/delete?id=" + id, function(data) {
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

		$("#contentTable").on("click", ".table-btn-edit",
				function(e, value, row, index) {
					editOaNotify($(this).data("value"));
				});

		$("#contentTable").on("click", ".table-btn-delete",
				function(e, value, row, index) {
					deleteOaNotify($(this).data("value"));
				});
	</script>
</body>
</html>