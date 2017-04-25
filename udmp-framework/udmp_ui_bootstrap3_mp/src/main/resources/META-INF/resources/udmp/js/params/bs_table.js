/**
 * Description:table组件
 * @author Seadon
 * @date 2016年3月15日
 *
 */

idefine(function(require, exports, module) {
	//载入依赖项
	require('bs_table');
	require('bs_table_cn');

	//表格方法
	var BsTable = {
		//初始化表格
		init: function($tb, options) {
			// 默认表格属性
			var defaultOptions = {
				classes: "table table-striped table-no-bordered table-condensed table-hover",
				striped: true,
				pagination: true,
				undefinedText: "",
				sidePagination: "server",
				pageList: "[5,10, 30, 50, 100]",
				queryParams: this._queryParams
			};
			// 拷贝自定义表格属性
			var options = jQuery.extend(true, defaultOptions, options);
			$tb.bootstrapTable(options).show();
		},
		//返回表单对象
		_serializeObject: function($form) {
			var o = {};
			jQuery.each($form.serializeArray(), function(index) {
				if (o[this['name']]) {
					o[this['name']] = o[this['name']] + "," + this['value'];
				} else {
					o[this['name']] = this['value'];
				}
			});
			return o;
		},
		//表格查询参数
		_queryParams: function(params) {
			var searchParams = BsTable._serializeObject(jQuery(".i-query-table"));
			searchParams.pageNo = (params.offset / params.limit) + 1;
			searchParams.pageSize = params.limit;
			return searchParams;
		},

	};
	module.exports = BsTable;
});
