/**
 * 自定义封装机构树
 * 
 * @author huoyp_wb
 * @param $
 * @date  2014年9月24日16:28:50
 */
(function($) {
	var asyncOrgTree = new function() {
		var settings = {
			box_ : navTab.getCurrentPanel(),// 当前机构树所在页面
			left : 0,// 机构树位置的微调
			top : 0,// 机构树位置的微调
			width : "200px",// 机构树宽度
			height : "300px",// 机构树高度
			inputId : null,// 机构树所基于的input框的id
			inputAId : null,// 机构树所基于的input框的a元素的id
			sinputId : null,// 用于显示机构名称的id
			url : "queryOrganTree_organTreeAction.action"// 机构树的action
		};
		// 初始化准备
		init = function(options) {
			settings = $.extend(settings, options);
			$(settings.box_)
					.append(
							"<div id='orgTreeDiv' class='menuContent' style='display: none; position: absolute; z-index: 5007px;'><ul id='organTree_ul' class='ztree' style='margin-top: 0; width:"
									+ settings.width
									+ ";height:"
									+ settings.height + "'></ul></div>");
			asyncOrgTree.treeInit();
		}
		// 生成tree
		this.generateTree = function(options) {
			init.apply(asyncOrgTree,[options]);
		}
		// 初始化tree
		this.treeInit = function() {
			var setting = {
				async : {
					enable : true,
					url : settings.url,
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
			//过滤
			function filter(treeId, parentNode, childNodes) {
				if (!childNodes)
					return null;
				for (var i = 0, l = childNodes.length; i < l; i++) {
					childNodes[i].organName = childNodes[i].organName.replace(
							/\.n/g, '.');
				}
				return childNodes;
			}
			//树节点的点击事件
			function onClick(e, treeId, treeNode) {
				// 通过treeId获取tree对象
				var zTree = $.fn.zTree.getZTreeObj(treeId);
				// 获取选中节点
				nodes = zTree.getSelectedNodes(),
				// 获取第一个选中节点名称
				v = nodes[0].organCode;
				// 获取input输入框
				var branchObj = $("#" + settings.inputId, settings.box_);
				// 给输入框赋值
				branchObj.attr("value", v);
				// 为后面的input赋值
				$("#" + settings.sinputId, settings.box_).val(
						nodes[0].organName.substring(nodes[0].organName.lastIndexOf("-") + 1));
				// 隐藏tree
				hideMenu();
			}
			$("#" + settings.inputAId).click(function() {
				var branchObj = $("#" + settings.inputId,
						settings.box_);
				var value = $("#" + settings.inputId,
						settings.box_).val() == null ? "" : $(
						"#" + settings.inputId, settings.box_)
						.val().trim();
				// 获取tree的相对位置
				var branchPosition = $("#" + settings.inputId,
						settings.box_).position();
				// 调用ajax 初始化树
				/*$.ajax({
					type : "POST",
					url : settings.url,
					async:false,
					data : {
						organCodeQuery : value,
					},
					dataType : "json",
					success : function(data) {
						//
					}
				});*/
				if(value){
					setting.async.url = settings.url + "?organCodeQuery=" + value;
				}
				$.fn.zTree.init($("#organTree_ul"), setting);
				setting.async.url = settings.url;
				// 显示树
				$("#orgTreeDiv", settings.box_).css(
						{
							left : branchPosition.left
									+ settings.left + "px",
							top : branchPosition.top
									+ branchObj.outerHeight()
									+ settings.top + "px"
						}).slideDown("fast");
				// 解绑事件
				$(settings.box_).bind("mousedown", onBodyDown);
				return false;
			});
			//隐藏div
			function hideMenu() {
				$("#orgTreeDiv", settings.box_).fadeOut("fast");
				$(settings.box_).unbind("mousedown", onBodyDown);
			}
			//监听鼠标点击的元素id，若不是tree，则隐藏tree的Div
			function onBodyDown(event) {
				if (event.target.id == ''
						|| !(event.target.id == settings.inputAId
								|| event.target.id == 'orgTreeDiv'
								|| event.target.id == settings.inputId || $(
								event.target).parents("#orgTreeDiv",
								settings.box_).length > 0)) {
					hideMenu();
				}
			}
	}};
	$.asyncOrgTree = asyncOrgTree.generateTree;
})(jQuery)
