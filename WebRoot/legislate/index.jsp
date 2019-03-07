<%@ page language="java" pageEncoding="UTF-8"%>
<!doctype html>
<html lang = "zh">
<head>
    <meta http-equiv = "X-UA-Compatible" content = "IE=edge,Chrome=1"/>
    <meta http-equiv = "Content-Type" content = "text/html; charset=utf-8"/>
    <meta name = "keywords" content = "OA系统"/>
    <meta name = "keywords" content = ""/>
    <meta name = "description" content = ""/>
    <meta name = "generator" content = "Notepad++"/>


    <title>上海市政府立法平台</title>
    <link rel = "stylesheet" type = "text/css" href = "css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "css/sui-themes-green-append.css" media = "screen"/>
    <script src = "js/jquery.js"></script>
    <!-- <script src = "js/jquery.messager.js"></script> -->
    <script src = "js/sui.js"></script>
    <script src = "js/tanchuang.js"></script>
    <style type="text/css">
    	body {
			overflow: hidden;
		}
		.top-right{
            position: absolute;
            right:100px;
            width:80%;
        }
        .top-right li {
            float: right;
        }
        .notice{
            margin-left: 15px;
        }
    </style>
</head>
<%
String message = (String)request.getAttribute("message2");
if(message!=null&&!"".equals(message)&&!"null".equals(message)){
%>
<script>
	alert("<%=message%>");
</script>
<%} %>
<body class = "body_bg_1" data-pageCode = "homepage1">
<div class = "header">
    <div class = "top-left">
        <img src = "images/text.png">
    </div>
    <div class = "top-right">
        <ul>
            <li class = "notice">
                <a><img src = "images/notice.png"></a>
            </li>
        
            <%String plantformId = session.getAttribute("plantformId")==null?"":session.getAttribute("plantformId").toString();%>
            <%if("".equals(plantformId)){%>
        		<li class = "tuichu"><a href="logout.jsp" target="_parent"><img src="images/exit.png" alt=""></a></li>
			<%}else{%>
       	 		<li class = "tuichu">
					<a href="../portal/login.do?plantformId=<%=plantformId%>" target="_parent"><img src="images/exit.png" alt=""></a>
				</li>
			<%}%> 

            </li>
            <li class = "touxiang">
                <img src = "images/touxiang.png"><span>${currentPerson.name }</span>
                <a target = "diyiframe" href="${basePath }/legislate/updatePassword.jsp" style="color: white;">修改密码</a>
                <a target = "_blank" href="${basePath }/legislate/NtkoOfficeControlSetup.zip" style="color: white;">NTKO</a>
            </li>
        </ul>
    </div>
</div>
</div>

<%

Boolean isLfc = (Boolean)session.getAttribute("isLfc");
Boolean isZhc = (Boolean)session.getAttribute("isZhc");
Boolean isFzb = (Boolean)session.getAttribute("isFzb");

