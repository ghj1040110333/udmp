/**
 * Description: 业务页面处理
 * @author Seadon
 * @date 2016年3月1日
 *
 */

idefine(function(require, exports, module) {
  var _tool = require('udmp/lib/util_tool'),
    wt = window.top;

  //页面加载事件
  window.onload = function() {

    //iframe自适应
    setTimeout(function() {
      jQuery(window).resize();
    }, 300);

    //进度完成
    if(wt.i_web_pg.progress===100){
      wt.i_web_pg.finish();
    }
  };

  //火狐滚动事件
  document.body.addEventListener("DOMMouseScroll", function(event) {
    var height = event.detail > 0 ? 35 : -35;
    wt.setScroll(height);
  });

  //ie,chrome滚动事件
  window.onmousewheel = document.onmousewheel = function(e) {
    e = e || window.event;
    var height = e.wheelDelta < 0 ? 35 : -35;
    window.top.setScroll(height);
  };

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

  //列样式
  window.cellStyle = function() {
    return {
      classes: 'i-cell-hover'
    }
  };

  //页面提交方法
  window.aplusSubmit = function(submitUrl,type,callback){
    var errNum = UdmpForm.input.methods.validForm();
    if(errNum === 0){
      udmpAjax.get("../logintest/service",null,function(data){
        var contentData = data,
          inputData = _tool.serializeObject($("#searchForm"));

        //整合数据
        if(type!=="mix")
          contentData.bizBody = inputData;
        else
          contentData = jQuery.extend(true, contentData, inputData);

        //提交请求
        udmpAjax.postJson(submitUrl,JSON.stringify(contentData),callback||function(data){
          wt.showInfo("success", data.techHeader.bizResText);
        });
      })
    }else{
      wt.showInfo("error","表单验证出现 "+errNum+" 处错误，请检查！")
    }
  };

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

    //指定列绑定查看事件
    $('body').on('click', '.i-cell-hover', function(e) {
      (e, $(this).text());
    })

  }(jQuery));
})
