<%@page import="com.wonders.fzb.base.beans.Page"%>
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
    <title>单位意见征求</title>
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${basePath}/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${basePath}/legislate/js/jquery.js"></script>
    <script src = "${basePath}/legislate/js/sui.js"></script>
    <script src = "${basePath}/legislate/js/tanchuang.js"></script>
     <script src = "js/layer/layer.js"></script>
</head>
<body class = "body_bg_1">
<!--面包屑-->
<div class = "conteng">
    <div class = "conteng-title">
        <span>当前位置：</span><em>查看历史记录</em>
    </div>
    <div class = "detai" style = "margin-bottom: 40px">
        <div class = "detai-con">
            <!--内容区-->
  <%--           <form class = "form-horizontal" role = "form">
            <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                            <label class="formlabel">单位名称:</label>
                            <span class="formdiv">
                                <input type = peext">
                            </span>
                        <span class="formbuttom">
                            <button type="button" class="btn btn-info btn-bg btn-120">查询</button>
                        </span>
                    </div>
                </div>
            </div>

            </form> --%>
<div class = "detai">
        <div class = "detai-con">
        <div class = "biaodan">
            <div class = "tab-content" style = "display: block">
                <table class = "table1">
                     <thead>
                     
                        <tr>
                           
                            <th>上传时间</th>
                            <th>文件名称</th>
                            <th>环节</th>
                        </tr>
                        </thead>
                        <tbody>
                         <s:iterator value="FileRecordlist" id="task"  status="st">
                        <tr>
                            <td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                            <td><a href="${basePath}/legislate/fileRecordLoad.do?fileRecordId=<s:property value="fileRecordId" />"><s:property value="fileName"/></a></td>
                            <td><s:property value="activityType"/></td>
                         
                        </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                        <div class="col-sm-12 text-center">
                         <a href = "${uuu }" class = "sui-btn btn-primary01 btn-lg btn-170 layer-close">返回</a>
                        </div>
                    </div>
                </div>
            </div><br/>
            </div>
        </div>
    </div>
</div>


<script>

$(function () {
//  var linum=$('.file-list li').size()*10
//  console.log(linum)
//  $('.detai').height($(window).height()+linum)
  $('input[type="file"]').change(function (e) {
      var filename = $(this).val().split("\\").pop();
      $('.file-text').text(filename)
  })
  
  $("#close").click(function(){
  	parent.document.form1.submit();
  });
})
    function fankui(teamOpinionDetailId){
    	window.location.href="${basePath}/legislate/teamOpinion_fk_save.do?st=jsqk&op=fk&teamOpinionDetailId="+teamOpinionDetailId+"&teamOpinionId=${teamOpinionId}"
	}
function uploadFile(bizType){
		layer.open({
	        type: 2,
	        title: '',
	        shade: false,
	        maxmin: true,
	        shadeClose: true, //点击遮罩关闭层
	        area: ['640px', '480px'],
	        content: '${basePath}/legislate/draft/upload_file.jsp?activityType=起草&draftId=${draftId}&bizType='+bizType,
	    });
	}
</script>
</div>
</body>
</html>