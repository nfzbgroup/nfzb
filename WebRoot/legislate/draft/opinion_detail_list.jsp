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
    <title>公开社会意见-详情页</title>
    <link rel = "stylesheet" type = "text/css" href = "css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "css/sui-themes-green-append.css" media = "screen"/>
    <script src = "js/jquery.js"></script>
    <script src = "js/sui.js"></script>
    <script src = "js/tanchuang.js"></script>
    <script src = "${basePath}/legislate/js/layer/layer.js"></script>
</head>
<body class = "body_bg_1">
<!--面包屑-->
<div class = "conteng">
    <div class = "conteng-title">
        <span>当前位置：</span><em>公开社会意见-详情列表页</em>
    </div>
    <div class = "detai" style = "margin-bottom: 40px">
        <div class = "detai-con">
            <!--内容区-->
               <form class = "form-horizontal" role = "form" action="${basePath}/legislate/opinion_detail_list.do?">
               <input type="hidden" value="${draftId }" name ="draftId">
               <input type="hidden" value="${source }" name="source">
                <div class="clearfix">
                    <div class="container-fluid container-top">
                        <div class="row rh">
                            <label class="formlabel">提议人:</label>
                            <span class="formdiv fbw">
                                <input type = text" name="name" id="name" value="${name }">
                            </span>
                           <%--  <label class="formlabel">网站类型:</label>
                            <span class="formdiv fbw">
                                <select name = "source" id = "source">
                                   <option value = "电子邮件" <s:if test='source == "电子邮件"'>selected="selected"</s:if>>电子邮件</option>
                                <option value = "网上民意" <s:if test='source == "网上民意"'>selected="selected"</s:if>>网上民意</option>
                                <option value = "信函" <s:if test='source == "信函"'>selected="selected"</s:if>>信函</option>
                                <option value = "电话" <s:if test='source == "电话"'>selected="selected"</s:if>>电话</option>
                                </select>
                            </span> --%>
                        </div>
                    </div>
                </div>
                <div class="clearfix">
                    <div class="container-fluid container-top">
                        <div class="row rh">
                            <div class="col-sm-12 text-center">
                                <button type="submit" class="btn btn-info btn-bg btn-120">查询</button>
                                <button type="button" class="btn btn-info btn-bg btn-120" onclick="window.location.href='${basePath}/legislate/opinion_detail_save.do?outId=${outId }'">添加</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class = "tab-list <s:if test='source == "电子邮件"'>tab-back</s:if>" onclick="tab('电子邮件')">电子邮件</div>
            <div class = "tab-list <s:if test='source == "网上民意"'>tab-back</s:if>" onclick="tab('网上民意')">网上民意</div>
            <div class = "tab-list <s:if test='source == "信函"'>tab-back</s:if>"		onclick="tab('信函')">信函</div>
            <div class = "tab-list <s:if test='source == "电话"'>tab-back</s:if>" 	onclick="tab('电话')">电话</div>
            <div class = "clearfix">
            <div class = "container-fluid container-top">
                <div class = "row">
                    <div class = "col-sm-12">
                        <div class = "biaodan">
                            <table class = "table1">
                                <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>提议人</th>
                                    <th>电话</th>
                                    <th>意见内容</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                  <s:set name="rownum" value="(pageNo-1)*pageSize+1"></s:set>
                  				  <s:iterator value="pageModel.result" id="task">
                                <tr>
                                    <td><s:property value="rownum"/></td>
                                    <td><s:property value="name"/></td>
                                    <td><s:property value="phone"/></td>
                                    <td><s:property value="content"/></td>
                                    <td>
                                        <a class = "tianbao caozuo-btn" href="${basePath}/legislate/opinion_detail_view.do?detailId=${detailId }">查看</a>
                                        <a class = "tianbao caozuo-btn" onclick="deleteopinion('${detailId}')">删除</a>
                                    </td>
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
		                <form action="" name="form1">
                	<%@include file="/platform/include/pagelinks.jsp" %>
                </form>
		                </div>
                            <div class="clearfix">
			                    <div class="container-fluid container-top">
			                        <div class="row rh">
			                            <div class="col-sm-12 text-center">
			                                <button type="button" class="btn btn-info btn-bg btn-120"onclick="parent.location.reload();">关闭</button>
			                            </div>
			                        </span>
			                        </div>
			                    </div>
                			</div>
                			<br/>
                        </div> 
                    </div>
                </div>
            </div>
    </div>
 
 
<script> 
var uuu;
$(function () {
	uuu= window.location.href;
	

    $('.tab-list').click(function () {
        var index = $(this).index();
        $(this).css({'background':'#288ce2','color':'#fff','font-size':'16px'}).siblings('.tab-list').css({'background':'#ececec','color':'#969696','font-size':'14px'})
        $('.biaodan .tab-content').eq(index).show().siblings('.tab-content').hide()
    })

});
function deleteopinion(detailId){
	var r=confirm("是否确定要删除");
	if(r){
	 	$.ajax({
	 		"type":"POST",
	 		"dataType":"json",
	 		"url":"/fzb_platform/legislate/deleteopinion.do",
	 		"data":{"detailId":detailId},
	 		"success":function(data){
	 			if(data.code == 200){
	 				alert(data.message);
	 				window.location.href(uuu);
	 			}
	 		}
	 	}) 
	} 	
}
	
	function tab(source){
		window.location.href("${basePath}/legislate/opinion_detail_list.do?draftId=${draftId}&source="+encodeURIComponent(source));
		
	}
    $(function () {
        $('#togs').change(function () {  
            var val = $(this).val();
            if(val=='0'){
                $('#tp').show().siblings('#fl').hide()
            }else{
                $('#tp').hide().siblings('#fl').show()
            }
        });
        $('input[type="file"]').change(function (e) {
            var filename = $(this).val().split("\\").pop();
            $('.file-text').text(filename)
        });  
    });
 
</script>
</div>
</body>
</html>

