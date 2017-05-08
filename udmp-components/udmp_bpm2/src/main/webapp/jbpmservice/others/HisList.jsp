<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.ArrayList"%>
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
	List userlist = (List) request.getAttribute("userlist");
	
	String  usercd= (String) request.getAttribute("usercd");
	String strMap = "";
	String msg = (String) request.getAttribute("msg");
	if(request.getAttribute("strMap")!=null)
	{
		strMap =request.getAttribute("strMap").toString();
	}	
	Page _page = new Page();
	_page.setPageNo(0);
	_page.setTotalPage(0); 
	if(request.getAttribute("_page")!=null){
		_page = (Page)request.getAttribute("_page");
	}	
	if(usercd == null){
		usercd=""; 
	}
	List<HashMap> vo  =  new ArrayList<HashMap>();
	if(request.getAttribute("list")!=null){
		vo = (List<HashMap>) request.getAttribute("list");	
	}
	
	String bizType = "";
	String customerName = "";
	String customerNum = "";
	String status="";
	if(request.getAttribute("bizType")!=null){
		bizType=request.getAttribute("bizType").toString();
	}
	if(request.getAttribute("customerName")!=null){
		customerName=request.getAttribute("customerName").toString();
	}
	if(request.getAttribute("customerNum")!=null){
		customerNum=request.getAttribute("customerNum").toString();
	}
	if(request.getAttribute("status")!=null){
		status=request.getAttribute("status").toString();
	}
	int pageSize=0;
	if(request.getAttribute("pageSize")!=null && !"".equals(request.getAttribute("pageSize"))){
		pageSize=new Integer(request.getAttribute("pageSize").toString());
	}
	
	String familyName = "";
	String givenName = "";
	if(request.getAttribute("familyName")!=null){
		familyName=request.getAttribute("familyName").toString();
	}
	if(request.getAttribute("givenName")!=null){
		givenName=request.getAttribute("givenName").toString();
	}
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

     <div id="popDiv" class="mydiv" style="display:none;">
     	<a><img src="<%=basePath %>/images/picture.png" ></a>
     	 <div class="um-images-right">
     		<a href="javascript:closeDiv()"><img src="<%=basePath %>/images/exit.png" style="border:none;"></a>
     	</div> 
		   <form action="usercdQuery.action?flag=3&usercd=<%=usercd%>&pageSize=<%=pageSize %>" onsubmit="return isOr()" method="post" > 
		   		 <div>	 	 			 		
				<span class="user-span"><label>姓氏：</label><input type="text" name="familyName" id="familyName" value="<%=familyName%>" class="seluser-input" /></span>
				<span class="user-span"><label>名字：</label><input type="text" name="givenName" id="givenName" value="<%=givenName%>" class="seluser-input" /></span>
				<input type="submit" value="查询" class="um-btn" />	
				</div>
			</form>
			
			<form action="otherhislist.action?pageSize=<%=pageSize %>"  method="post" onsubmit="return is()">	
			<%
				if (userlist != null  && userlist.size() > 0  || msg != null  && !"".equals(msg)) 
				{
			%>
					<script >
						 document.getElementById('popDiv').style.display='block';
					</script>
			<%
					if (userlist != null  && userlist.size() > 0) 
					{
			%>
						<div style=" overflow:auto;margin-top:5%;height:170px;border-collapse:collapse;"  >	
							<table class="um-table">	
								<thead>
									<tr>
									<td></td>
									<td>序号</td>
									<td>姓氏</td>
									<td>名字</td>
									<td>用户名</td>
									
									</tr>
								</thead>
								<tbody>
			<%
								for (int i = 0; i < userlist.size(); i++) {
										HashMap u = (HashMap)userlist.get(i);
			%>
									<tr>
									<td><input name="usercd" type="radio" value="<%=u.get("id")%>"/></td>
									<td><%=i+1 %></td>
									<td><%=u.get("familyName") %></td>
									<td><%=u.get("givenName") %></td>
									<td><%=u.get("id") %></td>
									</tr>
			<%
								}
			%>
								</tbody>	
							</table>
						</div>
						<input type="submit" value="确认" class="um-btn"/>
			<%
					}
				}
			%>
			</form> 
			   
     </div> 
     
      
     <div class="um-s-box">
	  	<form action="otherhislist.action?pageSize=<%=pageSize %>" method="post"> 
	  		<a href="javascript:showDiv()"><img src="<%=basePath %>/images/query.png" style="border:none;width:15px;height:15px;margin-top:4px;margin-left:-24px;"></a> 	 	 			 		
			<span class="um-span"><label>用户：</label><input type="text" name="usercd" value ="<%=usercd%>" class="um-input" /></span>
				<input type="submit" value="查询" class="query-btn" />
		</form>
 	</div>   
     	
	<hr>	
 	<%
 		if(usercd!=""&&usercd!=null)
 		{		
 	%>
 	
 			
 	<div class="um-s-box">
	 	<form action="otherhislist.action?usercd=<%=usercd%>&pageSize=<%=pageSize %>"  method="post"> 	 	 			 		
			<span class="um-span"><label>业务类型：</label><input type="text" value="<%=bizType%>" name="bizType" class="um-input" /></span>
				<span class="um-span"><label>客户名称：</label><input type="text" value="<%=customerName%>" name="customerName" class="um-input" /></span>	
				<%-- <span class="um-span"><label>客户编号：</label><input type="text" value="<%=customerNum%>" name="customerNum" class="um-input" /></span> --%>
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
 			
		<br/><br/><br/>
		 
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
					if (vo != null && vo.size() > 0) {
						for (int i = 0; i < vo.size(); i++) {
							HashMap t = (HashMap)vo.get(i);
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
		<a> 当前页【<%=_page.getPageNo()%>】/总页数【<%=_page.getTotalPage() %> 】 &nbsp &nbsp
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
	
	<%} %>
     
<script type="text/javascript">
	function showDiv(){
		document.getElementById('popDiv').style.display='block';
	}
	function closeDiv(){
		document.getElementById('popDiv').style.display='none';
	}
</script> 

<script type="text/javascript">
	function isOr(){
		var n= document.getElementById("familyName").value;
		var g= document.getElementById("givenName").value;
		
		if(n!=null && n!="" || g!=null && g!=""){
			return true;
		}else{
			alert("请输入查询条件");
			return false;
		}
	} 
	function is(){
		 var val=$('input:radio[name="usercd"]:checked').val();
            if(val==null){
                alert("请选择用户");
                return false;
            }
            else{
                return true;
            } 
	} 	
</script>

<script>
	function previous()
	{		
		if(<%=_page.getPageNo()%>==1||<%=_page.getPageNo()%>==0){
			alert("您已经到达第一页");
		}
		else{	
			window.location.href="<%=basePath%>/otherpreviouslist.action?otherpage_his=<%=_page.getPageNo()%>&pageMap=<%=strMap%>&usercd=<%=usercd%>&bizType=<%=bizType%>&customerName=<%=customerName%>&customerNum=<%=customerNum%>&pageSize=<%=pageSize %>&status=<%=status %>";	
		}
	}
	function next()
	{		
		if(<%=_page.getPageNo()%>>=<%=_page.getTotalPage()%>){
			alert("您已经到达最后一页");
		}
		else{	
			window.location.href="<%=basePath%>/othernextlist.action?otherpage_his=<%=_page.getPageNo()%>&pageMap=<%=strMap%>&usercd=<%=usercd%>&bizType=<%=bizType%>&customerName=<%=customerName%>&customerNum=<%=customerNum%>&pageSize=<%=pageSize %>&status=<%=status %>";	
		}
	}
	function toppage()
	{		
		if(<%=_page.getPageNo()%>==1||<%=_page.getPageNo()%>==0){
			alert("您已经到达首页");
		}
		else{	
			window.location.href="<%=basePath%>/othertoppagelist.action?otherpage_his=<%=_page.getPageNo()%>&pageMap=<%=strMap%>&usercd=<%=usercd%>&bizType=<%=bizType%>&customerName=<%=customerName%>&customerNum=<%=customerNum%>&pageSize=<%=pageSize %>&status=<%=status %>";	
		}
	}
	function endpage()
	{		
		if(<%=_page.getPageNo()%>>=<%=_page.getTotalPage()%>){
			alert("您已经到达最后一页");
		}
		else{	
			window.location.href="<%=basePath%>/otherendpagelist.action?otherpagetotle_his=<%=_page.getTotalPage()%>&pageMap=<%=strMap%>&usercd=<%=usercd%>&bizType=<%=bizType%>&customerName=<%=customerName%>&customerNum=<%=customerNum%>&pageSize=<%=pageSize %>&status=<%=status %>";	
		}
	}
	function pageSize(){
		var pageSize= document.getElementById("pageSize").value;
		window.location.href="<%=basePath%>/otherhislist.action?pageSize="+pageSize+"&usercd=<%=usercd%>&pageno=<%=_page.getPageNo()%>&otherpageMap_his=<%=strMap%>&bizType=<%=bizType%>&customerName=<%=customerName%>&customerNum=<%=customerNum%>&status=<%=status %>";	
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
							content:"url:showTaskList.action?processId="+id+"&usercd=<%=usercd%>",
							icon: 'error.gif',
							
							cancel: function () {
								flag=true;
								var win=this.iframe.contentWindow;
								var doc=win.document;
								$("#btn",doc).click();
								window.location.href = "otherhislist.action?usercd=<%=usercd%>&pageno=<%=_page.getPageNo()%>&otherpageMap_his=<%=strMap%>&bizType=<%=bizType%>&customerName=<%=customerName%>&customerNum=<%=customerNum%>&pageSize=<%=pageSize %>&status=<%=status %>"; 
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
							content:"url:showPicHis.action?processId="+id+"",
							icon: 'error.gif',
							
							cancel: function () {
								flag=true;
								var win=this.iframe.contentWindow;
								var doc=win.document;
								$("#btn",doc).click();
								window.location.href = "otherhislist.action?usercd=<%=usercd%>&pageno=<%=_page.getPageNo()%>&otherpageMap_his=<%=strMap%>&bizType=<%=bizType%>&customerName=<%=customerName%>&customerNum=<%=customerNum%>&pageSize=<%=pageSize %>&status=<%=status %>"; 
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
						content:"url:showLoginfo.action?processId="+id+"",
						icon: 'error.gif',
						cancel: function () {
							flag=true;
							var win=this.iframe.contentWindow;
							var doc=win.document;
							$("#btn",doc).click();
							window.location.href = "otherhislist.action?usercd=<%=usercd%>&pageno=<%=_page.getPageNo()%>&otherpageMap_his=<%=strMap%>&bizType=<%=bizType%>&customerName=<%=customerName%>&customerNum=<%=customerNum%>&pageSize=<%=pageSize %>&status=<%=status %>"; 
						},
					});
				}

			});
		});
});
</script>
 </body>
</html>