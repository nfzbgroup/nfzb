<!DOCTYPE html>
<html lang="zh-cn">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<head>
    <meta charset="utf-8">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="renderer" content="webkit">
    <title>上海市法制办统一平台门户</title>
    <link rel="stylesheet" href="css/main.css">
</head>
<body>
<header class="header">
    <div class="wrap">
        <div class="header-text">
            <img src="img/bg-header-text.png" alt="">
        </div>
        <ul class="userTools">
            <li><span><s:property value="#request.userInfo.name"/> 您好！</span></li>
            <li><a href=""><img src="img/icon-header-quit.png" alt=""></a></li>
            <!-- <li><a href=""><img src="img/icon-header-home.png" alt="login.jsp"></a></li> -->
        </ul>
    </div>
</header>
<div class="mainBox" style="text-align:center">
    <div class="wrap">
        <ul class="customAccordion">
            <s:if test="#request.strUserOp.indexOf('MODULE_REVIEW')!=-1">
	            <li class="con-img-1">
	                <div class="con-img-small">
	                    <p>复议应诉</p>
	                </div>
	                <a href="<s:property value='#request.moduleUrlMap["MODULE_REVIEW"]'/>" ></a>
	                <div class="customAccordion-title">
	                    <a class="title-img" href="<s:property value='#request.moduleUrlMap["MODULE_REVIEW"]'/>" ><img src="img/con-title-1.png" alt=""></a>
	                </div>
	                <div class="customAccordion-btn">
	                    <a class="btn-enter" href="<s:property value='#request.moduleUrlMap["MODULE_REVIEW"]'/>" >进入系统<span>>>></span></a>
	                </div>
	            </li>
            </s:if>
            <s:if test="#request.strUserOp.indexOf('MODULE_NORMALDOC')!=-1">
	            <li class="con-img-2">
	                <div class="con-img-small">
	                    <p>规范性文件</p>
	                </div>
	                <a href="<s:property value='#request.moduleUrlMap["MODULE_NORMALDOC"]'/>" ></a>
	                <div class="customAccordion-title">
	                    <a class="title-img" href="<s:property value='#request.moduleUrlMap["MODULE_NORMALDOC"]'/>" ><img src="img/con-title-2.png" alt=""></a>
	                </div>
	                <div class="customAccordion-btn">
	                    <a class="btn-enter" href="<s:property value='#request.moduleUrlMap["MODULE_NORMALDOC"]'/>" >进入系统<span>>>></span></a>
	                </div>
	            </li>
            </s:if>
            <s:if test="#request.strUserOp.indexOf('MODULE_REVIEW')!=-1">
	            <li class="con-img-3">
	                <div class="con-img-small">
	                    <p>行政执法</p>
	                </div>
	                <a href="<s:property value='#request.moduleUrlMap["MODULE_REVIEW"]'/>" ></a>
	                <div class="customAccordion-title">
	                    <a class="title-img" href="<s:property value='#request.moduleUrlMap["MODULE_REVIEW"]'/>" ><img src="img/con-title-3.png" alt=""></a>
	                </div>
	                <div class="customAccordion-btn">
	                    <a class="btn-enter" href="<s:property value='#request.moduleUrlMap["MODULE_REVIEW"]'/>" >进入系统<span>>>></span></a>
	                </div>
	            </li>
            </s:if>
            <s:if test="#request.strUserOp.indexOf('MODULE_LEGISLATE')!=-1">
	            <li class="con-img-4">
	                <div class="con-img-small">
	                    <p>立法信息</p>
	                </div>
	                <a href="<s:property value='#request.moduleUrlMap["MODULE_LEGISLATE"]'/>" ></a>
	                <div class="customAccordion-title">
	                    <a class="title-img" href="<s:property value='#request.moduleUrlMap["MODULE_LEGISLATE"]'/>" ><img src="img/con-title-4.png" alt=""></a>
	                </div>
	                <div class="customAccordion-btn">
	                    <a class="btn-enter" href="<s:property value='#request.moduleUrlMap["MODULE_LEGISLATE"]'/>" >进入系统<span>>>></span></a>
	                </div>
	            </li>
            </s:if>
            <s:if test="#request.strUserOp.indexOf('MODULE_BAHIVOR')!=-1">
	            <li class="con-img-5 active">
	                <div class="con-img-small">
	                    <p>行政行为</p>
	                </div>
	                <a href="<s:property value='#request.moduleUrlMap["MODULE_BAHIVOR"]'/>" ></a>
	                <div class="customAccordion-title">
	                    <a class="title-img" href="<s:property value='#request.moduleUrlMap["MODULE_BAHIVOR"]'/>" ><img src="img/con-title-5.png" alt=""></a>
	                </div>
	                <div class="customAccordion-btn">
	                    <a class="btn-enter" href="<s:property value='#request.moduleUrlMap["MODULE_BAHIVOR"]'/>" >进入系统<span>>>></span></a>
	                </div>
	            </li>
            </s:if>
            <li class="con-img-6">
                <div class="con-img-small">
                    <p>会议通知</p>
                </div>
                <a href="" onClick="javascript:alert('系统目前正在开发中！')"></a>
                <div class="customAccordion-title">
                    <a class="title-img" href="" onClick="javascript:alert('系统目前正在开发中！')" ><img src="img/con-title-6.png" alt=""></a>
                </div>
                <div class="customAccordion-btn">
                    <a class="btn-enter" href="" onClick="javascript:alert('系统目前正在开发中！')" >进入系统<span>>>></span></a>
                </div>
            </li>
            <li class="con-img-7">
                <div class="con-img-small">
                    <p>通讯录</p>
                </div>
                <a href="" onClick="javascript:alert('系统目前正在开发中！')"> </a>
                <div class="customAccordion-title">
                    <a class="title-img" href="" onClick="javascript:alert('系统目前正在开发中！')"><img src="img/con-title-7.png" alt=""></a>
                </div>
                <div class="customAccordion-btn">
                    <a class="btn-enter" href="" onClick="javascript:alert('系统目前正在开发中！')">进入系统<span>>>></span></a>
                </div>
            </li>
        </ul>
    </div>
