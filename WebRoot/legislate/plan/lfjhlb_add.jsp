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
    <title>立法计划发起</title>
    
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${basePath}/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${basePath}/legislate/js/jquery.js"></script>
    <script src = "${basePath}/legislate/js/sui.js"></script>
    <script src = "${basePath}/legislate/js/tanchuang.js"></script>
    <script src = "${basePath}/legislate/js/layer/layer.js"></script>
</head>
<body class = "body_bg_1">
<%
String planType = (String)request.getAttribute("planType");
String projectType = (String)request.getAttribute("projectType");
String sendYear = (String)request.getAttribute("sendYear");
String sendMonth = (String)request.getAttribute("sendMonth");
Calendar cal = Calendar.getInstance();
int year = cal.get(Calendar.YEAR);
if(sendYear==null||"".equals(sendYear)||"null".equals(sendYear)){
	sendYear = "" + year;
}
%>
<form action="${basePath}/legislate/savePlan.do" method="post" name="modelForm">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->

    <div class = "conteng-title">
        <span>当前位置：</span><em>立法计划发起-首页</em>
    </div>

    <!--内容区-->
    <div class = "detai">
        <div class = "detai-con">
            <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                        <label class="formlabel">立法计划名称:</label>
                        <span class="formdiv">
                            <s:textfield name="planName" id="planName" value="%{plan.planName}"></s:textfield>
                        </span>
                    </div>
                    <div class="row rh">
                        <label class="formlabel">立法计划类型:</label>
                        	<span class="formdiv">
                                <select name="planType">
                                	<option value="制定项目" <%if("制定项目".equals(planType)){ %>selected<%} %>>制定项目</option>
                                	<option value="修订项目" <%if("修订项目".equals(planType)){ %>selected<%} %>>修订项目</option>
                                	<option value="废止项目" <%if("废止项目".equals(planType)){ %>selected<%} %>>废止项目</option>
                                </select>
                            </span>
                    </div>
                    <div class="row rh">
                        <label class="formlabel">立法项目类型:</label>
                        	<span class="formdiv">
                                <select name="projectType" id="projectType" onchange="Getvaule();">
                                	<option value="正式项目" <%if("正式项目".equals(projectType)){ %>selected<%} %>>正式项目</option>
                                	<option value="预备项目" <%if("预备项目".equals(projectType)){ %>selected<%} %>>预备项目</option>
                                	<option value="调研项目" <%if("调研项目".equals(projectType)){ %>selected<%} %>>调研项目</option>
                                </select>
                            </span>
                    </div>
                    <div class="row rh">
                        <label class="formlabel">上位法依据:</label>
                        <span class="formdiv arh">
                             <s:textarea  style="font-size: 14px;height:50px;font-family: 微软雅黑, 黑体, 宋体"  name="lexSuperior" id="lexSuperior" value="%{plan.lexSuperior}"></s:textarea>
                        </span>
                    </div>
                    <div class="row rh">
                        <label class="formlabel">建议立项理由:</label>
                        <span class="formdiv arh">
                             <s:textarea  style="font-size: medium;font-family: 微软雅黑, 黑体, 宋体"  name="reason" id="reason" value="%{plan.reason}"></s:textarea>
                        </span>
                    </div>
                    <br><center><font color="red">“建议立项理由”栏请重点阐述立法的必要性以及拟通过立法解决的主要问题等</font></center>
                    <div class="row rh">
                        <label class="formlabel">目前进展情况:</label>
                        <span class="formdiv arh">
                             <s:textarea  style="font-size: medium;font-family: 微软雅黑, 黑体, 宋体"  name="progress" id="progress" value="%{plan.progress}"></s:textarea>
                        </span>
                    </div>
                    <br><center><font color="red">“目前进展情况”请注明开展立法调研的情况，是否形成调研论证报告或者规章草案</font></center>
                    <div class="row rh">
                        <label class="formlabel">联系人:</label>
                        <span class="formdiv arh">
                             <s:textarea  style="font-size: medium;font-family: 微软雅黑, 黑体, 宋体"  name="contacts" id="contacts" value="%{plan.contacts}"></s:textarea>
                        </span>
                    </div>
                    <div class="row rh">
                        <label class="formlabel">电话:</label>
                        <span class="formdiv arh">
                             <s:textarea  style="font-size: medium;font-family: 微软雅黑, 黑体, 宋体"  name="telephone" id="telephone" value="%{plan.telephone}"></s:textarea>
                        </span>
                    </div>
                    <div class="row rh">
                        <label class="formlabel">备注:</label>
                        <span class="formdiv arh">
                        	<div id="sendInfo" <%if("预备项目".equals(projectType)||"调研项目".equals(projectType)){ %>style="display:none"<%} %>>
                        	 拟于
                    		<select name="sendYear" style="width:60px">
                    			<%for(int i=cal.get(Calendar.YEAR)+1;i>=2011;i--){%>
                    				<option value="<%=i%>" <%if((""+i).equals(sendYear)){ %>selected<%} %>><%=i %></option> 
                    			<%} %>
                    		</select>
                    		年
                    		<select name="sendMonth" style="width:40px">
                    			<<%for(int i=1;i<=12;i++){ %>
                    				<option value="<%=i%>" <%if((""+i).equals(sendMonth)){ %>selected<%} %>><%=i %></option>
                    			<%} %> 
                    		</select>
                    		月前报送规章草案
                    		<br><br>
                    		</div>
                             <s:textarea  style="font-size: medium;font-family: 微软雅黑, 黑体, 宋体"  name="remark" id="remark" value="%{plan.remark}"></s:textarea>
                        </span>
                    </div>
                    
                    <%-- <div class="row rh">
                        <label class="formlabel"><font color="red">格式要求：</font></label>  
                        <span class="formdiv arh">
                        	<font color="red">
                            1、上边距：4cm，下边距：3.5cm，左、右边距：各3.4cm，行距：最小值28。<br>
                            2、标题：小二号宋体加粗，居中。<br>
                            3、主送单位：小三号仿宋。<br>
                            4、正文：一级标题小三号黑体，二级标题小三号楷体加粗，三级标题小三号仿宋加粗，其他内容均为小三号仿宋。
                             </font>
                        </span>
                        </div> --%>
					<div class="legislate-btnGroup">
						<a href="#"
							class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin"
							onclick="add();">保存</a>
							<s:if test="plan!=null">
						<!-- <button type="button"
							class="sui-btn btn-primary01 btn-lg btn-margin"
							onclick="openlfjhDoc();" id="tijiao">原稿编辑</button> -->
						<button type="button"
							class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin"
							onclick="openlfjhDoc2();" id="tijiao">打印</button>
							<button type="button"
							class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin"
							onclick="shangbao('${plan.planId}');" id="tijiao">报送</button>
							</s:if>
							<a href="${basePath}/legislate/plan_init_list.do"
							class="legislate-btn sui-btn btn-primary01 btn-lg btn-margin">返回</a> 
					</div>
                </div>
                <%if(request.getParameter("planId") != null){ 
            	String activityType = LegislateConst.ACTIVITY_TYPE_Plan_ADD;
            	String outId = (String)request.getAttribute("planId");
            	String bizId = outId;
            	%>
       			<%@include file="/legislate/plan/file_list.jsp" %>
            	<%} %>
            </div>
        </div>
    </div>
