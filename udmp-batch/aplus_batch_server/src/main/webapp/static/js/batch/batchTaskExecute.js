/**
 * 凭证补打页面
 */

idefine(function(require, exports, module) {
	// 引入依赖文件
	require('bin/seajs-handler');
	require('lib/app/print');
	require('udmp/widget/common/Layout.min');
	require('udmp/widget/common/Form.min');
	var rangeDate = require('udmp/lib/bs_daterange'), tableData = UdmpLayout.table, wt = window.top;

	// Vue调试开启
	Vue.config.devtools = true
	Vue.config.debug = true

	// 初始化Vue组件
	var vComponents = new Vue({
		el : '#i-manage-panel',
		components : {
			udmpSearch : UdmpLayout.search,
			udmpData : tableData,
			udmpInput : UdmpForm.input,
			udmpButton : UdmpForm.button
		},
		data : {
			table : [{
				name : 'legPerCod',
				value : '法人代码'
			}, {
				handle : 'handleBtnFormatter',
				value : '操作'
			} ],
			options : {
				clickToSelect : true
			},
			tableUrl : $ctx + "/batch/taskExecute/list",
			items : "",
			date : ""
		},
		methods : {
			startBatchAsyncJob : function(id) {
				$.post($ctx + "/batchAsyncJob/startBatchAsyncJob_batchAsyncJobAction.action?batchAsyncJobAVO.jobId=" + id, function(jsonData) {
					var data = JSON.parse(jsonData);
					if (data) {
						if (data.statusCode=='200') {
							top.window.showInfo("success", data.message);
						} else {
							top.window.showInfo("error", data.message);
						}
					}
				});
			}
		}
	});

	// 格式化操作按钮
	window.handleBtnFormatter = function(value, row, index) {
		var returnValue = "";
		var editBtn = "<button type='button' title='启动' data-value='"
				+ row.batchJobId
				+ "' class='btn btn-default btn-md table-btn-edit table-btn-column'><i class='glyphicon glyphicon-play'></i></button>";
		returnValue += editBtn;
		return returnValue;
	}

	// 用户触发操作
	$("#contentTable").on("click", ".table-btn-edit",
			function(e, value, row, index) {
				vComponents.startBatchAsyncJob($(this).data("value"));
			});

	rangeDate.init($("#date"));

	// 按下关闭按钮，触发表格刷新
	$("#i-udmp-search").on("mousedown", "span.close", function() {
		setTimeout(function() {
			$('#btnSubmit').trigger('click');
		}, 300);
	})

	// 查询事件
	$('#btnSubmit').on('click', function(e) {
		tableData.methods.search();
	});
})
