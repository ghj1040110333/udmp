<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
	/**
	 * 全选复选框操作的响应方法。
	 * @method checkedAllData(obj)
	 * @param {Object} obj 全选复选框对象
	 * @return {void}
	 */
	function checkedAllData(obj) {
		var objectArray = $(trans("input[name=taskId]"));
		for (var i = 0; i < objectArray.length; i++) {
			$(objectArray[i]).attr("checked", obj.checked);
		}
	}

	/**
	 * 遍历所有记录复选框，统计已经勾选的记录条数，并依照操作类型type判断并返回复选框勾选条数的校验结果。
	 * @method scanCheckBox(type)
	 * @param {String} type 此参数仅有两个值，修改update和删除delete
	 * @return {Boolean} result
	 */
	function scanCheckBox(type) {
		var result = true;
		var checkedCounter = 0;
		var objectArray = $(trans("input[name=taskId]"));
		for (var i = 0; i < objectArray.length; i++) {
			if ($(objectArray[i]).attr("checked")) {
				checkedCounter++;
			}
		}
		if (type == "del" && checkedCounter == 0) {
			result = false;
		}
		if (type == "update" && checkedCounter != 1) {
			result = false;
		}
		return result;
	}

	/**
	 * 点击删除按钮的响应方法，首先调用方法校验记录复选框勾选情况和批处理任务情况，如通过，将调用确认并删除方法，如不通过，予以提示。
	 * @method deleteBatchTask()
	 * @return {void}
	 */
	function deleteBatchTask() {
		if (scanCheckBox("del")) {
			if (checkBatchJob("del")) {
				confirmAndDelete();
			}
		} else {
			alertMsg.error("删除作业信息时，请至少勾选一条记录");
		}
	}

	/**
	 * 校验作业和任务的约束信息，采用同步ajax的方式。
	 * @param (String) type 调用校验的操作类型（del或Update）
	 * @method checkBatchJob(type)
	 * @return {Boolean} result
	 */

	function checkBatchJob(type) {
		var result = false;
		$.ajax({
			async : false,
			type : "POST",
			data : $('#taskOutlineForm').serialize(),
			url : "batch-task/" + type + "Check_batchTaskAction.action",
			dataType : "json",
			success : function(json) {
				result = outlineJsonMessage(json);
			}
		});
		return result;
	}

	/**
	 * 此方法实现了确认并提交删除相关记录的功能。
	 * @method confirmAndDelete()
	 * @return {void}
	 */
	function confirmAndDelete() {
		alertMsg.confirm("请确认是否删除勾选的作业信息", {
			okCall : function() {
				$.ajax({
					type : "POST",
					data : $('#taskOutlineForm').serialize(),
					url : "batch-task/del_batchTaskAction.action",
					dataType : "json",
					success : function(json) {
						outlineJsonMessage(json);
					}
				});
			}
		});
		return result;
	}

	/**
	 * 接收并相应Json返回的信息
	 * @method receiveJsonMessage(json)
	 * @param {String} json
	 * @return {Boolean} result 返回成功失败状态.
	 */
	function outlineJsonMessage(json) {
		var success = false;
		if (json.substring(0, 2) == "ok") {
			success = true;
			if (json.length > 3) {
				alertMsg.correct(json.substring(3));
				navTabPageBreak();
			}
		} else if (json.substring(0, 2) == "er") {
			alertMsg.error(json.substring(3));
		}
		return success;
	}

	/**
	 * 点击傀儡修改按钮的响应方法，首先调用方法校验记录复选框勾选情况和批处理任务情况，如通过，将拼接修改页面URL，并点击隐藏修改按钮，如不通过，予以提示。
	 * @method updateBatchTask()
	 * @return {void}
	 */
	function updateBatchTask() {
		if (scanCheckBox("update")) {
			if (checkBatchJob("update")) {
				var hrefStr = "batch-task/detail_batchTaskAction.action?menuId=${menuId}";
				hrefStr += "&taskId=" + getCheckedTaskId() + "&doUpdate=yes";
				$('#editClick').attr("href", hrefStr);
				$('#editClick').click();
			}
		} else {
			alertMsg.error("修改作业信息时，请勾选唯一一条记录");
		}
	}
	/**
	 * 拼接修改页面的URL时，需要通过以下方法获取已勾选记录的taskId。
	 * @method getCheckedTaskId()
	 * @return {String} result
	 */
	function getCheckedTaskId() {
		var result = "";
		var objectArray = $(trans("input[name=taskId]"));
		for (var i = 0; i < objectArray.length; i++) {
			if ($(objectArray[i]).attr("checked")) {
				result = $(objectArray[i]).val();
			}
		}
		return result;
	}

	/**
	 * 为了避免JQuery将.作为对象操作来处理（如batchTaskVO.taskName），故将.替换为\.。
	 * @method trans(str)
	 * @param {String} str 
	 * @return {String} str
	 */
	function trans(str) {
		return str.replace(".", "\\.");
	}
