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
    <title>立法计划汇总-首页</title>
    <link rel = "stylesheet" type = "text/css" href = "css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "css/sui-themes-green-append.css" media = "screen"/>
    <script src = "js/jquery.js"></script>
    <script src = "js/layer/layer.js"></script>
    <script src = "js/sui.js"></script>
    <script src = "js/tanchuang.js"></script>
    <script src = "${basePath}/resources/components/My97DatePicker/WdatePicker.js"></script>
</head>
<body class = "body_bg_1">
<!--面包屑-->
<div class = "conteng">
    <div class = "conteng-title">
        <span>当前位置：</span><em>立法计划汇总-首页</em>
    </div>
            <form action="${basePath}/legislate/planSummary_list.do?search=query" method="post" class = "form-horizontal" role = "form">
<%--                 <div class="clearfix">
                    <div class="container-fluid container-top">
                        <div class="row rh">
                            <label class="formlabel">经办处:</label>
                            <span class="formdiv fbw">
                               <s:select list="#{'U_3_5':'经济法规处','U_3_6':'城建法规处','U_3_7':'社会法规处'}" name="teamId" id="teamId" cssClass="select-option"></s:select>
                            </span>
                            <label class="formlabel">年份:</label>
                            <span class="formdiv fbw">
                                <s:select list="#{'请选择':'请选择','2017':'2017','2018':'2018','2019':'2019'}"  name = "year" id = "year" cssClass="select-option"></s:select>
                            </span>
                        </div>
                    </div>
                </div> --%>
                
        <div class="detai-con">
        	<div class="col">
                <div class="control-group">
                    <label class="control-label">
                        <span style="padding: 0 6px;">年份:</span>
                    </label>
                    <div>
                    	<%-- <s:select list="#{'请选择':'请选择','2017':'2017','2018':'2018','2019':'2019'}"  name = "year" id = "year" cssClass="select-option"></s:select> --%>
                    	<input type="text" style="height:25px" name="year" id="year" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy'})" value="${year}">
                    </div>
                </div>
            </div>
        	
            <div class="col thistop">
                <div class="control-group">
                    <label class="control-label">
                        <span style="padding: 0 6px;">经办处:</span>
                    </label>
                    <div>
                     	<s:select list="#{'请选择':'请选择','经济法规处':'经济法规处','城建法规处':'城建法规处','社会法规处':'社会法规处'}" name="teamName" id="teamName" cssClass="select-option thisoption"></s:select>
                    </div>
                </div>
            </div>
            
            <div class="col">
		                <div class="control-group">
		                    <label class="control-label">
		                        <span style="padding: 0 6px;">汇总时间：</span>
		                    </label>
		                    <div >
		                        <input type="text" name="processStartTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='processStartTime' format='yyyy-MM-dd'/>">&nbsp;-
		                        <input type="text" name="processEndTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='processEndTime' format='yyyy-MM-dd'/>">
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
            </form>
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
                                    <th>经办处</th>
                                    <th>年份</th>
                                    <th>汇总时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                 <s:set name="rownum" value="(pageNo-1)*pageSize+1"></s:set>
                   				 <s:iterator value="pageModel.result" id="task">
                               	<tr>
			                        <td><s:property value="rownum"/></td>
			                        <td><s:property value="teamName"/></td>
			                        <td><s:property value="year" /></td>
			                        <td><s:date name="lastTime" format="yyyy-MM-dd HH:mm:ss" /></td>
			                        <td><a class = "tianbao caozuo-btn" onclick="openSummaryItems('<s:property value="summaryId" />&op=view')">查看</a></th>
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
        </div>
    </div>
</div>
 
 
<script> 
  function openSummaryItems(summaryId) {
            layer.open({
                type: 2,
                title: '',
                shade: false,
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['850px', '420px'],
                content: '${basePath}/legislate/planSummary_items.do?summaryId='+summaryId,
            });
        }
</script>
</div>
</body>
</html>
