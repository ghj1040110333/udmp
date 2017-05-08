<%@page import="activiti_maven_project.com.git.action.StringAndMap"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidActivityConfigureMapperImpl"%>
<%@page import="org.springframework.context.support.FileSystemXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidActivityConfigure"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="activiti_maven_project.com.git.common.Page"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@page import="javax.servlet.http.HttpServletResponse" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
<link rel="stylesheet" href="<%=basePath %>css/default.css" type="text/css" />
<script src="<%=basePath %>js/jquery.min.js" type="text/javascript"></script>
<script src="<%=basePath %>js/lhgdialog.js" type="text/javascript"></script>
</head>
 <body>
 <% 
		String watMsg=null;
		String id=null;
		if(request.getAttribute("watMsg")!=null){
			watMsg=request.getParameter("watMsg");
			watMsg = new String(watMsg.getBytes("iso-8859-1"),"utf-8");
			if(watMsg.equals("删除成功")){
			
	%>
				<script>
					alert('<%=watMsg%>');
				</script>
		<% 
			}else{
				if(request.getAttribute("id")!=null){
					id=request.getParameter("id");
				}
		%>
				<script type="text/javascript">
					if(window.confirm("该数据已在其他地方引用，继续执行此操作会删除与之相关的所有数据，是否继续删除")){
						window.location.href='deleteRelevantWac.action?id=<%=id%>&pageno=<%=wacPage.getPageNo()%>&pageMap=<%=wacMap%>&msg=<%=msg%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&usercd=<%=usercd%>&activityName=<%=activeId%>&meetingType=<%=types%>&isMeeting=<%=isOr%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&bushu=<%=bushu%>';
					}else{
						window.location.href='wfmidActivityConfigureList.action?pageno=<%=wacPage.getPageNo()%>&pageMap=<%=wacMap%>&msg=<%=msg%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&usercd=<%=usercd%>&activityName=<%=activeId%>&meetingType=<%=types%>&isMeeting=<%=isOr%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&bushu=<%=bushu%>';
					}
				</script>
		<% 
			}
		%>
	<% 
		}
	%>
	<% 
		if(bushu==null || "".equals(bushu)){	
	%>
 	<div class="um-s-box">
 	 	<form action="<%=basePath%>/wfmidActivityConfigureList.action?msg=<%=msg%>&processTemplateId=<%=processTemplateId%>&wtcPage=<%=wtcPage%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&version=<%=version %>" method="post"> 	 		
			<span class="um-span"><label>节点名称：</label><input type="text" value="<%=activeId %>" name="activityName" class="um-input" /></span>
			<span class="um-span"><label>是否会签：</label>
			<% 
				if(isOr!=null && isOr!=""){
					if(isOr.equals("1")){
			%>
						<select id="isMeeting" name="isMeeting" class="um-select">
							<option value ="1">是</option>								
							<option value ="0">否</option>
							<option value ="">--是否会签--</option>									
		 				</select>
				<% 
					}else{
				%>
						<select id="isMeeting" name="isMeeting" class="um-select">
							<option value ="0">否</option>		
							<option value ="1">是</option>
							<option value ="">--是否会签--</option>									
		 				</select>
				<% 
					}
				%>
			<% 
				}else{
			%>
					<select id="isMeeting" name="isMeeting" class="um-select">
						<option value ="">--是否会签--</option>	
						<option value ="1">是</option>								
						<option value ="0">否</option>								
		 			</select>
		 	<% 
		 		}
		 	%>
		 	</span>
		 	<span class="um-span"><label>会签方式：</label>
		 	<% 
		 		if(types!=null && types!=""){
		 			if(types.equals("1")){
		 	%>	
		 				<select id="meetingType" name="meetingType" class="um-select">
							<option value ="1">百分比通过制</option>
							<option value ="2">一票通过制</option>								
							<option value ="3">一票否决制</option>	
							<option value ="">--请选择--</option>								
		 				</select>
		 		<% 
		 			}else if(types.equals("2")){
		 		%>
		 				<select id="meetingType" name="meetingType" class="um-select">
							<option value ="2">一票通过制</option>
							<option value ="1">百分比通过制</option>
							<option value ="3">一票否决制</option>	
							<option value ="">--请选择--</option>								
		 				</select>
		 		<% 
		 			}else{
		 		%>
		 				<select id="meetingType" name="meetingType" class="um-select">
							<option value ="3">一票否决制</option>	
							<option value ="1">百分比通过制</option>
							<option value ="2">一票通过制</option>
s							<option value ="">--请选择--</option>								
		 				</select>
		 		<% 
		 			}
		 		%>
		 	<%	
		 		}else{
		 	%>
		 			<select id="meetingType" name="meetingType" class="um-select">
						<option value ="">--请选择--</option>	
						<option value ="1">百分比通过制</option>
						<option value ="2">一票通过制</option>								
						<option value ="3">一票否决制</option>								
		 			</select>
		 	<% 
		 		}
		 	%>
		 	</span>			
				<input type="submit" value="查询" class="um-btn" />
		</form>
 	</div>
 	
 	<%
 		if(msg!=null && !"".equals(msg) && processTemplateId!=null && !"".equals(processTemplateId) && wtcPage!=null && !"".equals(wtcPage)){
 	%>
 		<div class="um-span-left">
 			<input onclick="location.href='wfmidTemplateConfigureList.action?pageno=<%=wtcPage %>&pageMap=<%=wtcMap%>&processTemplateId=<%=processTemplateId %>&pageSize=<%=wtcPageSize %>'" type="button" value="返回" class="um-btn" />
 		</div>
 	<%
 		}
 	%>
 	<div class="um-span-right">
 		<input id="addWindow" type="button" value="新增" class="um-btn" />
 		<%
 			if(msg!=null && !"".equals(msg)){
 		%>
 				<input onclick="location.href='wfmidActivityConfigureList.action?usercd=<%=usercd %>&processTemplateId=<%=processTemplateId %>&msg=msg&wtcPage=<%=wtcPage%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&version=<%=version %>'" type="button" value="列表首页" class="um-btn" />
 		<% 
 			}else{
 		%>
				<input onclick="location.href='wfmidActivityConfigureList.action?usercd=<%=usercd %>&pageSize=<%=pageSize %>'" type="button" value="列表首页" class="um-btn" />
		<% 
			}
		%>
	</div>
	<% 
 		}
 	%>
	<div class="table-wrapper">
			<table id="um-table-reload" class="um-table">
				<thead>
					<tr>
						<th>序号</th>
						<th>节点名称</th>
						<th>URL地址</th>
						<th>WSDL</th>
						<th>方法名称</th>
						<th>模板ID</th>
						<th>会签方式</th>
						<th>是否会签</th>
						<th>方法参数标签</th>
						<th>版本</th>
						<th>操作</th>									
					</tr>					
				</thead>
				<tbody>
					<%			
						List list = (List) request.getAttribute("list");
						if(list.size()>0){	
							for (int i = 0; i < list.size(); i++) {
								WfmidActivityConfigure vo = (WfmidActivityConfigure)list.get(i);
 								String isMeeting=vo.getIsMeeting();
 								if(isMeeting!=null && !"".equals(isMeeting)){
 									if(isMeeting.equals("1")){
 										isMeeting="是";
 									}else{
 										isMeeting="否";
 									}
 								}
 								String type=vo.getMeetingType();
 								if(type!=null && !"".equals(type)){
 									if(type.equals("1")){
 										type="百分比通过制";
 									}else if(type.equals("2")){
 										type="一票通过制";
 									}else if(type.equals("3")){
 										type="一票否决制";
 									}
 								}else{
 									type="";
 								}
					%>
					<tr>
						<td><%=i+1 %></td>
						<%
							if(processTemplateId!=null && !"".equals(processTemplateId)){
						%>
						 		<td><a href="wfmidActivityWsFieldList.action?activeId=<%=vo.getActivityName() %>&msg=msg&wacPage=<%=wacPage.getPageNo()%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&usercd=<%=usercd%>&wacMap=<%=wacMap%>&wtcMap=<%=wtcMap%>&wacPageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&version=<%=vo.getVersion()%>&bushu=<%=bushu%>"><%=vo.getActivityName() %></td>
						<% 
						 	}else{
						%>
						 		<td><a href="wfmidActivityWsFieldList.action?activeId=<%=vo.getActivityName() %>&msg=msg&wacPage=<%=wacPage.getPageNo()%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=vo.getTemplateId()%>&usercd=<%=usercd%>&wacMap=<%=wacMap%>&wtcMap=<%=wtcMap%>&wacPageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&version=<%=vo.getVersion()%>&bushu=<%=bushu%>"><%=vo.getActivityName() %></td>
						<% 
						 	}
						%>
						<td><%=vo.getUrl() %></td>
						<td><%=vo.getWsdl() %></td>
						<td><%=vo.getMethodName()%></td>
						<td><%=vo.getTemplateId()%></td>
						<td><%=type%></td>
						<td><%=isMeeting%></td>
						<td><%=vo.getMethodTag()%></td>	
						<td><%=vo.getVersion()%></td>
						<td>
							<select id="operation" name="x">
								<option value ="">--操作--</option>	
								<option value ="update,<%=vo.getId()%>" >修改</option>															
								<option value ="delete,<%=vo.getId()%>" >删除</option>																	 
	 						</select><br/>
						</td>
						
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
    		 <a>
    		 <% 
    		 	if(wacPage.getPageNo()<=0){
    		 %>
    		 		 当前页【0】/总页数【<%=wacPage.getTotalPage() %> 】
    		 <% 
    		 	}else{
    		 %>
					  当前页【<%=wacPage.getPageNo()%>】/总页数【<%=wacPage.getTotalPage() %>】
			<% 
				}
			%>
			&nbsp &nbsp
			每页显示 &nbsp<select name="pageSize" id="pageSize" onChange="pageSize()" class="um-select-pageSize">
							<option value =""><%=wacPage.getPageSize()%></option>
							<% 
								if(wacPage.getPageSize()!=5){
							%>
								<option value ="5">5</option>
							<% 
								}
								if(wacPage.getPageSize()!=10){
							%>
								<option value ="10">10</option>
							<% 
								}
								if(wacPage.getPageSize()!=15){
							%>								
								<option value ="15">15</option>
							<% 
								}
								if(wacPage.getPageSize()!=20){
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
		<script type="text/javascript" charset="UTF-8">
		function previous()
		{		
			if(<%=wacPage.getPageNo()%>==1 || <%=wacPage.getPageNo()%>==0){
				alert("您已经到达第一页");
			}
			else{	
				window.location.href="<%=basePath%>/previous.action?waccPage=<%=wacPage.getPageNo()%>&pageMap=<%=wacMap%>&usercd=<%=usercd%>&msg=<%=msg%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&activeId=<%=activeId%>&type=<%=types%>&isOr=<%=isOr%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&version=<%=version%>&bushu=<%=bushu%>";	
			}
		}
		function next()
		{	
			if(<%=wacPage.getPageNo()%>==<%=wacPage.getTotalPage()%>){
				alert("您已经到达最后一页");
			}
			else{
				window.location.href="<%=basePath%>/next.action?waccPage=<%=wacPage.getPageNo()%>&pageMap=<%=wacMap%>&usercd=<%=usercd%>&msg=<%=msg%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&activeId=<%=activeId%>&type=<%=types%>&isOr=<%=isOr%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&version=<%=version%>&bushu=<%=bushu%>";	
			}
		}
		function toppage()
		{	
			if(<%=wacPage.getPageNo()%>==1 || <%=wacPage.getPageNo()%>==0){
				alert("您已经到达首页");
			}
			else
				window.location.href="<%=basePath%>/toppage.action?waccPage=<%=wacPage.getPageNo()%>&pageMap=<%=wacMap%>&usercd=<%=usercd%>&msg=<%=msg%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&activeId=<%=activeId%>&type=<%=types%>&isOr=<%=isOr%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&version=<%=version%>&bushu=<%=bushu%>";	
		}
		function endpage()
		{		
			if(<%=wacPage.getPageNo()%>==<%=wacPage.getTotalPage()%>){
				alert("您已经到达最后一页");
			}
			else{	
				window.location.href="<%=basePath%>/endpage.action?pagetotleWac=<%=wacPage.getTotalPage()%>&pageMap=<%=wacMap%>&usercd=<%=usercd%>&msg=<%=msg%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&activeId=<%=activeId%>&type=<%=types%>&isOr=<%=isOr%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&version=<%=version%>&bushu=<%=bushu%>";	
			}
		}
		function pageSize(){
		var pageSize= document.getElementById("pageSize").value;
		window.location.href="<%=basePath%>/wfmidActivityConfigureList.action?pageSize="+pageSize+"&pageno=<%=wacPage.getPageNo()%>&pageMap=<%=wacMap%>&msg=<%=msg%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&usercd=<%=usercd%>&activityName=<%=activeId%>&meetingType=<%=types%>&isMeeting=<%=isOr%>&wtcMap=<%=wtcMap%>&wtcPageSize=<%=wtcPageSize%>&version=<%=version%>&bushu=<%=bushu%>";	
	}							
		</script> 
<script  charset="UTF-8">

$(document).ready(function(){
	$(".um-table tbody tr:odd").addClass("odd");
	var flag=false;
	$("#addWindow").click(function(){
		$.dialog({
			title:'新增',
			lock: true,
			background: '#000', /* 背景色 */
			opacity: 0.5,       /* 透明度 */
			content:encodeURI("url:addSelectIDacf.action?processTemplateId=<%=processTemplateId%>&wtcPage=<%=wtcPage%>&version=<%=version%>"),
			icon: 'error.gif',
			cancel: function () {
				flag=true;
				var win=this.iframe.contentWindow;
				var doc=win.document;
				$("#btn",doc).click();
				window.location.href='wfmidActivityConfigureList.action?pageno=<%=wacPage.getPageNo()%>&msg=<%=msg%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&usercd=<%=usercd%>&activityName=<%=activeId%>&meetingType=<%=types%>&isMeeting=<%=isOr%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&version=<%=version%>';
			},
		});
	})
	$("table.um-table select").each(function(){
				$(this).change(function(){
				
					var sel=$(this).val();
					var list=sel.split(",");
					var val=list[0];
					var id=list[1];
					if(val == "update"){
						$.dialog({
							title:'修改',
							lock: true,
							background: '#000', /* 背景色 */
							opacity: 0.5,       /* 透明度 */
							content:"url:updateSelectIDacf.action?id="+id+"&msg=<%=msg%>",
							icon: 'error.gif',
							//okVal:"确定",
							//ok: function () {
								//flag=true;
								//var win=this.iframe.contentWindow;
								//var doc=win.document;
								//$("#btn",doc).click();
								//window.location.href='wfmidActivityConfigureList.action?pageno=<%=wacPage.getPageNo()%>&pageMap=<%=wacMap%>&msg=<%=msg%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&usercd=<%=usercd%>';
								//return false;
							//},
							cancel: function () {
								flag=true;
								var win=this.iframe.contentWindow;
								var doc=win.document;
								$("#btn",doc).click();
								window.location.href='wfmidActivityConfigureList.action?pageno=<%=wacPage.getPageNo()%>&pageMap=<%=wacMap%>&msg=<%=msg%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&usercd=<%=usercd%>&activityName=<%=activeId%>&meetingType=<%=types%>&isMeeting=<%=isOr%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&version=<%=version%>&bushu=<%=bushu%>';
							},
						});
					}
				if(val == "delete"){
					if(confirm("确定要删除吗？")){
						window.location.href='deleteWfmidActivityConfigure.action?id='+id+'&pageno=<%=wacPage.getPageNo()%>&pageMap=<%=wacMap%>&msg=<%=msg%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&usercd=<%=usercd%>&activityName=<%=activeId%>&meetingType=<%=types%>&isMeeting=<%=isOr%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&version=<%=version%>&bushu=<%=bushu%>';
						//alert("删除成功");
					}else{
						window.location.href='wfmidActivityConfigureList.action?pageno=<%=wacPage.getPageNo()%>&pageMap=<%=wacMap%>&msg=<%=msg%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&usercd=<%=usercd%>&activityName=<%=activeId%>&meetingType=<%=types%>&isMeeting=<%=isOr%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&version=<%=version%>&bushu=<%=bushu%>';
					}
				}	
			})
	})
})
</script>
</body>
</html>