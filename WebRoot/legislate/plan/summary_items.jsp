<%@page import="com.wonders.fzb.base.beans.Page"%>
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
    <title>立法计划年度初审表</title>
    <link rel = "stylesheet" type = "text/css" href = "css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "css/sui-themes-green-append.css" media = "screen"/>
    <script src = "js/jquery.js"></script>
    <script src = "js/layer/layer.js"></script>
    <script src = "js/sui.js"></script>
    <script src = "js/tanchuang.js"></script>
</head>
<body class = "body_bg_1">
<!--面包屑-->
<form class = "form-horizontal" role = "form">
                <div class="clearfix">
                    <div class="container-fluid container-top text-center">
                               <h2 style="align-content: center;">${pageModel.result[0].planSummary.year }年度部门申报市政府规章项目初审表</h2>
                    </div>
                </div>
 
            </form>
 
<div class = "conteng">
    <div class = "conteng-title">
        <span>当前位置：</span><em>立法计划年度初审表</em>
    </div>
            <div class = "clearfix">
            <div class = "container-fluid container-top">
                <div class = "row">
                    <div class = "col-sm-12">
                    	<!--<div class = "tab-list tab-back">待汇总</div>
            			<div class = "tab-list">已汇总</div>-->
                        <div class = "biaodan">
                            <div class = "tab-content" style = "display: block">
                                <table class = "table1">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>起草部门</th>
                                    <th>草案名称</th>
                                    <th>立/改/废</th>
                                    <th>拟解决的主要问题</th>
                                    <th>目前进展情况</th>
                                    <th>初审意见</th>
                                </tr>
                                </thead>
                                <tbody>
                                <s:set name="rownum" value="(pageNo-1)*pageSize+1"></s:set>
                   				 <s:iterator value="pageModel.result" id="task">
	                                <tr>
	                                    <td><s:property value="rownum"/></td>
	                                    <td><s:property value="teamName"/></td>
	                                    <td><s:property value="planName"/></td>
	                                    <td><s:property value="planType"/></td>
	                                    <td><s:property value="reason"/></td>
	                                    <td><s:property value="progress"/></td>
	                                    <td><s:property value="teamName"/></td>
	                                </tr>
	                                <s:set name="rownum" value="#rownum+1"></s:set>
		           				 </s:iterator>
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
                </div>
            </div>
        </div>
 
<script> 
    $(function () {
        $('.detai .tab-list').click(function () {
            var index = $(this).index();
            $(this).css({'background':'#288ce2','color':'#fff','font-size':'16px'}).siblings('.tab-list').css({'background':'#ececec','color':'#969696','font-size':'14px'})
            $('.indextab .tab-content').eq(index).show().siblings('.tab-content').hide()
        })
 
        $('.layer-close').click(function () {
            var i = parent.layer.getFrameIndex(window.name);
            parent.layer.close(i);
        })
    });
</script>
</body>
</html>
