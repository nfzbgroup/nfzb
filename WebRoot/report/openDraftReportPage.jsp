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
				<span><%if("NOD_0000000106".equals(request.getAttribute("stNodeId"))){ %>报审审签(签报)<%}else{ %>报市长审签<%}%></span>
			</li>
			<li>
				<span>>法规规章草案报审材料准备</span>
			</li>
		</ul>
		<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal">
			<span aria-hidden="true">&times;</span>
			<span class="sr-only">Close</span>
		</button>
	</div>
	<div class="modal-body" id="draftReport">
		<div class="ibox float-e-margins">
			<div class="ibox-title">
				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="row">
						<form name="form1" id="legislationProcessDocForm" class="form-horizontal" novalidate="novalidate">
							<input type="hidden" name="stDocId" id="stDocId" value="${stDocId}">
							<input type="hidden" name="stNodeId" id="stNodeId" value="${stNodeId}">
							<input type="hidden" name="stTaskId" id="stTaskId" <c:if test="${legislationProcessTask.stTaskId!=null}"> value="${legislationProcessTask.stTaskId}" </c:if> > 
							<input type="hidden" id="nodeStatus" value="${nodeStatus}">
							<div class="form-body">
									<div class="form-group text-center">
										 <label class="col-sm-3 control-label text-left">法规规章草案：</label>
											<label class="col-sm-6 control-label" style="text-align: left;">
												<span style="font-size: 18px;">${legislationProcessDoc.stDocName}</span>
											</label>
									</div>
									<div class="form-group text-center">
									     <label class="col-sm-3 control-label text-left">法律规章草案报审材料说明：</label>
									     <div class="col-sm-6">
										    <textarea id="stComment2" name=stComment2 class="form-control form_control">${legislationProcessTask.stComment2}</textarea>
									     </div>
									</div>
							    <div class="form-group">
									<label class="control-label">上传材料接收 </label>
								</div>	
								<%@include file="/legislation/file/attachUpload.jsp" %>
							
								<div class="form-group text-center">
									<input type="hidden" id="op" name="op">
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
		$(".tab-left").css('width', $(window).width() * 0.2);
        var isbanli = '${isbanli}';
		if (isbanli == "banli") {
		    //$('#legislationProcessForm').modal('hide');
		   Duang.success("提示", "【报签】处理中，请到【报签】中进行操作");
		};
		$('#btnSave').click(function() {
			$('#op').val('save');
			var param = $('#legislationProcessDocForm').formToJson();
			if (param.stComment2 == null || param.stComment2 == "") {
				Duang.error("提示", "请输入报审材料说明");
			}else{
				$.post("../legislationReport/saveSendMayorDatum.do", param, function(data) {
					if (data.success) {
						$('#legislationProcessForm').modal('hide');
						if($('#stTaskId').val()!=null){
							Duang.success("提示", "操作成功");
						}else{
							Duang.success("提示", "后续操作请到【报签】中进行");
						}
					} else {
						Duang.error("提示", "保存失败！");
					}
				});
			}
		});
		$('#btnNext').click(function() {
			layer.confirm('请确认操作！', function(index) {
				layer.close(layer.index);
				$('#op').val('submit');
				var param = $('#legislationProcessDocForm').formToJson();
				if (param.stComment2 == null || param.stComment2 == "") {
					Duang.error("提示", "请输入报审材料说明");
				}else{
					$.post("../legislationReport/saveSendMayorDatum.do", param, function(data) {
						if (data.success) {
							$('#legislationProcessForm').modal('hide');
							if($('#stTaskId').val()!=null){
								Duang.success("提示", "操作成功");
							}else{
								Duang.success("提示", "后续操作请到【报签】中进行");
							}
						} else {
							Duang.error("提示", "保存失败！");
						}
						submitForm(1);
					});
				}
			});
		});
	});

	function toUploadFile(obj) {
		$(obj).next().click();
	};
	function uploadFile(id, type, stSampleId) {
		$.ajaxFileUpload({
			url : '${basePath}/file/upload.do?nodeStatus=${nodeStatus}&stNodeId=${nodeId}&stSampleId=' + stSampleId,
			type : 'post',
			secureuri : false, //是否启用安全提交,默认为false
			fileElementId : id,
			dataType : 'JSON',
			success : function(data, status) { //服务器响应成功时的处理函数
				data = data.replace(/<.*?>/ig, ""); //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
				var file = JSON.parse(data);
				if (file.success) {
					if (type == 1) {
						var html = '<a target="_blank" href="${basePath}/file/downloadAttach.do?name=' + file.name + '&url=' + file.url + '">下载</a>&nbsp;&nbsp;' + '<input type="hidden" id="'+file.fileId+'"  name="'+file.fileId+'" value='+file.fileId+'>' + '<label  style="color: red" onclick="deleteAttach(this,1,\'' + id + '\',\'' + file.fileId + '\',\'' + stSampleId + '\')" >删除</label>';
						$("#" + id).parent().prev().html('<span>' + file.name + '</span>');
						$("#" + id).parent().html(html);
					} else {
						var html = '<tr class="text-center">' + '<td class="text-left">需要报送的其他材料</td>' + '<td>' + file.name + '</td>' + '<td><a  target="_blank" href="${basePath}/file/downloadAttach.do?name=' + file.name + '&url=' + file.url + '">下载</a>&nbsp;&nbsp;' + '<label  style="color: red" onclick="deleteAttach(this,2,\'' + id + '\',\'' + file.fileId + '\',\'' + stSampleId
								+ '\')">删除</label>' + '<input type="hidden" id="'+file.fileId+'"  name="'+file.fileId+'" value='+file.fileId+'>' + '</td></tr>';
						$('#otherMaterial').append(html);
					}
					Duang.success("提示", "上传材料成功");
				} else {
					Duang.error("提示", "上传材料失败");
				}
			},
			error : function(data, status, e) { //服务器响应失败时的处理函数
				Duang.error("提示", "上传材料失败");
			}
		});
	};
	function deleteAttach(attachObj, type, id, fileId, stSampleId) {
		$.post('${basePath}/file/deleteAttach.do?fileId=' + fileId);
		var obj = $(attachObj);
		if (type == 1) {
			obj.parent().prev().html('<span style="color: red">暂未上传</span>');
			var html = '<label class="btn btn-w-m btn-success"  onclick="toUploadFile(this)">点击上传</label>' + '<input id="' + id + '" name="upload" type="file" style="display:none"  onchange="uploadFile(\'' + id + '\',1,\'' + stSampleId + '\')">';
			obj.parent().html(html);
		} else {
			obj.parent().parent().remove();
		}
	};
</script>