<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="table table-border table-bordered table-bg table-hover" id="showtable" data-toggle="table" data-mobile-responsive="true" data-card-view="true" data-pagination="true">
	<thead>
		<tr class="text-center">
		
			 <c:if test="${(taskStatus=='TODO')}">
			<th class="text-center" data-field="id"><input type="checkbox" id="J_checkitem_all" onclick="chooseAll(this)"></th>
			</c:if> 
			<th class="text-center" data-field="id" hidden="hidden">${taskStatus}nodeId${taskStatus}编号</th>
			<th class="text-center" data-field="district_name">事项名称</th>
			<th class="text-center" data-field="district_name" hidden="hidden">事项状态</th>
			<th class="text-center" data-field="created_at">事项类型</th>
			<th class="text-center" data-field="st_user_name">发起人</th>
			<th class="text-center" data-field="district_name">发起时间</th>
			<th class="text-center" data-field="set">操作</th>
		</tr>
	</thead>
	<tbody id="tb">
		<c:choose>
			<c:when test="${retPage.totalSize > 0}">
				<c:forEach items="${retPage.result}" var="item">
					<tr class="text-center">
						 <c:if test="${(item.stStatus=='TODO')}">
						<td><input type="checkbox" name="J_checkitem" value="${item.stItemId}"></td>
 						</c:if> 	
						<td hidden="hidden">${item.stItemId}</td>
						<td>${item.stItemName}</td>
						<td hidden="hidden">${item.stStatus}</td>
						<td>${item.stTypeName}</td>
						<td>${item.stUserName}</td>
						<td>
							<fmt:formatDate type="date" value="${item.dtCreateDate}" />
						</td>
							<c:choose>
							<c:when test="${item.stStatus=='TODO'}">
							<td>
							<a href="javaScript:void(0)" data-title="查看" onclick="openPage('openItemInfoPage','${item.stItemId}')" class="layer_full_link">查看</a>
							 <c:if test="${(item.stTypeName!='草案'&&item.stTypeName!='立法计划')}">
								&nbsp;&nbsp;<a href="javaScript:void(0)" data-title="编辑" onclick="openPage('editItemInfoPage','${item.stItemId}')" class="layer_full_link">编辑</a>
							 </c:if> 
							
							</td>							
							</c:when>
							<c:otherwise>
							<td>
							<a href="javaScript:void(0)" data-title="查看" onclick="openPage('openItemInfoPage','${item.stItemId}')" class="layer_full_link">查看</a>
						</td>							</c:otherwise>
						</c:choose>
			<%-- 			
						<c:choose>
							<c:when test="${nodeId=='NOD_0000000101'}">
								<c:choose>
									<c:when test="${buttonStatus=='DONE'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stMeetingId}')" class="layer_full_link">查看</a>
											<br />
											<a href="javascript:void(0);" onclick="openPage('openDraftHistoryPage','${task.stMeetingId}')" class="layer_full_link">草案历史 </a>
											<a href="javascript:void(0);" class="layer_full_link"> 办理情况</a>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<a href="javaScript:void(0)" data-title="修改" onclick="openPage('openEditPage','${task.stMeetingId}')" class="layer_full_link">修改</a>
											<br />
											<a href="javascript:void(0);" onclick="uploadReport('${task.stMeetingId}','${task.stNodeId}')" class="layer_full_link">上报</a>
										</td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${nodeId=='NOD_0000000103'}">
								<c:choose>
									<c:when test="${buttonStatus=='TODO'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stMeetingId}')" class="layer_full_link">查看</a>
											<br />
											<a href="javascript:void(0);" onclick="returnProcess('${task.stMeetingId}','${task.stNodeId}','returnProcess')" class="layer_full_link">退回</a>
											<a href="javascript:void(0);" onclick="nextProcess('${task.stMeetingId}','${task.stNodeId}','nextChildProcess')" class="layer_full_link">确认认领</a>
										</td>
									</c:when>
									<c:when test="${buttonStatus=='DOING'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stMeetingId}')" class="layer_full_link">查看</a>
											<br />
											<a href="javascript:void(0);" onclick="openProcessIndex('${task.stMeetingId}','${task.stDocName}')" class="layer_full_link">办理</a>
											<c:if test="${isZhc eq false}">
												<a href="javascript:void(0);" onclick="openLeaderIdeaPage('openLeaderIdeaPage','${task.stMeetingId}','${task.stNodeId}')" class="layer_full_link">办理完成</a>
										</td>
										</c:if>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stMeetingId}')" class="layer_full_link">查看</a>
											<br />
											<a href="javascript:void(0);" onclick="openPage('openDraftHistoryPage','${task.stMeetingId}')" class="layer_full_link">草案历史 </a>
											<a href="javascript:void(0);" class="layer_full_link"> 办理情况</a>
										</td>
									</c:otherwise>
								</c:choose>

							</c:when>
							<c:when test="${nodeId=='NOD_0000000104'}">
								<c:choose>
									<c:when test="${buttonStatus=='TODO'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('checkMeetingStart','${task.stMeetingId}')" class="layer_full_link">查看</a>
											<br />
											<a href="javaScript:void(0)" data-title="oa审核" onclick="openProMeetPage('openProMeetPage','${task.stMeetingId}','${buttonStatus}')" class="layer_full_link">oa审核</a>
										</td>
									</c:when>
									<c:when test="${buttonStatus=='DOING'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stMeetingId}')" class="layer_full_link">查看</a>
											<br />
											<c:if test="${task.hasReturn eq false}">
												<a href="javaScript:void(0)" data-title="添加oa审核结果" onclick="openProMeetPage('openProMeetPage','${task.stMeetingId}','TODO-RETURN')" class="layer_full_link">添加oa审核结果</a>
												<br>
											</c:if>
											<a href="javascript:void(0);" onclick="gatherProcess('${task.stMeetingId}','${task.stNodeId}','nextProcess',${task.hasReturn})" class="layer_full_link">提交</a>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stMeetingId}')" class="layer_full_link">查看</a>
											<br />
											<a href="javaScript:void(0)" data-title="审核情况" onclick="openPage('openProMeetCheckInfoPage','${task.stMeetingId}')" class="layer_full_link">oa审核情况</a>
											<br />
											<a href="javascript:void(0);" onclick="openPage('openDraftHistoryPage','${task.stMeetingId}')" class="layer_full_link">草案历史 </a>
											<a href="javascript:void(0);" class="layer_full_link"> 办理情况</a>
										</td>
									</c:otherwise>
								</c:choose>

							</c:when>
							<c:when test="${nodeId=='NOD_0000000106'}">
								<!-- 法规规章草案报审 -->
								<c:choose>
									<c:when test="${buttonStatus=='TODO'}">
										<td>
											<a href="javaScript:void(0)" data-title="报审" onclick="openPage('draftReportStart','${task.stMeetingId}')" class="layer_full_link">报审</a>
											<br />

										</td>
									</c:when>
									<c:when test="${buttonStatus=='DONE'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stMeetingId}')" class="layer_full_link">查看</a>
										</td>
									</c:when>
								</c:choose>

							</c:when>
							<c:when test="${nodeId=='NOD_0000000190'}">
								<!-- 法规规章草案报审处理 -->
								<c:choose>
									<c:when test="${buttonStatus=='DONE'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stMeetingId}')" class="layer_full_link">查看</a>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<a href="javaScript:void(0)" data-title="处理" onclick="openProMeetPage('draftReportAudit','${task.stMeetingId}','${buttonStatus}')" class="layer_full_link">11处理</a>
											<br />
										</td>
									</c:otherwise>
								</c:choose>
							</c:when>
							<c:when test="${nodeId=='NOD_0000000170'}">
								<!-- 法治调研处审核会议 -->
								<c:choose>
									<c:when test="${buttonStatus=='TODO'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('checkmeeting_add','${task.stMeetingId}')" class="layer_full_link">编辑</a>
										</td>
									</c:when>
									<c:when test="${buttonStatus=='FEEDBACK'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('checkmeeting_feedback','${task.stMeetingId}')" class="layer_full_link">编辑</a>
										</td>
									</c:when>
									<c:when test="${buttonStatus=='INPUT'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('checkmeeting_input','${task.stMeetingId}')" class="layer_full_link">编辑</a>
										</td>
									</c:when>
									<c:when test="${buttonStatus=='AFFIRM'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('checkmeeting_affirm','${task.stMeetingId}')" class="layer_full_link">编辑</a>
										</td>
									</c:when>
									<c:when test="${buttonStatus=='DONE'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('checkmeeting_end','${task.stMeetingId}')" class="layer_full_link">查看</a>
										</td>
									</c:when>
								</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${buttonStatus=='TODO'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stMeetingId}')" class="layer_full_link">2查看</a>
											<br />
											<a href="javascript:void(0);" onclick="nextProcess('${task.stMeetingId}','${task.stNodeId}','nextChildProcess')" class="layer_full_link">1接收</a>
										</td>
									</c:when>
									<c:when test="${buttonStatus=='DOING'}">
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stMeetingId}')" class="layer_full_link">3查看</a>
											<br />
											<a href="javascript:void(0);" onclick="openPage('openSeparatePage','${task.stMeetingId}')" class="layer_full_link">4分办</a>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPage('openInfoPage','${task.stMeetingId}')" class="layer_full_link">查看</a>
											<br />
											<a href="javascript:void(0);" onclick="openPage('openDraftHistoryPage','${task.stMeetingId}')" class="layer_full_link">草案历史 </a>
											<a href="javascript:void(0);" class="layer_full_link"> 办理情况</a>
										</td>
									</c:otherwise>
								</c:choose>
							</c:otherwise>
						</c:choose>
					
					 --%>
					</tr>
				</c:forEach>
			</c:when>
		</c:choose>
	</tbody>
