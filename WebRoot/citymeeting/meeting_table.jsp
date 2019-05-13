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

				<th class="text-center" data-field="id" hidden="hidden">议题编号</th>
				<th class="text-center" data-field="district_name">议题名称</th>
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
							<td hidden="hidden">${task.stTopicId}</td>
							<td >${task.stTopicName}</td>
							<td >${task.stNodeName}</td>
							<td ><fmt:formatDate type="date" value="${task.dtCreateDate}" /></td>
							<c:choose>
								<c:when test="${nodeId=='NOD_0000000101'}">
									<c:choose>
										<c:when test="${buttonStatus=='DONE'}">
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stTopicId}')" class="layer_full_link">查看</a><br/><a href="javascript:void(0);" onclick="openPage('openDraftHistoryPage','${task.stTopicId}')" class="layer_full_link">草案历史 </a><a href="javascript:void(0);" class="layer_full_link"> 办理情况</a></td>
										</c:when>
										<c:otherwise>
											<td ><a href="javaScript:void(0)" data-title="修改" onclick="openPage('openEditPage','${task.stTopicId}')" class="layer_full_link">修改</a><br/><a href="javascript:void(0);" onclick="uploadReport('${task.stTopicId}','${task.stNodeId}')" class="layer_full_link">上报</a></td>
										</c:otherwise>
									</c:choose>

								</c:when>
								<c:when test="${nodeId=='NOD_0000000103'}">
									<c:choose>
										<c:when test="${buttonStatus=='TODO'}">
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stTopicId}')" class="layer_full_link">查看</a><br/>
												<a href="javascript:void(0);" onclick="returnProcess('${task.stTopicId}','${task.stNodeId}','returnProcess')" class="layer_full_link">退回</a>
												<a href="javascript:void(0);" onclick="nextProcess('${task.stTopicId}','${task.stNodeId}','nextChildProcess')" class="layer_full_link">确认认领</a></td>
										</c:when>
										<c:when test="${buttonStatus=='DOING'}">
											<td >
												<a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stTopicId}')" class="layer_full_link">查看</a><br/>
												<a href="javascript:void(0);" onclick="openProcessIndex('${task.stTopicId}','${task.stDocName}')"  class="layer_full_link">办理</a>
												<c:if test="${isZhc eq false}">
													<a href="javascript:void(0);" onclick="openLeaderIdeaPage('openLeaderIdeaPage','${task.stTopicId}','${task.stNodeId}')" class="layer_full_link">办理完成</a></td>
												</c:if>
											</td>
										</c:when>
										<c:otherwise>
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stTopicId}')" class="layer_full_link">查看</a><br/><a href="javascript:void(0);" onclick="openPage('openDraftHistoryPage','${task.stTopicId}')" class="layer_full_link">草案历史 </a><a href="javascript:void(0);" class="layer_full_link"> 办理情况</a></td>
										</c:otherwise>
									</c:choose>

								</c:when>
								<c:when test="${nodeId=='NOD_0000000104'}">
									<c:choose>
										<c:when test="${buttonStatus=='TODO'}">
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stTopicId}')" class="layer_full_link">查看</a><br/>
												<a href="javaScript:void(0)" data-title="oa审核" onclick="openProMeetPage('openProMeetPage','${task.stTopicId}','${buttonStatus}')"  class="layer_full_link">oa审核</a>
											</td>
										</c:when>
										<c:when test="${buttonStatus=='DOING'}">
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stTopicId}')" class="layer_full_link">查看</a><br/>
												<c:if test="${task.hasReturn eq false}">
													<a href="javaScript:void(0)" data-title="添加oa审核结果" onclick="openProMeetPage('openProMeetPage','${task.stTopicId}','TODO-RETURN')"  class="layer_full_link">添加oa审核结果</a><br>
												</c:if>
												<a href="javascript:void(0);" onclick="gatherProcess('${task.stTopicId}','${task.stNodeId}','nextProcess',${task.hasReturn})"  class="layer_full_link">提交</a></td>
										</c:when>
										<c:otherwise>
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stTopicId}')" class="layer_full_link">查看</a><br/>
												<a href="javaScript:void(0)" data-title="审核情况" onclick="openPage('openProMeetCheckInfoPage','${task.stTopicId}')" class="layer_full_link">oa审核情况</a><br/>
												<a href="javascript:void(0);" onclick="openPage('openDraftHistoryPage','${task.stTopicId}')" class="layer_full_link">草案历史 </a><a href="javascript:void(0);" class="layer_full_link"> 办理情况</a></td>
										</c:otherwise>
									</c:choose>

								</c:when>
								<c:when test="${nodeId=='NOD_0000000106'}"><!-- 法规规章草案报审 -->
									<c:choose>
										<c:when test="${buttonStatus=='TODO'}">
											<td ><a href="javaScript:void(0)" data-title="报审" onclick="openPage('draftReportStart','${task.stTopicId}')" class="layer_full_link">报审</a><br/>
												
											</td>
										</c:when>
										<c:when test="${buttonStatus=='DONE'}">
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stTopicId}')" class="layer_full_link">查看</a></td>
										</c:when>
									</c:choose>

								</c:when>
								<c:when test="${nodeId=='NOD_0000000107'}"><!-- 法规规章草案报审处理 -->
									<c:choose>
										<c:when test="${buttonStatus=='DONE'}">
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stTopicId}')" class="layer_full_link">查看</a></td>
										</c:when>
										<c:otherwise>
											<td ><a href="javaScript:void(0)" data-title="处理" onclick="openProMeetPage('draftReportAudit','${task.stTopicId}','${buttonStatus}')" class="layer_full_link">处理</a><br/>
												</td>
										</c:otherwise>
									</c:choose>

								</c:when>
								<c:when test="${nodeId=='NOD_0000000180'}">
								<!-- 常务议题 -->
								<c:choose>
									<c:when test="${buttonStatus=='TODO'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openCitymeetingPage('city_meeting__TODO','${task.stTopicId}')" class="layer_full_link">编辑</a>
										</td>
									</c:when>
									<c:when test="${buttonStatus=='RESULT'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openCitymeetingPage('city_meeting__RESULT','${task.stTopicId}')" class="layer_full_link">编辑</a>
										</td>
									</c:when>
									<c:when test="${buttonStatus=='AFFIRM'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openCitymeetingPage('city_meeting__AFFIRM','${task.stTopicId}')" class="layer_full_link">编辑</a>
										</td>
									</c:when>
									<c:when test="${buttonStatus=='INPUT'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openCitymeetingPage('city_meeting__INPUT','${task.stTopicId}')" class="layer_full_link">编辑</a>
										</td>
									</c:when>
									<c:when test="${buttonStatus=='DONE'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openCitymeetingPage('city_meeting__DONE','${task.stTopicId}')" class="layer_full_link">查看</a>
										</td>
									</c:when>
								</c:choose>
							</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${buttonStatus=='TODO'}">
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stTopicId}')" class="layer_full_link">查看</a><br/><a href="javascript:void(0);" onclick="nextProcess('${task.stTopicId}','${task.stNodeId}','nextChildProcess')" class="layer_full_link">接收</a></td>
										</c:when>
										<c:when test="${buttonStatus=='DOING'}">
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stTopicId}')" class="layer_full_link">查看</a><br/><a href="javascript:void(0);" onclick="openPage('openSeparatePage','${task.stTopicId}')" class="layer_full_link">分办</a></td>
										</c:when>
										<c:otherwise>
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stTopicId}')" class="layer_full_link">查看</a><br/><a href="javascript:void(0);" onclick="openPage('openDraftHistoryPage','${task.stTopicId}')" class="layer_full_link">草案历史 </a><a href="javascript:void(0);" class="layer_full_link"> 办理情况</a></td>
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