<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<script type="text/javascript">

</script>

<div class="pageContent">
	<form id="subParamUpdateForm"
		action="batchAsyncJob/startBatchAsyncJob_batchAsyncJobAction.action?menuId=${menuId}"
		class="pageForm required-validate" method="post"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<div class="pageFormContent";>
			<dl>
				<dt>任务名称</dt>
				<dd>${batchAsyncJobAVO.jobName}</dd>
				<input type="hidden" name="batchAsyncJobAVO.jobId" value="${batchAsyncJobAVO.jobId}">
			</dl>
		</div>

		<div id="subParam">
			<div class="panel collapse">
				<h1>任务参数列表</h1>
				<div>
					<table class="list" width="100%">
						<thead>
							<tr height="25" align="center">
								<th>参数名称</th>
								<th>必选参数</th>
								<th>数组</th>
								<th>手工录入</th>
								<th>手工录入参数类型</th>
								<th>非手工录入关联参数</th>
								<th>参数值</th>
							</tr>
						</thead>
						<tbody id="asyncJobParametersList">
							<s:iterator value="batchAsyncJobAVO.batchJobParamList"
								status="st">
								<tr align="center">
									<td align="center">${paramName} <input type="hidden" name="asyncJobParamName" value="${paramName}" /></td>
									<td align="center"><s:if test="isRequired==1">
											<input type="checkbox" checked="checked" disabled="disabled" />
										</s:if> <s:else>
											<input type="checkbox" disabled="disabled" />
										</s:else></td>
									<td align="center"><s:if test="isArray==1">
											<input type="checkbox" checked="checked" disabled="disabled" />
										</s:if> <s:else>
											<input type="checkbox" disabled="disabled" />
										</s:else> <input type="hidden" name="isArray" value="${isArray}" /></td>
									<td align="center"><s:if test="isManu==1">
											<input type="checkbox" checked="checked" disabled="disabled" />
										</s:if> <s:else>
											<input type="checkbox" disabled="disabled" />
										</s:else></td>
									<td align="center"><s:if test="isManu==1">
											<s:if test="paramType==\"date\"">
													日期
												</s:if>
											<s:elseif test="paramType==\"number\"">
		               								数值
		               							</s:elseif>
											<s:elseif test="paramType==\"string\"">
		               								字符串
		               							</s:elseif>
										</s:if></td>
									<td align="center">${paramManageVo.paramName}</td>
									<td><s:if test="isManu==1">
											<s:if test="paramType==\"date\"">
												<input type="expandDateYMD" name="paramValue" value="${paramValue}" class="<s:if test="isRequired==1">required</s:if>" />
											</s:if>
											<s:elseif test="paramType==\"number\"">
												<input type="text" name="paramValue" value="${paramValue}" class="<s:if test="isRequired==1">required</s:if>" />
											</s:elseif>
											<s:elseif test="paramType==\"string\"">
												<input type="text" name="paramValue" value="${paramValue}" class="<s:if test="isRequired==1">required</s:if>" />
											</s:elseif>
										</s:if> 
										<s:else>
											<s:if test="paramManageVo.isSingleValue==1"> ${paramValue} <input type="hidden" name="paramValue" value="${paramValue}" />
											</s:if>
											<s:else>
												<select name="paramValue">
													<s:iterator value="paramManageVo.subParamList" status="st">
														<s:if test="paramItemValue==paramValue">
															<option selected="selected" value="${paramItemValue}">${paramItemName}
															</option>
														</s:if>
														<s:else>
															<option value="${paramItemValue}">${paramItemName}</option>
														</s:else>
													</s:iterator>
												</select>
											</s:else>
										</s:else>
									</td>
								</tr>
							</s:iterator>
						</tbody>
					</table>
				</div>
			</div>
		</div>

		<div class="formBar">
			<ul>
				<li>
					<div class="buttonActive">
						<div class="buttonContent">
							<button type="submit">启动</button>
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
