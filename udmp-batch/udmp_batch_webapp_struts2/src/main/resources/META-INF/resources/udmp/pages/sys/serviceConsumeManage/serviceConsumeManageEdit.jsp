<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="pageContent">
	<form method="post" id="userForm"
		action="sys/updateServiceConsume_serviceConsumeManageAction.action?menuId=${menuId}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<input type="hidden" name="serviceConsumeAVO.systemId"
			value="${serviceConsumeVO.systemId }" /> <input type="hidden"
			name="serviceConsumeAVO.serviceId"
			value="${servicePublishAndConsumeAVO.serviceId }" />
		<div class="pageFormContent nowrap" layoutH="66">
			<p>
				<label>支持服务类型：</label> <select class="combox"
					name="serviceConsumeAVO.serviceType">
					<c:if test="${ servicePublishAndConsumeAVO.allowApi == 'Y'}">
						<option value="A"
							<c:if test="${servicePublishAndConsumeAVO.serviceType == 'A'}">selected</c:if>>API</option>
					</c:if>
					<c:if test="${ servicePublishAndConsumeAVO.allowHessian == 'Y'}">
						<option value="H"
							<c:if test="${servicePublishAndConsumeAVO.serviceType == 'H'}">selected</c:if>>Hessian</option>
					</c:if>
					<c:if test="${ servicePublishAndConsumeAVO.allowWebservice == 'Y'}">
						<option value="W"
							<c:if test="${servicePublishAndConsumeAVO.serviceType == 'W'}">selected</c:if>>WebService</option>
					</c:if>
				</select>
			</p>
			<p>
				<label>申请服务系统：</label>
				<Field:codeTable name="" tableName="t_udmp_sub_system_info"
					value="${nciHeadVo.manageCode }" disabled="true" cssClass="combox" />

			</p>
			<p style="width: 500px;">
				<label>修改该服务原因：</label>
				<textarea rows="3" cols="40" name="serviceConsumeAVO.serviceCause">${servicePublishAndConsumeAVO.serviceCause }</textarea>
			</p>
		</div>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" id="serviceConsumeManEditSubmit">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="$.pdialog.closeCurrent();" id="serviceConsumeManEditCancel">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
