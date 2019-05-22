<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span >草案历史记录 </span>
		</li>
	</ul>
	<button style="padding-right: 10px;padding-top: 8px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<table class="table table-border table-bordered table-bg table-hover">
		<thead>
			<th class="text-center">环节</th> 
			<th class="text-center">草案名称</th>
			<th class="text-center">草案文本</th>
			<th class="text-center">上传人</th>
			<th class="text-center">上传时间</th>
		</thead>
		<tbody>
		<s:iterator value="#request.legislationDeal" var="deal">
			<tr class="text-center">
				<td>
					<span>${deal.stActionName}</span>
				</td>
				<td>
					<span>${deal.stBakOne}</span>
				</td>
				<td>
					<s:iterator value="#deal.docList" var="files">
						<span><a target="_blank" href="${basePath}/file/downloadAttach.do?name=${files.stTitle}&url=${files.stFileId}">${files.stTitle}</a></span></br>
					</s:iterator>
				</td>
				<td>
					<span>${deal.stUserName}</span>
				</td>
				<td>
					<span><fmt:formatDate type="time" pattern="yyyy-MM-dd HH:mm:ss"
            value="${deal.dtDealDate}" /></span>
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