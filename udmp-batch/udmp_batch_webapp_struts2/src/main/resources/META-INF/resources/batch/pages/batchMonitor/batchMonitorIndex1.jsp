<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- <%@ taglib uri="http://www.git.com.cn/taglib/udmp/field" prefix="Field" %> --%>
<script type="text/javascript" src="batch/pages/batchMonitor/dhtmlxgantt.js"></script>
<script type="text/javascript" src="batch/pages/batchMonitor/dhtmlxgantt_tooltip.js"></script>
<link rel="stylesheet" href="batch/css/dhtmlxgantt_skyblue.css">

<style media="screen" type="text/css">
.gantt_task_progress {
	text-align: left;
}

.gantt_task_content {
	padding-left: 30px;
}

.gantt_task_progress span {
	color: #FFF;
	position: relative;
	z-index: 20;
	padding: 3px;
	margin-left: 3px;
	border-radius: 3px;
}

.outTime {
	border: 2px solid #d96c49;
	color: #d96c49;
	background: #d96c49;
}

.outTime .gantt_task_progress {
	background: #db2536;
}

</style>
<form id="pagerForm" method="post"
	action="batchMonitor/showBatchMonitor1_batchMonitorAction.action?leftFlag=0&menuId=${menuId}">
	<input type="hidden" id="pageNum" name="pageNum" value="${currentPage.pageNo}" /> <input
		type="hidden" id="numPerPage"  name="numPerPage" value="${currentPage.pageSize}" />
</form>
<script type="text/javascript">
	function resetAll() {
		$('#jobName').attr('value', '');
		$('#statusSelect').val("");
		$('#processDateStart').val("");
		$('#processDateEnd').val("");
	}
</script>
<script language="javascript" for="window" event="onload">
	/* 甘特图初始化相关*/
	//页面每加载一次初始化甘特图
	if (document.readyState == "complete") {		
		
		$("#jobName").val($("#jobNameHidden").val());
		$("#processDateStart").val($("#processDateStartHidden").val());
		$("#processDateEnd").val($("#processDateEndHidden").val());
		$("#statusSelect").val($("#statusHidden").val());
		showGanttView();		
	}
	
	//加载甘特图数据
	function showGanttView(){
		var pageNum = $("#pageNum").val();
		var numPerPage = $("#numPerPage").val();
		var jobName = $("#jobName").val();
		var status = $("#statusSelect").val();
		var processDateStart = $("#processDateStart").val();
		var processDateEnd = $("#processDateEnd").val();
		$.ajax({
			type : "POST",
			data : {jobName:jobName},
			dataType:"json",
			async : false,
			url : "batchMonitor/showGanttView2_batchMonitorAction.action?pageNum="
					+ pageNum
					+ "&numPerPage="
					+ numPerPage
					+ "&status="
					+ status
					+ "&processDateStart="
					+ processDateStart
					+ "&processDateEnd=" 
					+ processDateEnd,
			success : function(data) {
				batchMonitorDatailInit1();
				//gantt.init("gantt_here");
// 				debugger;
				gantt.parse(data.tasks);
			}
		});
	}	

	//甘特图初始化
	function batchMonitorDatailInit1() {
		gantt.config.readonly = true;//甘特图只读
		gantt.config.drag_links = false;//任务依赖连接线禁止拖拽
		gantt.config.columns = [ //列设定
// 		    { name : "text", label : "任务名称", tree : true, width : '80px' }, 
		    { name : "text", label : "任务名称", align: "center", width : '80px' },
		    { name : "id", label : "实例ID", align: "center", width : '50px' },
		    { name : "start_date", label : "开始时间", align: "center", width : '80px' },
		    { name : "duration", label : "预期(分)", align : "center", width : '50px' }, 
		    { name : "state", label : "状态", align: "center", width : '60px' },
		    { name : "control", label : "控制", align: "center", template:creatStopButton,width:'50px'}
		];
		
		//进度条显示百分比数值
		gantt.templates.progress_text = function(start, end, task) {
			return "<span>" + Math.round(task.progress * 100) + "% </span>";
		};
		
		//设置tooltip
		gantt.templates.tooltip_text = function(start, end, task) {
			var date = new Date(task.start_date);
			return "<b>任务名称:</b> " + task.text + "<br/><b>开始时间:</b> "
					+ date.toLocaleDateString() + " " + date.toLocaleTimeString() + "<br/><b>预期时间:</b> " + task.duration;
		};
		
		//设置超时警告颜色，样式见CSS
		gantt.templates.task_class = function(start, end, task) {
			switch (task.state) {
			case "运行超时":
				return "outTime";
				break;
			case "失败":
				return "failure";
				break;
			}			
		};

		setScaleConfig('0');
		gantt.init("gantt_here");
		
	}

	/* 时视图、分时图切换相关*/
	var func = function(e) {
		e = e || window.event;
		var el = e.target || e.srcElement;
		var value = el.value;
		setScaleConfig(value);
		gantt.render();
	};

	var els = document.getElementsByName("ganttViewMode");
	for ( var i = 0; i < els.length; i++) {
		els[i].onclick = func;
	}

	function setScaleConfig(value) {
		switch (value) {
		case "0":
			gantt.config.scale_unit = "minute";
			gantt.config.step = 30;
			gantt.config.date_scale = "%G:%i";
			gantt.config.min_column_width = 50;
			gantt.config.duration_unit = "minute";
			gantt.config.duration_step = 1;
			gantt.config.scale_height = 60;
			gantt.config.subscales = [ {
				unit : "day",
				step : 1,
				date : "%Y-%m-%d"
			} ];
			break;
		case "1":
			//小时时间格式
			gantt.config.scale_unit = "hour";
			gantt.config.step = 1;
			gantt.config.date_scale = "%g %a";
			gantt.config.min_column_width = 50;
			gantt.config.duration_unit = "minute";
			gantt.config.duration_step = 60;
			gantt.config.scale_height = 60;
			gantt.config.subscales = [ {
				unit : "day",
				step : 1,
				date : "%Y-%m-%d"
			} ];
			break;
		}
	}

	/* 自定义列相关*/
	//自定义控制列，设置为停止按钮
	function creatStopButton(task){
		//debugger;
		if(task.state=="运行中"){
			return '<input type="button" value="终止" onclick="stopJobRun('+ task.id +')" />';
		}else if(task.state=="失败"){
			return '<input type="button"  value="重跑" onclick="reStart('+ task.id +')"/>';
		}else{
			return '<input type="button" disabled="disabled" value="终止" />';
		}

	}
	
	//停止按钮对应方法
	function stopJobRun(id){
		alertMsg.info("任务停止中，请稍候");
		$.ajax({
			type : "POST",
			data : {jobRunId:id},
			dataType:"json",
			url : "batchMonitor/endBatchJob_batchMonitorAction.action",
			success : function(data) {
				setTimeout(function () { alertMsg.info("任务已停止");showGanttView(); },3000);
			}
		});
	}
	
	//重跑失败的任务
	function reStart(id){
		alertMsg.info("任务重跑中，请稍候");
		$.ajax({
			type : "POST",
			data : {jobRunId:id},
			dataType:"json",
			url : "batchMonitor/reRunBatchJob_batchMonitorAction.action",
			success : function(data) {
				setTimeout(function () { alertMsg.info("任务已重跑");showGanttView(); },3000);
			}
		});
	}
	//$(document).ready(batchMonitorDatailInit1());
