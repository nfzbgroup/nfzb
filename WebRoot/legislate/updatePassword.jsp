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
    <title>密码修改</title>
    
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${basePath}/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${basePath}/legislate/js/jquery.js"></script>
    <script src = "${basePath}/legislate/js/sui.js"></script>
    <script src = "${basePath}/legislate/js/tanchuang.js"></script>
</head>
<body class = "body_bg_1">
<form action="${basePath}/legislate/updatePassword.do?id=${currentPerson.userId}" method="post" name="loginForm">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->

    <div class = "conteng-title">
        <span>当前位置：</span><em>密码修改</em>
    </div>

    <!--内容区-->
    <div class = "detai" style="margin-top:0">
        <div class = "detai-con">
            <div class="clearfix">
                <div class="container-fluid">
                    <div class="row rh">
                        <label class="formlabel">姓名:</label>
                        <span class="formdiv">
                            ${currentPerson.name }
                        </span>
                    </div>
                    <div class="row rh">
                        <label class="formlabel"> 原密码:</label>
                        <span class="formdiv">
                             <input type="password" name="oldPassword" id="oldPassword" value="">
                        </span>
                    </div>
                    
                    <div class="row rh">
                        <label class="formlabel">新密码:</label>
                        <span class="formdiv">
                             <input type="password" name="newPassword" id="newPassword" value="">
                        </span>
                    </div>
                    
                    <div class="row rh">
                        <label class="formlabel">确认密码:</label>
                        <span class="formdiv">
                             <input type="password" name="newPassword2" id="newPassword2" value="">
                        </span>
                    </div>
                    
					<div class="rows">
						<button type="button" onclick="updatepassword();" class="sui-btn btn-primary01 btn-lg" id="tijiao">提交</button>
					</div>
                </div>
            </div>
        </div>
    </div>
</div>
</form>

<script>
    
    function updatepassword(){
    	if($("#oldPassword").val()==""){
    		alert("请输入原密码！");
    		return;
    	}
    	if($("#newPassword").val()==""){
    		alert("请输入新密码！");
    		return;
    	}
    	if($("#newPassword").val() !=$("#newPassword2").val()){
    		alert("两次输入的密码不一致！");
    		return;
    	}
    	document.loginForm.submit();
    }
    
    var message = '${message}';
	if(message != ''){
		alert(message);
	}
    
</script>
</body>
</html>