<%@page import="activiti_maven_project.com.git.quartz.vo.ScheduleJob"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%	
	List<ScheduleJob> vo = (List<ScheduleJob>) request.getAttribute("list");	
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'list.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/um-page.css">
	<link rel="stylesheet" href="<%=basePath %>/css/default.css" type="text/css" />
	<script src="<%=basePath %>/js/jquery.min.js" type="text/javascript"></script>
	<script src="<%=basePath %>/js/lhgdialog.js" type="text/javascript"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  	<div class="um-s-box">
		<input id="addWindow" type="button" value="时间表达式填写规则" class="um-btn" />
 	</div>
 	
  	
    <div class="table-wrapper">
    	<table  id="um-table-reload" class="um-table">
	    	<thead>
				<tr>
					<th>序号</th>
					<th>任务名称</th>
					<th>任务组</th>
					<th>时间表达式</th>
					<th>状态</th>
					<th>备注</th>
					<th>操作</th>
				</tr>					
			</thead>
			<tbody>
				<%
					if (vo != null && vo.size() > 0) {
						for (int i = 0; i < vo.size(); i++) {
							ScheduleJob job = (ScheduleJob)vo.get(i);
				%>
				<tr>
					<td><%=i+1 %></td>
					<td><%=job.getJobName()%></td>
					<td><%=job.getJobGroup()%></td>
					<td><%=job.getJobCronExpression()%></td>
					<td><%=job.getJobStatus()%></td>
					<td><%=job.getDesc()%></td>
					<td>
						<select id="operation" name="x">
							<option value ="">--操作--</option>	
							<option value ="update,<%=job.getJobName()%>,<%=job.getJobGroup()%>,<%=job.getJobCronExpression()%>" >修改表达式</option>															
					 		<option value ="pause,<%=job.getJobName()%>,<%=job.getJobGroup()%>,<%=job.getJobStatus() %>" >暂停</option>	
							<option value ="resume,<%=job.getJobName()%>,<%=job.getJobGroup() %>,<%=job.getJobStatus() %>" >恢复</option>																	 
	 					</select>	 
					</td>
				</tr>
				<%
						}
					}
				%>
			</tbody>
    	</table>
    </div>
  <script>
$(document).ready(function(){
	$(".um-table tbody tr:odd").addClass("odd");
	var flag=false;
	$("#addWindow").click(function(){
		$.dialog({
			title:'校验规则',
			lock: true,
			background: '#000', /* 背景色 */
			opacity: 0.5,       /* 透明度 */
			width:1000,
			height:400,
			content:"url:<%=basePath%>/operation/timer/plan/updateRule.jsp",
			icon: 'error.gif',
			okVal:"确定",
			
			cancel: function () {
				flag=true;
				var win=this.iframe.contentWindow;
				var doc=win.document;
				$("#btn",doc).click();
				window.location.reload();
			},
		});
	})
	$("table.um-table select").each(function(){
				$(this).change(function(){
				
					var sel=$(this).val();
					var list=sel.split(",");
					var val=list[0];
					var id=list[1];
					var groupId=list[2];
					var cronExpression=list[3];
					
					if(val == "update"){
						$.dialog({
							title:'修改表达式',
							lock: true,
							background: '#000', /* 背景色 */
							opacity: 0.5,       /* 透明度 */
							content:"url:<%=basePath %>/operation/timer/plan/update.jsp?jobName="+id+"&groupId="+groupId+"&cronSchedule="+cronExpression+"",
							icon: 'error.gif',
							cancel: function () {
								flag=true;
								var win=this.iframe.contentWindow;
								var doc=win.document;
								$("#btn",doc).click();
								window.location.href='planjob.action';
							},
						});
					}
				if(val == "pause"){
					if(cronExpression=="PAUSED"){
						alert("该任务已经是暂停状态");
						window.location.reload();
					} else{
						if(confirm("确定暂停任务吗？")){
							window.location.href='pauseJob.action?jobName='+id+'&groupId='+groupId+'';
							alert("任务已暂停");
						}else{
							window.location.reload();
						} 
					}
				}
				if(val == "resume"){
					if(cronExpression=="NORMAL"){
						alert("该状态已恢复");
						window.location.reload();
					}else{
							if(confirm("确定恢复任务吗？")){
							window.location.href='resumeJob.action?jobName='+id+'&groupId='+groupId+'';
							alert("任务已恢复");
						}else{
							window.location.reload();
						}
					}
				}
			})
		})
})
</script>
  </body>
  
</html>
