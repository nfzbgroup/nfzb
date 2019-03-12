<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div class="page-bar">
	<ul class="page-breadcrumb">
		<li>
			<span >单位意见征求 ></span>
		</li>
		<li>
			<span >接收情况</span>
		</li>
	</ul>
	<button style="padding-right: 5px" type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
</div>
<div class="modal-body">
	<table class="table table-border table-bordered">
		<thead>
		<tr>
			<th style="text-align: center">
				单位名称
			</th>
			<th style="text-align: center">
				响应情况
			</th>
			<th style="text-align: center">
				响应时间
			</th>
			<th style="text-align: center">
				反馈内容
			</th>
		</tr>
		</thead>
		<tbody>
		<tr>
			<td style="text-align: center">
				未绑定接口
			</td>
			<td style="text-align: center">
				已反馈
			</td>
			<td style="text-align: center">
				2019-08-30 20:18:36
			</td>
			<td style="text-align: center">
				上海草案.doc
			</td>
		</tr>
		<tr>
			<td style="text-align: center">
				未绑定接口2
			</td>
			<td style="text-align: center">
				未接收
			</td>
			<td style="text-align: center">

			</td>
			<td style="text-align: center">
				<label class="btn btn-success" onclick="toUploadFile(this)">点击上传</label>
				<input id="2" name="upload" type="file" style="display:none"  onchange="uploadFile(this.id)">
			</td>
		</tr>
		<tr>
			<td style="text-align: center">
				未绑定接口3
			</td>
			<td style="text-align: center">
				未接收
			</td>
			<td style="text-align: center">

			</td>
			<td style="text-align: center">
				<label class="btn btn-success" onclick="toUploadFile(this)">点击上传</label>
				<input id="3" name="upload" type="file" style="display:none"  onchange="uploadFile(this.id)">
			</td>
		</tr>
		<tr>
			<td style="text-align: center">
				未绑定接口4
			</td>
			<td style="text-align: center">
				未接收
			</td>
			<td style="text-align: center">

			</td>
			<td style="text-align: center">
				<label class="btn btn-success" onclick="toUploadFile(this)">点击上传</label>
				<input id="4" name="upload" type="file" style="display:none"  onchange="uploadFile(this.id)">
			</td>
		</tr>
		<tr>
			<td style="text-align: center">
				未绑定接口5
			</td>
			<td style="text-align: center">
				未接收
			</td>
			<td style="text-align: center">

			</td>
			<td style="text-align: center">
				<label class="btn btn-success" onclick="toUploadFile(this)">点击上传</label>
				<input id="5" name="upload" type="file" style="display:none"  onchange="uploadFile(this.id)">
			</td>
		</tr>
		</tbody>
	</table>
	<div class="form-group text-center">
		<input type="button" class="btn btn-w-m btn-success" data-dismiss="modal" value="关闭">
	</div>
</div>
<script>
    function toUploadFile(obj) {
        $(obj).next().click();
    }
    function uploadFile(id) {
        <%--$.ajaxFileUpload({--%>
            <%--url: '${basePath}/file/upload.do?stNodeId=NOD_0000000101&stSampleId='+stSampleId,--%>
            <%--type: 'post',--%>
            <%--secureuri: false,                       //是否启用安全提交,默认为false--%>
            <%--fileElementId: id,--%>
            <%--dataType: 'JSON',--%>
            <%--success: function (data, status) {        //服务器响应成功时的处理函数--%>
                <%--data = data.replace(/<.*?>/ig, "");  //ajaxFileUpload会对服务器响应回来的text内容加上<pre>text</pre>前后缀--%>
                <%--var file = JSON.parse(data);--%>
                <%--if (file.success) {--%>
					$("#"+id).parent().prev().prev().html('已反馈');
					$("#"+id).parent().prev().html("2019-03-10 20:18:36");
        			$("#"+id).parent().html("上海市交通条例草案.doc");
                    Duang.info("提示", "上传材料成功");
        //         } else {
        //             Duang.error("提示", "上传材料失败");
        //         }
        //     },
        //     error: function (data, status, e) { //服务器响应失败时的处理函数
        //         Duang.error("提示", "上传材料失败");
        //     }
        // });
    }
</script>
