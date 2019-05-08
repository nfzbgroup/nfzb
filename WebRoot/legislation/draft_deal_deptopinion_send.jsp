<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li><span><c:if test="${nodeId=='NOD_0000000123'}">部门意见征求</c:if>
				<c:if test="${nodeId=='NOD_0000000161'}">部门会签</c:if> > </span></li>
		<li><span>发送部门 </span></li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close"
		data-dismiss="modal">
		<span aria-hidden="true">&times;</span><span class="sr-only">Close</span>
	</button>
</div>
<div class="modal-body">
<h2 style="color: #E4243D;text-align: center;font-weight: bold;margin-bottom: 20px">征询意见发送部门</h2>
	  <div class="form-group">
		<label class="col-sm-3 control-label">对应草案：</label>
		<label class="col-sm-9 control-label text-left"><span style="font-size: 18px;">${legislationProcessDoc.stDocName}</span></label>
		<c:if test="${legislationProcessTask.stTaskStatus=='DONE'}">
			<label class="col-sm-3 control-label text-right">发送时间：</label> <label
			class="col-sm-9 control-label"><fmt:formatDate type="time" pattern="yyyy-MM-dd HH:mm:ss"
            value="${legislationProcessTask.dtDealDate}" /></label>
		</c:if>
	  </div>
	 <div class="form-group">
		<label class="col-sm-3 control-label">选择部门：</label>
		 <div class="col-sm-9">
			 <textarea class="form-control" id="teamName" readonly ondblclick="checkDepartment('委办局,市司法局处室,区县')"><c:if test="${legislationProcessTask.stBakTwo !=null}">${legislationProcessTask.stBakTwo}</c:if></textarea>
		 </div>
		 <input type="hidden" id="teamId" <c:if test="${legislationProcessTask.stBakOne!=null}">value="${legislationProcessTask.stBakOne}" </c:if>>
	</div>

	<div class="form-group text-center">
	  <c:if test="${legislationProcessTask.stTaskStatus=='TODO'}">
		<input type="button" class="btn btn-w-m btn-success" id="btnSave" name="btnSave" onclick="saveLegislationDemonstration()" value="保存">
		&nbsp;&nbsp; 
		<input type="button" class="btn btn-w-m btn-success" id="btnSave" name="btnSave" onclick="sendDept()" value="发送">
		&nbsp;&nbsp; 
	  </c:if>
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
</div>
<script>
	function saveLegislationDemonstration() {
        var teamId = $('#teamId').val();
        var teamName = $('#teamName').val();
        if (teamId!=null&&teamId!='') {
			$.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationDemonstration&stTaskId=${stTaskId}&stBakOne=" + teamId+"&stBakTwo="+teamName, function(data) {
				$('#processIndexForm').modal('hide');
				$('#${buttonId}').parent().attr("class", "cell row_items row_item4 bcg_blue border_width border_style border_radius border_color_red");
				Duang.success("提示", "保存成功");
			});
		} else {
			Duang.error("提示", "请选择征询单位");
		}
	};
	function sendDept() {
		var teamId = $('#teamId').val();
        var teamName = $('#teamName').val();
		if (teamId!=null&&teamId!='') {
			$.post("${basePath}/legislationProcessTask/draft_task_list.do?stNodeId=${nodeId}&method=nextProcess&stDocId=${legislationProcessDoc.stDocId}&stTaskId=${stTaskId}&teamId=" + teamId+"&teamName="+teamName, function(data) {
				if (data.success) {
					$('#processIndexForm').modal('hide');
					//alert(JSON.stringify(data));
					$.each(data.nodeChangeArray, function(index, item) {
						//改变当前按钮的背景颜色
						$('#' + item.node).parent().removeClass('bcg_gray').removeClass('bcg_blue').removeClass('bcg_green');
						$('#' + item.node).parent().addClass(item.colorSet);
						console.info("item.nodeHref------"+item.nodeHref);
						if (item.nodeHref != undefined && item.nodeHref != null && item.nodeHref != '') {
							$('#' + item.node).parent().attr('nodeHref', item.nodeHref);
						}
					});
					Duang.success("提示", "操作成功");
				} else {
					Duang.error("提示", "操作失败");
				}
			});
		} else {
			Duang.error("提示", "请选择征询单位");
		}
	};
	function checkDepartment(orgType) {
        var teamId = $('#teamId').val();
        $("#processIndexChildForm").modal({
            remote : "../${requestUrl}?method=openDepartmentCheckPage&teamId=" + teamId+"&orgType="+orgType
        });
    }
</script>
