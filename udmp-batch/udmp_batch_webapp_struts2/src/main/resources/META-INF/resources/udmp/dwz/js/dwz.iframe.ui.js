/**
 * 2014/09/19 增加struts2 对 dwz combox组件的回显功能 使用说明见UI文档 新增全局变量
 * dwz_combox_myarray/dwz_combox_mybox/dwz_combox_last_value
 */
// combox组件回显用的全局变量 用来存放combox数组 BY HYP
var dwz_combox_myarray;
// combox组件回显用的全局变量 用来存放combox所在的页面对象 BY HYP
var dwz_combox_mybox;

// *******交易管理开关  Add HYP
var dealSwitch = false;
// *******交易管理用的记录button 属性标志的全局变量 Add HYP
var clickBtnMark;
// *******记录上一次点击的按钮ID  Add HYP
var prevBtnMark;
// *******用于刷新后 记录按钮提示ID  Add HYP
var remindBtnId = null;
// *******按钮亮色提示功能是否开启  Add HYP
var lightColorSwitch = false;


function initEnv() {
	$("body").append(DWZ.frag["dwzFrag"]);

	if ($.browser.msie && /6.0/.test(navigator.userAgent)) {
		try {
			document.execCommand("BackgroundImageCache", false, true);
		} catch (e) {
		}
	}
	// 清理浏览器内存,只对IE起效
	if ($.browser.msie) {
		window.setInterval("CollectGarbage();", 10000);
	}

	$(window).resize(function() {
		initLayout();
		$(this).trigger(DWZ.eventType.resizeGrid);
	});

	var ajaxbg = $("#background,#progressBar");
	ajaxbg.hide();
	$(document).ajaxStart(function() {
		ajaxbg.show();
	}).ajaxStop(function() {
		ajaxbg.hide();
	});

	$("#leftside").jBar({
		minW : 150,
		maxW : 700
	});

	if ($.taskBar)
		$.taskBar.init();
	navTab.init();
	if ($.fn.switchEnv)
		$("#switchEnvBox").switchEnv();
	if ($.fn.navMenu)
		$("#navMenu").navMenu();

	setTimeout(function() {
		initLayout();
		initUI();
		// navTab styles
		var jTabsPH = $("div.tabsPageHeader");
		jTabsPH.find(".tabsLeft").hoverClass("tabsLeftHover");
		jTabsPH.find(".tabsRight").hoverClass("tabsRightHover");
		jTabsPH.find(".tabsMore").hoverClass("tabsMoreHover");
	}, 10);
}
function initLayout(){
	var iContentW = $(window).width() - (DWZ.ui.sbar ? $("#sidebar").width() + 10 : 10) - 5 + 25;
	var iContentH = $(window).height() - $("#header").height() - 28 + 62;
	$("#container").width(iContentW);
	$("#container .tabsPageContent").height(iContentH - 34).find("[layoutH]").layoutH();
	$("#sidebar, #sidebar_s .collapse, #splitBar, #splitBarProxy").height(iContentH - 5);
	$("#taskbar").css({top: iContentH + $("#header").height() + 5, width:$(window).width()});
}

