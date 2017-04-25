/**
 * 角色管理页面
 */

idefine(function(require,exports,module){
  //引入依赖文件
  require('bin/seajs-handler');
  require('udmp/widget/common/Layout.min');
  var data = UdmpLayout.table,
    wt = window.top;

  //Vue调试开启
  Vue.config.devtools = true
  Vue.config.debug = true

  //初始化Vue组件
  var vComponents = new Vue({
    el: '#i-manage-panel',
    components: {
      udmpData: data
    },
    data: {
      table: [
        {name:'name',value:'角色名称'},
        {name:'enname',value:'英文名称'},
        {name:'name',value:'归属机构',handle:'officeFormatter'},
        {name:'dataScope',value:'数据范围'},
        {handle:'handleBtnFormatter',value:'操作'}
      ],
      tableUrl: $ctx+"/sys/role/listData",
    },
    methods: {
      assignRole: function(id){
        wt.showModal($ctx+"/sys/role/assign?id=" + id, null, {
          title : "分配角色",
          width : 1000
        }, null, function() {
        });
      },
      addRole: function() {
        wt.showModal($ctx+"/sys/role/form", null, {
          title : "添加角色",
          width : 1000
        }, null, function() {
        });
      },
      editRole: function(id) {
        wt.showModal($ctx+"/sys/role/form?id=" + id, null, {
          title : "修改角色",
          width : 1000
        }, null, function() {
        });
      },
      deleteRole: function(id) {
        wt.showConfirm("确认要删除该角色吗？", function() {
          $.post($ctx+"/sys/role/delete?id=" + id, function(data) {
            if(data) {
              if(data.success) {
                wt.showInfo("success", data.message);
              } else {
                wt.showInfo("error", data.message);
              }
            }
          });
        });
      }
    }
  });

  // 格式化机构名称
  window.officeFormatter = function(value, row, index) {
    if(row.hasOwnProperty("office")) {
      return row.office.name;
    } else {
      return "";
    }
  }

  // 格式化操作按钮
  window.handleBtnFormatter = function(value, row, index) {
    var returnValue = "";
    var assignBtn = "<button type='button' title='分配' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-assign table-btn-column'><i class='icon git'>&#xe602;</i></button>";
    var editBtn = "<button type='button' title='修改' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-edit table-btn-column'><i class='icon git'>&#xe600;</i></button>";
    var deleteBtn = "<button type='button' title='删除' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-delete table-btn-column'><i class='icon git'>&#xe601;</i></button>";
    returnValue += assignBtn;
    returnValue += editBtn;
    returnValue += deleteBtn;
    return returnValue;
  }
  //用户触发操作
  $("#contentTable").on("click", ".table-btn-assign", function (e, value, row, index) {
    vComponents.assignRole($(this).data("value"));
  });

  $("#contentTable").on("click", ".table-btn-edit", function (e, value, row, index) {
    vComponents.editRole($(this).data("value"));
  });

  $("#contentTable").on("click", ".table-btn-delete", function (e, value, row, index) {
    vComponents.deleteRole($(this).data("value"));
  });
})
