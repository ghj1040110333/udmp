<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	function changeModuleType() {
		var moduleType = $("#moduleType").attr("value");
		if (moduleType == 3) {
			$("#combox_externalSystemId").find("a").removeAttr("disabled");
			$("#externalSystemId").removeAttr("disabled");
		} else {
			$("#combox_externalSystemId").find("a").attr("disabled", true);
			$("#externalSystemId").attr("disabled", true);
		}
	}
	/*调整任务组树样式*/
	$(function() {
		var height = parseInt($("div.accordionContent").css("height")) - 13
				+ "px";
		$("#jobGroupTree").css("height", height);
	});
</script>
						

<div class="panelBar" style="width: 100%">
	<ul class="toolBar">
		<li><a class="add" onclick="addJobGroup()" title=""><span>新建任务组</span></a></li>
		<li><a class="edit" onclick="updateJobGroup()" title=""><span>修改任务组</span></a></li>
		<li><a id="deleteJobGroup" class="delete"
			onclick="deleteJobGroup();" title="是否确认删除？"> <span>删除任务组</span></a></li>
	</ul>
</div>
<div class="pageContent">
	<div id="jobGroupContent" class="jobGroupContent"
		style="display: none; position: absolute; z-index: 1002">
		<ul id="jobGroupTree1" class="ztree"
			style="margin-top: 0; width: 200px;"></ul>
		<a href="getBatchJobGroupList_BatchJobAction.action"
			style="display: none" id="actionA"></a>
	</div>
	<table id="jobGroupTable">
		<tr>
			<td width="15%" valign="top" layoutH="10">
				<ul id="jobGroupTree" class="ztree"></ul>
			</td>
			<td width="80%" valign="top">
				<div class="pageHeader">
					<div class="panel">
						<h1 id="jobGroupLabel">任务组</h1>
						<div class="pageFormContent">
							<form id="jobGroupForm" class="pageForm required-validate"
								method="post"
								onsubmit="return validateCallback(this,afterFormAjaxDone)">
								
								<!-- 用于储存任务组其他不展示的信息 -->
								<input type="hidden" id="jobId" name="batchJobVO.jobId" value="${batchJobVO.jobId}"/>
								<input type="hidden" id="isGroup" name="batchJobVO.isGroup" value="1"/>
								<input type="hidden" id="isDeleted" name="batchJobVO.isDeleted" value="0"/>
								<input type="hidden" id="ver" name="batchJobVO.ver" value="1.0.0"/>
								<input type="hidden" id="jobEndWindow" name="batchJobVO.jobEndWindow" value=""/>
								<input type="hidden" id="jobStartWindow" name="batchJobVO.jobStartWindow" value=""/>
								<input type="hidden" id="isAllowSplit" name="batchJobVO.isAllowSplit" value="0"/>
								<input type="hidden" id="isAllowManu" name="batchJobVO.isAllowManu" value="0"/>
								<input type="hidden" id="isEnable" name="batchJobVO.isEnable" value="1"/>
								<input type="hidden" id="jobExpectDuration" name="batchJobVO.jobExpectDuration" value="0"/>
								<input type="hidden" id="taskId" name="batchJobVO.taskId" value=""/>
								<input type="hidden" id="jobFrequency" name="batchJobVO.jobFrequency" value=""/>
								<input type="hidden" id="jobBatchSize" name="batchJobVO.jobBatchSize" value="0"/>
								<input type="hidden" id="jobThreadLimitCnt" name="batchJobVO.jobThreadLimitCnt" value="0"/>
								<input type="hidden" id="jobType" name="batchJobVO.jobType" value="G"/>
								<input type="hidden" id="jobAlertDuration" name="batchJobVO.jobAlertDuration" value="0"/>
								<dl>
									<dt>任务组名称：</dt>
									<dd>
										<input id="jobgroupName" type="text" name="batchJobVO.jobName"
											class="required" maxlength="200" />
									</dd>
								</dl>			
								<dl>
									<dt>父任务组：</dt>
									<dd>
										<ul class="list">
											<li class="title">
												<!-- 用来存储点击选中的任务组id，回传到后台 --> <input type="hidden" id="hiddenpId"
													name="batchJobVO.jobGroupId" /> <input id="jobGrouppId"
												type="text" readonly /> &nbsp; <a id='jobGroupBtn'
												class="btnLook" href='#'
												onclick='showJobGroup(); return false;'></a>
											</li>
										</ul>
									</dd>
								</dl>
								<div class="formBar" style="padding: 5px 43%; height: auto;">
									<ul>
										<li><div class="button">
												<div class="buttonContent">
													<button type="button" onclick="submitJobGroupForm();"
														id="submitJobGroupButton" disabled="true">保存</button>
												</div>
											</div></li>
										<li><div class="button">
												<div class="buttonContent">
													<button type="button" onclick="clearJobGroupForm();">清空</button>
												</div>
											</div></li>
									</ul>
								</div>
							</form>
						</div>
					</div>
				</div>
				<div class="pageContent">
					<div class="panel collapse">
						<h1>包含任务列表</h1>
						<table class="table" width="100%" layoutH="225">
							<thead>
								<tr height="25" align="center">
									<td align="center">任务名称</td>
									<td align="center">任务类型</td>
									<td align="center">作业ID</td>
									<td align="center">是否启用</td>
								</tr>
							</thead>
							<tbody id="jobList">
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
	
