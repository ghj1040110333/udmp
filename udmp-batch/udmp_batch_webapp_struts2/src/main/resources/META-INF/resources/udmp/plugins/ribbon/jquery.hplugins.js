/**
 * 2014年10月29日 15:25:53
 * 向浏览器控制台输出信息的小插件
 * 调用方式：$.log(variant);
 */
$.extend({
	log : function(message) {
		var now = new Date(), y = now.getFullYear(), m = now.getMonth() + 1, d = now
				.getDate(), h = now.getHours(), min = now.getMinutes(), s = now
				.getSeconds(), time = y + '/' + m + '/' + d + ' ' + h
				+ ':' + addTimeToDouble(min) + ':' + addTimeToDouble(s);
		console.log(time + ' : ' + message);
	}
});
/**
 * 2014年10月29日 15:37:45
 * 将时间从转换为两位字符串并返回
 * @param str
 * @returns
 */
function addTimeToDouble(str){
	if(str >= 0 && str <= 9){
		return '0' + '0123456789'.charAt(str);
	}else{
		return str;
	}
};
/**
 * 2014年10月29日 15:48:31
 * color属性转换成红色
 */
$.fn.colorToRed = function(){
	return this.css('color','red');
};
