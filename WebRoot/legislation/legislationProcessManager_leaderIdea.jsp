<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <span >正式文本说明</span>
        </li>
    </ul>
	<button style="padding-right: 10px;padding-top: 8px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></div>
</div>
<div class="modal-body">
  <h2 style="color: #E4243D; text-align: center; font-weight: bold; margin-bottom: 20px">正式文本准备</h2>
	<form id="legislationTaskCheckForm" class="form-horizontal"
		  novalidate="novalidate">
        <input hidden name="stDocId" value="${stDocId}">

        <div class="form-body">

		<%-- 	<div class="form-group">
			      <div class="form-group text-center">
					<label class="col-sm-3 control-label text-center">法规规章草案：</label>
					<label class="col-sm-6 control-label" style="text-align: left;">
						<span style="font-size: 18px;">${legislationProcessDoc.stDocName}</span>
					</label>
				   </div>
				<label class="col-sm-2 control-label text-left">正式文本说明：</label>
				<div class="col-sm-10">
					<textarea id="stComent" name="stComent" class="form-control"></textarea>
				</div>
			</div>  --%> <div class="form-group">
							<div class="form-group text-center">
									<label class="col-sm-3 control-label text-left">法规规章草案：</label>
									<label class="col-sm-6 control-label" style="text-align: left;">
										<span style="font-size: 18px;">${legislationProcessDoc.stDocName}</span>
									</label>
							</div>
							<label class="col-sm-3 control-label text-left">正式文本说明：</label>
							<div class="col-sm-7">
									<textarea id="stComent" name="stComent" class="form-control form_control"></textarea>
							</div>
						    </div>
                         
            <div class="form-group">
                <label class="control-label">附件上传
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
                    </tbody>
                </table>
            </div>
            <div class="form-group text-center">
                <input ${strDisplay} type="button"  class="btn btn-w-m btn-success" id="btnSave"
                       name="btnSave" onclick="saveTaskCheck('${buttonStatus}')" value="提交"> &nbsp;&nbsp;
                <input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="返回">
            </div>
		</div>
	</form>

</div>
<script>
    function saveTaskCheck() {
        var param=$('#legislationTaskCheckForm').formToJson();
        if(param.stComent==null||param.stComent==""){
            Duang.error("提示","请输入正式文本说明！");
        }else {
                $.post("../${requestUrl}?stTaskId=${stTaskId}&method=dealFinish",param,function(data){
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
            url: '${basePath}/file/upload.do?stNodeId=${stNodeId}',
            type: 'post',
            secureuri: false,                       //是否启用安全提交,默认为false
            fileElementId: id,
            dataType: 'JSON',
            success: function (data, status) {        //服务器响应成功时的处理函数
                data = data.replace(/<.*?>/ig, "");  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
                var file = JSON.parse(data);
                if (file.success) {
                        var html='<tr class="text-center">'
                            +'<td>'+file.name+'</td>'
                            +'<td><a  target="_blank" href="${basePath}/file/downloadAttach.do?name='+file.name+'&url='+file.url+'">下载</a>&nbsp;&nbsp;'
                            +'<label  style="color: red" onclick="deleteAttach(this,2,\''+id+'\',\''+file.fileId+'\',\''+stSampleId+'\')">删除</label>'
                            +'<input type="hidden" id="'+file.fileId+'"  name="'+file.fileId+'" value='+file.fileId+'>'
                            +'</td></tr>';
                        $('#otherMaterial').append(html);
                    Duang.success("提示", "上传附件成功");
                } else {
                    Duang.error("提示", "上传附件失败");
                }
            },
            error: function (data, status, e) { //服务器响应失败时的处理函数
                Duang.error("提示", "上传附件失败");
            }
        });
    }
    function deleteAttach(attachObj,type,id,fileId,stSampleId) {
        $.post('${basePath}/file/deleteAttach.do?fileId='+fileId);

        var obj=$(attachObj);
            obj.parent().parent().remove();
    }
</script>