<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<form id="user_form" class="form-horizontal"
	  novalidate="novalidate">
	<div class="form-body">
	<h2 style="color: #E4243D;text-align: center;font-weight: bold;margin-bottom: 20px">分办单</h2>
		<div class="form-group">
			<label class="control-label col-md-3">来文单位：
			</label>
			<div class="col-md-4">
				<input class="control-label underline" style="text-align:left;width:100%" readonly value="<c:if test="${legislationProcessDoc.stUnitName!=null}">${legislationProcessDoc.stUnitName}</c:if>">
			</div>
			<label class="control-label col-md-5">收文日期：
				<c:choose>
					<c:when test="${legislationProcessTask.dtOpenDate !=null}">
						<fmt:formatDate value="${legislationProcessTask.dtOpenDate}" dateStyle="long"/>
					</c:when>
					<c:otherwise>
						&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日
					</c:otherwise>
				</c:choose>
			</label>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">草案名称：
			</label>
			<div class="col-md-4">
				<input class="control-label underline" style="text-align:left;width:100%" readonly value="<c:if test="${legislationProcessDoc.stDocName!=null}">${legislationProcessDoc.stDocName}</c:if>">
			</div>
		</div>
		<div class="form-group">
			<label class="control-label col-md-3">分办：
			</label>
			<div class="col-md-6">
				<textarea  class="form-control" cols="30" rows="4" readonly><c:if test="${legislationProcessTask.stComment1!=null}">${legislationProcessTask.stComment1}</c:if>
				</textarea>
			</div>
		</div>
		<!-- <div style="width: 100%;height: 2px;background-color: #E4243D;margin-bottom: 20px"></div> -->
		<div class="form-group">
			<label class="control-label col-md-3">经办处室：
			</label>
			<div class="col-md-4">
			<label class="control-label "><c:if test="${legislationProcessTask.stDealName!=null}">${legislationProcessTask.stDealName}</c:if>
			</label>
			</div>
			<!-- <div class="col-md-5">
				<label class="control-label pull-right">办理时限：
					<c:choose>
						<c:when test="${legislationProcessTask.dtDeadDate !=null}">
							<fmt:formatDate value="${legislationProcessTask.dtDeadDate}" dateStyle="long"/>
						</c:when>
						<c:otherwise>
							&nbsp;&nbsp;年&nbsp;&nbsp;月&nbsp;&nbsp;日
						</c:otherwise>
					</c:choose>
				</label>
			</div> -->
		</div>
	</div>
</form>
<div class="form-group text-center">
	<input type="button" class="btn btn-w-m btn-success"  onclick = "exportWordTest()" value="导出分办单" >
	<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
</div>
<script>
    $(function () {
        $(".tab-left").css('width', $(window).width() * 0.2)
    })
    
    
    function exportWordTest(){
    	user_form.action = '${basePath}/legislate/exportWord.do';
    	user_form.submit();
}
</script>