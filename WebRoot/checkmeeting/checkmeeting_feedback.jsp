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
			<span>参加情况反馈</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span>
		<span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">
	<h2 style="color: #E4243D; text-align: center; font-weight: bold; margin-bottom: 20px">审核会议-参加情况反馈</h2>
	<form id="auditMeetingForm" class="form-horizontal" novalidate="novalidate">
		<input type="hidden" name="stMeetingId" value="${legislationCheckmeeting.stMeetingId}">
		<input type="hidden" name="allPersonFeedBack" id="allPersonFeedBack" value="">
		<input type="hidden" name="allPersonFeedBackTime" id="allPersonFeedBackTime" value="">
		<div class="form-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">会议名称：</label>
				<div class="col-sm-9 control-label" style="text-align: left">${legislationCheckmeeting.stMeetingName}</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">会议类型：</label>
				<div class="col-sm-9 control-label" style="text-align: left">${legislationCheckmeeting.stType}</div>
			</div>

			<div class="form-group">
				<label class="col-sm-3 control-label">会议地点：</label>
				<div class="col-sm-9 control-label" style="text-align: left">${legislationCheckmeeting.stAddress}</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">会议时间：</label>
				<div class="col-sm-9 control-label" style="text-align: left">
					<fmt:formatDate value="${legislationCheckmeeting.dtBeginDate}" pattern="yyyy-MM-dd" />
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">对应草案：</label>
				<div class="col-sm-6">
					<table class="table table-border table-bordered table-bg table-hover" id="showtable" data-toggle="table" data-mobile-responsive="true" data-card-view="true" data-pagination="true">
						<c:forEach items="${legislationProcessDocList}" var="doc" varStatus="status">
							<tr>
								<td>${status.index + 1}、${doc.stDocName}&nbsp;&nbsp;</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>

			<br>
			<div class="form-group">
				<label class="col-sm-3 control-label text-left">通知回执：</label>
				<div class="col-sm-6">
					<table class="table table-border table-bordered table-bg table-hover" id="showtable" data-toggle="table" data-mobile-responsive="true" data-card-view="true" data-pagination="true">
						<thead>
							<th class="text-center" width="20%">人员</th>
							<th class="text-center" width="50%">反馈信息</th>
							<th class="text-center" width="30%">反馈时间</th>
						</thead>
						<tbody>
							<c:forEach items="${mapListJson}" var="person" varStatus="status">
								<tr class="text-center">
									<td>
										<label>${person.name}</label>
									</td>
									<td>
										<input type="text" class="form-control" id="personFeedBack${status.index}" value="${person.feedBack}">
									</td>

									<td>
										<input type="text" class="form-control" id="feedTime${status.index}" value="${person.feedBackT}">
									</td>
								</tr>
							</c:forEach>
							<c:if test="${mapListJson==null}">
								<c:forEach items="${stPersonList}" var="person" varStatus="status">
									<tr class="text-center">
										<td>
											<label>${person}</label>
										</td>
										<td>
											<input type="text" class="form-control" id="personFeedBack${status.index}" value="">
										</td>

										<td>
											<input type="text" class="form-control" id="feedTime${status.index}" value="">
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>

				</div>
			</div>



			<div class="form-group text-center">
				<input type="hidden" id="op" name="op">
				<input type="button" class="btn btn-w-m btn-success" id="btnSave" name="btnSave" onclick="saveAuditMeeting1('save')" value="保存">
				&nbsp;&nbsp;
				<input type="button" class="btn btn-w-m btn-success" id="btnSubmit" name="btnSubmit" onclick="saveAuditMeeting1('submit')" value="提交">
				&nbsp;&nbsp;
				<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="返回">
			</div>

		</div>
	</form>

</div>
<script>
	$(function() {
		<c:forEach items="${mapListJson}" var="person" varStatus="status">
		laydate.render({
			elem : '#feedTime${status.index}',
			format : 'yyyy-MM-dd',
			calendar : true,
		});
		</c:forEach>
		<c:if test="${mapListJson==null}">
		<c:forEach items="${stPersonList}" var="person" varStatus="status">
		laydate.render({
			elem : '#feedTime${status.index}',
			format : 'yyyy-MM-dd',
			calendar : true,
		});
		</c:forEach>
		</c:if>
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
		//alert('----' + operation);
		$('#op').val(operation);
		//console.info($("[id^=personFeedBack]").size());
		//console.info($("[id^=personFeedBack]").get(0));
		//$('#allPersonFeedBack').val($("[id^=personFeedBack]").val());

		var allPersonFeedBack = "";
		var allPersonFeedBackTime = "";
		$("[id^=personFeedBack]").each(function(index, item) {
			var val = $(this).val(); //获取value值
			console.info(val);
			allPersonFeedBack += val + "#";
		});
		console.info("allPersonFeedBack---" + allPersonFeedBack);
		$('#allPersonFeedBack').val(allPersonFeedBack);
		$("[id^=feedTime]").each(function(index, item) {
			var val = $(this).val(); //获取value值
			console.info(val);
			allPersonFeedBackTime += val + "#";
		});
		console.info("allPersonFeedBackTime---" + allPersonFeedBackTime);
		$('#allPersonFeedBackTime').val(allPersonFeedBackTime);
		//[{"website":"America","create_time":"123"},{"website":"china","create_time":"234"}]

		var param = $('#auditMeetingForm').formToJson();
		console.info("param-------" + param);
		//if (param.stComment1 == null || param.stComment1 == "") {
		//	Duang.error("提示", "请输入会议人员的反馈");
		//} else {
		$.post("${requestUrl}?stNodeId=${nodeId}&method=saveCheckmeeting&stTaskStatus=${stTaskStatus}", param, function(data) {
			//console.log(JSON.stringify(data));
			if (data.success) {
				if (operation == 'submit') {
					$('#legislationProcessForm').modal('hide');
					submitForm(1);
				}
				Duang.success("提示", "操作成功");
			} else {
				Duang.error("提示", "操作失败");
			}
		});
		//}
	}

	
</script>