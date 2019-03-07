<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
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
    <link rel = "stylesheet" href = "${basePath}/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${basePath}/legislate/js/jquery.js"></script>
    <script src = "${basePath}/legislate/js/sui.js"></script>
    <script src = "${basePath}/legislate/js/tanchuang.js"></script>
</head>
<body class = "body_bg_1">
<!--<div class = "tab-list tab-back">审核意见</div>
<div class = "tab-list">归项单</div>-->
<!--内容区-->

<div class = "tab-content" style = "display: block">
    <div class = "clearfix"></div>
    <div class = "container-fluid container-top">
        <div class = "row">
            <div class = "col-sm-1 text-color " style = "float: left">计划名称:</div>
            <div class = "col-sm-2 " style = "float: left">
                <span class = "szgb">${planTask.plan.planName }</span>
            </div>
        </div>
    </div>
    <div class = "clearfix"></div>
    <div class="clearfix">
        <div class="container-fluid container-top">
            <div class = "row">
                <div class = "col-sm-1 text-color " style = "float: left;color:#000000 ;">法制机构意见:</div>
                <div class = "col-sm-2 " style = "float: left">
                     <textarea  style="font-size: medium;font-family: 微软雅黑, 黑体, 宋体"  class = "fenban-textarea" id="advice" style = "display:inline-block;width: 380px;float: left;border-color: #000000;"></textarea>
                </div>
                <div class = "col-sm-1"></div>
            </div>
        </div>
    </div>
    <div class="clearfix">
        <div class="container-fluid container-top">
            <div class = "row">
                <div class = "col-sm-1 text-color " style = "float: left;color:#000000 ;">备注:</div>
                <div class = "col-sm-2 " style = "float: left">
                     <textarea id="remark" class = "fenban-textarea"  style = "display:inline-block;width: 380px;float: left;border-color: #000000;">${planTask.plan.remark }</textarea>
                </div>
                <div class = "col-sm-1"></div>
            </div>
        </div>
    </div>
    <!--<div class="clearfix">
        <div class="container-fluid container-top">
            <div class = "row">
                <div class = "col-sm-1 text-color " style = "float: left;color:#000000 ;">归项类型:</div>
                <div class = "col-sm-5 " style = "float: left">
                    <label  class='labelmar'>类型1<input type = "checkbox" name="lx"></label>
                    <label  class='labelmar'>类型2<input type = "checkbox" name="lx"></label>
                    <label  class='labelmar'>类型3<input type = "checkbox" name="lx"></label>
                    <label  class='labelmar'>类型4<input type = "checkbox" name="lx"></label>
                </div>
            </div>
        </div>
    </div>-->

</div>
<!--<div class="tab-content">
<h1 align="center" >归项单</h1>
</div>-->
<div class="clearfix" style="margin-top: 20px;padding-bottom: 50px">
    <div class="container-fluid container-top">
        <div class="row rh">
            <div class="col-sm-12 text-center">
                <a class = "sui-btn btn-primary01 btn-lg btn-170" onclick="baocun('${planTask.taskId}')">保存</a>
                <a href = "#" class = "sui-btn btn-primary01 btn-lg btn-170 layer-close">关闭</a>
            </div>
        </div>
    </div>
</div>


</body>
<script>
    $('.layer-close').click(function () {
        var i = parent.layer.getFrameIndex(window.name);
        parent.layer.close(i);
    });
    $('.tab-list').click(function () {
        var index = $(this).index();
        $(this).css({'background':'#288ce2','color':'#fff','font-size':'16px'}).siblings('.tab-list').css({'background':'#ececec','color':'#969696','font-size':'14px'})
        $('.tab-content').eq(index).show().siblings('.tab-content').hide()
    })
	

    function baocun(taskId){
    	var remark = $("#remark").val();
    	var advice =$("#advice").val();
    	if(advice==""||advice==null){
    		alert("请填写好意见");
    		$("#advice").focus();
    		return;
    	}
    	if(remark==""||remark==null){
    		alert("请填写好备注");
    		$("#remark").focus();
    		return;
    	}
    	$.ajax({
 	 		"type":"POST",
 	 		"dataType":"json",
 	 		"url":"${basePath}/legislate/shenhe_gxd.do",
 	 		"data":{"taskId":taskId,"op":"doaudit","remark":remark},
 	 		"success":function(data){
 	 			if(data.code==200){
 	 				alert(data.message);
 	 				/* parent.location.reload(); */
 	 				parent.location.href(parent.location.href);
 	 			}
 	 		}
 	 	}) 
    }
</script>
</html>