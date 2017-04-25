/**
 * Description:
 * @author Seadon
 * @date 2016年1月28日
 *
 */
;
(function($, window, undefined) {

  var $content = $("#content");

  //登录方法
  window.Login = {
    //提示信息
    showInfo: function(type, msg) {
      $(".message").remove();
      $('body').append("<div class='msg-" + type + " message'><h3>" + msg +
        "</h3></div>");
      $('.msg-' + type).animate({
        bottom: "0"
      }, 500);
      $('.message').click(function() {
        $(this).animate({
          bottom: -$(this).outerHeight()
        }, 500);
      });
    },
    //关闭信息
    clearInfo: function() {
      $(".message").animate({
        bottom: -$(".message").outerHeight()
      }, 500)
    }
  };

  $(document).ready(function() {

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
        username: {
          required: true
        },
        password: {
          required: true
        },
        legPerCod: {
          required: true
        },
        depCod: {
          required: true
        },
        opr: {
          required: true
        },
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
        legPerCod: {
          required: "请填写法人代码"
        },
        depCod: {
          required: "请填写机构代码"
        },
        opr: {
          required: "请填写柜员信息"
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
      },
      submitHandler: function(form){
        $("#loginForm").fadeOut(300);
        $('#container').addClass('form-success');
        setTimeout(function(){
          form.submit();
        },1600);
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
