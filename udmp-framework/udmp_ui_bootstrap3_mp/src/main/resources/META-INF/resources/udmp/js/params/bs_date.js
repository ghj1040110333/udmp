/**
 * Description:datepicker组件
 * @author Seadon
 * @date 2016年3月18日
 *
 */

idefine(function(require, exports, module) {
	//载入依赖项
	require('bs_datetime');
	require('bs_datetime_cn');

	//日历选取
	var BsDate = {
		//默认属性
		_option: {
			language: 'zh-CN',
			format: 'yyyy-mm-dd',
			autoclose: true,
			weekStart: 1,
			todayBtn: true,
			forceParse: false,
			pickerPosition: 'bottom-left'
		},
		//初始化日历控件
		init: function($ele, options, callback) {
			var _op = {};
			jQuery.extend(_op, this._option, options);
			$ele.datetimepicker(_op).on('changeDate', callback);
		}
	};
	module.exports = BsDate;
});
