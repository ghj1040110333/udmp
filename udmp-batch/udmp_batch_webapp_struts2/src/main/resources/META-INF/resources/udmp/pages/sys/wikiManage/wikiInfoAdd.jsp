<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<style>
	.pageFormContent dl{
		width:570px;
	}
	.pageFormContent dd{
		width:440px;
	}
	.pageFormContent dd input{
		width:350px;
	}
</style>
<div class="pageContent">
	<form method="post" id="userForm"
		action="sys/insertWikiInfo_wikiUrlConfigAction.action?menuId=${menuId}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent" layoutH="55">
			<dl>
				<dt>知识库ID:</dt>
				<dd>
					<input type="text" name="wikiUrlConfigAVO.wikiId" class="required"
						maxlength="200" />
				</dd>
			</dl>
			<dl>
				<dt>知识库名称:</dt>
				<dd>
					<input type="text" name="wikiUrlConfigAVO.wikiName"
						class="required" maxlength="50" />
				</dd>
			</dl>
			<dl>
				<dt>知识库URL:</dt>
				<dd>
					<input type="text" name="wikiUrlConfigAVO.wikiUrl" class="required"
						maxlength="200" />
				</dd>
			</dl>
		</div>
		<div class="formBar">
			<ul style="margin-top: -3px;">
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="$.pdialog.closeCurrent();">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
