<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
  <head>
    <title>字典管理</title>
    <%@ include file="common/header.jsp" %>
    <link href="${ctxStatic}/css/params/system/manage.css" rel="stylesheet" type="text/css" />
  </head>
  <body>
    <%-- 流式布局容器 --%>
    <div class="container-fluid clearfix" id="i-manage-panel">
      <%-- 查询模块 --%>
      <udmp-search>
        <udmp-select :url="selectUrl" name="type" search clear-button placeholder="字典类型"></udmp-select>
        <udmp-input name="description" clear-button placeholder="描述详情"></udmp-input>
        <button id="btnSubmit" class="btn i-btn btn-default" type="button">查询</button>
      </udmp-search>

      <%-- 数据展示 --%>
      <udmp-data :itable="table" :url="tableUrl" :options="options"> 
        <shiro:hasPermission name="sys:dict:edit">
          <button type="button" class="btn i-btn" @click="addDict">
            <span class="glyphicon glyphicon-plus"></span>&nbsp;添加字典
          </button>
        </shiro:hasPermission>
      </udmp-data>
    </div>
    <%@ include file="common/footer.jsp" %>
    <%-- 页面脚本 --%>
    <script type="text/javascript">
      seajs.use('sys/dictIndex')
    </script>
  </body>
</html>
