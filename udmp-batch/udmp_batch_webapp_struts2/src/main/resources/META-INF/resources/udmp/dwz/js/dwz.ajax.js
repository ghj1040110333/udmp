/**
 * @author ZhangHuihua@msn.com
 * 2014年9月30日 14:59:26 处理iframe里新增修改后不刷新页面问题 BY HYP
 * 2014年12月15日 10:52:06 为防止子系统多做更改，修改各种提交操作，在提交时对子系统进行操作 BY HYP
 */

/**
 * 普通ajax表单提交
 * 
 * @param {Object}
 *            form
 * @param {Object}
 *            callback
 * @param {String}
 *            confirmMsg 提示确认信息
 */
function validateCallback(form, callback, confirmMsg) {
	// ******* 超时监控 Add HYP
	if (JudgeTimeOut()) {
		DWZ.loadLogin();
		return false;
	}
	
	// form 校验 若校验失败不允许提交
	var $form = $(form);
	if (!$form.valid()) {
		return false;
	}
	
	// ********添加自定义判断程序 add LiAnDong 
	var myOption = $form.attr("myOption");
	if (null != myOption && myOption != '') {
		var myFlag = eval(myOption);
		if (!myFlag)
			return myFlag;
	}
	
	var serializeArray = $form.serializeArray();
	
	// *******交易管理监控 Add HYP
	if(dealSwitch){
		var parent = $.pdialog.getCurrent() || navTab.getCurrentPanel();
		var result = addHiddenAttrs(form, parent);
		if(typeof result == "boolean"){
			return false;
		}
		serializeArray = result;
	}
	
	/*start  在请求参数中获取页面token ADD BY tanzl*/
	var $page = $(form).parents(".dialog");
	if($page.length == 0){
		$page = $(form).parents(".unitBox");
	}
	var tokenval=$page.find("input[name='token']").val();
	var tokenKey=$page.find("input[id='jspname']").val();
	//表单提交获取token和校验token标志
	serializeArray.push({'name':'token','value':tokenval});
	serializeArray.push({'name':'tokenKey','value':tokenKey});
	serializeArray.push({'name':'checkTokenFlag','value':'1'});
	/*token end*/
	
	var _submitFn = function() {
		$.ajax({
			type : form.method || 'POST',
			url : $form.attr("action"),
			data : serializeArray,
			dataType : "json",
			cache : false,
			success : callback || DWZ.ajaxDone,
			error : DWZ.ajaxError
		});
	}
	if (confirmMsg) {
		alertMsg.confirm(confirmMsg, {
			okCall : _submitFn
		});
	} else {
		_submitFn();
	}
	return false;
}
/**
 * 带文件上传的ajax表单提交
 * 
 * @param {Object}
 *            form
 * @param {Object}
 *            callback
 */
function iframeCallback(form, callback) {
	var $form = $(form), $iframe = $("#callbackframe");
	if (!$form.valid()) {
		return false;
	}

	if ($iframe.size() == 0) {
		$iframe = $(
				"<iframe id='callbackframe' name='callbackframe' src='about:blank' style='display:none'></iframe>")
				.appendTo("body");
	}
	if (!form.ajax) {
		$form.append('<input type="hidden" name="ajax" value="1" />');
	}
	form.target = "callbackframe";

	_iframeResponse($iframe[0], callback || DWZ.ajaxDone);
}
function _iframeResponse(iframe, callback) {
	var $iframe = $(iframe), $document = $(document);

	$document.trigger("ajaxStart");

	$iframe.bind("load", function(event) {
		$iframe.unbind("load");
		$document.trigger("ajaxStop");

		if (iframe.src == "javascript:'%3Chtml%3E%3C/html%3E';" || // For
		// Safari
		iframe.src == "javascript:'<html></html>';") { // For FF, IE
			return;
		}

		var doc = iframe.contentDocument || iframe.document;

		// fixing Opera 9.26,10.00
		if (doc.readyState && doc.readyState != 'complete')
			return;
		// fixing Opera 9.64
		if (doc.body && doc.body.innerHTML == "false")
			return;

		var response;

		if (doc.XMLDocument) {
			// response is a xml document Internet Explorer property
			response = doc.XMLDocument;
		} else if (doc.body) {
			try {
				response = $iframe.contents().find("body").text();
				response = jQuery.parseJSON(response);
			} catch (e) { // response is html document or plain text
				response = doc.body.innerHTML;
			}
		} else {
			// response is a xml document
			response = doc;
		}

		callback(response);
	});
}