var operateFlag = 0; //操作符: 0-无操作，1-新增，2-修改
var jobGroupLabel = $("#jobGroupLabel");
var menuForm = $("#jobGroupForm");
var jobGroupTree = "jobGroupTree";
var menuTable = $("#jobGroupTable");

//任务组树的配置
var setting = {
	data : {	key : {	title : "t"	},
	simpleData : {	enable : true	} },
	callback : {onClick : onJobGroupClick1},
	view:{	selectedMulti: false}
};

var zNodes;//任务组树的节点

//初始化树任务组
function jobGroupInit(){
$.ajax({
	type : "post",
	url : '<%=request.getContextPath()%>/batchJobGroup/getBatchJobGroupList_batchJobGroupAction.action',
			dataType : "json",
			async : false,
			success : function(result) {
				zNodes = result.jobGroupList;
				$.fn.zTree.init($("#"+jobGroupTree), setting, zNodes);
				var zTree = $.fn.zTree.getZTreeObj(jobGroupTree);
				zTree.expandAll(true);
			},
			error : function(result) {
				alertMsg.error('系统异常');
			}
		});
}
function onJobGroupClick1(event, treeId, treeNode, clickFlag) {
	clearJobGroupForm();	
	jobGroupLabel.text("选中任务组");
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	nodes = zTree.getSelectedNodes(),
	n = nodes[0].id;
	$("#jobId").val(n);
};

/**清除表单状态*/
function clearJobGroupForm() {
	jobGroupLabel.text("");//清空标题
	$("#jobGrouppId").attr("value", "");//input框置空
	$("#jobgroupName").attr("value", "");//input框置空
	$("#hiddenpId").attr("value", "");//input框置空
	$("#submitJobGroupButton").attr("disabled","true");//提交按钮变为不可提交
	operateFlag = 0;
};

/**新增任务组*/
function addJobGroup() {
	clearJobGroupForm();
	$("#submitJobGroupButton").removeAttr("disabled");//表单可提交
	operateFlag = 1;//新增标志
	jobGroupLabel.text("新增任务组");
}

