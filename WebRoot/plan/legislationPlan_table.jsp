<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="table table-border table-bordered table-bg table-hover" id="showtable" data-toggle="table" data-mobile-responsive="true" data-card-view="true" data-pagination="true">
	<thead>
		<tr class="text-center">
			<c:choose>
				<c:when test="${nodeId=='NOD_0000000201'||nodeId=='NOD_0000000208'||nodeId=='NOD_0000000209'||nodeId=='NOD_0000000211'}">
					<th class="text-center" data-field="id">通知编号</th>
					<th class="text-center" data-field="district_name">通知名称</th>
					<c:if test="${nodeId=='NOD_0000000201'}">
						<th class="text-center" data-field="district_name">处理环节</th>
					</c:if>
					<th class="text-center" data-field="created_at">发起人</th>
					<th class="text-center" data-field="district_name">发起时间</th>
					<c:if test="${buttonStatus=='DONE'}">
						<th class="text-center" data-field="district_name">处理时间</th>
					</c:if>
					<th class="text-center" data-field="set">操作</th>
				</c:when>
				<c:when test="${nodeId=='NOD_0000000202'||nodeId=='NOD_0000000203'||nodeId=='NOD_0000000204'||nodeId=='NOD_0000000205'||nodeId=='NOD_0000000207'}">
					<th class="text-center" data-field="id">项目编号</th>
					<th class="text-center" data-field="district_name">立法项目发起名称</th>
					<th class="text-center" data-field="district_name">处理环节</th>
					<th class="text-center" data-field="created_at">发起人</th>
					<th class="text-center" data-field="district_name">发起时间</th>
					<c:if test="${buttonStatus=='DONE'}">
						<th class="text-center" data-field="district_name">处理时间</th>
					</c:if>
					<th class="text-center" data-field="set">操作</th>
				</c:when>
			</c:choose>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${nodeId=='NOD_0000000201'||nodeId=='NOD_0000000208'||nodeId=='NOD_0000000209'||nodeId=='NOD_0000000211'}">
				<c:choose>
					<c:when test="${retPage.totalSize > 0}">
						<c:forEach items="${retPage.result}" var="plan">
							<tr class="text-center">
								<td>${plan.stPlanId}</td>
								<td>${plan.stFlowId}</td>
								<c:if test="${nodeId=='NOD_0000000201'}">
									<td>${plan.stNodeName}</td>
								</c:if>
								<td>${plan.stUserName}</td>
								<td>
									<fmt:formatDate type="date" value="${plan.dtOpenDate}" />
								</td>
								<c:choose>
									<c:when test="${buttonStatus=='TODO'}">
										<td>
											<c:if test="${nodeId=='NOD_0000000201'}">
												<a href="javaScript:void(0)" data-title="编辑" onclick="openPlanPage('openNoticeEditPage','${plan.stTaskId}')" class="layer_full_link">修改</a>
												<br>
												<a href="javaScript:void(0)" data-title="发布" onclick="nextPlanProcess('${plan.stTaskId}','${plan.stNodeId}')" class="layer_full_link">发布</a>
											</c:if>
											<c:if test="${nodeId=='NOD_0000000208'}">
												<a href="javaScript:void(0)" data-title="查看" onclick="openPlanPage('openNoticeInfoPage','${plan.stTaskId}')" class="layer_full_link">查看</a>
												<br>
												<a href="javaScript:void(0)" data-title="项目详情" onclick="openPlanPage('openNoticeProjectInfoPage','${plan.stTaskId}')" class="layer_full_link">项目详情</a>
												<br>
												<a href="javaScript:void(0)" data-title="处理" onclick="checkPlanItem('${plan.stTaskId}','${plan.stNodeId}')" class="layer_full_link">处理</a>
											</c:if>
											<c:if test="${nodeId=='NOD_0000000209'||nodeId=='NOD_0000000211'}">
												<a href="javaScript:void(0)" data-title="查看" onclick="openPlanPage('openNoticeInfoPage','${plan.stTaskId}')" class="layer_full_link">查看</a>
												<br>
												<a href="javaScript:void(0)" data-title="编辑计划说明" onclick="openPlanPage('openNoticeExplainPage','${plan.stTaskId}')" class="layer_full_link">编辑计划说明</a>
												<br>
												<a href="javaScript:void(0)" data-title="处理" onclick="checkPlanExplain('${plan.stTaskId}','${plan.stNodeId}')" class="layer_full_link">处理</a>
											</c:if>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<fmt:formatDate type="date" value="${plan.dtDealDate}" />
										</td>
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPlanPage('openNoticeInfoPage','${plan.stTaskId}')" class="layer_full_link">查看</a>
											<c:if test="${nodeId=='NOD_0000000209'||nodeId=='NOD_0000000211'}">
											<br>
											<a href="javaScript:void(0)" data-title="查看计划说明" onclick="openPlanPage('openNoticeExplainInfoPage','${plan.stTaskId}')" class="layer_full_link">查看计划说明</a>
											</c:if>
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</c:when>
			<c:when test="${nodeId=='NOD_0000000202'||nodeId=='NOD_0000000203'||nodeId=='NOD_0000000204'||nodeId=='NOD_0000000205'||nodeId=='NOD_0000000207'}">
				<c:choose>
					<c:when test="${retPage.totalSize > 0}">
						<c:forEach items="${retPage.result}" var="plan">
							<tr class="text-center">
								<td>${plan.stParentId}</td>
								<td>${plan.stFlowId}</td>
								<td>${plan.stNodeName}</td>
								<td>${plan.stUserName}</td>
								<td>
									<fmt:formatDate type="date" value="${plan.dtOpenDate}" />
								</td>
								<c:choose>
									<c:when test="${buttonStatus=='TODO'}">
										<td>
											<c:if test="${nodeId=='NOD_0000000202'}">
												<a href="javaScript:void(0)" data-title="编辑" onclick="openPlanPage('openPlanEditPage','${plan.stTaskId}')" class="layer_full_link">修改</a>
												<br>
												<a href="javaScript:void(0)" data-title="上报" onclick="nextPlanProcess('${plan.stTaskId}','${plan.stNodeId}')" class="layer_full_link">上报</a>
											</c:if>
											<c:if test="${nodeId=='NOD_0000000203'}">
												<a href="javaScript:void(0)" data-title="查看" onclick="openPlanPage('openPlanInfoPage','${plan.stTaskId}')" class="layer_full_link">查看</a>
												<br>
												<a href="javaScript:void(0)" data-title="接收" onclick="nextPlanProcess('${plan.stTaskId}','${plan.stNodeId}')" class="layer_full_link">接收</a>
											</c:if>
											<c:if test="${nodeId=='NOD_0000000204'}">
												<a href="javaScript:void(0)" data-title="查看" onclick="openPlanPage('openPlanInfoPage','${plan.stTaskId}')" class="layer_full_link">查看</a>
												<br>
												<a href="javaScript:void(0)" data-title="分办" onclick="openPlanPage('openPlanSeparatePage','${plan.stTaskId}')" class="layer_full_link">分办</a>
											</c:if>
											<c:if test="${nodeId=='NOD_0000000205'}">
												<a href="javaScript:void(0)" data-title="查看" onclick="openPlanPage('openPlanInfoPage','${plan.stTaskId}')" class="layer_full_link">查看</a>
												<br>
												<a href="javaScript:void(0)" data-title="审核" onclick="openPlanPage('openPlanAuditPage','${plan.stTaskId}')" class="layer_full_link">审核</a>
											</c:if>
											<c:if test="${nodeId=='NOD_0000000207'}">
												<a href="javaScript:void(0)" data-title="编辑" onclick="openPlanPage('openPlanEditPage','${plan.stTaskId}')" class="layer_full_link">编辑</a>
												<br>
												<a href="javaScript:void(0)" data-title="下一步" onclick="openPlanPage('openPlanCheckExplainPage','${plan.stTaskId}')" class="layer_full_link">下一步</a>
											</c:if>
										</td>
									</c:when>
									<c:when test="${buttonStatus=='DOING'}">
										<c:if test="${nodeId=='NOD_0000000207'}">
											<td>
												<a href="javaScript:void(0)" data-title="查看" onclick="openPlanPage('openPlanInfoPage','${plan.stTaskId}')" class="layer_full_link">查看</a>
												<br>
												<a href="javaScript:void(0)" data-title="下一步" onclick="openPlanPage('openPlanCheckExplainPage','${plan.stTaskId}')" class="layer_full_link">下一步</a>
											</td>
										</c:if>
									</c:when>
									<c:otherwise>
										<td>
											<fmt:formatDate type="date" value="${plan.dtDealDate}" />
										</td>
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openPlanPage('openPlanInfoPage','${plan.stTaskId}')" class="layer_full_link">查看</a>
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
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
    function changeType(type) {
        $('#'+type).parent().children().attr("class","btn btn-w-m btn-default");
        $('#'+type).attr("class","btn btn-w-m btn-success");
        $('#taskStatus').val(type);
        submitForm(1);
    }

    function checkPlanItem(stTaskId,stNodeId) {
        $.post("../legislationPlanItem/checkPlanItem.do?stTaskId="+stTaskId+"&stNode="+stNodeId,
            function (data) {
                if(data.success) {
                    nextPlanProcess(stTaskId,stNodeId);
                }else{
                    Duang.error("提示", "请将所有项目都审核完！");
                }
            },
            "json")
    }

    function checkPlanExplain(stTaskId,stNodeId) {
        $.post("../legislationPlan/checkPlanExplain.do?stTaskId="+stTaskId+"&stNode="+stNodeId,
            function (data) {
                if(data.success) {
                    nextPlanProcess(stTaskId,stNodeId);
                }else{
                    Duang.error("提示", "请补全计划说明信息！");
                }
            },
            "json")
    }
    function nextPlanProcess(stTaskId,stNodeId) {
        layer.confirm('请确认操作！',function(index){
            layer.close(layer.index);
            $.post("../"+$('#requestUrl').val()+"?stTaskId="+stTaskId+"&stNodeId="+stNodeId+"&method=nextPlanProcess");
            submitForm(1);
        });
    }
</script>