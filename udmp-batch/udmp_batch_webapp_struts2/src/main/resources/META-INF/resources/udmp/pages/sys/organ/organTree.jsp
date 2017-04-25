<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	var setting = {
		async: {
				enable: true,
				url:"queryOrganTree_organTreeAction.action",
				autoParam:["organCode=uporganCode"],
				dataFilter: filter
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "organCode",
					pIdKey: "uporganCode",
				},
				key: {
					name:"organName"
				}
			} 
		};

	function filter(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for (var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].organName = childNodes[i].organName.replace(/\.n/g, '.');
		}
		return childNodes;
	}
	function beforeClick(treeId, treeNode) {
		if (!treeNode.isParent) {
			alert("请选择父节点");
			return false;
		} else {
			return true;
		}
	}
	$(document).ready(function() {
		$.fn.zTree.init($("#treeDemo"), setting);
	});
</script>
<div class="pageContent">
	<div class="content_wrap">
		<div class="zTreeDemoBackground left">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
</div>