<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script>
	function servicePublishValidate(form, callback) {
		// 校验操作类型必选
		if ($("#operType :radio:checked", $.pdialog.getCurrent()).length == 0) {
			alertMsg.warn("请选择操作类型");
			return false;
		}
		// 校验服务支持类型必选
		if ($("#supportType :checkbox:checked", $.pdialog.getCurrent()).length == 0) {
			alertMsg.warn("请选择服务支持类型");
			return false;
		}
		var fileArr = [ 'inputVO', 'outPutVO', 'bizUccClass' ];
		var fileNameArr = [ '输入参数VO路径', '输出参数VO路径', '业务UCC路径' ];
		// 遍历为VO和CLASSPATH赋值
		for ( var i in fileArr) {
			var input = fileArr[i];
			var fileInput = $("#" + input, $.pdialog.getCurrent());
			var fileInputVal = udmpUtil.getPath(document.getElementById(input));
			//判断已经获取了该jQuery对象，并且该对象中值不为空
			if (fileInput.length > 0 && fileInputVal) {
				if (fileInput.val().indexOf(".java") == -1) {
					alertMsg.warn("你确定你选择的" + fileNameArr[i] + "是.java文件么");
					return false;
				}
				// 判断业务接口是以I开头
				if(i == 2){
					//获取最后一个点，截取接口名
					var lastdot = fileInputVal.lastIndexOf(".");
					var lastG = fileInputVal.lastIndexOf("\\");
					var bizUccName = fileInputVal.substring(lastG + 1,lastdot);
					//若不是以I开头，则不允许提交
					if(bizUccName[0] != 'I'){
						alertMsg.warn("业务UCC接口要以I开头");
						return false;
					}
				}
				var fileInputIndex = fileInputVal.indexOf("com\\nci\\");
				// 判断保重是否含有com.nci
				if (fileInputIndex == -1) {
					alertMsg.warn("你选择的" + fileNameArr[i] + "包路径不正确");
					return false;
				}
				// 取出包路径
				fileInputVal = fileInputVal.slice(fileInputIndex).replaceAll(
						"\\\\", ".");
				// 去除.java后缀
				fileInputVal = fileInputVal.substr(0, fileInputVal.length - 5);
				// 为隐藏于赋值
				$("#" + input + "Path", $.pdialog.getCurrent()).val(
						fileInputVal);
			}
		}
		return validateCallback(form, callback);
	}
</script>
<style>
#serviceInfoAdd p {
	width: 580px;
	margin-top: 5px;
}

#serviceInfoAdd p label {
	margin-left: 50px;
}

.firstInput {
	margin-left: 20px;
}

.secondInput {
	margin-left: 60px;
}
</style>
<div class="pageContent">
	<form method="post"
		action="sys/addServicePublish_servicePublishAction.action?leftFlag=0&menuId=${menuId}"
		class="pageForm required-validate"
		onsubmit="return servicePublishValidate(this,dialogAjaxDone)">
		<div class="pageFormContent" layoutH="56" id="serviceInfoAdd">
			<p>
				<label>服务名称：</label> <input name="servicePublishAVO.serviceName"
					required type="text" size="50" />
			</p>
			<p>
				<label>服务描述：</label>
				<textarea name="servicePublishAVO.serviceDesc"
					style="width: 280px; height: 30px;" required></textarea>
			</p>
			<p style="margin-top: 10px;">
				<label>服务用途：</label>
				<textarea name="servicePublishAVO.servicePurpose"
					style="width: 280px; height: 30px;" required></textarea>
			</p>
			<p style="margin-top: 15px;" id="operType">
				<label>操作类型：</label> <input type="radio" class="firstInput"
					name="servicePublishAVO.serviceOperType" value="U" /> 交易 <input
					type="radio" class="secondInput"
					name="servicePublishAVO.serviceOperType" value="Q" /> 查询
			</p>
			<p id="supportType">
				<label>支持服务类型：</label> <input type="checkbox" class="firstInput"
					name="servicePublishAVO.allowHessian" value="Y"> 支持HS <input
					type="checkbox" class="secondInput"
					name="servicePublishAVO.allowWebservice" value="Y"> 支持WS<br />
				<input type="checkbox" class="firstInput"
					name="servicePublishAVO.allowApi" value="Y"> 支持API
			</p>
			<p style="margin-top: 15px;">
				<label>输入参数VO全路径：</label> <input type="file" size="30" required
					id="inputVO" /> <input type="hidden"
					name="servicePublishAVO.bizParamter" id="inputVOPath">(interface工程下)
			</p>
			<p>
				<label>输出参数VO全路径：</label> <input type="file" size="30" required
					id="outPutVO" /> <input type="hidden"
					name="servicePublishAVO.bizReturnParamter" id="outPutVOPath">(interface工程下)
			</p>
			<p>
				<label>业务UCC接口路径：</label> <input type="file" size="30" required
					id="bizUccClass" /> <input type="hidden"
					name="servicePublishAVO.bizUccClassPath" id="bizUccClassPath">(impl工程下)
			</p>
			<p>
				<label>业务UCCBean名称：</label> <input title="spring注入的BeanID"
					name="servicePublishAVO.bizUccBeanName" size="50" required />
			</p>
			<p>
				<label>业务UCC方法名称：</label> <input title="UCC接口方法全称"
					name="servicePublishAVO.bizUccMethodName" size="50" required />
			</p>
			<p>
				<label>服务编码：</label> <input name="servicePublishAVO.serviceCode"
					type="text" size="50" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" id="servicePublishAddSubmit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close" id="servicePublishAddCancel">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