</div>
<s:hidden name="op"/>
<s:hidden name="planId" value="%{plan.planId}"/>
<input type="hidden" name="checkDuplicate" id="checkDuplicate">
</form>

<script>
    $(function () {
        $('input[type="file"]').change(function (e) {
            var filename = $(this).val().split("\\").pop();
            $('.file-text').text(filename)
        })
    })
    
    function add(){
    	if($("#planName").val()==""||$("#planName").val()==null){
    		alert("请填写立法计划名称");
    		$("#planName").focus();
    		return;
    	}
    	if($("#reason").val()==""||$("#reason").val()==null){
			alert("请填写建议立项理由");    
			$("#reason").focus();
			return;
    	}
    	if($("#progress").val()==""||$("#progress").val()==null){
    		alert("请填写目前进展情况");  
    		$("#progress").focus();
    		return;
    	}
    	/* if($("#remark").val()==""||$("#remark").val()==null){
    		alert("请填写备注");
    		$("#remark").focus();
    		return;
    	} */
    	document.modelForm.submit(); 
    }
    
    function openlfjhDoc(){
    	window.open('../platform/composer/compose_doc.jsp?docId=${plan.planId}&docType='+encodeURIComponent("规章制定计划项目表")+'&fromTemplate=${fromTemplate}','newWindow2','');
    	window.location.href=("${basePath}/legislate/savePlan.do?planId=${plan.planId}");
	}
    function openlfjhDoc2(){
    	window.open('../platform/composer/compose_doc.jsp?docId=${plan.planId}&docType='+encodeURIComponent("规章制定计划项目表")+'&fromTemplate=Y&op=read','newWindow2','');
	}
    function callBack(){
		 window.location.href=("${basePath}/legislate/savePlan.do?op=&planId=${planId}");
	}
    function Getvaule(){
    	var projectType=document.getElementById("projectType");
    	var index = projectType.selectedIndex;
		var text = projectType.options[index].text;
		if("正式项目"==text){
			$("#sendInfo").show();
		}else{
			$("#sendInfo").hide();
		}
    }
    
    function checkshangbao(planId){
    	$.ajax({
  	 		async:false,
    		"type":"POST",
  	 		"dataType":"json",
  	 		"url":"${basePath}/legislate/plan_check.do",
  	 		"data":{"planId":planId},
  	 		"success":function(data){
  	 			$("#checkDuplicate").val(data.duplicate);
  	 		}
  	 	}) 
    }
    
    function shangbao(planId){
   	 var action=window.location.href; 
 		 if(confirm("确定要上报吗？")){
 			checkshangbao(planId);
 			if($("#checkDuplicate").val() == 1){
 				var messageStr = "上报名称系统中已存在！请填写说明理由后方可上报！";
 				var defaultStr = "";
 				var content = window.prompt(messageStr, defaultStr);
 				if(content!=null&&content!=""){
	  				$.ajax({
	  	      	 		"type":"POST",
	  	      	 		"dataType":"json",
	  	      	 		"url":"${basePath}/legislate/plan_send.do",
	  	      	 		"data":{"planId":planId,"content":content},
	  	      	 		"success":function(data){
	  	      	 			if(data.code==200){
	  	      	 				alert(data.message);
		  	      	 			if(data.message=="报送成功"){
		    	 					window.location.href=('${basePath}/legislate/plan_init_list.do');
		    	 				}else{
		    	 					window.location.href=('${basePath}/legislate/savePlan.do?planId='+planId);
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
 	      	 		"url":"${basePath}/legislate/plan_send.do",
 	      	 		"data":{"planId":planId},
 	      	 		"success":function(data){
 	      	 			if(data.code==200){
 	      	 				alert(data.message);
	 	      	 			if(data.message=="报送成功"){
	    	 					window.location.href=('${basePath}/legislate/plan_init_list.do');
	    	 				}else{
	    	 					window.location.href=('${basePath}/legislate/savePlan.do?planId='+planId);
	    	 				}
 	      	 			}
 	      	 		}
 	      	 	}) 
 			}
     	 	
         }
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
 	 				location.href = '${basePath}/legislate/savePlan.do?planId=${planId}';
 	 			}
 	 		}
 	 	}) 
     }
	}
</script>
</body>
</html>