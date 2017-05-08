<%@page import="activiti_maven_project.com.git.mybatis.client.impl.WfmidTemplateConfigureMapperImpl"%>
<%@page import="org.springframework.context.support.FileSystemXmlApplicationContext"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="activiti_maven_project.com.git.mybatis.model.WfmidTemplateConfigure"%>
<%@ page language="java" pageEncoding="UTF-8"%> 
<%@ page contentType="text/html; charset=utf-8" %>
<%@page import="java.io.*"%>
<%@page import="java.util.*"%>
<%
	//String id=request.getParameter("id");
	String bizType=request.getParameter("wfmidTemplateConfigure.bizType");
	String processTemplateId=request.getParameter("wfmidTemplateConfigure.processTemplateId");
	String isEffect=request.getParameter("wfmidTemplateConfigure.isEffect");
	String version=request.getParameter("wfmidTemplateConfigure.version");
	String timerValue=request.getParameter("wfmidTemplateConfigure.timerValue");
	String timerType=request.getParameter("wfmidTemplateConfigure.timerType");
	
	WfmidTemplateConfigure configure = new WfmidTemplateConfigure();
	configure.setBizType(bizType);
	configure.setId("");
	configure.setIsEffect(isEffect);
	configure.setProcessTemplateId(processTemplateId);
	configure.setTimerType(timerType);
	configure.setTimerValue(timerValue);
	configure.setVersion(version);
	ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:applicationContext.xml");
	WfmidTemplateConfigureMapperImpl us =(WfmidTemplateConfigureMapperImpl)ctx.getBean("wfmidTemplateConfigureMapperImpl");
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
