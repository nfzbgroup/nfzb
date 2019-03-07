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
    <title>单位意见列表</title>
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${basePath}/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${basePath}/legislate/js/jquery.js"></script>
    <script src = "${basePath}/legislate/js/sui.js"></script>
    <script src = "${basePath}/legislate/js/tanchuang.js"></script>
    <script src = "${basePath}/legislate/js/layer/layer.js"></script>
</head>
<body class = "body_bg_1">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->
    <div class = "conteng-title">
        <span>当前位置：</span><em>单位意见-首页</em>
    </div>


            
          <form name="form1" class = "form-horizontal" role = "form" method="post" action="${basePath}/legislate/teamOpinion_fk_list.do">
          <s:hidden name="detailStatus"></s:hidden>
<%--             <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                            <label class="formlabel">名称:</label>
                            <span class="formdiv">
                                <input type = "text">
                            </span>
                        <span class="formbuttom">
                            <button type="button" class="btn btn-info btn-bg btn-120">查询</button>
                        </span>
                    </div>
                </div>
            </div> --%>
            
                    <div class="detai-con">
		            <div class="col">
		                <div class="control-group">
                            <label class="control-label">
                            	<span style="padding: 0 6px;">草案名称:</span>
                            </label>
                            <div>
                                 <s:textfield name="draftName" value="%{draftName}" id = "draftName"/>
                            </div>
                            
                            <label class="control-label">
                            	<span style="padding: 0 6px;">类型:</span>
                            </label>
                            <div>
                                 <s:textfield name="opinionType" value="%{opinionType}" id = "opinionType"/>
                            </div>
                        </div>
                    </div>
                    </div>
                    
                <div class="clearfix">
                    <div class="container-fluid container-top">
                        <div class="row rh">
                            <div class="col-sm-12 text-center">
                                <button type="submit" class="btn btn-info btn-bg btn-120">查询</button>
                            </div>
                        </div>
                    </div>
                </div>
		 
		 
		 
 <!--内容区-->

            <div class = "clearfix"></div>
			<div class = "detai">
        	<div class = "detai-con">
            <div class = "tab-list  <s:if test='detailStatus == "send"'>tab-back</s:if>"  onclick="window.location.href('${basePath}/legislate/teamOpinion_fk_list.do?detailStatus=send')">未接收</div>
            <div class = "tab-list <s:if test='detailStatus == "receive"'>tab-back</s:if>"  onclick="window.location.href('${basePath}/legislate/teamOpinion_fk_list.do?detailStatus=receive')">已接收</div>
            <div class = "tab-list <s:if test='detailStatus == "feedback"'>tab-back</s:if>" onclick="window.location.href('${basePath}/legislate/teamOpinion_fk_list.do?detailStatus=feedback')">已反馈</div>
        <div class = "biaodan">
            <div class = "tab-content" style = "display: block">
                <table class = "table1">
                    <thead>
                    <tr>
                        <th>编号</th>
                        <th>草案名称</th>
                        <th>类型</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                     <s:set name="rownum" value="(pageNo-1)*pageSize+1"></s:set>
	                  <s:iterator value="pageModel.result" id="task">
	                    
	                    <tr class = "gray">
	                    <td><s:property value="rownum"/></td>
	                        <td><s:property value="teamOpinion.draft.draftName"/></td>
	                        <td><s:property value="teamOpinion.opinionType" /></td>
	                       	<td nowrap="">
	                        
	                           <s:if test='detailStatus == "send"'>   
	                              <a class = "tianbao caozuo-btn btn-70" 
	                           onclick = "window.location.href='${basePath}/legislate/saveTeamOpinionfk.do?op=add&teamOpinionId=<s:property value="teamOpinion.teamOpinionId"/>'">查看</a>
	                           
	                           <a class = "chakan caozuo-btn layer-jieshou"
                                onclick = "window.location.href='${basePath}/legislate/teamOpinion_fk_update_status.do?teamOpinionDetailId=<s:property value="teamOpinionDetailId"/>&opinionStatus=receive&detailStatus=send'">接收</a>
	                       		</s:if>
	                       		
	                       		<s:if test='detailStatus == "receive"'>   
		                              <a class = "tianbao caozuo-btn btn-70" 
		                           onclick = "window.location.href='${basePath}/legislate/saveTeamOpinionfk.do?op=add&teamOpinionId=<s:property value="teamOpinion.teamOpinionId"/>'">查看</a>
		                           
		                           <a class = "chakan caozuo-btn layer-jieshou"
	                                onclick = "fankui('<s:property value="teamOpinionDetailId"/>')">反馈</a>
	                       		</s:if>
	                       		
	                       		<s:if test='detailStatus == "feedback"'>   
		                              <a class = "tianbao caozuo-btn btn-70" 
		                           onclick = "fasong('<s:property value="teamOpinionDetailId"/>')">查看</a>
	                       		</s:if>
	                       		
                            </td>
	                    </tr>
	                     <s:set name="rownum" value="#rownum+1"></s:set>
	                 </s:iterator>

                    </tr>
                    </tbody>
                </table>
                
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
    </div>
    </form>
</div>

<script>
    $(function () {
        $('.tab-list').click(function () {
            var index = $(this).index();
            $(this).css({'background':'#288ce2','color':'#fff','font-size':'16px'}).siblings('.tab-list').css({'background':'#ececec','color':'#969696','font-size':'14px'})
            $('.biaodan .tab-content').eq(index).show().siblings('.tab-content').hide()
        })

    });
    function fasong(teamOpinionDetailId){
    	layer.open({
            type: 2,
            title: '',
            shade: false,
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: ['850px', '620px'],
            content: '${basePath}/legislate/teamOpinion_fk_save.do?op=view&teamOpinionDetailId='+teamOpinionDetailId,

        });
	}
    function fankui(teamOpinionDetailId){
    	layer.open({
            type: 2,
            title: '',
            shade: false,
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: ['850px', '620px'],
            content: '${basePath}/legislate/teamOpinion_fk_save.do?op=fk&teamOpinionDetailId='+teamOpinionDetailId,

        });
	}
</script>
</body>
</html>