<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <span >立法项目 > </span>
        </li>
        <li>
            <span ><c:if test="${nodeId=='NOD_0000000202'||nodeId=='NOD_0000000207'}"><c:choose><c:when test="${legislationPlanTask.stTaskId !=null}"><c:if test="${legislationPlanTask.stTaskStatus=='TODO'}">编辑</c:if><c:if test="${legislationPlanTask.stTaskStatus=='DONE'||legislationPlanTask.stTaskStatus=='DOING'}">详情</c:if></c:when><c:otherwise>发起</c:otherwise></c:choose> </c:if><c:if test="${nodeId!='NOD_0000000202'&&nodeId!='NOD_0000000207'}">详情</c:if></span>
        </li>
    </ul>
    <button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<form id="legislationPlanForm" class="form-horizontal"
		  novalidate="novalidate">
        <input hidden name="stTaskId" <c:if test="${legislationPlanTask.stTaskId !=null}">value="${legislationPlanTask.stTaskId}" </c:if>>
        <div class="form-body">
            <div class="form-group">
                <label class="col-sm-3 control-label">立法项目名称：</label>
                <div class="col-sm-9">
                    <input type="text" class="form-control" name="stItemName" <c:if test="${(nodeId!='NOD_0000000202'&&nodeId!='NOD_0000000207')||(legislationPlanTask.stTaskStatus !=null&&legislationPlanTask.stTaskStatus !='TODO')}">disabled</c:if> <c:if test="${legislationPlanItem.stItemName !=null}">value="${legislationPlanItem.stItemName}" </c:if>>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">征集通知：</label>
                <div class="col-sm-9">
                    <select class="form-control" name="stPlanId" <c:if test="${(nodeId!='NOD_0000000202'&&nodeId!='NOD_0000000207')||(legislationPlanTask.stTaskStatus !=null&&legislationPlanTask.stTaskStatus !='TODO')}">disabled</c:if>>
                       <c:if test="${legislationPlanList!=null&&fn:length(legislationPlanList)>0}">
                           <c:forEach var="plan" items="${legislationPlanList}">
                               <option value="${plan.stPlanId}" <c:if test="${legislationPlanItem.stPlanId !=null&&legislationPlanItem.stPlanId ==plan.stPlanId}">selected</c:if>>${plan.stPlanName}</option>
                           </c:forEach>
                       </c:if>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">立法项目类型：</label>
                <div class="col-sm-9">
                    <select class="form-control" name="stTypeName" <c:if test="${(nodeId!='NOD_0000000202'&&nodeId!='NOD_0000000207')||(legislationPlanTask.stTaskStatus !=null&&legislationPlanTask.stTaskStatus !='TODO')}">disabled</c:if>>
                        <option value="立" <c:if test="${legislationPlanItem.stTypeName !=null&&legislationPlanItem.stTypeName =='立'}">selected</c:if>>立</option>
                        <option value="改" <c:if test="${legislationPlanItem.stTypeName !=null&&legislationPlanItem.stTypeName =='改'}">selected</c:if>>改</option>
                        <option value="废" <c:if test="${legislationPlanItem.stTypeName !=null&&legislationPlanItem.stTypeName =='废'}">selected</c:if>>废</option>
                        <option value="正式" <c:if test="${legislationPlanItem.stTypeName !=null&&legislationPlanItem.stTypeName =='正式'}">selected</c:if>>正式</option>
                        <option value="预备" <c:if test="${legislationPlanItem.stTypeName !=null&&legislationPlanItem.stTypeName =='预备'}">selected</c:if>>预备</option>
                        <option value="修订" <c:if test="${legislationPlanItem.stTypeName !=null&&legislationPlanItem.stTypeName =='修订'}">selected</c:if>>修订</option>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">建议立项理由：</label>
                <div class="col-sm-9">
                    <textarea class="form-control" name="stContent" <c:if test="${(nodeId!='NOD_0000000202'&&nodeId!='NOD_0000000207')||(legislationPlanTask.stTaskStatus !=null&&legislationPlanTask.stTaskStatus !='TODO')}">disabled</c:if> ><c:if test="${legislationPlanItem.stContent !=null}">${legislationPlanItem.stContent}</c:if></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">目前进展情况：</label>
                <div class="col-sm-9">
                    <textarea class="form-control" name="stStatus" <c:if test="${(nodeId!='NOD_0000000202'&&nodeId!='NOD_0000000207')||(legislationPlanTask.stTaskStatus !=null&&legislationPlanTask.stTaskStatus !='TODO')}">disabled</c:if> ><c:if test="${legislationPlanItem.stStatus !=null}">${legislationPlanItem.stStatus}</c:if></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">备注：</label>
                <div class="col-sm-9">
                    <textarea class="form-control" name="stBak" <c:if test="${(nodeId!='NOD_0000000202'&&nodeId!='NOD_0000000207')||(legislationPlanTask.stTaskStatus !=null&&legislationPlanTask.stTaskStatus !='TODO')}">disabled</c:if> ><c:if test="${legislationPlanItem.stBak !=null}">${legislationPlanItem.stBak}</c:if></textarea>
                </div>
            </div>
			<div class="form-group text-center">
                <c:if test="${(nodeId=='NOD_0000000202'||nodeId=='NOD_0000000207')&&(legislationPlanTask.stTaskStatus ==null||legislationPlanTask.stTaskStatus=='TODO')}">
                    <input type="button" class="btn btn-w-m btn-success"  value="提交" onclick="saveLegislationPlan()"> &nbsp;&nbsp;
                </c:if>
					<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
			</div>
            <div class="form-group">
                <label class="control-label">立法项目材料
                </label>
                <c:if test="${(nodeId=='NOD_0000000202'||nodeId=='NOD_0000000207')&&(legislationPlanTask.stTaskStatus ==null||legislationPlanTask.stTaskStatus=='TODO')}">
                    <label class="btn btn-w-m btn-success" onclick="toUploadFile(this)">点击上传
                    </label>
                    <input  type="file" id="7" name="upload" style="display:none"  onchange="uploadFile(this.id,2,null)">
                </c:if>
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
                                    <tr class="text-center">
                                        <td>立法项目材料</td>
                                        <td>${file.stTitle}</td>
                                        <td>
                                            <a  target="_blank" href="${basePath}/file/downloadAttach.do?name=${file.stTitle}&url=${file.stFileUrl}">下载</a>
                                           <c:if test="${(nodeId=='NOD_0000000202'||nodeId=='NOD_0000000207')&&(legislationPlanTask.stTaskStatus ==null||legislationPlanTask.stTaskStatus=='TODO')}">
                                             &nbsp;&nbsp;
                                            <label  style="color: red" onclick="deleteAttach(this,2,null,'${file.stFileId}',null)">删除</label>
                                            <input type="hidden" id="${file.stFileId}"  name="${file.stFileId}" value="${file.stFileId}">
                                           </c:if>
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
    function saveLegislationPlan() {
        var param=$('#legislationPlanForm').formToJson();
        if(param.stItemName==null||param.stItemName==""){
            Duang.error("提示","请输入立法项目名称");
        }else if(param.stPlanId==null||param.stPlanId==""){
            Duang.error("提示","请选择征集通知");
        }else if(param.stTypeName==null||param.stTypeName==""){
            Duang.error("提示","请选择立法项目类型");
        }else if(param.stContent==null||param.stContent==""){
            Duang.error("提示","请输入建议立项理由");
        }else if(param.stStatus==null||param.stStatus==""){
            Duang.error("提示","请输入目前进展情况");
        }else if(param.stBak==null||param.stBak==""){
            Duang.error("提示","请输入备注");
        }else {
            $.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationPlan",param,function(data){
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
                            +'<td>立法项目材料</td>'
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