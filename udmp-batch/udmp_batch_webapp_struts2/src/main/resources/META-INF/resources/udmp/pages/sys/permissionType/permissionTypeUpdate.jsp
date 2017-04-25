<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="pageContent">
	<form method="post" id="userForm"
		action="sys/updatePermissionType_permissionTypeAction.action?menuId=${menuId}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="55">
						<label>权限类型id:</label>
					<input type="text" readonly
						name="permissionTypeAVO.permissionTypeId"
						value="${permissionTypeAVO.permissionTypeId}"
						maxlength="20" />
						<br/><br/>
						<label>权限类型名称：</label>
					<input type="text"
						name="permissionTypeAVO.permissionTypeName"
						value="${permissionTypeAVO.permissionTypeName}"
						 maxlength="20" />
						 <br/><br/>
						<label>权限类型描述：</label>
					<input type="text"
						name="permissionTypeAVO.permissionTypeDesc"
						value="${permissionTypeAVO.permissionTypeDesc}"
						 maxlength="20" />
						<br/><br/>
						<label>权限处理类标识：</label>
					<input type="text"
						name="permissionTypeAVO.handlerFlag"
						value="${permissionTypeAVO.handlerFlag}"
						 maxlength="20" />
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
