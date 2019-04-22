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
                <th class="text-center">计划编号</th>
                <th class="text-center">项目名称</th>
                <th class="text-center">项目状态</th>
                <th class="text-center">发起人</th>
                <th class="text-center">发起时间</th>
            </tr>
        </thead>
        <tbody>
        <c:if test="${legislationPlanItemList !=null&&fn:length(legislationPlanItemList)>0}">
            <c:forEach var="planItem" items="${legislationPlanItemList}">
                <tr class="text-center">
                    <td>${planItem.stItemId}</td>
                    <td>${planItem.stItemName}</td>
                    <td>${planItem.stStatus}</td>
                    <td>${planItem.stUserName}</td>
                    <td><fmt:formatDate type="date" value="${planItem.dtCreateDate}" /></td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <div class="form-group text-center">
        <input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
    </div>
</div>