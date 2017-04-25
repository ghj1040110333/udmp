var subParamCnt = $("#subParamCnt").val();
//子参数列表模版
var subParamHtml = ""
		+ "<tr height=\"25\">"
		+ "<td align=\"center\">"
		+ "<input type=\"button\" value=\"＋\" onclick=\"addSubParam(this)\" />"
		+ "<input type=\"button\" value=\"－\" onclick=\"delSubParam(this)\" />"
		+ "</td>"
		+ "<td align=\"center\">"
		+ "<input type=\"text\" name=\"paramItemName\" value=\"\" class=\"required\"/>"
		+ "</td>"
		+ "<td align=\"center\">"
		+ "<input type=\"text\" name=\"paramItemValue\" value=\"\" class=\"required\"/>"
		+ "</td>"
		+ "<td align=\"center\">"
		+ "<input type=\"button\" value=\"↑\" onclick=\"upParamData(this)\" />"
		+ "<input type=\"button\" value=\"↓\" onclick=\"downParamData(this)\" />"
		+ "</td>" + "</tr>";

function addSubParam(buttonObj) {
	var trObj = $("#subParamList tr:eq(0)");
	if (buttonObj != null) {
		trObj = $(buttonObj).parent().parent();
	}
	$(subParamHtml).insertAfter($(trObj));
	subParamCnt++;
}

function delSubParam(buttonObj) {
	var trObj = $(buttonObj).parent().parent();
	if (subParamCnt > 1) {
		$(trObj).remove();
		subParamCnt--;
	} else {
		$("input[name=paramItemName]").val(" ");
		$("input[name=paramItemValue]").val(" ");
	}
}

function downParamData(buttonObj) {
	var trObj = $(buttonObj).parent().parent();
	var dataIndex = $(trObj).index();
	if (dataIndex < paramsCount) {
		exchangeParamData($(trObj), $(trObj).next(), "down");
	}
}

/**
 * 排序向上按钮
 */
function upParamData(buttonObj) {
	var trObj = $(buttonObj).parent().parent();
	var dataIndex = $(trObj).index();
	if (dataIndex > 1) {
		exchangeParamData($(trObj), $(trObj).prev(), "up");
	}
}

/**
 * 排序向下按钮
 */
function downParamData(buttonObj) {
	var trObj = $(buttonObj).parent().parent();
	var dataIndex = $(trObj).index();
	if (dataIndex < subParamCnt) {
		exchangeParamData($(trObj), $(trObj).next(), "down");
	}
}

/**
 * 交换两个对象，在此页面中用于交换两个tr对象。
 * 做了三件事情，1.临时存储第一个对象;2.删除第一个对象；3.根据target将第一个对象放在第二个对象前面/后面。
 * @method exchangeParamData(o1, o2, target)
 * @param {Object} o1 第一个对象，{Object} o2 第二个对象，{String} target 对象交换的方向（o1在o2上/下）
 * @return {void} 
 */
function exchangeParamData(o1, o2, target) {
	var tempObj = $(o1);
	$(o1).remove();
	if (target == "up") {
		$(o2).before(tempObj);
	} else if (target == "down") {
		$(o2).after(tempObj);
	}
}

function isSingleValue(obj) {
	var str = obj.value;
	if (str == 1) {
		$("#paramValue").removeAttr("disabled"); // 禁用
		$("#subParam").hide(); // 隐藏div
		$("#subParamList tr:gt(0)").remove();
	} else {
		$("#paramValue").val(""); // 参数值为空
		$("#paramValue").attr("disabled", "true"); // 禁用
		$("#subParam").show();
		addSubParam(null);
	}
}

function inputInit() {
	var objectArray = $("input[name=paramItemName]");
	subParamCnt = objectArray.length;
	if (subParamCnt == 0) {
		addSubParam(null);
	}
}