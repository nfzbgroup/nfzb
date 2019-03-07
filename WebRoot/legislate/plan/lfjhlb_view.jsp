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
    <title>规章草案起草办理</title>
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${basePath}/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${basePath}/legislate/js/jquery.js"></script>
    <script src = "${basePath}/legislate/js/layer/layer.js"></script>
    <script src = "${basePath}/legislate/js/sui.js"></script>
    <script src = "${basePath}/legislate/js/tanchuang.js"></script>
</head>
<%
java.util.List pageFileRecord = (java.util.List)request.getAttribute("pageFileRecord");
%>
<body class = "body_bg_1">
<div class = "conteng">
    <!--面包屑-->
    <div class = "conteng-title">
        <span>当前位置：</span><em>立法计划列表-查询</em>
    </div>

    <!--内容区-->
    <div class = "detai">
        <div class = "detai-con">
            <div class = "tab-list tab-back">立法信息列表</div>
        </div>

        <!--tab内容-->
        <div class = "indextab">
            <div class = "tab-content" style = "display: block">
                <div class = "col">
                    <div class = "control-group">
                        <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label">
                                    <span style = "padding: 0 6px;" class = "spanwidth">立法计划名称：</span>
                                </label>
                                <div class = "controls" style = "vertical-align: middle">
                                    <h4>${plan.planName }</h4>
                                </div>
                            </div>
                        </div>
 
                        <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label" style = "vertical-align:top">
                                    <span style = "padding: 0 6px;" class = "spanwidth">立法计划类型：</span>
                                </label>
                                <div class = "controls">
                                    <p>
                                         ${plan.planType }
                                    </p>
                                </div>
                            </div>
                        </div>
                        
                        <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label" style = "vertical-align:top">
                                    <span style = "padding: 0 6px;" class = "spanwidth">立法项目类型：</span>
                                </label>
                                <div class = "controls">
                                    <p>
                                         ${plan.projectType }
                                    </p>
                                </div>
                            </div>
                        </div>
                        
                        <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label" style = "vertical-align:top">
                                    <span style = "padding: 0 6px;" class = "spanwidth">建议立项理由：</span>
                                </label>
                                <div class = "controls">
                                    <p>
                                         ${plan.reason }
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label" style = "vertical-align:top">
                                    <span style = "padding: 0 6px;" class = "spanwidth">目前进展情况：</span>
                                </label>
                                <div class = "controls">
                                    <p>
                                         ${plan.progress }
                                    </p>
                                </div>
                            </div>
                        </div>
                        
                        <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label" style = "vertical-align:top">
                                    <span style = "padding: 0 6px;" class = "spanwidth">备注：</span>
                                </label>
                                <div class = "controls">
                                    <p>
                                         ${plan.remark }
                                    </p>
                                </div>
                            </div>
                        </div>
                        <div class = "row">
                            <div class = "control-group">
	 							<label class = "control-label" style = "vertical-align:top">
                                    <span style = "padding: 0 6px;" class = "spanwidth">相关资料：</span>
                                </label>
                                <div class = "controls">
                                	<ul class = "file-list-font" style="min-width: 520px;">
	                                    <%
	                                    for(int i=0;i<pageFileRecord.size();i++){
	                                    	com.wonders.fzb.legislate.beans.FileRecord fileRecord = (com.wonders.fzb.legislate.beans.FileRecord)pageFileRecord.get(i);
	                                    %>
	                                    <li><a href="${basePath}/legislate/fileRecordLoad.do?fileRecordId=<%=fileRecord.getFileRecordId() %>"><%=fileRecord.getFileName() %></a></li>
	                                    <%} %>
                                	</ul>
                                </div>
					    	</div>
                        </div>
						<%if(!"view".equals(request.getParameter("op"))){ %>
                        <div class = "submit-btn">
                            <a href = "#" class = "sui-btn btn-primary01 btn-lg btn-170">新建公众社会意见发起</a>
                            <a href = "#" class = "sui-btn btn-primary01 btn-lg btn-170">新建部门征询意见</a>
                            <a href = "#" class = "sui-btn btn-primary01 btn-lg btn-170">新建立法听证会</a>
                        </div>
						 <%} %>
                        <div class = "submit-btn">
                            <a href = "javascript:void (0)" class = "sui-btn btn-primary01 btn-lg btn-170 layer-close">关闭</a>
                        </div>
 
                    </div>
                </div>
            </div>
            
            <div class = "tab-content">
                <div class = "biaodan">
                    <table class = "table1">
                        <thead>
                        <tr>
                            <th>开始时间</th>
                            <th>操作人</th>
                            <th>操作单位</th>
                            <th>操作说明</th>
                        </tr>
                        </thead>
                        <tbody>
                        
                        <s:iterator value="pageModel.result" id="task">
                        	<tr class = "gray">
                            <td><s:date name="taskTime" format="yyyy-MM-dd HH:mm:ss" /></td>
                            <td><s:property value="userName"/></td>
                            <td><s:property value="teamName"/></td>
                            <td><s:property value="instructions"/></td>
                       		 </tr>
                        
                        </s:iterator>
                      
                        </tbody>
                    </table>
                </div>

				<%if(!"view".equals(request.getParameter("op"))){ %>
                <div class = "submit-btn">
                    <a href = "#" class = "sui-btn btn-primary01 btn-lg btn-170">新建公众社会意见发起</a>
                    <a href = "#" class = "sui-btn btn-primary01 btn-lg btn-170">新建部门征询意见</a>
                    <a href = "#" class = "sui-btn btn-primary01 btn-lg btn-170">新建立法听证会</a>
                </div>
                <%} %>
                <div class = "submit-btn">
                    <a href = "javascript:void (0)" class = "sui-btn btn-primary01 btn-lg btn-170 layer-close">关闭</a>
                </div>


            </div>
            
            
            
             <s:if test="isdise=='yes'"> 
            <div class = "tab-content">
          
