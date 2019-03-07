<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>


<!doctype html>
<html lang = "zh">
<head>
    <meta http-equiv = "X-UA-Compatible" content = "IE=edge,Chrome=1"/>
    <meta http-equiv = "Content-Type" content = "text/html; charset=utf-8"/>
    <meta name = "keywords" content = "OA系统"/>
    <meta name = "keywords" content = ""/>
    <meta name = "description" content = ""/>
    <meta name = "generator" content = "Notepad++"/>
    <title>规章草案分办列表</title>
    <link rel = "stylesheet" type = "text/css" href = "css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "css/sui-themes-green-append.css" media = "screen"/>
    <script src = "js/jquery.js"></script>
    <script src = "js/sui.js"></script>
    <script src = "js/tanchuang.js"></script>
    <script src = "js/layer/layer.js"></script>
    <script src = "${basePath}/resources/components/My97DatePicker/WdatePicker.js"></script>
</head>
<body class = "body_bg_1">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->
    <div class = "conteng-title">
        <span>当前位置：</span><em>立法听证会-首页</em>
    </div>

    <!--内容区-->
      <form class = "form-horizontal" name="form1" role = "form" action="${basePath}/legislate/hearing_list.do" method="post">
      <s:hidden name="hearingStatus"/>
     
      <div class="clearfix">
                <div class="container-fluid container-top">
                <div class="detai-con">
                <div class="col">
                <div class="control-group">
                <label class="control-label">
                        <span style="padding: 0 6px;">对应草案：</span>
                </label>
                        <div >
                                <s:textfield name="draftName" value="%{draftName}" id = "draftName"/>
                        </div>
                        
                <label class="control-label">
                        <span style="padding: 0 6px;">听证会议题：</span>
                </label>
                        <div >
                                <s:textfield name="hearingTitle" value="%{hearingTitle}" id = "hearingTitle"/>
                        </div>
                </div>
                </div>
                </div>
                
                <s:if test="hearingStatus == 'release'">
                <div class="detai-con">
		            <div class="col">
		                <div class="control-group">
		                    <label class="control-label">
		                        <span style="padding: 0 6px;">听证会地点：</span>
		                    </label>
		                    <div >
		                    	<s:textfield name="hearingPlace" value="%{hearingPlace}" id = "hearingPlace"/>	
		                    </div>
		                </div>
		            </div>
		        </div>
		        <div class="detai-con">
		            <div class="col">
		                <div class="control-group">
		                    <label class="control-label">
		                        <span style="padding: 0 6px;">听证会时间：</span>
		                    </label>
		                    <div >
		                        <input type="text" name="startTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='startTime' format='yyyy-MM-dd'/>">-
		                        <input type="text" name="endTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='endTime' format='yyyy-MM-dd'/>">
		                    </div>
		                </div>
		            </div>
		        </div>
                </s:if>  
                
                     <div class="clearfix">
	                    <div class="container-fluid container-top">
	                        <div class="row rh">
	                            <div class="col-sm-12 text-center">
	                               <button type="submit" class="btn btn-info btn-bg btn-120">查询</button>
	                            </div>
	                        </div>
	                    </div>
	                </div>
                </div>
            </div>
   
     
     
    <div class = "detai">
        <div class = "detai-con">
            <div class = "tab-list <s:if test='hearingStatus == "unrelease"'>tab-back</s:if>" onclick="window.location.href('${basePath}/legislate/hearing_list.do?hearingStatus=unrelease')">未发布</div>
            <div class = "tab-list <s:if test='hearingStatus == "release"'>tab-back</s:if>" onclick="window.location.href('${basePath}/legislate/hearing_list.do?hearingStatus=release')">已发布</div>
            <div class = "flright" onclick = "window.location.href='${basePath}/legislate/saveHearing.do'">添加</div>
            
        <div class = "biaodan">
            <div class = "tab-content" style = "display: block">
            	<s:if test='hearingStatus == "unrelease"'>
                <table class = "table1">
                    <thead>
                    <tr>
                    <th>编号</th>
                       <th>对应草案</th>
                       <th>听证会议题</th>
                       <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:set name="rownum" value="(pageNo-1)*pageSize+1"></s:set>
                    <s:iterator value="pageModel.result" id="task">
                    <tr>
                     <td><s:property value="rownum"/></td>
                        <td><s:property value="draft.draftName" /></td>
                        <td><s:property value="hearingTitle" /></td>
	                        <td nowrap="">
	                            <a class = "tianbao caozuo-btn" onclick="window.location.href='${basePath}/legislate/saveHearing.do?hearingId=<s:property value="hearingId" />'">编辑</a>
	                            <a class = "chakan caozuo-btn layer-jieshou" id = "fenban"  onclick="hearingRelease('<s:property value="hearingId" />')">发布</a>
	                        </td>
              
                    </tr>
                     <s:set name="rownum" value="#rownum+1"></s:set>
                    </s:iterator>
                    </tbody>
                </table>
                 </s:if>
                 
               <s:if test='hearingStatus == "release"'>
                <table class = "table1">
                    <thead>
                    <tr>
                    <th>编号</th>
                       <th>对应草案</th>
                       <th>听证会议题</th>
                       <th>听证会时间</th>
                       <th>听证会地点</th>
                       <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:set name="rownum" value="(pageNo-1)*pageSize+1"></s:set>
                    <s:iterator value="pageModel.result" id="task">
                    <tr>
                     <td><s:property value="rownum"/></td>
                        <td><s:property value="draft.draftName" /></td>
                        <td><s:property value="hearingTitle" /></td>
                        <td><s:date name='hearingTime' format='yyyy-MM-dd'/></td>
                        <td><s:property value="hearingPlace" /></td>
	                        <td nowrap="">
	                            <a class = "tianbao caozuo-btn" onclick="window.location.href='${basePath}/legislate/saveHearing.do?hearingId=<s:property value="hearingId" />'">编辑</a>
	                        </td>
              
                    </tr>
                     <s:set name="rownum" value="#rownum+1"></s:set>
                    </s:iterator>
                    </tbody>
                </table>
                 </s:if>
                <div class = "contentBox-paging">
                <%
                	Page retPage= (Page)request.getAttribute("pageModel");
                	int pageSize = (null == request.getParameter("pageSize") || "".equals(request.getParameter("pageSize")))?10:Integer.parseInt(request.getParameter("pageSize"));
                	int pageNo = (null == request.getParameter("pageNo") || "".equals(request.getParameter("pageNo")))?1:Integer.parseInt(request.getParameter("pageNo"));
                %>
                	<%@include file="/platform/include/pagelinks.jsp" %>
                </div>
            </div>
            

            
        </div>
            <div class = "clearfix"></div>
    </div>

    </div>
  </form>
</div>
<div class = "close-btn" id = "closebtn">X</div>

<script>
    $(function () {
        
      
       
        $('.tab-list').click(function () {
            var index = $(this).index();
            $(this).css({'background':'#288ce2','color':'#fff','font-size':'16px'}).siblings('.tab-list').css({'background':'#ececec','color':'#969696','font-size':'14px'})
            $('.biaodan .tab-content').eq(index).show().siblings('.tab-content').hide()
        });

    });
    
    function hearingRelease(hearingId){
		 if(confirm("确定要发布吗？")){
   	 	$.ajax({
   	 		"type":"POST",
   	 		"dataType":"json",
   	 		"url":"${basePath}/legislate/hearing_release.do",
   	 		"data":{"hearingId":hearingId},
   	 		"success":function(data){
   	 			alert(data.message);
   	 			if(data.code==0){
   	 				window.location.href(window.location.href);
   	 			}
   	 		}
   	 	}) 
       }
	}

</script>
</body>
</html>
