<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span>审签 > </span>
		</li>
		<li>
			<span>审批录入</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span>
		<span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">
	<h2 style="color: #E4243D; text-align: center; font-weight: bold; margin-bottom: 20px">签报件核稿OA审核结果</h2>
	<form id="auditReportForm" class="form-horizontal" novalidate="novalidate">
		<input type="hidden" name="stReportId" value="${legislationReport.stReportId}">
				<input type="hidden" id="nodeStatus" value="${nodeStatus}">
		<div class="form-body">
			<div class="form-group">
				<label class="col-sm-2 control-label">签报名称：</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="stReportName" disabled name="stReportName" value="${legislationReport.stReportName}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">报送领导：</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="stAddress" disabled name="stPersons" value="<c:if test="${legislationReportTaskdetail.stPersonName!=null}">${legislationReportTaskdetail.stPersonName}</c:if>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label text-left">领导审核意见:</label>
				<div class="col-sm-9">
				   <table class="table table-border table-bordered">
						<thead>
							<tr>
								<th style="text-align: center">领导姓名</th>
								<th style="text-align: center">是否反馈</th>
								<th style="text-align: center">反馈内容</th>
								<th style="text-align: center">反馈时间</th>
							</tr>
						</thead>
						<tbody>
							<c:if
								test="${legislationSendNoticeList !=null&&fn:length(legislationSendNoticeList)>0}">
								<c:forEach var="legislationSendNotice"
									items="${legislationSendNoticeList}">
									<tr>
										<td style="text-align: center">
											${legislationSendNotice.stUserName}</td>
										<td style="text-align: center">
											${legislationSendNotice.stNoticeStatus}</td>
										<td style="text-align: center"><c:choose>
												<c:when
													test="${legislationSendNotice.stNoticeStatus!='已反馈'}">---</c:when>
												<c:otherwise>${legislationSendNotice.stFeedbackContent}</c:otherwise>
											</c:choose></td>
										<td style="text-align: center"><c:if
												test="${legislationSendNotice.stNoticeStatus!='已反馈'}">
						---</c:if> <c:if test="${legislationSendNotice.stNoticeStatus=='已反馈'}">
												<fmt:formatDate type="time" pattern="yyyy-MM-dd HH:mm:ss"
													value="${legislationSendNotice.dtFeekbackDate}" />
											</c:if></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
					
			 <div class="form-group">
				<label class="control-label">材料接收 </label>
			</div>	
			<%@include file="/legislation/file/attachUpload.jsp" %>
			
			<div class="form-group text-center">
				<input type="hidden" id="op" name="op">
				<input type="button" class="btn btn-w-m btn-success" id="btnSave" name="btnSave" onclick="saveAuditReport1('save')" value="保存">
				&nbsp;&nbsp;
				<input type="button" class="btn btn-w-m btn-success" id="btnSubmit" name="btnSubmit" onclick="saveAuditReport1('submit')" value="下一步">
				&nbsp;&nbsp;
				<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="返回">
			</div>
		</div>
	</form>
</div>
<script>
	$(function() {
		laydate.render({
			elem : '#dtBeginDate',
			format : 'yyyy-MM-dd',
			calendar : true,
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
	function saveAuditReport1(operation) {

		layer.close(layer.index);
		$('#op').val(operation);
		var param = $('#auditReportForm').formToJson();
			if (operation == 'submit') {
				layer.confirm('请确认操作！', function(index) {
					layer.close(layer.index);
					$.post("${requestUrl}?stNodeId=${nodeId}&method=saveReport&stTaskStatus=${stTaskStatus}", param, function(data) {
						console.log(JSON.stringify(data));
						if (data.success) {
							if (operation == 'submit') {
								$('#legislationProcessForm').modal('hide');
							}
							submitForm(1);
							Duang.success("提示", "操作成功");
						} else {
							Duang.error("提示", "操作失败");
						}
					});
				});
			} else {
				$.post("${requestUrl}?stNodeId=${nodeId}&method=saveReport&stTaskStatus=${stTaskStatus}", param, function(data) {
					console.log(JSON.stringify(data));
					if (data.success) {
						$('#legislationProcessForm').modal('hide');
						submitForm(1);
						Duang.success("提示", "操作成功");
					} else {
						Duang.error("提示", "操作失败");
					}
				});
			}
		
	}

	
</script>