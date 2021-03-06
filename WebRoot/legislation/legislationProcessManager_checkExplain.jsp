<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <span >提交说明 </span>
        </li>
    </ul>
    <button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<form id="legislationTaskCheckForm" class="form-horizontal"
		  novalidate="novalidate">
        <input hidden name="stTaskId" value="${stTaskId}">
        <input hidden id="stTaskStatus" name="stTaskStatus" value="${stTaskStatus}">
        <input hidden name="stDocId" value="${stDocId}">
        <input hidden name="stNodeId" value="${nodeId}">

        <div class="form-body">

			<div class="form-group">
				<label class="col-sm-3 control-label text-left">情况说明：</label>
				<div class="col-sm-9">
					<textarea id="stComent" name="stComent" class="form-control"></textarea>
				</div>
			</div>
            <c:if test="${nodeId=='NOD_0000000131'&&stTaskStatus=='SEND'}">
                <div class="form-group">
                    <label class="col-sm-3 control-label text-left">办领导意见：</label>
                    <div class="col-sm-9">
                        <textarea  name="stComment1" class="form-control"></textarea>
                    </div>
                </div>
            </c:if>
            <c:if test="${nodeId=='NOD_0000000131'&&stTaskStatus=='PUBLISH'}">
                <div class="form-group">
                    <label class="col-sm-3 control-label text-left">法治调研处意见：</label>
                    <div class="col-sm-9">
                        <textarea  name="stBakOne" class="form-control"></textarea>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">征求意见开始时间：</label>
                    <div class="col-sm-9">
                        <input type="text"  class="form-control" readonly id="dtBakDate" name="dtBakDate" >
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label">征求意见结束时间：</label>
                    <div class="col-sm-9">
                        <input type="text"  class="form-control" readonly id="dtDeadDate" name="dtDeadDate" >
                    </div>
                </div>
            </c:if>
            <div class="form-group">
                <label class="control-label">附件上传
                </label>
                <label class="btn btn-w-m btn-success" onclick="toUploadFile(this)">点击上传
                </label>
                <input  type="file" id="7" name="upload" style="display:none"  onchange="uploadFile(this.id)">
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
                <input type="button" class="btn btn-w-m btn-success" id="btnSave"
                       name="btnSave" onclick="saveTaskCheck()" value="提交"> &nbsp;&nbsp;
                <input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
            </div>
		</div>
	</form>

</div>
<script>
    $(function () {
        laydate.render({
            elem: '#dtBakDate',
            format:'yyyy-MM-dd',
            calendar: true,
        });
        laydate.render({
            elem: '#dtDeadDate',
            format:'yyyy-MM-dd',
            calendar: true,
        });
    });
    function saveTaskCheck() {
        var param=$('#legislationTaskCheckForm').formToJson();
        if(param.stComent==null||param.stComent==""){
            Duang.error("提示","请输入情况说明！");
        }else if(param.stNodeId=='NOD_0000000131'&&param.stTaskStatus=='SEND'&&(param.stComment1==null||param.stComment1=="")){
            Duang.error("提示","请输入办领导意见！");
        }else if(param.stNodeId=='NOD_0000000131'&&param.stTaskStatus=='PUBLISH'&&(param.stBakOne==null||param.stBakOne=="")){
            Duang.error("提示","请输入法治调研处意见！");
        }else if(param.stNodeId=='NOD_0000000131'&&param.stTaskStatus=='PUBLISH'&&(param.dtBakDate==null||param.dtBakDate=="")){
            Duang.error("提示","请选择征求意见开始时间！");
        }else if(param.stNodeId=='NOD_0000000131'&&param.stTaskStatus=='PUBLISH'&&(param.dtDeadDate==null||param.dtDeadDate=="")){
            Duang.error("提示","请选择综征求意见结束时间！");
        }else{
             $.post("../${requestUrl}?stTaskId=${stTaskId}&method=saveTaskCheck&stTaskStatus=${stTaskStatus}",param,function(data){
                 var stTaskStatus=$('#stTaskStatus').val();
                 if("PUBLISH"==stTaskStatus||"GATHER-RETURN"==stTaskStatus){
                     $('#processIndexChildForm').modal('hide');
                     $('#'+stTaskStatus).attr("disabled","disabled");
                     $('#nextStep').removeAttr('disabled');
                 }else if("SEND"==stTaskStatus||"SEND-RETURN"==stTaskStatus){
                     $('#processIndexChildForm').modal('hide');
                     $('#'+stTaskStatus).attr("disabled","disabled");
                     var disabledNumber=Number($('#disabledNumber').val());
                     $('#disabledNumber').val(disabledNumber+1);
                     var disabledNumberLater=Number($('#disabledNumber').val());
                     if(disabledNumberLater==2){
                         $('#nextStep').removeAttr('disabled');
                     }
                 }else {
                     $('#legislationProcessForm').modal('hide');
                     submitForm(1);
                 }
                 Duang.success("提示","操作成功");
            });
        }
    };
    function toUploadFile(obj) {
        $(obj).next().click();
    }
    function uploadFile(id) {
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
                            +'<label  style="color: red" onclick="deleteAttach(this,\''+file.fileId+'\')">删除</label>'
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
    function deleteAttach(attachObj,fileId) {
        $.post('${basePath}/file/deleteAttach.do?fileId='+fileId);
        var obj=$(attachObj);
        obj.parent().parent().remove();
    }
</script>