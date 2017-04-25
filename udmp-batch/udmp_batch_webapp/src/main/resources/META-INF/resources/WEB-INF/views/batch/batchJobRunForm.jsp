<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
  <form id="inputBatchJobRunForm" method="post" class="form-horizontal" >
		<div>
			<input v-model="jobRunObj.jobRunId" name="jobRunId" type="hidden">
		</div>
	  <div class="form-group">
		    <label class="col-sm-2 control-label">
		      <span class="red">*</span>作业名称:
		    </label>
		    <div class="col-sm-3">
		    	<label v-model="jobRunObj.jobRunId"  name="jobRunId"></label>
<!-- 		    	<input type="text" name="jobRunName"></input> -->
		    </div>
	  </div>
	  <div class="form-group">
		    <label class="col-sm-2 control-label">
		      <span class="red">*</span>所属系统:
		    </label>
		    <div class="col-sm-3">
		    	<input v-model="jobRunObj.jobRunSystem" placeholder="edit me" name="jobRunSystem">
		    </div>
	  </div>
	  <div class="form-group">
		    <label class="col-sm-2 control-label">
		      <span class="red">*</span>作业类:
		    </label>
		    <div class="col-sm-3">
		    	<input v-model="jobRunObj.jobRunClazz" placeholder="edit me" name="jobRunClazz">
		    </div>
	  </div>
	  <div class="form-group">
		    <label class="col-sm-2 control-label">
		      <span class="red">*</span>是否spring bean:
		    </label>
		    <div class="col-sm-3">
		    		<input v-model="jobRunObj.isSpringBean" type="radio" name="isSpringBean" value="0" />否
					<input v-model="jobRunObj.isSpringBean" type="radio" name="isSpringBean" value="1" />是		
		    </div>
	  </div>
	<table id="table"
               data-toggle="table"
               data-pagination="true"
               data-show-export="true">
            <thead>
            <tr>
                <th data-field="id">ID</th>
                <th data-field="name" data-editable="true">Item Name</th>
                <th data-field="price" data-editable="true">Item Price</th>
            </tr>
            </thead>
        </table>
 </form>
	<div class="my-buttons">
	    <button id="btnSubmit" class="btn i-btn-ok" type="button">保 存</button>
	 	<button id="btnCancel" class="btn btn-default" type="button" data-dismiss="modal">返回</button>
	</div>

<script type="text/javascript">
  seajs.use('js/batch/batchJobRunForm');

  idefine('batchJobRunForm',function(require,exports,module){
    var tools = require('udmp/lib/util_tool');
    $('body').on('run.batchJobRunForm', function() {
      $("#btnSubmit").on("click", function() {
        $.post("${ctx}/batch/jobRunLog/save", tools.serializeObject($("#inputBatchJobRunForm")), function(data) {
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
  seajs.use('batchJobRunForm',function(){
    $('body').trigger("run.batchJobRunForm");
  });
  
  var msg = new Vue({
	  el : '#inputBatchJobRunForm',
	  data: {
		jobRunObj: {},
	  }
  });
</script>
