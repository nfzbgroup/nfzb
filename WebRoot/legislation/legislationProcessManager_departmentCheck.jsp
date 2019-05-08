<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span >部门选择 </span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
<c:if test="${teamList !=null&&fn:length(teamList)>0}">
	<c:forEach var="p" items="${teamList}">
		<label>${p.typeName}&nbsp;&nbsp;<input type="checkbox" propertyType="parent" id="${p.typeName}" onchange="changeType(this.id)"></label>
		<table class="table table-border table-bordered">
			<thead>
			<tr>
				<th style="text-align: center">
					选择
				</th>
				<th style="text-align: center">
					部门名称
				</th>
				<th style="text-align: center">
					选择
				</th>
				<th style="text-align: center">
					部门名称
				</th>
				<th style="text-align: center">
					选择
				</th>
				<th style="text-align: center">
					部门名称
				</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${p.teamInfoList !=null&&fn:length(p.teamInfoList)>0}">
				<tr>
				<c:forEach var="t" items="${p.teamInfoList}" varStatus="st">
						<td style="text-align: center">
							<input type="checkbox" <c:if test="${t.checked !=null&&t.checked}">checked</c:if> propertyType="child" name="${p.typeName}" value="${t.id}" onchange="changeTypeChild(this.name)" >
						</td>
						<td style="text-align: center">
								${t.teamName}
						</td>
					<c:if test="${(st.count % 3) eq '0'}"></tr></c:if>
				</c:forEach>
					<tr>
				</tr>
			</c:if>
			</tbody>
		</table>
	</c:forEach>
</c:if>
	<div class="form-group text-center">
		<input type="button" class="btn btn-w-m btn-success" id="btnSave"
			   name="btnSave" onclick="checkedDepartment()" value="确定"> &nbsp;&nbsp;
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
</div>
<script>
	$(function () {
        var teamId="";
        $('input:checkbox[propertyType="child"]:checked').each(function () {
            if(teamId.indexOf(this.name)==-1){
                teamId=teamId+","+this.name;
            }
        });
        if(teamId.length>0){
            var teamIdArray=teamId.substring(1).split(",");
            for (var i = 0; i < teamIdArray.length; i++) {
				changeTypeChild(teamIdArray[i])
            }
        }
    });

	function changeType(id) {
		if($('#'+id).prop("checked")){
            $('[name='+id+']').prop("checked",true);
		}else{
            $('[name='+id+']').prop("checked",false);
		}
    };
	
	function changeTypeChild(name) {
        var num=$('[name='+name+']').size();
        var checkedNum=0;
        $('[name='+name+']').each(function () {
            if($(this).prop("checked")){
                checkedNum++;
            }
        });
        if(num==checkedNum){
            $("#"+name).prop("checked",true);
        }else{
            $("#"+name).prop("checked",false);
        }
    }
	function checkedDepartment() {
	    var teamId="";
	    var teamName="";
        var checkedNum=0;
        $('input:checkbox[propertyType="child"]:checked').each(function () {
            teamId=teamId+","+this.value;
            teamName=teamName+","+$(this).parent().next().html().replace(/\s+/g,"");
            checkedNum++;
        });
        if(checkedNum>0){
            $("#processIndexChildForm").modal('hide');
            $('#teamId').val(teamId.substring(1));
            $('#teamName').val(teamName.substring(1));
        }else{
            Duang.error("提示","请选择部门");
        }
    }
</script>
