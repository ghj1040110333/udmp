<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<s:set var="ctx">${pageContext.request.contextPath}</s:set>
<script src="${ctx }/udmp/md5/md5.js" type="text/javascript"></script>
<script>
	// 取消默认提交表单事件，使用自定义方法进行传输，将原密码明文传输更改为md5加密传输
	$("#modifyPassword").click(function() {
		var $form = $("#modPassWordSubmitId");
		if (!$form.valid()) {
			return false;
		}
		// 获取旧密码
		var modPW_pwd = $("#modPW_pwd").val();
		// 获取新密码
		var modPW_NewPwd = $("#modPW_NewPwd").val();
		// 获取确认密码
		var modPW_SurePwd = $("#modPW_SurePwd").val();
		// 组装参数
		var serializeArray = new Array();
		serializeArray.push({
			name : "userVO.password",
			value : hex_md5(modPW_pwd)
		});
		serializeArray.push({
			name : "userVO.newPwd1",
			value : hex_md5(modPW_NewPwd)
		});
		serializeArray.push({
			name : "userVO.newPwd2",
			value : hex_md5(modPW_SurePwd)
		});
		$.ajax({
			type : 'POST',
			url : $form.attr("action"),
			data : serializeArray,
			dataType : "json",
			cache : false,
			success : dialogAjaxDone,
			error : DWZ.ajaxError
		});
	});
</script>
<div style="overflow: hidden; margin-top: 100px;">
	<form id="modPassWordSubmitId" method="post"
		style="width: inherit; height: 300px; margin-top: 50px;"
		action="modPassWordSubmit_loginAction.action"
		onsubmit="return validateCallback(this, dialogAjaxDone);"
		class="pageForm required-validate">
		<table align="center">
			<tr height="30">
				<td><label>旧密码：</label></td>
				<td><input id="modPW_pwd" class="required"
					name="userVO.password" type="password" style="width: 150px;" /></td>
			</tr>
			<tr height="30">
				<td><label>新密码：</label></td>
				<td><input id="modPW_NewPwd" class="required" minlength="6"
					maxlength="14" name="userVO.newPwd1" type="password"
					style="width: 150px;" /></td>
			</tr>
			<tr height="30">
				<td><label>确认密码：</label></td>
				<td><input id="modPW_SurePwd" class="required" minlength="6"
					maxlength="14" type="password" equalto="#modPW_NewPwd"
					name="userVO.newPwd2" style="width: 150px;" /></td>
			</tr>
			<tr height="50">
				<td colspan="2" align="center">
					<div class="buttonActive" style="margin-left: 120px;">
						<div class="buttonContent">
							<button type="button" id="modifyPassword">保存</button>
						</div>
					</div>
				</td>
			</tr>
		</table>
	</form>
</div>