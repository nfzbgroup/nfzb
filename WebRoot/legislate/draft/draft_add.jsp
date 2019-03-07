<%@page import="com.wonders.fzb.legislate.beans.Model"%>
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
    <title>规章草案发起</title>
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${basePath}/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${basePath}/legislate/js/jquery.js"></script>
    <script src = "${basePath}/legislate/js/sui.js"></script>
    <script src = "${basePath}/legislate/js/tanchuang.js"></script>
     <script src = "js/layer/layer.js"></script>
</head>
<body class = "body_bg_1">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->
 
    <div class = "conteng-title">
        <span>当前位置：</span><em>规章草案发起-首页</em>
    </div>
 	<form method="post" enctype="multipart/form-data" action="${basePath}/legislate/saveDraft.do" name="draftForm">
    <!--内容区-->
    <div class = "detai">
        <div class = "detai-con">
            <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                        <label class="formlabel">法规规章草案:</label>
                        <span class="formdiv">
                           <s:textfield name="draftName" id="draftName"/>
                        </span>
                    </div>
                    <div class="row rh">
                        <label class="formlabel">备注:</label>
                        <span class="formdiv arh">
                               <s:textarea style="height:50px;font-size: medium;font-family: 微软雅黑, 黑体, 宋体" name="instructions" id="instructions"></s:textarea>
                            </span>
                    </div>
                    <div class="legislate-btnGroup">
                    	<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="addDraft();">保存</button>
                    	<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="shangbao('${draft.draftId}');">报送</button>
                    	<button type="button" class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin" onclick="location.href='${basePath}/legislate/draft_init_list.do?state=init';">返回</button>
                    </div>
                </div>
            </div>
                

            <br>
            <br>
            
             <%if(request.getParameter("draftId") != null){ %>
       
          	 	<table class = " table-height">
                                <thead>
                                <tr>
                                    <th width="40%">文件类型</th>
                                    <th width="40%">文件名称</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <caption style="font-weight:bold;color:#000">草案材料</caption>
                <tbody>
                <s:iterator value="modellist" var="model">
	               	<% 
	               		Model model = (Model)request.getAttribute("model");
	               		if(!"需要报送的其他材料".equals(model.getModelType())){
	               	%>
	                <tr>
	                    <td>
	                        <a href = "${basePath}/legislate/modelFileLoad.do?modelId=<s:property value="modelId"/>"><s:property value="modelType"/>
	                        
	                        <font style = "color: #B9404D;font-size: 12px;">
		            		<s:if test="instructions == '' || instructions == null ">
		            		</s:if>
		            		<s:else>
		            			<br>使用说明 : <s:property value="instructions"/>
		            		</s:else>
		            		</font>
	                    </td>
	                	 <td>	 <s:set name="fcount" value="1"></s:set>
	                	 		
	                     	     <s:iterator value="draftFiles" var="draftFile">
			                		<s:if test="modelType == bizType">
			                			<s:property value="fileName"/>
			                			<s:set name="fcount" value="#fcount+1"></s:set>
			                		</s:if>
				           		 </s:iterator>
				           		 <s:if test="#fcount == 1">
		                    		暂未上传
		                    	</s:if>
	                    </td>
	                    <td>
	                    	<s:if test="#fcount == 1">
	                    		<input type="button"  class="chakan caozuo-btn btn-70" value="点击上传" onclick="uploadFile('${modelType}');">
	                    	</s:if>
	                    	<s:else>
	                    		<s:iterator value="draftFiles" var="draftFile">
				                	<s:if test="modelType == bizType">
		                    			<a  href = "${basePath}/legislate/fileRecordLoad.do?fileRecordId=<s:property value="fileRecordId" />">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
		                    			<a onclick="deleteFile('<s:property value="fileRecordId" />')" href = "javascript:void (0)"><font style = "color: #B9404D;font-size: 12px;">删除</font></a>
		                    		</s:if>
				           		 </s:iterator>
				           		 <s:iterator value="otherFiles" var="draftFile">
				                	<s:if test="modelType == bizType">
		                    			<a  href = "${basePath}/legislate/fileRecordLoad.do?fileRecordId=<s:property value="fileRecordId" />">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
		                    			<a onclick="deleteFile('<s:property value="fileRecordId" />')" href = "javascript:void (0)"><font style = "color: #B9404D;font-size: 12px;">删除</font></a>
		                    		</s:if>
				           		 </s:iterator>
	                    	</s:else>
	                    </td>
	                </tr>
	                <%} %>
                </s:iterator>
                </tbody>
            </table>
            
            <table class=" table-height">
				<tbody>
					<font color="red">
						<b>格式要求</b><br>
			           1、上边距：4cm，下边距：3.5cm，左、右边距：各3.4cm，行距：最小值28。<br>
			           2、标题：小二号宋体加粗，居中。<br>
			           3、主送单位：小三号仿宋。<br>
			           4、正文：一级标题小三号黑体，二级标题小三号楷体加粗，三级标题小三号仿宋加粗，其他内容均为小三号仿宋。
			       </font>
				</tbody>
			</table>
            
            <table class = " table-height">
                                <thead>
                                <tr>
                                    <th width="40%">文件类型</th>
                                    <th width="40%">文件名称</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <caption style="font-weight:bold;color:#000">其他材料 <input type="button"  class="chakan caozuo-btn btn-70" value="点击上传" onclick="uploadFile('需要报送的其他材料');"></caption>
                <tbody>
                <s:iterator value="modellist" var="model">
                	<% 
	               		Model model = (Model)request.getAttribute("model");
	               		if("需要报送的其他材料".equals(model.getModelType(
	               				))){
	               	%>
	               	<s:iterator value="otherFiles" var="draftFile">
                <tr>
                    <td>
                        <a href = "${basePath}/legislate/modelFileLoad.do?modelId=<s:property value="modelId"/>"><s:property value="modelType"/>
                        
                        <font style = "color: #B9404D;font-size: 12px;">
	            		<s:if test="instructions == '' || instructions == null ">
	            		</s:if>
	            		<s:else>
	            			<br>使用说明 : <s:property value="instructions"/>
	            		</s:else>
	            		</font>
                    </td>
                	 <td>	 
		                			<s:property value="fileName"/>
                    </td>
                    <td>
	                   			<a  href = "${basePath}/legislate/fileRecordLoad.do?fileRecordId=<s:property value="fileRecordId" />">下载</a><a onclick="deleteFile('<s:property value="fileRecordId" />')" href = "javascript:void (0)">&nbsp;&nbsp;&nbsp;&nbsp;<font style = "color: #B9404D;font-size: 12px;">删除</font></a>
                    </td>
                </tr>
                </s:iterator>
                 <%} %>
                </s:iterator>
                </tbody>
            </table>
            <%} %>
        </div>
    </div>
    <s:hidden name="op"/>
     <s:hidden name="draftId"/>
	</form>
