<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table class="table table-border table-bordered">
	<tr>
		<td class="text-right tab-left">
			<label style="white-space: nowrap">法规规章草案:</label>
		</td>
		<td class="text-center">
			<label>${legislationProcessDoc.stDocName}</label>
		</td>
	</tr>
	<tr>
		<td class="text-right tab-left">
			<label style="white-space: nowrap">备注:</label>
		</td>
		<td class="text-center">
			<label >${legislationProcessDoc.stComent}</label>
		</td>
	</tr>
	<tr>
		<td class="text-right tab-left">
			<label style="white-space: nowrap">相关材料:</label>
		</td>
		<td class="text-center">
			<s:iterator value="#request.docList" var="doc">
				<label class="control-label col-md-12 text-center"><a target="_blank" href="${basePath}/file/downloadAttach.do?name=${doc.stTitle}&url=${doc.stFileId}">${doc.stTitle}</a></label>
			</s:iterator>
		</td>
	</tr>
</table>
<div class="form-group text-center">
	<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
</div>
<script>
    $(function () {
        $(".tab-left").css('width', $(window).width() * 0.2)
    })
</script>