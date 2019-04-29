<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span ><c:if test="${nodeId=='NOD_0000000122'}">部门意见征求</c:if><c:if test="${nodeId=='NOD_0000000161'}">部门会签</c:if>  > </span>
		</li>
		<li>
			<span >接收反馈</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
<h2 style="color: #E4243D;text-align: center;font-weight: bold;margin-bottom: 20px">意见反馈</h2>
        <div class="form-group text-center" >
            <label class="col-sm-2 control-label text-left">对应草案：</label>
            <label class="col-sm-3 control-label text-left">${legislationProcessDoc.stDocName}</label>
        </div>
	<table class="table table-border table-bordered">
		<thead>
			<tr>
				<th style="text-align: center">
					部门名称
				</th>
				<th style="text-align: center">
					是否反馈
				</th>
				<th style="text-align: center">
					反馈时间
				</th>
				<c:if test="${allDone==false}" >
				 <th id="conHead" style="text-align: center">
					确认
				 </th>
				</c:if>
			</tr>
		</thead>
		<tbody>
		<c:if test="${legislationProcessTaskList !=null&&fn:length(legislationProcessTaskList)>0}">
			<c:forEach var="legislationProcessTask" items="${legislationProcessTaskList}" >
			   <tr>
					<td style="text-align: center">
						${legislationProcessTask.stComment1}
					</td> 
					<td style="text-align: center">
						<c:if test="${legislationProcessTask.stTaskStatus=='TODO'}" >
						未反馈</c:if>
						<c:if test="${legislationProcessTask.stTaskStatus=='DONE'}" >
						已反馈</c:if>
					</td> 
					<td style="text-align: center">
						<c:if test="${legislationProcessTask.stTaskStatus=='TODO'}" >
						---</c:if>
						<c:if test="${legislationProcessTask.stTaskStatus=='DONE'}" >
						<fmt:formatDate type="time" pattern="yyyy-MM-dd HH:mm:ss"
            value="${legislationProcessTask.dtDealDate}" /></c:if>
					<c:if test="${allDone==false}" >
					  <td style="text-align: center">
						<input id="${legislationProcessTask.stTaskId}"
						<c:if test="${legislationProcessTask.stTaskStatus=='DONE'}">disabled</c:if>
						 name="confirm" type="button" onclick="confirm(this)" value="确认" />
					  </td>
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
<script>
    $(function () {
        $(".tab-left").css('width', $(window).width() * 0.1);
    });
	
    function confirm(e) {
             $.post("../${requestUrl}?stNodeId=${nodeId}&stDocId=${legislationProcessDoc.stDocId}&stTaskId="+e.id+"&method=confirm",
            	function(data){
	            	 if(data.success){
	            		 Duang.success("提示","确认成功");
	            		 $(e).attr("disabled","disabled");
	                 	var dealDate =JSON.stringify(data.dealDate);
	                 	dealDate = dealDate.replace("\"","").replace("\"","");
	                 	 $(e).parent().prev().text(dealDate);
	                 	 $(e).parent().prev().prev().text("已反馈");
	                 	if(data.allDone){
	                 		$('#${nodeId}').parent().removeClass('bcg_gray').removeClass('bcg_blue').removeClass('bcg_green').addClass('bcg_blue');
	                 		$('[name="confirm"][id="conHead"]').attr("hidden","hidden");
	                 	}
	                 }else{
	                     Duang.error("提示","操作失败");
	                 }
             	});
    };
</script>
