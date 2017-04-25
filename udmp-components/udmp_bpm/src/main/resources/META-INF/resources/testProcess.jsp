<%@page import="java.util.HashMap"%>
<%@page import="java.util.Map"%>
<%@page import="cn.com.git.udmp.modules.act.service.ActTaskService"%>
<%@page import="cn.com.git.udmp.common.utils.SpringContextHolder"%>
<%

	ActTaskService actTaskService = SpringContextHolder.getBean("actTaskService");
	Map<String, Object> map = new HashMap<String, Object>();
	map.put("userName", "thinkgem");
	String processId = actTaskService.startProcess("process", null, "myid", "我的字典表",map);
	
%>