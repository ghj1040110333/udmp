(function($){
	var onClick ;
	$.extend($.fn, {
		checkorgtreegrn:function(parentBox){
			return this.each(function(i){
				var $this = $(this);
				var url = null;
				// 1表示要显示机构代码  0表示不显示机构代码
				var showCode = $this.attr("showCode") || 0;
				var randomM = Math.round(Math.random()*10000000);
				var orgTreeDivId = 'orgTreeDiv_' + $this.attr('id') || randomM　; 
				var orgOuterDivId = 'OuterDivId_' + $this.attr('id') || randomM;
				var orgTreeDivWidth = $this.attr('treeWidth') ||　300;
				var orgTreeDivHeight = $this.attr('treeHeight') || 350;
				var orgTreeId = 'organTree_ul_' + $this.attr('id') ||　randomM;
				var clickId = $this.attr('clickId');
				var showInputId = $this.attr('showOrgName');
				if($this.attr("needAll") == "true"){
					url = "queryAllOrganTree_organTreeAction.action?showCode=" + showCode;
				}else{
					url = "queryOrganTreeByUser_organTreeAction.action?showCode=" + showCode;
				}
				$('body').append("<div id=" + orgOuterDivId+ " style='position: absolute; z-index: 5007; border: 1px solid rgb(216, 206, 206);display: none;'>" +
						"<h1 style='margin-top:7px;margin-left:4px;font-size:11pt'>管理结构列表</h1><div id='" + orgTreeDivId + "' class='menuContent' style='margin-left:5px;'><ul id='"+orgTreeId + "' class='ztree' style='margin-left:20px;margin-top:5px;margin-right:20px;"
										+ orgTreeDivWidth + "px;height:" + orgTreeDivHeight + "px'></ul></div><div style='margin-top:7px;margin-bottom:3px;'><button style='margin-left:100px;' onClick='chooseOrgan(\"" + orgTreeId + "\",\"" +$(this).attr("id") + "\",\"" + $(this).attr("showOrgName") + "\")' type='button'>确定</button><button onClick='' type='button'>返回</button></div></div>");
				var setting = {
					check:{
						enable:true,
//							chkboxType:{"Y":"","N":""}
					},
					async : {
						enable : true,
						url : url,
						autoParam : [ "organCode=uporganCode" ],
						dataFilter : filter
					},
					data : {
						simpleData : {
							enable : true,
							idKey : "organCode",
							pIdKey : "uporganCode",
						},
						key : {
							name : "organName"
						}
					},
					callback : {
						onClick : onClick
					}
				};
				function filter(treeId, parentNode, childNodes) {
					if (!childNodes)
						return null;
					return childNodes;
				};
				$("#" + clickId,parentBox).click(function(event) {
					var value = $("#" + $this.attr('id'),parentBox).val() == null ? "" : $("#" + $this.attr('id'), parentBox).val().trim();
					if(value){
						setting.async.url = url + "?organCodeQuery=" + value;
					}
					$.fn.zTree.init($("#" + orgTreeId), setting);
					setting.async.url = url;
					// 获取tree的相对位置
					var branchPosition = $("#" + $this.attr("id"),parentBox).offset();
					$("#" + orgOuterDivId).css({
						left : branchPosition.left + "px",
						top : branchPosition.top + $this.outerHeight() +  "px"
					}).show();
					// 解绑事件
					$("body").bind("mousedown", onBodyDown);
					return false;
				});
				function onBodyDown(event) {
					if (event.target.id == '' || 
							!(event.target.id == clickId
									|| event.target.id == orgTreeDivId
									|| event.target.id == $this.attr('id') || $(
									event.target).parents("#" + orgTreeDivId,
											parentBox).length > 0)) {
						hideOrganTreeMenu($this.attr('id'));
					}
				}
			
				if(showInputId){
					$this.change(function(){
						$('#' + showInputId).val('');
					});
				}
				function hideOrganTreeMenu(inputId) {
					$("#OuterDivId_" + inputId).fadeOut("fast");
				}
				
			});
		}	
	});	
})(jQuery);
function chooseOrgan(organId,inputId,showInputId){
	// 获取树对象
	var zTree = $.fn.zTree.getZTreeObj(organId);
	// 获取选中节点集合
	var checkedNodes = zTree.getCheckedNodes(true);
	// 创建数组 存放所有非半选中的节点
	var nohalfCheck = new Array();
	var organCodes = '';
	var organNames = '';
	// 遍历获取所有非半选中节点
	for(var i = 0 ; i < checkedNodes.length; i++){
		if(!checkedNodes[i].getCheckStatus().half == true){
			nohalfCheck.push(checkedNodes[i]);
		}
	}
	// 按机构编码弟选中节点进行排序
	nohalfCheck = nohalfCheck.sort(function(a,b){
		return a.organCode - b.organCode;
	});
	
	var kk = new Array();
	while(nohalfCheck.length > 0){
		var checkNode = new Array();
//		 获取数组的第一个元素 ，并放入要显示的数组中
		var firstEle = nohalfCheck.shift();
		// 对象保存
		kk.push(firstEle);
		for(var i = 0 ; i < nohalfCheck.length ; i++){
			var innerEle = nohalfCheck[i];
			// 若是该首节点的子类，则将其从数组中移除
			if(!innerEle.organCode.indexOf(firstEle.organCode) == 0){
				// 否则将其保存到原数组中
				checkNode.push(innerEle);
			}
		}
		nohalfCheck = checkNode;
	}
	// 拼接字符串
	for(var i = 0 ; i < kk.length; i++){
		organCodes += kk[i].organCode + ",";
		organNames += kk[i].organName + ",";
	}
	// 去除最后一个逗号
	organCodes = organCodes.substr(0,organCodes.length -1);
	organNames = organNames.substr(0,organNames.length -1);
	// 赋值
	$("#" + inputId).val(organCodes);
	$("#" + showInputId).val(organNames);
}