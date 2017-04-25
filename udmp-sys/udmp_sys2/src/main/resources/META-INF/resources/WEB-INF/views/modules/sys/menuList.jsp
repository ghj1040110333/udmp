<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
  <head>
    <title>菜单管理</title>
    <%@ include file="common/header.jsp" %>
    <link href="${ctxStatic}/lib/tree-table/themes/vsStyle/treeTable.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctxStatic}/css/params/system/manage.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <%-- 流式布局容器 --%>
    <div class="container-fluid clearfix" id="i-manage-panel">
      <%-- 数据展示 --%>
      <udmp-data :itable="table" :url="tableUrl" :options="options">
        <shiro:hasPermission name="sys:menu:edit">
          <button type="button" class="btn i-btn" @click="addMenu">
            <span class="glyphicon glyphicon-plus"></span>&nbsp;添加菜单
          </button>
          <button type="button" class="btn i-btn" @click="updateSort">
            保存排序
          </button>
        </shiro:hasPermission>
      </udmp-data>
    </div>
    <%@ include file="common/footer.jsp" %>
    <script type="text/javascript">
      seajs.use('sys/menuIndex');
    </script>
  </body>
</html>
