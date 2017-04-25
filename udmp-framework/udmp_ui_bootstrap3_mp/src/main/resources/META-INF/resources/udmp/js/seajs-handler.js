/**
 * Description: 业务页面处理
 * @author Seadon
 * @date 2016年3月1日
 *
 */

idefine(function(require, exports, module) {
	require('bootstrap');
	var _tool = require('udmp/params/util_tool');

	//页面加载事件
	window.onload = function() {
		//关闭滚动条
		window.parent.i_web_pg.finish();

		//iframe自适应
		setTimeout(function() {
			jQuery(window).resize();
			jQuery('.i-form-panel').slideUp(500);
		}, 300);

	};

	//火狐滚动事件
	document.body.addEventListener("DOMMouseScroll", function(event) {
		var height = event.detail > 0 ? 35 : -35;
		window.top.setScroll(height);
	});

	//ie,chrome滚动事件
	window.onmousewheel = document.onmousewheel = function(e) {
		e = e || window.event;
		var height = e.wheelDelta < 0 ? 35 : -35;
		window.top.setScroll(height);
	};

	/*
	 * 公用方法
	 */

	//码值转换
	window.nameFormatter = function(value) {
		var type = this.type;
		return getDictName[type][value];
	};

	//时间简单格式化
	window.timeFormatter = function(value) {
		if (!!value) {
			var time_arr = value.split(' ');
			return time_arr[0];
		}
	};

	//表格截取字符串
	window.strFormatter = function(value) {
		var _ovalue = value || '',
			_length = this.len,
			_value = _tool.abbr(_ovalue, _length),
			result = '<span data-toggle="tooltip" data-placement="bottom" title="' +
			value + '">' + _value + '</span>';
		return result;
	};

	//等级自定义格式化
	window.diyFormatter = function(value) {
		var type = this.type,
			_value = getDictName[type][value];
		switch (type) {
			case "WARN_LEVEL_CD":
				var i_level = '<i class="git">&#xe61f;</i>';
				switch (value) {
					case "01":
						return '<div class="i-red">' + i_level + _value + '</div>';
					case "02":
						return '<div class="i-orange">' + i_level + _value + '</div>';
					case "03":
						return '<div class="i-yellow">' + i_level + _value + '</div>';
					default:
						return '<div>ERROR</div>';
				};
			case "ATTENTION_FLAG_CD":
				if (value === "01") {
					return '<div class="i-orange"><i class="git">&#xe62a;</i>' + _value +
						'</div>';
				} else if (value === "02") {
					return '<div class="i-red"><i class="git">&#xe623;</i>' + _value +
						'</div>';
				} else {
					return '<div>ERROR</div>';
				};
			case "NOTE_STATUS_CD":
				switch (value) {
					case "01":
						return '<div class="i-yellow"><i class="git">&#xe620;</i>' + _value +
							'</div>';
					case "05":
						return '<div class="i-orange"><i class="git">&#xe621;</i>' + _value +
							'</div>';
					case "06":
						return '<div class="i-red"><i class="git">&#xe629;</i>' + _value +
							'</div>';
					default:
						return '<div>ERROR</div>';
				};
			case "IS_FLAG":
				if (value === "0") {
					return '<div class="i-red"><i class="git">&#xe628;</i>' + _value +
						'</div>';
				} else if (value === "1") {
					return '<div class="i-orange"><i class="git">&#xe62b;</i>' + _value +
						'</div>';
				} else {
					return '<div>ERROR</div>';
				};
			case "ATTENTION_STATUS_CD":
				if (value === "01") {
					return '<div class="i-red"><i class="git">&#xe622;</i>' + _value +
						'</div>';
				} else if (value === "02") {
					return '<div class="i-grey"><i class="git">&#xe627;</i>' + _value +
						'</div>';
				} else {
					return '<div>ERROR</div>';
				};
			case "GGRP_TP_CD":
				switch (value) {
					case "01":
						return '<div class="i-yellow"><i class="git">&#xe61f;</i>' + _value +
							'</div>';
					case "02":
						return '<div class="i-orange"><i class="git">&#xe61f;</i>' + _value +
							'</div>';
					case "03":
						return '<div class="i-red"><i class="git">&#xe61f;</i>' + _value +
							'</div>';
					case "04":
						return '<div class="i-grey"><i class="git">&#xe61f;</i>' + _value +
							'</div>';
					default:
						return '<div>ERROR</div>';
				};
			case "INDEX_STATUS_CD":
				switch (value) {
					case "01":
						return '<div class="i-yellow"><i class="git">&#xe625;</i>' + _value +
							'</div>';
					case "02":
						return '<div class="i-orange"><i class="git">&#xe626;</i>' + _value +
							'</div>';
					case "03":
						return '<div class="i-grey"><i class="git">&#xe624;</i>' + _value +
							'</div>';
					default:
						return '<div>ERROR</div>';
				};
			case "MODEL_STATUS_CD":
				switch (value) {
					case "01":
						return '<div class="i-yellow"><i class="git">&#xe625;</i>' + _value +
							'</div>';
					case "02":
						return '<div class="i-orange"><i class="git">&#xe626;</i>' + _value +
							'</div>';
					case "03":
						return '<div class="i-grey"><i class="git">&#xe624;</i>' + _value +
							'</div>';
					default:
						return '<div>ERROR</div>';
				};
			case "BASE_STATUS":
				switch (value) {
					case "03":
						return '<div class="i-yellow"><i class="git">&#xe625;</i>' + _value +
							'</div>';
					case "01":
						return '<div class="i-orange"><i class="git">&#xe626;</i>' + _value +
							'</div>';
					case "02":
						return '<div class="i-grey"><i class="git">&#xe624;</i>' + _value +
							'</div>';
					default:
						return '<div>ERROR</div>';
				};
			case "EXTERNAL_STATUS_CD":
				switch (value) {
					case "01":
						return '<div class="i-yellow"><i class="git">&#xe625;</i>' + _value +
							'</div>';
					case "02":
						return '<div class="i-orange"><i class="git">&#xe62b;</i>' + _value +
							'</div>';
					case "03":
						return '<div class="i-grey"><i class="git">&#xe628;</i>' + _value +
							'</div>';
					default:
						return '<div>ERROR</div>';
				};
			default:
				return '<div>ERROR</div>';
		}
	}

	//列样式
	window.cellStyle = function() {
		return {
			classes: 'i-cell-hover'
		}
	}

	//页面加载事件
	(function($) {
		//iframe载入事件
		var i_frame = $(window.parent.document).find(
				'.tab-content .tab-pane:visible iframe'),
			full_screen = $(window.parent.document).find('#fullScreen');
		$(window).resize(function() {
			var elem = $('body');
			i_frame.css({
				height: elem.outerHeight(true) < 450 ? 450 : elem.outerHeight(true)
			});
			full_screen.css('z-index') === "1001" && full_screen.find('iframe').css({
				height: elem.outerHeight(true) < 450 ? 450 : elem.outerHeight(true)
			});
		});

		//标题栏收起
		$(".i-title.i-query").on('click', function() {
			var that = $(this);
			$(".i-form-panel,.i-knowledge-panel").slideToggle("fast", function() {
				var els = $(".i-rt>span", that);
				if (els.is(".glyphicon-menu-down"))
					els.removeClass("glyphicon-menu-down").addClass(
						"glyphicon-menu-up");
				else
					els.removeClass("glyphicon-menu-up").addClass(
						"glyphicon-menu-down");
				$("body").resize();
			});
		});

		//指定列绑定查看事件
		$('#contentTable').on('click', '.i-cell-hover', function(e) {
			viewDetail(e, $(this).text());
		})

		/*
		 * 业务页面公用方法
		 */

		//手动验证表单
		window.validateForm = function($form) {
			var $form = jQuery($form);
			var validator = $form.data("bootstrapValidator");
			if (validator) {
				validator.validate();
				return validator.isValid();
			} else
				return true;
		}

		//将form表单元素的值序列化成对象
		window.serializeObject = function($form) {
			var o = {};
			$.each($form.serializeArray(), function(index) {
				if (o[this['name']]) {
					o[this['name']] = o[this['name']] + "," + this['value'];
				} else {
					o[this['name']] = this['value'];
				}
			});
			return o;
		};
	}(jQuery));
})
