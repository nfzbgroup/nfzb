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
    <title>立法规章后评估报告发起</title>
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
        <span>当前位置：</span><em>立法规章后评估报告发起-首页</em>
    </div>
 	<form method="post" enctype="multipart/form-data" action="${basePath}/legislate/assessment_report_add.do" name="form1">
    <!--内容区-->
    <div class = "detai">
        <div class = "detai-con">
            <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                        <label class="formlabel">评估报告名称:</label>
                        <span class="formdiv">
                           <s:textfield name="assessmentTitle" id="assessmentTitle"></s:textfield>
                        </span>
                    </div>
                    <!-- 
                    <div class="row rh">
                        <label class="formlabel">评估报告摘要:</label>
                        <span class="formdiv arh">
                            <s:textarea style="height:50px;font-size: medium;font-family: 微软雅黑, 黑体, 宋体" name="assessmentContent" id="assessmentContent"></s:textarea>
                        </span>
                    </div>
                     -->
                    <div class="row rh">
                        <label class="formlabel" style="white-space: nowrap;">评估摘要及正文（.doc）下载:</label>
                        <a href="${basePath }/legislate/assessmentAttachLoad.do?assessmentId=<s:property value='#request.assessmentReport.assessmentId' />&reportType=ABSTRACT"><s:property value='#request.assessmentReport.abstractFileName' /></a>
                    </div>
                    <div class="row rh">
                        <label class="formlabel" style="white-space: nowrap;">评估摘要及正文（.pdf）下载:</label>
                        <a href="${basePath }/legislate/assessmentAttachLoad.do?assessmentId=<s:property value='#request.assessmentReport.assessmentId' />&reportType=REPORT"><s:property value='#request.assessmentReport.reportFileName' /></a>
                    </div>
                    <div class="row rh">
                        <label class="formlabel" style="white-space: nowrap;">是否公开:		                
			               <s:select list="#{'PUBLIC':'公开','PROTECTED':'部分不公开','PRIVATE':'不公开'}" name="secretLevel" id="secretLevel" onchange="diffFile()" cssClass="input-xlarge"></s:select>
                        </label>
                    </div>
                    <div id="PROTECTED" style="display: none" class="row rh">
                        <label class="formlabel"></label>
                                                               可公开部分报告下载：&nbsp;<a href="${basePath }/legislate/assessmentAttachLoad.do?assessmentId=<s:property value='#request.assessmentReport.assessmentId' />&reportType=PROTECTED"><s:property value='#request.assessmentReport.protectFileName' /></a>
                                                               部分不公开理由说明下载：&nbsp;<a href="${basePath }/legislate/assessmentAttachLoad.do?assessmentId=<s:property value='#request.assessmentReport.assessmentId' />&reportType=PART"><s:property value='#request.assessmentReport.partFileName' /></a>
                    </div>
                    <div id="PRIVATE" style="display: none" class="row rh">
                        <label class="formlabel">评估报告不公开原因:加盖印章说明下载</label>
                        <!--<span class="formdiv arh">
                            <s:textarea style="height:50px;font-size: medium;font-family: 微软雅黑, 黑体, 宋体" name="memo" id="memo"></s:textarea>
                        </span>-->
                        <a href="${basePath }/legislate/assessmentAttachLoad.do?assessmentId=<s:property value='#request.assessmentReport.assessmentId' />&reportType=PRIVATE"><s:property value='#request.assessmentReport.privateFileName' /></a>
                    </div>
                    <div class="legislate-btnGroup">
	                    <s:if test="#request.currentTeam.id=='U_3_3'">	
							<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="window.location.href='${basePath}/legislate/assessment_report_list.do?receive=N';">返回</button>
						</s:if>
						<s:if test="#session.isLfc">
							<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="window.location.href='${basePath}/legislate/assessment_report_dis_list.do';">返回</button>
						</s:if>
						<s:if test="#request.others">
							<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="window.location.href='${basePath}/legislate/assessment_reportUp_list.do?flag=EDIT';">返回</button>
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