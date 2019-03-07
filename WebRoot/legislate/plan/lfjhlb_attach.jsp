<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>  
<!doctype html>
<html lang = "zh">
<head>
    <meta http-equiv = "X-UA-Compatible" content = "IE=edge,Chrome=1"/>
    <meta http-equiv = "Content-Type" content = "text/html; charset=utf-8"/>
    <meta name = "keywords" content = "OA系统"/>
    <meta name = "keywords" content = ""/>
    <meta name = "description" content = ""/>
    <meta name = "generator" content = "Notepad++"/>
    <title>规章草案起草列表</title>
    
    <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath }/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${pageContext.request.contextPath }/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath }/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${pageContext.request.contextPath }/legislate/js/jquery.js"></script>
    <script src = "${pageContext.request.contextPath }/legislate/js/sui.js"></script>
    <script src = "${pageContext.request.contextPath }/legislate/js/tanchuang.js"></script>
    <script src = "${basePath }/legislate/js/layer/layer.js"></script>
    <script src = "${basePath}/resources/components/My97DatePicker/WdatePicker.js"></script>
</head>
<body class = "body_bg_1">
<form action="${basePath}/legislate/planAttach.do" enctype="multipart/form-data" method="post" name="modelForm">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->
    <div class = "conteng-title">
        <span>当前位置：</span><em>立法项目列表-首页</em>
    </div>
    <br><center><font size="4" style="font-size:28px;font-family:宋体;"><b>上传材料</b></font></center><br>
    <!--内容区-->
    <div class = "detai">
    	<div class = "tab-content" style = "display: block">
	        <table class = "table1">
	        <thead>
	        <tr>
	            <th style="text-align:center" nowrap="nowrap">材料名称</th>
	            <th style="text-align:center" nowrap="nowrap">下载</th>
	            <th style="text-align:center" nowrap="nowrap">删除</th>
	        </tr>
	        </thead>
	        <tbody>
	        <s:set name="rownum" value="(pageNo-1)*pageSize+1"></s:set>
	        <s:iterator value="pageModel.result" id="tasks">
		        <tr>
			        <td style="text-align:center"><s:property value="fileName"/></td>
			        <td style="text-align:center"><a  href = "${basePath}/legislate/fileRecordLoad.do?fileRecordId=<s:property value="fileRecordId"/>"><img src="images/down.png" title="下载" style="cursor:pointer;"></a></td>
			        <td style="text-align:center"><img src="images/iconDustbin.png" onclick="del('<s:property value="fileRecordId"/>')" title="删除" style="cursor:pointer;"></td>
			    </tr>
	        <s:set name="rownum" value="#rownum+1"></s:set>
            </s:iterator>
	        </tbody>
	        </table>
        </div>
    </div>
    <br>
    <div class = "detai-con">
	    <div class="clearfix">
	        <div class="container-fluid">
	            <div class="row rh">
	                <label class="formlabel">上传文件:</label>
	                <span class="formdiv">
	                    <s:file name="uploadFile" id="file"></s:file>
	                </span>
	            </div>
	            <div class="row rh">
	                <label class="formlabel">备注说明:</label>
	                <span class="formdiv arh">
                         <s:textarea  style="font-size: 14px;font-family: 微软雅黑, 黑体, 宋体"  name="remarks" id="remarks" value=""></s:textarea>
                    </span>
	            </div>
	            <br>
	            <div class="rows">
					<button type="button"
					class="sui-btn btn-primary01 btn-lg"
					onclick="add();" id="tijiao">上传</button>
					<button  type="button" onclick="window.location.href='${basePath}/legislate/plan_init_list.do'"
					class="sui-btn btn-primary01 btn-lg">返回</button>
				</div>
	        </div>
	    </div>
    </div>
</div>
<s:hidden name="op" value="%{op}"/>
<s:hidden name="fileRecordId"/>
<s:hidden name="planId" value="%{plan.planId}"/>
</form>
<script>
function add(){
	if($("#file").val()==""||$("#file").val()==null){
		alert("请选择要上传的文件。");
		$("#tijiao").focus();
		return;
	}
	document.modelForm.op.value="upload";
	document.modelForm.submit(); 
}
function del(fileRecordId){
	if(confirm("您确定要删除吗？")==1){
		document.modelForm.op.value="del";
		document.modelForm.fileRecordId.value=fileRecordId;
		document.modelForm.submit(); 
	}
}
</script>
</body>
</html>