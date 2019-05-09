<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="table table-border table-bordered table-bg table-hover" id="showtable" data-toggle="table" data-mobile-responsive="true" data-card-view="true" data-pagination="true">
	<thead>
		<tr class="text-center">
			<c:choose>
				<c:when test="${nodeId=='NOD_0000000251'||nodeId=='NOD_0000000253'||nodeId=='NOD_0000000255'||nodeId=='NOD_0000000262'}">
					<th class="text-center" data-field="district_name">规划名称</th>
					<th class="text-center" data-field="district_name">处理环节</th>
					<th class="text-center" data-field="created_at">发起人</th>
					<th class="text-center" data-field="district_name">发起时间</th>
					<c:if test="${buttonStatus=='DONE'}">
						<th class="text-center" data-field="district_name">处理时间</th>
					</c:if>
					<th class="text-center" data-field="set">操作</th>
				</c:when>
				<c:otherwise>
					<th class="text-center" data-field="district_name">评估项目名称</th>
					<th class="text-center" data-field="district_name">处理环节</th>
					<th class="text-center" data-field="created_at">发起人</th>
					<th class="text-center" data-field="district_name">发起时间</th>
					<c:if test="${buttonStatus=='DONE'}">
						<th class="text-center" data-field="district_name">处理时间</th>
					</c:if>
					<th class="text-center" data-field="set">操作</th>
				</c:otherwise>
			</c:choose>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${nodeId=='NOD_0000000251'||nodeId=='NOD_0000000253'||nodeId=='NOD_0000000255'||nodeId=='NOD_0000000262'}">
				<c:choose>
					<c:when test="${retPage.totalSize > 0}">
						<c:forEach items="${retPage.result}" var="plan">
							<tr class="text-center">
								<td>${plan.stFlowId}</td>
								<td>${plan.stNodeName}</td>
								<td>${plan.stUserName}</td>
								<td>
									<fmt:formatDate type="date" value="${plan.dtOpenDate}" />
								</td>
								<c:choose>
									<c:when test="${buttonStatus=='TODO'}">
										<td>
											<c:if test="${nodeId=='NOD_0000000251'}">
												<a href="javaScript:void(0)" data-title="编辑" onclick="openAssessPage('openAssessEditPage','${plan.stTaskId}')" class="layer_full_link">编辑</a>
												<a href="javaScript:void(0)" data-title="启动" onclick="nextAssessProcess('${plan.stTaskId}','${plan.stNodeId}')" class="layer_full_link">启动</a>
											</c:if>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<fmt:formatDate type="date" value="${plan.dtDealDate}" />
										</td>
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openAssessPage('openAssessInfoPage','${plan.stTaskId}')" class="layer_full_link">查看</a>
										</td>
									</c:otherwise>
								</c:choose>
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</c:when>
			<c:otherwise >
				<c:choose>
					<c:when test="${retPage.totalSize > 0}">
						<c:forEach items="${retPage.result}" var="plan">
							<tr class="text-center">
								<td>${plan.stFlowId}</td>
								<td>${plan.stNodeName}</td>
								<td>${plan.stUserName}</td>
								<td>
									<fmt:formatDate type="date" value="${plan.dtOpenDate}" />
								</td>
								<c:choose>
									<c:when test="${buttonStatus=='TODO'}">
										<td>
											<c:if test="${nodeId=='NOD_0000000252'}">
												<a href="javaScript:void(0)" data-title="编辑" onclick="openAssessItemPage('openAssessItemEditPage','${plan.stTaskId}')" class="layer_full_link">编辑</a>
												<a href="javaScript:void(0)" data-title="申请" onclick="nextAssessProcess('${plan.stTaskId}','${plan.stNodeId}')" class="layer_full_link">申请</a>
											</c:if>
										</td>
									</c:when>
									<c:otherwise>
										<td>
											<fmt:formatDate type="date" value="${plan.dtDealDate}" />
										</td>
										<td>
											<a href="javaScript:void(0)" data-title="查看" onclick="openAssessItemPage('openAssessItemInfoPage','${plan.stTaskId}')" class="layer_full_link">查看</a>
										</td>
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
    function nextAssessProcess(stTaskId,stNodeId) {
        layer.confirm('请确认操作！',function(index){
            layer.close(layer.index);
            $.post("../"+$('#requestUrl').val()+"?stTaskId="+stTaskId+"&stNodeId="+stNodeId+"&method=nextAssessProcess");
            submitForm(1);
        });
    }
</script>