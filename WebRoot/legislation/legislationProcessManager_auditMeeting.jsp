<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <span >审核会议 > </span>
        </li>
        <li>
            <span >发起</span>
        </li>
    </ul>
    <button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<form id="auditMeetingForm" class="form-horizontal"
		  novalidate="novalidate">
        <input hidden name="stDocId" <c:if test="${legislationProcessDoc.stDocId !=null}">value="${legislationProcessDoc.stDocId}" </c:if>>
		<div class="form-body">
            <div class="form-group">
                <label class="col-sm-3 control-label">会议名称：</label>
                <div class="col-sm-9">
                    <input type="text"  class="form-control" id="stDocName" name="stDocName" <c:if test="${legislationProcessDoc.stDocName !=null}">value="${legislationProcessDoc.stDocName}" </c:if>>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">会议类型：</label>
                <div class="col-sm-9">
                    <select class="form-control" name="stDocNo">
                        <option  value="草案审核会议">草案审核会议</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">对应草案：</label>
                <div class="col-sm-9">
                    <table class="table table-bordered table-hover">
                        <thead>
                            <c:if test="${legislationProcessDoc.stDocId==null}">
                                <th class="text-center">
                                    选择草案
                                </th>
                            </c:if>
                            <th class="text-center">
                                草案名称
                            </th>
                            <th class="text-center">
                                新草案名称
                            </th>
                            <c:if test="${stTaskStatus=='INPUT'}">
                                <th class="text-center">
                                    审核结果
                                </th>
                            </c:if>
                        </thead>
                        <tbody>
                        <c:if test="${legislationProcessDocList !=null&&fn:length(legislationProcessDocList)>0}">
                            <c:forEach items="${legislationProcessDocList}" var="doc">
                                <tr>
                                    <c:if test="${legislationProcessDoc.stDocId==null}">
                                        <td class="text-center">
                                            <input type="checkbox" name="stDocSourceCheck" value="${doc.stDocId}">
                                        </td>
                                    </c:if>
                                    <td class="text-center">
                                            ${doc.stDocName}
                                    </td>
                                    <td class="text-center">
                                         <c:if test="${legislationProcessDoc.stDocId !=null}">
                                         <input type="checkbox" style="display: none" name="stDocSourceCheck" checked value="${doc.stDocId}">
                                         </c:if>
                                         <input type="text" name="${doc.stDocId}" placeholder="需修改法规规章草案名称请在此处填写">
                                    </td>
                                    <c:if test="${stTaskStatus=='INPUT'}">
                                        <td class="text-center">
                                            <select name="stActive${doc.stDocId}">
                                                <c:choose>
                                                    <c:when test="${doc.stActive=='true'}">
                                                        <option value="true" selected>成功</option>
                                                        <option value="false">失败</option>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <option value="true" >成功</option>
                                                        <option value="false" selected>失败</option>
                                                    </c:otherwise>
                                                </c:choose>
                                            </select>
                                        </td>
                                    </c:if>
                                </tr>
                            </c:forEach>
                        </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">会议地点：</label>
                <div class="col-sm-9">
                    <input type="text"  class="form-control" id="stNodeName" name="stNodeName" <c:if test="${legislationProcessDoc.stNodeName !=null}">value="${legislationProcessDoc.stNodeName}" </c:if>>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">会议时间：</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" readonly id="dtCreateDate" name="dtCreateDate" <c:if test="${legislationProcessDoc.dtCreateDate !=null}">value="<fmt:formatDate value="${legislationProcessDoc.dtCreateDate}"/>" </c:if>>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">会议人员：</label>
                <div class="col-sm-9">
                    <textarea class="form-control" id="stComent" name="stComent" ><c:if test="${legislationProcessDoc.stComent !=null}">${legislationProcessDoc.stComent}</c:if></textarea>
                </div>
            </div>
			<div class="form-group text-center">
					<input type="button" class="btn btn-w-m btn-success" id="btnSave"
						   name="btnSave" onclick="saveAuditMeeting()" value="提交"> &nbsp;&nbsp;
					<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="返回">
			</div>
			<div class="form-group">
				<label class="control-label">审核会议前材料
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
            <div class="form-group">
                <label class="control-label">审核会议前其他材料
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
                        <c:if test="${legislationFilesList !=null&&fn:length(legislationFilesList)>0}">
                            <c:forEach var="file" items="${legislationFilesList}">
                                <c:if test="${file.stSampleId==null||file.stSampleId=='null'}">
                                    <tr class="text-center">'
                                        <td class="text-left">需要报送的其他材料</td>
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
    $(function () {
        laydate.render({
            elem: '#dtCreateDate',
            format:'yyyy-MM-dd',
            calendar: true,
        });
    });
    function toUploadFile(obj) {
        $(obj).next().click();
    };
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
    };
    function saveAuditMeeting() {
        var param=$('#auditMeetingForm').formToJson();
        var stDocSource="";
        var checkedNum=0;
        $('[name="stDocSourceCheck"]:checked').each(function () {
            stDocSource=stDocSource+"#"+this.value;
            checkedNum++;
        });
        if(checkedNum>0){
            param.stDocSource=stDocSource.substring(1);
        }
        if(param.stDocName==null||param.stDocName==""){
            Duang.error("提示","请输入会议名称");
        }else if(param.stDocNo==null||param.stDocNo==""){
            Duang.error("提示","请选择会议类型");
        }else if(param.stDocSource==null||param.stDocSource==""){
            Duang.error("提示","请选择对应草案");
        }else if(param.stNodeName==null||param.stNodeName==""){
            Duang.error("提示","请输入会议地点");
        }else if(param.dtCreateDate==null||param.dtCreateDate==""){
            Duang.error("提示","请选择会议时间");
        }else if(param.stComent==null||param.stComent==""){
            Duang.error("提示","请输入会议人员");
        }else {
            $.post("../${requestUrl}?stNodeId=${nodeId}&method=saveAuditMeeting&stTaskStatus=${stTaskStatus}",param,function(data){
                $('#legislationProcessForm').modal('hide');
                submitForm(1);
            });
        }
    }
</script>