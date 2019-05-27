<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span>审核会议 > </span>
		</li>
		<li>
			<span>修改反馈情况确认</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span>
		<span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">
	<h2 style="color: #E4243D; text-align: center; font-weight: bold; margin-bottom: 20px">反馈情况（仅立法过程）</h2>
	<form id="auditMeetingForm" class="form-horizontal" novalidate="novalidate">
		<input type="hidden" name="stMeetingId" value="${legislationCheckmeeting.stMeetingId}">
			<input type="hidden" id="nodeStatus" value="${nodeStatus}">
		<div class="form-body" align="center">
			<table class="table table-border table-bordered table-bg table-hover" style="width: 80%;">
				<tr class="text-center">
					<td class="text-right" width="20%">
						<label>会议名称：</label>
					</td>
					<td>${legislationCheckmeeting.stMeetingName}</td>
				</tr>
				<tr class="text-center">
					<td class="text-right">
						<label>会议地点：</label>
					</td>
					<td>${legislationCheckmeeting.stAddress}</td>
				</tr>
				<tr class="text-center">
					<td class="text-right">
						<label>会议时间：</label>
					</td>
					<td>
						<fmt:formatDate value="${legislationCheckmeeting.dtBeginDate}" pattern="yyyy-MM-dd" />
					</td>
				</tr>

				<tr class="text-center">
					<td class="text-right">
						<label>参会人员：</label>
					</td>
					<td>${legislationCheckmeeting.stPersons}</td>
				</tr>
				<tr class="text-center">
					<td class="text-right">
						<label>会议内容概述</label>
					</td>
					<td>${legislationCheckmeetingTask.stSummary}</td>
				</tr>
			</table>
		</div>

		<div class="form-body" align="center" hidden="hidden">
			<table class="table table-border table-bordered table-bg table-hover" style="width: 80%;">
				<tbody class="text-center" align="center">
					<tr class="text-right" align="center">
						<th class="text-right" width="20%">
							草案文本:
							<span style="color: red"></span>
							<span style="color: dodgerblue"></span>
						</th>
						<td class="text-center" width="80%">
							<span style="color: red">暂未上传</span>
						</td>
					</tr>
					<tr class="text-center">
						<th class="text-right">
							起草说明/审查报告:
							<span style="color: red"></span>
							<span style="color: dodgerblue"></span>
						</th>
						<td>
							<span style="color: red">暂未上传</span>
						</td>
					</tr>
					<tr class="text-center">
						<th class="text-right">
							会议纪要:
							<span style="color: red"></span>
							<span style="color: dodgerblue"></span>
						</th>
						<td>
							<span style="color: red">暂未上传</span>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<div class="form-body" align="center">
			<table class="table table-border table-bordered table-bg table-hover" style="width: 80%;">
				<tbody class="text-center" align="center">
					<tr class="text-center">
						<th class="text-center" >事项名称</th>
						<th class="text-center">事项类型</th>
						<th class="text-center">事项反馈</th>
					</tr>
					<c:forEach items="${checkmeetingItem}" var="doc">
						<tr class="text-center">
							<td class="text-center" width="20%">${doc.stItemName}</td>
							<td class="text-center">${doc.stTypeName}</td>
							<c:choose>
								<c:when test="${doc.stSource!=null && doc.stTypeName=='草案'}">
									<td class="text-center">${doc.stSource.stComment1} </td>
								</c:when>
								<c:when test="${doc.stSource!=null && doc.stTypeName=='立法计划'}">
									<td class="text-center">/</td>
								</c:when>
								<c:otherwise>
									<td></td>
								</c:otherwise>
							</c:choose>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>

 			<div class="form-group">
				<label class="control-label">审核会议反馈材料 </label>
			</div>	
			<%@include file="/legislation/file/attachUpload.jsp" %>


		<!-- 

		<div class="form-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">会议名称：</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="stMeetingName" name="stMeetingName" <c:if test="${legislationCheckmeeting.stMeetingName !=null}">value="${legislationCheckmeeting.stMeetingName}" </c:if>>
				</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">对应草案：</label>
				<div class="col-sm-9">
					<table class="table table-bordered table-hover">
						<thead>
							<th class="text-center">草案名称</th>
							<th class="text-center">新草案名称</th>
							<th class="text-center">修改说明</th>
						</thead>
						<tbody>
							<c:if test="${legislationProcessDocAll !=null&&fn:length(legislationProcessDocAll)>0}">
								<c:forEach items="${legislationProcessDocAll}" var="doc">
									<tr>
										<c:if test="${legislationCheckmeeting.stMeetingId==null}">
											<td class="text-center">
												<input type="checkbox" name="stDocSourceCheck" value="${doc.stDocId}">
											</td>
										</c:if>
										<td class="text-center">${doc.stDocName}</td>
										<td class="text-center">
											<c:if test="${legislationCheckmeeting.stMeetingId !=null}">
												<input type="checkbox" style="display: none" name="stDocSourceCheck" checked value="${doc.stDocId}">
											</c:if>
											<input type="text" name="${doc.stDocId}" placeholder="需修改法规规章草案名称请在此处填写">
										</td>
										<td class="text-center">${legislationProcessTaskMap.get(doc.stDocId).stComment1}</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			-->
		<div class="form-group text-center">
			<input type="hidden" id="op" name="op">
			<!--<input type="button" class="btn btn-w-m btn-success" id="btnSave" name="btnSave" onclick="saveAuditMeeting1('save')" value="保存"> -->
			&nbsp;&nbsp;
			<input type="button" class="btn btn-w-m btn-success" id="btnSubmit" name="btnSubmit" onclick="saveAuditMeeting1('submit')" value="会议关闭完成">
			&nbsp;&nbsp;
			<input id="btnCloseAffirm" type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="返回">
		</div>
		<!-- <div class="form-group">
				<label class="control-label">反馈材料 </label>
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
			<div class="form-group">
				<label class="control-label">其他材料 </label>
				<label class="btn btn-w-m btn-success" onclick="toUploadFile(this)">点击上传 </label>
				<input type="file" id="7" name="upload" style="display: none" onchange="uploadFile(this.id,2,null)">
			</div>
			<div class="form-group">
				<table class="table table-striped table-hover" data-toggle="table" data-mobile-responsive="true" data-card-view="true" data-pagination="true">
					<thead>
						<tr class="text-center">
							<th class="text-center" data-field="id">文件类型</th>
							<th class="text-center" data-field="district_name">文件名称</th>
							<th class="text-center" data-field="set">操作</th>
						</tr>
					</thead>
					<tbody id="otherMaterial">
						<c:if test="${legislationFilesList !=null&&fn:length(legislationFilesList)>0}">
							<c:forEach var="file" items="${legislationFilesList}">
								<c:if test="${file.stSampleId==null||file.stSampleId=='null'}">
									<tr class="text-center">
										<td class="text-left">需要报送的其他材料</td>
										<td>${file.stTitle}</td>
										<td>
											<a target="_blank" href="${basePath}/file/downloadAttach.do?name=${file.stTitle}&url=${file.stFileUrl}">下载</a>
											&nbsp;&nbsp;
											<label style="color: red" onclick="deleteAttach(this,2,null,'${file.stFileId}',null)">删除</label>
											<input type="hidden" id="${file.stFileId}" name="${file.stFileId}" value="${file.stFileId}">
										</td>
									</tr>
								</c:if>
							</c:forEach>
						</c:if>
					</tbody>
				</table>
			</div>
</div> -->
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
	function saveAuditMeeting1(operation) {
		$('#op').val(operation);
		var param = $('#auditMeetingForm').formToJson();
		if (operation == 'submit') {
			layer.confirm('请确认操作！', function(index) {
				layer.close(layer.index);
				$.post("${requestUrl}?stNodeId=${nodeId}&method=saveCheckmeeting&stTaskStatus=${stTaskStatus}", param, function(data) {
					console.log(JSON.stringify(data));
					if (data.success) {
						$('#legislationProcessForm').modal('hide');
						submitForm(1);
						Duang.success("提示", "操作成功");
					} else {
						Duang.error("提示", "操作失败");
					}
				});
			});
		} else {
			$.post("${requestUrl}?stNodeId=${nodeId}&method=saveCheckmeeting&stTaskStatus=${stTaskStatus}", param, function(data) {
				console.log(JSON.stringify(data));
				if (data.success) {
					submitForm(1);
					Duang.success("提示", "操作成功");
				} else {
					Duang.error("提示", "操作失败");
				}
			});
		}
	}

	
</script>