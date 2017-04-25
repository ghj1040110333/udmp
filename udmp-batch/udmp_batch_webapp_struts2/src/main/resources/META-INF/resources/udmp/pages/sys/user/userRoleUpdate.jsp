<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
$(document).ready(function (){init();initMenuRole();initDataRole();});
var menuRoleTree = "menuRoleTree";
var dataRoleTree = "dataRoleTree";
var uid;
var selectedNode;
var zNodesMenuRole;
var settingMenuRole = {
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
		onClick : onClickMenuRoleTree
	},
	view:{
		selectedMulti : false,
		showLine : false
	}
};
var zNodesDataRole;
var settingDataRole = {
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
		onClick : onClickDataRoleTree
	},
	view:{
		selectedMulti: false,
		showLine : false
	}
};
function init(){
	uid = $("#uid").val();
}
function initMenuRole(){
	$.ajax({
		type : "post",
		url : '<%=request.getContextPath()%>/sys/getMenuRoleList_userAction.action',
		data : {
			"uid" : uid
		},
		dataType : "json",
		success : function(result) {
			zNodesMenuRole = result.menuRoleList;
			$.fn.zTree.init($("#"+menuRoleTree), settingMenuRole, zNodesMenuRole);
			var zTree = $.fn.zTree.getZTreeObj("menuRoleTree");
			zTree.expandAll(true);
		},
		error : function(result) {
			alert('系统异常');
		}
	});
};
function initDataRole(){
	$.ajax({
		type : "post",
		url : '<%=request.getContextPath()%>/sys/getDataRoleList_userAction.action',
		data : {
			"uid" : uid
		},
		dataType : "json",
		success : function(result) {
			zNodesDataRole = result.dataRoleList;
			$.fn.zTree.init($("#"+dataRoleTree), settingDataRole, zNodesDataRole);
			var zTree = $.fn.zTree.getZTreeObj("dataRoleTree");
			zTree.expandAll(true);
		},
		error : function(result) {
			alert('系统异常');
		}
	});
};
function onClickMenuRoleTree(event, treeId, treeNode, clickFlag) {
	var zTree = $.fn.zTree.getZTreeObj("menuRoleTree");
	zTree.expandNode(treeNode);
};
function onClickDataRoleTree(event, treeId, treeNode, clickFlag) {
	var zTree = $.fn.zTree.getZTreeObj("dataRoleTree");
	zTree.expandNode(treeNode);
};
function saveRole(){
	var treeObj = $.fn.zTree.getZTreeObj("menuRoleTree");
	var checkedNode = treeObj.getCheckedNodes();
	var value = '';
	for(var i=0;i<checkedNode.length;i++){
		if (checkedNode[i].pId != null) {
			value += checkedNode[i].id + ",";
		}
	}
	var treeObj1 = $.fn.zTree.getZTreeObj("dataRoleTree");
	var checkedNode1 = treeObj1.getCheckedNodes();
	for(var i=0;i<checkedNode1.length;i++){
		if (checkedNode1[i].pId != null) {
			value += checkedNode1[i].id + ",";
		}
	}
	value += uid;
	$("#userForm").attr("action","sys/updateUserRole_userAction.action?value="+value);
	$("#userForm").submit();
}
</script>
<div class="pageContent">
	<form method="post" id="userForm" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)" style="height:560px">
		<input type="hidden" id="uid" value="${userAVo.userId }"/>
		<table>
			<tr>
				<td width="50%">
				<ul id="menuRoleTree" class="ztree" style="margin-top: 0; width: 480px; height:513px"></ul>
				</td>
				<td width="50%">
				<ul id="dataRoleTree" class="ztree" style="margin-top: 0; width: 480px; height:513px"></ul>
				</td>
			</tr>
		</table>
		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive" >
						<div class="buttonContent">
							<button type="button" onclick="saveRole();">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive" >
						<div class="buttonContent">
							<button type="button" onclick="$.pdialog.closeCurrent();">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
