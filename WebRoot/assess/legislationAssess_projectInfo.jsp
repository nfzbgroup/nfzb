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
            <span ><c:choose><c:when test="${nodeId=='NOD_0000000255'}">意见</c:when><c:when test="${nodeId=='NOD_0000000262'||nodeId=='NOD_0000000263'}">评估报告</c:when><c:otherwise>项目</c:otherwise></c:choose>列表</span>
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
                <c:if test="${nodeId=='NOD_0000000255'||nodeId=='NOD_0000000262'||nodeId=='NOD_0000000263'}">
                    <th class="text-center">审核意见</th>
                </c:if>
                <c:if test="${nodeId=='NOD_0000000262'||nodeId=='NOD_0000000263'}">
                    <th class="text-center">评估报告内容</th>
                </c:if>
                <th class="text-center">发起人</th>
                <th class="text-center">发起时间</th>
                <c:if test="${nodeId=='NOD_0000000262'||nodeId=='NOD_0000000263'}">
                    <th class="text-center">操作</th>
                </c:if>
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
							 <p><span style="color:green;"><c:out value="${assessItem.stStatus}"/></span><p>
						 </c:if>
					 	 <c:if test="${not flag}">
						 	  <p><span style="color:red;"><c:out value="${assessItem.stStatus}"/></span><p>
						 </c:if>
                    </td>
                    <c:if test="${nodeId=='NOD_0000000255'||nodeId=='NOD_0000000262'||nodeId=='NOD_0000000263'}">
                        <td>${assessItem.stActive}</td>
                    </c:if>
                    <c:if test="${nodeId=='NOD_0000000262'||nodeId=='NOD_0000000263'}">
                        <td>
                            <c:if test="${assessItem.stComment!=null&&assessItem.stComment!=''}">
                                ${assessItem.stComment}
                            </c:if>
                            <c:if test="${assessItem.stComment==null||assessItem.stComment==''}">
                                <span style="color: red">还未提交评估报告</span>
                            </c:if>
                        </td>
                    </c:if>
                    <td>${assessItem.stUserName}</td>
                    <td><fmt:formatDate type="date" value="${assessItem.dtCreateDate}" /></td>
                    <c:if test="${(nodeId=='NOD_0000000262'||nodeId=='NOD_0000000263')&&assessItem.stComment!=null&&assessItem.stComment!=''}">
                        <td><a href="javaScript:void(0)" data-title="评估报告详情" onclick="openAssessItemChildPage('openAssessItemReportPage','${assessItem.stTaskId}')" class="layer_full_link">评估报告详情</a></td>
                    </c:if>
                </tr>
            </c:forEach>
        </c:if>
        </tbody>
    </table>
    <div class="form-group text-center">
        <input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
    </div>
</div>