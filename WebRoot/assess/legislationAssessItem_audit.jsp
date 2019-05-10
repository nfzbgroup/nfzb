<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
    <ul class="page-breadcrumb">
        <li>
            <span >评估项目 > </span>
        </li>
        <li>
            <span >审核</span>
        </li>
    </ul>
    <button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<form id="legislationAssessItemAuditForm" class="form-horizontal"
		  novalidate="novalidate">
        <input hidden name="stTaskId" value="${stTaskId}" >
        <div class="form-body">
            <div class="form-group">
                <label class="col-sm-3 control-label">评估项目名称：</label>
                <div class="col-sm-9">
                    <label class="form-control">${legislationAssessTask.stFlowId}</label>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">审核意见：</label>
                <div class="col-sm-9">
                    <textarea class="form-control" name="stActive"></textarea>
                </div>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label">备注：</label>
                <div class="col-sm-9">
                    <textarea class="form-control" name="stComment1" ></textarea>
                </div>
            </div>
			<div class="form-group text-center">
                    <input type="button" class="btn btn-w-m btn-success"  value="提交" onclick="saveLegislationAssessItemAudit()"> &nbsp;&nbsp;
					<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
			</div>
		</div>
	</form>

</div>
<script>
    function saveLegislationAssessItemAudit() {
        var param=$('#legislationAssessItemAuditForm').formToJson();
        if(param.stActive==null||param.stActive==""){
            Duang.error("提示","请输入审核意见");
        }else {
        	layer.close(layer.index);
        	layer.confirm('是否提交！', function(index) {
				layer.close(layer.index);
            $.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationAssessItemAudit",param,function(data){
                $('#legislationProcessForm').modal('hide');
                submitForm(1);
            });
        	});
            
        }
    };
</script>