<%@page import="com.wonders.fzb.framework.beans.vo.UserInfoBean"%>
<%@page import="java.util.List"%>
<%@page import="com.wonders.fzb.legislate.beans.vo.TeamInfoBean"%>
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
    <title>审核会议</title>
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "${basePath}/legislate/css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "${basePath}/legislate/css/sui-themes-green-append.css" media = "screen"/>
    <script src = "${basePath}/legislate/js/jquery.js"></script>
    <script src = "${basePath}/legislate/js/sui.js"></script>
    <script src = "${basePath}/legislate/js/tanchuang.js"></script>
</head>
<body class = "body_bg_1">
<!--面包屑-->
<div class = "conteng">
    <div class = "conteng-title">
        <span>当前位置：</span><em>审核会议</em>
    </div>
    <div class = "detai" style = "margin-bottom: 40px">
        <div class = "detai-con">
            <!--内容区-->
         <%--    <form class = "form-horizontal" role = "form">
            <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                            <label class="formlabel">人员名称:</label>
                            <span class="formdiv">
                                <input type = peext">
                            </span>
                        <span class="formbuttom">
                            <button type="button" class="btn btn-info btn-bg btn-120">查询</button>
                        </span>
                    </div>
                </div>
            </div>

            </form> --%>
<div class = "detai">
        <div class = "detai-con">
        <div class = "biaodan">
            <div class = "tab-content" style = "display: block">
                <table class = "table1">
                     <thead>
                        <tr>
                            <th width="20%"><input type = "checkbox" id="checkAll"></th>
                            <th colspan="5">上海市政府法制办</th>
                        </tr>
                        </thead>
                        <tbody>
                        <s:iterator value="teams" id="team"  status="st">
                        <tr>
                            <td  colspan="6" style="font-weight: bold;"><s:property value="unitName"/></td>
                        </tr>
                        <% List  list = ((TeamInfoBean)request.getAttribute("team")).getList();
                        //list.addAll(list);
                     //  list.addAll(list);
                        int i =0;
                        for(; i<list.size();i++){
                        	UserInfoBean userInfoBean = (UserInfoBean)list.get(i);
                        	if(i % 6 == 0){
                        		out.print("<tr>");
                        	}
                        %>	
                        <td  width="17%">
	                            	<input type = "checkbox" name="test" value="<%=userInfoBean.getName() %>">
	                            	<%=userInfoBean.getName() %>
	                            </td>
                         <%
                        	if(i % 6 == 5){
                        		out.print("</tr>");
                        	}
                        }
                        int j = list.size() % 6;
                        if(j != 0){
							for(int k=j;k<6;k++){
								out.print("<td>&nbsp;</td>");
							}
							out.print("</tr>");
                        }
                        %>
	                            	
                        </s:iterator>
                        </tbody>
                    </table>
                <div class="clearfix">
                <div class="container-fluid container-top">
                    <div class="row rh">
                        <div class="col-sm-12 text-center">
                            <button type="button" class="btn btn-info btn-bg btn-120" id="frameindex">确定</button>
                            <button type="button" class="btn btn-info btn-bg btn-120" onclick="window.history.back()">返回</button>
                        </div>
                    </div>
                </div>
            </div><br/>
            </div>
        </div>
    </div>
</div>


<script>
    $(function () {
       $("#checkAll").click(function () {
            $("table td input").prop('checked',$(this).prop("checked"))
       });

		$("#frameindex").click(function(){
       		var check_val = [];
			var partxt=$('#meetingPeople',window.parent.document)
			$('input[name="test"]:checked').each(function(){ 
				check_val.push($(this).val()); 
				}); 
			partxt.val(check_val)
			
 	        var i = parent.layer.getFrameIndex(window.name);
	        parent.layer.close(i); 
		})
    });

</script>
</div>
</body>
</html>