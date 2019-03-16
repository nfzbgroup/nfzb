<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<form id="handleDemonstrationForm" class="form-horizontal"
      novalidate="novalidate">
    <input hidden name="stTaskId" <c:if test="${legislationProcessTask.stTaskId !=null}">value="${legislationProcessTask.stTaskId}" </c:if>>
    <div class="form-body">
        <div class="form-group">
            <label class="col-sm-3 control-label">指导说明：</label>
            <div class="col-sm-9">
                <textarea class="form-control" name="stComment2" ><c:if test="${legislationProcessTask.stComment2 !=null}">${legislationProcessTask.stComment2}</c:if></textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label">材料
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
                    <th class="text-center" data-field="id">文件类型</th>
                    <th class="text-center" data-field="district_name">文件名称</th>
                    <th class="text-center" data-field="set">操作</th>
                </tr>
                </thead>
                <tbody id="otherMaterial">
                <c:if test="${legislationFilesList !=null&&fn:length(legislationFilesList)>0}">
                    <c:forEach var="file" items="${legislationFilesList}">
                        <tr class="text-center">'
                            <td class="text-left">材料</td>
                            <td>${file.stTitle}</td>
                            <td>
                                <a  target="_blank" href="${basePath}/file/downloadAttach.do?name=${file.stTitle}&url=${file.stFileUrl}">下载</a>&nbsp;&nbsp;
                                <label  style="color: red" onclick="deleteAttach(this,2,null,'${file.stFileId}',null)">删除</label>
                                <input type="hidden" id="${file.stFileId}"  name="${file.stFileId}" value="${file.stFileId}">
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>
                </tbody>
            </table>
        </div>
        <div class="form-group text-center">
            <input type="button" class="btn btn-w-m btn-success" id="btnSave"
                   name="btnSave"  value="保存" onclick="saveLegislationDemonstration()">
        </div>
    </div>
</form>
<script>
    $(function () {
        laydate.render({
            elem: '#dtBakDate',
            format:'yyyy-MM-dd',
            calendar: true,
        });
    });
    function uploadFile(id) {
        $.ajaxFileUpload({
            url: '${basePath}/file/upload.do?stNodeId=${nodeId}',
            type: 'post',
            secureuri: false,                       //是否启用安全提交,默认为false
            fileElementId: id,
            dataType: 'JSON',
            success: function (data, status) {        //服务器响应成功时的处理函数
                data = data.replace(/<.*?>/ig, "");  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
                var file = JSON.parse(data);
                if (file.success) {
                    var html='<tr class="text-center">'
                        +'<td class="text-left">材料</td>'
                        +'<td>'+file.name+'</td>'
                        +'<td><a  target="_blank" href="${basePath}/file/downloadAttach.do?name='+file.name+'&url='+file.url+'">下载</a>&nbsp;&nbsp;'
                        +'<label  style="color: red" onclick="deleteAttach(this,2,\''+id+'\',\''+file.fileId+'\','+null+')">删除</label>'
                        +'<input type="hidden" id="'+file.fileId+'"  name="'+file.fileId+'" value='+file.fileId+'>'
                        +'</td></tr>';
                    $('#otherMaterial').append(html);
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
    function saveLegislationDemonstration() {
        var param=$('#handleDemonstrationForm').formToJson();
        if(param.stComment2==null||param.stComment2==""){
            Duang.error("提示","请输入指导说明");
        }else {
            $.post("../${requestUrl}?stNodeId=${nodeId}&method=saveHandleDemonstration",param,function(data){
                if(data.success){
                    Duang.success("提示","操作成功");
                }else{
                    Duang.error("提示","操作失败");
                }
            });
        }
    };
</script>