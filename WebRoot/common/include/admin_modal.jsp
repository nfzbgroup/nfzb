<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- 模态框（Modal） -->
<div class="modal fade" id="admin-modal" tabindex="-1" role="dialog" aria-labelledby="admin-modal-title" aria-hidden="true">
    <div class="modal-dialog" style="width:70%;height:auto;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="admin-modal-title">模态框标题</h4>
            </div>
            <div class="modal-body">
            	<iframe id="admin-modal-view" width="100%"  height="650" src=""></iframe>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-danger" data-dismiss="modal">关闭</button>
                <!-- <button type="button" class="btn btn-primary">提交更改</button> -->
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal -->
</div>