/**删除任务组*/
function deleteJobGroup() {
	clearJobGroupForm();
	operateFlag = 3;
	var treeObj = $.fn.zTree.getZTreeObj(jobGroupTree);//得到树对象
	//将请求参数拼接到请求url中
	if (treeObj.getSelectedNodes().length == 0) {
		alertMsg.error('请选择一个任务组');
		jobGroupLabel.text("");
	} else{
		var valueId = treeObj.getSelectedNodes()[0].id;
		alertMsg.confirm("确认删除？",{
			okCall:function(){
				$.ajax({type:"GET",url:"batchJobGroup/deleteJobGroup_batchJobGroupAction.action",
					data:{moduleId:valueId,flag:operateFlag},
					dataType:"json",success:function(json){
						parent.DWZ.ajaxDone(json);
						jobGroupInit();
					}
				});
			}
		});
	} 
};

/**表单提交*/
function submitJobGroupForm(){
	//如果提交时没有父任务组，则将表单提交时的父id设置为空
	if(isNulOrEmpty($("#jobGrouppId").val())){
		$("#hiddenpId").attr("value","");
	}
	
	menuForm.attr("action","batchJobGroup/updateJobGroup_batchJobGroupAction.action?flag="+operateFlag);
	menuForm.submit();
	$("#jobGrouppId").attr("value","");
	$("#jobgroupName").attr("value","");
	
}


/**更新任务组*/
function updateJobGroup() {
	clearJobGroupForm();//清除表单样式
	$("#submitJobGroupButton").removeAttr("disabled");
	operateFlag = 2; //更新标志
	var treeObj = $.fn.zTree.getZTreeObj(jobGroupTree);
	//判断是否选中任务组
	if (treeObj.getSelectedNodes().length == 0) {
		alertMsg.error('请选择一个任务组');
		jobGroupLabel.text("");
	} else {
		//更新选中任务组,参数为表单对象和选中的节点
		updateTable($("#jobGroupTable"), treeObj.getSelectedNodes()[0]);
		//显示选中任务组对应的功能点
		showFunction(treeObj.getSelectedNodes()[0].id);
		jobGroupLabel.text("更新任务组");
	}
};

/**将值填入div组件的input,textArea,select当中*/
function updateTable(parentDiv, obj) {

	//任务组表父任务组名字的回显
	$.each(zNodes,function(){
	//将被选中的节点的父任务组名字赋值为父任务组id对应的任务组id对应的名字
		if($(this).attr("id") == obj.pId){
			$("#jobGrouppId").attr("value",$(this).attr("name"));
		}
	});	
	//input输入框的回显
	$("#jobgroupName").val(obj.name);
	$("#hiddenpId").val(obj.pId);
}

//表单提交后的处理
function afterFormAjaxDone(json){
	DWZ.ajaxDone(json);
	if (json.statusCode == DWZ.statusCode.ok){
		if (json.navTabId){ //把指定navTab页面标记为需要“重新载入”。注意navTabId不能是当前navTab页面的
			navTab.reloadFlag(json.navTabId);
		} else { //重新载入当前navTab页面
			var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
			var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
			navTabPageBreak(args, json.rel);
			jobGroupInit();
		}
		
		if ("closeCurrent" == json.callbackType) {
			setTimeout(function(){navTab.closeCurrentTab(json.navTabId);}, 100);
		} else if ("forward" == json.callbackType) {
			navTab.reload(json.forwardUrl);
		} else if ("forwardConfirm" == json.callbackType) {
			alertMsg.confirm(json.confirmMsg || DWZ.msg("forwardConfirmMsg"), {
				okCall: function(){
					navTab.reload(json.forwardUrl);
				},
				cancelCall: function(){
					navTab.closeCurrentTab(json.navTabId);
				}
			});
		} else {
			navTab.getCurrentPanel().find(":input[initValue]").each(function(){
				var initVal = $(this).attr("initValue");
				$(this).val(initVal);
			});
		}
	}}
$(document).ready(jobGroupInit());

