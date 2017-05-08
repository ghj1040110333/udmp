<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="activiti_maven_project.com.git.common.Page"%>
<%@page import="java.util.List"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidLoginfo"%>
<%@page import="java.util.HashMap"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%   
	String  usercd= (String) request.getAttribute("usercd");	
	Page _page =(Page)request.getAttribute("_page");
	String processId=request.getParameter("processId");	
	List<WfmidLoginfo> list = (List<WfmidLoginfo>) request.getAttribute("vo");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>

<base href="<%=basePath%>">

<title>My JSP 'index.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/um-page.css">
	<link rel="stylesheet" href="<%=basePath %>/css/default.css" type="text/css" />

</head>

<body>
	<div style="padding:10px;"></div>
	<div class="table-wrapper"> 
		<table id="um-table-reload" class="um-table">
			<thead>
				<tr>
						<th>序号</th>
						<th>流程ID</th>
						<th>任务ID</th>
						<th>日志文本</th>
						<th>时间</th>
						<th>类型</th>
						<th>节点名称</th>
				</tr>
			</thead>
			<tbody>
				<%
					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							WfmidLoginfo vo = list.get(i);
							Object activityName = vo.getActivityName();
							Object taskId = vo.getTaskId();
							if(activityName == null )
							{
								activityName = "";
							}
							if(taskId == null )
							{
								taskId = "";
							} 
							
				%>
				<tr >
					<td><%=i+1 %></td>
					<td><%=vo.getProcessId() %></td>
					<td><%=taskId%></td>
					<td><a href="<%=basePath %>/fandText.action?id=<%=vo.getId()%>&processId=<%=processId%>&pageno=<%=_page.getPageNo()%>&usercd=<%=usercd%>">查看</td>
					<td><%=vo.getLogTime()%></td>
					<td><%=vo.getOperationType()%></td>
					<td><%=activityName%></td>
				</tr>
				<%
						}
					}else{
					%>
					<div class="um-span-center" style="color:#F00;font-size:20px">※    没有找到相关信息</div>  
					<%
					}
					
				%>
			</tbody>
		</table>
	</div>
	
	<div class="um-table-page fl">
		<input type="button" value="首页" onclick="toppage()" class="um-page-btn">
    	<input type="button" value="上一页" onclick="previous()" class="um-page-btn">
		<a> 当前页【<%=_page.getPageNo()%>】/总页数【<%=_page.getTotalPage() %>】</a>
		<input type="button" value="下一页" onclick="next()" class="um-page-btn">
		<input type="button" value="尾页" onclick="endpage()" class="um-page-btn">			 
	</div>
<script>
	function previous()
		{		
			if(<%=_page.getPageNo()%>==1||<%=_page.getPageNo()%>==0){
				alert("您已经到达第一页");
			}
			else{	
				window.location.href="<%=basePath%>/previouslist.action?page_logInfo=<%=_page.getPageNo()%>&processId=<%=processId%>&usercd=<%=usercd%>";	
			}
		}
		function next()
		{	
			if(<%=_page.getPageNo()%>==<%=_page.getTotalPage()%>){
				alert("您已经到达最后一页");
			}
			else{
				window.location.href="<%=basePath%>/nextlist.action?page_logInfo=<%=_page.getPageNo()%>&processId=<%=processId%>&usercd=<%=usercd%>";	
			}
		}
		function toppage()
		{	
			if(<%=_page.getPageNo()%>==1||<%=_page.getPageNo()%>==0){
				alert("您已经到达首页");
			}
			else
				window.location.href="<%=basePath%>/toppagelist.action?page_logInfo=<%=_page.getPageNo()%>&processId=<%=processId%>&usercd=<%=usercd%>";	
		}
		function endpage()
		{		
			if(<%=_page.getPageNo()%>==<%=_page.getTotalPage()%>){
				alert("您已经到达最后一页");
			}
			else{	
				window.location.href="<%=basePath%>/endpagelist.action?pagetotle_logInfo=<%=_page.getTotalPage()%>&processId=<%=processId%>&usercd=<%=usercd%>";	
			}
		}								
</script>
</body>
</html>
