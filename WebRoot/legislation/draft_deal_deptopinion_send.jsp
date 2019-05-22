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
	<button style="padding-right: 10px;padding-top: 8px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
<h2 style="color: #E4243D;text-align: center;font-weight: bold;margin-bottom: 20px">征询意见发送部门</h2>
<form id="unitDemonstrationForm" class="form-horizontal"
      novalidate="novalidate">
	<input type="hidden" id="nodeStatus" value="${nodeStatus}">
	    <input type="hidden" name="stDocId" value="${legislationProcessDoc.stDocId}">
	  <div class="form-group">
		<label class="col-sm-3 control-label">对应草案：</label>
		<label class="col-sm-9 control-label" style="text-align:left"><span style="font-size: 18px;">${legislationProcessDoc.stDocName}</span></label>
	  </div>
	 <div class="form-group">
		<label class="col-sm-3 control-label">选择部门：</label>
		 <div class="col-sm-9">
			 <textarea class="form-control" id="teamName" name="teamName" readonly ondblclick="checkDepartment('委办局,市司法局处室,区县')"><c:if test="${legislationProcessTask.stBakTwo !=null}">${legislationProcessTask.stBakTwo}</c:if></textarea>
		 </div>
		 <input type="hidden" id="teamId" <c:if test="${legislationProcessTask.stBakOne!=null}">value="${legislationProcessTask.stBakOne}" </c:if>>
	</div>

 	<div class="form-group">
		<label class="control-label">上传材料接收 </label>
	</div>	
	<%@include file="/legislation/file/attachUpload.jsp" %>


	<div class="form-group text-center">
	  <c:if test="${legislationProcessTask.stTaskStatus=='TODO'}">
		<input type="button" class="btn btn-w-m btn-success" id="btnSave" name="btnSave" onclick="saveLegislationDemonstration()" value="保存">
		&nbsp;&nbsp; 
		<input type="button" class="btn btn-w-m btn-success" id="btnSave" name="btnSave" onclick="sendDept()" value="发送">
		&nbsp;&nbsp; 
	  </c:if>
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
	</form>
</div>
<script>

//上传按钮点击事件
function toUploadFile(obj) {
	$(obj).next().click();
};
//上传材料到数据库表LEGISLATION_FILES事件
function uploadFile(id, type, stSampleId) {
	$.ajaxFileUpload({
		url : '${basePath}/file/upload.do?nodeStatus=${nodeStatus}&stNodeId=${nodeId}&stSampleId=' + stSampleId,
		type : 'post',
		secureuri : false, //是否启用安全提交,默认为false
		fileElementId : id,
		dataType : 'JSON',
		success : function(data, status) { //服务器响应成功时的处理函数
			data = data.replace(/<.*?>/ig, ""); //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀
			var file = JSON.parse(data);
			if (file.success) {
				if (type == 1) {
					var html = '<a target="_blank" href="${basePath}/file/downloadAttach.do?name=' + file.name + '&url=' + file.url + '">下载</a>&nbsp;&nbsp;' + '<input type="hidden" id="'+file.fileId+'"  name="'+file.fileId+'" value='+file.fileId+'>' + '<label  style="color: red" onclick="deleteAttach(this,1,\'' + id + '\',\'' + file.fileId + '\',\'' + stSampleId + '\')" >删除</label>';
					$("#" + id).parent().prev().html('<span>' + file.name + '</span>');
					$("#" + id).parent().html(html);
				} else {
					var html = '<tr class="text-center">' + '<td class="text-left">需要报送的其他材料</td>' + '<td>' + file.name + '</td>' + '<td><a  target="_blank" href="${basePath}/file/downloadAttach.do?name=' + file.name + '&url=' + file.url + '">下载</a>&nbsp;&nbsp;' + '<label  style="color: red" onclick="deleteAttach(this,2,\'' + id + '\',\'' + file.fileId + '\',\'' + stSampleId
							+ '\')">删除</label>' + '<input type="hidden" id="'+file.fileId+'"  name="'+file.fileId+'" value='+file.fileId+'>' + '</td></tr>';
					$('#otherMaterial').append(html);
				}
				Duang.success("提示", "上传材料成功");
			} else {
				Duang.error("提示", "上传材料失败");
			}
		},
		error : function(data, status, e) { //服务器响应失败时的处理函数
			Duang.error("提示", "上传材料失败");
		}
	});
};


//文件删除按钮事件
function deleteAttach(attachObj, type, id, fileId, stSampleId) {
	$.post('${basePath}/file/deleteAttach.do?fileId=' + fileId);
	var obj = $(attachObj);
	if (type == 1) {
		obj.parent().prev().html('<span style="color: red">暂未上传</span>');
		var html = '<label class="btn btn-w-m btn-success"  onclick="toUploadFile(this)">点击上传</label>' + '<input id="' + id + '" name="upload" type="file" style="display:none"  onchange="uploadFile(\'' + id + '\',1,\'' + stSampleId + '\')">';
		obj.parent().html(html);
	} else {
		obj.parent().parent().remove();
	}
};

function saveLegislationDemonstration() {
    var param=$('#unitDemonstrationForm').formToJson();
    var teamId = $('#teamId').val();
    var teamName = $('#teamName').val();
    if(teamId==null&&teamId==''){
        Duang.error("提示","请选择征询单位");
    }else {
        $.post("../${requestUrl}?stNodeId=${nodeId}&method=saveLegislationDemonstration&stTaskId=${stTaskId}&stBakOne=" + teamId+"&stBakTwo="+teamName,param,function(data){
            if(data.success){
                $('#processIndexForm').modal('hide');
                $('#${nodeId}').parent().removeClass('bcg_gray').removeClass('bcg_blue').removeClass('bcg_green').addClass('bcg_green');
                Duang.success("提示","操作成功");
            }else{
                Duang.error("提示","操作失败");
            }
        });
    }
};
	/* function saveLegislationDemonstration() {
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
	}; */
	function sendDept() {
		var teamId = $('#teamId').val();
        var teamName = $('#teamName').val();
		if (teamId!=null&&teamId!='') {
			$.post("${basePath}/legislationProcessTask/draft_task_list.do?stNodeId=${nodeId}&method=nextProcess&stDocId=${legislationProcessDoc.stDocId}&stTaskId=${stTaskId}&teamId=" + teamId+"&teamName="+teamName, function(data) {
				if (data.success) {
					$('#processIndexForm').modal('hide');
					$.each(data.nodeChangeArray, function(index, item) {
						//改变当前按钮的背景颜色
						$('#' + item.node).parent().removeClass('bcg_gray').removeClass('bcg_blue').removeClass('bcg_green');
						$('#' + item.node).parent().addClass(item.colorSet);
						console.info("item.nodeHref------"+item.nodeHref);
						if (item.nodeHref != undefined && item.nodeHref != null && item.nodeHref != '') {
							$('#' + item.node).parent().attr('nodeHref', item.nodeHref);
						}
					});
					$('#' + data.nodeChangeArray[1].node).parent().removeClass('bcg_gray').removeClass('bcg_blue').removeClass('bcg_green').addClass('bcg_blue');
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
