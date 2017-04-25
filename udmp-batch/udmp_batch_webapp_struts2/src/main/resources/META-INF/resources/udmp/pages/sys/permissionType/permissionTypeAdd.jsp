<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
 	$("#default-check").click(function(){
		if($("#default-check").attr("checked") == "checked"){
			$("#flag-input").attr("value", "default").attr("readonly","readonly");
		} 
		else {
			$("#flag-input").removeAttr("readonly").attr("value", '');
		}
	});
</script>
<div class="pageContent">
	<form method="post" id="userForm"
		action="sys/insertPermissionType_permissionTypeAction.action?menuId=${menuId}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="55">
			<dl class='nowrap'>
				<dt>权限类型名称:</dt>
				<dd style="width:300px">
					<input type="text" name="permissionTypeAVO.permissionTypeName"
						class="required" maxlength="20" />
				</dd>
			</dl>
			<dl class='nowrap'>
				<dt>权限类型描述:</dt>
				<dd style="width:300px">
					<input type="text" name="permissionTypeAVO.permissionTypeDesc"
					 maxlength="20" />
				</dd>
			</dl>
			<dl class='nowrap'>
				<dt>权限校验类标识:</dt>
				<dd style="width:300px">
					<input id="flag-input" type="text" name="permissionTypeAVO.handlerFlag"
					 maxlength="20" />
					 <input id="default-check" type="checkbox">使用默认校验类</input>
				</dd>
			</dl>
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