/**
 * navTabAjaxDone是DWZ框架中预定义的表单提交回调函数． 服务器转回navTabId可以把那个navTab标记为reloadFlag=1,
 * 下次切换到那个navTab时会重新载入内容. callbackType如果是closeCurrent就会关闭当前tab
 * 只有callbackType="forward"时需要forwardUrl值
 * navTabAjaxDone这个回调函数基本可以通用了，如果还有特殊需要也可以自定义回调函数. 如果表单提交只提示操作是否成功, 就可以不指定回调函数.
 * 框架会默认调用DWZ.ajaxDone() <form action="/user.do?method=save" onsubmit="return
 * validateCallback(this, navTabAjaxDone)">
 * 
 * form提交后返回json数据结构statusCode=DWZ.statusCode.ok表示操作成功, 做页面跳转等操作.
 * statusCode=DWZ.statusCode.error表示操作失败, 提示错误原因.
 * statusCode=DWZ.statusCode.timeout表示session超时，下次点击时跳转到DWZ.loginUrl
 * {"statusCode":"200", "message":"操作成功", "navTabId":"navNewsLi",
 * "forwardUrl":"", "callbackType":"closeCurrent"} {"statusCode":"300",
 * "message":"操作失败"} {"statusCode":"301", "message":"会话超时"}
 * 
 */
function navTabAjaxDone(json) {
	DWZ.ajaxDone(json);
	if (json.statusCode == DWZ.statusCode.ok) {
		if (json.navTabId) { // 把指定navTab页面标记为需要“重新载入”。注意navTabId不能是当前navTab页面的
			navTab.reloadFlag(json.navTabId);
		} else { // 重新载入当前navTab页面
			var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
			var args = $pagerForm.size() > 0 ? $pagerForm.serializeArray() : {};
			//操作后补充数据  交易管理 HYP
			args = reloadPage(args); 
			navTabPageBreak(args, json.rel);
		}
		if ("closeCurrent" == json.callbackType) {
			setTimeout(function() {
				navTab.closeCurrentTab(json.navTabId);
			}, 100);
		} else if ("forward" == json.callbackType) {
			navTab.reload(json.forwardUrl);
		} else if ("forwardConfirm" == json.callbackType) {
			alertMsg.confirm(json.confirmMsg || DWZ.msg("forwardConfirmMsg"), {
				okCall : function() {
					navTab.reload(json.forwardUrl);
				},
				cancelCall : function() {
					navTab.closeCurrentTab(json.navTabId);
				}
			});
		} else {
			navTab.getCurrentPanel().find(":input[initValue]").each(function() {
				var initVal = $(this).attr("initValue");
				$(this).val(initVal);
			});
		}
	}
}

/**
 * dialog上的表单提交回调函数 服务器转回navTabId，可以重新载入指定的navTab.
 * statusCode=DWZ.statusCode.ok表示操作成功, 自动关闭当前dialog
 * 
 * form提交后返回json数据结构,json格式和navTabAjaxDone一致
 */
function dialogAjaxDone(json) {
	DWZ.ajaxDone(json);
	if (json.navTabId) {
		navTab.reload(json.forwardUrl, {navTabId : json.navTabId});
	} else { 
		// else if (json.rel) 该为else HYP 处理iframe里新增修改后不刷新页面问题
		var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
		var args = $pagerForm.size() > 0 ? $pagerForm.serializeArray() : {};
		
		// 交易管理 HYP
		if(dealSwitch){
			args = reloadPage(args); 
		}
		
		/*start token 在请求参数中获取页面token*/
//		var serializeArray = $pagerForm.serializeArray();
//		var $page=$pagerForm.parents(".unitBox");
//		var tokenval=$page.find("input[name='token']").val();
//		args.push({'name':'token','value':tokenval});
		/*token end*/
		
		navTabPageBreak(args, json.rel);
	}
	if ("closeCurrent" == json.callbackType) {
		$.pdialog.closeCurrent();
	}
}

/**
 * 处理navTab上的查询, 会重新载入当前navTab
 * 
 * @param {Object}
 *            form
 */
