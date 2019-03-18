<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span >单位意见征求</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<table class="table table-border table-bordered">
		<thead>
			<tr>
				<th>
					<input type="checkbox" id="all" onchange="changeType(this)">
				</th>
				<th style="text-align: center">
					单位名称
				</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${teamInfoList !=null&&fn:length(teamInfoList)>0}">
			<c:forEach var="t" items="${teamInfoList}">
				<tr>
					<td>
						<input type="checkbox" name="unit" value="${t.id}">
					</td>
					<td style="text-align: center">
						${t.teamName}
					</td>
				</tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>
	<div class="form-group text-center">
		<input type="button" class="btn btn-w-m btn-success" id="btnSave"
			   name="btnSave" onclick="sendUnit()" value="确定"> &nbsp;&nbsp;
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
</div>
<script>
    $(function () {
        $(".tab-left").css('width', $(window).width() * 0.1);
        $('[name="unit"]').change(function () {
            var num=$('[name="unit"]').size();
            var checkedNum=0;
            $('[name="unit"]').each(function () {
				if($(this).prop("checked")){
					checkedNum++;
				}
            });
			if(num==checkedNum){
				$("#all").prop("checked",true);
			}else{
                $("#all").prop("checked",false);
			}
        });
    });
	function changeType(obj) {
		if($(obj).prop("checked")){
            $('[name="unit"]').prop("checked",true);
		}else{
            $('[name="unit"]').prop("checked",false);
		}
    };
	function sendUnit() {
	    var teamId="";
        var checkedNum=0;
        $('[name="unit"]:checked').each(function () {
			teamId=teamId+","+this.value;
            checkedNum++;
        });
        if(checkedNum>0){
            $.post("../${requestUrl}?stNodeId=${nodeId}&method=sendUnit&stTaskId=${stTaskId}&teamId="+teamId.substring(1),function(data){
				$('#processIndexForm').modal('hide');
				$('#${buttonId}').attr("class","btn btn-primary process-btn");
				$('#${buttonId}').parent().next().children().attr("class","btn btn-primary process-btn");
				$('#${buttonId}').parent().next().children().disabled=false;
				Duang.success("提示","发送成功");
            });
        }else{
            Duang.error("提示","请选择征询单位");
        }
    }
</script>
