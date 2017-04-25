/**
 * 自定义封装机构树
 * @author huoyp_wb
 * @param $
 * @date  2014年10月30日 16:35:04	创建 将原机构树插件的对象创建模式更改为拓展jquery方法模式 通过input的class属性去调用该方法
 * @data  2014年11月02日 14:15:30 支持开放onclick事件 并将原通过user-role-organ关系确定机构数据改为user-organId进行查询
 */
(function($){
	var onClick ;
	$.extend($.fn, {
		orgtreegrn:function(parentBox){
			return this.each(function(i){
				var $this = $(this);
				var url = null;
				if($this.attr("needAll") == "true"){
					url = "queryAllOrganTree_organTreeAction.action";
				}else{
					url = "queryOrganTreeByUser_organTreeAction.action";
				}
				var randomM = Math.round(Math.random()*10000000);
				var orgTreeDivId = 'orgTreeDiv_' + $this.attr('id') || randomM　; 
				var orgTreeDivWidth = $this.attr('treeWidth') ||　200;
				var orgTreeDivHeight = $this.attr('treeHeight') || 300;
				var orgTreeId = 'organTree_ul_' + $this.attr('id') ||　randomM;
				var clickId = $this.attr('clickId');
				var showInputId = $this.attr('showOrgName');
				$('body').append("<div id='" + orgTreeDivId + "' class='menuContent' style='display: none; position: absolute; z-index: 5007;'><ul id='"+orgTreeId + "' class='ztree' style='width:"
										+ orgTreeDivWidth + "px;height:" + orgTreeDivHeight + "px'></ul></div>");
				if($.isFunction($.fn.myClick)){
					onClick = $.fn.myClick;
				}else{
					onClick = function(e, treeId, treeNode){
						// 通过treeId获取tree对象
						var zTree = $.fn.zTree.getZTreeObj(treeId);
						// 获取选中节点
						nodes = zTree.getSelectedNodes();
						// 获取input输入框
						var branchObj = $("#" + $this.attr('id'), parentBox);
						// 给输入框赋值
						branchObj.val(nodes[0].organCode);
						// 为后面的input赋值
						if(showInputId){
							$("#" + showInputId, parentBox).val(
									nodes[0].organName.substring(nodes[0].organName.lastIndexOf("-") + 1));
						}
						// 隐藏tree
						hideOrganTreeMenu($this.attr('id'));
					}
				}
				var setting = {
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
					$("#" + orgTreeDivId).css({
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
				
			});
		}	
	});	
})(jQuery);
function hideOrganTreeMenu(inputId) {
	$("#orgTreeDiv_" + inputId).fadeOut("fast");
}
