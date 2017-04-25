<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="pageContent">
	<form method="post" id="userForm"
		action="sys/insertService_serviceAction.action?menuId=${menuId}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="56" id="serviceAdd">
			<p>
				<label>子系统环境：</label>
				<Field:codeTable name="serviceAVO.serviceType"
					tableName="T_UDMP_ENV_TYPE" cssClass="combox" />
			</p>
			<p>
				<label>环境IP：</label> <input name="serviceAVO.serviceIp" type="text"
					required>
			</p>
			<p>
				<label>环境端口号：</label> <input type="text" required
					name="serviceAVO.servicePort" />
			</p>
			<p>
				<label>环境上下文：</label> <input type="text" required
					name="serviceAVO.serviceContext">
			</p>
			<p>
				<label>所属子系统</label>
				<Field:codeTable name="serviceAVO.systemId" disabled="true"
					tableName="T_UDMP_SUB_SYSTEM_INFO" cssClass="combox"
					nullOption="true" value="${serviceAVO.systemId }" id="systemId" />
			</p>
			<p style="display: none;">
				<label>所属子系统：</label> <input type="text" required
					name="serviceAVO.systemId" value="${serviceAVO.systemId }">
			</p>
		</div>
		<div class="formBar">
			<ul style="margin-top: -3px;">
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" id="serviceEnvAddSubmit">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="$.pdialog.closeCurrent();"
								id="serviceEnvCancel">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
