<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="modal-body">
	<h2 style="color: #E4243D;text-align: center;font-weight: bold;margin-bottom: 20px">上海市人民政府法制办公室文件分办单</h2>
	<form id="user_form" class="form-horizontal"
		  novalidate="novalidate">
		<div class="form-body">
			<div class="form-group">
				<label class="control-label pull-right">EF2017规00001
				</label>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">来文单位：
				</label>
				<div class="col-md-4">
					<input class="control-label underline" style="text-align:left" readonly value="<c:if test="${request.legislationProcessDoc.stUnitName!=null}">${request.legislationProcessDoc.stUnitName}</c:if>">
				</div>
				<label class="control-label col-md-5">收文日期：<span id="receiveYear" class="underline">${request.legislationProcessTask.dtOpenDate}</span>
				</label>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">草案名称：
				</label>
				<div class="col-md-4">
					<input class="control-label underline" style="text-align:left" readonly value="<c:if test="${request.legislationProcessDoc.stDocName!=null}">${request.legislationProcessDoc.stDocName}</c:if>">
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-md-3">分办：
				</label>
				<div class="col-md-6">
					<textarea  class="form-control" cols="30" rows="4" id="distComment1" name="distComment1" value="<c:if test="${request.legislationProcessTask.stComment1!=null}">${request.legislationProcessTask.stComment1}</c:if>"></textarea>
				</div>
			</div>
			<div style="width: 100%;height: 2px;background-color: #E4243D;margin-bottom: 20px"></div>
			<%--<div class="form-group">--%>
				<%--<label class="control-label pull-right">办理时限：--%>
					<%--<input style="width: 40px" type="text" name="year" class="underline">年<input style="width: 40px" type="text" name="month" class="underline">月<input style="width: 40px" type="text" name="day" class="underline">日--%>
				<%--</label>--%>
			<%--</div>--%>
			<div class="form-group">
				<label class="control-label col-md-2">选择办理处：
				</label>
				<div class="col-md-5">
					<s:select id="distDealName" name="distDealName" list="#request.teamList" 
					 value="unitName" listKey="id" listValue="unitName" headerKey="" headerValue="=请选择="/>
				</div>
				<div class="col-md-5">
					<label class="control-label pull-right">办理时限：
						<input style="width: 40px" type="text" id="year" name="year" class="underline">年<input style="width: 40px" type="text" id="month" name="month" class="underline">月<input style="width: 40px" type="text" id="day" name="day" class="underline">日
					</label>
				</div>
			</div>
		</div>
	</form>
	<div class="form-group text-center">
		<input type="button" class="btn btn-w-m btn-success"  value="确认分办" onclick="distSubmit('submit')">
		<input type="button" class="btn btn-w-m btn-success"  value="保存分办单" onclick="distSubmit('save')">
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
</div>

<script>
$(function () {

        $("#distDealName option[value='${request.legislationProcessTask.stDealId}']").attr("selected","selected");

	
	document.getElementById('distComment1').value="${request.legislationProcessTask.stComment1}";
	var date="${request.legislationProcessTask.dtDeadDate}";
	if(date!=null&&date!=""){
		var dateymd=(date.split(" "))[0];
		document.getElementById('year').value=(dateymd.split("-"))[0];
		document.getElementById('month').value=(dateymd.split("-"))[1];
		document.getElementById('day').value=(dateymd.split("-"))[2];
	}
});
	function distSubmit(action){
		var param=$('#user_form').formToJson();
		var distComment = $('#distComment1').val();
		var year= $('#year').val();
		var month=$('#month').val();
		var day=$('#day').val();
		var transactDate=year+"年"+month+"月"+day+"日";
		var options=$("#distDealName option:selected");  //获取选中的项

		$.post("${basePath}/legislationProcessDoc/draft_dist_info.do?stDocId=${request.legislationProcessDoc.stDocId}&stComment1="+distComment+"&transactDate="+transactDate+"&action="+action+"&stDealId="+options.val()+"&stDealName="+options.text(),param,function(data){
            $('#legislationProcessForm').modal('hide');
            submitForm(1);
        });
	}
</script>