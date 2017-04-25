<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript" src="batch/pages/batchMonitor/dhtmlxgantt.js"></script>
<script type="text/javascript" src="batch/pages/batchMonitor/dhtmlxgantt_tooltip.js"></script>
<link rel="stylesheet" href="batch/css/dhtmlxgantt.css">

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
<script type="text/javascript">
	//甘特图初始化
	function batchMonitorDatailInit() {
		gantt.config.readonly = true;//甘特图只读
		gantt.config.drag_links = false;//任务依赖连接线禁止拖拽
		gantt.config.columns = [ //列设定
		    { name : "text", label : "任务名称", tree : true, width : '*' }, 
		    { name : "start_date", label : "开始时间", align: "center", width : '80px' },
		    { name : "duration", label : "持续时间(分)", align : "center", width : '80px' }, 
		    { name : "state", label : "状态", align: "center", width : '60px' }, 
		];
		
		//进度条显示百分比数值
		gantt.templates.progress_text = function(start, end, task) {
			return "<span>" + Math.round(task.progress * 100) + "% </span>";
		};
		
		//设置tooltip
		gantt.templates.tooltip_text = function(start, end, task) {
			return "<b>任务名称:</b> " + task.text + "<br/><b>开始时间:</b> "
					+ task.start_date + "<br/><b>预期时间:</b> " + task.duration;
		};
		
		//设置超时警告颜色
		gantt.templates.task_class = function(start, end, task) {
			switch (task.state) {
			case "运行超时":
				return "outTime";
				break;
			}
		};

		setScaleConfig('0');
		gantt.init("gantt_here");

		var Id = $("#jobRunId").val();
		$.ajax({
			type : "POST",
			data : Id,
			url : "batchMonitor/showGanttView_batchMonitorAction.action?jobRunId="+ Id,
			dataType : "json",
			success : function(data) {
				gantt.parse(data);
			}
		});
	}

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
			gantt.config.step = 2;
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
	
	function jobStart(){
		var Id = $("#jobRunId").val();
		$.ajax({
			type : "POST",
			data : Id,
			url : "batchMonitor/startBatchJob_batchMonitorAction.action?jobRunId="+ Id,
			dataType : "json",
			success : function(data) {
				
			}
		});
	}

	function jobEnd(){
		var Id = $("#jobRunId").val();
		$.ajax({
			type : "POST",
			data : Id,
			url : "batchMonitor/endBatchJob_batchMonitorAction.action?jobRunId="+ Id,
			dataType : "json",
			success : function(data) {
				
			}
		});
	}
	

	$(document).ready(function() {
		batchMonitorDatailInit();
	});
</script>
<input type="hidden" id="jobRunId" value="${batchJobRunAVO.jobRunId}">
<div class="pageHeader">
	<div class="panel">
		<h1>监控控制</h1>
		<div>
			<div class="searchBar">
				<div class="subBar">
					<input name="ganttViewMode" type="radio" value="0" checked="true" onclick="changeGanttViewMode()" />分视图&nbsp; 
					<input name="ganttViewMode" type="radio" value="1" onclick="changeGanttViewMode()" />时视图

					<ul>
						<li>
							<div class="buttonActive">
								<div class="buttonContent">
									<button type="button" onclick="jobEnd();">终止</button>
								</div>
							</div>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</div>
<div style="margin: 5px"></div>
<div id="gantt_here" style="width: 100%; height: 80%;"></div>


