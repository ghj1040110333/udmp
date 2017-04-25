// 2015年4月27日 16:26:39	 新建工具类  避免全局变量污染js全局变量空间
// 2015年9月7日 17:21:58 将全局变量移动到闭包中，后续将所有全局变量移动进来。
// 2015年9月8日 11:40:56 将dealSwitch 移动到闭包的变量中
(function(window) {
	var udmpUtil = {
		resetAll : function(btn) {
			if ($) {
				var $form = $(btn).parents("form:first");
				// 清空input中的内容
				$form.find("input").each(function() {
					$(this).val('');
				});
				// 清空下拉选中的内容
				$form.find("div.combox").find("select").each(function() {
					selectMyComBox($(this).attr("id"), "");
				});
				// 清空复选框的选中
				$form.find(":checkbox").each(function() {
					$(this).removeAttr("checked");
				});
			}
		},
		// 获取业务时间字符串
		getWorkDateString : function() {
			var bizTime = getCookie('bizTime');
			var date = new Date();
			var bizNow = '';
			if (bizTime) {
				bizNow = bizTime.substring(0, 4) + "-"
						+ bizTime.substring(4, 6) + "-"
						+ bizTime.substring(6, 8) + " "
						+ addZero(date.getHours()) + ":"
						+ addZero(date.getMinutes()) + ":"
						+ addZero(date.getSeconds());
			} else {
				bizNow = date.getFullYear() + "-" + addZero(date.getMonth())
						+ "-" + addZero(date.getDay()) + " "
						+ addZero(date.getHours()) + ":"
						+ addZero(date.getMinutes()) + ":"
						+ addZero(date.getSeconds());
			}
			function addZero(da) {
				var addz = [ '01', '02', '03', '04', '05', '06', '07', '08',
						'09' ];
				if (da < 10) {
					return addz[da - 1];
				} else {
					return da
				}
			}
			return bizNow;
		},
		// 获取业务时间
		getWorkDate : function() {
			var bizTime = getCookie('bizTime');
			var date = new Date();
			if (bizTime) {
				var bizNow = new Date(bizTime.substring(0, 4), (bizTime
						.substring(4, 6) - 1), bizTime.substring(6, 8), date
						.getHours(), date.getMinutes(), date.getSeconds());
				return bizNow;
			} else {
				return date;
			}

		},
		// 上传时获取文件在本地路径
		getPath : function(obj) {
			if (obj) {
				if (window.navigator.userAgent.indexOf("MSIE") >= 1) {
					obj.select();
					obj.blur();
					return document.selection.createRange().text;
				} else if (window.navigator.userAgent.indexOf("Firefox") >= 1) {
					if (obj.files) {

						return obj.files.item(0).getAsDataURL();
					}
					return obj.value;
				}
				return obj.value;
			}
		},
		// *******交易管理开关 Add HYP
		dealSwitch : false
	};
	window.udmpUtil = udmpUtil;
})(window);
// 判断是否为null或空   
function isNulOrEmpty(prop) {
	if (prop == null || trim(prop) == "")
		return true;
	else
		return false;
}
// 判断是否为初始化
function isNotDefined(prop) {
	if (prop == undefined || trim(prop) == "undefined")
		return true;
	else
		return false;
}

// 删除输入框前后空格
function trim(str) {
	str = str + "";
	return str.replace(/(^\s*)|(\s*$)/g, "");
}

function returnFalse() {
	return false;
}

function json2str(o) {
	var arr = [];
	var fmt = function(s) {
		if (typeof s == 'object' && s != null)
			return json2str(s);
		return /^(string|number)$/.test(typeof s) ? "'" + s + "'" : s;
	}
	for ( var i in o)
		arr.push("'" + i + "':" + fmt(o[i]));
	return '{' + arr.join(',') + '}';
}

// 存入cookie
function setCookie(key, value) {
	var cookieString = key + "=" + value;
	document.cookie = cookieString;
}
// 取出cookie
function getCookie(key) {
	var strCookie = document.cookie;
	var arrCookie = strCookie.split(";");
	for (var i = 0; i < arrCookie.length; i++) {
		var arr = arrCookie[i].split("=");
		if (arr[0].trim() == key)
			return arr[1];
	}
	return "";
}