<!--内容区-->
<div class = "jumbotron text-center container-top" style = "margin-top: 60px">
    <h1 style = "color: #B9404D;">上海市人民政府法制办公室文件分办单</h1>
</div>

<div style = "border-bottom: 2px solid #B9404D; height: 270px;">
    <div class = "container-fluid">
        <div class = "row" style="text-align: right">
            <div class = "col-sm-12 text-right" >
                ${planTaskBylfjhsh.plan.fbd.fbdId }
            </div>
        </div>

    </div>
    <div class = "clearfix"></div>
    <div class = "container-fluid container-top">
        <div class = "row">
            <div class = "col-sm-1 text-color " style = "float: left">来文单位:</div>
            <div class = "col-sm-2 " style = "float: left">
                <span class = "szgb"><s:textfield name="teamName" value="%{plan.teamName}"/></span>
            </div>
            <div class = "col-sm-1"></div>
            <div class = "col-sm-4 col-sm-offset-3" style = "float: right;">
                <span class = "text-color">日期:</span>
                <span class = "bdbt"><s:date name="plan.createTime" format="yyyy"/></span>
                <span class = "text-color">年</span>
                <span class = "bdbt"><s:date name="plan.createTime" format="MM"/></span>
                <span class = "text-color">月</span>
                <span class = "bdbt"><s:date name="plan.createTime" format="dd"/></span>
                <span class = "text-color">日</span>
            </div>
        </div>
    </div>
    <div class = "clearfix"></div>
    <div class = "container-fluid bdtop">
        <div class = "row">
            <div class = "col-sm-1 text-color " style = "float: left">计划名称:</div>
            <div class = "col-sm-8">
                <p class = "wjmc" style = "display: inline-block;">
                    <s:textfield name="planName" value="%{plan.planName}"/>
                </p>
            </div>
        </div>
    </div>

    <div class = "clearfix"></div>
    <div class = "container-fluid bdtop">
        <div class = "row">
            <div class = "col-sm-1 text-color " style = "float: left;">分办:</div>
            <div class = "col-sm-8">
                 <s:textarea  style="font-size: medium;font-family: 微软雅黑, 黑体, 宋体;display:inline-block;width: 420px;float: left;"  cssClass="fenban-textarea" value="%{planTaskBylfjhsh.plan.fbd.instructions}">
                 </s:textarea>
           </div>
        </div>
    </div>
    <div class = "clearfix"></div>
    <div class = "container-fluid container-top">
        <div class = "row">
            <div class = "col-sm-4 text-color" style = "display: inline-block;margin-left: 350px;">办理时限:</div>
            <div class = "col-sm-3" style = "display: inline-block;">
                <span class = "text-color">日期:</span>
                <span class = "bdbt"><s:date name="planTaskBylfjhsh.plan.fbd.processTime" format="yyyy"/></span>
                <span class = "text-color">年</span>
                <span class = "bdbt"><s:date name="planTaskBylfjhsh.plan.fbd.processTime" format="MM"/></span>
                <span class = "text-color">月</span>
                <span class = "bdbt"><s:date name="planTaskBylfjhsh.plan.fbd.processTime" format="dd"/></span>
                <span class = "text-color">日</span>
            </div>
        </div>
    </div>
    <div class = "clearfix"></div>
</div>
<div class = "container-fluid container-top">

        <div class = "col-sm-2 text-right" style = "padding-top: 4px;display: inline-block;">经办处室:${planTask.teamId }</div>
        <div class = "col-sm-4" style = "margin-left: 10px;display: inline-block;">
          <s:if test="planTaskBylfjhsh.teamId=='U_3_5'">
          	经济法规处
          </s:if>
          <s:if test="planTaskBylfjhsh.teamId=='U_3_6'">
          	城建法规处
          </s:if>
          <s:if test="planTaskBylfjhsh.teamId=='U_3_7'">
          	社会法规处
          </s:if>
        </div>
    </div>

<div class = "clearfix"></div>
<div class = "submit-btn">
    <a href = "#" class = "sui-btn btn-primary01 btn-lg btn-170 layer-close">关闭</a>
</div>
</div>
            </s:if>
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