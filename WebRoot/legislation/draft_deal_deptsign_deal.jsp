<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <span >部门会签 > </span>
        </li>
        <li>
            <span >会签盖章</span>
        </li>
    </ul>
    <button style="padding-right: 10px;padding-top: 8px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button></div>
</div>
<div class="modal-body">
<h2 style="color: #E4243D;text-align: center;font-weight: bold;margin-bottom: 20px">部门会签盖章</h2>
<form id="unitDemonstrationForm" class="form-horizontal"
      novalidate="novalidate">
    <input hidden id="stTaskId" name="stTaskId" <c:if test="${legislationProcessTask.stTaskId !=null}">value="${legislationProcessTask.stTaskId}" </c:if>>
    <input type="hidden" name="stDocId" value="${legislationProcessDoc.stDocId}">
    <input type="hidden" id="buttonId" value="${buttonId}">
    <c:if test=""></c:if>
    <div class="form-body">
       <c:if test="${legislationProcessTask.stTaskStatus == 'TODO'}">
        <div class="form-group">
            <label class="col-sm-3 control-label">对应草案：</label>
            <label class="col-sm-4 control-label" style="text-align:left">${legislationProcessDoc.stDocName}</label>
        </div>
        <div class="form-group">
				<label class="col-sm-3 control-label text-left">说明：</label>
				<div class="col-sm-9">
					<textarea id="stComment2" name="stComment2" class="form-control"
					><c:if test="${legislationProcessTask.stComment2 !=null}">${legislationProcessTask.stComment2}</c:if></textarea>
				</div>
		</div>
        <div class="form-group">
				<label class="control-label">部门会签材料 </label>
		</div>	
			<%@include file="/legislation/file/attachUpload.jsp" %>
        </c:if>
        <c:if test="${legislationProcessTask.stTaskStatus == 'DONE'}">
         <table class="table table-border table-bordered">
		  <tr>
			<td class="text-right" width="300px">
				<label style="white-space: nowrap">对应草案:</label>
			</td>
			<td class="text-center">
				${legislationProcessTask.stFlowId}
			</td>
		  </tr>
		  <tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">说明:</label>
			</td>
			<td class="text-center">
				${legislationProcessTask.stComment2}
			</td>
		  </tr>
		  <tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">盖章时间:</label>
			</td>
			<td class="text-center">
				<fmt:formatDate type="time" pattern="yyyy-MM-dd HH:mm:ss"
            value="${legislationProcessTask.dtDealDate}" />
			</td>
		  </tr>
		  <tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">单位意见相关材料:</label>
			</td>
			<td class="text-center">
				<c:if test="${legislationFilesList !=null&&fn:length(legislationFilesList)>0}">
					<c:forEach var="file" items="${legislationFilesList}">
						<c:if test="${file.stSampleId !=null&&file.stSampleId !='null'}">
							<label class="col-md-12 text-center"><a  target="_blank" href="${basePath}/file/downloadAttach.do?name=${file.stTitle}&url=${file.stFileUrl}">${file.stTitle}</a></label>
						</c:if>
					</c:forEach>
				</c:if>
				<c:if test="${legislationFilesList ==null||fn:length(legislationFilesList)==0}">无</c:if>
			</td>
		  </tr>
		  <tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">单位意见其他材料:</label>
			</td>
			<td class="text-center">
			<c:if test="${legislationFilesList !=null&&fn:length(legislationFilesList)>0}">
				<c:forEach var="file" items="${legislationFilesList}">
					<c:if test="${file.stSampleId==null||file.stSampleId=='null'}">
						<label class="col-md-12 text-center"><a  target="_blank" href="${basePath}/file/downloadAttach.do?name=${file.stTitle}&url=${file.stFileUrl}">${file.stTitle}</a></label>
					</c:if>
				</c:forEach>
			</c:if>
			<c:if test="${legislationFilesList ==null||fn:length(legislationFilesList)==0}">无</c:if>
			</td>
		  </tr>
	     </table>
	    </c:if>
        <div class="form-group text-center">
          <c:if test="${legislationProcessTask.stTaskStatus == 'TODO'}">
            <input type="button" class="btn btn-w-m btn-success"
                 id="btnSave"   name="btnSave" onclick="saveLegislationDemonstration()" value="保存"> &nbsp;&nbsp;
            <input type="button" class="btn btn-w-m btn-success"
                   name="btnSend" <c:if test="${legislationProcessTask.stTaskId !=null &&( legislationProcessTask.stComment1==null|| empty legislationProcessTask.stComment1)}"> onclick="sendOA()"</c:if>
                   <c:if test="${legislationProcessTask.stTaskId ==null ||(legislationProcessTask.stComment1!=null||not empty legislationProcessTask.stComment1)}"> disabled="disabled"</c:if> value="发OA盖章"> &nbsp;&nbsp;
            <input type="button" class="btn btn-w-m btn-success"
                   name="btnSave" <c:if test="${legislationProcessTask.stTaskId !=null}"> onclick="confirmReport('${stDocId}','${nodeId}','${buttonId}')"</c:if>
                   <c:if test="${legislationProcessTask.stTaskId ==null}">disabled="disabled"</c:if> value="送立法处"> &nbsp;&nbsp;
          </c:if>
            <input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
        </div>
    </div>
</form>
</div>
<script>
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
    function saveLegislationDemonstration() {
        var param=$('#unitDemonstrationForm').formToJson();
        if(param.stComment2==null||param.stComment2==""){
            Duang.error("提示","请填写说明");
        }else {
            $.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationDemonstration",param,function(data){
                if(data.success){
                    $('#processIndexForm').modal('hide');
                    Duang.success("提示","操作成功");
                }else{
                    Duang.error("提示","操作失败");
                }
            });
        }
    };
    function sendOA() {
        var param=$('#unitDemonstrationForm').formToJson();
        if(param.stComment2==null||param.stComment2==""){
            Duang.error("提示","请填写说明");
        }else {
        	layer.confirm('请确认操作！',function(index){
        	  layer.close(layer.index);
        		$.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationDemonstration&stComment1=OAsend",param,function(data){
                    if(data.success){
                    	$("input[name='btnSend']").attr("disabled","disabled");
                        Duang.success("提示","操作成功");
                    }else{
                        Duang.error("提示","操作失败");
                    }
                });
        	});
        }
    };
    function confirmReport(stDocId,nodeId,buttonId) {
        var param=$('#unitDemonstrationForm').formToJson();
        var isSend=$("input[name='btnSend']").attr("disabled");
        if(isSend!="disabled"){
            Duang.error("提示","请先发送OA");
        }else if(param.stComment2==null||param.stComment2==""){
            Duang.error("提示","请选择类型");
        }else {
        	//先保存数据
            $.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationDemonstration",param,function(data){
                if(data.success){
                	//处理附件与提交操作
                    uploadDemonstrationReport(stDocId,nodeId,buttonId);
                }else{
                    Duang.error("提示","操作失败");
                }
            });
        }
    }
</script>