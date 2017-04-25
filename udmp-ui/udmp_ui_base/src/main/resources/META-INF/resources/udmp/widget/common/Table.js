(function webpackUniversalModuleDefinition(root, factory) {
	if(typeof exports === 'object' && typeof module === 'object')
		module.exports = factory();
	else if(typeof define === 'function' && define.amd)
		define([], factory);
	else if(typeof exports === 'object')
		exports["UdmpTable"] = factory();
	else
		root["UdmpTable"] = factory();
})(this, function() {
return /******/ (function(modules) { // webpackBootstrap
/******/ 	// The module cache
/******/ 	var installedModules = {};
/******/
/******/ 	// The require function
/******/ 	function __webpack_require__(moduleId) {
/******/
/******/ 		// Check if module is in cache
/******/ 		if(installedModules[moduleId])
/******/ 			return installedModules[moduleId].exports;
/******/
/******/ 		// Create a new module (and put it into the cache)
/******/ 		var module = installedModules[moduleId] = {
/******/ 			exports: {},
/******/ 			id: moduleId,
/******/ 			loaded: false
/******/ 		};
/******/
/******/ 		// Execute the module function
/******/ 		modules[moduleId].call(module.exports, module, module.exports, __webpack_require__);
/******/
/******/ 		// Flag the module as loaded
/******/ 		module.loaded = true;
/******/
/******/ 		// Return the exports of the module
/******/ 		return module.exports;
/******/ 	}
/******/
/******/
/******/ 	// expose the modules object (__webpack_modules__)
/******/ 	__webpack_require__.m = modules;
/******/
/******/ 	// expose the module cache
/******/ 	__webpack_require__.c = installedModules;
/******/
/******/ 	// __webpack_public_path__
/******/ 	__webpack_require__.p = "";
/******/
/******/ 	// Load entry module and return exports
/******/ 	return __webpack_require__(0);
/******/ })
/************************************************************************/
/******/ ({

/***/ 0:
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	var _UdmpTable = __webpack_require__(161);
	
	var _UdmpTable2 = _interopRequireDefault(_UdmpTable);
	
	var _UdmpTableTree = __webpack_require__(167);
	
	var _UdmpTableTree2 = _interopRequireDefault(_UdmpTableTree);
	
	var _UdmpTableSimple = __webpack_require__(173);
	
	var _UdmpTableSimple2 = _interopRequireDefault(_UdmpTableSimple);
	
	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }
	
	var UdmpCommon = {
	  basetable: _UdmpTable2.default,
	  simpletable: _UdmpTableSimple2.default,
	  treetable: _UdmpTableTree2.default
	};
	
	module.exports = UdmpCommon;

/***/ },

/***/ 161:
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__(162)
	
	if (module.exports.__esModule) module.exports = module.exports.default
	;(typeof module.exports === "function" ? module.exports.options : module.exports).template = __webpack_require__(163)
	if (false) {
	(function () {
	var hotAPI = require("vue-hot-reload-api")
	hotAPI.install(require("vue"))
	if (!hotAPI.compatible) return
	var id = "-!babel!./../node_modules/vue-loader/lib/selector.js?type=script&index=0!./UdmpTable.vue"
	hotAPI.createRecord(id, module.exports)
	module.hot.accept(["-!babel!./../node_modules/vue-loader/lib/selector.js?type=script&index=0!./UdmpTable.vue","-!vue-html-loader!./../node_modules/vue-loader/lib/selector.js?type=template&index=0!./UdmpTable.vue"], function () {
	var newOptions = require("-!babel!./../node_modules/vue-loader/lib/selector.js?type=script&index=0!./UdmpTable.vue")
	if (newOptions && newOptions.__esModule) newOptions = newOptions.default
	var newTemplate = require("-!vue-html-loader!./../node_modules/vue-loader/lib/selector.js?type=template&index=0!./UdmpTable.vue")
	hotAPI.update(id, newOptions, newTemplate)
	})
	})()
	}

/***/ },

/***/ 162:
/***/ function(module, exports) {

	'use strict';
	
	Object.defineProperty(exports, "__esModule", {
	  value: true
	});
	// <template>
	//   <table :id="id" class="contentTable" data-click-to-select="true">
	//     <thead>
	//       <tr>
	//         <template  v-for="item in items">
	//           <th v-if="item.diy" :data-formatter="item.handle">{{item.value}}</th>
	//           <th v-else :data-field="item.name"
	//               :data-type="item.type"
	//               :data-formatter="item.handle"
	//               :data-cell-style="item.style"
	//               :data-checkbox="item.check"
	//               :data-radio="item.radio"
	//               :data-visible="item.visible"
	//               :data-editable="item.editable"
	//           >{{item.value}}</th>
	//         </template>
	//       </tr>
	//     </thead>
	//   </table>
	// </template>
	
	// <script>
	//声明数据地址
	
	var url,
	    diyOption,
	    wt = window.top;
	
	//临时混用CMD模块
	idefine('iTable', function (require, exports, module) {
	  $('body').on('run.udmpTable', function () {
	    var bt = require('udmp/lib/bs_table'),
	        _show = 1;
	
	    var defaultOptions = {
	      url: url
	    };
	
	    //拷贝自定义表格属性
	    var options = $.extend(true, defaultOptions, diyOption);
	
	    //初始化表格
	    bt.init($(".contentTable"), options);
	
	    //表格加载事件
	    $('.contentTable').on('post-body.bs.table', function () {
	      if (_show % 2 == 1) {
	        setTimeout(wt.hideLoading, 500);
	        $(".contentTable tbody tr:first").is(".no-records-found") ? _show += 2 : _show++;
	      } else {
	        _show++;
	      }
	      //组件加载完成，进度条完成
	      if (window.top.i_web_pg.getProgress() !== 0) {
	        window.top.i_web_pg.finish();
	      }
	      console.info('table组件加载完成');
	      $('body').resize();
	    });
	  });
	});
	
	exports.default = {
	  data: function data() {
	    return {};
	  },
	
	  props: {
	    items: {
	      required: true,
	      default: null
	    },
	    url: {
	      required: true,
	      default: null
	    },
	    options: {
	      default: null
	    },
	    id: {
	      default: 'contentTable'
	    }
	  },
	  mounted: function mounted() {
	    url = this.url;
	    diyOption = this.options;
	    seajs.use('iTable', function () {
	      $('body').trigger("run.udmpTable");
	    });
	  },
	
	  methods: {
	    search: function search() {
	      wt.showLoading();
	      $(".contentTable").bootstrapTable('removeAll').bootstrapTable('refresh').hide();
	      $('.page-first').trigger('click');
	      $(".contentTable").show();
	    }
	  }
	};
	
	// </script>

/***/ },

/***/ 163:
/***/ function(module, exports) {

	module.exports = "<table :id=\"id\" class=\"contentTable\" data-click-to-select=\"true\">\n    <thead>\n      <tr>\n        <template  v-for=\"item in items\">\n          <th v-if=\"item.diy\" :data-formatter=\"item.handle\">{{item.value}}</th>\n          <th v-else :data-field=\"item.name\"\n              :data-type=\"item.type\"\n              :data-formatter=\"item.handle\"\n              :data-cell-style=\"item.style\"\n              :data-checkbox=\"item.check\"\n              :data-radio=\"item.radio\"\n              :data-visible=\"item.visible\"\n              :data-editable=\"item.editable\"\n          >{{item.value}}</th>\n        </template>\n      </tr>\n    </thead>\n  </table>";

/***/ },

/***/ 167:
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__(168)
	
	if (module.exports.__esModule) module.exports = module.exports.default
	;(typeof module.exports === "function" ? module.exports.options : module.exports).template = __webpack_require__(169)
	if (false) {
	(function () {
	var hotAPI = require("vue-hot-reload-api")
	hotAPI.install(require("vue"))
	if (!hotAPI.compatible) return
	var id = "-!babel!./../node_modules/vue-loader/lib/selector.js?type=script&index=0!./UdmpTableTree.vue"
	hotAPI.createRecord(id, module.exports)
	module.hot.accept(["-!babel!./../node_modules/vue-loader/lib/selector.js?type=script&index=0!./UdmpTableTree.vue","-!vue-html-loader!./../node_modules/vue-loader/lib/selector.js?type=template&index=0!./UdmpTableTree.vue"], function () {
	var newOptions = require("-!babel!./../node_modules/vue-loader/lib/selector.js?type=script&index=0!./UdmpTableTree.vue")
	if (newOptions && newOptions.__esModule) newOptions = newOptions.default
	var newTemplate = require("-!vue-html-loader!./../node_modules/vue-loader/lib/selector.js?type=template&index=0!./UdmpTableTree.vue")
	hotAPI.update(id, newOptions, newTemplate)
	})
	})()
	}

/***/ },

