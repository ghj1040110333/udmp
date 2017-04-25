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
<link rel="icon" href="${ctxUdmp}/css/favicon.ico" type="image/x-icon" />
<link rel="shortcut icon" href="${ctxUdmp}/css/favicon.ico" type="image/x-icon" />
<link rel="bookmark" href="${ctxUdmp}/css/favicon.ico" type="image/x-icon" />
<link href="${ctxStatic}/bootstrap/3.3.5/css/bootstrap.min.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="${ctxUdmp}/css/animate.css" media="screen" charset="utf-8">
<link rel="stylesheet" href="${ctxUdmp}/css/frame.css" media="screen" charset="utf-8">
<link rel="stylesheet" href="${ctxUdmp}/css/params/index.css" media="screen" charset="utf-8">
<script type="text/javascript" src="${ctxUdmp}/js/sea.js"></script>
<script type="text/javascript" src="${ctxUdmp}/js/seajs-config.js"></script>
<script type="text/javascript" src="${ctxStatic}/tmpl/tppl/tppl.js"></script>
<script type="text/javascript">
	seajs.use('app_plugin',function(){
			jQuery.PageLoading({ sleep: 1000 });
			window.loginName = "${fns:getUser().loginName}";
			window.$ = jQuery;
	});
	var $ctx = '${ctx}';
	// echarts图表数据展示所需字典
	var warnLevelCdList = ${fns:toJson(fns:getDictList('WARN_LEVEL_CD'))}; // 预警级别
	var warnTypeCdList = ${fns:toJson(fns:getDictList('WARN_TYPE_CD'))}; // 预警类别
	var modelStatusCdList = ${fns:toJson(fns:getDictList('MODEL_STATUS_CD'))}; // 模型状态
	var ruleStatusCdList = ${fns:toJson(fns:getDictList('RULE_STATUS_CD'))}; // 规则状态
	var indexStatusCdList = ${fns:toJson(fns:getDictList('INDEX_STATUS_CD'))}; // 指标状态
</script>
<!--[if lt IE 9]>
  <script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
  <script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
