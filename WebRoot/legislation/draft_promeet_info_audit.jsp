<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><c:choose>
				<c:when test="${infoPage==true}">
					<span>审核会议待审草案->查看</span>
				</c:when>
				<c:otherwise>
					<span>OA审核 </span>
				</c:otherwise>
			</c:choose></li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close"
		data-dismiss="modal">
		<span aria-hidden="true">&times;</span> <span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">
	<c:choose>
		<c:when
			test="${legislationProcessTask.stTaskStatus==statusCodeArray[0]}">
			<h2
				style="color: #E4243D; text-align: center; font-weight: bold; margin-bottom: 20px">审核会前征询意见</h2>
		</c:when>
		<c:when
			test="${legislationProcessTask.stTaskStatus==statusCodeArray[1]}">
			<h2
				style="color: #E4243D; text-align: center; font-weight: bold; margin-bottom: 20px">审核会征询</h2>
		</c:when>
		<c:otherwise>
			<h2
				style="color: #E4243D; text-align: center; font-weight: bold; margin-bottom: 20px">审核会前征询意见</h2>
		</c:otherwise>
	</c:choose>
	<form id="legislationProcessDocForm" class="form-horizontal"
		novalidate="novalidate">
		<input hidden id="stDocId" name="stDocId" value="${stDocId}">
			<input type="hidden" id="nodeStatus" value="${nodeStatus}">
		<c:choose>
			<c:when test="${legislationProcessTask.stTaskStatus=='TODO' or isbanli=='banli'}">
				<div class="form-group">
					<label class="col-sm-3 control-label text-left">说明：</label>
					<div class="col-sm-9">
						<textarea id="stComent" name="stComent" class="form-control"><c:if
								test="${legislationProcessTask.stBakOne !=null}">${legislationProcessTask.stBakOne}</c:if></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label text-left">送审领导：</label>
					<div class="col-sm-9">
						<textarea class="form-control" id="stPersons" name="stPersons" readonly ondblclick="openEditParticipants('局领导','N')">${legislationProcessTaskdetail.stPersonName}</textarea>
					</div>
					<input type="hidden" name="stPersonsId" id="stPersonsId"
						<c:if test="${legislationProcessTaskdetail.stPersonId!=null}">value="${legislationProcessTaskdetail.stPersonId}" </c:if>>
				</div>
			</c:when>
			<c:when test="${legislationProcessTask.stTaskStatus==statusCodeArray[1]}">
				<!--<div class="form-group">
					<label class="col-sm-3 control-label text-left">领导意见：</label>
					<div class="col-sm-9">
						<textarea id="stComent" name="stComent" class="form-control"><c:if
								test="${legislationProcessTask.stComment1!=null}">${legislationProcessTask.stComment1}</c:if></textarea>
					</div>
				</div>  -->
				<div class="form-group">
				<label class="col-sm-3 control-label text-left">领导审核意见:</label>
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
			</c:when>
			<c:otherwise>
				<div class="form-group">
					<label class="col-sm-3 control-label text-left">说明：</label>
					<div class="col-sm-9">
						<textarea id="stComent" name="stComent" class="form-control"><c:if
								test="${legislationProcessTask.stBakOne !=null}">${legislationProcessTask.stBakOne}</c:if></textarea>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label text-left">送审领导：</label>
					<div class="col-sm-9">
						<textarea class="form-control" id="stPersons" name="stPersons" readonly ondblclick="openEditParticipants('局领导','N')">${legislationProcessTaskdetail.stPersonName}</textarea>
					</div>
					<input type="hidden" name="stPersonsId" id="stPersonsId"
						<c:if test="${legislationProcessTaskdetail.stPersonId!=null}">value="${legislationProcessTaskdetail.stPersonId}" </c:if>>
				</div>
			</c:otherwise>
		</c:choose>

		<div class="form-group text-center">
			<c:if test="${(infoPage==false and legislationProcessTask.stTaskId==null) or (legislationProcessTask.stTaskStatus!=null and legislationProcessTask.stTaskStatus!='DONE') and isbanli!='banli'}">
				<input ${strDisplay} type="button" class="btn btn-w-m btn-success"	id="btnSave" name="btnSave"	onclick="saveLegislationProcessDoc('save')" value="保存">
			&nbsp;&nbsp;
			<input ${strDisplay} type="button" class="btn btn-w-m btn-success"	id="btnSubmit" name="btnSubmit"	onclick="saveLegislationProcessDoc('submit')" value="提交">
			&nbsp;&nbsp;
		  </c:if>
			<input type="button" id="btnClose" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
		</div>

		
					
 			<div class="form-group">
				<label class="control-label">上传材料接收</label>
			</div>	
		<%@include file="/legislation/file/attachUpload.jsp" %>
		
		
		
	</form>
</div>
<script>
	//$('#legislationProcessForm').on('shown.bs.modal', function(event) {
	var isbanli = '${isbanli}';
	if (isbanli == "banli") {
		Duang.success("提示", "【审核会议】处理中，请到【审核会议】中进行操作");
	}
	//});

	function saveLegislationProcessDoc(type) {
		var param = $('#legislationProcessDocForm').formToJson();
		var status = '${legislationProcessTask.stTaskStatus}';
		var statusCodeArray = '${statusCodeArray}';
		if(status=='TODO'){
			if (param.stComent == null || param.stComent == "") {
				if (status == statusCodeArray[0]) {
					Duang.error("提示", "请输入说明");
				} else if (status == statusCodeArray[1]) {
					Duang.error("提示", "请输入领导意见");
				}

				return;
			} else if (status == statusCodeArray[0] && (param.stPersons == null || param.stPersons == "")) {
				Duang.error("提示", "请输入报审领导");
				return;
			}
		}
		var taskid = '${legislationProcessTask.stTaskId}';
		$.post("${requestUrl}?stNodeId=${nodeId}&method=draftPromeetInfo&type=" + type + "&stTaskStatus=${legislationProcessTask.stTaskStatus}", param, function(data) {
			if (taskid == null || taskid == "") {
				Duang.success("提示", "后续操作请到【审核会议】中进行");
			} else {
				Duang.success("提示", "操作成功");
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
			var html = '<label class="btn btn-w-m btn-success"  onclick="toUploadFile(this)">点击上传</label>' + '<input id="' + id + '" name="upload" type="file" style="display:none"   onchange="uploadFile(\'' + id + '\',1,\'' + stSampleId + '\')">';
			obj.parent().html(html);
		} else {
			obj.parent().parent().remove();
		}
	}

	
</script>