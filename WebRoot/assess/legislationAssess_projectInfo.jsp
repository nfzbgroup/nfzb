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
            <span ><c:choose><c:when test="${nodeId=='NOD_0000000255'||nodeId=='NOD_0000000262'}">意见</c:when><c:otherwise>项目</c:otherwise></c:choose>详情</span>
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
                <c:if test="${nodeId=='NOD_0000000255'||nodeId=='NOD_0000000262'}">
                    <th class="text-center">审核意见</th>
                </c:if>
                <th class="text-center">发起人</th>
                <th class="text-center">发起时间</th>
            </tr>
        </thead>
        <tbody>
        <c:if test="${legislationAssessItemList !=null&&fn:length(legislationAssessItemList)>0}">
            <c:forEach var="assessItem" items="${legislationAssessItemList}">
                <tr class="text-center">
                    <td>${assessItem.stItemName}</td>
                    <td>
                    	 <c:set var="theString" value='${assessItem.stStatus}' />
	                   	 <c:if test="${fn:contains(theString,'已处理')}" var="flag" scope="session">
							 <p><c:out value="${assessItem.stStatus}"/><p>
						 </c:if>
					 	 <c:if test="${not flag}">
						 	  <p> <span style="color:red;"><c:out value="${assessItem.stStatus}" /></span>	 <p>
						 </c:if>
                    </td>
                    <c:if test="${nodeId=='NOD_0000000255'||nodeId=='NOD_0000000262'}">
                        <td>${assessItem.stActive}</td>
                    </c:if>
                    <td>${assessItem.stUserName}</td>
                    <td><fmt:formatDate type="date" value="${assessItem.dtCreateDate}" /></td>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <div class="form-group text-center">
        <input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
    </div>
</div>