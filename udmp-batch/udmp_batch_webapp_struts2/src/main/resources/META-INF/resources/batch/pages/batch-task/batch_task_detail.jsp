<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- <%@ taglib uri="http://www.git.com.cn/taglib/udmp/field" prefix="Field" %> --%>
<script type="text/javascript">
	/**--------------------------------------页面全局变量，以及模板-------------------------------------------- */

	//页面提交URL，在添加提交和修改提交前拼接完成
	var submitUrl = "";
	//作业参数条数
	var paramsCount = $('#paramsCountDummy').val();
	//参数数据类型模板
	var paramDataTypeHtml = "<select name=\"taskParamDataTypeDummy\" class=\"required\" disabled><option value=\"\" >请选择</option><option value=\"date\">日期</option><option value=\"number\">数值</option><option value=\"string\">字符串</option></select>";
	//关联参数模板
	var paramIdHtml = $('#paramIdDummyDiv').html().replaceAll(
			'paramIdDummyFormer', 'paramIdDummy');
	//新增空白参数模板
	var paramDataHtml = ""
			+ "<tr>"
			+ "<td align=\"center\">"
			+ "<input type=\"button\" value=\"＋\" onclick=\"addParamData(this)\" />"
			+ "<input type=\"button\" value=\"－\" onclick=\"delParamData(this)\" />"
			+ "</td>"
			+ "<td align=\"center\">"
			+ "<input type=\"text\" name=\"taskParamName\" value=\"\" class=\"required\" />"
			+ "</td>"
			+ "<td align=\"center\">"
			+ "<input type=\"checkbox\" name=\"isManuDummy\" onclick=\"switchParamsInputType(this)\" />"
			+ "<input type=\"hidden\" name=\"isManu\" value=\"0\" />"
			+ "</td>"
			+ "<td align=\"center\">"
			+ paramDataTypeHtml
			+ "<input type=\"hidden\" name=\"taskParamDataType\" value=\"0\" />"
			+ "</td>"
			+ "<td align=\"center\">"
			+ paramIdHtml
			+ "<input type=\"hidden\" name=\"paramId\" value=\"0\" />"
			+ "</td>"
			+ "<td align=\"center\">"
			+ "<input name=\"isArrayDummy\" type=\"checkbox\" value=\"\" />"
			+ "<input name=\"isArray\" type=\"hidden\" value=\"0\" />"
			+ "</td>"
			+ "<td align=\"center\">"
			+ "<input name=\"isRequiredDummy\" type=\"checkbox\" value=\"\" />"
			+ "<input name=\"isRequired\" type=\"hidden\" value=\"0\" />"
			+ "</td>"
			+ "<td>"
			+ "<input type=\"button\" value=\"↑\" onclick=\"upParamData(this)\" />"
			+ "<input type=\"button\" value=\"↓\" onclick=\"downParamData(this)\" />"
			+ "</td>" + "</tr>" + "";

	/**--------------------------------------页面初始化相关方法-------------------------------------------- */

	/**
	 * 页面载入完成触发方法，做了两件事情：1.判断新增、修改后触发相对应的初始化方法；2.组装新增、修改提交的URL.
	 * @return {void}
	 */
	$(document).ready(
			function() {
				var detailControlFlag = $('#detailControlFlag').val();
				if (detailControlFlag == "add") {
					addDetailInit();
				} else {
					updateDetailInit();
				}
				submitUrl = "batch-task/" + detailControlFlag
						+ "_batchTaskAction.action";
			});

	/**
	 * 新增页面初始化，触发新增参数方法，在页面中新增了一条参数记录。（可考虑增加新方法进一步完善对第一个参数赋予默认值）
	 * @method addDetailInit()
	 * @return {void}
	 */
	function addDetailInit() {
		addParamData(null);
	}

	/**
	 * 修改页面初始化，通过调用各方法，分别对是否为SpringBean、手工录入参数数据类型、非手工录入关联参数、手工录入参数、数组、以及必选内容进行初始化。
	 * @method updateDetailInit()
	 * @return {void}
	 */
	function updateDetailInit() {
		isSpringBeanInit();
		taskParamDataTypeInit();
		paramIdInit();
		isManuInit();
		isArrayInit();
		isRequiredInit();
		$("#taskSystem").val($("#taskSystemHidden").val());
	}

	/**
	 * 修改页面初始化，通过调用各方法，是否为SpringBean初始化，根据hidden的isSpringBean的value值，改变傀儡checkbox的状态。
	 * @method isSpringBeanInit() 
	 * @return {void}
	 */
	function isSpringBeanInit() {
		if ($(trans("input[name=batchTaskVO.isSpringBean]")).val() == "1") {
			$(trans("input[name=batchTaskVO.isSpringBeanDummy]")).attr(
					"checked", "true");
		}
	}

	/**
	 * 手工录入参数勾选按钮初始化，调用了checkBoxInit()方法,同时，根据手工录入参数复选框状态，完成下拉选框可用切换。
	 * @method isManuInit() 
	 * @return {void}
	 */
	function isManuInit() {
		checkBoxInit("isManu");
		var objectArray = $("input[name=isManuDummy]");
		for (var i = 0; i < objectArray.length; i++) {
			switchParamsInputType(objectArray[i]);
		}
	}

	/**
	 * 手工录入参数类型下拉选框初始化，遍历所有hidden的taskParamDataType的value值，根据value值，将其对应傀儡select调整至相应index上。
	 * @method taskParamDataTypeInit() 
	 * @return {void}
	 */
	function taskParamDataTypeInit() {
		var objectArray = $("input[name=taskParamDataType]");
		for (var i = 0; i < objectArray.length; i++) {
			$(paramDataTypeHtml).insertAfter($(objectArray[i]));
			$(objectArray[i]).parent().find("[name='taskParamDataTypeDummy']")
					.val($(objectArray[i]).val());
		}
	}

	/**
	 * 非手工录入关联参数下拉选框初始化，遍历所有hidden的paramId的value值，根据value值，将其对应傀儡select调整至相应index上。
	 * @method paramIdInit() 
	 * @return {void}
	 */
	function paramIdInit() {
		var objectArray = $("input[name=paramId]");
		for (var i = 0; i < objectArray.length; i++) {
			$(paramIdHtml).insertAfter($(objectArray[i]));
			$(objectArray[i]).parent().find("[name='paramIdDummy']").val(
					$(objectArray[i]).val());
		}
	}

	/**
	 * 数组勾选按钮初始化，调用了checkBoxInit()方法。
	 * @method isArrayInit()
	 * @return {void}
	 */
	function isArrayInit() {
		checkBoxInit("isArray");
	}

	/**
	 * 必选勾选按钮初始化，调用了checkBoxInit()方法。
	 * @method isRequiredInit()
	 * @return {void}
	 */
	function isRequiredInit() {
		checkBoxInit("isRequired");
	}

	/**
	 * 根据checkboxName，遍历所有checkboxName相关复选框，根据value,将其傀儡checkbox打勾。
	 * @method checkBoxInit(checkboxName)
	 * @param {String} checkboxName 复选框名称
	 * @return {void} 
	 */
	function checkBoxInit(checkboxName) {
		var objectArray = $("input[name=" + checkboxName + "]");
		for (var i = 0; i < objectArray.length; i++) {
			if ($(objectArray[i]).val() == "1") {
				$(objectArray[i]).parent().find(
						"[name='" + checkboxName + "Dummy']").attr("checked",
						"true");
			}
		}
	}

	/**--------------------------------------页面操作相关方法-------------------------------------------- */

	/**
	 * 新增一条空白作业参数记录。处理了两种情况。
	 * 初始化新增页面时，传入null,将参数模板添加至第一条tr记录下;
	 * 点击+按钮时，根据+按钮找出+按钮所在的tr对象，将参数模板添加该tr对象后面。
	 * 每增加一条，页面全局变量paramsCount就要加一。
	 * @method addParamData(buttonObj)
	 * @param {Object} buttonObj按钮对象
	 * @return {void} 
	 */
	function addParamData(buttonObj) {
		if (paramsCount < 10) {
			var trObj = $("#parametersList tr:eq(0)");
			if (buttonObj != null) {
				trObj = $(buttonObj).parent().parent();
			}
			var tempDataHtml = paramDataHtml;
			$(tempDataHtml).insertAfter($(trObj));
			paramsCount++;
		} else {
			alertMsg.error("作业最多只能配置10个参数");
		}

	}

	/**
	 * 删除一条作业参数记录。如当前仅留下最后一条作业参数记录，不能删除。
	 * 点击-按钮时，根据-按钮找出-按钮所在的tr对象，将该tr对象删除。
	 * 每删除一条，页面全局变量paramsCount就要减一。
	 * @method delParamData(buttonObj)
	 * @param {Object} buttonObj按钮对象
	 * @return {void} 
	 */
	function delParamData(buttonObj) {
		var trObj = $(buttonObj).parent().parent();
		if (paramsCount > 1) {
			$(trObj).remove();
			paramsCount--;
		}
	}

	/**
	 * 上移一条记录。如当前准备上移的记录为第一条，不能上移。
	 * 点击↑按钮时，根据↑按钮找出-按钮所在的tr对象，调用exchangeParamData()方法将此tr对象和此tr的前一个对象交换位置。
	 * @method upParamData(buttonObj)
	 * @param {Object} buttonObj按钮对象
	 * @return {void} 
	 */
	function upParamData(buttonObj) {
		var trObj = $(buttonObj).parent().parent();
		var dataIndex = $(trObj).index();
		if (dataIndex > 1) {
			exchangeParamData($(trObj), $(trObj).prev(), "up");
		}
	}

	/**
	 * 下移一条记录。如当前准备下移的记录为最后一条，不能下移。
	 * 点击↓按钮时，根据↓按钮找出-按钮所在的tr对象，调用exchangeParamData()方法将此tr对象和此tr的后一个对象交换位置。
	 * @method downParamData(buttonObj)
	 * @param {Object} buttonObj按钮对象
	 * @return {void} 
	 */
	function downParamData(buttonObj) {
		var trObj = $(buttonObj).parent().parent();
		var dataIndex = $(trObj).index();
		if (dataIndex < paramsCount) {
			exchangeParamData($(trObj), $(trObj).next(), "down");
		}
	}

	/**
	 * 交换两个对象，在此页面中用于交换两个tr对象。
	 * 做了三件事情，1.临时存储第一个对象;2.删除第一个对象；3.根据target将第一个对象放在第二个对象前面/后面。
	 * @method exchangeParamData(o1, o2, target)
	 * @param {Object} o1 第一个对象，{Object} o2 第二个对象，{String} target 对象交换的方向（o1在o2上/下）
	 * @return {void} 
	 */
	function exchangeParamData(o1, o2, target) {
		var tempObj = $(o1);
		$(o1).remove();
		if (target == "up") {
			$(o2).before(tempObj);
		} else if (target == "down") {
			$(o2).after(tempObj);
		}
	}

	/**
	 * 根据手工录入参数复选框，找出该行“手工录入参数类型”和“非手工录入关联参数”的下拉选框对象。
	 * 根据手工录入参数复选框勾选情况，完成以上下拉选框的切换。
	 * @method switchParamsInputType(cBxObj)
	 * @param {cBxObj} 手工录入参数复选框对象
	 * @return {void} 
	 */
	function switchParamsInputType(cBxObj) {
		var taskParamDataTypeObj = $(cBxObj).parent().parent().find(
				"[name='taskParamDataTypeDummy']");
		var paramIdObj = $(cBxObj).parent().parent().find(
				"[name='paramIdDummy']");
		if (cBxObj.checked) {
			taskParamDataTypeObj.attr("disabled", false);
			paramIdObj.attr("disabled", true);
		} else {
			taskParamDataTypeObj.attr("disabled", true);
			paramIdObj.attr("disabled", false);
		}
	}

	/**--------------------------------------页面校验、提交相关方法-------------------------------------------- */

	/**
	 * 保存按钮点击的响应方法，保存detail页面信息。
	 * @method saveDetailInfo()
	 * @return {void} 
	 */
	function saveDetailInfo() {
		if (checkForm()) {
			submitForm();
		}
	}

	/**
	 * 校验提交的表单信息，调用方法校验作业基本信息和作业参数信息。
	 * @method checkForm()
	 * @return {Boolean} result
	 */
	function checkForm() {
		var result = (checkTaskBasic() && checkTaskParam());
		return result;
	}

	/**
	 * 校验提交的表单的作业基本信息，校验空值。
	 * @method checkTaskBasic()
	 * @return {Boolean} result
	 */
	function checkTaskBasic() {
		var result = true;
		var basicInfoArray = [ "batchTaskVO.taskName", "batchTaskVO.taskClazz"];
		var basicInfoObj = null;
		for (var i = 0; i < basicInfoArray.length; i++) {
			basicInfoObj = $("#" + trans(basicInfoArray[i]));
			if (basicInfoObj.val() == "") {
				alertNull(basicInfoObj.parent().prev().html());
				result = false;
				break;
			}
		}
		return result;
	}

	/**
	 * 校验提交的表单的作业参数信息，调用方法校验作业参数名称信息、手工录入参数类型信息、和非手工录入关联参数信息。
	 * @method checkTaskParam()
	 * @return {Boolean} result
	 */
	function checkTaskParam() {
		var result = (checkTaskParamNames() && checkTaskParamTypes() && checkParamIds());
		return result;

	}

	/**
	 * 校验作业参数名称信息，参数名称不能为空。
	 * @method checkTaskParamNames()
	 * @return {Boolean} result
	 */
	function checkTaskParamNames() {
		var result = true;
		var objectArray = $(trans("input[name=taskParamName]"));
		for (var i = 0; i < objectArray.length; i++) {
			if (objectArray[i].value == "") {
				alertNull("第" + (i + 1) + "个参数名称");
				result = false;
				break;
			}
		}
		return result;
	}

	/**
	 * 校验手工录入参数类型信息,当手工录入参数checkbox被勾选时，手工录入参数类型必须选择。
	 * @method checkTaskParamTypes()
	 * @return {Boolean} result
	 */
	function checkTaskParamTypes() {
		var result = true;
		var objectArray = $(trans("select[name=taskParamDataTypeDummy]"));
		for (var i = 0; i < objectArray.length; i++) {
			var isManuObj = $(objectArray[i]).parent().parent().find(
					"[name='isManuDummy']");
			if (objectArray[i].value == ""
					&& $(isManuObj).attr("checked") == "checked") {
				alertNull("第" + (i + 1) + "个数据类型");
				result = false;
				break;
			}
		}
		return result;
	}

	/**
	 * 校验非手工录入关联参数信息，当手工录入参数checkbox没有被勾选时，非手工关联参数必须选择。
	 * @method checkParamIds()
	 * @return {Boolean} result
	 */
	function checkParamIds() {
		var result = true;
		var objectArray = $(trans("select[name=paramIdDummy]"));
		for (var i = 0; i < objectArray.length; i++) {
			var isManuObj = $(objectArray[i]).parent().parent().find(
					"[name='isManuDummy']");
			if (objectArray[i].value == ""
					&& $(isManuObj).attr("checked") != "checked") {
				alertNull("第" + (i + 1) + "个关联参数");
				result = false;
				break;
			}
		}
		return result;
	}

	/**
	 * 必填项未填提示。
	 * @method alertNull(str)
	 * @return {void}
	 */
	function alertNull(str) {
		alertMsg.error(str + "为必填项，必须填写.");
	}

	/**
	 * 提交表单。提前前调用方法，根据傀儡对象的勾选、选择情况，向相应的hidden对象赋值。
	 * @method submitForm()
	 * @return {void} 
	 */
	function submitForm() {
		checkBoxAssign([ "isManu", "isArray", "isRequired",
				"batchTaskVO.isSpringBean" ]);
		selectAssign([ "taskParamDataType", "paramId" ]);
		$.ajax({
			type : "POST",
			data : $('#batchTaskDetailForm').serialize(),
			url : submitUrl,
			dataType : "json",
			success : function(json) {
				receiveJsonMessage(json);
			}
		});
	}

	/**
	 * 接收并相应Json返回的信息
	 * @method receiveJsonMessage(json)
	 * @param {String} json
	 * @return {void} 
	 */
	function receiveJsonMessage(json) {
		if (json.substring(0, 2) == "ok") {
			alertMsg.correct(json.substring(3));
			dialogClose();
			navTabPageBreak();
		} else if (json.substring(0, 2) == "er") {
			alertMsg.error(json.substring(3));
		}
	}

	/**
	 * 对傀儡checkbox相应的hidden对象赋值。
	 * @method checkBoxAssign(cBxNames)
	 * @param {String[]} cBxNames checkbox的Name数组
	 * @return {void} 
	 */
	function checkBoxAssign(cBxNames) {
		for (var i = 0; i < cBxNames.length; i++) {
			var sourceArray = $(trans("input[name=" + cBxNames[i] + "Dummy"
					+ "]"));
			var targetArray = $(trans("input[name=" + cBxNames[i] + "]"));
			for (var j = 0; j < sourceArray.length; j++) {
				if (sourceArray[j].checked) {
					targetArray[j].value = "1";
				} else {
					targetArray[j].value = "0";
				}
			}
		}
	}

	/**
	 * 对傀儡select下拉选框相应的hidden对象赋值。
	 * @method selectAssign(selectNames)
	 * @param {String[]} selectNames select的Name数组
	 * @return {void} 
	 */
	function selectAssign(selectNames) {
		for (var i = 0; i < selectNames.length; i++) {
			var sourceArray = $(trans("select[name=" + selectNames[i] + "Dummy"
					+ "]"));
			var targetArray = $(trans("input[name=" + selectNames[i] + "]"));
			for (var j = 0; j < sourceArray.length; j++) {
				if (!sourceArray[j].disabled) {
					targetArray[j].value = sourceArray[j].value;
				} else {
					targetArray[j].value = "-1";
				}
			}
		}
	}

	/**--------------------------------------其他方法-------------------------------------------- */

	/**
	 * 为了避免JQuery将“.”作为对象操作来处理（如batchTaskVO.taskName），故将.替换为\.。
	 * @method trans(str)
	 * @param {String} str 
	 * @return {String} str
	 */
	function trans(str) {
		return str.replace(".", "\\.");
	}

	/**
	 * 关闭当前对话框
	 * @method dialogClose()
	 * @return {void}
	 */
	function dialogClose() {
		$.pdialog.closeCurrent();
	}
