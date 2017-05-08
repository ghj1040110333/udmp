<%@ page import="activiti_maven_project.com.git.mybatis.model.WfmidLoginfo"%>
<%@ page import="activiti_maven_project.com.git.common.DesUtil"%>
<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String  usercd = request.getParameter("usercd");
	WfmidLoginfo loginfo = (WfmidLoginfo) request.getAttribute("loginfo");
	String processId = request.getParameter("processId");
	String  pageno = request.getParameter("pageno");
	String infoText;
	if(loginfo.getInfoText()!=null && !"".equals(loginfo.getInfoText())){
		
		infoText=DesUtil.decrypt(loginfo.getInfoText());
		//infoText = new String(infoText.getBytes("iso-8859-1"),"utf-8");
	}else{
		infoText="暂无内容";
	}
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- <meta http-equiv= "Refresh" content= "0;url=list.jsp "> -->
<title>jQuery EasyUI</title>
<link rel="stylesheet" type="text/css" href="css/reset.css">
<link rel="stylesheet" type="text/css" href="css/um-page.css">
<link rel="stylesheet" href="<%=basePath %>/css/default.css" type="text/css" />
</head>
<body>

<div class="um-span-left">
	<input onclick="location.href='showLoginfo.action?processId=<%=processId %>&pageno=<%=pageno %>&usercd=<%=usercd %>'" type="button" value="返回" class="um-btn" />
</div>
<div class="table-down-text">
			<table id="um-table-reload" class="um-table">
				<thead>
					<tr>
						<th>文本信息</th>
					</tr>					
				</thead>
				<tbody>
					
					<tr>
						<td><textarea class="text-textarea" rows="3" cols="10" name="comments" ><%=infoText%></textarea></td>
					</tr>
					
				</tbody>
			</table>
</div>
</body>
</html>