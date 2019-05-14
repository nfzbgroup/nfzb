<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><c:choose>
				<c:when test="${legislationPlanTask.stTaskStatus=='TODO'}">
					<span>提交说明 </span>
				</c:when>
				<c:otherwise>
					<span>意见反馈 </span>
				</c:otherwise>
			</c:choose></li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close"
		data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">
	<form id="legislationPlanTaskCheckForm" class="form-horizontal"
		novalidate="novalidate">
		<input hidden name="stTaskId" value="${stTaskId}">
		<div class="form-body">
			<c:choose>
				<c:when test="${legislationPlanTask.stTaskStatus=='TODO'}">
					<div class="form-group">
						<label class="col-sm-3 control-label text-left">送审领导：</label>
						<div class="col-sm-9">
							<textarea class="form-control" id="stPersons" name="stPersons" readonly ondblclick="openEditParticipants('局领导')"><c:if test="${userInfoList!=null}"><c:forEach items="${userInfoList}" var="userInfo" varStatus="idx">${userInfo.name}<c:if test="${idx.count!=userInfoList.size()}">,</c:if></c:forEach></c:if></textarea>
						</div>
						<input type="hidden" name="stPersonsId" id="stPersonsId" <c:if test="${stPersonsId!=null}">value="${stPersonsId}" </c:if>>
					</div>
				</c:when>
				<c:when test="${legislationPlanTask.stTaskStatus=='DOING'}">
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
			</c:choose>
			<div class="form-group">
						<label class="control-label">附件上传 </label> <label class="btn btn-w-m btn-success" onclick="toUploadFile(this)">点击上传
						</label> <input type="file" id="7" name="upload" style="display: none" onchange="uploadFile(this.id)">
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
							</tbody>
						</table>
					</div>
			<div class="form-group text-center">
				<input type="button" class="btn btn-w-m btn-success" id="btnSave" name="btnSave" onclick="savePlanTaskCheck()" value="提交">
				&nbsp;&nbsp; <input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
			</div>
		</div>
	</form>

</div>
<script>
	function savePlanTaskCheck() {
		var param = $('#legislationPlanTaskCheckForm').formToJson();
		stContent

		if (param.stPersons == null || param.stPersons == "") {
			Duang.error("提示", "请选择送审的领导！");
		} else {
			$.post("../${requestUrl}?stNodeId=${nodeId}&method=savePlanTaskCheck", param, function(data) {
				$('#legislationProcessForm').modal('hide');
				submitForm(1);
				Duang.success("提示", "操作成功");
			});
		}

	};
	function toUploadFile(obj) {
		$(obj).next().click();
	}
	function uploadFile(id) {
		$.ajaxFileUpload({
			url : '${basePath}/file/upload.do?stNodeId=${stNodeId}',
			type : 'post',
			secureuri : false, //是否启用安全提交,默认为false
			fileElementId : id,
			dataType : 'JSON',
			success : function(data, status) { //服务器响应成功时的处理函数
				data = data.replace(/<.*?>/ig, ""); //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
				var file = JSON.parse(data);
				if (file.success) {
					var html = '<tr class="text-center">' + '<td>' + file.name + '</td>' + '<td><a  target="_blank" href="${basePath}/file/downloadAttach.do?fileId=' + file.fileId + '">下载</a>&nbsp;&nbsp;' + '<label  style="color: red" onclick="deleteAttach(this,\'' + file.fileId + '\')">删除</label>' + '<input type="hidden" id="'+file.fileId+'"  name="'+file.fileId+'" value='+file.fileId+'>'
							+ '</td></tr>';
					$('#otherMaterial').append(html);
					Duang.success("提示", "上传附件成功");
				} else {
					Duang.error("提示", "上传附件失败");
				}
			},
			error : function(data, status, e) { //服务器响应失败时的处理函数
				Duang.error("提示", "上传附件失败");
			}
		});
	}
	function deleteAttach(attachObj, fileId) {
		$.post('${basePath}/file/deleteAttach.do?fileId=' + fileId);
		var obj = $(attachObj);
		obj.parent().parent().remove();
	}
	function openEditParticipants(showName) {
		var stPersonsId = $('#stPersonsId').val();
		var otherPersonsName = $('#otherPersonsName').val();
		$("#processIndexForm").modal({
			remote : "${basePath}/legislationSendNotice/openEditParticipants.do?showName=" + showName + "&stPersonsId=" + stPersonsId + "&otherPersons=N"
		});
	}

	
</script>