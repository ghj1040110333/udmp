/**
 * @author Roger Wu
 * 2014/07/25  修正关闭dialog时 下拉框不关闭bug
 * 2014/08/14  修正select.combox 不能设置disabled属性bug
 * 2014/09/19  增加struts2 对 dwz combox组件的回显功能  使用说明见UI文档
 * 2014/09/19  删除级联时跳转的等待小图标
 */

(function($){
	var allSelectBox = [];
	var killAllBox = function(bid){
		$.each(allSelectBox, function(i){
			if (allSelectBox[i] != bid) {
				if (!$("#" + allSelectBox[i])[0]) {
					$("#op_" + allSelectBox[i]).remove();
					//allSelectBox.splice(i,1);
				} else {
					$("#op_" + allSelectBox[i]).css({ height: "", width: "" }).hide();
				}
				$(document).unbind("click", killAllBox);
			}
		});
	};
	
	$.extend($.fn, {
		comboxSelect: function(options){
			var op = $.extend({ selector: ">a" }, options);
			
			return this.each(function(){
				var box = $(this);
				var selector = $(op.selector, box);

				allSelectBox.push(box.attr("id"));
				$(op.selector, box).click(function(){
					var options = $("#op_"+box.attr("id"));
					if (options.is(":hidden")) {
						if(options.height() > 300) {
							options.css({height:"300px",overflow:"scroll"});
							options.css({"padding-right":"20px"});
						}
						var top = box.offset().top+box[0].offsetHeight-50;
						if(top + options.height() > $(window).height() - 20) {
							top =  $(window).height() - 20 - options.height();
						}
						options.css({top:top,left:box.offset().left}).show();
						killAllBox(box.attr("id"));
						$(document).click(killAllBox);
						//修正关闭dialog时 下拉框不关闭bug  HuoYanpeng Start 2014/7/25
						if($("a.close",$.pdialog.getCurrent()).length > 0){
							$("a.close",$.pdialog.getCurrent()).click(killAllBox);
						}
						if($("button.close",$.pdialog.getCurrent()).length > 0){
							$("button.close",$.pdialog.getCurrent()).click(killAllBox);
						};
						//修正关闭dialog时 下拉框不关闭bug  HuoYanpeng End 2014/7/25
					} else {
						$(document).unbind("click", killAllBox);
						killAllBox();
					}
					return false;
				});
				$("#op_"+box.attr("id")).find(">li").comboxOption(selector, box);		
			});
		},
		comboxOption: function(selector, box){
			return this.each(function(){
				$(">a", this).click(function(){
					var $this = $(this);
					$this.parent().parent().find(".selected").removeClass("selected");
					$this.addClass("selected");
					selector.text($this.text());
					
					var $input = $("select", box);
					if ($input.val() != $this.attr("value")) {
						$("select", box).val($this.attr("value")).trigger("refChange").trigger("change");
					}
				});
			});
		},
		combox:function(){
			/* 清理下拉层 */
			var _selectBox = [];
			$.each(allSelectBox, function(i){ 
				if ($("#" + allSelectBox[i])[0]) {
					_selectBox.push(allSelectBox[i]);
				} else {
					$("#op_" + allSelectBox[i]).remove();
				}
			});
			allSelectBox = _selectBox;
			
			return this.each(function(i){
				var $this = $(this).removeClass("combox");
				var name = $this.attr("name");
				var value= $this.val();
				var label = $("option[value=" + value + "]",$this).text();
				var ref = $this.attr("ref");
				var refUrl = $this.attr("refUrl") || "";
				var init = $this.attr("init");
				var initval = $this.attr("initval");
				var disabled = $this.attr("disabled");
				var readOnly = $this.attr("readOnly") || $this.attr("readonly");
				var disabledAttr = disabled?'disabled="' + disabled + '"':'';
				
				var cid = $this.attr("id") || Math.round(Math.random()*10000000);
				var select = '<div class="combox"><div id="combox_'+ cid +'" class="select"' + (ref?' ref="' + ref + '"' : '') + '>' + '<a href="javascript:" class="'+$this.attr("class")+'" name="' + name +'" value="' + value + '" ' +disabledAttr +'>';
				var options = '<ul class="comboxop" id="op_combox_'+ cid +'">';
				if($this.attr("showInput") == "true"){
					$("option", $this).each(function(){
						var option = $(this);
						options +="<li><a class=\""+ (value==option[0].value?"selected":"") +"\" href=\"#\" value=\"" + option[0].value + "\" factVal=\"" + option[0].text +"\">" + option[0].value + "</a></li>";
					});
					select += value;
				}else{
					$("option", $this).each(function(){
						var option = $(this);
						options +="<li><a class=\""+ (value==option[0].value?"selected":"") +"\" href=\"#\" value=\"" + option[0].value + "\">" + option[0].text + "</a></li>";
					});
					select += label;
				}
				select += '</a></div></div>';
				options +="</ul>";
				$("body").append(options);
				$this.after(select);
				$("div.select", $this.next()).comboxSelect().append($this);
				if($this.attr("showInputId")){
					$("#op_combox_" + cid).find("a").click(function(){
						$("#" + $this.attr("showInputId")).val($(this).attr("factVal"));
					});
				}
				if (ref && refUrl) {
					//级联下拉框多次提交修正 HuoYanpeng 
					$this.unbind("refChange").bind("refChange",function(event){
						var $ref = $("#"+ref);
						if ($ref.size() == 0) return false;
						$.ajax({
							type:'POST', dataType:"json", url:refUrl.replace("{value}", encodeURIComponent($this.attr("value"))), cache: false,async:false,
							data:{},global:false,
							success: function(json){
								if (!json) return;
								var html = '';
								//修改成支持普通map转化的json，不采用二维数组。 修改人：LiAiDong
								 $.each(json,function(key,value){ 
					 				 html += '<option value="'+key+'">' + value + '</option>';   
					 			 });
								var $refCombox = $ref.parents("div.combox:first");
								$ref.html(html).insertAfter($refCombox);
								$refCombox.remove();
								//级联下拉框多次提交修正 HuoYanpeng 
								$ref.trigger("refChange").trigger("change").combox();
								if(initval){
									selectMyComBox(ref,initval);
									$("#"+ref).change();
									initval="";
								}
							},
							error: DWZ.ajaxError
						});
					});
					/*if(init == "true"){
						$this.trigger("refChange");
					}*/
				}
			});
		}
	});
	/**
	 * 自定义添加jquery插件 实现下拉框的回显
	 * @author huoyp
	 */
	$.fn.selectMyComBox = function(optvalue,options){
		var settings = $.extend({
			box_:navTab.getCurrentPanel()
		},options);
		if(options == "dialog"){
			settings.box_ = $.pdialog.getCurrent();
		}else if(options == "navTab"){
			settings.box_ = navTab.getCurrentPanel();
		}else if(options){
			alert("请为selectMyComBox方法传入正确的参数！\n(optvalue,('dialog'||'navTab')");
		}
		$(this,settings.box_).val(optvalue);
		var selectid = this.attr("id");
		if(isNulOrEmpty(optvalue)){
			opttext = $(this, settings.box_).find("option").filter(":eq(0)").text();
		}else{
			opttext=$("#combox_"+selectid, settings.box_).find("option[value="+optvalue+"]").text();
		}
		$("#combox_"+selectid, settings.box_).find("a").html(opttext);
	}
})(jQuery);
//设置下拉框选中add LiAnDong  selectid:下拉框id  optvalue：被选中的值 navTabPanel：navTab.getCurrentPanel()
function selectMyComBox(selectid, optvalue) {
	var str = "option[value='myval']".replace("myval", optvalue);
	var opttext = "";
	$("#" + selectid, navTab.getCurrentPanel()).val(optvalue);
	//键值为空时取第一个option
	if (isNulOrEmpty(optvalue)) {
		opttext = $("#" + selectid, navTab.getCurrentPanel()).find("option")
				.filter(":eq(0)").text();
	} else {
		opttext = $("#combox_" + selectid, navTab.getCurrentPanel()).find(str)
				.text();
	}
	$("#combox_" + selectid, navTab.getCurrentPanel()).find("a").html(opttext);
}
//H~ 增加struts2 对 dwz combox组件的回显功能  使用说明见UI文档
function hxMyCombox(dwz_combox_myarray, dwz_combox_mybox) {
	if (dwz_combox_myarray.length > 0) {
		for (var i = 0; i < dwz_combox_myarray.length - 1; i++) {
			$("#" + dwz_combox_myarray[i], dwz_combox_mybox).trigger("refChange");
		}
	}
}

