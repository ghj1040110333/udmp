/**
 * Description:首页加载脚本
 *
 * @author Seadon
 * @date 2016年2月18日
 *
 */

idefine(function(require, exports, module) {
	require('udmp/seajs-control');
	var count, _one, _flag, resizeArr = [],
		drag = require('udmp/libs/jq_drag'),
		ut = require('udmp/params/util_tool'),
		bc = require('udmp/params/bs_calendar');
	require('echarts');
	require('echarts_map_china');
	require('echarts_map_geoCoord');
	window.resizeArr = resizeArr;

	// 调整图表大小
	function rechart() {
		console.log('重置所有图表');
		$.each(resizeArr, function(i, n) {
			for (p in n) {
				n[p]();
			}
		})
	};

	//全屏打开关闭，判断刷新图表
	function refresh(obj) {
		switch (obj.data('cd')) {
			case "signal-stat":
				findModule("signal-stat")
				break;
			case "area-stat":
				findModule("area-stat")
				break;
			case "warn-type":
				findModule("warn-type")
				break;
			case "rule-stat":
				findModule("rule-stat")
				break;
			default:
				rechart();
		}
	};

	//查找对应模块刷新
	function findModule(name) {
		$.each(resizeArr, function(i, n) {
			for (p in n) {
				if (p === name)
					n[p]();
			}
		})
	}

	window.rechart = rechart;
	window.ifresh = refresh;

	(function($) {
		//获取本地存储的模块信息
		var iIndex, iModule, iName = loginName,
			iDefault = {};

		//默认加载模块信息
		iDefault[iName] = [{
			id: "GIT001",
			name: "link",
			url: "indexLink"
		}, {
			id: "GIT002",
			name: "model",
			url: "modelManage"
		}, {
			id: "GIT003",
			name: "modules",
			url: "indexModules"
		}];

		iIndex = localStorage.getItem('indexModule') || iDefault;

		//根据用户名取相应的模块
		if ($.type(iIndex) === "object") {
			localStorage.setItem("indexModule", JSON.stringify(iDefault));
		} else {
			iIndex = JSON.parse(iIndex);

			//没有用户信息时，动态添加默认值
			if (!iIndex[iName]) {
				iIndex[iName] = iDefault[iName];
				localStorage.setItem("indexModule", JSON.stringify(iIndex));
			}
		}

		iModule = iIndex[iName];

		//加载模块
		if (!!iModule) {
			$.each(iModule, function(i, n) { //i为惟一标识，n为具体对象
				var _name = n.name,
					_url = $ctx + "/../risk-alert/module/" + n.url + ".html",
					$panel = $('<div>').addClass('row i-chart-warn').data({
						'id': n.id,
						'name': _name,
						'url': n.url
					});
				$panel.load(_url,
					function() {
						var $panel = $(this).find('.panel');
						if ($panel.length > 1) {
							$panel.each(function() {
								$(this).dragging({
									hander: '.i-panel-title'
								});
							});
						} else {
							$panel.dragging({
								hander: '.i-panel-title'
							});
						}
					}).appendTo("#i-tabs-home"); //加载模块片段
			});
		}

		//菜单添加模块
		$('.i-nav-menu').on('click', '.glyphicon-link', function(e) {
			e.stopPropagation();
			var $this = $(this),
				_img = $this.data('img');
			$null = $('.tab-content .i-chart-warn .i-link .i-link-null:first');
			if (!!$null.get(0)) {
				var $a = $this.closest('a'),
					_id = $a.data('menu'),
					_url = $a.data('url'),
					_name = $a.attr('title'),
					linkArr = [];

				//现有超链接数据
				$('.tab-content .i-link .i-item-cover>div').each(function() {
					linkArr.push($(this).data('id'));
				});

				//判断新增链接是否存在
				if ($.inArray(_id, linkArr) < 0) {
					var strCover = '<div class="i-item-cover">' +
						'<div class="i-item-info" data-id="' + _id + '" url="' + _url + '">' +
						_name + '</div>' +
						'<a href="#fullScreen" class="btn btn-xs btn-info">查看</a>' +
						'<a class="btn btn-xs btn-danger">删除</a>' +
						'</div>';
					$null.removeClass('i-link-null').find('.i-item-body>img').attr('src',
							'/risk-alert/udmp/css/images/box/' + _img).end().find(
							'.i-item-body')
						.prepend(
							strCover);
					$null.find('.btn-info').fullscreen();
					toastr.success("添加超链接成功！");
					saveLinkSet();
				} else {
					toastr.error("添加的链接已存在，请确认！");
				}
			} else {
				toastr.error("超链接区域位置无空缺,请删除替换的链接后重试！");
			}
		}).on('click', '.glyphicon-dashboard', function(e) {
			e.stopPropagation();
			var _lnum = $('.i-left section').length,
				_rnum = $('.i-right section').length,
				$this = $(this),
				pos,
				$a = $this.closest('a'),
				_url = $this.data('url'),
				_id = $a.data('menu'),
				_name = $a.attr('title'),
				moduleArr = [];

			//现有模块ID
			$('.i-chart-warn section.panel').each(function() {
				moduleArr.push($(this).data('id'));
			});

			if ($.inArray(_id, moduleArr) < 0) {
				//判断并新增模块
				if (_rnum < _lnum) {
					pos = '.i-right';
				} else {
					pos = '.i-left';
				}
				getModule({
					"id": _id,
					"name": _name,
					"url": _url
				}, pos);
				toastr.success("添加功能模块成功！");
				setTimeout(function() {
					saveUserSet()
				}, 300);
			} else {
				toastr.error("添加的功能模块已存在，请确认！");
			}
		});

		//窗口改变时，重调echarts渲染
		$(window).resize(function() {
			rechart();
		});

		//收缩菜单时，重调echarts渲染
		$(".i-indent,#i-tabs-thome").on('click', function() {
			setTimeout(rechart, 100);
		});

		//关闭按钮触发事件
		$("#i-frame-tabs .nav-tabs").on('click', '.i-close-tab:eq(0)', function() {
			setTimeout(rechart, 100);
		});

		//委托在tab页的多个事件
		$('.tab-content').on('click', '.i-del', function() {
			var $panel = $(this).closest('.panel');
			if ($panel.parent().is('.col-md-12')) {
				$panel.closest('.i-chart-warn').remove();
			} else {
				$(this).closest('.panel').remove();
			}
			saveUserSet();
		}).on('click', '.i-item-body .btn-danger', function() {
			var $cover = $(this).closest('.i-item-cover'),
				$item = $cover.closest('.item');
			$cover.next('img').attr('src',
				'/risk-alert/udmp/css/images/box/opening.png');
			$cover.remove();
			$item.addClass('i-link-null');
			saveLinkSet();
		}).on('click', '.i-item-body .btn-info', function() {
			var $info = $(this).prev(),
				_text = $info.text(),
				_url = $info.attr('url'),
				_iframe = $('<iframe>', {
					'class': 'i-iframe',
					'height': '100%',
					'frameborder': 'no',
					'border': '0',
					'src': _url
				}),
				_height;
			$('#fullScreen .page-header h1').html(_text);
			_height = $('#fullScreen').height() - 30 - $(
					'#fullScreen .page-header')
				.height();
			$('#fullScreen .i-full-content').height(_height).html(_iframe);
		}).on('click', '.i-panel-title .i-full', function(e) {
			e.stopPropagation();
			var $info = $(this).closest('.i-panel-title'),
				$chart = $info.next(),
				_text = $info.find('.i-title-name').text();
			$('#fullScreen .page-header h1').html(_text);
			_height = $('#fullScreen').height() - 30 - $(
					'#fullScreen .page-header')
				.height();
			$('#fullScreen .i-full-content').html($chart);
			setTimeout(function() {
				$chart.height(_height).show();
				refresh($chart);
			}, 200);
		});

		//保存超链接配置
		var saveLinkSet = function() {
			var link_module = JSON.parse(localStorage.getItem('linkModule')) || {};
			link_module[loginName]['list'] = [];
			$('.i-chart-warn .i-item-content .item').each(function() {
				var $this = $(this),
					_index = Number($this.attr('index')),
					$info = $this.find('.i-item-info') || {};
				link_module[loginName]['list'][_index] = {
					id: $info.data('id') || null,
					name: $info.text() || '空缺',
					url: $info.attr('url'),
					img: $this.find('img').attr('src')
				};
				console.warn($info.data('id') + "--链接区--" + $info.attr('url'));
			});
			localStorage.setItem("linkModule", JSON.stringify(link_module));
		};

		//保存用户配置
		var saveUserSet = function() {
			var diy = JSON.parse(localStorage.getItem('indexModule')) || {},
				diy_module = JSON.parse(localStorage.getItem('singleModule')) || {};
			diy[loginName] = [];
			diy_module[loginName]['left'] = [];
			diy_module[loginName]['right'] = [];

			$('.row.i-chart-warn').each(function() {
				var $this = $(this),
					_id = $this.data('id'),
					_name = $this.data('name'),
					_url = $this.data('url');
				diy[loginName].push({
					id: _id,
					name: _name,
					url: _url
				});
				console.warn(_name + "--外层--" + _url)
			});
			$('.i-chart-warn .i-left section.panel').each(function() {
				var $this = $(this),
					_id = $this.data('id'),
					_name = $this.data('name'),
					_url = $this.data('url');
				diy_module[loginName]['left'].push({
					id: _id,
					name: _name,
					url: _url
				});
				console.warn(_name + "--左侧--" + _url);
			});
			$('.i-chart-warn .i-right section.panel').each(function() {
				var $this = $(this),
					_id = $this.data('id'),
					_name = $this.data('name'),
					_url = $this.data('url');
				diy_module[loginName]['right'].push({
					id: _id,
					name: _name,
					url: _url
				});
				console.warn(_name + "--右侧--" + _url);
			});
			localStorage.setItem("indexModule", JSON.stringify(diy));
			localStorage.setItem("singleModule", JSON.stringify(
				diy_module));
		}

		//抛出全局
		window.saveUserSet = saveUserSet;
		window.saveLinkSet = saveLinkSet;
		/*
		 * 首页初始化
		 */
		(function() {
			// 获取新消息
			getNewOaNotify();

			// 定时获取新消息(具体时间待定, 暂定5分钟刷新一次)
			//setInterval(getNewOaNotify, 300000);

			// 获取信号数
			getMyWork();
			
			//初始化工作日历
			initWorkRemind();
		}())

		// 获取我的工作面板信息
		function getMyWork() {
			$.getJSON($ctx + "/warnSignal/tWarnInfo/getIndexInfo", function(data) {
				$(".i-signal .i-work-info h1").text(data.signalNum).data('to', data.signalNum);
				$(".i-gtasks .i-work-info h1").text(data.todoNum).data('to', data.todoNum);
				$(".i-done .i-work-info h1").text(data.doneNum).data('to', data.doneNum);
				$(".i-trace .i-work-info h1").text(data.trackNum).data('to', data.trackNum);
				$('.i-work-info h1').each(function() {
					$(this).countTo();
				});
			});
		}

		//获取新消息
		function getNewOaNotify() {
			$.post($ctx + "/sys/oaNotify/self/unread", function(data) {
				var $n = $('#i-msg-count'),
					_length = data.length;
				if (data) {
					// 清空列表
					//$("#oaNotifyList").html("");
					// 判断是否有新通知
					if (_length == 0) {
						$n.text("");
						$("#oaNotifyCount").html("没有新消息!");
						return;
					}
					if (!_one) {
						for (var i = 0; i < _length; i++) {
							if (i > _length - 6) {
								dealMsg(data, i);
							};
						};
						_flag = data[_length - 1].id;
						console.log("通知消息末位Id为：" + _flag);
						_one = true;
					} else {
						var _hasNum = $("#oaNotifyList > a").length;
						//获取标识符所在index
						var _last = (function() {
							for (var i = 0; i < _length; i++) {
								if (data[i].id === _flag) {
									console.log("上次获取消息末位ID的Index：" + i);
									return i;
								}
							};
						})();
						//动态添加新消息
						if (_length - 1 === _last) {
							console.log("没有推送新消息");
							if (_hasNum < 5 && _length > 5) {
								for (var i = _length - 5; i < length; i++) {
									dealMsg(data, i);
								}
							}
						} else {
							//有数据后，再完善
							if (_length - _last > 5) {
								$("#oaNotifyList").html("");
							}
							for (var i = _last + 1; i < _length; i++) {
								dealMsg(data, i);
							}
						};
					};
				} else {
					$("#oaNotifyCount").html("没有新消息!");
				}
			});
		};

		// 绑定通知点击事件
		$("#oaNotifyList").on("click", ".oaNotify",
			function() {
				top.window.showModal($ctx + "/sys/oaNotify/view?id=" + $(this).data(
					"value"), null, {
					title: "查看通知",
					width: 800
				});
				updateMsg(this);
			});

		//处理消息
		function dealMsg(data, i) {
			var oaNotify =
				"<a href='javascript:void(0);' class='media list-group-item oaNotify' data-value='" +
				data[i].id + "' title='查看通知'>";
			oaNotify += data[i].title;
			oaNotify += "<br><span class='media-body block m-b-none'>";
			oaNotify += "<small class='text-muted'>";
			oaNotify += ut.abbr(data[i].content, 30);
			oaNotify += "</small>";
			oaNotify += "</span></a>";
			//添加新消息
			addMsg(oaNotify);
		};

		//添加新消息
		window.addMsg = function(msg) {
			var $el = $('#oaNotifyList'),
				$n = $('#i-msg-count'),
				num = parseInt($n.data('num') || 0) + 1;
			$n.data('num', num);
			clearTimeout(count);
			count = setTimeout(function() {
				$n.fadeOut().fadeIn().text(num);
			}, 100);
			$("#oaNotifyCount").html("您已收到 <span class='count'>" + num +
				"</span> 条通知信息");
			$(msg).hide().prependTo($el).slideDown().css(
				'display', 'block');
		}

		// 更新消息信息
		window.updateMsg = function(el) {
			var $el = $('#oaNotifyList'),
				$n = $('#i-msg-count'),
				num = parseInt($n.data('num') || 0) - 1;
			$n.data('num', num);
			$n.fadeOut().fadeIn().text(num);
			$("#oaNotifyCount").html("您已收到 <span class='count'>" + num +
				"</span> 条通知信息");
			$(el).slideUp();
			setTimeout(function() {
				$(el).remove();
				_flag = $el.children('a:first').data('value');
				console.log("读取消息后末位Id为：" + _flag);
				//重新获取信息，填充已读空缺
				getNewOaNotify();
			}, 500);
			console.log("您已读取消息的ID为：" + $(el).data('value'));
		}
		
		// 初始化工作提醒
		function initWorkRemind() {
			var options = {
					dataUrl : $ctx + '/warnWorkRemind/findByMonth',
					addUrl : $ctx + '/warnWorkRemind/save',
					delUrl : $ctx + '/warnWorkRemind/delete'
				};
			var calendar = bc.init($('<div id="calendar" class="calendar"></div>'), options);
			$('#work-remind').popover({
				content : calendar,
				container : 'body',
				placement : 'bottom',
				html : true
			});
		}
	}(jQuery));
});
