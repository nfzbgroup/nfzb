<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span >草案办理 </span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<form id="legislationProcessDocForm" class="form-horizontal"
		  novalidate="novalidate">
		<input hidden name="docId" value="${stDocId}">
		<div class="form-body">
			<div class="form-group">
				<label class="col-sm-3 control-label text-left">专家论证会：</label>
				<div class="col-sm-9 demonstration" >
					<textarea class="form-control" readonly onclick="openDemonstrationPage('openExpertDemonstrationPage','${stDocId}')" style="text-align: center;color: red;font-weight: bold">点击添加</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label text-left">网上意见：</label>
				<div class="col-sm-9 demonstration" >
					<textarea class="form-control" readonly style="text-align: center;color: red;font-weight: bold">点击添加</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label text-left">立法听证会：</label>
				<div class="col-sm-9 demonstration" >
					<textarea class="form-control" readonly onclick="openDemonstrationPage('openLegislationDemonstrationPage','${stDocId}')" style="text-align: center;color: red;font-weight: bold">点击添加</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label text-left">单位意见征求：</label>
				<div class="col-sm-9 demonstration" >
					<textarea class="form-control" readonly onclick="openDemonstrationPage('openUnitDemonstrationPage','${stDocId}')" style="text-align: center;color: red;font-weight: bold">点击添加</textarea>
				</div>
			</div>
		</div>
	</form>
	<div class="form-group text-center">
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
</div>
<script>
</script>