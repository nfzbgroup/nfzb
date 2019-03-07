<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.wonders.fzb.legislate.beans.FileRecord"%>
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
        <span>当前位置：</span><em>审核会议发起-首页</em>
    </div>
 	<form method="post" enctype="multipart/form-data" action="${basePath}/legislate/saveAuditMeeting.do" name="draftForm">
    <!--内容区-->
            <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                            <label class="formlabel">会议名称:</label>
                            <span class="formdiv"> 
                                <s:textfield name="meetingTitle" id="meetingTitle" value="%{auditMeeting.meetingTitle}" />
                            </span>
                    </div>
            
                      <div class="row rh">
                        <label class="formlabel">对应草案:</label>
                        <span class="formdiv">
                                <s:select name = "draftId" id = "draftId"  list="#request.draftList" value="#request.selectDraftId" listValue="draftName" listKey="draftId" />
                        </span>
                    </div>
                     
                    <div class="row rh">
                            <label class="formlabel">会议地点:</label>
                            <span class="formdiv">
                                <s:textfield name="meetingPlace" id="meetingPlace"  value="%{auditMeeting.meetingPlace}" />
                            </span>
                    </div>
                    <div class="row rh">
                        <label class="formlabel">会议时间:</label>
                        <span class="formdiv">
                                <input type = "date" value="<s:date name="auditMeeting.meetingTime" format='yyyy-MM-dd' />" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" id="meetingTime" name="meetingTime">
                            </span>
                    </div>
         			<div class="row rh">
                        <label class="formlabel">会议人员:</label>
                        <span class="formdiv arh" style="width: 81%;">
                                <textarea  style="font-size: medium;font-family: 微软雅黑, 黑体, 宋体"  name="meetingPeople"   value="" id = "meetingPeople">${auditMeeting.meetingPeople}</textarea>
                                <input type="hidden"  id="meetingPeopleId" name="meetingPeopleId">
                                <a class = "chakan caozuo-btn btn-120" id="huoqu" onclick="huoqu()">获取名单列表</a>
                            </span>
					</div>
					
				<div class="clearfix">
                    <div class="container-fluid container-top">
                        <div class="row rh">
                            <div class="col-sm-12 text-center">
                                <button type="button" onclick="add();" class="btn btn-info btn-bg btn-120">提交</button>
                                <button type="button" class="btn btn-info btn-bg btn-120" onclick="window.location.href='${basePath}/legislate/auditMeeting_list.do'">返回</button>
                            </div>
                        </span>
                        </div>
                    </div>
                </div>
					<!-- 
						<label class="formlabel">会议人员</label>
							<div class="formdiv">
								 <div class="input-group">
								    <s:textfield name="meetingPeople" id="meetingPeople" value="%{auditMeeting.meetingPeople}"  class="form-control class-tree" />
									
									<input type="hidden"  id="meetingPeopleId" name="meetingPeopleId">
									<span class="input-group-btn">
										<button class="btn btn-default class-tree"  type="button" >选择</button>
									</span>
								</div>
							</div>
					 -->
					 <br>
            		<br>
            		
            		 <%if(request.getParameter("auditMeetingId") != null){ %>
       
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
					               		//LegislateConst.ACTIVITY_TYPE_Draft_AuditMeeting  审核会议
					               		if("审核会议".equals(model.getActivityType())){
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
					                    		<input type="button" value="点击上传" class="chakan caozuo-btn btn-70" onclick="uploadFile('${modelType}');">
					                    	</s:if>
					                    	<s:else>
					                    		<s:iterator value="currFiles" var="currFile">
								                	<s:if test="modelType == bizType">
						                    			<a  href = "${basePath}/legislate/fileRecordLoad.do?fileRecordId=<s:property value="fileRecordId" />">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="deleteFile('<s:property value="fileRecordId" />')" href = "javascript:void (0)"><font style = "color: #B9404D;font-size: 12px;">删除</font></a>
						                    		</s:if>
								           		 </s:iterator>
								           		 <s:iterator value="otherFiles" var="draftFile">
								                	<s:if test="modelType == bizType">
						                    			<a  href = "${basePath}/legislate/fileRecordLoad.do?fileRecordId=<s:property value="fileRecordId" />">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="deleteFile('<s:property value="fileRecordId" />')" href = "javascript:void (0)"><font style = "color: #B9404D;font-size: 12px;">删除</font></a>
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
					                    <td  width="150px;">
							                <s:if test="#fcount == 1">
							                
							                		<s:set name="cfcount" value="1"></s:set>
									           		 <s:iterator value="draftFiles" var="draftFile">
								                		<s:if test="modelType == bizType">
								                			<a  href = "${basePath}/legislate/fileRecordLoad.do?fileRecordId=<s:property value="fileRecordId" />">下载</a>
								                			
								                			<s:set name="cfcount" value="#cfcount+1"></s:set>
								                		</s:if>
									           		 </s:iterator>
					                    		<input type="button" class="chakan caozuo-btn btn-70" value="点击上传"  onclick="uploadFile('${modelType}');">
					                    		<input type="button" class="chakan caozuo-btn历史记录0" value="历史记录"  onclick="lsjl('<s:property value="draftId"/>','<s:property value="modelType"/>');">
					                    	</s:if>
					                    	<s:else>
					                    		<s:iterator value="currFiles" var="currFile">
								                	<s:if test="modelType == bizType">
						                    			<a  href = "${basePath}/legislate/fileRecordLoad.do?fileRecordId=<s:property value="fileRecordId" />">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="deleteFile('<s:property value="fileRecordId" />')" href = "javascript:void (0)"><font style = "color: #B9404D;font-size: 12px;">删除</font></a>
						                    		</s:if>
								           		 </s:iterator>
								           		 <s:iterator value="otherFiles" var="draftFile">
								                	<s:if test="modelType == bizType">
						                    			<a  href = "${basePath}/legislate/fileRecordLoad.do?fileRecordId=<s:property value="fileRecordId" />">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="deleteFile('<s:property value="fileRecordId" />')" href = "javascript:void (0)"><font style = "color: #B9404D;font-size: 12px;">删除</font></a>
						                    		</s:if>
								           		 </s:iterator>
					                    	</s:else>
					                    </td>
					                </tr>
					                <%} %>
				                </s:iterator>
				                </tbody>
			            	</table>
			            	
            	            <table class = " table-height" >
                                <thead>
                                <tr>
                                    <th width="40%">文件类型</th>
                                    <th width="40%">文件名称</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <caption style="font-weight:bold;color:#000">其他材料 <input type="button" value="点击上传" class="chakan caozuo-btn btn-70" onclick="uploadFile('需要报送的其他材料');"></caption>
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
				                        <%-- <a href = "${basePath}/legislate/modelFileLoad.do?modelId=<s:property value="modelId"/>"> --%>
				                        
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
						           		 
					                   			<a  href = "${basePath}/legislate/fileRecordLoad.do?fileRecordId=<s:property value="fileRecordId" />">下载</a>&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="deleteFile('<s:property value="fileRecordId" />')" href = "javascript:void (0)"><font style = "color: #B9404D;font-size: 12px;">删除</font></a>
						           		 
				                    	
				                    </td>
				                </tr>
				                </s:iterator>
				                 <%} %>
				                </s:iterator>
				                </tbody>
				            </table>

			            <%} %>
			            
                <div id="menuContent" class="menuContent" style="display:none; position: absolute; background-color: #fff;height: 400px;border:1px solid black; overflow-x:hidden; overflow-y:auto; ">
					<ul id="classTree" class="ztree" style="margin-top:0; height: 350px;width:100%;"></ul>
					</div>
    <s:hidden name="op"/>
     <s:hidden name="auditMeetingId"/>
			                            <input type="hidden" id="activityType" value="审核会议" name="activityType">
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
	window.location.href("${basePath}/legislate/saveAuditMeeting.do?auditMeetingId=${auditMeetingId}");
}
function huoqu(){
	layer.open({
        type: 2,
        title: '',
        shade: false,
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area: ['850px', '620px'],
        content: '${basePath}/legislate/meetingOrgList.do?state=auditMeeting_add',
    /*     success: function(layero, index){
            var banlibtn = layer.getChildFrame('#banlibtn', index);
            banlibtn.click(function () {
                window.location.href='http://www.baidu.com'
           })
       } */
    });
}
function uploadFile(bizType){
	layer.open({
        type: 2,
        title: '',
        shade: false,
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area: ['640px', '480px'],
        content: '${basePath}/legislate/draft/upload_file.jsp?activityType=审核会议&draftId=${draftId}&bizType='+bizType,
    });
}
function onCheck(e, treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("classTree"),
	nodes = zTree.getCheckedNodes(true),
	displayName = "";
	valueId = "";
	for (var i=0, l=nodes.length; i<l; i++) {
		displayName += nodes[i].name + ",";
		valueId += nodes[i].id + ",";
	}
	if (displayName.length > 0 ) displayName = displayName.substring(0, displayName.length-1);
	if (valueId.length > 0 ) valueId = valueId.substring(0, valueId.length-1);
	var display = $("#meetingPeople");
	var value = $("#meetingPeopleId");
	
	value.attr("value",valueId);
	display.attr("value", displayName);
}

