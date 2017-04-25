<%@ page language="java" pageEncoding="UTF-8"%>
<div id="leftside" style="top:0px;">
	<div id="sidebar_s">
		<div class="collapse">
			<div class="toggleCollapse">
				<div></div>
			</div>
		</div>
	</div>
	<div id="sidebar">
		<div class="toggleCollapse">
			<h2>主菜单</h2>
			<div>收缩</div>
		</div>
		<div class="accordion" fillSpace="sidebar">
					<div class="accordionHeader">
						<h2><span>Folder</span>界面组件</h2>
					</div>
					<div class="accordionContent">
						<ul class="tree treeFolder">
							<li><a href="tabsPage.html" target="navTab">批处理功能</a>
								<ul>
									<li><a href="./batchAgent/showBatchAgent_batchAgentAction.action" target="navTab" rel="page1">执行代理配置</a></li>
									<li><a href="./batch-task/outline_batchTaskAction.action?initOutline=yes" target="navTab" rel="page2">作业配置</a></li>
									<li><a href="./batchParam/showBatchParam_batchParamAction.action" target="navTab" rel="page3">参数管理</a></li>
									<li><a href="./batchJob/showBatchJob_batchJobAction.action" target="navTab" rel="page4" >任务配置</a></li>
									<li><a href="./batchJobGroup/showBatchJobGroupIndexPage_batchJobGroupAction.action" target="navTab" rel="page5">任务组管理</a></li>
									<li><a href="./batchJob/showBatchJobExecutePage_batchJobAction.action" target="navTab" rel="page6">任务执行管理</a></li>
									<li><a href="./batchAsyncJob/showBatchAsyncJob_batchAsyncJobAction.action" target="navTab" rel="page7" >异步任务管理</a></li>
									<li><a href="./batchJobRunLog/showBatchJobRunLog_batchJobRunLogAction.action" target="navTab" rel="page48">日志查询</a></li>
									<li><a href="./batchMonitor/showBatchMonitor_batchMonitorAction.action" target="navTab" rel="page9">执行监控-数量</a></li>
									<li><a href="./batchMonitor/showBatchMonitor1_batchMonitorAction.action"  target="navTab" rel="page10">执行监控-甘特</a></li>
									<li><a href="./batchServerPort/showBatchServerPortIndexPage_batchServerPortAction.action"  target="navTab" rel="page11">server监听端口启停</a></li>
									<li><a href="./batchAgentPort/showBatchAgentPortIndexPage_batchAgentPortAction.action"  target="navTab" rel="page12">agent监听端口启停</a></li>
								</ul>
							</li>
							<li><a href="${ctx }/udmp/dwz/dwz.frag.xml" target="navTab" external="true">dwz.frag.xml</a></li>
						</ul>
					</div>
				</div>
	</div>
</div>
</div>
