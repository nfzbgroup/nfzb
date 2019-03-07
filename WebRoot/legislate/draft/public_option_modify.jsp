<%@page import="com.wonders.fzb.legislate.LegislateConst"%>
<%@page import="java.util.Map"%>
<%@page import="com.wonders.fzb.legislate.beans.Model"%>
<%@page import="com.wonders.fzb.legislate.beans.FileRecord"%>
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
    <script src = "${basePath}/legislate/js/layer/layer.js"></script>
    <script src = "${basePath}/resources/components/My97DatePicker/WdatePicker.js"></script>
</head>
<body class = "body_bg_1">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->
 
    <div class = "conteng-title">
        <span>当前位置：</span><em>公开社会意见</em>
    </div>
 	<form method="post" enctype="multipart/form-data" action="${basePath}/legislate/modifyPublicOption.do" name="draftForm">
    <!--内容区-->
    <div class = "detai">
        <div class = "detai-con">
            <div class="clearfix">
                <div class="container-fluid container-top">
                   		 <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label">
                                    <span style = "padding: 0 6px;" class = "spanwidth">规章草案起草名称:</span>
                                </label>
                                <div class = "controls" style = "vertical-align: middle">
                                   <s:property value="draft.draftName"/>
                                </div>
                            </div>
                        </div>
                        <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label">
                                    <span style = "padding: 0 6px;" class = "spanwidth">经办处意见：</span>
                                </label>
                                <div class = "controls" style = "vertical-align: middle">
                                   ${instructionsMap["经办处意见"] }
                                </div>
                            </div>
                        </div>
                        <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label">
                                    <span style = "padding: 0 6px;" class = "spanwidth">综合处意见：</span>
                                </label>
                                <div class = "controls" style = "vertical-align: middle">
                                     <textarea  style="font-size: medium;font-family: 微软雅黑, 黑体, 宋体"  name="zhcInstructions" id="zhcInstructions" cols="50" rows="5">${instructionsMap["综合处意见"] }</textarea>
                                </div>
                            </div>
                        </div>
                        <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label">
                                    <span style = "padding: 0 6px;" class = "spanwidth">办领导意见：</span>
                                </label>
                                <div class = "controls" style = "vertical-align: middle">
                                     <textarea  style="font-size: medium;font-family: 微软雅黑, 黑体, 宋体"   name="bldInstructions" id="bldInstructions" cols="50" rows="5">${instructionsMap["办领导意见"] }</textarea>
                                </div>
                            </div>
                        </div>
                        
                        
