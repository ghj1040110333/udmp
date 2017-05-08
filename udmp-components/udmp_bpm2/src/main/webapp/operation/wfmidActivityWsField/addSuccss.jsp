<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidActivityWsFieldMapperImpl"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidActivityWsField"%>
<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidTemplateConfigureMapperImpl"%>
<%@page import="org.springframework.context.support.FileSystemXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidTemplateConfigure"%>
<%@page language="java" pageEncoding="UTF-8"%> 
<%@page contentType="text/html; charset=utf-8" %>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%
	//String id=request.getParameter("id");
	String parameterName=request.getParameter("wfmidActivityWsField.parameterName");
	String valueName=request.getParameter("wfmidActivityWsField.valueName");
	String activeId=request.getParameter("wfmidActivityWsField.activeId");
	String inOrOut=request.getParameter("wfmidActivityWsField.inOrOut");
	String templateId=request.getParameter("wfmidActivityWsField.templateId");
	activeId = new String(activeId.getBytes("iso-8859-1"),"utf-8");
	out.println("添加的activeId【"+activeId+"】");
	WfmidActivityWsField configure = new WfmidActivityWsField();
	configure.setParameterName(parameterName);
	configure.setId("");
	configure.setValueName(valueName);
	configure.setActiveId(activeId);
	configure.setInOrOut(inOrOut);
	configure.setTemplateId(templateId);
	ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
	WfmidActivityWsFieldMapperImpl us =(WfmidActivityWsFieldMapperImpl)ctx.getBean("wfmidActivityWsFieldMapperImpl");
	us.insert(configure);
	out.println("添加成功");
	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv= "Refresh" content= "0;url=list.jsp ">
<title>jQuery EasyUI</title>

</head>
<body>
</body>
</html>
