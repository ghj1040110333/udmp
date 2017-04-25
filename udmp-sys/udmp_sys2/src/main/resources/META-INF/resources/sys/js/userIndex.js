/**
 * 用户管理页面
 */

idefine(function(require,exports,module){
  //引入依赖文件
  require('bin/seajs-handler');
  require('udmp/widget/common/Ztree.min');
  require('udmp/widget/common/Layout.min');
  require('udmp/widget/common/Form.min');
  var ztree = UdmpZtree.ztree,
    data = UdmpLayout.table,
    wt = window.top;

  //Vue调试开启
  Vue.config.devtools = true
  Vue.config.debug = true

  //初始化Vue组件
  var vComponents = new Vue({
    el: '#i-manage-panel',
    components: {
      udmpZtree: ztree,
      udmpSearch: UdmpLayout.search,
      udmpData: data,
      udmpInput: UdmpForm.input
    },
    data: {
      table: [
        {name:'company.name',value:'归属公司',style:'cellStyle'},
        {name:'office.name',value:'归属部门'},
        {name:'loginName',value:'登录名'},
        {name:'name',value:'姓名'},
        {name:'phone',value:'电话'},
        {name:'mobile',value:'手机'},
        {handle:'handleBtnFormatter',value:'操作'}
      ],
      tableUrl: $ctx+"/sys/user/listData",
      treeUrl:  $ctx+"/sys/office/treeData"
    },
    methods: {
      addUser:function(){
        wt.showModal($ctx+"/sys/user/form", null, {
          title : "添加用户",
          width : 800
        }, null, function() {
          $("#btnSubmit").click();
        });
      },
      importUser:function(){
        wt.toastr.error('导入功能暂未实现！');
      },
      exportUser:function(){
        wt.toastr.error('导出功能暂未实现！');
      },
      editUser:function(uid){
        wt.showModal($ctx+"/sys/user/form?id=" + uid, null, {
          title : "修改用户",
          width : 800
        }, null, function() {
          $("#btnSubmit").click();
        });
      },
      deleteUser:function(id){
        wt.showConfirm("确定要删除这个用户吗？", function() {
          $.post($ctx+"/sys/user/delete?id=" + id, function(data) {
            if(data) {
              if(data.success) {
                top.window.showInfo("success", data.message);
                $("#btnSubmit").click();
              } else {
                top.window.showInfo("error", data.message);
              }
            }
          });
        });
      }
    }
  });

  //刷新事件
  $('#i-group-refresh').on('click',function(){
    ztree.methods.fresh();
    wt.toastr.success('组织机构刷新完成！');
  })

  // 格式化操作按钮
  window.handleBtnFormatter = function(value, row, index) {
    var returnValue = "";
    var editBtn = "<button type='button' title='修改' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-edit table-btn-column'><i class='icon git'>&#xe600;</i></button>";
    var deleteBtn = "<button type='button' title='删除' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-delete table-btn-column'><i class='icon git'>&#xe601;</i></button>";
    returnValue += editBtn;
    returnValue += deleteBtn;
    return returnValue;
  }

  //用户触发操作
  $("#contentTable").on("click", ".table-btn-edit", function (e, value, row, index) {
    vComponents.editUser($(this).data("value"));
  });

  $("#contentTable").on("click", ".table-btn-delete", function (e, value, row, index) {
    vComponents.deleteUser($(this).data("value"));
  });

  // 查询事件
  $('#btnSubmit').on('click', function(e) {
    e.preventDefault();
    data.methods.search();
  });
})
