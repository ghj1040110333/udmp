/**
 * 2015年7月10日 09:41:17 二次封装jQuery提供的ajax方法，提供特殊处理
 * 
 */
;(function ($, window, document) {
	$.extend({
		/**
		 * ele 触发事件的元素
		 * option 针对原jQuery的参数对象
		 */
		nciAjax:function(ele,option){
			var $page = $(ele).parents(".dialog");
			if($page.length == 0){
				$page = $(ele).parents(".unit");
			}
			
			$.ajax(option);
		}
	});
})( jQuery, window, document );