function navTabSearch(form, navTabId) {
	var $form = $(form);
	
	// ******* 超时监控 Add HYP
	if (JudgeTimeOut()) {
		DWZ.loadLogin();
		return false;
	}
	
	// ********添加自定义判断程序 add LiAnDong
	var myOption = $form.attr("myOption");
	if (null != myOption && myOption != '') {
		var myFlag = eval(myOption);
		if (!myFlag)
			return myFlag;
	}
	
	var serializeArray = $form.serializeArray();

	// *******交易管理监控 Add HYP
	if(dealSwitch){
		var result = addHiddenAttrs(form, navTab.getCurrentPanel());
		if(typeof serializeArray == "boolean"){
			return false;
		}
		serializeArray = result;
	}
	
	/*start token 在请求参数中获取页面token add by tanzl*/
	var $page=$form.parents(".dialog:first");
	if($page.length == 0){
		$page = $form.parents(".page:first");
	}
	var tokenval=$page.find("input[name='token']").val();
	serializeArray.push({'name':'token','value':tokenval});
	/*token end*/
	
	if (form[DWZ.pageInfo.pageNum])
		form[DWZ.pageInfo.pageNum].value = 1;
	navTab.reload($form.attr('action'), {
		data : serializeArray,
		navTabId : navTabId
	});
	return false;
}
/**
 * 处理dialog弹出层上的查询, 会重新载入当前dialog
 * 
 * @param {Object}
 *            form
 */
function dialogSearch(form) {
	var $form = $(form);
	if (form[DWZ.pageInfo.pageNum])
		form[DWZ.pageInfo.pageNum].value = 1;
	var serializeArray = $form.serializeArray();
	
	// *******交易管理监控 Add HYP
	if(dealSwitch){
		var result = addHiddenAttrs(form, navTab.getCurrentPanel());
		if(typeof serializeArray == "boolean"){
			return false;
		}
		serializeArray = result;
	}
	
	$.pdialog.reload($form.attr('action'), {data : serializeArray});
	return false;
}
function dwzSearch(form, targetType) {
	if (targetType == "dialog")
		dialogSearch(form);
	else
		navTabSearch(form);
	return false;
}
/**
 * 处理div上的局部查询, 会重新载入指定div
 * 
 * @param {Object}
 *            form
 */
function divSearch(form, rel) {
	var $form = $(form);
	if (form[DWZ.pageInfo.pageNum])
		form[DWZ.pageInfo.pageNum].value = 1;
	if (rel) {
		var $box = $("#" + rel);
		var serializeArray = $form.serializeArray();
		
		// *******交易管理监控 Add HYP
		if(dealSwitch){
			var result = addHiddenAttrs(form, navTab.getCurrentPanel());
			if(typeof serializeArray == "boolean"){
				return false;
			}
			serializeArray = result;
		}
		
		$box.ajaxUrl({
			type : "POST",
			url : $form.attr("action"),
			data : serializeArray,
			callback : function() {
				$box.find("[layoutH]").layoutH();
			}
		});
	}
	return false;
}
/**
 * 
 * @param {Object}
 *            args {pageNum:"",numPerPage:"",orderField:"",orderDirection:""}
 * @param String
 *            formId 分页表单选择器，非必填项默认值是 "pagerForm"
 */
function _getPagerForm($parent, args) {
	var form = $("#pagerForm", $parent).get(0);

	if (form) {
		if (args["pageNum"])
			form[DWZ.pageInfo.pageNum].value = args["pageNum"];
		if (args["numPerPage"])
			form[DWZ.pageInfo.numPerPage].value = args["numPerPage"];
		if (args["orderField"])
			form[DWZ.pageInfo.orderField].value = args["orderField"];
		if (args["orderDirection"] && form[DWZ.pageInfo.orderDirection])
			form[DWZ.pageInfo.orderDirection].value = args["orderDirection"];
	}

	return form;
}

/**
 * 处理navTab中的分页和排序 
 * targetType: navTab 或 dialog 
 * rel: 可选 用于局部刷新div id号 
 * data:pagerForm参数 {pageNum:"n", numPerPage:"n", orderField:"xxx",orderDirection:""} 
 * callback: 加载完成回调函数
 */
