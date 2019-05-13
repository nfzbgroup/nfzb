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
			<span>会议纪要</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span>
		<span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">
	<h2 style="color: #E4243D; text-align: center; font-weight: bold; margin-bottom: 20px">s会议纪要</h2>
	<form id="auditMeetingForm" class="form-horizontal" novalidate="novalidate">
		<input type="hidden" name="stMeetingId" value="${legislationCheckmeeting.stMeetingId}">


		<div class="form-body" align="center">
			<table class="table table-border table-bordered table-bg table-hover" style="width: 60%;">
				<tr class="text-center">
					<th class="text-right" width="20%">
						<label>会议名称：</label>
					</th>
					<td>${legislationCheckmeeting.stMeetingName}</td>
				</tr>
				<tr class="text-center">
					<th class="text-right">
						<label>会议地点：</label>
					</th>
					<td>${legislationCheckmeeting.stAddress}</td>
				</tr>
				<tr class="text-center">
					<th class="text-right">
						<label>会议时间：</label>
					</th>
					<td>
						<fmt:formatDate value="${legislationCheckmeeting.dtBeginDate}" pattern="yyyy-MM-dd" />
					</td>
				</tr>
				<tr class="text-center">
					<th class="text-right">
						<label>对应草案：</label>
					</th>
					<td>
						<c:forEach items="${legislationProcessDocList}" var="doc" varStatus="status">
						【${status.index + 1}】${doc.stDocName}&nbsp;&nbsp;<br>
						</c:forEach>
					</td>
				</tr>
				<tr class="text-center">
					<th class="text-right">
						<label>对应计划：</label>
					</th>
					<td>
						<c:forEach items="${legislationPlanList}" var="plan" varStatus="status">
						【${status.index + 1}】${plan.stPlanName}&nbsp;&nbsp;<br>
						</c:forEach>
					</td>
				</tr>
				<tr class="text-center">
					<th class="text-right">
						<label>参会人员：</label>
					</th>
					<td>&nbsp;&nbsp;${legislationCheckmeeting.stPersons}</td>
				</tr>
				<tr class="text-center">
					<th class="text-right">
						<label>草案文本：</label>
					</th>
					<td>
						<span style="color: red">暂未上传</span>
					</td>
				</tr>
				<tr class="text-center">
					<th class="text-right">
						<label>起草说明/审查报告：</label>
					</th>
					<td>
						<span style="color: red">暂未上传</span>
					</td>
				</tr>
				<tr class="text-center">
					<th class="text-right">
						<label>会议纪要：</label>
					</th>
					<td>
						<span style="color: red">暂未上传</span>
					</td>
				</tr>
			</table>
		</div>

		<div class="form-body" align="center">
			<table class="table table-border table-bordered table-bg table-hover" style="width: 60%;">
				<tbody class="text-center" align="center">
					<tr class="text-center">
						<td class="text-right" width="20%">
							<label>会议内容概述：</label>
						</td>
						<td>
							<textarea class="form-control" id="stSummary" name="stSummary">${legislationCheckmeetingTask.stSummary}</textarea>
						</td>
					</tr>
				</tbody>
			</table>
		</div>

		<!-- 
		<div class="form-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">会议名称：</label>
				<div class="col-sm-9">${legislationProcessDoc.stDocName}</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">与会人员：</label>
				<div class="col-sm-9">${legislationProcessDoc.stComent}</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">会议纪要：</label>
				<div class="col-sm-9">
					<textarea class="form-control" id="stSummary" name="stSummary">${legislationCheckmeetingTask.stSummary}</textarea>
				</div>
			</div> 
			-->
		<div class="form-group text-center">
			<input type="hidden" id="op" name="op">
			<input ${strDisplay} type="button" class="btn btn-w-m btn-success" id="btnSave" name="btnSave" onclick="saveAuditMeeting1('save')" value="保存">
			&nbsp;&nbsp;
			<input ${strDisplay} type="button" class="btn btn-w-m btn-success" id="btnSubmit" name="btnSubmit" onclick="saveAuditMeeting1('submit')" value="提交">
			&nbsp;&nbsp;
			<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="返回">
		</div>
	</form>
</div>

<script>
	$(function() {
		laydate.render({
			elem : '#dtCreateDate',
			format : 'yyyy-MM-dd',
			calendar : true,
		});
	});
	function toUploadFile(obj) {
		$(obj).next().click();
	};
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
				//alert('----' + operation);
				//if (param.stComment1 == null || param.stComment1 == "") {
				//	Duang.error("提示", "请输入会议人员的反馈");
				//} else {
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
			})
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
		//}
	}

	
</script>