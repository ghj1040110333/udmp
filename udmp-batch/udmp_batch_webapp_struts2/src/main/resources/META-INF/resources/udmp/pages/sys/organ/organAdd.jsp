<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>	
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="menuContent" class="menuContent"
	style="display: none; position: absolute; z-index: 1002;">
	<ul id="branchTree" class="ztree"
		style="margin-top: 0; width: 300px; height: 300px"></ul>
</div>
<div class="pageContent">
	<form method="post" id="userForm"
		action="sys/insertOrgan_organAction.action?menuId=${menuId}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div id="organTable" class="pageFormContent nowrap" layoutH="60">
			<table width="100%" cellpadding="0" cellspacing="10" border="0">
				<tr>
					<td width="17%">
						<div class="label">机构名称:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organName"
						class="required" maxlength="50" /></td>

					<td width="17%">
						<div class="label">机构简称:</div>
					</td>
					<td width="33%"><input type="text"
						name="organVO.organShortname" maxlength="20" /></td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label">选择上级机构:</div>
					</td>
					<td width="33%"><input id="upOrganId" readonly /> &nbsp; <a
						id='organBtn' class="btnLook" href='#'
						onclick='showMenu(); return false;'></a> <s:hidden
							id="hiddenOrganGrade" name="organVO.organGrade" /> <s:hidden
							id="hiddenUpOrganLvlCode" name="organVO.uporganLvlCode" /> <s:hidden
							id="hiddenUpOrganId" name="organVO.uporganId" /> <s:hidden
							id="hiddenUpOrganCode" name="organVO.uporganCode" /></td>
					<td width="17%">
						<div class="label">机构编码:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organCode"
						class="required" maxlength="20" /></td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label">机构类型:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organType"
						maxlength="20" /></td>
					<td width="17%">
						<div class="label">机构状态:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organState"
						maxlength="20" /></td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label">地区id:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.regionId"
						maxlength="20" /></td>
					<td width="17%">
						<div class="label">机构地区类型:</div>
					</td>
					<td width="33%"><input type="text"
						name="organVO.organAreaType" maxlength="20" /></td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label">机构地址:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organAddress"
						maxlength="20" /></td>

					<td width="17%">
						<div class="label">机构邮编:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organZipcode"
						maxlength="20" /></td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label">机构所在省:</div>
					</td>
					<td width="33%"><input type="text"
						name="organVO.organProvince" maxlength="20" /></td>
					<td width="17%">
						<div class="label">机构所在城市:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organCity"
						maxlength="20" /></td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label">开业批复日期:</div>
					</td>
					<td width="33%"><input type="expandDateYMDRO"
						name="organVO.setupreplydate"
						value="<s:date format="yyyy-MM-dd" name="organVO.setupreplydate"/>"
						maxlength="20" /></td>
					<td width="17%">
						<div class="label">撤销批复日期:</div>
					</td>
					<td width="33%"><input type="expandDateYMDRO"
						name="organVO.backoutreplydate"
						value="<s:date format="yyyy-MM-dd" name="organVO.backoutreplydate"/>"
						maxlength="20" /></td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label">成立时间:</div>
					</td>
					<td width="33%"><input type="expandDateYMDRO"
						name="organVO.foundDate"
						value="<s:date format="yyyy-MM-dd" name="organVO.foundDate"/>"
						maxlength="20" /></td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label">网址:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.webAddress"
						maxlength="20" /></td>
					<td width="17%">
						<div class="label">邮箱:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.email"
						maxlength="20" /></td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label">机构电话:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organPhone"
						maxlength="20" /></td>

					<td width="17%">
						<div class="label">行政区划代码:</div>
					</td>
					<td width="33%"><input type="text"
						name="organVO.regionalismCode" maxlength="20" /></td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label">税务机构代码:</div>
					</td>
					<td width="33%"><input type="text"
						name="organVO.taxAuthorityCode" maxlength="20" /></td>
					<td width="17%">
						<div class="label">外部机构代码:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.outorganCode"
						maxlength="20" /></td>

				</tr>

				<tr>
					<td width="17%">
						<div class="label">是否开具发票:</div>
					</td>
					<td width="33%"><select id="isInvoice" class="combox"
						name="organVO.isInvoiceOrgan">
							<option value="true">是</option>
							<option value="false">否</option>
					</select></td>
					<td width="17%">
						<div class="label">发票描述:</div>
					</td>
					<td width="33%"><input type="text"
						name="organVO.invoiceOrganDesc" maxlength="20" /></td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label">机构传真:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organTax"
						maxlength="20" /></td>

					<td width="17%">
						<div class="label">机构负责人:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.satrapName"
						maxlength="20" /></td>
				</tr>

			</table>
		</div>
		<div class="formBar">
			<ul style="margin-top: -3px;">
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit" id="addOrgBtn" onclick="checkInfo();">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" id="cancelBtn" onclick="$.pdialog.closeCurrent();">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>

<<script type="text/javascript">
function checkInfo(){
	if($("#hiddenOrganGrade").val() == null || trim($("#hiddenOrganGrade").val()) == ""){
		$("#hiddenOrganGrade").attr("value","01");
	}
}
</script>
<script type="text/javascript">
var inputId = "upOrganId";//输入框的id
var inputAId = "organBtn";//点击按钮的id
var treeDivId = "menuContent";//树所在的div的id
var treeId = "branchTree";//树id
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
			onClick : onClick
		}
	};
function onClick(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj(treeId);
	nodes = zTree.getSelectedNodes(),
	n = nodes[0].id;
	$("#" + inputId,$parentCur).attr("value", nodes[0].name);
	$("#hiddenOrganGrade",$parentCur).attr("value",'0'+(new Number(nodes[0].grade)+1));
	$("#hiddenUpOrganLvlCode",$parentCur).attr("value",nodes[0].upLvlCode);
	$("#hiddenUpOrganId",$parentCur).attr("value",nodes[0].id);
	$("#hiddenUpOrganCode",$parentCur).attr("value",nodes[0].code);
	//如果没有选择上级机构则机构等级为01
	//隐藏tree
	hideMenu();
}

function showMenu() {
	var $parentCur = $.pdialog.getCurrent();
	//获取第一个paneBar的位置
	//获取action属性
	//获取树对象
	var branchObj = $("#" + inputId,$parentCur);
	//获取input框的值
	var value = $("#" + inputId,$parentCur).val() == null?"":$("#" + inputId,$parentCur).val().trim();
	//获取tree的相对位置
	var branchPosition = $("#" + inputId,$parentCur).position();
	//调用ajax 初始化树
 	$.ajax({type:"POST",url:"getOrganList_organAction.action",data:{branchLike:value},
		dataType:"json",success:function(data){
			var zNodes = data.menuList;
			$.fn.zTree.init($("#" + treeId,$parentCur),setting,zNodes);
		}
	}); 
	//显示树
	$("#" + treeDivId,$parentCur).css({
		left : branchPosition.left + 7 + "px",
		top : branchPosition.top + branchObj.outerHeight() + branchObj.outerHeight() + 12 + "px"
	}).slideDown("fast");
	//解绑事件
	$($parentCur).bind("mousedown", onBodyDown);
}

var $parentCur = $.pdialog.getCurrent();
var fval = null;
function hideMenu() {
	var $parentCur = $.pdialog.getCurrent();
	$("#" + treeDivId,$parentCur).fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	var $parentCur = $.pdialog.getCurrent();
	if(event.target.id == '' ||!(event.target.id == inputAId || event.target.id == treeDivId  
			||$(event.target).parents("#" + treeDivId,$parentCur).length > 0)){
		hideMenu();
	}
}
	
</script>