</table>
<div class="clearfix">
	<div class="list-page" id="listPage"></div>
</div>
<script>
	
	if(${retPage.totalSize > 0}){
		fullPageList(${retPage.totalSize},${pageNo},${pageSize},'listPage');
	}
	//待审事项、审核中、已审核按钮触发事件调用函数
    function changeType(type) {
        $('#'+type).parent().children().attr("class","btn btn-w-m btn-default");
        $('#'+type).attr("class","btn btn-w-m btn-success");
        $('#taskStatus').val(type);
        var b = (type == 'TODO');//审核中、已审核状态是审核会议发起隐藏或无法点击
        if(b){
        	$("#add").show();
        	$("#additem").show();
        }else{
        	$("#add").hide();
        	$("#additem").hide();
        }
        submitForm(1);
    }
    
    /* function sendProcess(method,stTaskId,buttonStatus,stMeetingId,stNodeId,hasSendReturn) {
        if(hasSendReturn){
            nextProcess(stMeetingId,stNodeId,"nextChildProcess");
        }else{
            Duang.error("提示", "审核未完成，请等待或手动添加审核结果！");
        }
    }
    function publishProcess(method,stTaskId,buttonStatus,stMeetingId,stNodeId,hasSendReturn) {
        if(hasSendReturn){
            openTaskPageWithStatus(method,stTaskId,buttonStatus,stMeetingId,stNodeId)
        }else{
            Duang.error("提示", "审核未完成，请等待或手动添加审核结果！");
        }
    }
    function gatherProcess(stMeetingId,stNodeId,method,hasReturn) {
        if(hasReturn){
            nextProcess(stMeetingId,stNodeId,method);
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

    function uploadReport(stMeetingId,stNodeId) {
        $.post("../legislationProcessTask/uploadReport.do?stMeetingId="+stMeetingId+"&stNode="+stNodeId,
            function (data) {
                if(data.success) {
                    nextProcess(stMeetingId,stNodeId,"nextProcess");
                }else{
                    Duang.error("提示", "请补全必填材料！");
                }
            },
            "json")
    }

    function nextProcess(stMeetingId,stNodeId,method) {
        layer.confirm('请确认操作！',function(index){
            layer.close(layer.index);
			$.post("../"+$('#requestUrl').val()+"?stMeetingId="+stMeetingId+"&stNodeId="+stNodeId+"&method="+method);
            submitForm(1);
        });
    }

    function returnProcess(stMeetingId,stNodeId,method) {
        layer.confirm('请确认操作！',function(index){
            layer.close(layer.index);
            $.post("../"+$('#requestUrl').val()+"?stMeetingId="+stMeetingId+"&stNodeId="+stNodeId+"&method="+method);
            submitForm(1);
        });
    } */
    
</script>