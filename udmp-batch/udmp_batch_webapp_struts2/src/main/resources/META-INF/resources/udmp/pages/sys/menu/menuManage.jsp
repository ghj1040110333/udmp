<%@page contentType="text/html; charset=utf-8"%>
<%@include file="/udmpCommon.jsp"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<script type="text/javascript">
	$(function(){
		$("button").click(function(event) {
			var btnId = $(this).attr("id");
			//判断交易管理是否开启
			if(udmpUtil.dealSwitch){
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
// $("button").click(function(event){
// 	console.log();
// });
			
		/**表单提交*/
		$("#submitButton").click(function(){
			//如果提交时没有父菜单，则将表单提交时的父id设置为空
			if(isNulOrEmpty($("#modulepId").val())){
				$("#hiddenpId").attr("value","");
			}
			menuForm.attr("action","sys/updateMenu_menuAction.action?flag="+operateFlag);
			menuForm.submit();
		});
	});
	function changeModuleType(){
		var moduleType = $("#moduleType").attr("value");
		if (moduleType == 3) {
			$("#combox_externalSystemId").find("a").removeAttr("disabled");
			$("#externalSystemId").removeAttr("disabled");
		} else {
			$("#combox_externalSystemId").find("a").attr("disabled",true);
			$("#externalSystemId").attr("disabled",true);
		}
	}
	/*调整菜单树样式*/
	$(function(){
		var height = parseInt($("div.accordionContent").css("height")) - 13 + "px";
		$("#menuTree").css("height",height);
	});
	
</script>
<div class="panelBar" style="width: 100%">
	<ul class="toolBar">
		<li><a class="add" onclick="addMenu()" title=""><span>新建菜单</span></a></li>
		<li><a class="edit" onclick="updateMenu()" title=""><span>修改菜单</span></a></li>
		<li><a id="deleteMenu" class="delete" onclick="deleteMenu()"
			title="是否确认删除？"> <span>删除菜单</span></a></li>
	</ul>
</div>
<div class="pageContent">
	<div id="menuContent" class="menuContent"
		style="display: none; position: absolute; z-index: 1002">
		<ul id="menuTree1" class="ztree" style="margin-top: 0; width: 200px;"></ul>
		<a href="getMenuList_menuAction.action" style="display: none"
			id="actionA"></a>
	</div>
	<table id="menuTable">
		<tr>
			<td width="15%" valign="top" layoutH="10">
				<ul id="menuTree" class="ztree"></ul>
			</td>
			<td width="80%" valign="top">
				<div class="pageContent" layoutH="0">
					<div class="panel collapse">
						<h1 id="functionLabel">菜单</h1>
						<div class="pageFormContent">
							<form id="menuForm" class="pageForm required-validate"
								method="post"
								onsubmit="return validateCallback(this,afterFormAjaxDone)">
								<dl>
									<dt>菜单名称：</dt>
									<dd>
										<input id="modNameId" type="text" name="module.moduleName"
											class="required" maxlength="20" />
									</dd>
								</dl>
								<!-- 用于存储菜单Id -->
								<s:hidden id="moduleId" name="module.moduleId" />
								<dl>
									<dt>父菜单：</dt>
									<dd>
										<ul class="list">
											<li class="title">
												<!-- 用来存储点击选中的菜单id，回传到后台 --> <s:hidden id="hiddenpId"
													name="module.parentId" /> <input id="modulepId"
												type="text" readonly /> &nbsp; <a id='menuBtn'
												class="btnLook" href='#' onclick='showMenu(); return false;'></a>
											</li>
										</ul>
									</dd>
								</dl>
								<dl>
									<dt>序列：</dt>
									<dd>
										<input type="text" name="module.listOrder"
											class="required digits" maxlength="20" />
									</dd>
								</dl>
								<dl class='nowrap'>
									<dt>URL入口地址：</dt>
									<dd>
										<textarea id="defaultUrl" name="module.url" cols="70"></textarea>
									</dd>
								</dl>
								<div class="divider"></div>
								<dl>
									<dt>菜单类型：</dt>
									<dd>
										<select id="moduleType" class="combox"
											name="module.moduleType" onChange="changeModuleType()">
											<option value="0">菜单</option>
											<option value="1">根菜单</option>
											<option value="3">外系统菜单</option>
										</select>
									</dd>
								</dl>
								<dl>
									<dt>菜单所属外系统：</dt>
									<dd>
										<Field:codeTable id="externalSystemId"
											name="module.externalSystemId"
											tableName="t_udmp_external_system_info" disabled="true" />
									</dd>
								</dl>
								<dl>
									<dt>是否叶模块标志：</dt>
									<dd>
										<select id="leaf" class="combox" name="module.leaf">
											<option value="Y">是</option>
											<option value="N">否</option>
										</select>
									</dd>
								</dl>
								<div class="formBar" style="padding: 5px 43%; height: auto;">
									<ul>
										<li><div class="button">
												<div class="buttonContent">
													<button type="button" id="submitButton" disabled="true">保存</button>
												</div>
											</div></li>
										<li><div class="button">
												<div class="buttonContent">
													<button type="button" id="clearMenu" onclick="clearForm();">清空</button>
												</div>
											</div></li>
									</ul>
								</div>
							</form>
						</div>
					</div>

					<div class="panel collapse">
						<h1>菜单权限</h1>
						<div id="urlForm" class="pageFormContent">
							<dl class='nowrap'>
								<dt>菜单id：</dt>
								<dd>
									<input type="text" width="300px" name="moduleId" readonly />
								</dd>
							</dl>
							<dl class='nowrap'>
								<dt style="width: 120px">菜单权限：</dt>
								<dd style="width: 650px">
									<select id="moduleUrl" name="moduleUrl" multiple="multiple"
										style="width: 300px" size=5
										ondblclick="return AppendItem('moduleUrl', 'moduleFunction', false);"></select>

									<div style="margin: 15px 0 0 10px; float: left;">
										<div class="buttonActive">
											<div class="buttonContent">
												<button id="toRight"
													onclick="return AppendItem('moduleUrl', 'moduleFunction', false);"
													type="button">==》</button>
											</div>
										</div>
										<div class="buttonActive"
											style="clear: left; margin: 5px 0px 0px;">
											<div class="buttonContent">
												<button id = "toLeft"
													onclick="return AppendItem('moduleFunction','moduleUrl', false);"
													type="button">《==</button>
											</div>
										</div>
									</div>

									<div style="width: 300px; margin-left: 355px">
										<select id="moduleFunction" name="moduleFunction"
											multiple="multiple" style="width: 300px;" size=5
											ondblclick="return AppendItem('moduleFunction','moduleUrl',  false);"></select>
									</div>

									<div class="buttonActive" style="margin: 5px 0 0 100px;">
										<div class="buttonContent">
											<button type="button" id="deleteUrlBtn" onclick="delUrlBtnFun();">删除URL</button>
										</div>
									</div>
									<div class="buttonActive" style="margin: 5px 0 0 300px;">
										<div class="buttonContent">
											<button type="button" id="deleteFucBtn" onclick="delFuncBtnFun();">删除功能点</button>
										</div>
									</div>
								</dd>
							</dl>
							<div class="divider"></div>
							<textarea id="addUrl" cols="70"
								style="margin-left: 130px; margin-bottom: 5px; height: 40px;"></textarea>

							<div
								style="position: absolute; width: 200px; margin-left: 670px; margin-top: -20px">

								<div class="buttonActive"
									style="margin: 30px 0 0 0; float: left;">
									<div class="buttonContent">
										<button type="button" onclick="addUrlBtnFun();" id="addUrlBtn">添加URL</button>
									</div>
								</div>

								<div class="buttonActive"
									style="margin: 30px 0 0 10px; float: left;">
									<div class="buttonContent">
										<button type="button" onclick="addFuncBtnFun();"
											id="addUrlBtn">添加功能点</button>
									</div>
								</div>
							</div>
							<br />

							<div class="formBar" style="padding: 5px 43%; height: auto;">
								<ul>
									<li><div class="button">
											<div class="buttonContent">
												<button onclick="submitUrlForm();" id="submitUrlButton"
													disabled="true">提交</button>
											</div>
										</div></li>
									<li><div class="button">
											<div class="buttonContent">
												<button type="button" id="clearUrlBtn" onclick="clearForm();">清空</button>
											</div>
										</div></li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</td>
		</tr>
	</table>

</div>

<script type="text/javascript">

	
var operateFlag = 0; //操作符: 0-无操作，1-新增，2-修改
var functionLabel = $("#functionLabel");
var menuForm = $("#menuForm");
var menuTree = "menuTree";
var menuTable = $("#menuTable");

//菜单树的配置
var setting = {
	data : {	key : {	title : "t"	},
	simpleData : {	enable : true	} },
	callback : {onClick : onClickSelect},
	view:{	selectedMulti: false}
};

var zNodes;//菜单树的节点

//初始化树菜单
function init(){
$.ajax({
	type : "post",
	url : '<%=request.getContextPath()%>/sys/getMenuList_menuAction.action',
			dataType : "json",
			async : false,
			success : function(result) {
				$("#jspname").val('<%=request.getRequestURI()%>');
				zNodes = result.menuList;
				$.fn.zTree.init($("#"+menuTree), setting, zNodes);
				var zTree = $.fn.zTree.getZTreeObj(menuTree);
				zTree.expandAll(true);
			},
			error : function(result) {
				alertMsg.error('系统异常');
			}
		});
}
function onClickSelect(event, treeId, treeNode, clickFlag) {
	clearForm();
	functionLabel.text("选中菜单");
};

/**清除表单状态*/
function clearForm() {
	functionLabel.text("");//清空标题
	$("input").not(":hidden").attr("value", "");//input框置空
	$("textarea").attr("value", "");//textarea置空
	$("#submitButton").attr("disabled","true");//提交按钮变为不可提交
	$("#submitUrlButton").attr("disabled","true");
	operateFlag = 0;
};

/**新增菜单*/
function addMenu() {
	clearForm();
	$("#submitButton").removeAttr("disabled");//表单可提交
	operateFlag = 1;//新增标志
	functionLabel.text("新增菜单");
}

/**删除菜单*/
function deleteMenu() {
	clearForm();
	operateFlag = 3;
	var treeObj = $.fn.zTree.getZTreeObj(menuTree);//得到树对象
	//将请求参数拼接到请求url中
	if (treeObj.getSelectedNodes().length == 0) {
		alertMsg.error('请选择一个菜单');
		functionLabel.text("");
	} else{
		var valueId = treeObj.getSelectedNodes()[0].id;
		alertMsg.confirm("确认删除？",{
			okCall:function(){
				$.ajax({type:"GET",url:"sys/deleteMenu_menuAction.action",
					data:{moduleId:valueId,flag:operateFlag},
					dataType:"json",success:function(json){
						$("#jspname").val('<%=request.getRequestURI()%>');
						parent.DWZ.ajaxDone(json);
						init();
					}
				});
			}
		});
	} 
};

/**更新菜单*/
function updateMenu() {
	clearForm();//清除表单样式
	$("#submitButton").removeAttr("disabled");
	$("#submitUrlButton").removeAttr("disabled");
	operateFlag = 2; //更新标志
	var treeObj = $.fn.zTree.getZTreeObj(menuTree);
	//判断是否选中菜单
	if (treeObj.getSelectedNodes().length == 0) {
		alertMsg.error('请选择一个菜单');
		functionLabel.text("");
	} else {
		//更新选中菜单,参数为表单对象和选中的节点
		updateTable($("#menuTable"), treeObj.getSelectedNodes()[0]);
		//显示选中菜单对应的url,参数为选中菜单的id
		showUrl(treeObj.getSelectedNodes()[0].id);
		//显示选中菜单对应的功能点
		showFunction(treeObj.getSelectedNodes()[0].id);
		functionLabel.text("更新菜单");
	}
};

/**将值填入div组件的input,textArea,select当中*/
function updateTable(parentDiv, obj) {
	//菜单表父菜单名字的回显
	$.each(zNodes,function(){
	//将被选中的节点的父菜单名字赋值为父菜单id对应的菜单id对应的名字
		if($(this).attr("id")==obj.parentId){
			$("#modulepId").attr("value",$(this).attr("name"));
		}
	});	
	//input输入框的回显
	parentDiv.find("input").each(function() {
		var inputValue = $(this);
		$.each(obj, function(i, val) {
			/*如果当前input框中的name属性等于返回的json中的key,
				就将input框中的值设置为key对应的value*/
			if (i == inputValue.attr("name")) {
				inputValue.attr("value", val);
			}
		});
	});
	//文本域的回显
	parentDiv.find("textarea").each(function() {
		var inputValue = $(this);
		$.each(obj, function(i, val) {
			if (i == inputValue.attr("name")) {
				inputValue.attr("value", val);
			}
		});
	});
	//下拉选的回显,参数为id和值
	selectMyComBox("moduleType",obj.moduleType);
	selectMyComBox("leaf",obj.leaf);
}

//表单提交后的处理
function afterFormAjaxDone(json){
	DWZ.ajaxDone(json);
	if (json.statusCode == DWZ.statusCode.ok){
		if (json.navTabId){ //把指定navTab页面标记为需要“重新载入”。注意navTabId不能是当前navTab页面的
			navTab.reloadFlag(json.navTabId);
		} else { //重新载入当前navTab页面
			var $pagerForm = $("#pagerForm", navTab.getCurrentPanel());
			var args = $pagerForm.size()>0 ? $pagerForm.serializeArray() : {}
			navTabPageBreak(args, json.rel);
			init();
		}
		
		if ("closeCurrent" == json.callbackType) {
			setTimeout(function(){navTab.closeCurrentTab(json.navTabId);}, 100);
		} else if ("forward" == json.callbackType) {
			navTab.reload(json.forwardUrl);
		} else if ("forwardConfirm" == json.callbackType) {
			alertMsg.confirm(json.confirmMsg || DWZ.msg("forwardConfirmMsg"), {
				okCall: function(){
					navTab.reload(json.forwardUrl);
				},
				cancelCall: function(){
					navTab.closeCurrentTab(json.navTabId);
				}
			});
		} else {
			navTab.getCurrentPanel().find(":input[initValue]").each(function(){
				var initVal = $(this).attr("initValue");
				$(this).val(initVal);
			});
		}
	}}
$(document).ready(init());
</script>
<script type="text/javascript">
/*功能点回显到下拉列表*/
function showFunction(id){
	$.ajax({
		type : "post",
		url : "sys/getModuleFuncPoint_menuAction.action",
		//将菜单id发送到后台,后台根据id查询对应的功能点,返回功能点的json
		data : {moduleId:id} ,
				dataType : "json",
				async : false,
				success : function(data) {
					$("#jspname").val('<%=request.getRequestURI()%>');
					var arr = data.funcList;
					//添加option之前先清空
					$("#moduleFunction").empty();
					for(i=0;i<arr.length;i++){
						$("#moduleFunction").append("<option class='item'>"+arr[i]+"</option><br/>");
					}
				},
				error : function(result) {
					alertMsg.error('系统异常');
				}
			});
}

/* 删除功能点 */
function delFuncBtnFun(){
	//将选中的option删除
	$("#moduleFunction option:selected").remove();
}

</script>

<script type="text/javascript">
/**得到Url显示到下拉菜单*/
function showUrl(id){
	$.ajax({
		type : "post",
		url : "sys/getModuleUrl_menuAction.action",
		//将选中菜单的id 发送到后台，后台根据菜单id查询出t_module_url表中id对应的记录
		//后台返回的数据会过滤掉此id对应的功能点，功能点将在另一个下拉选显示
		data : {moduleId:id} ,
				dataType : "json",
				async : false,
				//后台返回的json,回显到下拉选
				success : function(data) {
					$("#jspname").val('<%=request.getRequestURI()%>');
					var arr = data.urlList;
					$("#moduleUrl").empty();
					for(i=0;i<arr.length;i++){
						$("#moduleUrl").append("<option class='item'>"+arr[i]+"</option><br/>");
					}
				},
				error : function(result) {
					alertMsg.error('系统异常');
				}
			});
}

/**
 * 删除Url
 */
function delUrlBtnFun(){
	$("#moduleUrl option:selected").remove();
}
/**
 * 判断添加的内容是否在下拉选中存在
 */
function isAddable(addoptionValue){
	//菜单权限的下拉选子节点长度
	var urlLength = $("#moduleUrl").children().length;
	//功能点下拉选的长度
	var fucLength = $("#moduleFunction").children().length;
	
	//判断添加内容是否为空
	if(addoptionValue ==''){
		alertMsg.info('请添加内容！');
		return false;
	}
	//判断添加的值是否已存在,遍历下拉的option和添加的内容进行比较，如果已经存在值则不能添加
	for(var i = 0; i < urlLength ; i++){
		if($($("#moduleUrl option")[i]).text() == addoptionValue){
			alertMsg.info('输入值已在Url中存在!');
			return false;
		}
	}
	//遍历下拉的option和添加的内容进行比较，如果已经存在值则不能添加
	for(var i = 0; i < fucLength ; i++){
		if($($("#moduleFunction option")[i]).text() == addoptionValue){
			alertMsg.info('输入值已在功能点中存在!');
			return false;
		}
	}
	return true;
}
/**
 * 添加Url
 */
function addUrlBtnFun(){
	//菜单权限的下拉选子节点长度
	var urlLength = $("#moduleUrl").children().length;
	//添加内容（文本域的值）
	var addoptionValue = $("#addUrl").val();
	//对文本域的值进行添加校验
	if(isAddable(addoptionValue)){
		//如果下拉选为空，则在添加之前加一个值为空的option
		if(urlLength==0){
			$("#moduleUrl").append("<option class='item'>"+addoptionValue+"</option>");
		}else{
			$($("#moduleUrl option")[0]).before("<option class='item'>"+addoptionValue+"</option>");
		}
	}
	$("#addUrl").val("");
}

/* 添加功能点 */
function addFuncBtnFun(){
	//功能点下拉选的长度
	var fucLength = $("#moduleFunction").children().length;
	//添加内容（文本域的值）
	var addoptionValue = $("#addUrl").val();
	if(isAddable(addoptionValue)){
		if(fucLength==0){
			$("#moduleFunction").append("<option class='item'>"+addoptionValue+"</option>");
		}else{
			$($("#moduleFunction option")[0]).before("<option class='item'>"+addoptionValue+"</option>");
		}
	}
	$("#addUrl").val("");
}

/*提交菜单Url以及菜单功能点*/
function submitUrlForm(){
	//菜单权限的下拉选子节点长度
	var urlLength = $("#moduleUrl").children().length;
	//菜单功能点的下拉选子节点长度
	var funcLength = $("#moduleFunction").children().length;
	var urls = "";
	var funcPoints = "";
	//遍历下拉选的内容得到菜单权限及功能点
	for(var i = 0; i < urlLength ; i++){
		if($($("#moduleUrl option")[i]).text() != ''){
			urls += $($("#moduleUrl option")[i]).text() + ",";
		}
	}
	for(var i = 0; i < funcLength ; i++){
		if($($("#moduleFunction option")[i]).text() != ''){
			funcPoints +=$($("#moduleFunction option")[i]).text() + ",";
		}
	}
	//菜单id的值
	var id = $("#moduleId").attr("value");
	//去掉末尾的逗号
	urls = urls.substring(0,urls.length - 1);
	funcPoints =funcPoints.substring(0,funcPoints.length -1);
	$.ajax({type:"POST",url:"sys/updateUrl_menuAction.action",
	//请求参数为urls 和 菜单id 的值
			data:{urls:urls,funcPoints:funcPoints,moduleId:id},
			dataType:"json",
			success:function(json){
				$("#jspname").val('<%=request.getRequestURI()%>');
				parent.DWZ.ajaxDone(json);
			},
			error : function(result) {
				alertMsg.error('系统异常');
			}
	});
	 
}

</script>

<script type="text/javascript">
function AppendItem(allMenu, menu, isAll) {
	for (j=0; j<document.getElementById(allMenu).length; j++){
		if (isAll == true || document.getElementById(allMenu).options[j].selected){
			//GET VALUE
			document.getElementById(allMenu).options[j].selected = false;
			//GET LENGTH
			DesLen = document.getElementById(menu).length;
			// NEW OPTION
			document.getElementById(menu).options[DesLen] = new Option(LTrim(document.getElementById(allMenu).options[j].text), document.getElementById(allMenu).options[j].value);
			document.getElementById(allMenu).remove(j);
			j--;
		}
	}
}
function LTrim(str) {
	var whitespace = new String("　 \t\n\r");
	var s = new String(str);
	if (whitespace.indexOf(s.charAt(0)) != -1) {
		var j = 0, i = s.length;
		while (j < i && whitespace.indexOf(s.charAt(j)) != -1) {
			j++;
		}
		s = s.substring(j, i);
	}
	return s;
}
</script>

<script type="text/javascript">
var inputId = "modulepId";//输入框的id
var inputAId = "menuBtn";//点击按钮的id
var treeDivId = "menuContent";//树所在的div的id
var treeId = "menuTree1";//树id
var hidden = "hiddenpId";
var actionA = "actionA";
var setting = {
		view : {
			dblClickExpand : false
		},
		data : {
			simpleData : {
				enable : true
			},
		},
		callback : {
			onClick : onClick2
		}
	};
	function onClick2(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj(treeId);
		nodes = zTree.getSelectedNodes(),
		v = nodes[0].name;
		n = nodes[0].id;
		var branchObj = $("#" + inputId,$parentCur);
		var hiddenpId = $("#" + hidden,$parentCur);
		branchObj.attr("value", v);//给父菜单的文本框赋值当前选中的菜单的name
		hiddenpId.attr("value", n);//给父菜单的隐藏项赋值当前选中的菜单的id
		//隐藏tree
		hideMenu();
	}
	function showMenu() {
		//级联下拉绑定修改 判断该页面是navtab页或者是dailog页 HuoYanpeng
		var $parentCur = navTab.getCurrentPanel() == null ?  $.pdialog.getCurrent() : navTab.getCurrentPanel();
		//获取树对象
		var branchObj = $("#" + inputId,$parentCur);
		//获取tree的相对位置
		var branchPosition = $("#" + inputId,$parentCur).offset();
		//调用ajax 初始化树
	 	$.ajax({type:"POST",url:'<%=request.getContextPath()%>/sys/getMenuList_menuAction.action',
			dataType:"json",success:function(data){
				$("#jspname").val('<%=request.getRequestURI()%>');
					var zNodes = data.menuList;
				$.fn.zTree.init($("#" + treeId,$parentCur),setting,zNodes);
				var zTree = $.fn.zTree.getZTreeObj(treeId);
				zTree.expandAll(true);
			}
		}); 
		//显示树
		$("#" + treeDivId,$parentCur).css({
			left : branchPosition.left - $("#accordionPos").outerWidth() - $("#splitBar").outerWidth() - 8 + "px",
			top : branchPosition.top + branchObj.outerHeight() - $("#layout").outerHeight() - $("#taskbar").outerHeight() - 27 - 1  + "px"
			
		}).slideDown("fast");
		//解绑事件
		$($parentCur).bind("mousedown", onBodyDown);
	}
	var $parentCur = navTab.getCurrentPanel() == null ?  $.pdialog.getCurrent() : navTab.getCurrentPanel();
	var fval = null;
	function hideMenu() {
		//级联下拉绑定修改 判断该页面是navtab页或者是dailog页 HuoYanpeng
		var $parentCur = navTab.getCurrentPanel() == null ?  $.pdialog.getCurrent() : navTab.getCurrentPanel();
		$("#" + treeDivId,$parentCur).fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		//级联下拉绑定修改 判断该页面是navtab页或者是dailog页 HuoYanpeng
		var $parentCur = navTab.getCurrentPanel() == null ?  $.pdialog.getCurrent() : navTab.getCurrentPanel();
		if(event.target.id == '' ||!(event.target.id == inputAId || event.target.id == treeDivId  
				||$(event.target).parents("#" + treeDivId,$parentCur).length > 0)){
			hideMenu();
		}
	}
	
</script>


