<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
  <head>
    <title>用户管理</title>
    <%@ include file="common/header.jsp" %>
    <link href="${ctxStatic}/css/params/system/manage.css" type="text/css" rel="stylesheet" />
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
          <udmp-ztree :url="treeUrl"></udmp-ztree>
        </div>
      </div>

      <%-- 右侧面板 --%>
      <div class="container-fluid" id="manageContent">
        <%-- 查询模块 --%>
        <udmp-search>
          <input name="office.id" type="hidden" value="${user.company.id}" />
          <div class="form-group">
            <label for="company" class="sr-only">归属公司</label>
            <sys:treeselect335 id="company" name="company.id" value="${user.company.id}" labelName="company.name"
                 labelValue="${user.company.name}" title="归属公司" url="/sys/office/treeData?type=1" cssClass="form-control"
                 allowClear="true" />
          </div>
          <udmp-input name="loginName" clear-button placeholder="登录名"></udmp-input>
          <udmp-input name="name" clear-button placeholder="姓名"></udmp-input>
          <button id="btnSubmit" class="btn i-btn btn-default" type="button">查询</button>
        </udmp-search>

        <%-- 数据展示 --%>
        <udmp-data :itable="table" :url="tableUrl">
          <shiro:hasPermission name="sys:user:edit">
            <button type="button" @click="addUser" class="btn i-btn" >
              <span class="glyphicon glyphicon-plus"></span>&nbsp;添加用户
            </button>
          </shiro:hasPermission>
          <button type="button" id="btnImport" @click="importUser" class="btn i-btn">
            <span class="glyphicon glyphicon-save"></span>&nbsp;导入
          </button>
          <button type="button" id="btnExport" @click="exportUser" class="btn i-btn">
            <span class="glyphicon glyphicon-open"></span>&nbsp;导出
          </button>
        </udmp-data>
      </div>
    </div>
    <%@ include file="common/footer.jsp" %>
    <%-- 页面脚本 --%>
    <script type="text/javascript">
      seajs.use('sys/userIndex')
    </script>
  </body>
</html>
