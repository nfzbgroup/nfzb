<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link href="${basePath}/legislation/assets/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/animate.min.css" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/style.min.css?v=4.0.0" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/plugins/toastr/toastr.min.css" rel="stylesheet">
<link href="${basePath}/legislation/assets/plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/myUtil.css" rel="stylesheet">
<table class="table">
	<tbody>
	<tr>
		<td>
			<label id="unitEdit" class="${unitEditClass}" <c:if test="${unitEditDisabled}">disabled="disabled" </c:if> onclick="openDemonstrationPage(this.id,'openUnitDemonstrationPage','${stDocId}','NOD_0000000120','单位意见征求 > 内容添加')">部门征求意见编辑</label>
		</td>
		<%--<td>--%>
			<%--<label id="unitSeal" class="btn btn-default btn-rounded process-btn" disabled="disabled">部门征求意见盖章</label>--%>
		<%--</td>--%>
		<td>
			<label id="unitSend" class="${unitSendClass}" <c:if test="${unitSendDisabled}">disabled="disabled" </c:if> onclick="openDemonstrationPage(this.id,'openUnitSeekPage','${stDocId}','NOD_0000000121','单位意见征求')">部门征求意见发送部门</label>
		</td>
		<td>
			<label id="unitReceive" class="${unitReceiveClass}" <c:if test="${unitReceiveDisabled}">disabled="disabled" </c:if> onclick="openDemonstrationPage(this.id,'openUnitReceivePage','${stDocId}','NOD_0000000120','单位意见征求 > 接收情况')">部门征求意见接受反馈</label>
		</td>
	</tr>
	<tr>
		<td>
			<label class="btn btn-default process-btn">网上征求意见发起</label>
		</td>
		<td>
			<label class="btn btn-default process-btn">网上征求意见接收</label>
		</td>
		<td>
			<label class="btn btn-default process-btn">网上征求意见送审</label>
		</td>
		<td>
			<label class="btn btn-default process-btn">网上征求意见上网发布</label>
		</td>
		<td>
			<label class="btn btn-default process-btn">网上意见汇总</label>
		</td>
		<td>
			<label class="btn btn-default process-btn">采纳意见发布</label>
		</td>
	</tr>
	<tr>
		<td>
			<label class="btn btn-default process-btn">专家论证会发起</label>
		</td>
		<td>
			<label class="btn btn-default process-btn">专家论证会结果归档</label>
		</td>
	</tr>
	<tr>
		<td>
			<label class="btn btn-default process-btn">立法听证会发起</label>
		</td>
		<td>
			<label class="btn btn-default process-btn">立法听证会接收</label>
		</td>
		<td>
			<label class="btn btn-default process-btn">立法听证会送审</label>
		</td>
		<td>
			<label class="btn btn-default process-btn">立法听证会上网发布</label>
		</td>
		<td>
			<label class="btn btn-default process-btn">立法听证会网上报名</label>
		</td>
		<td>
			<label class="btn btn-default process-btn">立法听证会结果归档</label>
		</td>
	</tr>
	<tr>
		<td>
			<label class="btn btn-default process-btn">部门会签编辑</label>
		</td>
		<td>
			<label class="btn btn-default process-btn">部门会签盖章</label>
		</td>
		<td>
			<label class="btn btn-default process-btn">部门会签发起发送部门</label>
		</td>
		<td>
			<label class="btn btn-default process-btn">部门会签结果接收反馈</label>
		</td>
	</tr>
	</tbody>
</table>
<div class="modal inmodal fade" id="processIndexForm" data-backdrop tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true">
	<div class="modal-dialog" style="width: 800px">
		<div class="modal-content">
		</div>
	</div>
