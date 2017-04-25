<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	function startServerPort(){
		var serverListenPort=$("#serverListenPort").val();
		if(serverListenPort==""){
			alertMsg.error("请输入端口号");
		}else{
			$.ajax({
				type : "POST",
				data : {serverListenPort:serverListenPort} ,
				dataType : "json",
				url : "batchServerPort/startServerPort_batchServerPortAction.action?serverListenPort="+serverListenPort,
				success : function(data) {				
					alertMsg.info("启动成功");
					$("#startServerPortButton").attr("disabled", true);
					$("#stopServerPortButton").attr("disabled", false);
					$("#serverStatus").text(data.serverStatus);
					$("#serverListenPort").attr("disabled", true);
				}
			});
		}
	}
	
	function stopServerPort(){
		var serverListenPort=$("#serverListenPort").val();
		if(serverListenPort==""){
			alertMsg.error("请输入端口号");
		}else{
			$.ajax({
				type : "POST",
				data : {serverListenPort:serverListenPort} ,
				dataType : "json",
				url : "batchServerPort/stopServerPort_batchServerPortAction.action?serverListenPort="+serverListenPort,
				success : function(data) {
					alertMsg.info("停止成功");
					$("#startServerPortButton").attr("disabled", false);
					$("#stopServerPortButton").attr("disabled", true);
					$("#serverStatus").text(data.serverStatus);
					$("#serverListenPort").attr("disabled", false);
				}
			});
		}
	}

	function serverPortInit(){
		if($("#status").val()=="已启动"){
			$("#startServerPortButton").attr("disabled", true);
			$("#stopServerPortButton").attr("disabled", false);
			$("#serverStatus").text("已启动");
			$("#serverListenPort").attr("disabled", true);
		}else{
			$("#startServerPortButton").attr("disabled", false);
			$("#stopServerPortButton").attr("disabled", true);
			$("#serverStatus").text("已停用");
			$("#serverListenPort").attr("disabled", false);
		};		
	}
	$(document).ready(serverPortInit());
</script>

<div class="pageHeader">
	<div class="panel">
		<h1>Server端口启停管理</h1>
		<div class="searchBar">
		<input type="hidden" id="status" value="<s:property value="serverPortstatus"/>"  />
			<table width=100%>
				<tr height="25" align="center">
					<td width="10%">监听端口号: </td>
					<td width="20%"><input type="text" id="serverListenPort" value="8888"
						class="required" alt="请输入端口号"></td>
					<td width="20"><label id="serverStatus" /></td>
					<td width="50%"><button type="button"
							id="startServerPortButton" onclick="startServerPort()">启动</button>
						<button type="button" id="stopServerPortButton"
							onclick="stopServerPort()">停止</button></td>
				</tr>
			</table>
		</div>
	</div>
</div>
