/**
 * 交易请求前，统一js代码出口 代码方法预留，暂不处理
 */
function commonBeforeSubmit() {
}
/**
 * 公共交易请求报文头赋值处理
 * @para currentBtnRef 当前按钮对象this
 * @para parent 父对象
 * 对dialog中传递当前dialog（$.pdialog.getCurrent()）
 * 对form中传递当前按钮所在form（$(currentBtnRef).parents("form")）
 */
function setHeaderVal(currentBtnRef, parent) {
	// 通过读取当前按钮的自定义属性，对引入header.jsp隐藏域进行赋值
	// 1. name="nciHeadVo.UserCode" id="UserCode"
	$("#UserCode", parent).attr('value', currentBtnRef.UserCode);
	// 2. name="nciHeadVo.RoleId" id="RoleId"
	$("#RoleId", parent).attr('value', currentBtnRef.RoleId);
	// 3. name="nciHeadVo.ManageCode" id="ManageCode"
	$("#ManageCode", parent).attr('value', currentBtnRef.ManageCode);
	// 4. name="nciHeadVo.SysCode" id="SysCode"
	$("#SysCode", parent).attr('value', currentBtnRef.SysCode);
	// 5. name="nciHeadVo.MouldCode" id="MouldCode"
	$("#MouldCode", parent).attr('value', currentBtnRef.MouldCode);
	// 6. name="nciHeadVo.ButtonCode" id="ButtonCode"
	$("#ButtonCode", parent).attr('value', currentBtnRef.ButtonCode);
	// 7. name="nciHeadVo.TranCode" id="TranCode"
	$("#TranCode", parent).attr('value', currentBtnRef.TranCode);
	// 8. name="nciHeadVo.TransSerialno" id="TransSerialno"
	$("#TransSerialno", parent).attr('value', currentBtnRef.TransSerialno);
	// 9. name="nciHeadVo.UserName" id="UserName"
	$("#UserName", parent).attr('value', currentBtnRef.UserName);
	// 10. name="nciHeadVo.ManageCom" id="ManageCom"
	$("#ManageCom", parent).attr('value', currentBtnRef.ManageCom);
	// 11. name="nciHeadVo.RecordLog" id="RecordLog"
	$("#RecordLog", parent).attr('value', currentBtnRef.RecordLog);
	// 12. name="nciHeadVo.LogDetail" id="LogDetail"
	$("#LogDetail", parent).attr('value', currentBtnRef.LogDetail);
	// 13. name="nciHeadVo.IpStr" id="IpStr"
	$("#IpStr", parent).attr('value', currentBtnRef.IpStr);
	// 14. name="nciHeadVo.PageId" id="PageId"
	$("#PageId", parent).attr('value', currentBtnRef.PageId);
	// 15. name="nciHeadVo.PageId4Help" id="PageId4Help"
	$("#PageId4Help", parent).attr('value', currentBtnRef.PageId4Help);
	// 16. name="nciHeadVo.InputId" id="InputId"
	$("#InputId", parent).attr('value', currentBtnRef.InputId);
	// 17. name="nciHeadVo.KeyCode" id="KeyCode"
	$("#KeyCode", parent).attr('value', currentBtnRef.KeyCode);
	// 18. name="nciHeadVo.ReservedCode" id="ReservedCode"
	$("#ReservedCode", parent).attr('value', currentBtnRef.ReservedCode);
	// 19. name="nciHeadVo.ReservedDesc" id="ReservedDesc"
	$("#ReservedDesc", parent).attr('value', currentBtnRef.ReservedDesc);
}
/**
 * 通过表单提交的公共交易请求报文头赋值处理
 * 
 * @para currentBtnRef 当前按钮对象this
 */
function udmpSetSubmitBtnHeader(currentBtnRef) {
	// 通过当前事件按钮向祖先节点找到所在form对象
	var frm = $(currentBtnRef).parents("form");
	// 通过读取当前按钮的自定义属性，对引入header.jsp隐藏域进行赋值
	setHeaderVal(currentBtnRef, frm);
	// 赋值完毕，允许提交表单
	// 在form的onsubmit事件上添加公共方法 预置 引用公共函数
	frm.attr("onsubmit", 'commonBeforeSubmit();' + frm.attr("onsubmit"));
}
/**
 * dialog对话框中通过表单提交的公共交易请求报文头赋值处理
 * 
 * @para currentBtnRef 当前按钮对象this
 */
