<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="modal-body">
	<form id="exampleFileForm" class="form-horizontal"
		  novalidate="novalidate">
		<input type="hidden" name="stNodeId" value="${nodeId}">
		<input hidden name="stExampleId" <c:if test="${legislationExample.stExampleId !=null}">value="${legislationExample.stExampleId}" </c:if>>
		<div class="form-body">
			<div class="form-group">
				<label class="col-sm-3 control-label">样本名称：</label>
				<div class="col-sm-9">
					<input class="form-control" name="stExampleName" <c:if test="${button=='info'}">disabled</c:if> <c:if test="${legislationExample.stExampleName !=null}">value="${legislationExample.stExampleName}" </c:if>>
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">是否必须：</label>
				<div class="col-sm-9">
					<select  class="form-control" name="stNeed" <c:if test="${button=='info'}">disabled</c:if>>
						<option value="NEED" <c:if test="${legislationExample.stNeed!=null&&legislationExample.stNeed=='NEED'}">selected</c:if>>必须</option>
						<option value="NONEED" <c:if test="${legislationExample.stNeed!=null&&legislationExample.stNeed=='NONEED'}">selected</c:if>>非必须</option>
					</select>
				</div>
			</div>

			<div class="form-group text-center">
				<c:if test="${button !='info'}">
					<input type="button" class="btn btn-w-m btn-success"  value="保存" onclick="saveExampleFile()"> &nbsp;&nbsp;
				</c:if>
				<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
			</div>
			<div class="form-group">
				<label class="control-label">样本材料
				</label>
				<label class="btn btn-w-m btn-success" onclick="toUploadFile(this)" id="uploadButton" <c:if test="${button=='info'||legislationExample.stFileNo !=null}">style="display:none"</c:if>>点击上传
				</label>
				<input type="file" id="upload" name="upload" style="display:none"  onchange="uploadFile(this)">
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
						<c:if test="${legislationExample.stFileNo !=null}">
							<tr class="text-center">
								<td>样本材料</td>
								<td><c:if test="${legislationExample.stFileNo !=null}">${legislationExample.stFileNo}</c:if></td>
								<td>
									<a target="_blank" href="${basePath}/file/downloadSample.do?stExampleId=${legislationExample.stExampleId}">下载</a>
									<c:if test="${button=='edit'}">
										<label  style="color: red;cursor: pointer" onclick="deleteAttach(this)">删除</label>
									</c:if>
									<input type="hidden" name="stFileNo" value="${legislationExample.stFileNo}">
								</td>
							</tr>
						</c:if>
					</tbody>
				</table>
			</div>
		</div>
	</form>
</div>

<script>
    function toUploadFile(obj) {
        $(obj).next().click();
    };
    function uploadFile(file) {
		var html='<tr class="text-center">'
			+'<td>样本材料</td>'
			+'<td>'+file.files[0].name+'</td>'
			+'<td><label  style="color: red" onclick="deleteAttach(this)">删除</label>'
			+'</td></tr>';
		$('#otherMaterial').append(html);
		$('#uploadButton').hide();
    };
    function deleteAttach(attachObj) {
        var obj=$(attachObj);
        obj.parent().parent().remove();
        $('#upload').val("");
        $('#uploadButton').show();
    };
    function saveExampleFile() {
        var param=$('#exampleFileForm').formToJson();
        if(param.stExampleName==null||param.stExampleName==""){
            Duang.error("提示","请填写样本名称");
        }else {
            $.ajaxFileUpload({
                url: '../${requestUrl}?method=saveExampleFile',
                type: 'post',
                secureuri: false,                       //是否启用安全提交,默认为false
                fileElementId: 'upload',
                data:param,
                dataType: 'JSON',
                success: function (data, status) {        //服务器响应成功时的处理函数
                    data = data.replace(/<.*?>/ig, "");  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
                    var file = JSON.parse(data);
                    if (file.success) {
                        $('#processIndexChildForm').modal('hide');
                        querySampleTable(param.stNodeId);
                    } else {
                        Duang.error("提示", "操作失败");
                    }
                },
                error: function (data, status, e) { //服务器响应失败时的处理函数
                    Duang.error("提示", "操作失败");
                }
            });
        }
    };
</script>