</div>

<footer class="footer">
    <div class="wrap footer-con">
        <div class="foot-bottom">
            <p>
                <span>上海市法制办统一平台门户 @ 2017</span>
            </p>
        </div>
    </div>
</footer>

<script src="js/jquery-1.12.0.min.js"></script>
<script src="js/customAccordion.js"></script>
<script>
    $(function () {
        var $accordion=$('.customAccordion');
        var $width=$(window).width();
        var custom={
            pWidth:Math.min($width-120,1600),
            maxWidth:850,
            minWidth:function(){
                return Math.floor((this.pWidth-this.maxWidth)/($accordion.children().length-1))
            },
            _marginTop:80
        };
        if($accordion.children().length>4){
            custom.maxWidth=650;
        }
        if($width>=1600){
            custom.maxWidth=1000;
            custom._marginTop=100;
        }

        $accordion.css({"margin":custom._marginTop+"px "+($(window).width()-custom.pWidth)/2+"px"+" 75px"});
        $accordion.children('li').css('width',custom.minWidth());
        $accordion.find('.con-img-small img').css('width',custom.minWidth());
        $accordion.children('li').eq(0).css('width',custom.maxWidth);
        // customAccordion 自定义手风琴效果
        var opt = {
            "speed": "slow",	//变换速度,三速度可选 slow,normal,fast;
            "by": "mouseover",	//触发事件,click或者mouseover;
            "auto": false,	//是否自动播放;
            "maxWidth":custom.maxWidth,
            "minWidth":custom.minWidth,
            "index":2//default second pic
        };
        $accordion.IMGDEMO(opt);
        $accordion.find("li").eq(opt.index-1).trigger(opt.by);
    })
</script>
</body>
</html>