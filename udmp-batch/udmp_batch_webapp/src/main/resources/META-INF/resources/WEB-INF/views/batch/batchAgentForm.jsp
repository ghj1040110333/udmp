<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
  <form id="inputBatchAgentForm" method="post" class="form-horizontal" >
		<div>
			<input v-model="agentObj.agentId" name="agentId" type="hidden">
		</div>
	  <div class="form-group">
		    <label class="col-sm-2 control-label">
		      <span class="red">*</span>代理名称:
		    </label>
		    <div class="col-sm-3">
		    	<input v-model="agentObj.agentName" placeholder="edit me" name="agentName">
<!-- 		    	<input type="text" name="agentName"></input> -->
		    </div>
	  </div>
	  <div class="form-group">
		    <label class="col-sm-2 control-label">
		      <span class="red">*</span>所属系统:
		    </label>
		    <div class="col-sm-3">
		    	<input v-model="agentObj.agentSystem" placeholder="edit me" name="agentSystem">
		    </div>
	  </div>
	  <div class="form-group">
		    <label class="col-sm-2 control-label">
		      <span class="red">*</span>服务器IP:
		    </label>
		    <div class="col-sm-3">
		    	<input v-model="agentObj.agentIp" placeholder="edit me" name="agentIp">
		    </div>
	  </div>
	  <div class="form-group">
		    <label class="col-sm-2 control-label">
		      <span class="red">*</span>端口号:
		    </label>
		    <div class="col-sm-3">
		    	<input v-model="agentObj.agentPort" placeholder="edit me" name="agentPort">
		    </div>
	  </div>
	  <div class="form-group">
		    <label class="col-sm-2 control-label">
		      <span class="red">*</span>线程阀值:
		    </label>
		    <div class="col-sm-3">
		    	<input v-model="agentObj.agentThreadLimitCnt" placeholder="edit me" name="agentThreadLimitCnt">
		    </div>
	  </div>
	  <div class="form-group">
		    <label class="col-sm-2 control-label">
		      <span class="red">*</span>启用:
		    </label>
		    <div class="col-sm-3">
		    		<input v-model="agentObj.isEnable" type="radio" name="isEnable" value="0" />否
					<input v-model="agentObj.isEnable" type="radio" name="isEnable" value="1" />是		
		    </div>
	  </div>
	  <div class="form-group">
		    <label class="col-sm-2 control-label">
		      <span class="red">*</span>启用:
		    </label>
		    <div class="col-sm-3">
				<a class="buttonActive" >
							<span>测试连接</span>
				</a>
		    </div>
	  </div>
 </form>
	<div class="my-buttons">
	    <button id="btnSubmit" class="btn i-btn-ok" type="button">保 存</button>
	 	<button id="btnCancel" class="btn btn-default" type="button" data-dismiss="modal">返回</button>
	</div>

<script type="text/javascript">



  idefine('batchAgentForm',function(require,exports,module){
    var tools = require('udmp/lib/util_tool');
    $('body').on('run.batchAgentForm', function() {
      $("#btnSubmit").on("click", function() {
        $.post("${ctx}/batch/agent/save", tools.serializeObject($("#inputBatchAgentForm")), function(data) {
          if(data) {
            if(data.success) {
              window.showInfo("success","保存成功");
              window.hideModal();
            } else {
              window.showInfo("error", data.message);
            }
          }
        });
      });
    });
  });
  seajs.use('batchAgentForm',function(){
    $('body').trigger("run.batchAgentForm");
  });
  
  var msg = new Vue({
	  el : '#inputBatchAgentForm',
	  data: {
		agentObj: {},
	  }
  });
</script>
