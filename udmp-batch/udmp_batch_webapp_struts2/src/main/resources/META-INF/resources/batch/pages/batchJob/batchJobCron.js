//嵌入页获取cron 表达式
function getcron() {
	return getvalues();
}
var radio_type = new HashMap();
radio_type.put("1", "2");
radio_type.put("2", "1");
radio_type.put("3", "1");
radio_type.put("4", "1");
radio_type.put("5", "1");
radio_type.put("6", "1");
radio_type.put("7", "1");

// 修改radio_type值
function editradio(key, value) {
	if (radio_type.containsKey(key)) {
		radio_type.remove(key);
		radio_type.put(key, value);
	} else {
		radio_type.put(key, value);
	}
}

// 分钟tab事件
function fz_radio_click(obj) {
	var str = obj.value;
	if (str == "fz1") {
		editradio("1", "1");
		$("#fks").val("0");
		$("#fks").attr("disabled", "true");
		$("#fzx").val("0");
		$("#fzx").attr("disabled", "true");
		$("#mks").removeAttr("disabled");
		$("#mzx").removeAttr("disabled");
		chk_disabled(1);
	}
	if (str == "fz2") {
		editradio("1", "2");
		$("#fks").removeAttr("disabled");
		$("#fzx").removeAttr("disabled");
		$("#mks").val("0");
		$("#mks").attr("disabled", "true");
		$("#mzx").val("0");
		$("#mzx").attr("disabled", "true");
		chk_disabled(1);
	}
	if (str == "fz3") {
		editradio("1", "3");
		$("#fks").val("0");
		$("#fks").attr("disabled", "true");
		$("#fzx").val("0");
		$("#fzx").attr("disabled", "true");
		$("#mks").val("0");
		$("#mks").attr("disabled", "true");
		$("#mzx").val("0");
		$("#mzx").attr("disabled", "true");
		chk_disabled(2);
	}

}
// 0-59 分钟disabled控制
function chk_disabled(str) {
	if (str == '1') {
		for ( var i = 0; i < 60; i++) {
			$("#chk" + i).attr("disabled", "true");
			$("#chk" + i).removeAttr("checked");
		}
	}
	if (str == '2') {
		for ( var i = 0; i < 60; i++) {
			$("#chk" + i).removeAttr("disabled");
			// $("#chk"+i).checked=true;
		}
	}

}
// 小时tab事件

function xs_radio_click(obj) {
	var str = obj.value;
	if (str == "xs1") {
		editradio("2", "1");
		for ( var i = 0; i < 24; i++) {
			$("#sj" + i).attr("disabled", "true");
			$("#sj" + i).removeAttr("checked");
		}
	}
	if (str == "xs2") {
		editradio("2", "2");
		for ( var i = 0; i < 24; i++) {
			$("#sj" + i).removeAttr("disabled");
			// $("#sj"+i).checked=false;
		}
	}
}
// 天tab事件

function mt_radio_click(obj) {
	var str = obj.value;
	if (str == "mt1") {
		editradio("3", "1");
		for ( var i = 1; i < 32; i++) {
			$("#t" + i).attr("disabled", "true");
			$("#t" + i).removeAttr("checked");
		}
	}
	if (str == "mt2") {
		editradio("3", "2");
		for ( var i = 1; i < 32; i++) {
			$("#t" + i).removeAttr("disabled");
			// $("#sj"+i).checked=false;
		}
	}
}

// 月tab事件

function my_radio_click(obj) {
	var str = obj.value;
	if (str == "my1") {
		editradio("4", "1");
		for ( var i = 1; i < 13; i++) {
			$("#y" + i).attr("disabled", "true");
			$("#y" + i).removeAttr("checked");
		}
	}
	if (str == "my2") {
		editradio("4", "2");
		for ( var i = 1; i < 13; i++) {
			$("#y" + i).removeAttr("disabled");
			// $("#sj"+i).checked=false;
		}
	}
}

// 周tab事件
function zhou_checkbox_click() {
	if ($("#mzhou").attr("checked")) {
		editradio("5", "2");
		$("#mzh1").removeAttr("disabled");
		$("#mzh2").removeAttr("disabled");
		if($("#mzh2").attr("checked")){		
			for ( var i = 1; i < 8; i++) {
				$("#week" + i).removeAttr("disabled");
			}
		}
	} else {
		editradio("5", "1");
		editradio("6", "0");
		$("#mzh1").attr("disabled", "true");
		$("#mzh2").attr("disabled", "true");
		for ( var i = 1; i < 8; i++) {
			$("#week" + i).attr("disabled", "true");
			$("#week" + i).removeAttr("checked");
		}
	}
}

function zhou_radio_click(obj) {
	var str = obj.value;
	if (str == "mzh1") {
		editradio("6", "1");
		for ( var i = 1; i < 8; i++) {
			$("#week" + i).attr("disabled", "true");
			$("#week" + i).removeAttr("checked");
		}
	}
	if (str == "mzh2") {
		editradio("6", "2");
		for ( var i = 1; i < 8; i++) {
			$("#week" + i).removeAttr("disabled");
		}
	}
}

