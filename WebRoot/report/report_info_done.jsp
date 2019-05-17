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
	<div class="form-body" align="center">
	   <table class="table table-border table-bordered" style="width: 80%;">
	        <tr>
				<td class="text-right tab-left">
					<label style="white-space: nowrap">签报名称:</label>
				</td>
				<td class="text-center">
					${legislationReport.stReportName}
				</td>
			</tr>
	   </table>
	</div>
	<div id="legislationProcessInfoTab">
		<!--<table class="table table-border table-bordered">
			<!--<tr>
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
			</tr>  -->
			<div class="form-body" align="center">
				<table class="table table-border table-bordered table-bg table-hover" style="width: 80%;">
					<thead>
						<th class="text-center">领导姓名</th>
						<th class="text-center">反馈信息</th>
						<th class="text-center">反馈时间</th>
					</thead>
					<tbody>
						<c:if test="${legislationSendNoticeList!=null}">
							<c:forEach items="${legislationSendNoticeList}" var="person" >
								<tr>
								<td class="text-center">
									${person.stUserName}
								</td>
								<td class="text-center">
									${person.stFeedbackContent}
								</td>

								<td class="text-center">
									${person.dtFeekbackDate}
								</td>
								</tr>
							</c:forEach>
						</c:if>
						<tr>
				             <td class="text-right tab-left">
					            <label style="white-space: nowrap">相关材料:</label>
				             </td>
				             <td class="text-center" colspan="2">
					            <s:iterator value="#request.reportList" var="doc">
						          <label class="control-label col-md-12 text-center"><a target="_blank" href="${basePath}/file/downloadAttach.do?name=${doc.stTitle}&url=${doc.stFileId}">${doc.stTitle}</a></label>
					            </s:iterator>
				             </td>
			            </tr> 
						<%-- <c:forEach items="${mapListJson}" var="person" varStatus="status">
							<tr class="text-right">
								<td class="text-right">
									<label>${person.name}</label>
								</td>
								<td>
									<input type="text" class="form-control" id="personFeedBack${status.index}" value="${person.feedBack}">
								</td>

								<td>
									<input type="text" class="form-control" id="feedTime${status.index}" value="${person.feedBackT}">
								</td>
							</tr>
						</c:forEach>
						<c:if test="${mapListJson==null}">
							<c:forEach items="${stPersonList}" var="person" varStatus="status">
								<tr class="text-right">
									<td class="text-right">
										<label>${person}</label>
									</td>
									<td>
										<input type="text" class="form-control" id="personFeedBack${status.index}" value="">
									</td>

									<td>
										<input type="text" class="form-control" id="feedTime${status.index}" value="">
									</td>
								</tr>
							</c:forEach>
						</c:if> --%>
					</tbody>
					
				</table>

			</div>
		<!-- </table> -->
		<div class="form-body" align="center">
		  <table class="table table-border table-bordered" style="width: 80%;">
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
		</div>
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