function initUI(_box) {
	var $p = $(_box || document);
	$("input[type^=expand]", $p).intputValidExpand();

	$("div.panel", $p).jPanel();

	// tables
	$("table.table", $p).jTable();

	// css tables
	$('table.list', $p).cssTable();

	// auto bind tabs
	$("div.tabs", $p).each(function() {
		var $this = $(this);
		var options = {};
		options.currentIndex = $this.attr("currentIndex") || 0;
		options.eventType = $this.attr("eventType") || "click";
		$this.tabs(options);
	});

	$("ul.tree", $p).jTree();
	$('div.accordion', $p).each(function() {
		var $this = $(this);
		$this.accordion({
			fillSpace : $this.attr("fillSpace"),
			alwaysOpen : true,
			active : 0
		});
	});

	$(":button.checkboxCtrl, :checkbox.checkboxCtrl", $p).checkboxCtrl($p);

	if ($.fn.combox) {
		$("select.combox", $p).combox();
	}

	// ******* 新增struts2 回显的监听 Add HYP
	if (dwz_combox_myarray && dwz_combox_mybox) {
		hxMyCombox(dwz_combox_myarray, dwz_combox_mybox);
	}
	// 使用后 将全局变量置空 让内存回收机制回收
	dwz_combox_myarray = null;
	dwz_combox_mybox = null;
	
	if ($.fn.xheditor) {
		$("textarea.editor", $p).each(
				function() {
					var $this = $(this);
					var op = {
						html5Upload : false,
						skin : 'vista',
						tools : $this.attr("tools") || 'full'
					};
					var upAttrs = [
							[ "upLinkUrl", "upLinkExt", "zip,rar,txt" ],
							[ "upImgUrl", "upImgExt", "jpg,jpeg,gif,png" ],
							[ "upFlashUrl", "upFlashExt", "swf" ],
							[ "upMediaUrl", "upMediaExt", "avi" ] ];

					$(upAttrs).each(function(i) {
						var urlAttr = upAttrs[i][0];
						var extAttr = upAttrs[i][1];

						if ($this.attr(urlAttr)) {
							op[urlAttr] = $this.attr(urlAttr);
							op[extAttr] = $this.attr(extAttr) || upAttrs[i][2];
						}
					});

					$this.xheditor(op);
				});
	}

	if ($.fn.uploadify) {
		$(":file[uploaderOption]", $p).each(function() {
			var $this = $(this);
			var options = {
				fileObjName : $this.attr("name") || "file",
				auto : true,
				multi : true,
				onUploadError : uploadifyError
			};

			var uploaderOption = DWZ.jsonEval($this.attr("uploaderOption"));
			$.extend(options, uploaderOption);

			DWZ.debug("uploaderOption: " + DWZ.obj2str(uploaderOption));

			$this.uploadify(options);
		});
	}

	// init styles
	$("input[type=text], input[type=password], textarea", $p).addClass(
			"textInput").focusClass("focus");

	$("input[readonly], textarea[readonly]", $p).addClass("readonly");
	$("input[disabled=true], textarea[disabled=true]", $p).addClass("disabled");

	$("input[type=text]", $p).not("div.tabs input[type=text]", $p).filter(
			"[alt]").inputAlert();

	// Grid ToolBar
	$("div.panelBar li, div.panelBar", $p).hoverClass("hover");

	// Button
	$("div.button", $p).hoverClass("buttonHover");
	$("div.buttonActive", $p).hoverClass("buttonActiveHover");

	// tabsPageHeader
	$("div.tabsHeader li, div.tabsPageHeader li, div.accordionHeader, div.accordion",
			$p).hoverClass("hover");
	
	// *******增加错误提示鼠标划过事件 Add HYP
	$(".textInput").powerFloat({
		targetMode : "remind",
		targetAttr : "placeholder",
		position : "1-4"
	});
	
	// validate form
	$("form.required-validate", $p).each(function() {
		var $form = $(this);
		$form.validate({
			onsubmit : false,
			focusInvalid : false,
			focusCleanup : true,
			errorElement : "span",
			ignore : ".ignore",
			invalidHandler : function(form, validator) {
				var errors = validator.numberOfInvalids();
				if (errors) {
					var message = DWZ.msg("validateFormError", [ errors ]);
					alertMsg.error(message);
				}
			}
		});

		$form.find('input[customvalid]').each(function() {
			var $input = $(this);
			$input.rules("add", {
				customvalid : $input.attr("customvalid")
			})
		});
	});

	if ($.fn.datepicker) {
		$('input.date', $p).each(function() {
			var $this = $(this);
			var opts = {};
			if ($this.attr("dateFmt"))
				opts.pattern = $this.attr("dateFmt");
			if ($this.attr("minDate"))
				opts.minDate = $this.attr("minDate");
			if ($this.attr("maxDate"))
				opts.maxDate = $this.attr("maxDate");
			if ($this.attr("mmStep"))
				opts.mmStep = $this.attr("mmStep");
			if ($this.attr("ssStep"))
				opts.ssStep = $this.attr("ssStep");
			$this.datepicker(opts);
		});
	}

	// navTab
	$("a[target=navTab]", $p).each(function() {
		$(this).click(function(event) {
			
			// ******* 超时监控 Add HYP
			if (JudgeTimeOut()) {
				DWZ.loadLogin();
				return false;
			}
			
			var $this = $(this);
			var title = $this.attr("title") || $this.text();
			var tabid = $this.attr("rel") || "_blank";
			var fresh = eval($this.attr("fresh") || "true");
			var external = eval($this.attr("external") || "false");
			var url = unescape($this.attr("href")).replaceTmById(
							$(event.target).parents(".unitBox:first"));
			DWZ.debug(url);
			
			// *******判断是否进行交易管理  Add HYP
			if(dealSwitch){
				url = dealManagementAddUrl(this, url);
				if(!url){
					return false;
				}
			}
			
			if (!url.isFinishedTm()) {
				alertMsg.warn($this.attr("warn")
						|| DWZ.msg("alertSelectMsg"));
				return false;
			}
			navTab.openTab(tabid, url, {
				title : title,
				fresh : fresh,
				external : external
			});
			event.preventDefault();
		});
	});
	// external
	$("a[target=external]", $p).each(function() {
		$(this).click(function(event) {
			
			// ******* 超时监控 Add HYP
			if (JudgeTimeOut()) {
				DWZ.loadLogin();
				return false;
			}
			
			var $this = $(this);
			var title = $this.attr("title") || $this.text();
			var tabid = $this.attr("rel") || "_blank";
			var fresh = eval($this.attr("fresh") || "true");
			var external = eval($this.attr("external") || "false");
			var url = unescape($this.attr("myurl")).replaceTmById(
							$(event.target).parents(".unitBox:first"));
			DWZ.debug(url);
			
			//判断是否进行交易管理 HYP
			if(dealSwitch){
				url = dealManagementAddUrl(this,
						url);
				if(!url && tranManageMode != "ignore"){
					return false;
				}
			}
			
			if (!url.isFinishedTm()) {
				alertMsg.warn($this.attr("warn")
						|| DWZ.msg("alertSelectMsg"));
				return false;
			}
			navTab.openTab(tabid, url, {
				numPerPage:10,
				title : title,
				fresh : fresh,
				external : external
			});
			event.preventDefault();
		});
	});

	// dialogs
	$("a[target=dialog]", $p).each(function() {
		$(this).click(
			function(event) {
			// ******* 超时监控 Add HYP
			if (JudgeTimeOut()) {
				DWZ.loadLogin();
				return false;
			}
			
			var $this = $(this);
			var title = $this.attr("title") || $this.text();
			var rel = $this.attr("rel") || "_blank";
			var options = {};
			var w = $this.attr("width");
			var h = $this.attr("height");
			if (w)
				options.width = w;
			if (h)
				options.height = h;
			options.max = eval($this.attr("max") || "false");
			options.mask = eval($this.attr("mask") || "false");
			options.maxable = eval($this.attr("maxable") || "true");
			options.minable = eval($this.attr("minable") || "true");
			options.fresh = eval($this.attr("fresh") || "true");
			options.resizable = eval($this.attr("resizable") || "true");
			options.drawable = eval($this.attr("drawable") || "true");
			options.close = eval($this.attr("close") || "");
			options.param = $this.attr("param") || "";
			
			// ********添加自定义判断程序 add LiAnDong
			var myOption = $this.attr("myOption");
			if (null != myOption && myOption != '') {
				var myFlag = eval(myOption);
				if (!myFlag) {
					return myFlag;
				}
			}
			
			var url = unescape($this.attr("href")).replaceTmById(
					$(event.target).parents(".unitBox:first"));
			DWZ.debug(url);
			if (!url.isFinishedTm()) {
				alertMsg.warn($this.attr("warn") || DWZ.msg("alertSelectMsg"));
				return false;
			}
			
			// ******* 交易管理 Add HYP
			if(dealSwitch){
				// TODO
				var result = dealManagementAddUrl(this,url);
				if(result == null){
					return false;
				}else if(result != null){
					url = result;
				}
			}
			
			$.pdialog.open(url, rel, title,
					options);
			return false;
		});
	});
	$("a[target=ajax]", $p).each(function() {
		$(this).click(
			function(event) {
				// ******* 超时监控 Add HYP
				if (JudgeTimeOut()) {
					DWZ.loadLogin();
					return false;
				}
				var $this = $(this);
				
				// ********添加自定义判断程序 add LiAnDong
				var myOption = $this.attr("myOption");
				if (null != myOption && myOption != '') {
					var myFlag = eval(myOption);
					if (!myFlag)
						return myFlag;
				}
				
				var rel = $this.attr("rel");
				if (rel) {
					var $rel = $("#" + rel);
					// 增加传中文参数
					// 在a标签中书写例子如：cnparam="cn=中国&usa=美国&jap=日本"
					$rel.loadUrl($this.attr("href"), $this
							.attr("cnparam"), function() {
						$rel.find("[layoutH]").layoutH();
					});
				}
				event.preventDefault();
			});
		});
	$("div.pagination", $p).each(function() {
		var $this = $(this);
		$this.pagination({
			targetType : $this.attr("targetType"),
			rel : $this.attr("rel"),
			totalCount : $this.attr("totalCount"),
			numPerPage : $this.attr("numPerPage"),
			pageNumShown : $this.attr("pageNumShown"),
			currentPage : $this.attr("currentPage")
		});
	});

	if ($.fn.sortDrag)
		$("div.sortDrag", $p).sortDrag();

	// dwz.ajax.js
	if ($.fn.ajaxTodo)
		$("a[target=ajaxTodo]", $p).ajaxTodo();
	if ($.fn.dwzExport)
		$("a[target=dwzExport]", $p).dwzExport();

	if ($.fn.lookup)
		$("a[lookupGroup]", $p).lookup();
	if ($.fn.multLookup)
		$("[multLookup]:button", $p).multLookup();
	if ($.fn.suggest)
		$("input[suggestFields]", $p).suggest();
	if ($.fn.itemDetail)
		$("table.itemDetail", $p).itemDetail();
	if ($.fn.selectedTodo)
		$("a[target=selectedTodo]", $p).selectedTodo();
	if ($.fn.pagerForm)
		$("form[rel=pagerForm]", $p).pagerForm({
			parentBox : $p
		});
	
	// *******自定义机构树组件 Add HYP 
	if ($.fn.orgtreegrn) {
		$("input.organ", $p).orgtreegrn($p);
	}
	// *******解除退格键的后退功能 Add HYP
	if (EscapeBackSpace) {
		$("body").unbind("keydown", EscapeBackSpace);
		$("body").bind("keydown", EscapeBackSpace);
	}
	// 交易管理事件的监听 Add HYP
	$("button", $p).click(function(event) {
		var btnId = $(this).attr("id");
		//判断亮色提示是否开启
		if(lightColorSwitch){
			//根据页面内是否含有 name为btnflow的输入框 和 点击按钮是否含有id 来判断 是否要有提示色显示
			if($p.find("input[name='btnflow']").length != 0 && btnId){
				var arr = $("input[name='btnflow']").val().split(",");
				var nextId = "";
				// 遍历查找要亮色显示的button
				for(var i = 0; i < arr.length ; i++){
					if(btnId == arr[i]){
						nextId = arr[i+1];
						break;
					}
				}
				//若查找到需要亮色显示的button,则将其亮色显示，否则所有亮色显示提示去除
				if(nextId != null && nextId != ""){
					var $nextBtn = $p.find("button#" + nextId);
					//去除所有的亮色显示
					$p.find("button").css("color","");
					//将按钮用亮色显示 并记录 当前亮色显示的按钮的id
					if($nextBtn && $nextBtn.length != 0){
						remindBtnId = $nextBtn.attr("id");
						$nextBtn.css("color","#f00");
					};
				}else{
					$p.find("button").css("color","");
				}
				
			}
		}
		//判断交易管理是否开启
		if(dealSwitch){
			//若该按钮有为id属性赋值
			if(btnId){
				// 取得点击按钮的按钮id属性
				clickBtnMark = $(this).attr("id");
			}else {
				alertMsg.warn("请为触发事件按钮的id属性赋值！");
				return false;
			}
		}
		
	});
	// *******判断是否开启按钮亮色显示 Add HYP
	if(lightColorSwitch){
		// 判断该页面是否含有name为btnflow的输入框
		if($p.find("input[name='btnflow']").length != 0){
			// 获取显示顺序id的值
			var btnflowVal = $("input[name='btnflow']").val();
			// 若是刷新的操作，则将上次提醒的按钮重新亮色显示
			if(remindBtnId != null && remindBtnId != "" && btnflowVal.indexOf(remindBtnId)){
				$("button#" + remindBtnId).css("color","#f00");
				return ;
			}
			//否则将当前页面的第一个按钮进行亮色显示
			var arr = $("input[name='btnflow']").val().split(",");
			if(arr.length >= 1){
				$("button#" + arr[0]).css("color","#f00");
			}
		};
	}
	
	// 这里放其他第三方jQuery插件...
}
/**
 * 通过两次点击之间的事件间隔判断session是否超时
 * @returns {Boolean}
 * @version 1.0 BY HYP
 */
