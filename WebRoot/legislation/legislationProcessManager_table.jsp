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
		<c:choose>
			<c:when test="${nodeId=='NOD_0000000140'||nodeId=='NOD_0000000141'}">
				<th class="text-center" data-field="id">编号</th>
				<th class="text-center" data-field="district_name">对应草案</th>
				<th class="text-center" data-field="district_name">听证会议题</th>
				<th class="text-center" data-field="created_at">听证会时间</th>
				<th class="text-center" data-field="district_name">听证会地点</th>
				<th class="text-center" data-field="set">操作</th>
			</c:when>
			<c:when test="${nodeId=='NOD_0000000150'||nodeId=='NOD_0000000151'}">
				<th class="text-center" data-field="id">编号</th>
				<th class="text-center" data-field="district_name">对应草案</th>
				<th class="text-center" data-field="district_name">论证会议题</th>
				<th class="text-center" data-field="created_at">论证会时间</th>
				<th class="text-center" data-field="district_name">论证会地点</th>
				<th class="text-center" data-field="set">操作</th>
			</c:when>
			<c:when test="${nodeId=='NOD_0000000120'||nodeId=='NOD_0000000121'}">
				<th class="text-center" data-field="id">编号</th>
				<th class="text-center" data-field="district_name">对应草案</th>
				<c:if test="${buttonStatuss=='TODO'}">
					<th class="text-center" data-field="district_name">经办处</th>
					<th class="text-center" data-field="created_at">发起日期</th>
				</c:if>
				<c:if test="${buttonStatuss=='DONE'}">
					<th class="text-center" data-field="district_name">类型</th>
					<th class="text-center" data-field="created_at">征询发起日期</th>
				</c:if>
				<th class="text-center" data-field="district_name">经办处</th>
				<th class="text-center" data-field="created_at">发起日期</th>
				<th class="text-center" data-field="set">操作</th>
			</c:when>
			<c:otherwise>
				<th class="text-center" data-field="id">草案编号</th>
				<th class="text-center" data-field="district_name">法规规章草案</th>
				<th class="text-center" data-field="district_name">处理环节</th>
				<th class="text-center" data-field="district_name">发起人</th>
				<th class="text-center" data-field="created_at">发起时间</th>
				<th class="text-center" data-field="set">操作</th>
			</c:otherwise>
		</c:choose>

	</tr>
	</thead>
	<tbody>
	<c:choose>
		<c:when test="${nodeId=='NOD_0000000140'||nodeId=='NOD_0000000141'}">
				<c:choose>
					<c:when test="${retPage.totalSize > 0}">
						<c:forEach items="${retPage.result}" var="task">
							<tr class="text-center">
								<td >${task.stTaskId}</td>
								<td >${task.stFlowId}</td>
								<td >${task.stBakOne}</td>
								<td ><fmt:formatDate type="date" value="${task.dtBakDate}" /></td>
								<td >${task.stBakTwo}</td>
								<c:choose>
									<c:when test="${nodeId=='NOD_0000000140'}">
										<c:choose>
											<c:when test="${buttonStatus=='TODO'}">
												<td ><a href="javaScript:void(0)" data-title="编辑" onclick="openTaskPage('openHeartMeetingEditPage','${task.stTaskId}')" class="layer_full_link">编辑</a><br>
													<a href="javaScript:void(0)" data-title="上报" onclick="uploadReport('${task.stDocId}','${task.stNodeId}')" class="layer_full_link">上报</a></td>
											</c:when>
											<c:otherwise>
												<td >
                                                    <a href="javaScript:void(0)" data-title="审核情况" onclick="openPage('openHeartMeetingCheckInfoPage','${task.stDocId}')" class="layer_full_link">审核情况</a><br/>
                                                    <a href="javaScript:void(0)" data-title="查看会前" onclick="openTaskPage('openHeartMeetingBeforeInfoPage','${task.stTaskId}')" class="layer_full_link">查看会前</a><br/>
													<a href="javaScript:void(0)" data-title="查看会后" onclick="openTaskPage('openHeartMeetingAfterInfoPage','${task.stTaskId}')" class="layer_full_link">查看会后</a><br/></td>

											</c:otherwise>
										</c:choose>
									</c:when>
									<c:otherwise>
										<c:choose>
											<c:when test="${buttonStatus=='TODO'}">
												<td ><a href="javaScript:void(0)" data-title="查看会前" onclick="openTaskPage('openHeartMeetingBeforeInfoPage','${task.stTaskId}')" class="layer_full_link">查看会前</a><br>
													<a href="javaScript:void(0)" data-title="接收" onclick="nextProcess('${task.stDocId}','${task.stNodeId}','nextChildProcess')" class="layer_full_link">接收</a></td>
											</c:when>
											<c:when test="${buttonStatus=='SEND'}">
												<td ><a href="javaScript:void(0)" data-title="查看会前" onclick="openTaskPage('openHeartMeetingBeforeInfoPage','${task.stTaskId}')" class="layer_full_link">查看会前</a><br>
													<a href="javaScript:void(0)" data-title="送审" onclick="openTaskPageWithStatus('openCheckExplainPage','${task.stTaskId}','${buttonStatus}','${task.stDocId}','${task.stNodeId}')"  class="layer_full_link">送审</a></td>
											</c:when>
											<c:when test="${buttonStatus=='PUBLISH'}">
												<td ><a href="javaScript:void(0)" data-title="查看会前" onclick="openTaskPage('openHeartMeetingBeforeInfoPage','${task.stTaskId}')" class="layer_full_link">查看会前</a><br>
													 <a href="javaScript:void(0)" data-title="审核情况" onclick="openPage('openHeartMeetingCheckInfoPage','${task.stDocId}')" class="layer_full_link">审核情况</a><br/>
													<c:if test="${task.hasSendReturn eq false}">
                                                        <a href="javaScript:void(0)" data-title="送审" onclick="openTaskPageWithStatus('openCheckExplainPage','${task.stTaskId}','SEND-RETURN','${task.stDocId}','${task.stNodeId}')"  class="layer_full_link">添加审核结果</a><br>
                                                    </c:if>
                                                    <a href="javaScript:void(0)" data-title="发布" onclick="publishProcess('openCheckExplainPage','${task.stTaskId}','${buttonStatus}','${task.stDocId}','${task.stNodeId}',${task.hasSendReturn})"  class="layer_full_link">发布</a></td>
											</c:when>
											<c:when test="${buttonStatus=='GATHER'}">
												<td ><a href="javaScript:void(0)" data-title="查看会前" onclick="openTaskPage('openHeartMeetingBeforeInfoPage','${task.stTaskId}')" class="layer_full_link">查看会前</a><br>
													<a href="javaScript:void(0)" data-title="审核情况" onclick="openPage('openHeartMeetingCheckInfoPage','${task.stDocId}')" class="layer_full_link">审核情况</a><br/>
													<c:if test="${task.hasGatherReturn eq false}">
                                                        <a href="javaScript:void(0)" data-title="网上报名" onclick="openTaskPageWithStatus('openCheckExplainPage','${task.stTaskId}','GATHER-RETURN','${task.stDocId}','${task.stNodeId}')"  class="layer_full_link">添加网上报名结果</a><br>
                                                    </c:if>
                                                    <a href="javaScript:void(0)" data-title="汇总" onclick="gatherProcess('${task.stDocId}','${task.stNodeId}','nextChildProcess',${task.hasGatherReturn})"  class="layer_full_link">汇总</a></td>
											</c:when>
											<c:when test="${buttonStatus=='RESULT'}">
												<td ><a href="javaScript:void(0)" data-title="查看会前" onclick="openTaskPage('openHeartMeetingBeforeInfoPage','${task.stTaskId}')" class="layer_full_link">查看会前</a><br>
													<a href="javaScript:void(0)" data-title="审核情况" onclick="openPage('openHeartMeetingCheckInfoPage','${task.stDocId}')" class="layer_full_link">审核情况</a><br/>
													<a href="javaScript:void(0)" data-title="编辑" onclick="openTaskPage('openHeartMeetingEditPage','${task.stTaskId}')" class="layer_full_link">编辑会后信息</a><br/>
                                                    <a href="javaScript:void(0)" data-title="确认发布" onclick="nextProcess('${task.stDocId}','${task.stNodeId}','nextChildProcess')"  class="layer_full_link">确认发布</a></td>
											</c:when>
											<c:otherwise>
												<td >
													<a href="javaScript:void(0)" data-title="审核情况" onclick="openPage('openHeartMeetingCheckInfoPage','${task.stDocId}')" class="layer_full_link">审核情况</a><br/>
													<a href="javaScript:void(0)" data-title="查看会前" onclick="openTaskPage('openHeartMeetingBeforeInfoPage','${task.stTaskId}')"  class="layer_full_link">查看会前</a>
													<a href="javaScript:void(0)" data-title="查看会后" onclick="openTaskPage('openHeartMeetingAfterInfoPage','${task.stTaskId}')" class="layer_full_link">查看会后</a></td>
											</c:otherwise>
										</c:choose>
									</c:otherwise>
								</c:choose>

							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
		</c:when>
		<c:when test="${nodeId=='NOD_0000000120'||nodeId=='NOD_0000000121'}">
			<c:choose>
				<c:when test="${retPage.totalSize > 0}">
					<c:forEach items="${retPage.result}" var="task">
						<tr class="text-center">
							<td >${task.stTaskId}</td>
							<td >${task.stFlowId}</td>
							<td ><c:if test="${buttonStatus=='TODO'}">${task.stTeamName}</c:if><c:if test="${buttonStatus=='DONE'}">${task.stBakOne}</c:if> </td>
							<td ><fmt:formatDate type="date" value="${task.dtOpenDate}" /></td>
								<c:choose>
									<c:when test="${buttonStatus=='TODO'}">
										<td >
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stDocId}')" class="layer_full_link">查看</a><br>
											<a href="javaScript:void(0)" data-title="发送" onclick="openTaskPage('openUnitSeekPage','${task.stTaskId}')" class="layer_full_link">发送</a>
										</td>
									</c:when>
									<c:otherwise>
										<td >
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stDocId}')" class="layer_full_link">查看</a><br/>
											<a href="javaScript:void(0)" data-title="接收情况" onclick="openTaskPage('openUnitReceivePage','${task.stTaskId}')" class="layer_full_link">接收情况</a><br/>
										</td>
									</c:otherwise>
								</c:choose>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>
		</c:when>
		<c:otherwise>
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
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stDocId}')" class="layer_full_link">查看</a><br/><a href="javascript:void(0);" onclick="openPage('openDraftHistoryPage','${task.stDocId}')" class="layer_full_link">草案历史 </a><a href="javascript:void(0);" class="layer_full_link"> 办理情况</a></td>
										</c:when>
										<c:otherwise>
											<td ><a href="javaScript:void(0)" data-title="修改" onclick="openPage('openEditPage','${task.stDocId}')" class="layer_full_link">修改</a><br/><a href="javascript:void(0);" onclick="uploadReport('${task.stDocId}','${task.stNodeId}')" class="layer_full_link">上报</a></td>
										</c:otherwise>
									</c:choose>

								</c:when>
								<c:when test="${nodeId=='NOD_0000000103'}">
									<c:choose>
										<c:when test="${buttonStatus=='TODO'}">
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stDocId}')" class="layer_full_link">查看</a><br/>
												<a href="javascript:void(0);" onclick="returnProcess('${task.stDocId}','${task.stNodeId}','returnProcess')" class="layer_full_link">退回</a>
												<a href="javascript:void(0);" onclick="nextProcess('${task.stDocId}','${task.stNodeId}','nextChildProcess')" class="layer_full_link">确认认领</a></td>
										</c:when>
										<c:when test="${buttonStatus=='DOING'}">
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stDocId}')" class="layer_full_link">查看</a><br/>
												<a href="javascript:void(0);" onclick="openPage('openDemonstrationPage','${task.stDocId}')"  class="layer_full_link">办理</a></td>
										</c:when>
										<c:otherwise>
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stDocId}')" class="layer_full_link">查看</a><br/><a href="javascript:void(0);" onclick="openPage('openDraftHistoryPage','${task.stDocId}')" class="layer_full_link">草案历史 </a><a href="javascript:void(0);" class="layer_full_link"> 办理情况</a></td>
										</c:otherwise>
									</c:choose>

								</c:when>
								<c:otherwise>
									<c:choose>
										<c:when test="${buttonStatus=='TODO'}">
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stDocId}')" class="layer_full_link">查看</a><br/><a href="javascript:void(0);" onclick="nextProcess('${task.stDocId}','${task.stNodeId}','nextChildProcess')" class="layer_full_link">接收</a></td>
										</c:when>
										<c:when test="${buttonStatus=='DOING'}">
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stDocId}')" class="layer_full_link">查看</a><br/><a href="javascript:void(0);" onclick="openPage('openSeparatePage','${task.stDocId}')" class="layer_full_link">分办</a></td>
										</c:when>
										<c:otherwise>
											<td ><a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stDocId}')" class="layer_full_link">查看</a><br/><a href="javascript:void(0);" onclick="openPage('openDraftHistoryPage','${task.stDocId}')" class="layer_full_link">草案历史 </a><a href="javascript:void(0);" class="layer_full_link"> 办理情况</a></td>
										</c:otherwise>
									</c:choose>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</c:when>
			</c:choose>

		</c:otherwise>
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
    function sendProcess(method,stTaskId,buttonStatus,stDocId,stNodeId,hasSendReturn) {
        if(hasSendReturn){
            nextProcess(stDocId,stNodeId,"nextChildProcess");
        }else{
            Duang.error("提示", "审核未完成，请等待或手动添加审核结果！");
        }
    }
    function publishProcess(method,stTaskId,buttonStatus,stDocId,stNodeId,hasSendReturn) {
        if(hasSendReturn){
            openTaskPageWithStatus(method,stTaskId,buttonStatus,stDocId,stNodeId)
        }else{
            Duang.error("提示", "审核未完成，请等待或手动添加审核结果！");
        }
    }
    function gatherProcess(stDocId,stNodeId,method,hasGatherReturn) {
        if(hasGatherReturn){
            nextProcess(stDocId,stNodeId,"nextChildProcess");
        }else{
            Duang.error("提示", "网上报名结果未反馈，请等待或手动添加！");
        }
    }
    function uploadReport(stDocId,stNodeId) {
        $.post("../legislationProcessTask/uploadReport.do?stDocId="+stDocId+"&stNode="+stNodeId,
            function (data) {
                if(data.success) {
                    nextProcess(stDocId,stNodeId,"nextProcess");
                }else{
                    Duang.error("提示", "请补全必填材料！");
                }

            },
            "json")

    }

    function nextProcess(stDocId,stNodeId,method) {
        layer.confirm('请确认操作！',function(index){
            layer.close(layer.index);
			$.post("../"+$('#requestUrl').val()+"?stDocId="+stDocId+"&stNodeId="+stNodeId+"&method="+method);
            submitForm(1);
        });
    }

    function returnProcess(stDocId,stNodeId,method) {
        layer.confirm('请确认操作！',function(index){
            layer.close(layer.index);
            $.post("../"+$('#requestUrl').val()+"?stDocId="+stDocId+"&stNodeId="+stNodeId+"&method="+method);
            submitForm(1);
        });
    }
</script>