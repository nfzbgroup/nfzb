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
 	<form method="post" enctype="multipart/form-data" action="${basePath}/legislate/assessment_report_add.do" name="form1" id="form1">
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
                    <div class="row rh">
                        <label class="formlabel" style="white-space: nowrap;">评估摘要及正文（.doc）上传:</label>
                        <a href="${basePath }/legislate/assessmentAttachLoad.do?assessmentId=<s:property value='#request.assessmentReport.assessmentId' />&reportType=ABSTRACT"><s:property value='#request.assessmentReport.abstractFileName' /></a>
						<!-- <input type="button"  class="chakan caozuo-btn btn-70" value="点击上传" onclick="fileUpload('ABSTRACT');">&nbsp;摘要限1500-3000字 -->
						<div class="controls" style="width: 335px;">
							<s:file name="uploadFile" id="uploadFile" onclick="fileUpload('ABSTRACT');"></s:file>&nbsp;摘要限1500-3000字
						</div>
                    </div>
                    <div class="row rh">
                        <label class="formlabel" style="white-space: nowrap;">评估摘要及正文（.pdf盖章）上传:</label>
                        <a href="${basePath }/legislate/assessmentAttachLoad.do?assessmentId=<s:property value='#request.assessmentReport.assessmentId' />&reportType=REPORT"><s:property value='#request.assessmentReport.reportFileName' /></a>
						<!-- <input type="button"  class="chakan caozuo-btn btn-70" value="点击上传" onclick="fileUpload('REPORT');"> -->
						<div class="controls" style="width: 335px;">
							<s:file name="uploadFile" id="uploadFile1" onclick="fileUpload('REPORT');"></s:file>
						</div>
                    </div>
                    <div class="row rh">
                        <label class="formlabel" style="white-space: nowrap;">是否公开:		                
			               <s:select list="#{'PUBLIC':'公开','PROTECTED':'部分不公开','PRIVATE':'不公开'}" name="secretLevel" id="secretLevel" onchange="diffFile()" cssClass="input-xlarge"></s:select>
                        </label>
                    </div>
                    <div id="PROTECTED" style="display: none" class="row rh">
                        <label class="formlabel">公开部分摘要及正文</label>
                        <a href="${basePath }/legislate/assessmentAttachLoad.do?assessmentId=<s:property value='#request.assessmentReport.assessmentId' />&reportType=PROTECTED"><s:property value='#request.assessmentReport.protectFileName' /></a>
						<!-- <input type="button"  class="chakan caozuo-btn btn-70" value="点击上传" onclick="fileUpload('PROTECTED');"> -->
						<div class="controls" style="width: 335px;">
							<s:file name="uploadFile" id="uploadFile2" onclick="fileUpload('PROTECTED');"></s:file>
						</div>
						<label class="formlabel">部分不公开理由说明（.pdf盖章）</label>
						<a href="${basePath }/legislate/assessmentAttachLoad.do?assessmentId=<s:property value='#request.assessmentReport.assessmentId' />&reportType=PART"><s:property value='#request.assessmentReport.partFileName' /></a>
						<!--  <input type="button"  class="chakan caozuo-btn btn-70" value="点击上传" onclick="fileUpload('PART');">-->
						<div class="controls" style="width: 335px;">
							<s:file name="uploadFile" id="uploadFile3" onclick="fileUpload('PART');"></s:file>
						</div>
                    </div>
                    <div id="PRIVATE" style="display: none" class="row rh">
                        <label class="formlabel">请上传不公开理由说明（.pdf盖章）</label>
                        <!--<span class="formdiv arh">
                            <s:textarea style="height:50px;font-size: medium;font-family: 微软雅黑, 黑体, 宋体" name="memo" id="memo"></s:textarea>
                        </span>
                        -->
                        <a href="${basePath }/legislate/assessmentAttachLoad.do?assessmentId=<s:property value='#request.assessmentReport.assessmentId' />&reportType=PRIVATE"><s:property value='#request.assessmentReport.privateFileName' /></a>
                        <!-- <input type="button"  class="chakan caozuo-btn btn-70" value="点击上传" onclick="fileUpload('PRIVATE');"> -->
                        <div class="controls" style="width: 335px;">
							<s:file name="uploadFile" id="uploadFile4" onclick="fileUpload('PRIVATE');"></s:file>
						</div>
                    </div>
                    <div class="legislate-btnGroup">
                    	<s:if test="#request.assessmentReport.assessmentId!=null">
							<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="javascript:save('modify');">保存</button>
						</s:if>
						<s:else>
							<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="javascript:save('save');">保存</button>
						</s:else>
	                    <s:if test="#request.currentTeam.id=='U_3_3'">	
							<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="window.location.href='${basePath}/legislate/assessment_report_list.do?receive=N';">返回</button>
						</s:if>
						<s:else>
							<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="assessmentUpReport('<s:property value="#request.assessmentReport.assessmentId" />');">报送</button>
							<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="window.location.href='${basePath}/legislate/assessment_reportUp_list.do?flag=EDIT';">返回</button>
						</s:else>
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
function diffFile(){
	var secretLevel =  $('#secretLevel option:selected').val();
	if(secretLevel=='PUBLIC'){
		$('#PROTECTED').css('display','none');
		$('#PRIVATE').css('display','none');
	}
	if(secretLevel=='PROTECTED'){
		$('#PROTECTED').css('display','block');
		$('#PRIVATE').css('display','none');
	}
	if(secretLevel=='PRIVATE'){
		$('#PROTECTED').css('display','none');
		$('#PRIVATE').css('display','block');
	}
}
function fileUpload(reportType){
	$('#reportType').val(reportType);
	//$('#uploadFile').click();
}
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
function assessmentUpReport(assessmentId){
	if(assessmentId==null||assessmentId==''){
		alert('未检测到已保存报告，请先保存。');
	}else{
		$.ajax({
			url:'${basePath}/legislate/assessmentUpReport.do',
			data:'assessmentId='+assessmentId,
			type:'post',
			async:false,
			success:function(msg){
				if(msg=='true'){
					alert('已报送市法制办。');
				}else{
					alert('报送失败，请联系管理员。');
				}
			}
		});
	}
   	window.location.reload();
}
$(function() {
	$("#uploadFile").change(function() {
		var needType = $('#reportType').val();
		var fileFlag = true;
		var path = $(this).val().substr($(this).val().lastIndexOf("."));
		if (path!=null||path!='') {
			if(needType=='ABSTRACT'){
				if(path=='.doc'||path=='.docx'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择DOC文件作为附件。');
				}
			}else if(needType=='REPORT'){
				if(path=='.pdf'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择PDF文件作为附件。');
				}
			}else if(needType=='PROTECTED'){
				fileFlag = true;
			}else if(needType=='PRIVATE'){
				if(path=='.pdf'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择PDF文件作为附件。');
				}
			}else if(needType=='PART'){
				if(path=='.pdf'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择PDF文件作为附件。');
				}
			}
			if(fileFlag){
				alert('附件选择成功');
				form1.action = "${basePath}/legislate/assessment_report_attach_add.do";
				form1.submit();
			}
		} else {
			alert('附件为空，请选择');
		}
	});
	$("#uploadFile1").change(function() {
		var needType = $('#reportType').val();
		var fileFlag = true;
		var path = $(this).val().substr($(this).val().lastIndexOf("."));
		if (path!=null||path!='') {
			if(needType=='ABSTRACT'){
				if(path=='.doc'||path=='.docx'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择DOC文件作为附件。');
				}
			}else if(needType=='REPORT'){
				if(path=='.pdf'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择PDF文件作为附件。');
				}
			}else if(needType=='PROTECTED'){
				fileFlag = true;
			}else if(needType=='PRIVATE'){
				if(path=='.pdf'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择PDF文件作为附件。');
				}
			}else if(needType=='PART'){
				if(path=='.pdf'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择PDF文件作为附件。');
				}
			}
			if(fileFlag){
				alert('附件选择成功');
				form1.action = "${basePath}/legislate/assessment_report_attach_add.do";
				form1.submit();
			}
		} else {
			alert('附件为空，请选择');
		}
	});
	$("#uploadFile2").change(function() {
		var needType = $('#reportType').val();
		var fileFlag = true;
		var path = $(this).val().substr($(this).val().lastIndexOf("."));
		if (path!=null||path!='') {
			if(needType=='ABSTRACT'){
				if(path=='.doc'||path=='.docx'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择DOC文件作为附件。');
				}
			}else if(needType=='REPORT'){
				if(path=='.pdf'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择PDF文件作为附件。');
				}
			}else if(needType=='PROTECTED'){
				fileFlag = true;
			}else if(needType=='PRIVATE'){
				if(path=='.pdf'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择PDF文件作为附件。');
				}
			}else if(needType=='PART'){
				if(path=='.pdf'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择PDF文件作为附件。');
				}
			}
			if(fileFlag){
				alert('附件选择成功');
				form1.action = "${basePath}/legislate/assessment_report_attach_add.do";
				form1.submit();
			}
		} else {
			alert('附件为空，请选择');
		}
	});
	$("#uploadFile3").change(function() {
		var needType = $('#reportType').val();
		var fileFlag = true;
		var path = $(this).val().substr($(this).val().lastIndexOf("."));
		if (path!=null||path!='') {
			if(needType=='ABSTRACT'){
				if(path=='.doc'||path=='.docx'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择DOC文件作为附件。');
				}
			}else if(needType=='REPORT'){
				if(path=='.pdf'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择PDF文件作为附件。');
				}
			}else if(needType=='PROTECTED'){
				fileFlag = true;
			}else if(needType=='PRIVATE'){
				if(path=='.pdf'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择PDF文件作为附件。');
				}
			}else if(needType=='PART'){
				if(path=='.pdf'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择PDF文件作为附件。');
				}
			}
			if(fileFlag){
				alert('附件选择成功');
				form1.action = "${basePath}/legislate/assessment_report_attach_add.do";
				form1.submit();
			}
		} else {
			alert('附件为空，请选择');
		}
	});
	$("#uploadFile4").change(function() {
		var needType = $('#reportType').val();
		var fileFlag = true;
		var path = $(this).val().substr($(this).val().lastIndexOf("."));
		if (path!=null||path!='') {
			if(needType=='ABSTRACT'){
				if(path=='.doc'||path=='.docx'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择DOC文件作为附件。');
				}
			}else if(needType=='REPORT'){
				if(path=='.pdf'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择PDF文件作为附件。');
				}
			}else if(needType=='PROTECTED'){
				fileFlag = true;
			}else if(needType=='PRIVATE'){
				if(path=='.pdf'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择PDF文件作为附件。');
				}
			}else if(needType=='PART'){
				if(path=='.pdf'){
					fileFlag = true;
				}else{
					fileFlag = false;
					alert('请选择PDF文件作为附件。');
				}
			}
			if(fileFlag){
				alert('附件选择成功');
				form1.action = "${basePath}/legislate/assessment_report_attach_add.do";
				form1.submit();
			}
		} else {
			alert('附件为空，请选择');
		}
	});
});
function save(op){//不保存附件
	$('#op').val(op);
	$('#reportType').val("");
	alert('保存成功');
	form1.submit();
}
</script>
</body>
</html>