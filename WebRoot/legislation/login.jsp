<%@ page language="java" contentType="text/html;charset=utf-8"%>
<!DOCTYPE HTML>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title >上海市立法平台-登录</title>
    <link href="${basePath}/legislation/assets/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
    <link href="${basePath}/legislation/assets/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${basePath}/legislation/assets/css/animate.min.css" rel="stylesheet">
    <link href="${basePath}/legislation/assets/css/style.min.css?v=4.0.1" rel="stylesheet">
    <link href="${basePath}/legislation/assets/css/main.css?v=4.0.1" rel="stylesheet">
    <link href="${basePath}/legislation/assets/css/plugins/toastr/toastr.min.css" rel="stylesheet">
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>
<body class="login-bg">
    <div class="middle-box text-center loginscreen animated " style="width: 480px;height: 480px;">
        <div>
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">
                        欢迎使用上海市立法平台
                    </h3>
                </div>
                <div class="panel-body">
                    <form class="m-t" role="form" action="${basePath }/legislation/login.do" method="post" id="loginForm">
                        <div class="form-group">
                            <input id="username" name="username" type="text" placeholder="用户名" class="form-control" value="">
                        </div>
                        <div class="form-group">
                            <input id="password" name="password" type="password" placeholder="密码" class="form-control" onkeydown="jump();">
                        </div>
                        <div class="form-group">
                        
                        </div>
                        <button type="submit" class="btn btn-success block full-width m-b">登 录</button>
                    </form>
                </div>
            </div>

        </div>
    </div>
    <script src="${basePath}/legislation/assets/js/jquery.min.js?v=2.1.4"></script>
    <script src="${basePath}/legislation/assets/plugin/jquery.md5.js" type="text/javascript"></script>
    <script src="${basePath}/legislation/assets/js/bootstrap.min.js?v=3.3.5"></script>
    <script src="${basePath}/legislation/assets/js/content.min.js?v=1.0.1"></script>
    <script src="${basePath}/legislation/assets/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${basePath}/legislation/assets/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${basePath}/legislation/assets/js/plugins/layer/layer.min.js"></script>
    <script src="${basePath}/legislation/assets/js/plugins/toastr/toastr.min.js"></script>
    <script>
	    var message = '${errorMessage}';
			if(message != ''){
				alert(message);
				parent.callBack();
	
		}
	    function jump(){
			var event = arguments.callee.caller.arguments[0] || window.event;
			if(event.keyCode == 13){//判断是否按了回车，enter的keycode代码是13。
				document.loginform.submit();
			}
		}
        $(function () {
            $.validator.setDefaults({
                highlight: function(e) {
                    $(e).closest(".form-group").removeClass("has-success").addClass("has-error")
                },
                success: function(e) {
                    e.closest(".form-group").removeClass("has-error")
                },
                errorElement: "span",
                errorPlacement: function(e, r) {
                    e.appendTo(r.is(":radio") || r.is(":checkbox") ? r.parent().parent().parent() : r.parent())
                },
                errorClass: "help-block m-b-none",
                validClass: "help-block m-b-none"
            });
            
        });
        toastr.options = {
        		  "closeButton": true,
        		  "debug": false,
        		  "progressBar": false,
        		  "positionClass": "toast-top-center",
        		  "onclick": null,
        		  "showDuration": "400",
        		  "hideDuration": "1000",
        		  "timeOut": "4000",
        		  "extendedTimeOut": "1000",
        		  "showEasing": "swing",
        		  "hideEasing": "linear",
        		  "showMethod": "fadeIn",
        		  "hideMethod": "fadeOut"
        		}
    </script>
</body>
</html>