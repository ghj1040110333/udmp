/**
 * 自定义封装机构树
 * 
 * @author huoyp_wb
 * @param $
 */
(function($){
	var orgTree = new function(){
		var box_ = navTab.getCurrentPanel();// 为box_赋初值
		var settings = {
				box_:navTab.getCurrentPanel(),//当前机构树所在页面
				left:0,//机构树位置的微调
				top:0,//机构树位置的微调
				width:"200px",//机构树宽度
				height:"300px",//机构树高度
				inputId:null,//机构树所基于的input框的id
				inputAId:null,//机构树所基于的input框的a元素的id
				sinputId:null,//用于显示机构名称的id
				url:"getBranches_branchTreeAction.action"//机构树的action
		};
		// 初始化准备
		this.init = function(options){
			settings = $.extend(settings,options);
			$(settings.box_).append("<div id='orgTreeDiv' class='menuContent' style='display: none; position: absolute; z-index: 5007px;'><ul id='branchTree' class='ztree' style='margin-top: 0; width:"
					+ settings.width + ";height:" + settings.height + "'></ul></div>");
			orgTree.treeInit();
		}
		// 生成tree
		this.generateTree = function(options){
			orgTree.init(options);
		}
		// 初始化tree
		this.treeInit = function(){
			var setting = {
					view : {
						dblClickExpand : false
					},
					data : {
						simpleData : {
							enable : true,
							idKey:"organ_code",
							pIdKey:"uporgan_code"
						},
						key: {
							name:"organ_name"
						}
					},
					callback : {
						onClick : onClick
					}
				};
			function onClick(e, treeId, treeNode) {
				// 通过treeId获取tree对象
				var zTree = $.fn.zTree.getZTreeObj(treeId);
				// 获取选中节点
				nodes = zTree.getSelectedNodes(),
				// 获取第一个选中节点名称
				v = nodes[0].organ_code;
				// 获取input输入框
				var branchObj = $("#" + settings.inputId,settings.box_);
				// 给输入框赋值
				branchObj.attr("value", v);
				// 为后面的input赋值
				$("#" + settings.sinputId,settings.box_).val(nodes[0].organ_name.substring(nodes[0].organ_name.indexOf("-") + 1));
				// 隐藏tree
				hideMenu();
			}
			$("#" + settings.inputAId).click(function(){
				var branchObj = $("#" + settings.inputId,settings.box_);
				var value = $("#" + settings.inputId,settings.box_).val() == null?"":$("#" + settings.inputId,settings.box_).val().trim();
				// 获取tree的相对位置
				var branchPosition = $("#" + settings.inputId,settings.box_).position();
				// 调用ajax 初始化树
			 	$.ajax({type:"POST",url:settings.url,data:{branchLike:value,fact:"0"},
					dataType:"json",success:function(branchNode){
						$.fn.zTree.init($("#branchTree",settings.box_),setting,branchNode);
					}
				}); 
				// 显示树
				$("#orgTreeDiv",settings.box_).css({
					left : branchPosition.left + settings.left + "px",
					top : branchPosition.top + branchObj.outerHeight() + settings.top + "px"
				}).slideDown("fast");
				// 解绑事件
				$(settings.box_).bind("mousedown", onBodyDown);
				return false;
			});
			function hideMenu() {
				$("#orgTreeDiv",settings.box_).fadeOut("fast");
				$(settings.box_).unbind("mousedown", onBodyDown);
			}
			function onBodyDown(event) {
				if(event.target.id == '' ||!(event.target.id == settings.inputAId || event.target.id == 'orgTreeDiv' ||event.target.id == settings.inputId
						||$(event.target).parents("#orgTreeDiv",settings.box_).length > 0)){
					hideMenu();
				}
			}
			$("#" + settings.inputId,settings.box_).change(function(){
				if(!isNulOrEmpty($("#" + settings.inputId,settings.box_).val())){
					$.post(settings.url,{branchLike:$("#" + settings.inputId,settings.box_).val(),fact:"1"},
							function(data){
						if(data && data != "null"){
							var obj = eval("(" + data + ")");
							$.each(obj,function(key,value){
								$.each(value,function(key,value){
									if(key == "organ_name"){
										$("#" + settings.sinputId,settings.box_).val(value.indexOf("-")==-1?value:value.substring(value.indexOf("-") + 1));
									}
								});
							});
						}
						else{
							$("#" + settings.sinputId,settings.box_).val("");
						}
					});
				}else{
					$("#" + settings.sinputId,settings.box_).val("");
				}
			});
			var fval = "";
			$("#" + settings.inputId,settings.box_).keyup(function(event){
				//获取树对象
				var branchObj = $(this);
				//获取input框的值
				var value = $(this).val() == null?"":$(this).val().trim();
				//获取tree的相对位置
				var branchPosition = $(this).position();
				//若值发生变化、不为空、输入框中长度是2的倍数，则触发ajax方法
				if(fval != value && $.trim(value) != ""){
					//调用ajax 初始化树
				 	$.ajax({type:"POST",url:settings.url,data:{branchLike:value,fact:"0"},
						dataType:"json",success:function(branchNode){
							$.fn.zTree.init($("#branchTree",settings.box_),setting,branchNode)
						}
					}); 
					//显示树
				 	$("#orgTreeDiv",settings.box_).css({
						left : branchPosition.left + settings.left + "px",
						top : branchPosition.top + branchObj.outerHeight() + settings.top + "px"
					}).slideDown("fast");
				}
				//若值为空，则重新取出
				else if($.trim(value) == ""){
					//调用ajax 初始化树
					$.ajax({type:"POST",url:settings.url,data:{branchLike:value,fact:"0"},
						dataType:"json",success:function(branchNode){
							$.fn.zTree.init($("#branchTree",settings.box_),setting,branchNode)
						}
					}); 
					//显示树
					$("#orgTreeDiv",settings.box_).css({
						left : branchPosition.left + settings.left + "px",
						top : branchPosition.top + branchObj.outerHeight() + settings.top + "px"
					}).slideDown("fast");
				}
				$(settings.box_).bind("mousedown", onBodyDown);
				fval = value;
			});
		}
	};
	$.orgTree = orgTree.generateTree;
	
})(jQuery)
