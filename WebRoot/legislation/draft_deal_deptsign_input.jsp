<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span ><c:if test="${nodeId=='NOD_0000000122'}">部门会签征求</c:if><c:if test="${nodeId=='NOD_0000000161'}">部门会签</c:if>  > </span>
		</li>
		<li>
			<span >接收反馈</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
<h2 style="color: #E4243D;text-align: center;font-weight: bold;margin-bottom: 20px">意见反馈</h2>
        <div class="form-group text-center" >
            <label class="col-sm-2 control-label text-left">对应草案：</label>
            <label class="col-sm-3 control-label text-left">${legislationProcessDoc.stDocName}</label>
        </div>
	<table class="table table-border table-bordered">
		<thead>
			<tr>
				<th style="text-align: center">
					部门名称
				</th>
				<th style="text-align: center">
					是否反馈
				</th>
				<th style="text-align: center">
					反馈内容
				</th>
				<th style="text-align: center">
					反馈时间
				</th>
			</tr>
		</thead>
		<tbody>
		<c:if test="${legislationSendNoticeList !=null&&fn:length(legislationSendNoticeList)>0}">
			<c:forEach var="legislationSendNotice" items="${legislationSendNoticeList}" >
			   <tr>
					<td style="text-align: center">
						${legislationSendNotice.stTeamName}
					</td> 
					<td style="text-align: center">
						${legislationSendNotice.stNoticeStatus}
					</td> 
					<td style="text-align: center">
						 <c:choose>
					  <c:when test="${legislationSendNotice.stNoticeStatus!='已反馈'}">---</c:when>
					  <c:otherwise>${legislationSendNotice.stFeedbackContent}</c:otherwise>
					 </c:choose>
					</td> 
					<td style="text-align: center">
						<c:if test="${legislationSendNotice.stNoticeStatus!='已反馈'}" >
						---</c:if>
						<c:if test="${legislationSendNotice.stNoticeStatus=='已反馈'}" >
						<fmt:formatDate type="time" pattern="yyyy-MM-dd HH:mm:ss"
            value="${legislationSendNotice.dtFeekbackDate}" /></c:if>
                    </td>
			   </tr>
			</c:forEach>
		</c:if>
		</tbody>
	</table>
	
 	<div class="form-group">
		<label class="control-label">上传材料接收 </label>
	</div>	
	<%@include file="/legislation/file/attachUpload.jsp" %>
	
	
	<div class="form-group text-center">
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
</div>
<script>
    $(function () {
        $(".tab-left").css('width', $(window).width() * 0.1);
    });
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

</script>
