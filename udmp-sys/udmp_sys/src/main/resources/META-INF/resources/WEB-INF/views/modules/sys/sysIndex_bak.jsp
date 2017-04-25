<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>${fns:getConfig('productName')}</title>
<meta name="decorator" content="bootstrap335" />
<link href="${ctxUdmp}/css/mainframe.css" rel="Stylesheet" />
</head>
<body>
	<div id="frameMain">
		<div id="frameHeader" class="navbar">
			<div class="container-fluid">
				<div class="navbar-header">
			      <a href="${ctx}/">
			        <img src="${ctxStatic}/images/logo.png" alt="${fns:getConfig('productName')}">
			      </a>
			    </div>
				<ul class="nav navbar-nav">
					<!-- 左侧菜单收缩按钮 -->
					<li>
						<a id="btn-collapse" href="javascript:void(0);" class="btn-icon" title="收起菜单">
							<span class="glyphicon glyphicon-menu-hamburger"></span>
						</a>
					</li>
					<!-- 标签模式按钮 -->
					<li>
						<a id="btn-tabpage" href="javascript:void(0);" class="btn-icon" title="关闭标签页">
							<span class="glyphicon glyphicon-folder-close"></span>
						</a>
					</li>
					<!-- 查询框 -->
					<li>
						<form class="form-query">
							<div class="form-group has-feedback">
								<input type="text" class="form-control" placeholder="请输入查询内容">
								<span class="glyphicon glyphicon-search form-control-feedback"></span>
							</div>
						</form>
					</li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<!-- 通知信息 -->
					<li>
						<a href="${ctx}/oa/oaNotify/self" class="btn-message" target="mainFrame" title="我的通知">
							<span class="glyphicon glyphicon-envelope"></span>
							<span class="badge show"></span>
						</a>
					</li>
					<!-- 个人信息 -->
					<li class="dropdown">
						<a class="dropdown-toggle user-img" data-toggle="dropdown" href="#">系统管理员
						<img src="${ctxStatic}/images/userinfo.jpg"></a>
						<ul class="dropdown-menu">
							<li>
								<a href="${ctx}/sys/user/info" target="mainFrame">
									<span class="glyphicon glyphicon-user"></span>
									个人信息
								</a>
							</li>
							<li>
								<a href="${ctx}/sys/user/modifyPwd" target="mainFrame">
									<span class="glyphicon glyphicon-lock"></span>
									修改密码
								</a>
							</li>
							<li>
								<a href="${ctx}/oa/oaNotify/self" target="mainFrame">
									<span class="glyphicon glyphicon-envelope"></span>
									我的通知
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
		</div>
		<div id="frameBody">
			<!-- 左侧菜单 -->
			<div id="frameLeft">
				<jsp:include page="menuTree.jsp"></jsp:include>
			</div>
			<!-- 右侧主面板 -->
			<div id="frameRight">
				<!-- 页面载入进度条 -->
				<div id="loadingBar" class="bg-success"></div>
				
				<!-- tab页 -->
				<div id="tabFrame">
					<ul class="nav nav-tabs"></ul>
					<div class="tab-content"></div>
				</div>
				
				<!-- iframe 页-->
				<iframe id="mainFrame" name="mainFrame" src=""></iframe>
			</div>
		</div>
	</div>
	
	<!-- 右下角消息框 -->
	<div id="message" class="alert bg-success">
		<button type="button" class="close"><span>&times;</span></button>
		<h5 class="title">标题</h5>
		<div class="content">消息内容</div>
	</div>
	
	<script src="${ctxUdmp}/js/mainframe.js" type="text/javascript"></script>
	
	<script>
		/*通用控件Demo代码*/
		//showModal("", "窗体内容", { "title": "测试弹框" }, null, function(data, $src) { alert("callback"); });
		//showError("测试错误消息。", function() { alert("callback"); });
		//showInfo("测试消息。", function() { alert("callback"); }, 2000);
		//showLogin();
		//showConfirm("您确定要删除这条记录吗？", function() { alert("执行删除操作。"); });
		//showMessage("消息标题", "这是一条消息。", 5000);
	</script>
</body>
</html>