</script>

<!-- ------------------------------- 细览页面传值隐藏内容开始            ------------------------------   -->

<!-- 作业参数数量   -->
<input name="paramsCountDummy" id="paramsCountDummy"
	value="${paramsCount}" type="hidden" />

<!-- 细览页面控制参数，标识此页面是add还是update   -->
<div id="detailControlDummy" style="display: none">
	<input id="detailControlFlag" type="hidden"
		value="${detailControlFlag}" />
</div>

<!-- 非手工关联参数下拉列表   -->
<div id="paramIdDummyDiv" style="display: none">
	<select name="paramIdDummyFormer">
		<s:iterator value="paramManageVOList">
			<option value='${paramId}'>${paramName}</option>
		</s:iterator>
	</select>
</div>

<!-- ------------------------------- 细览页面传值隐藏内容结束            ------------------------------   -->

<div class="pageContent">
	<form id="batchTaskDetailForm" class="pageForm required-validate"
		method="post" onsubmit="return validateCallback(this, dialogAjaxDone)">
		
		<!-- taskId，用于update提交时使用  -->
	<input type="hidden" name="batchTaskVO.taskId" id="batchTaskVO.taskId"
		value="${batchTaskVO.taskId}" />

		<!-- ------------------------------- 细览页面作业基本信息内容开始            ------------------------------   -->
		<div class="pageFormContent">

			<h1>作业基本信息</h1>
			<table style="width: 90%; margin: 0 auto;">
			<tr height="25">
				<td>作业名称</td>
				<td><input type="text" name="batchTaskVO.taskName"
							id="batchTaskVO.taskName" value="${batchTaskVO.taskName}"
							class="required" /></td>
				<td>作业执行类</td>
				<td><input type="text" name="batchTaskVO.taskClazz"
							id="batchTaskVO.taskClazz" value="${batchTaskVO.taskClazz}"
							class="required" /></td>
			</tr>
			<tr>				
				<td>所属系统</td>
				<td>
