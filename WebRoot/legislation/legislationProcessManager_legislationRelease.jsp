<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span >立法听证会 > </span>
		</li>
		<li>
			<span>上网发布</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<table class="table table-border table-bordered">
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">听证会议题:</label>
			</td>
			<td class="text-center">
				<label>${legislationProcessTask.stBakOne}</label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">对应草案:</label>
			</td>
			<td class="text-center">
				<label>${legislationProcessTask.stFlowId}</label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">听证会地点:</label>
			</td>
			<td class="text-center">
				<label>${legislationProcessTask.stBakTwo}</label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">听证会时间:</label>
			</td>
			<td class="text-center">
			<label ><fmt:formatDate type="date" value="${legislationProcessTask.dtBakDate}" /></label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">听证会人员:</label>
			</td>
			<td class="text-center">
				<label>${legislationProcessTask.stComment2}</label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">立法听证会前相关材料:</label>
			</td>
			<td class="text-center">
				<c:if test="${legislationFilesList !=null&&fn:length(legislationFilesList)>0}">
					<c:forEach var="file" items="${legislationFilesList}">
						<c:if test="${file.stSampleId !=null&&file.stSampleId !='null'}">
							<label class="control-label col-md-12 text-center"><a  target="_blank" href="${basePath}/file/downloadAttach.do?name=${file.stTitle}&url=${file.stFileUrl}">${file.stTitle}</a></label>
						</c:if>
					</c:forEach>
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">立法听证会前其他材料:</label>
			</td>
			<td class="text-center">
				<c:if test="${legislationFilesList !=null&&fn:length(legislationFilesList)>0}">
					<c:forEach var="file" items="${legislationFilesList}">
						<c:if test="${file.stSampleId==null||file.stSampleId=='null'}">
							<label class="control-label col-md-12 text-center"><a  target="_blank" href="${basePath}/file/downloadAttach.do?name=${file.stTitle}&url=${file.stFileUrl}">${file.stTitle}</a></label>
						</c:if>
					</c:forEach>
				</c:if>
			</td>
		</tr>
	</table>
	<div class="form-group text-center">
		<input type="button" class="btn btn-w-m btn-success" value="发布" id="PUBLISH"
		<c:choose>
				<c:when test="${publishListDisabled}">
				 disabled="disabled"
				</c:when>
				<c:otherwise>
			   onclick="openDemonstrationTaskPageWithStatus('openCheckExplainPage','${legislationProcessTask.stTaskId}','PUBLISH','${legislationProcessTask.stDocId}','${legislationProcessTask.stNodeId}')"
		        </c:otherwise>
		</c:choose>
			   > &nbsp;&nbsp;
		<input type="button" class="btn btn-w-m btn-success" value="下一步" id="nextStep"
		<c:choose>
				<c:when test="${publishListDisabled}">
				</c:when>
				<c:otherwise>
			   disabled="disabled"
				</c:otherwise>
		</c:choose>
			   onclick="nextStep()" > &nbsp;&nbsp;
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
</div>
<script>
    $(function () {
        $(".tab-left").css('width', $(window).width() * 0.2)
    });
    function nextStep() {
        if($('#nextStep').attr('disabled')==undefined){
            nextChildDemonstrationProcess('${stDocId}','${nodeId}','nextChildProcess','${buttonId}');
        }
    }
</script>