/***/ 168:
/***/ function(module, exports) {

	'use strict';
	
	Object.defineProperty(exports, "__esModule", {
	  value: true
	});
	// <template>
	//   <table :id="id" class="treeTable">
	//     <thead>
	//       <tr>
	//         <template  v-for="item in items">
	//           <th v-if="item.diy" :data-formatter="item.handle">{{item.value}}</th>
	//           <th v-else :data-field="item.name"
	//               :data-type="item.type"
	//               :data-formatter="item.handle"
	//               :data-cell-style="item.style"
	//           >{{item.value}}</th>
	//         </template>
	//       </tr>
	//     </thead>
	//   </table>
	// </template>
	
	// <script>
	//声明数据地址
	var url,
	    diyOption,
	    wt = window.top;
	
	//临时混用CMD模块
	idefine('iTableTree', function (require, exports, module) {
	  var bt = require('udmp/lib/bs_table'),
	      _show = 1;
	
	  var defaultOptions = {
	    url: url,
	    pagination: false
	  };
	
	  //拷贝自定义表格属性
	  var options = $.extend(true, defaultOptions, diyOption);
	
	  //初始化表格
	  bt.initTree($(".treeTable"), options, $("#parentId").val() ? $("#parentId").val() : 0);
	  //bt.initTree($("#treeTable"), options);
	
	  //表格加载事件
	  $('.treeTable').on('post-body.bs.table', function () {
	    if (_show % 2 == 1) {
	      setTimeout(wt.hideLoading, 500);
	      $(".treeTable tbody tr:first").is(".no-records-found") ? _show += 2 : _show++;
	    } else {
	      _show++;
	    }
	    //组件加载完成，进度条完成
	    if (window.top.i_web_pg.getProgress() !== 0) {
	      window.top.i_web_pg.finish();
	      //不推荐方式，特殊处理
	      setTimeout(function () {
	        return $('#i-user-org').height($('body').outerHeight(true) - 100);
	      }, 2000);
	    }
	    console.info('treetable组件加载完成');
	    $('body').resize();
	  });
	});
	
	exports.default = {
	  data: function data() {
	    return {};
	  },
	
	  props: {
	    items: {
	      required: true,
	      default: null
	    },
	    url: {
	      required: true,
	      default: null
	    },
	    options: {
	      default: null
	    },
	    id: {
	      default: 'treeTable'
	    }
	  },
	  mounted: function mounted() {
	    url = this.url;
	    diyOption = this.options;
	    seajs.use('iTableTree');
	  },
	
	  methods: {
	    search: function search() {
	      wt.showLoading();
	      $(".treeTable").bootstrapTable('removeAll').bootstrapTable('refresh').hide();
	      $('.page-first').trigger('click');
	      $(".treeTable").show();
	    }
	  }
	};
	
	// </script>

/***/ },

