<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="csrf-token" content="Y5OVOmDep8d96uDKyDf7EsLvELGUUrPLSimrCRo8" />
</head>
<body >
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <span >立法项目</span>
        </li>
    </ul>
    <button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<form id="legislationPlanForm" class="form-horizontal"  novalidate="novalidate">
        <div class="form-body">
            <div class="form-group">
					<ul id="account_discount_div1_tab_ul1" class="nav nav-tabs">
						 
						<li class="active" id="account_discount_div1_tab_li1">     
						<a href="#sampleTable" data-toggle="tab" class="account_discount_div1_tab_1">&nbsp;&nbsp;&nbsp;议题汇总</a>							 
						</li>  
						<li>  
						<a href="#sampleTable2" data-toggle="tab" class="account_discount_div1_tab_2">&nbsp;&nbsp;&nbsp;会议通知反馈</a>						 
						</li>  
						<li>  
						<a href="#sampleTable3" data-toggle="tab" class="account_discount_div1_tab_2">&nbsp;&nbsp;&nbsp;意见整理</a>							 
						</li>
						<li>  
						<a href="#sampleTable4" data-toggle="tab" class="account_discount_div1_tab_2">&nbsp;&nbsp;&nbsp;采纳情况</a>							 
						</li>
					</ul>
				<div id="myTabContent" class="tab-content">	
				<div class="tab-pane fade in active" id="sampleTable">
               </div>
               <div class="tab-pane fade" id="sampleTable2">
               </div>
               <div class="tab-pane fade" id="sampleTable3">
               </div>
               <div class="tab-pane fade" id="sampleTable4">
               </div>
                </div>
            </div>
            
            <div class="form-group text-center">
					<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
			</div>
		</div>
	</form>

</div>
<script type="text/javascript">


function queryCheckTable() {
	$.post("../legislationPlan/draft_plan_info.do?stMeetingId=${request.stMeetingId}&method=openPlanCheckMeeting_ajax", function(data) {
		if (data.success) {	
			$.each(data.nodeInfoArray, function(index, item) {
				if(item.nodeStatus=="TODO"){
					 $.post("${basePath}/legislationCheckmeeting/checkmeeting_info.do?stNodeId=NOD_0000000170&method=checkmeeting_add&stMeetingId=${request.stMeetingId}",function(data){
				            $('#sampleTable').html(data);
				            $("#btnClose").hide();
				            $("[name='fileBtn']").hide();
				              });
				}if(item.nodeStatus=="FEEDBACK"){
					 $.post("${basePath}/legislationCheckmeeting/checkmeeting_info.do?stNodeId=NOD_0000000170&method=checkmeeting_feedback&stMeetingId=${request.stMeetingId}",function(data){
				            $('#sampleTable2').html(data);
				            $("#btnCloseFeedBack").hide();
				            $("[name='fileBtn']").hide();
				              });
				}if(item.nodeStatus=="INPUT"){
					
					 $.post("${basePath}/legislationCheckmeeting/checkmeeting_info.do?stNodeId=NOD_0000000170&method=checkmeeting_input&stMeetingId=${request.stMeetingId}",function(data){				
						 $('#sampleTable3').html(data);
						 $("#btnCloseInput").hide();
						 $("[name='fileBtn']").hide();
				              });
				}if(item.nodeStatus=="AFFIRM"){
					
					 $.post("${basePath}/legislationCheckmeeting/checkmeeting_info.do?stNodeId=NOD_0000000170&method=checkmeeting_affirm&stMeetingId=${request.stMeetingId}",function(data){
						 $('#sampleTable4').html(data);
						 $("#btnCloseAffirm").hide();
						 $("#btnSubmit").hide();
						 $("[name='fileBtn']").hide();
				              });
				}
			});
		}
	});
}

</script>
</body>
</html>