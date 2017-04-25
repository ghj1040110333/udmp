/**
 * 字典管理页面
 */

idefine(function(require,exports,module){
  //引入依赖文件
  require('bin/seajs-handler');
  require('udmp/widget/common/Layout.min');
  require('udmp/widget/common/Form.min');
  var data = UdmpLayout.table,
    wt = window.top;

  //Vue调试开启
  Vue.config.devtools = true
  Vue.config.debug = true

  //初始化Vue组件
  var vComponents = new Vue({
    el: '#i-manage-panel',
    components: {
      udmpSearch: UdmpLayout.search,
      udmpData: data,
      udmpInput: UdmpForm.input,
      udmpSelect: UdmpForm.select
    },
    data: {
      table: [
        {name:'value',value:'键值'},
        {name:'label',value:'标签',style:'cellStyle'},
        {name:'type',value:'类型'},
        {name:'description',value:'描述'},
        {handle:'handleBtnFormatter',value:'操作'}
      ],
      options: {
        uniqueId : 'id'
      },
      selectUrl: $ctx+"/sys/dict/listType",
      tableUrl: $ctx+"/sys/dict/listPageData",
    },
    methods: {
      addDict: function() {
        wt.showModal($ctx+"/sys/dict/form", null, {
          title : "添加字典",
          width : 600
        }, null, function() {
          $('#btnSubmit').trigger('click');
        });
      },
      editDict: function(id) {
        wt.showModal($ctx+"/sys/dict/form?id=" + id, null, {
          title : "修改字典",
          width : 600
        }, null, function() {
          $('#btnSubmit').trigger('click');
        });
      },
      addDictKV: function(id) {
        var row = $('#contentTable').bootstrapTable("getRowByUniqueId",id);
        wt.showModal($ctx+"/sys/dict/form?type=" + row.type + "&sort=" + row.sort + "&description=" + row.description, null, {
          title : "添加键值",
          width : 600
        }, null, function() {
          $('#btnSubmit').trigger('click');
        });
      },
      deleteDict: function(id) {
        wt.showConfirm("确认要删除该字典吗？", function() {
          udmpAjax.post($ctx+"/sys/dict/delete?id=" + id,null,function(data){
            wt.showInfo("success", data.message);
            wt.hideConfirm();
            $('#btnSubmit').trigger('click');
          })
        });
      }
    }
  });

  // 格式化操作按钮
  window.handleBtnFormatter = function(value, row, index) {
    var returnValue = "";
    var editBtn = "<button type='button' title='修改' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-edit table-btn-column'><i class='icon git'>&#xe600;</i></button>";
    var deleteBtn = "<button type='button' title='删除' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-delete table-btn-column'><i class='icon git'>&#xe601;</i></button>";
    var addKVBtn = "<button type='button' title='添加键值' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-child table-btn-column'><i class='icon git'>&#xe604;</i></button>";
    returnValue += editBtn;
    returnValue += deleteBtn;
    returnValue += addKVBtn;
    return returnValue;
  }

  //用户触发操作
  $("#contentTable").on("click", ".table-btn-edit",function(e, value, row, index) {
    vComponents.editDict($(this).data("value"));
  });

  $("#contentTable").on("click", ".table-btn-delete",function(e, value, row, index) {
    vComponents.deleteDict($(this).data("value"));
  });

  $("#contentTable").on("click", ".table-btn-child",function(e, value, row, index) {
    vComponents.addDictKV($(this).data("value"));
  });

  //按下关闭按钮，触发表格刷新
  $("#i-udmp-search").on("mousedown", "span.close",function(){
    setTimeout(function(){
      $('#btnSubmit').trigger('click');
    },300);
  })

  // 查询事件
  $('#btnSubmit').on('click', function(e) {
    e.preventDefault();
    data.methods.search();
  });
})
