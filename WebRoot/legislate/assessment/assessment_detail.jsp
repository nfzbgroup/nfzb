<%@page import="com.wonders.fzb.legislate.beans.*"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<html lang = "zh">
<head> 
    <meta http-equiv = "X-UA-Compatible" content = "IE=8">
    <meta http-equiv = "Content-Type" content = "text/html; charset=utf-8"/>
    <meta name = "keywords" content = "OA系统"/>
    <meta name = "keywords" content = ""/>
    <meta name = "description" content = ""/>
    <meta name = "generator" content = "Notepad++"/>
    <title>规章立法后评估项目详情</title>
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${basePath}/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${basePath}/legislate/js/jquery.js"></script>
    <script src = "${basePath}/legislate/js/sui.js"></script>
    <script src = "${basePath}/legislate/js/tanchuang.js"></script>
    <script src = "js/layer/layer.js"></script>
</head>
<body onload="blockChange()" class = "body_bg_1">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->
 
    <div class = "conteng-title">
        <span>当前位置：</span><em>规章立法后评估项目详情-首页</em>
    </div>
 	<form method="post" enctype="multipart/form-data" action="${basePath}/legislate/assessment_add.do" name="form1">
    <!--内容区-->
    <div class = "detai">
        <div class = "detai-con">
            <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                        <label class="formlabel">后评估项目名称:</label>
                        <span class="formdiv">
                           <s:textfield name="assessmentTitle" id="assessmentTitle"></s:textfield>
                        </span>
                    </div>
                    <div class="row rh">
                        <label class="formlabel" style="white-space: nowrap;">后评估项目申报表（.doc）下载:</label>
                        <a href="${basePath }/legislate/assessmentAttachDownload.do?assessmentId=<s:property value='#request.assessment.assessmentId' />"><s:property value='#request.assessment.assessmentFileName' /></a>
                    </div>
                    <div class="legislate-btnGroup">
	                    <s:if test="#request.currentTeam.id=='U_3_3'">	
							<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="window.location.href='${basePath}/legislate/assessment_list.do?receive=N';">返回</button>
						</s:if>
						<s:if test="#session.isLfc">
							<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="window.location.href='${basePath}/legislate/assessment_dis_list.do';">返回</button>
						</s:if>
						<s:if test="#request.others">
							<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="window.location.href='${basePath}/legislate/assessment_up_list.do?flag=EDIT';">返回</button>
						</s:if>
                    </div>
                    <div style="display: none">
                    	<div class="controls" style="width: 335px;">
							<s:file name="uploadFile" id="uploadFile"></s:file>
						</div>
					</div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" name="op" id="op" />
	<input type="hidden" name="reportType" id="reportType" />  
    <input type="hidden" name="assessmentId" id="assessmentId" value="<s:property value='#request.assessmentReport.assessmentId' />" /> 
	</form>
</div>
</div>
<script> 
function blockChange(){
	var secretLevel = $('#secretLevel').val();
	if(secretLevel=='PROTECTED'){
		$('#PROTECTED').css('display','block');
		$('#PRIVATE').css('display','none');
	}else if(secretLevel=='PRIVATE'){
		$('#PROTECTED').css('display','none');
		$('#PRIVATE').css('display','block');
	}
}
</script>
</body>
</html>