</div>
</div>
 <input type="hidden" name="checkDuplicate" id="checkDuplicate">
<script> 

	function callBack(){
		 	window.location.href = "${basePath}/legislate/saveDraft.do?op=&draftId=${draftId}";
	}

	function checkshangbao(DraftId){
    	$.ajax({
  	 		async:false,
    		"type":"POST",
  	 		"dataType":"json",
  	 		"url":"${basePath}/legislate/draft_check.do",
  	 		"data":{"draftId":DraftId},
  	 		"success":function(data){
  	 			$("#checkDuplicate").val(data.duplicate);
  	 		}
  	 	}) 
    }
    
	function shangbao(DraftId){
		if(confirm("确定要上报吗？")){
			checkshangbao(DraftId);
	 		if($("#checkDuplicate").val() == 1){
	 			var messageStr = "上报名称系统中已存在！请填写说明理由后方可上报！";
	 			var defaultStr = "";
	 			var content = window.prompt(messageStr, defaultStr);
	 			if(content!=null&&content!=""){
		  			$.ajax({
		  	  	 		"type":"POST",
		  	  	 		"dataType":"json",
		  	  	 		"url":"${basePath}/legislate/draft_send.do",
		  	  	 		"data":{"draftId":DraftId,"content":content},
		  	  	 		"success":function(data){
		  	  	 			if(data.code==200){
		  	  	 				alert(data.message);
			      	 			if(data.message=="报送成功"){
				 					window.location.href=('${basePath}/legislate/gzcaqclb.do?state=init');
				 				}else{
				 					window.location.href=('${basePath}/legislate/saveDraft.do?draftId='+DraftId);
				 				}
		  	  	 			}
		  	  	 		}
		  	  	 	}) 
	 			}else{
	 				alert("请填写说明理由后方可上报！");
	 			}
	 		}else{
	 			$.ajax({
	 	     		"type":"POST",
	 	     		"dataType":"json",
	 	     		"url":"${basePath}/legislate/draft_send.do",
	 	     		"data":{"draftId":DraftId},
	 	     		"success":function(data){
	 	     			if(data.code==200){
	 	     				alert(data.message);
		 	  	 			if(data.message=="报送成功"){
		     					window.location.href=('${basePath}/legislate/gzcaqclb.do?state=init');
		     				}else{
		     					window.location.href=('${basePath}/legislate/saveDraft.do?draftId='+DraftId);
		     				}
	 	     			}
	 	     		}
	 	     	}) 
	 		}
		}
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

	function addDraft(){
		if($("#draftName").val()==""||$("#draftName").val()==null){
			alert("请填写法规规章草案名称");
			$("#draftName").focus();
    		return;
		}
		<%if(request.getParameter("draftId") != null){ %>
		document.draftForm.op.value ="update";
		<%} %>
		document.draftForm.action = '${basePath}/legislate/saveDraft.do';
		document.draftForm.submit();
	}
	
	function uploadDraftFile(){
		if($("#draftName").val()==""||$("#draftName").val()==null){
			alert("请填写法规规章草案名称");
			$("#draftName").focus();
    		return;
		}
		document.draftForm.op.value = 'update';
		document.draftForm.action = '${basePath}/legislate/uploadDraftFile.do';
		document.draftForm.submit();
	}
    $(function () {
    	uuu= window.location.href;
//        var linum=$('.file-list li').size()*10
//        console.log(linum)
//        $('.detai').height($(window).height()+linum)
        $('input[type="file"]').change(function (e) {
            var filename = $(this).val().split("\\").pop();
            $('.file-text').text(filename)
        })
    })
    function deleteFile(fileRecordId){
		 if(confirm("确定要删除吗？")){
   	 	$.ajax({
   	 		"type":"POST",
   	 		"dataType":"json",
   	 		"url":"${basePath}/legislate/deleteFile.do",
   	 		"data":{"fileRecordId":fileRecordId},
   	 		"success":function(data){
   	 			if(data.code==200){
   	 				alert(data.message);
   	 				location.href = '${basePath}/legislate/saveDraft.do?draftId=${draftId}';
   	 			}
   	 		}
   	 	}) 
       }
	}
</script>
</body>
</html>