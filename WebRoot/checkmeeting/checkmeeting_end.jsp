<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span>审核会议</span>
		</li>
		<li>
			<span></span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span>
		<span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">
	<h2 style="color: #E4243D; text-align: center; font-weight: bold; margin-bottom: 20px">审核会议s</h2>
	<form id="auditMeetingForm" class="form-horizontal" novalidate="novalidate">
		<input type="hidden" name="stMeetingId" value="${legislationCheckmeeting.stMeetingId}">
		<input type="hidden" id="nodeStatus" value="${nodeStatus}">
		<div class="form-body" align="center">
			<table class="table table-border table-bordered table-bg table-hover" style="width: 60%;">
				<tr class="text-center">
					<td class="text-right" width="20%">
						<label>会议名称：</label>
					</td>
					<td>${legislationCheckmeeting.stMeetingName}</td>
				</tr>
				<tr class="text-center">
					<td class="text-right">
						<label>会议地点：</label>
					</td>
					<td>${legislationCheckmeeting.stAddress}</td>
				</tr>
				<tr class="text-center">
					<td class="text-right">
						<label>会议时间：</label>
					</td>
					<td>
						<fmt:formatDate value="${legislationCheckmeeting.dtBeginDate}" pattern="yyyy-MM-dd" />
					</td>
				</tr>

				<tr class="text-center">
					<td class="text-right">
						<label>参会人员：</label>
					</td>
					<td>${legislationCheckmeeting.stPersons}</td>
				</tr>
				<tr class="text-center">
					<td class="text-right">
						<label>会议内容概述</label>
					</td>
					<td>${legislationCheckmeetingTask.stSummary}</td>
				</tr>
			</table>
		</div>

		

		<div class="form-body" align="center">
			<table class="table table-border table-bordered table-bg table-hover" style="width: 60%;">
				<tbody class="text-center" align="center">
					<%-- <tr class="text-center">
						<th class="text-right" width="20%">草案名称</th>
						<th class="text-center">草案答复反馈信息</th>
					</tr>
					<c:forEach items="${legislationProcessDocAll}" var="doc">
						<tr class="text-center">
							<th class="text-right" width="20%">${doc.stDocName}</th>
							<td class="text-center">${legislationProcessTaskMap.get(doc.stDocId).stComment1}</td>
						</tr>
					</c:forEach> --%>
					<tr class="text-center">
						<th class="text-right" width="20%">事项名称</th>
						<th class="text-center">事项类型</th>
						<th class="text-center">事项反馈</th>
					</tr>
					<c:forEach items="${checkmeetingItem}" var="doc">
						<tr class="text-center">
							<th class="text-right" width="20%">${doc.stItemName}</th>
							<td class="text-center">${doc.stTypeName}</td>
							<c:choose>
								<c:when test="${doc.stSource!=null && doc.stTypeName=='草案'}">
									<td class="text-center">${doc.stSource.stComment1} </td>
								</c:when>
								<c:when test="${doc.stSource!=null && doc.stTypeName=='立法计划'}">
									<td class="text-center">${doc.stSource.stRemark} </td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

 			<div class="form-group">
				<label class="control-label">上传材料接收 </label>
			</div>	
			<%@include file="/legislation/file/attachUpload.jsp" %>

		<div class="form-group text-center">
			<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="返回">
		</div>
		
	</form>

</div>
<script>
	$(function() {
		laydate.render({
			elem : '#dtBeginDate',
			format : 'yyyy-MM-dd',
			calendar : true,
		});
	});

	
</script>