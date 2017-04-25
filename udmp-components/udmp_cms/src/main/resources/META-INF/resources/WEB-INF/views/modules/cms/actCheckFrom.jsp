<%@ page contentType="text/html;application/json; charset=utf-8" %>
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
	
	jQuery.validator.addMethod("isFloat", function(value, element) {         
        return /^[-\+]?\d+(\.\d+)?$/.test(value);         
   }, "只能包含数字、小数点等字符");
</script>
</head>
<body>
	<div>
		<div class="body">
			<form:form id="inputForm" modelAttribute="cusCustomer" action="${ctx}/approve/task/complete/" method="post" class="form-horizontal">
				 <input type=hidden id="taskid" value=${(act.taskId)}>
				 <input type=hidden id="procInsId" value=${(act.procInsId)}>
				  <input type=hidden id="act" value=${(cusCustomer.act)}>
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
						<th>合同信息</th>
					</td>
				</tr>
				<tr>
					<td>
						<div class="control-group">
							<label class="control-label">合同号：</label>
							<div class="controls">
								<input value=${tbContract.contractNum} htmlEscape="false" maxlength="100"  readonly="true"  />
							</div>
						</div>
					</td>
					<td>
						<div class="control-group">
							<label class="control-label">合同金额：</label>
							<div class="controls">
								<input value=${tbContract.contractTotalAmt} htmlEscape="false" maxlength="100"  readonly="true"  />
							</div>
						</div>
					</td>
				</tr>
				<tr>
					<td>
						<div class="control-group">
							<label class="control-label">合同期限：</label>
							<div class="controls">
								<input value=${tbContract.contractTerm} htmlEscape="false" maxlength="100"  readonly="true"  />
							</div>
						</div>
					</td>
					<td>
						<div class="control-group">
							<label class="control-label">执行利率：</label>
							<div class="controls">
								<input value=${tbContract.rateYear} htmlEscape="false" maxlength="100"  readonly="true"  />
							</div>
						</div>
					</td>
				</tr>
				<tr> 
					<td>
						<th>影像信息</th>
					</td>
				</tr>
				<c:forEach items="${documentList}" var="document">
					<c:set var="path" value="${document.path}" />
					<c:set var="type" value="${document.documentType}" />
					<tr>
						<td width='50%'>
						      <div  >
						         <img src="${imageServerUrl}?documentId=${document.id}&size=SIZE250"/>
						      </div>
		      				  <!-- <input type="checkbox"  name="pid" value="${type}" /> 选择  -->
						</td>
						<td width='50%'>审查意见：<input type='textarea'  name='opinion'/></td>
					</tr>
				</c:forEach>
				<input type="hidden" name="customerNum"  value=${cusCustomer.customerNum} >
				<input type="hidden" name="customerName"  value=${cusCustomer.customerName} >
				<input type="hidden" name="contractNum"  value=${tbContract.contractNum} >
				<tr> 
					<td>
						<th>审查意见</th>
					</td>
				</tr>
		</table>
						<div class="form-actions">
							<input  type="button" id="btnSubmit1"	value="通过"  class="btn i-btn-ok"  onclick="submitAct()"	/>&nbsp;
							<input  type="button" id="btnSubmit2" 	value="退回补件"  class="btn i-btn-ok"  	  onclick=""/>&nbsp;
						</div>
		
		 </form:form>
		</div>
	</div>
	
	<script type="text/javascript">
		 function submitAct(){
			var taskid = document.getElementById("taskid").value;
			var procInsId = document.getElementById("procInsId").value;
			var act = document.getElementById("act").value;
			var customerNum = document.getElementsByName("customerNum")[0].value;
			var customerName = document.getElementsByName("customerName")[0].value;
			var contractNum = document.getElementsByName("contractNum")[0].value;
			var opinionArr  = document.getElementsByName("opinion");
			var jsonStr ="" ;
			var str ;
			for (var i = opinionArr.length; i--;) {
			    str = "customNum:"+"\\"+customerNum+"\\"+",customName:"+"\\"+customerName+"\\"+",contractNum:"+"\\"+contractNum+"\\"+",opinion:"+"\\"+opinionArr[i].value+"\\" ;
			    jsonStr = jsonStr +"{"+ str +"}," ;
			}
			
			 jsonStr = "["+jsonStr.substr(0,jsonStr.length-1)+"]";
			 $.ajax({  
		          type : "post",  
		          cache : false,
		          url : "${ctx}/approve/task/completeCheck",  
		          data : {taskId:taskid,procInsId:procInsId,jsonStr:jsonStr,contractNum:contractNum,endFlag:"0"},  
		          async : false,  
		          success : function(data){  
		             if(data=="OK"){
		            	 alert("提交成功");
		            	 location.href = "${ctx}/act/task/todo";
		             }else if(data=="ERROR"){
		            	 alert("提交出错！");
		             }
		          }  
		     }); 
		} 
		$(":checkbox").click(function(){
			var val = $(this).val();
			var str ;
			if(this.checked){
				$("tr").eq(-1).after("<tr align='center'><td width='30%'>"+val+" 的审查意见：</td><td width='70%'><input type='textarea' name='opinion'/></td></tr> ");
			} 
		});   
		
	</script>
</body>
</html>