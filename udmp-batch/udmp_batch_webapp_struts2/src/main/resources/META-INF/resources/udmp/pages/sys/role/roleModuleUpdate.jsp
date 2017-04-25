<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/JavaScript">
/* 页面加载时，初始化菜单树 */
$(document).ready(function (){init();initModuleTree();});
var rid; /*角色ID*/
var moduleTree = "moduleTree";
var zNodesModuleRole;
var settingModuleRole = {
		data : {
			simpleData : {
				enable : true
			}
		},
		check : {
			enable : true,
			chkStyle : "checkbox",
		},
		callback : {
			onClick : onClickModuleRoleTree
		},
		view:{
			selectedMulti : false,
			showLine : false
		}
	};
function init(){
	rid = $("#rid").val();
}
/* 初始化菜单树 */
function initModuleTree(){
	$.ajax({
		type : "post",
		url : '<%=request.getContextPath()%>/sys/getModuleList_roleAction.action',
		data : {
			"rid" : rid
		},
		dataType : "json",
		success : function(result) {
			zNodesModuleRole = result.ModuleList;
			$.fn.zTree.init($("#"+moduleTree), settingModuleRole, zNodesModuleRole);
			var zTree = $.fn.zTree.getZTreeObj("moduleTree");
			zTree.expandAll(true);
		},
		error : function(result) {
			alert('初始化菜单树异常');
		}
	});
};
function onClickModuleRoleTree(event, treeId, treeNode, clickFlag) {
	var zTree = $.fn.zTree.getZTreeObj("moduleTree");
	zTree.expandNode(treeNode);
};
/* 保存角色菜单 */
function saveModuleRole(){
	var treeObj = $.fn.zTree.getZTreeObj("moduleTree");
	var checkedNodes = treeObj.getCheckedNodes(); //选中的节点
	var value = '';
	for(var i=0; i<checkedNodes.length; i++){
		value += checkedNodes[i].id + ",";
	}
	value += rid; //将选中的节点的Id和角色Id拼接成字符串，传到后台进行保存
// 	alert(value);
	$("#roleModuleForm").attr("action","sys/updateRoleModule_roleAction.action?value="+value);
	$("#roleModuleForm").submit();
}
</script>

<div class="pageContent">
	<form method="post" id="roleModuleForm" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)" style="height:560px">
		<input type="hidden" id="rid" value="${roleAVO.roleId }"/>
		<table>
			<tr>
				<td width="100%">
				<ul id="moduleTree" class="ztree" style="margin-top: 0; width: 660px; height:500px"></ul>
				</td>
			</tr>
		</table>
		<div class="formBar">
			<ul>
				<li>  
					<div class="buttonActive" >
						<div class="buttonContent">
							<button type="button" id="roleModuleUpdateSave" onclick="saveModuleRole();">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive" >
						<div class="buttonContent">
							<button type="button" id="roleModuleUpdateCancel" onclick="$.pdialog.closeCurrent();">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
