<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>批处理运行日志</title>
<%@ include file="../common/header.jsp"%>
<link
	href="${ctxStatic}/lib/bootstrap-daterangepicker/daterangepicker.css"
	type="text/css" rel="stylesheet" />
<style>
#date {
	min-width: 210px;
}
</style>
</head>
<body>
	<%-- 流式布局容器 --%>
	<div class="container-fluid clearfix" id="i-manage-panel">
		<%-- 查询模块 --%>
		<udmp-search>
		<div id="path"></div>
		<udmp-input name="jobChainRunId" clear-button placeholder="任务链运行实例ID"></udmp-input>
		<udmp-input name="jobChainBatch" clear-button placeholder="任务链运行批次"></udmp-input>
		<udmp-button label="查询"></udmp-button> </udmp-search>

		<%-- 数据展示 --%>
		<udmp-data :itable="table" :url="tableUrl" :options="options">
		<button type="button" class="btn i-btn add-btn" @click="addJobRun">
			<span class="glyphicon glyphicon-plus"></span>&nbsp;详细信息
		</button>
		<!--       	<button type="button" id="updateBtn" class="btn i-btn update-btn"> -->
		<!--             <span class="glyphicon glyphicon-plus"></span>&nbsp;更新作业配置 -->
		<!--           </button> --> <!--       	<button type="button" id="deleteBtn" class="btn btn-danger update-btn" @click="deleteJobRun"> -->
		<!--             <span class="glyphicon glyphicon-minus"></span>&nbsp;删除作业配置 -->
		<!--           </button> --> <!--           <button type="button" class="btn i-btn" @click="updateSort">保存排序</button> -->
		</udmp-data>
<!-- 		    <table id="table" -->
<!--            data-toolbar="#toolbar" -->
<!--            data-search="true" -->
<!--            data-show-refresh="true" -->
<!--            data-show-toggle="true" -->
<!--            data-show-columns="true" -->
<!--            data-show-export="true" -->
<!--            data-detail-view="true" -->
<!--            data-detail-formatter="detailFormatter" -->
<!--            data-minimum-count-columns="2" -->
<!--            data-show-pagination-switch="true" -->
<!--            data-pagination="true" -->
<!--            data-id-field="id" -->
<!--            data-page-list="[10, 25, 50, 100, ALL]" -->
<!--            data-show-footer="false" -->
<!--            data-side-pagination="server" -->
<!--            data-url="../jobRun/list" -->
<!--            data-response-handler="responseHandler"> -->
<!--     </table> -->
	</div>
	<%@ include file="../common/footer.jsp"%>

	<%-- 页面脚本 --%>
	<script type="text/javascript">
		seajs.use('js/batch/batchJobRunIndex')
	</script>
</body>
</html>
