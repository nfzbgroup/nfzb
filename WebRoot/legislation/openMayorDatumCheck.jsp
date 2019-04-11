<!DOCTYPE html>
<html>
<head>
<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
.access {
	padding: 10px;
	background-color: #fff;
	border: 1px solid #e5e6e7;
}

.form_control {
	min-height: 113px;
}

.leader span {
	display: inline-block;
	height: 25px;
	line-height: 28px;
	margin-top: 5px;
	border-bottom: 1px solid #666;
}

.access {
	padding: 10px;
	background-color: #fff;
	border: 1px solid #e5e6e7;
}

.form_control {
	min-height: 113px;
}

.leader span {
	display: inline-block;
	height: 25px;
	line-height: 28px;
	margin-top: 5px;
	border-bottom: 1px solid #666;
}

.title h2 {
	color: red;
	text-align: center;
	font-family: "新宋体";
	font-weight: bolder;
}
</style>
</head>
<body class="gray-bg">
	<div class="page-bar">
		<ul class="page-breadcrumb">
			<li>
				<span>报市长审签 > </span>
			</li>
			<li>
				<span>报市长审签</span>
			</li>
		</ul>
		<!-- <button style="padding-right: 5px" type="button" class="close" data-dismiss="modal">
			<span aria-hidden="true">&times;</span>
			<span class="sr-only">Close</span>
		</button> -->
	</div>
	<div class="modal-body">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="row">
						<form name="legislationProcessDocForm" id="legislationProcessDocForm" class="form-horizontal" novalidate="novalidate">
							<input type="hidden" name="stDocId" id="stDocId" value="${stDocId}">
							<input type="hidden" name="stNodeId" id="stNodeId" value="${stNodeId}">
							<div class="form-body">
								<div class="title">
									<h2>办公厅工作签报</h2>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label text-left">流转范围：</label>
									<div class="col-sm-6">
										<input type="text" id="stExchangeRange" name="stExchangeRange" style="width: 490px;" class="form-control　form_control" value="${legislationProcessTaskdetail.stBak1}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label text-left">标题：</label>
									<div class="col-sm-6">
										<input type="text" id="stTitle" name="stTitle" style="width: 490px;" class="form-control　form_control" value="${legislationProcessTaskdetail.stTitle}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label text-left">文号：</label>
									<div class="col-sm-2">
										<input type="text" id="stDocumentNo" name="stDocumentNo" class="form-control　form_control" value="${legislationProcessTaskdetail.stBak2}">
									</div>
									<label class="col-sm-2 control-label text-left">报送日期：</label>
									<div class="col-sm-2">
										<input type="text" id="stSendTime" name="stSendTime" class="form-control　form_control" value="${legislationProcessTaskdetail.dtBak1}">
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label text-left">正文：</label>
									<div class="col-sm-6">
										<textarea id="stContent" name="stContent" class="form-control form_control">${legislationProcessTaskdetail.stContent}</textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label text-left">办理情况：</label>
									<div class="col-sm-6">
										<textarea id="stSituation" name="stSituation" class="form-control form_control">${legislationProcessTaskdetail.stBak6}</textarea>
									</div>
								</div>
								<div class="form-group text-center">
									<input type="button" class="btn btn-w-m btn-success" id="btnSave" name="btnSave" value="保存">
									<input type="button" class="btn btn-w-m btn-success" id="save" name="save" onclick="layer_close()" value="报签编辑">
									<input type="button" class="btn btn-w-m btn-success" id="save" name="save" onclick="layer_close()" value="流转设置">
									<input type="button" class="btn btn-w-m btn-success" id="save" name="save" onclick="layer_close()" value="发送">
									<input ${strDisplay} type="button" class="btn btn-w-m btn-success" id="btnNext" name="btnNext" value="提交">
									<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="返回">
								</div>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
<script>
	$(function() {
		$(".tab-left").css('width', $(window).width() * 0.2)

		$('#btnSave').click(function() {
			var param = $('#legislationProcessDocForm').formToJson();
			$.post("../legislationProcessDoc/saveMayorDatumCheck.do", param, function(data) {
				if (data.success) {
					Duang.success("提示", "操作成功。");
				} else {
					Duang.error("提示", "保存失败！");
				}
			});
		});

		$('#btnNext').click(function() {
			layer.confirm('请确认操作！', function(index) {
				layer.close(layer.index);
				var param = $('#legislationProcessDocForm').formToJson();
				$.post("${requestUrl}?stNodeId=${nodeId}&method=nextMayorDatumCheck", param, function(data) {
					$('#legislationProcessForm').modal('hide');
					if (data.success) {
						Duang.success("提示", "操作成功。");
					} else {
						Duang.error("提示", "保存失败！");
					}
					submitForm(1);
				});
			});
		});
	});

	
</script>