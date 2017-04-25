/**
 * 菜单管理页面
 */

idefine(function(require,exports,module){
  //引入依赖文件
  require('bin/seajs-handler');
  require('udmp/widget/common/Layout.min');
  var treedata = UdmpLayout.treetable,
    _tool = require('udmp/lib/util_tool'),
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
        {name:'name',value:'名称',handle:'nameFormatter'},
        {name:'href',value:'链接',handle:'abbrFormatter'},
        {name:'sort',value:'排序',handle:'sortFormatter'},
        {name:'isShow',value:'可见',handle:'isShowFormatter'},
        {name:'permission',value:'权限标识',handle:'abbrFormatter'},
        {handle:'handleBtnFormatter',value:'操作'}
      ],
      options: {
        rowAttributes : function(row, index) {
          // 隐藏根节点
          if(row.parentId == 0){
            return {
              id : row['id'],
              pId : row['parentId'],
              class : 'hide'
            };
          }
          // 配置上下级关联字段
          return {
            id : row['id'],
            pId : row['parentId']
          };
        }
      },
      tableUrl: $ctx+"/sys/menu/listData",
    },
    methods: {
      addMenu: function(id) {
        wt.showModal($ctx+"/sys/menu/form?parent.id=" + id, null, {
          title : "添加菜单",
          width : 1000
        }, null, function() {
          vComponents.refreshTable();
        });
      },
      editMenu: function(id) {
        wt.showModal($ctx+"/sys/menu/form?id=" + id, null, {
          title : "修改菜单",
          width : 1000
        }, null, function() {
          vComponents.refreshTable();
        });
      },
      deleteMenu: function(id) {
        wt.showConfirm("要删除该菜单及所有子菜单项吗？", function() {
          udmpAjax.post($ctx+"/sys/menu/delete?id=" + id,null,function(data) {
            wt.showInfo("success", data.message);
            wt.hideConfirm();
            vComponents.refreshTable();
          });
        });
      },
      //保存排序
      updateSort: function() {
        var params = git.serializeObject($("#listForm"));
        $.post("${ctx}/sys/menu/updateSort", params, function(data) {
          if (data) {
            if (data.success) {
              top.window.showInfo("success", data.message);
              git.table.query($("#contentTable"));
            } else {
              top.window.showInfo("error", data.message);
            }
          }
        });
      },
      //更新树表表格
      refreshTable: function(){
        treedata.methods.search();
      }
    }
  });

  // 格式化名称
  window.nameFormatter = function(value, row, index) {
    var returnValue =  "";
    if(row.icon) {
      returnValue += "<i class='glyphicon glyphicon-" + row.icon + "'></i>";
    } else {
      returnValue += "<i class='hide'></i>";
    }
    returnValue +=  "&nbsp;<a href='javascript:void(0);' onclick='editMenu(\"" + row.id + "\")'>";
    returnValue += row.name;
    returnValue += "</a>";
    return returnValue;
  }

  // 格式化排序
  window.sortFormatter = function(value, row, index) {
    var returnValue =  "<shiro:hasPermission name='sys:menu:edit'>";
    returnValue += "<input type='hidden' name='ids' value='" + row.id + "'/>";
    returnValue += "<input name='sorts' class='form-control' type='text' value='" + row.sort + "' style='width: 50px; margin: 0; padding: 0; text-align: center;'>";
    returnValue += "</shiro:hasPermission>";
    return returnValue;
  }

  // 格式化可见
  window.isShowFormatter = function(value, row, index) {
    if(value == 1) {
      return "显示"
    } else {
      return "隐藏";
    }
  }

  // 缩写
  window.abbrFormatter = function(value, row, index) {
    return _tool.abbr(value, 30);
  }

  // 格式化操作按钮
  window.handleBtnFormatter = function(value, row, index) {
    var returnValue = "";
    var editBtn = "<button type='button' title='修改' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-edit table-btn-column'><i class='icon git'>&#xe600;</i></button>";
    var deleteBtn = "<button type='button' title='删除' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-delete table-btn-column'><i class='icon git'>&#xe601;</i></button>";
    var childBtn = "<button type='button' title='添加下级菜单' data-value='" + row.id + "' class='btn btn-default btn-md table-btn-child table-btn-column'><i class='icon git'>&#xe603;</i></button>";
    returnValue += editBtn;
    returnValue += deleteBtn;
    returnValue += childBtn;
    return returnValue;
  }

  //用户触发操作
  $("#treeTable").on("click", ".table-btn-edit", function (e, value, row, index) {
    vComponents.editMenu($(this).data("value"));
  });

  $("#treeTable").on("click", ".table-btn-delete", function (e, value, row, index) {
    vComponents.deleteMenu($(this).data("value"));
  });

  $("#treeTable").on("click", ".table-btn-child", function (e, value, row, index) {
    vComponents.addMenu($(this).data("value"));
  });
})
