<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html lang="zh-cn">
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

<div class="loginBox-container">
    <div class="wrap">
        <div class="loginText">
            <img src="img/bg-header-text.png" alt="">
        </div>
        <div class="loginBox">
            <div class="loginBox-title">欢迎使用,请登录</div>
            <form id="form1" name="form1" action="login.do" method="post" class="login-form">
                <div class="login-form-control">
                    <!-- <input type="text" name="account" id="account" value="<s:property value='#request.account'/>" placeholder="请输入用户名"> -->
                    <input type="text" name="account" id="account" value="yaoym" placeholder="请输入用户名">
                </div>
                <div class="login-form-control">
                    <!-- <input type="text" name="password" id="password" placeholder="请输入密码"> -->
                    <input type="text" name="password" id="password" value="a" placeholder="请输入密码">
                </div>
                <div class="login-form-control vCode">
                    <input type="text" id="codeId" name="codeId" placeholder="请输入验证码">
                    <img id="imgObj" src="../verifyCodeServlet" alt="" class="vCode" onclick="changeImg()">
                </div>
                <div class="login-form-btnGroup">
                    <a class="btn-login">确&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;定</a>
                </div>
                <input type="hidden" name="error" id="error" value="<s:property value='#request.error'/>">
            </form>
        </div>
    </div>
</div>

<script src="js/jquery-1.12.0.min.js"></script>

<script language="javascript">
	function changeImg(){
	    var imgSrc = $("#imgObj");   
	    var src = imgSrc.attr("src");
	    imgSrc.attr("src",chgUrl(src));   
	}   
	//时间戳   
	//为了使每次生成图片不一致，即不让浏览器读缓存，所以需要加上时间戳   
	function chgUrl(url){   
	    var timestamp = (new Date()).valueOf();   
	    url = url.substring(0,20);   
	    if((url.indexOf("&")>=0)){   
	        url = url + "×tamp=" + timestamp;   
	    }else{   
	        url = url + "?timestamp=" + timestamp;   
	    }   
	    return url;  
	} 
</script>

<script>
    $(function () {//垂直居中
    	//点击回车按钮
		$(document).keyup(function(event){
			if(event.keyCode ==13){
				$(".btn-login").trigger("click");
			}
		});
    	
        var _wh=$(window).height();
        $(".loginBox-container .wrap").css({'margin':(_wh-430)/2+"px auto"});
        
     	if($('#error').val()&&$('#account').val()){
     		alert("登录失败，请重新登录！");
     	}
   
    
	    $(".btn-login").click(function() {
			//如果  输入框是空的
			if($('#account').val()&&$('#password').val()){
				$.ajax({
					url : 'code_check.do',
					data : 'codeId=' +  $("#codeId").val(),
					type : 'post',
					success : function(msg) {
						if(msg=="T"){
							form1.submit();
						}else{
							alert("校验码错误");
						}
					}
				});
			}else{
				alert("请先填写用户名密码");
			}
		}); 
    
    });
</script>
</body>
</html>