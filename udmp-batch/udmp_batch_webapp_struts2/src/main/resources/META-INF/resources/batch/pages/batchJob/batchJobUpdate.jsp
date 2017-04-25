<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>


<script type="text/javascript">
	//前置依赖数量
	var preCount = $('#preCountDummy').val();
	//后置依赖数量
	var postCount = $('#postCountDummy').val();
	//前置任务模版
	var preDenendJobIdHtml = $('#preDependJobIdDummyDiv').html();
	//前置状态模版
	var preDependStateHtml = $('#preDependStateDummyDiv').html();

	//var preDependIdHtml = $('#preDependIdDummyDiv').html();
	
	//后置任务模版
	var postDenpndJobIdHtml = $('#postDependJobIdDummyDiv').html();
	//后置状态模版
	var postDependStateHtml = $('#postDependStateDummyDiv').html();

	//var postDependIdHtml = $('#postDependIdDummyDiv').html();

	//新增空白前置依赖模板
	var jobPreDependHtml = ""
			+ "<tr>"
			+ "<td>"
			+ "<input type=\"button\" value=\"＋\" onclick=\"addPreDepend(this)\" />"
			+ "<input type=\"button\" value=\"－\" onclick=\"delPreDepend(this)\" />"
			+ "</td>" + "<td>" + preDenendJobIdHtml + "</td>" + "<td>"
			+ preDependStateHtml + 
			//+ preDependIdHtml +
			"</td>" + "</tr>" + "";

	//新增空白前置依赖模板
	var jobPostDependHtml = ""
			+ "<tr>"
			+ "<td>"
			+ "<input type=\"button\" value=\"＋\" onclick=\"addPostDepend(this)\" />"
			+ "<input type=\"button\" value=\"－\" onclick=\"delPostDepend(this)\" />"
			+ "</td>" + "<td>" + postDenpndJobIdHtml + "</td>" + "<td>"
			+ postDependStateHtml + 
			//+ postDependIdHtml +
			"</td>" + "</tr>" + "";

			function selectTask() {

				var taskID = $("#taskId").val();
				
				$.ajax({
							type : "POST",
							data : taskID,
							url : "batchJob/getTaskInfo_batchJobAction.action?taskID="
									+ taskID,
							dataType : "json",
							success : function(json) {
								var tb = $('#parametersList');
								$('#parametersList tr td').remove();
								for(var i = 0;i < json.batchJobParamList.length ; i++){
									var requiredHtml;
									if(json.batchJobParamList[i].isRequired == 1){
										requiredHtml = "<input type=\"checkbox\" checked=\"checked\" disabled=\"disabled\" />";
									}else{
										requiredHtml = "<input type=\"checkbox\" disabled=\"disabled\" />";
									}
									requiredHtml += "<input name=\"isRequired\" type=\"hidden\" value=\""
											+ json.batchJobParamList[i].isRequired
											+ "\" />";
									var arrayHtml;
									var buttonHtml;
									if(json.batchJobParamList[i].isArray == 1){
										arrayHtml = "<input type=\"checkbox\" checked=\"checked\" disabled=\"disabled\" />";
										buttonHtml = ""
											+ "<input type=\"button\" value=\"＋\" onclick=\"addParamData(this)\" />"
											+ "<input type=\"button\" value=\"－\" onclick=\"delParamData(this)\" />";
									}else{
										arrayHtml = "<input type=\"checkbox\" disabled=\"disabled\" />";
										buttonHtml = "";
									}
									arrayHtml += "<input name=\"isArray\" type=\"hidden\" value=\""
											+ json.batchJobParamList[i].isArray
											+ "\" />";
									var manuHtml;
									var paramTypeHtml;
									var paramIDHtml;
									var paramSubIDHtml;
									if(json.batchJobParamList[i].isManu == 1){
										manuHtml = "<input type=\"checkbox\" checked=\"checked\" disabled=\"disabled\" />";
										if(json.batchJobParamList[i].paramType == "date"){
											paramTypeHtml = "日期";
											paramSubIDHtml = "<input type=\"expandDateYMD\" name=\"paramValue\" value=\"\" ";
										}else if(json.batchJobParamList[i].paramType == "number"){
											paramTypeHtml = "数值";
											paramSubIDHtml = "<input type=\"text\" name=\"paramValue\" value=\"\" ";
										}else if(json.batchJobParamList[i].paramType == "string"){
											paramTypeHtml = "字符串";
											paramSubIDHtml = "<input type=\"text\" name=\"paramValue\" value=\"\" ";
										}else{
											paramTypeHtml = "";
											paramSubIDHtml = "";
										}
										if(json.batchJobParamList[i].isRequired == 1){
											paramSubIDHtml += "class=\"required\" />";
										}else{
											paramSubIDHtml += "/>";
										}
										paramIDHtml = "";
									}else{
										manuHtml = "<input type=\"checkbox\" disabled=\"disabled\" />";
										paramTypeHtml = "";
										paramIDHtml = json.batchJobParamList[i].paramManageVo.paramName;
										if(json.batchJobParamList[i].paramManageVo.isSingleValue == 0){
											paramSubIDHtml = "<select name=\"paramValue\" >";
											for(var j = 0;j < json.batchJobParamList[i].paramManageVo.subParamList.length ; j++){
												paramSubIDHtml += "<option value=\""
													+ json.batchJobParamList[i].paramManageVo.subParamList[j].paramItemValue
													+ "\">"
													+ json.batchJobParamList[i].paramManageVo.subParamList[j].paramItemName
													+ "</option>";
											}
											paramSubIDHtml += "</select>";
										}else{
											paramSubIDHtml = json.batchJobParamList[i].paramManageVo.paramValue
												+ "<input type=\"hidden\" name=\"paramValue\" value=\""
												+ json.batchJobParamList[i].paramManageVo.paramValue
												+ "\" />";
										}
									}
									manuHtml += "<input type=\"hidden\" name=\"isManu\" value=\""
											+ json.batchJobParamList[i].isManu
											+ "\" />";
									paramTypeHtml += "<input type=\"hidden\" name=\"taskParamDataType\" value=\""
											+ json.batchJobParamList[i].paramType
											+ "\" />";
									paramIDHtml += "<input type=\"hidden\" name=\"taskParamId\" value=\""
											+ json.batchJobParamList[i].taskParamId
											+ "\" />";
									tb.append(""
											+ "<tr>"
											+ "<td align=\"center\">"
											+ buttonHtml
											+ "</td>"
											+ "<td align=\"center\">"
											+ json.batchJobParamList[i].paramName
											+ "<input type=\"hidden\" name=\"taskParamName\" value=\""
											+ json.batchJobParamList[i].paramName
											+ "\" />"
											+ "</td>"
											+ "<td align=\"center\">"
											+ requiredHtml
											+ "</td>"
											+ "<td align=\"center\">"
											+ arrayHtml
											+ "</td>"
											+ "<td align=\"center\">"
											+ manuHtml
											+ "</td>"
											+ "<td align=\"center\">"
											+ paramTypeHtml
											+ "</td>"
											+ "<td align=\"center\">"
											+ paramIDHtml
											+ "</td>"
											+ "<td >"
											+ paramSubIDHtml
											+ "</td></tr>").initUI();
								}
								//receiveJsonMessage(json);
							}
						});
			}
	
	function addParamData(buttonObj) {
		var trObj = $(buttonObj).parent().parent();
		var tempDataHtml = "<tr>" 
			+ $(buttonObj).parents("tr:first").html()
			+ "</tr>";
		$(tempDataHtml).insertAfter($(trObj));
	}
	
	function delParamData(buttonObj) {
		var trObj = $(buttonObj).parent().parent();
		var paramName = trObj.find("td:eq(1)").text();
		var count = 0;
		$(buttonObj).parents("tbody:first").find("tr").each(function(){
			if (paramName == $(this).find("td:eq(1)").text()) {
				count++;
			}
		});
		if (count == 1) {
			alert("不允许删除，该数组参数至少要有一个！");
		} else {
			$(trObj).remove();
		}
	}

	function addPreDepend(buttonObj) {
		var trObj = $("#preDependList tr:eq(0)");

		if (buttonObj != null) {
			trObj = $(buttonObj).parent().parent();
		}
		var tempJobDependHtml = jobPreDependHtml;
		$(tempJobDependHtml).insertAfter($(trObj));
		preCount++;
	}

	function delPreDepend(buttonObj) {
		var trObj = $(buttonObj).parent().parent();
		if (preCount > 1) {
			$(trObj).remove();
			preCount--;
		} else {
			$("select[name=preDependJobId]").val("0");
			$("select[name=preDependState]").val("-1");
			$("input[name=preDependId]").val("-1");
		}
	}

	function addPostDepend(buttonObj) {
		var trObj = $("#postDependList tr:eq(0)");
		if (buttonObj != null) {
			trObj = $(buttonObj).parent().parent();
		}
		var tempJobDependHtml = jobPostDependHtml;
		$(tempJobDependHtml).insertAfter($(trObj));
		postCount++;
	}

	function delPostDepend(buttonObj) {
		var trObj = $(buttonObj).parent().parent();
		if (postCount > 1) {
			$(trObj).remove();
			postCount--;
		} else {
			$("select[name=postDependJobId]").val("0");
			$("select[name=postDependState]").val("-1");
			$("input[name=postDependId]").val("-1");
		}
	}

	/**----------- 初始化相关 -----------*/

	function selectInit() {
		var taskId = $("#inputTaskId").val();
		$("#taskId").val(taskId);

		var jobGroup = $("#inputJobGroup").val();
		$("#jobGroup").val(jobGroup);

		var jobType = $('input:radio[name="batchJobAVO.jobType"]:checked').val();
		if(jobType=="A"){
			$("#frequenceTab").attr("style","display:none");		}
		
		var objectArray = $("input[name=inputPreDependJobId]");
		preCount = objectArray.length;
		if (preCount == 0) {
			addPreDepend(null);
		} else {
			for ( var i = 0; i < objectArray.length; i++) {
				$(preDenendJobIdHtml).insertAfter($(objectArray[i]));
				$(objectArray[i]).parent().find("[name='preDependJobId']").val(
						$(objectArray[i]).val());
			}

			objectArray = $("input[name=inputPreDependState]");
			for ( var i = 0; i <= objectArray.length; i++) {
				$(preDependStateHtml).insertAfter($(objectArray[i]));
				$(objectArray[i]).parent().find("[name='preDependState']").val(
						$(objectArray[i]).val());
			}
 			objectArray = $("input[name=preDependId]");
			for ( var i = 0; i <= objectArray.length; i++) {
				//$(preDependIdHtml).insertAfter($(objectArray[i]));
				if($(objectArray[i]).val() == null) $(objectArray[i]).val("-1");
			} 
		}

		objectArray = $("input[name=inputPostDependJobId]");
		postCount = objectArray.length;
		if (postCount == 0) {
			addPostDepend(null);
		} else {
			for ( var i = 0; i < objectArray.length; i++) {
				$(postDenpndJobIdHtml).insertAfter($(objectArray[i]));
				$(objectArray[i]).parent().find("[name='postDependJobId']")
						.val($(objectArray[i]).val());
			}

			objectArray = $("input[name=inputPostDependState]");
			for ( var i = 0; i < objectArray.length; i++) {
				$(postDependStateHtml).insertAfter($(objectArray[i]));
				$(objectArray[i]).parent().find("[name='postDependState']")
						.val($(objectArray[i]).val());
			}
 			objectArray = $("input[name=postDependId]");
			for ( var i = 0; i <= objectArray.length; i++) {
				//$(postDependIdHtml).insertAfter($(objectArray[i]));
				if($(objectArray[i]).val() == null) $(objectArray[i]).val("-1");
			} 
		}
	}

	
	function jobTypeChange(objBtn){
		var jobType = objBtn.value;
		if(jobType=="S"){
			$("#frequenceTab").attr("style","display:block");
		}else if(jobType=="A"){
			$("#frequenceTab").attr("style","display:none");
			$("#frequenceTabDiv").attr("style","display:none");
			$("#jobFrequency").val("");
		}
	}
	
	$(document).ready(function() {
		selectInit();
	});
