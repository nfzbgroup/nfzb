<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link href="${basePath}/legislation/assets/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/animate.min.css" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/style.min.css?v=4.0.0" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<link href="${basePath}/legislation/assets/plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/myUtil.css" rel="stylesheet">
</head>

<body class="gray-bg">
<div class="wrapper animated fadeInRight" id="processIndex">
	<div class="row">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<div class="row">
						<div class="col-md-12">
							<form action="${basePath}/${requestUrl}" id="legislationProcessTask" method="post" role="form" class="form-horizontal form-bordered">
								<input type="hidden" id="taskStatus" value="TODO">
								<input type="hidden" id="stNodeId" value="${nodeId}">
								<input type="hidden" id="requestUrl" value="${requestUrl}">
								<c:choose>
									<c:when test="${nodeId=='NOD_0000000201'||nodeId=='NOD_0000000208'||nodeId=='NOD_0000000209'||nodeId=='NOD_0000000211'||nodeId=='NOD_0000000215'}">
										<div class="col-md-5">
											<div class="form-group">
												<label class="col-md-3 control-label">发起时间:</label>
												<div class="col-md-9">
													<div class="input-group input-large">
														<input type="text" class="form-control" readonly id="startTime" name="startTime">
														<span class="input-group-addon"> - </span>
														<input type="text" class="form-control" readonly id="endTime" name="endTime">
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label class="col-md-5 control-label">通知名称:</label>
												<div class="col-md-7">
													<input type="text"  class="form-control" id="stPlanName">
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="col-md-5 control-label">发起人:</label>
												<div class="col-md-7">
													<input type="text"  class="form-control" id="stUserName">
												</div>
											</div>
										</div>
										<div class="col-md-12">
										</div>
										<div class="col-md-5">
										</div>
									</c:when>
									<c:when test="${nodeId=='NOD_0000000202'||nodeId=='NOD_0000000203'||nodeId=='NOD_0000000204'||nodeId=='NOD_0000000205'||nodeId=='NOD_0000000207'}">
										<div class="col-md-5">
											<div class="form-group">
												<label class="col-md-3 control-label">发起时间:</label>
												<div class="col-md-9">
													<div class="input-group input-large">
														<input type="text" class="form-control" readonly id="startTime" name="startTime">
														<span class="input-group-addon"> - </span>
														<input type="text" class="form-control" readonly id="endTime" name="endTime">
													</div>
												</div>
											</div>
										</div>
										<div class="col-md-3">
											<div class="form-group">
												<label class="col-md-5 control-label">项目名称:</label>
												<div class="col-md-7">
													<input type="text"  class="form-control" id="stPlanName">
												</div>
											</div>
										</div>
										<div class="col-md-4">
											<div class="form-group">
												<label class="col-md-5 control-label">发起人:</label>
												<div class="col-md-7">
													<input type="text"  class="form-control" id="stUserName">
												</div>
											</div>
										</div>
										<div class="col-md-12">
										</div>
										<div class="col-md-5">
										</div>
									</c:when>
								</c:choose>

								<div class="col-md-4">
									<label class="btn btn-w-m btn-success" onclick="submitForm(1)"> 查询</label>
								</div>
								<div class="col-md-8 padding0 order-btn">
										<s:iterator value="#request.stTodoNameList" var="buttonTask">
											<label class="${buttonTask.buttonClass}" id=${buttonTask.buttonId} onclick="changeType('${buttonTask.buttonId}')">${buttonTask.buttonName}</label>
										</s:iterator>
								</div>

								<c:choose>
									<c:when test="${nodeId=='NOD_0000000201'}">
										<div class="pull-right">
											<label class="btn btn-w-m btn-success" id="addNotice" onclick="openPlanPage('openNoticeAddPage',null)">征集通知发起</label>
										</div>
									</c:when>
									<c:when test="${nodeId=='NOD_0000000202'||nodeId=='NOD_0000000207'}">
										<div class="pull-right">
											<label class="btn btn-w-m btn-success" id="addPlan" onclick="openPlanPage('openPlanAddPage',null)">项目发起</label>
										</div>
									</c:when>
								</c:choose>

							</form>
						</div>
				</div>
			</div>
			<div class="ibox-content">
				<div class="row" id="legislationProcessTaskTable">

				</div>
				</div>
			</div>
			<div class="modal inmodal fade" id="legislationProcessForm" data-backdrop keyboard tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
					</div>
				</div>
			</div>
			<div class="modal inmodal fade" id="legislationProcessChildForm" data-backdrop keyboard tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
					</div>
				</div>
			</div>
		</div>
	</div>
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
	$(function () {
        $.ajaxSetup({
            async : false
        });
        var startDate = laydate.render({
            elem: '#startTime',
            format:'yyyy-MM-dd',
            calendar: true,
            done:function(value,date){
                if( value !== '' ){
                    endDate.config.min.year = date.year;
                    endDate.config.min.month = date.month - 1;
                    endDate.config.min.date = date.date;
                }else{
                    endDate.config.min.year = 1800;
                    endDate.config.min.month = 1;
                    endDate.config.min.date = 1;
                }
            }
        });
        var endDate = laydate.render({
            elem: '#endTime',
            format:'yyyy-MM-dd',
            calendar: true,
            done:function(value,date){
                if( value !== '' ){
                    startDate.config.max.year = date.year;
                    startDate.config.max.month = date.month - 1;
                    startDate.config.max.date = date.date;
                }else{
                    startDate.config.max.year = 2099;
                    startDate.config.max.month = 1;
                    startDate.config.max.date = 1;
                }
            }
        });
        $.post("${requestUrl}?stNodeId=${nodeId}&method=queryTable",function(data){
            $('#legislationProcessTaskTable').html(data);
        });
        $('body').on('hidden.bs.modal', '.modal', function () {
            $(this).removeData('bs.modal');
        });
        $('#legislationProcessForm').on('show.bs.modal', function () {
            $('#legislationProcessForm .modal-body').css('overflow', 'auto');
            $('#legislationProcessForm .modal-body').css('height', $(window).height());
			$('#legislationProcessForm .modal-dialog').css('width', $(window).width()*0.96);
        });
        $('#legislationProcessChildForm').on('show.bs.modal', function () {
            $('#legislationProcessForm .modal-body').css('overflow', 'auto');
            $('#legislationProcessForm .modal-body').css('height', $(window).height());
            $('#legislationProcessForm .modal-dialog').css('width', $(window).width()*0.9);
        });
	});
    function openPlanPage(method,stTaskId) {
        $("#legislationProcessForm").modal({
            remote: "${basePath}/legislationPlan/draft_plan_info.do?stNodeId=${nodeId}&method="+method+"&stTaskId="+stTaskId
        });
    };
	</script>
</body>

</html>