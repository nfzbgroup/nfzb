<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<form id="expertDemonstrationForm" class="form-horizontal"
      novalidate="novalidate">
    <input hidden name="stTaskId" <c:if test="${legislationProcessTask.stTaskId !=null}">value="${legislationProcessTask.stTaskId}" </c:if>>
    <div class="form-body">
        <div class="form-group">
            <label class="col-sm-3 control-label">论证会议题：</label>
            <div class="col-sm-9">
                <textarea class="form-control" name="stBakOne"><c:if test="${legislationProcessTask.stBakOne !=null}">${legislationProcessTask.stBakOne}</c:if></textarea>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">对应草案：</label>
            <div class="col-sm-9">
                <select class="form-control" name="stDocId" >
                    <c:choose>
                        <c:when test="${legislationProcessTask.stDocId!=null}">
                            <option value="${legislationProcessDoc.stDocId}" selected>${legislationProcessDoc.stDocName}</option>
                        </c:when>
                        <c:otherwise>
                            <c:if test="${legislationProcessDocList !=null&&fn:length(legislationProcessDocList)>0}">
                                <c:forEach items="${legislationProcessDocList}" var="doc">
                                    <option value="${doc.stDocId}">${doc.stDocName}</option>
                                </c:forEach>
                            </c:if>
                        </c:otherwise>
                    </c:choose>
                </select>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label"></label>
            <div class="col-sm-4">
                <input type="text"  class="form-control" name="newDocName">
            </div>
            <label class="col-sm-5 control-label" style="color: red;text-align: left">*需修改法规规章草案名称请在此处填写</label>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">论证会地点：</label>
            <div class="col-sm-9">
                <input type="text"  class="form-control" name="stBakTwo" <c:if test="${legislationProcessTask.stBakTwo !=null}">value="${legislationProcessTask.stBakTwo}" </c:if>>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">论证会时间：</label>
            <div class="col-sm-9">
                <input type="text"  class="form-control" readonly id="dtBakDate" name="dtBakDate" <c:if test="${legislationProcessTask.dtBakDate !=null}">value="<fmt:formatDate value="${legislationProcessTask.dtBakDate}"/>" </c:if>>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-3 control-label">论证会人员：</label>
            <div class="col-sm-9">
                <textarea class="form-control" name="stComment2" ><c:if test="${legislationProcessTask.stComment2 !=null}">${legislationProcessTask.stComment2}</c:if></textarea>
            </div>
        </div>
        <div class="form-group">
            <c:choose>
                <c:when test="${legislationProcessTask.stNodeId=='NOD_0000000151'}">
                    <label class="control-label">专家论证后材料
                    </label>
                </c:when>
                <c:otherwise>
                    <label class="control-label">专家论证前材料
                    </label>
                </c:otherwise>
            </c:choose>
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
            <c:choose>
                <c:when test="${legislationProcessTask.stNodeId=='NOD_0000000151'}">
                    <label class="control-label">专家论证后其他材料
                    </label>
                </c:when>
                <c:otherwise>
                    <label class="control-label">专家论证前其他材料
                    </label>
                </c:otherwise>
            </c:choose>

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
        <div class="form-group text-center">
            <input type="button" class="btn btn-w-m btn-success" id="btnSave"
                   name="btnSave"  value="提交" onclick="saveLegislationDemonstration()"> &nbsp;&nbsp;
            <input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="返回">
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
    function saveLegislationDemonstration() {
        var param=$('#expertDemonstrationForm').formToJson();
        if(param.stBakOne==null||param.stBakOne==""){
            Duang.error("提示","请输入论证会议题");
        }else if(param.stDocId==null||param.stDocId==""){
            Duang.error("提示","请选择对应草案");
        }else if(param.stBakTwo==null||param.stBakTwo==""){
            Duang.error("提示","请输入论证会地点");
        }else if(param.dtBakDate==null||param.dtBakDate==""){
            Duang.error("提示","请选择论证会时间");
        }else if(param.stComment2==null||param.stComment2==""){
            Duang.error("提示","请输入论证会人员");
        }else {
            $.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationDemonstration",param,function(data){
                $('#legislationProcessForm').modal('hide');
                submitForm(1);
            });
        }
    };
</script>