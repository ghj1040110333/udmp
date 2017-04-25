<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
  <form id="inputBatchJobForm" method="post" class="form-horizontal" >
		<div>
			<input v-model="jobObj.jobId" name="jobId" type="hidden">
		</div>
	  <div class="form-group">
		    <label class="col-sm-2 control-label">
		      <span class="red">*</span>任务名称:
		    </label>
		    <div class="col-sm-3">
		    	<input v-model="jobObj.jobName" placeholder="edit me" name="jobName">
<!-- 		    	<input type="text" name="jobName"></input> -->
		    </div>
	  </div>
	  <div class="form-group">
		    <label class="col-sm-2 control-label">
		      <span class="red">*</span>任务组ID:
		    </label>
		    <div class="col-sm-3">
		    	<input v-model="jobObj.jobGroupId" placeholder="edit me" name="jobGroupId">
		    </div>
	  </div>
	  <div class="form-group">
		    <label class="col-sm-2 control-label">
		      <span class="red">*</span>任务类型:
		    </label>
		    <div class="col-sm-3">
		    	<input v-model="jobObj.jobType" placeholder="edit me" name="jobType">
		    </div>
	  </div>
	  <div class="form-group">
		    <label class="col-sm-2 control-label">
		      <span class="red">*</span>对应作业:
		    </label>
		    <div class="col-sm-3">
		    	<input v-model="jobObj.taskId" placeholder="edit me" name="taskId">
		    </div>
	  </div>
 </form>
	<div class="my-buttons">
	    <button id="btnSubmit" class="btn i-btn-ok" type="button">保 存</button>
	 	<button id="btnCancel" class="btn btn-default" type="button" data-dismiss="modal">返回</button>
	</div>

<script type="text/javascript">
  seajs.use('js/batch/batchJobForm');

  idefine('jobForm',function(require,exports,module){
    var tools = require('udmp/lib/util_tool');
    $('body').on('run.jobForm', function() {
      $("#btnSubmit").on("click", function() {
        $.post("${ctx}/batch/job/save", tools.serializeObject($("#inputBatchJobForm")), function(data) {
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
  seajs.use('jobForm',function(){
    $('body').trigger("run.jobForm");
  });
  
  var msg = new Vue({
	  el : '#inputBatchJobForm',
	  data: {
		jobObj: {},
	  }
  });
</script>