</script>
<input type="hidden" id="jobRunId" value="${batchJobRunAVO.jobRunId}">
<div class="pageHeader">
	<form
		action="batchMonitor/showBatchMonitor1_batchMonitorAction.action?leftFlag=0&menuId=${menuId}"
		method="post" name="batchMonitorForm" id="batchMonitorForm"
		class="pageForm required-validate"
		onsubmit="return navTabSearch(this);" rel="pagerForm">
		<div class="panel">
			<h1>监控查询条件</h1>
			<div>
<%-- 			<s:date name="" format="" timezone="" nice=""/> --%>
				<div class="searchBar">
					<table width="80%">
						<tr height="25">
							<td width="5%">任务名称:</td>
							<td width="10%"><input id="jobName"
								name="batchJobRunTVO.jobName" type="text" size="15"
								maxlength="50" value="${batchJobRunTVO.jobName}" /></td>
							<td width="5%">状态:</td>
							<td width="10%"><input type="hidden" id="statusHidden"
								value="${batchJobRunTVO.status}" />
								<Field:codeTable id="statusSelect" name="batchJobRunTVO.status"  
									tableName="T_UDMP_BATCH_PARAM_SUB" whereClause="PARAM_ID=1" nullOption="true" disabled="false"/>
							</td>
							<td width="10%">开始时间:</td>
							<td width="15%"><input type="hidden" id="processDateStartHidden"
								value="<s:date name="batchJobRunTVO.processDateStart" format="yyyy-MM-dd HH:mm:ss" />">
								<input type="expandDateYMDHmsRO" id="processDateStart"
								name="batchJobRunTVO.processDateStart" /></td>
							<td width="5%">~</td>
							<td width="15%"><input type="hidden" id="processDateEndHidden"
								value="<s:date name="batchJobRunTVO.processDateEnd" format="yyyy-MM-dd HH:mm:ss" />">
								<input type="expandDateYMDHmsRO" id="processDateEnd"
								name="batchJobRunTVO.processDateEnd" /></td>
						</tr>
					</table>

					<div class="subBar">
					<input name="ganttViewMode" type="radio" value="0" checked="true" />分视图&nbsp; 
					<input name="ganttViewMode" type="radio" value="1" />时视图
						<ul>
							<li>
								<div class="buttonActive">
									<div class="buttonContent">
										<button type="submit">查询</button>
									</div>
								</div>
							</li>
							<li>
								<div class="buttonActive">
									<div class="buttonContent">
										<button type="button" onclick="resetAll();">重置</button>
									</div>
								</div>
							</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</form>
</div>

<div class="pageContent">
	<div>		
		<div>			
			<div style="margin:5px">
				<div id="gantt_here" style="width: 100%; height: 500px; layoutH:200; "></div>
			</div>			
			
			<div class="panelBar">
				<div class="pages">
					<span>显示</span>
					<s:select list="#{20:'20',50:'50',100:'100',200:'200'}"
						name="select" onchange="navTabPageBreak({numPerPage:this.value})"
						value="currentPage.pageSize">
					</s:select>
					<span>条，共${currentPage.total}条</span>
				</div>
				<div class="pagination" targetType="navTab"
					totalCount="${currentPage.total}"
					numPerPage="${currentPage.pageSize}" pageNumShown="10"
					currentPage="${currentPage.pageNo}"></div>
			</div>
		</div>
	</div>
</div>



