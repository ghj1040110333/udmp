/**
 * 机构管理页面
 */

idefine(function(require,exports,module){
  //引入依赖文件
  require('bin/seajs-handler');
  require('udmp/widget/common/Ztree.min');
  require('udmp/widget/common/Layout.min');
  var ztree = UdmpZtree.ztree,
    treedata = UdmpLayout.treetable,
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
      udmpData: treedata
    },
    data: {
      table: [
        {name:'name',value:'机构名称'},
        {name:'area.name',value:'归属区域'},
        {name:'code',value:'机构编码'},
        {name:'type',value:'机构类型'},
        {name:'remarks',value:'备注'},
        {handle:'handleBtnFormatter',value:'操作',diy:true}
      ],
      options: {
        rowAttributes : function(row, index) {
          //如果id不为空,则作为根节点
          if($("#id").val() && row.id == $("#id").val()){
            return {
              id : row['id'],
              pId : "0"
            };
          }
          // 配置上下级关联字段
          return {
            id : row['id'],
            pId : row['parentId']
          };
        }
      },
      tableUrl: $ctx+"/sys/office/listData",
      treeUrl:  $ctx+"/sys/office/treeData"
    },
    methods: {
      addOffice: function(id){
        wt.showModal($ctx+"/sys/office/form?parent.id=" + id, null, {
          title : "添加机构",
          width : 800
        }, null, function() {
          vComponents.refreshTable();
        });
      },
      editOffice: function(id){
        wt.showModal($ctx+"/sys/office/form?id=" + id, null, {
          title : "修改机构",
          width : 800
        }, null, function() {
          vComponents.refreshTable();
        });
      },
      deleteOffice:function(id){
        wt.showConfirm("要删除该机构及所有子机构项吗？", function() {
          udmpAjax.post($ctx+"/sys/office/delete?id=" + id, function(data) {
            wt.showInfo("success", data.message);
            vComponents.refreshTable();
          });
        });
      },
      //更新树表表格
      refreshTable: function(){
        treedata.methods.search();
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
    var childBtn = "<button type='button' title='添加下级机构' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-child table-btn-column'><i class='icon git'>&#xe603;</i></button>";
    returnValue += editBtn;
    returnValue += deleteBtn;
    returnValue += childBtn;
    return returnValue;
  }

  //用户触发操作
  $("#treeTable").on("click", ".table-btn-edit", function (e, value, row, index) {
    vComponents.editOffice($(this).data("value"));
  });

  $("#treeTable").on("click", ".table-btn-delete", function (e, value, row, index) {
    vComponents.deleteOffice($(this).data("value"));
  });

  $("#treeTable").on("click", ".table-btn-child", function (e, value, row, index) {
    vComponents.addOffice($(this).data("value"));
  });

  // 刷新事件
  $('body').on('refresh', function(e) {
    treedata.methods.search();
  });
})
