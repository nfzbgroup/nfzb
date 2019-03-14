<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span >单位意见征求 > </span>
		</li>
		<li>
			<span>填写意见</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<table class="table table-border table-bordered">
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">对应草案:</label>
			</td>
			<td class="text-center">
				<label >${legislationProcessTask.stFlowId}</label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">类型:</label>
			</td>
			<td class="text-center">
				<label >${legislationProcessTask.stBakTwo}</label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">渠道:</label>
			</td>
			<td class="text-center">
				<label >${legislationProcessTask.stBakTwo}</label>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">单位意见相关材料:</label>
			</td>
			<td class="text-center">
				<c:if test="${legislationFilesList !=null&&fn:length(legislationFilesList)>0}">
					<c:forEach var="file" items="${legislationFilesList}">
						<c:if test="${file.stSampleId !=null&&file.stSampleId !='null'}">
							<label class="control-label col-md-12 text-center"><a  target="_blank" href="${basePath}/file/downloadAttach.do?name=${file.stTitle}&url=${file.stFileUrl}">${file.stTitle}</a></label>
						</c:if>
					</c:forEach>
				</c:if>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">单位意见其他材料:</label>
			</td>
			<td class="text-center">
			<c:if test="${legislationFilesList !=null&&fn:length(legislationFilesList)>0}">
				<c:forEach var="file" items="${legislationFilesList}">
					<c:if test="${file.stSampleId==null||file.stSampleId=='null'}">
						<label class="control-label col-md-12 text-center"><a  target="_blank" href="${basePath}/file/downloadAttach.do?name=${file.stTitle}&url=${file.stFileUrl}">${file.stTitle}</a></label>
					</c:if>
				</c:forEach>
			</c:if>
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">反馈:</label>
			</td>
			<td style="text-align: center">
				<label class="btn btn-success" onclick="toUploadFile(this)">点击上传</label>
				<input id="2" name="upload" type="file" style="display:none"  onchange="uploadFile(this.id)">
			</td>
		</tr>
	</table>
	<form id="unitOpinionForm" class="form-horizontal"
		  novalidate="novalidate" hidden>
		<input hidden name="stTaskId" <c:if test="${legislationProcessTask.stTaskId !=null}">value="${legislationProcessTask.stTaskId}" </c:if>>
		<input hidden name="opinion" id="opinion">
	</form>
	<div class="form-group text-center">
		<input type="button" class="btn btn-w-m btn-success" id="btnSave"
			   name="btnSave" onclick="addOpinion()" value="提交"> &nbsp;&nbsp;
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
</div>

<script>
    $(function () {
        $(".tab-left").css('width', $(window).width() * 0.2)
    });
    function toUploadFile(obj) {
        $(obj).next().click();
    };
    function uploadFile(id) {
        $.ajaxFileUpload({
			url: '${basePath}/file/addOpinion.do?stTaskId=${stTaskId}',
			type: 'post',
			secureuri: false,                       //是否启用安全提交,默认为false
			fileElementId: id,
			dataType: 'JSON',
			success: function (data, status) {        //服务器响应成功时的处理函数
			data = data.replace(/<.*?>/ig, "");  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
			var file = JSON.parse(data);
			if (file.success) {
                var html='<a target="_blank" href="${basePath}/file/downloadAttach.do?name='+file.name+'&url='+file.url+'">下载</a>&nbsp;&nbsp;'
                    +'<label  style="color: red" onclick="deleteAttach(this,\''+id+'\',\''+file.fileId+'\')" >删除</label>';
                $("#opinion").val(file.fileId);
                $("#"+id).parent().html(html);
			Duang.info("提示", "上传材料成功");
                } else {
                    Duang.error("提示", "上传材料失败");
                }
            },
            error: function (data, status, e) { //服务器响应失败时的处理函数
                Duang.error("提示", "上传材料失败");
            }
        });
    };
    function deleteAttach(attachObj,id,fileId) {
        $.post('${basePath}/file/deleteAttach.do?fileId='+fileId);
        var obj=$(attachObj);
		var html= '<label class="btn btn-w-m btn-success"  onclick="toUploadFile(this)">点击上传</label>'
			+'<input id="'+id+'" name="upload" type="file" style="display:none"  onchange="uploadFile(\''+id+'\')">';
        $("#opinion").val("");
		obj.parent().html(html);
    };
    function addOpinion() {
        var param=$('#unitOpinionForm').formToJson();
        if(param.opinion==null||param.opinion==""){
            Duang.error("提示","请上传反馈意见");
        }else {
            $.post("../${requestUrl}?stNodeId=${nodeId}&method=addOpinion",param,function(data){
                $('#legislationProcessForm').modal('hide');
                submitForm(1);
            });
        }
    }
</script>