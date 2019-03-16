<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span >规章草案办理 </span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<div class="row" style="margin-bottom: 20px;">
		<div class="col-md-12">
			<label class="btn btn-w-m btn-success"  onclick="openDemonstrationPage(this,'openHandleDemonstrationPage','${stDocId}')">办理</label>
			<label class="btn btn-w-m btn-default"  onclick="openDemonstrationPage(this,'openExpertDemonstrationPage','${stDocId}')">专家论证会</label>
			<label class="btn btn-w-m btn-default"  >网上意见</label>
			<label class="btn btn-w-m btn-default"  onclick="openDemonstrationPage(this,'openLegislationDemonstrationPage','${stDocId}')">立法听证会</label>
			<label class="btn btn-w-m btn-default"  onclick="openDemonstrationPage(this,'openUnitDemonstrationPage','${stDocId}')">单位意见征求</label>
		</div>
	</div>
	<div id="demonstration">

	</div>
</div>
<script>
	$(function () {
        openDemonstrationPage(this,'openHandleDemonstrationPage','${stDocId}');
    })
    function toUploadFile(obj) {
        $(obj).next().click();
    };
    function deleteAttach(attachObj,type,id,fileId,stSampleId) {
        $.post('${basePath}/file/deleteAttach.do?fileId='+fileId);

        var obj=$(attachObj);
        if(type==1){
            obj.parent().prev().html('<span style="color: red">暂未上传</span>');
            var html= '<label class="btn btn-w-m btn-success"  onclick="toUploadFile(this)">点击上传</label>'
                +'<input id="'+id+'" name="upload" type="file" style="display:none"  onchange="uploadFile(\''+id+'\',1,\''+stSampleId+'\')">';
            obj.parent().html(html);
        }else{
            obj.parent().parent().remove();
        }
    }
</script>