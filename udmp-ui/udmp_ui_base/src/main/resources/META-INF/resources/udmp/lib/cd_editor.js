/**
 * Description: Ueditor组件
 * @author Seadon
 * @date 2016年3月21日
 *
 */

idefine(function(require, exports, module) {
	//载入依赖项
	require('ueditor');
	window.m_editor = {};
	//编辑器渲染
	var Editor = {
		defaultOptions: {
			toolbars: [
				['source', 'undo', 'redo', '|', 'fontsize', 'bold',
					'italic', 'underline', '|', 'insertimage'
				]
			],
			elementPathEnabled: false,
			maximumWords: 1000,
			autoHeightEnabled: false
		},	
		initSimple: function(container, options) {
			typeof(window.w_editor) != "undefined" && window.w_editor.destroy();
			var options = jQuery.extend(true, this.defaultOptions, options);
			window.w_editor = UE.getEditor(container, options);
		},
		initMul: function(container,options) {
			typeof(window.m_editor) && typeof(window.m_editor[container]) != "undefined" && window.m_editor[container].destroy();
			var options = jQuery.extend(true, this.defaultOptions, options);
			m_editor[container] = UE.getEditor(container, options);
		}
	};
	module.exports = Editor;
})