/*功能点回显到下拉列表*/
function showFunction(id){
	$.ajax({
		type : "post",
		url : "batchJobGroup/showJobGroupInfo_batchJobGroupAction.action",
		//将任务组id发送到后台,后台根据id查询任务组相关信息
		data : {moduleId:id} ,
				dataType : "json",
				async : false,
				success : function(data) {					
					var tb = $('#jobList');
					$('#jobList tr:gt(0)').remove();
					var jobType;
					var isEnable;
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
						
						tb.append(""
								+ "<tr height=\"25\"><td align=\"center\">"
								+ data.batchJobList[i].jobName
								+ "</td><td align=\"center\">" 
								+ jobType
								+ "</td><td align=\"center\">"
								+ data.batchJobList[i].taskId
								+ "</td><td align=\"center\">"
								+ isEnable
								+ "</td></tr>");
					}
				
				},
				
				
			});
}

var inputId = "jobGrouppId";//输入框的id
var inputAId = "jobGroupBtn";//点击按钮的id
var treeDivId = "jobGroupContent";//树所在的div的id
var treeId = "jobGroupTree1";//树id
var hidden = "hiddenpId";
var actionA = "actionA";
var setting2 = {
		view : {
			dblClickExpand : false
		},
		data : {
			simpleData : {
				enable : true
			},
		},
		callback : {
			onClick : onJobGroupClick2
		}
	};
	function onJobGroupClick2(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		nodes = zTree.getSelectedNodes(),
		v = nodes[0].name;
		n = nodes[0].id;
		var branchObj = $("#" + inputId,$parentCur);
		var hiddenpId = $("#" + hidden,$parentCur);
		branchObj.attr("value", v);//给父任务组的文本框赋值当前选中的任务组的name
		hiddenpId.attr("value", n);//给父任务组的隐藏项赋值当前选中的任务组的id
		//隐藏tree
		hideMenu();
	}
	function showJobGroup() {
		//级联下拉绑定修改 判断该页面是navtab页或者是dailog页 
		var $parentCur = navTab.getCurrentPanel() == null ?  $.pdialog.getCurrent() : navTab.getCurrentPanel();
		//获取树对象
		var branchObj = $("#" + inputId,$parentCur);
		//获取tree的相对位置
		var branchPosition = $("#" + inputId,$parentCur).offset();
		//调用ajax 初始化树
	 	$.ajax({type:"POST",url:'<%=request.getContextPath()%>/batchJobGroup/getBatchJobGroupList_batchJobGroupAction.action',
					dataType : "json",
					success : function(data) {
						var zNodes = data.jobGroupList;
						$.fn.zTree.init($("#" + treeId, $parentCur), setting2,
								zNodes);
						var zTree = $.fn.zTree.getZTreeObj(treeId);
						zTree.expandAll(true);
					}
				});
	 	//显示树
		$("#" + treeDivId,$parentCur).css({
			left : branchPosition.left - $("#accordionPos").outerWidth() - $("#splitBar").outerWidth() - 8 + "px",
			top : branchPosition.top + branchObj.outerHeight() - $("#layout").outerHeight() - $("#taskbar").outerHeight() - 27 - 1  + "px"
			
		}).slideDown("fast");
		//解绑事件
		$($parentCur).bind("mousedown", onBodyDown);
	}
	var $parentCur = navTab.getCurrentPanel() == null ?  $.pdialog.getCurrent() : navTab.getCurrentPanel();
	var fval = null;
	function hideMenu() {
		//级联下拉绑定修改 判断该页面是navtab页或者是dailog页 HuoYanpeng
		var $parentCur = navTab.getCurrentPanel() == null ?  $.pdialog.getCurrent() : navTab.getCurrentPanel();
		$("#" + treeDivId,$parentCur).fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		//级联下拉绑定修改 判断该页面是navtab页或者是dailog页 HuoYanpeng
		var $parentCur = navTab.getCurrentPanel() == null ?  $.pdialog.getCurrent() : navTab.getCurrentPanel();
		if(event.target.id == '' ||!(event.target.id == inputAId || event.target.id == treeDivId  
				||$(event.target).parents("#" + treeDivId,$parentCur).length > 0)){
			hideMenu();
		}
	}
</script>




