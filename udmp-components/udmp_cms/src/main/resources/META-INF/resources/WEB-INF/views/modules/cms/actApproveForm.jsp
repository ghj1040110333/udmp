<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>${fns:getConfig('productName')}</title>
<meta name="decorator" content="default"/>
<script type="text/javascript">
	$(document).ready(function() {
		//$("#name").focus();
		$("input").css("height","30px");
		$("#inputForm").validate({
			submitHandler: function(form){
				loading('正在提交，请稍等...');
				form.submit();
			},
			errorContainer: "#messageBox",
			errorPlacement: function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});
	});
	
</script>
</head>
<body>
	<div>
		<div class="body">
			<form:form id="inputForm" modelAttribute="cusCustomer" action="${ctx}/approve/task/complete/" method="post" class="form-horizontal">
				 <input type=hidden id="taskid" value=${(act.taskId)}>
				 <input type=hidden id="procInsId" value=${(act.procInsId)}>
				<table class="table">
					<tr> 
						<td>
								<th>客户信息</th>
						</td>
					</tr>
					<tr>
						<td>
							<div class="control-group">
								<label class="control-label">工作收入：</label>
								<div class="controls">
									<form:input path="workIncome" htmlEscape="false" class="isFloat" readonly="true"/>
									<span class="help-inline"><font >元</font> </span>
								</div>
							</div>
						</td>
						<td>
							<div class="control-group">
								<label class="control-label">其他收入：</label>
								<div class="controls">
									<form:input path="otherIncome" htmlEscape="false" class="isFloat" readonly="true"/>
									<span class="help-inline"><font >元</font> </span>
								</div>
							</div>
						</td>
						
					</tr>
					<tr>
						<td>
							<div class="control-group">
								<label class="control-label">其他贷款：</label>
								<div class="controls">
									<form:input path="otherLoan" htmlEscape="false" class="isFloat" readonly="true"/>
									<span class="help-inline"><font >元</font> </span>
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="control-group">
								<label class="control-label">住宅电话：</label>
								<div class="controls">
									<form:input path="homePhone" htmlEscape="false" maxlength="25" class="phone" readonly="true"/>
								</div>
							</div>
						</td>
						<td>
							<div class="control-group">
								<label class="control-label">常用邮箱：</label>
								<div class="controls">
									<form:input path="email" htmlEscape="false" maxlength="100" class="email" readonly="true"/>
								</div>
							</div>
						</td>
					</tr>
				
					<tr>
						<td>
								<div class="control-group">
									<label class="control-label">居住地址：</label>
									<div class="controls">
										<form:input path="homeVillages" htmlEscape="false" maxlength="200" readonly="true"/>
										<span class="help-inline"><font >区</font> </span>
									</div>
								</div>
							</td>
							<td>
								<div class="control-group">
									<label class="control-label"></label>
									<div class="controls">
										<form:input path="homeVillages" htmlEscape="false" maxlength="200"  readonly="true"  />
										<span class="help-inline"><font >镇</font> </span>
									</div>
								</div>
						</td>
					</tr>
					<tr>
						<td>
								<div class="control-group">
									<label class="control-label"></label>
									<div class="controls">
										<form:input path="homeStreet" htmlEscape="false" maxlength="200"  readonly="true"  />
										<span class="help-inline"><font >街道</font> </span>
									</div>
								</div>
							</td>
							<td>
								<div class="control-group">
									<label class="control-label"></label>
									<div class="controls">
										<form:input path="homeCommunity" htmlEscape="false" maxlength="200"  readonly="true"  />
										<span class="help-inline"><font >小区</font> </span>
									</div>
								</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="control-group">
								<label class="control-label"></label>
								<div class="controls">
									<form:input path="homeRoomnum" htmlEscape="false" maxlength="200"  readonly="true"  />
									<span class="help-inline"><font >单元</font> </span>
								</div>
							</div>
						</td>
					</tr>
					
					 <tr>
						<td>
							<div class="control-group">
								<label class="control-label">QQ：</label>
								<div class="controls">
									<form:input path="qqNum" htmlEscape="false" maxlength="20"   readonly="true"  />
								</div>
							</div>
						</td>
						<td>
							<div class="control-group">
								<label class="control-label">淘宝：</label>
								<div class="controls">
									<form:input path="taobaoNum" htmlEscape="false" maxlength="100"  readonly="true"  />
								</div>
							</div>
						</td>
					</tr>
					<tr>
						<td>
							<div class="control-group">
								<label class="control-label">京东：</label>
								<div class="controls">
									<form:input path="jingdongNum" htmlEscape="false" maxlength="100"  readonly="true"  />
								</div>
							</div>
						</td>
						<td>
							<div class="control-group">
								<label class="control-label">新浪微博：</label>
								<div class="controls">
									<form:input path="sinaNum" htmlEscape="false" maxlength="100"  readonly="true"  />
								</div>
							</div>
					</td>
				</tr>
				<tr> 
					<td>
						<th>自动化决策信息</th>
					</td>
				</tr>
				 
				<tr> 
					<td>
						<th>人工决策信息</th>
					</td>
				</tr>
				<input type="hidden" name="contractNum"  value=${tbContract.contractNum} >
		</table>
						<div class="form-actions">
							<input  type="button" id="btnSubmit1"	value="同意"  class="btn i-btn-ok"  onclick="submitAct()"	/>&nbsp;
							<input  type="button" id="btnSubmit2" 	value="退回审查"  class="btn i-btn-ok"  	  onclick=""/>&nbsp;
						</div>
		
		 </form:form>
		</div>
	</div>
	
	<script type="text/javascript">
		function submitAct(){
			var taskid = document.getElementById("taskid").value;
			var procInsId = document.getElementById("procInsId").value;
			var contractNum = document.getElementsByName("contractNum")[0].value;
			$.ajax({  
		          type : "post",  
		          cache : false,
		          url : "${ctx}/approve/task/completeApprove",  
		          data : {taskId:taskid,procInsId:procInsId,contractNum:contractNum,endFlag:"1"},  
		          async : false,  
		          success : function(data){  
		             if(data=="OK"){
		            	 alert("审批完成，开始放款");
		            	 location.href = "${ctx}/act/task/todo";
		             }else if(data=="ERROR"){
		            	 alert("提交出错！");
		             }
		          }  
		     });
		}
	</script>
</body>
</html>