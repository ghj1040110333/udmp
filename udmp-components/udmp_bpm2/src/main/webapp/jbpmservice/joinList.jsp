<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="activiti_maven_project.com.git.common.Page"%>
<%@page import="java.util.List"%>
<%@page import="java.util.HashMap"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%   
	String  usercd= (String) request.getAttribute("usercd");	
	//String strMap =request.getAttribute("strMap").toString();
	String strMap = "";
	if(request.getAttribute("strMap")!=null)
	{
		strMap =request.getAttribute("strMap").toString();
	}
	Page _page =(Page)request.getAttribute("_page");
	List<HashMap> vo = (List<HashMap>) request.getAttribute("vo");	
	
	String bizType = "";
	String customerName = "";
	String customerNum = "";
	if(request.getAttribute("bizType")!=null){
		bizType=request.getAttribute("bizType").toString();
	}
	if(request.getAttribute("customerName")!=null){
		customerName=request.getAttribute("customerName").toString();
	}
	if(request.getAttribute("customerNum")!=null){
		customerNum=request.getAttribute("customerNum").toString();
	}
	int pageSize=0;
	if(request.getAttribute("pageSize")!=null && !"".equals(request.getAttribute("pageSize"))){
		pageSize=new Integer(request.getAttribute("pageSize").toString());
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
		 
	<script src="<%=basePath %>/js/jquery.min.js" type="text/javascript"></script>
	 <script src="<%=basePath %>/js/jquery14.js" type="text/javascript"></script>
	<script src="<%=basePath %>/js/lhgdialog.js" type="text/javascript"></script>	
</head>
<body>
	<% 
		String msg=null;
		if(request.getAttribute("msg")!=null && !"".equals(request.getAttribute("msg"))){
			msg=request.getParameter("msg");
			msg = new String(msg.getBytes("iso-8859-1"),"utf-8");
	%>
		<script >
				alert('<%=msg%>');
		</script>
	<% 
		}
	%>
		<div class="um-s-box">
	 	 	<form action="joinList.action?usercd=<%=usercd%>&pageSize=<%=pageSize %>" method="post"> 	 	 			 		
				<span class="um-span"><label>业务类型：</label><input type="text" value="<%=bizType%>" name="bizType" class="um-input" /></span>
				<span class="um-span"><label>客户名称：</label><input type="text" value="<%=customerName%>" name="customerName" class="um-input" /></span>	
				<span class="um-span"><label>客户编号：</label><input type="text" value="<%=customerNum%>" name="customerNum" class="um-input" /></span>	
					<input type="submit" value="查询" class="um-btn" />
			</form>
 		</div>
	<hr>	
	<div class="um-span-right">
		<input onclick="location.href='joinList.action?usercd=<%=usercd%>&pageSize=<%=pageSize %>'" type="button" value="列表首页" class="um-btn" />
	</div> 
        
    <div class="table-wrapper">	 
		<table id="um-table-reload" class="um-table">
			<thead>
				<tr>
					<th>序号</th>
					<th>业务id</th>
					<th>业务类型</th>
					<th>客户名称</th>
					<th>客户编号</th>
					<th>任务Id</th>
					<th>任务名称</th>
					<th>操作</th>
				</tr>
			</thead>
			<tbody>
				<%   if (vo != null && vo.size() > 0) {
						for (int i = 0; i < vo.size(); i++) {
							HashMap t = (HashMap)vo.get(i);
				%>
				<tr>
					<td><%=i+1 %></td>
					<td><%=t.get("bizId") %></td>
					<td><%=t.get("bizType") %></td>
					<td><%=t.get("customerName") %></td>
					<td><%=t.get("customerNum") %></td>
					<td id ="x"  value="<%=t.get("id")%>,<%=t.get("executionId")%>"><%=t.get("id")%></td>
					<td><%=t.get("name")%></td>
					<td>
						<select id="sel">
								<option value="">--操作--</option>
								<option value="handle,<%=t.get("id") %>">执行</option>
								<option value="showPic,<%=t.get("id") %>,<%=t.get("executionId")%>">流程图</option>
	   	 				</select>
	   	 			</td>
				</tr>
					<%
							}
					}else{
					%>
						<div class="um-span-centerDown" style="color:#F00;font-size:20px">※    没有找到相关信息</div>  
					<%
					}
					%>
			</tbody>
		</table>
	</div>
		
	<div class="um-table-page fl">
		 <input type="button" value="首页" onclick="toppage()" class="um-page-btn">
    	 <input type="button" value="上一页" onclick="previous()" class="um-page-btn">
		 <a> 当前页【<%=_page.getPageNo()%>】/总页数【<%=_page.getTotalPage() %>】 &nbsp &nbsp
			 	每页显示 &nbsp<select name="pageSize" id="pageSize" onChange="pageSize()" class="um-select-pageSize">
							<option value =""><%=_page.getPageSize()%></option>
							<% 
								if(_page.getPageSize()!=5){
							%>
								<option value ="5">5</option>
							<% 
								}
								if(_page.getPageSize()!=10){
							%>
								<option value ="10">10</option>
							<% 
								}
								if(_page.getPageSize()!=15){
							%>								
								<option value ="15">15</option>
							<% 
								}
								if(_page.getPageSize()!=20){
							%>	
								<option value ="20">20</option>
							<% 
								}
							%>									
		 				</select>
		 		 &nbsp条数据
		 	</a>
		 <input type="button" value="下一页" onclick="next()" class="um-page-btn">
		 <input type="button" value="尾页" onclick="endpage()" class="um-page-btn">			 
	</div>

<script type="text/javascript">
		function previous()
		{		
			if(<%=_page.getPageNo()%>==1||<%=_page.getPageNo()%>==0){
				alert("您已经到达第一页");
			}
			else{	
				window.location.href="<%=basePath%>/previouslist.action?joinList=<%=_page.getPageNo()%>&pageMap=<%=strMap%>&usercd=<%=usercd%>&bizType=<%=bizType%>&customerName=<%=customerName%>&customerNum=<%=customerNum%>&pageSize=<%=pageSize %>";	
			}
		}
		function next()
		{	
			if(<%=_page.getPageNo()%>>=<%=_page.getTotalPage()%>){
				alert("您已经到达最后一页");
			}
			else{
				window.location.href="<%=basePath%>/nextlist.action?joinList=<%=_page.getPageNo()%>&pageMap=<%=strMap%>&usercd=<%=usercd%>&bizType=<%=bizType%>&customerName=<%=customerName%>&customerNum=<%=customerNum%>&pageSize=<%=pageSize %>";	
			}
		}
		function toppage()
		{	
			if(<%=_page.getPageNo()%>==1||<%=_page.getPageNo()%>==0){
				alert("您已经到达首页");
			}
			else
				window.location.href="<%=basePath%>/toppagelist.action?joinList=<%=_page.getPageNo()%>&pageMap=<%=strMap%>&usercd=<%=usercd%>&bizType=<%=bizType%>&customerName=<%=customerName%>&customerNum=<%=customerNum%>&pageSize=<%=pageSize %>";	
		}
		function endpage()
		{		
			if(<%=_page.getPageNo()%>>=<%=_page.getTotalPage()%>){
				alert("您已经到达最后一页");
			}
			else{	
				window.location.href="<%=basePath%>/endpagelist.action?joinList=<%=_page.getTotalPage()%>&pageMap=<%=strMap%>&usercd=<%=usercd%>&bizType=<%=bizType%>&customerName=<%=customerName%>&customerNum=<%=customerNum%>&pageSize=<%=pageSize %>";	
			}
		}
		function pageSize(){
		var pageSize= document.getElementById("pageSize").value;
		window.location.href="<%=basePath%>/joinList.action?pageSize="+pageSize+"&usercd=<%=usercd%>&pageno=<%=_page.getPageNo()%>&pageMap_list=<%=strMap%>&bizType=<%=bizType%>&customerName=<%=customerName%>&customerNum=<%=customerNum%>";	
	}							
</script> 

<script>
	$(document).ready(function(){
	
	$(".um-table tbody tr:odd").addClass("odd");
	var flag=false;
	$("table.um-table select").each(function(){
				$(this).change(function(){
					var sel=$(this).val();
					var list=sel.split(",");
					var val=list[0];
					var id=list[1];
					var second = list[2];
				if(val == "handle"){
						location.replace("<%=basePath%>/handle.action?taskId="+id+"&usercd=<%=usercd%>&pageno=<%=_page.getPageNo()%>&pageMap=<%=strMap%>&bizType=<%=bizType%>&customerName=<%=customerName%>&customerNum=<%=customerNum%>&pageSize=<%=pageSize %>");
					}
				if(val == "showPic"){
					$.dialog({
							title:'流程图',
							lock: true,
							height:400, 
							width:1250,
							background: '#000', /* 背景色 */
							opacity: 0.5,       /* 透明度 */
							content:"url:<%=basePath%>/showPic.action?taskId="+id+"&processId="+second+"",
							icon: 'error.gif',
							cancel: function () {
								flag=true;
								var win=this.iframe.contentWindow;
								var doc=win.document;
								$("#btn",doc).click();
								window.location.href="<%=basePath%>/joinList.action?usercd=<%=usercd%>&pageno=<%=_page.getPageNo()%>&pageMap_list=<%=strMap%>&bizType=<%=bizType%>&customerName=<%=customerName%>&customerNum=<%=customerNum%>&pageSize=<%=pageSize %>"; 
							},
							
						});
					
					}
			});
		});
});
</script>		
</body>
</html>
