(function webpackUniversalModuleDefinition(root, factory) {
	if(typeof exports === 'object' && typeof module === 'object')
		module.exports = factory();
	else if(typeof define === 'function' && define.amd)
		define([], factory);
	else if(typeof exports === 'object')
		exports["UdmpZtree"] = factory();
	else
		root["UdmpZtree"] = factory();
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
/******/ ([
/* 0 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	
	var _UdmpZtree = __webpack_require__(177);
	
	var _UdmpZtree2 = _interopRequireDefault(_UdmpZtree);
	
	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }
	
	var UdmpCommon = {
	  ztree: _UdmpZtree2.default
	};
	
	module.exports = UdmpCommon;

/***/ },
/* 1 */,
/* 2 */,
/* 3 */,
/* 4 */,
/* 5 */,
/* 6 */,
/* 7 */,
/* 8 */,
/* 9 */,
/* 10 */,
/* 11 */,
/* 12 */,
/* 13 */,
/* 14 */,
/* 15 */,
/* 16 */,
/* 17 */,
/* 18 */,
/* 19 */,
/* 20 */,
/* 21 */,
/* 22 */,
/* 23 */
/***/ function(module, exports) {

	/*
		MIT License http://www.opensource.org/licenses/mit-license.php
		Author Tobias Koppers @sokra
	*/
	// css base code, injected by the css-loader
	module.exports = function() {
		var list = [];
	
		// return the list of modules as css string
		list.toString = function toString() {
			var result = [];
			for(var i = 0; i < this.length; i++) {
				var item = this[i];
				if(item[2]) {
					result.push("@media " + item[2] + "{" + item[1] + "}");
				} else {
					result.push(item[1]);
				}
			}
			return result.join("");
		};
	
		// import a list of modules into the list
		list.i = function(modules, mediaQuery) {
			if(typeof modules === "string")
				modules = [[null, modules, ""]];
			var alreadyImportedModules = {};
			for(var i = 0; i < this.length; i++) {
				var id = this[i][0];
				if(typeof id === "number")
					alreadyImportedModules[id] = true;
			}
			for(i = 0; i < modules.length; i++) {
				var item = modules[i];
				// skip already imported module
				// this implementation is not 100% perfect for weird media query combinations
				//  when a module is imported multiple times with different media queries.
				//  I hope this will never occur (Hey this way we have smaller bundles)
				if(typeof item[0] !== "number" || !alreadyImportedModules[item[0]]) {
					if(mediaQuery && !item[2]) {
						item[2] = mediaQuery;
					} else if(mediaQuery) {
						item[2] = "(" + item[2] + ") and (" + mediaQuery + ")";
					}
					list.push(item);
				}
			}
		};
		return list;
	};


/***/ },
/* 24 */
/***/ function(module, exports, __webpack_require__) {

	/*
		MIT License http://www.opensource.org/licenses/mit-license.php
		Author Tobias Koppers @sokra
	*/
	var stylesInDom = {},
		memoize = function(fn) {
			var memo;
			return function () {
				if (typeof memo === "undefined") memo = fn.apply(this, arguments);
				return memo;
			};
		},
		isOldIE = memoize(function() {
			return /msie [6-9]\b/.test(window.navigator.userAgent.toLowerCase());
		}),
		getHeadElement = memoize(function () {
			return document.head || document.getElementsByTagName("head")[0];
		}),
		singletonElement = null,
		singletonCounter = 0,
		styleElementsInsertedAtTop = [];
	
	module.exports = function(list, options) {
		if(false) {
			if(typeof document !== "object") throw new Error("The style-loader cannot be used in a non-browser environment");
		}
	
		options = options || {};
		// Force single-tag solution on IE6-9, which has a hard limit on the # of <style>
		// tags it will allow on a page
		if (typeof options.singleton === "undefined") options.singleton = isOldIE();
	
		// By default, add <style> tags to the bottom of <head>.
		if (typeof options.insertAt === "undefined") options.insertAt = "bottom";
	
		var styles = listToStyles(list);
		addStylesToDom(styles, options);
	
		return function update(newList) {
			var mayRemove = [];
			for(var i = 0; i < styles.length; i++) {
				var item = styles[i];
				var domStyle = stylesInDom[item.id];
				domStyle.refs--;
				mayRemove.push(domStyle);
			}
			if(newList) {
				var newStyles = listToStyles(newList);
				addStylesToDom(newStyles, options);
			}
			for(var i = 0; i < mayRemove.length; i++) {
				var domStyle = mayRemove[i];
				if(domStyle.refs === 0) {
					for(var j = 0; j < domStyle.parts.length; j++)
						domStyle.parts[j]();
					delete stylesInDom[domStyle.id];
				}
			}
		};
	}
	
	function addStylesToDom(styles, options) {
		for(var i = 0; i < styles.length; i++) {
			var item = styles[i];
			var domStyle = stylesInDom[item.id];
			if(domStyle) {
				domStyle.refs++;
				for(var j = 0; j < domStyle.parts.length; j++) {
					domStyle.parts[j](item.parts[j]);
				}
				for(; j < item.parts.length; j++) {
					domStyle.parts.push(addStyle(item.parts[j], options));
				}
			} else {
				var parts = [];
				for(var j = 0; j < item.parts.length; j++) {
					parts.push(addStyle(item.parts[j], options));
				}
				stylesInDom[item.id] = {id: item.id, refs: 1, parts: parts};
			}
		}
	}
	
	function listToStyles(list) {
		var styles = [];
		var newStyles = {};
		for(var i = 0; i < list.length; i++) {
			var item = list[i];
			var id = item[0];
			var css = item[1];
			var media = item[2];
			var sourceMap = item[3];
			var part = {css: css, media: media, sourceMap: sourceMap};
			if(!newStyles[id])
				styles.push(newStyles[id] = {id: id, parts: [part]});
			else
				newStyles[id].parts.push(part);
		}
		return styles;
	}
	
	function insertStyleElement(options, styleElement) {
		var head = getHeadElement();
		var lastStyleElementInsertedAtTop = styleElementsInsertedAtTop[styleElementsInsertedAtTop.length - 1];
		if (options.insertAt === "top") {
			if(!lastStyleElementInsertedAtTop) {
				head.insertBefore(styleElement, head.firstChild);
			} else if(lastStyleElementInsertedAtTop.nextSibling) {
				head.insertBefore(styleElement, lastStyleElementInsertedAtTop.nextSibling);
			} else {
				head.appendChild(styleElement);
			}
			styleElementsInsertedAtTop.push(styleElement);
		} else if (options.insertAt === "bottom") {
			head.appendChild(styleElement);
		} else {
			throw new Error("Invalid value for parameter 'insertAt'. Must be 'top' or 'bottom'.");
		}
	}
	
	function removeStyleElement(styleElement) {
		styleElement.parentNode.removeChild(styleElement);
		var idx = styleElementsInsertedAtTop.indexOf(styleElement);
		if(idx >= 0) {
			styleElementsInsertedAtTop.splice(idx, 1);
		}
	}
	
	function createStyleElement(options) {
		var styleElement = document.createElement("style");
		styleElement.type = "text/css";
		insertStyleElement(options, styleElement);
		return styleElement;
	}
	
	function createLinkElement(options) {
		var linkElement = document.createElement("link");
		linkElement.rel = "stylesheet";
		insertStyleElement(options, linkElement);
		return linkElement;
	}
	
	function addStyle(obj, options) {
		var styleElement, update, remove;
	
		if (options.singleton) {
			var styleIndex = singletonCounter++;
			styleElement = singletonElement || (singletonElement = createStyleElement(options));
			update = applyToSingletonTag.bind(null, styleElement, styleIndex, false);
			remove = applyToSingletonTag.bind(null, styleElement, styleIndex, true);
		} else if(obj.sourceMap &&
			typeof URL === "function" &&
			typeof URL.createObjectURL === "function" &&
			typeof URL.revokeObjectURL === "function" &&
			typeof Blob === "function" &&
			typeof btoa === "function") {
			styleElement = createLinkElement(options);
			update = updateLink.bind(null, styleElement);
			remove = function() {
				removeStyleElement(styleElement);
				if(styleElement.href)
					URL.revokeObjectURL(styleElement.href);
			};
		} else {
			styleElement = createStyleElement(options);
			update = applyToTag.bind(null, styleElement);
			remove = function() {
				removeStyleElement(styleElement);
			};
		}
	
		update(obj);
	
		return function updateStyle(newObj) {
			if(newObj) {
				if(newObj.css === obj.css && newObj.media === obj.media && newObj.sourceMap === obj.sourceMap)
					return;
				update(obj = newObj);
			} else {
				remove();
			}
		};
	}
	
	var replaceText = (function () {
		var textStore = [];
	
		return function (index, replacement) {
			textStore[index] = replacement;
			return textStore.filter(Boolean).join('\n');
		};
	})();
	
	function applyToSingletonTag(styleElement, index, remove, obj) {
		var css = remove ? "" : obj.css;
	
		if (styleElement.styleSheet) {
			styleElement.styleSheet.cssText = replaceText(index, css);
		} else {
			var cssNode = document.createTextNode(css);
			var childNodes = styleElement.childNodes;
			if (childNodes[index]) styleElement.removeChild(childNodes[index]);
			if (childNodes.length) {
				styleElement.insertBefore(cssNode, childNodes[index]);
			} else {
				styleElement.appendChild(cssNode);
			}
		}
	}
	
	function applyToTag(styleElement, obj) {
		var css = obj.css;
		var media = obj.media;
	
		if(media) {
			styleElement.setAttribute("media", media)
		}
	
		if(styleElement.styleSheet) {
			styleElement.styleSheet.cssText = css;
		} else {
			while(styleElement.firstChild) {
				styleElement.removeChild(styleElement.firstChild);
			}
			styleElement.appendChild(document.createTextNode(css));
		}
	}
	
	function updateLink(linkElement, obj) {
		var css = obj.css;
		var sourceMap = obj.sourceMap;
	
		if(sourceMap) {
			// http://stackoverflow.com/a/26603875
			css += "\n/*# sourceMappingURL=data:application/json;base64," + btoa(unescape(encodeURIComponent(JSON.stringify(sourceMap)))) + " */";
		}
	
		var blob = new Blob([css], { type: "text/css" });
	
		var oldSrc = linkElement.href;
	
		linkElement.href = URL.createObjectURL(blob);
	
		if(oldSrc)
			URL.revokeObjectURL(oldSrc);
	}


/***/ },
/* 25 */,
/* 26 */,
/* 27 */,
/* 28 */
/***/ function(module, exports, __webpack_require__) {

	__webpack_require__(29);
	var global        = __webpack_require__(40)
	  , hide          = __webpack_require__(44)
	  , Iterators     = __webpack_require__(32)
	  , TO_STRING_TAG = __webpack_require__(71)('toStringTag');
	
	for(var collections = ['NodeList', 'DOMTokenList', 'MediaList', 'StyleSheetList', 'CSSRuleList'], i = 0; i < 5; i++){
	  var NAME       = collections[i]
	    , Collection = global[NAME]
	    , proto      = Collection && Collection.prototype;
	  if(proto && !proto[TO_STRING_TAG])hide(proto, TO_STRING_TAG, NAME);
	  Iterators[NAME] = Iterators.Array;
	}

/***/ },
/* 29 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	var addToUnscopables = __webpack_require__(30)
	  , step             = __webpack_require__(31)
	  , Iterators        = __webpack_require__(32)
	  , toIObject        = __webpack_require__(33);
	
	// 22.1.3.4 Array.prototype.entries()
	// 22.1.3.13 Array.prototype.keys()
	// 22.1.3.29 Array.prototype.values()
	// 22.1.3.30 Array.prototype[@@iterator]()
	module.exports = __webpack_require__(37)(Array, 'Array', function(iterated, kind){
	  this._t = toIObject(iterated); // target
	  this._i = 0;                   // next index
	  this._k = kind;                // kind
	// 22.1.5.2.1 %ArrayIteratorPrototype%.next()
	}, function(){
	  var O     = this._t
	    , kind  = this._k
	    , index = this._i++;
	  if(!O || index >= O.length){
	    this._t = undefined;
	    return step(1);
	  }
	  if(kind == 'keys'  )return step(0, index);
	  if(kind == 'values')return step(0, O[index]);
	  return step(0, [index, O[index]]);
	}, 'values');
	
	// argumentsList[@@iterator] is %ArrayProto_values% (9.4.4.6, 9.4.4.7)
	Iterators.Arguments = Iterators.Array;
	
	addToUnscopables('keys');
	addToUnscopables('values');
	addToUnscopables('entries');

/***/ },
/* 30 */
/***/ function(module, exports) {

	module.exports = function(){ /* empty */ };

/***/ },
/* 31 */
/***/ function(module, exports) {

	module.exports = function(done, value){
	  return {value: value, done: !!done};
	};

/***/ },
/* 32 */
/***/ function(module, exports) {

	module.exports = {};

/***/ },
/* 33 */
/***/ function(module, exports, __webpack_require__) {

	// to indexed object, toObject with fallback for non-array-like ES3 strings
	var IObject = __webpack_require__(34)
	  , defined = __webpack_require__(36);
	module.exports = function(it){
	  return IObject(defined(it));
	};

/***/ },
/* 34 */
/***/ function(module, exports, __webpack_require__) {

	// fallback for non-array-like ES3 and non-enumerable old V8 strings
	var cof = __webpack_require__(35);
	module.exports = Object('z').propertyIsEnumerable(0) ? Object : function(it){
	  return cof(it) == 'String' ? it.split('') : Object(it);
	};

/***/ },
/* 35 */
/***/ function(module, exports) {

	var toString = {}.toString;
	
	module.exports = function(it){
	  return toString.call(it).slice(8, -1);
	};

/***/ },
/* 36 */
/***/ function(module, exports) {

	// 7.2.1 RequireObjectCoercible(argument)
	module.exports = function(it){
	  if(it == undefined)throw TypeError("Can't call method on  " + it);
	  return it;
	};

/***/ },
/* 37 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	var LIBRARY        = __webpack_require__(38)
	  , $export        = __webpack_require__(39)
	  , redefine       = __webpack_require__(54)
	  , hide           = __webpack_require__(44)
	  , has            = __webpack_require__(55)
	  , Iterators      = __webpack_require__(32)
	  , $iterCreate    = __webpack_require__(56)
	  , setToStringTag = __webpack_require__(70)
	  , getPrototypeOf = __webpack_require__(72)
	  , ITERATOR       = __webpack_require__(71)('iterator')
	  , BUGGY          = !([].keys && 'next' in [].keys()) // Safari has buggy iterators w/o `next`
	  , FF_ITERATOR    = '@@iterator'
	  , KEYS           = 'keys'
	  , VALUES         = 'values';
	
	var returnThis = function(){ return this; };
	
	module.exports = function(Base, NAME, Constructor, next, DEFAULT, IS_SET, FORCED){
	  $iterCreate(Constructor, NAME, next);
	  var getMethod = function(kind){
	    if(!BUGGY && kind in proto)return proto[kind];
	    switch(kind){
	      case KEYS: return function keys(){ return new Constructor(this, kind); };
	      case VALUES: return function values(){ return new Constructor(this, kind); };
	    } return function entries(){ return new Constructor(this, kind); };
	  };
	  var TAG        = NAME + ' Iterator'
	    , DEF_VALUES = DEFAULT == VALUES
	    , VALUES_BUG = false
	    , proto      = Base.prototype
	    , $native    = proto[ITERATOR] || proto[FF_ITERATOR] || DEFAULT && proto[DEFAULT]
	    , $default   = $native || getMethod(DEFAULT)
	    , $entries   = DEFAULT ? !DEF_VALUES ? $default : getMethod('entries') : undefined
	    , $anyNative = NAME == 'Array' ? proto.entries || $native : $native
	    , methods, key, IteratorPrototype;
	  // Fix native
	  if($anyNative){
	    IteratorPrototype = getPrototypeOf($anyNative.call(new Base));
	    if(IteratorPrototype !== Object.prototype){
	      // Set @@toStringTag to native iterators
	      setToStringTag(IteratorPrototype, TAG, true);
	      // fix for some old engines
	      if(!LIBRARY && !has(IteratorPrototype, ITERATOR))hide(IteratorPrototype, ITERATOR, returnThis);
	    }
	  }
	  // fix Array#{values, @@iterator}.name in V8 / FF
	  if(DEF_VALUES && $native && $native.name !== VALUES){
	    VALUES_BUG = true;
	    $default = function values(){ return $native.call(this); };
	  }
	  // Define iterator
	  if((!LIBRARY || FORCED) && (BUGGY || VALUES_BUG || !proto[ITERATOR])){
	    hide(proto, ITERATOR, $default);
	  }
	  // Plug for library
	  Iterators[NAME] = $default;
	  Iterators[TAG]  = returnThis;
	  if(DEFAULT){
	    methods = {
	      values:  DEF_VALUES ? $default : getMethod(VALUES),
	      keys:    IS_SET     ? $default : getMethod(KEYS),
	      entries: $entries
	    };
	    if(FORCED)for(key in methods){
	      if(!(key in proto))redefine(proto, key, methods[key]);
	    } else $export($export.P + $export.F * (BUGGY || VALUES_BUG), NAME, methods);
	  }
	  return methods;
	};

/***/ },
/* 38 */
/***/ function(module, exports) {

	module.exports = true;

/***/ },
/* 39 */
/***/ function(module, exports, __webpack_require__) {

	var global    = __webpack_require__(40)
	  , core      = __webpack_require__(41)
	  , ctx       = __webpack_require__(42)
	  , hide      = __webpack_require__(44)
	  , PROTOTYPE = 'prototype';
	
	var $export = function(type, name, source){
	  var IS_FORCED = type & $export.F
	    , IS_GLOBAL = type & $export.G
	    , IS_STATIC = type & $export.S
	    , IS_PROTO  = type & $export.P
	    , IS_BIND   = type & $export.B
	    , IS_WRAP   = type & $export.W
	    , exports   = IS_GLOBAL ? core : core[name] || (core[name] = {})
	    , expProto  = exports[PROTOTYPE]
	    , target    = IS_GLOBAL ? global : IS_STATIC ? global[name] : (global[name] || {})[PROTOTYPE]
	    , key, own, out;
	  if(IS_GLOBAL)source = name;
	  for(key in source){
	    // contains in native
	    own = !IS_FORCED && target && target[key] !== undefined;
	    if(own && key in exports)continue;
	    // export native or passed
	    out = own ? target[key] : source[key];
	    // prevent global pollution for namespaces
	    exports[key] = IS_GLOBAL && typeof target[key] != 'function' ? source[key]
	    // bind timers to global for call from export context
	    : IS_BIND && own ? ctx(out, global)
	    // wrap global constructors for prevent change them in library
	    : IS_WRAP && target[key] == out ? (function(C){
	      var F = function(a, b, c){
	        if(this instanceof C){
	          switch(arguments.length){
	            case 0: return new C;
	            case 1: return new C(a);
	            case 2: return new C(a, b);
	          } return new C(a, b, c);
	        } return C.apply(this, arguments);
	      };
	      F[PROTOTYPE] = C[PROTOTYPE];
	      return F;
	    // make static versions for prototype methods
	    })(out) : IS_PROTO && typeof out == 'function' ? ctx(Function.call, out) : out;
	    // export proto methods to core.%CONSTRUCTOR%.methods.%NAME%
	    if(IS_PROTO){
	      (exports.virtual || (exports.virtual = {}))[key] = out;
	      // export proto methods to core.%CONSTRUCTOR%.prototype.%NAME%
	      if(type & $export.R && expProto && !expProto[key])hide(expProto, key, out);
	    }
	  }
	};
	// type bitmap
	$export.F = 1;   // forced
	$export.G = 2;   // global
	$export.S = 4;   // static
	$export.P = 8;   // proto
	$export.B = 16;  // bind
	$export.W = 32;  // wrap
	$export.U = 64;  // safe
	$export.R = 128; // real proto method for `library` 
	module.exports = $export;

/***/ },
/* 40 */
/***/ function(module, exports) {

	// https://github.com/zloirock/core-js/issues/86#issuecomment-115759028
	var global = module.exports = typeof window != 'undefined' && window.Math == Math
	  ? window : typeof self != 'undefined' && self.Math == Math ? self : Function('return this')();
	if(typeof __g == 'number')__g = global; // eslint-disable-line no-undef

/***/ },
/* 41 */
/***/ function(module, exports) {

	var core = module.exports = {version: '2.4.0'};
	if(typeof __e == 'number')__e = core; // eslint-disable-line no-undef

/***/ },
/* 42 */
/***/ function(module, exports, __webpack_require__) {

	// optional / simple context binding
	var aFunction = __webpack_require__(43);
	module.exports = function(fn, that, length){
	  aFunction(fn);
	  if(that === undefined)return fn;
	  switch(length){
	    case 1: return function(a){
	      return fn.call(that, a);
	    };
	    case 2: return function(a, b){
	      return fn.call(that, a, b);
	    };
	    case 3: return function(a, b, c){
	      return fn.call(that, a, b, c);
	    };
	  }
	  return function(/* ...args */){
	    return fn.apply(that, arguments);
	  };
	};

/***/ },
/* 43 */
/***/ function(module, exports) {

	module.exports = function(it){
	  if(typeof it != 'function')throw TypeError(it + ' is not a function!');
	  return it;
	};

/***/ },
/* 44 */
/***/ function(module, exports, __webpack_require__) {

	var dP         = __webpack_require__(45)
	  , createDesc = __webpack_require__(53);
	module.exports = __webpack_require__(49) ? function(object, key, value){
	  return dP.f(object, key, createDesc(1, value));
	} : function(object, key, value){
	  object[key] = value;
	  return object;
	};

/***/ },
/* 45 */
/***/ function(module, exports, __webpack_require__) {

	var anObject       = __webpack_require__(46)
	  , IE8_DOM_DEFINE = __webpack_require__(48)
	  , toPrimitive    = __webpack_require__(52)
	  , dP             = Object.defineProperty;
	
	exports.f = __webpack_require__(49) ? Object.defineProperty : function defineProperty(O, P, Attributes){
	  anObject(O);
	  P = toPrimitive(P, true);
	  anObject(Attributes);
	  if(IE8_DOM_DEFINE)try {
	    return dP(O, P, Attributes);
	  } catch(e){ /* empty */ }
	  if('get' in Attributes || 'set' in Attributes)throw TypeError('Accessors not supported!');
	  if('value' in Attributes)O[P] = Attributes.value;
	  return O;
	};

/***/ },
/* 46 */
/***/ function(module, exports, __webpack_require__) {

	var isObject = __webpack_require__(47);
	module.exports = function(it){
	  if(!isObject(it))throw TypeError(it + ' is not an object!');
	  return it;
	};

/***/ },
/* 47 */
/***/ function(module, exports) {

	module.exports = function(it){
	  return typeof it === 'object' ? it !== null : typeof it === 'function';
	};

/***/ },
/* 48 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = !__webpack_require__(49) && !__webpack_require__(50)(function(){
	  return Object.defineProperty(__webpack_require__(51)('div'), 'a', {get: function(){ return 7; }}).a != 7;
	});

/***/ },
/* 49 */
/***/ function(module, exports, __webpack_require__) {

	// Thank's IE8 for his funny defineProperty
	module.exports = !__webpack_require__(50)(function(){
	  return Object.defineProperty({}, 'a', {get: function(){ return 7; }}).a != 7;
	});

/***/ },
/* 50 */
/***/ function(module, exports) {

	module.exports = function(exec){
	  try {
	    return !!exec();
	  } catch(e){
	    return true;
	  }
	};

/***/ },
/* 51 */
/***/ function(module, exports, __webpack_require__) {

	var isObject = __webpack_require__(47)
	  , document = __webpack_require__(40).document
	  // in old IE typeof document.createElement is 'object'
	  , is = isObject(document) && isObject(document.createElement);
	module.exports = function(it){
	  return is ? document.createElement(it) : {};
	};

/***/ },
/* 52 */
/***/ function(module, exports, __webpack_require__) {

	// 7.1.1 ToPrimitive(input [, PreferredType])
	var isObject = __webpack_require__(47);
	// instead of the ES6 spec version, we didn't implement @@toPrimitive case
	// and the second argument - flag - preferred type is a string
	module.exports = function(it, S){
	  if(!isObject(it))return it;
	  var fn, val;
	  if(S && typeof (fn = it.toString) == 'function' && !isObject(val = fn.call(it)))return val;
	  if(typeof (fn = it.valueOf) == 'function' && !isObject(val = fn.call(it)))return val;
	  if(!S && typeof (fn = it.toString) == 'function' && !isObject(val = fn.call(it)))return val;
	  throw TypeError("Can't convert object to primitive value");
	};

/***/ },
/* 53 */
/***/ function(module, exports) {

	module.exports = function(bitmap, value){
	  return {
	    enumerable  : !(bitmap & 1),
	    configurable: !(bitmap & 2),
	    writable    : !(bitmap & 4),
	    value       : value
	  };
	};

/***/ },
/* 54 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__(44);

/***/ },
/* 55 */
/***/ function(module, exports) {

	var hasOwnProperty = {}.hasOwnProperty;
	module.exports = function(it, key){
	  return hasOwnProperty.call(it, key);
	};

/***/ },
/* 56 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	var create         = __webpack_require__(57)
	  , descriptor     = __webpack_require__(53)
	  , setToStringTag = __webpack_require__(70)
	  , IteratorPrototype = {};
	
	// 25.1.2.1.1 %IteratorPrototype%[@@iterator]()
	__webpack_require__(44)(IteratorPrototype, __webpack_require__(71)('iterator'), function(){ return this; });
	
	module.exports = function(Constructor, NAME, next){
	  Constructor.prototype = create(IteratorPrototype, {next: descriptor(1, next)});
	  setToStringTag(Constructor, NAME + ' Iterator');
	};

/***/ },
/* 57 */
/***/ function(module, exports, __webpack_require__) {

	// 19.1.2.2 / 15.2.3.5 Object.create(O [, Properties])
	var anObject    = __webpack_require__(46)
	  , dPs         = __webpack_require__(58)
	  , enumBugKeys = __webpack_require__(68)
	  , IE_PROTO    = __webpack_require__(65)('IE_PROTO')
	  , Empty       = function(){ /* empty */ }
	  , PROTOTYPE   = 'prototype';
	
	// Create object with fake `null` prototype: use iframe Object with cleared prototype
	var createDict = function(){
	  // Thrash, waste and sodomy: IE GC bug
	  var iframe = __webpack_require__(51)('iframe')
	    , i      = enumBugKeys.length
	    , lt     = '<'
	    , gt     = '>'
	    , iframeDocument;
	  iframe.style.display = 'none';
	  __webpack_require__(69).appendChild(iframe);
	  iframe.src = 'javascript:'; // eslint-disable-line no-script-url
	  // createDict = iframe.contentWindow.Object;
	  // html.removeChild(iframe);
	  iframeDocument = iframe.contentWindow.document;
	  iframeDocument.open();
	  iframeDocument.write(lt + 'script' + gt + 'document.F=Object' + lt + '/script' + gt);
	  iframeDocument.close();
	  createDict = iframeDocument.F;
	  while(i--)delete createDict[PROTOTYPE][enumBugKeys[i]];
	  return createDict();
	};
	
	module.exports = Object.create || function create(O, Properties){
	  var result;
	  if(O !== null){
	    Empty[PROTOTYPE] = anObject(O);
	    result = new Empty;
	    Empty[PROTOTYPE] = null;
	    // add "__proto__" for Object.getPrototypeOf polyfill
	    result[IE_PROTO] = O;
	  } else result = createDict();
	  return Properties === undefined ? result : dPs(result, Properties);
	};


/***/ },
/* 58 */
/***/ function(module, exports, __webpack_require__) {

	var dP       = __webpack_require__(45)
	  , anObject = __webpack_require__(46)
	  , getKeys  = __webpack_require__(59);
	
	module.exports = __webpack_require__(49) ? Object.defineProperties : function defineProperties(O, Properties){
	  anObject(O);
	  var keys   = getKeys(Properties)
	    , length = keys.length
	    , i = 0
	    , P;
	  while(length > i)dP.f(O, P = keys[i++], Properties[P]);
	  return O;
	};

/***/ },
/* 59 */
/***/ function(module, exports, __webpack_require__) {

	// 19.1.2.14 / 15.2.3.14 Object.keys(O)
	var $keys       = __webpack_require__(60)
	  , enumBugKeys = __webpack_require__(68);
	
	module.exports = Object.keys || function keys(O){
	  return $keys(O, enumBugKeys);
	};

/***/ },
/* 60 */
/***/ function(module, exports, __webpack_require__) {

	var has          = __webpack_require__(55)
	  , toIObject    = __webpack_require__(33)
	  , arrayIndexOf = __webpack_require__(61)(false)
	  , IE_PROTO     = __webpack_require__(65)('IE_PROTO');
	
	module.exports = function(object, names){
	  var O      = toIObject(object)
	    , i      = 0
	    , result = []
	    , key;
	  for(key in O)if(key != IE_PROTO)has(O, key) && result.push(key);
	  // Don't enum bug & hidden keys
	  while(names.length > i)if(has(O, key = names[i++])){
	    ~arrayIndexOf(result, key) || result.push(key);
	  }
	  return result;
	};

/***/ },
/* 61 */
/***/ function(module, exports, __webpack_require__) {

	// false -> Array#indexOf
	// true  -> Array#includes
	var toIObject = __webpack_require__(33)
	  , toLength  = __webpack_require__(62)
	  , toIndex   = __webpack_require__(64);
	module.exports = function(IS_INCLUDES){
	  return function($this, el, fromIndex){
	    var O      = toIObject($this)
	      , length = toLength(O.length)
	      , index  = toIndex(fromIndex, length)
	      , value;
	    // Array#includes uses SameValueZero equality algorithm
	    if(IS_INCLUDES && el != el)while(length > index){
	      value = O[index++];
	      if(value != value)return true;
	    // Array#toIndex ignores holes, Array#includes - not
	    } else for(;length > index; index++)if(IS_INCLUDES || index in O){
	      if(O[index] === el)return IS_INCLUDES || index || 0;
	    } return !IS_INCLUDES && -1;
	  };
	};

/***/ },
/* 62 */
/***/ function(module, exports, __webpack_require__) {

	// 7.1.15 ToLength
	var toInteger = __webpack_require__(63)
	  , min       = Math.min;
	module.exports = function(it){
	  return it > 0 ? min(toInteger(it), 0x1fffffffffffff) : 0; // pow(2, 53) - 1 == 9007199254740991
	};

/***/ },
/* 63 */
/***/ function(module, exports) {

	// 7.1.4 ToInteger
	var ceil  = Math.ceil
	  , floor = Math.floor;
	module.exports = function(it){
	  return isNaN(it = +it) ? 0 : (it > 0 ? floor : ceil)(it);
	};

/***/ },
/* 64 */
/***/ function(module, exports, __webpack_require__) {

	var toInteger = __webpack_require__(63)
	  , max       = Math.max
	  , min       = Math.min;
	module.exports = function(index, length){
	  index = toInteger(index);
	  return index < 0 ? max(index + length, 0) : min(index, length);
	};

/***/ },
/* 65 */
/***/ function(module, exports, __webpack_require__) {

	var shared = __webpack_require__(66)('keys')
	  , uid    = __webpack_require__(67);
	module.exports = function(key){
	  return shared[key] || (shared[key] = uid(key));
	};

/***/ },
/* 66 */
/***/ function(module, exports, __webpack_require__) {

	var global = __webpack_require__(40)
	  , SHARED = '__core-js_shared__'
	  , store  = global[SHARED] || (global[SHARED] = {});
	module.exports = function(key){
	  return store[key] || (store[key] = {});
	};

/***/ },
/* 67 */
/***/ function(module, exports) {

	var id = 0
	  , px = Math.random();
	module.exports = function(key){
	  return 'Symbol('.concat(key === undefined ? '' : key, ')_', (++id + px).toString(36));
	};

/***/ },
/* 68 */
/***/ function(module, exports) {

	// IE 8- don't enum bug keys
	module.exports = (
	  'constructor,hasOwnProperty,isPrototypeOf,propertyIsEnumerable,toLocaleString,toString,valueOf'
	).split(',');

/***/ },
/* 69 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = __webpack_require__(40).document && document.documentElement;

/***/ },
/* 70 */
/***/ function(module, exports, __webpack_require__) {

	var def = __webpack_require__(45).f
	  , has = __webpack_require__(55)
	  , TAG = __webpack_require__(71)('toStringTag');
	
	module.exports = function(it, tag, stat){
	  if(it && !has(it = stat ? it : it.prototype, TAG))def(it, TAG, {configurable: true, value: tag});
	};

/***/ },
/* 71 */
/***/ function(module, exports, __webpack_require__) {

	var store      = __webpack_require__(66)('wks')
	  , uid        = __webpack_require__(67)
	  , Symbol     = __webpack_require__(40).Symbol
	  , USE_SYMBOL = typeof Symbol == 'function';
	
	var $exports = module.exports = function(name){
	  return store[name] || (store[name] =
	    USE_SYMBOL && Symbol[name] || (USE_SYMBOL ? Symbol : uid)('Symbol.' + name));
	};
	
	$exports.store = store;

/***/ },
/* 72 */
/***/ function(module, exports, __webpack_require__) {

	// 19.1.2.9 / 15.2.3.2 Object.getPrototypeOf(O)
	var has         = __webpack_require__(55)
	  , toObject    = __webpack_require__(73)
	  , IE_PROTO    = __webpack_require__(65)('IE_PROTO')
	  , ObjectProto = Object.prototype;
	
	module.exports = Object.getPrototypeOf || function(O){
	  O = toObject(O);
	  if(has(O, IE_PROTO))return O[IE_PROTO];
	  if(typeof O.constructor == 'function' && O instanceof O.constructor){
	    return O.constructor.prototype;
	  } return O instanceof Object ? ObjectProto : null;
	};

/***/ },
/* 73 */
/***/ function(module, exports, __webpack_require__) {

	// 7.1.13 ToObject(argument)
	var defined = __webpack_require__(36);
	module.exports = function(it){
	  return Object(defined(it));
	};

/***/ },
/* 74 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	var $at  = __webpack_require__(75)(true);
	
	// 21.1.3.27 String.prototype[@@iterator]()
	__webpack_require__(37)(String, 'String', function(iterated){
	  this._t = String(iterated); // target
	  this._i = 0;                // next index
	// 21.1.5.2.1 %StringIteratorPrototype%.next()
	}, function(){
	  var O     = this._t
	    , index = this._i
	    , point;
	  if(index >= O.length)return {value: undefined, done: true};
	  point = $at(O, index);
	  this._i += point.length;
	  return {value: point, done: false};
	});

/***/ },
/* 75 */
/***/ function(module, exports, __webpack_require__) {

	var toInteger = __webpack_require__(63)
	  , defined   = __webpack_require__(36);
	// true  -> String#at
	// false -> String#codePointAt
	module.exports = function(TO_STRING){
	  return function(that, pos){
	    var s = String(defined(that))
	      , i = toInteger(pos)
	      , l = s.length
	      , a, b;
	    if(i < 0 || i >= l)return TO_STRING ? '' : undefined;
	    a = s.charCodeAt(i);
	    return a < 0xd800 || a > 0xdbff || i + 1 === l || (b = s.charCodeAt(i + 1)) < 0xdc00 || b > 0xdfff
	      ? TO_STRING ? s.charAt(i) : a
	      : TO_STRING ? s.slice(i, i + 2) : (a - 0xd800 << 10) + (b - 0xdc00) + 0x10000;
	  };
	};

/***/ },
/* 76 */,
/* 77 */,
/* 78 */,
/* 79 */,
/* 80 */,
/* 81 */
/***/ function(module, exports, __webpack_require__) {

	"use strict";
	
	exports.__esModule = true;
	
	var _iterator = __webpack_require__(82);
	
	var _iterator2 = _interopRequireDefault(_iterator);
	
	var _symbol = __webpack_require__(85);
	
	var _symbol2 = _interopRequireDefault(_symbol);
	
	var _typeof = typeof _symbol2.default === "function" && typeof _iterator2.default === "symbol" ? function (obj) { return typeof obj; } : function (obj) { return obj && typeof _symbol2.default === "function" && obj.constructor === _symbol2.default ? "symbol" : typeof obj; };
	
	function _interopRequireDefault(obj) { return obj && obj.__esModule ? obj : { default: obj }; }
	
	exports.default = typeof _symbol2.default === "function" && _typeof(_iterator2.default) === "symbol" ? function (obj) {
	  return typeof obj === "undefined" ? "undefined" : _typeof(obj);
	} : function (obj) {
	  return obj && typeof _symbol2.default === "function" && obj.constructor === _symbol2.default ? "symbol" : typeof obj === "undefined" ? "undefined" : _typeof(obj);
	};

/***/ },
/* 82 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = { "default": __webpack_require__(83), __esModule: true };

/***/ },
/* 83 */
/***/ function(module, exports, __webpack_require__) {

	__webpack_require__(74);
	__webpack_require__(28);
	module.exports = __webpack_require__(84).f('iterator');

/***/ },
/* 84 */
/***/ function(module, exports, __webpack_require__) {

	exports.f = __webpack_require__(71);

/***/ },
/* 85 */
/***/ function(module, exports, __webpack_require__) {

	module.exports = { "default": __webpack_require__(86), __esModule: true };

/***/ },
/* 86 */
/***/ function(module, exports, __webpack_require__) {

	__webpack_require__(87);
	__webpack_require__(98);
	__webpack_require__(99);
	__webpack_require__(100);
	module.exports = __webpack_require__(41).Symbol;

/***/ },
/* 87 */
/***/ function(module, exports, __webpack_require__) {

	'use strict';
	// ECMAScript 6 symbols shim
	var global         = __webpack_require__(40)
	  , has            = __webpack_require__(55)
	  , DESCRIPTORS    = __webpack_require__(49)
	  , $export        = __webpack_require__(39)
	  , redefine       = __webpack_require__(54)
	  , META           = __webpack_require__(88).KEY
	  , $fails         = __webpack_require__(50)
	  , shared         = __webpack_require__(66)
	  , setToStringTag = __webpack_require__(70)
	  , uid            = __webpack_require__(67)
	  , wks            = __webpack_require__(71)
	  , wksExt         = __webpack_require__(84)
	  , wksDefine      = __webpack_require__(89)
	  , keyOf          = __webpack_require__(90)
	  , enumKeys       = __webpack_require__(91)
	  , isArray        = __webpack_require__(94)
	  , anObject       = __webpack_require__(46)
	  , toIObject      = __webpack_require__(33)
	  , toPrimitive    = __webpack_require__(52)
	  , createDesc     = __webpack_require__(53)
	  , _create        = __webpack_require__(57)
	  , gOPNExt        = __webpack_require__(95)
	  , $GOPD          = __webpack_require__(97)
	  , $DP            = __webpack_require__(45)
	  , $keys          = __webpack_require__(59)
	  , gOPD           = $GOPD.f
	  , dP             = $DP.f
	  , gOPN           = gOPNExt.f
	  , $Symbol        = global.Symbol
	  , $JSON          = global.JSON
	  , _stringify     = $JSON && $JSON.stringify
	  , PROTOTYPE      = 'prototype'
	  , HIDDEN         = wks('_hidden')
	  , TO_PRIMITIVE   = wks('toPrimitive')
	  , isEnum         = {}.propertyIsEnumerable
	  , SymbolRegistry = shared('symbol-registry')
	  , AllSymbols     = shared('symbols')
	  , OPSymbols      = shared('op-symbols')
	  , ObjectProto    = Object[PROTOTYPE]
	  , USE_NATIVE     = typeof $Symbol == 'function'
	  , QObject        = global.QObject;
	// Don't use setters in Qt Script, https://github.com/zloirock/core-js/issues/173
	var setter = !QObject || !QObject[PROTOTYPE] || !QObject[PROTOTYPE].findChild;
	
	// fallback for old Android, https://code.google.com/p/v8/issues/detail?id=687
	var setSymbolDesc = DESCRIPTORS && $fails(function(){
	  return _create(dP({}, 'a', {
	    get: function(){ return dP(this, 'a', {value: 7}).a; }
	  })).a != 7;
	}) ? function(it, key, D){
	  var protoDesc = gOPD(ObjectProto, key);
	  if(protoDesc)delete ObjectProto[key];
	  dP(it, key, D);
	  if(protoDesc && it !== ObjectProto)dP(ObjectProto, key, protoDesc);
	} : dP;
	
	var wrap = function(tag){
	  var sym = AllSymbols[tag] = _create($Symbol[PROTOTYPE]);
	  sym._k = tag;
	  return sym;
	};
	
	var isSymbol = USE_NATIVE && typeof $Symbol.iterator == 'symbol' ? function(it){
	  return typeof it == 'symbol';
	} : function(it){
	  return it instanceof $Symbol;
	};
	
	var $defineProperty = function defineProperty(it, key, D){
	  if(it === ObjectProto)$defineProperty(OPSymbols, key, D);
	  anObject(it);
	  key = toPrimitive(key, true);
	  anObject(D);
	  if(has(AllSymbols, key)){
	    if(!D.enumerable){
	      if(!has(it, HIDDEN))dP(it, HIDDEN, createDesc(1, {}));
	      it[HIDDEN][key] = true;
	    } else {
	      if(has(it, HIDDEN) && it[HIDDEN][key])it[HIDDEN][key] = false;
	      D = _create(D, {enumerable: createDesc(0, false)});
	    } return setSymbolDesc(it, key, D);
	  } return dP(it, key, D);
	};
	var $defineProperties = function defineProperties(it, P){
	  anObject(it);
	  var keys = enumKeys(P = toIObject(P))
	    , i    = 0
	    , l = keys.length
	    , key;
	  while(l > i)$defineProperty(it, key = keys[i++], P[key]);
	  return it;
	};
	var $create = function create(it, P){
	  return P === undefined ? _create(it) : $defineProperties(_create(it), P);
	};
	var $propertyIsEnumerable = function propertyIsEnumerable(key){
	  var E = isEnum.call(this, key = toPrimitive(key, true));
	  if(this === ObjectProto && has(AllSymbols, key) && !has(OPSymbols, key))return false;
	  return E || !has(this, key) || !has(AllSymbols, key) || has(this, HIDDEN) && this[HIDDEN][key] ? E : true;
	};
	var $getOwnPropertyDescriptor = function getOwnPropertyDescriptor(it, key){
	  it  = toIObject(it);
	  key = toPrimitive(key, true);
	  if(it === ObjectProto && has(AllSymbols, key) && !has(OPSymbols, key))return;
	  var D = gOPD(it, key);
	  if(D && has(AllSymbols, key) && !(has(it, HIDDEN) && it[HIDDEN][key]))D.enumerable = true;
	  return D;
	};
	var $getOwnPropertyNames = function getOwnPropertyNames(it){
	  var names  = gOPN(toIObject(it))
	    , result = []
	    , i      = 0
	    , key;
	  while(names.length > i){
	    if(!has(AllSymbols, key = names[i++]) && key != HIDDEN && key != META)result.push(key);
	  } return result;
	};
	var $getOwnPropertySymbols = function getOwnPropertySymbols(it){
	  var IS_OP  = it === ObjectProto
	    , names  = gOPN(IS_OP ? OPSymbols : toIObject(it))
	    , result = []
	    , i      = 0
	    , key;
	  while(names.length > i){
	    if(has(AllSymbols, key = names[i++]) && (IS_OP ? has(ObjectProto, key) : true))result.push(AllSymbols[key]);
	  } return result;
	};
	
	// 19.4.1.1 Symbol([description])
	if(!USE_NATIVE){
	  $Symbol = function Symbol(){
	    if(this instanceof $Symbol)throw TypeError('Symbol is not a constructor!');
	    var tag = uid(arguments.length > 0 ? arguments[0] : undefined);
	    var $set = function(value){
	      if(this === ObjectProto)$set.call(OPSymbols, value);
	      if(has(this, HIDDEN) && has(this[HIDDEN], tag))this[HIDDEN][tag] = false;
	      setSymbolDesc(this, tag, createDesc(1, value));
	    };
	    if(DESCRIPTORS && setter)setSymbolDesc(ObjectProto, tag, {configurable: true, set: $set});
	    return wrap(tag);
	  };
	  redefine($Symbol[PROTOTYPE], 'toString', function toString(){
	    return this._k;
	  });
	
	  $GOPD.f = $getOwnPropertyDescriptor;
	  $DP.f   = $defineProperty;
	  __webpack_require__(96).f = gOPNExt.f = $getOwnPropertyNames;
	  __webpack_require__(93).f  = $propertyIsEnumerable;
	  __webpack_require__(92).f = $getOwnPropertySymbols;
	
	  if(DESCRIPTORS && !__webpack_require__(38)){
	    redefine(ObjectProto, 'propertyIsEnumerable', $propertyIsEnumerable, true);
	  }
	
	  wksExt.f = function(name){
	    return wrap(wks(name));
	  }
	}
	
	$export($export.G + $export.W + $export.F * !USE_NATIVE, {Symbol: $Symbol});
	
	for(var symbols = (
	  // 19.4.2.2, 19.4.2.3, 19.4.2.4, 19.4.2.6, 19.4.2.8, 19.4.2.9, 19.4.2.10, 19.4.2.11, 19.4.2.12, 19.4.2.13, 19.4.2.14
	  'hasInstance,isConcatSpreadable,iterator,match,replace,search,species,split,toPrimitive,toStringTag,unscopables'
	).split(','), i = 0; symbols.length > i; )wks(symbols[i++]);
	
	for(var symbols = $keys(wks.store), i = 0; symbols.length > i; )wksDefine(symbols[i++]);
	
	$export($export.S + $export.F * !USE_NATIVE, 'Symbol', {
	  // 19.4.2.1 Symbol.for(key)
	  'for': function(key){
	    return has(SymbolRegistry, key += '')
	      ? SymbolRegistry[key]
	      : SymbolRegistry[key] = $Symbol(key);
	  },
	  // 19.4.2.5 Symbol.keyFor(sym)
	  keyFor: function keyFor(key){
	    if(isSymbol(key))return keyOf(SymbolRegistry, key);
	    throw TypeError(key + ' is not a symbol!');
	  },
	  useSetter: function(){ setter = true; },
	  useSimple: function(){ setter = false; }
	});
	
	$export($export.S + $export.F * !USE_NATIVE, 'Object', {
	  // 19.1.2.2 Object.create(O [, Properties])
	  create: $create,
	  // 19.1.2.4 Object.defineProperty(O, P, Attributes)
	  defineProperty: $defineProperty,
	  // 19.1.2.3 Object.defineProperties(O, Properties)
	  defineProperties: $defineProperties,
	  // 19.1.2.6 Object.getOwnPropertyDescriptor(O, P)
	  getOwnPropertyDescriptor: $getOwnPropertyDescriptor,
	  // 19.1.2.7 Object.getOwnPropertyNames(O)
	  getOwnPropertyNames: $getOwnPropertyNames,
	  // 19.1.2.8 Object.getOwnPropertySymbols(O)
	  getOwnPropertySymbols: $getOwnPropertySymbols
	});
	
	// 24.3.2 JSON.stringify(value [, replacer [, space]])
	$JSON && $export($export.S + $export.F * (!USE_NATIVE || $fails(function(){
	  var S = $Symbol();
	  // MS Edge converts symbol values to JSON as {}
	  // WebKit converts symbol values to JSON as null
	  // V8 throws on boxed symbols
	  return _stringify([S]) != '[null]' || _stringify({a: S}) != '{}' || _stringify(Object(S)) != '{}';
	})), 'JSON', {
	  stringify: function stringify(it){
	    if(it === undefined || isSymbol(it))return; // IE8 returns string on undefined
	    var args = [it]
	      , i    = 1
	      , replacer, $replacer;
	    while(arguments.length > i)args.push(arguments[i++]);
	    replacer = args[1];
	    if(typeof replacer == 'function')$replacer = replacer;
	    if($replacer || !isArray(replacer))replacer = function(key, value){
	      if($replacer)value = $replacer.call(this, key, value);
	      if(!isSymbol(value))return value;
	    };
	    args[1] = replacer;
	    return _stringify.apply($JSON, args);
	  }
	});
	
	// 19.4.3.4 Symbol.prototype[@@toPrimitive](hint)
	$Symbol[PROTOTYPE][TO_PRIMITIVE] || __webpack_require__(44)($Symbol[PROTOTYPE], TO_PRIMITIVE, $Symbol[PROTOTYPE].valueOf);
	// 19.4.3.5 Symbol.prototype[@@toStringTag]
	setToStringTag($Symbol, 'Symbol');
	// 20.2.1.9 Math[@@toStringTag]
	setToStringTag(Math, 'Math', true);
	// 24.3.3 JSON[@@toStringTag]
	setToStringTag(global.JSON, 'JSON', true);

/***/ },
/* 88 */
/***/ function(module, exports, __webpack_require__) {

	var META     = __webpack_require__(67)('meta')
	  , isObject = __webpack_require__(47)
	  , has      = __webpack_require__(55)
	  , setDesc  = __webpack_require__(45).f
	  , id       = 0;
	var isExtensible = Object.isExtensible || function(){
	  return true;
	};
	var FREEZE = !__webpack_require__(50)(function(){
	  return isExtensible(Object.preventExtensions({}));
	});
	var setMeta = function(it){
	  setDesc(it, META, {value: {
	    i: 'O' + ++id, // object ID
	    w: {}          // weak collections IDs
	  }});
	};
	var fastKey = function(it, create){
	  // return primitive with prefix
	  if(!isObject(it))return typeof it == 'symbol' ? it : (typeof it == 'string' ? 'S' : 'P') + it;
	  if(!has(it, META)){
	    // can't set metadata to uncaught frozen object
	    if(!isExtensible(it))return 'F';
	    // not necessary to add metadata
	    if(!create)return 'E';
	    // add missing metadata
	    setMeta(it);
	  // return object ID
	  } return it[META].i;
	};
	var getWeak = function(it, create){
	  if(!has(it, META)){
	    // can't set metadata to uncaught frozen object
	    if(!isExtensible(it))return true;
	    // not necessary to add metadata
	    if(!create)return false;
	    // add missing metadata
	    setMeta(it);
	  // return hash weak collections IDs
	  } return it[META].w;
	};
	// add metadata on freeze-family methods calling
	var onFreeze = function(it){
	  if(FREEZE && meta.NEED && isExtensible(it) && !has(it, META))setMeta(it);
	  return it;
	};
	var meta = module.exports = {
	  KEY:      META,
	  NEED:     false,
	  fastKey:  fastKey,
	  getWeak:  getWeak,
	  onFreeze: onFreeze
	};

/***/ },
/* 89 */
/***/ function(module, exports, __webpack_require__) {

	var global         = __webpack_require__(40)
	  , core           = __webpack_require__(41)
	  , LIBRARY        = __webpack_require__(38)
	  , wksExt         = __webpack_require__(84)
	  , defineProperty = __webpack_require__(45).f;
	module.exports = function(name){
	  var $Symbol = core.Symbol || (core.Symbol = LIBRARY ? {} : global.Symbol || {});
	  if(name.charAt(0) != '_' && !(name in $Symbol))defineProperty($Symbol, name, {value: wksExt.f(name)});
	};

/***/ },
/* 90 */
/***/ function(module, exports, __webpack_require__) {

	var getKeys   = __webpack_require__(59)
	  , toIObject = __webpack_require__(33);
	module.exports = function(object, el){
	  var O      = toIObject(object)
	    , keys   = getKeys(O)
	    , length = keys.length
	    , index  = 0
	    , key;
	  while(length > index)if(O[key = keys[index++]] === el)return key;
	};

/***/ },
/* 91 */
/***/ function(module, exports, __webpack_require__) {

	// all enumerable object keys, includes symbols
	var getKeys = __webpack_require__(59)
	  , gOPS    = __webpack_require__(92)
	  , pIE     = __webpack_require__(93);
	module.exports = function(it){
	  var result     = getKeys(it)
	    , getSymbols = gOPS.f;
	  if(getSymbols){
	    var symbols = getSymbols(it)
	      , isEnum  = pIE.f
	      , i       = 0
	      , key;
	    while(symbols.length > i)if(isEnum.call(it, key = symbols[i++]))result.push(key);
	  } return result;
	};

/***/ },
/* 92 */
/***/ function(module, exports) {

	exports.f = Object.getOwnPropertySymbols;

/***/ },
/* 93 */
/***/ function(module, exports) {

	exports.f = {}.propertyIsEnumerable;

/***/ },
/* 94 */
/***/ function(module, exports, __webpack_require__) {

	// 7.2.2 IsArray(argument)
	var cof = __webpack_require__(35);
	module.exports = Array.isArray || function isArray(arg){
	  return cof(arg) == 'Array';
	};

/***/ },
/* 95 */
/***/ function(module, exports, __webpack_require__) {

	// fallback for IE11 buggy Object.getOwnPropertyNames with iframe and window
	var toIObject = __webpack_require__(33)
	  , gOPN      = __webpack_require__(96).f
	  , toString  = {}.toString;
	
	var windowNames = typeof window == 'object' && window && Object.getOwnPropertyNames
	  ? Object.getOwnPropertyNames(window) : [];
	
	var getWindowNames = function(it){
	  try {
	    return gOPN(it);
	  } catch(e){
	    return windowNames.slice();
	  }
	};
	
	module.exports.f = function getOwnPropertyNames(it){
	  return windowNames && toString.call(it) == '[object Window]' ? getWindowNames(it) : gOPN(toIObject(it));
	};


/***/ },
/* 96 */
/***/ function(module, exports, __webpack_require__) {

	// 19.1.2.7 / 15.2.3.4 Object.getOwnPropertyNames(O)
	var $keys      = __webpack_require__(60)
	  , hiddenKeys = __webpack_require__(68).concat('length', 'prototype');
	
	exports.f = Object.getOwnPropertyNames || function getOwnPropertyNames(O){
	  return $keys(O, hiddenKeys);
	};

/***/ },
/* 97 */
/***/ function(module, exports, __webpack_require__) {

	var pIE            = __webpack_require__(93)
	  , createDesc     = __webpack_require__(53)
	  , toIObject      = __webpack_require__(33)
	  , toPrimitive    = __webpack_require__(52)
	  , has            = __webpack_require__(55)
	  , IE8_DOM_DEFINE = __webpack_require__(48)
	  , gOPD           = Object.getOwnPropertyDescriptor;
	
	exports.f = __webpack_require__(49) ? gOPD : function getOwnPropertyDescriptor(O, P){
	  O = toIObject(O);
	  P = toPrimitive(P, true);
	  if(IE8_DOM_DEFINE)try {
	    return gOPD(O, P);
	  } catch(e){ /* empty */ }
	  if(has(O, P))return createDesc(!pIE.f.call(O, P), O[P]);
	};

/***/ },
/* 98 */
/***/ function(module, exports) {



/***/ },
/* 99 */
/***/ function(module, exports, __webpack_require__) {

	__webpack_require__(89)('asyncIterator');

/***/ },
/* 100 */
/***/ function(module, exports, __webpack_require__) {

	__webpack_require__(89)('observable');

/***/ },
/* 101 */,
/* 102 */,
/* 103 */,
/* 104 */,
/* 105 */,
/* 106 */,
/* 107 */,
/* 108 */,
/* 109 */,
/* 110 */,
/* 111 */,
/* 112 */,
/* 113 */,
/* 114 */,
/* 115 */,
/* 116 */,
/* 117 */,
/* 118 */,
/* 119 */,
/* 120 */,
/* 121 */,
/* 122 */,
/* 123 */,
/* 124 */,
/* 125 */,
/* 126 */,
/* 127 */,
/* 128 */,
/* 129 */,
/* 130 */,
/* 131 */,
/* 132 */,
/* 133 */,
/* 134 */,
/* 135 */,
/* 136 */,
/* 137 */,
/* 138 */,
/* 139 */,
/* 140 */,
/* 141 */,
/* 142 */,
/* 143 */,
/* 144 */,
/* 145 */,
/* 146 */,
/* 147 */,
/* 148 */,
/* 149 */,
/* 150 */,
/* 151 */,
/* 152 */,
/* 153 */,
/* 154 */,
/* 155 */,
/* 156 */,
/* 157 */,
/* 158 */,
/* 159 */,
/* 160 */,
/* 161 */,
/* 162 */,
/* 163 */,
/* 164 */,
/* 165 */,
/* 166 */,
/* 167 */,
/* 168 */,
/* 169 */,
/* 170 */,
/* 171 */,
/* 172 */,
/* 173 */,
/* 174 */,
/* 175 */,
/* 176 */,
/* 177 */
/***/ function(module, exports, __webpack_require__) {

	__webpack_require__(178)
	module.exports = __webpack_require__(180)
	
	if (module.exports.__esModule) module.exports = module.exports.default
	;(typeof module.exports === "function" ? module.exports.options : module.exports).template = __webpack_require__(182)
	if (false) {
	(function () {
	var hotAPI = require("vue-hot-reload-api")
	hotAPI.install(require("vue"))
	if (!hotAPI.compatible) return
	var id = "-!babel!./../node_modules/vue-loader/lib/selector.js?type=script&index=0!./UdmpZtree.vue"
	hotAPI.createRecord(id, module.exports)
	module.hot.accept(["-!babel!./../node_modules/vue-loader/lib/selector.js?type=script&index=0!./UdmpZtree.vue","-!vue-html-loader!./../node_modules/vue-loader/lib/selector.js?type=template&index=0!./UdmpZtree.vue"], function () {
	var newOptions = require("-!babel!./../node_modules/vue-loader/lib/selector.js?type=script&index=0!./UdmpZtree.vue")
	if (newOptions && newOptions.__esModule) newOptions = newOptions.default
	var newTemplate = require("-!vue-html-loader!./../node_modules/vue-loader/lib/selector.js?type=template&index=0!./UdmpZtree.vue")
	hotAPI.update(id, newOptions, newTemplate)
	})
	})()
	}

/***/ },
/* 178 */
/***/ function(module, exports, __webpack_require__) {

	// style-loader: Adds some css to the DOM by adding a <style> tag
	
	// load the styles
	var content = __webpack_require__(179);
	if(typeof content === 'string') content = [[module.id, content, '']];
	// add the styles to the DOM
	var update = __webpack_require__(24)(content, {});
	if(content.locals) module.exports = content.locals;
	// Hot Module Replacement
	if(false) {
		// When the styles change, update the <style> tags
		if(!content.locals) {
			module.hot.accept("!!./../node_modules/css-loader/index.js!./../node_modules/vue-loader/lib/style-rewriter.js?id=_v-3d61d7dc&file=UdmpZtree.vue!./../node_modules/sass-loader/index.js!./../node_modules/vue-loader/lib/selector.js?type=style&index=0!./UdmpZtree.vue", function() {
				var newContent = require("!!./../node_modules/css-loader/index.js!./../node_modules/vue-loader/lib/style-rewriter.js?id=_v-3d61d7dc&file=UdmpZtree.vue!./../node_modules/sass-loader/index.js!./../node_modules/vue-loader/lib/selector.js?type=style&index=0!./UdmpZtree.vue");
				if(typeof newContent === 'string') newContent = [[module.id, newContent, '']];
				update(newContent);
			});
		}
		// When the module is disposed, remove the <style> tags
		module.hot.dispose(function() { update(); });
	}

/***/ },
/* 179 */
/***/ function(module, exports, __webpack_require__) {

	exports = module.exports = __webpack_require__(23)();
	// imports
	
	
	// module
	exports.push([module.id, "@charset \"UTF-8\";\n.ztree * {\n  padding: 0;\n  margin: 0;\n  font-size: 12px;\n  font-family: Verdana, Arial, Helvetica, AppleGothic, sans-serif; }\n\n.ztree {\n  margin: 0;\n  padding: 5px;\n  color: #333; }\n\n.ztree li {\n  padding: 0;\n  margin: 0;\n  list-style: none;\n  line-height: 14px;\n  text-align: left;\n  white-space: nowrap;\n  outline: 0; }\n\n.ztree li ul {\n  margin: 0;\n  padding: 0 0 0 18px; }\n\n.ztree li a {\n  padding: 1px 3px 0 0;\n  margin: 0;\n  cursor: pointer;\n  height: 17px;\n  color: #333;\n  background-color: transparent;\n  text-decoration: none;\n  vertical-align: top;\n  display: inline-block; }\n\n.ztree li a:hover {\n  text-decoration: underline; }\n\n.ztree li a.curSelectedNode {\n  padding-top: 0px;\n  background-color: #FFE6B0;\n  color: black;\n  height: 16px;\n  border: 1px #FFB951 solid;\n  opacity: 0.8; }\n\n.ztree li a.curSelectedNode_Edit {\n  padding-top: 0px;\n  background-color: #FFE6B0;\n  color: black;\n  height: 16px;\n  border: 1px #FFB951 solid;\n  opacity: 0.8; }\n\n.ztree li a.tmpTargetNode_inner {\n  padding-top: 0px;\n  background-color: #316AC5;\n  color: white;\n  height: 16px;\n  border: 1px #316AC5 solid;\n  opacity: 0.8;\n  filter: alpha(opacity=80); }\n\n.ztree li a input.rename {\n  height: 14px;\n  width: 80px;\n  padding: 0;\n  margin: 0;\n  font-size: 12px;\n  border: 1px #7EC4CC solid;\n  *border: 0px; }\n\n.ztree li span {\n  line-height: 16px;\n  margin-right: 2px; }\n\n.ztree li span.button {\n  line-height: 0;\n  margin: 0;\n  width: 16px;\n  height: 16px;\n  display: inline-block;\n  vertical-align: middle;\n  border: 0 none;\n  cursor: pointer;\n  outline: none;\n  background-color: transparent;\n  background-repeat: no-repeat;\n  background-attachment: scroll; }\n\n/*ztree样式*/\n#i-user-org {\n  padding: 0 5px 0 15px;\n  overflow-y: hidden;\n  overflow-x: hidden; }\n\n#ztree {\n  width: 200px; }\n\n#orgTree ol > li.active {\n  width: 100%; }\n\n#orgTree ol > li.active em {\n  font-style: normal;\n  font-weight: bold; }\n\n#orgTree ol span.glyphicon {\n  top: 3px; }\n\n#ztree li {\n  margin: 0;\n  position: relative; }\n\n#ztree li::before, #ztree li::after {\n  content: '';\n  left: -30px;\n  position: absolute;\n  right: auto; }\n\n#ztree li::before {\n  border-left: 1px solid #999;\n  bottom: 50px;\n  height: 100%;\n  top: -16px;\n  width: 1px; }\n\n#ztree li::after {\n  border-top: 1px solid #999;\n  height: 20px;\n  top: 13px;\n  width: 30px; }\n\n#ztree > li::before, #ztree > li.i_tree_hidden::after {\n  border: 0; }\n\n#ztree > li::after {\n  width: 12px;\n  left: -12px; }\n\n#ztree li a {\n  padding: 1px 3px 0 0;\n  height: 30px;\n  background-color: transparent;\n  border: none; }\n\n#ztree a > .button, #ztree span.switch {\n  display: none; }\n\n#ztree li a > span {\n  -moz-border-radius: 5px;\n  -webkit-border-radius: 5px;\n  border: 1px solid #999;\n  border-radius: 5px;\n  display: inline-block;\n  padding: 3px 8px;\n  text-decoration: none; }\n\n#ztree li a > span:hover, #ztree li > a.curSelectedNode > span {\n  background-color: #33CCFF;\n  color: #fff;\n  border: 1px solid #33CCDD; }\n\n#ztree i.glyphicon {\n  font-family: 'Glyphicons Halflings';\n  margin-right: 5px; }\n", ""]);
	
	// exports


/***/ },
/* 180 */
/***/ function(module, exports, __webpack_require__) {

	"use strict";
	
	Object.defineProperty(exports, "__esModule", {
	  value: true
	});
	
	__webpack_require__(181);
	
	var url,
	    operate,
	    setting = {
	  data: {
	    simpleData: {
	      enable: true,
	      idKey: "id",
	      pIdKey: "pId",
	      rootPId: '0'
	    }
	  },
	  view: {
	    showLine: false,
	    showIcon: false
	  },
	  callback: {
	    onClick: function onClick(event, treeId, treeNode) {
	      // 判断是否展开/收缩图标
	      if (event.target.tagName && event.target.tagName == 'I') {
	        return;
	      }
	      var id = treeNode.id == '0' ? '' : treeNode.id,
	          parentId = treeNode.pId,
	          parentIds = treeNode.pIds + id;
	
	      if (operate === "table") {
	        //用户管理回调处理
	        $("#manageContent").find("input[name='office.id']").val(id);
	        $("#manageContent").find("input[name='company.name']").val(treeNode.name);
	        $("#manageContent").find("#btnSubmit").trigger("click");
	      } else if (operate === "treetable") {
	        //机构管理回调处理
	        $("#manageContent").find("input[name='id']").val(id);
	        $("#manageContent").find("input[name='parentId']").val(parentId);
	        $("#manageContent").find("input[name='parentIds']").val(parentIds);
	        $("body").trigger('refresh');
	      } else {
	        $.noop();
	      }
	
	      $("#ztree").data({ oid: id, oname: treeNode.name });
	    }
	  }
	};
	// <template>
	//   <div id = "i-user-org">
	//     <div id="ztree" class="ztree"></div>
	//   </div>
	// </template>
	
	// <script>
	
	
	function refreshTree() {
	  $.getJSON(url, function (data) {
	    ///FIS/vue-strap/treeData.json
	    $.fn.zTree.init($("#ztree"), setting, data).expandAll(true);
	    $('.ztree li:has(ul)').addClass('parent_li').find('>a >span:last').prepend('<i class="glyphicon glyphicon-minus-sign"></i>');
	    $('.ztree li>a>span').on('click', function () {
	      $('#ztree>li').removeClass('i_tree_hidden');
	      $('#ztree .curSelectedNode').removeClass('curSelectedNode');
	    });
	    $('.ztree li.parent_li >a >span > i').on('click', function (e) {
	      var $children = $(this).parents('li.parent_li:eq(0)').find(' > ul > li');
	      if ($children.is(":visible")) {
	        $children.hide('fast');
	        if ($(this).parent("a").attr("class") == "level0") {
	          $('#ztree>li').addClass('i_tree_hidden');
	        }
	        $(this).find(' > i').addClass('glyphicon-plus-sign').removeClass('glyphicon-minus-sign').end().parent("a").addClass("curSelectedNode");
	      } else {
	        $children.show('fast');
	        $(this).find(' > i').addClass('glyphicon-minus-sign').removeClass('glyphicon-plus-sign').end().parent("a").addClass("curSelectedNode");
	      }
	    });
	    //组件加载完成，进度条增加30
	    if (window.top.i_web_pg.getProgress() !== 0) {
	      window.top.i_web_pg.increase(30);
	    }
	    console.info('tree组件加载完成');
	  });
	};
	
	exports.default = {
	  data: function data() {
	    return {};
	  },
	
	  props: {
	    url: {
	      required: true,
	      default: null
	    },
	    operate: {
	      default: "table"
	    }
	  },
	  methods: {
	    fresh: refreshTree
	  },
	  mounted: function mounted() {
	    url = this.url;
	    operate = this.operate;
	    refreshTree();
	    //设置Tree的高度
	    //$('#i-user-org').height(window.top.document.body.clientHeight-190);
	    setTimeout(function () {
	      return $('#i-user-org').height($('body').outerHeight(true) - 100).css('overflow-y', 'auto');
	    }, 2000);
	  }
	};
	// </script>
	
	// <style lang="sass">
	
	//   .ztree * {padding:0; margin:0; font-size:12px; font-family: Verdana, Arial, Helvetica, AppleGothic, sans-serif}
	//   .ztree {margin:0; padding:5px; color:#333}
	//   .ztree li{padding:0; margin:0; list-style:none; line-height:14px; text-align:left; white-space:nowrap; outline:0}
	//   .ztree li ul{ margin:0; padding:0 0 0 18px}
	
	//   .ztree li a {padding:1px 3px 0 0; margin:0; cursor:pointer; height:17px; color:#333; background-color: transparent;text-decoration:none; vertical-align:top; display: inline-block}
	//   .ztree li a:hover {text-decoration:underline}
	//   .ztree li a.curSelectedNode {padding-top:0px; background-color:#FFE6B0; color:black; height:16px; border:1px #FFB951 solid; opacity:0.8;}
	//   .ztree li a.curSelectedNode_Edit {padding-top:0px; background-color:#FFE6B0; color:black; height:16px; border:1px #FFB951 solid; opacity:0.8;}
	//   .ztree li a.tmpTargetNode_inner {padding-top:0px; background-color:#316AC5; color:white; height:16px; border:1px #316AC5 solid; opacity:0.8; filter:alpha(opacity=80)}
	//   .ztree li a.tmpTargetNode_prev {}
	//   .ztree li a.tmpTargetNode_next {}
	//   .ztree li a input.rename {height:14px; width:80px; padding:0; margin:0; font-size:12px; border:1px #7EC4CC solid; *border:0px}
	//   .ztree li span {line-height:16px; margin-right:2px}
	//   .ztree li span.button {line-height:0; margin:0; width:16px; height:16px; display: inline-block; vertical-align:middle; border:0 none; cursor: pointer;outline:none; background-color:transparent; background-repeat:no-repeat; background-attachment: scroll;}
	
	//   /*ztree样式*/
	//   #i-user-org {
	//       padding: 0 5px 0 15px;
	//       overflow-y: hidden;
	//       overflow-x: hidden;
	//   }
	//   #ztree{
	//       width: 200px;
	//   }
	//   #orgTree ol>li.active{
	//       width:100%;
	//   }
	//   #orgTree ol>li.active em{
	//       font-style:normal;
	//       font-weight:bold;
	//   }
	//   #orgTree ol span.glyphicon{
	//       top: 3px;
	//   }
	//   #ztree li {
	//       margin:0;
	//       position:relative
	//   }
	//   #ztree li::before, #ztree li::after {
	//       content:'';
	//       left:-30px;
	//       position:absolute;
	//       right:auto
	//   }
	//   #ztree li::before {
	//       border-left:1px solid #999;
	//       bottom:50px;
	//       height:100%;
	//       top:-16px;
	//       width:1px
	//   }
	//   #ztree li::after {
	//       border-top:1px solid #999;
	//       height:20px;
	//       top:13px;
	//       width:30px
	//   }
	//   #ztree>li::before,#ztree>li.i_tree_hidden::after{
	//       border:0
	//   }
	//   #ztree>li::after{
	//       width:12px;
	//       left:-12px;
	//   }
	//   #ztree li a{
	//       padding: 1px 3px 0 0;
	//           height: 30px;
	//       background-color: transparent;
	//           border: none;
	//   }
	//   #ztree a>.button,#ztree span.switch{
	//       display: none;
	//   }
	//   #ztree li a>span{
	//       -moz-border-radius: 5px;
	//       -webkit-border-radius: 5px;
	//       border: 1px solid #999;
	//       border-radius: 5px;
	//       display: inline-block;
	//       padding: 3px 8px;
	//       text-decoration: none;
	//   }
	//   #ztree li a>span:hover,#ztree li>a.curSelectedNode>span{
	//       background-color: #33CCFF;
	//       color: #fff;
	//       border: 1px solid #33CCDD;
	//   }
	//   #ztree i.glyphicon{
	//       font-family: 'Glyphicons Halflings';
	//           margin-right: 5px;
	//   }
	// </style>

/***/ },
/* 181 */
/***/ function(module, exports, __webpack_require__) {

	"use strict";var _typeof2=__webpack_require__(81);var _typeof3=_interopRequireDefault(_typeof2);function _interopRequireDefault(obj){return obj&&obj.__esModule?obj:{default:obj};}/*
	 * JQuery zTree core v3.5.24
	 * http://zTree.me/
	 *
	 * Copyright (c) 2010 Hunter.z
	 *
	 * Licensed same as jquery - MIT License
	 * http://www.opensource.org/licenses/mit-license.php
	 *
	 * email: hunter.z@263.net
	 * Date: 2016-06-06
	 */(function(_$){var settings={},roots={},caches={},//default consts of core
	_consts={className:{BUTTON:"button",LEVEL:"level",ICO_LOADING:"ico_loading",SWITCH:"switch",NAME:'node_name'},event:{NODECREATED:"ztree_nodeCreated",CLICK:"ztree_click",EXPAND:"ztree_expand",COLLAPSE:"ztree_collapse",ASYNC_SUCCESS:"ztree_async_success",ASYNC_ERROR:"ztree_async_error",REMOVE:"ztree_remove",SELECTED:"ztree_selected",UNSELECTED:"ztree_unselected"},id:{A:"_a",ICON:"_ico",SPAN:"_span",SWITCH:"_switch",UL:"_ul"},line:{ROOT:"root",ROOTS:"roots",CENTER:"center",BOTTOM:"bottom",NOLINE:"noline",LINE:"line"},folder:{OPEN:"open",CLOSE:"close",DOCU:"docu"},node:{CURSELECTED:"curSelectedNode"}},//default setting of core
	_setting={treeId:"",treeObj:null,view:{addDiyDom:null,autoCancelSelected:true,dblClickExpand:true,expandSpeed:"fast",fontCss:{},nameIsHTML:false,selectedMulti:true,showIcon:true,showLine:true,showTitle:true,txtSelectedEnable:false},data:{key:{children:"children",name:"name",title:"",url:"url",icon:"icon"},simpleData:{enable:false,idKey:"id",pIdKey:"pId",rootPId:null},keep:{parent:false,leaf:false}},async:{enable:false,contentType:"application/x-www-form-urlencoded",type:"post",dataType:"text",url:"",autoParam:[],otherParam:[],dataFilter:null},callback:{beforeAsync:null,beforeClick:null,beforeDblClick:null,beforeRightClick:null,beforeMouseDown:null,beforeMouseUp:null,beforeExpand:null,beforeCollapse:null,beforeRemove:null,onAsyncError:null,onAsyncSuccess:null,onNodeCreated:null,onClick:null,onDblClick:null,onRightClick:null,onMouseDown:null,onMouseUp:null,onExpand:null,onCollapse:null,onRemove:null}},//default root of core
	//zTree use root to save full data
	_initRoot=function _initRoot(setting){var r=data.getRoot(setting);if(!r){r={};data.setRoot(setting,r);}r[setting.data.key.children]=[];r.expandTriggerFlag=false;r.curSelectedList=[];r.noSelection=true;r.createdNodes=[];r.zId=0;r._ver=new Date().getTime();},//default cache of core
	_initCache=function _initCache(setting){var c=data.getCache(setting);if(!c){c={};data.setCache(setting,c);}c.nodes=[];c.doms=[];},//default bindEvent of core
	_bindEvent=function _bindEvent(setting){var o=setting.treeObj,c=consts.event;o.bind(c.NODECREATED,function(event,treeId,node){tools.apply(setting.callback.onNodeCreated,[event,treeId,node]);});o.bind(c.CLICK,function(event,srcEvent,treeId,node,clickFlag){tools.apply(setting.callback.onClick,[srcEvent,treeId,node,clickFlag]);});o.bind(c.EXPAND,function(event,treeId,node){tools.apply(setting.callback.onExpand,[event,treeId,node]);});o.bind(c.COLLAPSE,function(event,treeId,node){tools.apply(setting.callback.onCollapse,[event,treeId,node]);});o.bind(c.ASYNC_SUCCESS,function(event,treeId,node,msg){tools.apply(setting.callback.onAsyncSuccess,[event,treeId,node,msg]);});o.bind(c.ASYNC_ERROR,function(event,treeId,node,XMLHttpRequest,textStatus,errorThrown){tools.apply(setting.callback.onAsyncError,[event,treeId,node,XMLHttpRequest,textStatus,errorThrown]);});o.bind(c.REMOVE,function(event,treeId,treeNode){tools.apply(setting.callback.onRemove,[event,treeId,treeNode]);});o.bind(c.SELECTED,function(event,treeId,node){tools.apply(setting.callback.onSelected,[treeId,node]);});o.bind(c.UNSELECTED,function(event,treeId,node){tools.apply(setting.callback.onUnSelected,[treeId,node]);});},_unbindEvent=function _unbindEvent(setting){var o=setting.treeObj,c=consts.event;o.unbind(c.NODECREATED).unbind(c.CLICK).unbind(c.EXPAND).unbind(c.COLLAPSE).unbind(c.ASYNC_SUCCESS).unbind(c.ASYNC_ERROR).unbind(c.REMOVE).unbind(c.SELECTED).unbind(c.UNSELECTED);},//default event proxy of core
	_eventProxy=function _eventProxy(event){var target=event.target,setting=data.getSetting(event.data.treeId),tId="",node=null,nodeEventType="",treeEventType="",nodeEventCallback=null,treeEventCallback=null,tmp=null;if(tools.eqs(event.type,"mousedown")){treeEventType="mousedown";}else if(tools.eqs(event.type,"mouseup")){treeEventType="mouseup";}else if(tools.eqs(event.type,"contextmenu")){treeEventType="contextmenu";}else if(tools.eqs(event.type,"click")){if(tools.eqs(target.tagName,"span")&&target.getAttribute("treeNode"+consts.id.SWITCH)!==null){tId=tools.getNodeMainDom(target).id;nodeEventType="switchNode";}else{tmp=tools.getMDom(setting,target,[{tagName:"a",attrName:"treeNode"+consts.id.A}]);if(tmp){tId=tools.getNodeMainDom(tmp).id;nodeEventType="clickNode";}}}else if(tools.eqs(event.type,"dblclick")){treeEventType="dblclick";tmp=tools.getMDom(setting,target,[{tagName:"a",attrName:"treeNode"+consts.id.A}]);if(tmp){tId=tools.getNodeMainDom(tmp).id;nodeEventType="switchNode";}}if(treeEventType.length>0&&tId.length==0){tmp=tools.getMDom(setting,target,[{tagName:"a",attrName:"treeNode"+consts.id.A}]);if(tmp){tId=tools.getNodeMainDom(tmp).id;}}// event to node
	if(tId.length>0){node=data.getNodeCache(setting,tId);switch(nodeEventType){case"switchNode":if(!node.isParent){nodeEventType="";}else if(tools.eqs(event.type,"click")||tools.eqs(event.type,"dblclick")&&tools.apply(setting.view.dblClickExpand,[setting.treeId,node],setting.view.dblClickExpand)){nodeEventCallback=handler.onSwitchNode;}else{nodeEventType="";}break;case"clickNode":nodeEventCallback=handler.onClickNode;break;}}// event to zTree
	switch(treeEventType){case"mousedown":treeEventCallback=handler.onZTreeMousedown;break;case"mouseup":treeEventCallback=handler.onZTreeMouseup;break;case"dblclick":treeEventCallback=handler.onZTreeDblclick;break;case"contextmenu":treeEventCallback=handler.onZTreeContextmenu;break;}var proxyResult={stop:false,node:node,nodeEventType:nodeEventType,nodeEventCallback:nodeEventCallback,treeEventType:treeEventType,treeEventCallback:treeEventCallback};return proxyResult;},//default init node of core
	_initNode=function _initNode(setting,level,n,parentNode,isFirstNode,isLastNode,openFlag){if(!n)return;var r=data.getRoot(setting),childKey=setting.data.key.children;n.level=level;n.tId=setting.treeId+"_"+ ++r.zId;n.parentTId=parentNode?parentNode.tId:null;n.open=typeof n.open=="string"?tools.eqs(n.open,"true"):!!n.open;if(n[childKey]&&n[childKey].length>0){n.isParent=true;n.zAsync=true;}else{n.isParent=typeof n.isParent=="string"?tools.eqs(n.isParent,"true"):!!n.isParent;n.open=n.isParent&&!setting.async.enable?n.open:false;n.zAsync=!n.isParent;}n.isFirstNode=isFirstNode;n.isLastNode=isLastNode;n.getParentNode=function(){return data.getNodeCache(setting,n.parentTId);};n.getPreNode=function(){return data.getPreNode(setting,n);};n.getNextNode=function(){return data.getNextNode(setting,n);};n.getIndex=function(){return data.getNodeIndex(setting,n);};n.getPath=function(){return data.getNodePath(setting,n);};n.isAjaxing=false;data.fixPIdKeyValue(setting,n);},_init={bind:[_bindEvent],unbind:[_unbindEvent],caches:[_initCache],nodes:[_initNode],proxys:[_eventProxy],roots:[_initRoot],beforeA:[],afterA:[],innerBeforeA:[],innerAfterA:[],zTreeTools:[]},//method of operate data
	data={addNodeCache:function addNodeCache(setting,node){data.getCache(setting).nodes[data.getNodeCacheId(node.tId)]=node;},getNodeCacheId:function getNodeCacheId(tId){return tId.substring(tId.lastIndexOf("_")+1);},addAfterA:function addAfterA(afterA){_init.afterA.push(afterA);},addBeforeA:function addBeforeA(beforeA){_init.beforeA.push(beforeA);},addInnerAfterA:function addInnerAfterA(innerAfterA){_init.innerAfterA.push(innerAfterA);},addInnerBeforeA:function addInnerBeforeA(innerBeforeA){_init.innerBeforeA.push(innerBeforeA);},addInitBind:function addInitBind(bindEvent){_init.bind.push(bindEvent);},addInitUnBind:function addInitUnBind(unbindEvent){_init.unbind.push(unbindEvent);},addInitCache:function addInitCache(initCache){_init.caches.push(initCache);},addInitNode:function addInitNode(initNode){_init.nodes.push(initNode);},addInitProxy:function addInitProxy(initProxy,isFirst){if(!!isFirst){_init.proxys.splice(0,0,initProxy);}else{_init.proxys.push(initProxy);}},addInitRoot:function addInitRoot(initRoot){_init.roots.push(initRoot);},addNodesData:function addNodesData(setting,parentNode,index,nodes){var childKey=setting.data.key.children,params;if(!parentNode[childKey]){parentNode[childKey]=[];index=-1;}else if(index>=parentNode[childKey].length){index=-1;}if(parentNode[childKey].length>0&&index===0){parentNode[childKey][0].isFirstNode=false;view.setNodeLineIcos(setting,parentNode[childKey][0]);}else if(parentNode[childKey].length>0&&index<0){parentNode[childKey][parentNode[childKey].length-1].isLastNode=false;view.setNodeLineIcos(setting,parentNode[childKey][parentNode[childKey].length-1]);}parentNode.isParent=true;if(index<0){parentNode[childKey]=parentNode[childKey].concat(nodes);}else{params=[index,0].concat(nodes);parentNode[childKey].splice.apply(parentNode[childKey],params);}},addSelectedNode:function addSelectedNode(setting,node){var root=data.getRoot(setting);if(!data.isSelectedNode(setting,node)){root.curSelectedList.push(node);}},addCreatedNode:function addCreatedNode(setting,node){if(!!setting.callback.onNodeCreated||!!setting.view.addDiyDom){var root=data.getRoot(setting);root.createdNodes.push(node);}},addZTreeTools:function addZTreeTools(zTreeTools){_init.zTreeTools.push(zTreeTools);},exSetting:function exSetting(s){_$.extend(true,_setting,s);},fixPIdKeyValue:function fixPIdKeyValue(setting,node){if(setting.data.simpleData.enable){node[setting.data.simpleData.pIdKey]=node.parentTId?node.getParentNode()[setting.data.simpleData.idKey]:setting.data.simpleData.rootPId;}},getAfterA:function getAfterA(setting,node,array){for(var i=0,j=_init.afterA.length;i<j;i++){_init.afterA[i].apply(this,arguments);}},getBeforeA:function getBeforeA(setting,node,array){for(var i=0,j=_init.beforeA.length;i<j;i++){_init.beforeA[i].apply(this,arguments);}},getInnerAfterA:function getInnerAfterA(setting,node,array){for(var i=0,j=_init.innerAfterA.length;i<j;i++){_init.innerAfterA[i].apply(this,arguments);}},getInnerBeforeA:function getInnerBeforeA(setting,node,array){for(var i=0,j=_init.innerBeforeA.length;i<j;i++){_init.innerBeforeA[i].apply(this,arguments);}},getCache:function getCache(setting){return caches[setting.treeId];},getNodeIndex:function getNodeIndex(setting,node){if(!node)return null;var childKey=setting.data.key.children,p=node.parentTId?node.getParentNode():data.getRoot(setting);for(var i=0,l=p[childKey].length-1;i<=l;i++){if(p[childKey][i]===node){return i;}}return-1;},getNextNode:function getNextNode(setting,node){if(!node)return null;var childKey=setting.data.key.children,p=node.parentTId?node.getParentNode():data.getRoot(setting);for(var i=0,l=p[childKey].length-1;i<=l;i++){if(p[childKey][i]===node){return i==l?null:p[childKey][i+1];}}return null;},getNodeByParam:function getNodeByParam(setting,nodes,key,value){if(!nodes||!key)return null;var childKey=setting.data.key.children;for(var i=0,l=nodes.length;i<l;i++){if(nodes[i][key]==value){return nodes[i];}var tmp=data.getNodeByParam(setting,nodes[i][childKey],key,value);if(tmp)return tmp;}return null;},getNodeCache:function getNodeCache(setting,tId){if(!tId)return null;var n=caches[setting.treeId].nodes[data.getNodeCacheId(tId)];return n?n:null;},getNodeName:function getNodeName(setting,node){var nameKey=setting.data.key.name;return""+node[nameKey];},getNodePath:function getNodePath(setting,node){if(!node)return null;var path;if(node.parentTId){path=node.getParentNode().getPath();}else{path=[];}if(path){path.push(node);}return path;},getNodeTitle:function getNodeTitle(setting,node){var t=setting.data.key.title===""?setting.data.key.name:setting.data.key.title;return""+node[t];},getNodes:function getNodes(setting){return data.getRoot(setting)[setting.data.key.children];},getNodesByParam:function getNodesByParam(setting,nodes,key,value){if(!nodes||!key)return[];var childKey=setting.data.key.children,result=[];for(var i=0,l=nodes.length;i<l;i++){if(nodes[i][key]==value){result.push(nodes[i]);}result=result.concat(data.getNodesByParam(setting,nodes[i][childKey],key,value));}return result;},getNodesByParamFuzzy:function getNodesByParamFuzzy(setting,nodes,key,value){if(!nodes||!key)return[];var childKey=setting.data.key.children,result=[];value=value.toLowerCase();for(var i=0,l=nodes.length;i<l;i++){if(typeof nodes[i][key]=="string"&&nodes[i][key].toLowerCase().indexOf(value)>-1){result.push(nodes[i]);}result=result.concat(data.getNodesByParamFuzzy(setting,nodes[i][childKey],key,value));}return result;},getNodesByFilter:function getNodesByFilter(setting,nodes,filter,isSingle,invokeParam){if(!nodes)return isSingle?null:[];var childKey=setting.data.key.children,result=isSingle?null:[];for(var i=0,l=nodes.length;i<l;i++){if(tools.apply(filter,[nodes[i],invokeParam],false)){if(isSingle){return nodes[i];}result.push(nodes[i]);}var tmpResult=data.getNodesByFilter(setting,nodes[i][childKey],filter,isSingle,invokeParam);if(isSingle&&!!tmpResult){return tmpResult;}result=isSingle?tmpResult:result.concat(tmpResult);}return result;},getPreNode:function getPreNode(setting,node){if(!node)return null;var childKey=setting.data.key.children,p=node.parentTId?node.getParentNode():data.getRoot(setting);for(var i=0,l=p[childKey].length;i<l;i++){if(p[childKey][i]===node){return i==0?null:p[childKey][i-1];}}return null;},getRoot:function getRoot(setting){return setting?roots[setting.treeId]:null;},getRoots:function getRoots(){return roots;},getSetting:function getSetting(treeId){return settings[treeId];},getSettings:function getSettings(){return settings;},getZTreeTools:function getZTreeTools(treeId){var r=this.getRoot(this.getSetting(treeId));return r?r.treeTools:null;},initCache:function initCache(setting){for(var i=0,j=_init.caches.length;i<j;i++){_init.caches[i].apply(this,arguments);}},initNode:function initNode(setting,level,node,parentNode,preNode,nextNode){for(var i=0,j=_init.nodes.length;i<j;i++){_init.nodes[i].apply(this,arguments);}},initRoot:function initRoot(setting){for(var i=0,j=_init.roots.length;i<j;i++){_init.roots[i].apply(this,arguments);}},isSelectedNode:function isSelectedNode(setting,node){var root=data.getRoot(setting);for(var i=0,j=root.curSelectedList.length;i<j;i++){if(node===root.curSelectedList[i])return true;}return false;},removeNodeCache:function removeNodeCache(setting,node){var childKey=setting.data.key.children;if(node[childKey]){for(var i=0,l=node[childKey].length;i<l;i++){data.removeNodeCache(setting,node[childKey][i]);}}data.getCache(setting).nodes[data.getNodeCacheId(node.tId)]=null;},removeSelectedNode:function removeSelectedNode(setting,node){var root=data.getRoot(setting);for(var i=0,j=root.curSelectedList.length;i<j;i++){if(node===root.curSelectedList[i]||!data.getNodeCache(setting,root.curSelectedList[i].tId)){root.curSelectedList.splice(i,1);setting.treeObj.trigger(consts.event.UNSELECTED,[setting.treeId,node]);i--;j--;}}},setCache:function setCache(setting,cache){caches[setting.treeId]=cache;},setRoot:function setRoot(setting,root){roots[setting.treeId]=root;},setZTreeTools:function setZTreeTools(setting,zTreeTools){for(var i=0,j=_init.zTreeTools.length;i<j;i++){_init.zTreeTools[i].apply(this,arguments);}},transformToArrayFormat:function transformToArrayFormat(setting,nodes){if(!nodes)return[];var childKey=setting.data.key.children,r=[];if(tools.isArray(nodes)){for(var i=0,l=nodes.length;i<l;i++){r.push(nodes[i]);if(nodes[i][childKey])r=r.concat(data.transformToArrayFormat(setting,nodes[i][childKey]));}}else{r.push(nodes);if(nodes[childKey])r=r.concat(data.transformToArrayFormat(setting,nodes[childKey]));}return r;},transformTozTreeFormat:function transformTozTreeFormat(setting,sNodes){var i,l,key=setting.data.simpleData.idKey,parentKey=setting.data.simpleData.pIdKey,childKey=setting.data.key.children;if(!key||key==""||!sNodes)return[];if(tools.isArray(sNodes)){var r=[];var tmpMap=[];for(i=0,l=sNodes.length;i<l;i++){tmpMap[sNodes[i][key]]=sNodes[i];}for(i=0,l=sNodes.length;i<l;i++){if(tmpMap[sNodes[i][parentKey]]&&sNodes[i][key]!=sNodes[i][parentKey]){if(!tmpMap[sNodes[i][parentKey]][childKey])tmpMap[sNodes[i][parentKey]][childKey]=[];tmpMap[sNodes[i][parentKey]][childKey].push(sNodes[i]);}else{r.push(sNodes[i]);}}return r;}else{return[sNodes];}}},//method of event proxy
	event={bindEvent:function bindEvent(setting){for(var i=0,j=_init.bind.length;i<j;i++){_init.bind[i].apply(this,arguments);}},unbindEvent:function unbindEvent(setting){for(var i=0,j=_init.unbind.length;i<j;i++){_init.unbind[i].apply(this,arguments);}},bindTree:function bindTree(setting){var eventParam={treeId:setting.treeId},o=setting.treeObj;if(!setting.view.txtSelectedEnable){// for can't select text
	o.bind('selectstart',handler.onSelectStart).css({"-moz-user-select":"-moz-none"});}o.bind('click',eventParam,event.proxy);o.bind('dblclick',eventParam,event.proxy);o.bind('mouseover',eventParam,event.proxy);o.bind('mouseout',eventParam,event.proxy);o.bind('mousedown',eventParam,event.proxy);o.bind('mouseup',eventParam,event.proxy);o.bind('contextmenu',eventParam,event.proxy);},unbindTree:function unbindTree(setting){var o=setting.treeObj;o.unbind('selectstart',handler.onSelectStart).unbind('click',event.proxy).unbind('dblclick',event.proxy).unbind('mouseover',event.proxy).unbind('mouseout',event.proxy).unbind('mousedown',event.proxy).unbind('mouseup',event.proxy).unbind('contextmenu',event.proxy);},doProxy:function doProxy(e){var results=[];for(var i=0,j=_init.proxys.length;i<j;i++){var proxyResult=_init.proxys[i].apply(this,arguments);results.push(proxyResult);if(proxyResult.stop){break;}}return results;},proxy:function proxy(e){var setting=data.getSetting(e.data.treeId);if(!tools.uCanDo(setting,e))return true;var results=event.doProxy(e),r=true,x=false;for(var i=0,l=results.length;i<l;i++){var proxyResult=results[i];if(proxyResult.nodeEventCallback){x=true;r=proxyResult.nodeEventCallback.apply(proxyResult,[e,proxyResult.node])&&r;}if(proxyResult.treeEventCallback){x=true;r=proxyResult.treeEventCallback.apply(proxyResult,[e,proxyResult.node])&&r;}}return r;}},//method of event handler
	handler={onSwitchNode:function onSwitchNode(event,node){var setting=data.getSetting(event.data.treeId);if(node.open){if(tools.apply(setting.callback.beforeCollapse,[setting.treeId,node],true)==false)return true;data.getRoot(setting).expandTriggerFlag=true;view.switchNode(setting,node);}else{if(tools.apply(setting.callback.beforeExpand,[setting.treeId,node],true)==false)return true;data.getRoot(setting).expandTriggerFlag=true;view.switchNode(setting,node);}return true;},onClickNode:function onClickNode(event,node){var setting=data.getSetting(event.data.treeId),clickFlag=setting.view.autoCancelSelected&&(event.ctrlKey||event.metaKey)&&data.isSelectedNode(setting,node)?0:setting.view.autoCancelSelected&&(event.ctrlKey||event.metaKey)&&setting.view.selectedMulti?2:1;if(tools.apply(setting.callback.beforeClick,[setting.treeId,node,clickFlag],true)==false)return true;if(clickFlag===0){view.cancelPreSelectedNode(setting,node);}else{view.selectNode(setting,node,clickFlag===2);}setting.treeObj.trigger(consts.event.CLICK,[event,setting.treeId,node,clickFlag]);return true;},onZTreeMousedown:function onZTreeMousedown(event,node){var setting=data.getSetting(event.data.treeId);if(tools.apply(setting.callback.beforeMouseDown,[setting.treeId,node],true)){tools.apply(setting.callback.onMouseDown,[event,setting.treeId,node]);}return true;},onZTreeMouseup:function onZTreeMouseup(event,node){var setting=data.getSetting(event.data.treeId);if(tools.apply(setting.callback.beforeMouseUp,[setting.treeId,node],true)){tools.apply(setting.callback.onMouseUp,[event,setting.treeId,node]);}return true;},onZTreeDblclick:function onZTreeDblclick(event,node){var setting=data.getSetting(event.data.treeId);if(tools.apply(setting.callback.beforeDblClick,[setting.treeId,node],true)){tools.apply(setting.callback.onDblClick,[event,setting.treeId,node]);}return true;},onZTreeContextmenu:function onZTreeContextmenu(event,node){var setting=data.getSetting(event.data.treeId);if(tools.apply(setting.callback.beforeRightClick,[setting.treeId,node],true)){tools.apply(setting.callback.onRightClick,[event,setting.treeId,node]);}return typeof setting.callback.onRightClick!="function";},onSelectStart:function onSelectStart(e){var n=e.originalEvent.srcElement.nodeName.toLowerCase();return n==="input"||n==="textarea";}},//method of tools for zTree
	tools={apply:function apply(fun,param,defaultValue){if(typeof fun=="function"){return fun.apply(zt,param?param:[]);}return defaultValue;},canAsync:function canAsync(setting,node){var childKey=setting.data.key.children;return setting.async.enable&&node&&node.isParent&&!(node.zAsync||node[childKey]&&node[childKey].length>0);},clone:function clone(obj){if(obj===null)return null;var o=tools.isArray(obj)?[]:{};for(var i in obj){o[i]=obj[i]instanceof Date?new Date(obj[i].getTime()):(0,_typeof3.default)(obj[i])==="object"?tools.clone(obj[i]):obj[i];}return o;},eqs:function eqs(str1,str2){return str1.toLowerCase()===str2.toLowerCase();},isArray:function isArray(arr){return Object.prototype.toString.apply(arr)==="[object Array]";},$:function $(node,exp,setting){if(!!exp&&typeof exp!="string"){setting=exp;exp="";}if(typeof node=="string"){return _$(node,setting?setting.treeObj.get(0).ownerDocument:null);}else{return _$("#"+node.tId+exp,setting?setting.treeObj:null);}},getMDom:function getMDom(setting,curDom,targetExpr){if(!curDom)return null;while(curDom&&curDom.id!==setting.treeId){for(var i=0,l=targetExpr.length;curDom.tagName&&i<l;i++){if(tools.eqs(curDom.tagName,targetExpr[i].tagName)&&curDom.getAttribute(targetExpr[i].attrName)!==null){return curDom;}}curDom=curDom.parentNode;}return null;},getNodeMainDom:function getNodeMainDom(target){return _$(target).parent("li").get(0)||_$(target).parentsUntil("li").parent().get(0);},isChildOrSelf:function isChildOrSelf(dom,parentId){return _$(dom).closest("#"+parentId).length>0;},uCanDo:function uCanDo(setting,e){return true;}},//method of operate ztree dom
	view={addNodes:function addNodes(setting,parentNode,index,newNodes,isSilent){if(setting.data.keep.leaf&&parentNode&&!parentNode.isParent){return;}if(!tools.isArray(newNodes)){newNodes=[newNodes];}if(setting.data.simpleData.enable){newNodes=data.transformTozTreeFormat(setting,newNodes);}if(parentNode){var target_switchObj=$$(parentNode,consts.id.SWITCH,setting),target_icoObj=$$(parentNode,consts.id.ICON,setting),target_ulObj=$$(parentNode,consts.id.UL,setting);if(!parentNode.open){view.replaceSwitchClass(parentNode,target_switchObj,consts.folder.CLOSE);view.replaceIcoClass(parentNode,target_icoObj,consts.folder.CLOSE);parentNode.open=false;target_ulObj.css({"display":"none"});}data.addNodesData(setting,parentNode,index,newNodes);view.createNodes(setting,parentNode.level+1,newNodes,parentNode,index);if(!isSilent){view.expandCollapseParentNode(setting,parentNode,true);}}else{data.addNodesData(setting,data.getRoot(setting),index,newNodes);view.createNodes(setting,0,newNodes,null,index);}},appendNodes:function appendNodes(setting,level,nodes,parentNode,index,initFlag,openFlag){if(!nodes)return[];var html=[],childKey=setting.data.key.children;var tmpPNode=parentNode?parentNode:data.getRoot(setting),tmpPChild=tmpPNode[childKey],isFirstNode,isLastNode;if(!tmpPChild||index>=tmpPChild.length){index=-1;}for(var i=0,l=nodes.length;i<l;i++){var node=nodes[i];if(initFlag){isFirstNode=(index===0||tmpPChild.length==nodes.length)&&i==0;isLastNode=index<0&&i==nodes.length-1;data.initNode(setting,level,node,parentNode,isFirstNode,isLastNode,openFlag);data.addNodeCache(setting,node);}var childHtml=[];if(node[childKey]&&node[childKey].length>0){//make child html first, because checkType
	childHtml=view.appendNodes(setting,level+1,node[childKey],node,-1,initFlag,openFlag&&node.open);}if(openFlag){view.makeDOMNodeMainBefore(html,setting,node);view.makeDOMNodeLine(html,setting,node);data.getBeforeA(setting,node,html);view.makeDOMNodeNameBefore(html,setting,node);data.getInnerBeforeA(setting,node,html);view.makeDOMNodeIcon(html,setting,node);data.getInnerAfterA(setting,node,html);view.makeDOMNodeNameAfter(html,setting,node);data.getAfterA(setting,node,html);if(node.isParent&&node.open){view.makeUlHtml(setting,node,html,childHtml.join(''));}view.makeDOMNodeMainAfter(html,setting,node);data.addCreatedNode(setting,node);}}return html;},appendParentULDom:function appendParentULDom(setting,node){var html=[],nObj=$$(node,setting);if(!nObj.get(0)&&!!node.parentTId){view.appendParentULDom(setting,node.getParentNode());nObj=$$(node,setting);}var ulObj=$$(node,consts.id.UL,setting);if(ulObj.get(0)){ulObj.remove();}var childKey=setting.data.key.children,childHtml=view.appendNodes(setting,node.level+1,node[childKey],node,-1,false,true);view.makeUlHtml(setting,node,html,childHtml.join(''));nObj.append(html.join(''));},asyncNode:function asyncNode(setting,node,isSilent,callback){var i,l;if(node&&!node.isParent){tools.apply(callback);return false;}else if(node&&node.isAjaxing){return false;}else if(tools.apply(setting.callback.beforeAsync,[setting.treeId,node],true)==false){tools.apply(callback);return false;}if(node){node.isAjaxing=true;var icoObj=$$(node,consts.id.ICON,setting);icoObj.attr({"style":"","class":consts.className.BUTTON+" "+consts.className.ICO_LOADING});}var tmpParam={};for(i=0,l=setting.async.autoParam.length;node&&i<l;i++){var pKey=setting.async.autoParam[i].split("="),spKey=pKey;if(pKey.length>1){spKey=pKey[1];pKey=pKey[0];}tmpParam[spKey]=node[pKey];}if(tools.isArray(setting.async.otherParam)){for(i=0,l=setting.async.otherParam.length;i<l;i+=2){tmpParam[setting.async.otherParam[i]]=setting.async.otherParam[i+1];}}else{for(var p in setting.async.otherParam){tmpParam[p]=setting.async.otherParam[p];}}var _tmpV=data.getRoot(setting)._ver;_$.ajax({contentType:setting.async.contentType,cache:false,type:setting.async.type,url:tools.apply(setting.async.url,[setting.treeId,node],setting.async.url),data:tmpParam,dataType:setting.async.dataType,success:function success(msg){if(_tmpV!=data.getRoot(setting)._ver){return;}var newNodes=[];try{if(!msg||msg.length==0){newNodes=[];}else if(typeof msg=="string"){newNodes=eval("("+msg+")");}else{newNodes=msg;}}catch(err){newNodes=msg;}if(node){node.isAjaxing=null;node.zAsync=true;}view.setNodeLineIcos(setting,node);if(newNodes&&newNodes!==""){newNodes=tools.apply(setting.async.dataFilter,[setting.treeId,node,newNodes],newNodes);view.addNodes(setting,node,-1,!!newNodes?tools.clone(newNodes):[],!!isSilent);}else{view.addNodes(setting,node,-1,[],!!isSilent);}setting.treeObj.trigger(consts.event.ASYNC_SUCCESS,[setting.treeId,node,msg]);tools.apply(callback);},error:function error(XMLHttpRequest,textStatus,errorThrown){if(_tmpV!=data.getRoot(setting)._ver){return;}if(node)node.isAjaxing=null;view.setNodeLineIcos(setting,node);setting.treeObj.trigger(consts.event.ASYNC_ERROR,[setting.treeId,node,XMLHttpRequest,textStatus,errorThrown]);}});return true;},cancelPreSelectedNode:function cancelPreSelectedNode(setting,node,excludeNode){var list=data.getRoot(setting).curSelectedList,i,n;for(i=list.length-1;i>=0;i--){n=list[i];if(node===n||!node&&(!excludeNode||excludeNode!==n)){$$(n,consts.id.A,setting).removeClass(consts.node.CURSELECTED);if(node){data.removeSelectedNode(setting,node);break;}else{list.splice(i,1);setting.treeObj.trigger(consts.event.UNSELECTED,[setting.treeId,n]);}}}},createNodeCallback:function createNodeCallback(setting){if(!!setting.callback.onNodeCreated||!!setting.view.addDiyDom){var root=data.getRoot(setting);while(root.createdNodes.length>0){var node=root.createdNodes.shift();tools.apply(setting.view.addDiyDom,[setting.treeId,node]);if(!!setting.callback.onNodeCreated){setting.treeObj.trigger(consts.event.NODECREATED,[setting.treeId,node]);}}}},createNodes:function createNodes(setting,level,nodes,parentNode,index){if(!nodes||nodes.length==0)return;var root=data.getRoot(setting),childKey=setting.data.key.children,openFlag=!parentNode||parentNode.open||!!$$(parentNode[childKey][0],setting).get(0);root.createdNodes=[];var zTreeHtml=view.appendNodes(setting,level,nodes,parentNode,index,true,openFlag),parentObj,nextObj;if(!parentNode){parentObj=setting.treeObj;//setting.treeObj.append(zTreeHtml.join(''));
	}else{var ulObj=$$(parentNode,consts.id.UL,setting);if(ulObj.get(0)){parentObj=ulObj;//ulObj.append(zTreeHtml.join(''));
	}}if(parentObj){if(index>=0){nextObj=parentObj.children()[index];}if(index>=0&&nextObj){_$(nextObj).before(zTreeHtml.join(''));}else{parentObj.append(zTreeHtml.join(''));}}view.createNodeCallback(setting);},destroy:function destroy(setting){if(!setting)return;data.initCache(setting);data.initRoot(setting);event.unbindTree(setting);event.unbindEvent(setting);setting.treeObj.empty();delete settings[setting.treeId];},expandCollapseNode:function expandCollapseNode(setting,node,expandFlag,animateFlag,callback){var root=data.getRoot(setting),childKey=setting.data.key.children;var tmpCb,_callback;if(!node){tools.apply(callback,[]);return;}if(root.expandTriggerFlag){_callback=callback;tmpCb=function tmpCb(){if(_callback)_callback();if(node.open){setting.treeObj.trigger(consts.event.EXPAND,[setting.treeId,node]);}else{setting.treeObj.trigger(consts.event.COLLAPSE,[setting.treeId,node]);}};callback=tmpCb;root.expandTriggerFlag=false;}if(!node.open&&node.isParent&&(!$$(node,consts.id.UL,setting).get(0)||node[childKey]&&node[childKey].length>0&&!$$(node[childKey][0],setting).get(0))){view.appendParentULDom(setting,node);view.createNodeCallback(setting);}if(node.open==expandFlag){tools.apply(callback,[]);return;}var ulObj=$$(node,consts.id.UL,setting),switchObj=$$(node,consts.id.SWITCH,setting),icoObj=$$(node,consts.id.ICON,setting);if(node.isParent){node.open=!node.open;if(node.iconOpen&&node.iconClose){icoObj.attr("style",view.makeNodeIcoStyle(setting,node));}if(node.open){view.replaceSwitchClass(node,switchObj,consts.folder.OPEN);view.replaceIcoClass(node,icoObj,consts.folder.OPEN);if(animateFlag==false||setting.view.expandSpeed==""){ulObj.show();tools.apply(callback,[]);}else{if(node[childKey]&&node[childKey].length>0){ulObj.slideDown(setting.view.expandSpeed,callback);}else{ulObj.show();tools.apply(callback,[]);}}}else{view.replaceSwitchClass(node,switchObj,consts.folder.CLOSE);view.replaceIcoClass(node,icoObj,consts.folder.CLOSE);if(animateFlag==false||setting.view.expandSpeed==""||!(node[childKey]&&node[childKey].length>0)){ulObj.hide();tools.apply(callback,[]);}else{ulObj.slideUp(setting.view.expandSpeed,callback);}}}else{tools.apply(callback,[]);}},expandCollapseParentNode:function expandCollapseParentNode(setting,node,expandFlag,animateFlag,callback){if(!node)return;if(!node.parentTId){view.expandCollapseNode(setting,node,expandFlag,animateFlag,callback);return;}else{view.expandCollapseNode(setting,node,expandFlag,animateFlag);}if(node.parentTId){view.expandCollapseParentNode(setting,node.getParentNode(),expandFlag,animateFlag,callback);}},expandCollapseSonNode:function expandCollapseSonNode(setting,node,expandFlag,animateFlag,callback){var root=data.getRoot(setting),childKey=setting.data.key.children,treeNodes=node?node[childKey]:root[childKey],selfAnimateSign=node?false:animateFlag,expandTriggerFlag=data.getRoot(setting).expandTriggerFlag;data.getRoot(setting).expandTriggerFlag=false;if(treeNodes){for(var i=0,l=treeNodes.length;i<l;i++){if(treeNodes[i])view.expandCollapseSonNode(setting,treeNodes[i],expandFlag,selfAnimateSign);}}data.getRoot(setting).expandTriggerFlag=expandTriggerFlag;view.expandCollapseNode(setting,node,expandFlag,animateFlag,callback);},isSelectedNode:function isSelectedNode(setting,node){if(!node){return false;}var list=data.getRoot(setting).curSelectedList,i;for(i=list.length-1;i>=0;i--){if(node===list[i]){return true;}}return false;},makeDOMNodeIcon:function makeDOMNodeIcon(html,setting,node){var nameStr=data.getNodeName(setting,node),name=setting.view.nameIsHTML?nameStr:nameStr.replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;');html.push("<span id='",node.tId,consts.id.ICON,"' title='' treeNode",consts.id.ICON," class='",view.makeNodeIcoClass(setting,node),"' style='",view.makeNodeIcoStyle(setting,node),"'></span><span id='",node.tId,consts.id.SPAN,"' class='",consts.className.NAME,"'>",name,"</span>");},makeDOMNodeLine:function makeDOMNodeLine(html,setting,node){html.push("<span id='",node.tId,consts.id.SWITCH,"' title='' class='",view.makeNodeLineClass(setting,node),"' treeNode",consts.id.SWITCH,"></span>");},makeDOMNodeMainAfter:function makeDOMNodeMainAfter(html,setting,node){html.push("</li>");},makeDOMNodeMainBefore:function makeDOMNodeMainBefore(html,setting,node){html.push("<li id='",node.tId,"' class='",consts.className.LEVEL,node.level,"' tabindex='0' hidefocus='true' treenode>");},makeDOMNodeNameAfter:function makeDOMNodeNameAfter(html,setting,node){html.push("</a>");},makeDOMNodeNameBefore:function makeDOMNodeNameBefore(html,setting,node){var title=data.getNodeTitle(setting,node),url=view.makeNodeUrl(setting,node),fontcss=view.makeNodeFontCss(setting,node),fontStyle=[];for(var f in fontcss){fontStyle.push(f,":",fontcss[f],";");}html.push("<a id='",node.tId,consts.id.A,"' class='",consts.className.LEVEL,node.level,"' treeNode",consts.id.A," onclick=\"",node.click||'',"\" ",url!=null&&url.length>0?"href='"+url+"'":""," target='",view.makeNodeTarget(node),"' style='",fontStyle.join(''),"'");if(tools.apply(setting.view.showTitle,[setting.treeId,node],setting.view.showTitle)&&title){html.push("title='",title.replace(/'/g,"&#39;").replace(/</g,'&lt;').replace(/>/g,'&gt;'),"'");}html.push(">");},makeNodeFontCss:function makeNodeFontCss(setting,node){var fontCss=tools.apply(setting.view.fontCss,[setting.treeId,node],setting.view.fontCss);return fontCss&&typeof fontCss!="function"?fontCss:{};},makeNodeIcoClass:function makeNodeIcoClass(setting,node){var icoCss=["ico"];if(!node.isAjaxing){icoCss[0]=(node.iconSkin?node.iconSkin+"_":"")+icoCss[0];if(node.isParent){icoCss.push(node.open?consts.folder.OPEN:consts.folder.CLOSE);}else{icoCss.push(consts.folder.DOCU);}}return consts.className.BUTTON+" "+icoCss.join('_');},makeNodeIcoStyle:function makeNodeIcoStyle(setting,node){var icoStyle=[];if(!node.isAjaxing){var icon=node.isParent&&node.iconOpen&&node.iconClose?node.open?node.iconOpen:node.iconClose:node[setting.data.key.icon];if(icon)icoStyle.push("background:url(",icon,") 0 0 no-repeat;");if(setting.view.showIcon==false||!tools.apply(setting.view.showIcon,[setting.treeId,node],true)){icoStyle.push("width:0px;height:0px;");}}return icoStyle.join('');},makeNodeLineClass:function makeNodeLineClass(setting,node){var lineClass=[];if(setting.view.showLine){if(node.level==0&&node.isFirstNode&&node.isLastNode){lineClass.push(consts.line.ROOT);}else if(node.level==0&&node.isFirstNode){lineClass.push(consts.line.ROOTS);}else if(node.isLastNode){lineClass.push(consts.line.BOTTOM);}else{lineClass.push(consts.line.CENTER);}}else{lineClass.push(consts.line.NOLINE);}if(node.isParent){lineClass.push(node.open?consts.folder.OPEN:consts.folder.CLOSE);}else{lineClass.push(consts.folder.DOCU);}return view.makeNodeLineClassEx(node)+lineClass.join('_');},makeNodeLineClassEx:function makeNodeLineClassEx(node){return consts.className.BUTTON+" "+consts.className.LEVEL+node.level+" "+consts.className.SWITCH+" ";},makeNodeTarget:function makeNodeTarget(node){return node.target||"_blank";},makeNodeUrl:function makeNodeUrl(setting,node){var urlKey=setting.data.key.url;return node[urlKey]?node[urlKey]:null;},makeUlHtml:function makeUlHtml(setting,node,html,content){html.push("<ul id='",node.tId,consts.id.UL,"' class='",consts.className.LEVEL,node.level," ",view.makeUlLineClass(setting,node),"' style='display:",node.open?"block":"none","'>");html.push(content);html.push("</ul>");},makeUlLineClass:function makeUlLineClass(setting,node){return setting.view.showLine&&!node.isLastNode?consts.line.LINE:"";},removeChildNodes:function removeChildNodes(setting,node){if(!node)return;var childKey=setting.data.key.children,nodes=node[childKey];if(!nodes)return;for(var i=0,l=nodes.length;i<l;i++){data.removeNodeCache(setting,nodes[i]);}data.removeSelectedNode(setting);delete node[childKey];if(!setting.data.keep.parent){node.isParent=false;node.open=false;var tmp_switchObj=$$(node,consts.id.SWITCH,setting),tmp_icoObj=$$(node,consts.id.ICON,setting);view.replaceSwitchClass(node,tmp_switchObj,consts.folder.DOCU);view.replaceIcoClass(node,tmp_icoObj,consts.folder.DOCU);$$(node,consts.id.UL,setting).remove();}else{$$(node,consts.id.UL,setting).empty();}},scrollIntoView:function scrollIntoView(dom){if(!dom){return;}if(dom.scrollIntoViewIfNeeded){dom.scrollIntoViewIfNeeded();}else if(dom.scrollIntoView){dom.scrollIntoView(false);}else{try{dom.focus().blur();}catch(e){}}},setFirstNode:function setFirstNode(setting,parentNode){var childKey=setting.data.key.children,childLength=parentNode[childKey].length;if(childLength>0){parentNode[childKey][0].isFirstNode=true;}},setLastNode:function setLastNode(setting,parentNode){var childKey=setting.data.key.children,childLength=parentNode[childKey].length;if(childLength>0){parentNode[childKey][childLength-1].isLastNode=true;}},removeNode:function removeNode(setting,node){var root=data.getRoot(setting),childKey=setting.data.key.children,parentNode=node.parentTId?node.getParentNode():root;node.isFirstNode=false;node.isLastNode=false;node.getPreNode=function(){return null;};node.getNextNode=function(){return null;};if(!data.getNodeCache(setting,node.tId)){return;}$$(node,setting).remove();data.removeNodeCache(setting,node);data.removeSelectedNode(setting,node);for(var i=0,l=parentNode[childKey].length;i<l;i++){if(parentNode[childKey][i].tId==node.tId){parentNode[childKey].splice(i,1);break;}}view.setFirstNode(setting,parentNode);view.setLastNode(setting,parentNode);var tmp_ulObj,tmp_switchObj,tmp_icoObj,childLength=parentNode[childKey].length;//repair nodes old parent
	if(!setting.data.keep.parent&&childLength==0){//old parentNode has no child nodes
	parentNode.isParent=false;parentNode.open=false;tmp_ulObj=$$(parentNode,consts.id.UL,setting);tmp_switchObj=$$(parentNode,consts.id.SWITCH,setting);tmp_icoObj=$$(parentNode,consts.id.ICON,setting);view.replaceSwitchClass(parentNode,tmp_switchObj,consts.folder.DOCU);view.replaceIcoClass(parentNode,tmp_icoObj,consts.folder.DOCU);tmp_ulObj.css("display","none");}else if(setting.view.showLine&&childLength>0){//old parentNode has child nodes
	var newLast=parentNode[childKey][childLength-1];tmp_ulObj=$$(newLast,consts.id.UL,setting);tmp_switchObj=$$(newLast,consts.id.SWITCH,setting);tmp_icoObj=$$(newLast,consts.id.ICON,setting);if(parentNode==root){if(parentNode[childKey].length==1){//node was root, and ztree has only one root after move node
	view.replaceSwitchClass(newLast,tmp_switchObj,consts.line.ROOT);}else{var tmp_first_switchObj=$$(parentNode[childKey][0],consts.id.SWITCH,setting);view.replaceSwitchClass(parentNode[childKey][0],tmp_first_switchObj,consts.line.ROOTS);view.replaceSwitchClass(newLast,tmp_switchObj,consts.line.BOTTOM);}}else{view.replaceSwitchClass(newLast,tmp_switchObj,consts.line.BOTTOM);}tmp_ulObj.removeClass(consts.line.LINE);}},replaceIcoClass:function replaceIcoClass(node,obj,newName){if(!obj||node.isAjaxing)return;var tmpName=obj.attr("class");if(tmpName==undefined)return;var tmpList=tmpName.split("_");switch(newName){case consts.folder.OPEN:case consts.folder.CLOSE:case consts.folder.DOCU:tmpList[tmpList.length-1]=newName;break;}obj.attr("class",tmpList.join("_"));},replaceSwitchClass:function replaceSwitchClass(node,obj,newName){if(!obj)return;var tmpName=obj.attr("class");if(tmpName==undefined)return;var tmpList=tmpName.split("_");switch(newName){case consts.line.ROOT:case consts.line.ROOTS:case consts.line.CENTER:case consts.line.BOTTOM:case consts.line.NOLINE:tmpList[0]=view.makeNodeLineClassEx(node)+newName;break;case consts.folder.OPEN:case consts.folder.CLOSE:case consts.folder.DOCU:tmpList[1]=newName;break;}obj.attr("class",tmpList.join("_"));if(newName!==consts.folder.DOCU){obj.removeAttr("disabled");}else{obj.attr("disabled","disabled");}},selectNode:function selectNode(setting,node,addFlag){if(!addFlag){view.cancelPreSelectedNode(setting,null,node);}$$(node,consts.id.A,setting).addClass(consts.node.CURSELECTED);data.addSelectedNode(setting,node);setting.treeObj.trigger(consts.event.SELECTED,[setting.treeId,node]);},setNodeFontCss:function setNodeFontCss(setting,treeNode){var aObj=$$(treeNode,consts.id.A,setting),fontCss=view.makeNodeFontCss(setting,treeNode);if(fontCss){aObj.css(fontCss);}},setNodeLineIcos:function setNodeLineIcos(setting,node){if(!node)return;var switchObj=$$(node,consts.id.SWITCH,setting),ulObj=$$(node,consts.id.UL,setting),icoObj=$$(node,consts.id.ICON,setting),ulLine=view.makeUlLineClass(setting,node);if(ulLine.length==0){ulObj.removeClass(consts.line.LINE);}else{ulObj.addClass(ulLine);}switchObj.attr("class",view.makeNodeLineClass(setting,node));if(node.isParent){switchObj.removeAttr("disabled");}else{switchObj.attr("disabled","disabled");}icoObj.removeAttr("style");icoObj.attr("style",view.makeNodeIcoStyle(setting,node));icoObj.attr("class",view.makeNodeIcoClass(setting,node));},setNodeName:function setNodeName(setting,node){var title=data.getNodeTitle(setting,node),nObj=$$(node,consts.id.SPAN,setting);nObj.empty();if(setting.view.nameIsHTML){nObj.html(data.getNodeName(setting,node));}else{nObj.text(data.getNodeName(setting,node));}if(tools.apply(setting.view.showTitle,[setting.treeId,node],setting.view.showTitle)){var aObj=$$(node,consts.id.A,setting);aObj.attr("title",!title?"":title);}},setNodeTarget:function setNodeTarget(setting,node){var aObj=$$(node,consts.id.A,setting);aObj.attr("target",view.makeNodeTarget(node));},setNodeUrl:function setNodeUrl(setting,node){var aObj=$$(node,consts.id.A,setting),url=view.makeNodeUrl(setting,node);if(url==null||url.length==0){aObj.removeAttr("href");}else{aObj.attr("href",url);}},switchNode:function switchNode(setting,node){if(node.open||!tools.canAsync(setting,node)){view.expandCollapseNode(setting,node,!node.open);}else if(setting.async.enable){if(!view.asyncNode(setting,node)){view.expandCollapseNode(setting,node,!node.open);return;}}else if(node){view.expandCollapseNode(setting,node,!node.open);}}};// zTree defind
	_$.fn.zTree={consts:_consts,_z:{tools:tools,view:view,event:event,data:data},getZTreeObj:function getZTreeObj(treeId){var o=data.getZTreeTools(treeId);return o?o:null;},destroy:function destroy(treeId){if(!!treeId&&treeId.length>0){view.destroy(data.getSetting(treeId));}else{for(var s in settings){view.destroy(settings[s]);}}},init:function init(obj,zSetting,zNodes){var setting=tools.clone(_setting);_$.extend(true,setting,zSetting);setting.treeId=obj.attr("id");setting.treeObj=obj;setting.treeObj.empty();settings[setting.treeId]=setting;//For some older browser,(e.g., ie6)
	if(typeof document.body.style.maxHeight==="undefined"){setting.view.expandSpeed="";}data.initRoot(setting);var root=data.getRoot(setting),childKey=setting.data.key.children;zNodes=zNodes?tools.clone(tools.isArray(zNodes)?zNodes:[zNodes]):[];if(setting.data.simpleData.enable){root[childKey]=data.transformTozTreeFormat(setting,zNodes);}else{root[childKey]=zNodes;}data.initCache(setting);event.unbindTree(setting);event.bindTree(setting);event.unbindEvent(setting);event.bindEvent(setting);var zTreeTools={setting:setting,addNodes:function addNodes(parentNode,index,newNodes,isSilent){if(!parentNode)parentNode=null;if(parentNode&&!parentNode.isParent&&setting.data.keep.leaf)return null;var i=parseInt(index,10);if(isNaN(i)){isSilent=!!newNodes;newNodes=index;index=-1;}else{index=i;}if(!newNodes)return null;var xNewNodes=tools.clone(tools.isArray(newNodes)?newNodes:[newNodes]);function addCallback(){view.addNodes(setting,parentNode,index,xNewNodes,isSilent==true);}if(tools.canAsync(setting,parentNode)){view.asyncNode(setting,parentNode,isSilent,addCallback);}else{addCallback();}return xNewNodes;},cancelSelectedNode:function cancelSelectedNode(node){view.cancelPreSelectedNode(setting,node);},destroy:function destroy(){view.destroy(setting);},expandAll:function expandAll(expandFlag){expandFlag=!!expandFlag;view.expandCollapseSonNode(setting,null,expandFlag,true);return expandFlag;},expandNode:function expandNode(node,expandFlag,sonSign,focus,callbackFlag){if(!node||!node.isParent)return null;if(expandFlag!==true&&expandFlag!==false){expandFlag=!node.open;}callbackFlag=!!callbackFlag;if(callbackFlag&&expandFlag&&tools.apply(setting.callback.beforeExpand,[setting.treeId,node],true)==false){return null;}else if(callbackFlag&&!expandFlag&&tools.apply(setting.callback.beforeCollapse,[setting.treeId,node],true)==false){return null;}if(expandFlag&&node.parentTId){view.expandCollapseParentNode(setting,node.getParentNode(),expandFlag,false);}if(expandFlag===node.open&&!sonSign){return null;}data.getRoot(setting).expandTriggerFlag=callbackFlag;if(!tools.canAsync(setting,node)&&sonSign){view.expandCollapseSonNode(setting,node,expandFlag,true,showNodeFocus);}else{node.open=!expandFlag;view.switchNode(this.setting,node);showNodeFocus();}return expandFlag;function showNodeFocus(){var a=$$(node,setting).get(0);if(a&&focus!==false){view.scrollIntoView(a);}}},getNodes:function getNodes(){return data.getNodes(setting);},getNodeByParam:function getNodeByParam(key,value,parentNode){if(!key)return null;return data.getNodeByParam(setting,parentNode?parentNode[setting.data.key.children]:data.getNodes(setting),key,value);},getNodeByTId:function getNodeByTId(tId){return data.getNodeCache(setting,tId);},getNodesByParam:function getNodesByParam(key,value,parentNode){if(!key)return null;return data.getNodesByParam(setting,parentNode?parentNode[setting.data.key.children]:data.getNodes(setting),key,value);},getNodesByParamFuzzy:function getNodesByParamFuzzy(key,value,parentNode){if(!key)return null;return data.getNodesByParamFuzzy(setting,parentNode?parentNode[setting.data.key.children]:data.getNodes(setting),key,value);},getNodesByFilter:function getNodesByFilter(filter,isSingle,parentNode,invokeParam){isSingle=!!isSingle;if(!filter||typeof filter!="function")return isSingle?null:[];return data.getNodesByFilter(setting,parentNode?parentNode[setting.data.key.children]:data.getNodes(setting),filter,isSingle,invokeParam);},getNodeIndex:function getNodeIndex(node){if(!node)return null;var childKey=setting.data.key.children,parentNode=node.parentTId?node.getParentNode():data.getRoot(setting);for(var i=0,l=parentNode[childKey].length;i<l;i++){if(parentNode[childKey][i]==node)return i;}return-1;},getSelectedNodes:function getSelectedNodes(){var r=[],list=data.getRoot(setting).curSelectedList;for(var i=0,l=list.length;i<l;i++){r.push(list[i]);}return r;},isSelectedNode:function isSelectedNode(node){return data.isSelectedNode(setting,node);},reAsyncChildNodes:function reAsyncChildNodes(parentNode,reloadType,isSilent){if(!this.setting.async.enable)return;var isRoot=!parentNode;if(isRoot){parentNode=data.getRoot(setting);}if(reloadType=="refresh"){var childKey=this.setting.data.key.children;for(var i=0,l=parentNode[childKey]?parentNode[childKey].length:0;i<l;i++){data.removeNodeCache(setting,parentNode[childKey][i]);}data.removeSelectedNode(setting);parentNode[childKey]=[];if(isRoot){this.setting.treeObj.empty();}else{var ulObj=$$(parentNode,consts.id.UL,setting);ulObj.empty();}}view.asyncNode(this.setting,isRoot?null:parentNode,!!isSilent);},refresh:function refresh(){this.setting.treeObj.empty();var root=data.getRoot(setting),nodes=root[setting.data.key.children];data.initRoot(setting);root[setting.data.key.children]=nodes;data.initCache(setting);view.createNodes(setting,0,root[setting.data.key.children],null,-1);},removeChildNodes:function removeChildNodes(node){if(!node)return null;var childKey=setting.data.key.children,nodes=node[childKey];view.removeChildNodes(setting,node);return nodes?nodes:null;},removeNode:function removeNode(node,callbackFlag){if(!node)return;callbackFlag=!!callbackFlag;if(callbackFlag&&tools.apply(setting.callback.beforeRemove,[setting.treeId,node],true)==false)return;view.removeNode(setting,node);if(callbackFlag){this.setting.treeObj.trigger(consts.event.REMOVE,[setting.treeId,node]);}},selectNode:function selectNode(node,addFlag,isSilent){if(!node)return;if(tools.uCanDo(setting)){addFlag=setting.view.selectedMulti&&addFlag;if(node.parentTId){view.expandCollapseParentNode(setting,node.getParentNode(),true,false,showNodeFocus);}else if(!isSilent){try{$$(node,setting).focus().blur();}catch(e){}}view.selectNode(setting,node,addFlag);}function showNodeFocus(){if(isSilent){return;}var a=$$(node,setting).get(0);view.scrollIntoView(a);}},transformTozTreeNodes:function transformTozTreeNodes(simpleNodes){return data.transformTozTreeFormat(setting,simpleNodes);},transformToArray:function transformToArray(nodes){return data.transformToArrayFormat(setting,nodes);},updateNode:function updateNode(node,checkTypeFlag){if(!node)return;var nObj=$$(node,setting);if(nObj.get(0)&&tools.uCanDo(setting)){view.setNodeName(setting,node);view.setNodeTarget(setting,node);view.setNodeUrl(setting,node);view.setNodeLineIcos(setting,node);view.setNodeFontCss(setting,node);}}};root.treeTools=zTreeTools;data.setZTreeTools(setting,zTreeTools);if(root[childKey]&&root[childKey].length>0){view.createNodes(setting,0,root[childKey],null,-1);}else if(setting.async.enable&&setting.async.url&&setting.async.url!==''){view.asyncNode(setting);}return zTreeTools;}};var zt=_$.fn.zTree,$$=tools.$,consts=zt.consts;})(jQuery);/*
	 * JQuery zTree excheck v3.5.24
	 * http://zTree.me/
	 *
	 * Copyright (c) 2010 Hunter.z
	 *
	 * Licensed same as jquery - MIT License
	 * http://www.opensource.org/licenses/mit-license.php
	 *
	 * email: hunter.z@263.net
	 * Date: 2016-06-06
	 */(function($){//default consts of excheck
	var _consts={event:{CHECK:"ztree_check"},id:{CHECK:"_check"},checkbox:{STYLE:"checkbox",DEFAULT:"chk",DISABLED:"disable",FALSE:"false",TRUE:"true",FULL:"full",PART:"part",FOCUS:"focus"},radio:{STYLE:"radio",TYPE_ALL:"all",TYPE_LEVEL:"level"}},//default setting of excheck
	_setting={check:{enable:false,autoCheckTrigger:false,chkStyle:_consts.checkbox.STYLE,nocheckInherit:false,chkDisabledInherit:false,radioType:_consts.radio.TYPE_LEVEL,chkboxType:{"Y":"ps","N":"ps"}},data:{key:{checked:"checked"}},callback:{beforeCheck:null,onCheck:null}},//default root of excheck
	_initRoot=function _initRoot(setting){var r=data.getRoot(setting);r.radioCheckedList=[];},//default cache of excheck
	_initCache=function _initCache(treeId){},//default bind event of excheck
	_bindEvent=function _bindEvent(setting){var o=setting.treeObj,c=consts.event;o.bind(c.CHECK,function(event,srcEvent,treeId,node){event.srcEvent=srcEvent;tools.apply(setting.callback.onCheck,[event,treeId,node]);});},_unbindEvent=function _unbindEvent(setting){var o=setting.treeObj,c=consts.event;o.unbind(c.CHECK);},//default event proxy of excheck
	_eventProxy=function _eventProxy(e){var target=e.target,setting=data.getSetting(e.data.treeId),tId="",node=null,nodeEventType="",treeEventType="",nodeEventCallback=null,treeEventCallback=null;if(tools.eqs(e.type,"mouseover")){if(setting.check.enable&&tools.eqs(target.tagName,"span")&&target.getAttribute("treeNode"+consts.id.CHECK)!==null){tId=tools.getNodeMainDom(target).id;nodeEventType="mouseoverCheck";}}else if(tools.eqs(e.type,"mouseout")){if(setting.check.enable&&tools.eqs(target.tagName,"span")&&target.getAttribute("treeNode"+consts.id.CHECK)!==null){tId=tools.getNodeMainDom(target).id;nodeEventType="mouseoutCheck";}}else if(tools.eqs(e.type,"click")){if(setting.check.enable&&tools.eqs(target.tagName,"span")&&target.getAttribute("treeNode"+consts.id.CHECK)!==null){tId=tools.getNodeMainDom(target).id;nodeEventType="checkNode";}}if(tId.length>0){node=data.getNodeCache(setting,tId);switch(nodeEventType){case"checkNode":nodeEventCallback=_handler.onCheckNode;break;case"mouseoverCheck":nodeEventCallback=_handler.onMouseoverCheck;break;case"mouseoutCheck":nodeEventCallback=_handler.onMouseoutCheck;break;}}var proxyResult={stop:nodeEventType==="checkNode",node:node,nodeEventType:nodeEventType,nodeEventCallback:nodeEventCallback,treeEventType:treeEventType,treeEventCallback:treeEventCallback};return proxyResult;},//default init node of excheck
	_initNode=function _initNode(setting,level,n,parentNode,isFirstNode,isLastNode,openFlag){if(!n)return;var checkedKey=setting.data.key.checked;if(typeof n[checkedKey]=="string")n[checkedKey]=tools.eqs(n[checkedKey],"true");n[checkedKey]=!!n[checkedKey];n.checkedOld=n[checkedKey];if(typeof n.nocheck=="string")n.nocheck=tools.eqs(n.nocheck,"true");n.nocheck=!!n.nocheck||setting.check.nocheckInherit&&parentNode&&!!parentNode.nocheck;if(typeof n.chkDisabled=="string")n.chkDisabled=tools.eqs(n.chkDisabled,"true");n.chkDisabled=!!n.chkDisabled||setting.check.chkDisabledInherit&&parentNode&&!!parentNode.chkDisabled;if(typeof n.halfCheck=="string")n.halfCheck=tools.eqs(n.halfCheck,"true");n.halfCheck=!!n.halfCheck;n.check_Child_State=-1;n.check_Focus=false;n.getCheckStatus=function(){return data.getCheckStatus(setting,n);};if(setting.check.chkStyle==consts.radio.STYLE&&setting.check.radioType==consts.radio.TYPE_ALL&&n[checkedKey]){var r=data.getRoot(setting);r.radioCheckedList.push(n);}},//add dom for check
	_beforeA=function _beforeA(setting,node,html){var checkedKey=setting.data.key.checked;if(setting.check.enable){data.makeChkFlag(setting,node);html.push("<span ID='",node.tId,consts.id.CHECK,"' class='",view.makeChkClass(setting,node),"' treeNode",consts.id.CHECK,node.nocheck===true?" style='display:none;'":"","></span>");}},//update zTreeObj, add method of check
	_zTreeTools=function _zTreeTools(setting,zTreeTools){zTreeTools.checkNode=function(node,checked,checkTypeFlag,callbackFlag){var checkedKey=this.setting.data.key.checked;if(node.chkDisabled===true)return;if(checked!==true&&checked!==false){checked=!node[checkedKey];}callbackFlag=!!callbackFlag;if(node[checkedKey]===checked&&!checkTypeFlag){return;}else if(callbackFlag&&tools.apply(this.setting.callback.beforeCheck,[this.setting.treeId,node],true)==false){return;}if(tools.uCanDo(this.setting)&&this.setting.check.enable&&node.nocheck!==true){node[checkedKey]=checked;var checkObj=$$(node,consts.id.CHECK,this.setting);if(checkTypeFlag||this.setting.check.chkStyle===consts.radio.STYLE)view.checkNodeRelation(this.setting,node);view.setChkClass(this.setting,checkObj,node);view.repairParentChkClassWithSelf(this.setting,node);if(callbackFlag){this.setting.treeObj.trigger(consts.event.CHECK,[null,this.setting.treeId,node]);}}};zTreeTools.checkAllNodes=function(checked){view.repairAllChk(this.setting,!!checked);};zTreeTools.getCheckedNodes=function(checked){var childKey=this.setting.data.key.children;checked=checked!==false;return data.getTreeCheckedNodes(this.setting,data.getRoot(this.setting)[childKey],checked);};zTreeTools.getChangeCheckedNodes=function(){var childKey=this.setting.data.key.children;return data.getTreeChangeCheckedNodes(this.setting,data.getRoot(this.setting)[childKey]);};zTreeTools.setChkDisabled=function(node,disabled,inheritParent,inheritChildren){disabled=!!disabled;inheritParent=!!inheritParent;inheritChildren=!!inheritChildren;view.repairSonChkDisabled(this.setting,node,disabled,inheritChildren);view.repairParentChkDisabled(this.setting,node.getParentNode(),disabled,inheritParent);};var _updateNode=zTreeTools.updateNode;zTreeTools.updateNode=function(node,checkTypeFlag){if(_updateNode)_updateNode.apply(zTreeTools,arguments);if(!node||!this.setting.check.enable)return;var nObj=$$(node,this.setting);if(nObj.get(0)&&tools.uCanDo(this.setting)){var checkObj=$$(node,consts.id.CHECK,this.setting);if(checkTypeFlag==true||this.setting.check.chkStyle===consts.radio.STYLE)view.checkNodeRelation(this.setting,node);view.setChkClass(this.setting,checkObj,node);view.repairParentChkClassWithSelf(this.setting,node);}};},//method of operate data
	_data={getRadioCheckedList:function getRadioCheckedList(setting){var checkedList=data.getRoot(setting).radioCheckedList;for(var i=0,j=checkedList.length;i<j;i++){if(!data.getNodeCache(setting,checkedList[i].tId)){checkedList.splice(i,1);i--;j--;}}return checkedList;},getCheckStatus:function getCheckStatus(setting,node){if(!setting.check.enable||node.nocheck||node.chkDisabled)return null;var checkedKey=setting.data.key.checked,r={checked:node[checkedKey],half:node.halfCheck?node.halfCheck:setting.check.chkStyle==consts.radio.STYLE?node.check_Child_State===2:node[checkedKey]?node.check_Child_State>-1&&node.check_Child_State<2:node.check_Child_State>0};return r;},getTreeCheckedNodes:function getTreeCheckedNodes(setting,nodes,checked,results){if(!nodes)return[];var childKey=setting.data.key.children,checkedKey=setting.data.key.checked,onlyOne=checked&&setting.check.chkStyle==consts.radio.STYLE&&setting.check.radioType==consts.radio.TYPE_ALL;results=!results?[]:results;for(var i=0,l=nodes.length;i<l;i++){if(nodes[i].nocheck!==true&&nodes[i].chkDisabled!==true&&nodes[i][checkedKey]==checked){results.push(nodes[i]);if(onlyOne){break;}}data.getTreeCheckedNodes(setting,nodes[i][childKey],checked,results);if(onlyOne&&results.length>0){break;}}return results;},getTreeChangeCheckedNodes:function getTreeChangeCheckedNodes(setting,nodes,results){if(!nodes)return[];var childKey=setting.data.key.children,checkedKey=setting.data.key.checked;results=!results?[]:results;for(var i=0,l=nodes.length;i<l;i++){if(nodes[i].nocheck!==true&&nodes[i].chkDisabled!==true&&nodes[i][checkedKey]!=nodes[i].checkedOld){results.push(nodes[i]);}data.getTreeChangeCheckedNodes(setting,nodes[i][childKey],results);}return results;},makeChkFlag:function makeChkFlag(setting,node){if(!node)return;var childKey=setting.data.key.children,checkedKey=setting.data.key.checked,chkFlag=-1;if(node[childKey]){for(var i=0,l=node[childKey].length;i<l;i++){var cNode=node[childKey][i];var tmp=-1;if(setting.check.chkStyle==consts.radio.STYLE){if(cNode.nocheck===true||cNode.chkDisabled===true){tmp=cNode.check_Child_State;}else if(cNode.halfCheck===true){tmp=2;}else if(cNode[checkedKey]){tmp=2;}else{tmp=cNode.check_Child_State>0?2:0;}if(tmp==2){chkFlag=2;break;}else if(tmp==0){chkFlag=0;}}else if(setting.check.chkStyle==consts.checkbox.STYLE){if(cNode.nocheck===true||cNode.chkDisabled===true){tmp=cNode.check_Child_State;}else if(cNode.halfCheck===true){tmp=1;}else if(cNode[checkedKey]){tmp=cNode.check_Child_State===-1||cNode.check_Child_State===2?2:1;}else{tmp=cNode.check_Child_State>0?1:0;}if(tmp===1){chkFlag=1;break;}else if(tmp===2&&chkFlag>-1&&i>0&&tmp!==chkFlag){chkFlag=1;break;}else if(chkFlag===2&&tmp>-1&&tmp<2){chkFlag=1;break;}else if(tmp>-1){chkFlag=tmp;}}}}node.check_Child_State=chkFlag;}},//method of event proxy
	_event={},//method of event handler
	_handler={onCheckNode:function onCheckNode(event,node){if(node.chkDisabled===true)return false;var setting=data.getSetting(event.data.treeId),checkedKey=setting.data.key.checked;if(tools.apply(setting.callback.beforeCheck,[setting.treeId,node],true)==false)return true;node[checkedKey]=!node[checkedKey];view.checkNodeRelation(setting,node);var checkObj=$$(node,consts.id.CHECK,setting);view.setChkClass(setting,checkObj,node);view.repairParentChkClassWithSelf(setting,node);setting.treeObj.trigger(consts.event.CHECK,[event,setting.treeId,node]);return true;},onMouseoverCheck:function onMouseoverCheck(event,node){if(node.chkDisabled===true)return false;var setting=data.getSetting(event.data.treeId),checkObj=$$(node,consts.id.CHECK,setting);node.check_Focus=true;view.setChkClass(setting,checkObj,node);return true;},onMouseoutCheck:function onMouseoutCheck(event,node){if(node.chkDisabled===true)return false;var setting=data.getSetting(event.data.treeId),checkObj=$$(node,consts.id.CHECK,setting);node.check_Focus=false;view.setChkClass(setting,checkObj,node);return true;}},//method of tools for zTree
	_tools={},//method of operate ztree dom
	_view={checkNodeRelation:function checkNodeRelation(setting,node){var pNode,i,l,childKey=setting.data.key.children,checkedKey=setting.data.key.checked,r=consts.radio;if(setting.check.chkStyle==r.STYLE){var checkedList=data.getRadioCheckedList(setting);if(node[checkedKey]){if(setting.check.radioType==r.TYPE_ALL){for(i=checkedList.length-1;i>=0;i--){pNode=checkedList[i];if(pNode[checkedKey]&&pNode!=node){pNode[checkedKey]=false;checkedList.splice(i,1);view.setChkClass(setting,$$(pNode,consts.id.CHECK,setting),pNode);if(pNode.parentTId!=node.parentTId){view.repairParentChkClassWithSelf(setting,pNode);}}}checkedList.push(node);}else{var parentNode=node.parentTId?node.getParentNode():data.getRoot(setting);for(i=0,l=parentNode[childKey].length;i<l;i++){pNode=parentNode[childKey][i];if(pNode[checkedKey]&&pNode!=node){pNode[checkedKey]=false;view.setChkClass(setting,$$(pNode,consts.id.CHECK,setting),pNode);}}}}else if(setting.check.radioType==r.TYPE_ALL){for(i=0,l=checkedList.length;i<l;i++){if(node==checkedList[i]){checkedList.splice(i,1);break;}}}}else{if(node[checkedKey]&&(!node[childKey]||node[childKey].length==0||setting.check.chkboxType.Y.indexOf("s")>-1)){view.setSonNodeCheckBox(setting,node,true);}if(!node[checkedKey]&&(!node[childKey]||node[childKey].length==0||setting.check.chkboxType.N.indexOf("s")>-1)){view.setSonNodeCheckBox(setting,node,false);}if(node[checkedKey]&&setting.check.chkboxType.Y.indexOf("p")>-1){view.setParentNodeCheckBox(setting,node,true);}if(!node[checkedKey]&&setting.check.chkboxType.N.indexOf("p")>-1){view.setParentNodeCheckBox(setting,node,false);}}},makeChkClass:function makeChkClass(setting,node){var checkedKey=setting.data.key.checked,c=consts.checkbox,r=consts.radio,fullStyle="";if(node.chkDisabled===true){fullStyle=c.DISABLED;}else if(node.halfCheck){fullStyle=c.PART;}else if(setting.check.chkStyle==r.STYLE){fullStyle=node.check_Child_State<1?c.FULL:c.PART;}else{fullStyle=node[checkedKey]?node.check_Child_State===2||node.check_Child_State===-1?c.FULL:c.PART:node.check_Child_State<1?c.FULL:c.PART;}var chkName=setting.check.chkStyle+"_"+(node[checkedKey]?c.TRUE:c.FALSE)+"_"+fullStyle;chkName=node.check_Focus&&node.chkDisabled!==true?chkName+"_"+c.FOCUS:chkName;return consts.className.BUTTON+" "+c.DEFAULT+" "+chkName;},repairAllChk:function repairAllChk(setting,checked){if(setting.check.enable&&setting.check.chkStyle===consts.checkbox.STYLE){var checkedKey=setting.data.key.checked,childKey=setting.data.key.children,root=data.getRoot(setting);for(var i=0,l=root[childKey].length;i<l;i++){var node=root[childKey][i];if(node.nocheck!==true&&node.chkDisabled!==true){node[checkedKey]=checked;}view.setSonNodeCheckBox(setting,node,checked);}}},repairChkClass:function repairChkClass(setting,node){if(!node)return;data.makeChkFlag(setting,node);if(node.nocheck!==true){var checkObj=$$(node,consts.id.CHECK,setting);view.setChkClass(setting,checkObj,node);}},repairParentChkClass:function repairParentChkClass(setting,node){if(!node||!node.parentTId)return;var pNode=node.getParentNode();view.repairChkClass(setting,pNode);view.repairParentChkClass(setting,pNode);},repairParentChkClassWithSelf:function repairParentChkClassWithSelf(setting,node){if(!node)return;var childKey=setting.data.key.children;if(node[childKey]&&node[childKey].length>0){view.repairParentChkClass(setting,node[childKey][0]);}else{view.repairParentChkClass(setting,node);}},repairSonChkDisabled:function repairSonChkDisabled(setting,node,chkDisabled,inherit){if(!node)return;var childKey=setting.data.key.children;if(node.chkDisabled!=chkDisabled){node.chkDisabled=chkDisabled;}view.repairChkClass(setting,node);if(node[childKey]&&inherit){for(var i=0,l=node[childKey].length;i<l;i++){var sNode=node[childKey][i];view.repairSonChkDisabled(setting,sNode,chkDisabled,inherit);}}},repairParentChkDisabled:function repairParentChkDisabled(setting,node,chkDisabled,inherit){if(!node)return;if(node.chkDisabled!=chkDisabled&&inherit){node.chkDisabled=chkDisabled;}view.repairChkClass(setting,node);view.repairParentChkDisabled(setting,node.getParentNode(),chkDisabled,inherit);},setChkClass:function setChkClass(setting,obj,node){if(!obj)return;if(node.nocheck===true){obj.hide();}else{obj.show();}obj.attr('class',view.makeChkClass(setting,node));},setParentNodeCheckBox:function setParentNodeCheckBox(setting,node,value,srcNode){var childKey=setting.data.key.children,checkedKey=setting.data.key.checked,checkObj=$$(node,consts.id.CHECK,setting);if(!srcNode)srcNode=node;data.makeChkFlag(setting,node);if(node.nocheck!==true&&node.chkDisabled!==true){node[checkedKey]=value;view.setChkClass(setting,checkObj,node);if(setting.check.autoCheckTrigger&&node!=srcNode){setting.treeObj.trigger(consts.event.CHECK,[null,setting.treeId,node]);}}if(node.parentTId){var pSign=true;if(!value){var pNodes=node.getParentNode()[childKey];for(var i=0,l=pNodes.length;i<l;i++){if(pNodes[i].nocheck!==true&&pNodes[i].chkDisabled!==true&&pNodes[i][checkedKey]||(pNodes[i].nocheck===true||pNodes[i].chkDisabled===true)&&pNodes[i].check_Child_State>0){pSign=false;break;}}}if(pSign){view.setParentNodeCheckBox(setting,node.getParentNode(),value,srcNode);}}},setSonNodeCheckBox:function setSonNodeCheckBox(setting,node,value,srcNode){if(!node)return;var childKey=setting.data.key.children,checkedKey=setting.data.key.checked,checkObj=$$(node,consts.id.CHECK,setting);if(!srcNode)srcNode=node;var hasDisable=false;if(node[childKey]){for(var i=0,l=node[childKey].length;i<l;i++){var sNode=node[childKey][i];view.setSonNodeCheckBox(setting,sNode,value,srcNode);if(sNode.chkDisabled===true)hasDisable=true;}}if(node!=data.getRoot(setting)&&node.chkDisabled!==true){if(hasDisable&&node.nocheck!==true){data.makeChkFlag(setting,node);}if(node.nocheck!==true&&node.chkDisabled!==true){node[checkedKey]=value;if(!hasDisable)node.check_Child_State=node[childKey]&&node[childKey].length>0?value?2:0:-1;}else{node.check_Child_State=-1;}view.setChkClass(setting,checkObj,node);if(setting.check.autoCheckTrigger&&node!=srcNode&&node.nocheck!==true&&node.chkDisabled!==true){setting.treeObj.trigger(consts.event.CHECK,[null,setting.treeId,node]);}}}},_z={tools:_tools,view:_view,event:_event,data:_data};$.extend(true,$.fn.zTree.consts,_consts);$.extend(true,$.fn.zTree._z,_z);var zt=$.fn.zTree,tools=zt._z.tools,consts=zt.consts,view=zt._z.view,data=zt._z.data,event=zt._z.event,$$=tools.$;data.exSetting(_setting);data.addInitBind(_bindEvent);data.addInitUnBind(_unbindEvent);data.addInitCache(_initCache);data.addInitNode(_initNode);data.addInitProxy(_eventProxy,true);data.addInitRoot(_initRoot);data.addBeforeA(_beforeA);data.addZTreeTools(_zTreeTools);var _createNodes=view.createNodes;view.createNodes=function(setting,level,nodes,parentNode,index){if(_createNodes)_createNodes.apply(view,arguments);if(!nodes)return;view.repairParentChkClassWithSelf(setting,parentNode);};var _removeNode=view.removeNode;view.removeNode=function(setting,node){var parentNode=node.getParentNode();if(_removeNode)_removeNode.apply(view,arguments);if(!node||!parentNode)return;view.repairChkClass(setting,parentNode);view.repairParentChkClass(setting,parentNode);};var _appendNodes=view.appendNodes;view.appendNodes=function(setting,level,nodes,parentNode,index,initFlag,openFlag){var html="";if(_appendNodes){html=_appendNodes.apply(view,arguments);}if(parentNode){data.makeChkFlag(setting,parentNode);}return html;};})(jQuery);/*
	 * JQuery zTree exedit v3.5.24
	 * http://zTree.me/
	 *
	 * Copyright (c) 2010 Hunter.z
	 *
	 * Licensed same as jquery - MIT License
	 * http://www.opensource.org/licenses/mit-license.php
	 *
	 * email: hunter.z@263.net
	 * Date: 2016-06-06
	 */(function($){//default consts of exedit
	var _consts={event:{DRAG:"ztree_drag",DROP:"ztree_drop",RENAME:"ztree_rename",DRAGMOVE:"ztree_dragmove"},id:{EDIT:"_edit",INPUT:"_input",REMOVE:"_remove"},move:{TYPE_INNER:"inner",TYPE_PREV:"prev",TYPE_NEXT:"next"},node:{CURSELECTED_EDIT:"curSelectedNode_Edit",TMPTARGET_TREE:"tmpTargetzTree",TMPTARGET_NODE:"tmpTargetNode"}},//default setting of exedit
	_setting={edit:{enable:false,editNameSelectAll:false,showRemoveBtn:true,showRenameBtn:true,removeTitle:"remove",renameTitle:"rename",drag:{autoExpandTrigger:false,isCopy:true,isMove:true,prev:true,next:true,inner:true,minMoveSize:5,borderMax:10,borderMin:-5,maxShowNodeNum:5,autoOpenTime:500}},view:{addHoverDom:null,removeHoverDom:null},callback:{beforeDrag:null,beforeDragOpen:null,beforeDrop:null,beforeEditName:null,beforeRename:null,onDrag:null,onDragMove:null,onDrop:null,onRename:null}},//default root of exedit
	_initRoot=function _initRoot(setting){var r=data.getRoot(setting),rs=data.getRoots();r.curEditNode=null;r.curEditInput=null;r.curHoverNode=null;r.dragFlag=0;r.dragNodeShowBefore=[];r.dragMaskList=new Array();rs.showHoverDom=true;},//default cache of exedit
	_initCache=function _initCache(treeId){},//default bind event of exedit
	_bindEvent=function _bindEvent(setting){var o=setting.treeObj;var c=consts.event;o.bind(c.RENAME,function(event,treeId,treeNode,isCancel){tools.apply(setting.callback.onRename,[event,treeId,treeNode,isCancel]);});o.bind(c.DRAG,function(event,srcEvent,treeId,treeNodes){tools.apply(setting.callback.onDrag,[srcEvent,treeId,treeNodes]);});o.bind(c.DRAGMOVE,function(event,srcEvent,treeId,treeNodes){tools.apply(setting.callback.onDragMove,[srcEvent,treeId,treeNodes]);});o.bind(c.DROP,function(event,srcEvent,treeId,treeNodes,targetNode,moveType,isCopy){tools.apply(setting.callback.onDrop,[srcEvent,treeId,treeNodes,targetNode,moveType,isCopy]);});},_unbindEvent=function _unbindEvent(setting){var o=setting.treeObj;var c=consts.event;o.unbind(c.RENAME);o.unbind(c.DRAG);o.unbind(c.DRAGMOVE);o.unbind(c.DROP);},//default event proxy of exedit
	_eventProxy=function _eventProxy(e){var target=e.target,setting=data.getSetting(e.data.treeId),relatedTarget=e.relatedTarget,tId="",node=null,nodeEventType="",treeEventType="",nodeEventCallback=null,treeEventCallback=null,tmp=null;if(tools.eqs(e.type,"mouseover")){tmp=tools.getMDom(setting,target,[{tagName:"a",attrName:"treeNode"+consts.id.A}]);if(tmp){tId=tools.getNodeMainDom(tmp).id;nodeEventType="hoverOverNode";}}else if(tools.eqs(e.type,"mouseout")){tmp=tools.getMDom(setting,relatedTarget,[{tagName:"a",attrName:"treeNode"+consts.id.A}]);if(!tmp){tId="remove";nodeEventType="hoverOutNode";}}else if(tools.eqs(e.type,"mousedown")){tmp=tools.getMDom(setting,target,[{tagName:"a",attrName:"treeNode"+consts.id.A}]);if(tmp){tId=tools.getNodeMainDom(tmp).id;nodeEventType="mousedownNode";}}if(tId.length>0){node=data.getNodeCache(setting,tId);switch(nodeEventType){case"mousedownNode":nodeEventCallback=_handler.onMousedownNode;break;case"hoverOverNode":nodeEventCallback=_handler.onHoverOverNode;break;case"hoverOutNode":nodeEventCallback=_handler.onHoverOutNode;break;}}var proxyResult={stop:false,node:node,nodeEventType:nodeEventType,nodeEventCallback:nodeEventCallback,treeEventType:treeEventType,treeEventCallback:treeEventCallback};return proxyResult;},//default init node of exedit
	_initNode=function _initNode(setting,level,n,parentNode,isFirstNode,isLastNode,openFlag){if(!n)return;n.isHover=false;n.editNameFlag=false;},//update zTreeObj, add method of edit
	_zTreeTools=function _zTreeTools(setting,zTreeTools){zTreeTools.cancelEditName=function(newName){var root=data.getRoot(this.setting);if(!root.curEditNode)return;view.cancelCurEditNode(this.setting,newName?newName:null,true);};zTreeTools.copyNode=function(targetNode,node,moveType,isSilent){if(!node)return null;if(targetNode&&!targetNode.isParent&&this.setting.data.keep.leaf&&moveType===consts.move.TYPE_INNER)return null;var _this=this,newNode=tools.clone(node);if(!targetNode){targetNode=null;moveType=consts.move.TYPE_INNER;}if(moveType==consts.move.TYPE_INNER){var copyCallback=function copyCallback(){view.addNodes(_this.setting,targetNode,-1,[newNode],isSilent);};if(tools.canAsync(this.setting,targetNode)){view.asyncNode(this.setting,targetNode,isSilent,copyCallback);}else{copyCallback();}}else{view.addNodes(this.setting,targetNode.parentNode,-1,[newNode],isSilent);view.moveNode(this.setting,targetNode,newNode,moveType,false,isSilent);}return newNode;};zTreeTools.editName=function(node){if(!node||!node.tId||node!==data.getNodeCache(this.setting,node.tId))return;if(node.parentTId)view.expandCollapseParentNode(this.setting,node.getParentNode(),true);view.editNode(this.setting,node);};zTreeTools.moveNode=function(targetNode,node,moveType,isSilent){if(!node)return node;if(targetNode&&!targetNode.isParent&&this.setting.data.keep.leaf&&moveType===consts.move.TYPE_INNER){return null;}else if(targetNode&&(node.parentTId==targetNode.tId&&moveType==consts.move.TYPE_INNER||$$(node,this.setting).find("#"+targetNode.tId).length>0)){return null;}else if(!targetNode){targetNode=null;}var _this=this;function moveCallback(){view.moveNode(_this.setting,targetNode,node,moveType,false,isSilent);}if(tools.canAsync(this.setting,targetNode)&&moveType===consts.move.TYPE_INNER){view.asyncNode(this.setting,targetNode,isSilent,moveCallback);}else{moveCallback();}return node;};zTreeTools.setEditable=function(editable){this.setting.edit.enable=editable;return this.refresh();};},//method of operate data
	_data={setSonNodeLevel:function setSonNodeLevel(setting,parentNode,node){if(!node)return;var childKey=setting.data.key.children;node.level=parentNode?parentNode.level+1:0;if(!node[childKey])return;for(var i=0,l=node[childKey].length;i<l;i++){if(node[childKey][i])data.setSonNodeLevel(setting,node,node[childKey][i]);}}},//method of event proxy
	_event={},//method of event handler
	_handler={onHoverOverNode:function onHoverOverNode(event,node){var setting=data.getSetting(event.data.treeId),root=data.getRoot(setting);if(root.curHoverNode!=node){_handler.onHoverOutNode(event);}root.curHoverNode=node;view.addHoverDom(setting,node);},onHoverOutNode:function onHoverOutNode(event,node){var setting=data.getSetting(event.data.treeId),root=data.getRoot(setting);if(root.curHoverNode&&!data.isSelectedNode(setting,root.curHoverNode)){view.removeTreeDom(setting,root.curHoverNode);root.curHoverNode=null;}},onMousedownNode:function onMousedownNode(eventMouseDown,_node){var i,l,setting=data.getSetting(eventMouseDown.data.treeId),root=data.getRoot(setting),roots=data.getRoots();//right click can't drag & drop
	if(eventMouseDown.button==2||!setting.edit.enable||!setting.edit.drag.isCopy&&!setting.edit.drag.isMove)return true;//input of edit node name can't drag & drop
	var target=eventMouseDown.target,_nodes=data.getRoot(setting).curSelectedList,nodes=[];if(!data.isSelectedNode(setting,_node)){nodes=[_node];}else{for(i=0,l=_nodes.length;i<l;i++){if(_nodes[i].editNameFlag&&tools.eqs(target.tagName,"input")&&target.getAttribute("treeNode"+consts.id.INPUT)!==null){return true;}nodes.push(_nodes[i]);if(nodes[0].parentTId!==_nodes[i].parentTId){nodes=[_node];break;}}}view.editNodeBlur=true;view.cancelCurEditNode(setting);var doc=$(setting.treeObj.get(0).ownerDocument),body=$(setting.treeObj.get(0).ownerDocument.body),curNode,tmpArrow,tmpTarget,isOtherTree=false,targetSetting=setting,sourceSetting=setting,preNode,nextNode,preTmpTargetNodeId=null,preTmpMoveType=null,tmpTargetNodeId=null,moveType=consts.move.TYPE_INNER,mouseDownX=eventMouseDown.clientX,mouseDownY=eventMouseDown.clientY,startTime=new Date().getTime();if(tools.uCanDo(setting)){doc.bind("mousemove",_docMouseMove);}function _docMouseMove(event){//avoid start drag after click node
	if(root.dragFlag==0&&Math.abs(mouseDownX-event.clientX)<setting.edit.drag.minMoveSize&&Math.abs(mouseDownY-event.clientY)<setting.edit.drag.minMoveSize){return true;}var i,l,tmpNode,tmpDom,tmpNodes,childKey=setting.data.key.children;body.css("cursor","pointer");if(root.dragFlag==0){if(tools.apply(setting.callback.beforeDrag,[setting.treeId,nodes],true)==false){_docMouseUp(event);return true;}for(i=0,l=nodes.length;i<l;i++){if(i==0){root.dragNodeShowBefore=[];}tmpNode=nodes[i];if(tmpNode.isParent&&tmpNode.open){view.expandCollapseNode(setting,tmpNode,!tmpNode.open);root.dragNodeShowBefore[tmpNode.tId]=true;}else{root.dragNodeShowBefore[tmpNode.tId]=false;}}root.dragFlag=1;roots.showHoverDom=false;tools.showIfameMask(setting,true);//sort
	var isOrder=true,lastIndex=-1;if(nodes.length>1){var pNodes=nodes[0].parentTId?nodes[0].getParentNode()[childKey]:data.getNodes(setting);tmpNodes=[];for(i=0,l=pNodes.length;i<l;i++){if(root.dragNodeShowBefore[pNodes[i].tId]!==undefined){if(isOrder&&lastIndex>-1&&lastIndex+1!==i){isOrder=false;}tmpNodes.push(pNodes[i]);lastIndex=i;}if(nodes.length===tmpNodes.length){nodes=tmpNodes;break;}}}if(isOrder){preNode=nodes[0].getPreNode();nextNode=nodes[nodes.length-1].getNextNode();}//set node in selected
	curNode=$$("<ul class='zTreeDragUL'></ul>",setting);for(i=0,l=nodes.length;i<l;i++){tmpNode=nodes[i];tmpNode.editNameFlag=false;view.selectNode(setting,tmpNode,i>0);view.removeTreeDom(setting,tmpNode);if(i>setting.edit.drag.maxShowNodeNum-1){continue;}tmpDom=$$("<li id='"+tmpNode.tId+"_tmp'></li>",setting);tmpDom.append($$(tmpNode,consts.id.A,setting).clone());tmpDom.css("padding","0");tmpDom.children("#"+tmpNode.tId+consts.id.A).removeClass(consts.node.CURSELECTED);curNode.append(tmpDom);if(i==setting.edit.drag.maxShowNodeNum-1){tmpDom=$$("<li id='"+tmpNode.tId+"_moretmp'><a>  ...  </a></li>",setting);curNode.append(tmpDom);}}curNode.attr("id",nodes[0].tId+consts.id.UL+"_tmp");curNode.addClass(setting.treeObj.attr("class"));curNode.appendTo(body);tmpArrow=$$("<span class='tmpzTreeMove_arrow'></span>",setting);tmpArrow.attr("id","zTreeMove_arrow_tmp");tmpArrow.appendTo(body);setting.treeObj.trigger(consts.event.DRAG,[event,setting.treeId,nodes]);}if(root.dragFlag==1){if(tmpTarget&&tmpArrow.attr("id")==event.target.id&&tmpTargetNodeId&&event.clientX+doc.scrollLeft()+2>$("#"+tmpTargetNodeId+consts.id.A,tmpTarget).offset().left){var xT=$("#"+tmpTargetNodeId+consts.id.A,tmpTarget);event.target=xT.length>0?xT.get(0):event.target;}else if(tmpTarget){tmpTarget.removeClass(consts.node.TMPTARGET_TREE);if(tmpTargetNodeId)$("#"+tmpTargetNodeId+consts.id.A,tmpTarget).removeClass(consts.node.TMPTARGET_NODE+"_"+consts.move.TYPE_PREV).removeClass(consts.node.TMPTARGET_NODE+"_"+_consts.move.TYPE_NEXT).removeClass(consts.node.TMPTARGET_NODE+"_"+_consts.move.TYPE_INNER);}tmpTarget=null;tmpTargetNodeId=null;//judge drag & drop in multi ztree
	isOtherTree=false;targetSetting=setting;var settings=data.getSettings();for(var s in settings){if(settings[s].treeId&&settings[s].edit.enable&&settings[s].treeId!=setting.treeId&&(event.target.id==settings[s].treeId||$(event.target).parents("#"+settings[s].treeId).length>0)){isOtherTree=true;targetSetting=settings[s];}}var docScrollTop=doc.scrollTop(),docScrollLeft=doc.scrollLeft(),treeOffset=targetSetting.treeObj.offset(),scrollHeight=targetSetting.treeObj.get(0).scrollHeight,scrollWidth=targetSetting.treeObj.get(0).scrollWidth,dTop=event.clientY+docScrollTop-treeOffset.top,dBottom=targetSetting.treeObj.height()+treeOffset.top-event.clientY-docScrollTop,dLeft=event.clientX+docScrollLeft-treeOffset.left,dRight=targetSetting.treeObj.width()+treeOffset.left-event.clientX-docScrollLeft,isTop=dTop<setting.edit.drag.borderMax&&dTop>setting.edit.drag.borderMin,isBottom=dBottom<setting.edit.drag.borderMax&&dBottom>setting.edit.drag.borderMin,isLeft=dLeft<setting.edit.drag.borderMax&&dLeft>setting.edit.drag.borderMin,isRight=dRight<setting.edit.drag.borderMax&&dRight>setting.edit.drag.borderMin,isTreeInner=dTop>setting.edit.drag.borderMin&&dBottom>setting.edit.drag.borderMin&&dLeft>setting.edit.drag.borderMin&&dRight>setting.edit.drag.borderMin,isTreeTop=isTop&&targetSetting.treeObj.scrollTop()<=0,isTreeBottom=isBottom&&targetSetting.treeObj.scrollTop()+targetSetting.treeObj.height()+10>=scrollHeight,isTreeLeft=isLeft&&targetSetting.treeObj.scrollLeft()<=0,isTreeRight=isRight&&targetSetting.treeObj.scrollLeft()+targetSetting.treeObj.width()+10>=scrollWidth;if(event.target&&tools.isChildOrSelf(event.target,targetSetting.treeId)){//get node <li> dom
	var targetObj=event.target;while(targetObj&&targetObj.tagName&&!tools.eqs(targetObj.tagName,"li")&&targetObj.id!=targetSetting.treeId){targetObj=targetObj.parentNode;}var canMove=true;//don't move to self or children of self
	for(i=0,l=nodes.length;i<l;i++){tmpNode=nodes[i];if(targetObj.id===tmpNode.tId){canMove=false;break;}else if($$(tmpNode,setting).find("#"+targetObj.id).length>0){canMove=false;break;}}if(canMove&&event.target&&tools.isChildOrSelf(event.target,targetObj.id+consts.id.A)){tmpTarget=$(targetObj);tmpTargetNodeId=targetObj.id;}}//the mouse must be in zTree
	tmpNode=nodes[0];if(isTreeInner&&tools.isChildOrSelf(event.target,targetSetting.treeId)){//judge mouse move in root of ztree
	if(!tmpTarget&&(event.target.id==targetSetting.treeId||isTreeTop||isTreeBottom||isTreeLeft||isTreeRight)&&(isOtherTree||!isOtherTree&&tmpNode.parentTId)){tmpTarget=targetSetting.treeObj;}//auto scroll top
	if(isTop){targetSetting.treeObj.scrollTop(targetSetting.treeObj.scrollTop()-10);}else if(isBottom){targetSetting.treeObj.scrollTop(targetSetting.treeObj.scrollTop()+10);}if(isLeft){targetSetting.treeObj.scrollLeft(targetSetting.treeObj.scrollLeft()-10);}else if(isRight){targetSetting.treeObj.scrollLeft(targetSetting.treeObj.scrollLeft()+10);}//auto scroll left
	if(tmpTarget&&tmpTarget!=targetSetting.treeObj&&tmpTarget.offset().left<targetSetting.treeObj.offset().left){targetSetting.treeObj.scrollLeft(targetSetting.treeObj.scrollLeft()+tmpTarget.offset().left-targetSetting.treeObj.offset().left);}}curNode.css({"top":event.clientY+docScrollTop+3+"px","left":event.clientX+docScrollLeft+3+"px"});var dX=0;var dY=0;if(tmpTarget&&tmpTarget.attr("id")!=targetSetting.treeId){var clearMove=function clearMove(){tmpTarget=null;tmpTargetNodeId="";moveType=consts.move.TYPE_INNER;tmpArrow.css({"display":"none"});if(window.zTreeMoveTimer){clearTimeout(window.zTreeMoveTimer);window.zTreeMoveTargetNodeTId=null;}};var tmpTargetNode=tmpTargetNodeId==null?null:data.getNodeCache(targetSetting,tmpTargetNodeId),isCopy=(event.ctrlKey||event.metaKey)&&setting.edit.drag.isMove&&setting.edit.drag.isCopy||!setting.edit.drag.isMove&&setting.edit.drag.isCopy,isPrev=!!(preNode&&tmpTargetNodeId===preNode.tId),isNext=!!(nextNode&&tmpTargetNodeId===nextNode.tId),isInner=tmpNode.parentTId&&tmpNode.parentTId==tmpTargetNodeId,canPrev=(isCopy||!isNext)&&tools.apply(targetSetting.edit.drag.prev,[targetSetting.treeId,nodes,tmpTargetNode],!!targetSetting.edit.drag.prev),canNext=(isCopy||!isPrev)&&tools.apply(targetSetting.edit.drag.next,[targetSetting.treeId,nodes,tmpTargetNode],!!targetSetting.edit.drag.next),canInner=(isCopy||!isInner)&&!(targetSetting.data.keep.leaf&&!tmpTargetNode.isParent)&&tools.apply(targetSetting.edit.drag.inner,[targetSetting.treeId,nodes,tmpTargetNode],!!targetSetting.edit.drag.inner);if(!canPrev&&!canNext&&!canInner){clearMove();}else{var tmpTargetA=$("#"+tmpTargetNodeId+consts.id.A,tmpTarget),tmpNextA=tmpTargetNode.isLastNode?null:$("#"+tmpTargetNode.getNextNode().tId+consts.id.A,tmpTarget.next()),tmpTop=tmpTargetA.offset().top,tmpLeft=tmpTargetA.offset().left,prevPercent=canPrev?canInner?0.25:canNext?0.5:1:-1,nextPercent=canNext?canInner?0.75:canPrev?0.5:0:-1,dY_percent=(event.clientY+docScrollTop-tmpTop)/tmpTargetA.height();if((prevPercent==1||dY_percent<=prevPercent&&dY_percent>=-.2)&&canPrev){dX=1-tmpArrow.width();dY=tmpTop-tmpArrow.height()/2;moveType=consts.move.TYPE_PREV;}else if((nextPercent==0||dY_percent>=nextPercent&&dY_percent<=1.2)&&canNext){dX=1-tmpArrow.width();dY=tmpNextA==null||tmpTargetNode.isParent&&tmpTargetNode.open?tmpTop+tmpTargetA.height()-tmpArrow.height()/2:tmpNextA.offset().top-tmpArrow.height()/2;moveType=consts.move.TYPE_NEXT;}else if(canInner){dX=5-tmpArrow.width();dY=tmpTop;moveType=consts.move.TYPE_INNER;}else{clearMove();}if(tmpTarget){tmpArrow.css({"display":"block","top":dY+"px","left":tmpLeft+dX+"px"});tmpTargetA.addClass(consts.node.TMPTARGET_NODE+"_"+moveType);if(preTmpTargetNodeId!=tmpTargetNodeId||preTmpMoveType!=moveType){startTime=new Date().getTime();}if(tmpTargetNode&&tmpTargetNode.isParent&&moveType==consts.move.TYPE_INNER){var startTimer=true;if(window.zTreeMoveTimer&&window.zTreeMoveTargetNodeTId!==tmpTargetNode.tId){clearTimeout(window.zTreeMoveTimer);window.zTreeMoveTargetNodeTId=null;}else if(window.zTreeMoveTimer&&window.zTreeMoveTargetNodeTId===tmpTargetNode.tId){startTimer=false;}if(startTimer){window.zTreeMoveTimer=setTimeout(function(){if(moveType!=consts.move.TYPE_INNER)return;if(tmpTargetNode&&tmpTargetNode.isParent&&!tmpTargetNode.open&&new Date().getTime()-startTime>targetSetting.edit.drag.autoOpenTime&&tools.apply(targetSetting.callback.beforeDragOpen,[targetSetting.treeId,tmpTargetNode],true)){view.switchNode(targetSetting,tmpTargetNode);if(targetSetting.edit.drag.autoExpandTrigger){targetSetting.treeObj.trigger(consts.event.EXPAND,[targetSetting.treeId,tmpTargetNode]);}}},targetSetting.edit.drag.autoOpenTime+50);window.zTreeMoveTargetNodeTId=tmpTargetNode.tId;}}}}}else{moveType=consts.move.TYPE_INNER;if(tmpTarget&&tools.apply(targetSetting.edit.drag.inner,[targetSetting.treeId,nodes,null],!!targetSetting.edit.drag.inner)){tmpTarget.addClass(consts.node.TMPTARGET_TREE);}else{tmpTarget=null;}tmpArrow.css({"display":"none"});if(window.zTreeMoveTimer){clearTimeout(window.zTreeMoveTimer);window.zTreeMoveTargetNodeTId=null;}}preTmpTargetNodeId=tmpTargetNodeId;preTmpMoveType=moveType;setting.treeObj.trigger(consts.event.DRAGMOVE,[event,setting.treeId,nodes]);}return false;}doc.bind("mouseup",_docMouseUp);function _docMouseUp(event){if(window.zTreeMoveTimer){clearTimeout(window.zTreeMoveTimer);window.zTreeMoveTargetNodeTId=null;}preTmpTargetNodeId=null;preTmpMoveType=null;doc.unbind("mousemove",_docMouseMove);doc.unbind("mouseup",_docMouseUp);doc.unbind("selectstart",_docSelect);body.css("cursor","auto");if(tmpTarget){tmpTarget.removeClass(consts.node.TMPTARGET_TREE);if(tmpTargetNodeId)$("#"+tmpTargetNodeId+consts.id.A,tmpTarget).removeClass(consts.node.TMPTARGET_NODE+"_"+consts.move.TYPE_PREV).removeClass(consts.node.TMPTARGET_NODE+"_"+_consts.move.TYPE_NEXT).removeClass(consts.node.TMPTARGET_NODE+"_"+_consts.move.TYPE_INNER);}tools.showIfameMask(setting,false);roots.showHoverDom=true;if(root.dragFlag==0)return;root.dragFlag=0;var i,l,tmpNode;for(i=0,l=nodes.length;i<l;i++){tmpNode=nodes[i];if(tmpNode.isParent&&root.dragNodeShowBefore[tmpNode.tId]&&!tmpNode.open){view.expandCollapseNode(setting,tmpNode,!tmpNode.open);delete root.dragNodeShowBefore[tmpNode.tId];}}if(curNode)curNode.remove();if(tmpArrow)tmpArrow.remove();var isCopy=(event.ctrlKey||event.metaKey)&&setting.edit.drag.isMove&&setting.edit.drag.isCopy||!setting.edit.drag.isMove&&setting.edit.drag.isCopy;if(!isCopy&&tmpTarget&&tmpTargetNodeId&&nodes[0].parentTId&&tmpTargetNodeId==nodes[0].parentTId&&moveType==consts.move.TYPE_INNER){tmpTarget=null;}if(tmpTarget){var dropCallback=function dropCallback(){if(isOtherTree){if(!isCopy){for(var i=0,l=nodes.length;i<l;i++){view.removeNode(setting,nodes[i]);}}if(moveType==consts.move.TYPE_INNER){view.addNodes(targetSetting,dragTargetNode,-1,newNodes);}else{view.addNodes(targetSetting,dragTargetNode.getParentNode(),moveType==consts.move.TYPE_PREV?dragTargetNode.getIndex():dragTargetNode.getIndex()+1,newNodes);}}else{if(isCopy&&moveType==consts.move.TYPE_INNER){view.addNodes(targetSetting,dragTargetNode,-1,newNodes);}else if(isCopy){view.addNodes(targetSetting,dragTargetNode.getParentNode(),moveType==consts.move.TYPE_PREV?dragTargetNode.getIndex():dragTargetNode.getIndex()+1,newNodes);}else{if(moveType!=consts.move.TYPE_NEXT){for(i=0,l=newNodes.length;i<l;i++){view.moveNode(targetSetting,dragTargetNode,newNodes[i],moveType,false);}}else{for(i=-1,l=newNodes.length-1;i<l;l--){view.moveNode(targetSetting,dragTargetNode,newNodes[l],moveType,false);}}}}view.selectNodes(targetSetting,newNodes);var a=$$(newNodes[0],setting).get(0);view.scrollIntoView(a);setting.treeObj.trigger(consts.event.DROP,[event,targetSetting.treeId,newNodes,dragTargetNode,moveType,isCopy]);};var dragTargetNode=tmpTargetNodeId==null?null:data.getNodeCache(targetSetting,tmpTargetNodeId);if(tools.apply(setting.callback.beforeDrop,[targetSetting.treeId,nodes,dragTargetNode,moveType,isCopy],true)==false){view.selectNodes(sourceSetting,nodes);return;}var newNodes=isCopy?tools.clone(nodes):nodes;if(moveType==consts.move.TYPE_INNER&&tools.canAsync(targetSetting,dragTargetNode)){view.asyncNode(targetSetting,dragTargetNode,false,dropCallback);}else{dropCallback();}}else{view.selectNodes(sourceSetting,nodes);setting.treeObj.trigger(consts.event.DROP,[event,setting.treeId,nodes,null,null,null]);}}doc.bind("selectstart",_docSelect);function _docSelect(){return false;}//Avoid FireFox's Bug
	//If zTree Div CSS set 'overflow', so drag node outside of zTree, and event.target is error.
	if(eventMouseDown.preventDefault){eventMouseDown.preventDefault();}return true;}},//method of tools for zTree
	_tools={getAbs:function getAbs(obj){var oRect=obj.getBoundingClientRect(),scrollTop=document.body.scrollTop+document.documentElement.scrollTop,scrollLeft=document.body.scrollLeft+document.documentElement.scrollLeft;return[oRect.left+scrollLeft,oRect.top+scrollTop];},inputFocus:function inputFocus(inputObj){if(inputObj.get(0)){inputObj.focus();tools.setCursorPosition(inputObj.get(0),inputObj.val().length);}},inputSelect:function inputSelect(inputObj){if(inputObj.get(0)){inputObj.focus();inputObj.select();}},setCursorPosition:function setCursorPosition(obj,pos){if(obj.setSelectionRange){obj.focus();obj.setSelectionRange(pos,pos);}else if(obj.createTextRange){var range=obj.createTextRange();range.collapse(true);range.moveEnd('character',pos);range.moveStart('character',pos);range.select();}},showIfameMask:function showIfameMask(setting,showSign){var root=data.getRoot(setting);//clear full mask
	while(root.dragMaskList.length>0){root.dragMaskList[0].remove();root.dragMaskList.shift();}if(showSign){//show mask
	var iframeList=$$("iframe",setting);for(var i=0,l=iframeList.length;i<l;i++){var obj=iframeList.get(i),r=tools.getAbs(obj),dragMask=$$("<div id='zTreeMask_"+i+"' class='zTreeMask' style='top:"+r[1]+"px; left:"+r[0]+"px; width:"+obj.offsetWidth+"px; height:"+obj.offsetHeight+"px;'></div>",setting);dragMask.appendTo($$("body",setting));root.dragMaskList.push(dragMask);}}}},//method of operate ztree dom
	_view={addEditBtn:function addEditBtn(setting,node){if(node.editNameFlag||$$(node,consts.id.EDIT,setting).length>0){return;}if(!tools.apply(setting.edit.showRenameBtn,[setting.treeId,node],setting.edit.showRenameBtn)){return;}var aObj=$$(node,consts.id.A,setting),editStr="<span class='"+consts.className.BUTTON+" edit' id='"+node.tId+consts.id.EDIT+"' title='"+tools.apply(setting.edit.renameTitle,[setting.treeId,node],setting.edit.renameTitle)+"' treeNode"+consts.id.EDIT+" style='display:none;'></span>";aObj.append(editStr);$$(node,consts.id.EDIT,setting).bind('click',function(){if(!tools.uCanDo(setting)||tools.apply(setting.callback.beforeEditName,[setting.treeId,node],true)==false)return false;view.editNode(setting,node);return false;}).show();},addRemoveBtn:function addRemoveBtn(setting,node){if(node.editNameFlag||$$(node,consts.id.REMOVE,setting).length>0){return;}if(!tools.apply(setting.edit.showRemoveBtn,[setting.treeId,node],setting.edit.showRemoveBtn)){return;}var aObj=$$(node,consts.id.A,setting),removeStr="<span class='"+consts.className.BUTTON+" remove' id='"+node.tId+consts.id.REMOVE+"' title='"+tools.apply(setting.edit.removeTitle,[setting.treeId,node],setting.edit.removeTitle)+"' treeNode"+consts.id.REMOVE+" style='display:none;'></span>";aObj.append(removeStr);$$(node,consts.id.REMOVE,setting).bind('click',function(){if(!tools.uCanDo(setting)||tools.apply(setting.callback.beforeRemove,[setting.treeId,node],true)==false)return false;view.removeNode(setting,node);setting.treeObj.trigger(consts.event.REMOVE,[setting.treeId,node]);return false;}).bind('mousedown',function(eventMouseDown){return true;}).show();},addHoverDom:function addHoverDom(setting,node){if(data.getRoots().showHoverDom){node.isHover=true;if(setting.edit.enable){view.addEditBtn(setting,node);view.addRemoveBtn(setting,node);}tools.apply(setting.view.addHoverDom,[setting.treeId,node]);}},cancelCurEditNode:function cancelCurEditNode(setting,forceName,isCancel){var root=data.getRoot(setting),nameKey=setting.data.key.name,node=root.curEditNode;if(node){var inputObj=root.curEditInput,newName=forceName?forceName:isCancel?node[nameKey]:inputObj.val();if(tools.apply(setting.callback.beforeRename,[setting.treeId,node,newName,isCancel],true)===false){return false;}node[nameKey]=newName;var aObj=$$(node,consts.id.A,setting);aObj.removeClass(consts.node.CURSELECTED_EDIT);inputObj.unbind();view.setNodeName(setting,node);node.editNameFlag=false;root.curEditNode=null;root.curEditInput=null;view.selectNode(setting,node,false);setting.treeObj.trigger(consts.event.RENAME,[setting.treeId,node,isCancel]);}root.noSelection=true;return true;},editNode:function editNode(setting,node){var root=data.getRoot(setting);view.editNodeBlur=false;if(data.isSelectedNode(setting,node)&&root.curEditNode==node&&node.editNameFlag){setTimeout(function(){tools.inputFocus(root.curEditInput);},0);return;}var nameKey=setting.data.key.name;node.editNameFlag=true;view.removeTreeDom(setting,node);view.cancelCurEditNode(setting);view.selectNode(setting,node,false);$$(node,consts.id.SPAN,setting).html("<input type=text class='rename' id='"+node.tId+consts.id.INPUT+"' treeNode"+consts.id.INPUT+" >");var inputObj=$$(node,consts.id.INPUT,setting);inputObj.attr("value",node[nameKey]);if(setting.edit.editNameSelectAll){tools.inputSelect(inputObj);}else{tools.inputFocus(inputObj);}inputObj.bind('blur',function(event){if(!view.editNodeBlur){view.cancelCurEditNode(setting);}}).bind('keydown',function(event){if(event.keyCode=="13"){view.editNodeBlur=true;view.cancelCurEditNode(setting);}else if(event.keyCode=="27"){view.cancelCurEditNode(setting,null,true);}}).bind('click',function(event){return false;}).bind('dblclick',function(event){return false;});$$(node,consts.id.A,setting).addClass(consts.node.CURSELECTED_EDIT);root.curEditInput=inputObj;root.noSelection=false;root.curEditNode=node;},moveNode:function moveNode(setting,targetNode,node,moveType,animateFlag,isSilent){var root=data.getRoot(setting),childKey=setting.data.key.children;if(targetNode==node)return;if(setting.data.keep.leaf&&targetNode&&!targetNode.isParent&&moveType==consts.move.TYPE_INNER)return;var oldParentNode=node.parentTId?node.getParentNode():root,targetNodeIsRoot=targetNode===null||targetNode==root;if(targetNodeIsRoot&&targetNode===null)targetNode=root;if(targetNodeIsRoot)moveType=consts.move.TYPE_INNER;var targetParentNode=targetNode.parentTId?targetNode.getParentNode():root;if(moveType!=consts.move.TYPE_PREV&&moveType!=consts.move.TYPE_NEXT){moveType=consts.move.TYPE_INNER;}if(moveType==consts.move.TYPE_INNER){if(targetNodeIsRoot){//parentTId of root node is null
	node.parentTId=null;}else{if(!targetNode.isParent){targetNode.isParent=true;targetNode.open=!!targetNode.open;view.setNodeLineIcos(setting,targetNode);}node.parentTId=targetNode.tId;}}//move node Dom
	var targetObj,target_ulObj;if(targetNodeIsRoot){targetObj=setting.treeObj;target_ulObj=targetObj;}else{if(!isSilent&&moveType==consts.move.TYPE_INNER){view.expandCollapseNode(setting,targetNode,true,false);}else if(!isSilent){view.expandCollapseNode(setting,targetNode.getParentNode(),true,false);}targetObj=$$(targetNode,setting);target_ulObj=$$(targetNode,consts.id.UL,setting);if(!!targetObj.get(0)&&!target_ulObj.get(0)){var ulstr=[];view.makeUlHtml(setting,targetNode,ulstr,'');targetObj.append(ulstr.join(''));}target_ulObj=$$(targetNode,consts.id.UL,setting);}var nodeDom=$$(node,setting);if(!nodeDom.get(0)){nodeDom=view.appendNodes(setting,node.level,[node],null,-1,false,true).join('');}else if(!targetObj.get(0)){nodeDom.remove();}if(target_ulObj.get(0)&&moveType==consts.move.TYPE_INNER){target_ulObj.append(nodeDom);}else if(targetObj.get(0)&&moveType==consts.move.TYPE_PREV){targetObj.before(nodeDom);}else if(targetObj.get(0)&&moveType==consts.move.TYPE_NEXT){targetObj.after(nodeDom);}//repair the data after move
	var i,l,tmpSrcIndex=-1,tmpTargetIndex=0,oldNeighbor=null,newNeighbor=null,oldLevel=node.level;if(node.isFirstNode){tmpSrcIndex=0;if(oldParentNode[childKey].length>1){oldNeighbor=oldParentNode[childKey][1];oldNeighbor.isFirstNode=true;}}else if(node.isLastNode){tmpSrcIndex=oldParentNode[childKey].length-1;oldNeighbor=oldParentNode[childKey][tmpSrcIndex-1];oldNeighbor.isLastNode=true;}else{for(i=0,l=oldParentNode[childKey].length;i<l;i++){if(oldParentNode[childKey][i].tId==node.tId){tmpSrcIndex=i;break;}}}if(tmpSrcIndex>=0){oldParentNode[childKey].splice(tmpSrcIndex,1);}if(moveType!=consts.move.TYPE_INNER){for(i=0,l=targetParentNode[childKey].length;i<l;i++){if(targetParentNode[childKey][i].tId==targetNode.tId)tmpTargetIndex=i;}}if(moveType==consts.move.TYPE_INNER){if(!targetNode[childKey])targetNode[childKey]=new Array();if(targetNode[childKey].length>0){newNeighbor=targetNode[childKey][targetNode[childKey].length-1];newNeighbor.isLastNode=false;}targetNode[childKey].splice(targetNode[childKey].length,0,node);node.isLastNode=true;node.isFirstNode=targetNode[childKey].length==1;}else if(targetNode.isFirstNode&&moveType==consts.move.TYPE_PREV){targetParentNode[childKey].splice(tmpTargetIndex,0,node);newNeighbor=targetNode;newNeighbor.isFirstNode=false;node.parentTId=targetNode.parentTId;node.isFirstNode=true;node.isLastNode=false;}else if(targetNode.isLastNode&&moveType==consts.move.TYPE_NEXT){targetParentNode[childKey].splice(tmpTargetIndex+1,0,node);newNeighbor=targetNode;newNeighbor.isLastNode=false;node.parentTId=targetNode.parentTId;node.isFirstNode=false;node.isLastNode=true;}else{if(moveType==consts.move.TYPE_PREV){targetParentNode[childKey].splice(tmpTargetIndex,0,node);}else{targetParentNode[childKey].splice(tmpTargetIndex+1,0,node);}node.parentTId=targetNode.parentTId;node.isFirstNode=false;node.isLastNode=false;}data.fixPIdKeyValue(setting,node);data.setSonNodeLevel(setting,node.getParentNode(),node);//repair node what been moved
	view.setNodeLineIcos(setting,node);view.repairNodeLevelClass(setting,node,oldLevel);//repair node's old parentNode dom
	if(!setting.data.keep.parent&&oldParentNode[childKey].length<1){//old parentNode has no child nodes
	oldParentNode.isParent=false;oldParentNode.open=false;var tmp_ulObj=$$(oldParentNode,consts.id.UL,setting),tmp_switchObj=$$(oldParentNode,consts.id.SWITCH,setting),tmp_icoObj=$$(oldParentNode,consts.id.ICON,setting);view.replaceSwitchClass(oldParentNode,tmp_switchObj,consts.folder.DOCU);view.replaceIcoClass(oldParentNode,tmp_icoObj,consts.folder.DOCU);tmp_ulObj.css("display","none");}else if(oldNeighbor){//old neigbor node
	view.setNodeLineIcos(setting,oldNeighbor);}//new neigbor node
	if(newNeighbor){view.setNodeLineIcos(setting,newNeighbor);}//repair checkbox / radio
	if(!!setting.check&&setting.check.enable&&view.repairChkClass){view.repairChkClass(setting,oldParentNode);view.repairParentChkClassWithSelf(setting,oldParentNode);if(oldParentNode!=node.parent)view.repairParentChkClassWithSelf(setting,node);}//expand parents after move
	if(!isSilent){view.expandCollapseParentNode(setting,node.getParentNode(),true,animateFlag);}},removeEditBtn:function removeEditBtn(setting,node){$$(node,consts.id.EDIT,setting).unbind().remove();},removeRemoveBtn:function removeRemoveBtn(setting,node){$$(node,consts.id.REMOVE,setting).unbind().remove();},removeTreeDom:function removeTreeDom(setting,node){node.isHover=false;view.removeEditBtn(setting,node);view.removeRemoveBtn(setting,node);tools.apply(setting.view.removeHoverDom,[setting.treeId,node]);},repairNodeLevelClass:function repairNodeLevelClass(setting,node,oldLevel){if(oldLevel===node.level)return;var liObj=$$(node,setting),aObj=$$(node,consts.id.A,setting),ulObj=$$(node,consts.id.UL,setting),oldClass=consts.className.LEVEL+oldLevel,newClass=consts.className.LEVEL+node.level;liObj.removeClass(oldClass);liObj.addClass(newClass);aObj.removeClass(oldClass);aObj.addClass(newClass);ulObj.removeClass(oldClass);ulObj.addClass(newClass);},selectNodes:function selectNodes(setting,nodes){for(var i=0,l=nodes.length;i<l;i++){view.selectNode(setting,nodes[i],i>0);}}},_z={tools:_tools,view:_view,event:_event,data:_data};$.extend(true,$.fn.zTree.consts,_consts);$.extend(true,$.fn.zTree._z,_z);var zt=$.fn.zTree,tools=zt._z.tools,consts=zt.consts,view=zt._z.view,data=zt._z.data,event=zt._z.event,$$=tools.$;data.exSetting(_setting);data.addInitBind(_bindEvent);data.addInitUnBind(_unbindEvent);data.addInitCache(_initCache);data.addInitNode(_initNode);data.addInitProxy(_eventProxy);data.addInitRoot(_initRoot);data.addZTreeTools(_zTreeTools);var _cancelPreSelectedNode=view.cancelPreSelectedNode;view.cancelPreSelectedNode=function(setting,node){var list=data.getRoot(setting).curSelectedList;for(var i=0,j=list.length;i<j;i++){if(!node||node===list[i]){view.removeTreeDom(setting,list[i]);if(node)break;}}if(_cancelPreSelectedNode)_cancelPreSelectedNode.apply(view,arguments);};var _createNodes=view.createNodes;view.createNodes=function(setting,level,nodes,parentNode,index){if(_createNodes){_createNodes.apply(view,arguments);}if(!nodes)return;if(view.repairParentChkClassWithSelf){view.repairParentChkClassWithSelf(setting,parentNode);}};var _makeNodeUrl=view.makeNodeUrl;view.makeNodeUrl=function(setting,node){return setting.edit.enable?null:_makeNodeUrl.apply(view,arguments);};var _removeNode=view.removeNode;view.removeNode=function(setting,node){var root=data.getRoot(setting);if(root.curEditNode===node)root.curEditNode=null;if(_removeNode){_removeNode.apply(view,arguments);}};var _selectNode=view.selectNode;view.selectNode=function(setting,node,addFlag){var root=data.getRoot(setting);if(data.isSelectedNode(setting,node)&&root.curEditNode==node&&node.editNameFlag){return false;}if(_selectNode)_selectNode.apply(view,arguments);view.addHoverDom(setting,node);return true;};var _uCanDo=tools.uCanDo;tools.uCanDo=function(setting,e){var root=data.getRoot(setting);if(e&&(tools.eqs(e.type,"mouseover")||tools.eqs(e.type,"mouseout")||tools.eqs(e.type,"mousedown")||tools.eqs(e.type,"mouseup"))){return true;}if(root.curEditNode){view.editNodeBlur=false;root.curEditInput.focus();}return!root.curEditNode&&(_uCanDo?_uCanDo.apply(view,arguments):true);};})(jQuery);

/***/ },
/* 182 */
/***/ function(module, exports) {

	module.exports = "<div id = \"i-user-org\">\n    <div id=\"ztree\" class=\"ztree\"></div>\n  </div>";

/***/ }
/******/ ])
});
;
//# sourceMappingURL=Ztree.js.map