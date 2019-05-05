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

				<th class="text-center" data-field="district_name">通知名称</th>
				<th class="text-center" data-field="created_at">类型</th>
				<th class="text-center" data-field="district_name">时间</th>
				<th class="text-center" data-field="set">操作</th>
	</tr>
	</thead>
	<tbody>

			<c:choose>
				<c:when test="${retPage.totalSize > 0}">
					<c:forEach items="${retPage.result}" var="task">
						<tr class="text-center">
							<td >${task.stTile}</td>
							<td >${task.stBak}</td>
							<td ><fmt:formatDate type="date" value="${task.dtSendDate}" /></td>
									<c:choose>
										<c:when test="${buttonStatus=='TODO'}">
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stNoticeId}')" class="layer_full_link">查看</a><br/><a href="javascript:void(0);" onclick="nextProcess('${task.stNoticeId}','${task.stNoticeId}','nextChildProcess')" class="layer_full_link">接收</a></td>
										</c:when>
										<c:when test="${buttonStatus=='DOING'}">
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stNoticeId}')" class="layer_full_link">查看</a><br/><a href="javascript:void(0);" onclick="openPage('openSeparatePage','${task.stNoticeId}')" class="layer_full_link">分办</a></td>
										</c:when>
										<c:otherwise>
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stNoticeId}')" class="layer_full_link">查看</a><br/><a href="javascript:void(0);" onclick="openPage('openDraftHistoryPage','${task.stNoticeId}')" class="layer_full_link">办理 </a></td>
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
    function sendProcess(method,stTaskId,buttonStatus,stTopicId,stNodeId,hasSendReturn) {
        if(hasSendReturn){
            nextProcess(stTopicId,stNodeId,"nextChildProcess");
        }else{
            Duang.error("提示", "审核未完成，请等待或手动添加审核结果！");
        }
    }
    function publishProcess(method,stTaskId,buttonStatus,stTopicId,stNodeId,hasSendReturn) {
        if(hasSendReturn){
            openTaskPageWithStatus(method,stTaskId,buttonStatus,stTopicId,stNodeId)
        }else{
            Duang.error("提示", "审核未完成，请等待或手动添加审核结果！");
        }
    }
    function gatherProcess(stTopicId,stNodeId,method,hasReturn) {
        if(hasReturn){
            nextProcess(stTopicId,stNodeId,method);
        }else{
            if(stNodeId=="NOD_0000000104"){
                Duang.error("提示", "oa审核结果未反馈，请等待或手动添加！");
            }else if(stNodeId=="NOD_0000000170"){
                Duang.error("提示", "审核会议发布结果未反馈，请等待或手动添加！");
            }else{
                Duang.error("提示", "网上报名结果未反馈，请等待或手动添加！");
            }
        }
    }

    function uploadReport(stTopicId,stNodeId) {
        $.post("../legislationProcessTask/uploadReport.do?stTopicId="+stTopicId+"&stNode="+stNodeId,
            function (data) {
                if(data.success) {
                    nextProcess(stTopicId,stNodeId,"nextProcess");
                }else{
                    Duang.error("提示", "请补全必填材料！");
                }
            },
            "json")
    }

    function nextProcess(stTopicId,stNodeId,method) {
        layer.confirm('请确认操作！',function(index){
            layer.close(layer.index);
			$.post("../"+$('#requestUrl').val()+"?stTopicId="+stTopicId+"&stNodeId="+stNodeId+"&method="+method);
            submitForm(1);
        });
    }

    function returnProcess(stTopicId,stNodeId,method) {
        layer.confirm('请确认操作！',function(index){
            layer.close(layer.index);
            $.post("../"+$('#requestUrl').val()+"?stTopicId="+stTopicId+"&stNodeId="+stNodeId+"&method="+method);
            submitForm(1);
        });
    }
</script>