function add(){
	var meetingTitle=$("#meetingTitle").val();
	var meetingPlace=$("#meetingPlace").val();
	var meetingTime=$("#meetingTime").val();
	var meetingPeople=$("#meetingPeople").val();
	
	var date=/^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;
	if(meetingTitle==null||meetingTitle==""){
		alert("请填写会议名称");
		$("#meetingTitle").focus(); 
		return;
	}
	if(meetingPlace==null||meetingPlace==""){
		alert("请填写会议地点");
		$("#meetingPlace").focus();
		return;
	}
	if(meetingTime==null||meetingTime==""){
		alert("请填写会议时间");
		$("#meetingTime").focus();
		return;
	}
	if(meetingTime.match(date)==null){
		alert("请输入正确的日期格式");
		$("#meetingTime").focus();
		return;
	}
	if(meetingPeople==null||meetingPeople==""){
		alert("请选择会议人员");
		return;
	}
	document.draftForm.submit();
}

function beforeClick(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("teamTree");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}

function showMenu() {
	var teamObj = $("#meetingPeople");
	var teamOffset = $("#meetingPeople").offset();
	$("#menuContent").css({left:teamOffset.left + "px", top:teamOffset.top + teamObj.outerHeight() + "px"}).slideDown("fast");
	$("#menuContent").width($("#meetingPeople").width() + 24);
	
	$("body").bind("mousedown", onBodyDown);
}
function hideMenu() {
	$("#menuContent").fadeOut("fast");
	$("body").unbind("mousedown", onBodyDown);
}
function onBodyDown(event) {
	if (!(event.target.id == "menuBtn" || event.target.id == "teamName" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
		hideMenu();
	}
}
$(function(){
	
	var setting = {
		check: {
			enable: true,
			chkboxType: {"Y":"", "N":""}
		},
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick,
			onCheck: onCheck
		}
		

	};
	$(".class-tree").click(function(){
		showMenu();
	});
});
$(function () {
//  var linum=$('.file-list li').size()*10
//  console.log(linum)
//  $('.detai').height($(window).height()+linum)
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
   	 				location.href = '${basePath}/legislate/saveAuditMeeting.do?auditMeetingId=${auditMeetingId}';
   	 			}
   	 		}
   	 	}) 
       }
	}
	
	
</script>
</body>
</html>