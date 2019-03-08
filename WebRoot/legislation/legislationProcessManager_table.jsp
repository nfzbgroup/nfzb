<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="table table-border table-bordered table-bg table-hover" id="showtable"
	   data-toggle="table"
	   data-mobile-responsive="true"
	   data-card-view = "true"
	   data-pagination="true">
	<thead>
	<tr class="text-center">
		<th class="text-center" data-field="id">草案编号</th>
		<th class="text-center" data-field="district_name">法规规章草案</th>
		<th class="text-center" data-field="district_name">处理环节</th>
		<th class="text-center" data-field="district_name">发起人</th>
		<th class="text-center" data-field="created_at">发起时间</th>
		<th class="text-center" data-field="set">操作</th>
	</tr>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${retPage.totalSize > 0}">
			<c:forEach items="${retPage.result}" var="task">
				<tr class="text-center">
					<td >${task.stDocNo}</td>
					<td >${task.stDocName}</td>
					<td >${task.stNodeName}</td>
					<td >${task.stUserName}</td>
					<td ><fmt:formatDate type="date" value="${task.dtCreateDate}" /></td>
					<c:choose>
						<c:when test="${nodeId=='NOD_0000000101'}">
							<c:choose>
								<c:when test="${buttonStatus=='DONE'}">
									<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('queryDocInfo','${task.stDocId}')" class="layer_full_link">查看</a><br/><a href="javascript:void(0);" onclick="openPage('openDraftHistoryPage','${task.stDocId}')" class="layer_full_link">草案历史 </a><a href="javascript:void(0);" class="layer_full_link"> 办理情况</a></td>
								</c:when>
								<c:otherwise>
									<td ><a href="javaScript:void(0)" data-title="修改" class="layer_full_link">修改</a><br/><a href="javascript:void(0);" onclick="nextProcess('${task.stDocId}','${task.stNodeId}','nextProcess')" class="layer_full_link">上报</a></td>
								</c:otherwise>
							</c:choose>

						</c:when>
						<c:otherwise>
							<c:choose>
								<c:when test="${buttonStatus=='TODO'}">
									<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('queryDocInfo','${task.stDocId}')" class="layer_full_link">查看</a><br/><a href="javascript:void(0);" onclick="nextProcess('${task.stDocId}','${task.stNodeId}','nextChildProcess')" class="layer_full_link">接收</a></td>
								</c:when>
								<c:when test="${buttonStatus=='DOING'}">
									<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('queryDocInfo','${task.stDocId}')" class="layer_full_link">查看</a><br/><a href="javascript:void(0);" onclick="openPage('openSeparatePage','${task.stDocId}')" class="layer_full_link">分办</a></td>
								</c:when>
								<c:otherwise>
									<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('queryDocInfo','${task.stDocId}')" class="layer_full_link">查看</a><br/><a href="javascript:void(0);" onclick="openPage('openDraftHistoryPage','${task.stDocId}')" class="layer_full_link">草案历史 </a><a href="javascript:void(0);" class="layer_full_link"> 办理情况</a></td>
								</c:otherwise>
							</c:choose>
						</c:otherwise>
					</c:choose>
				</tr>
			</c:forEach>
		</c:when>
	</c:choose>
	</tbody>
</table>
<div class="clearfix">
	<div class="list-page" id="listPage">
	</div>
</div>
<script>
	if(${retPage.totalSize > 0}){
		fullPageList(${retPage.totalSize},${pageNo},${pageSize},'listPage');
	}
    function changeType(type) {
        $('#'+type).parent().children().attr("class","btn btn-w-m btn-default");
        $('#'+type).attr("class","btn btn-w-m btn-success");
        $('#taskStatus').val(type);
        submitForm(1);
    }

    function nextProcess(stDocId,stNodeId,method) {
        layer.confirm('请确认操作！',function(index){
            layer.close(layer.index);
			$.post("../"+$('#requestUrl').val()+"?stDocId="+stDocId+"&stNodeId="+stNodeId+"&method="+method);
            submitForm(1);
        });
    }
</script>