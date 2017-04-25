/**
 * Description:
 * @author Seadon
 * @date 2016年3月28日
 *
 */

idefine(function(require, exports, module) {
	//载入依赖项
	require('moment');
	require('bs_daterange');

	//日历范围选取
	var BsDate = {
		//默认属性
		_option: {
			locale: {
				format: 'YYYY-MM-DD',
				applyLabel: '选择',
				cancelLabel: '取消'
			}
		},
		//初始化日历控件
		init: function($ele, options, callback) {
			var _op = {};
			jQuery.extend(_op, this._option, options);
			$ele.daterangepicker(_op, callback);
		}
	};
	module.exports = BsDate;
});
