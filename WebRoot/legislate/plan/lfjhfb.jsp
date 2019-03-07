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
    <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath }/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${pageContext.request.contextPath }/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${pageContext.request.contextPath }/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${pageContext.request.contextPath }/legislate/js/jquery.js"></script>
    <script src = "${pageContext.request.contextPath }/legislate/js/sui.js"></script>
    <script src = "${pageContext.request.contextPath }/legislate/js/tanchuang.js"></script>
    <script src = "${basePath }/legislate/js/layer/layer.js"></script>
    <script src = "${basePath}/resources/components/My97DatePicker/WdatePicker.js"></script>
</head>
<body class = "body_bg_1">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->
    <div class = "conteng-title">
        <span>当前位置：</span><em>立法计划分办列表-首页</em>
    </div>

	<form class = "form-horizontal" action="${basePath}/legislate/plan_serach_lfjhfb.do" method="post">
    <input type="hidden" name="state" id="state" value="${state }">
    
                <div class="detai-con">
		            <div class="col">
		                <div class="control-group">
                            <label class="control-label">
                            	<span style="padding: 0 6px;">计划名称:</span>
                            </label>
                            <div>
                                 <%--  <input name="planName" id = "planName" type = text" value="${planName}"> --%>
                                 <s:textfield name="planName" value="%{planName}" id = "planName"/>
                            </div>
 <%--                            <label class="control-label">
                            	<span style="padding: 0 6px;">发起人:</span>
                            </label>
                            <div>
                                <input name="creatorName" id = "creatorName" type = text" value="${creatorName }">
                                <s:textfield name="creatorName" value="%{creatorName}" id = "creatorName"/>
                            </div> --%>
                        </div>
                    </div>
                    
	                <s:if test='state=="dise"'>
	                <div class="col">
	                <div class="control-group">
	                <label class="control-label">
	                        <span style="padding: 0 6px;">处理环节：</span>
	                </label>
	                        <div >
	                                <s:select list="#{'请选择':'请选择','dise':'未认领','process':'办理中','summary':'已完成'}" name="status" cssClass="input-xlarge"></s:select>
	                        </div>
	                </div>
	                </div>
	                </s:if>
	                
	                <div class="col">
		                <div class="control-group">
                            <label class="control-label">
                            	<span style="padding: 0 6px;">发起人:</span>
                            </label>
                            <div>
                                <s:textfield name="creatorName" value="%{creatorName}" id = "creatorName"/>
                            </div>
                        </div>
                    </div>
                </div>
                
                <div class="detai-con">
		            <div class="col">
		                <div class="control-group">
		                    <label class="control-label">
		                        <span style="padding: 0 6px;">发起时间:</span>
		                    </label>
		                    <div >
		                        <input type="text" name="startTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='startTime' format='yyyy-MM-dd'/>">&nbsp;-
		                        <input type="text" name="endTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='endTime' format='yyyy-MM-dd'/>">
		                    </div>
		                </div>
		            </div>
		        </div>
		        
		        <s:if test='state=="dise"'>
		        <div class="detai-con" style="position:relative">
		        <!-- <a javascript="void(0);" class="sui-btn btn-primary01 btn-lg btn-margin thisbtn" id="search" >查询</a> -->
		            <div class="col">
		                <div class="control-group">
		                    <label class="control-label">
		                        <span style="padding: 0 6px;">处理时间：</span>
		                    </label>
		                    <div >
		                        <input type="text" name="processStartTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='processStartTime' format='yyyy-MM-dd'/>">&nbsp;-
		                        <input type="text" name="processEndTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='processEndTime' format='yyyy-MM-dd'/>">
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
                
    </form> 
	<input type="hidden" id="action" value=""/>
    <!--内容区-->
    <div class = "detai">
        <div class = "detai-con">
            <div class = "tab-list <s:if test='state == "unreceive"'>tab-back</s:if>" onclick="window.location.href('${basePath}/legislate/plan_unreceive_list.do')">未接收</div>
            <div class = "tab-list <s:if test='state == "undise"'>tab-back</s:if>" onclick="window.location.href('${basePath}/legislate/plan_undise_list.do')">已接收</div>
            <div class = "tab-list <s:if test='state == "dise"'>tab-back</s:if>" onclick="window.location.href('${basePath}/legislate/plan_dise_list.do')">已分办</div>
        <div class = "biaodan">
            <div class = "tab-content" style = "display: block">
                <table class = "table1">
                    <thead>
                    <tr>
                        <th>计划编号</th>
                        <th>立法计划名称</th>
                        <th>处理环节</th>
                        <th>发起人</th>
                        <th>发起时间</th>
                        <s:if test="state=='dise'">
	                        <th>处理时间</th>
                        </s:if>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <s:set name="rownum" value="(pageNo-1)*pageSize+1"></s:set>
                    <s:iterator value="pageModel.result" id="task">
                    <s:if test="state == 'unreceive'">
                    <tr>
                        <td><s:property value="rownum"/></td>
                        <td><s:property value="plan.planName" /></td>
                        <td>未接收</td>
                        <td><s:property value="plan.creatorName"/></td>
                        <td><s:date name="plan.createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                        <td nowrap="">
                            <a class = "tianbao caozuo-btn" onclick="chakan('<s:property value="plan.planId" />&op=view')">查看</a>
                            <a class = "chakan caozuo-btn layer-jieshou" id = "fenban"  onclick="jieshou('<s:property value="taskId" />')">接收</a>
                        </td>
                    </tr>
                    </s:if>
                    <s:if test="state == 'undise'">
                    <tr>
                        <td><s:property value="rownum"/></td>
                        <td><s:property value="plan.planName" /></td>
                        <td>待分办</td>
                        <td><s:property value="plan.creatorName"/></td>
                        <td><s:date name="plan.createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                        <td nowrap="">
                            <a class = "tianbao caozuo-btn" onclick="chakan('<s:property value="plan.planId" />&op=view')">查看</a>
                            <a class = "chakan caozuo-btn layer-fenban" onclick="fenban('<s:property value="taskId"/>')" id = "fenban">分办</a>
                        </td>
                    </tr>
                    </s:if>
                     <s:if test="state == 'dise'">
                    <tr>
                        <td><s:property value="rownum"/></td>
                        <td><s:property value="plan.planName" /></td>
                        <td>
                        	<s:if test="plan.status =='dise'">
		                        		未认领
		                    </s:if>
		                    <s:if test="plan.status =='process'">
		                        		办理中
		                    </s:if>
		                    <s:if test="plan.status =='summary'">
		                        		已完成
		                    </s:if>
                        </td>
                        <td><s:property value="plan.creatorName"/></td>
                        <td><s:date name="plan.createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                        <td><s:date name="processTime"   format="yyyy-MM-dd HH:mm:ss"/></td>
                        <td nowrap="">
                            <a class = "tianbao caozuo-btn" onclick="chakan('<s:property value="plan.planId" />&op=view')">查看</a>
                        </td>
                    </tr>
                    </s:if>
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
            <div class = "clearfix"></div>
    </div>

    </div>

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
    
    
    function jieshou(taskId){
    	var action=window.location.href;
		if(confirm("确定要接收吗？")){
   	 	$.ajax({
   	 		"type":"POST",
   	 		"dataType":"json",
   	 		"url":"${basePath}/legislate/plan_receive.do",
   	 		"data":{"taskId":taskId},
   	 		"success":function(data){
   	 			if(data.code==200){
   	 				alert(data.message);
   	 				window.location.href(action);
   	 			}
   	 		}
   	 	}) 
       }
	}
    function fenban(taskId){
        layer.open({
            type: 2,
            title: '',
            shade: false,
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: ['850px', '620px'],
            content: '${basePath}/legislate/plan_dise.do?taskId='+taskId,
        });
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
