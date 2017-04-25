<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>	
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="menuContent" class="menuContent"
	style="display: none; position: absolute; z-index: 1002;">
	<ul id="branchTree" class="ztree"
		style="margin-top: 0; width: 300px; height: 300px"></ul>
</div>
<div class="pageContent">
	<form method="post" id="userForm"
		action="sys/insertUserLog_userLogLevelAction.action?menuId=${menuId}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="50">
			<table width="100%" cellpadding="0" cellspacing="10" border="0">
				<dl>
					<dt>用户ID：</dt>
					<dd>
						<input id="userid" type="text" name="userLogAVO.userId"
							class="required" maxlength="20" title="请选择用户" />
					</dd>
				</dl>
				<dl>
					<dt>用户名：</dt>
					<dd>
						<input id="username" name="userLogAVO.userName" class="required" readonly/> &nbsp; 
						<a id='menuBtn' class="btnLook" href='#'
							onclick='showMenu(); return false;'></a>
					</dd>
				</dl>
				<dl>
					<dt>日志等级：</dt>
					<dd>
						<select id="loglevel" class="combox" name="userLogAVO.loglevel">
						   <!-- 目前用户日志表的日志等级只包括这五个，不会进行扩展 -->
							<option value="trace">trace</option>
							<option value="debug">debug</option>
							<option value="info">info</option>
							<option value="warn">warn</option>
							<option value="error">error</option>
						</select>
					</dd>
				</dl>
			</table>
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
<script type="text/javascript">
var inputId = "username"; //输入框的id
var inputAId = "menuBtn"; //<a>标签点击按钮的id
var treeDivId = "menuContent";//树所在的div的id
var treeId = "branchTree";//树id
var setting = {
		view : {
			dblClickExpand : false
		},
		data : {
			simpleData : {
				enable : true
			},
		},
		callback : {
			onClick : onClick
		}
	};
function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	nodes = zTree.getSelectedNodes();
	//输入框赋值
	$("#" + inputId,$parentCur).attr("value", nodes[0].name);
	$("#userid",$parentCur).attr("value", nodes[0].id)
	hideMenu();
}

function showMenu() {
	var $parentCur = $.pdialog.getCurrent();
	//获取第一个paneBar的位置
	//获取action属性
	//获取树对象
	var branchObj = $("#" + inputId,$parentCur);
	//获取input框的值
	var value = $("#" + inputId,$parentCur).val() == null?"":$("#" + inputId,$parentCur).val().trim();
	//获取tree的相对位置
	var branchPosition = $("#" + inputId,$parentCur).position();
	//调用ajax 初始化树
 	$.ajax({type:"POST",url:"queryUserList_userLogLevelAction.action",data:{branchLike:value},
		dataType:"json",success:function(data){
			var zNodes = data.userList;
			$.fn.zTree.init($("#" + treeId,$parentCur),setting,zNodes);
		}
	}); 
	//显示树
	$("#" + treeDivId,$parentCur).css({
		left : branchPosition.left + 7 + "px",
		top : branchPosition.top + branchObj.outerHeight() + branchObj.outerHeight() + 12 + "px"
	}).slideDown("fast");
	//解绑事件
	$($parentCur).bind("mousedown", onBodyDown);
}

var $parentCur = $.pdialog.getCurrent();
var fval = null;
function hideMenu() {
	var $parentCur = $.pdialog.getCurrent();
	$("#" + treeDivId,$parentCur).fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	var $parentCur = $.pdialog.getCurrent();
	if(event.target.id == '' ||!(event.target.id == inputAId || event.target.id == treeDivId  
			||$(event.target).parents("#" + treeDivId,$parentCur).length > 0)){
		hideMenu();
	}
}
</script>
