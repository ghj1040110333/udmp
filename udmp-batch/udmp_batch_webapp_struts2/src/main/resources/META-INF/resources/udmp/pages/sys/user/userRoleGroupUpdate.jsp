<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
$(document).ready(function (){init();initRoleGroup();});
var roleGroupTree = "roleGroupTree";
var uid;
var selectedNode;
var zNodesRoleGroup;
var settingRoleGroup = {
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
		onClick : onClickRoleGroupTree
	},
	view:{
		selectedMulti : false,
		showLine : false
	}
};
function init(){
	uid = $("#uid").val();
}
function initRoleGroup(){
	$.ajax({
		type : "post",
		url : '<%=request.getContextPath()%>/sys/getRoleGroupList_userAction.action',
		data : {
			"uid" : uid
		},
		dataType : "json",
		success : function(result) {
			zNodesRoleGroup = result.roleGroupList;
			$.fn.zTree.init($("#"+roleGroupTree), settingRoleGroup, zNodesRoleGroup);
			var zTree = $.fn.zTree.getZTreeObj("roleGroupTree");
			zTree.expandAll(true);
		},
		error : function(result) {
			alert('系统异常');
		}
	});
};

function onClickRoleGroupTree(event, treeId, treeNode, clickFlag) {
	var zTree = $.fn.zTree.getZTreeObj("roleGroupTree");
	zTree.expandNode(treeNode);
};

function saveRole(){
	var treeObj = $.fn.zTree.getZTreeObj("roleGroupTree");
	var checkedNode = treeObj.getCheckedNodes();
	var value = '';
	for(var i=0;i<checkedNode.length;i++){
		value += checkedNode[i].id + ",";
	}
	value += uid;
	//alert(value);
	$("#userGroupForm").attr("action","sys/updateUserAndRoleGroup_userAction.action?value="+value);
	$("#userGroupForm").submit();
}
</script>
<div class="pageContent">
	<form method="post" id="userGroupForm" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)" style="height:560px">
		<input type="hidden" id="uid" value="${userAVo.userId }"/>
		<table>
			<tr>
				<td>
				<ul id="roleGroupTree" class="ztree" style="margin-top: 0; width: 480px; height:513px"></ul>
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
