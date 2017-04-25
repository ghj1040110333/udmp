<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="pageContent">
	<form method="post" id="userForm"
		action="sys/insertServiceConsume_serviceConsumeAction.action?menuId=${menuId}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<input type="hidden" name="serviceConsumeVO.systemId"
			value="${serviceConsumeVO.systemId }" /> <input type="hidden"
			name="serviceConsumeVO.serviceId"
			value="${servicePublishAVO.serviceId }" />
		<div class="pageFormContent nowrap" layoutH="66">
			<p>
				<label>支持服务类型：</label> <select class="combox"
					name="serviceConsumeVO.serviceType">
<!-- 					<option value="">请选择</option> -->
					<c:if test="${ servicePublishAVO.allowApi == 'Y'}">
						<option value="A">API</option>
					</c:if>
					<c:if test="${ servicePublishAVO.allowHessian == 'Y'}">
						<option value="H">Hessian</option>
					</c:if>
					<c:if test="${ servicePublishAVO.allowWebservice == 'Y'}">
						<option value="W">WebService</option>
					</c:if>
				</select>
			</p>
			<p>
				<label>申请服务系统：</label>
				<Field:codeTable name="" tableName="t_udmp_sub_system_info"
					value="${nciHeadVo.manageCode }" disabled="true" cssClass="combox" />

			</p>
			<p style="width: 500px;">
				<label>申请该服务原因：</label>
				<textarea rows="3" cols="40" name="serviceConsumeVO.serviceCause">${serviceConsumeVO.serviceCause }</textarea>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" id="serviceConsumeAddSubmit">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="$.pdialog.closeCurrent();" id="serviceConsumeAddCancel">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
