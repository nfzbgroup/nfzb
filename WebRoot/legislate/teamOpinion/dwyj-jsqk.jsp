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
        <span>当前位置：</span><em>单位意见征求-接收情况</em>
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
                           
                            <th>单位名称</th>
                            <th>响应情况</th>
                            <th>响应时间</th>
                            <th>反馈内容</th>
                        </tr>
                        </thead>
                        <tbody>
                         <s:iterator value="details" id="task"  status="st">
                        <tr>
                            <td><s:property value="teamName"/></td>
                            <td>
                            <s:if test="opinionStatus == 'send'">未接收</s:if>
                            <s:if test="opinionStatus == 'feedback'">已反馈</s:if>
                            <s:if test="opinionStatus == 'receive'">已接收</s:if>
                            </td>
                            <td><s:date name="receiveTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                            <td>
                           <%--  <s:if test="opinionInfo == null">
			        <span class="formdiv" style="width: 50%">
			            <input type = "text" style="width: 77%;"id="tp"  class="hidden" value="http://">
			            
			            <!--<select name = "" id="togs">
			                 <option value = "1">非音视频</option>
			                <option value = "0">音视频</option>
			            </select>-->
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
		        		</s:if>
    	        		<s:else>
    	        			 <a  href = "${basePath}/legislate/teamOpinionDetailFileRecordLoad.do?teamOpinionDetailId=<s:property value="teamOpinionDetailId"/>"><s:property value="opinionInfo"/></a>
    	        		</s:else> --%>
    	        		<s:if test="opinionInfo == null">
    	        			<input type="button"  class="chakan caozuo-btn btn-70" value="点击上传" onclick="fankui('<s:property value="teamOpinionDetailId"/>')">
    	        		</s:if>
    	        		<s:else>
    	        			<a  href = "${basePath}/legislate/teamOpinionDetailFileRecordLoad.do?teamOpinionDetailId=<s:property value="teamOpinionDetailId"/>"><s:property value="opinionInfo"/></a>
    	        		</s:else>
                            </td>
                        </tr>
                        </s:iterator>
                        </tbody>
                    </table>
                <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                        <div class="col-sm-12 text-center">
                         <a href = "#" class = "sui-btn btn-primary01 btn-lg btn-170 layer-close" id="close">关闭</a>
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