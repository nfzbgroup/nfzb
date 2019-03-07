<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang="zh">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords" content="OA系统" />
<meta name="keywords" content="" />
<meta name="description" content="" />
<meta name="generator" content="Notepad++" />
<title>详细办理页面</title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/legislate/css/main.css" media="screen" />
<link rel="stylesheet"
	href="${basePath}/legislate/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${basePath}/legislate/css/sui-themes-green-append.css"
	media="screen" />
<script src="${basePath}/legislate/js/jquery.js"></script>
<script src="${basePath}/legislate/js/sui.js"></script>
<script src="${basePath}/legislate/js/tanchuang.js"></script>
</head>
<body class="body_bg_1">
	<form method="post" enctype="multipart/form-data"
		action="${basePath}/legislate/saveModel.do" name="modelForm">
		<!--头部结asd束-->
		<div class="conteng">
			<!--面包屑-->
			<div class="conteng-title">
				<span>当前位置：</span><em>范本管理-添加范本</em>
			</div>

			<!--内容区-->
			<div class="detai">
				<div class="detai-con">
					<div class="rows">
						<div class="control-group">
							<label class="control-label"> <span
								style="padding: 0 6px;">范本类型：</span>
							</label>
							<div>
								<s:select list="#{'1':'规章草案范本','2':'起草说明','3':'专家意见','4':'报请函'}" name="modelType" cssClass="input-xlarge"></s:select>
							</div>
						</div>
					</div>

					<div class="rows">
						<div class="control-group">
							<label class="control-label"> <span
								style="padding: 0 6px;">该范本应用环节：</span>
							</label>
							<div class="controls">
								<s:textfield name="activityType"/>
							</div>
						</div>
					</div>

					<div class="rows">
						<div class="control-group">
							<label class="control-label" style="vertical-align: top">
								<span style="padding: 0 6px;">使用说明：</span>
							</label>
							<div class="controls">
								<s:textarea  style="font-size: medium;font-family: 微软雅黑, 黑体, 宋体"  name="instructions" cssClass="text-area"></s:textarea>
							</div>
						</div>
					</div>

					<div class="rows">
						<div class="control-group">
							<label class="control-label"> <span
								style="padding: 0 6px;">范本信息：</span>
							</label>
							<div class="controls" style="width: 335px;">
								<s:file name="uploadFile"></s:file>
							</div>
						</div>
					</div>
				</div>


				<div class="rows">
					 <a href="#"
						class="sui-btn btn-primary01 btn-lg btn-margin"
						onclick="document.modelForm.submit();">提交</a>
						<a href="javascript:history.go(-1)"
						class="sui-btn btn-primary01 btn-lg btn-margin">返回</a>
				</div>


			</div>

		</div>
		<input name="op" value="add" type="hidden">
	</form>
</body>
</html>