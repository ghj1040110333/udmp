<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="renderer" content="webkit">
  <meta http-equiv="Content-Type" content="text/html;charset=utf-8" />
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta http-equiv="Cache-Control" content="public,no-cache">
  <meta http-equiv="Expires" content="0">
  <title>${fns:getConfig('productName')}</title>
  <link rel="icon" href="${ctxStatic}/favicon.ico" type="image/x-icon" />
  <link rel="shortcut icon" href="${ctxStatic}/favicon.ico" type="image/x-icon" />
  <link rel="bookmark" href="${ctxStatic}/favicon.ico" type="image/x-icon" />
  <link href="${ctxStatic}/lib/bootstrap3/css/bootstrap.css" type="text/css" rel="stylesheet" />
  <link rel="stylesheet" href="${ctxStatic}/css/animate.css" media="screen" charset="utf-8">
  <link rel="stylesheet" href="${ctxStatic}/lib/message/sweetalert.css" media="screen" charset="utf-8">
  <link rel="stylesheet" href="${ctxUdmp}/params/aplus_index.css" media="screen" charset="utf-8">
  <script type="text/javascript" src="${ctxStatic}/bin/sea.js"></script>
  <script type="text/javascript" src="${ctxStatic}/bin/seajs-config.js"></script>
  <script type="text/javascript" src="${ctxUdmp}/bin/ajax.js"></script>
  <script type="text/javascript">
    var $ctx = '${ctx}',
        udmpAjax = new udmpAjax();

  	seajs.use('aplus_plugin',function(){
      jQuery.PageLoading({ sleep: 1000 });
      window.loginName = "${fns:getUser().loginName}";
      window.$ = jQuery;
    });
  </script>
  <!--[if lt IE 9]>
    <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
  <![endif]-->
