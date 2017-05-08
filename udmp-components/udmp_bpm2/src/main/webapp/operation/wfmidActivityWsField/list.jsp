<%@page import="activiti_maven_project.com.git.common.Page"%>
<%@page import="java.util.HashMap"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidActivityWsField"%>
<%@page contentType="text/html; charset=utf-8"%>
<%@page import="java.util.List"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
			
%>
<%
	String msg="";
	String wacPage="";
	String wtcPage="";
	String processTemplateId="";
	String activeId="";
	String usercd="";
	String wtcMap="";
	String wacMap="";
	String parameterName="";
	String wtcPageSize="";
	String wacPageSize="";
	String version="";
	int pageSize=0;
	if(request.getAttribute("processTemplateId")!=null){
		processTemplateId=request.getAttribute("processTemplateId").toString();
		//processTemplateId = new String(processTemplateId.getBytes("iso-8859-1"),"utf-8");
	}
	if(request.getAttribute("usercd")!=null){
		usercd=request.getAttribute("usercd").toString();
	}
	if(request.getAttribute("msg")!=null){
		msg=request.getAttribute("msg").toString();
	}
	if(request.getAttribute("wacPage")!=null){
		wacPage=request.getAttribute("wacPage").toString();
	}
	if(request.getAttribute("wtcPage")!=null){
		wtcPage=request.getAttribute("wtcPage").toString();
	}
	if(request.getAttribute("activeId")!=null){
		activeId=request.getAttribute("activeId").toString();
	}
	if(request.getAttribute("parameterName")!=null){
		parameterName=request.getAttribute("parameterName").toString();
	}
	if(request.getAttribute("version")!=null){
		version=request.getAttribute("version").toString();
	}
	if(request.getAttribute("wtcMap")!=null){
		wtcMap=request.getAttribute("wtcMap").toString();
	}
	if(request.getAttribute("wacMap")!=null){
		wacMap=request.getAttribute("wacMap").toString();
	}
	if(request.getAttribute("wtcPageSize")!=null){
		wtcPageSize=request.getAttribute("wtcPageSize").toString();
	}
	if(request.getAttribute("wacPageSize")!=null){
		wacPageSize=request.getAttribute("wacPageSize").toString();
	}
	if(request.getAttribute("pageSize")!=null && !"".equals(request.getAttribute("pageSize"))){
		pageSize=new Integer(request.getAttribute("pageSize").toString());
	}
	String wawfMap =request.getAttribute("stringMap").toString();
	Page wawfPage=(Page)request.getAttribute("wawfPage");
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
	<div class="um-s-box">
 	 	<form action="<%=basePath%>/wfmidActivityWsFieldList.action?activeIds=<%=activeId%>&msg=<%=msg%>&wacPage=<%=wacPage%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&usercd=<%=usercd%>&wacMap=<%=wacMap%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&wacPageSize=<%=wacPageSize%>&version=<%=version %>" method="post"> 	 		
			<span class="um-span"><label>参数名称：</label><input type="text" value="<%=parameterName %>" name="parameterName" class="um-input" /></span>
			<span class="um-span"><label>节点ID：</label><input type="text" value="<%=activeId %>" name="activeId" class="um-input" /></span>			
				  <input type="submit" value="查询"  class="um-btn" />
				
		</form>	
 	</div>
 	<%
 		if(msg!=null && !"".equals(msg)){
 			if(usercd!=null && !"".equals(usercd)){
 	%>
 				<div class="um-span-left">
 					<input onclick="location.href='wfmidActivityConfigureList.action?pageno=<%=wacPage %>&msg=msg&wtcPage=<%=wtcPage %>&usercd=<%=usercd %>&pageMap=<%=wacMap%>&pageSize=<%=wacPageSize %>&version=<%=version %>'" type="button" value="返回" class="um-btn" />
 				</div>
 		<%
 			}else{
 		%>
 			<div class="um-span-left">
 				<input onclick="location.href='wfmidActivityConfigureList.action?pageno=<%=wacPage %>&msg=msg&wtcPage=<%=wtcPage %>&processTemplateId=<%=processTemplateId %>&pageMap=<%=wacMap%>&wtcMap=<%=wtcMap%>&pageSize=<%=wacPageSize %>&wtcPageSize=<%=wtcPageSize %>&version=<%=version %>'" type="button" value="返回" class="um-btn" />
 			</div>
 	<%
 			}
 		}
 	%>
 	<div class="um-span-right">
 	<input id="addWindow" type="button" value="新增" class="um-btn" />
 	<% 
 		if(msg!=null && !"".equals(msg)){
 	%>
 			<input onclick="location.href='wfmidActivityWsFieldList.action?activeId=<%=activeId %>&msg=msg&wacPage=<%=wacPage%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&usercd=<%=usercd%>&wacMap=<%=wacMap%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&wacPageSize=<%=wacPageSize%>&version=<%=version %>'" type="button" value="列表首页" class="um-btn" />
 	<% 
 		}else{
 	%>
 			<input onclick="location.href='wfmidActivityWsFieldList.action?&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&wacPageSize=<%=wacPageSize%>'" type="button" value="列表首页" class="um-btn" />
	<% 
		}
	%>
	</div>
  <div class="table-wrapper">
			<table  id="um-table-reload" class="um-table" >
				<thead>
					<tr>
						<th>序列号</th>
						<th>参数名称</th>
						<th>值名称</th>
						<th>节点ID</th>
						<th>入参出参</th>
						<th>模板ID</th>
						<th>版本</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<%
						List list = (List) request.getAttribute("list");
						if(list.size()>0){
							for (int i = 0; i < list.size(); i++) {
								WfmidActivityWsField vo = (WfmidActivityWsField)list.get(i);
								String inOrOut=vo.getInOrOut();
								if(inOrOut!=null && !"".equals(inOrOut)){
 									if(inOrOut.equals("1")){
 										inOrOut="入参";
 									}else{
 										inOrOut="出参";
 									}
 								}
					%>
					 <tr>
						<td><%=i+1 %></td>
						<td><%=vo.getParameterName() %></td>
						<td><%=vo.getValueName() %></td>
						<td><%=vo.getActiveId() %></td>
						<td><%=inOrOut %></a></td>	
						<td><%=vo.getTemplateId() %></td>
						<td><%=vo.getVersion()%></td>
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
			 <a> 当前页【<%=wawfPage.getPageNo()%>】/总页数【<%=wawfPage.getTotalPage() %>】 &nbsp &nbsp
			 	每页显示 &nbsp<select name="pageSize" id="pageSize" onChange="pageSize()" class="um-select-pageSize">
							<option value =""><%=wawfPage.getPageSize()%></option>
							<% 
								if(wawfPage.getPageSize()!=5){
							%>
								<option value ="5">5</option>
							<% 
								}
								if(wawfPage.getPageSize()!=10){
							%>
								<option value ="10">10</option>
							<% 
								}
								if(wawfPage.getPageSize()!=15){
							%>								
								<option value ="15">15</option>
							<% 
								}
								if(wawfPage.getPageSize()!=20){
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
		if(<%=wawfPage.getPageNo()%>==1 || <%=wawfPage.getPageNo()%>==0){
			alert("您已经到达第一页");
		}
		else{	
			window.location.href=encodeURI("<%=basePath%>/previous.action?wawwfPage=<%=wawfPage.getPageNo()%>&pageMap=<%=wawfMap%>&usercd=<%=usercd %>&msg=<%=msg%>&wacPage=<%=wacPage%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&activeId=<%=activeId%>&parameterName=<%=parameterName%>&wacMap=<%=wacMap%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&wacPageSize=<%=wacPageSize%>&version=<%=version %>");	
		}
	}
	function next()
	{		
		if(<%=wawfPage.getPageNo()%>==<%=wawfPage.getTotalPage()%>){
			alert("您已经到达最后一页");
		}
		else{	
			window.location.href=encodeURI("<%=basePath%>/next.action?wawwfPage=<%=wawfPage.getPageNo()%>&pageMap=<%=wawfMap%>&usercd=<%=usercd %>&msg=<%=msg%>&wacPage=<%=wacPage%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&activeId=<%=activeId%>&parameterName=<%=parameterName%>&wacMap=<%=wacMap%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&wacPageSize=<%=wacPageSize%>&version=<%=version %>");	
		}
	}
	function toppage()
	{		
		if(<%=wawfPage.getPageNo()%>==1 || <%=wawfPage.getPageNo()%>==0){
			alert("您已经到达首页");
		}
		else{	
			window.location.href=encodeURI("<%=basePath%>/toppage.action?wawwfPage=<%=wawfPage.getPageNo()%>&pageMap=<%=wawfMap%>&usercd=<%=usercd %>&msg=<%=msg%>&wacPage=<%=wacPage%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&activeId=<%=activeId%>&parameterName=<%=parameterName%>&wacMap=<%=wacMap%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&wacPageSize=<%=wacPageSize%>&version=<%=version %>");	
		}
	}
	function endpage()
	{		
		if(<%=wawfPage.getPageNo()%>==<%=wawfPage.getTotalPage()%>){
			alert("您已经到达最后一页");
		}
		else{	
			window.location.href=encodeURI("<%=basePath%>/endpage.action?pagetotleWawf=<%=wawfPage.getTotalPage()%>&pageMap=<%=wawfMap%>&usercd=<%=usercd %>&msg=<%=msg%>&wacPage=<%=wacPage%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&activeId=<%=activeId%>&parameterName=<%=parameterName%>&wacMap=<%=wacMap%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&wacPageSize=<%=wacPageSize%>&version=<%=version %>");	
		}
	}
	function pageSize(){
		var pageSize= document.getElementById("pageSize").value;
		window.location.href="<%=basePath%>/wfmidActivityWsFieldList.action?pageSize="+pageSize+"&pageno=<%=wawfPage.getPageNo()%>&pageMap=<%=wawfMap%>&msg=<%=msg%>&wacPage=<%=wacPage%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&activeId=<%=activeId%>&usercd=<%=usercd %>&parameterName=<%=parameterName%>&wacMap=<%=wacMap%>&wtcMap=<%=wtcMap%>&wtcPageSize=<%=wtcPageSize %>&wacPageSize=<%=wacPageSize%>&version=<%=version %>";	
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
				content:encodeURI("url:addSelectIDatwf.action?activeId=<%=activeId%>&templateId=<%=processTemplateId%>&version=<%=version %>"),
				icon: 'error.gif',
				cancel: function () {
					flag=true;
					var win=this.iframe.contentWindow;
					var doc=win.document;
					$("#btn",doc).click();
					window.location.href='wfmidActivityWsFieldList.action?pageno=<%=wawfPage.getPageNo()%>&msg=<%=msg%>&wacPage=<%=wacPage%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&activeId=<%=activeId%>&usercd=<%=usercd %>&parameterName=<%=parameterName%>&wacMap=<%=wacMap%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&wacPageSize=<%=wacPageSize%>&version=<%=version %>';
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
							content:"url:updateSelectIDawfd.action?id="+id+"&msg=<%=msg%>",
							icon: 'error.gif',
							//okVal:"确定",
							//ok: function () {
								//flag=true;
								//var win=this.iframe.contentWindow;
								//var doc=win.document;
								//$("#btn",doc).click();
								//window.location.href='wfmidActivityWsFieldList.action';
								//return false;
							//},
							cancel: function () {
								flag=true;
								var win=this.iframe.contentWindow;
								var doc=win.document;
								$("#btn",doc).click();
								window.location.href='wfmidActivityWsFieldList.action?pageno=<%=wawfPage.getPageNo()%>&pageMap=<%=wawfMap%>&msg=<%=msg%>&wacPage=<%=wacPage%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&activeId=<%=activeId%>&usercd=<%=usercd %>&parameterName=<%=parameterName%>&wacMap=<%=wacMap%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&wacPageSize=<%=wacPageSize%>&version=<%=version %>';
							},
						});
					}
				if(val == "delete"){
					if(confirm("确定要删除吗？")){
						window.location.href='deleteWfmidActivityWsField.action?id='+id+'&pageno=<%=wawfPage.getPageNo()%>&pageMap=<%=wawfMap%>&msg=<%=msg%>&wacPage=<%=wacPage%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&activeId=<%=activeId%>&usercd=<%=usercd %>&parameterName=<%=parameterName%>&wacMap=<%=wacMap%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&wacPageSize=<%=wacPageSize%>&version=<%=version %>';
						alert("删除成功");
					}else{
						window.location.href='wfmidActivityWsFieldList.action?pageno=<%=wawfPage.getPageNo()%>&pageMap=<%=wawfMap%>&msg=<%=msg%>&wacPage=<%=wacPage%>&wtcPage=<%=wtcPage%>&processTemplateId=<%=processTemplateId%>&activeId=<%=activeId%>&usercd=<%=usercd %>&parameterName=<%=parameterName%>&wacMap=<%=wacMap%>&wtcMap=<%=wtcMap%>&pageSize=<%=pageSize %>&wtcPageSize=<%=wtcPageSize %>&wacPageSize=<%=wacPageSize%>&version=<%=version %>';
					}
				}
			})
		})
})
</script>
</body>
</html>
