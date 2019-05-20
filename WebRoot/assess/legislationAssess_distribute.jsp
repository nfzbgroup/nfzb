<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <span >评估规划 > </span>
        </li>
        <li>
            <span >分送</span>
        </li>
    </ul>
    <button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<form id="legislationAssessDistributeForm" class="form-horizontal"
		  novalidate="novalidate">
        <input hidden name="stTaskId" value="${stTaskId}" >
        <table class="table table-border table-bordered table-bg table-hover">
            <thead>
            <tr class="text-center">
                <th class="text-center">项目名称</th>
                <th class="text-center">发起人</th>
                <th class="text-center">发起时间</th>
                <th class="text-center">选择办理处</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${legislationAssessItemList !=null&&fn:length(legislationAssessItemList)>0}">
                <c:forEach var="assessItem" items="${legislationAssessItemList}">
                    <tr class="text-center">
                        <td>${assessItem.stItemName}</td>
                        <td>${assessItem.stUserName}</td>
                        <td><fmt:formatDate type="date" value="${assessItem.dtCreateDate}" /></td>
                        <td>
                            <select class="form-control" name="stTeamId${assessItem.stItemId}" >
                                <c:if test="${morList!=null&&fn:length(morList)>0}">
                                    <c:forEach var="mor" items="${morList}">
                                        <option value="${mor.teamCid}" >${mor.showName}</option>
                                    </c:forEach>
                                </c:if>
                            </select>
                        </td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
        <div class="form-group text-center">
                <input type="button" class="btn btn-w-m btn-success"  value="确认分送" onclick="saveLegislationAssessDistribute()"> &nbsp;&nbsp;
                <input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
        </div>
	</form>

</div>
<script>
    function saveLegislationAssessDistribute() {
        var param=$('#legislationAssessDistributeForm').formToJson();
        $.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationAssessDistribute",param,function(data){
            $('#legislationProcessForm').modal('hide');
            submitForm(1);
        });
    }
</script>