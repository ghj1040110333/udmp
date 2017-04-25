<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<style>
.toTop {
	top:40px
}
</style>
<script type="text/JavaScript">
/* 新增参数时，校验参数范围为必填项  */
$("#saveBtn").click(function(){
	if($("#addScopeCode").val()==0){
		alertMsg.warn("参数范围为必填项");
		return false;
	}
});
</script>
<div class="pageContent">
	<form id="addParaFormId" name="addParaForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);"
		action="sys/insertPara_paraDefAction.action?menuId=${menuId}"
		method="post">
		<div class="pageFormContent" layoutH="50">
			<dl>
				<dt>参数名称：</dt>
				<dd>
					<input type="text" name="paraDefAVO.paraName" maxlenght="50"
						class="required" />
				</dd>
			</dl>
			<dl>
				<dt>参数描述：</dt>
				<dd>
					<textarea rows="3" cols="54" name="paraDefAVO.paraDesc" maxlenght="100" class="required"></textarea>
				</dd>
			</dl>
			<dl class="toTop">
				<dt>参数数值：</dt>
				<dd>
					<input type="text" name="paraDefAVO.paraValue" maxlenght="50"
						class="required" />
				</dd>
			</dl>
			<dl class="toTop">
				<dt>参数数值名称：</dt>
				<dd>
					<input type="text" name="paraDefAVO.paraValueName" maxlenght="50"
						class="required" />
				</dd>
			</dl>
			<dl class="toTop">
				<dt>参数范围：<span style="color:red;">*</span></dt>
				<dd>
					<s:select cssClass="required combox" id="addScopeCode" name="paraDefAVO.scopeCode" list="paraScopeMap" listKey="key" listValue="value" 
							headerKey="0" headerValue="全部">
					</s:select>
				</dd>
			</dl>
		</div>
		<div class="formBar"
			style="border-left-width: 1px; border-right-width: 1px; border-bottom-width: 1px;">
			<ul style="margin-top: -3px;">
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" id="saveBtn">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" id="paraDefAddCancel" onclick="$.pdialog.closeCurrent();">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>