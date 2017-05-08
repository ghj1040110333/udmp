<%@page import="java.util.Iterator"%>
<%@page import="java.util.Map"%>
<%@page import="activiti_maven_project.com.git.action.StringAndMap"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="activiti_maven_project.com.git.service.impl.ActQueryWorkListServiceImpl"%>
<%@page import="org.springframework.web.context.WebApplicationContext "%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
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
	String msg =request.getParameter("msg");
	String status="";
	if(request.getAttribute("status")!=null){
		status=request.getAttribute("status").toString();
	}
	Page _page =(Page)request.getAttribute("_page");
	String strMap = "";
	if(request.getAttribute("strMap")!=null)
	{
		strMap =request.getAttribute("strMap").toString();
	}
	int pageSize=0;
	if(request.getAttribute("pageSize")!=null && !"".equals(request.getAttribute("pageSize"))){
		pageSize=new Integer(request.getAttribute("pageSize").toString());
	}
	String customerName="";
	if(request.getParameter("customerName")!=null){
		if(strMap!=null && !"".equals(strMap)){
			StringAndMap stringAndMap=new StringAndMap();
  			Map mapJsp =new HashMap();
  			Map map=stringAndMap.stringToMap(strMap, mapJsp);
  	
			Iterator it = map.entrySet().iterator() ;
			while (it.hasNext()){
				Map.Entry entry = (Map.Entry) it.next() ;
				Object key = entry.getKey() ;
				Object value= entry.getValue() ;
				if("customerName".equals(key.toString())){
					customerName=value.toString();
				}
			}
		}
	}
	List<HashMap> list = (List<HashMap>) request.getAttribute("list");	 
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">
	<title>My JSP 'list.jsp' starting page</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">	    
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
</head>

