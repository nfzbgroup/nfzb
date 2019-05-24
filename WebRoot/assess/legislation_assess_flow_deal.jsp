<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="csrf-token" content="Y5OVOmDep8d96uDKyDf7EsLvELGUUrPLSimrCRo8" />
    <title>法规规章草案后评估</title>
    <link href="assets/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="assets/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="assets/css/animate.min.css" rel="stylesheet">
    <link href="assets/css/style.min.css?v=4.0.0" rel="stylesheet">
    <link href="assets/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <link href="assets/plugin/bootstrap-datetimepicker/css/bootstrap-datetimepicker.min.css" rel="stylesheet">
    <style>
        /*边框样式*/
        ul{padding:0;}
        li{ list-style: none;}
        a{display: block;}
        .border_style{border-style:solid;}
        .border_width{border-width:2px;}
        .border_color_t{border-color: transparent;}
        .border_color_g{border-color: #98b954;}
        .border_color_blue{border-color: #4a7ebb;
            -webkit-box-shadow: 0px 0px 0px 1px #e5e5e5;
            -moz-box-shadow: 0px 0px 0px 1px #e5e5e5;
            box-shadow: 0px 0px 0px 1px #e5e5e5;}
        .border_color_red{border-color: #c00000;
            -webkit-box-shadow: 0px 0px 0px 1px #e5e5e5;
            -moz-box-shadow: 0px 0px 0px 1px #e5e5e5;
            box-shadow: 0px 0px 0px 1px #e5e5e5;}
        .border_radius_circle{-webkit-border-radius: 100%;-moz-border-radius: 100%;border-radius: 100%;  }
        .border_color_j{border-color: #f69240;
            -webkit-box-shadow: 0px 0px 0px 1px #e5e5e5;
            -moz-box-shadow: 0px 0px 0px 1px #e5e5e5;
            box-shadow: 0px 0px 0px 1px #e5e5e5;}
        /*小标签边框设置*/
        .border_blue{border:1px solid #74cdff;}
        .border_yellow{border:1px solid #e6a93f;}
        /*背景颜色*/
        .bcg_gray{background: -webkit-linear-gradient(#dfeaff, #bbd2ff);background: -o-linear-gradient(#dfeaff, #bbd2ff);
            background: -moz-linear-gradient(#dfeaff, #bbd2ff); background: linear-gradient(#dfeaff, #bbd2ff);}
        .bcg_j{background: -webkit-linear-gradient(#ffe5d0, #ffc391);background: -o-linear-gradient(#ffe5d0, #ffc391);
            background: -moz-linear-gradient(#ffe5d0, #ffc391); background: linear-gradient(#ffe5d0, #ffc391);}
        .bcg_purple{background: -webkit-linear-gradient(#eae2f6, #cdbbe9);background: -o-linear-gradient(#eae2f6, #cdbbe9);
            background: -moz-linear-gradient(#eae2f6, #cdbbe9); background: linear-gradient(#eae2f6, #cdbbe9);}
        .bcg_blue1{background-color: #4f81bd;-webkit-box-shadow: 0px 0px 0px 1px #a5a5a5;-moz-box-shadow: 0px 0px 0px 1px #a5a5a5;  box-shadow: 0px 0px 0px 1px #a5a5a5;
            border: 2px solid #385d8a;}
        /*字体颜色*/
        .font_color_white{color: #ffffff;}
        .font_color_black{color: #000000;}
        .font_color_blue{color: #008cdc;}
        .font_color_red{color: #d15900;}
        .white-bg1{background-color: #ebebeb;}
        .flowPic {position: relative;top:80px;}
        .flowPic .left_menu{position: absolute;top:14px;left:20px;}

        .flowPic .legend_box p{line-height:25px;font-size: 18px;color:#333;margin-bottom:0;}
        .flowPic .legend_box{position:absolute;top:600px;left:70%;}
        .flowPic .legend_box p{line-height:25px;font-size: 18px;color:#333;margin-bottom:0;}
        .flowPic .legend_box p span.color_red{color:#ff0000;}
        .flowPic .legend_box p span.color_yellow{color:#e46c0a;}
        .flowPic .legend_box p span.color_purple{color:#7030a0;}

        .left_menu ul{position: relative;padding-left:0;}
        .left_menu ul li.flow3_li{width:66px;height:280px;text-align: center;border:2px solid #385d8a;background-color:#4f81bd;
            padding:28px 20px;position: absolute;}
        .left_menu ul li p{width:100%;height:100%;font-size:20px;font-weight:bold;color:#fff;}
        .left_menu ul li span{display: inline-block;width:22px;height:22px;position: absolute;  left: 42%;  }
        .left_menu ul li.flow3_li{top: 50%;margin-top:140px;}
        .guj_img{width:76px;height:521px;position:absolute;left:85px;top: 50%;margin-top:32px;}
        .guj_img img{width:76px;height:521px;}
        .flowPic .row1{position: absolute;left:160px;}
        .flowPic .row1 .row_child{position:relative;}
        .row .row_items{position:absolute;}
        .ibox-title{background-color:#aa0007;}
        .ibox-title .row_title{font-size:24px;font-weight:700;color:#fff;}
        .row_title{line-height:50px;text-align: center;font-size:16px;font-weight:700;  color: #333333;  }
        .flowPic .two_row{top:16px;}
        .flowPic .three_row{top:188px;}
        .flowPic .four_row{top:363px;}
        .flowPic .four_row_1{left:307px;top:601px;}
        .flowPic .four_row_2{left:307px;top:678px;}
        .flowPic .eight_row{top:528px;}
        .row1 .row_item1{left:0;}
        .row1 .row_item2,.row1 .row_item_top1{left:147px;}
        .row1.three_row .row_item_top1{top:0px;}
        .row1.twelve_row .row_item2{left:170px;}
        .row1 .row_item3,.row1 .row_item3.row_item_top1{left:295px;}
        .row1 .row_item_3{left:312px;}
        .row1 .row_item4{left:441px;}
        .row1 .row_item5{left:589px;}
        .row1.four_row .row_item_1{left:147px;top:-34px;}
        .row1.four_row .row_item_2{left:441px;top:-34px;}
        .row1.four_row .row_item_3{left:589px;top:-34px;}
        .row1.eight_row .row_item_1{left:147px;top:-34px;}

        .row1.two_row .row_item_1{left:146px;top:-34px;}
        .row1.two_row .row_item_2{left:295px;top:-34px;}

        .row1 .row_item4.row_item_top1{left:446px;}
        .row1 .row_item5.row_item_top1{left:597px;}
        .row1 .row_item6.row_item_top1{left:746px;}
        .row1 .arrow{position:absolute;}
        .row1.one_row .arrow{top:16px;}
        .row1.two_row .arrow{top:13px;}
        .row1.three_row .arrow, .row1.six_row .arrow{top:20px;}
        .row1.three_row .arrow2.line_top1{left:273px;}
        .row1.three_row .arrow3.line_top1{left:422px;}
        .row1.three_row .arrow4.line_top1{left:572px;}
        .row1.three_row .arrow5.line_top1{left:723px;}
        .row1.three_row .arrow.line_top1{top:21px;}
        .row1.seven_row .arrow,.row1.eight_row .arrow,.row1.nine_row .arrow,.row1.ten_row .arrow
        ,.row1.eleven_row .arrow ,.row1.twelve_row .arrow,.row1.four_row .arrow,.row1.four_row_1 .arrow, .row1.five_row .arrow
        , .row1.five_row_01 .arrow{top:17px;}

        .row1 .arrow{display:block;width:24px;height:5px;}
        .row1.one_row .arrow1,.row1.seven_row .arrow1,.row1.eight_row .arrow1
        ,.row1.nine_row .arrow1,.row1.ten_row .arrow1,.row1.eleven_row .arrow1,.row1.twelve_row .arrow1{left:-24px;}
        .row1.one_row .arrow2,.row1.eleven_row .arrow2,.row1.three_row .arrow1.line_top1{left:124px;}
        .row1.twelve_row .arrow2{left:147px;}
        .row1.one_row .arrow3,.row1.eleven_row .arrow3{left:271px;}

        .row1.two_row .arrow1,.row1.three_row .arrow1,.row1.four_row .arrow1
        ,.row1.five_row .arrow1, .row1.six_row .arrow1,.row1.seven_row .arrow2,
        .row1.eight_row .arrow2,.row1.nine_row .arrow2,.row1.ten_row .arrow2{left:124px;}
        .row1.two_row .arrow2,.row1.three_row .arrow2,.row1.four_row .arrow2
        , .row1.five_row .arrow2, .row1.six_row .arrow2,.row1.seven_row .arrow3,
        .row1.eight_row .arrow3, .row1.nine_row .arrow3{left:271px;}
        .row1.two_row .arrow3,.row1.three_row .arrow3, .row1.four_row .arrow3
        ,.row1.five_row .arrow3,.row1.six_row .arrow3 {left:418px;}
        .row1.four_row .arrow4{left:566px;}
        .row1.four_row_1 .arrow1{left:126px;}
        .row1.four_row_1 .arrow2{left:273px;}
        .row1.four_row_1 .arrow3{left:420px;}



        .row1 div{width:126px;height:39px;text-align: center;}
        .row1 div p{width:126px;height:39px;display: table-cell;vertical-align: middle;}
        .row1 div span{display:inline-block;width:91px;height:15px;line-height:15px;text-align: center;
            background-color:#fff; position: absolute;top:34px;left:12%;}
        .row1 div span.img_style{width:23px;height:20px;line-height:20px;left:42%;}
        .row1 div span.img_style.two_work b{color:#d15900;}
        .flowPic .six_row h3{color:#8064a2;margin-top:0;}
        .flowPic .six_row .public_list li{width:108px;height:32px;line-height:32px;text-align:center;font-size:14px;color:#fff;font-weight:bold;
            -webkit-box-shadow: 0px 0px 0px 2px #afafaf;
            -moz-box-shadow: 0px 0px 0px 2px #afafaf;
            box-shadow: 0px 0px 0px 2px #afafaf;background-color:#403152;}
        .flowPic .six_row .public_list li+li{margin-top:15px;}
        .flowPic .chart_explain img{float:left;margin-right:3px;margin-top:3px;width:15px;height:16px;}
        .flowPic .chart_explain p{float:left;width:calc(100% - 18px);margin-bottom:0;line-height:25px;font-size:16px;font-weight:700;color:#595959;}
        .flowPic .chart_explain p span{border-bottom: 1px solid #595959;}
        .row1 div .notes{display:block;border:1px solid #17375e;padding:7px 4px;font-style:normal;color: #10253f;background-color:#fff;}
        .row1 div .notes1{display:block;width:164px;border:2px solid #e46c0a;padding:7px 4px;font-style:normal;background-color:#fff;
            text-align: center;color: #10253f;}
        .arrow_down{position: absolute;left:206px;}
        .arrow_down img{height:139px;}
        .arrow_down.arrow_down_01 {top: 50px;}
        .arrow_down.arrow_down_02 {top: 226px;}
        .arrow_down.arrow_down_03 {top: 393px;}
        .row1.four_row_1 .arrow_1{position:absolute;left: -16px;top: -52px;height: 78px;}
        .row1.four_row_2 .arrow_1{position:absolute;left: 126px;top: -55px;height: 83px;}

    </style>
</head>
<body class="white-bg1">
<div class="wrapper wrapper-content animated fadeInRight" style="background-color:white">
    <div class="row">
        <div class="ibox float-e-margins">
            <div class="ibox-title">
             <button style="padding-right: 5px" type="button" class="close" onclick="closeProcessIndex()">
					<span aria-hidden="true">&times;</span>
					<span class="sr-only">Close</span>
				</button>
                <div class="row">
                    <div class="row_title">业务流程-法规规章草案后评估</div>
                    <div>
                        <div class="flowPic" style="background-color:gray">
                            <!--图例-->
                            <div class="legend_box">
                                <p><span class="color_red">红框</span>流程涉及非秘单向导出</p>
                                <p><span class="color_purple">紫色</span>流程涉及司法局OA的系统对接</p>
                                <p><span class="color_yellow">黄色</span>流程涉及与市政府系统的对接</p>
                            </div>
                            <div class="left_menu">
                                <ul>
                                    <li class="flow3_li">
                                        <a href="#">
                                            <p class="font_color_white">立法计划管理</p>
                                        </a>
                                    </li>

                                </ul>
                            </div>
                            <div class="guj_img"><img src="/assess//assess/img/arrow_block_04.png" alt=""></div>
                            <div class="arrow_down arrow_down_01"><img src="/assess/img/arrow_down.png" alt=""></div>
                            <div class="arrow_down arrow_down_02"><img src="/assess/img/arrow_down.png" alt=""></div>
                            <div class="arrow_down arrow_down_03"><img src="/assess/img/arrow_down.png" alt=""></div>
                            <div class="row1 two_row">
                                <div class="row_child">
                                    <img src="/assess/img/arrow_right.png" class="arrow arrow1" alt="">
                                    <img src="/assess/img/arrow_right.png" class="arrow arrow2" alt="">
                                    <div class="cell row_items row_item1 bcg_blue1">
                                        <a href="#">
                                            <p class="font_color_white">立法后评估</br>启动</p>
                                        </a>
                                    </div>
                                    <div class="cell row_items row_item2 bcg_gray border_width border_style border_color_blue">
										<a href="javaScript:void(0)" id="NOD_0000000251" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                            <p class="font_color_black">立法后评估规</br>划启动</p>
                                            <span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
                                        </a>
                                    </div>
                                    <div class="cell row_items row_item_1">
                                        <a href="#">
                                            <i class="notes">每年第四季度</i>
                                        </a>
                                    </div>
                                    <div class="cell row_items row_item3 bcg_gray border_width border_style border_color_red">
											<a href="javaScript:void(0)" id="NOD_0000000252" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                            <p class="font_color_black">立法后评估项</br>目申请</p>
                                        </a>
                                    </div>
                                    <div class="cell row_items row_item_2">
                                        <a href="#">
                                            <i class="notes">待定申请表格</i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="row1 three_row">
                                <div class="row_child">
                                    <img src="/assess/img/arrow_right.png" class="arrow arrow1 line_top1" alt="">
                                    <img src="/assess/img/arrow_right.png" class="arrow arrow2 line_top1" alt="">
                                    <img src="/assess/img/arrow_right.png" class="arrow arrow3 line_top1" alt="">
                                    <img src="/assess/img/arrow_right.png" class="arrow arrow4 line_top1" alt="">
                                    <img src="/assess/img/arrow_right.png" class="arrow arrow5 line_top1" alt="">
                                    <div class="cell row_items row_item1 bcg_blue1">
                                        <a href="#">
                                            <p class="font_color_white">编制计划</p>
                                        </a>
                                    </div>
                                    <!--草案第二行-->
                                    <div class="cell row_items row_item2 row_item_top1 bcg_gray border_width border_style border_color_blue">
										<a href="javaScript:void(0)" id="NOD_0000000253" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                            <p class="font_color_black">立法后评估项</br>目汇总</p>
                                            <span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
                                        </a>
                                    </div>
                                    <div class="cell row_items row_item3 row_item_top1 bcg_gray border_width border_style border_color_blue">
                                        <a href="#">
                                            <p class="font_color_black">后评估汇总</br>分送</p>
                                            <span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>

                                        </a>
                                    </div>
                                    <div class="cell row_items row_item4 row_item_top1 bcg_gray border_width border_style border_color_blue">
										<a href="javaScript:void(0)" id="NOD_0000000254" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                            <p class="font_color_black">立法处初审</p>
                                            <span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>

                                        </a>
                                    </div>
                                    <div class="cell row_items row_item5 row_item_top1 bcg_gray border_width border_style border_color_blue">
										<a href="javaScript:void(0)" id="NOD_0000000255" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                            <p class="font_color_black">初审意见汇总</p>
                                            <span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>

                                        </a>
                                    </div>
                                    <div class="cell row_items row_item6 row_item_top1 bcg_gray border_width border_style border_color_blue">
                                        <a href="#">
                                            <p class="font_color_black">纳入后评估计</br>划</p>
                                            <span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>

                                        </a>
                                    </div>
                                    <!--<div class="cell row_items row_item_z">-->
                                        <!--<a href="#">-->
                                            <!--<i class="notes">立、改、废</br>正式、预备、修订</i>-->
                                        <!--</a>-->
                                    <!--</div>-->
                                </div>
                            </div>
                            <div class="row1 four_row">
                                <div class="row_child">
                                    <img src="/assess/img/arrow_right.png" class="arrow arrow1" alt="">
                                    <img src="/assess/img/arrow_right.png" class="arrow arrow2" alt="">
                                    <img src="/assess/img/arrow_right.png" class="arrow arrow3" alt="">
                                    <img src="/assess/img/arrow_right.png" class="arrow arrow4" alt="">
                                    <div class="cell row_items row_item1 bcg_blue1">
                                        <a href="#">
                                            <p class="font_color_white">开展评估</p>
                                        </a>
                                    </div>
                                    <div class="cell row_items row_item2 bcg_gray border_width border_style border_color_red">
										<a href="javaScript:void(0)" id="NOD_0000000256" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                            <p class="font_color_black">制定评估方案</p>
                                            <span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
                                        </a>
                                    </div>
                                    <div class="cell row_items row_item_1">
                                        <a href="#">
                                            <i class="notes">制式表格</i>
                                        </a>
                                    </div>
                                    <div class="cell row_items row_item3 bcg_gray border_width border_style border_color_blue">
										<a href="javaScript:void(0)" id="NOD_0000000257" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                            <p class="font_color_black">汇同立法处提</br>出完善建议</p>
                                            <span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
                                        </a>
                                    </div>
                                    <div class="cell row_items row_item4 bcg_gray border_width border_style border_color_red">
										<a href="javaScript:void(0)" id="NOD_0000000258" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                            <p class="font_color_black">定期发聩评估</br>进度</p>
                                            <span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
                                        </a>
                                    </div>
                                    <div class="cell row_items row_item_2">
                                        <a href="#">
                                            <i class="notes">按季度报</i>
                                        </a>
                                    </div>
                                    <div class="cell row_items row_item5 bcg_gray border_width border_style border_color_red">
										<a href="javaScript:void(0)" id="NOD_0000000259" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                            <p class="font_color_black">提交评估报告</p>
                                            <span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
                                        </a>
                                    </div>
                                    <div class="cell row_items row_item_3">
                                        <a href="#">
                                            <i class="notes">制定格式要求</i>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="row1 eight_row">
                                <div class="row_child">
                                    <img src="/assess/img/arrow_right.png" class="arrow arrow2" alt="">
                                    <div class="cell row_items row_item1 bcg_blue1 border_radius">
                                        <a href="#">
                                            <p class="font_color_white">评估后工作</p>
                                        </a>
                                    </div>
                                    <div class="cell row_items row_item_1">
                                        <a href="#">
                                            <i class="notes">5天内</i>
                                        </a>
                                    </div>
                                    <div class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_blue">
                                        <a href="#">
                                            <p class="font_color_black">本单位网站</br>公开</p>
                                            <span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
                                        </a>
                                    </div>

                                </div>
                            </div>
                            <div class="row1 four_row_1">
                                <div class="row_child">
                                    <img src="/assess/img/arrow_right_1.png" class="arrow_1" alt="">
                                    <img src="/assess/img/arrow_right.png" class="arrow arrow1" alt="">
                                    <img src="/assess/img/arrow_right.png" class="arrow arrow2" alt="">
                                    <img src="/assess/img/arrow_right.png" class="arrow arrow3" alt="">
                                    <div class="cell row_items row_item1 bcg_gray border_width border_style border_color_blue">
										<a href="javaScript:void(0)" id="NOD_0000000260" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                            <p class="font_color_black">分送立法处</p>
                                            <span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
                                        </a>
                                    </div>

                                    <div class="cell row_items row_item2 bcg_purple border_width border_style border_color_red">
                                        <a href="#">
                                            <p class="font_color_black">司法行政网</br>公开</p>
                                            <span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
                                        </a>
                                    </div>
                                    <div class="cell row_items row_item3 bcg_gray border_width border_style border_color_blue">
										<a href="javaScript:void(0)" id="NOD_0000000262" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                            <p class="font_color_black">年度评估计划</br>完成情况报告</p>
                                            <span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
                                        </a>
                                    </div>
                                    <div class="cell row_items row_item4 bcg_j border_width border_style border_color_j">
										<a href="javaScript:void(0)" id="NOD_0000000263" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                            <p class="font_color_black">报市政府</p>
                                            <span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                            <div class="row1 four_row_2">
                                <img src="/assess/img/arrow_right_2.png" class="arrow_1" alt="">
                                <div class="row_child">
                                    <div class="cell row_items row_item1 bcg_gray border_width border_style border_color_blue">
										<a href="javaScript:void(0)" id="NOD_0000000261" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                            <p class="font_color_black">评估报告接收</p>
                                            <span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
                                        </a>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
function openDemonstrationPage(buttonId, method, stPlanId) {
	var nodeHref = $('#' + buttonId).parent().attr('nodeHref');
	//alert(buttonId);
	//alert(nodeHref);
	var index = method.indexOf("__");
	if (method == '' || index == 0)
		method = nodeHref;
	if (method == '' || typeof (method) == "undefined") {
		alert('当前节点不可以操作！');
		return false;
	}
	//alert("method=" + method);
	console.log("method=" + method);
	$("#processIndexForm").modal({
		remote : "${basePath}/legislationPlan/draft_plan_info.do?stNodeId=" + buttonId + "&method=" + method + "&stPlanId=" + stPlanId
	});
};


function closeProcessIndex() {
	 $('#processIndexRootForm').modal('hide');
}


//页面加载后，去后台获取所有节点的状态，连接等信息
$('#processIndexRootForm').on('shown.bs.modal', function(event) {	
	<!--Ajax请求-->
	$.post("../legislationPlan/draft_plan_info.do?stPlanId=${request.planInfo.stPlanId}&method=openPlanIndexPage_ajax", function(data) {
		//alert(11);
		if (data.success) {
			//alert(JSON.stringify(data));
			$.each(data.nodeInfoArray, function(index, item) {
				//改变当前按钮的背景颜色
				$('#' + item.node).parent().removeClass('bcg_blue');
				//如果不是绿的，可以覆盖。部门多个接收任务，可以绿盖蓝。
				 if (!$('#' + item.node).parent().hasClass("bcg_green")) {
					$('#' + item.node).parent().addClass(item.colorSet);
				} 
				//$('#' + item.node).parent().addClass("bcg_green");
				$('#' + item.node).parent().attr('nodeHref', item.nodeHref);
				$('#' + item.node).addClass('addHand');
			});
		}
	});

});
</script>
</body>
</html>