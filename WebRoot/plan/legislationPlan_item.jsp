<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<table class="table table-border table-bordered table-bg table-hover" id="showtable" data-toggle="table" data-mobile-responsive="true" data-card-view="true" data-pagination="true">
	<thead>
		<tr class="text-center">
			<c:choose>
				<c:when test="${nodeId=='NOD_0000000202'||nodeId=='NOD_0000000203'||nodeId=='NOD_0000000204'||nodeId=='NOD_0000000205'||nodeId=='NOD_0000000207'}">
					<th class="text-center" data-field="district_name">立法项目发起名称</th>
					<th class="text-center" data-field="district_name">处理环节</th>
					<th class="text-center" data-field="created_at">发起人</th>
					<th class="text-center" data-field="district_name">发起时间</th>
					<c:if test="${buttonStatus=='DONE'}">
						<th class="text-center" data-field="district_name">处理时间</th>
					</c:if>
					<th class="text-center" data-field="set">操作</th>
				</c:when>
			</c:choose>
		</tr>
	</thead>
	<tbody>
		<c:choose>
			<c:when test="${nodeId=='NOD_0000000202'||nodeId=='NOD_0000000203'||nodeId=='NOD_0000000204'||nodeId=='NOD_0000000205'||nodeId=='NOD_0000000207'}">
				<c:choose>
					<c:when test="${retPage.totalSize > 0}">
						<c:forEach items="${retPage.result}" var="plan">
							<tr class="text-center">
								<td>${plan.stItemName}</td>
								<td>${plan.stNodeName}</td>
								<td>${plan.stUserName}</td>
								<td>
									<fmt:formatDate type="date" value="${plan.dtCreateDate}" />
								</td>
								<td>
									<fmt:formatDate type="date" value="${plan.dtCreateDate}" />
								</td>
								<td>
									<a href="javaScript:void(0)" data-title="查看" onclick="openPlanPage('openPlanItemInfoPage','${plan.stItemId}')" class="layer_full_link">查看</a>
								</td>
								
							</tr>
						</c:forEach>
					</c:when>
				</c:choose>
			</c:when>
		</c:choose>
	</tbody>
</table>
     <div class="form-group text-center">
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
	<div class="modal inmodal fade" id="legislationProcessForm1" data-backdrop keyboard tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
					</div>
				</div>
	</div>
<div class="clearfix">
	<div class="list-page" id="listPage"></div>
</div>
	<script src="${basePath}/legislation/assets/js/jquery.min.js?v=2.1.4"></script>
	<script src="${basePath}/legislation/assets/js/bootstrap.min.js?v=3.3.5"></script>
	<script src="${basePath}/legislation/assets/js/plugins/toastr/toastr.min.js"></script>
	<script src="${basePath}/legislation/assets/plugins/laydate/laydate.js"></script>
	<script src="${basePath}/legislation/assets/js/content.min.js?v=1.0.0"></script>
	<link href="${basePath}/legislation/assets/page/common.css" rel="stylesheet" type="text/css" />
	<script src="${basePath}/legislation/assets/page/page.js" type="text/javascript"></script>
	<script src="${basePath}/legislation/assets/page/common.js" type="text/javascript"></script>
	<script src="${basePath}/legislation/assets/util/util.js" type="text/javascript"></script>
	<script src="${basePath}/legislation/assets/js/plugins/layer/layer.min.js?v=2.0"></script>
	<script src="${basePath}/legislation/assets/util/ajaxfileupload.js" type="text/javascript"></script>
	<link href="${basePath}/legislation/assets/css/plugins/layout/css/layout.min.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript">
	
	if(${retPage.totalSize > 0}){
		fullPageList(${retPage.totalSize},${pageNo},${pageSize},'listPage');
	}
 /*    window.onload=function(){
    	 $('#legislationProcessForm1').on('show.bs.modal', function () {
             $('#legislationProcessForm .modal-body').css('overflow', 'auto');
             $('#legislationProcessForm .modal-body').css('height', $(window).height());
 			$('#legislationProcessForm .modal-dialog').css('width', $(window).width()*0.96);
         });
    } */
    function openPlanPage(method,stItemId) {
        $("#legislationProcessForm1").modal({
            remote: "${basePath}/legislationPlan/draft_plan_info.do?stNodeId=${nodeId}&method="+method+"&stItemId="+stItemId
        });
    };
    
   
</script>