<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <span >征集通知 > </span>
        </li>
        <li>
            <span >项目详情</span>
        </li>
    </ul>
    <button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
    <table class="table table-border table-bordered table-bg table-hover">
        <thead>
            <tr class="text-center">
                <th class="text-center">项目名称</th>
                <th class="text-center">项目状态</th>
                <c:if test="${nodeId=='NOD_0000000215'}">
                    <th class="text-center">项目归属</th>
                </c:if>
                <th class="text-center">发起人</th>
                <th class="text-center">发起时间</th>
                <c:if test="${nodeId=='NOD_0000000215'&&status=='TODO'}">
                    <th class="text-center">操作</th>
                </c:if>
            </tr>
        </thead>
        <tbody>
        <c:if test="${legislationPlanItemList !=null&&fn:length(legislationPlanItemList)>0}">
            <c:forEach var="planItem" items="${legislationPlanItemList}">
                <tr class="text-center">
                    <td>${planItem.stItemName}</td>
                    <td>
                    	 <c:set var="theString" value='${planItem.stStatus}' />
	                   	 <c:if test="${fn:contains(theString,'已处理')}" var="flag" scope="session">
							 <p>a<c:out value="${planItem.stStatus}"/><p>
						 </c:if>
					 	 <c:if test="${not flag}">
						 	  <p> <span style="color:red;"><c:out value="${planItem.stStatus}" /></span>	 <p>
						 </c:if>
                    </td>
                    <c:if test="${nodeId=='NOD_0000000215'}">
                        <td id="${planItem.stItemId}">${planItem.stSuggest}</td>
                    </c:if>
                    <td>${planItem.stUserName}</td>
                    <td><fmt:formatDate type="date" value="${planItem.dtCreateDate}" /></td>
                    <c:if test="${nodeId=='NOD_0000000215'&&status=='TODO'}">
                        <td><a href="javaScript:void(0)" data-title="项目归属" onclick="openProjectPage('openProjectAscriptionPage','${planItem.stItemId}')" class="layer_full_link">项目归属</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <div class="form-group text-center">
        <input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
    </div>
	<div class="modal inmodal fade" id="legislationProcessChildForm"
		data-backdrop keyboard tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content"></div>
		</div>
	</div>
</div>
<script>
    function openProjectPage(method,stItemId) {
        $("#legislationProcessChildForm").modal({
            remote: "${basePath}/legislationPlan/draft_plan_info.do?stNodeId=${nodeId}&method="+method+"&stItemId="+stItemId
        });
    };
</script>