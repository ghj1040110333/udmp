<%@page import="java.util.Iterator"%>
<%@page import="java.util.HashMap"%>
<%@page import="activiti_maven_project.com.git.action.StringAndMap"%>
<%@ page  pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@page import="java.util.Map"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String pageSize = "";
	if(request.getAttribute("pageSize")!=null){
		pageSize=request.getAttribute("pageSize").toString();
	}

	String _bizType = "";
	String _customerName = "";
	String _customerNum = "";
	if(request.getAttribute("bizType")!=null){
		_bizType=request.getAttribute("bizType").toString();
	}
	if(request.getAttribute("customerName")!=null){
		_customerName=request.getAttribute("customerName").toString();
	}
	if(request.getAttribute("customerNum")!=null){
		_customerNum=request.getAttribute("customerNum").toString();
	}
	
	String  usercd= (String) request.getAttribute("usercd");
	String  pageno= (String) request.getAttribute("pageno");
	String  pageMap= (String) request.getAttribute("pageMap");
	String taskId=(String) request.getAttribute("taskId");
  	String  _map = request.getAttribute("_map").toString();
  	StringAndMap stringAndMap=new StringAndMap();
  	Map mapJsp =new HashMap();
  	Map map=stringAndMap.stringToMap(_map, mapJsp);
  	
	Iterator it = map.entrySet().iterator() ;
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
	<link rel="stylesheet" type="text/css" href="css/um-page.css">
	<link rel="stylesheet" type="text/css" href="css/reset.css">
	<link rel="stylesheet" type="text/css" href="css/temp_form.css">
  </head>
  <body>
   <% 
		String msg=null;
		if(request.getAttribute("msg")!=null && !"".equals(request.getAttribute("msg"))){
			msg=request.getAttribute("msg").toString();
	%>
		<script >
				alert('<%=msg%>');
		</script>
	<% 
		}
	%>
	<center>
	<div class="table-request">
	<form action="saveTake.action?usercd=<%=usercd%>&pageno=<%=pageno%>&pageMap=<%=pageMap%>&taskId=<%=taskId %>&map=<%=_map%>&_bizType=<%=_bizType%>&_customerName=<%=_customerName%>&_customerNum=<%=_customerNum%>&pageSize=<%=pageSize %>" class="bootstrap-frm" method="post">
        	
        	<div style="font-size:20px"><legend>流程业务信息</legend><input style="border-style:none" value="" /></div>
        	
            <% 
        		while (it.hasNext()){
					Map.Entry entry = (Map.Entry) it.next() ;
					Object key = entry.getKey() ;
					Object value= entry.getValue() ;
			%>
			<div class="grid1_of_4" style=" margin-right: 40px">
				   <span>
				    	<input style="border-style:none" value="<%=key%>" name="<%=key.toString() %>_name"/><input class="um-input" type="text" value="<%=value%>" name="<%=key.toString()%>"/>
				   </span><br>
			</div> 
			 <%
             	} 
             %>
         	<div class="request-clear"><input type="submit" name="submit" value="保存" class="um-btn"/></div>
	</form>
	</div>
	</center>
	<div class="table-request-down">
  	<form action="submitJoinTask.action?usercd=<%=usercd%>&pageno=<%=pageno%>&pageMap=<%=pageMap%>&bizType=<%=_bizType%>&customerName=<%=_customerName%>&customerNum=<%=_customerNum%>&pageSize=<%=pageSize %>" class="bootstrap-frm" method="post">
   		<input type="hidden" name="taskId" value="<%=map.get("taskId") %>"/> 
  		<div><%=map.get("taskName") %>意见:</div><br/>

  		<span class="d-span"><textarea rows="3" cols="10" name="comments" ></textarea></span><br/>
  		路由:<input type="radio" value="PASS" name="SignResult">同意
			<input type="radio" value="OPPOSE" name="SignResult">否决
			<input type="radio" value="ABSTAIN" name="SignResult">弃权<br>
  		<input type="submit" value="确认" class="um-btn"/>
  		<input type="button" value="撤销申请" onclick="window.location.href='cancleTask.action?taskId=<%=map.get("taskId") %>&usercd=<%=usercd%>&pageno=<%=pageno%>&bizType=<%=_bizType%>&customerName=<%=_customerName%>&customerNum=<%=_customerNum%>&pageSize=<%=pageSize %>'" class="um-btn"/>
		<input type="button" value="返回" onclick="window.location.href='joinList.action?usercd=<%=usercd%>&pageno=<%=pageno%>&pageMap=<%=pageMap%>&bizType=<%=_bizType%>&customerName=<%=_customerName%>&customerNum=<%=_customerNum%>&pageSize=<%=pageSize %>'" class="um-btn"/>
	</form>
	</div>
<script>
	document.getElementsByName("operation")[0].checked="checked";
</script>
  </body>
</html>
