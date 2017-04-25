/**
 * Description:首页加载脚本
 *
 * @author Seadon
 * @date 2017年2月14日
 *
 */

idefine(function(require, exports, module) {
  require('vue2');
  require('bootstrap');
  require('message');
  require('bootbox');
  require('toastr');
  require('lib/app/aplus_menu');
  //require('control');
  var _tool = require('udmp/lib/aplus_tool'),
      bodyDesk = $('#i-body-desk'),
      deskWrapper = $('#i-desk-previews'),
      deskPreviews = deskWrapper.children('li'),
      deskItem = deskPreviews.children('div'),
      projects = $('#i-desk-body'),
      asideMenu = $('#i-aside-menu'),
      iBody = $('#i-body'),
      navBody = $('#i-nav-body'),
      contentBody = $('#i-content-body'),
      numRandoms = 4,
      uniqueRandoms = [],
      menuData = [],
      animating = false;

  window.onload = function() {
    i_web_pg.finish();
    asideMenu.find('.i-nav-ul').wrap('<div class="i-nav-scroll">').css('height',$('body').height() - 168).end().hide();
  };

  var iHeader = new Vue({
    el: '#i-header',
    methods: {
      //返回首页
      goHome: function(){
        animating = false;
        contentBody.fadeOut(300);
        asideMenu.fadeOut(300);
        iBody.fadeOut(300);
        navBody.removeClass('nav-visible').fadeOut(300);
        slideToggleProjects(deskPreviews,-1,0,false);
      },
      //搜索菜单
      searchMenu: function(e){
        var _value = e.target.value;
        var menuArr = $.grep(menuData,function(i){
          return i.isShow == "1" && i.name.indexOf(_value) >= 0
        })
        dealMenu(menuArr,"333","搜索","");
        //加载搜索页面
        if(animating == false) {
          animating = true;
          iBody.show();
          contentBody.fadeOut(300);
          asideMenu.css({'visibility':'visible','z-index':'1'}).fadeIn(1200);
          navBody.addClass('nav-visible').find('.i-nav-menu .active').removeClass('active').end().fadeIn(1200);
          slideToggleProjects(deskPreviews,-1,0,true);
        } else {
          navBody.find('.i-nav-menu .active').removeClass('active');
          contentBody.fadeOut(300);
          navBody.fadeIn(1200);
        }
      }
    }
  });
  var iMenu = new Vue({
    el: '#i-banner',
    data: {
      items: [],
      menus: []
    },
    computed: {
      filterMenu: function(){
        var menu = this.menus[0].id;
        return this.items.filter(function(item){
          if(menu == "333"){
            return true;
          } else {
            var iArr = item.parentIds.split(',');
            return menu in iArr || menu == item.parentId;
          }
        })
      }
    }
  })

  $(document).ready(function(){
    //桌面背景加载
    deskItem.bgLoaded({
      afterLoaded: function(){
        showPreview(deskPreviews.eq(0));
      }
    });

    // 菜单加载
    asideMenu.multilevelpushmenu({
      containersToPush: [$( '#i-body' )],
      wrapperClass: 'i-menu-wrapper',
      menuInactiveClass: 'i-menu-active',
      preventItemClick: false
    });

    //tab页插件
    $('#i-frame-tabs').multabs({
    });

    //菜单目录点击事件
    $('#i-aside-menu a[href]').on('click',function(){
      var $content = $("#i-frame-tabs .tab-content .tab-pane.active"),
          contentWidth = $content.width();
      if($(this).find('.glyphicon-menu-left').length) {
        contentWidth += 40;
      } else{
        contentWidth -= 40;
      }

      $content.css('width',contentWidth);
    });
  });

  //菜单项触发事件
  navBody.on('click','.i-nav-menu > li',function(e){
    showMenu(e);
  });

  //获取菜单数据
  navBody.find(".i-nav-menu > li[data-id]").each(function(key,obj){
    var mid = $(obj).data('id');
    $.get($ctx+'/sys/menu/node?id='+mid,function(data){
      var strData = JSON.stringify(data);
      localStorage.setItem(mid,strData);
      menuData = menuData.concat(data);
    })
  });

  //菜单点击事件
  $('#i-aside-menu .glyphicon-menu-hamburger').on('click',function(){
    var _active = $(this).closest('.levelHolderClass'),
        _left = 0;

    setTimeout(function(){
      var navWidth = $('.i-menu-wrapper > .levelHolderClass').width(),
      	  bodyWidth = 'calc(100% - ' + (navWidth-2) + 'px)',
      	  bodyLeft = navWidth + 2;

      if(_active.length){
        _left = parseFloat(_active.css('margin-left'));
      }

      if(_left < 0) {
        bodyWidth = 'calc(100% - 38px)';
      }

      iBody.css({'width':bodyWidth,'left':bodyLeft});
    },430)

  });

  //触发收缩菜单
  //$('#i-aside-menu .glyphicon-menu-hamburger').trigger('click');
  let navLeft = $('.i-menu-wrapper > .levelHolderClass').width() + 2;
  $('#i-body').css('width','calc(100% - '+navLeft+'px)');

  //关闭模态层
  contentBody.on('click','.tab-pane.active .i-close-tab:first',function(){
    contentBody.find('.nav-tabs .active .i-close-tab').trigger('click');
  }).on('click','.tab-pane.active .i-close-tab:not(:first)',function(){
    var $modal = $(this).parents('.i-frame-modal');
    //之前的元素
    var prevNum = $modal.prevAll('.i-frame-modal').length,
        $prevModal = $modal.prev();
    //移除当前与之后的同级元素
    $modal.nextAll('.i-frame-modal').remove().end().remove();
    //模态层全部删除后，移除遮罩层
    if(!prevNum) {
      $('.i-frame-content .i-frame-cover').remove();
    } else {
      $prevModal.find('.i-frame-cover').remove();
    }

    setTabWidth();
  }).on('click','.i-modal-footer .btn-default',function(){
    hideModal();
  })

  // 显示桌面项
  function showPreview(deskPreview){
    if(deskPreview.length > 0 ){
      setTimeout(function(){
        deskPreview.addClass('bg-loaded');
        showPreview(deskPreview.next());
        $('.i-previews-body').show();
      }, 150);
    }
  }

  //打开业务页面
  function openProject(projectPreview) {
    var projectIndex = projectPreview.index();
    slideToggleProjects(deskPreviews, projectIndex, 0, true);
  }

  //切换业务页面
  window.slideToggleProjects = function(projectWrapper, projectIndex, index, bool) {
    if(index == 0) createArrayRandom();
    if(projectIndex != -1 && index == 0) index = 1;

    var randomProjectIndex = makeUniqueRandom();
    if(randomProjectIndex == projectIndex) randomProjectIndex = makeUniqueRandom();

    if(index < numRandoms - 1) {
      projectWrapper.eq(randomProjectIndex).toggleClass('slide-out', bool);
      setTimeout(function(){
        slideToggleProjects(projectWrapper, projectIndex, index + 1, bool);
      }, 150);
    } else if (index == numRandoms - 1) {
      projectWrapper.eq(randomProjectIndex).toggleClass('slide-out', bool).one('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend', function(){
        if(projectIndex != -1) {
          projects.children('li.selected').addClass('content-visible');
          projectWrapper.eq(projectIndex).addClass('slide-out').removeClass('selected');
        } else if (navBody.hasClass('nav-visible') && bool) {
          navBody.addClass('nav-clickable');
        }
        projectWrapper.eq(randomProjectIndex).off('webkitTransitionEnd otransitionend oTransitionEnd msTransitionEnd transitionend');
        animating = false;
      })
    }
  }

  //创建循环数组
  function createArrayRandom() {
    uniqueRandoms.length = 0;
    for(var i = 0; i < numRandoms; i++) {
      uniqueRandoms.push(i);
    }
  }

  //随机确定值
  function makeUniqueRandom() {
    var index = Math.floor(Math.random() * uniqueRandoms.length),
        val = uniqueRandoms[index];
    uniqueRandoms.splice(index, 1);
    return val;
  }

  //显示菜单方法
  function showMenu(e){
    var $this = $(e.target);
    $this.is('li') || ($this = $this.closest('li'));
    var mid = $this.data('id'),
        mname = $this.data('name'),
        micon = $this.data('icon'),
        $active,data;

    //更改节点样式
    $active = $this.siblings('.active');
    $active && $active.removeClass('active');
    $this.addClass('active');

    data = JSON.parse(localStorage.getItem(mid));
    dealMenu(data,mid,mname,micon);
  }

  //处理菜单数据
  function dealMenu(data,mid,mname,micon){
    var count = 0;
    //二级菜单数据
    var menuData = $.grep(data,function(obj){
      if(obj.isShow === "1" && obj.parentId == mid && obj.childCount === 0) {
        count += 1;
      }
      return obj.isShow === "1" && obj.parentId == mid && obj.childCount !== 0;
    });
    if(mid === "333") {
      count = 3;
    }
    if (count > -1) {
      menuData.unshift({id:mid,name:mname,icon:micon});
    }

    var itemData = $.grep(data,function(obj){
      return obj.isShow === "1"
    });
    iMenu.items = itemData;
    iMenu.menus = menuData;
    setTimeout(function(){$('#i-banner-content li:eq(0)').show();},0)
    $('#i-banner').show();
  }

  //动态设置页面区域的宽度
  function setTabWidth(){
    var activeTab = contentBody.find('.tab-pane.active'),
        frameWidth = activeTab.find('.i-frame-panel').width(),
        modalWidth = 0,
        menuWidth = 0;

    activeTab.find('.i-modal-panel').each(function(i,o){
      modalWidth += o.offsetWidth;
    });
    //菜单动态宽度
    menuWidth = $('#i-aside-menu .i-menu-active').length*40;
    //打开窗口宽度
    letWidth = $('.i-frame-modal').length*2;
    activeTab.width(frameWidth + modalWidth + menuWidth + letWidth);
    return frameWidth + modalWidth + menuWidth;
  }

  /* --------------------------------

   公用方法

  -------------------------------- */
  //滚动空方法
  window.setScroll = function() {
    $.noop();
  }

  //显示Loading窗口
  window.showLoading = function() {
    $("<div>", {
      "class": "modal-backdrop fade in"
    }).appendTo('body');
    $("#loadingTips .loading-info").text("努力加载中...");
    $("#loadingPage").css("opacity", "1").show();
  };

  //隐藏Loading窗口
  window.hideLoading = function() {
    var $back = $(".modal-backdrop.in"),
      _true = $('body').is('.modal-open');
    $("#loadingTips .loading-info").text("数据加载完成");
    _true && $back.length > 1 && $back.last().fadeOut(1000).remove();
    !_true && $back.fadeOut(1000).remove();
    $("#loadingPage").fadeOut(1000);
  }

  //提示信息调用
  window.showInfo = function(type, msg) {
    msg = msg.replace(/<br\/>/g,'\n');
      _tool.showMsg(type,msg,function(){var callback = $('body').data('hideModal'); if(callback) {callback();$('body').data('hideModal',null);}});
  };

  //确认提示
  window.showConfirm = function(msg, callback) {
    _tool.showConfirm(msg,callback);
  };

  //提示框基类
  window.showBase = function(title,text,type,confirmtext,canceltext,showconfirm,showcancel,isconfirm,iscancel,callback,error,timer) {
    _tool.showBase(title,text,type,confirmtext,canceltext,showconfirm,showcancel,isconfirm,iscancel,callback,error,timer);
  };

  //关闭确认提示
  window.hideConfirm = function(msg, callback) {
    _tool.hideConfirm();
  };

  //确认信息
  window.showMsg = function(type, msg) {
    _tool.showMsg(type,msg);
  }

  //prompt
  window.showPrompt = function(type, title, callback) {
    _tool.showPrompt(type,title,callback);
  }

  //加载模态窗口
  window.showModal = function(url, html, params, buttons, callback, loadfunc) {
    var $content = $("#i-frame-tabs .tab-content .tab-pane.active"),
        $modals = $content.find('.i-frame-modal'),
        $modal;

    !$modals.length && $content.find('.i-frame-content').append('<div class="i-frame-cover"></div>');
    $modals.length && $modals.find('.i-modal-panel').append('<div class="i-frame-cover"></div>');
    if (params) {
      $content.append('<div class="i-frame-modal animated fadeInRight">'+
        '<div class="i-title-panel">'+
          '<div class="i-title-name">'+params.title+'</div>'+
          '<div class="i-title-operate"><i class="i-close-tab glyphicon glyphicon-remove"></i></div>'+
        '</div>'+
        '<div class="i-modal-panel" style="width:'+params.width+'px"></div>'+
      '</div>');

      $modal = $content.find('.i-modal-panel').last();
    }

    if (callback) {
      $modal.data("callback", callback);
    }

    if (url) {
      $.get(url, function(data) {
        setModalShow($modal, data);
        if(loadfunc) {
           loadfunc();
        }
      });
    } else {
      setModalShow($modal, html);
    }
  }

  //显示模态框
  function setModalShow($modal, html) {
    var scrollWidth = setTabWidth(),
        $frameModal = $modal.closest('.i-frame-modal');

    //填充窗体内容
    $modal.html(html);

    //替换自定义按钮组
    var $buttons = $modal.find(".my-buttons");
    if ($buttons.length) {
      $('<div>',{
        class:"i-modal-footer"
      }).append($buttons.children()).appendTo($frameModal);
      $buttons.remove();
    } else {
      var defaultButton = '<button type="button" class="btn i-btn-ok btn-confirm">确定</button>'+
                '<button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">关闭</button>';

      $('<div>',{
        class:"i-modal-footer"
      }).append(defaultButton).appendTo($frameModal);
    }
    setTabWidth();
    contentBody.animate({
      scrollLeft: scrollWidth
    }, 2000);
  }

  //设置窗体数据
  window.setModalData = function(name, value, $modal) {
    if(!$modal) {
      $modal = $("#i-frame-tabs .tab-content .tab-pane.active").find('.i-modal-panel').last();
    }
    $modal.data(name, value);
  }

  //获取窗体数据
  window.getModalData = function(name,$modal) {
    if(!$modal) {
      $modal = $("#i-frame-tabs .tab-content .tab-pane.active").find('.i-modal-panel').last();
    }
    return $modal.data(name);
  }

  //关闭模态框
  window.hideModal = function(isHide) {
    var $content = $("#i-frame-tabs .tab-content .tab-pane.active"),
        $frame = $content.find('.i-frame-modal').last(),
        $modal = $frame.find('.i-modal-panel'),
        prevNum = $frame.prevAll('.i-frame-modal').length,
        $prevFrame = $frame.prev();

    //调用回调函数
    if($modal.data("callback")){
      $modal.data("callback")($modal);
    }

    //移除当前层与父级遮罩，设置宽度
    $frame.remove();
    if(!prevNum) {
      $('.i-frame-content .i-frame-cover').remove();
    } else {
      $prevFrame.find('.i-frame-cover').remove();
    }
    setTabWidth();
  }

  //关闭模态层方法
  window.callModal = function() {
    $('body').data('hideModal',hideModal);
  }

  //获取URL地址参数
  window.getQueryString = function(name, url) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    if (!url || url == "") {
      url = window.location.search;
    } else {
      url = url.substring(url.indexOf("?"));
    }
    r = url.substr(1).match(reg)
    if (r != null) return unescape(r[2]);
    return null;
  }

});