/***/ 169:
/***/ function(module, exports) {

	module.exports = "<table :id=\"id\" class=\"treeTable\">\n    <thead>\n      <tr>\n        <template  v-for=\"item in items\">\n          <th v-if=\"item.diy\" :data-formatter=\"item.handle\">{{item.value}}</th>\n          <th v-else :data-field=\"item.name\"\n              :data-type=\"item.type\"\n              :data-formatter=\"item.handle\"\n              :data-cell-style=\"item.style\"\n          >{{item.value}}</th>\n        </template>\n      </tr>\n    </thead>\n  </table>";

/***/ },

/***/ 173:
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__(174)
	
	if (module.exports.__esModule) module.exports = module.exports.default
	;(typeof module.exports === "function" ? module.exports.options : module.exports).template = __webpack_require__(175)
	if (false) {
	(function () {
	var hotAPI = require("vue-hot-reload-api")
	hotAPI.install(require("vue"))
	if (!hotAPI.compatible) return
	var id = "-!babel!./../node_modules/vue-loader/lib/selector.js?type=script&index=0!./UdmpTableSimple.vue"
	hotAPI.createRecord(id, module.exports)
	module.hot.accept(["-!babel!./../node_modules/vue-loader/lib/selector.js?type=script&index=0!./UdmpTableSimple.vue","-!vue-html-loader!./../node_modules/vue-loader/lib/selector.js?type=template&index=0!./UdmpTableSimple.vue"], function () {
	var newOptions = require("-!babel!./../node_modules/vue-loader/lib/selector.js?type=script&index=0!./UdmpTableSimple.vue")
	if (newOptions && newOptions.__esModule) newOptions = newOptions.default
	var newTemplate = require("-!vue-html-loader!./../node_modules/vue-loader/lib/selector.js?type=template&index=0!./UdmpTableSimple.vue")
	hotAPI.update(id, newOptions, newTemplate)
	})
	})()
	}

/***/ },

/***/ 174:
/***/ function(module, exports) {

	"use strict";
	
	Object.defineProperty(exports, "__esModule", {
	  value: true
	});
	// <template>
	//   <table :id="id" class="table table-condensed" :style="istyle">
	//     <thead>
	//     <tr>
	//       <template  v-for="title in titles">
	//         <th>{{ title }}</th>
	//       </template>
	//     </tr>
	//     </thead>
	//     <tbody>
	//       <template v-for="item in items">
	//         <tr>
	//           <td v-for="tdata in item">{{ tdata }}</td>
	//         </tr>
	//       </template>
	//   </table>
	// </template>
	
	// <script>
	
	exports.default = {
	  props: {
	    id: {
	      type: String,
	      default: null
	    },
	    titles: {
	      required: true,
	      default: null
	    },
	    items: {
	      required: true,
	      default: null
	    },
	    istyle: {
	      dufault: null
	    }
	  }
	};
	// </script>

/***/ },

/***/ 175:
/***/ function(module, exports) {

	module.exports = "<table :id=\"id\" class=\"table table-condensed\" :style=\"istyle\">\n    <thead>\n    <tr>\n      <template  v-for=\"title in titles\">\n        <th>{{ title }}</th>\n      </template>\n    </tr>\n    </thead>\n    <tbody>\n      <template v-for=\"item in items\">\n        <tr>\n          <td v-for=\"tdata in item\">{{ tdata }}</td>\n        </tr>\n      </template>\n  </table>";

/***/ }

/******/ })
});
;
//# sourceMappingURL=Table.js.map