<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<form:form id="inputForm" modelAttribute="role" method="post" class="form-horizontal">
	<form:hidden path="id" />
	<div class="form-group">
		<label class="col-sm-2 control-label">归属机构:</label>
		<div class="col-sm-5">
			<sys:treeselect335 id="office" name="office.id" value="${role.office.id}" labelName="office.name"
				labelValue="${role.office.name}" title="机构" url="/sys/office/treeData" cssClass="form-control"
				dataMsgRequired="请选择归属公司" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">
			<span class="red">*</span>角色名称:
		</label>
		<div class="col-sm-5">
			<input id="oldName" name="oldName" type="hidden" value="${role.name}">
			<form:input path="name" htmlEscape="false" maxlength="50" class="form-control" data-bv-notempty="true"
				data-bv-trigger="blur" data-bv-notempty-message="请输入角色名称" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">
			<span class="red">*</span>英文名称:
		</label>
		<div class="col-sm-5">
			<input id="oldEnname" name="oldEnname" type="hidden" value="${role.enname}">
			<form:input path="enname" htmlEscape="false" maxlength="50" class="form-control" data-bv-notempty="true"
				data-bv-trigger="blur" data-bv-notempty-message="请输入角色名称" />
		</div>
		<div class="col-sm-5">
			<p class="form-control-static">工作流用户组标识</p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">角色类型:</label>
		<div class="col-sm-2">
			<form:select path="roleType" class="form-control">
				<form:option value="assignment">任务分配</form:option>
				<form:option value="security-role">管理角色</form:option>
				<form:option value="user">普通角色</form:option>
			</form:select>
		</div>
		<div class="col-sm-8">
			<p class="form-control-static">工作流组用户组类型（任务分配：assignment、管理角色：security-role、普通角色：user）</p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">是否系统数据:</label>
		<div class="col-sm-2">
			<form:select path="sysData" class="form-control">
				<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
			</form:select>
		</div>
		<div class="col-sm-8">
			<p class="form-control-static">“是”表示此数据只有超级管理员能进行修改，“否”表示拥有角色修改人员的权限都能进行修改</p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">是否可用</label>
		<div class="col-sm-2">
			<form:select path="useable" class="form-control">
				<form:options items="${fns:getDictList('yes_no')}" itemLabel="label" itemValue="value" htmlEscape="false" />
			</form:select>
		</div>
		<div class="col-sm-8">
			<p class="form-control-static">“是”表示此数据可用，“否”表示此数据不可用</p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">数据范围:</label>
		<div class="col-sm-2">
			<form:select path="dataScope" class="form-control">
				<form:options items="${fns:getDictList('sys_data_scope')}" itemLabel="label" itemValue="value" htmlEscape="false" />
			</form:select>
		</div>
		<div class="col-sm-8">
			<p class="form-control-static">特殊情况下，设置为“按明细设置”，可进行跨机构授权</p>
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">角色授权:</label>
		<div class="col-sm-10">
			<div id="menuTree" class="ztree" style="margin-top: 3px; float: left;"></div>
			<form:hidden path="menuIds" />
			<div id="officeTree" class="ztree" style="margin-left: 100px; margin-top: 3px; float: left;"></div>
			<form:hidden path="officeIds" />
		</div>
	</div>
	<div class="form-group">
		<label class="col-sm-2 control-label">备注:</label>
		<div class="col-sm-5">
			<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="form-control" />
		</div>
	</div>
	<div class="my-buttons">
		<shiro:hasPermission name="sys:user:edit">
			<button id="btnSubmit" class="btn i-btn-ok" type="button">保 存</button>
		</shiro:hasPermission>
		<button id="btnCancel" class="btn btn-default" type="button" data-dismiss="modal">返回</button>
	</div>
</form:form>
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
<script src="${ctxUdmp}/js/git-common.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$("#btnSubmit").on("click", function() {
			var ids = [], nodes = tree.getCheckedNodes(true);
			for(var i=0; i<nodes.length; i++) {
				ids.push(nodes[i].id);
			}
			$("#menuIds").val(ids);
			var ids2 = [], nodes2 = tree2.getCheckedNodes(true);
			for(var i=0; i<nodes2.length; i++) {
				ids2.push(nodes2[i].id);
			}
			$("#officeIds").val(ids2);
			$.post("${ctx}/sys/role/save", git.serializeObject($("#inputForm")), function(data) {
				if(data) {
					if(data.success) {
						window.showInfo("success", data.message);
						window.hideModal();
					} else {
						window.showInfo("error", data.message);
					}
				}
			});
		});
		
		var setting = {check:{enable:true,nocheckInherit:true},view:{selectedMulti:false},
				data:{simpleData:{enable:true}},callback:{beforeClick:function(id, node){
					tree.checkNode(node, !node.checked, true, true);
					return false;
				}}};
		
		// 用户-菜单
		var zNodes=[
				<c:forEach items="${menuList}" var="menu">{id:"${menu.id}", pId:"${not empty menu.parent.id?menu.parent.id:0}", name:"${not empty menu.parent.id?menu.name:'权限列表'}"},
	            </c:forEach>];
		// 初始化树结构
		var tree = $.fn.zTree.init($("#menuTree"), setting, zNodes);
		// 不选择父节点
		tree.setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
		// 默认选择节点
		var ids = "${role.menuIds}".split(",");
		for(var i=0; i<ids.length; i++) {
			var node = tree.getNodeByParam("id", ids[i]);
			try{tree.checkNode(node, true, false);}catch(e){}
		}
		// 默认展开全部节点
		tree.expandAll(true);
		
		// 用户-机构
		var zNodes2=[
				<c:forEach items="${officeList}" var="office">{id:"${office.id}", pId:"${not empty office.parent?office.parent.id:0}", name:"${office.name}"},
	            </c:forEach>];
		// 初始化树结构
		var tree2 = $.fn.zTree.init($("#officeTree"), setting, zNodes2);
		// 不选择父节点
		tree2.setting.check.chkboxType = { "Y" : "ps", "N" : "s" };
		// 默认选择节点
		var ids2 = "${role.officeIds}".split(",");
		for(var i=0; i<ids2.length; i++) {
			var node = tree2.getNodeByParam("id", ids2[i]);
			try{tree2.checkNode(node, true, false);}catch(e){}
		}
		// 默认展开全部节点
		tree2.expandAll(true);
		// 刷新（显示/隐藏）机构
		refreshOfficeTree();
		$("#dataScope").change(function(){
			refreshOfficeTree();
		});
	});
	function refreshOfficeTree(){
		if($("#dataScope").val()==9){
			$("#officeTree").show();
		}else{
			$("#officeTree").hide();
		}
	}
</script>