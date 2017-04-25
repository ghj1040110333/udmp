<%@ page contentType="text/html;charset=UTF-8" %>
<!-- 消息框 -->
<div id="alert" class="opacity">
	<div class="alert shadow">
		<div class="row">
			<div class="col-xs-2">
				<span class="glyphicon"></span>
			</div>
			<div class="col-xs-10">
				<div class="alert-content"></div>
			</div>
		</div>
		<div class="text-center buttons">
			<button type="button" class="btn i-btn-ok btn-close">确定</button>
			<button type="button" class="btn btn-default btn-cancel">取消</button>
		</div>
	</div>
</div>

<!-- 模态窗口 -->
<div id="winModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
        	<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">
					<span class="title-text">标题</span>
					<img src="${ctxStatic}/images/index/loading.gif" class="loading">
				</h4>
			</div>
			<div class="modal-body">
				<div class="alert">
					<button type="button" class="close" data-dismiss="alert">
  						<span>&times;</span>
					</button>
       				<div class="alert-content"></div>
   				</div>
   				<div class="body-content clearfix"></div>
			</div>
			<div class="modal-footer">
   				<button type="button" class="btn i-btn-ok btn-confirm">确定</button>
				<button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">关闭</button>
			</div>
        </div>
    </div>
</div>

<!-- 二层模态窗口 -->
<div id="secondModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
        	<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">
					<span class="title-text">标题</span>
					<img src="${ctxStatic}/images/index/loading.gif" class="loading">
				</h4>
			</div>
			<div class="modal-body">
   				<div class="body-content clearfix"></div>
			</div>
			<div class="modal-footer">
   				<button type="button" class="btn i-btn-ok btn-confirm">确定</button>
				<button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">关闭</button>
			</div>
        </div>
    </div>
</div>

<!-- 三层模态窗口 -->
<div id="thirdModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static">
    <div class="modal-dialog">
        <div class="modal-content">
        	<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				<h4 class="modal-title">
					<span class="title-text">标题</span>
					<img src="${ctxStatic}/images/index/loading.gif" class="loading">
				</h4>
			</div>
			<div class="modal-body">
   				<div class="body-content clearfix"></div>
			</div>
			<div class="modal-footer">
   				<button type="button" class="btn i-btn-ok btn-confirm">确定</button>
				<button type="button" class="btn btn-default btn-cancel" data-dismiss="modal">关闭</button>
			</div>
        </div>
    </div>
</div>