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
    <title>公开社会意见-内容添加页</title>
    <link rel = "stylesheet" type = "text/css" href = "css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "css/sui-themes-green-append.css" media = "screen"/>
    <script src = "js/jquery.js"></script>
    <script src = "js/sui.js"></script>
    <script src = "js/tanchuang.js"></script>
</head>
<body class = "body_bg_1">
<!--面包屑-->
<div class = "conteng">
    <div class = "conteng-title">
        <span>当前位置：</span><em>公开社会意见-内容编辑页</em>
    </div>
    <div class = "detai" style = "margin-bottom: 40px">
        <div class = "detai-con">
            <!--内容区-->
            <form class = "form-horizontal" enctype="multipart/form-data" action="${basePath }/legislate/opinion_detail_save.do" method="post" name="form1">
            <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                        <label class="formlabel">意见来源:</label>
                    	<span class="formdiv">
                            <select name = "source" id = "source">
                                <option value = "电子邮件" <s:if test='source == "电子邮件"'>selected="selected"</s:if>>电子邮件</option>
                                <option value = "网上民意" <s:if test='source == "网上民意"'>selected="selected"</s:if>>网上民意</option>
                                <option value = "信函" <s:if test='source == "信函"'>selected="selected"</s:if>>信函</option>
                                <option value = "电话" <s:if test='source == "电话"'>selected="selected"</s:if>>电话</option>
                            </select>
                        </span>
                    </div>
                    <div class="row rh">
                        <label class="formlabel">提议人:</label>
                        <span class="formdiv">
                                <input type = "text" name="name" id="name" value="${name }">
                            </span>
                    </div>
                    <div class="row rh">
                        <label class="formlabel">电话:</label>
                        <span class="formdiv">
                                <input type = "text" name="phone" id="phone" value="${phone }">
                            </span>
                    </div>
                    <div class="row rh">
                        <label class="formlabel">意见内容:</label>
                        <span class="formdiv arh" style="width:81%">
                                <textarea  style="font-size: medium;font-family: 微软雅黑, 黑体, 宋体;"  name = "content" id = "content">${content }</textarea>
                            </span>
                    </div>
                    <div class="row rh">
                        <label class="formlabel" >相关材料上传:</label>
                        <span class="formdiv" style="width: 81%">
                           <div id="fl">
                                <a href="javascript:void (0)" class="file sui-btn btn-primary01 btn-lg btn-120" style="line-height: 30px;">
                            点击上传材料
                            <input type="file" id="filevalue" name="uploadFile">
                          </a>
                            <p class="file-text">
                            未选择文件
                           </p>
                           </div>
                            </span>
 
                    </div>
                </div>
            </div>
 
                <div class="clearfix">
                    <div class="container-fluid container-top">
                        <div class="row rh">
                            <div class="col-sm-12 text-center">
                                <button type="button" class="btn btn-info btn-bg btn-120" onclick="save();">提交</button>
                                <button type="button" class="btn btn-info btn-bg btn-120" onclick="parentLocationReload();">关闭</button>
                            </div>
                        </span>
                        </div>
                    </div>
                </div>
                 <s:hidden name="op"/>
                 <s:hidden name="draftId"/>
    			 <input type="hidden" name="detailId" value="${opinionDetail.detailId }"/>
    			 <s:hidden name="updateop"></s:hidden>
    			  <s:hidden name="outId"/>
            </form>
    </div>
</div>
</div>
 
 
<script> 
var uuu;

function parentLocationReload(){
	parent.callBack();
}
function save(){
	var name=$("#name").val();
	var phone=$("#phone").val();
	var content=$("#content").val();
	
	var reg=/^\d{11}$/ 
	if(name==""||name==null){
		alert("请填写提议人");
		$("#name").focus();
		return;
	}
	if(phone==""||phone==null){
		alert("请填写电话");
		$("#phone").focus();
		return;
	}
	if(phone.match(reg)==null){
		alert("请填写正确的手机号码");
		$("#phone").focus();
		return;
	}
	if(content==null||content==""){
		alert("请填写意见内容");
		$("#content").focus();
		return;
	}
	document.form1.submit();
}
    $(function () {
    	uuu= window.location.href;
        $('#togs').change(function () {
            var val = $(this).val();
            if(val=='0'){
                $('#tp').show().siblings('#fl').hide()
            }else{
                $('#tp').hide().siblings('#fl').show()
            }
        });
        $('input[type="file"]').change(function (e) {
            var filename = $(this).val().split("\\").pop();
            $('.file-text').text(filename)
        });
    });
 
</script>
</div>
</body>
</html>
