<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang = "zh">
<head>
    <meta http-equiv = "X-UA-Compatible" content = "IE=8">
    <meta http-equiv = "Content-Type" content = "text/html; charset=utf-8"/>
    <meta name = "keywords" content = "OA系统"/>
    <meta name = "keywords" content = ""/>
    <meta name = "description" content = ""/>
    <meta name = "generator" content = "Notepad++"/>
    <title>规章草案发起</title>
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${basePath}/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${basePath}/legislate/js/jquery.js"></script>
    <script src = "${basePath}/legislate/js/sui.js"></script>
    <script src = "${basePath}/legislate/js/tanchuang.js"></script>
</head>
<body class = "body_bg_1">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->
 
    <div class = "conteng-title">
        <span>当前位置：</span><em>文件上传</em>
    </div>
 	<form method="post" enctype="multipart/form-data" action="${basePath}/legislate/uploadPlanFile.do" name="planForm">
 	<input type="hidden" id="callBack" name="callBack">
    <!--内容区-->
    <div class = "detai">
        <div class = "detai-con">
            <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                        <label class="formlabel" >上传附件资料:</label>
                        <span class="formdiv">
                                <a href="javascript:void (0)" class="file sui-btn btn-primary01 btn-lg btn-120" style="line-height: 30px;">
                            点击选择材料
                             <input type="file" id="filevalue" name="uploadFile">
                             <input type="hidden" id="activityType" value="${param.activityType }" name="activityType">
                             <input type="hidden" id="bizType" value="${param.bizType }" name="bizType">
                             <input type="hidden" id="op" value="update" name="op">
                             <input type="hidden" id="op" value="0" name="savePlan">
                             <input type="hidden" id="op" value="${param.planId }" name="planId">
                          </a>
                            <p class="file-text">
                            未选择文件
                           </p>
                            </span>
                           
                    </div>
                    
                </div>
            </div>
            <div class="clearfix">
                    <div class="container-fluid container-top">
                        <div class="row rh">
                            <div class="col-sm-12 text-center">
                            	<button type="button" class="btn btn-info btn-bg btn-120" onclick="uploadDraftFile();">提交</button>
                            </div>
                        </span>
                        </div>
                    </div>
                </div>
            
        </div>
    </div>
	</form>
</div>
</div>
 
<script> 
/* var uuu; */


	var message = '${message}';
	if(message != ''){
		alert(message);
		parent.callBack();

	}
	
	function uploadDraftFile(){
		document.planForm.action = '${basePath}/legislate/uploadPlanFile.do';
		document.planForm.submit();
	}
	
    $(function () {
//        var linum=$('.file-list li').size()*10
//        console.log(linum)
//        $('.detai').height($(window).height()+linum)
        $('input[type="file"]').change(function (e) {
            var filename = $(this).val().split("\\").pop();
            $('.file-text').text(filename)
        })
    })
    function deleteFile(fileRecordId){
		 if(confirm("确定要删除吗？")){
   	 	$.ajax({
   	 		"type":"POST",
   	 		"dataType":"json",
   	 		"url":"${basePath}/legislate/deleteFile.do",
   	 		"data":{"fileRecordId":fileRecordId},
   	 		"success":function(data){
   	 			if(data.code==200){
   	 				alert(data.message);
   	 				location.href = '${basePath}/legislate/savePlan.do?planId=${planId}';
   	 			}
   	 		}
   	 	}) 
       }
	}
</script>
</body>
</html>