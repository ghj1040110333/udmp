<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/JavaScript">
	
</script>
<div class="pageContent">
	<form id="updateRoleGroupFormId" name="updateRoleGroupForm" class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);" action="sys/updateRoleGroup_roleGroupAction.action?menuId=${menuId}" method="post">
		<input type="hidden" name="roleGroupVo.roleGroupId" value="${roleGroupAVO.roleGroupId}"/>
		<div class="pageFormContent nowrap" layoutH="70">
			<dl>
				<dt>角色组编号：</dt>
				<dd>
					<input type="text" name="roleGroupAVO.roleGroupId" maxlenght="20" value="${roleGroupAVO.roleGroupId}" readonly="readonly"/>
				</dd>
			</dl>
			<dl>
				<dt>角色组名称：</dt>
				<dd>
					<input type="text" name="roleGroupAVO.roleGroupName" maxlenght="20" value="${roleGroupAVO.roleGroupName}" maxlenght="200" class="required"/>
				</dd>
			</dl>
			<dl>
				<dt>角色组描述：</dt>
				<dd>
					 <textarea rows="3" cols="54" name="roleGroupAVO.roleGroupDesc">${roleGroupAVO.roleGroupDesc}</textarea>
				</dd>
			</dl>
		</div>
		<div class="formBar" style="border-left-width: 1px; border-right-width: 1px;; border-bottom-width: 1px;">
			<ul style="margin-top: -3px;">
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button id="roleGroupUpdateSubmit" type="submit">确定</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" id="roleGroupUpdateCancel" onclick="$.pdialog.closeCurrent();">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>