<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <style>
        /*边框样式*/
        li{ list-style: none;}
        a{display: block;}
        .border_radius{  -webkit-border-radius: 19px;  -moz-border-radius: 19px;  border-radius: 19px;}
        .border_style{border-style:solid;}
        .border_width{border-width:2px;}
        .border_color_red{border-color: #c83535;}
        .border_color_yellow{border-color: #df8e00;}
        .border_color_purple{border-color: #7700af;}
        .border_color_brown{border-color: #af7c00;}
        .border_color_t{border-color: transparent;}
        .border_color_grey{border-color: #999;}
        .border_color_grey1{border-color: #adadad;}

        /*小标签边框设置*/
        .border_blue{border:1px solid #74cdff;}
        .border_yellow{border:1px solid #e6a93f;}
    /*背景颜色*/
        .bcg_gray{background-color: #cecece;  }
        .bcg_blue{background-color: #c7ebff;}
        .bcg_blue1{background-color: #008cdc;}
        .bcg_green{background-color: #daf1bc;}
        /*字体颜色*/
        .font_color_white{color: #ffffff;}
        .font_color_black{color: #333333;}
        .font_color_blue{color: #008cdc;}
        .font_color_red{color: #d15900;}
        .font_color_gray{color: #adadad;}
        .border_radius_circle{-webkit-border-radius: 100%;-moz-border-radius: 100%;border-radius: 100%;  }
        .white-bg1{background-color: #fff;}
        .flowPic {position: relative;}
        .ibox-title_inner{background-color:#d05353;}
        .ibox-title_inner .row_title_inner{font-size:24px;font-weight:700;color:#fff;}
        .row_title{line-height:50px;text-align: center;font-size:16px;font-weight:700;  color: #333333;  }
        .row_title_inner{line-height:50px;text-align: center;font-size:16px;font-weight:700;  color: #333333;  }
        .flowPic .left_menu{position: absolute;top:14px;left:20px;}
        .flowPic .nav_line{position:absolute;top:14px;left:61px;}
        .flowPic .nav_line img{height:800px;}
        .flowPic .legend_box{position:absolute;top:600px;left:70%;}
        .flowPic .legend_box p{line-height:25px;font-size: 18px;color:#333;margin-bottom：0;}
        .flowPic .legend_box p.title{font-weight:700;text-align: center;}
        .flowPic .legend_box p span.color_red{color:#c83535;}
        .flowPic .legend_box p span.color_yellow{color:#df8e00;}
        .flowPic .legend_box p span.color_purple{color:#7700af;}
        .flowPic .legend_box p span.color_brown{color:#af7c00;}
        .left_menu ul{position: relative;padding-left:0;}
        .left_menu ul li{width:126px;height:38px;text-align: center;-webkit-border-radius: 19px;  -moz-border-radius: 19px;  border-radius: 19px;  background-color: #008cdc;  position: absolute;  }
        .left_menu ul li p{width:126px;height:38px;display: table-cell;vertical-align: middle;  }
        .left_menu ul li span{display: inline-block;width:22px;height:22px;position: absolute;left: 42%;top: 33px;   }
        .left_menu ul li span.flow1_span{background: url("${basePath}/legislation/assets/demo/flow2.png") no-repeat center;}
        .left_menu ul li span.flow2_span{background: url("${basePath}/legislation/assets/demo/flow1.png") no-repeat center;}
        .left_menu ul li span.flow3_span{background: url("${basePath}/legislation/assets/demo/flow3.png") no-repeat center;}
        .left_menu ul li span.flow4_span{background: url("${basePath}/legislation/assets/demo/flow4.png") no-repeat center;}
        .left_menu ul li span.flow5_span{background: url("${basePath}/legislation/assets/demo/flow5.png") no-repeat center;}
        .left_menu ul li span.flow6_span{background: url("${basePath}/legislation/assets/demo/flow6.png") no-repeat center;}
        .left_menu ul li span.flow7_span{background: url("${basePath}/legislation/assets/demo/flow7.png") no-repeat center;}
        .left_menu ul li.flow2_li{top: 56px;}
        .left_menu ul li.flow3_li{top: 236px;}
        .left_menu ul li.flow4_li{top:415px;}
        .left_menu ul li.flow5_li{top:474px;}
        .left_menu ul li.flow6_li{top:535px;}
        .left_menu ul li.flow7_li{top:594px;}
        .left_menu ul li.flow8_li{top:651px;}
        .left_menu ul li.flow9_li{top:712px;}
        .left_menu ul li.flow10_li{top:768px;}
        .flowPic .row1{position: absolute;left:169px;}
        .flowPic .row1 .row_child{position:relative;}
        .row .row_items{position:absolute;}
        .flowPic .one_row{top:70px;}
        .flowPic .two_row{top:130px;}
        .flowPic .three_row{top:190px;}
        .flowPic .four_row{top:248px;}
        .flowPic .five_row{top:310px;}
        .flowPic .six_row{top:369px;}
        .flowPic .seven_row{top:430px;}
        .flowPic .eight_row{top:489px;}
        .flowPic .nine_row{top:549px;}
        .flowPic .ten_row{top:608px;}
        .flowPic .eleven_row{top:667px;}
        .flowPic .twelve_row{top:729px;}
        .row1 .row_item1{left:0;}
        .row1 .row_item2{left:147px;}
        .row1.twelve_row .row_item2{left:170px;}
        .row1 .row_item3{left:294px;}
        .row1 .row_item4{left:441px;}
        .row1 .row_item5{left:588px;}
        .row1 .row_item6{left:735px;}
        .row1 .row_item7{left:884px;}
        .row1 .row_item8{left:738px;top:76px;}
        .row1 .row_item9{left:884px;top:76px;}
        .row1 .arrow{position:absolute;}
        .row1.one_row .arrow{top:16px;}
        .row1.two_row .arrow{top:13px;}
        .row1.three_row .arrow, .row1.four_row .arrow, .row1.five_row .arrow, .row1.six_row .arrow{top:20px;}
        .row1.seven_row .arrow,.row1.eight_row .arrow,.row1.nine_row .arrow,.row1.ten_row .arrow
        ,.row1.eleven_row .arrow ,.row1.twelve_row .arrow{top:17px;}

        .row1 .arrow{display:block;width:24px;height:5px;}
        .row1.one_row .arrow1,.row1.seven_row .arrow1,.row1.eight_row .arrow1
        ,.row1.nine_row .arrow1,.row1.ten_row .arrow1,.row1.eleven_row .arrow1,.row1.twelve_row .arrow1{left:-24px;}
        .row1.one_row .arrow2,.row1.eleven_row .arrow2{left:124px;}
        .row1.twelve_row .arrow2{left:147px;}
        .row1.one_row .arrow3,.row1.eleven_row .arrow3{left:271px;}

        .row1.two_row .arrow1,.row1.three_row .arrow1,.row1.four_row .arrow1
        ,.row1.five_row .arrow1, .row1.six_row .arrow1,.row1.seven_row .arrow2,
        .row1.eight_row .arrow2,.row1.nine_row .arrow2,.row1.ten_row .arrow2{left:124px;}
        .row1.two_row .arrow2,.row1.three_row .arrow2,.row1.four_row .arrow2
        , .row1.five_row .arrow2, .row1.six_row .arrow2,.row1.seven_row .arrow3,
        .row1.eight_row .arrow3, .row1.nine_row .arrow3{left:271px;}
        .row1.two_row .arrow3,.row1.three_row .arrow3, .row1.four_row .arrow3
        ,.row1.five_row .arrow3,.row1.six_row .arrow3,.row1.seven_row .arrow4
        ,.row1.eleven_row .arrow4,.row1.nine_row .arrow4{left:418px;}
        .row1.seven_row .arrow5,.row1.two_row .arrow4,.row1.three_row .arrow4
        ,.row1.five_row .arrow4,.row1.six_row .arrow4{left:565px;}
        .row1.seven_row .arrow6,.row1.three_row .arrow5,.row1.five_row .arrow5{left:712px;}
        .row1.seven_row .arrow7,.row1.three_row .arrow6,.row1.five_row .arrow6{left:860px;}
        .row1.seven_row .arrow8{left:860px;top:90px;}
        .row1.seven_row .arrow9{position: absolute;left:798px;top:66px;height:10px;}

        .row1 div{width:126px;height:39px;text-align: center;}
        .row1 div p{width:126px;height:39px;display: table-cell;vertical-align: middle;}
        .row1 div span{display:inline-block;width:91px;height:15px;line-height:15px;text-align: center;
           background-color:#fff; position: absolute;top:34px;left:12%;}
        .row1 div span.img_style{width:23px;height:20px;line-height:20px;left:42%;}
        .row1 div span.img_style1{left:40%;}
        .row1 div span.img_style2{left:39%;}
        .arrow_block{position:absolute;top:144px;left:144px;}
        .arrow_line{display: inline-block;height:1px;background-color:#4a7ebb;position: absolute;top:18px;}
        .arrow_line.line1{width:30px;left:1000px;}
        .arrow_line.line2{width:1px;height:49px;left:1030px;}
        .arrow_line.line3{width:232px;left:799px;top:66px;}
        .row1 div span.img_style.two_work{width:50px;}
        .row1 div span.img_style2.two_work{left:27%;}
        .row1 div span.img_style.two_work b{color:#d15900;}
        .row1 div.width_p{width:150px;padding-left:10px;}
        .self_style{  -webkit-border-radius: 100%;-moz-border-radius: 100%;border-radius: 100%;border:1px solid #fff;}
        .flow8_span1{background:#008cdc url("${basePath}/legislation/assets/demo/icon01.png") no-repeat center;}
        .flow8_span2{background:#008cdc url("${basePath}/legislation/assets/demo/icon02.png") no-repeat center;}
        .flow8_span3{background:#008cdc url("${basePath}/legislation/assets/demo/icon03.png") no-repeat center;}
        .divright { position:absolute; color:#fff;z-index: 99; font-size: 1.2em; background-color: #aa0007;width: 1.5em !important;height: 1.5em !important;left : 85%;top : -12px;text-align: center;-webkit-border-radius: 2.75em;border-radius: 2.75em;}
   
    </style>
</head>
<body class="gray-bg white-bg1">
<div class="wrapper wrapper-content animated fadeInRight">
    <div class="row">
        <div class="ibox float-e-margins">
            <div class="ibox-title_inner">
	        	<button style="padding-right: 5px" type="button" class="close" onclick="closeProcessIndex()">
					<span aria-hidden="true">&times;</span>
					<span class="sr-only">Close</span>
				</button>
                <div class="row">
                    <div class="row_title_inner">立法过程样本管理(点击修改节点样本)</div>
                    <div class="flowPic">
                        <!--<img src="flowPicNew.png" alt="">-->
                        <img src="${basePath}/legislation/assets/demo/arrow_block_03.png" alt="" class="arrow_block">
                        <!--图例-->
                        <div class="legend_box">
                            <p class="title">图&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;例</p>
                            <p><span class="color_red">红色、红框</span>流程涉及非秘单向导出</p>
                            <p><span class="color_yellow">黄色</span>司法局OA</p>
                            <p><span class="color_purple">紫色</span>网站</p>
                            <p><span class="color_brown">棕色</span>市政府系统</p>
                        </div>
                        <div class="left_menu">
                            <div class="nav_line"><img src="${basePath}/legislation/assets/demo/nav_meun.png" alt=""></div>
                            <ul>
                                <li nodeHref="draft_create_info">
                                <div class="divright"></div>
                                    <a href="javaScript:void(0)" id="NOD_0000000101"  onclick="openDemonstrationPage(this.id)">
                                        <p class="font_color_white">法规规章</br>草案起草</p>
                                        <span class="border_radius flow1_span"></span>
                                    </a>
                                </li>
                                <li class="flow2_li">
                                    <a href="#">
                                        <p class="font_color_white">接受分办</p>
                                        <span class="border_radius flow2_span"></span>
                                    </a>
                                </li>
                                <li class="flow3_li">
                                    <a href="#">
                                        <p class="font_color_white">立法办理</p>
                                        <span class="border_radius flow3_span"></span>
                                    </a>
                                </li>
                                <li class="flow4_li">
                                    <a href="#">
                                        <p class="font_color_white">审核会议</p>
                                        <span class="border_radius flow4_span"></span>
                                    </a>
                                </li>
                                <li class="flow5_li">
                                    <a href="#">
                                        <p class="font_color_white">报审审签</br>（签报）</p>
                                        <span class="border_radius flow5_span"></span>
                                    </a>
                                </li>
                                <li class="flow6_li">
                                    <a href="#">
                                        <p class="font_color_white">常务会议</p>
                                        <span class="border_radius flow6_span"></span>
                                    </a>
                                </li>
                                <li class="flow7_li">
                                    <a href="#">
                                        <p class="font_color_white">规章（法规议案）</br>草案报市长审签</p>
                                        <span class="border_radius flow7_span"></span>
                                    </a>
                                </li>
                                <li class="flow8_li">
                                    <a href="#">
                                        <p class="font_color_white">规章（法规议<br>案）正式版本</p>
                                        <span class="border_radius flow8_span self_style flow8_span1"></span>
                                    </a>
                                </li>
                                <li class="flow9_li">
                                    <a href="#">
                                        <p class="font_color_white">规章文本报备</p>
                                        <span class="border_radius flow9_span self_style flow8_span2"></span>
                                    </a>
                                </li>
                                <li class="flow10_li">
                                    <a href="#">
                                        <p class="font_color_white">规章规章草案<br>归档</p>
                                        <span class="border_radius flow10_span self_style flow8_span3"></span>
                                    </a>
                                </li>
                            </ul>
                        </div>
                        <div class="row1 one_row">
                            <div class="row_child">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow1" alt="">
                                <!-- <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow2" alt="">-->
                                <div nodeHref="openSeparateTabPage" class="cell row_items row_item1 bcg_gray border_width border_style border_radius border_color_t gb">
                                    <a href="javaScript:void(0)" id="NOD_0000000102" class="layer_model_link" data-title="部门征求意见编辑" onclick="openDemonstrationPage(this.id)">
                                        <p class="font_color_black">分办审核</p>
                                        <span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
                                    </a>
                                </div>
                                <!-- <div class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t gc">
                                    <a href="#">
                                        <p class="font_color_black">立法处分办</p>
                                        <span class="img_style img_style1 font_color_blue border_blue border_radius_circle">立</span>
                                    </a>
                                </div> -->
                            </div>
                        </div>

						<div class="row1 two_row">
							<div class="row_child">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow1" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow2" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow3" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow4" alt="">
								<div class="cell row_items row_item1 bcg_blue1 border_radius">
									<a href="javaScript:void(0)" class="removeHand">
										<p class="font_color_white">部门意见征求</p>
										<span class="img_style border_radius_circle">
											<img src="${basePath}/legislation/assets/demo/flow10.png">
										</span>
									</a>
								</div>
								<div nodeHref="" class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000120" class="removeHand" handStatus="1" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">
											部门征求意见
											</br>
											编辑
										</p>
										<span class="img_style img_style1 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>
								<div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_yellow">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000121" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">
											部门征求意见
											</br>
											盖章
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_red">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000123" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">
											部门征求意见
											</br>
											发送部门
										</p>
										<span class="img_style img_style1 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>

								<div class="cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_red">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000122" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">
											部门征求意见
											</br>
											接受发聩
										</p>
										<span class="img_style img_style1 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>

							</div>
						</div>


						<div class="row1 three_row">
							<div class="row_child">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow1" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow2" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow3" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow4" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow5" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow6" alt="">
								<div class="cell row_items row_item1 bcg_blue1 border_radius">
									<a href="javaScript:void(0)" class="removeHand">
										<p class="font_color_white">公开征求意见</p>
										<span class="img_style border_radius_circle">
											<img src="${basePath}/legislation/assets/demo/flow11.png">
										</span>
									</a>
								</div>
								<div nodeHref="" class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000130" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">
											公开征求意见
											</br>
											发起
										</p>
										<span class="img_style img_style1 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>
								<div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_red">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000131__TODO" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">
											公开征求意见
											</br>
											接收
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_yellow">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000131__SEND" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">
											公开征求意见
											</br>
											送审
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_purple">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000131__PUBLISH" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">
											网上征求意见
											</br>
											上网发布
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item6 bcg_gray border_width border_style border_radius border_color_purple">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000131__GATHER" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">汇总整理分析</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item7 bcg_gray border_width border_style border_radius border_color_purple">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000131__RESULT" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">采纳意见发布</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
							</div>
						</div>
						<div class="row1 four_row">
							<div class="row_child">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow1" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow2" alt="">
								<div class="cell row_items row_item1 bcg_blue1 border_radius">
									<a href="javaScript:void(0)" class="removeHand">
										<p class="font_color_white">专家论证会</p>
										<span class="img_style border_radius_circle">
											<img src="${basePath}/legislation/assets/demo/flow12.png">
										</span>
									</a>
								</div>
								<div nodeHref="" class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000150" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">
											专家论证会发
											</br>
											起
										</p>
										<span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>
								<div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_t">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000151" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">
											专家论证会结
											</br>
											果归档
										</p>
										<span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>
							</div>
						</div>
						<div class="row1 five_row">
							<div class="row_child">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow1" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow2" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow3" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow4" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow5" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow6" alt="">
								<div class="cell row_items row_item1 bcg_blue1 border_radius">
									<a href="javaScript:void(0)" class="removeHand">
										<p class="font_color_white">立法听证会</p>
										<span class="img_style border_radius_circle">
											<img src="${basePath}/legislation/assets/demo/flow13.png">
										</span>
									</a>
								</div>
								<div nodeHref="" class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000140" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">
											立法听证会
											</br>
											发起
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">立</span>
									</a>
								</div>
								<div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_red">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000141__TODO" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">
											立法听证会发
											</br>
											接收
										</p>
										<span class="img_style img_style2 font_color_blue border_blue border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_yellow">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000141__SEND" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">
											立法听证会
											</br>
											送审
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_purple">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000141__PUBLISH" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">
											立法听证会上
											</br>
											网发布
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item6 bcg_gray border_width border_style border_radius border_color_purple">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000141__GATHER" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">
											立法听证会网
											</br>
											上报名
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item7 bcg_gray border_width border_style border_radius border_color_t">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000141__RESULT" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">
											立法听证会结
											</br>
											果归档
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
							</div>
						</div>


						<div class="row1 six_row">
							<div class="row_child">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow1" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow2" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow3" alt="">
								<img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow4" alt="">
								<div class="cell row_items row_item1 bcg_blue1 border_radius">
									<a href="javaScript:void(0)" class="removeHand">
										<p class="font_color_white">部门会签</p>
										<span class="img_style border_radius_circle">
											<img src="${basePath}/legislation/assets/demo/flow9.png">
										</span>
									</a>
								</div>
								<div nodeHref="" class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000160" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">部门会签编辑</p>
										<span class="img_style img_style1 font_color_blue border_blue border_radius_circle">立</span>
									</a>
								</div>
								<div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_yellow">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000161" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">部门会签盖章</p>
										<span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
									</a>
								</div>
								<div class="cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_red">
									<div class="divright"></div>
									<a href="javaScript:void(0)" id="NOD_0000000163" class="removeHand" onclick="openDemonstrationPage(this.id)">
										<p class="font_color_black">
											部门会签发起
											</br>
											发送部门
										</p>
										<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">立</span>
									</a>
								</div>
								<c:choose>
									<c:when test="${nodeId=='NOD_0000000162'||nodeId=='NOD_0000000122'}">
										<div class="cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_red">
											<div class="divright"></div>
											<a href="javaScript:void(0)" id="NOD_0000000162" handStatus="1" class="removeHand" onclick="openDemonstrationPage(this.id)">
												<p class="font_color_black">
													部门会签结果
													</br>
													接收反馈
												</p>
												<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">立</span>
											</a>
										</div>
									</c:when>
									<c:otherwise>
										<div class="cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_red">
											<div class="divright"></div>
											<a href="javaScript:void(0)" id="NOD_0000000162" class="removeHand" onclick="openDemonstrationPage(this.id)">
												<p class="font_color_black">
													部门会签结果
													</br>
													接收反馈
												</p>
												<span class="img_style img_style1 font_color_red border_yellow border_radius_circle">立</span>
											</a>
										</div>
									</c:otherwise>
								</c:choose>
							</div>
						</div>


                        <div class="row1 seven_row">
                            <div class="row_child">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow1" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow2" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow3" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow4" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow5" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow6" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow7" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow8" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow1.png" class="arrow9" alt="">
                                <i class="arrow_line line1"></i>
                                <i class="arrow_line line2"></i>
                                <i class="arrow_line line3"></i>
                                <div class="cell row_items row_item1 bcg_gray border_width border_style border_radius border_color_yellow">
                                    <div class="divright"></div>
                                    <a href="javaScript:void(0)" id="NOD_0000000104__TODO" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                        <p class="font_color_black">审核会前征询</br>意见发起</p>
                                        <span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>
                                    </a>
                                </div>
                                <div class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_yellow">
                                    <div class="divright"></div>
                                    <a href="javaScript:void(0)" id="NOD_0000000104__DOING" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                         <p class="font_color_black">审核会前征询</br>意见反馈汇总</p>
                                        <span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>
                                    </a>
                                </div>
                                <div nodeHref="" class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_t">
                                    <div class="divright"></div>
                                    <a href="javaScript:void(0)" id="NOD_0000000170__TODO" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                     <p class="font_color_black">审核会议发起</p>
                                        <span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>
                                    </a>
                                </div>
                                <div class="cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_t">
                                    <div class="divright"></div>
                                    <a href="javaScript:void(0)" id="NOD_0000000170__FEEDBACK" class="removeHand" onclick="openDemonstrationPage(this.id)">

                                        <p class="font_color_black">审核会议汇总</p>
                                        <span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
                                    </a>
                                </div>
                                <div class="cell row_items row_item5 bcg_gray border_width border_style border_radius border_color_t">
                                   <div class="divright"></div>
                                   <a href="javaScript:void(0)" id="NOD_0000000170__INPUT" class="removeHand" onclick="openDemonstrationPage(this.id)">

                                        <p class="font_color_black">审核会议安排</p>
                                        <span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
                                    </a>
                                </div>
                                <div class="cell row_items row_item6 bcg_gray border_width border_style border_radius border_color_yellow">
                                  <div class="divright"></div>
                                   <a href="javaScript:void(0)" id="NOD_0000000170__AFFIRM" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                        <p class="font_color_black">审核会议通知</br>发送</p>
                                        <span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
                                    </a>
                                </div>
                                <div class="cell row_items row_item7 bcg_gray border_width border_style border_radius border_color_t">
                                    <div class="divright"></div>
                                    <a href="javaScript:void(0)" id="NOD_0000000170__AFFIRM" class="removeHand" onclick="openDemonstrationPage(this.id)">

                                        <p class="font_color_black">审核会议意见整理</p>
                                        <span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
                                    </a>
                                </div>
                                <div class="cell row_items row_item8 bcg_gray border_width border_style border_radius border_color_t">
                                   <div class="divright"></div>
                                    <a href="javaScript:void(0)" id="NOD_0000000170__DONE" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                         <p class="font_color_black">审核会议意见发送</p>
                                        <span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
                                    </a>
                                </div>
                                <div class="cell row_items row_item9 bcg_gray border_width border_style border_radius border_color_t">
                                   <div class="divright"></div>
                                   <a href="javaScript:void(0)" id="NOD_0000000109__TODO" class="removeHand" onclick="openDemonstrationPage(this.id)">

                                        <p class="font_color_black">审核会议意见<br>采纳情况说明</p>
                                        <span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="row1 eight_row">
                            <div class="row_child">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow1" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow2" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow3" alt="">
                                <div class="cell row_items row_item1 bcg_gray border_width border_style border_radius border_color_t">
                                    <a href="#">
                                        <p class="font_color_black">法规规章草案</br>报审材料准备</p>
                                        <span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>
                                    </a>
                                </div>
                                <div class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_yellow">
                                    <a href="#">
                                        <p class="font_color_black">法规规章草案</br>报审审批</p>
                                        <span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
                                    </a>
                                </div>
                                <div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_red">
                                    <a href="#">
                                        <p class="font_color_black">法规规章草案</br>报审（签报）</p>
                                        <span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="row1 nine_row">
                            <div class="row_child">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow1" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow2" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow3" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow4" alt="">
                                <div class="cell row_items row_item1 bcg_gray border_width border_style border_radius border_color_red">
                                    <a href="#">
                                        <p class="font_color_black">常务会议议题</br>发起</p>
                                        <span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
                                    </a>
                                </div>
                                <div class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t">
                                    <a href="#">
                                        <p class="font_color_black">常务会议议题</br>议题材料准备</p>
                                        <span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>
                                    </a>
                                </div>
                                <div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_t">
                                    <a href="#">
                                        <p class="font_color_black">议题材料报送</p>
                                        <span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
                                    </a>
                                </div>
                                <div class="cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_t">
                                    <a href="#">
                                        <p class="font_color_black">常务会审议</br>意见</p>
                                        <span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="row1 ten_row">
                            <div class="row_child">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow1" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow2" alt="">
                                <div class="cell row_items row_item1 bcg_gray border_width border_style border_radius border_color_t">
                                    <a href="#">
                                        <p class="font_color_black">法规规章草案报</br>市长审签材料</p>
                                        <span class="img_style img_style2 font_color_blue border_blue border_radius_circle">立</span>
                                    </a>
                                </div>
                                <div class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_red">
                                    <a href="#">
                                        <p class="font_color_black">法规规章草案</br>报市长审签</p>
                                        <span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="row1 eleven_row">
                            <div class="row_child">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow1" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow2" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow3" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow4" alt="">
                                <div nodeHref=""  class="cell row_items row_item1 bcg_gray border_width border_style border_radius border_color_t">
                                 <div class="divright"></div>
                                 <a href="javaScript:void(0)" id="NOD_0000000114" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                        <p class="font_color_black">校对/复核</p>
                                        <span class="img_style img_style2 font_color_blue border_blue border_radius_circle two_work">立<b>/调</b></span>
                                    </a>
                                </div>
                                <div class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t">
                                    <div class="divright"></div>
                                     <a href="javaScript:void(0)" id="NOD_0000000115__TODO" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                        <p class="font_color_black">送印</p>
                                        <span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
                                    </a>
                                </div>
                                <div class="cell row_items row_item3 bcg_gray border_width border_style border_radius border_color_t">
                                  <div class="divright"></div>
                                    <a href="javaScript:void(0)" id="NOD_0000000115__PRINT" class="removeHand" onclick="openDemonstrationPage(this.id)">

                                        <p class="font_color_black">正式文本</p>
                                        <span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
                                    </a>
                                </div>
                                <div class="cell row_items row_item4 bcg_gray border_width border_style border_radius border_color_t">
                            <div class="divright"></div>
                            <a href="javaScript:void(0)" id="NOD_0000000115__ONLINE" class="removeHand" onclick="openDemonstrationPage(this.id)">
                                        <p class="font_color_black">确定上网</p>
                                        <span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                        <div class="row1 twelve_row">
                            <div class="row_child">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow1" alt="">
                                <img src="${basePath}/legislation/assets/demo/arrow.png" class="arrow arrow2" alt="">
                                <div class="cell row_items row_item1 bcg_gray border_width border_style border_radius border_color_t width_p">
                                    <div class="divright"></div>
                                    <a href="javaScript:void(0)" id="NOD_0000000116__TODO" class="removeHand" onclick="openDemonstrationPage(this.id)">

                                        <p class="font_color_black width_p">规章文本向司法部和</br>市人大委员会报备</p>
                                        <span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
                                    </a>
                                </div>
                                <div class="cell row_items row_item2 bcg_gray border_width border_style border_radius border_color_t">
                                    <div class="divright"></div>
                                     <a href="javaScript:void(0)" id="NOD_0000000116__DOING" class="removeHand" onclick="openDemonstrationPage(this.id)">

                                        <p class="font_color_black">备案审查意见</br>处理</p>
                                        <span class="img_style img_style2 font_color_red border_yellow border_radius_circle">调</span>
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
<script>

	function openDemonstrationPage(buttonId) {
		$("#processIndexForm").modal({
			remote : "${basePath}/legislationProcessDoc/draft_doc_info.do?method=sampleList&stNodeId="+buttonId
		});
	};
    function openDemonstrationChildPage(method,stExampleId,stNodeId) {
        $("#processIndexChildForm").modal({
            remote : "${basePath}/legislationProcessDoc/draft_doc_info.do?method="+method+"&stExampleId="+stExampleId+"&stNodeId="+stNodeId
        });
    };
	function closeProcessIndex() {
		$('#processIndexRootForm').modal('hide');
	}
	
	
	//页面加载后，去后台获取所有节点的状态，连接等信息
	$('#processIndexRootForm').on('shown.bs.modal', function(event) {	
		<!--Ajax请求-->
		$.post("../legislationProcessDoc/draft_doc_info.do?method=openDocAttachNum_ajax", function(data) {
			if (data.success) {
				//alert(JSON.stringify(data));
				$.each(data.nodeInfoArray, function(index, item) {
						$('#' + item.node).parent().addClass("bcg_green");
						$('#' + item.node).parent().children(":first").html(item.num);

				});
			}
		});

	});

</script>
<!--js区 end-->
</body>
</html>