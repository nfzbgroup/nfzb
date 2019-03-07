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
    <title>审核会议列表</title>
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
        <span>当前位置：</span><em>审核会议-首页</em>
    </div>

    <!--内容区-->
            <form class = "form-horizontal"  name="form1" action="${basePath}/legislate/auditMeeting_list.do" method="post" role = "form">
            <div class="clearfix">
                <div class="container-fluid container-top">
                
  <%--                   <div class="row rh">
                            <label class="formlabel">名称:</label>
                            <s:textfield name="meetingTitle" value="%{meetingTitle}" />
                            <span>
                         	        开始时间：<input type="text" name="startTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='startTime' format='yyyy-MM-dd'/>">
                            </span>
                            <span>
                                   	   结束时间：<input type="text" name="endTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='endTime' format='yyyy-MM-dd'/>">
                            </span>
                    </div> --%>
                    
                    
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
                        <span style="padding: 0 6px;">会议名称：</span>
                </label>
                        <div >
                                <s:textfield name="meetingTitle" value="%{meetingTitle}" id = "meetingTitle"/>
                        </div>
                </div>
                </div>
                </div>
                
		        <div class="detai-con">
		            <div class="col">
		                <div class="control-group">
		                    <label class="control-label">
		                        <span style="padding: 0 6px;">会议时间：</span>
		                    </label>
		                    <div >
		                        <input type="text" name="startTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='startTime' format='yyyy-MM-dd'/>">-
		                        <input type="text" name="endTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='endTime' format='yyyy-MM-dd'/>">
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
                     <div class="row rh " style="width: 100%;float: right;">
                            <button type="button" class="btn btn-info btn-bg btn-120" onclick = "window.location.href='${basePath}/legislate/saveAuditMeeting.do'">添加</button>
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
                                    <th>会议名称</th>
                                    <th>会议时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                <s:set name="rownum" value="(pageNo-1)*pageSize+1"></s:set>
                                 <s:iterator value="pageModel.result" id="task">
				                    
				                    <tr>
				                    	 <td><s:property value="rownum"/></td>
				                        <td><s:property value="draft.draftName"/></td>
				                        <td><s:property value="meetingTitle" /></td>
				                        <td><s:date name="meetingTime" format="yyyy-MM-dd" /></td>
				                        <td nowrap="">
				                            <a class = "tianbao caozuo-btn" onclick="window.location.href='${basePath}/legislate/saveAuditMeeting.do?auditMeetingId=<s:property value="auditMeetingId" />'">编辑 </a>
				                            <a class = "chakan caozuo-btn"  onclick="window.location.href='${basePath}/legislate/deleteAuditMeeting.do?auditMeetingId=<s:property value="auditMeetingId" />'">删除</a>
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
        })

    });
    function renling(DraftId){
		 if(confirm("确定要认领吗？")){
  	 	$.ajax({
  	 		"type":"POST",
  	 		"dataType":"json",
  	 		"url":"${basePath}/legislate/draft_claim.do",
  	 		"data":{"draftId":DraftId},
  	 		"success":function(data){
  	 			if(data.code==200){
  	 				alert(data.message);
  	 				location.reload();
  	 				window.location.href='${basePath}/legislate/auditMeeting_list.do';
  	 			}
  	 		}
  	 	}) 
      }
	}
    function banli(DraftId){
    	layer.open({
            type: 2,
            title: '',
            shade: false,
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: ['850px', '620px'],
            content: '${basePath}/legislate/gzcaqcbl.do?draftId='+DraftId+'&t='+new Date(),
//            success: function(layero, index){
//                var banlibtn = layer.getChildFrame('#banlibtn', index);
//                banlibtn.click(function () {
//                    window.location.href='http://www.baidu.com'
//                })
//            }
        });
	}
</script>
</body>
</html>