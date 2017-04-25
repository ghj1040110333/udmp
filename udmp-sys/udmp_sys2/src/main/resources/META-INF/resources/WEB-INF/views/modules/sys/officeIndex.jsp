<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
  <head>
    <title>机构管理</title>
    <%@ include file="common/header.jsp" %>
    <link href="${ctxStatic}/lib/tree-table/themes/vsStyle/treeTable.min.css" rel="stylesheet" type="text/css" />
    <link href="${ctxStatic}/css/params/system/manage.css" rel="stylesheet" type="text/css" />
    <style>
      .i-title.p-t5 {padding: 0 0 5px 0}
    </style>
  </head>
<body>
  <%-- 流式布局容器 --%>
  <div class="container-fluid clearfix" id="i-manage-panel">

    <%-- 左侧面板 --%>
    <div class="orgTree">
      <div class="i-fix-ie">
        <ol class="breadcrumb">
          <li class="active">
            <em>组织机构</em><i class="glyphicon glyphicon-refresh pull-right" id="i-group-refresh"></i>
          </li>
        </ol>
        <%-- tree组件 --%>
        <udmp-ztree :url="treeUrl" operate="treetable"></udmp-ztree>
      </div>
    </div>
    <%-- 右侧面板 --%>
    <div class="container-fluid" id="manageContent">
      <%-- 表单面板 --%>
      <form id="searchForm" class="i-query-table" method="post">
        <input id="id" name="id" type="hidden" value="${office.id}" />
        <input id="parentId" name="parentId" type="hidden" value="${office.parentId}" />
        <input id="parentIds" name="parentIds" type="hidden" value="${office.parentIds}" />
      </form>
      <%-- 数据展示 --%>
      <udmp-data :itable="table" :url="tableUrl" :options="options">
        <shiro:hasPermission name="sys:office:edit">
          <button type="button" class="btn i-btn" @click="addOffice">
            <span class="glyphicon glyphicon-plus"></span>&nbsp;添加机构
          </button>
        </shiro:hasPermission>
      </upmp-data>
    </div>
  </div>
  <%@ include file="common/footer.jsp" %>
  <script type="text/javascript">
    seajs.use('sys/officeIndex')
  </script>
</body>
</html>