</head>
<body>
  <section id="i-container" class="i-son-abs">
    <!-- header start -->
    <header class="i-frame-header i-bg-white">
      <!-- LOGO -->
      <div class="i-aside i-bg-logo">
        <a class="btn btn-link visible-xs glyphicon glyphicon-list" data-toggle="class:i-aside-off,open" data-target="#i-nav-left,html">
        </a>
        <a href="#" class="i-navbar-brand">
          <img src="${ctxStatic}/images/logo.png" alt="${fns:getConfig('productName')}" />
        </a>
        <a class="btn btn-link visible-xs glyphicon glyphicon-cog" id="i-user-set" data-toggle="dropdown" data-target=".i-drop-down">
        </a>
      </div>
      <!-- 菜单收缩与搜索按钮 -->
      <div class="i-header-left hidden-xs">
        <ul class="nav navbar-nav navbar-left">
          <li>
            <a class="i-indent" data-toggle="class:i-aside-xs" data-target=".i-aside">
              <i class="glyphicon glyphicon-indent-right"></i>
            </a>
          </li>
          <li class="i-header-search">
            <input type="text" class="form-control i-search-txt" placeholder="搜索" />
          </li>
        </ul>
      </div>
      <div class="i-header-right">
        <ul class="nav navbar-nav navbar-right hidden-xs i-drop-down">
          <!-- 通知信息 -->
           <li class="hidden-xs" id="i-my-notice">
            <a data-toggle="dropdown" class="btn-message" href="#" title="我的通知">
              <i class="glyphicon glyphicon-bell top-5"></i>
              <span id="i-msg-count" class="badge badge-sm i-badge-up"></span>
            </a>
            <section class="dropdown-menu i-noti-width animated fadeInUp">
              <section class="panel panel-default">
                <div class="panel-heading">
                  <strong id="oaNotifyCount"></strong>
                </div>
                <div id="oaNotifyList" class="list-group">
                </div>
                <div class="panel-footer">
                	<a href="#" class="pull-right"><i class="fa fa-cog"></i></a>
                	<a href="javascript:void(0);" data-url="${ctx}/sys/oaNotify/self" data-menu="89" title="我的通知" data-toggle="class:show animated fadeInRight">查看所有通知信息</a>
                </div>
              </section>
            </section>
          </li>
          <!-- 个人信息 -->
          <li class="dropdown">
            <a class="dropdown-toggle user-img" data-toggle="dropdown" href="#">
            	<!-- <span id="clock"></span>&nbsp;|&nbsp; -->
            	${fns:getUser().name}&nbsp;|&nbsp;${fns:getUser().office.name}
              <img src="${ctxStatic}/images/userinfo.jpg" class="i-user-img img-circle"/>
            </a>
            <ul class="dropdown-menu animated fadeInRight">
              <li>
                <span class="arrow top"></span>
                <a data-url="${ctx}/sys/user/info" data-menu="29">
                  <span class="glyphicon glyphicon-user"></span>
                  个人信息
                </a>
              </li>
              <li>
                <a data-url="${ctx}/sys/user/modifyPwd" data-menu="30">
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
    <!-- header end -->
    <section>
      <section class="i-frame-body i-body-stretch">
        <!-- aside -->
        <aside class="i-bg-dark i-aside hidden-xs" id="i-nav-left">
          <section class="i-son-abs">
            <section class="i-scroll">
                <!-- nav -->
                <nav class="i-nav-menu">
                  <ul  class="i-nav-ul bg clearfix">
                    <%-- <li class="i-aside-title i-hidden">
                      常用选项配置
                    </li> --%>
                  	<c:set var="menuList" value="${fns:getMenuList()}" />
                  	<c:forEach items="${menuList}" var="menu1" varStatus="idxStatus">
                  		<c:if test="${menu1.parent.id eq '1' && menu1.isShow eq '1'}">
                  			<li>
                          <c:if test="${menu1.childCount > 0}">
                    				<a>
                              <span class="pull-right">
                                <i class="glyphicon glyphicon-chevron-left i-hidden"></i>
                                <i class="glyphicon glyphicon-chevron-down i-show"></i>
                              </span>
                              <i class="git">${not empty menu1.icon ? menu1.icon : '&#xe60e;'}</i>
                              <span>${menu1.name}</span>
                    				</a>
                    				<ul id="menu-${menu1.id}" class="i-nav-ul">
                    					<c:forEach items="${menuList}" var="menu2">
                    						<c:if test="${menu2.parent.id eq menu1.id && menu2.isShow eq '1'}">
                    							<li>
                    								<c:if test="${menu2.childCount > 0}">
                    									<a>
                                        <span class="pull-right">
                                          <i class="glyphicon glyphicon-chevron-left i-hidden"></i>
                                          <i class="glyphicon glyphicon-chevron-down i-show"></i>
                                        </span>
                    										<i class="glyphicon glyphicon-${not empty menu2.icon ? menu2.icon : 'folder-open'} icon"></i>
																				<c:choose>
																					<c:when test="${fn:length(menu2.name)>10}">
																						<span data-toggle="tooltip" data-placement="bottom" title="${menu2.name}">${fn:substring(menu2.name,0,9)}...</span>
																					</c:when>
																					<c:otherwise>
																						<span>${menu2.name}</span>
																					</c:otherwise>
																				</c:choose>
  	                  								</a>
  	                  								<ul id="menu-${menu2.id}"  class="i-nav-ul">
  	                  									<c:forEach items="${menuList}" var="menu3">
  	                  										<c:if test="${menu3.parent.id eq menu2.id && menu3.isShow eq '1'}">
  	                  											<li>
  	                  												<a data-menu="${menu3.id}" data-url="${fn:indexOf(menu3.href, '://') eq -1 ? ctx : ''}${not empty menu3.href ? menu3.href : '/404'}" title="${menu3.name}">
																								<c:if test="${menu3.type == '01'}">
																									<span class="pull-right">
																										<i class="glyphicon glyphicon-link" data-img="${menu3.imgURL}" title="添加到链接区"></i>
																									</span>
																								</c:if>
																								<c:if test="${menu3.type == '02'}">
																									<span class="pull-right">
																									<i class="glyphicon glyphicon-dashboard" data-url="${menu3.modelURL}" title="添加到模块"></i>
																									</span>
																								</c:if>
																								<c:if test="${menu3.type == '03'}">
																									<span class="pull-right">
																										<i class="glyphicon glyphicon-link" data-img="${menu3.imgURL}" title="添加到链接区"></i>
																										<i class="glyphicon glyphicon-dashboard" data-url="${menu3.modelURL}" title="添加到模块"></i>
																									</span>
																								</c:if>
																								<i class="glyphicon glyphicon-${not empty menu3.icon ? menu3.icon : 'chevron-right'} icon"></i>
																								<c:choose>
																									<c:when test="${fn:length(menu3.name)>10}">
																										<span data-toggle="tooltip" data-placement="bottom" title="${menu3.name}">${fn:substring(menu3.name,0,9)}...</span>
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
                    									<a data-menu="${menu2.id}" data-url="${fn:indexOf(menu2.href, '://') eq -1 ? ctx : ''}${not empty menu2.href ? menu2.href : '/404'}" title="${menu2.name}">
																				<c:if test="${menu2.type == '01'}">
																					<span class="pull-right">
	                                          <i class="glyphicon glyphicon-link" data-img="${menu2.imgURL}" title="添加到链接区"></i>
	                                        </span>
																				</c:if>
																				<c:if test="${menu2.type == '02'}">
																					<span class="pull-right">
                                          <i class="glyphicon glyphicon-dashboard" data-url="${menu2.modelURL}" title="添加到模块"></i>
	                                        </span>
																				</c:if>
																				<c:if test="${menu2.type == '03'}">
																					<span class="pull-right">
	                                          <i class="glyphicon glyphicon-link" data-img="${menu2.imgURL}" title="添加到链接区"></i>
	                                          <i class="glyphicon glyphicon-dashboard" data-url="${menu2.modelURL}" title="添加到模块"></i>
	                                        </span>
																				</c:if>
																				<i class="glyphicon glyphicon-${not empty menu2.icon ? menu2.icon : 'folder-open'} icon"></i>
																				<c:choose>
																					<c:when test="${fn:length(menu2.name)>10}">
																						<span data-toggle="tooltip" data-placement="bottom" title="${menu2.name}">${fn:substring(menu2.name,0,9)}...</span>
																					</c:when>
																					<c:otherwise>
																						<span>${menu2.name}</span>
																					</c:otherwise>
																				</c:choose>
  	                  								</a>
                    								</c:if>
                    							</li>
                    						</c:if>
                    					</c:forEach>
                    				</ul>
                  			</li>
                  		</c:if>
                      <c:if test="${menu1.childCount == 0}">
                        <a data-menu="${menu1.id}" href="${fn:indexOf(menu1.href, '://') eq -1 ? ctx : ''}${not empty menu1.href ? menu1.href : '/404'}" target="${not empty menu1.target ? menu1.target : 'mainFrame'}" title="${menu1.name}">
													<c:if test="${menu1.type == '01'}">
														<span class="pull-right">
															<i class="glyphicon glyphicon-link" data-img="${menu1.imgURL}" title="添加到链接区"></i>
														</span>
													</c:if>
													<c:if test="${menu1.type == '02'}">
														<span class="pull-right">
														<i class="glyphicon glyphicon-dashboard" data-url="${menu1.modelURL}" title="添加到模块"></i>
														</span>
													</c:if>
													<c:if test="${menu1.type == '03'}">
														<span class="pull-right">
															<i class="glyphicon glyphicon-link" data-img="${menu1.imgURL}" title="添加到链接区"></i>
															<i class="glyphicon glyphicon-dashboard" data-url="${menu1.modelURL}" title="添加到模块"></i>
														</span>
													</c:if>
													<i class="glyphicon glyphicon-${not empty menu1.icon ? menu1.icon : 'folder-open'} icon"></i>
                          <span>${menu1.name}</span>
                        </a>
                      </c:if>
                      </c:if>
                  	</c:forEach>
                  </ul>
                </nav>
                <!-- / nav -->
            </section>
          </section>
        </aside>
        <!-- aside end -->
        <!-- container -->
        <section id="i-main-body">
          <section class="i-son-abs">
            <!-- 主体内容 开始 -->
            <section class="i-scroll pad-15">
              <!-- frame tab -->
              <div class="row">
                <div class="col-xs-12" id="i-frame-tabs">
                  <!-- nav tabs -->
                  <ul class="nav nav-tabs" role="tablist">
                      <li role="presentation" class="active"><a id="i-tabs-thome" href="#i-tabs-home" aria-controls="i-tabs-home" role="tab" data-toggle="tab"><i class="glyphicon glyphicon-home"></i>首页</a></li>
                  </ul>
                  <!-- tab panes -->
                  <div class="tab-content">
                    <div role="tabpanel" class="tab-pane i-tabs active" id="i-tabs-home">
                      <div class="row i-my-work">
                        <div class="col-lg-3 col-sm-6">
                          <section class="panel clearfix">
														<a href="javascript:void(0);" class="i-gtasks clearfix" data-url="${ctx}/act/workFlow/todoTaskList" data-menu="2a9b3d7050544a51af675bae1ba0aa0f" title="待办事项">
															<div class="i-work-title"><span>●</span>待办事项</div>
	                            <div class="i-work-symbol">
																<div class="i-work-img">
	                              	<img src="${ctxUdmp}/css/images/index/gtasks.png" />
	                              </div>
	                            </div>
	                            <div class="i-work-info">
	                              <h1>0</h1>
	                            </div>
														</a>
                          </section>
                        </div>
                        <div class="col-lg-3 col-sm-6">
                          <section class="panel clearfix">
														<a href="javascript:void(0);" class="i-done clearfix" data-url="${ctx}/act/workFlow/doneTaskList" data-menu="57222b17587c425c864785a5c8960295" title="已办事项">
															<div class="i-work-title"><span>●</span>已办事项</div>
	                            <div class="i-work-symbol">
																<div class="i-work-img">
	                              	<img src="${ctxUdmp}/css/images/index/done.png" />
	                              </div>
	                            </div>
	                            <div class="i-work-info">
	                              <h1>0</h1>
	                            </div>
														</a>
                          </section>
                        </div>
												<div class="col-lg-3 col-sm-6">
                          <section class="panel clearfix">
														<a href="javascript:void(0);" class="i-signal clearfix" data-url="${ctx}/warnSignal/tWarnInfo/listRisk" data-menu="1e21f764159e4aefa4ff5c5ca79c4be4" title="信号处理">
															<div class="i-work-title"><span>●</span>信号处理</div>
	                            <div class="i-work-symbol">
	                              <div class="i-work-img">
	                              	<img src="${ctxUdmp}/css/images/index/signal.png" />
	                              </div>
	                            </div>
	                            <div class="i-work-info">
	                              <h1>0</h1>
	                            </div>
														</a>
														<shiro:lacksPermission name="warnSignal:tWarnInfo:view"><div class="i-disable"></div></shiro:lacksPermission>
                          </section>
                        </div>
                        <div class="col-lg-3 col-sm-6">
                          <section class="panel clearfix">
														<a href="javascript:void(0);" class="i-trace clearfix" data-url="${ctx}/receipt/tWarnReceiptInfo/receiptList" data-menu="a49b474cac11445cb0c35e518e5c8b3d" title="跟踪事项">
															<div class="i-work-title"><span>●</span>跟踪事项</div>
	                            <div class="i-work-symbol">
																<div class="i-work-img">
	                              	<img src="${ctxUdmp}/css/images/index/trace.png" />
	                              </div>
	                            </div>
	                            <div class="i-work-info">
	                              <h1>0</h1>
	                            </div>
														</a>
														<shiro:lacksPermission name="receipt:tWarnReceiptInfo:view"><div class="i-disable"></div></shiro:lacksPermission>
                          </section>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
              </div>
            </section>
            <!-- 主体内容 结束 -->
						<div id="fullScreen">
							<div class="page-header">
								<h1></h1>
								<small></small>
								<div class="i-close-fullScreen">
									<i class="git">&#xe633;</i>
								</div>
							</div>
							<div class="i-full-content">
							</div>
						</div>
          </section>
          <a href="#" class="hide i-aside-off-block" data-toggle="class:i-aside-off,open" data-target="#i-nav-left,html"></a>
        </section>
        <!-- container end -->
      </section>
    </section>
  </section>
  <%@include file="/WEB-INF/views/include/common.jsp" %>
	<script src="${ctxStatic}/ueditor/ueditor.config.js" type="text/javascript"></script>
	<script type="text/javascript">
    seajs.use('udmp/params/index');
  </script>
</body>
</html>
