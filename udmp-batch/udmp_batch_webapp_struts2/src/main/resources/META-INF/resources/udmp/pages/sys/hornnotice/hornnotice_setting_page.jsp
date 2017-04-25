<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<div class="tabsContent">
	<div>

		<div layoutH="20"
			style="float: left; display: block; overflow: auto; width: 180px; border: solid 1px #CCC; line-height: 21px; background: #fff">
			<ul class="tree treeFolder">
				<li>
					<a href="javascript">个人设置</a>
					<ul>
						<li>
							<a href="modPassWord_loginAction.action" target="ajax"
								rel="personal_setting">密码修改</a>
						</li>
						<li>
							<a href="sys/showNavigationPage_hornPersonalSettingAction.action" target="ajax"
								rel="personal_setting">导航栏设置</a>
						</li>
					</ul>
				</li>
			</ul>
		</div>

		<div layoutH="20" id="personal_setting" class="unitBox"
			style="margin-left: 200px; width: 650px;">
			<!--#include virtual="list1.html" -->
		</div>

	</div>
	<div class="tabsFooter">
		<div class="tabsFooterContent"></div>
	</div>
</div>