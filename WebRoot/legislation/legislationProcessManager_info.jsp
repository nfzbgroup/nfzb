<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="modal-header">
	<button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
	<h4 class="modal-title">查看草案</h4>
</div>
<div class="modal-body">
	<form  class="form-horizontal" novalidate="novalidate">
		<div class="form-body">
            <div class="col-md-8 padding0 order-btn">
                    <label class="btn btn-w-m btn-success">草案信息列表</label>
            </div>
			<div class="form-group">
				<label class="col-sm-3 control-label">法规规章草案：</label>
                <label class="col-sm-9 control-label">接口未绑定</label>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">备注：</label>
                <textarea class="col-sm-9 form-control">草案备注</textarea>
			</div>
            <div class="form-group">
                <label class="col-sm-3 control-label">相关材料：</label>
                <textarea class="col-sm-9 form-control">草案备注</textarea>
            </div>
		</div>
	</form>

</div>
<script>

</script>