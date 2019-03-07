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
        <span>当前位置：</span><em>填写反馈</em>
    </div>
 	<form method="post" enctype="multipart/form-data" action="${basePath}/legislate/teamOpinion_fk_uploadFile.do" name="draftForm" id="draftForm">
 	<s:hidden name="teamOpinionDetailId"></s:hidden>
    <!--内容区-->


		<div class = "tab-content" style = "display: block">
		    <div class = "clearfix"></div><br/><br/>
		    <div class = "container-fluid container-top">
		        <div class = "row">
		            <div class = "col-sm-1 text-color " style = "float: left">草案名称:</div>
		            <div class = "col-sm-2 " style = "float: left">
		                <span class = "szgb"><s:property value="%{teamOpinionDetail.teamOpinion.draft.draftName}"/></span>
		            </div>
		        </div>
		    </div>
		    <div class = "clearfix"></div>
		    
		    <div class="row rh"></div><br/><br/>
		        <label class="formlabel" style="font-size: 16px;">相关材料上传:</label>
		        <s:if test="teamOpinionDetail.opinionInfo == null">
			        <span class="formdiv" style="width: 50%">
			            <input type = "text" style="width: 77%;"id="tp"  class="hidden" value="http://">
			            
			            <!--<select name = "" id="togs">
			                 <option value = "1">非音视频</option>
			                <option value = "0">音视频</option>
			            </select>-->
			           <div id="fl">
			                <a href="javascript:void (0)" class="file sui-btn btn-primary01 btn-lg btn-120" style="line-height: 30px;">
			            点击上传材料
			            <input type="file" id="filevalue" name="uploadFile">
			          </a>
			            <p class="file-text">
			            未选择文件
			           </p>
			           </div>
			        </span>
		        </s:if>
		        <s:if test="teamOpinionDetail.opinionInfo != null">
		         <a  href = "${basePath}/legislate/teamOpinionDetailFileRecordLoad.do?teamOpinionDetailId=${teamOpinionDetailId}"><s:property value="%{teamOpinionDetail.opinionInfo}"/></a>
				</s:if>
		    </div>
			</form>
		</div>
		
		<div class="clearfix" style="margin-top: 20px;padding-bottom: 50px">
		    <div class="container-fluid container-top">
		        <div class="row rh">
		            <div class="col-sm-12 text-center">
		            <s:if test="op=='fk'">
		            	<button type="button" class="btn btn-info btn-bg btn-120" id="frameindex" onclick="uploadDraftFile();">提交</button>
		             </s:if>
		                <a href = "#" class = "sui-btn btn-primary01 btn-lg btn-170 layer-close" id="close">关闭</a>
		            </div>
		        </div>
		    </div>
		</div>
</div>
</div>
<script> 

	
 	function uploadDraftFile(){
		document.draftForm.action = '${basePath}/legislate/teamOpinion_fk_uploadFile.do';
		alert("反馈成功");
		document.draftForm.submit();
		
	} 
	
/*     $("#frameindex").click(function(){
		$.ajax({
   	 		"type":"POST",
   	 		"dataType":"json",
   	 		"url":"${basePath}/legislate/teamOpinion_fk_uploadFile.do",
   	 		"data":$("#draftForm").serialize(),
   	 		"success":function(data){
   	 				parent.location.href(parent.location.href);
   	 		}
   	 	}) 
	}) */
	
	
    $(function () {
//        var linum=$('.file-list li').size()*10
//        console.log(linum)
//        $('.detai').height($(window).height()+linum)
        $('input[type="file"]').change(function (e) {
            var filename = $(this).val().split("\\").pop();
            $('.file-text').text(filename)
        })
        
        $("#close").click(function(){
        	parent.document.form1.submit();
        });
        
    })
</script>
</body>
</html>