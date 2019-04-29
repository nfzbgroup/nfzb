<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span >报签列表 > </span>
		</li>
		<li>
			<span>查看</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<div class="row" style="margin-bottom: 20px;">
		<div class="col-md-12">
			<!-- <label class="btn btn-w-m btn-default"  onclick="changeInfoTab(this,'openOnlineTabPage','${nodeId}','${stDocId}')">网上征求意见</label>
			<label class="btn btn-w-m btn-default"  onclick="changeInfoTab(this,'openUnitTabPage','${nodeId}','${stDocId}')">单位意见</label>
			<label class="btn btn-w-m btn-default"  onclick="changeInfoTab(this,'openAuditMeetingTabPage','${nodeId}','${stDocId}')">审核会议</label> -->
		</div>
	</div>
	<table class="table table-border table-bordered">
	        <tr>
				<td class="text-right tab-left">
					<label style="white-space: nowrap">签报名称:</label>
				</td>
				<td class="text-center">
					${legislationReport.stReportName}
				</td>
			</tr>
	</table>
	<div id="legislationProcessInfoTab">
		<table class="table table-border table-bordered">
			<tr>
				<td class="text-right tab-left">
					<label style="white-space: nowrap">报送领导:</label>
				</td>
				<td class="text-center">
					${legislationReport.stAddress}
				</td>
			</tr>
			<tr>
				<td class="text-right tab-left">
					<label style="white-space: nowrap">领导意见:</label>
				</td>
				<td class="text-center">
					${legislationReportTask.stComment1}
				</td>
			</tr>
			<tr>
				<td class="text-right tab-left">
					<label style="white-space: nowrap">相关材料:</label>
				</td>
				<td class="text-center">
					<s:iterator value="#request.reportList" var="doc">
						<label class="control-label col-md-12 text-center"><a target="_blank" href="${basePath}/file/downloadAttach.do?name=${doc.stTitle}&url=${doc.stFileId}">${doc.stTitle}</a></label>
					</s:iterator>
				</td>
			</tr> 
		</table>
		<table class="table table-border table-bordered">
			<tr>
				<td class="text-right tab-left">
					<label style="white-space: nowrap">报送市领导:</label>
				</td>
				<td class="text-center">
					${legislationReportTask.stBakTwo}
				</td>
			</tr>
			<tr>
				<td class="text-right tab-left">
					<label style="white-space: nowrap">报审结果说明:</label>
				</td>
				<td class="text-center">
					${legislationReportTask.stBakOne}
				</td>
			</tr>
			<tr>
				<td class="text-right tab-left">
					<label style="white-space: nowrap">相关材料:</label>
				</td>
				<td class="text-center">
					<s:iterator value="#request.reportList" var="doc">
						<label class="control-label col-md-12 text-center"><a target="_blank" href="${basePath}/file/downloadAttach.do?name=${doc.stTitle}&url=${doc.stFileId}">${doc.stTitle}</a></label>
					</s:iterator>
				</td>
			</tr> 
		</table>
		<div class="form-group text-center">
			<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
		</div>
	</div>
</div>
<script>
    $(function () {
        $(".tab-left").css('width', $(window).width() * 0.2)
    });

</script>