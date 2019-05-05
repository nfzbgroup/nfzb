<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span >网上征求意见 > </span>
		</li>
		<c:if test="${legislationProcessTask.stTaskStatus=='RESULT'}">
			<li>
				<span >采纳意见发布 </span>
			</li>
		</c:if>
		<c:if test="${legislationProcessTask.stTaskStatus !='RESULT'}">
			<li>
				<span >汇总详情 </span>
			</li>
		</c:if>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<table class="table table-border table-bordered">
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">法规规章草案:</label>
			</td>
			<td class="text-center">
				<label>${legislationProcessTask.stFlowId}</label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">经办处室:</label>
			</td>
			<td class="text-center">
				<label>${legislationProcessTask.stTeamName}</label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">经办处意见:</label>
			</td>
			<td class="text-center">
				<label>${legislationProcessTask.stComment2}</label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">办领导意见:</label>
			</td>
			<td class="text-center">
				<label>${legislationProcessTask.stComment1}</label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">法治调研处意见:</label>
			</td>
			<td class="text-center">
				<label>${legislationProcessTask.stBakOne}</label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">征求意见开始时间:</label>
			</td>
			<td class="text-center">
				<label><fmt:formatDate value="${legislationProcessTask.dtBakDate}"/></label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">征求意见结束时间:</label>
			</td>
			<td class="text-center">
				<label><fmt:formatDate value="${legislationProcessTask.dtDeadDate}"/></label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">征求意见汇总情况:</label>
			</td>
			<td class="text-center">
				<label>门户网站征询平台(反馈意见数)${legislationProcessTask.stBakTwo}条。</label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">网上征求意见采纳情况材料:</label>
			</td>
			<td class="text-center">
				<c:if test="${legislationFilesList !=null&&fn:length(legislationFilesList)>0}">
					<c:forEach var="file" items="${legislationFilesList}">
						<label class="control-label col-md-12 text-center"><a  target="_blank" href="${basePath}/file/downloadAttach.do?name=${file.stTitle}&url=${file.stFileUrl}">${file.stTitle}</a></label>
					</c:forEach>
				</c:if>
			</td>
		</tr>
	</table>
	<div class="form-group text-center">
		<c:if test="${legislationProcessTask.stTaskStatus=='RESULT'}">
			<input type="button" class="btn btn-w-m btn-success" value="发布"
				   onclick="nextChildDemonstrationProcess('${legislationProcessTask.stDocId}','${nodeId}','nextChildProcess','${buttonId}')" > &nbsp;&nbsp;
		</c:if>
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
</div>
<script>
    $(function () {
        $(".tab-left").css('width', $(window).width() * 0.2)
    })
</script>