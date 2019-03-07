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
    <title>规章立法后评估项目</title>
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${basePath}/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${basePath}/legislate/js/jquery.js"></script>
    <script src = "${basePath}/legislate/js/sui.js"></script>
    <script src = "${basePath}/legislate/js/tanchuang.js"></script>
    <script src = "js/layer/layer.js"></script>
</head>
<body class = "body_bg_1">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->
 
    <div class = "conteng-title">
        <span>当前位置：</span><em>规章立法后评估项目发起-首页</em>
    </div>
 	<form method="post" enctype="multipart/form-data" action="${basePath}/legislate/assessment_add.do" name="form1" id="form1">
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
                    <div class = "flright" style="display: block;" onclick = "divAdd()">添加项目</div>
                    <div id="one" class = "detai" style="display: block">
	                    <div class="container-fluid container-top">
	                    	<label class="formlabel">后评估项目一名称:</label>
	                    	<span class="formdiv">
	                           <s:textfield name="assessmentTitle1" id="assessmentTitle1"></s:textfield>
	                        </span>
	                    </div>
	                    <div>
	                    		<label class="formlabel">理由:</label>
	                            <select name="select1" id="select1" onchange="select_1()">
	                            <option value="">请选择</option>
	                       		<option value="A">拟上升为地方性法规</option>
	                       		<option value="B">拟进行重大修改</option>
	                       		<option value="C">拟废止但有较大争议</option>
	                       		<option value="D">与经济社会发展或者公众利益密切相关，且实施满5年以上</option>
	                       		<option value="E">人大代表、政协委员或者社会各界意见、建议较为集中</option>
	                       		<option value="F">其他理由</option>
	                       	</select>
	                    </div>
	                   	<div id="show_1" style="display: none">
	                   		<label class="formlabel">其他:</label>
	                       	<span class="formdiv">
	                           <s:textfield name="others1" id="others1"></s:textfield>
	                        </span>
	                    </div>
                    </div>
                    <div id="two" class = "detai" style="display: none">
	                    <div class="container-fluid container-top">
	                    	<label class="formlabel">后评估项目二名称:</label>
	                    	<span class="formdiv">
	                           <s:textfield name="assessmentTitle2" id="assessmentTitle2"></s:textfield>
	                        </span>
	                    </div>
	                    <div>
	                    		<label class="formlabel">理由:</label>
	                            <select name="select2" id="select2" onchange="select_2()">
	                            <option value="">请选择</option>
	                       		<option value="A">拟上升为地方性法规</option>
	                       		<option value="B">拟进行重大修改</option>
	                       		<option value="C">拟废止但有较大争议</option>
	                       		<option value="D">与经济社会发展或者公众利益密切相关，且实施满5年以上</option>
	                       		<option value="E">人大代表、政协委员或者社会各界意见、建议较为集中</option>
	                       		<option value="F">其他理由</option>
	                       	</select>
	                    </div>
	                   	<div id="show_2" style="display: none">
	                   		<label class="formlabel">其他:</label>
	                       	<span class="formdiv">
	                           <s:textfield name="others2" id="others2"></s:textfield>
	                        </span>
	                    </div>
                    </div>
                    <div id="three" class = "detai" style="display: none">
	                    <div class="container-fluid container-top">
	                    	<label class="formlabel">后评估项目三名称:</label>
	                    	<span class="formdiv">
	                           <s:textfield name="assessmentTitle3" id="assessmentTitle3"></s:textfield>
	                        </span>
	                    </div>
	                    <div>
	                    		<label class="formlabel">理由:</label>
	                            <select name="select3" id="select3" onchange="select_3()">
	                            <option value="">请选择</option>
	                       		<option value="A">拟上升为地方性法规</option>
	                       		<option value="B">拟进行重大修改</option>
	                       		<option value="C">拟废止但有较大争议</option>
	                       		<option value="D">与经济社会发展或者公众利益密切相关，且实施满5年以上</option>
	                       		<option value="E">人大代表、政协委员或者社会各界意见、建议较为集中</option>
	                       		<option value="F">其他理由</option>
	                       	</select>
	                    </div>
	                   	<div id="show_3" style="display: none">
	                   		<label class="formlabel">其他:</label>
	                       	<span class="formdiv">
	                           <s:textfield name="others3" id="others3"></s:textfield>
	                        </span>
	                    </div>
                    </div>
                    <div id="four" class = "detai" style="display: none">
	                    <div class="container-fluid container-top">
	                    	<label class="formlabel">后评估项目四名称:</label>
	                    	<span class="formdiv">
	                           <s:textfield name="assessmentTitle4" id="assessmentTitle4"></s:textfield>
	                        </span>
	                    </div>
	                    <div>
	                    		<label class="formlabel">理由:</label>
	                            <select name="select4" id="select4" onchange="select_4()">
	                            <option value="">请选择</option>
	                       		<option value="A">拟上升为地方性法规</option>
	                       		<option value="B">拟进行重大修改</option>
	                       		<option value="C">拟废止但有较大争议</option>
	                       		<option value="D">与经济社会发展或者公众利益密切相关，且实施满5年以上</option>
	                       		<option value="E">人大代表、政协委员或者社会各界意见、建议较为集中</option>
	                       		<option value="F">其他理由</option>
	                       	</select>
	                    </div>
	                   	<div id="show_4" style="display: none">
	                   		<label class="formlabel">其他:</label>
	                       	<span class="formdiv">
	                           <s:textfield name="others4" id="others4"></s:textfield>
	                        </span>
	                    </div>
                    </div>
                    <div id="five" class = "detai" style="display: none">
	                    <div class="container-fluid container-top">
	                    	<label class="formlabel">后评估项目五名称:</label>
	                    	<span class="formdiv">
	                           <s:textfield name="assessmentTitle5" id="assessmentTitle5"></s:textfield>
	                        </span>
	                    </div>
	                    <div>
	                    		<label class="formlabel">理由:</label>
	                            <select name="select5" id="select5" onchange="select_5()">
	                            <option value="">请选择</option>
	                       		<option value="A">拟上升为地方性法规</option>
	                       		<option value="B">拟进行重大修改</option>
	                       		<option value="C">拟废止但有较大争议</option>
	                       		<option value="D">与经济社会发展或者公众利益密切相关，且实施满5年以上</option>
	                       		<option value="E">人大代表、政协委员或者社会各界意见、建议较为集中</option>
	                       		<option value="F">其他理由</option>
	                       	</select>
	                    </div>
	                   	<div id="show_5" style="display: none">
	                   		<label class="formlabel">其他:</label>
	                       	<span class="formdiv">
	                           <s:textfield name="others5" id="others5"></s:textfield>
	                        </span>
	                    </div>
                    </div>
                    <div class="row rh">
                        <label class="formlabel" style="white-space: nowrap;">后评估项目（.pdf盖章）上传:</label>
                        <a href="${basePath }/legislate/assessmentAttachDownload.do?assessmentId=<s:property value='#request.assessment.assessmentId' />&reportType=ABSTRACT"><s:property value='#request.assessment.assessmentFileName' /></a>
						<!--  <input type="button"  class="chakan caozuo-btn btn-70" value="点击上传" onclick="fileUpload('ABSTRACT');">&nbsp;-->
						<div class="controls" style="width: 335px;">
							<s:file name="uploadFile" id="uploadFile" onclick="fileUpload('REPORT');"></s:file>
						</div>
						<div class = "flright" style="display: block;" onclick = "exportWordTest()">导出</div>
                    </div>
                    <!-- 
	                    <div class="row rh">
	                        <label class="formlabel" style="white-space: nowrap;">模板下载:</label>
	                        <a href="${basePath }/legislate/assessmentAttachDownload.do" />2019年后评估项目申报表18-10-15.doc</a>
	                    </div>
                     -->
                    <div class="legislate-btnGroup">
                    	<s:if test="#request.assessment.assessmentId!=null">
							<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="javascript:save('modify');">保存</button>
						</s:if>
						<s:else>
							<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="javascript:save('save');">保存</button>
						</s:else>
	                    <s:if test="#request.currentTeam.id=='U_3_3'">	
							<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="window.location.href='${basePath}/legislate/assessment_list.do?receive=N';">返回</button>
						</s:if>
						<s:else>
							<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="assessmentUpReport('<s:property value="#request.assessment.assessmentId" />');">报送</button>
							<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="window.location.href='${basePath}/legislate/assessment_up_list.do?flag=EDIT';">返回</button>
						</s:else>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <input type="hidden" name="op" id="op" />
	<input type="hidden" name="reportType" id="reportType" />  
    <input type="hidden" name="assessmentId" id="assessmentId" value="<s:property value='#request.assessment.assessmentId' />" /> 
	</form>
