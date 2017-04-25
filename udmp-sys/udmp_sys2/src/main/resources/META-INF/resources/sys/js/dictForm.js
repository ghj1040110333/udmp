/**
 * 字典表单页面
 */

idefine(function(require,exports,module){
  //引入依赖文件
  var _tool = require('udmp/lib/util_tool');

  $('body').on('run.dictForm', function() {
    //表单提交
    $("#btnSubmit").on("click", function() {
      udmpAjax.post($ctx+"/sys/dict/save",_tool.serializeObject($("#inputForm")),function(data) {
        showInfo("success", data.message);
        hideModal();
      });
    });
  });

})