<body>
	<% 
		String restartMsg=null;
		if(request.getAttribute("restartMsg")!=null && !"".equals(request.getAttribute("restartMsg"))){
			restartMsg=request.getParameter("restartMsg");
			restartMsg = new String(restartMsg.getBytes("iso-8859-1"),"utf-8");
	%>
		<script >
				alert('<%=restartMsg%>');
		</script>
	<% 
		}
	%>
	<div class="um-span-right">
	<input onclick="location.href='hisListAll.action?msg=allHis&pageSize=<%=pageSize %>&status=<%=status%>'" type="button" value="列表首页" class="um-btn" />
	</div>
		<div class="um-s-box">
	 	 	<form action="hisListAll.action?msg=<%=msg %>&pageSize=<%=pageSize %>" method="post"> 	 	 			 		
				<span class="um-span"><label>客户名称：</label><input type="text" value="<%=customerName %>" name="customerName" class="um-input" /></span>	
				<span class="um-span"><label>状态：</label>
				<% 
					if(status!=null && !"".equals(status)){
						if(status.equals("finish")){
				%>
							<select id="status" name="status" class="um-select">
								<option value ="finish">已结束</option>
								<option value ="">进行中</option>	
		 					</select>
					<% 
						}
					}else{
				%>
						<select id="status" name="status" class="um-select">
							<option value ="">进行中</option>								
							<option value ="finish">已结束</option>									
		 				</select>
		 		<% 
		 			}
		 		%>
		 		</span>
				<input type="submit" value="查询" class="um-btn" />
			</form>
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
					<th>流程Id</th>
					<th>发起时间</th>
					<th>结束时间</th>
					<th>状态</th>
					<th colspan="2">操作</th>
				</tr>
			</thead>
			<tbody>
				<%
					if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							HashMap t = (HashMap)list.get(i);
		 					Object finishTime = t.get("finishTime"); 
							if(finishTime == null )
							{
								finishTime = "";
							} 
				%>
				
			<tr >
				<td><%=i+1 %></td>
				<td><%=t.get("bizId") %></td>
				<td><%=t.get("bizType") %></td>
				<td><%=t.get("customerName") %></td>
				<td><%=t.get("customerNum") %></td>
				<td><%=t.get("processId")%></td>
				<td><%=t.get("createTime")%></td>
				<td><%=finishTime%></td>
				<td><%=t.get("status")%></td>
				<td>
					<select id="sel">
							<option value="">--操作--</option>
							<option value="showTaskList,<%=t.get("processId")%>">详情</option>
							<option value="showPicHis,<%=t.get("processId")%>">流程图</option>
							<option value="showLoginfo,<%=t.get("processId")%>">查看日志</option>
							<option value="restart,<%=t.get("processId")%>,<%=t.get("bizType") %>">重新发起</option>
   	 				</select>
   	 			</td> 
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
		<a> 当前页【<%=_page.getPageNo()%>】/总页数【<%=_page.getTotalPage() %>】&nbsp &nbsp
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
<script>
	function previous()
	{		
		if(<%=_page.getPageNo()%>==1||<%=_page.getPageNo()%>==0){
			alert("您已经到达第一页");
		}
		else{	
			window.location.href="<%=basePath%>/previouslist.action?pageListAll=<%=_page.getPageNo()%>&pageMap=<%=strMap%>&msg=<%=msg%>&customerName=<%=customerName%>&status=<%=status%>&pageSize=<%=pageSize %>";	
		}
	}
	function next()
	{		
		if(<%=_page.getPageNo()%>>=<%=_page.getTotalPage()%>){
			alert("您已经到达最后一页");
		}
		else{	
			window.location.href="<%=basePath%>/nextlist.action?pageListAll=<%=_page.getPageNo()%>&pageMap=<%=strMap%>&msg=<%=msg%>&customerName=<%=customerName%>&status=<%=status%>&pageSize=<%=pageSize %>";	
		}
	}
	function toppage()
	{		
		if(<%=_page.getPageNo()%>==1||<%=_page.getPageNo()%>==0){
			alert("您已经到达首页");
		}
		else{	
			window.location.href="<%=basePath%>/toppagelist.action?pageListAll=<%=_page.getPageNo()%>&pageMap=<%=strMap%>&msg=<%=msg%>&customerName=<%=customerName%>&status=<%=status%>&pageSize=<%=pageSize %>";	
		}
	}
	function endpage()
	{		
		if(<%=_page.getPageNo()%>>=<%=_page.getTotalPage()%>){
			alert("您已经到达最后一页");
		}
		else{	
			window.location.href="<%=basePath%>/endpagelist.action?pageListAll=<%=_page.getTotalPage()%>&pageMap=<%=strMap%>&msg=<%=msg%>&customerName=<%=customerName%>&status=<%=status%>&pageSize=<%=pageSize %>";	
		}
	}
	function pageSize(){
		var pageSize= document.getElementById("pageSize").value;
		window.location.href="<%=basePath%>/hisListAll.action?pageSize="+pageSize+"&msg=<%=msg%>&pageno=<%=_page.getPageNo()%>&pageMap=<%=strMap%>&customerName=<%=customerName%>&status=<%=status%>";	
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
				if(val == "showTaskList"){
						$.dialog({
							title:'详情',
							lock: true,
							height:400,
							width:800,
							background: '#000', /* 背景色 */
							opacity: 0.5,       /* 透明度 */
							content:"url:showTaskList.action?processId="+id+"&msg=<%=msg%>",
							icon: 'error.gif',
							
							cancel: function () {
								flag=true;
								var win=this.iframe.contentWindow;
								var doc=win.document;
								$("#btn",doc).click();
								window.location.href = "hisListAll.action?msg=<%=msg%>&pageno=<%=_page.getPageNo()%>&pageMap=<%=strMap%>&customerName=<%=customerName%>&status=<%=status%>&pageSize=<%=pageSize %>"; 
							},
						});
					}
				if(val == "showPicHis"){
					$.dialog({
							title:'流程图',
							lock: true,
							height:400, 
							width:1250,
							background: '#000', /* 背景色 */
							opacity: 0.5,       /* 透明度 */
							content:"url:showPicHis.action?processId="+id+"&msg=<%=msg%>",
							icon: 'error.gif',
							
							cancel: function () {
								flag=true;
								var win=this.iframe.contentWindow;
								var doc=win.document;
								$("#btn",doc).click();
								window.location.href = "hisListAll.action?msg=<%=msg%>&pageno=<%=_page.getPageNo()%>&pageMap=<%=strMap%>&customerName=<%=customerName%>&status=<%=status%>&pageSize=<%=pageSize %>"; 
							},
						});
					}
				if(val == "showLoginfo"){
					$.dialog({
						title:'日志',
						lock: true,
						height:400,
						width:800,
						background: '#000', /* 背景色 */
						opacity: 0.5,       /* 透明度 */
						content:"url:showLoginfo.action?processId="+id+"&msg=<%=msg%>",
						icon: 'error.gif',
						cancel: function () {
							flag=true;
							var win=this.iframe.contentWindow;
							var doc=win.document;
							$("#btn",doc).click();
							window.location.href = "hisListAll.action?msg=<%=msg%>&pageno=<%=_page.getPageNo()%>&pageMap=<%=strMap%>&customerName=<%=customerName%>&status=<%=status%>&pageSize=<%=pageSize %>"; 
						},
					});
				}
				if(val == "restart"){
					if(confirm("确定重新发起吗？")){
						window.location.href='restart.action?processId='+id+'&bizType='+second+'&msg=<%=msg%>&pageno=<%=_page.getPageNo()%>&pageMap=<%=strMap%>&customerName=<%=customerName%>&status=<%=status%>&pageSize=<%=pageSize %>';
						//alert("该流程已重新发布");
					}else{
						window.location.href = "hisListAll.action?msg=<%=msg%>&pageno=<%=_page.getPageNo()%>&pageMap=<%=strMap%>&customerName=<%=customerName%>&status=<%=status%>&pageSize=<%=pageSize %>"; 
					}
				}
			});
		});
});
</script>	
</body>
</html>
