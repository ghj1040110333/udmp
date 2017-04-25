/**
 * Description: 区域元素拖拽模块
 * @author Seadon
 * @date 2016年5月13日
 *
 */

idefine(function(require, exports, module) {
  (function($) {

    //坐标构造函数
    function Pointer(x, y) {
      this.x = x;
      this.y = y;
    };

    //位置偏移量
    function Position(left, top) {
      this.left = left;
      this.top = top;
    };

    //初始化组件
    exports.boxDrag = function(i) {
      this.init = function() {
          this.box = $(this).parent();
          $(this).attr("index", i).css({
            position: "absolute",
            left: this.box.position().left,
            top: this.box.position().top
          }).appendTo(".i-item-content");
          this.drag();
        },
        this.move = function(callback) {
          $(this).stop(true).animate({
            left: this.box.position().left,
            top: this.box.position().top
          }, 500, function() {
            if (callback) {
              callback.call(this);
            }
          });
        },
        this.collisionCheck = function() {
          var currentItem = this;
          var direction = null;
          $(this).siblings(".item").each(function() {
            if (
              currentItem.pointer.x > this.box.offset().left &&
              currentItem.pointer.y > this.box.offset().top &&
              (currentItem.pointer.x < this.box.offset().left +
                this.box
                .width()) &&
              (currentItem.pointer.y < this.box.offset().top + this
                .box
                .height())
            ) {
              if (currentItem.box.offset().top < this.box.offset().top) {
                direction = "down";
              } else if (currentItem.box.offset().top > this.box.offset()
                .top) {
                direction = "up";
              } else {
                direction = "normal";
              }
              this.swap(currentItem, direction);
            }
          });
        },
        this.swap = function(currentItem, direction) {
          if (this.moveing) return false;
          var directions = {
            normal: function() {
              var saveBox = this.box;
              this.box = currentItem.box;
              currentItem.box = saveBox;
              this.move();
              $(this).attr("index", this.box.index());
              $(currentItem).attr("index", currentItem.box.index());
            },
            down: function() {
              var box = this.box;
              var node = this;
              var startIndex = currentItem.box.index();
              var endIndex = node.box.index();;
              for (var i = endIndex; i > startIndex; i--) {
                var prevNode = $(".i-item-content .item[index=" + (
                  i -
                  1) + "]")[0];
                node.box = prevNode.box;
                $(node).attr("index", node.box.index());
                node.move();
                node = prevNode;
              }
              currentItem.box = box;
              $(currentItem).attr("index", box.index());
            },
            up: function() {
              var box = this.box;
              var node = this;
              var startIndex = node.box.index();
              var endIndex = currentItem.box.index();;
              for (var i = startIndex; i < endIndex; i++) {
                var nextNode = $(".i-item-content .item[index=" + (
                  i +
                  1) + "]")[0];
                node.box = nextNode.box;
                $(node).attr("index", node.box.index());
                node.move();
                node = nextNode;
              }
              currentItem.box = box;
              $(currentItem).attr("index", box.index());
            }
          }
          directions[direction].call(this);
        },
        this.drag = function() {
          var oldPosition = new Position(),
            oldPointer = new Pointer(),
            isDrag = false,
            currentItem = null;
          $(this).mousedown(function(e) {
            e.preventDefault();
            oldPosition.left = $(this).position().left;
            oldPosition.top = $(this).position().top;
            oldPointer.x = e.clientX;
            oldPointer.y = e.clientY;
            isDrag = true;
            currentItem = this;
            $(document).mousemove(function(e) {
              var currentPointer = new Pointer(e.clientX, e.clientY);
              if (!isDrag) return false;
              console.log('movemove')
              $(currentItem).css({
                "opacity": "0.8",
                "z-index": 999
              });
              var left = currentPointer.x - oldPointer.x +
                oldPosition.left;
              var top = currentPointer.y - oldPointer.y +
                oldPosition
                .top;
              $(currentItem).css({
                left: left,
                top: top
              });
              currentItem.pointer = currentPointer;
              currentItem.collisionCheck();
            });
            $(document).mouseup(function(e) {
              var _x = e.pageX - oldPointer.x,
                _y = e.pageY - oldPointer.y;

              //移除事件
              $(document).off('mousemove').off('mouseup');

              //误差一定范围内，判断点击事件
              if (_x > 5 || _x < -5 || _y > 5 || _y < -5) {
                isDrag = true;
              } else {
                isDrag = false;
              }

              if (!isDrag) return false;
              isDrag = false;
              //保存链接位置
              saveLinkSet();
              currentItem.move(function() {
                $(this).css({
                  "opacity": "1",
                  "z-index": 0
                });
              });
            });
          });
        }
      this.init();
    };

    //更新链接模块位置
    exports.boxUpdate = function(i) {
      $box = $('.i-item-content ul li').eq(i);
      $(this).css({
        left: $box.position().left,
        top: $box.position().top
      });
    };

  })(jQuery);
});
