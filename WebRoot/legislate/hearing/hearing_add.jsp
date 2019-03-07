<%@page import="com.wonders.fzb.legislate.LegislateConst"%>
<%@page import="com.wonders.fzb.legislate.beans.Model"%>
<%@page import="com.wonders.fzb.legislate.beans.FileRecord"%>
<%@page import="java.util.Map"%>

<%@page import="org.apache.commons.lang3.StringUtils"%>
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
    <title>审核会议发起</title>
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${basePath}/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${basePath}/legislate/js/jquery.js"></script>
    <script src = "${basePath}/legislate/js/sui.js"></script>
    <script src = "${basePath}/legislate/js/tanchuang.js"></script>
    <script src = "${basePath}/resources/components/My97DatePicker/WdatePicker.js"></script>
    <script src = "js/layer/layer.js"></script>
</head>
<body class = "body_bg_1">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->
 
    <div class = "conteng-title">
        <span>当前位置：</span><em>立法听证会-内容添加页</em>
    </div>
 	<form method="post" enctype="multipart/form-data" action="${basePath}/legislate/saveHearing.do" name="draftForm">
    <!--内容区-->
            <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                            <label class="formlabel" style="vertical-align:top;">听证会议题:</label>
                            <span class="formdiv"> 
                            	<s:textarea  name="hearingTitle" id="hearingTitle" value="%{hearing.hearingTitle}" style="width:100%;font-size: medium;font-family: 微软雅黑, 黑体, 宋体"></s:textarea>
                            </span>
                    </div>
                      <div class="row rh" style="margin-top: 30px;">
                        <label class="formlabel">对应草案:</label>
                        <span class="formdiv">
                                <s:select name = "draftId" id = "draftId"  style="width:100%" list="#request.draftList" value="#request.selectDraftId" listValue="draftName" listKey="draftId" />
                            </span>
                    </div>
                    <s:if test='hearingStatus == "release"'>
	                    <div class="row rh">
	                            <label class="formlabel">听证会地点:</label>
	                            <span class="formdiv">
	                                <s:textfield name="hearingPlace" id="hearingPlace" value="%{hearing.hearingPlace}" />
	                            </span>
	                    </div>
	                    <div class="row rh">
	                        <label class="formlabel">听证会时间:</label>
	                        <span class="formdiv">
	                                <input type = "date" value="2017-03-24" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" name="hearingTime" id="hearingTime">
	                            </span>
	                    </div>
                    </s:if>
         			
         		<div class="clearfix">
                    <div class="container-fluid container-top">
                        <div class="row rh">
                            <div class="col-sm-12 text-center">
                                <button type="button" onclick="add();" class="btn btn-info btn-bg btn-120">提交</button>
                                <button type="button" class="btn btn-info btn-bg btn-120" onclick="window.location.href='${basePath}/legislate/hearing_list.do'">返回</button>
                            </div>
                        </span>
                        </div>
                    </div>
                </div>
         			
				 <%if(request.getParameter("hearingId") != null){ %>
       
			          	 <table class = " table-height">
			                                <thead>
			                                <tr>
			                                    <th width="40%">文件类型</th>
			                                    <th width="40%">文件名称</th>
			                                    <th>操作</th>
			                                </tr>
			                                </thead>
			                                <caption style="font-weight:bold;color:#000">本环节材料</caption>
				                <tbody>
				                <s:iterator value="modellist" var="model">
					               	<% 
					               	
					               		Model model = (Model)request.getAttribute("model");
					               		//LegislateConst.ACTIVITY_TYPE_Draft_HEARING 
					               		if(LegislateConst.ACTIVITY_TYPE_Draft_HEARING.equals(model.getActivityType())){
					               	%>
					                <tr>
					                    <td>
					                    	<s:if test="modelId == '' || modelId == null ">
					                    		<s:property value="modelType"/>
						            		</s:if>
						            		<s:else>
						            			<a href = "${basePath}/legislate/modelFileLoad.do?modelId=<s:property value="modelId"/>"><s:property value="modelType"/></a>
						            		</s:else>
					                        
					                        
					                        <font style = "color: #B9404D;font-size: 12px;">
						            		<s:if test="instructions == '' || instructions == null ">
						            		</s:if>
						            		<s:else>
						            			<br/>使用说明 : <s:property value="instructions"/>
						            		</s:else>
						            		</font>
					                    </td>
					                	 <td>	
												<s:set name="fcount" value="1"></s:set>
					                	 		
					                     	     <s:iterator value="currFiles" var="currFile">
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
					                    		<input type="button" class="chakan caozuo-btn btn-70"  value="点击上传" onclick="uploadFile('${modelType}');">
					                    	</s:if>
					                    	<s:else>
					                    		<s:iterator value="currFiles" var="currFile">
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
			            	
			            	<table class = " table-height">
			                                <thead>
			                                <tr>
			                                    <th width="40%">文件类型</th>
			                                    <th width="40%">文件名称</th>
			                                    <th>操作</th>
			                                </tr>
			                                </thead>
			                                <caption style="font-weight:bold;color:#000">草案相关材料</caption>
				                <tbody>
				                <s:iterator value="modellist" var="model">
					               	<% 
					               		Model model = (Model)request.getAttribute("model");
					               		//LegislateConst.ACTIVITY_TYPE_Draft_AuditMeeting  审核会议
					               		if("起草".equals(model.getActivityType()) && !"需要报送的其他材料".equals(model.getModelType())){
					               	%>
					                <tr>
					                    <td>
					                    	<s:if test="modelId == '' || modelId == null ">
					                    		<s:property value="modelType"/>
						            		</s:if>
						            		<s:else>
						            			<a href = "${basePath}/legislate/modelFileLoad.do?modelId=<s:property value="modelId"/>"><s:property value="modelType"/>
						            		</s:else>
					                        
					                        
					                        <font style = "color: #B9404D;font-size: 12px;">
						            		<s:if test="instructions == '' || instructions == null ">
						            		</s:if>
						            		<s:else>
						            			<br>使用说明 : <s:property value="instructions"/>
						            		</s:else>
						            		</font>
					                    </td>
					                	 <td>	
														<s:set name="fcount" value="1"></s:set>
					                	 		
					                     	     <s:iterator value="currFiles" var="currFile">
							                		<s:if test="modelType == bizType">
							                			<s:property value="fileName"/>
							                			<s:set name="fcount" value="#fcount+1"></s:set>
							                		</s:if>
								           		 </s:iterator>
								           		 <s:if test="#fcount == 1">
						                    		<s:set name="cfcount" value="1"></s:set>
									           		 <s:iterator value="draftFiles" var="draftFile">
								                		<s:if test="modelType == bizType">
								                			<s:property value="fileName"/>
								                			<s:set name="cfcount" value="#cfcount+1"></s:set>
								                		</s:if>
									           		 </s:iterator>
									           		 <s:if test="#cfcount == 1">
						                    			暂未上传
						                    		  </s:if>
						                    	</s:if>
					                    </td>
					                    <td width="150px;">
							                 <s:if test="#fcount == 1">
							                 
									           		 <s:iterator value="draftFiles" var="draftFile">
								                		<s:if test="modelType == bizType">
								                			<a  href = "${basePath}/legislate/fileRecordLoad.do?fileRecordId=<s:property value="fileRecordId" />">下载</a>
						                		
								                		</s:if>
									           		 </s:iterator>
					                    		<input type="button" class="chakan caozuo-btn btn-70"  value="点击上传" onclick="uploadFile('${modelType}');">
					                    		<input type="button" class="chakan caozuo-btn btn-70" value="历史记录"  onclick="lsjl('<s:property value="draftId"/>','<s:property value="modelType"/>');">
					                    	</s:if>
					                    	<s:else>
					                    		<s:iterator value="currFiles" var="currFile">
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
			            	
            	          <table class = " table-height">
                                <thead>
                                <tr>
                                    <th width="40%">文件类型</th>
                                    <th width="40%">文件名称</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <caption style="font-weight:bold;color:#000">其他材料 <input type="button" class="chakan caozuo-btn btn-70"  value="点击上传" onclick="uploadFile('需要报送的其他材料');"></caption>
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
				                       <%--  <a href = "${basePath}/legislate/modelFileLoad.do?modelId=<s:property value="modelId"/>" --%>
				                        <s:property value="modelType"/>
				                        
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
						           		 
					                   			<a  href = "${basePath}/legislate/fileRecordLoad.do?fileRecordId=<s:property value="fileRecordId" />">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;
					                   			<a onclick="deleteFile('<s:property value="fileRecordId" />')" href = "javascript:void (0)"><font style = "color: #B9404D;font-size: 12px;">删除</font></a>
						           		 
				                    	
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

                <div id="menuContent" class="menuContent" style="display:none; position: absolute; background-color: #fff;height: 400px;border:1px solid black; overflow-x:hidden; overflow-y:auto; ">
					<ul id="classTree" class="ztree" style="margin-top:0; height: 350px;width:100%;"></ul>
					</div>
    <s:hidden name="op" id="op"/>
     <s:hidden name="hearingId"/>
     <input type="hidden" value="${hearingStatus }" id="hearingStatus">
     			                            <input type="hidden" id="activityType" value="立法听证会" name="activityType">
			                            <input type="hidden" id="activityType" value="0" name="saveDraft">   
	</form>
</div>
</div>
 <jsp:include page="/common/scripts.jsp" />

<script language="javascript">
	$.include([ 'tree', 'treeEX', 'datepicker' ]);
</script>
<script> 
function lsjl(draftId,bizType){
	var uuu = window.location.href;
		window.location.href("${basePath}/legislate/lsjlck.do?draftId="+draftId+"&bizType="+bizType+"&uuu="+uuu);
}
function callBack(){
	window.location.href("/fzb_platform/legislate/saveHearing.do?hearingId=${hearingId}");
}
function uploadFile(bizType){
	layer.open({
        type: 2,
        title: '',
        shade: false,
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area: ['640px', '480px'],
        content: '${basePath}/legislate/draft/upload_file.jsp?activityType=立法听证会&draftId=${draftId}&bizType='+bizType,
    });
}
$(function () {
//  var linum=$('.file-list li').size()*10
//  console.log(linum)
//  $('.detai').height($(window).height()+linum)
  $('input[type="file"]').change(function (e) {
      var filename = $(this).val().split("\\").pop();
      $('.file-text').text(filename)
  })
})

function add(){
	var hearingTitle=$("#hearingTitle").val();
	var hearingPlace=$("#hearingPlace").val();
	var hearingTime=$("#hearingTime").val();
	//判断添加页面 未发布和已发布的编辑页面传递值
	var op=$("#op").val();
	var date=/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
	if(op=="save"){
		if(hearingTitle==null||hearingTitle==""){
			alert("请填写听证会议题");
			$("#hearingTitle").focus();
			return;
		}
	}else{
		    var hearingStatus=$("#hearingStatus").val();
			if(hearingStatus=="release"){
				if(hearingPlace==null||hearingPlace==""){
					alert("请填写听证会地点");
					$("#hearingPlace").focus();
					return;
				}
				if(hearingTime==null||hearingTime==""){
					alert("请填写听证会时间");
					$("#hearingTime").focus();
					return;
				}
				if(hearingTime.match(date)==null){
					alert("请输入正确的日期格式");
					$("#hearingTime").focus();
					return;
				}
			}
	}
	document.draftForm.submit();
}

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
   	 				location.href = '${basePath}/legislate/saveHearing.do?hearingId=${hearingId}';
   	 			}
   	 		}
   	 	}) 
       }
	}
</script>
</body>
</html>