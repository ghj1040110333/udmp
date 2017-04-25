/**
 * Description:table组件
 * @author Seadon
 * @date 2016年3月15日
 *
 */

idefine(function(require, exports, module) {
  //载入依赖项
  require('bootstrap');
  require('bs_table');
  require('bs_table_cn');
  //require('bs_editable');
  //require('bs_table_editable');
  require('tree_table');

  //表格方法
  var BsTable = {
    //初始化表格
    init: function($tb, options) {
      // 默认表格属性
      var defaultOptions = {
        classes: "table table-striped table-no-bordered table-condensed table-hover",
        striped: true,
        pagination: true,
        undefinedText: "",
        sidePagination: "server",
        queryParams: this._queryParams,
        responseHandler : this._responseHandler
      };
      // 拷贝自定义表格属性
      var options = jQuery.extend(true, defaultOptions, options);
      $tb.bootstrapTable(options).show();
    },
    //初始化树形表格
    initTree: function(tb,options,rootId){
      options.responseHandler = function(res) {
        // 将数据按父子关系排序
        if (res.hasOwnProperty("rows")) {
          res.rows = BsTable._sort2TreeData(res.rows, BsTable._isNullObj(rootId) ? "0" : rootId );
        } else {
          res.rows = [];
        }
        return res;
      };
      options.onLoadSuccess = function(data) {
        // 在数据加载结束后转换为树形结构
        $(tb).treeTable({expandLevel : 5});
      };
      BsTable.init(tb, options);
    },
    //是否为空对象
    _isNullObj: function(o) {
      return o === undefined || o === null;
    },
    //表格响应处理(如果后台没有数据,返回的json对象没有rows属性,bootstrap-table插件不会刷新表格,这里判断如果没有rows属性,添加一个空的rows)
    _responseHandler : function(res) {
      if (!res.hasOwnProperty("rows")) {
        res.rows = [];
      }
      return res;
    },
    //返回表单对象
    _serializeObject: function($form) {
      var o = {};
      jQuery.each($form.serializeArray(), function(index) {
        if (o[this['name']]) {
          o[this['name']] = o[this['name']] + "," + this['value'];
        } else {
          o[this['name']] = this['value'];
        }
      });
      return o;
    },
    //表格查询参数
    _queryParams: function(params) {
      var searchParams = BsTable._serializeObject(jQuery(".i-query-table"));
      searchParams.pageNo = (params.offset / params.limit) + 1;
      searchParams.pageSize = params.limit;
      return searchParams;
    },
    //将列表按树形结构(父子关系)排序
    _sort2TreeData: function(data, rootId) {
      var map = {};
      var tree = [];
      var i = 0;
      // 将列表转换为树形结构
      while (data.length != 0) {
        if (data[i].parentId == rootId) {
          tree.push({
            id : data[i].id,
            obj : data[i],
            children : []
          });
          map[data[i].id] = [ tree.length - 1 ];
          data.splice(i, 1);
          i--;
        } else {
          var mapArr = map[data[i].parentId];
          if (mapArr != undefined) {
            var obj = tree[mapArr[0]];
            for (var j = 1; j < mapArr.length; j++) {
              obj = obj.children[mapArr[j]];
            }

            obj.children.push({
              id : data[i].id,
              obj : data[i],
              children : []
            });
            map[data[i].id] = mapArr.concat([ obj.children.length - 1 ]);
            data.splice(i, 1);
            i--;
          }
        }
        i++;
        if (i > data.length - 1) {
          i = 0;
        }
      }
      var rows = [];
      // 将树转换为列表
      BsTable._tree2Array(tree, rows);
      return rows;
    },
    //将树形结构转换为列表
    _tree2Array: function(tree, rows) {
      $.each(tree, function(index) {
        rows.push(this.obj);
        if (this.children.length != 0) {
        	BsTable._tree2Array(this.children, rows);
        }
      });
    }
  };
  module.exports = BsTable;
});
