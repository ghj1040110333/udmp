<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
  <head>
    <title>角色管理</title>
    <%@ include file="common/header.jsp" %>
    <link href="${ctxStatic}/css/params/system/manage.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <%-- 流式布局容器 --%>
    <div class="container-fluid clearfix" id="i-manage-panel">
      <%-- 数据展示 --%>
      <udmp-data :itable="table" :url="tableUrl">
        <shiro:hasPermission name="sys:role:edit">
          <button type="button" class="btn i-btn" @click="addRole">
            <span class="glyphicon glyphicon-plus"></span>&nbsp;添加角色
          </button>
        </shiro:hasPermission>
      </upmp-data>
    </div>
    <%@ include file="common/footer.jsp" %>
    <script type="text/javascript">
      seajs.use('sys/roleIndex')
    </script>
  </body>
</html>
