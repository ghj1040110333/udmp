/**
 * Description: 首页模块拖拽组件
 * @author Seadon
 * @date 2016年5月9日
 *
 */
idefine(function(require, exports, module) {
  (function($) {

    //定义拖拽对象
    var Drag = function(ele, opt) {
        this.$ele = ele;
        this.pX = 0;
        this.pY = 0;
        this.defaults = {
          target: '.i-panel-body',
          hander: 1,
          pDown: false,
          pClick: false,
          oX: 0,
          oY: 0
        };
        this.options = $.extend({}, this.defaults, opt);
        if (this.options.hander == 1) {
          this.hander = this.$ele;
        } else {
          this.hander = this.$ele.find(this.options.hander);
        }
      },
      pPos, pTarget, pTime, pMove, pDown, pArr;

    //拖拽模块方法
    Drag.prototype = {
      init: function() {

        var _self = this,
          $this = this.$ele,
          $that = $this.closest('.tab-content'),
          $hander = this.hander;

        //初始化容器，拖拽元素
        $that.css({
          position: "relative",
          overflow: "hidden"
        });

        //鼠标按下触发事件
        $hander.on('mousedown', function(e) {
          //左键按下有效，其它无效
          if (e.which == 1) {
            _self.mouseDown(e);
            //阻止默认事件
            return false;
          }
        });
      },
      mouseDown: function(e) {
        var _placeholder,
          _self = this,
          $this = this.$ele,
          $that = $this.closest('.tab-content'),
          $hander = this.hander,
          _opt = this.options,
          _pos = $this.position(),
          _width = $this.width();

        //设置状态值
        _opt.pDown = true;
        _opt.pClick = true;
        _opt.oX = e.pageX; //按下时坐标
        _opt.oY = e.pageY;
        _self.pX = _opt.oX - _pos.left;
        _self.pY = _opt.oY - _pos.top;
        pTarget = $this.find(_opt.target) || true;

        //按住一定时间后，触发拖动
        pTime = setTimeout(function() {
          pArr = [];
          $that.children('.row').css({
            zIndex: 0
          });
          $hander.addClass('i-drag-cursor');

          //生成预览块
          _placeholder = $('<div></div>');
          _placeholder.addClass('i-placeholder').css({
            width: $this.width(),
            height: $this.height()
          })
          $this.after(_placeholder);
          $this.css({
            position: "absolute",
            opacity: 0.9,
            zIndex: 1,
            width: _width
          });

          //记录坐标信息
          $('.i-chart-warn .panel').each(function(i) {
            var _width = this.offsetWidth,
              _height = this.offsetHeight,
              _offset = $(this).offset(),
              position = {};
            position.w = _width;
            position.h = _height;
            position.x = _offset.left + _width / 2;
            position.y = _offset.top + _height / 2;
            pArr.push(position);
          });

          //判断列表左右侧为空
          var $panel = $('.i-chart-warn>.col-md-6:has(.panel)');
          if ($panel.length < 2) {
            pPos = $panel.children(':first').offset().left;
          } else {
            pPos = false;
          }

        }, 300);

        //鼠标移动事件
        $that.on('mousemove', function(e) {
          _self.mouseMove(e);
        });

        //鼠标抬起事件
        $hander.on('mouseup', function(e) {
          _self.mouseUp(e);
        });
      },
      mouseUp: function(e) {
        var _self = this,
          $this = this.$ele,
          $that = $this.closest('.tab-content'),
          $hander = this.hander,
          _opt = this.options,
          _x = e.pageX - _opt.oX,
          _y = e.pageY - _opt.oY;

        //设置状态值
        _opt.pDown = false;
        pTarget = false;
        pTime && clearTimeout(pTime);

        //移除事件
        $that.off('mousemove');
        $hander.removeClass(
          'i-drag-cursor').off('mouseup');

        //误差一定范围内，判断点击事件
        if (_x > 5 || _x < -5 || _y > 5 || _y < -5)
          _opt.pClick = false;
        $this.removeAttr('style');

        //点击事件
        if (_opt.pClick) {
          var pbody = $hander.next(".i-panel-body");
          pbody.slideToggle(
            "fast");
          return;
        };

        //判断拖动的类型
        if ($this.parent().is('.col-md-12')) {
          $('.i-placeholder').replaceWith($this.closest(
            '.i-chart-warn'));
        } else {
          $('.i-placeholder').replaceWith($this);
        }

        //保存用户数据
        saveUserSet();
      },
      mouseMove: function(e) {
        console.log('movemove')
        var _opt = this.options,
          $this = this.$ele,
          _width = $(document).width() / 2,
          _x = e.pageX,
          _y = e.pageY,
          _left = _x - this.pX,
          _top = _y - this.pY;
        pMove && clearTimeout(pMove);
        if (_opt.pDown) {
          $this.css({
            left: _left,
            top: _top
          });
          pMove = setTimeout(function() {

            //如果移动的位置列为空，则把位置填充到列父级上
            if (pPos) {
              if (pPos > _width && _x < pPos) {
                Drag.prototype.updatePos($(
                  '.i-chart-warn>.col-md-6:eq(0)'), 2);
              } else {
                Drag.prototype.updatePos($(
                  '.i-chart-warn>.col-md-6:eq(1)'), 2);
              }
            };

            //根据坐标数据遍历位置
            pArr && $.each(pArr, function(i, n) {

              //判断是否位于区域中
              var xTrue = Math.abs(_x - n.x) < n.w / 2,
                yTrue = Math.abs(_y - n.y) < n.h / 2;

              if (xTrue && yTrue) {
                if (_y - n.y < 0) {
                  if ($this.parent().is('.col-md-12')) {
                    Drag.prototype.updatePos($(
                      '.i-chart-warn .panel').eq(i).closest(
                      '.i-chart-warn'), 0);
                  } else {
                    if (!$('.i-chart-warn .panel').eq(i).parent()
                      .is('.col-md-12')) {
                      Drag.prototype.updatePos($(
                          '.i-chart-warn .panel').eq(i),
                        0);
                    }
                  }
                } else {
                  if ($this.parent().is('.col-md-12')) {
                    Drag.prototype.updatePos($(
                      '.i-chart-warn .panel').eq(i).closest(
                      '.i-chart-warn'), 1);
                  } else {
                    if (!$('.i-chart-warn .panel').eq(i).parent()
                      .is('.col-md-12')) {
                      Drag.prototype.updatePos($(
                          '.i-chart-warn .panel').eq(i),
                        1);
                    }
                  }
                }
              };
            });
          }, 100);
          //this.updatePos($('.i-chart-warn .panel:eq(2)'));
        };
      },
      updatePos: function(obj, dr) {
        var $ph = $('.i-placeholder');
        if (dr === 0) {
          obj.before($ph);
        } else if (dr === 1) {
          obj.after($ph);
        } else {
          obj.append($ph);
        };
      }
    };

    //插件信息
    $.fn.dragging = function(options) {
      var dg = new Drag(this, options);
      return dg.init();
    };
  })(jQuery);
});
