<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html lang = "zh">
<head>
    <meta http-equiv = "Content-Type" content = "text/html; charset=utf-8"/>
    <meta name = "keywords" content = "OA系统"/>
    <meta name = "keywords" content = ""/>
    <meta name = "description" content = ""/>
    <meta name = "generator" content = "Notepad++"/>
    <meta http-equiv = "X-UA-Compatible" content = "IE=9"/>
    <meta http-equiv = "X-UA-Compatible" content = "IE=edge,Chrome=1"/>
    <title>上海市人民政府法制办公室文件分办单</title>
    
    <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath }/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${pageContext.request.contextPath }/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath }/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${pageContext.request.contextPath }/legislate/js/jquery.js"></script>
    <script src = "${pageContext.request.contextPath }/legislate/js/sui.js"></script>
    <script src = "${pageContext.request.contextPath }/legislate/js/tanchuang.js"></script>
</head>
<body class = "body_bg_1">
<!--头部结asd束-->
<!--面包屑-->
<!--<div class="conteng-title">-->
<!--<span>当前位置：</span><em>规章草案发起-首页</em>-->
<!--</div>-->
<form action="${basePath}/legislate/draft_dise.do" method="post">
<!--内容区-->
<div class = "jumbotron text-center container-top" style = "margin-top: 60px">
    <h1 style = "color: #B9404D;">上海市人民政府法制办公室文件分办单</h1>
</div>

<div style = "border-bottom: 2px solid #B9404D; height: 270px;">
    <div class = "container-fluid">
        <div class = "row" style="text-align: right">
            <div class = "col-sm-12 text-right" >
                ${draftTask.draft.fbd.fbdId }
            </div>
        </div>

    </div>
    <div class = "clearfix"></div>
    <div class = "container-fluid container-top">
        <div class = "row">
            <div class = "col-sm-1 text-color " style = "float: left">来文单位:</div>
            <div class = "col-sm-2 " style = "float: left">
                <span class = "szgb"><s:textfield disabled="true" name="teamName" value="%{draftTask.draft.teamName}"/></span>
            </div>
            <div class = "col-sm-1"></div>
            <div class = "col-sm-4 col-sm-offset-3" style = "float: right;">
                <span class = "text-color">日期:</span>
                <span class = "bdbt"><s:date name="draftTask.draft.createTime" format="yyyy"/></span>
                <span class = "text-color">年</span>
                <span class = "bdbt"><s:date name="draftTask.draft.createTime" format="MM"/></span>
                <span class = "text-color">月</span>
                <span class = "bdbt"><s:date name="draftTask.draft.createTime" format="dd"/></span>
                <span class = "text-color">日</span>
            </div>
        </div>
    </div>
    <div class = "clearfix"></div>
    <div class = "container-fluid bdtop">
        <div class = "row">
            <div class = "col-sm-1 text-color " style = "float: left">草案名称:</div>
            <div class = "col-sm-8">
                <p class = "wjmc" style = "display: inline-block;">
                    <s:textfield disabled="true" name="draftName" value="%{draftTask.draft.draftName}"/>
                </p>
            </div>
        </div>
    </div>

    <div class = "clearfix"></div>
    <div class = "container-fluid bdtop">
        <div class = "row">
            <div class = "col-sm-1 text-color " style = "float: left;">分办:</div>
            <div class = "col-sm-8">
                 <s:textarea name="instructions" id="instructions" cssClass="fenban-textarea" style="display:inline-block;width: 420px;float: left;"> ${draftTask.draft.fbd.instructions }</s:textarea>
            </div>
        </div>
    </div>
    <div class = "clearfix"></div>
    <div class = "container-fluid container-top">
        <div class = "row">
            <div class = "col-sm-4 text-color" style = "display: inline-block;margin-left: 470px;">办理时限:</div>
            <div class = "col-sm-3" style = "display: inline-block;">
                <span class = "bdbt"><input name="year" id="year" style="width:40px;margin-left:-5px;"/></span>
                <span class = "text-color">年</span>
                <span class = "bdbt"><input name="month" id="month" style="width:30px"/></span>
                <span class = "text-color">月</span>
                <span class = "bdbt"><input name="day" id="day" style="width:30px"/></span>
                <span class = "text-color">日</span>
            </div>
        </div>
    </div>
    <div class = "clearfix"></div>
</div>
<div class = "container-fluid container-top">
    <div class = "row">
        <div class = "col-sm-2 text-right" style = "padding-top: 4px;display: inline-block;">选择办理处:</div>
        <div class = "col-sm-4" style = "margin-left: 10px;display: inline-block;">
            <s:select list="#{'U_3_5':'经济法规处','U_3_6':'城建法规处','U_3_7':'社会法规处'}" name="pteamId" id="pteamId" cssClass="select-option"></s:select>
        </div>
    </div>

</div>
<div class = "clearfix"></div>
<div class = "submit-btn">
    <a  onclick="saveFBD('${draftTask.draft.draftId}')" class = "sui-btn btn-primary01 btn-lg btn-170">确认分办</a>
     <a  onclick="openFbdDoc();" class = "sui-btn btn-primary01 btn-lg btn-170">编辑分办单</a>
    <a href = "#" class = "sui-btn btn-primary01 btn-lg btn-170 layer-close">关闭</a>
</div>
</div>

</form>
</body>
<script>
var uuu;
	$(function(){
		uuu = window.location.href;
	})
	function openFbdDoc(){
	    window.open('../platform/composer/compose_doc.jsp?docId=${param.taskId}&docType='+encodeURIComponent("草案分办单")+'&fromTemplate=Y');
	}
    $('.layer-close').click(function () {
        var i = parent.layer.getFrameIndex(window.name);
        parent.layer.close(i);
    })
	function saveFBD(draftid){
	   	if($("#instructions").val()==""||$("#instructions").val()==null){
	   		alert("请填写分办信息");
	   		$("#instructions").focus();
	   		return;
	   	}
	   	var re=/^[\d-]*$/;
	   	var year=$("#year").val();
	   	var month=$("#month").val();
	   	var day=$("#day").val();
	   	if(year.length!=4||year.match(re)==null||year==""||year==null){
	   		alert("请输入正确的年格式");
	   		$("#year").focus();
	   		return;
	   	}
	   	if(month.length!=2||month.match(re)==null||month==""||month==null){
	   		alert("请输入正确的月格式");
	   		$("#month").focus();
	   		return;
	   	}
	   	if(day.length!=2||day.match(re)==null||day==""||day==null){
	   		alert("请输入正确的日格式");
	   		$("#day").focus();
	   		return;
	   	}
     	 	$.ajax({
     	 		"type":"POST",
     	 		"dataType":"json",
     	 		"url":"${basePath}/legislate/draft_dise.do",
     	 		"data":{'fbdId':'${draftTask.draft.fbd.fbdId }',"teamId":$('#pteamId').val(),"draftId":draftid,"op":"doDise","year":$('#year').val(),"month":$('#month').val(),"day":$('#day').val(),"instructions":$('#instructions').val(),"taskId":"${taskId}"},
     	 		"success":function(data){
     	 			if(data.code==200){
     	 				alert(data.message);
     	 				parent.location.href(parent.location.href);
     	 			}
     	 		}
     	 	}) 
    }
</script>
</html>