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
    <script src = "${basePath}/resources/components/My97DatePicker/WdatePicker.js"></script>
</head>
<body class = "body_bg_1">
<!--头部结asd束-->
<div class = "conteng">
    <!--面包屑-->
    <div class = "conteng-title">
        <span>当前位置：</span><em>单位意见-首页</em>
    </div>

    <!--内容区-->
            
          <form class = "form-horizontal" name="form1"  method="post" action="${basePath}/legislate/teamOpinion_list.do">
          <s:hidden name="state"/>
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
                            	<span style="padding: 0 6px;">对应草案:</span>
                            </label>
                            <div>
                                 <s:textfield name="draftName" value="%{draftName}" id = "draftName"/>
                            </div>
                            
                            <s:if test="state == 'summary'">
                            <label class="control-label">
                            	<span style="padding: 0 6px;">类型:</span>
                            </label>
                            <div>
                                 <s:textfield name="opinionType" value="%{opinionType}" id = "opinionType"/>
                            </div>
                            </s:if>
                            <s:else>
                            <label class="control-label">
                            	<span style="padding: 0 6px;">经办处:</span>
                            </label>
                            <div>
                                 <s:select list="#{'请选择':'请选择','经济法规处':'经济法规处','城建法规处':'城建法规处','社会法规处':'社会法规处'}" name="teamName" id="teamName" cssClass="select-option thisoption"></s:select>
                            </div>
                            </s:else>
                        </div>
                    </div>
                    </div>
                    
                <div class="detai-con" style="position:relative">
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
                    
                <div class="clearfix">
                    <div class="container-fluid container-top">
                        <div class="row rh">
                            <div class="col-sm-12 text-center">
                                <button type="submit" class="btn btn-info btn-bg btn-120">查询</button>
                                <button type="button" onclick="window.location.href('${basePath}/legislate/to_Teamadd.do')" class="btn btn-info btn-bg btn-120">添加</button>
                            </div>
                        </div>
                    </div>
                </div>
<div class = "detai">
        <div class = "detai-con">
            <div class = "tab-list <s:if test='state == "unsummary"'>tab-back</s:if>" onclick="window.location.href('${basePath}/legislate/teamOpinion_list.do?state=unsummary')">未征询</div>
            <div class = "tab-list <s:if test='state == "summary"'>tab-back</s:if>"   onclick="window.location.href('${basePath}/legislate/teamOpinion_list.do?state=summary')">已征询</div>
        </div>
</div>
            

            <div class = "container-fluid container-top">
                <div class = "row">
                    <div class = "col-sm-12">
                        <div class = "biaodan">
                            <table class = "table1">
                            
                                <thead>
                                <tr>
                                 <s:if test="state=='unsummary'">
                                    <th>编号</th>
				                    <th>对应草案</th>
				                    <th>经办处</th>
				                    <th>发起日期</th>
				                    <th>操作</th>
				                     </s:if>
				                     <s:if test="state=='summary'">
				                     	 <th>编号</th>
				                    <th>对应草案</th>
				                    <th>类型</th>
				                    <th>征询发起日期</th>
				                    <th>操作</th>
				                     </s:if>
                                </tr>
                                </thead>
                                <tbody>
                                 <s:if test="state=='unsummary'">
                                <tr>
                               
                                 <s:set name="rownum" value="(pageNo-1)*pageSize+1"></s:set>
                                 <s:iterator value="pageModel.result" id="task">
				                    
				                    <tr>
				                    <td><s:property value="rownum"/></td>
				                        <td><s:property value="draft.draftName"/></td>
				                        <td><s:property value="teamName" /></td>
				                        <td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
			                        	<td nowrap="">
			                        	 <s:if test="opinionStatus == 'init'">
			                        	 	<a class = "tianbao caozuo-btn btn-70" onclick = "window.location.href='${basePath}/legislate/saveTeamOpinion.do?op=add&teamOpinionId=<s:property value="teamOpinionId"/>'">发起征询</a>
				                          </s:if><s:else>
				                             <a class = "tianbao caozuo-btn btn-70" onclick="chakan('<s:property value="teamOpinionId"/>')">查看</a>
				                          	 <a class = "tianbao caozuo-btn btn-70" onclick="fasong('<s:property value="teamOpinionId" />')">发送</a>
				                       		</s:else>
				                        </td>
				                    </tr>
				                     <s:set name="rownum" value="#rownum+1"></s:set>
				                 </s:iterator>
                                
                               
                                </tr>
                                </s:if>
                                <s:if test="state=='summary'">
                                	  <tr>
                                 <s:set name="rownum" value="(pageNo-1)*pageSize+1"></s:set>
                                 <s:iterator value="pageModel.result" id="task">
				                    
				                    <tr>
				                    <td><s:property value="rownum"/></td>
				                        <td><s:property value="draft.draftName"/></td>
				                        <td><s:property value="opinionType" /></td>
				                        <td><s:date name="createTime" format="yyyy-MM-dd HH:mm:ss" /></td>
			                        	<td nowrap="">
				                          	 <a class = "tianbao caozuo-btn btn-70" onclick="chakan('<s:property value="teamOpinionId"/>')">查看</a>
				                          	 <a class = "tianbao caozuo-btn btn-70" onclick="jieshou('<s:property value="teamOpinionId"/>')">接收情况</a>
				                        </td>
				                    </tr>
				                     <s:set name="rownum" value="#rownum+1"></s:set>
				                 </s:iterator>
                                
                               
                                </tr>
                                </s:if>
                                </tbody>
                            </table>
      				
                </div>
               
                        </div>
                        <div class = "contentBox-paging">
                             <%
		                Page retPage= (Page)request.getAttribute("pageModel");
		                int pageSize = (null == request.getParameter("pageSize") || "".equals(request.getParameter("pageSize")))?10:Integer.parseInt(request.getParameter("pageSize"));
		                int pageNo = (null == request.getParameter("pageNo") || "".equals(request.getParameter("pageNo")))?1:Integer.parseInt(request.getParameter("pageNo"));
		                %>
                			<%@include file="/platform/include/pagelinks.jsp" %>
                    </div>
                     
		         </div>
                  <div class = "clearfix"></div>
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
    
    function fasong(teamOpinionId){
    	layer.open({
            type: 2,
            title: '',
            shade: false,
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: ['850px', '620px'],												 
            content: '${basePath}/legislate/meetingOrgList.do?state=teamOpinion_list&teamOpinionId='+teamOpinionId,
        /*     success: function(layero, index){
                var banlibtn = layer.getChildFrame('#banlibtn', index);
                banlibtn.click(function () {
                    window.location.href='http://www.baidu.com'
               })
           } */
        });
	}
    function jieshou(teamOpinionId){
    	layer.open({
            type: 2,
            title: '',
            shade: false,
            maxmin: true,
            shadeClose: true, //点击遮罩关闭层
            area: ['850px', '620px'],												 
            content: '${basePath}/legislate/detaillist.do?teamOpinionId='+teamOpinionId,
        /*     success: function(layero, index){
                var banlibtn = layer.getChildFrame('#banlibtn', index);
                banlibtn.click(function () {
                    window.location.href='http://www.baidu.com'
               })
           } */
        });
	}
    //查看
    function chakan(teamOpinionId){
    	window.location.href="${basePath}/legislate/detailTeamOpinion.do?teamOpinionId="+teamOpinionId;
    }
</script>
</body>
</html>