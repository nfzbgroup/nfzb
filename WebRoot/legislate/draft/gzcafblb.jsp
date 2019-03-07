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
        <span>当前位置：</span><em>规章草案分办列表-首页</em>
    </div>
<form class = "form-horizontal" action="${basePath}/legislate/draft_serach_gzcafblb.do" method="post" role = "form">
    <input type="hidden" name="state" id="state" value="${state }">
    
<%--                 <div class="clearfix">
                    <div class="container-fluid container-top">
                        <div class="row rh">
                            <label class="formlabel">草案名称:</label>
                            <span class="formdiv fbw">
                                <input name="draftName" id = "draftName" type = text" value="${draftName }">
                            </span>
                            <label class="formlabel">发起人:</label>
                            <span class="formdiv fbw">
                                <input name="creatorName" id = "creatorName" type = text" value="${creatorName }">
                            </span>
                            <label class="formlabel">上报类型:</label>
                            <span class="formdiv fbw">
                                <select name = "planType" id = "planType">
                                    <option value = "修订" <s:if test="planType=='修订'">selected="selected"</s:if>>修订</option>
                                    <option value = "制定" <s:if test="planType=='制定'">selected="selected"</s:if>>制定</option>
                                    <option value = "废止" <s:if test="planType=='废止'">selected="selected"</s:if>>废止</option>
                                </select>
                            </span>
                        </div>
                    </div>
                </div>
				<div class="clearfix">
                    <div class="container-fluid container-top">
                        <div class="row rh">
                            <label class="formlabel">发起时间:</label>
                            <span class="formdiv fbw">
                                <input name="createstartTime" id = "createstartTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" type = text" value="<s:date name='createstartTime' format='yyyy-MM-dd' />">
                            </span>
                            <span class="formdiv fbw">
                                <input name="createendTime" id = "createendTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" type = text" value="<s:date name='createendTime' format='yyyy-MM-dd' />">
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
            
            	   	<s:if test='state=="dise"'>
	                <div class="col">
	                <div class="control-group">
	                <label class="control-label">
	                        <span style="padding: 0 6px;">处理环节：</span>
	                </label>
	                        <div >
	                                <s:select list="#{'请选择':'请选择','dise':'未认领','claim':'办理中'}" name="status" cssClass="input-xlarge"></s:select>
	                        </div>
	                </div>
	                </div>
	                </s:if>
	                
            <div class="col">
                <div class="control-group">
                    <label class="control-label">
                        <span style="padding: 0 6px;">发起人：</span>
                    </label>
                    <div >
                         <s:textfield name="creatorName" value="%{creatorName}" id = "creatorName"/>
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
    <!--内容区-->
    <div class = "detai">
        <div class = "detai-con">
            <div class = "tab-list <s:if test='state == "unreceive"'>tab-back</s:if>" onclick="window.location.href('${basePath}/legislate/draft_unreceive_list.do?state=unreceive')">未接收</div>
            <div class = "tab-list <s:if test='state == "undise"'>tab-back</s:if>" onclick="window.location.href('${basePath}/legislate/draft_undise_list.do?state=undise')">已接收</div>
            <div class = "tab-list <s:if test='state == "dise"'>tab-back</s:if>" onclick="window.location.href('${basePath}/legislate/draft_dise_list.do?state=dise')">已分办</div>
        <div class = "biaodan">
            <div class = "tab-content" style = "display: block">
                <table class = "table1">
                    <thead>
                    <tr>
                        <th>草案编号</th>
                        <th>规章草案起草名称</th>
                        <th>处理环节</th>
                        <th>发起人</th>
                        <th>发起时间</th>
                        <s:if test="state == 'dise'">
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
                        <td><s:property value="draft.draftName" /></td>
                        <td>综合处-未接收</td>
                        <td><s:property value="draft.creatorName"/></td>
                        <td><s:date name="draft.createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                        <td nowrap="">
                            <a class = "tianbao caozuo-btn" onclick="chakan('<s:property value="taskId" />&op=view')">查看</a>
                            <a class = "chakan caozuo-btn layer-jieshou" id = "fenban"  onclick="jieshou('<s:property value="taskId" />')">接收</a>
                        </td>
                    </tr>
                    </s:if>
                    <s:if test="state == 'undise'">
                    <tr>
                        <td><s:property value="rownum"/></td>
                        <td><s:property value="draft.draftName" /></td>
                        <td>综合处-待分办</td>
                        <td><s:property value="draft.creatorName"/></td>
                        <td><s:date name="draft.createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                        <td nowrap="">
                            <a class = "tianbao caozuo-btn" onclick="chakan('<s:property value="taskId" />&op=view')">查看</a>
                            <a class = "chakan caozuo-btn layer-fenban" onclick="fenban('<s:property value="taskId"/>')" id = "fenban">分办</a>
                        </td>
                    </tr>
                    </s:if>
                     <s:if test="state == 'dise'">
                    <tr>
                        <td><s:property value="rownum"/></td>
                        <td><s:property value="draft.draftName" /></td>
                        <td>
                        	<s:if test="draft.status =='dise'">
		                        		立法处-未认领
		                    </s:if>
		                    <s:if test="draft.status =='claim'">
		                        		立法处-办理中
		                    </s:if>
                        </td>
                        <td><s:property value="draft.creatorName"/></td>
                        <td><s:date name="draft.createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                        <td><s:date name="processTime"   format="yyyy-MM-dd HH:mm:ss"/></td>
                        <td nowrap="">
                            <a class = "tianbao caozuo-btn" onclick="chakan('<s:property value="taskId" />&op=view')">查看</a>
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
var uuu;
    $(function () {
        
      
    	uuu= window.location.href;
        $('.tab-list').click(function () {
            var index = $(this).index();
            $(this).css({'background':'#288ce2','color':'#fff','font-size':'16px'}).siblings('.tab-list').css({'background':'#ececec','color':'#969696','font-size':'14px'})
            $('.biaodan .tab-content').eq(index).show().siblings('.tab-content').hide()
        })

    });
    
    function jieshou(taskId){
		 if(confirm("确定要接收吗？")){
   	 	$.ajax({
   	 		"type":"POST",
   	 		"dataType":"json",
   	 		"url":"${basePath}/legislate/draft_receive.do",
   	 		"data":{"taskId":taskId},
   	 		"success":function(data){
   	 			alert(data.message);
   	 			if(data.code==200){
   	 			window.location.href(uuu);
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
            content: '${basePath}/legislate/draft_dise.do?taskId='+taskId,
        });
   }
    function chakan(taskId){
    	layer.open({
            type: 2,
            title: '',
            shade: false,
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: ['850px', '620px'],
            content: '${basePath}/legislate/gzcaqcbl.do?taskId='+taskId+'&t='+new Date(),
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
