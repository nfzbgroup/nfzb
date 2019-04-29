<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <span >报审待审批 </span>
        </li>
    </ul>
    <button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<form id="legislationProcessDocForm" class="form-horizontal"
		  novalidate="novalidate">
        <input hidden id="stDocId" name="stDocId" value="${stDocId}">
			
				<c:choose>
					<c:when test="${legislationProcessTask.stTaskStatus=='TODO'}">
						<div class="form-group">
							<label class="col-sm-3 control-label text-left">说明：</label>
							<div class="col-sm-9">
								<textarea id="stComent" name="stComent" class="form-control"
								><c:if test="${legislationProcessTask.stBakOne !=null}">${legislationProcessTask.stBakOne}</c:if></textarea>
							</div>
						</div>
						<div class="form-group">
							<label class="col-sm-3 control-label text-left">送审领导：</label>
							<div class="col-sm-9">
								<textarea id="reportLeadershipReview" name="reportLeadershipReview" class="form-control"
								><c:if test="${legislationProcessTask.stBakTwo !=null}">${legislationProcessTask.stBakTwo}</c:if></textarea>
							</div>
						</div>
					</c:when>
					
					<c:when test="${legislationProcessTask.stTaskStatus==statusCodeArray[1]}">
						<div class="form-group">
							<label class="col-sm-3 control-label text-left">领导意见：</label>
							<div class="col-sm-9">
								<textarea id="stComent" name="stComent" class="form-control"
								><c:if test="${taskDetail.stBak1!=null}">${taskDetail.stBak1}</c:if></textarea>
							</div>
						</div>
					</c:when>
				
				<c:when test="${legislationProcessTask.stTaskStatus==statusCodeArray[2]}">
						<div class="form-group">
							<label class="col-sm-3 control-label text-left">办公厅签报说明：</label>
							<div class="col-sm-9">
								<textarea id="stComent" name="stComent" class="form-control"
								><c:if test="${taskDetail.stBak2!=null}">${taskDetail.stBak2}</c:if></textarea>
							</div>
						</div>
				</c:when>
				
				<c:when test="${legislationProcessTask.stTaskStatus==statusCodeArray[3]}">
						<div class="form-group">
							<label class="col-sm-3 control-label text-left">办公厅反馈说明：</label>
							<div class="col-sm-9">
								<textarea id="stComent" name="stComent" class="form-control"
								><c:if test="${taskDetail.stBak3!=null}">${taskDetail.stBak3}</c:if></textarea>
							</div>
						</div>
					</c:when>
				</c:choose>
			
			<div class="form-group text-center">
					<input type="button" class="btn btn-w-m btn-success" id="btnSave"
						   name="btnSave" onclick="saveLegislationProcessDoc('save')" value="保存"> &nbsp;&nbsp;
						   <input type="button" class="btn btn-w-m btn-success" id="btnSubmit"
						   name="btnSubmit" onclick="saveLegislationProcessDoc('submit')" value="提交"> &nbsp;&nbsp;
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
		</div>
	</form>

</div>
<script>
    function saveLegislationProcessDoc(type) {
        var param=$('#legislationProcessDocForm').formToJson();
        var status= '${legislationProcessTask.stTaskStatus}';
        var statusCodeArray='${statusCodeArray}';
        if(param.stComent==null||param.stComent==""){
        	if(status==statusCodeArray[0]){
        		Duang.error("提示","请输入说明");
        	}else if(status==statusCodeArray[1]){
        		Duang.error("提示","请输入领导意见");
        	}else if(status==statusCodeArray[2]){
        		Duang.error("提示","请输入办公厅签报说明");
        	}else if(status==statusCodeArray[3]){
        		Duang.error("提示","请输入办公厅反馈说明");
        	}
            
            return;
        } else if(status==statusCodeArray[0]&&(param.reportLeadershipReview==null||param.reportLeadershipReview=="")){
        	Duang.error("提示","请输入报审领导");
        	return;
        }
         $.post("${requestUrl}?stNodeId=${nodeId}&method=draftReportAudit&type="+type+"&stTaskStatus=${legislationProcessTask.stTaskStatus}",param,function(data){
         $('#legislationProcessForm').modal('hide');
         submitForm(1);
            });
        
    };
    function toUploadFile(obj) {
        $(obj).next().click();
    }
    function uploadFile(id,type,stSampleId) {
        $.ajaxFileUpload({
            url: '${basePath}/file/upload.do?stNodeId=NOD_0000000190&stSampleId='+stSampleId,
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
                     +'<input id="'+id+'" name="upload" type="file" style="display:none"   onchange="uploadFile(\''+id+'\',1,\''+stSampleId+'\')">';
            obj.parent().html(html);
        }else{
            obj.parent().parent().remove();
        }
    }
</script>