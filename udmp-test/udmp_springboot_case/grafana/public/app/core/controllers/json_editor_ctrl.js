/*! grafana - v4.1.2 - 2017-02-13
 * Copyright (c) 2017 Torkel Ödegaard; Licensed Apache-2.0 */

define(["angular","../core_module"],function(a,b){"use strict";b.default.controller("JsonEditorCtrl",["$scope",function(b){b.json=a.toJson(b.object,!0),b.canUpdate=void 0!==b.updateHandler&&b.contextSrv.isEditor,b.update=function(){var c=a.fromJson(b.json);b.updateHandler(c,b.object)}}])});