<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%-- <%@ taglib uri="http://www.git.com.cn/taglib/udmp/field" prefix="Field" %> --%>
<%@ taglib uri="http://www.git.com.cn/taglib/udmp/dict" prefix="dict" %>
<script>
function formSubmit(){		
	var ip = $("#agentIp").val();				
	var port = $("#agentPort").val();
	if( (check_Ip(ip)) && (check_Port(port)) ){
		$("#insertBatchAgentForm").submit();
	}
}

function check_Ip(obj) {
	var exp = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])$/;
	var reg = obj.match(exp);
	if (reg == null) {
		alertMsg.error("请输入合法的计算机ip地址!");
		return false;
	} 
	return true;
}

function check_Port(obj) {
	if (obj <= 65535 && obj >= 0) {
		return true;
	}
	alertMsg.error("请输入合法的计算机ip地址端口号!");
	return false;
}

</script>

<div class="pageContent">
	<form method="post" id="insertBatchAgentForm"
		action="batchAgent/insertBatchAgent_batchAgentAction.action?menuId=${menuId}"
		class="pageForm required-validate"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent nowrap" layoutH="50">
			<table width="100%" cellpadding="0" cellspacing="10" border="0">
				<tr height="25">
					<td width="17%">
						<div class="label" style="height: auto">代理名称：</div>
					</td>
					<td width="33%">
						<input type="text" name="batchAgentAVO.agentName" maxlength="50" class="required" />
					</td>
					<td width="17%">
						<div class="label" style="height: auto">所属系统：</div>
					</td>
					<td width="33%">
<!-- 						<Field:codeTable name="batchAgentAVO.agentSystem" id="batchAgentAVO.agentSystem"  -->
<!-- 							tableName="T_UDMP_BATCH_PARAM_SUB" whereClause="PARAM_ID=4" nullOption="false" disabled="false"/> -->
<%-- 						<dict:select cssClass="combox" codeType="dept_positions" id="batchAgentAVO.agentSystem" name="batchAgentAVO.agentSystem" /> --%>
						<input type="text" name="batchAgentAVO.agentSystem" maxlength="50" class="required" />
					</td>
				</tr>
				<tr height="25">
					<td width="17%">
						<div class="label" style="height: auto">服务器IP：</div>
					</td>
					<td width="33%">
						<input type="text" id="agentIp" name="batchAgentAVO.agentIp" maxlength="15" class="required" />
					</td>
					<td width="17%">
						<div class="label" style="height: auto">端口号：</div>
					</td>
					<td width="33%">
						<input type="text" id="agentPort" name="batchAgentAVO.agentPort" maxlength="5" class="required digits" />
					</td>				
				</tr>
				<tr height="25">
					<td width="17%">
						<div class="label" style="height: auto">线程阀值：</div>
					</td>
					<td width="33%">
						<input type="text" id="agentThreadLimitCnt" name="batchAgentAVO.agentThreadLimitCnt" maxlength="4" class="required digits" />
					</td>
					<td width="17%">
						<div class="label" style="height: auto">启用：</div>
					</td>
					<td width="33%">
						<input type="radio" name="batchAgentAVO.isEnable" value="0" checked="true" />否
						<input type="radio" name="batchAgentAVO.isEnable" value="1" />是		
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