function dwzPageBreak(options) {
	var op = $.extend({ targetType:"navTab", rel:"", data:{pageNum:"", numPerPage:"", orderField:"", orderDirection:""}, callback:null}, options);
	var $parent = op.targetType == "dialog" ? $.pdialog.getCurrent() : navTab.getCurrentPanel();
	
	//将分页数据显示到div中
	if (op.rel) {
		var $box = $parent.find("#" + op.rel);
		var form = _getPagerForm($box, op.data);
		if (form) {
			var serializeArray = $(form).serializeArray();
			
			// *******交易管理监控 Add HYP
			if(dealSwitch){
				var result = addHiddenAttrs(form, navTab.getCurrentPanel());
				if(typeof serializeArray == "boolean"){
					return false;
				}
				serializeArray = result;
			}
			
			$box.ajaxUrl({
				type : "POST",
				url : $(form).attr("action"),
				data : serializeArray,
				callback : function() {
					$box.find("[layoutH]").layoutH();
				}
			});
		}
	} else {
		var form = _getPagerForm($parent, op.data);
		var params = $(form).serializeArray();
		
		// ******* 交易管理
		if(dealSwitch){
			params = addHiddenAttrs(form,$parent);
			//不是按钮分页
			if (typeof params == "string") {
				//获取form所在页面的分页组件
				var $form = $(form);
				var $box = null;
				if($form.parents(".page").length == 1){
					$box = $form.parents(".page");
				}else{
					$box = $form.parents(".dialog");
				}
				var $page = $box.find(".pagination");
				if($page.length != 0 && !$page.attr("id")){
					alertMsg.warn("请为分页组件的id属性赋值");
					return false;
				}else{
					params = $form.serializeArray();
					var buttonCode = new Object();
					buttonCode.name = 'nciHeadVo.buttonCode';
					buttonCode.value = getJspAllName($page) + "_id(" + $page.attr("id") +  ")";
					params[params.length] = buttonCode;
				}
			}else if (typeof params == "boolean") {
				return false;
			}else{
				params = $(form).serializeArray();
			}
		}
		
		
		if (op.targetType == "dialog") {
			if (form)
				$.pdialog.reload($(form).attr("action"), {data : params,callback : op.callback });
		} else {
			if (form)
				navTab.reload($(form).attr("action"), {data : params,callback : op.callback });
		}
	}
}
/**
 * 处理navTab中的分页和排序
 * 
 * @param args
 *            {pageNum:"n", numPerPage:"n", orderField:"xxx", orderDirection:""}
 * @param rel：
 *            可选 用于局部刷新div id号
 */
function navTabPageBreak(args, rel) {
	dwzPageBreak({
		targetType : "navTab",
		rel : rel,
		data : args
	});
}
/**
 * 处理dialog中的分页和排序 参数同 navTabPageBreak
 */
function dialogPageBreak(args, rel) {
	dwzPageBreak({
		targetType : "dialog",
		rel : rel,
		data : args
	});
}

function ajaxTodo(url, callback) {
	var $callback = callback || navTabAjaxDone;
	if (!$.isFunction($callback))
		$callback = eval('(' + callback + ')');
	$.ajax({
		type : 'POST',
		url : url,
		dataType : "json",
		cache : false,
		success : $callback,
		error : DWZ.ajaxError
	});
}

function ajaxSubmit(url, data) {
	var $callback = navTabAjaxDone;
	$.ajax({
		type : 'POST',
		url : url,
		data : data,
		dataType : "json",
		cache : false,
		success : $callback,
		error : DWZ.ajaxError
	});
}

/**
 * http://www.uploadify.com/documentation/uploadify/onqueuecomplete/
 */
function uploadifyQueueComplete(queueData) {

	var msg = "已上传文件的总个数为: " + queueData.uploadsSuccessful + "<br/>"
			+ "上传时出错的个数为: " + queueData.uploadsErrored + "<br/>" + "上传总大小为: "
			+ queueData.queueBytesUploaded + " bytes " + "<br/>"
			+ "上传所有文件的平均速度为: " + queueData.averageSpeed;
	// var msg = "The total number of files uploaded:
	// "+queueData.uploadsSuccessful+"<br/>"
	// + "The total number of errors while uploading:
	// "+queueData.uploadsErrored+"<br/>"
	// + "The total number of bytes uploaded:
	// "+queueData.queueBytesUploaded+"<br/>"
	// + "The average speed of all uploaded files: "+queueData.averageSpeed;
	if (queueData.uploadsErrored) {
		alertMsg.error(msg);
	} else {
		alertMsg.correct(msg);
	}
}
/**
 * http://www.uploadify.com/documentation/uploadify/onuploadsuccess/
 */
function uploadifySuccess(file, data, response) {
	$.log(response);
}
/**
 * http://www.uploadify.com/documentation/uploadify/onuploaderror/
 */
function uploadifyError(file, errorCode, errorMsg) {
	alertMsg.error(errorCode + ": " + errorMsg);
}

/**
 * http://www.uploadify.com/documentation/
 * 
 * @param {Object}
 *            event
 * @param {Object}
 *            queueID
 * @param {Object}
 *            fileObj
 * @param {Object}
 *            errorObj
 */