<%--                     <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label">
                                    <span style = "padding: 0 6px;" class = "spanwidth">征求意见开始时间：</span>
                                </label>
                                <div class = "controls" style = "vertical-align: middle">
                                    <s:date name="publicOpinion.startTime" format="yyyy-MM-dd"/>
                                </div>
                            </div>
                        </div>
                        <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label">
                                    <span style = "padding: 0 6px;" class = "spanwidth">征求意见结束时间：</span>
                                </label>
                                <div class = "controls" style = "vertical-align: middle">
                                    <s:date name="publicOpinion.endTime" format="yyyy-MM-dd"/>
                                </div>
                            </div>
                        </div> --%>
                        
                        <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label">
                                    <span style = "padding: 0 6px;" class = "spanwidth">征求意见开始时间：</span>
                                </label>
                                <div class = "controls" style = "vertical-align: middle">
                                    <input name="startTime" id="startTime"  format="yyyy-MM-dd" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name="publicOpinion.startTime" format="yyyy-MM-dd" />"/>
                                </div>
                            </div>
                        </div>
                        <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label">
                                    <span style = "padding: 0 6px;" class = "spanwidth">征求意见结束时间：</span>
                                </label>
                                <div class = "controls" style = "vertical-align: middle">
                                    <input name="endTime" id="endTime"  format="yyyy-MM-dd" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name="publicOpinion.endTime" format="yyyy-MM-dd" />"/>
                                </div>
                            </div>
                        </div>
                        
                      <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label"  style="vertical-align:top;">
                                    <span style = "padding: 0 6px;" class = "spanwidth">征求意见汇总情况：</span>
                                </label>
                                <div class = "controls" style = "vertical-align: middle;width: 100%" >
                                <table>
                                	<tr>
                                		<p style="line-height:60px;"><td width="60px;">收到电子邮件<input title="收到电子邮件" class="countInput"  type="text" style="width: 53px;" name="emailCount" id="emailCount" value="${publicOpinion.emailCount }"/>件，提出意见和建议<input type="text" style="width: 25px;" id="emailOpinionCount" name="emailOpinionCount" value="${publicOpinion.emailOpinionCount }"/>条；</td>
                                		</p>
                                	</tr>
                                	<tr>
                                		<p style="line-height:60px;"><td>网上民意征询平台<input title="收到电子邮件" class="countInput" type="text" style="width: 25px;" name="webCount" id="webCount" value="${publicOpinion.webCount }"/>件，提出意见和建议<input type="text" style="width: 25px;" name="webOpinionCount" id="webOpinionCount" value="${publicOpinion.webOpinionCount }"/>条；</td>
                                		</p>
                                	</tr>
                                	<tr>
                                	<p style="line-height:60px;">	<td>收到来信来函<input type="text" style="width: 53px;" name="letterCount" id="letterCount" value="${publicOpinion.letterCount }"/>件，提出意见和建议<input type="text" style="width: 25px;" name="letterOpinionCount" id="letterOpinionCount" value="${publicOpinion.letterOpinionCount }"/>条；</td>
                                	
                                	</p></tr>
                                	<tr>
                                	<p style="line-height:60px;">	<td>接到电话<input type="text" style="width: 80px;" name="callCount" id="callCount" value="${publicOpinion.callCount }"/>个，收到意见和建议<input type="text" style="width: 25px;" name="callOpinionCount" id="callOpinionCount" value="${publicOpinion.callOpinionCount }"/>条；</td>
                                	</p>
                                	</tr>
                                	<tr style="font-weight: bold;">
                                		<td>共收到具体意见<input type="text" style="width: 37px;" name="opinionCount" id="opinionCount" value="${publicOpinion.opinionCount }"/>件。</td>
                                	</tr>
                                </table>
                        </div>
                    </div>
                    
             <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                        <div class="col-sm-12 text-center">
                        <button type="button" class="btn btn-info btn-bg btn-120" onclick="detaillist('<s:property value="draft.draftId" />');">意见详情列表</button>
                            <button type="button" class="btn btn-info btn-bg btn-120" onclick="addDraft();">提交</button>
                            <button type="button" class="btn btn-info btn-bg btn-120" onclick="location.href='${basePath}/legislate/public_opinion_list.do';">返回</button>
                        </div>
                    </span>
                    </div>
                </div>
            </div>
       
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
					               		//LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION 
					               		if(LegislateConst.ACTIVITY_TYPE_Draft_PUBLIC_OPINION.equals(model.getActivityType())){
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
				                        <%-- <a href = "${basePath}/legislate/modelFileLoad.do?modelId=<s:property value="modelId"/>"> --%><s:property value="modelType"/>
				                        
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
                </div>
            </div>
           
        </div>
    </div>
     <s:hidden name="opinionId"/>
       <input type="hidden" id="activityType" value="0" name="saveDraft">
       <input type="hidden" id="activityType" value="update" name="op">
	</form>
</div>
</div>
 
<script> 
function lsjl(draftId,bizType){
	var uuu = window.location.href;
		window.location.href("${basePath}/legislate/lsjlck.do?draftId="+draftId+"&bizType="+bizType+"&uuu="+uuu);
}
function callBack(){
 	window.location.href("${basePath}/legislate/modifyPublicOption.do?opinionId=${opinionId}");
}
function uploadFile(bizType){
	layer.open({
        type: 2,
        title: '',
        shade: false,
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area: ['640px', '480px'],
        content: '${basePath}/legislate/draft/upload_file.jsp?activityType=公开意见&draftId=${draftId}&bizType='+bizType,
    });
}
	function addDraft(){
		
/* 		var inputs = $('.countInput');
		for(countInput in inputs){
		
			if(countInput.value ==""){
				alert(countInput.title + "不能为空！");
				return;
			}
		} */
		
		
 		var emailCount=$("input[name=emailCount]").val();
		var emailOpinionCount=$("input[name=emailOpinionCount]").val();
		var webCount=$("input[name=webCount]").val();
		var webOpinionCount=$("input[name=webOpinionCount]").val();
		var letterCount=$("input[name=letterCount]").val();
		var letterOpinionCount=$("input[name=letterOpinionCount]").val();
		var callCount=$("input[name=callCount]").val();
		var callOpinionCount=$("input[name=callOpinionCount]").val();
		var opinionCount=$("input[name=opinionCount]").val();
		
		
		var date=/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
		var startTime=$("#startTime").val();
		var endTime=$("#endTime").val();
		if(startTime==""||startTime==null){
			alert("请填写好时间");
			$("#startTime").focus();
			return;
		}
		if(startTime.match(date)==null){
			alert("请填写正确的日期格式");
			$("#startTime").focus();
			return;
		}
		if(endTime==""||startTime==null){
			alert("请填写好时间");
			$("#startTime").focus();
			return;
		}
		if(endTime.match(date)==null){
			alert("请填写正确的日期格式");
			$("#startTime").focus();
			return;
		}
		if($("#zhcInstructions").val()==""||$("#zhcInstructions").val()==null){
			alert("请填写综合处意见");
			$("#zhcInstructions").focus();
			return;
		}
		if($("#bldInstructions").val()==""||$("#bldInstructions").val()==null){
			alert("请填写办领导意见");
			$("#bldInstructions").focus();
			return;
		}
		
		if(emailCount==""||emailCount==null){
			alert("收到电子邮件封数不能为空");
			$("#emailCount").focus();
			return;
		}
		if(emailOpinionCount==""||emailOpinionCount==null){
			alert("提出意见和建议条数不能为空");
			$("#emailOpinionCount").focus();
			return;
		}
		if(webCount==""||webCount==null){
			alert("网上民意不能为空");
			$("#webCount").focus();
			return;
		}
		if(webOpinionCount==""||webOpinionCount==null){
			alert("提出意见和建议条数不能为空");
			$("#webOpinionCount").focus();
			return;
		}
		if(letterCount==""||letterCount==null){
			alert("收到来信不能为空");
			$("#letterCount").focus();
			return;
		}
		if(letterOpinionCount==""||letterOpinionCount==null){
			alert("提出意见和建议条数不能为空");
			$("#letterOpinionCount").focus();
			return;
		}
		if(callCount==""||callCount==null){
			alert("接到电话个数不能为空");
			$("#emailCount").focus();
			return;
		}
		if(callOpinionCount==""||callOpinionCount==null){
			alert("提出意见和建议条数不能为空");
			$("#callOpinionCount").focus();
			return;
		}
		if(opinionCount==""||opinionCount==null){
			alert("共收到具体意见不能为空");
			$("#opinionCount").focus();
			return;
		}
		/* if($.trim($("#file").val())=="未选择文件 "||$.trim($("#file").val())==null||$.trim($("#file").val())==""){
			alert("请选择文件");
			return;
		} */
		document.draftForm.submit(); 
	}
	
    $(function () {
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
   	 				location.href = '${basePath}/legislate/savePublicOption.do?draftId=${draftId}';
   	 			}
   	 		}
   	 	}) 
       }
	}
    function detaillist(draftId){
        layer.open({
            type: 2, 
            title: '',
            shade: false,
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: ['850px', '620px'],
            content: '${basePath}/legislate/opinion_detail_list.do?draftId='+draftId,
        });
   }
</script>
</body>
</html>