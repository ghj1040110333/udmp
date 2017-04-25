<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="pageContent">
	<form method="post" id="userForm" action="sys/updateUserLog_userLogLevelAction.action?menuId=${menuId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="50">
			<table width="100%" cellpadding="0" cellspacing="10" border="0">
				<dl>
					<dt>用户ID：</dt>
					<dd>
						<input id="userid" type="text" name="userLogAVO.userId"
							class="required" maxlength="20" title="请选择用户" value="${userLogAVO.userId}" />
					</dd>
				</dl>
				<dl>
					<dt>用户名：</dt>
					<dd>
						<input id="username" type="text" name="userLogAVO.userName"
							class="required" maxlength="20" value="${userLogAVO.userName}" />
					</dd>
				</dl>
				<dl>
					<dt>日志等级：</dt>
					<dd>
						<select id="loglevel" class="combox" name="userLogAVO.loglevel">
							<option value="trace" <s:if test="userLogAVO.loglevel==\"trace\" ">selected</s:if>>trace</option>
							<option value="debug" <s:if test="userLogAVO.loglevel==\"debug\" ">selected</s:if>>debug</option>
							<option value="info"  <s:if test="userLogAVO.loglevel==\"info\" ">selected</s:if>>info</option>
							<option value="warn"  <s:if test="userLogAVO.loglevel==\"warn\" ">selected</s:if>>warn</option>
							<option value="error" <s:if test="userLogAVO.loglevel==\"error\" ">selected</s:if>>error</option>
						</select>
					</dd>
				</dl>
			</table>
		</div>
		<div class="formBar">
			<ul style="margin-top: -3px;">
				<li>
					<div class="buttonActive" >
						<div class="buttonContent">
							<button id="userLoglvupdate" type="submit" >保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive" >
						<div class="buttonContent">
							<button id="userLoglvupdatecancel" type="button" onclick="$.pdialog.closeCurrent();">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
