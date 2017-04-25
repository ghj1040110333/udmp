<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/JavaScript">
/* 页面加载时，初始化数据权限树 */
$(document).ready(function (){init();initPermisstionTree();});
var rid;  /*角色ID*/
var permissionTree = "permissionTree";
var zNodesPermissionRole;
var settingPermissionRole = {
		data : {
			simpleData : {
				enable : true
			}
		},
		check : {
			enable : true,
			chkStyle : "radio",
			radioType: "all"
		},
		callback : {
			onClick : onClickPermissionRoleTree
		},
		view:{
			selectedMulti : false,
			showLine : false
		}
	};
function init(){
	rid = $("#rid").val();
}
/* 初始化数据权限树 */
function initPermisstionTree(){
	$.ajax({
		type : "post",
		url : '<%=request.getContextPath()%>/sys/getRoleDataList_roleAction.action',
		data : {
			"rid" : rid
		},
		dataType : "json",
		success : function(result) {
			zNodesPermissionRole = result.permissionList;
			$.fn.zTree.init($("#"+permissionTree), settingPermissionRole, zNodesPermissionRole);
			var zTree = $.fn.zTree.getZTreeObj("permissionTree");
			zTree.expandAll(true);
		},
		error : function(result) {
			alert('初始化数据权限树异常');
		}
	});
};
function onClickPermissionRoleTree(event, treeId, treeNode, clickFlag) {
	var zTree = $.fn.zTree.getZTreeObj("permissionTree");
	zTree.expandNode(treeNode);
};
/* 保存角色数据权限关系信息 */
function savePermissionRole(){
	var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
	var checkedNodes = treeObj.getCheckedNodes();
	var value = '';
	for(var i=0; i<checkedNodes.length; i++){
		if (checkedNodes[i].pId != null) {
			value += checkedNodes[i].id + ",";
			value += checkedNodes[i].pId + ",";
		}
	}
	value += rid; //将选中的节点的Id(t_permission_info的permission_id),其父表的id
				  //(t_permission_type的permission_type_id)和角色Id拼接成字符串，传到后台进行保存
				  //光选中没有子节点的父节点不会保存数据
 	//alert(value);
	$("#rolePermissionForm").attr("action","sys/updateRolePermission_roleAction.action?value="+value);
	$("#rolePermissionForm").submit();
}
</script>

<div class="pageContent">
	<form method="post" id="rolePermissionForm" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)" style="height:560px">
		<input type="hidden" id="rid" value="${roleAVO.roleId }"/>
		<table>
			<tr>
				<td width="100%">
				<ul id="permissionTree" class="ztree" style="margin-top: 0; width: 660px; height:500px"></ul>
				</td>
			</tr>
		</table>
		<div class="formBar">
			<ul>
				<li>  
					<div class="buttonActive" >
						<div class="buttonContent">
							<button type="button" id="rolePermUpdateSave" onclick="savePermissionRole();">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive" >
						<div class="buttonContent">
							<button type="button" id="rolePermUpdateCancel" onclick="$.pdialog.closeCurrent();">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
