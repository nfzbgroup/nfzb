<%@page import="com.wonders.fzb.base.beans.Page"%>
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
    <title>单位意见征求</title>
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${basePath}/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${basePath}/legislate/js/jquery.js"></script>
    <script src = "${basePath}/legislate/js/sui.js"></script>
    <script src = "${basePath}/legislate/js/tanchuang.js"></script>
</head>
<body class = "body_bg_1">
<!--面包屑-->
<div class = "conteng">
    <div class = "conteng-title">
        <span>当前位置：</span><em>单位意见征求</em>
    </div>
    <div class = "detai" style = "margin-bottom: 40px">
        <div class = "detai-con">
            <!--内容区-->
        <%--     <form class = "form-horizontal" role = "form">
            <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                            <label class="formlabel">单位名称:</label>
                            <span class="formdiv">
                                <input type = peext">
                            </span>
                        <span class="formbuttom">
                            <button type="button" class="btn btn-info btn-bg btn-120">查询</button>
                        </span>
                    </div>
                </div>
            </div>

            </form> --%>
<div class = "detai">
        <div class = "detai-con">
        <div class = "biaodan">
            <div class = "tab-content" style = "display: block">
                <table class = "table1">
                     <thead>
                     
                        <tr>
                            <th><input type = "checkbox" id="checkAll"></th>
                            <th>单位名称</th>
                        </tr>
                        </thead>
                        <tbody>
                         <s:iterator value="teams" id="task"  status="st">
                        <tr>
                            <td><input type = "checkbox" name="test"  value="<s:property value='orgId'/>"></td>
                            <td><s:property value="unitName"/></td>
                        </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                        <div class="col-sm-12 text-center">
                            <button type="button" class="btn btn-info btn-bg btn-120" id="frameindex">确定</button>
                            <button type="button" class="btn btn-info btn-bg btn-120" onclick="window.history.back()">返回</button>
                        </div>
                    </div>
                </div>
            </div><br/>
            </div>
        </div>
    </div>
</div>


<script>
    $(function () {
       /* $('.close-btn').click(function () {

        })
        $('.tab-list').click(function () {
            var index = $(this).index();
            $(this).css({'background':'#288ce2','color':'#fff','font-size':'16px'}).siblings('.tab-list').css({'background':'#ececec','color':'#969696','font-size':'14px'})
            $('.biaodan .tab-content').eq(index).show().siblings('.tab-content').hide()
        })*/
       $("#checkAll").click(function () {
            $("table td input").prop('checked',$(this).prop("checked"))
       });
       $("#frameindex").click(function(){
      		var check_val ="";
			$('input[name="test"]:checked').each(function(){ 
				check_val = check_val + $(this).val() +","
				}); 
			$.ajax({
	   	 		"type":"POST",
	   	 		"dataType":"json",
	   	 		"url":"${basePath}/legislate/teamOpinionDetailSava.do",
	   	 		"data":{"checkval":check_val,"teamOpinionId":"${teamOpinionId}"},
	   	 		"success":function(data){
	   	 			alert(data.message);
	   	 			if(data.code==200){
	   	 				/* location.reload(); */
	   	 				parent.location.href(parent.location.href);
	   	 			}
	   	 		}
	   	 	}) 
		})
    });

</script>
</div>
</body>
</html>