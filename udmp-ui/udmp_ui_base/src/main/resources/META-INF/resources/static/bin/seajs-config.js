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
    'udmp': _cd_pname + '/udmp',
    'sys': _cd_pname + '/sys/js'
  },
  alias: {
    control: 'bin/seajs-control',
    controls: 'bin/seajs-controls',
    jquery: 'lib/jquery/jquery',
    vue: 'bin/vue',
    vue2: 'bin/vue2',
    jquery_ui: 'lib/jquery/jquery-ui.min',
    bootstrap: 'lib/bootstrap3/bootstrap',
    echarts: 'lib/echarts/echarts.min',
    echarts_map_china: 'lib/echarts/map/china',
    echarts_map_geoCoord: 'lib/echarts/map/geoCoordMap',
    echarts_v2: 'lib/echarts/v2/echarts',
    nicescroll: 'lib/jquery-nicescroll/jquery.nicescroll',
    toastr: 'lib/toastr/toastr',
    app_plugin: 'lib/app/app_plugin',
    aplus_plugin: 'lib/app/aplus_plugin',
    bs_table: 'lib/bootstrap-table/bootstrap-table',
    bs_table_cn: 'lib/bootstrap-table/locale/sea-bootstrap-table-zh-CN.min',
    bs_editable: 'lib/bootstrap-editable/bootstrap-editable',
    bs_table_editable: 'lib/bootstrap-editable/bootstrap-table-editable',
    bs_validator: 'lib/bootstrap-validator/bootstrap-validator',
    bs_datetime: 'lib/bootstrap-datepicker/bootstrap-datepicker',
    bs_datetime_cn: 'lib/bootstrap-datetimepicker/js/locales/sea-bootstrap-datetimepicker.zh-CN',
    bs_daterange: 'lib/bootstrap-daterangepicker/daterangepicker',
    ztree: 'lib/ztree/js/jquery.ztree.all.js',
    tree_table: 'lib/tree-table/jquery.treeTable.min',
    message: 'lib/message/sweetalert',
    bootbox: 'lib/message/bootbox',
    moment: 'lib/bootstrap-daterangepicker/moment.min',
    ueditor: 'lib/ueditor/ueditor.all.min',
    ueditor_con: 'lib/ueditor/ueditor.config'
  },
  vars: {
    locale: 'zh-CN'
  },
  preload: [
    'jquery'
  ],
  charset: 'utf-8'
});
