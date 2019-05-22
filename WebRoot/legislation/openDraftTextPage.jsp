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
				<!--<span>>法规规章草案</span>-->
				<c:choose>
				    <c:when test="${nodeId=='NOD_0000000114'}">正式文本准备</c:when>
				    <c:when test="${nodeId=='NOD_0000000117'}">立法归档
				        <c:if test="${nodeStatus=='TODO'}">> 待处理</c:if>
	                    <c:if test="${nodeStatus=='DONE'}">> 已处理</c:if>
				    </c:when>
				    <c:when test="${nodeId=='NOD_0000000116'}">规章文本报备
				        <c:if test="${nodeStatus=='TODO'}">> 待报备</c:if>
				        <c:if test="${nodeStatus=='DOING'}">> 意见处理</c:if>
				        <c:if test="${nodeStatus=='DONE'}">> 已完成</c:if>
				    </c:when>
				    <c:when test="${nodeId=='NOD_0000000115'}">正式文本处理
				        <c:if test="${nodeStatus=='TODO'}">> 复核</c:if>
				        <c:if test="${nodeStatus=='PRINT'}">> 送印</c:if>
				        <c:if test="${nodeStatus=='FINAL'}">> 正式文本</c:if>
				        <c:if test="${nodeStatus=='ONLINE'}">> 上网发布</c:if>
				        <c:if test="${nodeStatus=='DONE'}">> 已完成</c:if>
				    </c:when>
				</c:choose>
			</li>
		</ul>
		<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal">
			<span aria-hidden="true">&times;</span>
			<span class="sr-only">Close</span>
		</button>
	</div>
	<div class="modal-body">
	  <h2 style="color: #E4243D; text-align: center; font-weight: bold; margin-bottom: 20px">
	   <c:if test="${nodeId=='NOD_0000000114'}">正式文本准备</c:if>
	   <c:if test="${nodeId=='NOD_0000000117'}">立法归档</c:if>
	   <c:if test="${nodeId=='NOD_0000000115'}">
	    <c:if test="${nodeStatus=='TODO'}">复核</c:if>
	    <c:if test="${nodeStatus=='PRINT'}">送印</c:if>
		<c:if test="${nodeStatus=='FINAL' or nodeStatus=='DONE'}">正式文本</c:if>
		<c:if test="${nodeStatus=='ONLINE'}">上网发布</c:if>
	   </c:if>
	   <c:if test="${nodeId=='NOD_0000000116'}">
	    <c:if test="${nodeStatus=='TODO' or nodeStatus=='DONE'}">规章文本报备</c:if>
	    <c:if test="${nodeStatus=='DOING'}">意见处理</c:if>
	   </c:if>
	  </h2>
		<!-- <div class="ibox float-e-margins">
			<div class="ibox-title">
				<div class="wrapper wrapper-content animated fadeInRight">
					<div class="row"> -->
						<form name="form1" id="legislationProcessDocForm" class="form-horizontal" novalidate="novalidate">
							<input type="hidden" name="stDocId" id="stDocId" value="${stDocId}">
							<input type="hidden" name="stNodeId" id="stNodeId" value="${nodeId}">
							<div class="form-body">
								<div class="form-group">
										<div class="form-group text-center">
											<label class="col-sm-3 control-label text-left">法规规章草案：</label>
											<label class="col-sm-7 control-label" style="text-align: left;">
												<span style="font-size: 18px;">${legislationProcessDoc.stDocName}</span>
											</label>
										</div>
										<c:if test="${nodeId=='NOD_0000000114'}">
										   <div class="form-group text-center">
												<label class="col-sm-3 control-label text-left">正式文本说明：</label>
									            <div class="col-sm-7">
										           <textarea  name="stComment2" class="form-control form_control">${legislationProcessTask.stComment2}</textarea>
									            </div>
									       </div>
										</c:if>
										<c:if test="${nodeId=='NOD_0000000117'}">
										   <div class="form-group text-center">
												<label class="col-sm-3 control-label text-left">立法归档说明：</label>
									            <div class="col-sm-7">
										           <textarea  name="stComment1" class="form-control form_control">${legislationProcessTask.stComment1}</textarea>
									            </div>
									       </div>
										</c:if>
										<c:if test="${nodeId=='NOD_0000000116'}">
										<c:if test="${nodeStatus=='TODO'}">
										    <div class="form-group text-center">
												<label class="col-sm-3 control-label text-left">待报备说明：</label>
									            <div class="col-sm-7">
										           <textarea  name="stComment1" class="form-control form_control">${legislationProcessTask.stComment1}</textarea>
									            </div>
									        </div>
										</c:if>
										<c:if test="${nodeStatus=='DOING'}">
										    <div class="form-group text-center">
												<label class="col-sm-3 control-label text-left">意见处理说明：</label>
									            <div class="col-sm-7">
										           <textarea name="stBakOne" class="form-control form_control">${legislationProcessTask.stBakOne}</textarea>
									            </div>
									        </div>
										</c:if>
										<c:if test="${nodeStatus=='DONE'}">
										   <div class="form-group">
												<label class="col-sm-3 control-label text-left">待报备说明：</label>
												<div class="col-sm-7">
													<textarea  name="stComment1" class="form-control">${legislationProcessTask.stComment1}</textarea>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label text-left">意见处理说明：</label>
												<div class="col-sm-7">
													<textarea  name="stBakOne" class="form-control">${legislationProcessTask.stBakOne}</textarea>
												</div>
											</div>
										</c:if>
										</c:if>
										
							
										<c:if test="${nodeId=='NOD_0000000115'}">
										<c:if test="${nodeStatus=='TODO'}">
										    <div class="form-group text-center">
												<label class="col-sm-3 control-label text-left">复核说明：</label>
									            <div class="col-sm-7">
										           <textarea  name="stComment1" class="form-control form_control">${legislationProcessTask.stComment1}</textarea>
									            </div>
									        </div>
										</c:if>
										<c:if test="${nodeStatus=='PRINT'}">
										    <div class="form-group text-center">
												<label class="col-sm-3 control-label text-left">送印说明：</label>
									            <div class="col-sm-7">
										           <textarea name="stBakOne" class="form-control form_control">${legislationProcessTask.stBakOne}</textarea>
									            </div>
									        </div>
										</c:if>
										<c:if test="${nodeStatus=='FINAL'}">
										    <div class="form-group text-center">
												<label class="col-sm-3 control-label text-left">正式文本说明：</label>
									            <div class="col-sm-7">
										           <textarea name="stComment2" class="form-control form_control">${legislationProcessTask.stComment2}</textarea>
									            </div>
									        </div>
										</c:if>
										<c:if test="${nodeStatus=='ONLINE'}">
										    <div class="form-group text-center">
												<label class="col-sm-3 control-label text-left">上网发布说明：</label>
									            <div class="col-sm-7">
										           <textarea  name="stBakTwo" class="form-control form_control">${legislationProcessTask.stBakTwo}</textarea>
									            </div>
									        </div>
										</c:if>
										<c:if test="${nodeStatus=='DONE'}">
											<div class="form-group">
												<label class="col-sm-3 control-label text-left">复核说明：</label>
												<div class="col-sm-7">
													<textarea  name="stComment1" class="form-control">${legislationProcessTask.stComment1}</textarea>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label text-left">送印说明：</label>
												<div class="col-sm-7">
													<textarea  name="stBakOne" class="form-control">${legislationProcessTask.stBakOne}</textarea>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label text-left">正式文本说明：</label>
												<div class="col-sm-7">
													<textarea  name="stComment2" class="form-control">${legislationProcessTask.stComment2}</textarea>
												</div>
											</div>
											<div class="form-group">
												<label class="col-sm-3 control-label text-left">上网发布说明：</label>
												<div class="col-sm-7">
													<textarea  name="stBakTwo" class="form-control">${legislationProcessTask.stBakTwo}</textarea>
												</div>
											</div>   
										</c:if>
										</c:if>
									
								</div>
							<div class="form-group">
								<table class="table table-striped table-bordered table-hover" data-toggle="table" data-mobile-responsive="true" data-card-view="true" data-pagination="true">
									<thead>
										<tr class="text-center">
											<th class="text-center" data-field="id">文件类型</th>
											<th class="text-center" data-field="district_name">文件名称</th>
											<th class="text-center" data-field="set">操作</th>
										</tr>
									</thead>
									<tbody>
										<s:iterator value="#request.LegislationExampleList" var="example">
											<tr class="text-center">
												<td class="text-left">${example.stExampleName}
													<c:if test="${example.stNeed=='NEED'}">
														<span style="color: red">(必须上传)</span>
													</c:if>
													<span style="color: dodgerblue">(范本)</span>
												</td>
												<td>
													<c:choose>
														<c:when test="${example.fileId !=null}">
															<span>${example.fileName}</span>
														</c:when>
														<c:otherwise>
															<span style="color: red">暂未上传</span>
														</c:otherwise>
													</c:choose>
												</td>
												<td>
													<c:choose>
														<c:when test="${example.fileId !=null}">
															<a target="_blank" href="${basePath}/file/downloadAttach.do?name=${example.fileName}&url=${example.fileUrl}">下载</a>&nbsp;&nbsp;
				                                            <input type="hidden" id="${example.fileId}" name="${example.fileId}" value="${example.fileId}">
															<label style="color: red" onclick="deleteAttach(this,1,'${example.stExampleId}','${example.fileId}','${example.stExampleId}')">删除</label>
														</c:when>
														<c:otherwise>
															<label class="btn btn-w-m btn-success" onclick="toUploadFile(this)">点击上传</label>
															<input id="${example.stExampleId}" name="upload" type="file" style="display: none" onchange="uploadFile(this.id,1,'${example.stExampleId}')">
														</c:otherwise>
													</c:choose>
												</td>
											</tr>
										</s:iterator>
									</tbody>
								</table>
							</div>
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
					<!--</div>
				</div>
			</div>
		</div>  -->
	</div>
</body>
<script>
	$(function() {
		$(".tab-left").css('width', $(window).width() * 0.2)
		


		$('#btnSave').click(function() {
			$('#op').val('save');
			var param = $('#legislationProcessDocForm').formToJson();
			$.post("../legislationProcessDoc/saveSend.do", param, function(data) {
				$('#legislationProcessForm').modal('hide');
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
				$('#op').val('submit');
				var param = $('#legislationProcessDocForm').formToJson();
				$.post("../legislationProcessDoc/saveSend.do", param, function(data) {
					$('#legislationProcessForm').modal('hide');
					if (data.success) {
						Duang.success("提示", "操作成功,已提交。");
					} else {
						Duang.error("提示", "保存失败！");
					}
					submitForm(1);
				});
			});
		});
	});

	function uploadFile(id, type, stSampleId) {
		$.ajaxFileUpload({
			url : '${basePath}/file/upload.do?stNodeId=${nodeId}&stSampleId=' + stSampleId,
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
</script>