<%@page import="com.wonders.fzb.legislate.LegislateConst"%>
<%@page import="org.apache.commons.lang3.StringUtils"%>
<%@page import="com.wonders.fzb.legislate.beans.Model"%>
<%@page import="com.wonders.fzb.legislate.beans.FileRecord"%>
<%@page import="java.util.Map"%>
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
    <title>征询单位意见发起</title>
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
        <span>当前位置：</span><em>单位意见征求-内容添加页</em>
    </div>
    <div class = "detai" style = "margin-bottom: 40px">
        <div class = "detai-con">
            <!--内容区-->
          <form method="post" enctype="multipart/form-data" action="${basePath}/legislate/saveTeamOpinion.do" name="draftForm">
            <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                            <div class="col-sm-12 text-center">
                                <h1 style="color: #B9404D;">征求意见单</h1>
                            </div>
                    </div>
                    <div class="row rh">
                        <div class="centtbody" >
                            <table width="50%">
                                <tbody>
                                <tr>
                                	<th>对应草案:</th>
                                	<td><h3>${draftName }</h3></td>
<%--                                     <s:select label="对应草案" name = "draftId" id = "draftId"  list="#request.draftList" value="draftId" listValue="draftName" listKey="draftId" ></s:select> --%>
                                </tr>

                                <tr>
                                    <th>表单:</th>
                                    <td> <a href = "${basePath}/legislate/teamOpinionFileRecordLoad.do?teamOpinionId=<s:property value="teamOpinionId"/>">单位意见内容信息.doc</td>
                                    
                                </tr>
                                 <tr>
                                    <th>反馈信息:</th>
                                    <td> <a href = "${basePath}/legislate/teamOpinionFileRecordLoad.do?teamOpinionId=<s:property value="teamOpinionId"/>"></td>
                                    
                                </tr>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
               	 <%if(request.getParameter("teamOpinionId") != null){ %>
       
			            	
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
						           		 
					                   			<a  href = "${basePath}/legislate/fileRecordLoad.do?fileRecordId=<s:property value="fileRecordId" />">下载</a>
					                   			
						           		 
				                    	
				                    </td>
				                </tr>
				                </s:iterator>
				                 <%} %>
				                </s:iterator>
				                </tbody>
				            </table>

			            <%} %>
            </div>

                 <s:hidden name="op"/>
     <s:hidden name="teamOpinionId"/>
      <s:hidden name="draftId"/>
     
            </form>
    </div>
</div>
</div>


<script>
    $(function () {
        $('input[type="file"]').change(function (e) {
            var filename = $(this).val().split("\\").pop();
            $('.file-text').text(filename)
        })
    });

</script>
</div>
 <jsp:include page="/common/scripts.jsp" />

<script language="javascript">
	$.include([ 'tree', 'treeEX', 'datepicker' ]);
</script>
<script> 
function callBack(){
	window.location.href(window.location.href);
}
	
function openFbdDoc(){
    window.open('../platform/composer/compose_doc.jsp?docId=${teamOpinionId}&docType='+encodeURIComponent("单位意见内部表单")+'&fromTemplate=${fromTemplate}');
}

function uploadFile(bizType){
	layer.open({
        type: 2,
        title: '',
        shade: false,
        maxmin: true,
        shadeClose: true, //点击遮罩关闭层
        area: ['640px', '480px'],
        content: '${basePath}/legislate/draft/upload_file.jsp?activityType=单位意见&draftId=${draftId}&bizType='+bizType,
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
	var display = $("#opinionTeamList");
	var value = $("#opinionTeamListId");
	
	value.attr("value",valueId);
	display.attr("value", displayName);
}

function add(){
	var opinionInfo=$("#opinionInfo").val();
	var opinionTeamList=$("#opinionTeamList").val();
	/* if(opinionInfo==""||opinionInfo==null){
		alert("请填写单位意见信息");
		$("#opinionInfo").focus();
		return;
	} */
	/* if(opinionTeamList==""||opinionTeamList==null){
		alert("请选择会议人员");
		return;
	} */
	document.draftForm.submit();
}

function beforeClick(treeId, treeNode) {
	var zTree = $.fn.zTree.getZTreeObj("teamTree");
	zTree.checkNode(treeNode, !treeNode.checked, null, true);
	return false;
}

function showMenu() {
	var teamObj = $("#opinionTeamList");
	var teamOffset = $("#opinionTeamList").offset();
	$("#menuContent").css({left:teamOffset.left + "px", top:teamOffset.top + teamObj.outerHeight() + "px"}).slideDown("fast");
	$("#menuContent").width($("#opinionTeamList").width() + 24);
	
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

	$.ajax({
		url:"${basePath}/legislate/teamOpinionOrgList.do",
		type:"post",
		success:function(json){
			$.fn.zTree.init($("#classTree"), setting, JSON.parse(json));
		}
	});
	
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
   	 				location.href = '/fzb_platform/legislate/saveTeamOpinionfk.do?op=add&teamOpinionId=${teamOpinionId}';
   	 			}
   	 		}
   	 	}) 
       }
	}
</script>
</body>
</html>