</div>
<script src="${basePath}/legislation/assets/js/jquery.min.js?v=2.1.4"></script>
<script src="${basePath}/legislation/assets/js/bootstrap.min.js?v=3.3.5"></script>
<script src="${basePath}/legislation/assets/js/plugins/toastr/toastr.min.js"></script>
<script src="${basePath}/legislation/assets/plugins/laydate/laydate.js"></script>
<script src="${basePath}/legislation/assets/js/content.min.js?v=1.0.0"></script>
<link href="${basePath}/legislation/assets/page/common.css" rel="stylesheet" type="text/css" />
<script src="${basePath}/legislation/assets/page/page.js" type="text/javascript"></script>
<script src="${basePath}/legislation/assets/page/common.js" type="text/javascript"></script>
<script src="${basePath}/legislation/assets/util/util.js" type="text/javascript"></script>
<script src="${basePath}/legislation/assets/js/plugins/layer/layer.min.js?v=2.0"></script>
<script src="${basePath}/legislation/assets/util/ajaxfileupload.js" type="text/javascript"></script>
<link href="${basePath}/legislation/assets/css/plugins/layout/css/layout.min.css" rel="stylesheet" type="text/css"/>
<script>
    $(function () {
        $(".process-btn").css('width', '170px');
        $(".table>tbody>tr>td").css('border', '0px solid #e7eaec');
        $(".table>tbody>tr>td").css('padding-top', '30px');
        $('#processIndexForm').on('hide.bs.modal', '.modal', function () {
            $(this).removeData('bs.modal');
        });
        $('#processIndexForm').on('show.bs.modal', function () {
            $('.modal .modal-body').css('overflow-y', 'auto');
            $('.modal .modal-body').css('max-height', $(window).height());
        });
    });
    function openProMeetPage(method,stDocId,buttonStatus) {
        $("#processIndexForm").modal({
            remote: "${basePath}/legislationProcessDoc/draft_doc_info.do?stNodeId=${nodeId}&method="+method+"&stDocId="+stDocId+"&stTaskStatus="+buttonStatus
        });
    };

    function openTaskPageWithStatus(method,stTaskId,buttonStatus,stDocId,stNodeId) {
        $("#processIndexForm").modal({
            remote: "${basePath}/legislationProcessDoc/draft_doc_info.do?stNodeId=${nodeId}&method="+method+"&stTaskId="+stTaskId+"&stTaskStatus="+buttonStatus+"&stDocId="+stDocId+"&stNodeId="+stNodeId
        });
    };
    function openDemonstrationPage(buttonId,method,stDocId,stNodeId,name) {
		if($('#'+buttonId).attr("disabled")==undefined){
            $("#processIndexForm").modal({
                remote: "${basePath}/legislationProcessDoc/draft_doc_info.do?buttonId="+buttonId+"&method="+method+"&stDocId="+stDocId+"&stNodeId="+stNodeId
            });
            <%--layer.open({--%>
                <%--type: 2,--%>
                <%--title: name,--%>
                <%--shadeClose: true,--%>
                <%--shade: 0.8,--%>
                <%--area: ['90%', '90%'],--%>
                <%--content: "${basePath}/legislationProcessDoc/draft_doc_info.do?buttonId="+buttonId+"&method="+method+"&stDocId="+stDocId+"&stNodeId="+stNodeId //iframe的url--%>
            <%--});--%>
		}
    };
    function uploadReport1(stDocId,stNodeId,buttonId) {
        $.post("../legislationProcessTask/uploadReport.do?stDocId="+stDocId+"&stNode="+stNodeId,
            function (data) {
                if(data.success) {
                    nextProcess1(stDocId,stNodeId,"nextProcess",buttonId);
                }else{
                    Duang.error("提示", "请补全必填材料！");
                }
            },
            "json")
    };
    function nextProcess1(stDocId,stNodeId,method,buttonId) {
        layer.confirm('请确认操作！',function(index){
            layer.close(layer.index);
            $.post("../legislationProcessTask/nextProcess.do?stDocId="+stDocId+"&stNodeId="+stNodeId,function(data){
                if(data.success){
                    $('#processIndexForm').modal('hide');
                    $('#'+buttonId).attr("class","btn btn-primary process-btn");
                    $('#'+buttonId).parent().next().children().attr("class","btn btn-warning process-btn");
                    if(data.removeDisabled){
                        $('#'+buttonId).parent().next().children().disabled=false;
                    }
                    Duang.success("提示","操作成功");
                }else{
                    Duang.error("提示","操作失败");
                }
            });
        });
    };
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