<%@ page language="java" pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/JavaScript">
/* 页面加载时，初始化机构树 */
$(document).ready(function (){init();initOrganTree();});
var rid; /*角色ID*/
var organTree = "organTree";
var zNodesOrganRole;
var settingOrganRole = {
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
			onClick : onClickOrganRoleTree
		},
		view:{
			selectedMulti : false,
			showLine : false
		}
	};
function init(){
	rid = $("#rid").val();
}
/* 初始化机构树 */
function initOrganTree(){
	$.ajax({
		type : "post",
		url : '<%=request.getContextPath()%>/sys/getOrganList_roleAction.action',
		data : {
			"rid" : rid
		},
		dataType : "json",
		success : function(result) {
			zNodesOrganRole = result.OrganList;
			$.fn.zTree.init($("#"+organTree), settingOrganRole, zNodesOrganRole);
			var zTree = $.fn.zTree.getZTreeObj("organTree");
			//zTree.expandAll(true);
			result = null;
		},
		error : function(result) {
			alert('初始化机构树异常');
		}
	});
};
function onClickOrganRoleTree(event, treeId, treeNode, clickFlag) {
	var zTree = $.fn.zTree.getZTreeObj("organTree");
	zTree.expandNode(treeNode);
};
/* 保存角色机构 */
function saveOrganRole(){
	var treeObj = $.fn.zTree.getZTreeObj("organTree");
	var checkedNodes = treeObj.getCheckedNodes(); //选中的节点
	var value = '';
	for(var i=0; i<checkedNodes.length; i++){
		value += checkedNodes[i].id + ",";
	}
	value += rid; //将选中的节点的Id和角色Id拼接成字符串，传到后台进行保存
// 	alert(value);
	$("#roleOrganForm").attr("action","sys/updateRoleOrgan_roleAction.action?value="+value);
	$("#roleOrganForm").submit();
}
</script>

<div class="pageContent">
	<form method="post" id="roleOrganForm" class="pageForm required-validate" onsubmit="return validateCallback(this, dialogAjaxDone)" style="height:560px">
		<input type="hidden" id="rid" value="${roleAVO.roleId }"/>
		<table>
			<tr>
				<td width="100%">
				<ul id="organTree" class="ztree" style="margin-top: 0; width: 660px; height:500px"></ul>
				</td>
			</tr>
		</table>
		<div class="formBar">
			<ul>
				<li>  
					<div class="buttonActive" >
						<div class="buttonContent">
							<button type="button" id="roleOrganUpdateSave" onclick="saveOrganRole();">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive" >
						<div class="buttonContent">
							<button type="button" id="roleOrganUpdateCancel" onclick="$.pdialog.closeCurrent();">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
