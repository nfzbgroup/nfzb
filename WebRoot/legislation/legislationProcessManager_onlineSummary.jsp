<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <span >网上征求意见 > </span>
        </li>
        <li>
            <span >汇总 </span>
        </li>
    </ul>
    <button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
<form id="onlineSummaryForm" class="form-horizontal"
      novalidate="novalidate">
    <input hidden name="stTaskId" id="stTaskId" value="${legislationProcessTask.stTaskId}">
    <input hidden name="stDocId" id="stDocId" value="${legislationProcessTask.stDocId}">
    <input hidden  id="nodeId" value="${nodeId}">
    <input hidden  id="buttonId" value="${buttonId}">
    <div class="form-body">
        <div class="form-group">
            <label class="col-sm-3 control-label">法规规章草案：</label>
            <label class="col-sm-5 control-label control-label-left">${legislationProcessTask.stFlowId}</label>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">经办处室：</label>
            <label class="col-sm-5 control-label control-label-left">${legislationProcessTask.stTeamName}</label>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">经办处意见：</label>
            <label class="col-sm-5 control-label control-label-left">${legislationProcessTask.stComment2}</label>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">办领导意见：</label>
            <label class="col-sm-5 control-label control-label-left">${legislationProcessTask.stComment1}</label>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">法治调研处意见：</label>
            <label class="col-sm-5 control-label control-label-left">${legislationProcessTask.stBakOne}</label>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">征求意见开始时间：</label>
            <label class="col-sm-5 control-label control-label-left"><fmt:formatDate value="${legislationProcessTask.dtBakDate}"/></label>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">征求意见结束时间：</label>
            <label class="col-sm-5 control-label control-label-left"><fmt:formatDate value="${legislationProcessTask.dtDeadDate}"/></label>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">征求意见汇总情况：</label>
            <label class="col-sm-5 control-label control-label-left">门户网站征询平台(反馈意见数)<input type="number" name="stBakTwo" style="width: 50px" <c:if test="${legislationProcessTask.stBakTwo !=null}">value="${legislationProcessTask.stBakTwo}" </c:if>>条。</label>
        </div>
        <div class="form-group">
            <label class="control-label">网上征求意见采纳情况材料
            </label>
        </div>
        <div class="form-group">
            <table class="table table-striped table-bordered table-hover"
                   data-toggle="table"
                   data-mobile-responsive="true"
                   data-card-view = "true"
                   data-pagination="true">
                <thead>
                <tr class="text-center">
                    <th class="text-center" data-field="id">文件类型</th>
                    <th class="text-center" data-field="district_name">文件名称</th>
                    <th class="text-center" data-field="set">操作</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="#request.LegislationExampleList" var="example">
                    <tr class="text-center">
                        <td class="text-left">${example.stExampleName}
                            <c:if test="${example.stNeed=='NEED'}">
                                <span style="color: red">(必须上传)</span>
                            </c:if>
                            <span style="color: dodgerblue">(范本)</span>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${example.fileId !=null}">
                                    <span >${example.fileName}</span>
                                </c:when>
                                <c:otherwise>
                                    <span style="color: red">暂未上传</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td >
                            <c:choose>
                                <c:when test="${example.fileId !=null}">
                                    <a target="_blank" href="${basePath}/file/downloadAttach.do?name=${example.fileName}&url=${example.fileUrl}">下载</a>&nbsp;&nbsp;
                                    <input type="hidden" id="${example.fileId}"  name="${example.fileId}" value="${example.fileId}">
                                    <label style="color: red" onclick="deleteAttach(this,1,'${example.stExampleId}','${example.fileId}','${example.stExampleId}')" >删除</label>
                                </c:when>
                                <c:otherwise>
                                    <label class="btn btn-w-m btn-success"  onclick="toUploadFile(this)">点击上传</label>
                                    <input id="${example.stExampleId}" name="upload" type="file" style="display:none"  onchange="uploadFile(this.id,1,'${example.stExampleId}')">
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
        <div class="form-group text-center">
            <input type="button" class="btn btn-w-m btn-success"
                   name="btnSave"  value="保存" onclick="saveOnlineSummary()"> &nbsp;&nbsp;
            <input type="button" class="btn btn-w-m btn-success" id="nextStep"
                    onclick="nextSummaryStep()" <c:if test="${summaryDisabled}">disabled="disabled"</c:if> value="下一步"> &nbsp;&nbsp;
            <input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
        </div>
    </div>
