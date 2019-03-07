<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!doctype html>
<html lang = "zh">
<head>
    <meta http-equiv = "X-UA-Compatible" content = "IE=8">
    <meta http-equiv = "Content-Type" content = "text/html; charset=utf-8"/>
    <meta name = "keywords" content = "上海市政府立法平台"/>
    <meta name = "keywords" content = ""/>
    <meta name = "description" content = ""/>
    <meta name = "generator" content = "Notepad++"/>
    <title>上海市政府立法平台-登录</title>
    
    <script src = "js/jquery.js"></script>
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/main.css" media = "screen"/>
    <style>
    .register_page_com{
    	position:absolute;
    	bottom:30px;
    }
    </style>
</head>

<body>
	<script>
	var message = '${errorMessage}';
	if(message != ''){
		alert(message);
		parent.callBack();

	}
	
	function jump(){
		var event = arguments.callee.caller.arguments[0] || window.event;
		if(event.keyCode == 13){//判断是否按了回车，enter的keycode代码是13，想看其他代码请猛戳这里。
			document.loginform.submit();
		}
	}
	</script>
	<div class = "bg">
		<form action="${basePath }/legislate/login.do" method="post" name="loginform">
		    <div class = "title1">
		        <div class = "title"></div>
		    </div>
		    <div class = "register_cen">
		        <div class = "cen">
		            <div class = "register_cen_login">
		                <p>用户登录</>
		                <div class = "register_cen_login_div">
		                    <span>
			                    <img src = "${basePath}/legislate/images/user.png">
			                    <img src = "${basePath}/legislate/images/zhaungshi.png" style = "margin-left:10px;">
		                    </span>
		                    <input type = "text" name="username" placeholder = "请输入帐号"/>
		                </div>
		                <div class = "register_cen_login_div">
		                    <span>
		                    	<img src = "${basePath}/legislate/images/password.png">
		                    	<img src = "${basePath}/legislate/images/zhaungshi.png" style = "margin-left:10px;">
		                    </span>
		                    <input type = "password" name="password" placeholder = "请输入密码" onkeydown="jump();"/>
		                </div>
		                <div class = "register_cen_login_diva">
		                    <div class = "register_cen_login_divar" onclick="document.loginform.submit();">登 录</div>
		                </div>
		            </div>
		        </div>
		    </div>
		    
	    </form>
	    <div class = "register_page_pa">
		        <div class = "register_page_com"><a>版权所有@上海市政府立法平台 Copyright ©2017</a></div>
		    </div>
	</div>
	<script type="text/javascript">
		$(function(){
		var pageH=$(window).height()
		var pageW=$(window).width()
			$('.bg').width(pageW).height(pageH)
			$('body').width(pageW).height(pageH)
		})
	</script>
</body>
</html>