</script>

<!-- -----------隐藏 ----------- -->

<input name="postCountDummy" id="postCountDummy" value="${postCount}"
	type="hidden" />
<input type="hidden" name="preCountDummy" id="preCountDummy"
	value="${preCount} " />

<div id="preDependJobIdDummyDiv" style="display: none">
	<select cssClass="combox" id="preDependJobId" name="preDependJobId">
		<option value='0'>--请选择任务--</option>
		<s:iterator value="jobList">
			<option value='${jobId}'>${jobName}</option>
		</s:iterator>
	</select>
</div>

<div id="preDependStateDummyDiv" style="display: none">
	<select cssClass="combox" id="preDependState" name="preDependState">
		<option value="-1">--请选择--</option>
		<option value="0">成功</option>
		<option value="1">失败</option>
		<option value="2">部分成功</option>
		<option value="3">未启动</option>
		<option value="4">运行中</option>
	</select>
</div>

<div id="postDependJobIdDummyDiv" style="display: none">
	<select cssClass="combox" id="postDependJobId" name="postDependJobId">
		<option value='0'>--请选择任务--</option>
		<s:iterator value="jobList">
			<option value='${jobId}'>${jobName}</option>
		</s:iterator>
	</select>
</div>

<div id="postDependStateDummyDiv" style="display: none">
	<select cssClass="combox" id="postDependState" name="postDependState">
		<option value="-1">--请选择--</option>
		<option value="0">成功</option>
		<option value="1">失败</option>
		<option value="2">部分成功</option>
		<option value="3">未启动</option>
		<option value="4">运行中</option>
	</select>
