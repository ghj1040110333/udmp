/**
 * 区域管理页面
 */

idefine(function(require,exports,module){
  //引入依赖文件
  require('bin/seajs-handler');
  require('udmp/widget/common/Layout.min');
  var treedata = UdmpLayout.treetable,
    wt = window.top;

  //Vue调试开启
  Vue.config.devtools = true
  Vue.config.debug = true

  //初始化Vue组件
  var vComponents = new Vue({
    el: '#i-manage-panel',
    components: {
      udmpData: treedata
    },
    data: {
      table: [
        {name:'name',value:'区域名称'},
        {name:'code',value:'区域编码'},
        {name:'type',value:'区域类型'},
        {name:'remarks',value:'备注'},
        {handle:'handleBtnFormatter',value:'操作',diy:true}
      ],
      tableUrl: $ctx+"/sys/area/listData",
    },
    methods: {
      addArea: function(id) {
        wt.showModal($ctx+"/sys/area/form?parent.id=" + id, null, {
          title : "添加区域",
          width : 800
        }, null, function() {

        });
      },
      editArea: function(id) {
        wt.showModal($ctx+"/sys/area/form?id=" + id, null, {
          title : "修改区域",
          width : 800
        }, null, function() {

        });
      },
      deleteArea: function(id) {
        wt.showConfirm("要删除该区域及所有子区域项吗？", function() {
          $.post($ctx+"/sys/area/delete?id=" + id, function(data) {
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
  function nameFormatter(value, row, index) {
    return "<a href='javascript:void(0);' onclick='editArea(\"" + row.id + "\")'>" + row.name + "</a>";
  }

  // 格式化机构类型代码
  function typeFormatter(value, row, index) {
    // return getDictLabel(${fns:toJson(fns:getDictList('sys_area_type'))}, row.type);
  }

  // 格式化操作按钮
  window.handleBtnFormatter = function(value, row, index)  {
    var returnValue = "";
    var editBtn = "<button type='button' title='修改' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-edit table-btn-column'><i class='icon git'>&#xe600;</i></button>";
    var deleteBtn = "<button type='button' title='删除' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-delete table-btn-column'><i class='icon git'>&#xe601;</i></button>";
    var childBtn = "<button type='button' title='添加下级区域' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-child table-btn-column'><i class='icon git'>&#xe603;</i></button>";
    returnValue += editBtn;
    returnValue += deleteBtn;
    returnValue += childBtn;
    return returnValue;
  }

  //用户触发操作
  $("#treeTable").on("click", ".table-btn-edit", function (e, value, row, index) {
    vComponents.editArea($(this).data("value"));
  });

  $("#treeTable").on("click", ".table-btn-delete", function (e, value, row, index) {
    vComponents.deleteArea($(this).data("value"));
  });

  $("#treeTable").on("click", ".table-btn-child", function (e, value, row, index) {
    vComponents.addArea($(this).data("value"));
  });
})
