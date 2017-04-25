<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<head>
<title>主页</title>
<meta name="decorator" content="bootstrap335" />
</head>
<body>
	<div id="homePage" class="contrain-fluid">
		<div class="row">
			<div class="col-md-8">
				<div class="panel panel-default">
					<div class="panel-heading">我的工作</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-sm-6">
								<ul class="media-list">
									<li class="media">
										<div class="media-left">
											<a href="#" target="mainFrame" title="信号处理">
												<img class="media-object" src="${ctxStatic}/images/mywork_1.png" alt="信号处理">
												<span class="badge show"></span>
											</a>
										</div>
										<div class="media-body">
											<a href="#" target="mainFrame" title="信号处理"><h4>信号处理</h4>
											有一个信号变更。</a>
										</div>
									</li>
									<li class="media">
										<div class="media-left">
											<a href="#" target="mainFrame" title="代办事项">
												<img class="media-object" src="${ctxStatic}/images/mywork_3.png" alt="代办事项">
												<span class="badge show"></span>
											</a>
										</div>
										<div class="media-body">
											<a href="#" target="mainFrame" title="代办事项"><h4>代办事项</h4>
											今天有5个代办事项，1个代办事项有变动。</a>
										</div>
									</li>
									<li class="media">
										<div class="media-left">
											<a href="#" target="mainFrame" title="已办事项">
												<img class="media-object" src="${ctxStatic}/images/mywork_5.png" alt="已办事项">
											</a>
										</div>
										<div class="media-body">
											<a href="#" target="mainFrame" title="已办事项"><h4>已办事项</h4></a>
										</div>
									</li>
								</ul>
							</div>
							<div class="col-sm-6">
								<ul class="media-list">
									<li class="media">
										<div class="media-left">
											<a href="#" target="mainFrame" title="关注事项">
												<img class="media-object" src="${ctxStatic}/images/mywork_2.png" alt="关注事项">
											</a>
										</div>
										<div class="media-body">
											<a href="#" target="mainFrame" title="关注事项"><h4>关注事项</h4></a>
										</div>
									</li>
									<li class="media">
										<div class="media-left">
											<a href="#" target="mainFrame" title="跟踪事项">
												<img class="media-object" src="${ctxStatic}/images/mywork_4.png" alt="跟踪事项">
												<span class="badge show"></span>
											</a>
										</div>
										<div class="media-body">
											<a href="#" target="mainFrame" title="跟踪事项"><h4>跟踪事项</h4>
											有2个跟踪事项变，1个需要紧急处理。</a>
										</div>
									</li>
								</ul>
							</div>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-4">
				<div class="panel panel-default">
					<div class="panel-heading">预警客户</div>
					<div class="panel-body">
						<ul class="media-list">
							<li class="media">
								<div class="media-left">
									<a href="#" target="mainFrame" title="明晓溪">
										<img class="media-object" src="${ctxStatic}/images/customer.png" alt="明晓溪">
									</a>
								</div>
								<div class="media-body">
									<a href="#" target="mainFrame" title="明晓溪"><h4>明晓溪</h4>
									客户拖欠贷款1个月，共拖欠3次。</a>
								</div>
							</li>
							<li class="media">
								<div class="media-left">
									<a href="#" target="mainFrame" title="明晓溪">
										<img class="media-object" src="${ctxStatic}/images/customer.png" alt="明晓溪">
									</a>
								</div>
								<div class="media-body">
									<a href="#" target="mainFrame" title="明晓溪"><h4>明晓溪</h4>
									客户拖欠贷款资料不全，有不良信用记录。</a>
								</div>
							</li>
						</ul>
						<div class="more">
							<a href="#" target="mainFrame" title="预警客户">查看全部</a>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						预警行业
						<span class="glyphicon glyphicon-remove pull-right btn-remove"></span>
						<span class="glyphicon glyphicon-menu-down pull-right btn-collapse"></span>
					</div>
					<div class="panel-body">
						<img src="${ctxStatic}/images/预警行业.png">
					</div>
				</div>
			</div>
			<div class="col-md-6">
				<div class="panel panel-default">
					<div class="panel-heading">
						预警信号
						<span class="glyphicon glyphicon-remove pull-right btn-remove"></span>
						<span class="glyphicon glyphicon-menu-down pull-right btn-collapse"></span>
					</div>
					<div class="panel-body">
						<img src="${ctxStatic}/images/预警信号.png">
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-12">
				<div class="panel panel-default">
					<div class="panel-heading">
						预警模型
						<span class="glyphicon glyphicon-remove pull-right btn-remove"></span>
						<span class="glyphicon glyphicon-menu-down pull-right btn-collapse"></span>
					</div>
					<div class="panel-body">
						<img src="${ctxStatic}/images/模型管理.png">
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<script>
		//showError("测试消息。", function() { alert("callback"); }, 2000);
		//window.top.showModal("", "窗体内容", { "title": "测试弹框", "width": 500 }, null, function(data, $src) { alert("callback"); });
	</script>
</body>
</html>