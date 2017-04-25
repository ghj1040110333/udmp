<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/JavaScript">
	
</script>
<div class="pageContent">
	<form id="updateRoleFormId" name="updateRoleForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);"
		action="sys/updateRole_roleAction.action?menuId=${menuId}"
		method="post">
		<input type="hidden" name="roleVo.roleId" value="${roleTVO.roleId}"/>
		<div class="pageFormContent nowrap" layoutH="70">
			<dl>
				<dt>角色编号：</dt>
				<dd>
					<input type="text" name="roleAVO.roleId" maxlenght="20"
						value="${roleAVO.roleId}" readonly="readonly"/>
				</dd>
			</dl>
			<dl>
				<dt>角色名称：</dt>
				<dd>
					<input type="text" name="roleAVO.roleName" maxlenght="20"
						value="${roleAVO.roleName}" maxlenght="200" class="required"/>
				</dd>
			</dl>
			<dl>
				<dt>角色描述：</dt>
				<dd>
					 <textarea rows="3" cols="54"
								name="roleAVO.roleDesc">${roleAVO.roleDesc}</textarea>
				</dd>
			</dl>
		</div>
		<div class="formBar"
			style="border-left-width: 1px; border-right-width: 1px;; border-bottom-width: 1px;">
			<ul style="margin-top: -3px;">
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" id="roleUpdateSave">确定</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" id="roleUpdateCancel" onclick="$.pdialog.closeCurrent();">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>