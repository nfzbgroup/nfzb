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
    <title>立法计划审核</title>
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
        <span>当前位置：</span><em>立法计划审核列表-首页</em>
    </div>
    <!--内容区-->
    <form class = "form-horizontal" action="${basePath}/legislate/plan_serach_lfjhsh.do" method="post" role = "form" name="plansh">
    <input type="hidden" name="state" id="state" value="${state }">
<%--                 <div class="clearfix">
                    <div class="container-fluid container-top">
                        <div class="row rh">
                            <label class="formlabel">计划名称:</label>
                            <span class="formdiv fbw">
                                <input name="planName" id = "planName" type = text" value="${planName }">
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
                </div> --%>
                
        <div class="detai-con">
        
        <s:if test="state == 'summary_list'">
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
        </s:if>
        
        <s:if test="state == 'summary'">
           	<div class="col">
                <div class="control-group">
                    <label class="control-label">
                        <span style="padding: 0 6px;">起草部门：</span>
                    </label>
                    <div >
                         <s:textfield name="teamName" value="%{teamName}" id = "teamName"/>
                    </div>
                </div>
            </div>
            
            <div class="col">
                <div class="control-group">
                    <label class="control-label">
                        <span style="padding: 0 6px;">立/改/废：</span>
                    </label>
                    <div >
                         <s:select list="#{'请选择':'请选择','修订':'修订','制定':'制定','废止':'废止'}" name="planType" id="planType" cssClass="input-xlarge"></s:select>
                    </div>
                </div>
            </div>
            
            <div class="col">
                <div class="control-group">
                    <label class="control-label">
                        <span style="padding: 0 6px;">规章名称：</span>
                    </label>
                    <div >
                         <s:textfield name="planName" value="%{planName}" id = "planName"/>
                    </div>
                </div>
            </div>
        </s:if>
        
        <s:if test="state=='summary' || state=='summary_list'">
        </s:if>
        <s:else>
         	<div class="col">
                <div class="control-group">
                    <label class="control-label">
                        <span style="padding: 0 6px;">计划名称：</span>
                    </label>
                    <div >
                         <s:textfield name="planName" value="%{planName}" id = "planName"/>
                    </div>
                </div>
            </div>
            
            <div class="col">
                <div class="control-group">
                    <label class="control-label">
                        <span style="padding: 0 6px;">上报类型：</span>
                    </label>
                    <div>
                                <s:select list="#{'请选择':'请选择','修订':'修订','制定':'制定','废止':'废止'}" name="planType" id="planType" cssClass="input-xlarge"></s:select>
                    </div>
                </div>
            </div>
        </s:else>
          
            <s:if test="state=='unaudit' || state=='audit'">
            <div class="col">
                <div class="control-group">
                    <label class="control-label">
                        <span style="padding: 0 6px;">发起人：</span>
                    </label>
                    <div>
                                <s:textfield name="creatorName" value="%{creatorName}" id = "creatorName"/>
                    </div>
                </div>
            </div>
            </s:if>
            
            <s:if test="state == 'done'">
            <div class="col">
                <div class="control-group">
                    <label class="control-label">
                        <span style="padding: 0 6px;">单位：</span>
                    </label>
                    <div>
                                <s:textfield name="teamName" value="%{teamName}" id = "teamName"/>
                    </div>
                </div>
            </div>
            <div class="col">
                <div class="control-group">
                    <label class="control-label">
                        <span style="padding: 0 6px;">项目模式：</span>
                    </label>
                    <div>
                                <s:select list="#{'请选择':'请选择','正式项目':'正式项目','预备项目':'预备项目','调研项目':'调研项目'}" name="projectType" cssClass="input-xlarge"></s:select>
                    </div>
                </div>
            </div>
            </s:if>
       		</div>
                
				
				<s:if test="state=='unaudit' || state=='audit'">
				<div class="detai-con">
		            <div class="col">
		                <div class="control-group">
		                    <label class="control-label">
		                        <span style="padding: 0 6px;">发起时间：</span>
		                    </label>
		                    <div >
		                        <input type="text" name="startTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='startTime' format='yyyy-MM-dd'/>">&nbsp;-
		                        <input type="text" name="endTime" readonly="readonly" onclick="WdatePicker({readOnly:true,dateFmt:'yyyy-MM-dd'})" value="<s:date name='endTime' format='yyyy-MM-dd'/>">
		                    </div>
		                </div>
		            </div>
		        </div>
				</s:if>
				
				<s:if test="state != 'summary_list'">
                <div class="clearfix">
                    <div class="container-fluid container-top">
                        <div class="row rh">
                            <div class="col-sm-12 text-center">
                                <button type="submit" class="btn btn-info btn-bg btn-120">查询</button>
                            </div>
                        </div>
                    </div>
                </div>
                </s:if>
                <s:else>
                <div class="clearfix">
                    <div class="container-fluid container-top">
                        <div class="row rh">
                            <div class="col-sm-12 text-center">
                                <button type="button" onclick="chushen();" class="btn btn-info btn-bg btn-120">查询</button>
                            </div>
                        </div>
                    </div>
                </div>
                </s:else>
            </form>
    <!--内容区-->
    <div class = "detai">
        <div class = "detai-con">
            <div class = "tab-list <s:if test='state == "unaudit"'>tab-back</s:if>" onclick="window.location.href('${basePath}/legislate/plan_unaudit_list.do')">未审核</div>
            <div class = "tab-list <s:if test='state == "audit"'>tab-back</s:if>" onclick="window.location.href('${basePath}/legislate/plan_audit_list.do')">审核中</div>
            <div class = "tab-list <s:if test='state == "done"'>tab-back</s:if>" onclick="window.location.href('${basePath}/legislate/plan_done_list.do')">已完成</div>
      		<div class = "tab-list <s:if test='state == "summary"'>tab-back</s:if>" onclick="window.location.href('${basePath}/legislate/plan_summary_list.do')">已汇总</div>
      		<div class = "tab-list <s:if test='state == "summary_list"'>tab-back</s:if>" onclick="window.location.href('${basePath}/legislate/planSummary_list.do')">年度初审表</div>
        <div class = "biaodan">
            <div class = "tab-content" style = "display: block">
                <table class = "table1">
                    <thead>
                    <s:if test="state == 'unaudit' || state == 'audit'">
                    	<tr>
                        <th>计划编号</th>
                        <th>立法计划名称</th>
                        <th>上报类型</th>
                        <th>处理环节</th>
                        <th>发起人</th>
                        <th>发起时间</th>
                        <th>操作</th>
                   		 </tr>
                    </s:if>
                    <s:if test="state == 'done'">
                    	<tr>
                        <th><input type = "checkbox" id="checkAll"></th>
                            <th>计划编号</th>
                            <th>单位</th>
                            <th>立法计划名称</th>
                            <th>上报类型</th>
                            <th>项目模式</th>
                            <th>操作</th>
                   		 </tr>
                    </s:if>
                    <s:if test="state == 'summary'">
                    	<tr>
                        <th>序号</th>
                        <th>起草部门</th>
                        <th>规章名称</th>
                        <th>立/改/废</th>
                        <th>操作</th>
                    	</tr>
                    </s:if>
                    <s:if test="state == 'summary_list'">
                    	<tr>
                        <th>序号</th>
                        <th>年度</th>
                        <th>经办处</th>
                        <th>汇总时间</th>
                        <th>操作</th>
                    	</tr>
                    </s:if>
                    </thead>
                    <tbody>
                    
                    <s:set name="rownum" value="(pageNo-1)*pageSize+1"></s:set>
                    <s:iterator value="pageModel.result" id="task">
                    <s:if test="state == 'unaudit'">
                    <tr>
                        <td><s:property value="rownum"/></td>
                        <td><s:property value="plan.planName" /></td>
                        <td><s:property value="plan.planType" /></td>
                        <td>立法处-未认领</td>
                        <td><s:property value="plan.creatorName"/></td>
                       <td><s:date name="plan.createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                        <td nowrap="">
                            <a class = "tianbao caozuo-btn" onclick="chakan('<s:property value="plan.planId" />&op=view')">查看</a>
                            <a class = "chakan caozuo-btn btn-70" onclick="renling('<s:property value="taskId" />')">确认认领</a>
                        </td>
                    </tr>
                    </s:if>
                    <s:if test="state == 'audit'">
                    <tr>
                       <td><s:property value="rownum"/></td>
                        <td><s:property value="plan.planName" /></td>
                        <td><s:property value="plan.planType" /></td>
                        <td>立法处-审核中</td>
                        <td><s:property value="plan.creatorName"/></td>
                       <td><s:date name="plan.createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                        <td nowrap="">
                            <a class = "tianbao caozuo-btn" onclick="chakan('<s:property value="plan.planId" />&op=view')">查看</a>
                            <a class = "chakan caozuo-btn layer-fenban" onclick="shenhe('<s:property value="taskId" />')">审核</a>
                        </td>
                    </tr>
                    </s:if>
                    <s:if test="state == 'done'">
                    <tr>
                       <td><input type = "checkbox" name="taskIds" value="<s:property value="taskId"/>"></td>
                            <td><s:property value="rownum"/></td>
                            <td><s:property value="teamName" /></td>
                            <td><s:property value="plan.planName" /><s:property value="planName" /></td>
                            <td><s:property value="plan.planType" /><s:property value="planType" /></td>
                            <td><s:property value="plan.projectType" /><s:property value="projectType" /></td>
                            <td nowrap="">
                            <s:if test="finishstate=='finish'">
                            	<a class = "tianbao caozuo-btn" onclick="chakan('<s:property value="planId" />&op=view')">查看</a>
                            </s:if>
                            <s:else>
                                <a class = "tianbao caozuo-btn" onclick="chakan('<s:property value="plan.planId" />&op=view')">查看</a>
                            </s:else>
                            </td>
                    </tr>
                    </s:if>
                    <s:if test="state == 'summary'">
                    	<tr>
                        <td><s:property value="rownum"/></td>
                        <td><s:property value="teamName"/></td>
                        <td><s:property value="planName" /></td>
                        <td><s:property value="planType" /></td>
                        <td><a class = "tianbao caozuo-btn" onclick="chakan('<s:property value="planId" />&op=view')">查看</a></th>
                    	</tr>
                    </s:if>
                    <s:if test="state == 'summary_list'">
                    	<tr>
                        <td><s:property value="rownum"/></td>
                        <td><s:property value="year"/></td>
                        <td><s:property value="teamName" /></td>
                        <td><s:date name="lastTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                        <td><a class = "tianbao caozuo-btn" onclick="openSummaryItems('<s:property value="summaryId" />&op=view')">查看</a></th>
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
		                <%@include file="/platform/include/pagelinks.jsp" %>
		                </div>
		            <s:if test="state == 'done'">
		            	 <div class="clearfix">
	                	<div class="container-fluid container-top">
	                    <div class="row rh">
	                        <div class="col-sm-12 text-center">
	                            <button type="button" class="btn btn-info btn-bg btn-120" onclick="huizong()">汇总</button>
	                        </div>
	                        </span>
	                    </div>
	               		</div>
            			</div>
		            </s:if>
            </div>
        </div>
            <div class = "clearfix"></div>
    </div>
    </div>
