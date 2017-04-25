<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@include file="/udmpCommon.jsp"%>	
<%@ taglib uri="/struts-tags" prefix="s"%>
<div id="menuContent" class="menuContent"
	style="display: none; position: absolute; z-index: 1002;">
	<ul id="branchTree" class="ztree" style="margin-top: 0; width: 300px; height: 300px"></ul>
</div>
<div class="pageContent">
	<form method="post" id="userForm" action="sys/updateOrg_organAction.action?menuId=${menuId}" 
		class="pageForm required-validate" 
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="60">
			<table width="100%" cellpadding="0" cellspacing="10" border="0">
				<tr>
					<tr>
					<td width="17%">
						<div class="label">机构名称:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organName"
						class="required" maxlength="20" value="${organVO.organName}" /></td>
						
					<td width="17%">
						<div class="label">机构简称:</div>
					</td>
					<td width="33%"><input type="text"	name="organVO.organShortname" 
						value="${organVO.organShortname}" maxlength="20" /></td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label">选择上级机构:</div>
					</td>
					<td width="33%">
						<input id="upOrganId" value="${organVO.uporganName}" readonly/> &nbsp; 
							<a id='organBtn' class="btnLook" href='#' onclick='showMenu(); return false;'></a>
						<s:hidden id="hiddenOrganId" name="organVO.organId"/>
						<s:hidden id="hiddenOrganGrade" name="organVO.organGrade"/>
						<s:hidden id="hiddenUpOrganLvlCode" name="organVO.uporganLvlCode"/>
						<s:hidden id="hiddenUpOrganId" name="organVO.uporganId"/>
						<s:hidden id="hiddenUpOrganCode" name="organVO.uporganCode"/>
					</td>	
					<td width="17%">
						<div class="label">机构编码:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organCode"
						value="${organVO.organCode}" class="required" maxlength="20" /></td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label">机构类型:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organType"
						 value="${organVO.organType}" maxlength="20" /></td>
					<td width="17%">
						<div class="label">机构状态:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organState"
						value="${organVO.organState}" maxlength="20" /></td>
					
				</tr>
				<tr>
					<td width="17%">
						<div class="label">地区id:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.regionId"
						 value="${organVO.regionId}" maxlength="20" /></td>
					<td width="17%">
						<div class="label">机构地区类型:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organAreaType"
						value="${organVO.organAreaType}" maxlength="20" /></td>
				</tr>
				<tr>	
					<td width="17%">
						<div class="label">机构地址:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organAddress"
						 value="${organVO.organAddress}" maxlength="50" /></td>
				
					<td width="17%">
						<div class="label">机构邮编:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organZipcode"
						value="${organVO.organZipcode}" maxlength="20" /></td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label">机构所在省:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organProvince"
						 value="${organVO.organProvince}" maxlength="20" /></td>
					<td width="17%">
						<div class="label">机构所在城市:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organCity"
						value="${organVO.organCity}" maxlength="20" /></td>
				</tr>
				<tr>	 
					<td width="17%">
						<div class="label">开业批复日期:</div>
					</td>
					<td width="33%"><input type="expandDateYMDRO" name="organVO.setupreplydate"
						value="<s:date format="yyyy-MM-dd" name="organVO.setupreplydate"/>" maxlength="20" />
						</td>
					<td width="17%">
						<div class="label">撤销批复日期:</div>
					</td>
					<td width="33%"><input type="expandDateYMDRO" name="organVO.backoutreplydate" 
						value="<s:date format="yyyy-MM-dd" name="organVO.backoutreplydate"/>" maxlength="20" />
					</td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label">成立时间:</div>
					</td>
					<td width="33%"><input type="expandDateYMDRO" name="organVO.foundDate"
						value="<s:date format="yyyy-MM-dd" name="organVO.foundDate"/>" maxlength="20" /></td>
					
				</tr>
				<tr>
					<td width="17%">
						<div class="label">网址:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.webAddress"
						value="${organVO.webAddress}" maxlength="20" /></td>
					<td width="17%">
						<div class="label">邮箱:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.email"
						value="${organVO.email}" maxlength="20" /></td>
				</tr>
				<tr>	 
					<td width="17%">
						<div class="label">机构电话:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organPhone"
						value="${organVO.organPhone}" maxlength="20" /></td>
						 
					<td width="17%">
						<div class="label">行政区划代码:</div>
					</td>
					<td width="33%"><input type="text"	name="organVO.regionalismCode" 
						value="${organVO.regionalismCode}" maxlength="20" /></td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label">税务机构代码:</div>
					</td>
					<td width="33%"><input type="text"	name="organVO.taxAuthorityCode" 
						value="${organVO.taxAuthorityCode}" maxlength="20" /></td>
					<td width="17%">
						<div class="label">外部机构代码:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.outorganCode"
						value="${organVO.outorganCode}" maxlength="20" /></td>
					
				</tr>
				
				<tr>
					<td width="17%">
						<div class="label">是否开具发票:</div>
					</td>
					<td width="33%">
						<input style="display: none" id="isInvoiceVal" value="${organVO.isInvoiceOrgan}"/>
						<select id="isInvoice" class="combox" name="organVO.isInvoiceOrgan">
							<option value="true" >是</option>
							<option value="false">否</option>
						</select>
					</td>
					<td width="17%">
						<div class="label">发票描述:</div>
					</td>
					<td width="33%"><input type="text"	name="organVO.invoiceOrganDesc" 
						value="${organVO.invoiceOrganDesc}" maxlength="20" />
					</td>
				</tr>
				<tr>
					<td width="17%">
						<div class="label">机构传真:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.organTax"
						 value="${organVO.organTax}" maxlength="20" /></td>
				
					<td width="17%">
						<div class="label">机构负责人:</div>
					</td>
					<td width="33%"><input type="text" name="organVO.satrapName"
						value="${organVO.satrapName}"  maxlength="20" /></td>
				</tr>
				
			</table>
		</div>
		<div class="formBar">
			<ul style="margin-top: -3px;">
				<li>
					<div class="buttonActive" >
						<div class="buttonContent">
							<button type="submit" id="saveOrgBtn" >保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive" >
						<div class="buttonContent">
							<button type="button" id="cancelSaveBtn" onclick="$.pdialog.closeCurrent();">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>

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
		nodes = zTree.getSelectedNodes();
		//alert('上级机构id'+nodes[0].id+'/名称:'+nodes[0].name+'/等级'+nodes[0].upLvlCode+'代码'+nodes[0].code);
		//判断上级机构等级是否小于当前机构等级，是则弹出警告
		if(nodes[0].pId == $("#hiddenOrganId").val()){
			alertMsg.warn('不能选择当前机构的下级机构作为上级机构');
		} else if (nodes[0].id==$("#hiddenOrganId").val()){
			alertMsg.warn('不能选择当前机构作为上级机构');
		} else{
			$("#" + inputId,$parentCur).attr("value", nodes[0].name);
			$("#hiddenOrganGrade",$parentCur).attr("value",'0'+(new Number(nodes[0].grade)+1));
			$("#hiddenUpOrganLvlCode",$parentCur).attr("value",nodes[0].upLvlCode);
			$("#hiddenUpOrganCode",$parentCur).attr("value",nodes[0].code);
			$("#hiddenUpOrganId",$parentCur).attr("value",nodes[0].id);
		}
		//隐藏tree
		hideMenu();
	}
	function showMenu() {
		var $parentCur = $.pdialog.getCurrent();
		//获取树对象
		var branchObj = $("#" + inputId,$parentCur);
		//获取tree的相对位置
		var branchPosition = $("#" + inputId,$parentCur).position();
		//调用ajax 初始化树
	 	$.ajax({type:"POST",url:"sys/getOrganList_organAction.action",
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
	};
	function onBodyDown(event) {
		var $parentCur = $.pdialog.getCurrent();
		if(event.target.id == '' ||!(event.target.id == inputAId || event.target.id == treeDivId  
				||$(event.target).parents("#" + treeDivId,$parentCur).length > 0)){
			hideMenu();
		}
	};
	
</script>