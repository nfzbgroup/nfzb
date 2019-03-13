<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span >审核记录 </span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<table class="table table-border table-bordered table-bg table-hover">
		<thead>
			<th class="text-center">环节</th>
			<th class="text-center">情况说明</th>
			<th class="text-center">附件</th>

		</thead>
		<tbody>
			<s:iterator value="#request.checkDetails" var="checkDetail">
				<tr class="text-center">
						<td>
							<span>${checkDetail.stTitle}</span>
						</td>
						<td>
							<textarea readonly class="form-control">${checkDetail.stContent}</textarea>
						</td>
						<td>
							<s:iterator value="#checkDetail.filesList" var="files">
								<span><a target="_blank" href="${basePath}/file/downloadAttach.do?name=${files.stTitle}&url=${files.stFileUrl}">${files.stTitle}</a></span></br>
							</s:iterator>
						</td>
				</tr>
			</s:iterator>

		</tbody>
	</table>
	<div class="form-group text-center">
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
</div>
<script>

</script>