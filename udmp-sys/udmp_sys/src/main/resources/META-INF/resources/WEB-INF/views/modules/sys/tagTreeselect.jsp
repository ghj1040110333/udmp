<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<%@include file="/WEB-INF/views/include/treeview.jsp" %>
<div id="search" class="input-group" style="display:none;">
	<input type="text" id="key" name="key" class="form-control empty" maxlength="50" placeholder="搜索">
	<span class="input-group-btn">
		<button class="btn btn-default" type="button" onclick="searchNode()">
			<span class="glyphicon glyphicon-search"></span>
		</button>
	</span>
</div>
<div id="tree" class="ztree" style="padding:10px 0"></div>
<div class="my-buttons">
	<button id="btn-ok" type="button" class="btn i-btn-ok" data-placement="top" data-trigger="focus">确定</button>
	<c:if test="${allowClear}">
	<button id="btn-clear" type="button" class="btn btn-danger">清除</button>
	</c:if>
	<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
</div>

<script type="text/javascript">
	var key, lastValue = "", nodeList = [], type = getQueryString("type", "${url}");
	var tree;
	var setting = {view:{selectedMulti:false,dblClickExpand:false},check:{enable:"${checked}",nocheckInherit:true},
		async:{enable:(type==3),url:"${ctx}/sys/user/treeData",autoParam:["id=officeId"]},
		data:{simpleData:{enable:true}},callback:{<%--
			beforeClick: function(treeId, treeNode){
				if("${checked}" == "true"){
					//tree.checkNode(treeNode, !node.checked, true, true);
					tree.expandNode(treeNode, true, false, false);
				}
			}, --%>
			onClick:function(event, treeId, treeNode){
				tree.expandNode(treeNode);
			},onCheck: function(e, treeId, treeNode){
				var nodes = tree.getCheckedNodes(true);
				for (var i=0, l=nodes.length; i<l; i++) {
					tree.expandNode(nodes[i], true, false, false);
				}
				return false;
			},onAsyncSuccess: function(event, treeId, treeNode, msg){
				var nodes = tree.getNodesByParam("pId", treeNode.id, null);
				for (var i=0, l=nodes.length; i<l; i++) {
					try{tree.checkNode(nodes[i], treeNode.checked, true);}catch(e){}
					//tree.selectNode(nodes[i], false);
				}
				selectCheckNode();
			},onDblClick: function(){//<c:if test="${!checked}">
				//top.$.jBox.getBox().find("button[value='ok']").trigger("click");
				$("#btn-ok").trigger("click");
				//$("input[type='text']", top.mainFrame.document).focus();//</c:if>
			}
		}
	};
	
	function expandNodes(nodes) {
		if (!nodes) return;
		for (var i=0, l=nodes.length; i<l; i++) {
			tree.expandNode(nodes[i], true, false, false);
			if (nodes[i].isParent && nodes[i].zAsync) {
				expandNodes(nodes[i].children);
			}
		}
	}
	
	// 默认选择节点
	function selectCheckNode(){
		var ids = "${selectIds}".split(",");
		for(var i=0; i<ids.length; i++) {
			var node = tree.getNodeByParam("id", (type==3?"u_":"")+ids[i]);
			if("${checked}" == "true"){
				try{tree.checkNode(node, true, true);}catch(e){}
				tree.selectNode(node, false);
			}else{
				tree.selectNode(node, true);
			}
		}
	}
	
  	function focusKey(e) {
		if (key.hasClass("empty")) {
			key.removeClass("empty");
		}
	}
  	
	function blurKey(e) {
		if (key.val() === "") {
			key.addClass("empty");
		}
		searchNode(e);
	}
	
	//搜索节点
	function searchNode() {
		// 取得输入的关键字的值
		var value = $.trim(key.get(0).value);
		
		// 按名字查询
		var keyType = "name";<%--
		if (key.hasClass("empty")) {
			value = "";
		}--%>
		
		// 如果和上次一次，就退出不查了。
		if (lastValue === value) {
			return;
		}
		
		// 保存最后一次
		lastValue = value;
		
		var nodes = tree.getNodes();
		// 如果要查空字串，就退出不查了。
		if (value == "") {
			showAllNode(nodes);
			return;
		}
		hideAllNode(nodes);
		nodeList = tree.getNodesByParamFuzzy(keyType, value);
		updateNodes(nodeList);
	}
	
	//隐藏所有节点
	function hideAllNode(nodes){			
		nodes = tree.transformToArray(nodes);
		for(var i=nodes.length-1; i>=0; i--) {
			tree.hideNode(nodes[i]);
		}
	}
	
	//显示所有节点
	function showAllNode(nodes){			
		nodes = tree.transformToArray(nodes);
		for(var i=nodes.length-1; i>=0; i--) {
			/* if(!nodes[i].isParent){
				tree.showNode(nodes[i]);
			}else{ */
				if(nodes[i].getParentNode()!=null){
					tree.expandNode(nodes[i],false,false,false,false);
				}else{
					tree.expandNode(nodes[i],true,true,false,false);
				}
				tree.showNode(nodes[i]);
				showAllNode(nodes[i].children);
			/* } */
		}
	}
	
	//更新节点状态
	function updateNodes(nodeList) {
		tree.showNodes(nodeList);
		for(var i=0, l=nodeList.length; i<l; i++) {
			//展开当前节点的父节点
			tree.showNode(nodeList[i].getParentNode()); 
			//tree.expandNode(nodeList[i].getParentNode(), true, false, false);
			//显示展开符合条件节点的父节点
			while(nodeList[i].getParentNode()!=null){
				tree.expandNode(nodeList[i].getParentNode(), true, false, false);
				nodeList[i] = nodeList[i].getParentNode();
				tree.showNode(nodeList[i].getParentNode());
			}
			//显示根节点
			tree.showNode(nodeList[i].getParentNode());
			//展开根节点
			tree.expandNode(nodeList[i].getParentNode(), true, false, false);
		}
	}
	
	//获取所选值
	function getCheckedNodesValue() {
		var $popover = $("#btn-ok").next(".popover");
		if ($popover.length) {
			$("#btn-ok").popover('destroy');
		}
		
		var ids = [], names = [], nodes = [], codes = [];
		//<c:if test="${checked}">
		nodes = tree.getCheckedNodes(true);
		//</c:if>
		//<c:if test="${!checked}">
		nodes = tree.getSelectedNodes();
		//</c:if>
		for(var i=0; i<nodes.length; i++) {
			//<c:if test="${checked && notAllowSelectParent}">
			if (nodes[i].isParent){
				continue; // 如果为复选框选择，则过滤掉父节点
			}
			//</c:if>
			//<c:if test="${notAllowSelectRoot}">
			if (nodes[i].level == 0){
				$("#btn-ok").popover({content:"不能选择根节点（"+nodes[i].name+"）请重新选择。"}).popover('show');
				//top.$.jBox.tip("不能选择根节点（"+nodes[i].name+"）请重新选择。");
				return false;
			}
			//</c:if>
			//<c:if test="${notAllowSelectParent}">
			if (nodes[i].isParent){
				$("#btn-ok").popover({content:"不能选择父节点（"+nodes[i].name+"）请重新选择。"}).popover('show');
				//top.$.jBox.tip("不能选择父节点（"+nodes[i].name+"）请重新选择。");
				return false;
			}
			//</c:if>
			//<c:if test="${not empty module && selectScopeModule}">
			if (nodes[i].module == ""){
				$("#btn-ok").popover({content:"不能选择公共模型（"+nodes[i].name+"）请重新选择。"}).popover('show');
				//top.$.jBox.tip("不能选择公共模型（"+nodes[i].name+"）请重新选择。");
				return false;
			} else if (nodes[i].module != "${module}"){
				$("#btn-ok").popover({content:"不能选择当前栏目以外的栏目模型，请重新选择。"}).popover('show');
				//top.$.jBox.tip("不能选择当前栏目以外的栏目模型，请重新选择。");
				return false;
			}
			//</c:if>
			ids.push(nodes[i].id);
			names.push(nodes[i].name);
			codes.push(nodes[i].code);
			//<c:if test="${!checked}">
			break;	//如果为非复选框选择，则返回第一个选择  
			//</c:if>
		}
		
		top.setModalData("ids", ids.join(",").replace(/u_/ig,""));
		top.setModalData("names", names.join(","));
		top.setModalData("codes", codes.join(","));
	}
	
	$(document).ready(function(){
		key = $("#key");
		key.bind("focus", focusKey).bind("blur", blurKey).bind("change cut input propertychange", searchNode);
		key.bind('keydown', function (e){if(e.which == 13){searchNode();}});
		
		$("#btn-ok").on("click", function() {
			getCheckedNodesValue();
			window.top.hideModal();
		});
		
		//<c:if test="${allowClear}">
		$("#btn-clear").on("click", function() {
			//<c:if test="${checked}">
			tree.checkAllNodes(false);
			//</c:if>
			//<c:if test="${!checked}">
			tree.cancelSelectedNode();
			//</c:if>
		});
		//</c:if>
		
		$.get("${ctx}${url}${fn:indexOf(url,'?')==-1?'?':'&'}&extId=${extId}&isAll=${isAll}&module=${module}&t="
				+ new Date().getTime(), function(zNodes){
			// 初始化树结构
			console.dir($.fn.zTree);
			tree = $.fn.zTree.init($("#tree"), setting, zNodes);
			
			// 默认展开一级节点
			var nodes = tree.getNodesByParam("level", 0);
			for(var i=0; i<nodes.length; i++) {
				tree.expandNode(nodes[i], true, false, false);
			}
			//异步加载子节点（加载用户）
			var nodesOne = tree.getNodesByParam("isParent", true);
			for(var j=0; j<nodesOne.length; j++) {
				tree.reAsyncChildNodes(nodesOne[j],"!refresh",true);
			}
			selectCheckNode();
			$("#search").slideDown(200);
			key.focus();
		});
	});
</script>