</script>

<div class="pageHeader">

	<!-- ------------------------------- 概览页面作业查询内容开始 -------------------------------   -->

	<form
		action="batch-task/outline_batchTaskAction.action?leftFlag=1&amp;menuId=${menuId }&initPager=yes"
		method="post" onsubmit="return navTabSearch(this);">
		<div class="panel">
			<h1>作业查询</h1>
			<div>
				<div class="searchBar">
					<ul class="searchContent">
						<li><label>作业名称:</label><input type="text"
							name="batchTaskVoOutline.taskName" value="${taskNameDummy}" /></li>
					</ul>
					<div class="subBar">
						<ul>
							<li>
								<div class="button">
									<div class="buttonContent">
										<button type="submit" id="queryButton">查询</button>
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
<!-- ------------------------------- 概览页面作业查询内容结束 -------------------------------  -->

<!-- ------------------------------- 概览页面操作按钮内容开始 -------------------------------   -->
<div class="pageContent">
	<div class="panel">
		<h1>作业列表</h1>
		<div>
			<div class="panelBar">
				<ul class="toolBar">
					<li><a class="add"
						href="batch-task/detail_batchTaskAction.action?menuId=${menuId}"
						minable="false" title="作业新增" width="850" height="600"
						target="dialog" mask="true" scrollbar="true" rel="addBatchTask">
							<span>新增</span>
					</a></li>
					<li><a class="edit" href="javascript:void(0);"
						rel="updateBatchTask" onclick="updateBatchTask()"><span>修改</span></a>
						<a id="editClick" maxable="false" minable="false" title="作业修改"
						width="850" height="600" target="dialog" mask="true"
						scrollbar="true" rel="updateBatchTask" style="display: none" /></li>
					<li><a class="delete" href="javascript:void(0);"
						rel="deleteBatchTask" onclick="deleteBatchTask();"><span>删除</span></a></li>
				</ul>
			</div>

			<!-- ------------------------------- 概览页面操作按钮内容结束 ------------------------------   -->

			<!-- ------------------------------- 概览页面作业列表内容开始 ------------------------------   -->
			<form id="taskOutlineForm">
				<table class="list" width="100%" layoutH="225">
					<thead>
						<tr align="center">
							<th width="5%"><input type="checkbox" id="theadCheckBox"
								onclick="checkedAllData(this);" />选择</th>
							<th>作业名称</th>
							<th>作业执行类</th>
							<th>所属系统</th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="currentPage.pageItems" status="st">
							<tr rel="${taskId}">
								<td align="center"><input name="taskId" type="checkbox"
									value="${taskId}" /></td>
								<td align="center">${taskName}</td>
								<td align="center">${taskClazz}</td>
								<td align="center">
									<s:if test="taskSystem==\"UDMP\"">UDMP</s:if>
									<s:elseif test="taskSystem==\"PA\"">保单管理</s:elseif>
									<s:elseif test="taskSystem==\"NB\"">新契约</s:elseif>
									<s:elseif test="taskSystem==\"UW\"">核保</s:elseif>
									<s:elseif test="taskSystem==\"CLM\"">理赔</s:elseif>
									<s:elseif test="taskSystem==\"CS\"">保全</s:elseif>
									<s:elseif test="taskSystem==\"CSS\"">柜面</s:elseif>
									<s:elseif test="taskSystem==\"CAP\"">收付费</s:elseif>
									<s:elseif test="taskSystem==\"MMS\"">营销支持</s:elseif>
									<s:elseif test="taskSystem==\"ECS\"">影像采集</s:elseif>	
									<s:elseif test="taskSystem==\"BPM\"">工作流</s:elseif>	
									<s:else>${taskSystem}</s:else>
								</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</form>
		</div>

		<!-- ------------------------------- 概览页面作业列表内容结束 ------------------------------   -->

		<!-- ------------------------------- 概览页面分页内容开始            ------------------------------   -->

		<form id="pagerForm" method="post"
			action="batch-task/outline_batchTaskAction.action?menuId=${menuId }">
			<input type="hidden" name="pageNum" value="${currentPage.pageNo}" />
			<input type="hidden" name="numPerPage"
				value="${currentPage.pageSize}" />
		</form>

		<div class="panelBar">
			<div class="pages">
				<span>显示</span>
				<s:select list="#{10:'10',20:'20',50:'50',100:'100',200:'200'}"
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

		<!-- ------------------------------- 概览页面分页内容结束            ------------------------------   -->
	</div>
</div>