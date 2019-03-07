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
<%
String tp = (String)request.getAttribute("tp");
%>
<body class = "body_bg_1">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->
    <div class = "conteng-title">
        <span>当前位置：</span><em>立法计划列表-首页</em>
    </div>
    <form class = "form-horizontal" action="${basePath}/legislate/plan_serach_lfjhlb.do" method="post">
    <input type="hidden" name="state" id="state" value="${state }">
    			<div class="detai-con" id="searchContent">
		        <%-- <s:if test='state=="send"'>
		            <div class="col">
		                <div class="control-group">
		                    <label class="control-label">
		                        <span style="padding: 0 6px;">发起时间：</span>
		                    </label>
		                    <div >
		                        <input type="text" class="input-width2" name="startTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='startTime' format='yyyy-MM-dd'/>">&nbsp;-
		                        <input type="text" class="input-width2" name="endTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='endTime' format='yyyy-MM-dd'/>">
		                    </div>
		                </div>
		            </div>
		         </s:if>    --%>
		            <div class="col">
	                <div class="control-group">
	                <label class="control-label" style="width:155px">
	                        <span style="padding: 0 10px;">立法计划项目名称：</span>
	                </label>
	                        <div>
	                                <s:textfield cssClass="input-width" name="planName" value="%{planName}" id = "planName" style="margin-top:10px"/>
	                        </div>
	                </div>
	                </div>
		            <s:if test='state=="send"'>
		            <div class="col">
		                <div class="control-group">
		                    <label class="control-label">
		                        <span style="padding: 0 6px;">报送时间：</span>
		                    </label>
		                    <div >
		                        <input type="text" class="input-width2" name="processStartTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='processStartTime' format='yyyy-MM-dd'/>" style="margin-top:10px">&nbsp;-
		                        <input type="text" class="input-width2" name="processEndTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='processEndTime' format='yyyy-MM-dd'/>" style="margin-top:10px">
		                    </div>
		                </div>
		            </div>
		        	</s:if>
		        </div>
    				
   		 		<div class="detai-con">
   		 		<%-- <s:if test='state=="init"'>
                <div class="col">
		                <div class="control-group">
		                    <label class="control-label" style="<s:if test='state=="init"'>width:90px</s:if>">
		                        <span style="padding: 0 6px;">发起时间：</span>
		                    </label>
		                    <div >
		                        <input type="text" class="input-width" name="startTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='startTime' format='yyyy-MM-dd'/>" style="margin-top:10px">&nbsp;-
		                        <input type="text" class="input-width" name="endTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='endTime' format='yyyy-MM-dd'/>" style="margin-top:10px">
		                    </div>
		                </div>
		        </div>
		        </s:if> --%>
   		 		
                
                
                <%-- <s:if test='state=="send"'>
                <div class="col">
                <div class="control-group">
                <label class="control-label">
                        <span style="padding: 0 6px;">处理环节：</span>
                </label>
                        <div >
                                <s:select list="#{'请选择':'请选择','dise':'未认领','process':'办理中','receive':'待分办','send':'未接收','claim':'已认领','summary':'已汇总'}" name="status" cssClass="input-xlarge" style="margin-top:10px"></s:select>
                        </div>
                </div>
                </div>
                </s:if> --%>
                
                <%-- <div class="col">
                <div class="control-group">
                <label class="control-label" style="<s:if test='state=="init"'>width:90px</s:if>">
                        <span style="padding: 0 6px;">发起人：</span>
                </label>
                        <div >
                                <s:textfield cssClass="input-width" name="creatorName" value="%{creatorName}" id = "creatorName" style="margin-top:10px"/>
                        </div>
                </div>
                </div> --%>
                </div>
			    <div class="clearfix" id="showButton">
	                <div class="container-fluid container-top">
	                    <div class="row rh">
	                        <div class="col-sm-12 text-center">
	                            <input type="button" onclick="showSearch();" class="btn btn-info btn-bg btn-120" value="查询"></input>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            <div class="clearfix" id="searchButton">
	                <div class="container-fluid container-top">
	                    <div class="row rh">
	                        <div class="col-sm-12 text-center">
	                            <button type="submit" class="btn btn-info btn-bg btn-120">查询</button>
	                        </div>
	                    </div>
	                </div>
	            </div>
    </form>           
    <!--内容区-->
    <div class = "detai">
        <div class = "detai-con">
            <div id="init" class = "tab-list <s:if test='state == "init"'>tab-back</s:if> " onclick="window.location.href=('${basePath}/legislate/plan_init_list.do')">未上报</div>
            <div id="send" class = "tab-list <s:if test='state == "send"'>tab-back</s:if>"  onclick="window.location.href=('${basePath}/legislate/plan_send_list.do')">已上报</div>
            <s:if test='state=="init"'><div class = "flright" onclick = "window.location.href='${basePath}/legislate/savePlan.do'">新建</div></s:if>
        <div class = "biaodan">
        
            <div class = "tab-content" style = "display: block">
                    <table class = "table1">
                    <thead>
                    <tr>
                        <th>草案编号</th>
                        <th>立法计划发起名称</th>
                        <!-- <th>处理环节</th>
                        <th>发起人</th> -->
                        <th colspan="2">立法计划项目类型</th>
                        <s:if test='state=="init"'>
                        	<th>新建时间</th>
                        </s:if>
                        <s:if test='state=="send"'>
                        	<th>报送时间</th>
                        </s:if>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                     <s:set name="rownum" value="(pageNo-1)*pageSize+1"></s:set>
                    <s:iterator value="pageModel.result" id="tasks">
                   		<s:if test='state == "init"'>
                   			 <tr>
		                        <td><s:property value="rownum"/></td>
		                        <td><s:property value="planName" /></td>
		                        <%-- <td>未上报</td>
		                        <td><s:property value="creatorName"/></td> --%>
		                        <td><s:property value="planType"/></td>
		                        <td><s:property value="projectType"/></td>
		                        <s:if test='state=="init"'>
		                        	<td><s:date name="createTime" format="yyyy-MM-dd" /></td>
		                        </s:if>
		                        <td>
		                      	   <a class="tianbao caozuo-btn" onclick="xiugai('<s:property value="planId" />')">修改</a>
		                      	   <%--<a class="tianbao caozuo-btn" onclick="cailiao('<s:property value="planId" />')">材料</a>--%>
		                      	   <a class = "tianbao caozuo-btn layer-fenban" onclick="shangbao('<s:property value="planId" />')">报送</a>
		                      	   <a class="tianbao caozuo-btn" onclick="shanchu('<s:property value="planId" />')">删除</a>
		                        </td>
		                    </tr>
                   		</s:if>
                   		<s:else>
                   			 <tr>
		                        <td><s:property value="rownum"/></td>
		                        <td><s:property value="plan.planName" /></td>
		                        <%-- <td>
		                        	<s:if test="plan.status =='dise'">
		                        		未认领
				                    </s:if>
				                    <s:if test="plan.status =='process'">
				                       	办理中
				                    </s:if>
		                        	<s:if test="plan.status =='receive'">
		                        		待分办
		                        	</s:if>
		                        	<s:if test="plan.status =='send'">
		                        		未接收
		                        	</s:if>
		                        	<s:if test="plan.status =='claim'">
		                        		已认领
		                        	</s:if>
		                        	<s:if test="plan.status =='summary'">
		                        		已汇总
		                        	</s:if>
		                        </td>
		                        <td><s:property value="plan.creatorName"/></td> --%>
		                        <td><s:property value="plan.planType"/></td>
		                        <td><s:property value="plan.projectType"/></td>
		                        <%-- <td><s:date name="plan.createTime" format="yyyy-MM-dd HH:mm:ss" /></td> --%>
		                        <td><s:date name="processTime" format="yyyy-MM-dd" /></td>
		                        <td>
		                      	   <a class="tianbao caozuo-btn" onclick="chakan('<s:property value="plan.planId" />&op=view')">查看</a>
		                        </td>
		                    </tr>
                   		</s:else>
                    <s:set name="rownum" value="#rownum+1"></s:set>
                    </s:iterator>
                  
                    </tbody>
                </table>
            </div>
                
                <div class = "contentBox-paging">
                <%
                Page retPage= (Page)request.getAttribute("pageModel");
                int pageSize = (null == request.getParameter("pageSize") || "".equals(request.getParameter("pageSize")))?10:Integer.parseInt(request.getParameter("pageSize"));
                int pageNo = (null == request.getParameter("pageNo") || "".equals(request.getParameter("pageNo")))?1:Integer.parseInt(request.getParameter("pageNo"));
                %>
                <form name="form1" action="">
                <%@include file="/platform/include/pagelinks.jsp" %>
                </form>
                </div>
        </div>
    </div>
        <div class = "clearfix"></div>
    </div>
