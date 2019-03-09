<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <span >规章草案发起 </span>
        </li>
    </ul>
    <button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<form id="legislationProcessDocForm" class="form-horizontal"
		  novalidate="novalidate">
		<div class="form-body">
			<div class="form-group">
				<label class="col-sm-3 control-label text-left">法规规章草案：</label>
				<div class="col-sm-9">
					<input type="text" id="docName" name="docName" class="form-control">
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label text-left">备注：</label>
				<div class="col-sm-9">
					<textarea id="stComent" name="stComent" class="form-control"
					></textarea>
				</div>
			</div>
			<div class="form-group text-center">
					<input type="button" class="btn btn-w-m btn-success" id="btnSave"
						   name="btnSave" onclick="saveLegislationProcessDoc()" value="提交"> &nbsp;&nbsp;
					<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="返回">
			</div>
			<div class="form-group">
				<label class="control-label">起草材料
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
                                <td ><span style="color: red">暂未上传</span></td>
                                <td >
                                    <label class="btn btn-w-m btn-success"  onclick="toUploadFile(this)">点击上传</label>
                                    <input id="1" name="upload" type="file" style="display:none"  onchange="uploadFile(this.id,1,'${example.stExampleId}')">
                                </td>
                            </tr>
                        </s:iterator>
                    </tbody>
                </table>
            </div>
            <div class="form-group">
                <label class="control-label">起草其他材料
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
                        <th class="text-center" data-field="id">文件类型</th>
                        <th class="text-center" data-field="district_name">文件名称</th>
                        <th class="text-center" data-field="set">操作</th>
                    </tr>
                    </thead>
                    <tbody id="otherMaterial">

                    </tbody>
                </table>
            </div>
		</div>
	</form>

</div>
<script>
    function saveLegislationProcessDoc() {
        var param=$('#legislationProcessDocForm').formToJson();
        if(param.docName==null||param.docName==""){
            Duang.error("提示","请输入法规规章草案");
        }else if(param.stComent==null||param.stComent==""){
            Duang.error("提示","请输入备注");
        }else {
                $.post("../${requestUrl}?stNodeId=${nodeId}&method=editLegislationProcessDoc",param,function(data){
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
            url: '${basePath}/file/upload.do?stNodeId=NOD_0000000101&stSampleId='+stSampleId,
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
                        +'<label  style="color: red" onclick="deleteAttach(this,1,'+id+',\''+file.fileId+'\')" >删除</label>';
                        $("#"+id).parent().prev().html('<span>'+file.name+'</span>');
                        $("#"+id).parent().html(html);
                    }else{
                        var html='<tr class="text-center">'
                            +'<td class="text-left">需要报送的其他材料</td>'
                            +'<td>'+file.name+'</td>'
                            +'<td><a  target="_blank" href="${basePath}/file/downloadAttach.do?name='+file.name+'&url='+file.url+'">下载</a>&nbsp;&nbsp;'
                            +'<label  style="color: red" onclick="deleteAttach(this,2,'+id+',\''+file.fileId+'\')">删除</label>'
                            +'<input type="hidden" id="'+file.fileId+'"  name="'+file.fileId+'" value='+file.fileId+'>'
                            +'</td></tr>';
                        $('#otherMaterial').append(html);
                    }
                    Duang.info("提示", "上传材料成功");
                } else {
                    Duang.error("提示", "上传材料失败");
                }
            },
            error: function (data, status, e) { //服务器响应失败时的处理函数
                Duang.error("提示", "上传材料失败");
            }
        });
    }
    function deleteAttach(attachObj,type,id,fileId) {
        $.post('${basePath}/file/deleteAttach.do?fileId='+fileId);

        var obj=$(attachObj);
        if(type==1){
            obj.parent().prev().html('<span style="color: red">暂未上传</span>');
            var html= '<label class="btn btn-w-m btn-success"  onclick="toUploadFile(this)">点击上传</label>'
                     +'<input id="'+id+'" type="file" style="display:none"  onchange="uploadFile('+id+',1)">';
            obj.parent().html(html);
        }else{
            obj.parent().parent().remove();
        }
    }
</script>