<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:set var="ctx">${pageContext.request.contextPath}</s:set>
<link rel="stylesheet" type="text/css" href="${ctx}/udmp/plugins/jquery_ui/multiselect/jquery.multiselect.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/udmp/plugins/jquery_ui/assets/style.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/udmp/plugins/jquery_ui/assets/prettify.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/udmp/plugins/jquery_ui/jquery-ui.css" />
<script type="text/javascript" src="${ctx}/udmp/plugins/jquery_ui/jquery.ui.core.js"></script>
<script type="text/javascript" src="${ctx}/udmp/plugins/jquery_ui/jquery.ui.widget.js"></script>
<script type="text/javascript" src="${ctx}/udmp/plugins/jquery_ui/assets/prettify.js"></script>
<script type="text/javascript" src="${ctx}/udmp/plugins/jquery_ui/multiselect/jquery.multiselect.js"></script>
<!-- 数据权限修改页面 -->
<script type="text/javascript">
var permissionId_dialog = $("#permissionId_dialog");
var permissionName_dialog = $("#permissionName_dialog");
var permissionForm_dialog = $("#permissionForm");
var op_flag = $("#op_flag");
$(document).ready(init());
function init(){
	 $("select[selectType='selectMulti']").multiselect({
	        noneSelectedText: "==请选择==",
	        checkAllText: "全选",
	        uncheckAllText: '全不选',
	        selectedList:2
	    });
	    $("select[selectType='selectSingle']").multiselect({                                                                                                                                                                                                            
	    	multiple:false,
	    	header:"请选择",
	        noneSelectedText: "==请选择==",
	        selectedList:1
	    });
	if(op_flag.val()=='1'){
		//新增
		var permissionTypeId = $("#permissionTypeId").val();
		permissionId_dialog.removeAttr("readonly");
		permissionId_dialog.css("display","none");
		$("#permissionId_label").css("display","none");
		formClear(permissionForm_dialog);
		$("#op_flag").val('1');
		$("#permissionTypeId").val(permissionTypeId);
		//permissionForm_dialog.clear();
	}else if(op_flag.val()=='2'){
		//更新
		permissionId_dialog.css("display","block");
		$("#permissionId_label").css("display","block");
		permissionId_dialog.attr("readonly","readonly");
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
function saveForm(obj){
	var jsonText = conTableToJson();
	if(!jsonText){
		return false;
	}
	$("#permissionDatas_dialog").val(jsonText);
	return validateCallback(obj, dialogAjaxDone)
}

/**
 * 保存校验
 * 先校验数字的格式，再校验数字的顺序
 */
function saveCheck(){
	try{
		var resPattern = checkNumberPattern();
		//alert(resPattern);
		if(resPattern != 0){
			return false;
		}
		var resOrder = checkTwoNumOrder();
		//alert(resOrder);
		if(resOrder!=0){
			return false;
		}
		var form = document.getElementById("permissionForm");
		return saveForm(form);
	}catch(e){
		alertMsg.error("校验数字不合法，请重新输入！");
		return false;
	}
}
</script>
<div class="pageContent">
	<form method="post" id="permissionForm"
		action="sys/formUpdatePermission_dataPermAction.action?menuId=${menuId}"
		class="pageForm required-validate"
		>
		<input type="hidden" name="op_flag" id="op_flag" value="${op_flag}" />
		<input type="hidden" name="permissionTypeId" id="permissionTypeId" value="${permissionTypeId}" />
		<div class="pageFormContent nowrap" layoutH="55">
			<input type="hidden" name="permissionDatas" id="permissionDatas_dialog"/>
			<table width="100%" cellpadding="0" cellspacing="10" border="0">
				<tr>
					<td width="17%">
						<div class="label" style="height: auto" id="permissionId_label">权限ID：</div>
					</td>
					<td width="33%"><input type="text" name="permissionId" id="permissionId_dialog"
						value="${permissionId}"  maxlength="20"/>
					</td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label" style="height: auto">权限名称：</div>
					</td>
					<td width="33%"><input type="text" name="permissionName" id="permissionName_dialog"
						value="${permissionName}" class="required"  maxlength="20" />
					</td>
				</tr>
				
				<tr>
					<td width="17%">
						<div class="label" style="height: auto">权限等级：</div>
					</td>
					<td width="33%"><input type="text" name="permLevelWorkflow" 
						value="${permLevelWorkflow}"  maxlength="20" />
					</td>
				</tr>
				
				<tr>
					<td width="17%">
						<div class="label">权限内容：</div>
					</td>
				</tr>
				<tr>
					<td width="33%">
						
					</td>
				</tr>
			</table>
			${permissionDataHtml}	
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="saveCheck();">保存</button>
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
