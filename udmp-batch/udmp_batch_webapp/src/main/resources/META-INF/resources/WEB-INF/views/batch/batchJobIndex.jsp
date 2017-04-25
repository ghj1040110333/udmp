<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
  <head>
    <title>批处理任务配置</title>
    <%@ include file="../common/header.jsp" %>
    <link href="${ctxStatic}/lib/bootstrap-daterangepicker/daterangepicker.css" type="text/css" rel="stylesheet" />
    <style>
      #date { min-width:210px;}
    </style>
  </head>
  <body>
    <%-- 流式布局容器 --%>
    <div class="container-fluid clearfix" id="i-manage-panel">
      <%-- 查询模块 --%>
      <udmp-search>
      	<div id="path"></div>
        <udmp-input name="jobName" clear-button placeholder="任务名称"></udmp-input>
        <udmp-button label="查询"></udmp-button>
      </udmp-search>

      <%-- 数据展示 --%>
      <udmp-data :itable="table" :url="tableUrl" :options="options">
      	<button type="button" class="btn i-btn add-btn" @click="addJob">
            <span class="glyphicon glyphicon-plus"></span>&nbsp;注册任务
          </button>
      	<button type="button" id="updateBtn" class="btn i-btn update-btn">
            <span class="glyphicon glyphicon-plus"></span>&nbsp;更新任务配置
          </button>
      	<button type="button" id="deleteBtn" class="btn btn-danger update-btn" @click="deleteJob">
            <span class="glyphicon glyphicon-minus"></span>&nbsp;删除任务配置
          </button>
<!--           <button type="button" class="btn i-btn" @click="updateSort">保存排序</button> -->
      </udmp-data>
	</div>
    <%@ include file="../common/footer.jsp" %>
    <%-- 页面脚本 --%>
    <script type="text/javascript">
      seajs.use('js/batch/batchJobIndex')
    </script>
  </body>
</html>
