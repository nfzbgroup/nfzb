<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


	<link href="${basePath}/legislation/assets/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
	<link href="${basePath}/legislation/assets/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
	<link href="${basePath}/legislation/assets/css/animate.min.css" rel="stylesheet">
	<link href="${basePath}/legislation/assets/css/style.min.css?v=4.0.0" rel="stylesheet">
	<link href="${basePath}/legislation/assets/css/plugins/toastr/toastr.min.css" rel="stylesheet">
	<link href="${basePath}/legislation/assets/plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
	<link href="${basePath}/legislation/assets/css/myUtil.css" rel="stylesheet">

</head>

<body class="gray-bg">
<div class="wrapper animated fadeInRight" id="processIndex">
	<div class="row">
		<div class="ibox float-e-margins">
			<div class="ibox-content">
				<table class="table table-bordered">
					<thead>
						<tr>
							<th>序号</th>
							<th>类型名称</th>
							<th>必填样本总数</th>
							<th>样本总数</th>
							<th>操作</th>
						</tr>
					</thead>
					<tbody>

							<tr>
								<td>1</td>
								<td>立法计划</td>
								<td>*</td>
								<td>*</td>
								<td><a href="#" onclick="alert('待开发');">处理</a></td>
							</tr>
							<tr>
								<td>2</td>
								<td>立法过程</td>
								<td>*</td>
								<td>*</td>
								<td><a href="javaScript:void(0)" onclick="openSamplePage()" >处理(可点击)</a></td>
							</tr>

							<tr>
								<td>3</td>
								<td>立法评估</td>
								<td>*</td>
								<td>*</td>
								<td><a href="#" onclick="alert('待开发');">处理</a></td>
							</tr>
												<tr>
								<td>4</td>
								<td>立法清理</td>
								<td>*</td>
								<td>*</td>
								<td><a href="#" onclick="alert('待开发');">处理</a></td>
							</tr>
					</tbody>
				</table>
			</div>
			<div class="modal inmodal fade" id="legislationProcessForm" data-backdrop keyboard tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
					</div>
				</div>
			</div>
			<div class="modal inmodal fade" id="processIndexRootForm" data-backdrop keyboard tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true">
				<div class="modal-dialog" style="margin-top: 0px">
					<div class="modal-content">
					</div>
				</div>
			</div>
			<div class="modal inmodal fade" id="processIndexForm" data-backdrop keyboard tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
					</div>
				</div>
			</div>
			<div class="modal inmodal fade" id="processIndexChildForm" data-backdrop keyboard tabindex="-1" role="dialog" aria-labelledby="myModalLabel"  aria-hidden="true">
				<div class="modal-dialog">
					<div class="modal-content">
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
	<script src="${basePath}/legislation/assets/js/jquery.min.js?v=2.1.4"></script>
	<script src="${basePath}/legislation/assets/js/bootstrap.min.js?v=3.3.5"></script>
	<script src="${basePath}/legislation/assets/js/plugins/toastr/toastr.min.js"></script>
	<script src="${basePath}/legislation/assets/plugins/laydate/laydate.js"></script>
	<script src="${basePath}/legislation/assets/js/content.min.js?v=1.0.0"></script>
	<link href="${basePath}/legislation/assets/page/common.css" rel="stylesheet" type="text/css" />
	<script src="${basePath}/legislation/assets/page/page.js" type="text/javascript"></script>
	<script src="${basePath}/legislation/assets/page/common.js" type="text/javascript"></script>
	<script src="${basePath}/legislation/assets/util/util.js" type="text/javascript"></script>
	<script src="${basePath}/legislation/assets/js/plugins/layer/layer.min.js?v=2.0"></script>
	<script src="${basePath}/legislation/assets/util/ajaxfileupload.js" type="text/javascript"></script>
	<link href="${basePath}/legislation/assets/css/plugins/layout/css/layout.min.css" rel="stylesheet" type="text/css"/>
	
	
	<script type="text/javascript">
        $(function () {
            $.ajaxSetup({
                async : false
            });
            $('body').on('hidden.bs.modal', '.modal', function () {
                $(this).removeData('bs.modal');
            });
            $('#processIndexForm').on('hidden.bs.modal', function () {
                $(document.body).addClass("modal-open");
            });
            $('#legislationProcessForm').on('show.bs.modal', function () {
                $('#legislationProcessForm .modal-body').css('overflow', 'auto');
                $('#legislationProcessForm .modal-body').css('height', $(window).height());
                $('#legislationProcessForm .modal-dialog').css('width', $(window).width()*0.96);
            });
            $('#processIndexRootForm').on('show.bs.modal', function () {
                $('#processIndexRootForm .wrapper').css('overflow', 'auto');
                $('#processIndexRootForm .wrapper').css('height', $(window).height());
                $('#processIndexRootForm .modal-dialog').css('width', $(window).width());
            });
            $('#processIndexChildForm').on('show.bs.modal', function () {
                $('#processIndexChildForm .modal-body').css('overflow', 'auto');
                $('#processIndexChildForm .modal-body').css('height', $(window).height());
                $('#processIndexChildForm .modal-dialog').css('width', $(window).width()*0.9);
            });
            $('#processIndexForm').on('show.bs.modal', function () {
                $('#processIndexForm .modal-body').css('overflow', 'auto');
                $('#processIndexForm .modal-body').css('height', $(window).height());
                $('#processIndexForm .modal-dialog').css('width', $(window).width()*0.9);
            });
        });
        function openSamplePage() {
			$("#processIndexRootForm").modal({
				remote: "${basePath}/legislationProcessDoc/draft_doc_info.do?method=legislation_saple_flow"
			});
        };
	</script>
	
</body>

</html>