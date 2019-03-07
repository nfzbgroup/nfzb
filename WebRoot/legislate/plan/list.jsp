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

    <title>详细办理页面</title>
    <link rel = "stylesheet" type = "text/css" href = "css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "css/sui-themes-green-append.css" media = "screen"/>
    <script src = "js/jquery.js"></script>
    <script src = "js/sui.js"></script>
    <script src = "js/tanchuang.js"></script>
</head>
<body class = "body_bg_1" data-pageCode = "homepage1">
<div class = "header">
    <div class = "top-left">
        <img src = "images/text.png">
    </div>
    <div class = "top-right">
        <ul>
            <li class = "touxiang">
                <img src = "images/touxiang.png"><span>王美丽</span>
            </li>
            <li class = "notice">
                <a><img src = "images/notice.png"></a>
                <i class = "new01"><span>3</span></i>
            </li>
            <li class = "tuichu"><a><img src = "images/exit.png"></a></li>
        </ul>
    </div>
</div>
</div>


<!--头部结束-->
<div class = "content">
    <div class = "left">
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
                <!--<ul>-->
                <!--<li><a><img src = "images/sanji.png">准予备案</a></li>-->
                <!--<li><a><img src = "images/sanji.png">准予备案(附法制建议)</a></li>-->
                <!--<li><a><img src = "images/sanji.png">中止审查</a></li>-->
                <!--</ul>-->
            </div>
        </ul>

        <ul class = "side-tab">
            <li class = "side-tit">
                <img src = "images/banwen.png">
                <a href = "###" class = "tab-xwfb">立法计划报送管理
                    <span><img src = "images/enter.png"></span>
                </a>
            </li>
            <div class = "xiala ">
                <h4>
                    <img src = "images/erji.png" class = "menu-icon">
                    <a href = "${basePath}/legislate/plan_init_list.do" target = "diyiframe" class = "menu-list">立法计划上报</a></h4>
                <h4>
                    <img src = "images/erji.png" class = "menu-icon">
                    <a href = "${basePath }/legislate/plan_unreceive_list.do" target = "diyiframe" class = "menu-list">立法计划分办</a></h4>
                <h4>
                    <img src = "images/erji.png" class = "menu-icon">
                    <a href = "${basePath}/legislate/plan_unaudit_list.do" target = "diyiframe" class = "menu-list">立法计划审核</a></h4>
                <h4>
                    <img src = "images/erji.png" class = "menu-icon">
                    <a href = "${basePath}/legislate/lfjhhz.do" target = "diyiframe" class = "menu-list">立法计划汇总</a></h4>
                <!--<ul>-->
                <!--<li><a><img src = "images/sanji.png">准予备案</a></li>-->
                <!--<li><a><img src = "images/sanji.png">准予备案(附法制建议)</a></li>-->
                <!--<li><a><img src = "images/sanji.png">中止审查</a></li>-->
                <!--</ul>-->
            </div>
        </ul>

        <ul class = "side-tab">
            <li class = "side-tit">
                <img src = "images/banan.png">
                <a href = "###" class = "tab-xwfb">规章草案起草及办理管理
                    <span><img src = "images/enter.png"></span>
                </a>
            </li>
            <div class = "xiala ">
                <h4>
                    <img src = "images/erji.png" class = "menu-icon">
                    <a href = "${basePath}/legislate/draft_init_list.do" target = "diyiframe" class = "menu-list">规章草案起草列表</a></h4>
                <h4>
                    <h4>
                        <img src = "images/erji.png" class = "menu-icon">
                        <a href = "${basePath}/legislate/draft_unreceive_list.do" target = "diyiframe" class = "menu-list">规章草案分办列表</a></h4>
                    <h4>
                        <img src = "images/erji.png" class = "menu-icon">
                        <a href = "${basePath}/legislate/draft_unprocess_list.do" target = "diyiframe" class = "menu-list">规章草案办理列表</a></h4>
            </div>
        </ul>

        <ul class = "side-tab">
            <li class = "side-tit">
                <img src = "images/work.png">
                <a href = "###" class = "tab-xwfb">征询意见管理
                    <span><img src = "images/enter.png"></span>
                </a>
            </li>
            <div class = "xiala ">
                <h4>
                    <img src = "images/erji.png" class = "menu-icon">
                    <a href = "${basePath}/legislate/Opinionlist.do" target = "diyiframe" class = "menu-list">公开社会意见</a></h4>
                <h4>
                    <img src = "images/erji.png" class = "menu-icon">
                    <a href = "${basePath}/legislate/regulations/dwyjzq.jsp" target = "diyiframe" class = "menu-list">单位意见</a></h4>
                <h4>
                    <img src = "images/erji.png" class = "menu-icon">
                    <a href = "${basePath}/legislate/regulations/lftzh.jsp" target = "diyiframe" class = "menu-list">立法听证会</a></h4>
                <!--<ul>-->
                <!--<li><a><img src = "images/sanji.png">准予备案</a></li>-->
                <!--<li><a><img src = "images/sanji.png">准予备案(附法制建议)</a></li>-->
                <!--<li><a><img src = "images/sanji.png">中止审查</a></li>-->
                <!--</ul>-->
            </div>
        </ul>
        <ul class = "side-tab">
            <li class = "side-tit">
                <img src = "images/banhui.png">
                <a href = "###" class = "tab-xwfb">审核会议管理
                    <span><img src = "images/enter.png"></span>
                </a>
            </li>
            <div class = "xiala ">
                <h4>
                    <img src = "images/erji.png" class = "menu-icon">
                    <a href = "${basePath}/legislate/regulations/shhy.jsp" target = "diyiframe" class = "menu-list">审核会议</a></h4>
            </div>
        </ul>
    </div>
    <!--左侧导航栏结束-->
    <iframe src = "${basePath}/legislate/listModel.do" frameborder = "0" name = "diyiframe" class = "right">
    </iframe>
    <!--表单结束-->
</div>
</body>
<script>
    $(function () {
        $('.right').width($(window).width() - 280);
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