<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span >参会人员选择 </span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
<c:if test="${participantsList !=null&&fn:length(participantsList)>0}">
	<c:forEach var="p" items="${participantsList}">
		<label>${p.teamName}&nbsp;&nbsp;<input type="checkbox" propertyType="team" id="${p.teamId}" onchange="changeType(this.id)"></label>
		<table class="table table-border table-bordered">
			<thead>
			<tr>
				<th style="text-align: center">
					选择
				</th>
				<th style="text-align: center">
					人员名称
				</th>
				<th style="text-align: center">
					选择
				</th>
				<th style="text-align: center">
					人员名称
				</th>
				<th style="text-align: center">
					选择
				</th>
				<th style="text-align: center">
					人员名称
				</th>
			</tr>
			</thead>
			<tbody>
			<c:if test="${p.userInfoList !=null&&fn:length(p.userInfoList)>0}">
				<tr>
				<c:forEach var="u" items="${p.userInfoList}" varStatus="st">
						<td style="text-align: center">
							<input type="checkbox" <c:if test="${u.checked !=null&&u.checked}">checked</c:if> propertyType="staff" name="${p.teamId}" value="${u.userId}" onchange="changeTypeChild(this.name)" >
						</td>
						<td style="text-align: center">
								${u.name}
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
    <c:if test="${otherPersons=='Y'}">
	  <div class="form-group">
		<label class="col-sm-2 control-label">外部人员：</label>
		<div class="col-sm-9">
			<textarea class="form-control" id="others" >${otherPersonsName}</textarea>
		</div>
	  </div>
	</c:if>
	<div class="form-group text-center">
		<input type="button" class="btn btn-w-m btn-success" id="btnSave"
			   name="btnSave" onclick="checkedParticipants()" value="确定"> &nbsp;&nbsp;
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
</div>
<script>
	$(function () {
        var teamId="";
        $('input:checkbox[propertyType="staff"]:checked').each(function () {
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
	function checkedParticipants() {
	    var userId="";
	    var userName="";
        var checkedNum=0;
        var shut='${process}';
        var others=$('#others').val();
        $('input:checkbox[propertyType="staff"]:checked').each(function () {
            userId=userId+","+this.value;
            userName=userName+","+$(this).parent().next().html().replace(/\s+/g,"");
            checkedNum++;
        });
        if(checkedNum>0){
            if(others!=null&&others!=''){
				userName=userName+","+others
            }
            if(shut=='process'){
            	$("#processIndexChildForm").modal('hide');
            }else{
            	$("#processIndexForm").modal('hide');
            }
            $('#stPersons').val(userName.substring(1));
            $('#stPersonsId').val(userId.substring(1));
            $('#otherPersonsName').val(others);
        }else{
            Duang.error("提示","请选择参会人员");
        }
    }
</script>
