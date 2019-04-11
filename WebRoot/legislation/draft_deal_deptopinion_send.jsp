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
	  <div class="form-group">
		<label class="col-sm-2 control-label">对应草案：</label> <label
			class="col-sm-7 control-label">${legislationProcessDoc.stDocName}</label>
		<c:if test="${legislationProcessTask.stTaskStatus=='DONE'}">
			<label class="col-sm-2 control-label">发送时间：</label> <label
			class="col-sm-2 control-label"><fmt:formatDate type="time" pattern="yyyy-MM-dd HH:mm:ss"
            value="${legislationProcessTask.dtDealDate}" /></label>
		</c:if>
	  </div>
	<table class="table table-border table-bordered" >
		<thead>
			<tr>
			  <c:if test="${legislationProcessTask.stTaskStatus=='TODO'}">
				<th><input type="checkbox" id="all" onchange="changeType(this)">
				</th>
		      </c:if>
				<th style="text-align: center">单位名称</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${teamInfoList !=null&&fn:length(teamInfoList)>0}">
				<c:forEach var="t" items="${teamInfoList}" varStatus="status">
					<tr>
					  <c:if test="${legislationProcessTask.stTaskStatus=='TODO'}">
						<td><input type="checkbox"
							<c:forEach items="${deptIds}" var="id" >
                                   <c:if test="${id==t.id}">
                                               checked="checked"
                                   </c:if>
                         </c:forEach>
							name="dept" value="${t.id}"></td>
					  </c:if>
						<td style="text-align: center">${t.teamName}</td>
					</tr>
				</c:forEach>
			</c:if>
		</tbody>
	</table>

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
	$(function() {
		$(".tab-left").css('width', $(window).width() * 0.1);
		$('[name="dept"]').change(function() {
			var num = $('[name="dept"]').size();
			var checkedNum = 0;
			$('[name="dept"]').each(function() {
				if ($(this).prop("checked")) {
					checkedNum++;
				}
			});
			if (num == checkedNum) {
				$("#all").prop("checked", true);
			} else {
				$("#all").prop("checked", false);
			}
		});
	});
	function changeType(obj) {
		if ($(obj).prop("checked")) {
			$('[name="dept"]').prop("checked", true);
		} else {
			$('[name="dept"]').prop("checked", false);
		}
	};
	function saveLegislationDemonstration() {
		var teamId = "";
		var checkedNum = 0;
		$('[name="dept"]:checked').each(function() {
			teamId = teamId + "," + this.value;
			checkedNum++;
		});
		if (checkedNum > 0) {
			$.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationDemonstration&stTaskId=${stTaskId}&stBakOne=" + teamId.substring(1), function(data) {
				$('#processIndexForm').modal('hide');
				$('#${buttonId}').parent().attr("class", "cell row_items row_item4 bcg_blue border_width border_style border_radius border_color_red");
				Duang.success("提示", "保存成功");
			});
		} else {
			Duang.error("提示", "请选择征询单位");
		}
	};
	function sendDept() {
		var teamId = "";
		var checkedNum = 0;
		$('[name="dept"]:checked').each(function() {
			teamId = teamId + "," + this.value;
			checkedNum++;
		});
		if (checkedNum > 0) {
			$.post("${basePath}/legislationProcessTask/draft_task_list.do?stNodeId=${nodeId}&method=nextProcess&stDocId=${legislationProcessDoc.stDocId}&stTaskId=${stTaskId}&teamId=" + teamId.substring(1), function(data) {
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
	}
</script>
