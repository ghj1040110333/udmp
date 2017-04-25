/**
 * 批处理执行代理配置页面
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
						name : 'agentId',
						value : '代理ID',
						visible : 'false'
					}, {
						name : 'agentName',
						value : '代理名称'
					}, {
						name : 'agentIp',
						value : 'IP地址'
					}, {
						name : 'agentPort',
						value : '端口号'
					}, {
						name : 'agentThreadLimitCnt',
						value : '线程阀值'
					}, {
						name : 'agentSystem',
						value : '所属系统'
					}, {
						name : 'isEnable',
						value : '状态',
						handle : 'handleBtnFormatter'
					} ],
					options : {
						clickToSelect : true
					},
					tableUrl : $ctx + "/batch/agent/list",
					items : "",
					date : ""
				},
				methods : {
					addAgent : function(id) {
						vComponents.showForm("注册代理");
					},
					updateAgent : function(id) {
						$
								.post(
										$ctx
												+ "/batch/agent/queryById?agentId="
												+ id,
										"agentId:" + id,
										function(data) {
											if (data) {
												if (data.success) {
													// 展现更新form
													vComponents
															.showForm(
																	"更新代理配置",
																	id,
																	function() {
																		// 设置form中的值
																		window.top.msg.agentObj = data.data;
																	});
													// 设置form中的值
													// setTimeout(function(){
													// window.top.msg.agentObj =
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
					deleteAgent : function(e) {
						var selected = $('#contentTable').bootstrapTable(
								'getSelections');
						window.top.showConfirm("确定要删除这个Agent代理吗？", function() {
							$.post($ctx + "/batch/agent/delete?agentId="
									+ selected[0].agentId, "agentId:"
									+ selected[0].agentId, function(data) {
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
						var url = "/batch/agent/form";
						if (id != null) {
							url += "?agentId=" + id;
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
	$('#btnSubmit').on('click', function(e) {
		tableData.methods.search();
		vComponents.updateAgent();
	});
	// 更新页面的按钮事件
	$('#updateBtn').on('click', function(e) {
		var selected = $('#contentTable').bootstrapTable('getSelections');
		vComponents.updateAgent(selected[0].agentId);
	});

	//	
	// $("#tableData").on("click", ".update-btn", function (e, value, row,
	// index) {
	// console.log(row);
	// vComponents.updateAgent($(this).data("value"));
	// });
})
