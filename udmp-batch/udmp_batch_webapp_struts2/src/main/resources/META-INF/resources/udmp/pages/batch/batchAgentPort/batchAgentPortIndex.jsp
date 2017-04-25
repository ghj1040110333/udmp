<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
	function startAgentPort(){
		var agentListenPort=$("#agentListenPort").val();
		if(agentListenPort==""){
			alertMsg.error("请输入端口号");
		}else{
			$.ajax({
				type : "POST",
				data : {agentListenPort:agentListenPort} ,
				dataType : "json",
				url : "batchAgentPort/startAgentPort_batchAgentPortAction.action?agentListenPort="+agentListenPort,
				success : function(data) {
					alertMsg.info("启动成功");
					$("#startAgentPortButton").attr("disabled", true);
					$("#stopAgentPortButton").attr("disabled", false);
					$("#agentStatus").text(data.agentStatus);
					$("#agentListenPort").attr("disabled", true);
				}
			});
		}
	}

	function stopAgentPort(){
		var agentListenPort=$("#agentListenPort").val();
		if(agentListenPort==""){
			alertMsg.error("请输入端口号");
		}else{
			$.ajax({
				type : "POST",
				data : {agentListenPort:agentListenPort} ,
				dataType : "json",
				url : "batchAgentPort/stopAgentPort_batchAgentPortAction.action?agentListenPort="+agentListenPort,
				success : function(data) {
					alertMsg.info("停止成功");
					$("#startAgentPortButton").attr("disabled", false);
					$("#stopAgentPortButton").attr("disabled", true);
					$("#agentStatus").text(data.agentStatus);
					$("#agentListenPort").attr("disabled", false);
				}
			});
		}
	}

	function agentPortInit(){
		if($("#status").val()=="已启动"){
			$("#startAgentPortButton").attr("disabled", true);
			$("#stopAgentPortButton").attr("disabled", false);
			$("#serverStatus").text("已启动");
			$("#agentListenPort").attr("disabled", true);
		}else{
			$("#startAgentPortButton").attr("disabled", false);
			$("#stopAgentPortButton").attr("disabled", true);
			$("#serverStatus").text("已停用");
			$("#agentListenPort").attr("disabled", false);
		};		
	}
	$(document).ready(agentPortInit()); 
</script>

<div class="pageHeader">
	<div class="panel">
		<h1>Agent端口启停管理</h1>
		<div class="searchBar">
		<input type="hidden" id="status" value="<s:property value="agentPortStatus"/>"  />
			<table width=100%>
				<tr height="25" align="center">
					<td width="10%">本机ip：</td>
					<td width="10%">${localHost}</td>
					<td width="10%">监听端口号</td>
					<td width="20%"><input type="text" id="agentListenPort"
						class="required" alt="请输入端口号" value="8888"></td>
					<td width="10%"><label id="agentStatus" name="agentStatus" value="${agentStatus}"/></td>
					<td width="40%"><button type="button"
							id="startAgentPortButton" onclick="startAgentPort()">启动</button>
						<button type="button" id="stopAgentPortButton"
							onclick="stopAgentPort()">停止</button></td>
				</tr>
			</table>
		</div>
	</div>
</div>
