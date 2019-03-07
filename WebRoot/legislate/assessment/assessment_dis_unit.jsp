<%@page import="com.wonders.fzb.legislate.LegislateConst"%>
<%@page import="com.wonders.fzb.legislate.beans.Model"%>
<%@page import="java.util.Calendar"%>
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
    <title>规章立法后评估项目分送</title>
    
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${basePath}/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${basePath}/legislate/js/jquery.js"></script>
    <script src = "${basePath}/legislate/js/sui.js"></script>
    <script src = "${basePath}/legislate/js/tanchuang.js"></script>
    <script src = "${basePath}/legislate/js/layer/layer.js"></script>
</head>
<body class = "body_bg_1">
	<form action="${basePath}/legislate/assessment_report_dis_unit.do" method="post" name="form1">
		<!--头部结asd束-->
		<div class = "conteng">
		    <!--面包屑-->
		    <div class = "conteng-title">
		        <span>当前位置：</span><em>评估项目分送单位选择-首页</em>
		    </div>
		    <!--内容区-->
		    <div class = "detai">
		        <div class = "detai-con" align="center">
		        	<s:iterator value="#request.mors" id="info" status="number">
       		         	<s:if test="#request.listUnit.contains(#info.teamCid)">
       		         	   <s:if test="#request.receives.contains(#info.teamCid)">
                    	   		<span class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" style="width: 150px;background-color:red;"><s:property value='#info.showName' /></span><input type="checkbox" name="unitIds" id="unitIds" disabled="disabled" value="<s:property value='#info.teamCid' />" />
                    	   </s:if>
                    	   <s:else>
                    	   		<span class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" style="width: 150px;background-color:#76828c;"><s:property value='#info.showName' /></span><input type="checkbox" name="unitIds" id="unitIds" disabled="disabled" value="<s:property value='#info.teamCid' />" />
                    	   </s:else>
                    	</s:if>
                    	<s:else>
                    		<span class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" style="width: 150px;background-color:#0f90ff;"><s:property value='#info.showName' /></span><input type="checkbox" name="unitIds" id="unitIds" value="<s:property value='#info.teamCid' />" />
                    	</s:else>
		        		<s:if test="(#number.index+1)%5==0">
		        			<br>
		        		</s:if>
		        	</s:iterator>
		        </div>
		        <div class="legislate-btnGroup">
					<input type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="confirmCheck()" value='确认' />
					<a href="${basePath}/legislate/assessment_list.do?receive=Y" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" >返回</a>
				</div>
		    </div>
		</div>
		<input type="hidden" name="assessmentId" id="assessmentId" value="<s:property value='#request.assessmentId' />">
	</form>
</body>
<script type="text/javascript">
function confirmCheck(){
	$.ajax({
		url:'${basePath}/legislate/assessment_dis.do',
		data:$(form1).serialize(),
		type:'post',
		success:function(msg){
			alert(msg);
		}
	});
}
</script>
</html>