<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>

<%@ page import="java.sql.*,
	com.wonders.fzb.legislation.services.*,
	com.wonders.fzb.legislation.beans.*,
	com.wonders.fzb.base.*
	"
%>

<!-- 根据ID查一遍doc表  获取到当前的doc表字段-->
<%
	com.wonders.fzb.base.kit.SpringKit ctx = new com.wonders.fzb.base.kit.SpringKit();
	com.wonders.fzb.legislation.services.LegislationProcessDocService legislationProcessDocService = (LegislationProcessDocService) ctx.getANTBean("legislationProcessDocService");
	String action=request.getParameter("action");
	if(action.equals("edit")){
		String stDocId=request.getParameter("stDocId");
		LegislationProcessDoc legislationProcessDoc =legislationProcessDocService.findById(stDocId);
	}
%>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>

<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">


<link href="${basePath}/legislation/assets/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/animate.min.css" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/style.min.css?v=4.0.0" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/main.css?v=4.0.0" rel="stylesheet">
<link href="${basePath}/legislation/assets/css/plugins/toastr/toastr.min.css" rel="stylesheet">

</head>

<body>
	<h5>草案发起</h5>
	<div class="ibox-content">
		<form class="form-horizontal m-t" role="form" action="${basePath }/legislationProcessDoc/draft_create_info.do" method="post" id="loginForm">
			<div class="form-group">
				<label class="col-sm-3 control-label">法规规章草案：</label>
				<div class="col-sm-8">
					<input type="text" id="docName" name="docName" class="form-control">
		
				</div>
			</div>
			<div class="form-group">
				<label class="col-sm-3 control-label">备注：</label>
				<div class="col-sm-8">
					<textarea id="stComment1" name="stComment1" class="form-control"
						></textarea>
				</div>
			</div>
	
			<div class="form-group">
				<div class="col-sm-4 col-sm-offset-3">
					<input type="button" class="btn btn-w-m btn-success" id="btnSave"
						name="btnSave" value="保存"> &nbsp;&nbsp; <input
						type="button" class="btn btn-w-m btn-success" id="btnSubmit"
						name="btnSubmit" value="提交">
				</div>
			</div>
		</form>
	</div>
	
	<script src="${basePath}/legislation/assets/js/jquery.min.js?v=2.1.4"></script>
	<script src="${basePath}/legislation/assets/js/bootstrap.min.js?v=3.3.5"></script>
	<script src="${basePath}/legislation/assets/js/plugins/layer/layer.min.js?v=2.0"></script>
	<script src="${basePath}/legislation/assets/js/content.min.js?v=1.0.1"></script>
	<script src="${basePath}/legislation/assets/js/plugins/validate/jquery.validate.min.js"></script>
	<script src="${basePath}/legislation/assets/js/plugins/validate/messages_zh.min.js"></script>
	<script src="${basePath}/legislation/assets/js/plugins/toastr/toastr.min.js"></script>
	
	<script type="text/javascript">
	
	$(function () {
		if("${param.action}"=="edit"){
			 document.getElementById('docName').value="${param.stDocName}";
			 var beizhu="${param.stComment1}";
			 if(beizhu==""){
				 document.getElementById('stComment1').value="kongde";
			 }else{
				 document.getElementById('stComment1').value="${param.stComment1}";
			 }
			 
		}
	});
	
	//保存
    $('#btnSave').click(function () {
    	var protocolName = $('#docName').val();
    	if(protocolName == ""){
    		toastr.error("请输入草案名称", "提示");
    		return;
    	}
    	
    	$.ajax({
    	 		"type":"POST",
    	 		"dataType":"json",
    	 		"url":"${basePath}/legislationProcessDoc/draft_create_info.do",
    	 		"data":{"action":'${param.action}',"docId":'${param.stDocId}',"stNodeId":'${param.stNodeId}',"stNodeName":'${param.stNodeName}',"docName":$('#docName').val(),"stComment1":$('#stComment1').val()},
    	 		"success":function(data){
    	 			if(data.code==200){
    	 				alert(data.message);
	      	 			if(data.message==""){
	 					window.location.href=('/');
	 				}else{
	 					window.location.href=('/');
	 				}
    	 			}
    	 			window.close();
    	 		}
    	 	})
    	
    });
    
 	 //提交
    $('#btnSubmit').click(function () {
    	window.close();
    });
  
    toastr.options = {
	  		  "closeButton": true,
	  		  "debug": false,
	  		  "progressBar": false,
	  		  "positionClass": "toast-top-center",
	  		  "onclick": null,
	  		  "showDuration": "400",
	  		  "hideDuration": "1000",
	  		  "timeOut": "4000",
	  		  "extendedTimeOut": "1000",
	  		  "showEasing": "swing",
	  		  "hideEasing": "linear",
	  		  "showMethod": "fadeIn",
	  		  "hideMethod": "fadeOut"
	  		}

    	</script>
	
</body>

</html>