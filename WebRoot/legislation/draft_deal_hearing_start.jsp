<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

</head>

<body class="gray-bg">										
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span>立法听证会</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal">
		<span aria-hidden="true">&times;</span>
		<span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">
	<h2 style="color: #E4243D; text-align: center; font-weight: bold; margin-bottom: 20px">立法听证会s</h2>
	<form id="onlineDemonstrationForm" class="form-horizontal" novalidate="novalidate">
		<input type="hidden" name="stTaskId" id="stTaskId" value="${legislationProcessTask.stTaskId}">
		<input type="hidden" name="stDocId" value="${legislationProcessDoc.stDocId}">
		<input type="hidden" id="buttonId" value="${buttonId}">
		<div class="form-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">法规规章草案：</label>

				<label class="col-sm-9 control-label" style="text-align: left;">
					<span style="font-size: 18px;">${legislationProcessDoc.stDocName}</span>
				</label>
			</div>
				<div class="form-group">
				<label class="col-sm-3 control-label">发起时间：</label>
				<div class="col-sm-9">
				<div class="input-group input-large">
					<input type="text" class="form-control"  id="dtBeginDate" name="dtBakDate" value="<fmt:formatDate value="${legislationProcessTask.dtBakDate}"  pattern="yyyy-MM-dd"/>">
				</div>
				</div>
			   </div>	
					
					
			<div class="form-group">
			    <label class="col-sm-3 control-label">地点：</label>
				<div class="col-sm-9">
					<input class="form-control"  name="stBakOne" value="${legislationProcessTask.stBakOne}"/>
				</div></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">参与人员：</label>
				<div class="col-sm-9">
					<input class="form-control" name="stBakTwo"  value="${legislationProcessTask.stBakTwo}"/>
				</div></div>
			<div class="form-group">
				<label class="col-sm-3 control-label">立法听证会说明：</label>
				<div class="col-sm-9">
					<textarea class="form-control" name="stComment2">${legislationProcessTask.stComment2}</textarea>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label">立法听证会材料 </label>
			</div>
			<%@include file="/legislation/file/attachUpload.jsp" %>
			<div class="form-group text-center">
			 <c:if test="${legislationProcessTask.stTaskStatus=='TODO'||legislationProcessTask==null}">
				<input   type="button" class="btn btn-w-m btn-success" id="btnSave" name="btnSave" value="保存" onclick="saveLegislationDemonstration()">
				&nbsp;&nbsp;
				<input  type="button" class="btn btn-w-m btn-success" onclick="confirmOnlineReport('${stDocId}','${nodeId}','${buttonId}')" value="提交">
				&nbsp;&nbsp;
				 </c:if>
				<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
			</div>
		</div>
	</form>
</div>
<script type="text/javascript">
$(function() {
	laydate.render({
		elem : '#dtBeginDate',
		format : 'yyyy-MM-dd',
		calendar : true,
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
	function saveLegislationDemonstration() {
		var param = $('#onlineDemonstrationForm').formToJson();
		if (param.stBakOne == null || param.stBakOne == "") {
			Duang.error("提示", "请输入立法听证会地点");
		} if (param.stBakTwo == null || param.stBakTwo == "") {
			Duang.error("提示", "请输入立法听证会参与人员");
		}if (param.stComment2 == null || param.stComment2 == "") {
			Duang.error("提示", "请输入立法听证会说明");
		}else {
			$.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationDemonstration", param, function(data) {
				if (data.success) {
					$('#processIndexForm').modal('hide');
					//var buttonId=$('#buttonId').val();
					$('#${nodeId}').parent().removeClass('bcg_gray').removeClass('bcg_blue').removeClass('bcg_green').addClass('bcg_green');
					Duang.success("提示", "操作成功");
					//location.reload();
				} else {
					Duang.error("提示", "操作失败");
				}
			});
		}
	};
	function confirmOnlineReport(stDocId, nodeId, buttonId) {
		var param = $('#onlineDemonstrationForm').formToJson();
		if (param.stComment2 == null || param.stComment2 == "") {
			Duang.error("提示", "请输入经办部门意见");
		} else {
			$.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationDemonstration", param, function(data) {
				if (data.success) {
					uploadDemonstrationReport(stDocId, nodeId, buttonId);
				} else {
					Duang.error("提示", "操作失败");
				}
			});
		}
	}

	
</script>
</body>

</html>