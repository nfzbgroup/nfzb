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
</style>
</head>
<body class="gray-bg">
	<div class="page-bar">
		<ul class="page-breadcrumb">
			<li>
				<span>报市长审签材料 > </span>
			</li>
			<li>
				<span>报市长审签材料</span>
			</li>
		</ul>
		<!--<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal">
			<span aria-hidden="true">&times;</span>
			<span class="sr-only">Close</span>
		</button>-->
	</div>
	<div class="modal-body">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="row">
						<form name="form1" id="legislationProcessDocForm" class="form-horizontal" novalidate="novalidate">
							<input type="hidden" name="stDocId" id="stDocId" value="${stDocId}">
							<input type="hidden" name="stNodeId" id="stNodeId" value="${stNodeId}">
							<div class="form-body">
								<div class="form-group">
									<label class="col-sm-3 control-label text-left">说明：</label>
									<div class="col-sm-6">
										<textarea id="stComent" name="stComent" class="form-control form_control">${legislationProcessTask.stComment1}</textarea>
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-3 control-label text-left">附件：</label>
									<div class="col-sm-6">
										<div class="access">
											<p>数据金融建模.doc</p>
											<p>数据金融建模1.doc</p>
											<p>${op}${strDisplay}</p>
										</div>
									</div>
									<div class="col-sm-3">
										<label class="btn btn-w-m btn-success" onclick="toUploadFile(this)">点击上传</label>
									</div>
								</div>
								<div class="form-group text-center">
									<input ${strDisplay} type="button" class="btn btn-w-m btn-success" id="btnSave" name="btnSave" value="保存">
									&nbsp;&nbsp;
									<input ${strDisplay} type="button" class="btn btn-w-m btn-success" id="btnNext" name="btnNext" value="提交">
									&nbsp;&nbsp;
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
			$.post("../legislationProcessDoc/saveSendMayorDatum.do", param, function(data) {
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
				$.post("${requestUrl}?stNodeId=${nodeId}&method=nextSendMayorDatum", param, function(data) {
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