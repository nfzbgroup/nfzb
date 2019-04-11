<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <span >审核会议发起 </span>
        </li>
    </ul>
    <button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<form id="legislationcheckMeetingForm" class="form-horizontal"
		  novalidate="novalidate">
        <input hidden id="stMeetingId" name="stDocId" value="${stMeetingId}">
			<div class="form-group">
				<label class="col-sm-3 control-label text-left">会议名称：</label>
				<div class="col-sm-9">
					<textarea id="stMeetingName" name="stMeetingName" class="form-control"
					><c:if test="${legislationCheckMeeting.stMeetingName!=null}">${legislationCheckMeeting.stMeetingName}</c:if></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label text-left">主题：</label>
				<div class="col-sm-9">
					<textarea id="stTopic" name="stTopic" class="form-control"
					><c:if test="${legislationCheckMeeting.stTopic !=null}">${legislationCheckMeeting.stTopic}</c:if></textarea>
				</div>
			</div>

			<div class="form-group">
				<label class="col-md-3 control-label">会议时间:</label>
				<div class="col-md-9">
					<div class="input-group input-large">
						<input type="text" class="form-control" readonly id="dtBeginDate" name="dtBeginDate">
						<span class="input-group-addon"></span> 
						<input type="text" class="form-control" readonly id="dtEndDate"name="dtEndDate">
					</div>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label text-left">会议地点：</label>
				<div class="col-sm-9">
					<textarea id="stAddress" name="stAddress" class="form-control"
					><c:if test="${legislationCheckMeeting.stAddress !=null}">${legislationCheckMeeting.stAddress}</c:if></textarea>
				</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-3 control-label text-left">参会人员：</label>
				<div class="col-sm-9">
					<textarea id="stPersons" name="stPersons" class="form-control"
					><c:if test="${legislationCheckMeeting.stPersons !=null}">${legislationCheckMeeting.stPersons}</c:if></textarea>
				</div>
			</div>
			
			
		<div class="form-group text-center">
					<input type="button" class="btn btn-w-m btn-success" id="btnSave"
						   name="btnSave" onclick="saveLegislationCheckMeeting('save')" value="保存"> &nbsp;&nbsp;
						   <input type="button" class="btn btn-w-m btn-success" id="btnSubmit"
						   name="btnSubmit" onclick="saveLegislationCheckMeeting('submit')" value="提交"> &nbsp;&nbsp;
					<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
			</div>
			
            <div class="form-group">
                <label class="control-label">报审材料上传
                </label>
                <label class="btn btn-w-m btn-success" onclick="toUploadFile(this)">点击上传
                </label>
                <input  type="file" id="7" name="upload" style="display:none"  onchange="uploadFile(this.id,2,null)">
            </div>
            <div class="form-group">
                <table class="table table-striped table-hover"
                       data-toggle="table"
                       data-mobile-responsive="true"
                       data-card-view = "true"
                       data-pagination="true">
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
                                            <a  target="_blank" href="${basePath}/file/downloadAttach.do?name=${file.stTitle}&url=${file.stFileUrl}">下载</a>&nbsp;&nbsp;
                                            <label  style="color: red" onclick="deleteAttach(this,2,null,'${file.stFileId}',null)">删除</label>
                                            <input type="hidden" id="${file.stFileId}"  name="${file.stFileId}" value="${file.stFileId}">
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
<script src="${basePath}/legislation/assets/js/plugins/layer/layer.min.js?v=2.0"></script>
<link href="${basePath}/legislation/assets/css/plugins/layout/css/layout.min.css" rel="stylesheet" type="text/css"/>
<script>
    function saveLegislationCheckMeeting(type) {
        var param=$('#legislationcheckMeetingForm').formToJson();
        if(param.stMeetingName==null||param.stMeetingName==""){
            Duang.error("提示","请输入会议名称");
        }else if(param.stTopic==null||param.stTopic==""){
            Duang.error("提示","请输入主题");
        }
       /*  else if(param.dtBeginDate==null||param.dtBeginDate==""||param.dtEndDate==null||param.dtEndDate==""){
            Duang.error("提示","请输入会议时间");
        } */
        else if(param.stAddress==null||param.stAddress==""){
            Duang.error("提示","请输入会议地点");
        }else if(param.stPersons==null||param.stPersons==""){
            Duang.error("提示","请输入参会人员");
        }else {
                $.post("${requestUrl}?stNodeId=${nodeId}&method=checkMeetingStart&type="+type,param,function(data){
                $('#legislationProcessForm').modal('hide');
                submitForm(1);
            });
        }
    };
    function toUploadFile(obj) {
        $(obj).next().click();
    }
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
    }
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