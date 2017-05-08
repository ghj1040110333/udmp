<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidActivityWsField"%>
<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidActivityWsFieldMapperImpl"%>
<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidActivityConfigureMapperImpl"%>
<%@page import="org.springframework.context.support.FileSystemXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidActivityConfigure"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="activiti_maven_project.com.git.mybatis.common.page.Page"%>

<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String activeId=request.getParameter("activeId");
	activeId = new String(activeId.getBytes("iso-8859-1"),"utf-8");
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
		<a>节点配置表中，节点ID为【<%=activeId %>】的相关信息</a>
		</div>
	</form>
   
    <div id="container">
    <center>
			<table class="zebra">
				<thead>
					<tr>
						<th>序列号</th>
						<th>id</th>
						<th>参数名称</th>
						<th>值名称</th>
						<th>节点ID</th>
						<th>入参出参</th>
						<th>模板ID</th>
					</tr>
				</thead>
				<tbody>
					<%					
						ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
						WfmidActivityWsFieldMapperImpl us =(WfmidActivityWsFieldMapperImpl)ctx.getBean("wfmidActivityWsFieldMapperImpl");
						
						List<WfmidActivityWsField> list=us.selectByActiveId(activeId);
						for (int i = 0; i < list.size(); i++) {
							WfmidActivityWsField vo = list.get(i);							
					%>
					<tr>
						<td><%=i+1 %></td>
						<td><%=vo.getId() %></td>
						<td><%=vo.getParameterName() %></td>
						<td><%=vo.getValueName() %></td>
						<td><%=vo.getActiveId() %></td>
						<td><%=vo.getInOrOut() %></a></td>	
						<td><%=vo.getTemplateId() %></td>
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
