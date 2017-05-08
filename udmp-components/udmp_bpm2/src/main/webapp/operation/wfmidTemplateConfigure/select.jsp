<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidActivityConfigureMapperImpl"%>
<%@page import="org.springframework.context.support.FileSystemXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidActivityConfigure"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="activiti_maven_project.com.git.mybatis.common.page.Page"%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String processTemplateId=request.getParameter("processTemplateId");
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String wfid = request.getParameter("wfid");
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
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link rel="stylesheet" type="text/css" href="css/temp_table.css">
<link rel="stylesheet" type="text/css" href="css/temp_form.css">

  </head>
  
  <body>

  	<form action="">
		<div id="container2" align="center" >
		<a>节点表中，模板ID为【<%=processTemplateId %>】的相关信息</a>
		</div>
	</form>
   
    <div id="container">
    <center>
			<table class="zebra">
				<thead>
					<tr>
						<th>序号</th>
						<th>id值</th>
						<th>节点名称</th>
						<th>URL地址</th>
						<th>WSDL</th>
						<th>方法名称</th>
						<th>模板ID</th>
						<th>会签方式</th>
						<th>是否会签</th>
						<th>方法参数标签</th>
					</tr>
				</thead>
				<tbody>
					<%					
						ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
						WfmidActivityConfigureMapperImpl us =(WfmidActivityConfigureMapperImpl)ctx.getBean("wfmidActivityConfigureMapperImpl");
						
						List<WfmidActivityConfigure> list=us.selectByTemplateId(processTemplateId,"1");
						for (int i = 0; i < list.size(); i++) {
							WfmidActivityConfigure vo = list.get(i);							
					%>
					<tr>
						<td><%=i+1 %></td>
						<td><%=vo.getId() %></td>
						<td><%=vo.getActivityName() %></td>
						<td><%=vo.getUrl() %></td>
						<td><%=vo.getWsdl() %></td>
						<td><%=vo.getMethodName() %></td>
						<td><%=vo.getTemplateId() %></td>
						<td><%=vo.getIsMeeting()%></td>
						<td><%=vo.getMeetingType() %></td>
						<td><%=vo.getMethodTag()%></td>
					</tr>										
				<%
				}
				%>
					<tr></tr>
				</tbody>
			</table>
			</center>
		</div>
	
    
  </body>
</html>
