/**
 * Description:
 * @author Seadon
 * @date 2016年1月28日
 *
 */
;
(function($, window, undefined) {

	var _speed = 4000,
		_begin = 400,
		$content = $("#content"),
		_dest = $content.width() - 300,
		_padd = 10,
		_cheight = $(window).height() - 172;

	//图片高度处理
	_cheight < 550 && $(".login-bg").height(_cheight);

	//登录方法
	window.Login = {
		//提示信息
		showInfo: function(type, msg) {
			$(".message").remove();
			$('body').append("<div class='msg-" + type + " message'><h3>" + msg +
				"</h3></div>");
			$('.msg-' + type).animate({
				top: "0"
			}, 500);
			$('.message').click(function() {
				$(this).animate({
					top: -$(this).outerHeight()
				}, 500);
			});
		},
		//关闭信息
		clearInfo: function() {
			$(".message").animate({
				top: -$(".message").outerHeight()
			}, 500)
		},
		//布局自适应
		gLayout: function() {
			var _height = document.body.clientHeight,
				bheight = _height - 123 - 60;
			$("#container").css("height", bheight)
		},
		//随机生成字符串
		randomStr: function(flag, min, max) {
			var str = "",
				range = min,
				arr = ['0', '1'];
			if (flag) {
				range = Math.round(Math.random() * (max - min)) + min;
			}
			for (var i = 0; i < range; i++) {
				_pos = Math.round(Math.random() * (arr.length - 1));
				str += arr[_pos];
			}
			return str;
		},
		//返回元素数组
		makeArr: function(cd) {
			var _self = this,
				_ele, _num,
				arr = [];
			cd ? _num = Math.floor(Math.random() * 3) + 4 : _num = Math.round(Math.random() *
				8) + 7;
			for (var i = 0; i < _num; i++) {
				if (cd === undefined)
					_ele = _self.randomStr(true, 7, 23);
				else {
					var _z = (Math.random() * 0.5 + 0.3).toFixed(2);
					_ele = $(".o-circle").clone().addClass("cp").data("zoom", _z);
				}
				arr.push(_ele);
			}
			return arr;
		},
		//添加页面元素
		drawLay: function(arr, z) {
			var _self = this,
				$contain = $("#container"),
				str = "",
				_width = $contain.width(),
				_height = $contain.height(),
				_img = $(".login-bg").height() || 549;

			$.each(arr, function(i, n) {
				var _x = Math.ceil(Math.random() * _width * 0.05) + 365,
					_y = Math.floor(Math.random() * _img) + (_height - _img) / 2,
					_speedx = Math.round(Math.random() * 5000) + _speed,
					_size = Math.round(Math.random() * 8) + 12;
				if (z === undefined) {
					$div = $("<div>").addClass("l-info").css({
						"right": _x,
						"top": _y,
						"font-size": _size
					}).data({
						"speed": _speedx,
						"size": _size
					}).text(n);
				} else {
					var _z = n.data("zoom"),
						_x = Math.ceil(Math.random() * _width * 0.5),
						_y = Math.floor(Math.random() * 0.2 * _img / i) + _img / i * 0.8,
						_speedx = Math.round(Math.random() * -2000) + _speed;
					$div = n.css({
						"left": _x / _z + 300,
						"top": _y,
						"zoom": _z,
						"-moz-transform": "scale(" + _z + ")"
					}).data({
						"speed": _speedx
					});
				}
				$("#i-element").append($div);
				//str += $div.get(0).outerHTML;
			});
			//$("#i-element").append(str);

			z ? $(".o-circle").each(function(i) {
				_self.floatEle($(this), i)
			}) : $(".l-info").each(function(i) {
				_self.floatEle($(this));
			});

		},
		//元素流动效果
		floatEle: function(div, x) {
			if (x === undefined) {
				var _right = parseFloat(div.css("right") || div.prop("offsetRight")),
					_speedx = div.data("speed") || _speed;
				if (_right == _dest) {
					div.css({
						"right": _begin + "px",
						"opacity": 1
					}).fadeIn(1000);
				}
				div.animate({
					right: _dest + "px",
					opacity: 0.3
				}, _speedx, function() {
					div.delay(300).queue(function() {
						div.hide();
						Login.floatEle(div);
						div.dequeue();
					});
				}).animate({
					opacity: 0
				}, 'slow');
			} else {
				var _padding = Math.round(Math.random() * 15) + 20,
					_speedx = div.data("speed") || _speed;
				(function() {
					var circle = {
						"padding": _padding
					}
					if (div.css('padding') == _padd + "px") {
						circle.padding = _padding;
					} else {
						circle.padding = _padd;
					}
					div.animate(circle, _speedx, arguments.callee);
				})()
			}
		}
	};

	//生成动态元素
	function run() {
		var i_str = Login.makeArr(),
			i_circle = Login.makeArr(true);
		Login.drawLay(i_circle, true);
		Login.drawLay(i_str);
	}

	//加载完成后执行
	window.onload = function() {
		//run();
	}

	$(document).ready(function() {

		//监听窗口变化事件
		/*$(window).resize(function() {
			var _width = $('body').width();
			_dest = $content.width() - 300;
			$('.l-info,.o-circle.cp').stop(true).remove();
			if (_width > 768) run();
		});*/

		//表单提交返回错误信息弹出
		if ($("#loginError").html()) {
			Login.showInfo('error', $("#loginError").html());
			setTimeout(function() {
				window.i_show_error = undefined;
				Login.clearInfo();
			}, 3000);
		}

		//表单验证
		$("#loginForm").validate({
			rules: {
				validateCode: {
					remote: cpath + "/servlet/validateCodeServlet"
				}
			},
			messages: {
				username: {
					required: "请填写用户名"
				},
				password: {
					required: "请填写密码"
				},
				validateCode: {
					remote: "验证码不正确",
					required: "请填写验证码"
				}
			},
			errorPlacement: function(error, element) {
				//避免验证控件频繁触发
				if (typeof(window.i_show_error) == "undefined") {
					var _err = error[0].innerText ? error[0].innerText : error[0].innerHTML;
					window.i_show_error = true;
					Login.showInfo('error', _err);
					setTimeout(function() {
						window.i_show_error = undefined;
						Login.clearInfo();
					}, 3000);
				}
			}
		});
	});

	// 如果在框架或在对话框中，则弹出提示并跳转到首页
	if (self.frameElement && self.frameElement.tagName == "IFRAME" || $('#left')
		.length >
		0 || $('.jbox').length > 0) {
		window.top.showInfo('error', '未登录或登录超时。请重新登录，谢谢！');
		window.top.location = ctx;
	}
})(jQuery, window);
