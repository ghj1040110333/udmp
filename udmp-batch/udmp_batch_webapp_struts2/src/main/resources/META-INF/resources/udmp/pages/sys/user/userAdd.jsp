<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@include file="/udmpCommon.jsp"%>

<script type="text/JavaScript">
	$(function(){
		$.fn.myClick = function(e, treeId, treeNode) {
			// 通过treeId获取tree对象
			var zTree = $.fn.zTree.getZTreeObj(treeId);
			// 获取选中节点
			nodes = zTree.getSelectedNodes();
			// 获取input输入框
			var branchObj = $('#branchCode', $.pdialog.getCurrent());
			// 给输入框赋值
			branchObj.attr('value', nodes[0].organCode);
			// 为后面的input赋值
			$('#branchname', $.pdialog.getCurrent()).val(
					nodes[0].organName.substring(nodes[0].organName.lastIndexOf('-') + 1));
			//给id框赋值
			var branchIdObj = $('#branchId', $.pdialog.getCurrent());
			branchIdObj.attr('value', nodes[0].organId);
			// 隐藏tree 将第一个下拉框的id作为参数传过去
			hideOrganTreeMenu('branchCode');
		};
	}); 
</script>

<div class="pageContent">
	<form method="post" id="userForm" action="sys/addUser_userAction.action?menuId=${menuId}" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="55">
			<dl>
				<dt>用户代码：</dt>
				<dd><input type="text" name="userAVo.userName" class="required"  maxlength="20"/></dd>
			</dl>
			<dl>
				<dt>用户姓名：</dt>
				<dd><input type="text" name="userAVo.realname" class="required" maxlength="20"/></dd>
			</dl>
			<dl>
				<dt>所属机构：</dt>
				<dd>
					<input name="userAVo.organCode" id="branchCode" type="text" class="organ" clickId="menuBtn" showOrgName="branchname" needAll="true"/>
					<a id="menuBtn" class="btnLook" href="#"></a>
				</dd>
			</dl>
			<dl>
				<dt>机构ID：</dt>
				<dd>
					<input name="userAVo.organId" id="branchId" type="text" readOnly/>
				</dd>
			</dl>
			<dl>
				<dt>机构名称</dt>
				<input id="branchname" type="text" readOnly/>
			</dl>
			<dl>
				<dt>用户状态：</dt>
				<dd><input type="radio" name="userAVo.userdisable" value="N" checked="checked"/>有效&nbsp;
					<input type="radio" name="userAVo.userdisable" value="Y"/>失效</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul >
				<li>
					<div class="buttonActive" >
						<div class="buttonContent">
							<button type="submit" id="userAddSubmit">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive" >
						<div class="buttonContent">
							<button type="button" onclick="$.pdialog.closeCurrent();" id="userAddCancel">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
