/**
 * 批处理执行任务配置页面
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
	var vComponents = new Vue(
			{
				el : '#i-manage-panel',
				components : {
					udmpSearch : UdmpLayout.search,
					udmpData : tableData,
					udmpInput : UdmpForm.input,
					udmpButton : UdmpForm.button
				},
				data : {
					table : [ {
						name : 'state',
						radio : 'true'
					}, {
						name : 'jobId',
						value : '任务ID',
						visible : 'false'
					}, {
						name : 'jobName',
						value : '任务名称'
					}, {
						name : 'jobGroupId',
						value : '任务组ID'
					}, {
						name : 'jobSystem',
						value : '所属系统'
					}],
					options : {
						clickToSelect : true
					},
					tableUrl : $ctx + "/batch/job/list",
					items : "",
					date : ""
				},
				methods : {
					addJob : function(id) {
						vComponents.showForm("注册任务");
					},
					updateJob : function(id) {
						$
								.post(
										$ctx+ "/batch/job/queryById?jobId="+ id,
										"jobId:" + id,
										function(data) {
											if (data) {
												if (data.success) {
													// 展现更新form
													vComponents
															.showForm(
																	"更新任务配置",
																	id,
																	function() {
																		// 设置form中的值
																		window.top.msg.jobObj = data.data;
																	});
													// 设置form中的值
													// setTimeout(function(){
													// window.top.msg.jobObj =
													// data.data;
													// },500);
												} else {
													top.window.showInfo(
															"error",
															data.message);
												}
											}
										});
					},
					deleteJob : function(e) {
						var selected = $('#contentTable').bootstrapTable(
								'getSelections');
						window.top.showConfirm("确定要删除这个任务吗？", function() {
							$.post($ctx + "/batch/job/delete?jobId="
									+ selected[0].jobId, "jobId:"
									+ selected[0].jobId, function(data) {
								if (data) {
									if (data.success) {
										top.window.showInfo("success", "删除成功");
										vComponents.refreshTable();
									} else {
										top.window.showInfo("error",
												data.message);
									}
								}
							});
						});
					},
					showForm : function(titleName, id, complete) {
						var url = "/batch/job/form";
						if (id != null) {
							url += "?jobId=" + id;
						}
						if (complete) {
							wt.showModal($ctx + url, null, {
								title : titleName,
								width : 1000
							}, null, function() {
								vComponents.refreshTable();
							}, complete);
						}else{
							wt.showModal($ctx + url, null, {
								title : titleName,
								width : 1000
							}, null, function() {
								vComponents.refreshTable();
							});
						}
					},
					// 更新树表表格
					refreshTable : function() {
						tableData.methods.search();
					},
					startBatchAsyncJob : function(id) {
						$
								.post(
										$ctx
												+ "/batchAsyncJob/startBatchAsyncJob_batchAsyncJobAction.action?batchAsyncJobAVO.jobId="
												+ id, function(jsonData) {
											var data = JSON.parse(jsonData);
											if (data) {
												if (data.statusCode == '200') {
													top.window.showInfo(
															"success",
															data.message);
												} else {
													top.window.showInfo(
															"error",
															data.message);
												}
											}
										});
					}
				}
			});

	// 格式化操作按钮
	window.handleBtnFormatter = function(value, row, index) {
		if (row.isEnable == 1) {
			return "启用";
		} else {
			return "停用";
		}
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
	// 更新页面的按钮事件
	$('#updateBtn').on('click', function(e) {
		var selected = $('#contentTable').bootstrapTable('getSelections');
		vComponents.updateJob(selected[0].jobId);
	});

	//	
	// $("#tableData").on("click", ".update-btn", function (e, value, row,
	// index) {
	// console.log(row);
	// vComponents.updateJob($(this).data("value"));
	// });
})
