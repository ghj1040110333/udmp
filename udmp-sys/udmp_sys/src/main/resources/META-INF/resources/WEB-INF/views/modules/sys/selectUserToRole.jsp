<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
<div class="container-fluid">
	<div id="assignRole" class="row-fluid">
		<div class="col-sm-4" style="border-right: 1px solid #A8A8A8;">
			<p>所在部门：</p>
			<div id="officeTree" class="ztree"></div>
		</div>
		<div class="col-sm-4">
			<p>待选人员：</p>
			<div id="userTree" class="ztree"></div>
		</div>
		<div class="col-sm-4" style="padding-left: 16px; border-left: 1px solid #A8A8A8;">
			<p>已选人员：</p>
			<div id="selectedTree" class="ztree"></div>
		</div>
	</div>
</div>
<div class="my-buttons">
	<button id="btn-ok" type="button" class="btn i-btn-ok" data-placement="top" data-trigger="focus" onclick="assign();">确定分配</button>
	<button id="btn-clear" type="button" class="btn btn-danger" onclick="clearAssign();">清除已选</button>
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>
<script type="text/javascript">
	var officeTree;
	var selectedTree;//zTree已选择对象
	
	var setting = {
			view : {selectedMulti : false, nameIsHTML : true, showTitle : false, dblClickExpand : false},
			data : {simpleData : {enable : true}},
			callback : {onClick : treeOnClick}};
	var officeNodes = [
   	    <c:forEach items="${officeList}" var="office">
   	     {
   	     	id : "${office.id}",
   	      	pId : "${not empty office.parent?office.parent.id:0}", 
   	      	name : "${office.name}"
   	     },
   	    </c:forEach>];

   	var pre_selectedNodes = [
     		<c:forEach items="${userList}" var="user">
     			{
     				id : "${user.id}",
     		        pId : "0",
     		        name : "<font color='red' style='font-weight:bold;'>${user.name}</font>"
     		    },
     		</c:forEach>];
   	
   	var selectedNodes = [
   		<c:forEach items="${userList}" var="user">
   	    	{
   	    		id : "${user.id}",
   	         	pId : "0",
   	         	name : "<font color='red' style='font-weight:bold;'>${user.name}</font>"
   	        },
   		</c:forEach>];
	// 初始化
	$(document).ready(function() {
		officeTree = $.fn.zTree.init($("#officeTree"), setting, officeNodes);
		selectedTree = $.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
	});

	var pre_ids = "${selectIds}".split(",");
	var ids = [];
	
	//点击选择项回调
	function treeOnClick(event, treeId, treeNode, clickFlag) {
		$.fn.zTree.getZTreeObj(treeId).expandNode(treeNode);
		if("officeTree" == treeId) {
			$.get("${ctx}/sys/role/users?officeId=" + treeNode.id, function(userNodes) {
				$.fn.zTree.init($("#userTree"), setting, userNodes);
			});
		}
		if("userTree" == treeId) {
			if($.inArray(String(treeNode.id), ids) < 0) {
				selectedTree.addNodes(null, treeNode);
				ids.push(String(treeNode.id));
			}
		};
		if("selectedTree" == treeId) {
			if($.inArray(String(treeNode.id), pre_ids) < 0) {
				selectedTree.removeNode(treeNode);
				ids.splice($.inArray(String(treeNode.id), ids), 1);
			} else {
				top.window.showInfo("success", "角色原有成员不能清除！");
			}
		}
	}
	// 分配角色
	function assign() {
		// 删除''的元素
		if(ids[0] == ''){
			ids.shift();
			pre_ids.shift();
		}
		if(pre_ids.sort().toString() == ids.sort().toString()){
			top.window.showInfo("success", "未给角色【${role.name}】分配新成员！");
			return;
		};
    	// 执行保存
    	var idsArr = "";
    	for (var i = 0; i < ids.length; i++) {
    		idsArr = (idsArr + ids[i]) + (((i + 1)== ids.length) ? '':',');
    	}
    	$.post("${ctx}/sys/role/assignrole?id=${role.id}&idsArr=" + idsArr, function(data){
    		if(data) {
    			if(data.success) {
					top.window.showInfo("success", data.message);
					window.top.hideModal();
				} else {
					top.window.showInfo("error", data.message);
				}
    		}
    	})
	}
	// 清除分配
	function clearAssign() {
		var submit = function () {
			var tips = "";
			if(pre_ids.sort().toString() == ids.sort().toString()) {
				tips = "未给角色【${role.name}】分配新成员！";
			} else {
				tips = "已选人员清除成功！";
			}
			ids = [];
			selectedNodes = pre_selectedNodes;
			$.fn.zTree.init($("#selectedTree"), setting, selectedNodes);
			top.window.showInfo("success", tips);
		};
		tips = "确定清除角色【${role.name}】下的已选人员？";
		top.window.showConfirm(tips, submit);
	}
</script>