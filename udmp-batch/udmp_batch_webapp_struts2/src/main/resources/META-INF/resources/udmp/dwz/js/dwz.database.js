/**
 * @author ZhangHuihua@msn.com
 * 2014/08/01 更改lookup_hidden 生成隐藏input的位置，更改为td的第一个input
 * 2014/10/10 为lookup_hidden 和lookup添加myOption属性，为其a标签触发事件前进行操作
 */
(function($) {
	var _lookup = {
		currentGroup : "",
		suffix : "",
		$target : null,
		pk : "id"
	};
	var _util = {
		_lookupPrefix : function(key) {
			var strDot = _lookup.currentGroup ? "." : "";
			return _lookup.currentGroup + strDot + key + _lookup.suffix;
		},
		lookupPk : function(key) {
			return this._lookupPrefix(key);
		},
		lookupField : function(key) {
			return this.lookupPk(key);
		}
	};

	$.extend({
		bringBackSuggest : function(args) {
			var $box = _lookup['$target'].parents(".unitBox:first");
			$box.find(":input").each(
					function() {
						var $input = $(this), inputName = $input.attr("name");
						for ( var key in args) {
							var name = (_lookup.pk == key) ? _util
									.lookupPk(key) : _util.lookupField(key);
							if (name == inputName) {
								$input.val(args[key]);
								break;
							}
						}
					});
		},
		bringBack : function(args) {
			$.bringBackSuggest(args);
			$.pdialog.closeCurrent();
		}
	});

	$.fn.extend({lookup : function() {
		return this.each(function() {
			var $this = $(this), options = {
				mask : true,
				width : $this.attr('width') || 820,
				height : $this.attr('height') || 400,
				maxable : eval($this.attr("maxable") || "true"),
				resizable : eval($this.attr("resizable") || "true")
			};
			$this.click(function(event) {
				_lookup = $.extend(_lookup, {
					currentGroup : $this.attr("lookupGroup") || "",
					suffix : $this.attr("suffix") || "",
					$target : $this,
					pk : $this.attr("lookupPk") || "id"
				});
				// ********添加自定义判断程序 add begin****************
								var myOption = $this.attr("myOption");
								if (null != myOption && myOption != '') {
									var myFlag = eval(myOption);
									if (!myFlag)
										return myFlag;
								}
				// ********添加自定义判断程序 add end****************
				var url = unescape($this.attr("href"))
						.replaceTmById(
								$(event.target).parents(
										".unitBox:first"));
				if (!url.isFinishedTm()) {
					alertMsg.warn($this.attr("warn")
							|| DWZ.msg("alertSelectMsg"));
					return false;
				}

				

				$.pdialog.open(url, "_blank", $this.attr("title")
						|| $this.text(), options);
				return false;
			});
		});},
		multLookup : function() {
					return this.each(function() {
						var $this = $(this), args = {};
						$this.click(function(event) {
							var $unitBox = $this.parents(".unitBox:first");
							$unitBox.find("[name='"+ $this.attr("multLookup")+ "']").filter(":checked").each(
								function() {
									var _args = DWZ
											.jsonEval($(
													this)
													.val());
									for ( var key in _args) {
										var value = args[key] ? args[key] + ",": "";
										args[key] = value + _args[key];
									}
							});

							if ($.isEmptyObject(args)) {
								alertMsg.warn(
										$this.attr("warn")|| DWZ.msg("alertSelectMsg"));
								return false;
							}
							$.bringBack(args);
						});
					});
				},
				suggest : function() {
					var op = {
						suggest$ : "#suggest",
						suggestShadow$ : "#suggestShadow"
					};
					var selectedIndex = -1;
					return this.each(function() {
						var $input = $(this).attr('autocomplete', 'off').keydown(
							function(event) {
								if (event.keyCode == DWZ.keyCode.ENTER
										&& $(op.suggest$)
												.is(
														':visible'))
									return false; // 屏蔽回车提交
							}
						);
					var suggestFields = $input.attr('suggestFields').split(",");
					function _show(event) {
						var offset = $input.offset();
						var iTop = offset.top + this.offsetHeight;
						var $suggest = $(op.suggest$);
						if ($suggest.size() == 0)
							$suggest = $('<div id="suggest"></div>').appendTo($('body'));

						$suggest.css({
							left : offset.left + 'px',
							top : iTop + 'px'
						}).show();

						_lookup = $.extend(_lookup, {
							currentGroup : $input
									.attr("lookupGroup")
									|| "",
							suffix : $input.attr("suffix") || "",
							$target : $input,
							pk : $input.attr("lookupPk") || "id"
						});

						var url = unescape(
							$input.attr("suggestUrl")).replaceTmById(
								$(event.target).parents(".unitBox:first"));
						if (!url.isFinishedTm()) {
							alertMsg.warn($input.attr("warn")
									|| DWZ.msg("alertSelectMsg"));
							return false;
						}

						var postData = {};
						postData[$input.attr("")
								|| "inputValue"] = $input.val();
						$.ajax({
							global : false,
							type : 'POST',
							dataType : "json",
							url : url,
							cache : false,
							data : postData,
							success : function(response) {
								if (!response)
									return;
								var html = '';
								$.each(response,function(i) {
									var liAttr = '', liLabel = '';
									for (var i = 0; i < suggestFields.length; i++) {
										var str = this[suggestFields[i]];
										if (str) {
											if (liLabel)
												liLabel += '-';
											liLabel += str;
										}
									}
									for ( var key in this) {
										if (liAttr)
											liAttr += ',';
										liAttr += key + ":'" + this[key] + "'";
									}
									html += '<li lookupAttrs="' + liAttr + '">' + liLabel + '</li>';
								});

								var $lis = $suggest.html('<ul>' + html + '</ul>').find("li");
								$lis.hoverClass("selected").click(
									function() {
										_select($(this));
									});
								if ($lis.size() == 1
										&& event.keyCode != DWZ.keyCode.BACKSPACE) {
									_select($lis.eq(0));
								} else if ($lis.size() == 0) {
									var jsonStr = "";
									for (var i = 0; i < suggestFields.length; i++) {
										if (_util.lookupField(suggestFields[i]) == event.target.name) {
											break;
										}
										if (jsonStr)
											jsonStr += ',';
											jsonStr += suggestFields[i] + ":''";
									}
									jsonStr = "{"+ _lookup.pk+ ":'',"+ jsonStr + "}";
									$.bringBackSuggest(DWZ.jsonEval(jsonStr));
								}
							},
							error : function() {
								$suggest.html('');
							}
						});

						$(document).bind("click", _close);
							return false;
						}
						function _select($item) {
							var jsonStr = "{"
									+ $item.attr('lookupAttrs') + "}";
							$.bringBackSuggest(DWZ.jsonEval(jsonStr));
						}
						function _close() {
							$(op.suggest$).html('').hide();
							selectedIndex = -1;
							$(document).unbind("click", _close);
						}
		
						$input.focus(_show).click(false).keyup(
								function(event) {
									var $items = $(op.suggest$).find(
											"li");
									switch (event.keyCode) {
									case DWZ.keyCode.ESC:
									case DWZ.keyCode.TAB:
									case DWZ.keyCode.SHIFT:
									case DWZ.keyCode.HOME:
									case DWZ.keyCode.END:
									case DWZ.keyCode.LEFT:
									case DWZ.keyCode.RIGHT:
										break;
									case DWZ.keyCode.ENTER:
										_close();
										break;
									case DWZ.keyCode.DOWN:
										if (selectedIndex >= $items
												.size() - 1)
											selectedIndex = -1;
										else
											selectedIndex++;
										break;
									case DWZ.keyCode.UP:
										if (selectedIndex < 0)
											selectedIndex = $items
													.size() - 1;
										else
											selectedIndex--;
										break;
									default:
										_show(event);
									}
									$items.removeClass("selected");
									if (selectedIndex >= 0) {
										var $item = $items.eq(
												selectedIndex)
												.addClass("selected");
										_select($item);
									}
								});
							});
				},

				itemDetail : function() {
					return this.each(function() {
								var $table = $(this).css("clear", "both"), 
								$tbody = $table.find("tbody");
								var fields = [];
								//遍历table中第一行中的所有th属性,得到其属性并将其放入到fields中
								$table.find("tr:first th[type]").each(
									function(i) {
										var $th = $(this);
										var field = {
											type : $th.attr("type")|| "text",
											patternDate : $th.attr("dateFmt")|| "yyyy-MM-dd",
											name : $th.attr("name")|| "",
											defaultVal : $th.attr("defaultVal")|| "",
											size : $th.attr("size")|| "12",
											enumUrl : $th.attr("enumUrl")|| "",
											lookupGroup : $th.attr("lookupGroup")|| "",
											lookupUrl : $th.attr("lookupUrl")|| "",
											lookupPk : $th.attr("lookupPk")|| "id",
											suggestUrl : $th.attr("suggestUrl"),
											suggestFields : $th.attr("suggestFields"),
											postField : $th.attr("postField")|| "",
											fieldClass : $th.attr("fieldClass")|| "",
											fieldAttrs : $th.attr("fieldAttrs")|| "",
											hiddenAttr : $th.attr("hiddenAttr")|| "",
											myOption: $th.attr("myOption") ||""
										};
										fields.push(field);
								});
								//监听 a.btnDel 的点击事件
								$tbody.find("a.btnDel").click(
									function() {
										var $btnDel = $(this);
										//若$btnDel的href属性是以javascript开头
										if ($btnDel.is("[href^=javascript:]")) {
											//删除该行
											$btnDel.parents("tr:first").remove();
											//重新初始化tbody，对其中index进行处理
											initSuffix($tbody);
											return false;
										}
										//调用ajax处理
										function delDbData() {
											$.ajax({
												type : 'POST',
												dataType : "json",
												url : $btnDel.attr('href'),
												cache : false,
												success : function() {
													//删除该行
													$btnDel.parents("tr:first").remove();
													//重新初始化下标
													initSuffix($tbody);
												},
												error : DWZ.ajaxError
											});
										}
										//若href属性不是javascript开头的，并且a标签的title属性不为空
										if ($btnDel.attr("title")) {
											//弹出提示框
											alertMsg.confirm($btnDel.attr("title"),
											{
												okCall : delDbData
											});
										} else {
											//否则直接执行delDbData()方法
											delDbData();
										}

										return false;
									});
								//取得addButton的值，若为设置，默认将其设置为Add New
								var addButTxt = $table.attr('addButton')
										|| "Add New";
								if (addButTxt) {
									//创建新增一行按钮 将其放到table的前面
									var $addBut = $(
											'<div class="button"><div class="buttonContent"><button type="button">'
													+ addButTxt + '</button></div></div>').insertBefore($table).find("button");
									//创建新增行数的输入框 将其放到table的前面
									var $rowNum = $(
											'<input type="text" name="dwz_rowNum" class="textInput" style="margin:2px;" value="1" size="2"/>')
											.insertBefore($table);

									var trTm = "";
									$addBut.click(function() {
										if (!trTm)
											trTm = trHtml(fields);
										var rowNum = 1;
										try {
											rowNum = parseInt($rowNum.val())
										} catch (e) {
										}

										for (var i = 0; i < rowNum; i++) {
											var $tr = $(trTm);
											$tr.appendTo($tbody).initUI().find("a.btnDel").click(
												function() {
													$(this).parents("tr:first").remove();
													initSuffix($tbody);
													return false;
												});
										}
										initSuffix($tbody);
									});
								}
							});

					/**
					 * 删除时重新初始化下标
					 */
					function initSuffix($tbody) {
						//遍历tbody中所有的tr元素
						$tbody.find('>tr').each(function(i) {
							//找到tbody中所有input,textarea,select,button,a.btnLook,a.btnAttach 元素
							$(':input, a.btnLook, a.btnAttach,',this).each(function() {
								var $this = $(this), 
								id = $this.attr("id"),name = $this.attr('name'), val = $this.val();
								//H 替换id中的#index#
								if(id && id.indexOf("#index#") >= 0){
									$this.attr("id",id.replaceSuffix(i));
								}
								if (name) {
									$this.attr('name',name.replaceSuffix(i));
								}
								var lookupGroup = $this.attr('lookupGroup');
								if (lookupGroup) {
									$this.attr('lookupGroup',lookupGroup.replaceSuffix(i));
								}
								var suffix = $this.attr("suffix");
								if (suffix) { 
									$this.attr('suffix',suffix.replaceSuffix(i));
								}
								if (val&& val.indexOf("#index#") >= 0)
									$this.val(val.replace('#index#',i + 1));
								});
						});
					}
					//H 创建td的方法
					function tdHtml(field) {
						var html = '', suffix = '';
						//H 若field.name是以[#index#]结尾 后缀设置为[#index#] 
						if (field.name.endsWith("[#index#]")){
							suffix = "[#index#]";
						}
						//H 若field.name是以[]结尾 后缀设置为[] 
						else if (field.name.endsWith("[]")){
							suffix = "[]";
						}
						//H 后缀标志
						var suffixFrag = suffix ? ' suffix="' + suffix + '" ': '';
						//H 属性标志 用来定义特殊的属性
						var attrFrag = '';
						//H 若th设置了fieldAttrs 属性
						if (field.fieldAttrs) {
							//H 字符串转换成对象
							var attrs = DWZ.jsonEval(field.fieldAttrs);
							//H 将attrs中的属性值以 key=value 的形式拼接
							for ( var key in attrs) {
								attrFrag += key + '="' + attrs[key] + '"';
							}
						}
						//H 保存field.type 
						var expand_type = field.type;
						//H 判断是否是自定义的拓展组件
						if (field.type.indexOf("expand") != -1) {
							field.type = "expand";
						}
						switch (field.type) {
							//H 增加删除按钮
							case 'del':
								html = '<a href="javascript:void(0)" class="btnDel '
										+ field.fieldClass + '">删除</a>';
								break;
							//H 查找带回
							case 'lookup':
								var suggestFrag = '';
								if (field.suggestFields) {
									suggestFrag = 'autocomplete="off" lookupGroup="'
											+ field.lookupGroup
											+ '"'
											+ suffixFrag
											+ ' suggestUrl="'
											+ field.suggestUrl
											+ '" suggestFields="'
											+ field.suggestFields
											+ '"'
											+ ' postField="'
											+ field.postField
											+ '"';
								}
	
								html = '<input type="hidden" name="'
										+ field.lookupGroup + '.' + field.lookupPk
										+ suffix + ' "/>'
										+ '<input type="text" name="' + field.name
										+ '"' + suggestFrag + ' lookupPk="'
										+ field.lookupPk + '" size="' + field.size
										+ '" class="' + field.fieldClass + '"/>'
										+ '<a class="btnLook" href="'
										+ field.lookupUrl + '" lookupGroup="'
										+ field.lookupGroup + '" ' + suggestFrag
										+ ' lookupPk="' + field.lookupPk
										+ '" title="查找带回" myOption="' + field.myOption + '">查找带回</a>';
								break;
							//H 附件
							case 'attach':
								html = '<input type="hidden" name="'
										+ field.lookupGroup
										+ '.'
										+ field.lookupPk
										+ suffix
										+ '"/>'
										+ '<input type="text" name="'
										+ field.name
										+ '" size="'
										+ field.size
										+ '" readonly="readonly" class="'
										+ field.fieldClass
										+ '"/>'
										+ '<a class="btnAttach" href="'
										+ field.lookupUrl
										+ '" lookupGroup="'
										+ field.lookupGroup
										+ '" '
										+ suggestFrag
										+ ' lookupPk="'
										+ field.lookupPk
										+ '" width="560" height="300" title="查找带回">查找带回</a>';
								break;
							//H 枚举
							case 'enum':
								$.ajax({
									type : "POST",
									dataType : "html",
									async : false,
									url : field.enumUrl,
									data : {
										inputName : field.name
									},
									success : function(response) {
										html = response;
									}
								});
								break;
							//H 日期
							case 'date':
								html = '<input type="text" name="' + field.name + '" value="' + field.defaultVal 
										+ '" class="date ' + field.fieldClass + '" dateFmt="' + field.patternDate
										+ '" size="' + field.size + '"' + attrFrag + '/>'
										+ '<a class="inputDateButton" href="javascript:void(0)">选择</a>';
								break;
							//H 新增支持拓展组件
							case 'expand':
								html = '<input type="' + expand_type + '" name="'
										+ field.name + '" value="'
										+ field.defaultVal + '" size="'
										+ field.size + '" class="'
										+ field.fieldClass + '" ' + attrFrag + '/>';
								break;
							//H 查找带回并增加隐藏属性
							case 'lookup_hidden':
								var suggestFrag = '';
								if (field.suggestFields) {
									suggestFrag = 'autocomplete="off" lookupGroup="'
											+ field.lookupGroup + '"' + suffixFrag
											+ ' suggestUrl="' + field.suggestUrl
											+ '" suggestFields="' + field.suggestFields
											+ '"' + ' postField="' + field.postField + '"';
								}
								html = '<input type="hidden" name="'
									+ field.hiddenAttr
									+ '" id="hdn_'
									+ field.hiddenAttr
									+ '"/>';
								html += '<input type="text" name="' + field.name
										+ '"' + suggestFrag + ' lookupPk="'
										+ field.lookupPk + '" size="' + field.size
										+ '" class="' + field.fieldClass + '"/>'
										+ '<a class="btnLook" href="'
										+ field.lookupUrl + '" lookupGroup="'
										+ field.lookupGroup + '" ' + suggestFrag
										+ ' lookupPk="' + field.lookupPk
										+ '" title="查找带回" myOption="' + field.myOption + '">查找带回</a>';
								break;
							//H 默认值为text
							default:
								html = '<input type="text" name="' + field.name
										+ '" value="' + field.defaultVal
										+ '" size="' + field.size + '" class="'
										+ field.fieldClass + '" ' + attrFrag + '/>';
								break;
							}
						//H 将生成input放入td中
						return '<td>' + html + '</td>';
					}
					function trHtml(fields) {
						var html = '';
						$(fields).each(function() {
							//H 拼接所有的td
							html += tdHtml(this);
						});
						//H 将生成的tds 放入 tr.unitBox 中
						return '<tr class="unitBox">' + html + '</tr>';
					}
				},

				selectedTodo : function() {

					function _getIds(selectedIds, targetType) {
						var ids = "";
						var $box = targetType == "dialog" ? $.pdialog
								.getCurrent() : navTab.getCurrentPanel();
						$box.find("input:checked").filter(
								"[name='" + selectedIds + "']").each(
								function(i) {
									var val = $(this).val();
									ids += i == 0 ? val : "," + val;
								});
						return ids;
					}
					return this.each(function() {
						var $this = $(this);
						var selectedIds = $this.attr("rel") || "ids";
						var postType = $this.attr("postType") || "map";

						$this.click(function() {
							var targetType = $this.attr("targetType");
							var ids = _getIds(selectedIds, targetType);
							if (!ids) {
								alertMsg.warn($this.attr("warn")
										|| DWZ.msg("alertSelectMsg"));
								return false;
							}

							var _callback = $this.attr("callback")
									|| (targetType == "dialog" ? dialogAjaxDone
											: navTabAjaxDone);
							if (!$.isFunction(_callback))
								_callback = eval('(' + _callback + ')');

							//********添加自定义判断程序 add LiAnDong begin****************
							var myOption = $this.attr("myOption");
							if (null != myOption && myOption != '') {
								var myFlag = eval(myOption);
								if (!myFlag)
									return myFlag;
							}
							//********添加自定义判断程序 add LiAnDong end****************

							function _doPost() {
								$.ajax({
									type : 'POST',
									url : $this.attr('href'),
									dataType : 'json',
									cache : false,
									data : function() {
										if (postType == 'map') {
											return $.map(ids.split(','),
													function(val, i) {
														return {
															name : selectedIds,
															value : val
														};
													})
										} else {
											var _data = {};
											_data[selectedIds] = ids;
											return _data;
										}
									}(),
									success : _callback,
									error : DWZ.ajaxError
								});
							}
							var title = $this.attr("title");
							if (title) {
								alertMsg.confirm(title, {
									okCall : _doPost
								});
							} else {
								_doPost();
							}
							return false;
						});

					});
				}
			});
})(jQuery);
