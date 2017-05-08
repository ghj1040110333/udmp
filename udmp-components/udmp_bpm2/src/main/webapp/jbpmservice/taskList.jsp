<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="activiti_maven_project.com.git.service.impl.ActQueryWorkListServiceImpl"%>
<%@page import="activiti_maven_project.com.git.common.Page"%>
<%@page import="java.util.List"%>
<%@page import="org.springframework.web.context.WebApplicationContext "%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
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
	List<HashMap> vo = (List<HashMap>) request.getAttribute("vo");
	//String strMap =request.getAttribute("strMap").toString();
	String strMap = "";
	if(request.getAttribute("strMap")!=null)
	{
		strMap =request.getAttribute("strMap").toString();
	}	 
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
					<th>任务名称</th>
					<th>任务Id</th>
					<th>处理人</th>
					<th>开始时间</th>
					<th>结束时间</th>
					<th>状态</th>
					<th>意见</th>
				</tr>
			</thead>
			<tbody>
				<%
					if (vo != null && vo.size() > 0) {
						for (int i = 0; i < vo.size(); i++) {
							HashMap t = (HashMap)vo.get(i);
							Object processComment = t.get("processComment");
							Object finishTime = t.get("finishTime");
							if(processComment == null )
							{
								processComment = "";
							}
							if(finishTime == null )
							{
								finishTime = "";
							}
				%>
				<tr >
					<td><%=i+1 %></td>
					<td><%=t.get("activityId") %></td>
					<td><%=t.get("taskId") %></td>
					<td><%=t.get("owner") %></td>
					<td><%=t.get("createTime") %></td>
					<td><%=finishTime%></td>
					<td><%=t.get("status") %></td>
					<td><%=processComment%></td>
				</tr>
				<%
						}
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
			window.location.href="<%=basePath%>/previouslist.action?page_tlist=<%=_page.getPageNo()%>&usercd=<%=usercd%>&pageMap=<%=strMap%>";	
		}
	}
	function next()
	{		
		if(<%=_page.getPageNo()%>>=<%=_page.getTotalPage()%>){
			alert("您已经到达最后一页");
		}
		else{	
			window.location.href="<%=basePath%>/nextlist.action?page_tlist=<%=_page.getPageNo()%>&usercd=<%=usercd%>&pageMap=<%=strMap%>";	
		}
	}
	function toppage()
	{		
		if(<%=_page.getPageNo()%>==1||<%=_page.getPageNo()%>==0){
			alert("您已经到达首页");
		}
		else{	
			window.location.href="<%=basePath%>/toppagelist.action?page_tlist=<%=_page.getPageNo()%>&usercd=<%=usercd%>&pageMap=<%=strMap%>";	
		}
	}
	function endpage()
	{		
		if(<%=_page.getPageNo()%>>=<%=_page.getTotalPage()%>){
			alert("您已经到达最后一页");
		}
		else{	
			window.location.href="<%=basePath%>/endpagelist.action?pagetotle_tlist=<%=_page.getTotalPage()%>&usercd=<%=usercd%>&pageMap=<%=strMap%>";	
		}
	}							
</script>
</body>
</html>
