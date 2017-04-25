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
			//获取输入框jQuery对象
			var input = fileArr[i];
			var fileInput = $("#" + input, $.pdialog.getCurrent());
			// 判断操作时要修改路径文件
			var fileInputVal = fileInput.val();
			//判断已经获取了该jQuery对象，并且该对象中值不为空
			if (fileInput.length > 0 && fileInputVal) {

				if (fileInput.val().indexOf(".java") == -1) {
					alertMsg.warn("你确定你选择的" + fileNameArr[i] + "是.java文件么");
					return false;
				}
				// 判断业务接口是以I开头
				if (i == 2) {
					//获取最后一个点，截取接口名
					var lastdot = fileInputVal.lastIndexOf(".");
					var lastG = fileInputVal.lastIndexOf("\\");
					var bizUccName = fileInputVal.substring(lastG + 1, lastdot);
					//若不是以I开头，则不允许提交
					if (bizUccName[0] != 'I') {
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
	$(".showFileInput").click(function() {
		// 获取对应id
		var hiddenP = $(this).attr("id") + "_P";
		var $hiddenP = $("#" + hiddenP, $.pdialog.getCurrent());
		// 将隐藏选择上传框进行显示
		$hiddenP.css("display", "block");
		// 为file输入框加入非空校验
		$hiddenP.find("input:file").attr("required", "required");
		// 删除disabled属性
		$hiddenP.find("input").removeAttr("disabled");
		// 删除原P元素
		$(this).parents("p:first").remove();
	});
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
		action="sys/editForUpdateServicePublish_servicePublishAction.action?leftFlag=0&menuId=${menuId}"
		class="pageForm required-validate"
		onsubmit="return servicePublishValidate(this, dialogAjaxDone);">
		<div class="pageFormContent" layoutH="56" id="serviceInfoAdd">
			<p style="display: none;">
				<label>服务名称：</label> <input name="servicePublishAVO.serviceId"
					required type="text" size="50"
					value="${servicePublishAVO.serviceId}" />
			</p>
			<p>
				<label>服务名称：</label> <input name="servicePublishAVO.serviceName"
					required type="text" size="50"
					value="${servicePublishAVO.serviceName}" />
			</p>
			<p>
				<label>服务描述：</label>
				<textarea name="servicePublishAVO.serviceDesc" required
					style="width: 280px; height: 30px;">${servicePublishAVO.serviceDesc }</textarea>
			</p>
			<p style="margin-top: 10px;">
				<label>服务用途：</label>
				<textarea name="servicePublishAVO.servicePurpose" required
					style="width: 280px; height: 30px;" required>${servicePublishAVO.servicePurpose }</textarea>
			</p>
			<p style="margin-top: 15px;" id="operType">
				<label>操作类型：</label> <input type="radio" class="firstInput"
					name="servicePublishAVO.serviceOperType" value="U"
					<c:if test="${ servicePublishAVO.serviceOperType == 'U'}">checked</c:if> />
				交易 <input type="radio" class="secondInput"
					name="servicePublishAVO.serviceOperType" value="Q"
					<c:if test="${ servicePublishAVO.serviceOperType == 'Q'}">checked</c:if> />
				查询
			</p>
			<p id="supportType">
				<label>支持服务类型：</label> <input type="checkbox" class="firstInput"
					name="servicePublishAVO.allowHessian" value="Y"
					<c:if test="${ servicePublishAVO.allowHessian == 'Y'}">checked</c:if>>
				支持HS <input type="checkbox" class="secondInput"
					name="servicePublishAVO.allowWebservice" value="Y"
					<c:if test="${ servicePublishAVO.allowWebservice == 'Y'}">checked</c:if>>
				支持WS<br /> <input type="checkbox" class="firstInput"
					name="servicePublishAVO.allowApi" value="Y"
					<c:if test="${ servicePublishAVO.allowApi == 'Y'}">checked</c:if>>
				支持API
			</p>
			<p style="margin-top: 15px;">
				<label>输入参数VO全路径：</label> <input
					name="servicePublishAVO.bizParamter" size="50" required
					value="${servicePublishAVO.bizParamter }" />
				<button type="button" class="showFileInput" id="bizPara">更改</button>
			</p>
			<p style="display: none; margin-top: 15px;" id="bizPara_P">
				<label>输入参数VO全路径：</label> <input size="30" id="inputVO"
					name="bizParamter" type="file" /> <input type="hidden"
					disabled="disabled" name="servicePublishAVO.bizParamter"
					id="inputVOPath">(interface工程下)
			</p>
			<p>
				<label>输出参数VO全路径：</label> <input
					name="servicePublishAVO.bizReturnParamter" size="50" required
					value="${servicePublishAVO.bizReturnParamter }" />
				<button type="button" class="showFileInput" id="bizReturnPara">更改</button>
			</p>
			<p style="display: none;" id="bizReturnPara_P">
				<label>输出参数VO全路径：</label> <input type="file" size="30"
					name="bizReturnParamter" id="outPutVO" /> <input type="hidden"
					disabled="disabled" name="servicePublishAVO.bizReturnParamter"
					id="outPutVOPath">(interface工程下)
			</p>
			<p>
				<label>业务接口UCC路径：</label> <input
					name="servicePublishAVO.bizUccClassPath" size="50" required
					value="${servicePublishAVO.bizUccClassPath }" />
				<button type="button" class="showFileInput" id="bizUccClassPth">更改</button>
			</p>
			<p id="bizUccClassPth_P" style="display: none;">
				<label>业务接口UCC路径：</label> <input type="file" size="30"
					id="bizUccClass" /> <input type="hidden" disabled="disabled"
					name="servicePublishAVO.bizUccClassPath" id="bizUccClassPath">(impl工程下)
			</p>
			<p>
				<label>业务UCCBean名称：</label> <input
					name="servicePublishAVO.bizUccBeanName" size="50" required
					value="${servicePublishAVO.bizUccBeanName }" />
			</p>
			<p>
				<label>业务UCC方法名称：</label> <input
					name="servicePublishAVO.bizUccMethodName" size="50" required
					value="${servicePublishAVO.bizUccMethodName }" />
			</p>
			<p>
				<label>服务编码：</label> <input name="servicePublishAVO.serviceCode"
					type="text" size="50" value="${servicePublishAVO.serviceCode }" />
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li><div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" id="servicePublishEditSubmit">保存</button>
						</div>
					</div></li>
				<li>
					<div class="button">
						<div class="buttonContent">
							<button type="button" class="close" id="servicePublishEditCancel">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