function JudgeTimeOut() {
	var hasPass = false;
	nextTime = new Date();
	if (nextTime - prevTime > sessionTimeOut) {
		$.ajax({
			url : 'sys/queryUser_userAction.action?' + nextTime,
			async : false,
			method : 'POST',
			success : function(response) {
				var json = DWZ.jsonEval(response);
				if (json.statusCode == DWZ.statusCode.timeout) {
					hasPass = true;
					DWZ.loadLogin();
				}
			},
			error : DWZ.ajaxError
		});
	}
	prevTime = nextTime;
	return hasPass;
}
/**
 * 解除backspace键的后退功能
 * 
 * @returns {Boolean}
 * @version 1.0 BY HYP
 * @version 1.1 补充textarea元素的判断 2014年12月12日 14:45:52 BY HYP
 */
function EscapeBackSpace(event) {
	if (event.keyCode == 8) {
		if (event.srcElement.nodeName != 'INPUT'
				&& event.srcElement.nodeName != 'TEXTAREA') {
			event.stopPropagation();
			return false;
		}
	}
	event.stopPropagation();
}
/**
 * 交易管理补充url
 * 
 * @param aEle
 *            需要补充的a对象
 * @param url
 *            经过dwz处理之后的url
 * @returns 交易管理后补充的url
 * @version 1.0 2014年12月12日 14:50:04 BY HYP
 * @version 1.1 2014年12月15日 14:06:13 BY HYP 补充所有19个字段
 */
