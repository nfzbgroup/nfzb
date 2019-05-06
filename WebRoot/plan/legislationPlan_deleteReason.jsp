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
            <span ><c:if test="${button=='delete'}">删除</c:if><c:if test="${button=='back'}">退回</c:if></span>
        </li>
    </ul>
    <button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<form id="legislationPlanDeleteReasonForm" class="form-horizontal"
		  novalidate="novalidate">
        <input hidden name="stTaskId" value="${stTaskId}" >
        <div class="form-body">
            <div class="form-group">
                <label class="col-sm-3 control-label"><c:if test="${button=='delete'}">删除</c:if><c:if test="${button=='back'}">退回</c:if>原因：</label>
                <div class="col-sm-9">
                    <textarea class="form-control" name="stActive"></textarea>
                </div>
            </div>
			<div class="form-group text-center">
                    <input type="button" class="btn btn-w-m btn-success"  value="确认<c:if test="${button=='delete'}">删除</c:if><c:if test="${button=='back'}">退回</c:if>" onclick="saveLegislationPlanDeleteReason()"> &nbsp;&nbsp;
					<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
			</div>
		</div>
	</form>

</div>
<script>
    function saveLegislationPlanDeleteReason() {
        var param=$('#legislationPlanDeleteReasonForm').formToJson();
        if(param.stActive==null||param.stActive==""){
            if(${button=='delete'}){
                Duang.error("提示","请输入删除原因");
            }else{
                Duang.error("提示","请输入退回原因");
            }
        }else {
            layer.close(layer.index);
            if(${button=='delete'}){
                layer.confirm('是否删除！', function(index) {
                    layer.close(layer.index);
                    $.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationPlanDeleteReason",param,function(data){
                        $('#legislationProcessForm').modal('hide');
                        submitForm(1);
                    });
                });
            }else{
                layer.confirm('是否退回！', function(index) {
                    layer.close(layer.index);
                    $.post("../${requestUrl}?stNodeId=${nodeId}&method=goBackPlanProcess",param,function(data){
                        $('#legislationProcessForm').modal('hide');
                        submitForm(1);
                    });
                });
            }
        }
    }
</script>