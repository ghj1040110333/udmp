<%@page import="java.util.Iterator"%>
<%@page import="activiti_maven_project.com.git.action.StringAndMap"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.web.context.WebApplicationContext"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidTemplateConfigure"%>
<%@page import="activiti_maven_project.com.git.common.Page"%>
<%@page import="java.util.HashMap"%>
<%@page import="org.springframework.context.support.FileSystemXmlApplicationContext"%>
<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidTemplateConfigureMapperImpl"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String wtcMap =request.getAttribute("stringMap").toString();
	Page wtcPage=(Page)request.getAttribute("wtcPage");
	String bizType="";
	if(request.getAttribute("bizType")!=null){
		bizType=request.getAttribute("bizType").toString();
	}
	String processTemplateId="";
	if(request.getAttribute("processTemplateId")!=null){
		processTemplateId=request.getAttribute("processTemplateId").toString();
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
<script src="<%=basePath %>/js/lhgdialog.js" type="text/javascript"></script>
</head>
<body>
	<% 
		String msg=null;
		String id=null;
		if(request.getAttribute("msg")!=null){
			msg=request.getParameter("msg");
			msg = new String(msg.getBytes("iso-8859-1"),"utf-8");
			if(msg.equals("删除成功")){
			
	%>
				<script>
					alert('<%=msg%>');
				</script>
		<% 
			}else{
				if(request.getAttribute("id")!=null){
					id=request.getParameter("id");
				}
		%>
				<script type="text/javascript">
					if(window.confirm("该数据已在其他地方引用，继续执行此操作会删除与之相关的所有数据，是否继续删除")){
						window.location.href='deleteRelevantWtc.action?id=<%=id%>&pageno=<%=wtcPage.getPageNo()%>&pageMap=<%=wtcMap%>&bizType=<%=bizType%>&pageSize=<%=pageSize%>';
					}else{
						window.location.href='wfmidTemplateConfigureList.action?pageno=<%=wtcPage.getPageNo()%>&pageMap=<%=wtcMap%>&bizType=<%=bizType%>&pageSize=<%=pageSize%>';
					}
				</script>
		<% 
			}
		%>
	<% 
		}
	%>
	<div class="um-s-box">
 	 	<form action="<%=basePath%>/wfmidTemplateConfigureList.action?pageSize=<%=pageSize%>" method="post">
 	 		<% 
 	 			if(processTemplateId!=null && !"".equals(processTemplateId)){
 	 				StringAndMap stringAndMap=new StringAndMap();
  					Map mapJsp =new HashMap();
  					Map map=stringAndMap.stringToMap(wtcMap, mapJsp);
					Iterator it = map.entrySet().iterator() ;
					while (it.hasNext()){
						Map.Entry entry = (Map.Entry) it.next() ;
						Object key = entry.getKey() ;
						Object value= entry.getValue() ;
						if(key.toString().equals("bizType")){
							bizType=value.toString();
						}
					}
			%>
					<span class="um-span"><label>业务类型：</label><input type="text" value="<%=bizType %>" name="bizType" class="um-input" /></span>
 	 		<% 
 	 			}else{
 	 		%> 	 		
				<span class="um-span"><label>业务类型：</label><input type="text" value="<%=bizType %>" name="bizType" class="um-input" /></span>
			<% 
				}
			%>	  
				  <input type="submit" value="查询"  class="um-btn" />
		</form>	
 	</div>
 	<div class="um-span-right">
 	<input id="addWindow" type="button" value="新增" class="um-btn" />
 	<input onclick="location.href='wfmidTemplateConfigureList.action?pageSize=<%=pageSize %>'" type="button" value="列表首页" class="um-btn" />
	</div>
	<!-- <span id="apDiv3"><a href="operation/wfmidTemplateConfigure/add.jsp">新增</a></span> -->
  	<div class="table-wrapper">
			<table id="um-table-reload" class="um-table">
				<thead>
					<tr>
						<th>序列号</th>
						<th>业务类型</th>
						<th>流程模板ID</th>
						<th>是否生效</th>
						<th>版本</th>
						<th>时长</th>
						<th>时长类型</th>
						<th>AUTO_TAKE</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<%
						List list = (List) request.getAttribute("list");
						if(list.size()>0){
							for (int i = 0; i < list.size(); i++) {
								WfmidTemplateConfigure vo = (WfmidTemplateConfigure)list.get(i);
								String value=vo.getTimerValue();
								if(value==null){
									value="";
								}
								String isEffect=vo.getIsEffect();
								if(isEffect!=null && !"".equals(isEffect)){
									if(isEffect.equals("1")){
										isEffect="是";
									}else{
										isEffect="否";
									}
								}
								String AUTO_TAKE=vo.getAutoTake();
								if(AUTO_TAKE!=null && !"".equals(AUTO_TAKE)){
									if(AUTO_TAKE.equals("1")){
										AUTO_TAKE="是";
									}else{
										AUTO_TAKE="否";
									}
								}
								String timerType=vo.getTimerType();
								if(timerType!=null && !"".equals(timerType)){
									if(timerType.equals("minute")){
										timerType="分钟";
									}else if(timerType.equals("day")){
										timerType="天";
									}else{
										timerType="小时";
									}
								}
					%>
					<tr>
						<td><%=i+1 %></td>
						<td><%=vo.getBizType() %></td>
						<td><a href="wfmidActivityConfigureList.action?processTemplateId=<%=vo.getProcessTemplateId() %>&msg=msg&wtcPage=<%=wtcPage.getPageNo()%>&wtcMap=<%=wtcMap%>&wtcPageSize=<%=pageSize%>&version=<%=vo.getVersion()%>"><%=vo.getProcessTemplateId() %></td>
						<td><%=isEffect %></td>
						<td><%=vo.getVersion() %></a></td>	
						<td><%=value %></td>
						<td><%=timerType %></td>
						<td><%=AUTO_TAKE %></td>
						<td>
							<select id="sel">
								<option value="">--操作--</option>
								<option value="update,<%=vo.getId() %>">修改</option>
								<option value="delete,<%=vo.getId() %>">删除</option>
   	 						</select>
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
			 <a> 当前页【<%=wtcPage.getPageNo()%>】/总页数【<%=wtcPage.getTotalPage() %>】 &nbsp &nbsp
			 	每页显示 &nbsp<select name="pageSize" id="pageSize" onChange="pageSize()" class="um-select-pageSize">
							<option value =""><%=wtcPage.getPageSize()%></option>
							<% 
								if(wtcPage.getPageSize()!=5){
							%>
								<option value ="5">5</option>
							<% 
								}
								if(wtcPage.getPageSize()!=10){
							%>
								<option value ="10">10</option>
							<% 
								}
								if(wtcPage.getPageSize()!=15){
							%>								
								<option value ="15">15</option>
							<% 
								}
								if(wtcPage.getPageSize()!=20){
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
		if(<%=wtcPage.getPageNo()%>==1 || <%=wtcPage.getPageNo()%>==0){
			alert("您已经到达第一页");
		}
		else{	
			window.location.href="<%=basePath%>/previous.action?wtcPage=<%=wtcPage.getPageNo()%>&pageMap=<%=wtcMap%>&bizType=<%=bizType%>&pageSize=<%=pageSize%>";	
		}
	}
	function next()
	{		
		if(<%=wtcPage.getPageNo()%>==<%=wtcPage.getTotalPage()%>){
			alert("您已经到达最后一页");
		}
		else{	
			window.location.href="<%=basePath%>/next.action?wtcPage=<%=wtcPage.getPageNo()%>&pageMap=<%=wtcMap%>&bizType=<%=bizType%>&pageSize=<%=pageSize%>";	
		}
	}
	function toppage()
	{		
		if(<%=wtcPage.getPageNo()%>==1 || <%=wtcPage.getPageNo()%>==0){
			alert("您已经到达首页");
		}
		else{	
			window.location.href="<%=basePath%>/toppage.action?wtcPage=<%=wtcPage.getPageNo()%>&pageMap=<%=wtcMap%>&bizType=<%=bizType%>&pageSize=<%=pageSize%>";		
		}
	}
	function endpage()
	{		
		if(<%=wtcPage.getPageNo()%>==<%=wtcPage.getTotalPage()%>){
			alert("您已经到达最后一页");
		}
		else{	
			window.location.href="<%=basePath%>/endpage.action?pagetotleWtc=<%=wtcPage.getTotalPage()%>&pageMap=<%=wtcMap%>&bizType=<%=bizType%>&pageSize=<%=pageSize%>";	
		}
	}
	function pageSize(){
		var pageSize= document.getElementById("pageSize").value;
		window.location.href="<%=basePath%>/wfmidTemplateConfigureList.action?pageSize="+pageSize+"&pageMap=<%=wtcMap%>&bizType=<%=bizType%>";	
	}							
</script>
<script charset="UTF-8">
$(document).ready(function(){
	$(".um-table tbody tr:odd").addClass("odd");
	var flag=false;
	$("#addWindow").click(function(){
		$.dialog({
			title:'新增',
			lock: true,
			background: '#000', /* 背景色 */
			opacity: 0.5,       /* 透明度 */
			content:"url:<%=basePath %>/operation/wfmidTemplateConfigure/add.jsp",
			icon: 'error.gif',
			//okVal:"确定",
			//ok: function () {
				//flag=true;
				//var win=this.iframe.contentWindow;
				//var doc=win.document;
				//$("#btn",doc).click();
				//window.location.href='wfmidTemplateConfigureList.action?pageno=<%=wtcPage.getTotalPage()%>';
				//return false;
			//},
			cancel: function () {
				flag=true;
				var win=this.iframe.contentWindow;
				var doc=win.document;
				$("#btn",doc).click();
				window.location.href='wfmidTemplateConfigureList.action?pageno=<%=wtcPage.getPageNo()%>&bizType=<%=bizType%>&pageSize=<%=pageSize%>';
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
							content:"url:updateSelectIDtcf.action?id="+id+"",
							icon: 'error.gif',
							//okVal:"确定",
							//ok: function () {
								//flag=true;
								//var win=this.iframe.contentWindow;
								//var doc=win.document;
								//$("#btn",doc).click();
								//window.location.href='wfmidTemplateConfigureList.action?pageno=<%=wtcPage.getPageNo()%>&pageMap=<%=wtcMap%>';
								//return false;
							//},
							cancel: function () {
								flag=true;
								var win=this.iframe.contentWindow;
								var doc=win.document;
								$("#btn",doc).click();
								window.location.href='wfmidTemplateConfigureList.action?pageno=<%=wtcPage.getPageNo()%>&pageMap=<%=wtcMap%>&bizType=<%=bizType%>&pageSize=<%=pageSize%>';
							},
						});
					}
				if(val == "delete"){
					if(confirm("确定要删除吗？")){
						window.location.href='deleteWfmidTemplateConfigure.action?id='+id+'&pageno=<%=wtcPage.getPageNo()%>&pageMap=<%=wtcMap%>&bizType=<%=bizType%>&pageSize=<%=pageSize%>';
						//alert("删除成功");
					}else{
						//window.location.reload();
						window.location.href='wfmidTemplateConfigureList.action?pageno=<%=wtcPage.getPageNo()%>&pageMap=<%=wtcMap%>&bizType=<%=bizType%>&pageSize=<%=pageSize%>';
					}
				}
			})
		})
})
</script>
</body>
</html>
