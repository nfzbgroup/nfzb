<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@page import="java.util.Map"%>
<%@page import="com.wonders.fzb.legislate.beans.FileRecord"%>
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
    <title>公开社会意见</title>
    <link rel = "stylesheet" type = "text/css" href = "css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "css/sui-themes-green-append.css" media = "screen"/>
   
    <script src = "js/jquery.js"></script>
    <script src = "${basePath}/resources/components/My97DatePicker/WdatePicker.js"></script>
    <script src = "js/sui.js"></script>
    <script src = "js/tanchuang.js"></script>
    <script src = "js/layer/layer.js"></script>
</head>
<body class = "body_bg_1">
<!--面包屑-->
<div class = "conteng">
    <div class = "conteng-title">
        <span>当前位置：</span><em>公开社会意见-首页</em>
    </div>
    <div class = "detai" style = "margin-bottom: 40px">
        <div class = "detai-con">
            <!--内容区-->
            <form class = "form-horizontal" role = "form" name="form1" method="POST" action="${basePath}/legislate/public_opinion_list.do">
<%--                     <div class="container-fluid container-top">
                        <div class="row rh">
                            <label class="formlabel">征求开始时间:</label>
                            <span class="formdiv fbw">
                                <input name="startTime" id = "startTime" type = text" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='startTime' format='yyyy-MM-dd' />">
                            </span>
                            <span class="formdiv fbw">
                                <input name="endTime" id = "endTime" type = text" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})"  value="<s:date name='endTime' format='yyyy-MM-dd' />">
                            </span>
                        </div>
                    </div>
                    
            <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                            <label class="formlabel">草案名称:</label>
                            <span class="formdiv">
                                <input type = text" name="draftName" value="${param.draftName }">
                            </span>
                        <span class="formbuttom">
                            <button type="submit" class="btn btn-info btn-bg btn-120">查询</button>
                        </span>
                    </div>
                </div>
            </div> --%>
 
       <div class="detai-con">
            <div class="col">
                <div class="control-group">
                    <label class="control-label">
                        <span style="padding: 0 6px;">草案名称：</span>
                    </label>
                    <div >
                         <s:textfield name="draftName" value="%{draftName}" id = "draftName"/>
                    </div>
                </div>
            </div>
            
            <div class="col">
                <div class="control-group">
                    <label class="control-label">
                        <span style="padding: 0 6px;">经办处：</span>
                    </label>
                    <div >
                         <s:select list="#{'请选择':'请选择','经济法规处':'经济法规处','城建法规处':'城建法规处','社会法规处':'社会法规处'}" name="teamName" id="teamName" cssClass="select-option thisoption"></s:select>
                    </div>
                </div>
            </div>
        </div>
                
                <div class="detai-con">
		            <div class="col">
		                <div class="control-group">
		                    <label class="control-label">
		                        <span style="padding: 0 6px;">发起时间：</span>
		                    </label>
		                    <div >
		                        <input type="text" name="createstartTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='createstartTime' format='yyyy-MM-dd'/>">&nbsp;-
		                        <input type="text" name="createendTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='createendTime' format='yyyy-MM-dd'/>">
		                    </div>
		                </div>
		            </div>
		        </div>
		        
		        <div class="detai-con" style="position:relative">
		            <div class="col">
		                <div class="control-group">
		                    <label class="control-label">
		                        <span style="padding: 0 6px;">征求开始时间：</span>
		                    </label>
		                    <div >
		                        <input type="text" name="startTimeS" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='startTimeS' format='yyyy-MM-dd'/>">&nbsp;-
		                        <input type="text" name="startTimeE" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='startTimeE' format='yyyy-MM-dd'/>">
		                    </div>
		                </div>
		            </div>
		        </div>
		        
		        <div class="detai-con" style="position:relative">
		            <div class="col">
		                <div class="control-group">
		                    <label class="control-label">
		                        <span style="padding: 0 6px;">反馈截止时间：</span>
		                    </label>
		                    <div >
		                        <input type="text" name="endTimeS" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='endTimeS' format='yyyy-MM-dd'/>">&nbsp;-
		                        <input type="text" name="endTimeE" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='endTimeE' format='yyyy-MM-dd'/>">
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
            
            <div class = "clearfix">
            <div class = "container-fluid container-top">
                <div class = "row">
                    <div class = "col-sm-12">
                        <div class = "biaodan">
                            <table class = "table1">
                                <thead>
                                <tr>
                                    <th>编号</th>
                                    <th>对应草案</th>
                                    <th>经办处</th>
                                    <th>发起时间</th>
                                    <th>征求开始时间</th>
                                    <th>反馈截止时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <s:set name="rownum" value="(pageNo-1)*pageSize+1"></s:set>
                    			<s:iterator value="pageModel.result" id="task">
	                                <tr>
	                                    <td><s:property value="rownum"/></td>
                        				<td><s:property value="draft.draftName" /></td>
	                                    <td><s:property value="teamName" /></td>
	                                    <td><s:date name="createTime" format="yyyy-MM-dd"/></td>
	                                    <td><s:date name="startTime" format="yyyy-MM-dd"/></td>
	                                    <td><s:date name="endTime" format="yyyy-MM-dd"/></td>
	                                    <td>
	                                        <a href="${basePath}/legislate/modifyPublicOption.do?opinionId=<s:property value="opinionId"/>" class = "tianbao caozuo-btn">编辑</a>
	                                        <a onclick="yijian('<s:property value="opinionId"/>')" class = "tianbao caozuo-btn btn-70">意见详情</a>
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
                        </div>
                    </div>
                </div>
            </div>
    		</form>
    </div>
</div>
 
 
<script> 

function callBack(){
	window.location.href(window.location.href);
}
    $(function () {
    	uuu= window.location.href;
        $('.close-btn').click(function () {
 
        });
        
    });
    
    function yijian(outId){
        layer.open({
            type: 2,
            title: '',
            shade: false,
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: ['850px', '620px'],
            content: '${basePath}/legislate/opinion_detail_save.do?updateop=update&outId='+outId,
        });
   }
</script>
</div>
</body>
</html>
