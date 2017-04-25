<%@ page contentType="text/html;charset=UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
<title>用户管理</title>
<meta name="decorator" content="bootstrap335"/>
<%@include file="/WEB-INF/views/include/treeview.jsp"%>
<link href="${ctxUdmp}/css/params/system/userlist.css" type="text/css" rel="stylesheet" />
</head>
<body>
	<div>
		<div id="orgTree" class="col-sm-2" style="padding:0;">
			<ol class="breadcrumb">
		      <li class="active">
		      	<em>组织机构</em><a href="javascript:void(0);"><span class="glyphicon glyphicon-refresh pull-right" onclick="refreshTree();"></span></a>
		      </li>
		    </ol>
			<div id="ztree" class="ztree"></div>
		</div>
		<div id="userContent" class="col-sm-10" style="padding:0;"></div>
	</div>
	
	<script type="text/javascript">
		var setting = {
			data : {
				simpleData : {
					enable : true,
					idKey : "id",
					pIdKey : "pId",
					rootPId : '0'
				}
			},
			view:{
				showLine:false,
				showIcon:false
			},
			callback : {
				onClick : function(event, treeId, treeNode) {
					// 判断是否展开/收缩图标
					if(event.target.tagName && event.target.tagName == 'I') {
						return;
					}
					var id = treeNode.id == '0' ? '' : treeNode.id;
					$("#userContent").find("input[name='office.id']").val(id);
					$("#userContent").find("input[name='company.name']").val(treeNode.name);
					$("#userContent").find("#btnSubmit").trigger("click");
				}
			}
		};

		function refreshTree() {
			$.getJSON("${ctx}/sys/office/treeData", function(data) {
				$.fn.zTree.init($("#ztree"), setting, data).expandAll(true);
				$('.ztree li:has(ul)').addClass('parent_li').find('>a >span:last').prepend('<i class="glyphicon glyphicon-minus-sign"></i>');
				$('.ztree li>a>span').on('click',function(){
					$('#ztree>li').removeClass('i_tree_hidden');
					$('#ztree .curSelectedNode').removeClass('curSelectedNode');
				});
			    $('.ztree li.parent_li >a >span > i').on('click', function (e) {
			        var $children = $(this).parents('li.parent_li:eq(0)').find(' > ul > li');
			        if ($children.is(":visible")) {
			            $children.hide('fast');
						if($(this).parent("a").attr("class")=="level0"){
							$('#ztree>li').addClass('i_tree_hidden');
						}
			            $(this).find(' > i').addClass('glyphicon-plus-sign').removeClass('glyphicon-minus-sign').end().parent("a").addClass("curSelectedNode");
			        } else {
			            $children.show('fast');
			            $(this).find(' > i').addClass('glyphicon-minus-sign').removeClass('glyphicon-plus-sign').end().parent("a").addClass("curSelectedNode");
			        }
			       	//e.stopPropagation();//停止冒泡
			     });
			});
		}
		
		refreshTree();
		$("#userContent").load("${ctx}/sys/user/list");
	</script>
</body>
</html>