function dealManagementAddUrl(aEle, url) {
	//获取a元素jquery对象
	var $aEle = $(aEle);
	// 判断url中是否有? 若有问号直接补充变量，若无则添加问号
	url += url.indexOf("?") < 0 ? "?" : "";
	//判断事件源为菜单按钮
	if($aEle.parents(".dialog").length == 0 && $aEle.parents(".page").length == 0){
		url += "&nciHeadVo.buttonCode=menuId(" + $aEle.attr("rel") + ")";
		return url;
	}
	//判断该a元素既没有id属性也没有rel属性
	if(!($aEle.attr("id") || $aEle.attr("rel"))){
		alertMsg.warn("请为该元素的id属性赋值！");
		return null;
	}
	var jspname = getJspAllName($aEle);
	if(jspname){
		var buttonCode = jspname + "_";
		if($aEle.attr("id")){
			buttonCode += "id(" + $aEle.attr("id") + ")";
		}else{
			buttonCode += "rel(" + $aEle.attr("rel") + ")";
		}
		prevBtnMark = buttonCode;
		url += "&nciHeadVo.buttonCode=" + buttonCode;
		return url;
	}else{
		alertMsg.warn("该页面没有引入udmpCommon.jsp，请引入");
		return null;
	}
	return null;
}
/**
 * 处理a元素交易管理的公共校验
 */
