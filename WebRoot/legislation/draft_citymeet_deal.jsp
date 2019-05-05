<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
		  <c:choose>
		   <c:when test="${nodeId=='NOD_0000000110'}">
		    <span>常务会议材料上传 </span>
		   </c:when>
		   <c:otherwise>
		    <span>常务会议->议题确认 </span>
		   </c:otherwise>
		  </c:choose>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span>
		<span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">
	<h2 style="color: #E4243D; text-align: center; font-weight: bold; margin-bottom: 20px">选择草案对应的议题</h2>
	<form id="legislationProcessDocForm" class="form-horizontal" novalidate="novalidate">
		<input hidden id="stDocId" name="stDocId" value="${stDocId}" >
		<input hidden id="stTaskId" name="stTaskId" <c:if test="${legislationProcessTask.stTaskId !=null}">value="${legislationProcessTask.stTaskId}"</c:if>>
			<div class="form-group">
				<label class="col-sm-3 control-label">草案名称：</label>
				<div class="col-sm-9">
					${legislationProcessDoc.stDocName}
				</div>
			</div>
				<div class="form-group">
					<label class="col-sm-3 control-label text-left">材料说明：</label>
					<div class="col-sm-9">
						<textarea id="stComment1" <c:if test="${legislationProcessTask.stTaskStatus =='DONE'}">disabled</c:if> name="stComment1" class="form-control"><c:if test="${legislationProcessTask.stComment1 !=null}">${legislationProcessTask.stComment1}</c:if></textarea>
					</div>
				</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">议题选择：</label>
				<div class="col-sm-9">
					<table class="table table-bordered table-hover">
						<thead>
							<th class="text-center">选择议题</th>
							<th class="text-center">议题名称</th>
						</thead>
						<tbody>
							<c:if test="${cityMeetingList !=null&&fn:length(cityMeetingList)>0}">
								<c:forEach items="${cityMeetingList}" var="topic">
									<tr>
										<td class="text-center">
											<input type="checkbox" <c:if test="${legislationProcessTask.stTaskStatus =='DONE'}">disabled</c:if> name="stDocSourceCheck" value="${topic.stTopicId}" <c:if test="${legislationProcessTask.stComment2.indexOf(topic.stTopicId)>=0 }">checked</c:if>>
										</td>
										<td class="text-center">${topic.stTopicName}</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>

		<div class="form-group text-center">
		<label class="col-sm-3 control-label"></label>
		<c:if test="${(legislationProcessTask.stTaskId == null)||(legislationProcessTask.stTaskStatus !='DONE')}">
			<input type="button" class="btn btn-w-m btn-success" id="btnSave" name="btnSave" onclick="saveLegislationProcessDoc('save')" value="保存">
			&nbsp;&nbsp;
			<input type="button" class="btn btn-w-m btn-success" id="btnSubmit" name="btnSubmit" onclick="saveLegislationProcessDoc('submit')" value="提交">
		</c:if>
			&nbsp;&nbsp;
			<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
		</div>

		<div class="form-group" >
			<label class="col-sm-3 control-label">报审材料上传 </label>
			<label class="btn btn-w-m btn-success" onclick="toUploadFile(this)">点击上传 </label>
			<input type="file" id="7" name="upload" style="display: none" onchange="uploadFile(this.id,2,null)">
		</div>
		<div class="form-group">
			<table class="table table-striped table-hover" data-toggle="table" data-mobile-responsive="true" data-card-view="true" data-pagination="true">
				<thead>
					<tr class="text-center">
						<th class="text-center" data-field="district_name">文件名称</th>
						<th class="text-center" data-field="set">操作</th>
					</tr>
				</thead>
				<tbody id="otherMaterial">
					<c:if test="${legislationFilesList !=null&&fn:length(legislationFilesList)>0}">
						<c:forEach var="file" items="${legislationFilesList}">
							<c:if test="${file.stSampleId==null||file.stSampleId=='null'}">
								<tr class="text-center">
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
	</form>
</div>
<script>
	$('#legislationProcessForm').on('shown.bs.modal', function(event) {
		var isbanli = '${isbanli}';
		if (isbanli == "banli") {
			$('#legislationProcessForm').modal('hide');
			Duang.success("提示", "【审核会议】处理中，请到【审核会议】中进行操作");
		}

	});

	function saveLegislationProcessDoc(type) {
		var param = $('#legislationProcessDocForm').formToJson();
		var status = '${legislationProcessTask.stTaskStatus}';
		var statusCodeArray = '${statusCodeArray}';
		
		var stComment2 = "";
		var checkedNum = 0;
		$('[name="stDocSourceCheck"]:checked').each(function() {
			stComment2 = this.value;
			checkedNum++;
		});
		console.log(stComment2);
		if (checkedNum != 1) {
			Duang.error("提示", "请仅选择一个议题");
			return;
		}
		param.stComment2 = stComment2;
		if (param.stComment1 == null || param.stComment1 == "") {
			Duang.error("提示", "请输入说明");

			return;
		}
		var taskid = '${legislationProcessTask.stTaskId}';

		$.post("${requestUrl}?stNodeId=NOD_0000000110&method=saveCitymeetingDraft&op=" + type + "&stTaskStatus=${legislationProcessTask.stTaskStatus}", param, function(data) {
			if (taskid == null || taskid == "") {
				Duang.success("提示", "后续操作请到【常务会议】中进行");
			}
			$('#legislationProcessForm').modal('hide');
			submitForm(1);
		});

	};
	function toUploadFile(obj) {
		$(obj).next().click();
	}
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
						var html = '<tr class="text-center">' + '<td>' + file.name + '</td>' + '<td><a  target="_blank" href="${basePath}/file/downloadAttach.do?name=' + file.name + '&url=' + file.url + '">下载</a>&nbsp;&nbsp;' + '<label  style="color: red" onclick="deleteAttach(this,2,\'' + id + '\',\'' + file.fileId + '\',\'' + stSampleId + '\')">删除</label>'
								+ '<input type="hidden" id="'+file.fileId+'"  name="'+file.fileId+'" value='+file.fileId+'>' + '</td></tr>';
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
	}
	function deleteAttach(attachObj, type, id, fileId, stSampleId) {
		$.post('${basePath}/file/deleteAttach.do?fileId=' + fileId);

		var obj = $(attachObj);
		if (type == 1) {
			obj.parent().prev().html('<span style="color: red">暂未上传</span>');
			var html = '<label class="btn btn-w-m btn-success"  onclick="toUploadFile(this)">点击上传</label>' + '<input id="' + id + '" name="upload" type="file" style="display:none"   onchange="uploadFile(\'' + id + '\',1,\'' + stSampleId + '\')">';
			obj.parent().html(html);
		} else {
			obj.parent().parent().remove();
		}
	}

	
</script>