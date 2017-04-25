<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
  <head>
    <title>区域管理</title>
    <%@ include file="common/header.jsp" %>
    <link href="${ctxStatic}/lib/tree-table/themes/vsStyle/treeTable.min.css" rel="stylesheet" type="text/css" />
		<link href="${ctxStatic}/css/params/system/manage.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <%-- 流式布局容器 --%>
    <div class="container-fluid clearfix" id="i-manage-panel">
			<%-- 数据展示 --%>
      <udmp-data :itable="table" :url="tableUrl">
				<shiro:hasPermission name="sys:area:edit">
					<button type="button" class="btn i-btn" @click="addArea">
						<span class="glyphicon glyphicon-plus"></span>&nbsp;添加区域
					</button>
				</shiro:hasPermission>
      </upmp-data>
    </div>
		<%@ include file="common/footer.jsp" %>
		<script type="text/javascript">
			seajs.use('sys/areaIndex')
		// rowAttributes : function(row, index) {
		// 	var pIds = row['parentIds'].split(',');
		// 	var pId = pIds[pIds.length - 1];
		// 	// 配置上下级关联字段
		// 	return {
		// 		id : row['id'],
		// 		pId : pId
		// 	};
		// }
		</script>
  </body>
</html>
