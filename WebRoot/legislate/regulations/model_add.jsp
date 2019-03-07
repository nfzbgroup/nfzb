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
        
    <script type="text/javascript">
    	
    	var modelType = ${modelType};
    	
    	function changeModelType(s){
    		var modelTypes = modelType[s.value];
    		var modelTypeSelect = document.modelForm.modelType ;
    		modelTypeSelect.options.length = 0;
    		for(var i =0;i<modelTypes.length;i++){
    			modelTypeSelect.options[i] = new Option(modelTypes[i],modelTypes[i]);
    		}
    	}
    
    </script>
</head>
<%
String unique1 = (String)request.getAttribute("unique1");
%>
<body class = "body_bg_1">
<%@include file="/legislate/draft/common.jsp" %>
<form method="post" enctype="multipart/form-data"
		action="${basePath}/legislate/saveModel.do" name="modelForm">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->
    <div class="conteng-title">
        <span>当前位置：</span><em>范本管理-添加范本</em>
    </div>

    <!--内容区-->
    <div class="clearfix">
    <div class="container-fluid container-top">
         		<div class="row rh">
                      <label class="formlabel">
                          	该范本应用环节：
                      </label>
                      <span class="formdiv">
                          <s:select list="#request.activityTypeList"  onchange="changeModelType(this);"
                          id="activityType" name="activityType"  value="model.activityType" cssClass="input-xlarge"></s:select>
                      </span>
               	 </div>
                
               <div class="row rh">
                       <label class="formlabel">
                           	范本类型：
                       </label>
                       <span class="formdiv">
                           <s:select list="#request.modelTypeList" 
                           id="modelType" name="modelType"  value="model.modelType" cssClass="input-xlarge"></s:select>
                       </span>
            </div>
            <div class="row rh">
                       <label class="formlabel">
                           	是否必须上传：
                       </label>
                       <span class="formdiv">
                       		<select name="unique" cssClass="input-xlarge">
                       			<option value="0" <%if("0".equals(unique1)){ %>selected<%} %>>否</option>
                       			<option value="1" <%if("1".equals(unique1)){ %>selected<%} %>>是</option>
                       		</select>
                          <%-- <s:select list="#{'0':'否','1':'是'}" name="unique" value="model.unique" cssClass="input-xlarge"></s:select> --%>
                       </span>
            </div>

               
           		<div class="row rh">
							<label class="formlabel" style="vertical-align: top">
								使用说明：
							</label>
							<span class="formdiv" style="width:81%">
								<s:textarea name="instructions" id="instructions" value="%{model.instructions}" ></s:textarea>
							</span>
				</div>

               	<div class="row rh">
                    <label class="formlabel">范本信息：</label>
					</label>
					<span class="formdiv" style="width: 81%">
					<div id="fl">
                          <a href="javascript:void (0)" class="file sui-btn btn-primary01 btn-lg btn-120" style="line-height: 30px;">
                                                                                                   点击上传材料
                            <s:file name="uploadFile" id="file"></s:file>
                          </a>
                            <p class="file-text">
                          	  未选择文件
                           </p>
                           </div>
<%-- 					<div class="controls" style="width: 335px;">
						<s:file name="uploadFile" id="file"></s:file>
					</div> --%>
            	</div>
            	
           <%-- <%if("update".equals(request.getAttribute("op"))){%> --%>
           	<s:if test="model.modelFile.fileName!=''">
            <ul class = "file-list">
                <li style = "font-size: 15px;font-weight: bold;">范本文件：</li>
                <li><a id="modelfileName" href = "${basePath}/legislate/modelFileLoad.do?modelId=${model.modelId }"><s:property value="model.modelFile.fileName"/></a> &nbsp;&nbsp;&nbsp;&nbsp; <a href = "javascript:void (0)" onclick="deleteModelFile('${model.modelFile.fileRecordId }')"><font style = "color: #B9404D;font-size: 12px;">删除</font></a> </li>
            </ul>
            </s:if>
            <%--  <%}%> --%>
        </div>


            <div class="rows">
                
				<button class="sui-btn btn-primary01 btn-lg"
						onclick="add();">提交</button>
				<button type="button" onclick="window.location.href='${basePath}/legislate/listModel.do'"
						class="sui-btn btn-primary01 btn-lg"/>返回</button>	
            </div>

    </div>

</div>
	<s:hidden name="op" id="op"/>
	<s:hidden name="modelId" value="%{model.modelId}"/>
</form>

<script type="text/javascript">
    $(function(){
        $('input[type="file"]').change(function (e) {
            var filename = $(this).val().split("\\").pop();
            $('.file-text').text(filename)
        })
        <%if("add".equals(request.getAttribute("op"))){%>
        	changeModelType($('#activityType')[0])
        <%}%>
    })
    
    function deleteModelFile(fileRecordId){
		 if(confirm("确定要删除吗？")){
		   	 	$.ajax({
		   	 		"type":"POST",
		   	 		"dataType":"json",
		   	 		"url":"${basePath}/legislate/deleteModelFile.do",
		   	 		"data":{"fileRecordId":fileRecordId},
		   	 		"success":function(data){
		   	 			if(data.code==200){
		   	 				alert(data.message);
		   	 				location.href = '${basePath}/legislate/saveModel.do?modelId=${model.modelId }';
		   	 			}
		   	 		}
		   	 	}) 
		  }
    }
    
    function add(){
    	var activityType=$("#activityType").val();
    	var modelType=$("#modelType").val();
    	if(activityType=="请选择"){
    		alert("请选择该范本的应用环节");
    		return;
    	}
    	if(modelType=="请选择"){
    		alert("请选择范本类型");
    		return;
    	}
    	if(checkLock()){
			return false;
		}
    	var op=$("#op").val();
    	/* if(op=="add"){
			if($("#file").val()=="未选择文件 "||$("#file").val()==null||$("#file").val()==""){
				alert("请选择范本文件");
				return;
			} 
    	} */
    	document.modelForm.submit();
    }
</script>
</body>
</html>