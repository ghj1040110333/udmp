<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@page import="activiti_maven_project.com.git.common.vo.ProcessDefinitionVO"%>
<%@page import="activiti_maven_project.com.git.common.Page"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%   
	String  usercd= (String) request.getAttribute("usercd");	
	Page _page =(Page)request.getAttribute("_page");
	int pageSize=0;
	if(request.getAttribute("pageSize")!=null && !"".equals(request.getAttribute("pageSize"))){
		pageSize=new Integer(request.getAttribute("pageSize").toString());
	}
	//String  templateName=(String) request.getAttribute("templateName");
	String templateName = "";
	if(request.getAttribute("templateName")!=null){
		templateName=request.getAttribute("templateName").toString();
	}
	List<ProcessDefinitionVO> list = (List<ProcessDefinitionVO>) request.getAttribute("list"); 
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
	<div class="um-span-right">
		<input onclick="location.href='definitionlist.action?usercd=<%=usercd%>&pageSize=<%=pageSize %>'" type="button" value="列表首页" class="um-btn" />
	</div>
	
	<div style="margin: 0 auto; width: 100%; padding-top: 20px; text-align: center; border: 0px solid #555" align="center">
		<form name="upload"   action="upload.action?usercd=<%=usercd%>&pageSize=<%=pageSize %>" method="post" enctype="multipart/form-data"  onsubmit="return check()">
			      文件位置：<input type="file" id ="mfile" name="mfile"/><br>       
						 <input type="submit" value="提交" class="um-btn"/> 
		</form>
	</div>
	<hr><br>	
	<form action="definitionlist.action?pageSize=<%=pageSize %>&usercd=<%=usercd%>" method="post">
		模板名称：<input type="text" name="templateName" value="<%=templateName%>"/>
			    <input type="submit" value="查询" class="um-btn"/>
	</form><br>	
	 
	<div class="table-wrapper">  
			<table id="um-table-reload" class="um-table">
				<thead>
					<tr>
						<th>序号</th>
						<th>id值</th>
						<th>流程名称</th>
						<th>版本号</th>
						<th>配置文件</th>
						<th>模板主键</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<% if (list != null && list.size() > 0) {
						for (int i = 0; i < list.size(); i++) {
							ProcessDefinitionVO vo = list.get(i);
				%>
					<tr>
						<td><%=i+1 %></td>
						<td><%=vo.getId() %></td>
						<td><%=vo.getName() %></td>
						<%-- <td id ="jieDian" value="<%=vo.getVersion()%>,<%=vo.getName() %>"><a href="javascript:void()" onclick="viewphoto()"><%=vo.getVersion() %></a></td> --%>
						<td><a href="javascript:void(0)" onclick="window.open ('wfmidActivityConfigureList.action?usercd=<%=usercd%>&version=<%=vo.getVersion() %>&templateId=<%=vo.getName() %>&bushu=bushu', 'newwindow', 'height=500, width=1000, toolbar=yes, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no') ">
							<%=vo.getVersion() %></a></td>
						<td><a href="javascript:void(0)" onclick="window.open ('detail.action?isPic=0&deploymentId=<%=vo.getDeploymentId() %>', 'newwindow', 'height=500, width=1000, toolbar=yes, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no') ">
							<%=vo.getImageResourceName() %></a></td>	
						<td><%=vo.getKey() %></td>
						 <td>
							<select id="sel" >
									<option value="">--操作--</option>
									<option value="detail,<%=vo.getDeploymentId() %>">流程图</option>
									<option value="remove,<%=vo.getDeploymentId() %>">移除发布</option>
									<option value="start,<%=vo.getName()%>,<%=usercd%>">发起测试</option>
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
					<tr></tr>
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
			window.location.href="<%=basePath%>/previouslist.action?page_defin=<%=_page.getPageNo()%>&templateName=<%=templateName%>&pageSize=<%=pageSize %>&usercd=<%=usercd%>";	
		}
	}
	function next()
	{		
		if(<%=_page.getPageNo()%>>=<%=_page.getTotalPage()%>){
			alert("您已经到达最后一页");
		}
		else{	
			window.location.href="<%=basePath%>/nextlist.action?page_defin=<%=_page.getPageNo()%>&templateName=<%=templateName%>&pageSize=<%=pageSize %>&usercd=<%=usercd%>";	
		}
	}
	function toppage()
	{		
		if(<%=_page.getPageNo()%>==1||<%=_page.getPageNo()%>==0){
			alert("您已经到达首页");
		}
		else{	
			window.location.href="<%=basePath%>/toppagelist.action?page_defin=<%=_page.getPageNo()%>&templateName=<%=templateName%>&pageSize=<%=pageSize %>&usercd=<%=usercd%>";	
		}
	}
	function endpage()
	{		
		if(<%=_page.getPageNo()%>>=<%=_page.getTotalPage()%>){
			alert("您已经到达最后一页");
		}
		else{	
			window.location.href="<%=basePath%>/endpagelist.action?pagetotle_defin=<%=_page.getTotalPage()%>&templateName=<%=templateName%>&pageSize=<%=pageSize %>&usercd=<%=usercd%>";	
		}
	}
	function pageSize(){
		var pageSize= document.getElementById("pageSize").value;
		window.location.href="definitionlist.action?pageSize="+pageSize+"&usercd=<%=usercd%>&pageno=<%=_page.getPageNo()%>&templateName=<%=templateName%>";	
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
					var second=list[2];
				if(val == "remove"){
					if(confirm("确定要移除吗？")){
							window.location.href='remove.action?usercd=<%=usercd%>&deploymentId='+id+'&pageno=<%=_page.getPageNo()%>&templateName=<%=templateName%>&pageSize=<%=pageSize %>';
					}else{
						window.location.href="definitionlist.action?usercd=<%=usercd%>&pageno=<%=_page.getPageNo()%>&templateName=<%=templateName%>&pageSize=<%=pageSize %>";
					}
				}
				if(val == "detail"){
						$.dialog({
							title:'流程图',
							lock: true,
							height:400, 
							width:1250,
							background: '#000', /* 背景色 */
							opacity: 0.5,       /* 透明度 */
							content:"url:detail.action?isPic=1&&deploymentId="+id+"",
							icon: 'error.gif',
							cancel: function () {
								flag=true;
								var win=this.iframe.contentWindow;
								var doc=win.document;
								$("#btn",doc).click();
								window.location.href="definitionlist.action?usercd=<%=usercd%>&pageno=<%=_page.getPageNo()%>&templateName=<%=templateName%>&pageSize=<%=pageSize %>";
							},
					});
				}
				if(val == "start"){
					if( id=="bpm_test01"){
						 $.dialog({
								title:'发起测试',
								lock: true,
								background: '#000', /* 背景色 */
								opacity: 0.5,       /* 透明度 */
								content:"url:<%=basePath %>/jbpmservice/start.jsp?processKey="+id+"&usercd=<%=usercd%>",
								icon: 'error.gif',
								cancel: function () {
									flag=true;
									var win=this.iframe.contentWindow;
									var doc=win.document;
									$("#btn",doc).click();
									window.location.href="definitionlist.action?usercd=<%=usercd%>&pageno=<%=_page.getPageNo()%>&templateName=<%=templateName%>&pageSize=<%=pageSize %>";
								},
						}); 
					}
					else{
						location.replace("start.action?processDefinitionKey="+id+"&usercd=<%=usercd%>&pageno=<%=_page.getPageNo()%>&templateName=<%=templateName%>&pageSize=<%=pageSize %>");
					}
				}

			});
		});
});
</script>	
<script  type="text/javascript">
function check(){   
		var path = document.upload.mfile.value;
		if(/\.zip$/i.test(path)){    /*正则表达式匹配路径是否以.zip结尾*/  
	        return true;     
	    }else{
	    	alert("文件添加错误，模板必须为zip格式,请重新输入");
	    	return false;
	    }	    
}
</script>	
</body>
</html>
