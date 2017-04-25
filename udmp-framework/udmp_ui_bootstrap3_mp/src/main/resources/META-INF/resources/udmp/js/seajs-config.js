/**
 * Description:seajs配置
 * @author Seadon
 * @date 2016年2月06日
 *
 */

//获取项目路径
var _cd_tname = window.document.location.pathname,
	_cd_pname = _cd_tname.substring(0, _cd_tname.substr(1).indexOf('/') + 1);

seajs.config({
	base: _cd_pname + '/static/',
	paths: {
		'udmp': _cd_pname + '/udmp/js/',
		'risk': _cd_pname + '/risk-alert/js'
	},
	alias: {
		jquery: 'jquery/jquery-1.11.3.min',
		sea_jquery: 'jquery/sea-jquery-1.11.3.min',
		bootstrap: 'bootstrap/3.3.5/js/bootstrap.min',
		echarts: 'echart/echarts.min',
		echarts_map_china: 'echart/map/china',
		echarts_map_geoCoord: 'echart/map/geoCoordMap',
		echarts_v2: 'echart/v2/echarts',
		nicescroll: 'scroll/jquery.nicescroll.min',
		toastr: 'toastr/toastr.min',
		app_plugin: 'app/app_plugin',
		bs_table: 'bootstrap-table/bootstrap-table.min',
		bs_table_cn: 'bootstrap-table/locale/sea-bootstrap-table-zh-CN.min',
		bs_validator: 'bootstrap-validator/js/bootstrapValidator.min',
		bs_datetime: 'bootstrap-datetimepicker/js/bootstrap-datetimepicker.min',
		bs_datetime_cn: 'bootstrap-datetimepicker/js/locales/sea-bootstrap-datetimepicker.zh-CN',
		bs_daterange: 'bootstrap-daterangepicker/daterangepicker',
		moment: 'bootstrap-daterangepicker/moment.min',
		ueditor: 'ueditor/ueditor.all.min',
		ueditor_con: 'ueditor/ueditor.config'
	},
	vars: {
		locale: 'zh-CN'
	},
	preload: [
		'sea_jquery'
	],
	charset: 'utf-8'
});