%>
<!--头部结束-->
<div class = "content">
    <div class = "left">
        <%if(isZhc){ %>
        <ul class = "side-tab">
            <li class = "side-tit">
                <img src = "images/work.png">
                <a href = "###" class = "tab-xwfb">范本管理
                    <span><img src = "images/xiala.png"></span>
                </a>
            </li>
            <div class = "xiala expand">
                <h4>
                    <img src = "images/erji.png" class = "menu-icon">
                    <a href = "${basePath}/legislate/listModel.do" target = "diyiframe" class = "menu-list">范本管理</a></h4>
            </div>
        </ul>
		<%} %>    
        <ul class = "side-tab">
            <li class = "side-tit">
                <img src = "images/banwen.png">
                <a href = "###" class = "tab-xwfb">立法计划项目
                    <span><img src = "images/enter.png"></span>
                </a>
            </li>
            <div class = "xiala ">
            	<%if(!isZhc||!isFzb){ %>
	               <h4>
	                   <img src = "images/erji.png" class = "menu-icon">
	                   <a href = "${basePath}/legislate/plan_init_list.do" target = "diyiframe" class = "menu-list">立法计划项目上报</a>
	               </h4>
                <%}%>
                <%if(isLfc||isZhc){ %>
            	    <h4>
	                    <img src = "images/erji.png" class = "menu-icon">
	                    <a href = "${basePath}/legislate/assessment_dis_list.do" target = "diyiframe" class = "menu-list">立法计划项目查看</a>
                    </h4>
            	<%}else if(isFzb){ %>
	                <h4>
	                    <img src = "images/erji.png" class = "menu-icon">
	                    <a href = "${basePath}/legislate/assessment_list.do?receive=N" target = "diyiframe" class = "menu-list">立法后评估项目接收</a>
	                </h4>
                <%}else{ %>
                <h4>
                    <img src = "images/erji.png" class = "menu-icon">
                    <a href = "${basePath}/legislate/assessment_up_list.do?flag=EDIT" target = "diyiframe" class = "menu-list">立法后评估项目上报</a>
                </h4>
                <%} %>
            </div>
        </ul>

        <ul class = "side-tab">
            <li class = "side-tit">
                <img src = "images/banan.png">
                <a href = "###" class = "tab-xwfb">法规规章草案
                    <span><img src = "images/enter.png"></span>
                </a>
            </li>
            <div class = "xiala ">
            	<%if(!isZhc){ %>
                	<h4>
                    <img src = "images/erji.png" class = "menu-icon">
                    <a href = "${basePath}/legislate/draft_init_list.do?state=init" target = "diyiframe" class = "menu-list">法规规章草案报送</a></h4>
                     <%} %>     
                 <%	if(isLfc || isZhc){
                 	
                  %>
                <h4>
                    <img src = "images/erji.png" class = "menu-icon">
                    <a href = "${basePath}/legislate/draft_upload_file.do" target = "diyiframe" class = "menu-list">材料上传</a></h4>
                     <%} %> 
            </div>
        </ul>
        
        <ul class = "side-tab"> <!-- 立法后评估 -->
            <li class = "side-tit">
                <img src = "images/banwen.png">
                <a href = "###" class = "tab-xwfb">规章立法后评估报告
                    <span><img src = "images/enter.png"></span>
                </a>
            </li>
            <div class = "xiala ">
            	<%if(isLfc){ %>
            	    <h4>
                    <img src = "images/erji.png" class = "menu-icon">
                    <a href = "${basePath}/legislate/assessment_report_dis_list.do" target = "diyiframe" class = "menu-list">评估报告查看</a></h4>
                 <%}else if(isFzb){ %>
                    <h4>
                    <img src = "images/erji.png" class = "menu-icon">
                    <a href = "${basePath}/legislate/assessment_report_list.do?receive=N" target = "diyiframe" class = "menu-list">评估报告接收</a></h4>
                 <%}else{ %>
                 	<h4>
                    <img src = "images/erji.png" class = "menu-icon">
                    <a href = "${basePath}/legislate/assessment_reportUp_list.do?flag=EDIT" target = "diyiframe" class = "menu-list">评估报告报送</a></h4>
                 <%} %>     
            </div>
        </ul>
    </div>
    <!--左侧导航栏结束-->
    <iframe src = "${basePath}/legislate/indexss.jsp" frameborder = "0" name = "diyiframe" class = "right">
    </iframe>
    <!--表单结束-->
</div>
</body>
<script>
    $(function () {
    var pageH=$(window).height();
    var pageW=$(window).width();
    $('.content').height(pageH).width(pageW)
        $('.right').width($(window).width() - 290).height(pageH-108);
        $(window).resize(function () {
            var pageW = $(window).width();
            var pagew = pageW - 280;
            $('.right').width(pagew);
        });
            //menu菜单栏展开-20170601
            $(".left .xiala li").on("click", function () {
                $(".left .xiala li").removeClass("xuanzhong");
                $(this).addClass("xuanzhong");
            });
            $(".left .side-tit").on("click", function () {
                $(".left .xiala").removeClass("expand");
                $(this).next(".xiala").addClass("expand");
                $(".side-tit a img").attr("src", "images/enter.png");
                $(this).find("a span img").attr("src", "images/xiala.png");
            });
            $(".left .xiala h4").on("click", function () {
                $(".xiala ul").hide();
                $(this).next("ul").show();
            })
    })
</script>
</html>