</head>
<body>
  <div id="i-html">
    <header id="i-header">
      <%-- LOGO --%>
      <div class="i-header-logo">
        <a href="#" @click="goHome">
          <img src="${ctxStatic}/images/logo.png" alt="${fns:getConfig('productName')}" />
        </a>
      </div>
      <%-- 菜单搜索按钮 --%>
      <div class="i-header-left hidden-xs">
        <%-- 搜索菜单 --%>
        <div class="i-nav-search" >
          <input class="form-control i-menu-search" placeholder="搜索菜单项" @keyup="searchMenu">
        </div>
      </div>
      <%-- 个人信息 --%>
      <div class="i-header-right">
        <ul class="nav navbar-nav navbar-right hidden-xs i-drop-down">
          <li class="dropdown">
            <a class="dropdown-toggle user-img" data-toggle="dropdown" href="#">
              ${fns:getUser().name}&nbsp;|&nbsp;${fns:getUser().office.name}
              <img src="${ctxStatic}/images/userinfo.png" class="i-user-img img-circle"/>
            </a>
            <ul class="dropdown-menu animated fadeInRight">
              <li>
                <span class="arrow top"></span>
                <a data-url="/sys/user/info" data-menu="29">
                  <span class="glyphicon glyphicon-user"></span>
                  个人信息
                </a>
              </li>
              <li>
                <a data-url="/sys/user/modifyPwd" data-menu="30">
                  <span class="glyphicon glyphicon-lock"></span>
                  修改密码
                </a>
              </li>
              <li>
                <a href="${ctx}/logout" title="退出登录">
                  <span class="glyphicon glyphicon-log-out"></span>
                  退出
                </a>
              </li>
            </ul>
          </li>
        </ul>
      </div>
    </header>

    <main id="i-container">
      <%-- 桌面 --%>
      <section id="i-body-desk">
        <ul id="i-desk-previews">
          <li>
            <div>
              <div class="i-previews-container">
                <div class="i-previews-body clearfix animated fadeInUp">
                  <div class="i-desk-block" data-menu="36bc0ede270f4353ad47f203bf63f906" data-url="/counter/T1101" title="放款">
                    <span><img class="img-circle img-responsive" src="static/images/desk/loan.jpg"></span>
                    <p>放款</p>
                  </div>
                  <div class="i-desk-block" data-menu="f202ea46eefc480680a91b062e24cdaf" data-url="/counter/T1102" title="还款">
                    <span><img class="img-circle img-responsive" src="static/images/desk/receivables.jpg"></span>
                    <p>还款</p>
                  </div>
                </div>
              </div>
            </div>
          </li>

          <li>
            <div>
              <div class="i-previews-container">
                <div class="i-previews-body clearfix animated fadeInUp">
                  <div class="i-desk-block" data-menu="3409cf003b944ff3a5e2c1f29d29ca2a" data-url="/manage/T1405" title="还款方式变更">
                    <span><img class="img-circle img-responsive" src="static/images/desk/type.jpg"></span>
                    <p>还款方式变更</p>
                  </div>
                  <div class="i-desk-block" data-menu="9574c0a0f70847ed830033ab50172c30" data-url="/manage/T1413" title="查询贷款还款计划表">
                    <span><img class="img-circle img-responsive" src="static/images/desk/plan.jpg"></span>
                    <p>查询贷款还款计划表</p>
                  </div>
                </div>
              </div>
            </div>
          </li>

          <li>
            <div>
              <div class="i-previews-container">
                <div class="i-previews-body clearfix animated fadeInUp">
                  <div class="i-desk-block" data-menu="6b980f78b7aa4bfe850866682b5b1c0b" data-url="/manage/T1401" title="贷款出账检查">
                    <span><img class="img-circle img-responsive" src="static/images/desk/check.jpg"></span>
                    <p>贷款出账检查</p>
                  </div>
                  <div class="i-desk-block" data-menu="928c62776872486599a617446fc7b64b" data-url="/counter/T1100" title="结清试算">
                    <span><img class="img-circle img-responsive" src="static/images/desk/settlement.jpg"></span>
                    <p>结清试算</p>
                  </div>
                </div>
              </div>
            </div>
          </li>
        </ul>
      </section>
      <%-- 桌面结束 --%>

      <%-- 左侧导航 --%>
      <aside id="i-aside-menu">
        <nav>
          <h2><i class="glyphicon glyphicon-menu-hamburger"></i>项目分类</h2>
          <ul>
            <c:set var="menuList" value="${fns:getMenuList()}" />
            <c:forEach items="${menuList}" var="menu1" varStatus="idxStatus">
              <c:if test="${menu1.parent.id eq '1' && menu1.isShow eq '1'}">
                <li>
                  <c:if test="${menu1.childCount > 0}">
                    <a href="#"><i class="git">${not empty menu1.icon ? menu1.icon : '&#xe60e;'}</i>${menu1.name}</a>
                    <h2><i class="git">${not empty menu1.icon ? menu1.icon : '&#xe60e;'}</i>${menu1.name}</h2>
                    <ul id="menu-${menu1.id}" class="i-nav-ul">
                      <c:forEach items="${menuList}" var="menu2">
                        <c:if test="${menu2.parent.id eq menu1.id && menu2.isShow eq '1'}">
                          <li>
                            <c:if test="${menu2.childCount > 0}">
                              <a href="#"><i class="glyphicon glyphicon-${not empty menu2.icon ? menu2.icon : 'folder-open'} icon"></i>${menu2.name}</a>
                              <h2><i class="glyphicon glyphicon-${not empty menu2.icon ? menu2.icon : 'folder-open'} icon"></i>${menu2.name}</h2>
                              <ul id="menu-${menu2.id}"  class="i-nav-ul">
                                <c:forEach items="${menuList}" var="menu3">
                                  <c:if test="${menu3.parent.id eq menu2.id && menu3.isShow eq '1'}">
                                    <li>
                                      <a data-menu="${menu3.id}" data-url="${not empty menu3.href ? menu3.href : '/404'}" title="${menu3.name}">
                                        <c:choose>
                                          <c:when test="${fn:length(menu3.name)>12}">
                                            <span data-toggle="tooltip" data-placement="bottom" title="${menu3.name}">${fn:substring(menu3.name,0,11)}...</span>
                                          </c:when>
                                          <c:otherwise>
                                            <span>${menu3.name}</span>
                                          </c:otherwise>
                                        </c:choose>
                                      </a>
                                    </li>
                                  </c:if>
                                </c:forEach>
                              </ul>
                            </c:if>
                            <c:if test="${menu2.childCount == 0}">
                              <a data-menu="${menu2.id}" data-url="${not empty menu2.href ? menu2.href : '/404'}" title="${menu2.name}">
                                ${menu2.name}
                              </a>
                            </c:if>
                          </li>
                        </c:if>
                      </c:forEach>
                    </ul>
                  </c:if>
                  <c:if test="${menu1.childCount == 0}">
                    <a data-menu="${menu1.id}" data-url="${not empty menu3.href ? menu3.href : '/404'}" title="${menu1.name}">
                      ${menu1.name}
                    </a>
                  </c:if>
                </li>
              </c:if>
            </c:forEach>
          </ul>
        </nav>
      </aside>
      <%-- 左侧导航结束 --%>

      <section id="i-body">
        <%-- 导航菜单分离 --%>
        <div id="i-nav-body">
          <%-- 目录结构 --%>
          <nav class="navbar navbar-default">
            <div class="container-fluid">
              <div class="navbar-header">
                <a class="navbar-brand" href="#">快捷导航</a>
              </div>

              <ul class="i-nav-menu">
                <c:set var="menuList" value="${fns:getMenuList()}" />
                <c:forEach items="${menuList}" var="menu1" varStatus="idxStatus">
                  <c:if test="${menu1.parent.id eq '1' && menu1.isShow eq '1'}">
                    <c:if test="${menu1.childCount > 0}">
                      <li data-id="${menu1.id }" data-name="${menu1.name}" data-icon="${not empty menu1.icon ? menu1.icon : '&#xe60e;'}">
                        <i class="git">${not empty menu1.icon ? menu1.icon : '&#xe60e;'}</i>
                        <span data-hover="${menu1.name}">${menu1.name}</span>
                      </li>
                    </c:if>
                  </c:if>
                </c:forEach>
              </ul>
            </div>
          </nav>
          <%-- 菜单页面项 --%>
          <section id="i-banner">
            <ul id="i-banner-content">
              <template v-for="menu in menus">
                <li :data-id="menu.id">
                  <div class="wrapper">
                    <div class="i-menu-list clearfix">
                      <div v-if="items.length == 0" style="width: calc(100%);">
                        <div class="alert alert-danger" role="alert">您搜索的结果为空，请更换关键词！</div>
                      </div>
                      <template v-for="item in filterMenu">
                        <div class="i-desk-item" :data-menu="item.id" :data-url="item.href" :title="item.name">
                          <p>
                            <span>{{item.name}}</span>
                          </p>
                        </div>
                      </template>
                    </div>
                  </div>
                </li>
              </template>
            </ul>
          </section>
        </div>
        <%-- 导航菜单分离结束 --%>

        <%-- 业务页面展示 --%>
        <section  id="i-content-body" class="clearfix animated fadeInRight">
          <div id="i-frame-tabs">
            <!-- nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
            </ul>
            <!-- tab panes -->
            <div class="tab-content">
            </div>
          </div>
        </section>
      </section>
    </main>
  </div>
  <%-- <%@include file="/WEB-INF/views/include/common.jsp" %> --%>
  <script src="${ctxStatic}/lib/ueditor/ueditor.config.js" type="text/javascript"></script>
  <script type="text/javascript">
    seajs.use('udmp/params/aplus_index');
  </script>
</body>
</html>
