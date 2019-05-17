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
			<span>发起</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span>
		<span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">
	<h2 style="color: #E4243D; text-align: center; font-weight: bold; margin-bottom: 20px">审核会议发起</h2>
	<form id="auditMeetingForm" class="form-horizontal" novalidate="novalidate">
		<input type="hidden" name="stMeetingId" value="${legislationCheckmeeting.stMeetingId}">
		<input type="hidden" name="stItemIds" value="${stItemId}">
		<input type="hidden" id="nodeStatus" value="${nodeStatus}">
		<div class="form-body">
			<div class="form-group">
				<label class="col-sm-2 control-label">会议名称：</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="stMeetingName" name="stMeetingName" value="${legislationCheckmeeting.stMeetingName}">
				</div>
			</div>
			
			
			<div class="form-group">
				<label class="col-sm-2 control-label">会议地点：</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="stAddress" name="stAddress" value="${legislationCheckmeeting.stAddress}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">会议时间：</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" readonly id="dtBeginDate" name="dtBeginDate" value="<fmt:formatDate value="${legislationCheckmeeting.dtBeginDate}"  pattern="yyyy-MM-dd"/>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">参会人员：</label>
				<div class="col-sm-9">
					<textarea class="form-control" id="stPersons" name="stPersons" readonly ondblclick="openEditParticipants('局领导,秘书处,立法一处')">${legislationCheckmeeting.stPersons}</textarea>
				</div>
				<input type="hidden" name="stPersonsId" id="stPersonsId" <c:if test="${stPersonsId!=null}">value="${stPersonsId}" </c:if>>
				<input type="hidden" name="otherPersonsName" id="otherPersonsName" <c:if test="${otherPersonsName!=null}">value="${otherPersonsName}" </c:if>>
			</div>
			
			<c:choose>
				<c:when test="${checkmeetingItems!=null&&fn:length(checkmeetingItems)>0}">
					<div class="form-group">
						<label class="col-sm-2 control-label">已选事项：</label>
						<div class="col-sm-9">
							<table class="table table-bordered table-hover">
								<thead>
									<th class="text-center" hidden="hidden">事项编号</th>
									<th class="text-center">事项名称</th>
									<th class="text-center">事项类型</th>
								</thead>
								<tbody>
									<c:forEach items="${checkmeetingItems}" var="item">
										<tr>
											<td class="text-center" hidden="hidden">${item.stItemId}</td>
											<td class="text-center">${item.stItemName}</td>
											<td class="text-center">${item.stTypeName}</td>
										</tr>
									</c:forEach>
									
								</tbody>
							</table>
						</div>
					</div>				
				</c:when>
			</c:choose>
			<%-- 	
			 <div class="form-group">
				<label class="control-label">审核会议材料 </label>
			</div>	
			<%@include file="/legislation/file/attachUpload.jsp" %>
			 --%>
			<div class="form-group text-center">
				<input  type="hidden" id="op" name="op">
				<input ${strDisplay} type="button" class="btn btn-w-m btn-success" id="btnSave" name="btnSave" onclick="saveAuditMeeting1('save')" value="保存">
				&nbsp;&nbsp;
				<input ${strDisplay} type="button" class="btn btn-w-m btn-success" id="btnSubmit" name="btnSubmit" onclick="saveAuditMeeting1('submit')" value="发送通知">
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
	function saveAuditMeeting1(operation) {
		layer.close(layer.index);
		$('#op').val(operation);
		var param = $('#auditMeetingForm').formToJson();
		//alert(param.stItemIds);
		
		if (param.stMeetingName == null || param.stMeetingName == "") {
			Duang.error("提示", "请输入会议名称");
		//} else if (param.stType == null || param.stType == "") {
			//Duang.error("提示", "请选择会议类型");
		} else if (param.stAddress == null || param.stAddress == "") {
			Duang.error("提示", "请输入会议地点");
		} else if (param.dtBeginDate == null || param.dtBeginDate == "") {
			Duang.error("提示", "请选择会议时间");
		} else if (param.stPersons == null || param.stPersons == "") {
			Duang.error("提示", "请输入会议人员");
		} else {
			if (operation == 'submit') {
				layer.confirm('请确认操作！', function(index) {
					layer.close(layer.index);
					$.post("${requestUrl}?stNodeId=${nodeId}&method=commitCheckmeeting&stTaskStatus=${stTaskStatus}", param, function(data) {
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
			}else{
				$.post("${requestUrl}?stNodeId=${nodeId}&method=saveCheckmeeting&stTaskStatus=TODO", param, function(data) {
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
	}

	
</script>