</div>

<!-- ----------- 页面内容 ----------- -->

<div class="pageContent">
	<form method="post" id="userForm"
		action="batchJob/update_batchJobAction.action?menuId=${menuId}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent nowrap">
			<table style="width: 90%; margin: 0 auto;">
				<tr height="25">
					<td width="20%">任务名称：</td>
					<td width="30%">
					<!-- 隐藏域放置job_id -->
					<input type="hidden" id="jobId" name="batchJobAVO.jobId"
									value="${batchJobAVO.jobId}">
					<!-- 隐藏域设置ver -->
					<input type="hidden" id="ver" name="batchJobAVO.ver"
									value="${batchJobAVO.ver}">
					<!-- 隐藏域设置isGroup -->
					<input type="hidden" id="isGroup" name="batchJobAVO.isGroup"
									value="${batchJobAVO.isGroup}">
					<input type="text" name="batchJobAVO.jobName"
						value="${batchJobAVO.jobName}" maxlenght="50" class="required" /></td>
					<td width="20%">所属任务组：</td>
					<td width="30%"><input type="hidden" id="inputJobGroup"
						value="${batchJobAVO.jobGroupId}"> <select id="jobGroup"
						name="batchJobAVO.jobGroupId" value="batchJobAVO.jobGroupId">
							<s:iterator value="jobGroupList">
								<option value='${jobId}'>${jobName}</option>
							</s:iterator>
					</select></td>
				</tr>
				<tr height="25">
					<td>任务类型：</td>
					<td><s:if test="batchJobAVO.jobType==\"S\"">
							<input name="batchJobAVO.jobType" type="radio" value="S"
								checked="true" onclick="jobTypeChange(this);"/>定时任务
							<input name="batchJobAVO.jobType" type="radio" value="A" onclick="jobTypeChange(this);"/>异步任务
		            	</s:if> <s:elseif test="batchJobAVO.jobType==\"A\"">
							<input name="batchJobAVO.jobType" type="radio" value="S" onclick="jobTypeChange(this);"/>定时任务
							<input name="batchJobAVO.jobType" type="radio" value="A" onclick="jobTypeChange(this);"
								checked="true" />异步任务
		            	</s:elseif></td>
					<td>任务状态：</td>
					<td><s:if test="batchJobAVO.isEnable==\"0\"">
							<input name="batchJobAVO.isEnable" type="radio" value="0"
								checked="true" />停用
							<input name="batchJobAVO.isEnable" type="radio" value="1" />启用
			            </s:if> <s:elseif test="batchJobAVO.isEnable==\"1\"">
							<input name="batchJobAVO.isEnable" type="radio" value="0" />停用
							<input name="batchJobAVO.isEnable" type="radio" value="1"
								checked="true" />启用
			            </s:elseif></td>
				</tr>
				<tr height="25">
					<td>手工执行：</td>
					<td><s:if test="batchJobAVO.isAllowManu==\"0\"">
							<input name="batchJobAVO.isAllowManu" type="radio" value="0"
								checked="true" />否
							<input name="batchJobAVO.isAllowManu" type="radio" value="1" />是
		            	</s:if> <s:elseif test="batchJobAVO.isAllowManu==\"1\"">
							<input name="batchJobAVO.isAllowManu" type="radio" value="0" />否
							<input name="batchJobAVO.isAllowManu" type="radio" value="1"
								checked="true" />是
			            </s:elseif></td>
					<td>拆分执行：</td>
					<td><s:if test="batchJobAVO.isAllowSplit==\"0\"">
							<input name="batchJobAVO.isAllowSplit" type="radio" value="0"
								checked="true" />否
							<input name="batchJobAVO.isAllowSplit" type="radio" value="1" />是
			            </s:if> <s:elseif test="batchJobAVO.isAllowSplit==\"1\"">
							<input name="batchJobAVO.isAllowSplit" type="radio" value="0" />否
							<input name="batchJobAVO.isAllowSplit" type="radio" value="1"
								checked="true" />是
			            </s:elseif></td>
				</tr>
				<tr height="25">
					<td>提交事物批次：</td>
					<td><input type="text" name="batchJobAVO.jobBatchSize"
						value="${batchJobAVO.jobBatchSize}" maxlenght="5" class="required"
						value="100" /></td>
					<td>执行线程阀值：</td>
					<td><input type="text" name="batchJobAVO.jobThreadLimitCnt"
						value="${batchJobAVO.jobThreadLimitCnt}" maxlenght="4"
						class="required" value="1" /></td>
				</tr>
				<tr height="25">
					<td>删除标记：</td>
					<td><s:if test="batchJobAVO.isDeleted==\"0\"">
							<input type="radio" name="batchJobAVO.isDeleted" value="0"
								checked="true" />否
							<input type="radio" name="batchJobAVO.isDeleted" value="1" />是
		               	</s:if> <s:elseif test="paramAVo.isDeleted==\"1\"">
							<input type="radio" name="batchJobAVO.isDeleted" value="0" />否
							<input type="radio" name="batchJobAVO.isDeleted" value="1"
								checked="true" />是
		               	</s:elseif></td>
					<td></td>
					<td></td>
				</tr>
			</table>
		</div>
		<div class="tabs" eventtype="click" currentindex="0">
			<div class="tabsHeader">
				<div class="tabsHeaderContent">
					<ul>
						<li class="selected" width="33%"><a href="javascript:;"><span>启动参数</span></a></li>
						<li class="" width="33%"><a id="frequenceTab" href="javascript:;"><span>执行频率</span></a></li>
						<li class="" width="33%"><a href="javascript:;"><span>逻辑依赖</span></a></li>
					</ul>
				</div>
			</div>
			<div class="tabsContent" style="height: 180px;">
				<div style="display: block;">
					<div>
						<table>
							<tr>
								<td align="center" width="50%">执行作业：</td>
								<td><input type="hidden" id="inputTaskId"
									value="${batchJobAVO.taskId}"> <select id="taskId"
									name="batchJobAVO.taskId" onchange="selectTask();">
										<option value='0'>--请选择作业--</option>
										<s:iterator value="taskList">
											<s:if test="taskId.equals(batchJobAVO.taskId)">
												<option value='${taskId}' selected="selected">${taskName}</option>
											</s:if>
											<s:else>
												<option value='${taskId}'>${taskName}</option>
											</s:else>
										</s:iterator>
								</select></td>
							</tr>
						</table>
					</div>
					<div style="margin-top: 5px;">
						<table class="list" width="100%">
							<thead>
								<tr align="center">
									<th>操作</th>
									<th>参数名称</th>
									<th>必选参数</th>
									<th>数组</th>
									<th>手工录入</th>
									<th>手工录入参数类型</th>
									<th>非手工录入关联参数</th>
									<th>参数值</th>
								</tr>
							</thead>
							<tbody id="parametersList">
								<!-- 隐藏第一条tr,内容为空，供新增页面初始化使用 -->
								<tr style="display: none" />
								
								<s:iterator value="batchJobAVO.batchJobParamList" status="st">
									<tr>
										<td align="center">
											<s:if test="isArray==1">
												<input type="button" value="+" onclick="addParamData(this)" />
												<input type="button" value="-" onclick="delParamData(this)" />
											</s:if>
										</td>
										<td align="center">
											${paramName}
											<input type="hidden" name="taskParamName" value="${paramName}" />
											<input type="hidden" name="taskParamId" value="${taskParamId}" />
											<input type="hidden" name="jobParamId" value="${jobParamId}" />
										</td>
										<td align="center">
											<s:if test="isRequired==1">
												<input type="checkbox" checked="checked" disabled="disabled" />
											</s:if>
											<s:else>
												<input type="checkbox" disabled="disabled" />
											</s:else>
											
										</td>
										<td align="center">
											<s:if test="isArray==1">
												<input type="checkbox" checked="checked" disabled="disabled" />
											</s:if>
											<s:else>
												<input type="checkbox" disabled="disabled" />
											</s:else>
											<input type="hidden" name="isArray" value="${isArray}" />
										</td>
										<td align="center">
											<s:if test="isManu==1">
												<input type="checkbox" checked="checked" disabled="disabled" />
											</s:if>
											<s:else>
												<input type="checkbox" disabled="disabled" />
											</s:else>
										</td>
										<td align="center">
											<s:if test="isManu==1">
												<s:if test="paramType==\"date\"">
													日期
												</s:if>
												<s:elseif test="paramType==\"number\"">
		               								数值
		               							</s:elseif>
		               							<s:elseif test="paramType==\"string\"">
		               								字符串
		               							</s:elseif>
											</s:if>
										</td>
										<td align="center">${paramManageVo.paramName}</td>
										<td>
											<s:if test="isManu==1">
												<s:if test="paramType==\"date\"">
													<input type="expandDateYMD" name="paramValue" value="${paramValue}" class="<s:if test="isRequired==1">required</s:if>" />
												</s:if>
												<s:elseif test="paramType==\"number\"">
		               								<input type="text" name="paramValue" value="${paramValue}" class="<s:if test="isRequired==1">required</s:if>" />
		               							</s:elseif>
		               							<s:elseif test="paramType==\"string\"">
		               								<input type="text" name="paramValue" value="${paramValue}" class="<s:if test="isRequired==1">required</s:if>" />
		               							</s:elseif>
											</s:if>
											<s:else>
												<s:if test="paramManageVo.isSingleValue==1">
													${paramValue}
													<input type="hidden" name="paramValue" value="${paramValue}" />
												</s:if>
												<s:else>
													<select name="paramValue" >
														<s:iterator value="paramManageVo.subParamList" status="st">
															<s:if test="paramItemValue==paramValue">
																<option selected="selected" value="${paramItemValue}">${paramItemName}
																</option>
															</s:if>
															<s:else>
																<option value="${paramItemValue}">${paramItemName}</option>
															</s:else>
														</s:iterator>
													</select>
												</s:else>
											</s:else>
										</td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
					</div>
				</div>
				<div style="display: none;" id="frequenceTabDiv">
					<table width="100%" style="margin: 10px 5px;">
						<tr height="25">
							<td width="10%">执行频率</td>
							<td width="30%">
								<table>
									<tr>
										<td><input type="text" id="jobFrequency"
											name="batchJobAVO.jobFrequency"
											value="${batchJobAVO.jobFrequency}" maxlenght="30" /></td>
										<td><a class="button" title="执行频率" mask="true"
											target="dialog" maxable="false" minable="false"
											resizable="false" width="600" height="400"
											href="batchJob/showBatchJobCronPage_batchJobAction.action"><span>高级设置</span></a></td>
									</tr>
								</table>
							</td>
							<td width="10%"></td>
							<td width="30%"></td>
						</tr>
						<tr height="25">
							<td>启动时间</td>
							<td><input type="expandDateYMDHmsRO"
								name="batchJobAVO.jobStartWindow"
								value="<s:date name="batchJobAVO.jobStartWindow" format="yyyy-MM-dd HH:mm:ss" />" />
							</td>
							<td>预警阀值</td>
							<td><input type="text" name="batchJobAVO.jobAlertDuration"
								value="${batchJobAVO.jobAlertDuration}" maxlenght="30" />分钟</td>
						</tr>
						<tr height="25">
							<td>结束时间</td>
							<td><input type="expandDateYMDHmsRO"
								name="batchJobAVO.jobEndWindow"
								value="<s:date name="batchJobAVO.jobEndWindow" format="yyyy-MM-dd HH:mm:ss" />" />
							</td>
							<td>预期执行时间</td>
							<td><input type="text" name="batchJobAVO.jobExpectDuration"
								value="${batchJobAVO.jobExpectDuration}" maxlenght="4" value"60" />分钟</td>
						</tr>
					</table>
				</div>
				<div style="display: none;">
					<div style="width: 50%; float: left;">
						<div
							style="margin: 10px 5px; line-height: 10px; text-align: center">启动依赖任务清单</div>
						<div>
							<table style="width: 100%; margin: 0 auto;">
								<thead>
									<tr>
										<th align="center">选择</th>
										<th align="center">任务名称</th>
										<th align="center">依赖状态</th>
									</tr>
								</thead>
								<tbody id="preDependList" name="batchJobPreDependList">
									<!-- 前置依赖数量   -->
									<tr style="display: none" /></tr>
									<s:iterator value="batchJobAVO.batchJobPreDependList"
										status="st">
										<tr>
											<td><input type="button" value="＋"
												onclick="addPreDepend(this)" /><input type="button"
												value="－" onclick="delPreDepend(this)" /></td>
											<td><input type="hidden" name="inputPreDependJobId"
												value="${dependJobId}"></td>
											<td><input type="hidden" name="inputPreDependState"
												value="${dependStatus}">
												<input type="hidden" name="preDependId"
												value="${dependId}"></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
					</div>
					<div style="width: 50%; float: left;">
						<div
							style="margin: 10px 5px; line-height: 10px; text-align: center">结束依赖任务清单</div>
						<div>
							<table style="width: 100%; margin: 0 auto;">
								<thead>
									<tr>
										<th align="center">选择</th>
										<th align="center">任务名称</th>
										<th align="center">依赖状态</th>
									</tr>
								</thead>
								<tbody id="postDependList" name="batchJobPostDependList">
									<!-- 后置依赖数量   -->
									<tr style="display: none" />
									<s:iterator value="batchJobAVO.batchJobPostDependList"
										status="st2">
										<tr>
											<td><input type="button" value="＋"
												onclick="addPostDepend(this)" /><input type="button"
												value="－" onclick="delPostDepend(this)" /></td>
											<td><input type="hidden" name="inputPostDependJobId"
												value="${dependJobId}"></td>
											<td><input type="hidden" name="inputPostDependState"
												value="${dependStatus}">
												<input type="hidden" name="postDependId"
												value="${dependId}"></td>
										</tr>
									</s:iterator>
								</tbody>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="formBar">
			<ul style="margin-top: -3px;">
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="$.pdialog.closeCurrent();">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
