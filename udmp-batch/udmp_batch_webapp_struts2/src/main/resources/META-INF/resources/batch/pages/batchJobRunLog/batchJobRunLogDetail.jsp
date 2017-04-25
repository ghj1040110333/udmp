<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<script type="text/javascript">
	$(document).ready(function() {
		var logLevelVal = $("#logLevelHidden").val();

		if (logLevelVal == 0) {
			$("#Level").val("Debug");
		} else if (logLevelVal == 1) {
			$("#Level").attr("value", "Info");
		} else if (logLevelVal == 2) {
			$("#Level").val("Error");
		} else if (logLevelVal == 3) {
			$("#Level").val("Fatal");
		}

		$("#logInfo").val($("#logInfoInput").val());

	});
</script>

<div class="pageContent">
	<div class="pageFormContent" layoutH="60">
		<table width="100%" cellpadding="0" cellspacing="10" border="0">
			<tr height="25">
				<td width="17%"><div class="label" style="height: auto">日志ID:</div></td>
				<td width="33%"><input type="text" readonly
					value="${batchJobRunLogAVO.jobRunLogId}"></td>
				<td width="17%"><div class="label" style="height: auto">记录日期:</div></td>
				<td width="33%"><input type="text" readonly
					value="<s:date name="batchJobRunLogAVO.logTime" format="yyyy-MM-dd HH:mm:ss" />" />
				</td>
			</tr>
			<tr height="25">
				<td width="17%"><div class="label" style="height: auto">日志级别:</div></td>
				<td width="33%"><input type="hidden" id="logLevelHidden"
					value="${batchJobRunLogAVO.logLevel}"> <input type="text"
					id="Level" readonly name="logLevel"></td>
				<td width="17%"><div class="label" style="height: auto">运行实例编号:</div></td>
				<td width="33%"><input type="text" readonly
					value="${batchJobRunLogAVO.jobRunId}"></td>
			</tr>
		</table>
		<div style="margin: 10px 5px;">
			<input type="hidden" id="logInfoInput"
				value="${batchJobRunLogAVO.logInfo}">
			<textarea id="logInfo" class="textInput readonly" rows="10" cols="90"
				readonly="true"></textarea>
		</div>
	</div>
	<div class="formBar">
		<ul>
			<li>
				<div class="buttonActive">
					<div class="buttonContent">
						<button type="button" onclick="$.pdialog.closeCurrent();">确定</button>
					</div>
				</div>
			</li>
		</ul>
	</div>
</div>

