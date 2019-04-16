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
            <span >分办</span>
        </li>
    </ul>
    <button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<form id="legislationPlanSeparateForm" class="form-horizontal"
		  novalidate="novalidate">
        <input hidden name="stTaskId" value="${stTaskId}" >
        <div class="form-body">
            <div class="form-group">
                <label class="col-sm-3 control-label">选择办理处：</label>
                <div class="col-sm-9">
                    <select class="form-control" name="stTeamId" >
                       <c:if test="${teamList!=null&&fn:length(teamList)>0}">
                           <c:forEach var="team" items="${teamList}">
                               <option value="${team.id}" >${team.teamName}</option>
                           </c:forEach>
                       </c:if>
                    </select>
                </div>
            </div>
			<div class="form-group text-center">
                    <input type="button" class="btn btn-w-m btn-success"  value="确认分办" onclick="saveLegislationPlanSeparate()"> &nbsp;&nbsp;
					<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
			</div>
		</div>
	</form>

</div>
<script>
    function saveLegislationPlanSeparate() {
        var param=$('#legislationPlanSeparateForm').formToJson();
        if(param.stTeamId==null||param.stTeamId==""){
            Duang.error("提示","请选择办理处");
        }else {
            $.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationPlanSeparate",param,function(data){
                $('#legislationProcessForm').modal('hide');
                submitForm(1);
            });
        }
    }
</script>