<%-- 					<input type="hidden" id="taskSystemHidden" value="${batchTaskVO.taskSystem}" /> --%>
<!-- 					<Field:codeTable id="taskSystem" name="batchTaskVO.taskSystem" tableName="T_UDMP_BATCH_PARAM_SUB" whereClause="PARAM_ID=4" disabled="false"/> -->
						<input type="text" name="batchTaskVO.taskSystem"	id="batchTaskVO.taskSystem" value="${batchTaskVO.taskSystem}"			class="required" />
				</td>
				<td>Spring-bean</td>
				<td><input type="checkbox" name="batchTaskVO.isSpringBeanDummy" />
						<input type="hidden" name="batchTaskVO.isSpringBean"
							value="${batchTaskVO.isSpringBean}" /></td>
			</tr>
			</table>
		</div>
			

		<!-- ------------------------------- 细览页面作业基本信息内容结束            ------------------------------   -->

		<!-- ------------------------------- 细览页面作业参数信息内容开始            ------------------------------   -->

		<div class="pageFormContent">
			<h1>作业参数信息</h1>
			<div>
				<table class="list" width="100%">
					<thead>
						<tr align="center">
							<th>操作</th>
							<th>参数名称</th>
							<th>手工录入参数</th>
							<th>手工录入参数类型</th>
							<th>非手工录入关联参数</th>
							<th>数组</th>
							<th>必选</th>
							<th>顺序</th>
						</tr>
					</thead>
					<tbody id="parametersList">

						<!-- 隐藏第一条tr,内容为空，供新增页面初始化使用 -->
						<tr style="display: none" />

						<s:iterator value="batchTaskVO.batchTaskParamVOList" status="st">
							<tr>
								<td><input type="button" value="＋"
									onclick="addParamData(this)" /><input type="button" value="－"
									onclick="delParamData(this)" /></td>
								<td><input type="text" name="taskParamName"
									value="${taskParamName}" class="required" /></td>
								<td align="center"><input type="checkbox"
									name="isManuDummy" onclick="switchParamsInputType(this)" /> <input
									type="hidden" name="isManu" value="${isManu}" /></td>
								<td><input type="hidden" name="taskParamDataType"
									value="${taskParamDataType}" /></td>
								<td><input type="hidden" name="paramId" value="${paramId}" />
								</td>
								<td align="center"><input name="isArrayDummy"
									type="checkbox" value="" /> <input name="isArray"
									type="hidden" value="${isArray}" /></td>
								<td align="center"><input name="isRequiredDummy"
									type="checkbox" value="" /> <input name="isRequired"
									type="hidden" value="${isRequired}" /></td>
								<td><input type="button" value="↑"
									onclick="upParamData(this)" /><input type="button" value="↓"
									onclick="downParamData(this)" /></td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
			</div>
		</div>

		<!-- ------------------------------- 细览页面作业参数信息内容结束            ------------------------------   -->

	</form>
	<div class="formBar">
		<ul>
			<li>
				<div class="button">
					<div class="buttonContent">
						<button type="button" id="saveButton" onclick="saveDetailInfo()">保存</button>
					</div>
				</div>
			</li>
			<li>
				<div class="button">
					<div class="buttonContent">
						<button type="button" id="cancelButton" class="close"
							onclick="javascript:dialogClose();">取消</button>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>