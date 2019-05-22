<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <span >部门意见征求 > </span>
        </li>
        <li>
            <span >意见编辑</span>
        </li>
    </ul>
	<button style="padding-right: 10px;padding-top: 8px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
<h2 style="color: #E4243D;text-align: center;font-weight: bold;margin-bottom: 20px">征求意见单</h2>
<form id="unitDemonstrationForm" class="form-horizontal"
      novalidate="novalidate">
    <input hidden id="stTaskId" name="stTaskId" <c:if test="${legislationProcessTask.stTaskId !=null}">value="${legislationProcessTask.stTaskId}" </c:if>>
    <input type="hidden" name="stDocId" value="${legislationProcessDoc.stDocId}">
    <input type="hidden" id="buttonId" value="${buttonId}">
    <div class="form-body">
      <c:if test="${legislationProcessTask.stTaskStatus == 'TODO'||legislationProcessTask.stTaskStatus == null}">
        <div class="form-group">
            <label class="col-sm-4 control-label">对应草案：</label>
            <label class="col-sm-5 control-label" style="text-align:left">${legislationProcessDoc.stDocName}</label>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label"></label>
            <div class="col-sm-5">
                <input type="text"  class="form-control" name="newDocName">
            </div>
            <label class="col-sm-3 control-label" style="color: red;text-align: left">*需修改法规规章草案名称请在此处填写</label>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">类型：</label>
            <div class="col-sm-5">
                <select  class="form-control" name="stBakOne" >
                    <c:choose>
                        <c:when test="${legislationProcessTask.stBakOne !=null}">
                            <c:if test="${legislationProcessTask.stBakOne=='征询'}">
                                <option value="征询" selected>征询</option>
                                <option value="征询含会签">征询含会签</option>
                            </c:if>
                            <c:if test="${legislationProcessTask.stBakOne=='征询含会签'}">
                                <option value="征询" >征询</option>
                                <option value="征询含会签" selected>征询含会签</option>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <option value="征询">征询</option>
                            <option value="征询含会签">征询含会签</option>
                        </c:otherwise>
                    </c:choose>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">渠道：</label>
            <div class="col-sm-5">
                <select  class="form-control" name="stBakTwo">
                    <c:choose>
                        <c:when test="${legislationProcessTask.stBakTwo !=null}">
                            <c:if test="${legislationProcessTask.stBakTwo=='内部'}">
                                <option value="内部" selected>内部</option>
                                <option value="外部">外部</option>
                            </c:if>
                            <c:if test="${legislationProcessTask.stBakTwo=='外部'}">
                                <option value="内部">内部</option>
                                <option value="外部" selected>外部</option>
                            </c:if>
                        </c:when>
                        <c:otherwise>
                            <option value="内部">内部</option>
                            <option value="外部">外部</option>
                        </c:otherwise>
                    </c:choose>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="control-label">单位意见材料
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
            <label class="control-label">单位意见其他材料
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
      </c:if>
      <c:if test="${legislationProcessTask.stTaskStatus == 'DONE'}">
       <table class="table table-border table-bordered">
		<tr>
			<td class="text-right tab-left" width="300px">
				<label style="white-space: nowrap">对应草案:</label>
			</td>
			<td class="text-center">
				${legislationProcessTask.stFlowId}
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">类型:</label>
			</td>
			<td class="text-center">
				${legislationProcessTask.stBakOne}
			</td>
		</tr>
		<tr>
			<td class="text-right tab-left">
				<label style="white-space: nowrap">渠道:</label>
			</td>
			<td class="text-center">
				${legislationProcessTask.stBakTwo}
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
		<c:if test="${nodeId=='NOD_0000000122'}">
			<tr>
				<td class="text-right tab-left">
					<label style="white-space: nowrap">反馈:</label>
				</td>
				<td class="text-center">
					<c:if test="${legislationFiles !=null}">
						<label class="col-md-12 text-center"><a  target="_blank" href="${basePath}/file/downloadAttach.do?name=${legislationFiles.stTitle}&url=${legislationFiles.stFileUrl}">${legislationFiles.stTitle}</a></label>
					</c:if>
				</td>
			</tr>
		</c:if>
	  </table>
	</c:if>
        <div class="form-group text-center">
          <c:if test="${legislationProcessTask.stTaskStatus == 'TODO'||legislationProcessTask.stTaskStatus == null}">
            <input type="button" class="btn btn-w-m btn-success"
                   name="btnSave" onclick="saveLegislationDemonstration()" value="保存"> &nbsp;&nbsp;
            <input type="button" class="btn btn-w-m btn-success"
                   name="btnSave"  onclick="confirmReport('${stDocId}','${nodeId}','${buttonId}')"
                  value="发起征询"> &nbsp;&nbsp; 
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
        if(param.stBakOne==null||param.stBakOne==""){
            Duang.error("提示","请选择类型");
        }else if(param.stBakTwo==null||param.stBakTwo==""){
            Duang.error("提示","请选择渠道");
        }else {
            $.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationDemonstration",param,function(data){
                if(data.success){
                    $('#processIndexForm').modal('hide');
                    $('#${nodeId}').parent().removeClass('bcg_gray').removeClass('bcg_blue').removeClass('bcg_green').addClass('bcg_green');
                    Duang.success("提示","操作成功");
                }else{
                    Duang.error("提示","操作失败");
                }
            });
        }
    };
    function confirmReport(stDocId,nodeId,buttonId) {
    	//alert("___");
        var param=$('#unitDemonstrationForm').formToJson();
        if(param.stBakOne==null||param.stBakOne==""){
            Duang.error("提示","请选择类型");
        }else if(param.stBakTwo==null||param.stBakTwo==""){
            Duang.error("提示","请选择渠道");
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