function dealManagementCommonValidatorAEle(aEle) {
	return true;
}
/**
 * 针对交易管理的ajax的二次封装
 *  source 触发事件的元素 
 *  url ajax调用的url 
 *  type 访问类型 post||get
 *  param ajax调用的参数对象
 *  async 是否为异步请求
 *  dataType ajax调用的参数类型 默认为text 
 *  successCallBack 调用成功后的回调函数
 *  errorCallBack 调用失败后的回调函数
 */
function dealManagementAjax(source, url, requestType, param, async, dataType,
		successCallBack, errorCallBack) {
	var buttonCode = getbuttonCodeVal(source);
	//若buttonCode为false，则返回false
	if(typeof buttonCode == "boolean"){
		return false;
	}
	prevBtnMark = buttonCode;
	// 若url没有拼接参数
	if(url.indexOf("?") == -1){
		url += "?nciHeadVo.buttonCode=" + buttonCode;
	}else{ 
		url += "&nciHeadVo.buttonCode=" + buttonCode;
	}
	$.ajax({
		url : url,
		type : requestType || "post",// 若为设置
		data : param || {},
		async: async || true,
		dataType:dataType || "text",
		success : successCallBack || function() {
			// Do Nothing
		},
		error : errorCallBack || function() {
			alertMsg.error("调用ajax发生错误！");
		}
	});
}
/**
 * 针对udmp封装的ajax获取buttonCode的方法
 * @param source
 * @returns buttonCodeVal or false
 */
