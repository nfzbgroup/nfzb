<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span >规章草案列表 > </span>
		</li>
		<li>
			<span>查看</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<div class="row" style="margin-bottom: 20px;">
		<div class="col-md-12">
			<label class="btn btn-w-m btn-success"  onclick="changeInfoTab(this,'openInfoTabPage','${nodeId}','${stDocId}')">草案信息列表</label>
			<label class="btn btn-w-m btn-default"  onclick="changeInfoTab(this,'openSeparateTabPage','${nodeId}','${stDocId}')">分办单</label>
			<label class="btn btn-w-m btn-default"  onclick="changeInfoTab(this,'openOnlineTabPage','${nodeId}','${stDocId}')">网上征求意见</label>
			<label class="btn btn-w-m btn-default"  onclick="changeInfoTab(this,'openUnitTabPage','${nodeId}','${stDocId}')">单位意见</label>
			<label class="btn btn-w-m btn-default"  onclick="changeInfoTab(this,'openAuditMeetingTabPage','${nodeId}','${stDocId}')">审核会议</label>
		</div>
	</div>
	<div id="legislationProcessInfoTab">
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
						<label class="control-label col-md-12 text-center"><a target="_blank" href="${basePath}/file/downloadAttach.do?name=${doc.stTitle}&url=${doc.stFileUrl}">${doc.stTitle}</a></label>
					</s:iterator>
				</td>
			</tr>
		</table>
		<div class="form-group text-center">
			<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
		</div>
	</div>
</div>
<script>
    $(function () {
        $(".tab-left").css('width', $(window).width() * 0.2)
    });
	function changeInfoTab(obj,method,stNodeId,stDocId){
	    var $obj=$(obj);
        $obj.parent().children().attr("class","btn btn-w-m btn-default");
        $obj.attr("class","btn btn-w-m btn-success");
        $.post("${basePath}/legislationProcessDoc/draft_doc_info.do?stNodeId="+stNodeId+"&method="+method+"&stDocId="+stDocId,function(data){
            $('#legislationProcessInfoTab').html(data);
        });
    }
</script>