</form>
</div>
<script>
    function uploadFile(id,type,stSampleId) {
        $.ajaxFileUpload({
            url: '${basePath}/file/upload.do?stNodeId=${nodeId}&stSampleId='+stSampleId,
            type: 'post',
            secureuri: false,                       //是否启用安全提交,默认为false
            fileElementId: id,
            dataType: 'JSON',
            success: function (data, status) {        //服务器响应成功时的处理函数
                data = data.replace(/<.*?>/ig, "");  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
                var file = JSON.parse(data);
                if (file.success) {
                    if(type==1){
                        var html='<a target="_blank" href="${basePath}/file/downloadAttach.do?name='+file.name+'&url='+file.url+'">下载</a>&nbsp;&nbsp;'
                            +'<input type="hidden" id="'+file.fileId+'"  name="'+file.fileId+'" value='+file.fileId+'>'
                        +'<label  style="color: red" onclick="deleteAttach(this,1,\''+id+'\',\''+file.fileId+'\',\''+stSampleId+'\')" >删除</label>';
                        $("#"+id).parent().prev().html('<span>'+file.name+'</span>');
                        $("#"+id).parent().html(html);
                    }else{
                        var html='<tr class="text-center">'
                            +'<td class="text-left">需要报送的其他材料</td>'
                            +'<td>'+file.name+'</td>'
                            +'<td><a  target="_blank" href="${basePath}/file/downloadAttach.do?name='+file.name+'&url='+file.url+'">下载</a>&nbsp;&nbsp;'
                            +'<label  style="color: red" onclick="deleteAttach(this,2,\''+id+'\',\''+file.fileId+'\',\''+stSampleId+'\')">删除</label>'
                            +'<input type="hidden" id="'+file.fileId+'"  name="'+file.fileId+'" value='+file.fileId+'>'
                            +'</td></tr>';
                        $('#otherMaterial').append(html);
                    }
                    Duang.success("提示", "上传材料成功");
                } else {
                    Duang.error("提示", "上传材料失败");
                }
            },
            error: function (data, status, e) { //服务器响应失败时的处理函数
                Duang.error("提示", "上传材料失败");
            }
        });
    };
    function saveOnlineSummary() {
        var param=$('#onlineSummaryForm').formToJson();
        if(param.stBakTwo==null||param.stBakTwo==""){
            Duang.error("提示","请输入反馈意见数");
        }else if(param.stBakTwo<0){
            Duang.error("提示","请输入正确的反馈意见数");
        }else{
            $.post("../${requestUrl}?method=saveOnlineSummary",param,function(data){
                if(data.success){
                    $('#processIndexForm').modal('hide');
                    Duang.success("提示","操作成功");
                }else{
                    Duang.error("提示","操作失败");
                }
            });
        }
    };
    function nextSummaryStep() {
        if($('#nextStep').attr('disabled')==undefined){
            var param=$('#onlineSummaryForm').formToJson();
            if(param.stBakTwo==null||param.stBakTwo==""){
                Duang.error("提示","请输入反馈意见数");
            }else if(param.stBakTwo<0){
                Duang.error("提示","请输入正确的反馈意见数");
            }else{
                $.post("../${requestUrl}?method=saveOnlineSummary",param,function(data){
                    if(data.success){
                        var stDocId=$('#stDocId').val();
                        var nodeId=$('#nodeId').val();
                        var buttonId=$('#buttonId').val();
                        nextChildDemonstrationProcess(stDocId,nodeId,'nextChildProcess',buttonId);
                    }else{
                        Duang.error("提示","操作失败");
                    }
                });
            }
        }
    }
</script>