</div>
</div>
<script>
var flag = 2;
function fileUpload(reportType){
	$('#reportType').val(reportType);
	//$('#uploadFile').click();
}
function assessmentUpReport(assessmentId){
	if(assessmentId==null||assessmentId==''){
		alert('未检测到已保存项目申报报告，请先保存。');
	}else{
		$.ajax({
			url:'${basePath}/legislate/assessmentUp.do',
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
				form1.action = "${basePath }/legislate/assessment_attach_add.do";
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
function divAdd(){
	var assessmentTitle = $('#assessmentTitle').val();
	var assessmentTitle1 = $('#assessmentTitle1').val();
	var assessmentTitle2 = $('#assessmentTitle2').val();
	var assessmentTitle3 = $('#assessmentTitle3').val();
	var assessmentTitle4 = $('#assessmentTitle4').val();
	var assessmentTitle5 = $('#assessmentTitle5').val();
	var isNull = assessmentTitle!=null&&assessmentTitle!='';
	var isNull1 = assessmentTitle1!=null&&assessmentTitle1!='';
	var isNull2 = assessmentTitle2!=null&&assessmentTitle2!='';
	var isNull3 = assessmentTitle3!=null&&assessmentTitle3!='';
	var isNull4 = assessmentTitle4!=null&&assessmentTitle4!='';
	var isNull5 = assessmentTitle5!=null&&assessmentTitle5!='';
	if(flag==1&&isNull){
		flag++;
		$('#one').css('display','block');
	}else if(flag==2&&isNull1){
		flag++;
		$('#two').css('display','block');
	}else if(flag==3&&isNull2){
		flag++;
		$('#three').css('display','block');
	}else if(flag==4&&isNull3){
		flag++;
		$('#four').css('display','block');
	}else if(flag==5&&isNull4){
		flag++;
		$('#five').css('display','block');
	}else{
		alert('请填写后评估项目名称。');
	}
}
function select_1(){
	var select1 = $('#select1').val();
	console.log(select1);
	if(select1=='F'){
		$('#show_1').css('display','block');
	}else{
		$('#show_1').css('display','none');
	}
}
function select_2(){
	var select2 = $('#select2').val();
	if(select2=='F'){
		$('#show_2').css('display','block');
	}else{
		$('#show_2').css('display','none');
	}
}
function select_3(){
	var select3 = $('#select3').val();
	if(select3=='F'){
		$('#show_3').css('display','block');
	}else{
		$('#show_3').css('display','none');
	}
}
function select_4(){
	var select4 = $('#select4').val();
	if(select4=='F'){
		$('#show_4').css('display','block');
	}else{
		$('#show_4').css('display','none');
	}
}
function select_5(){
	var select5 = $('#select5').val();
	if(select5=='F'){
		$('#show_5').css('display','block');
	}else{
		$('#show_5').css('display','none');
	}
}
function exportWordTest(){
	form1.action = '${basePath}/legislate/exportWord.do';
	form1.submit();
}
</script>
</body>
</html>