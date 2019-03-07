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
	<div class="ibox float-e-margins">
		<div class="ibox-title">
			<div class="row">
				<div class="col-md-12 padding0 order-btn">
					<label class="btn btn-w-m btn-success">草案信息列表</label>
				</div>
			</div>
		</div>
		<div class="ibox-content">
			<table class="table table-border table-bordered">
				<tr>
					<td class="text-right">
						<label style="white-space: nowrap">法规规章草案:</label>
					</td>
					<td class="text-center">
						<label>接口未绑定</label>
					</td>
				</tr>
				<tr>
					<td class="text-right">
						<label style="white-space: nowrap">备注:</label>
					</td>
					<td class="text-center">
						<label >草案备注</label>
					</td>
				</tr>
				<tr>
					<td class="text-right">
						<label style="white-space: nowrap">相关材料:</label>
					</td>
					<td class="text-center">
						<label class="control-label col-md-12 text-center">相关材料</label>
						<label class="control-label col-md-12 text-center">相关材料</label>
						<label class="control-label col-md-12 text-center">相关材料</label>
						<label class="control-label col-md-12 text-center">相关材料</label>
						<label class="control-label col-md-12 text-center">相关材料</label>
						<label class="control-label col-md-12 text-center">相关材料</label>
						<label class="control-label col-md-12 text-center">相关材料</label>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<div class="form-group text-center">
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
</div>
<script>

</script>