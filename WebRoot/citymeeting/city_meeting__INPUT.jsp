<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
	<ul class="page-breadcrumb"> 
		<li>
			<span>常务会议会议记要></span>
		</li>
		<li>
			<span>会议记要 </span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span>
		<span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">
	<h2 style="color: #E4243D; text-align: center; font-weight: bold; margin-bottom: 20px">常务会议会议记要 </h2>
	<form id="auditMeetingForm" class="form-horizontal" novalidate="novalidate">
		<input type="hidden" name="stTopicId" value="${legislationCitymeeting.stTopicId}">
		<div class="form-body">
			<div class="form-group">
				<label class="col-sm-2 control-label">议题名称：</label>
				<div class="col-sm-9">
					<input type="text"  readonly="readonly" class="form-control" id="stTopicName" name="stTopicName" value="${legislationCitymeeting.stTopicName}">
				</div>
			</div>
			
			
		<div class="form-group">
				<label class="col-sm-2 control-label">议题说明：</label>
				<div class="col-sm-9">
					<input type="text"  readonly="readonly" class="form-control" id="stBak" name="stBak" value="${legislationCitymeetingTask.stBak}">
				</div>
			</div>
			
			
			
			
			<div class="form-group">
				<label class="col-sm-2 control-label">议题材料： </label>
				<div class="col-sm-9">
					<table class="table table-striped table-bordered table-hover text-center" data-toggle="table" data-mobile-responsive="true" data-card-view="true" data-pagination="true">
						<thead align="center">
							<tr class="text-center">
								<th class="text-center" data-field="id" width="40%">文件类型</th>
								<th class="text-center" data-field="district_name" width="40%">文件名称</th>
								<th class="text-center" data-field="set" width="20%">操作</th>
							</tr>
						</thead>
						<tbody class="text-center" align="center">
							<tr class="text-center" align="center">
								<td class="text-left">
									材料
									<span style="color: red"></span>
									<span style="color: dodgerblue">(范本)</span>
								</td>
								<td>
									<span style="color: red">暂未上传</span>
								</td>
								<td>
									<label class="btn btn-w-m btn-success" onclick="toUploadFile(this)">点击上传</label>
									<input id="upload" name="upload" type="file" style="display: none">
								</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">材料说明：</label>
				<div class="col-sm-9">
					<input type="text"  readonly="readonly" class="form-control" id="stBak1" name="stBak1" value="${legislationCitymeetingTask.stBak1}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">议题结果：</label>
				<div class="col-sm-9">
					<input type="text"  readonly="readonly" class="form-control" id="stType" name="stType" value="${legislationCitymeeting.stType}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">会议地点：</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="stAddress" name="stAddress" value="${legislationCitymeeting.stAddress}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">会议时间：</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" readonly id="dtBeginDate" name="dtBeginDate" value="<fmt:formatDate value="${legislationCitymeeting.dtBeginDate}"  pattern="yyyy-MM-dd"/>">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">参会人员：</label>
				<div class="col-sm-9">
					<textarea class="form-control" id="stPersons" name="stPersons">${legislationCitymeeting.stPersons}</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">会议记要：</label>
				<div class="col-sm-9">
					<input type="text" class="form-control" id="stBak2" name="stBak2" value="${legislationCitymeetingTask.stBak2}">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-2 control-label">议题待处理立法：</label>
				<div class="col-sm-9">
					<table class="table table-bordered table-hover">
						<thead>
							<th class="text-center">立法名称</th>
						</thead>
						<tbody>
							<c:if test="${legislationPlanTaskList !=null&&fn:length(legislationPlanTaskList)>0}">
								<c:forEach items="${legislationPlanTaskList}" var="doc">
									<tr>
										<td class="text-center">${doc.stFlowId}</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
				</div>
			</div>
			<div class="form-group text-center">
			   <c:if test="${legislationCitymeetingTask.stTaskStatus=='INPUT'}">
				<input type="hidden" id="op" name="op">
				<input type="button" class="btn btn-w-m btn-success" id="btnSave" name="btnSave" onclick="saveAuditMeeting1('save')" value="保存">
				&nbsp;&nbsp;
				<input type="button" class="btn btn-w-m btn-success" id="btnSubmit" name="btnSubmit" onclick="saveAuditMeeting1('submit')" value="确定记要">
				&nbsp;&nbsp;
			   </c:if>
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

		layer.close(layer.index);
		$('#op').val(operation);
		var param = $('#auditMeetingForm').formToJson();
		var stDocSource = "";
		var checkedNum = 0;
		$('[name="stDocSourceCheck"]:checked').each(function() {
			stDocSource = stDocSource + "#" + this.value;
			checkedNum++;
		});
		console.log(stDocSource);
		if (checkedNum > 0) {
			param.stDocSource = stDocSource.substring(1);
		}
		if (param.stTopicName == null || param.stTopicName == "") {
			Duang.error("提示", "请输入议题名称");
		} else if (param.stBak == null || param.stBak == "") {
			Duang.error("提示", "请输入议题说明");
		} else if (param.stBak1 == null || param.stBak1 == "") {
			Duang.error("提示", "请输入材料说明");
		} else if (param.stAddress == null || param.stAddress == "") {
			Duang.error("提示", "请输入会议地点");
		} else if (param.dtBeginDate == null || param.dtBeginDate == "") {
			Duang.error("提示", "请选择会议时间");
		} else if (param.stPersons == null || param.stPersons == "") {
			Duang.error("提示", "请输入会议人员");
		}else if (param.stBak2 == null || param.stBak2 == "") {
			Duang.error("提示", "请输入会议记要");
		} else if (param.stType == null || param.stType == "") {
			Duang.error("提示", "请输入议题结果");
		} else {
			if (operation == 'submit') {
				layer.confirm('请确认操作！', function(index) {
					layer.close(layer.index);
					$.post("${requestUrl}?stNodeId=${nodeId}&method=saveCitymeeting&stTaskStatus=${stTaskStatus}", param, function(data) {
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
				$.post("${requestUrl}?stNodeId=${nodeId}&method=saveCitymeeting&stTaskStatus=${stTaskStatus}", param, function(data) {
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