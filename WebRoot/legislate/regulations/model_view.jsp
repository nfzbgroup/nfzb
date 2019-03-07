<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!doctype html>
<html lang = "zh">
<head>
    <meta http-equiv = "X-UA-Compatible" content = "IE=8">
    <meta http-equiv = "Content-Type" content = "text/html; charset=utf-8"/>
    <meta name = "keywords" content = "OA系统"/>
    <meta name = "keywords" content = ""/>
    <meta name = "description" content = ""/>
    <meta name = "generator" content = "Notepad++"/>
    <title>详细办理页面</title>
    
    <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath }/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${pageContext.request.contextPath }/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath }/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${pageContext.request.contextPath }/legislate/js/jquery.js"></script>
    <script src = "${pageContext.request.contextPath }/legislate/js/sui.js"></script>
    <script src = "${pageContext.request.contextPath }/legislate/js/tanchuang.js"></script>
</head>
<body class = "body_bg_1">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->
    <div class="conteng-title">
        <span>当前位置：</span><em>范本管理-查看范本</em>
    </div>

    <!--内容区-->
    <div class="clearfix">
    <div class="container-fluid container-top">
               <div class="row rh">
                       <label class="formlabel">范本类型：</label>
                       <span class="formdiv">
                           <s:select list="#{'1':'规章草案范本','2':'起草说明','3':'专家意见','4':'报请函'}" name="model.modelType"  cssClass="input-xlarge" disabled="true"></s:select>
                       </span>
               </div>

                <div class="row rh">
                    <label class="formlabel">该范本应用环节：</label>
                        <span class="formdiv">
                            <s:textfield name="model.activityType" disabled="true"></s:textfield>
                        </span>
                </div>
                
           		<div class="row rh">
							<label class="formlabel">使用说明：</label>
							<span class="formdiv" style="width:81%">
								<s:textarea  style="font-size: medium;font-family: 微软雅黑, 黑体, 宋体"  name="model.instructions"  cssClass="text-area"  disabled="true"></s:textarea>
							</span>
				</div>
            	
            <ul class = "file-list">
                <li style = "font-size: 15px;font-weight: bold;">范本文件：</li>
            	<li><a id="modelfileName" href = "${basePath}/legislate/modelFileLoad.do?modelId=${model.modelId }"><s:property value="model.modelFile.fileName"/></a> &nbsp;&nbsp;&nbsp;&nbsp;  </li>
            </ul>
        </div>


            <div class="rows">
                
                <a href="${basePath}/legislate/listModel.do"
						class="sui-btn btn-primary01 btn-lg btn-margin">返回</a> 
            </div>

    </div>

</div>

</body>
</html>