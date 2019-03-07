<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>


<!doctype html>
<html lang = "zh"> 
<head>
    <meta http-equiv = "X-UA-Compatible" content = "IE=edge,Chrome=1"/>
    <meta http-equiv = "Content-Type" content = "text/html; charset=utf-8"/>
    <meta http-equiv = "Pragma" content = "no-cache"/>
    <meta http-equiv = "Cache-Control" content = "no-cache"/>
    <meta name = "keywords" content = "OA系统"/>
    <meta name = "keywords" content = ""/>
    <meta name = "description" content = ""/>
    <meta name = "generator" content = "Notepad++"/>
    <title>立法后评估报告查看</title>
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${basePath}/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${basePath}/legislate/js/jquery.js"></script>
    <script src = "${basePath}/legislate/js/sui.js"></script>
    <script src = "${basePath}/legislate/js/tanchuang.js"></script>
    <script src = "${basePath}/legislate/js/layer/layer.js"></script>
 	<script src = "${basePath}/resources/components/My97DatePicker/WdatePicker.js"></script>
</head>
<body class = "body_bg_1">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->
    <div class = "conteng-title">
        <span>当前位置：</span><em>立法后评估报告列表-首页</em>
    </div>
	<form class = "form-horizontal" action="${basePath}/legislate/assessment_report_list.do" method="post" role = "form">   
          <div class="col">
	         <div class="control-group">
		         <label class="control-label">
		         	<span style="padding: 0 6px;">查询类型：</span>
		         </label>
                 <div >
                    <s:select list="#{'assessmentTitle':'报告名称','agentUnitName':'上报单位'}" name="queryType" cssClass="input-xlarge"></s:select>
                 </div>
                 <label  class="control-label">
                 	<span style="padding: 0 6px;">请输入查询内容：</span>
                 </label> 
				 <input name="queryValue" value="<s:property value='#request.queryValue' />" type="text"	style="border: 1px solid #006699; position: relative; top: -3px" size="20" />
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
	      <input type="hidden" name ="receive" value="<s:property value='#request.receive' />"> 
    <!--内容区-->
    <div class = "detai">
        <div class = "detai-con">
            <div id="init" class = "tab-list <s:if test='#request.flag == "EDIT"'>tab-back</s:if> " onclick="window.location.href='${basePath}/legislate/assessment_reportUp_list.do?flag=EDIT'">编辑中</div>
            <div id="send" class = "tab-list <s:if test='#request.flag == "REPORT"'>tab-back</s:if>"  onclick="window.location.href='${basePath}/legislate/assessment_reportUp_list.do?flag=REPORT'">已上报</div>
            <div class = "flright" onclick = "window.location.href='${basePath}/legislate/assessment_report_add.do'">报告报送</div>
        <div class = "biaodan">
            <div class = "tab-content" style = "display: block">
                <table class = "table1">
                    <thead>
                    <tr>
                        <th>序号</th>
                        <th>名称</th>
                        <th>年份</th>
                        <th>报送单位</th>
                        <th>报送人</th>
                        <th>报送时间</th>
                        <th>接收时间</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
	                   <s:iterator value="#request.retPage.result" id="info" status="number">
	                 	<tr>
	                        <td><s:property value="#number.index+1"/></td>
	                        <td><s:property value="#info.assessmentTitle" /></td>
	                        <td><s:property value="#info.year"/></td>
	                        <td><s:property value="#info.agentUnitName"/></td>
	                        <td><s:property value="#info.agentName"/></td>
	                        <td><s:property value="#info.insertDate" /></td>
	                        <td><s:property value="#info.receiveDate" /></td>
	                        <td>
	                          
		                       <a class = "tianbao caozuo-btn" onclick="assessmentDetail('<s:property value="#info.assessmentId" />')">查看</a>
		                       <s:if test='#request.flag == "EDIT"'>
		                      	   <a class = "tianbao caozuo-btn layer-fenban" onclick="assessmentModify('<s:property value="#info.assessmentId" />')">修改</a>
		                      	   <a class = "tianbao caozuo-btn" onclick="assessmentUpReport('<s:property value="#info.assessmentId" />')">报送</a>
	                      	   </s:if>
	                        </td>
	                    </tr>
	                   </s:iterator>
                    </tbody>
                </table>
                <div class = "contentBox-paging">
                <%
	                Page retPage= (Page)request.getAttribute("retPage");
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
        <div class = "clearfix"></div>
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
    function assessmentDetail(assessmentId){
    	window.location.href = '${basePath}/legislate/assessment_report_detail.do?assessmentId='+assessmentId;
    }
    function assessmentModify(assessmentId){
    	window.location.href = '${basePath}/legislate/assessment_report_add.do?assessmentId='+assessmentId;
    }
    function assessmentUpReport(assessmentId){
    	$.ajax({
    		url:'${basePath}/legislate/assessmentUpReport.do',
    		data:'assessmentId='+assessmentId,
    		type:'post',
    		async:false,
    		success:function(msg){
    			if(msg=='true'){
    				alert('已报送市法制办。');
    			}else{
    				alert('报送失败，请联系管理员。');
    			}
    		}
    	});
       	window.location.reload();
    }
    function assessmentReportDis(assessmentId,unitId){
    	$.ajax({
    		url:'${basePath}/legislate/assessment_report_dis.do',
    		data:'assessmentId='+assessmentId+'&unitId='+unitId,
    		type:'post',
    		async:false,
    		success:function(msg){
    			if(msg=='true'){
    				alert('已分送成功。');
    			}else{
    				alert(msg);
    			}
    		}
    	});
       	window.location.reload();
    }
    
    function assessmentReportBack(assessmentId,unitId){
    	$.ajax({
    		url:'${basePath}/legislate/assessmentReportBack.do',
    		data:'assessmentId='+assessmentId,
    		type:'post',
    		async:false,
    		success:function(msg){
    			if(msg=='true'){
    				alert('已退回到上报单位。');
    			}else{
    				alert('退回失败，请联系管理员。');
    			}
    		}
    	});
       	window.location.reload();
    }
</script>
</body>
</html>
