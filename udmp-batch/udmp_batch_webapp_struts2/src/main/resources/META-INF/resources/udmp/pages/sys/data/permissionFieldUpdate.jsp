<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!-- 数据权限修改页面 -->
<script type="text/javascript">
var fieldId_dialog = $("#fieldId_dialog");
var displayName_dialog = $("#displayName_dialog");
var fieldForm_dialog = $("#fieldForm_dialog");
var op_flag = $("#op_flag");
$(document).ready(init());
function init(){
	if(op_flag.val()=='1'){
		//新增
		var permissionTypeId = $('#permissionTypeId').val();
		fieldId_dialog.css("display","none");
		$("#fieldId_label").css("display","none");
		formClear(fieldForm_dialog);
		$("#op_flag").val('1');
		$('#permissionTypeId').val(permissionTypeId);
		//fieldForm_dialog.clear();
	}else if(op_flag.val()=='2'){
		//更新
		fieldId_dialog.css("display","block");
		$("#fieldId_label").css("display","block");
		fieldId_dialog.attr("readonly","readonly");
	}
}
/**
 * 清空form
 */
function formClear(obj){
	obj.find("input").each(function(){
		$(this).val("");
	});
};

/**
 * 保存表单的操作，将table转为json字符串存储
 */
function saveForm(){
	var jsonText = conTableToJson();
	$("#permissionDatas_dialog").val(jsonText);
}
</script>
<div class="pageContent">
	<form method="post" id="fieldForm_dialog"
		action="sys/formUpdateField_permissionFieldAction.action?menuId=${menuId}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<input type="hidden" name="op_flag" id="op_flag" value="${op_flag}" />
		<input type="hidden" name="permissionTypeId" id="permissionTypeId" value="${permissionTypeId}" />
		<div class="pageFormContent nowrap" layoutH="55">
			<table width="100%" cellpadding="0" cellspacing="10" border="0">
				<tr>
					<td width="17%">
						<div class="label" style="height: auto" id="fieldId_label">记录ID：</div>
					</td>
					<td width="33%"><input type="text" name="fieldId" id="fieldId_dialog"
						value="${fieldId}"   maxlength="20"/>
					</td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label" style="height: auto">字段ID：</div>
					</td>
					<td width="33%"><input type="text" name="fieldName" id="fieldName_dialog"
						value="${fieldName}" class="required"  maxlength="20"/>
					</td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label" style="height: auto">字段名称：</div>
					</td>
					<td width="33%"><input type="text" name="displayName" id="displayName_dialog"
						value="${displayName}" class="required"  maxlength="20" />
					</td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label" style="height: auto">字段类型：</div>
					</td>
					<td width="33%">
					<select name="dataType" id="dataType_dialog">
						<s:iterator value="dataTypeMap" id="column">
							<s:property value="#column"/>
							<option value="${key}" <s:if test='dataType eq key'>selected</s:if>>${value}</option>
						</s:iterator>
<!-- 						<option value="String" <s:if test='dataType eq "String"'>selected</s:if>>单选字段</option> -->
<!-- 						<option value="StringList" <s:if test='dataType eq "StringList"'>selected</s:if>>多选字段</option> -->
<!-- 						<option value="Number" <s:if test='dataType eq "Number"'>selected</s:if>>数字区间</option> -->
					</select>
<!-- 					<input type="text" name="dataType" id="dataType_dialog" -->
<%-- 						value="${dataType}" class="required"  maxlength="20" /> --%>
					</td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label" style="height: auto">是否必填：</div>
					</td>
					<td width="33%">
						<select  name="isRequired" id="isRequired_dialog">
							<option value="Y" <s:if test='isRequired eq "Y"'>selected</s:if> >是</option>
							<option value="N" <s:if test='isRequired eq "N"'>selected</s:if> >否</option>
						</select>
<!-- 						<input type="text" name="isRequired" id="isRequired_dialog" -->
<%-- 						value="${isRequired}" class="required"  maxlength="20" /> --%>
					</td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label" style="height: auto">字段对应代码：</div>
					</td>
					<td width="33%"><input type="text" name="codeName" id="codeName_dialog"
						value="${codeName}"  maxlength="20" />
					</td>
				</tr>
			</table>
		</div>

		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" onclick="saveForm();">保存</button>
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
