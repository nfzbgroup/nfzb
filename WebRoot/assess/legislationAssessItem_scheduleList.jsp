<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <span >评估进度 > </span>
        </li>
        <li>
            <span >查看</span>
        </li>
    </ul>
    <button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
    <table class="table table-border table-bordered table-bg table-hover">
        <thead>
            <tr class="text-center">
                <th class="text-center">季度</th>
                <th class="text-center">评估进度</th>
                <th class="text-center">操作</th>
            </tr>
        </thead>
        <tbody>
        <c:if test="${legislationAssessTaskList !=null&&fn:length(legislationAssessTaskList)>0}">
            <c:forEach var="task" items="${legislationAssessTaskList}">
                <tr class="text-center">
                    <td>第${task.stActive}季度</td>
                    <td>${task.stComment1}</td>
                    <td>
                        <a href="javaScript:void(0)" data-title="详情" onclick="openAssessItemChildPage('openAssessItemSchedulePage','${task.stTaskId}')" class="layer_full_link">详情</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <div class="form-group text-center">
        <input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
    </div>
</div>