</div>
<input type="hidden" name="checkDuplicate" id="checkDuplicate">
<script>
    <%if("search".equals(tp)){%>
	    $("#searchContent").show();
		$("#searchButton").show();
		$("#showButton").hide();
    <%}else{%>
	    $("#searchContent").hide();
		$("#searchButton").hide();
		$("#showButton").show();
    <%}%>
    $(function () {
        $('.tab-list').click(function () {
            var index = $(this).index();
/*             var thia=$(".thisbtn").index();
            $('.thisbtn').eq(thia).show().parents('.detai-con').find('.thisbtn').hide(); */
            $(this).css({'background':'#288ce2','color':'#fff','font-size':'16px'}).siblings('.tab-list').css({'background':'#ececec','color':'#969696','font-size':'14px'})
            $('.biaodan .tab-content').eq(index).show().siblings('.tab-content').hide()
        })

    });
    
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
	  	      	 				window.location.href=(action);
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
  	      	 				window.location.href=(action);
  	      	 			}
  	      	 		}
  	      	 	}) 
  			}
      	 	
          }
  	}
    
    function showSearch(){
    	$("#showButton").hide();
    	$("#searchContent").show();
    	$("#searchButton").show();
    }
    
    function xiugai(planId){
     	 	window.location.href = "${basePath}/legislate/savePlan.do?planId="+planId ;
  	}
    
    function cailiao(planId){
 	 	window.location.href=("${basePath}/legislate/planAttach.do?planId="+planId)
	}
    
    function shanchu(planId){
    	if(confirm("确定要删除吗？")){
    		window.location.href=("${basePath}/legislate/plan_init_list.do?op=del&planId="+planId);
    	}
    }
    	
    function chakan(planId){
       	layer.open({
               type: 2,
               title: '',
               shade: false,
               maxmin: true,
               shadeClose: true, //点击遮罩关闭层
               area: ['850px', '620px'],
               content: '${basePath}/legislate/lfjhlb.do?planId='+planId+'&t='+new Date(),
           });
   	}
</script>
</body>
</html>