function udmpSetDialogSubmitBtnHeader(currentBtnRef) {
	// 对于打开的dialog，需要先定位到当前dialog
	var dialog = $.pdialog.getCurrent();
	// 通过读取当前按钮的自定义属性，对引入header.jsp隐藏域进行赋值
	setHeaderVal(currentBtnRef, dialog);
	// 赋值完毕，允许提交表单
	// 通过当前事件按钮向祖先节点找到所在form对象
	var frm = $(currentBtnRef).parents("form");
	// 在form的onsubmit事件上添加公共方法 预置 引用公共函数
	frm.attr("onsubmit", 'commonBeforeSubmit();' + frm.attr("onsubmit"));
}

/**
 * 通过超链接提交表单的公共交易请求报文头赋值处理
 * 
 * @para currentALinkRef 当前超链接对象this
 */
function udmpSetALinkHeader(currentALinkRef) {
	// 如果之前动态修改过href，需要从后补充起始位置截取到原始请求信息
	var hrefVal = currentALinkRef.href;
	var headerIndex = hrefVal.indexOf("&header=header");
	if (headerIndex > 0) {
		currentALinkRef.href = hrefVal.substring(0, headerIndex);
	}

	// 预置参数起始位置，供下次调用前截取，避免重复累加赋值
	currentALinkRef.href = currentALinkRef.href + "&header=header";
	// 通过读取当前A-Link的自定义属性，对引入request.parameter进行赋值
	// 1. name="nciHeadVo.UserCode" id="UserCode"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.UserCode="
			+ currentALinkRef.UserCode;
	// 2. name="nciHeadVo.RoleId" id="RoleId"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.RoleId="
			+ currentALinkRef.RoleId;
	// 3. name="nciHeadVo.ManageCode" id="ManageCode"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.ManageCode="
			+ currentALinkRef.ManageCode;
	// 4. name="nciHeadVo.SysCode" id="SysCode"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.SysCode="
			+ currentALinkRef.SysCode;
	// 5. name="nciHeadVo.MouldCode" id="MouldCode"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.MouldCode="
			+ currentALinkRef.MouldCode;
	// 6. name="nciHeadVo.ButtonCode" id="ButtonCode"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.ButtonCode="
			+ currentALinkRef.ButtonCode;
	// 7. name="nciHeadVo.TranCode" id="TranCode"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.TranCode="
			+ currentALinkRef.TranCode;
	// 8. name="nciHeadVo.TransSerialno" id="TransSerialno"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.TransSerialno="
			+ currentALinkRef.TransSerialno;
	// 9. name="nciHeadVo.UserName" id="UserName"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.UserName="
			+ currentALinkRef.UserName;
	// 10. name="nciHeadVo.ManageCom" id="ManageCom"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.ManageCom="
			+ currentALinkRef.ManageCom;
	// 11. name="nciHeadVo.RecordLog" id="RecordLog"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.RecordLog="
			+ currentALinkRef.RecordLog;
	// 12. name="nciHeadVo.LogDetail" id="LogDetail"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.LogDetail="
			+ currentALinkRef.LogDetail;
	// 13. name="nciHeadVo.IpStr" id="IpStr"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.IpStr="
			+ currentALinkRef.IpStr;
	// 14. name="nciHeadVo.PageId" id="PageId"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.PageId="
			+ currentALinkRef.PageId;
	// 15. name="nciHeadVo.PageId4Help" id="PageId4Help"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.PageId4Help="
			+ currentALinkRef.PageId4Help;
	// 16. name="nciHeadVo.InputId" id="InputId"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.InputId="
			+ currentALinkRef.InputId;
	// 17. name="nciHeadVo.KeyCode" id="KeyCode"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.KeyCode="
			+ currentALinkRef.KeyCode;
	// 18. name="nciHeadVo.ReservedCode" id="ReservedCode"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.ReservedCode="
			+ currentALinkRef.ReservedCode;
	// 19. name="nciHeadVo.ReservedDesc" id="ReservedDesc"
	currentALinkRef.href = currentALinkRef.href + "&nciHeadVo.ReservedDesc="
			+ currentALinkRef.ReservedDesc;

	currentALinkRef.href = encodeURI(currentALinkRef.href);
	// 预置 引用公共函数
	commonBeforeSubmit();
}