<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/JavaScript">
/* 新增角色组时，校验角色组名称为必填项  */
	$("#saveBtn").click(function(){
		if($("#AddRoleGroupName").val()==0){
			alertMsg.warn("角色组名称为必填项！");
			return false;
		}
	});
</script>
<div class="pageContent">
	<form id="addRoleGroupFormId" name="addRoleGroupForm" class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);" action="sys/insertRoleGroup_roleGroupAction.action?menuId=${menuId}" method="post">
		<div class="pageFormContent nowrap" layoutH="50">
			<dl>
				<dt>角色组名称：</dt>
				<dd>
					<input type="text" id="AddRoleGroupName" name="roleGroupAVO.roleGroupName" maxlenght="50" class="required" />
				</dd>
			</dl>
			<dl>
				<dt>角色组描述：</dt>
				<dd>
					<textarea rows="3" cols="54" name="roleGroupAVO.roleGroupDesc" maxlenght="100"></textarea>
				</dd>
			</dl>
		</div>
		<div class="formBar" style="border-left-width: 1px; border-right-width: 1px; border-bottom-width: 1px;">
			<ul style="margin-top: -3px;">
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" id="roleGroupAddSave" id="saveBtn">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" id="roleGroupAddCancel" onclick="$.pdialog.closeCurrent();">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>