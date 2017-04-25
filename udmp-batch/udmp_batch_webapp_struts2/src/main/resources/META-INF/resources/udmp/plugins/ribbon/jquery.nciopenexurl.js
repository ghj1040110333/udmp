function openExternalMenu(aEle){
		// 获取当前a元素的jquery对象
		var $this = $(aEle);
		// 获取菜单id
		var rel = $this.attr("rel");
		// 获取dwz模版的li字符串
	 	var li = '<li tabid="#tabid#"><a href="javascript:void(0)" title="#title#" class="#tabid#">' + 
	 					'<span>#title#</span></a><a href="javascript:;" class="close">close</a></li>';
	 	// 获取navTab 模版
	 	var pageStr = "<div class=\"page unitBox\" id=\"" + rel + "\"><iframe id=\"iframe" + rel + "\""
	 						+ "style=\"height:" +  ($("div[fillSpace='sidebar']").height() - 15) + "px;"
	 						+ " width:100%;\" src=\"about:blank\"></iframe></div>";  
	 	// 替换成需要li字符串
	 	li = li.replaceAll("#title#",aEle.innerHTML).replaceAll("#tabid#",rel);
	 	// 获取li字符串 用于获取jquery对象
	 	var thisLi = ".navTab-tab li[tabid='"+rel+"']";
	 	// 获取unitBox字符串 用于获取jquery对象
	 	var thisBox = "div.page[id='" + rel + "']";
	 	// 判断是否第一次点击 若第一次点击，在li列表最后方添加 li
	 	if($(thisLi).length == 0){
		 	$(".navTab-tab").append(li).initUI(); 
	 	}
	 	//让所有navTab页隐藏
	 	$(".navTab-panel").find("div.page").css("display","none");
	 	//若没有该iframe页面，则添加该页面
		if($(".navTab-panel").find(thisBox).length == 0){
			$(".navTab-panel").append(pageStr).initUI();
			var htmlForm = getHtmlForm($this);
			document.getElementById("iframe" + rel).contentWindow.document.write(htmlForm);
			document.getElementById("iframe" + rel).contentWindow.document.getElementById("postData_form").submit();
			addCloseEventToNavTab(thisLi,thisBox);
			//解决自定义navtab在最后一个位置不能切换的bug
			$(thisLi).click(function(){
				$("div.page").hide();
				$("div.page").eq($(thisBox).index("div.page")).show();
				$(".navTab-tab li").removeClass("selected");
				$(thisLi).addClass("selected");
			});
		}
	 	//若有iframe页面，则切换到该iframe页面
		else{
			// 隐藏所有iframe
			$("div.page").hide();
			//找到div的位置
			var divIndex = $(thisBox).index("div.page") - 1;
			$(".navTab-panel").find(thisBox).remove();
			$(".navTab-panel").find("div.page").eq(divIndex).after(pageStr);
			var htmlForm = getHtmlForm($this);
			document.getElementById("iframe" + rel).contentWindow.document.write(htmlForm);
			document.getElementById("iframe" + rel).contentWindow.document.getElementById("postData_form").submit();
			addCloseEventToNavTab(thisLi,thisBox);
// 			$("div.page").find("[layoutH]").layoutH();
			//解决自定义navtab在最后一个位置不能切换的bug
			$(thisLi).click(function(){
				$("div.page").hide();
				$("div.page").eq($(thisBox).index("div.page")).show();
				$(".navTab-tab li").removeClass("selected");
				$(thisLi).addClass("selected");
			});
			$(".navTab-tab li").removeClass("selected");
			$(thisLi).addClass("selected"); 
		}
		$(".navTab-tab li").removeClass("selected");
		$(thisLi).addClass("selected"); 
	}
	//监听session是否超时并阻止点击时候的事件冒泡
	$("*").click(function(event){
		JudgeTimeOut();
		//2014年11月14日 10:40:28 注释掉取消事件冒泡部分 解决combox不能消失bug
		//阻止事件冒泡
// 		event.stopPropagation();
	});
	
	
function getHtmlForm($a){
	var externalurl = $a.attr("externalurl");
 	var userid = getCookie("git_userid");
 	var bizTime = getCookie("bizTime");
 	var date = new Date;
 	var toUrl = $a.attr("href");
 	var encrypt = $a.attr("encrypt");
 	var htmlForm = '<form action="'+externalurl+'" method="post" target="_self" id="postData_form">'+
	   '<input id="userid" name="userid" type="hidden" value="'+userid+'"/>'+
	   '<input id="encrypt" name="encrypt" type="hidden" value="'+encrypt+'"/>'+
	   '<input id="bizTime" name="bizTime" type="hidden" value="' + bizTime +'"/>'+
	   '<input id="date" name="date" type="hidden" value="'+date+'"/>'+
	   '<input id="toUrl" name="toUrl" type="hidden" value="'+toUrl+'"/>'+
	   '</form>';
   return htmlForm;
}
function addCloseEventToNavTab(thisLi,thisBox){
	//新增后，添加navTab的关闭事件
	$(thisLi).find("a.close").click(function(){
		var index = $(thisLi).index("ul.navTab-tab li") - 1;
		//处理关闭后不切换到当前次序一面的bug
		var $thisli = $(thisLi);
		
		$("ul.navTab-tab li").removeClass("selected");
		$("ul.navTab-tab li").eq(index).addClass("selected");
		$("div.page").hide();
		$("div.page").eq(index).show();
		$(".navTab-tab li").removeClass("selected");
		$("ul.navTab-tab li").eq(index).addClass("selected"); 
		
		$thisli.remove();
		$(thisBox).remove();
		return false;
	});
}