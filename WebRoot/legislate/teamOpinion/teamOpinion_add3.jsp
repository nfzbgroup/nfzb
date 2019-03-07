<%@page import="com.wonders.fzb.legislate.LegislateConst"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.wonders.fzb.legislate.beans.Model"%>
<%@page import="com.wonders.fzb.legislate.beans.FileRecord"%>
<%@page import="java.util.Map"%>
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
    <title>征询单位意见发起</title>
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${basePath}/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${basePath}/legislate/js/jquery.js"></script>
    <script src = "${basePath}/legislate/js/sui.js"></script>
    <script src = "${basePath}/legislate/js/tanchuang.js"></script>
    <script src = "js/layer/layer.js"></script>
</head>

<body class = "body_bg_1">
<!--面包屑-->
<div class = "conteng">
    <div class = "conteng-title">
        <span>当前位置：</span><em>单位意见征求-添加</em>
    </div>
    <div class = "detai" style = "margin-bottom: 40px">
        <div class = "detai-con">
            <!--内容区-->
            <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                            <div class="col-sm-12 text-center">
                                <h1 style="color: #B9404D;">征求意见单</h1>
                            </div>
                    </div>
                    <div class="row rh">
                        <div class="centtbody" >
                            <table width="50%">
                                <tbody>
                                <tr>
                                   <s:select label="对应草案" name = "draftId" id = "draftId"  list="#request.draftList" value="draftId" listValue="draftName" listKey="draftId" ></s:select>
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                     <div class="clearfix">
                    <div class="container-fluid container-top">
                        <div class="row rh">
                            <div class="col-sm-12 text-center">
                                <button type="button" onclick="zhengxun()" class="btn btn-info btn-bg btn-120">提交</button>
                                 <button type="button" class="btn btn-info btn-bg btn-120" onclick="window.location.href='${basePath}/legislate/teamOpinion_list.do'">返回</button>
                            </div>
                        </span>
                        </div>
                    </div>
                </div>
                </div>
                
          </div>
          </div>
</div>
</div>



<script>
    $(function () {
        $('input[type="file"]').change(function (e) {
            var filename = $(this).val().split("\\").pop();
            $('.file-text').text(filename);
        })
    });
    function zhengxun(){
   	 	$.ajax({
   	 		"type":"POST",
   	 		"dataType":"json",
   	 		"url":"${basePath}/legislate/saveTeamOpinion.do?op=zhengxun&draftId="+$("#draftId").val(),
   	 		"success":function(data){
   	 			alert(data.message);
   	 			if(data.code==200){
   	 			window.location.href(uuu);
   	 			}
   	 		}
   	 	}) 
	}
</script>
</div>

<script> 

</script>
</body>
</html>