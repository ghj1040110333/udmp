<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	/*调整任务组树样式*/
	$(function() {
		var height = parseInt($("div.accordionContent").css("height")) - 13
				+ "px";
		$("#jobTree").css("height", height);
	});
</script>

<div class="pageContent">
	<div id="jobGroupContent" class="jobGroupContent"
		style="display: none; position: absolute; z-index: 1002">
		<ul id="jobTree1" class="ztree" style="margin-top: 0; width: 200px;"></ul>
		<a href="getBatchjobTreeList_BatchJobAction.action"
			style="display: none" id="actionA"></a>
	</div>
	<table id="jobExecuteTable">
		<tr>
			<td width="15%" valign="top" layoutH="10">
				<ul id="jobTree" class="ztree"></ul>
			</td>
			<td width="80%" valign="top">
				<div class="pageHeader">
					<div class="panel">
						<h1 id="jobExecuteLabe">任务组</h1>
						<div class="searchBar">
							<div class="pageFormContent">								

									<!-- 用于储存任务组其他不展示的信息 -->
									<input type="hidden" id="jobId" name="batchJobVO.jobId"
										value="${batchJobVO.jobId}" />
									<dl>
										<dt>名称：</dt>
										<dd>
											<label id="jobName" />
										</dd>
									</dl>
									<div class="subBar">
										<ul>
											<li><div class="button">
													<div class="buttonContent">
														<button type="button" onclick="startBatchJob(this);" 
															id="startAllButton">全部启动</button>
													</div>
												</div></li>
											<li><div class="button">
													<div class="buttonContent">
														<button type="button" onclick="endBatchJob(this);"
															id="endAllButton">全部停止</button>
													</div>
												</div></li>
										</ul>
									</div>
							</div>
						</div>
					</div>
				</div>
				<div class="pageContent">
					<div class="panel collapse">
						<h1>包含任务列表</h1>
						<table class="table" width="100%" layoutH="225">
							<thead>
								<tr height="25" align="center">
									<td align="center">任务ID</td>
									<td align="center">任务名称</td>
									<td align="center">任务类型</td>
									<td align="center">是否启用</td>
									<td align="center">手动启动</td>
									<td align="center">启动管理</td>
								</tr>
							</thead>
							<tbody id="jobExecuteList">
								<tr style="display: none"></tr>
							</tbody>
						</table>
					</div>
				</div>
			</td>
		</tr>
	</table>
</div>

<script type="text/javascript">

var jobExecuteLabe = $("#jobExecuteLabe");
var jobTree = "jobTree";
var menuTable = $("#jobExecuteTable");



//任务组树的配置
var setting = {
	data : {	key : {	title : "t"	},
	simpleData : {	enable : true	} },
	callback : {onClick : onJobExecuteClick1},
	view:{	selectedMulti: false}
};

var zNodes;//任务组树的节点

//初始化任务树
function jobTreeInit(){
$.ajax({
	type : "post",
	url : '<%=request.getContextPath()%>/batchJob/getBatchJobTree_batchJobAction.action',
			dataType : "json",
			async : false,
			success : function(result) {
				zNodes = result.jobTreeList;
				$.fn.zTree.init($("#"+jobTree), setting, zNodes);
				var zTree = $.fn.zTree.getZTreeObj(jobTree);
				zTree.expandAll(true);
			},
			error : function(result) {
				alertMsg.error('系统异常');
			}
		});
}
function onJobExecuteClick1(event, treeId, treeNode, clickFlag) {
	jobExecuteLabe.text("选中任务");
	
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	nodes = zTree.getSelectedNodes(),
	showJobList(nodes[0].id);
	$("#jobId").val(nodes[0].id);
	$("#jobName").text(nodes[0].name);
};


/*点击树查询*/
function showJobList(id){
	$.ajax({
		type : "post",
		url : "batchJob/showJobGroupInfo_batchJobAction.action?jobId="+id,
		//将任务组id发送到后台,后台根据id查询任务组相关信息
		data : {jobId:id} ,
		dataType : "json",
		async : false,
		success : function(data) {					
		var tb = $('#jobExecuteList');
		$('#jobExecuteList tr:gt(0)').remove();
		var jobType;
		var isEnable;
		var isAllowManu;
		for(var i = 0;i < data.batchJobList.length ; i++){						
			if(data.batchJobList[i].jobType == "S"){
				jobType = "定时任务";							
			}else {
				jobType = "异步任务";
			}
			if(data.batchJobList[i].isEnable == "1"){
				isEnable = "启用";							
			}else {
				isEnable = "停用";
			}
			if(data.batchJobList[i].isAllowManu == "1"){
				isAllowManu = "是";							
			}else {
				isAllowManu = "否";
			}
			var htmlTemp="";					
			htmlTemp = htmlTemp
				+ "<tr height=\"25\"><td align=\"center\" id=\"jobId\">"
				+ data.batchJobList[i].jobId
				+ "</td><td align=\"center\">"
				+ data.batchJobList[i].jobName
				+ "</td><td align=\"center\">"
				+ jobType
				+ "</td><td align=\"center\">"
				+ isEnable
				+ "</td><td align=\"center\">"
				+ isAllowManu
				+ "</td><td align=\"center\">";
			if (data.batchJobList[i].isAllowManu == "1") {
				htmlTemp = htmlTemp
				+ "<input type=\"button\" value=\"启动\" onclick=\"startBatchJob(this)\" />";
			}
			htmlTemp = htmlTemp + "</td></tr>";
			tb.append(htmlTemp);
		}
		},
		});
	}

/*任务启动*/
function startBatchJob(buttonObj){
	var jobId;
	if(buttonObj.id =="startAllButton"){
		jobId = $("#jobId").val();
		if(jobId==null||jobId==""){
			alertMsg.error("请先选择需要启动的任务");
		}
	}else{
		var tr = $(buttonObj).parent().parent();
		jobId = $(tr).children('td').eq(0).html();
	}
	$.ajax({
		type : "post",
		url : "batchJob/repeatCheck_batchJobAction.action?jobId="+jobId,
		//将任务组id发送到后台,后台根据id查询任务组相关信息
		dataType : "json",
		success : function(data) {
			//如果后台已经存在运行中的实例，则会返回一个resultType为0的标志过来，进而再提醒用户是否要重复启动
			if(data.resultType ==0){			
				alertMsg.confirm("该任务已存在运行中的实例，是否重复启动？", {
					okCall : function() {
						$.ajax({
							type : "POST",
							url : "batchJob/startBatchJob_batchJobAction.action?jobId="+jobId,
							success : function(json) {
								alertMsg.info("任务手工启动操作成功");
							}
						});
					}
				});
			}else{
				alertMsg.info("任务手工启动操作成功");
			}
		},
	});
}

$(document).ready(jobTreeInit());
</script>




