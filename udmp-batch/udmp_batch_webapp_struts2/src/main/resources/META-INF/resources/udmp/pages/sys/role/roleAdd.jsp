<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/JavaScript">
/* 新增角色时，校验角色类型和子角色类型为必填项  */
	$("#saveBtn").click(function(){
		if($("#AddroleType").val()==0){
			alertMsg.warn("角色类型为必填项");
			return false;
		}
		if($("#add_subRoleType").val()==0){
			alertMsg.warn("子角色类型为必填项");
			return false;
		}
	});
</script>
<div class="pageContent">
	<form id="addRoleFormId" name="addRoleForm"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this,dialogAjaxDone);"
		action="sys/insertRole_roleAction.action?menuId=${menuId}"
		method="post">
		<!-- nowrap不换行 -->
		<div class="pageFormContent nowrap" layoutH="50">
			<dl>
				<dt>角色名称：</dt>
				<dd>
					<input type="text" name="roleAVO.roleName" maxlenght="50"
						class="required" />
				</dd>
			</dl>
			<dl>
				<dt>角色描述：</dt>
				<dd>
					<textarea rows="3" cols="54" name="roleAVO.roleDesc" maxlenght="100"></textarea>
				</dd>
			</dl>
			<dl>
				<dt>角色类型<span style="color:red;">*</span></dt>
				<dd>
					<s:select cssClass="required combox" id="AddroleType" name="roleAVO.roleType" list="roleTypeMap" listKey="key" listValue="value" 
					headerKey="" headerValue="全部"
					ref="add_subRoleType" 
					refUrl="sys/getSubRole_roleAction.action?roleTypeCode={value}">
					</s:select>
 				</dd>
			</dl>
<!-- 			<dl> -->
<%-- 				<dt>子角色类型<span style="color:red;">*</span></dt> --%>
<!-- 				<dd> -->
<%-- 					<s:select cssClass="required combox" id="AddsubRoleType" name="roleAVO.subRoleType" --%>
<%-- 					 list="subRoleTypeMap" listKey="key" listValue="value"  --%>
<%--  					 headerKey="" headerValue="全部">  --%>
<%-- 				</s:select> --%>
<!-- 				</dd> -->
<!-- 			</dl> -->
			<dl>
				<dt>子角色类型<span style="color:red;">*</span></dt>
				<dd>
					<select id="add_subRoleType" name="roleAVO.subRoleType" class="required combox">
						<option value="">全部</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>Disabled<span style="color:red;">*</span></dt>
				<dd>
					<select id="disabled" name="roleAVO.disabled" class="required combox">
						<option value="N">否</option>
						<option value="Y">是</option>
					</select>
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
							<button type="button" id="roleAddCancel" onclick="$.pdialog.closeCurrent();">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>