// 获取值
function getvalues() {
	// var mks,mzx,fks,fzx,chk1,sj0,t1,y1,zhou1,d4311,d4312="";
	var values = new HashMap();

	// 循环周期 分钟处理
	if (radio_type.get("1") == "1") {
		var mks = $("#mks").val();
		var mzx = $("#mzx").val();
		var str = mks + "/" + mzx;
		values.put("1", str);
		$("#cronSecond").val(str);
	}
	if (radio_type.get("1") == "2") {
		var fks = $("#fks").val();
		var fzx = $("#fzx").val();
		var str = fks + "/" + fzx;
		values.put("2", str);
		$("#cronMinute").val(str);
	}
	if (radio_type.get("1") == "3") {
		var sMinute = "";
		for ( var i = 0; i < 60; i++) {			
			if ($("#chk" + i).attr("checked"))
				sMinute += $("#chk" + i).val() + ",";
		}
		str = sMinute.substring(0, sMinute.length - 1);
		values.put("2", str);
		$("#cronMinute").val(str);
	}
	// 小时处理
	if (radio_type.get("2") == "1") {
		values.put("5", "*");
		$("#cronHour").val("*");
	}
	if (radio_type.get("2") == "2") {
		var sHour = "";
		for ( var i = 0; i < 24; i++) {
			if ($("#sj" + i).attr("checked"))
				sHour += $("#sj" + i).val() + ",";
		}
		str = sHour.substring(0, sHour.length - 1);
		values.put("5", str);
		$("#cronHour").val(str);
	}
	// 日处理
	if (radio_type.get("3") == "1") {
		values.put("6", "*");
		$("#cronDay").val("*");
	}
	if (radio_type.get("3") == "2") {
		var sDay = "";
		for ( var i = 1; i < 32; i++) {
			if ($("#t" + i).attr("checked"))
				sDay += $("#t" + i).val() + ",";
		}
		str = sDay.substring(0, sDay.length - 1);
		values.put("6", sDay.substring(0, sDay.length - 1));
		$("#cronDay").val(str);
	}

	// 月处理
	if (radio_type.get("4") == "1") {
		values.put("7", "*");
		$("#cronMonth").val("*");
	}
	if (radio_type.get("4") == "2") {
		var sMonth = "";
		for ( var i = 1; i < 13; i++) {
			if ($("#y" + i).attr("checked"))
				sMonth += $("#y" + i).val() + ",";
		}
		str = sMonth.substring(0, sMonth.length - 1);
		values.put("7", sMonth.substring(0, sMonth.length - 1));
		$("#cronMonth").val(str);
	}

	// 周处理

	if (radio_type.get("5") == "1") {
		values.put("8", "?");
		$("#cronWeek").val("?");
	}
	if (radio_type.get("5") == "2") {
		if (radio_type.get("6") == "1") {
			values.put("8", "*");
			$("#cronWeek").val("*");
		}
		if (radio_type.get("6") == "2") {
			var sWeek = "";
			for ( var i = 1; i < 8; i++) {
				if ($("#week" + i).attr("checked"))
					sWeek += $("#week" + i).val() + ",";
			}
			str = sWeek.substring(0, sWeek.length - 1);
			values.put("8", sWeek.substring(0, sWeek.length - 1));
			$("#cronWeek").val(str);
		}
	}

	// 勾选周循环天循环失效
	if (radio_type.get("5") == "2" && values.containsKey("6")) {
		values.remove("6");
		values.put("6", "?");
		$("#cronDay").val("?");
	}
	// 秒循环设0
	if (values.get("1") == null || values.get("1") == "0/0") {
		if (values.containsKey("1")) {
			values.remove("1");
			values.put("1", "0");
			$("#cronSecond").val("0");
		} else {
			values.put("1", "0");
			$("#cronSecond").val("0");
		}
	}
	// 分循环设0
	if (values.get("2") == null || values.get("2") == "0/0") {
		if (values.containsKey("2")) {
			values.remove("2");
			values.put("2", "0");
			$("#cronMinute").val("0");
		} else {
			values.put("2", "0");
			$("#cronMinute").val("0");
		}
	}

	var cron = values.get("1") + " " + values.get("2") + " " + values.get("5")
			+ " " + values.get("6") + " " + values.get("7") + "  "
			+ values.get("8");
	$("#cronText").val(cron);

	return cron;
}

function HashMap() {
	/** Map大小* */
	var size = 0;
	/** 对象* */
	var entry = new Object();
	/** Map的存put方法* */
	this.put = function(key, value) {
		if (!this.containsKey(key)) {
			size++;
			entry[key] = value;
		}
	};
	/** Map取get方法* */
	this.get = function(key) {
		return this.containsKey(key) ? entry[key] : null;
	};
	/** Map删除remove方法* */
	this.remove = function(key) {
		if (this.containsKey(key) && (delete entry[key])) {
			size--;
		}
	};
	/** 是否包含Key* */
	this.containsKey = function(key) {
		return (key in entry);
	};
	/** 是否包含Value* */
	this.containsValue = function(value) {
		for ( var prop in entry) {
			if (entry[prop] == value) {
				return true;
			}
		}
		return false;
	};
	/** 所有的Value* */
	this.values = function() {
		var values = new Array();
		for ( var prop in entry) {
			values.push(entry[prop]);
		}
		return values;
	};
	/** 所有的 Key* */
	this.keys = function() {
		var keys = new Array();
		for ( var prop in entry) {
			keys.push(prop);
		}
		return keys;
	};
	/** Map size* */
	this.size = function() {
		return size;
	};
	/** 清空Map* */
	this.clear = function() {
		size = 0;
		entry = new Object();
	};
}
