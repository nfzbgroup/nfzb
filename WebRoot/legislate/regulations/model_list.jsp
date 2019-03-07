<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="s"  uri="/struts-tags"%>
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
    <script src = "${basePath}/resources/components/My97DatePicker/WdatePicker.js"></script>
    
<script type="text/javascript">
$(function(){
	$("#search").click(function(){
		 $("#form1").attr("action","${basePath}/legislate/listModel.do?search=query")
		 document.form1.submit();	
	});
})
	function deleteModel(modelId){
	var r=confirm("是否确定要删除");
	if(r){
	 	$.ajax({
	 		"type":"POST",
	 		"dataType":"json",
	 		"url":"/fzb_platform/legislate/deleteModel.do",
	 		"data":{"modelId":modelId},
	 		"success":function(data){
	 			if(data.code == 200){
	 				alert(data.message);
	 				location.reload();
	 			}
	 		}
	 	}) 
	} 	
	}
	
	function disabledd(modelId){
	var r=confirm("是否要禁用");
	if(r){
	 	$.ajax({
	 		"type":"POST",
	 		"dataType":"json",
	 		"url":"/fzb_platform/legislate/disabled.do",
	 		"data":{"modelId":modelId},
	 		"success":function(data){
	 			if(data.code == 200){
	 				alert(data.message);
	 				location.reload();
	 			}
	 		}
	 	}) 
	} 	
	}
	
	function enabled(modelId){
	var r=confirm("是否要启用");
	if(r){
	 	$.ajax({
	 		"type":"POST",
	 		"dataType":"json",
	 		"url":"/fzb_platform/legislate/enabled.do",
	 		"data":{"modelId":modelId},
	 		"success":function(data){
	 			if(data.code == 200){
	 				alert(data.message);
	 				/* location.reload(); */
	 				window.location.href(window.location.href);
	 			}
	 		}
	 	}) 
	} 	
	}
</script>

</head>

<body class = "body_bg_1">
<form action="${basePath}/legislate/listModel.do" method="post" id="form1" name="form1">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->
    <div class="conteng-title">
        <span>当前位置：</span><em>范本管理-首页</em>
    </div>
    
    <!--内容区-->
    <div class="detai">
        <div class="detai-con">
            <div class="col">
                <div class="control-group">
                    <label class="control-label">
                        <span style="padding: 0 6px;">范本名称：</span>
                    </label>
                    <div >
                        <s:textfield name="modelName"/>
                    </div>
                </div>
            </div>
            
            <div class="col">
                <div class="control-group">
                    <label class="control-label">
                        <span style="padding: 0 6px;">范本类型：</span>
                    </label>
                    <div>
                        <s:select list="#{'请选择':'请选择','报请审核规章草案的函':'报请审核规章草案的函','规章草案':'规章草案','起草说明':'起草说明','拟定规章所依据的法律、法规目录':'拟定规章所依据的法律、法规目录','征求各方面意见的情况和材料':'征求各方面意见的情况和材料','参与起草工作的专家对规章草案的意见':'参与起草工作的专家对规章草案的意见','需要报送的其他材料':'需要报送的其他材料','听证会公告':'听证会公告','听证会报名名单':'听证会报名名单'}" name="modelType" cssClass="input-xlarge"></s:select>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="detai-con">
            <div class="col">
                <div class="control-group">
                    <label class="control-label">
                        <span style="padding: 0 6px;">应用环节：</span>
                    </label>
                    <div >
                        <s:textfield name="activityType"/>
                    </div>
                </div>
            </div>
        </div>
        
        <div class="detai-con" style="position:relative">
        <!-- <a javascript="void(0);" class="sui-btn btn-primary01 btn-lg btn-margin thisbtn" id="search" >查询</a> -->
            <div class="col">
                <div class="control-group">
                    <label class="control-label">
                        <span style="padding: 0 6px;">添加时间：</span>
                    </label>
                    <div >
                        <input type="text" name="startTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='startTime' format='yyyy-MM-dd'/>">&nbsp;-
                        <input type="text" name="endTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='endTime' format='yyyy-MM-dd'/>">
                    </div>
                </div>
            </div>
        </div>
        
        <div class="rows">
            <div class="rows">
                <a javascript="void(0);" class="sui-btn btn-primary01 btn-lg btn-margin" id="search" >查询</a>
            </div>
        </div>
        <%int i=1; %>
        <div class="biaodan">
            <table class="table1">
                <thead>
                <tr>
                    <th nowrap="nowrap">范本编号</th>
                    <th nowrap="nowrap">应用环节</th>
                    <th nowrap="nowrap">范本类型</th>
                    <th nowrap="nowrap">范本名称</th>
                    <th nowrap="nowrap">添加时间</th>
                    <th nowrap="nowrap">操作</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="pageModel.result" id="a">
                    <tr>
                        <td nowrap="nowrap">
                            <%=i %>
                        </td>
                        <td><s:property value="activityType"/></td>
                        
                        <td>
                       		 <s:property value="modelType"/>
                        </td>
                        <td><s:property value="modelName"/></td>
                        <td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss"/></td>
                        <td nowrap="nowrap">
                            <a class="chakan" style="margin: 0;" href="${basePath}/legislate/saveModel.do?modelId=${modelId }">修改</a>
                            <a class="chakan" onclick="deleteModel('${modelId}')">删除</a>
                            <a class="chakan" href="${basePath}/legislate/viewModel.do?modelId=${modelId }">查看</a>
                            <s:if test="status == 1">
                            <a class="chakan" onclick="disabledd('${modelId}')">禁用</a>
                            </s:if>
                            <s:else>
                            <a class="chakan" onclick="enabled('${modelId}')">启用</a>
                            </s:else>
                        </td>
                    </tr>       
                    <% i = i+1; %>         
                </s:iterator>
                
                </tbody>
            </table>
            <div class="contentBox-paging">
                <%
	                Page retPage= (Page)request.getAttribute("pageModel");
	                int pageSize = (null == request.getParameter("pageSize") || "".equals(request.getParameter("pageSize")))?10:Integer.parseInt(request.getParameter("pageSize"));
	                int pageNo = (null == request.getParameter("pageNo") || "".equals(request.getParameter("pageNo")))?1:Integer.parseInt(request.getParameter("pageNo"));
	            %>
	            <%@include file="/platform/include/pagelinks.jsp" %>
            </div>
        </div>
        <div class="rows">
            <a href="${pageContext.request.contextPath }/legislate/saveModel.do" class="sui-btn btn-primary01 btn-lg btn-margin">添加范本</a>
        </div>
    </div>


</div>
</form>
</body>
</html>