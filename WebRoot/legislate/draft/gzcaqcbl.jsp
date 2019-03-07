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
<body class = "body_bg_1">
<div class = "conteng">
    <!--面包屑-->
    <div class = "conteng-title">
        <span>当前位置：</span><em><%if(!"view".equals(request.getParameter("op"))){ %>
      规章草案列表-办理
        <%}else{ %>
          规章草案列表-查看
        <%} %>
        </em>
    </div>

    <!--内容区-->
    <div class = "detai">
        <div class = "detai-con">
            <div class = "tab-list tab-back">草案信息列表</div>
            <div class = "tab-list">草案日志记录</div>
            <s:if test="isdise=='yes'">
            	<div class = "tab-list">分办单</div>
            </s:if>
        </div>

        <!--tab内容-->
        <div class = "indextab">
            <div class = "tab-content" style = "display: block">
                <div class = "col">
                    <div class = "control-group">
                        <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label">
                                    <span style = "padding: 0 6px;" class = "spanwidth">规章草案起草名称：</span>
                                </label>
                                <div class = "controls" style = "vertical-align: middle">
                                    <h4>${draftTask.draft.draftName }</h4>
                                </div>
                            </div>
                        </div>
 
                        <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label" style = "vertical-align:top">
                                    <span style = "padding: 0 6px;" class = "spanwidth">规章草案起草信息：</span>
                                </label>
                                <div class = "controls">
                                    <p>
                                         ${draftTask.draft.instructions }
                                    </p>
                                </div>
                            </div>
                        </div>
 
                        <div class = "row">
                            <div class = "control-group">
                                <label class = "control-label" style = "vertical-align: top; padding-top: 23px">
                                    <span style = "padding: 0 6px;" class = "spanwidth">相关资料：</span>
                                </label>
                                <ul class = "file-list-font" style="min-width: 520px;">
                                     <s:iterator value="draftFiles" var="draftFile">
				                		<li><a  href = "${basePath}/legislate/fileRecordLoad.do?fileRecordId=<s:property value="fileRecordId" />"><s:property value="fileName"/></a></li>
				           			</s:iterator>
                                </ul>
                            </div>
                        </div>
						<%if(!"view".equals(request.getParameter("op"))){ %>
                        <div class = "submit-btn">
                            <a href = "${basePath}/legislate/savePublicOption.do?draftId=${draftTask.draft.draftId}" class = "sui-btn btn-primary01 btn-lg btn-170">新建公众社会意见</a>
                            <a onclick="zhengxun('${draftTask.draft.draftId}')" class = "sui-btn btn-primary01 btn-lg btn-170">新建部门征询意见</a>
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
                            <th>处理时间</th>
                            <th>操作人</th>
                            <th>操作单位</th>
                            <th>操作说明</th>
                        </tr>
                        </thead>
                        <tbody>
                        
                        <s:iterator value="pageModel.result" id="task">
                        	<tr class = "gray">
                            <td><s:date name="processTime" format="yyyy-MM-dd HH:mm:ss" /></td>
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
                    <a href = "${basePath}/legislate/savePublicOption.do?draftId=${draftTask.draft.draftId}" class = "sui-btn btn-primary01 btn-lg btn-170">新建公众社会意见发起</a>
                    <a onclick="zhengxun('${draftTask.draft.draftId}')"   class = "sui-btn btn-primary01 btn-lg btn-170">新建部门征询意见</a>
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
                ${draftTask.draft.fbd.fbdId }
            </div>
        </div>

    </div>
    <div class = "clearfix"></div>
    <div class = "container-fluid container-top">
        <div class = "row">
            <div class = "col-sm-1 text-color " style = "float: left">来文单位:</div>
            <div class = "col-sm-2 " style = "float: left">
                <span class = "szgb"><s:textfield disabled="true" name="draftName" value="%{draftTask.draft.teamName}"/></span>
            </div>
            <div class = "col-sm-1"></div>
            <div class = "col-sm-4 col-sm-offset-3" style = "float: right;">
                <span class = "text-color">日期:</span>
                <span class = "bdbt"><s:date name="draftTask.draft.createTime" format="yyyy"/></span>
                <span class = "text-color">年</span>
                <span class = "bdbt"><s:date name="draftTask.draft.createTime" format="MM"/></span>
                <span class = "text-color">月</span>
                <span class = "bdbt"><s:date name="draftTask.draft.createTime" format="dd"/></span>
                <span class = "text-color">日</span>
            </div>
        </div>
    </div>
    <div class = "clearfix"></div>
    <div class = "container-fluid bdtop">
        <div class = "row">
            <div class = "col-sm-1 text-color " style = "float: left">草案名称:</div>
            <div class = "col-sm-8">
                <p class = "wjmc" style = "display: inline-block;">
                    <s:textfield disabled="true" name="draftName" value="%{draftTask.draft.draftName}"/>
                </p>
            </div>
        </div>
    </div>

    <div class = "clearfix"></div>
    <div class = "container-fluid bdtop">
        <div class = "row">
            <div class = "col-sm-1 text-color " style = "float: left;">分办:</div>
            <div class = "col-sm-8">
                 <s:textarea cssClass="fenban-textarea" style="display:inline-block;width: 420px;float: left;" value="%{draftTask.draft.fbd.instructions}">
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
                <span class = "bdbt"><s:date name="draftTask.draft.fbd.processTime" format="yyyy"/></span>
                <span class = "text-color">年</span>
                <span class = "bdbt"><s:date name="draftTask.draft.fbd.processTime" format="MM"/></span>
                <span class = "text-color">月</span>
                <span class = "bdbt"><s:date name="draftTask.draft.fbd.processTime" format="dd"/></span>
                <span class = "text-color">日</span>
            </div>
        </div>
    </div>
    <div class = "clearfix"></div>
</div>
<div class = "container-fluid container-top">
    <div class = "row">
        <div class = "col-sm-2 text-right" style = "padding-top: 4px;display: inline-block;">经办处室:</div>
        <div class = "col-sm-4" style = "margin-left: 10px;display: inline-block;">
          <s:if test=" draftTaskBygzcaqcbl.teamId=='U_3_5'">
          	经济法规处
          </s:if>
          <s:if test=" draftTaskBygzcaqcbl.teamId=='U_3_6'">
          	城建法规处
          </s:if>
          <s:if test=" draftTaskBygzcaqcbl.teamId=='U_3_7'">
          	社会法规处
          </s:if>
        </div>
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
</div>

<script>
var uuu;
    $(function () {
    	uuu= window.location.href;
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
    
    
    function zhengxun(draftId){
   	 	$.ajax({
   	 		"type":"POST",
   	 		"dataType":"json",
   	 		"url":"${basePath}/legislate/saveTeamOpinion.do?op=zhengxun&draftId="+draftId,
   	 		"success":function(data){
   	 			alert(data.message);
   	 			if(data.code==200){
   	 			window.location.href(uuu);
   	 			}
   	 		}
   	 	}) 
	}
</script>
</body>
</html>