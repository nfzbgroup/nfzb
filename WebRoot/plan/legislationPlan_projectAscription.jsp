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
            <span >项目归属</span>
        </li>
    </ul>
    <button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<form id="legislationProjectAscriptionForm" class="form-horizontal"
		  novalidate="novalidate">
        <input hidden name="stItemId" <c:if test="${legislationPlanItem.stItemId !=null}">value="${legislationPlanItem.stItemId}" </c:if>>
        <div class="form-body">
            <div class="form-group">
                <label class="col-sm-3 control-label">立法项目归属：</label>
                <div class="col-sm-9">
                    <select class="form-control" name="stSuggest">
                        <option value="正式项目" <c:if test="${legislationPlanItem.stSuggest !=null&&legislationPlanItem.stSuggest =='正式项目'}">selected</c:if>>正式项目</option>
                        <option value="预备项目" <c:if test="${legislationPlanItem.stSuggest !=null&&legislationPlanItem.stSuggest =='预备项目'}">selected</c:if>>预备项目</option>
                        <option value="调研项目" <c:if test="${legislationPlanItem.stSuggest !=null&&legislationPlanItem.stSuggest =='调研项目'}">selected</c:if>>调研项目</option>
                    </select>
                </div>
            </div>
			<div class="form-group text-center">
                    <input type="button" class="btn btn-w-m btn-success"  value="提交" onclick="saveLegislationProjectAscription()"> &nbsp;&nbsp;
					<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
			</div>
		</div>
	</form>

</div>
<script>
    function saveLegislationProjectAscription() {
        var param=$('#legislationProjectAscriptionForm').formToJson();
        if(param.stSuggest==null||param.stSuggest==""){
            Duang.error("提示","请选择立法项目归属");
        }else {
            $.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationProjectAscription",param,function(data){
                if(data.success) {
                    $('#legislationProcessChildForm').modal('hide');
                    $('#'+data.stItemId).html(data.stSuggest);
                }else{
                    Duang.error("提示", "保存项目归属失败！");
                }
            });
        }
    };
</script>