</div>
<div class = "close-btn" id = "closebtn">X</div>

<script>
    $(function () {
    	$("#checkAll").click(function () {
            $("table td input").prop('checked',$(this).prop("checked"))
        })
        $('.tab-list').click(function () {
            var index = $(this).index();
            $(this).css({'background':'#288ce2','color':'#fff','font-size':'16px'}).siblings('.tab-list').css({'background':'#ececec','color':'#969696','font-size':'14px'})
            $('.biaodan .tab-content').eq(index).show().siblings('.tab-content').hide()
        })
		
    });
    
    var action=window.location.href;
    function renling(taskId){
		 if(confirm("确定要认领吗？")){
 	 	$.ajax({
 	 		"type":"POST",
 	 		"dataType":"json",
 	 		"url":"${basePath}/legislate/plan_claim.do",
 	 		"data":{"taskId":taskId},
 	 		"success":function(data){
 	 			if(data.code==200){
 	 				alert(data.message);
 	 				/* location.reload(); */
 	 				window.location.href(action);
 	 			}
 	 		}
 	 	}) 
     }
	}
    function huizong(){
    	var chk_value = "";
    	$('input[name="taskIds"]:checked').each(function(){
    		chk_value = chk_value+ ($(this).val())+",";
    	});
    	if(chk_value.length==0){
    		alert('你还没有选择')
    	}else{
    		$.ajax({
     	 		"type":"POST",
     	 		"dataType":"json",
     	 		"url":"${basePath}/legislate/dosummary.do",
     	 		"data":{"taskIds":chk_value},
     	 		"success":function(data){
     	 			alert(data.message);
     	 			if(data.code==200){
     	 				/* location.reload(); */
     	 				window.location.href(action);
     	 			}
     	 		}
     	 	}) 
    	}
    	
    }
    
    function shenhe(taskId){
    	layer.open({
            type: 2,
            title: '',
            shade: false,
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: ['850px', '620px'],
            content: '${basePath}/legislate/shenhe_gxd.do?op=toaudit&taskId='+taskId,
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
    
    function chushen(){
		document.plansh.action = '${basePath}/legislate/planSummary_list.do?search=query';
		document.plansh.submit();
    }
</script>
</body>
</html>