function uploadifyError(event, queueId, fileObj, errorObj) {
	alert("event:" + event + "\nqueueId:" + queueId + "\nfileObj.name:"
			+ fileObj.name + "\nerrorObj.type:" + errorObj.type
			+ "\nerrorObj.info:" + errorObj.info);
}

$.fn.extend({
	ajaxTodo : function() {
		return this.each(function() {
			var $this = $(this);
			$this.click(function(event) {
				
				// ******* 超时监控 Add HYP
				if (JudgeTimeOut()) {
					DWZ.loadLogin();
					return false;
				}
				
				// ********添加自定义判断程序 add LiAnDong
				var myOption = $this.attr("myOption");
				if (null != myOption && myOption != '') {
					var myFlag = eval(myOption);
					if (!myFlag)
						return myFlag;
				}
				
				var url = unescape($this.attr("href")).replaceTmById(
						$(event.target).parents(".unitBox:first"));
				DWZ.debug(url);
				
				// ******* 交易管理 处理 Add HYP
				if(dealSwitch) {
					var result = dealManagementAddUrl(this, url);
					if(result == null) {
						return false;
					}else {
						url = result;
					}
				}
				
				if (!url.isFinishedTm()) {
					alertMsg.warn($this.attr("warn")
							|| DWZ.msg("alertSelectMsg"));
					return false;
				}
				var title = $this.attr("title");
				if (title) {
					alertMsg.confirm(title, {
						okCall : function() {
							ajaxTodo(url, $this.attr("callback"));
						}
					});
				} else {
					ajaxTodo(url, $this.attr("callback"));
				}
				event.preventDefault();
			});
		});
	},
	dwzExport : function() {
		function _doExport($this) {
			var $p = $this.attr("targetType") == "dialog" ? $.pdialog
					.getCurrent() : navTab.getCurrentPanel();
			var $form = $("#pagerForm", $p);
			var url = $this.attr("href");
			window.location = url + (url.indexOf('?') == -1 ? "?" : "&")
					+ $form.serialize();
		}

		return this.each(function() {
			var $this = $(this);
			$this.click(function(event) {
				var title = $this.attr("title");
				if (title) {
					alertMsg.confirm(title, {
						okCall : function() {
							_doExport($this);
						}
					});
				} else {
					_doExport($this);
				}

				event.preventDefault();
			});
		});
	}
});
/**
 * 为form表单添加隐藏属性
 * 
 * @param form
 *            被序列化的form表单
 * @return form表单添加隐藏属性后的属性序列化数组
 * @retrun false
 * @version 1.0 2014年12月12日 18:06:21 BY HYP
 * @version 1.1 2014年12月15日 10:37:31 BY HYP 将所有字段均进行添加，并将parent当做参数传入
 */
function addHiddenAttrs(form, parent) {
	// 获取当前form的jquery对象
	var $form = $(form, parent);
	// 在当前form中查找点击提交的button按钮
	var $buttonObj = $form.find("#" + clickBtnMark);
	//若未找到该对象则不允许提交
	if($buttonObj.length == 0){
		return "NotBtnSubmit";
	}
	var buttonCodeVal = getbuttonCodeVal($buttonObj[0]);
	if(typeof buttonCodeVal == "boolean"){
		return false;
	}else{
		// 序列号当前表单元素 并转换成json对象 serializeArray是一个可变长度的数组
		var serializeArray = $form.serializeArray();
		var buttonCode = new Object();
		buttonCode.name = 'nciHeadVo.buttonCode';
		prevBtnMark = buttonCodeVal;
		buttonCode.value = buttonCodeVal;
		serializeArray[serializeArray.length] = buttonCode;
		return serializeArray;
	}
}
/**
 * 交易管理的公共校验函数
 * 
 * @returns true||false 是否允许该表单提交
 * @version 1.0 2014年12月12日 18:06:21 BY HYP
 */
function dealManagementCommonValidator() {
	// 若未取到form表单提交时的button对象，则返回false,前端控制不允许提交
	if ($("#" + clickBtnMark).length == 0 && tranManageMode != "ignore") {
		// 强制控制一下，让开发人员为form表单的提交按钮赋值id
		alertMsg.warn("请为点击按钮的id属性赋值再提交！");
		return false;
	}
	return true;
}
/**
 * 处理完后的回调补充数据 补充上一次的buttonCode
 * @param args
 * @returns
 */
function reloadPage(args){
	var buttonCode = new Object();
	buttonCode.name = 'nciHeadVo.buttonCode';
	buttonCode.value = prevBtnMark;
	args[args.length] = buttonCode;
	return args;
}