function formatText(value, type) {
	if (!isNulOrEmpty(value) && !isNulOrEmpty(type)) {
		if (type == 'clearSpace') {
			return value.replace(/\s+/g, "");
		}
	} else {
		return value;
	}
}
// 校验电话号码
function checkPhone(data) {
	var phoneRegex = /^(([0\+]\d{2,3}-)?(0\d{2,3})-)?(\d{7,8})(-(\d{3,}))?$/;
	if (!isNulOrEmpty(data) && !isNulOrEmpty(data)) {
		return phoneRegex.test(data);
	} else {
		return false;
	}
}
// 校验手机号码
function checkMobile(data) {
	var mobile = /^\d{11}$/;
	if (!isNulOrEmpty(data) && !isNulOrEmpty(data)) {
		return mobile.test(data);
	} else {
		return false;
	}
}
// 校验电子邮箱
function checkEmail(data) {
	var email = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;

	if (!isNulOrEmpty(data) && !isNulOrEmpty(data)) {
		return email.test(data);
	} else {
		return false;
	}
}
// 检验是否是邮编
function checkPostCode(data) {
	var postRegex = /^[1-9]\d{5}$/;

	if (!isNulOrEmpty(data) && !isNulOrEmpty(data)) {
		return postRegex.test(data);
	} else {
		return false;
	}
}
// 检验身份证号码
var idCardNoUtil = {
	/* 省,直辖市代码表 */
	provinceAndCitys : {
		11 : "北京",
		12 : "天津",
		13 : "河北",
		14 : "山西",
		15 : "内蒙古",
		21 : "辽宁",
		22 : "吉林",
		23 : "黑龙江",
		31 : "上海",
		32 : "江苏",
		33 : "浙江",
		34 : "安徽",
		35 : "福建",
		36 : "江西",
		37 : "山东",
		41 : "河南",
		42 : "湖北",
		43 : "湖南",
		44 : "广东",
		45 : "广西",
		46 : "海南",
		50 : "重庆",
		51 : "四川",
		52 : "贵州",
		53 : "云南",
		54 : "西藏",
		61 : "陕西",
		62 : "甘肃",
		63 : "青海",
		64 : "宁夏",
		65 : "新疆",
		71 : "台湾",
		81 : "香港",
		82 : "澳门",
		91 : "国外"
	},

	/* 每位加权因子 */
	powers : [ "7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9",
			"10", "5", "8", "4", "2" ],

	/* 第18位校检码 */
	parityBit : [ "1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2" ],

	/* 性别 */
	genders : {
		male : "男",
		female : "女"
	},

	/* 校验地址码 */
	checkAddressCode : function(addressCode) {
		var check = /^[1-9]\d{5}$/.test(addressCode);
		if (!check)
			return false;
		if (idCardNoUtil.provinceAndCitys[parseInt(addressCode.substring(0, 2))]) {
			return true;
		} else {
			return false;
		}
	},

	/* 校验日期码 */
	checkBirthDayCode : function(birDayCode) {
		var check = /^[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))$/
				.test(birDayCode);
		if (!check)
			return false;
		var yyyy = parseInt(birDayCode.substring(0, 4), 10);
		var mm = parseInt(birDayCode.substring(4, 6), 10);
		var dd = parseInt(birDayCode.substring(6), 10);
		var xdata = new Date(yyyy, mm - 1, dd);
		if (xdata > new Date()) {
			return false;// 生日不能大于当前日期
		} else if ((xdata.getFullYear() == yyyy)
				&& (xdata.getMonth() == mm - 1) && (xdata.getDate() == dd)) {
			return true;
		} else {
			return false;
		}
	},

	/* 计算校检码 */
	getParityBit : function(idCardNo) {
		var id17 = idCardNo.substring(0, 17);
		/* 加权 */
		var power = 0;
		for (var i = 0; i < 17; i++) {
			power += parseInt(id17.charAt(i), 10)
					* parseInt(idCardNoUtil.powers[i]);
		}
		/* 取模 */
		var mod = power % 11;
		return idCardNoUtil.parityBit[mod];
	},

	/* 验证校检码 */
	checkParityBit : function(idCardNo) {
		var parityBit = idCardNo.charAt(17).toUpperCase();
		if (idCardNoUtil.getParityBit(idCardNo) == parityBit) {
			return true;
		} else {
			return false;
		}
	},

	/* 校验15位或18位的身份证号码 */
	checkIdCardNo : function(idCardNo) {
		// 15位和18位身份证号码的基本校验
		var check = /^\d{15}|(\d{17}(\d|x|X))$/.test(idCardNo);
		if (!check)
			return false;
		// 判断长度为15位或18位
		if (idCardNo.length == 15) {
			return idCardNoUtil.check15IdCardNo(idCardNo);
		} else if (idCardNo.length == 18) {
			return idCardNoUtil.check18IdCardNo(idCardNo);
		} else {
			return false;
		}
	},

	// 校验15位的身份证号码
	check15IdCardNo : function(idCardNo) {
		// 15位身份证号码的基本校验
		var check = /^[1-9]\d{7}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}$/
				.test(idCardNo);
		if (!check)
			return false;
		// 校验地址码
		var addressCode = idCardNo.substring(0, 6);
		check = idCardNoUtil.checkAddressCode(addressCode);
		if (!check)
			return false;
		var birDayCode = '19' + idCardNo.substring(6, 12);
		// 校验日期码
		return idCardNoUtil.checkBirthDayCode(birDayCode);
	},

	// 校验18位的身份证号码
	check18IdCardNo : function(idCardNo) {
		// 18位身份证号码的基本格式校验
		var check = /^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}(\d|x|X)$/
				.test(idCardNo);
		if (!check)
			return false;
		// 校验地址码
		var addressCode = idCardNo.substring(0, 6);
		check = idCardNoUtil.checkAddressCode(addressCode);
		if (!check)
			return false;
		// 校验日期码
		var birDayCode = idCardNo.substring(6, 14);
		check = idCardNoUtil.checkBirthDayCode(birDayCode);
		if (!check)
			return false;
		// 验证校检码
		return idCardNoUtil.checkParityBit(idCardNo);
	},

	formateDateCN : function(day) {
		var yyyy = day.substring(0, 4);
		var mm = day.substring(4, 6);
		var dd = day.substring(6);
		return yyyy + '-' + mm + '-' + dd;
	},

	// 获取信息
	getIdCardInfo : function(idCardNo) {
		var idCardInfo = {
			gender : "", // 性别
			birthday : "" // 出生日期(yyyy-mm-dd)
		};
		if (idCardNo.length == 15) {
			var aday = '19' + idCardNo.substring(6, 12);
			idCardInfo.birthday = idCardNoUtil.formateDateCN(aday);
			if (parseInt(idCardNo.charAt(14)) % 2 == 0) {
				idCardInfo.gender = idCardNoUtil.genders.female;
			} else {
				idCardInfo.gender = idCardNoUtil.genders.male;
			}
		} else if (idCardNo.length == 18) {
			var aday = idCardNo.substring(6, 14);
			idCardInfo.birthday = idCardNoUtil.formateDateCN(aday);
			if (parseInt(idCardNo.charAt(16)) % 2 == 0) {
				idCardInfo.gender = idCardNoUtil.genders.female;
			} else {
				idCardInfo.gender = idCardNoUtil.genders.male;
			}

		}
		return idCardInfo;
	},

	/* 18位转15位 */
	getId15 : function(idCardNo) {
		if (idCardNo.length == 15) {
			return idCardNo;
		} else if (idCardNo.length == 18) {
			return idCardNo.substring(0, 6) + idCardNo.substring(8, 17);
		} else {
			return null;
		}
	},

	/* 15位转18位 */
	getId18 : function(idCardNo) {
		if (idCardNo.length == 15) {
			var id17 = idCardNo.substring(0, 6) + '19' + idCardNo.substring(6);
			var parityBit = idCardNoUtil.getParityBit(id17);
			return id17 + parityBit;
		} else if (idCardNo.length == 18) {
			return idCardNo;
		} else {
			return null;
		}
	}
};
// 检验是否是正确的日期
function isRightDate(value) {
	if (value.length == 10 && value.substring(4, 5) == "-"
			&& value.substring(7, 8) == "-") {
		var year = value.substring(0, 4);
		var month = value.substring(5, 7);
		var day = value.substring(8, 10);
		if (isNum(year) && isNum(month) && isNum(day)) {
			if (year % 400 == 0 || (year % 100 != 0 && year % 4 == 0)
					&& month == 2 && day <= 29) {
				return true;
			} else {
				if (month == 2) {
					if (day <= 28) {
						return true;
					} else {
						return false;
					}
				} else if ((month == 1 || month == 3 || month == 5
						|| month == 7 || month == 8 || month == 10 || month == 12)
						&& day <= 31) {
					return true;
				} else if ((month == 4 || month == 6 || month == 9 || month == 11)
						&& (day <= 30)) {
					return true;
				} else {
					return false;
				}
			}
		} else {
			return false;
		}
	} else {
		return false;
	}
}
// 检验是否是数字
function isNum(data) {
	if (!isNulOrEmpty(data)) {
		return /^-*\d+$/.test(data);
	} else {
		return false;
	}
}
// 检验取出空格后，银行卡号是否是数字
function isBankCard(data) {
	if (!isNulOrEmpty(data)) {
		var array = data.split(" ");
		var str = "";
		for (var i = 0; i < array.length; i++) {
			str += array[i];
		}
		return /^-*\d+$/.test(str);
	} else {
		return false;
	}
}
// 全角转半角
function FullToDBC(Str) {
	var DBCStr = "";
	for (var i = 0; i < Str.length; i++) {
		var c = Str.charCodeAt(i);
		if (c == 12288) {
			DBCStr += String.fromCharCode(32);
			continue;
		}
		if (c > 65280 && c < 65375) {
			DBCStr += String.fromCharCode(c - 65248);
			continue;
		}
		DBCStr += String.fromCharCode(c);
	}
	return DBCStr.toUpperCase();
}

// 日期补0
function addZero(da) {
	var addz = [ '01', '02', '03', '04', '05', '06', '07', '08', '09' ];
	if (da < 10) {
		return addz[da - 1];
	} else {
		return da
	}
}

// 获取当前用户角色
function getRoleByCurrentUser() {
	return JSON.parse($.ajax({
		type : "post",
		url : "sys/queryRole_userAction.action",
		// 将菜单id发送到后台,后台根据id查询对应的功能点,返回功能点的json
		dataType : "json",
		async : false,
	}).responseText).roleList;
}
