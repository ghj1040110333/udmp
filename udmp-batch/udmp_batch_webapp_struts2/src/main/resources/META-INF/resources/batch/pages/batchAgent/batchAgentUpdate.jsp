<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- <%@ taglib uri="http://www.git.com.cn/taglib/udmp/field" prefix="Field" %> --%>
<%@ taglib uri="http://www.git.com.cn/taglib/udmp/dict" prefix="dict" %>
<script language="javascript" for="window" event="onload">
	function batchAgentUpdateInit() {
		$("#agentSystemSelect").val($("#agentselect").val());		
	};

	if (document.readyState == "complete") {
		batchAgentUpdateInit();
	}
</script>
<script>
	function formSubmit(){		
		var ip = $("#agentIp").val();				
		var port = $("#agentPort").val();
		if( (check_ip(ip)) && (check_port(port)) ){
			$("#updateBatchAgentForm").submit();
		}
	}
	
	function check_ip(obj) {
		var exp = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
		var reg = obj.match(exp);
		if (reg == null) {
			alertMsg.error("请输入合法的计算机ip地址!");
			return false;
		} 
		return true;
	}

	function check_port(obj) {
		if (obj <= 65535 && obj >= 0) {
			return true;
		}
		alertMsg.error("请输入合法的计算机ip地址端口号!");
		return false;
	}
</script>

<div class="pageContent">
	<form method="post" id="updateBatchAgentForm"
		action="batchAgent/update_batchAgentAction.action?menuId=${menuId}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<input type="hidden" name="batchAgentAVO.agentId" value="${batchAgentAVO.agentId}" />
		<input type="hidden" id="agentselect" value="${batchAgentAVO.agentSystem}" />
		<input type="hidden" id="isEnableselect" value="${batchAgentAVO.isEnable}" />
		<div class="pageFormContent nowrap" layoutH="50">
			<table width="100%" cellpadding="0" cellspacing="10" border="0">
				<tr height="25">
					<td width="17%">
						<div class="label" style="height: auto">代理名称：</div>
					</td>
					<td width="33%">
						<input type="text" name="batchAgentAVO.agentName" value="${batchAgentAVO.agentName}" maxlength="50" class="required" />
					</td>
					<td width="17%">
						<div class="label" style="height: auto">所属系统：</div>
					</td>
					<td width="33%">
<%-- 						<Field:codeTable name="batchAgentAVO.agentSystem"  value="${batchAgentAVO.agentSystem}" id="agentSystemSelect"	tableName="T_UDMP_BATCH_PARAM_SUB" whereClause="PARAM_ID=4" nullOption="false" disabled="false"/> --%>
						<input type="text" name="batchAgentAVO.agentSystem" value="${batchAgentAVO.agentSystem}" maxlength="50" class="required" />
					</td>					
				</tr>
				<tr height="25">
					<td width="17%">
						<div class="label" style="height: auto">服务器IP：</div>
					</td>
					<td width="33%">
						<input type="text" id="agentIp" name="batchAgentAVO.agentIp" value="${batchAgentAVO.agentIp}" maxlength="15" class="required" />
					</td>
					<td width="17%">
						<div class="label" style="height: auto">端口号：</div>
					</td>
					<td width="33%">
						<input type="text" id="agentPort" name="batchAgentAVO.agentPort" value="${batchAgentAVO.agentPort}" maxlength="5" class="required digits" />
					</td>				
				</tr>
				<tr height="25">
					<td width="17%">
						<div class="label" style="height: auto">线程阀值：</div>
					</td>
					<td width="33%">
						<input type="text" name="batchAgentAVO.agentThreadLimitCnt" value="${batchAgentAVO.agentThreadLimitCnt}" maxlength="4" class="required digits" />
					</td>
					<td width="17%">
					</td>
					<td width="33%">						
					</td>				
				</tr>
				<tr height="25">
					<td width="17%">
						<div class="label" style="height: auto">启用：</div>
					</td>
					<td width="33%">
						<s:if test="batchAgentAVO.isEnable==\"0\"">
							<input type="radio" name="batchAgentAVO.isEnable" value="0" checked="true" />否
							<input type="radio" name="batchAgentAVO.isEnable" value="1" />是
		               	</s:if>
						<s:elseif test="batchAgentAVO.isEnable==\"1\"">
							<input type="radio" name="batchAgentAVO.isEnable" value="0" />否
							<input type="radio" name="batchAgentAVO.isEnable" value="1" checked="true" />是
		               	</s:elseif>						
					</td>
					<td width="17%">
						<div class="label" style="height: auto">删除标记：</div>
					</td>
					<td width="33%">
						<s:if test="batchAgentAVO.isDeleted==\"0\"">
							<input type="radio" name="batchAgentAVO.isDeleted" value="0" checked="true" />否
							<input type="radio" name="batchAgentAVO.isDeleted" value="1" />是
		               	</s:if>
						<s:elseif test="batchAgentAVO.isDeleted==\"1\"">
							<input type="radio" name="batchAgentAVO.isDeleted" value="0" />否
							<input type="radio" name="batchAgentAVO.isDeleted" value="1" checked="true" />是
		               	</s:elseif>
					</td>				
				</tr>
				<tr height="25">
					<td>
						<a class="buttonActive" >
							<span>测试连接</span>
						</a>
					</td>
				</tr>	
			</table>
		</div>
		<div class="formBar">
			<ul style="margin-top: -3px;">
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="formSubmit()">保存</button>
						</div>
					</div>
				</li>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="button" onclick="$.pdialog.closeCurrent();">取消</button>
						</div>
					</div>
				</li>
			</ul>
		</div>
	</form>
</div>
