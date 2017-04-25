<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<script type="text/javascript" src="batch/pages/batchParam/batchParam.js"></script>

<!-- -----------隐藏 ----------- -->
<input type="hidden" name="subParamCnt" id="subParamCnt"
	value="${subParamCnt}" />

<div class="pageContent">
	<form id="subParamAddForm"
		action="batchParam/insertParam_batchParamAction.action?menuId=${menuId}"
		class="pageForm required-validate" method="post"
		onsubmit="return validateCallback(this, dialogAjaxDone)">
		<input type="hidden" name="paramVo.paramId" value="${paramVo.paramId}" />
		<input type="hidden" name="batchParamAVo.paramId"
			value="${batchParamAVo.paramId}" />
		<div class="pageFormContent" style="height: 120px";>
			<dl>
				<dt>参数类型</dt>
				<dd>
					<input name="batchParamAVo.paramType" type="radio" value="S"
						checked="true" />系统参数&nbsp; <input name="batchParamAVo.paramType"
						type="radio" value="B" />任务参数
				</dd>
			</dl>
			<dl>
				<dt>数据类型</dt>
				<dd>
					<select name="batchParamAVo.paramDataType">
						<option value="string" selected="selected">字符串</option>
						<option value="number">数值</option>
						<option value="date">日期</option>
					</select>
				</dd>
			</dl>
			<dl>
				<dt>参数名称</dt>
				<dd>
					<input type="text" name="batchParamAVo.paramName" maxlenght="20"
						class="required" />
				</dd>
			</dl>
			<dl>
				<dt>参数代码</dt>
				<dd>
					<input type="text" name="batchParamAVo.paramCode" maxlenght="200"
						class="required" />
				</dd>
			</dl>
			<dl>
				<dt>参数描述</dt>
				<input type="text" name="batchParamAVo.paramDesc" maxlenght="3000" />
				</dd>
			</dl>
			<dl>
				<dt>单值标记</dt>
				<dd>
					<input name="batchParamAVo.isSingleValue" type="radio" value="1"
						onclick="isSingleValue(this);" checked="true" />单值&nbsp; <input
						name="batchParamAVo.isSingleValue" type="radio" value="0"
						onclick="isSingleValue(this);" />多值
				</dd>
			</dl>
			<dl>
				<dt>参数值</dt>
				<dd>
					<input id="paramValue" type="text" name="batchParamAVo.paramValue"
						maxlenght="3000" />
				</dd>
			</dl>
		</div>

		<div id="subParam" style="display: none;">
			<div class="panel collapse">
				<h1>子参数列表</h1>
				<div>
					<table id="subParamTable" class="list" width="100%">
						<thead>
							<tr align="center">
								<th>选择</th>
								<th>子参数名称</th>
								<th>子参数值</th>
								<th>排序</th>
							</tr>
						</thead>
						<tbody id="subParamList" name="subParamList">
							<tr style="display: none" />
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
							<button type="submit">保存</button>
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