function getbuttonCodeVal(source){ 
	var $source = $(source);
	//调用函数获取jsp全路径
	var jspname = getJspAllName($source);
	if(jspname == ""){
		alertMsg.warn("该页面没有引入udmpCommon.jsp，请引入");
		return false;
	}
	// 若触发元素的的id为空，则不允许触发，让其补充后触发
	if(!$source.attr("id")){
		alertMsg.error("请为触发事件源对象的id属性赋值");
		return false;
	}
	var buttonCode = jspname + "_id(" + $source.attr("id") + ")";
	if($source.attr("name")){
		buttonCode += "_name(" + $source.attr("name") + ")";
	}
	return buttonCode;
}

/**
 * 获取jsp全路径
 * @param 触发事件的源元素的jquery对象
 * @returns "" or jspAllPath
 */
function getJspAllName($source){
	// 定义触发元素的顶级父元素
	// 判断触发元素所在页面是dialog还是navTab
	if($source.parents(".dialog").length == 1){
		$box = $source.parents(".dialog");
	}else{
		$box = $source.parents(".page");
	}
	// 获取udmpCommon.jsp中的隐藏文本框狂对象
	var $jspname = $box.find("#jspname");
	var jspname = "";
	// 判断是否获取到隐藏文本框对象，若未获取到
	if($jspname.length == 1){
		jspname = $jspname.val();
	}
	return jspname;
}
