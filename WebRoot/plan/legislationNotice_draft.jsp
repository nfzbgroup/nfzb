<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <span >编辑草案送审稿说明</span>
        </li>
    </ul>
    <button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<form id="legislationNoticeForm" class="form-horizontal"
		  novalidate="novalidate">
        <input hidden name="stTaskId" <c:if test="${legislationPlanTask.stTaskId !=null}">value="${legislationPlanTask.stTaskId}" </c:if>>
        <div class="form-body">
            <div class="form-group">
                <label class="col-sm-3 control-label">通知名称：</label>
                <div class="col-sm-9">
                    <label class="form-control">${legislationPlan.stPlanName}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">通知说明：</label>
                <div class="col-sm-9">
                    <label class="form-control">${legislationPlan.stReason}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">计划名称：</label>
                <div class="col-sm-9">
                     <label class="form-control">${legislationPlan.stProgress}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">计划说明：</label>
                <div class="col-sm-9">
                  <label class="form-control">${legislationPlan.stRemark}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">送审稿说明：</label>
                <div class="col-sm-9">
                    <textarea class="form-control" name="stComment1" <c:if test="${legislationPlanTask.stTaskStatus !=null&&legislationPlanTask.stTaskStatus=='DONE'}">disabled</c:if> ><c:if test="${legislationPlanTask.stComment1 !=null}">${legislationPlanTask.stComment1}</c:if></textarea>
                </div>
            </div>
            <div class="form-group">
				<label class="col-sm-3 control-label">常务会议议题：</label>
				<div class="col-sm-9">
					<select class="form-control" name="checkmeetingTaskId" value="${checkmeetingTaskId}">
		                       <c:if test="${legislationCitymeetingList!=null&&fn:length(legislationCitymeetingList)>0}">
		                           <c:forEach var="ctask" items="${legislationCitymeetingList}">
		                               <option value="${ctask.stTopicId}" >${ctask.stTopicName}</option>
		                           </c:forEach>
		                       </c:if>
		            </select>
					<%-- <table class="table table-bordered table-hover">
						<thead>
							<th class="text-center"></th>
							<th class="text-center">任务编号</th>
							<th class="text-center">会议名称</th>
						</thead>
						<tbody>
							<c:if test="${legislationCitymeetingTask !=null&&fn:length(legislationCitymeetingTask)>0}">
								<c:forEach items="${legislationCitymeetingTask}" var="ctask">
									<tr>
										<td class="text-center">
											<input type="checkbox" name="checkmeetingTaskId" value="${ctask.stTaskId}" <c:if test="${legislationCheckmeetingTask.stDocSource.indexOf(doc.stDocId)>=0 }">checked</c:if>>
											<input type="radio" name="checkmeetingTaskId" value="${ctask.stTopicId}" >
										</td>
										<td>${ctask.stTaskId}</td>
										<td>
											${ctask.stFlowId}
										</td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table> --%>
				</div>
			</div>
            
            
			<div class="form-group text-center">
                <c:if test="${legislationPlanTask.stTaskStatus ==null||legislationPlanTask.stTaskStatus=='TODO'}">
                    <input type="button" class="btn btn-w-m btn-success"  value="保存" onclick="saveLegislationNotice()"> &nbsp;&nbsp;
                </c:if>
					<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
			</div>
            <div class="form-group">
                <label class="control-label">送审稿材料
                </label>
                <c:if test="${legislationPlanTask.stTaskStatus ==null||legislationPlanTask.stTaskStatus=='TODO'}">
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
                                        <td>计划材料</td>
                                        <td>${file.stTitle}</td>
                                        <td>
                                            <a  target="_blank" href="${basePath}/file/downloadAttach.do?fileId=${file.stFileId}">下载</a>
                                            <c:if test="${legislationPlanTask.stTaskStatus ==null||legislationPlanTask.stTaskStatus=='TODO'}">
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
    function saveLegislationNotice() {
        var param=$('#legislationNoticeForm').formToJson();
        if(param.stComment1==null||param.stComment1==""){
            Duang.error("提示","请输入送审稿说明");
        }else {
            $.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationNotice",param,function(data){
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
            url: '${basePath}/file/upload.do?stNodeId=NOD_0000000209&stSampleId='+stSampleId,
            type: 'post',
            secureuri: false,                       //是否启用安全提交,默认为false
            fileElementId: id,
            dataType: 'JSON',
            success: function (data, status) {        //服务器响应成功时的处理函数
                data = data.replace(/<.*?>/ig, "");  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
                var file = JSON.parse(data);
                if (file.success) {
                    if(type==1){
                        var html='<a target="_blank" href="${basePath}/file/downloadAttach.do?fileId='+file.fileId+'">下载</a>&nbsp;&nbsp;'
                            +'<input type="hidden" id="'+file.fileId+'"  name="'+file.fileId+'" value='+file.fileId+'>'
                        +'<label  style="color: red" onclick="deleteAttach(this,1,\''+id+'\',\''+file.fileId+'\',\''+stSampleId+'\')" >删除</label>';
                        $("#"+id).parent().prev().html('<span>'+file.name+'</span>');
                        $("#"+id).parent().html(html);
                    }else{
                        var html='<tr class="text-center">'
                            +'<td>计划材料</td>'
                            +'<td>'+file.name+'</td>'
                            +'<td><a  target="_blank" href="${basePath}/file/downloadAttach.do?fileId='+file.fileId+'">下载</a>&nbsp;&nbsp;'
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