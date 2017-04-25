/**
 * Description: 工具库
 * @author Seadon
 * @date 2016年2月16日
 *
 */

idefine({
	//弹出窗口
	showInfo: function(type, msg) {
		//var myMessages = ['info','warning','error','success'];
		jQuery(".message").remove();
		jQuery('body').append("<div class='msg-" + type + " message'><h3>" + msg +
			"</h3></div>");
		jQuery('.msg-' + type).animate({
			top: "0"
		}, 500);
		jQuery('.message').click(function() {
			jQuery(this).animate({
				top: -jQuery(this).outerHeight()
			}, 500);
		});
	},
	//清除窗口
	clearInfo: function() {
		jQuery(".message").animate({
			top: -jQuery(".message").outerHeight()
		}, 500)
	},
	//字符串过长处理
	abbr: function(str, length) {
		if (str && str.length > length) {
			str = str.substr(0, length) + "...";
		}
		return str;
	},
	//清除最后出现字符
	clearEndStr: function(str, flag) {
		var i_reg = eval("/" + flag + "$/gi");
		str = str.replace(i_reg, "");
		return str;
	},
	//获取当前日期
	getToday: function() {
		var now = new Date(),
			date = now.getFullYear() + "-" + (now.getMonth() + 1) + "-" + now.getDate();
		return date;
	},
	//将form表单元素的值序列化成对象
	serializeObject: function($form) {
		var o = {};
		jQuery.each($form.serializeArray(), function(index) {
			if (o[this['name']]) {
				o[this['name']] = o[this['name']] + "," + this['value'];
			} else {
				o[this['name']] = this['value'];
			}
		});
		return o;
	}
});
