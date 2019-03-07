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
    <title>立法计划汇总-首页</title>
    <link rel = "stylesheet" type = "text/css" href = "css/main.css" media = "screen"/>
    <link rel = "stylesheet" href = "css/bootstrap.min.css">
    <link rel = "stylesheet" type = "text/css" href = "css/sui-themes-green-append.css" media = "screen"/>
    <script src = "js/jquery.js"></script>
    <script src = "js/layer/layer.js"></script>
    <script src = "js/sui.js"></script>
    <script src = "js/tanchuang.js"></script>
</head>
<body class = "body_bg_1">
<!--面包屑-->
<div class = "conteng">
    <div class = "conteng-title">
        <span>当前位置：</span><em>立法计划汇总-首页</em>
    </div>
            <form class = "form-horizontal" role = "form">
                <div class="clearfix">
                    <div class="container-fluid container-top">
                        <div class="row rh">
                            <label class="formlabel">经办处:</label>
                            <span class="formdiv fbw">
                               <select name = "" id = "">
                               		<option value = "">请选择</option>
                                    <option value = "">社会法规处</option>
                                    <option value = "">城建法规处</option>
                                    <option value = "">经济法规处</option>
                                </select>
                            </span>
                            <label class="formlabel">年份:</label>
                            <span class="formdiv fbw">
                               <select name = "" id = "">
                                    <option value = "">请选择</option>
                                    <option value = "">2016</option>
                                    <option value = "">2017</option>
                                    <option value = "">2018</option>
                                    <option value = "">...</option>
                                </select>
                            </span>
                        </div>
                    </div>
                </div>

                <div class="clearfix">
                    <div class="container-fluid container-top">
                        <div class="row rh">
                            <div class="col-sm-12 text-center">
                                <button type="button" class="btn btn-info btn-bg btn-120">查询</button>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
            <div class = "clearfix">
            <div class = "container-fluid container-top">
                <div class = "row">
                    <div class = "col-sm-12">
                    	<!--<div class = "tab-list tab-back">待汇总</div>
            			<div class = "tab-list">已汇总</div>-->
                        <div class = "biaodan">
                            <div class = "tab-content" style = "display: block">
                                <table class = "table1">
                                <thead>
                                <tr>
                                    <th>序号</th>
                                    <th>经办处</th>
                                    <th>汇总时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>经济法规处</td>
                                    <td>XX</td>
                                    <td nowrap="">
                                        <a class = "tianbao caozuo-btn">查看</a>
                                    </td>
                                </tr>
                                <tr class = "gray">
                                    <td>2</td>
                                    <td>经济法规处</td>
                                    <td>XX</td>
                                    <td>
                                        <a class = "tianbao caozuo-btn">查看</a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
	                            <div class = "contentBox-paging">
	                                <a href = "javascript:void(0)" class = "prevPage"><span>上一页</span></a>
	                                <a href = "javascript:void(0)" class = "paging-no current"><span>1</span></a>
	                                <a href = "javascript:void(0)" class = "paging-no"><span>2</span></a>
	                                <a href = "javascript:void(0)" class = "paging-no"><span>3</span></a>
	                                <a href = "javascript:void(0)" class = "paging-no"><span>4</span></a>
	                                <a href = "javascript:void(0)" class = "nextPage"><span>下一页</span></a>
	                                <span>共<b>6</b>页</span>
	                                <span>跳转</span>
	                                <input type = "text" class = "paging-to">
	                                <span>页</span>
	                            </div>
                            </div>
                            
                            <!--<div class = "tab-content">
                            <table class = "table1">
                                <thead>
                                <tr>
                                    <th>汇总编号</th>
                                    <th>汇总名称</th>
                                    <th>汇总时间</th>
                                    <th>操作</th>
                                </tr>
                                </thead>
                                <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>修改 XXX计划</td>
                                    <td>2017/03/03</td>
                                    <td nowrap="">
                                        <a class = "tianbao caozuo-btn">查看</a>
                                    </td>
                                </tr>
                                <tr>
                                    <td>1</td>
                                    <td>修改 XXX计划</td>
                                    <td>2017/03/03</td>
                                    <td nowrap="">
                                        <a class = "tianbao caozuo-btn">查看</a>
                                    </td>
                                </tr>
                                </tbody>
                            </table>
                            <div class = "contentBox-paging">
                                <a href = "javascript:void(0)" class = "prevPage"><span>上一页</span></a>
                                <a href = "javascript:void(0)" class = "paging-no current"><span>1</span></a>
                                <a href = "javascript:void(0)" class = "paging-no"><span>2</span></a>
                                <a href = "javascript:void(0)" class = "paging-no"><span>3</span></a>
                                <a href = "javascript:void(0)" class = "paging-no"><span>4</span></a>
                                <a href = "javascript:void(0)" class = "nextPage"><span>下一页</span></a>
                                <span>共<b>6</b>页</span>
                                <span>跳转</span>
                                <input type = "text" class = "paging-to">
                                <span>页</span>
                            </div>
                            </div>-->
					            <!--<div class="clearfix">
					                <div class="container-fluid container-top">
					                    <div class="row rh">
					                        <div class="col-sm-12 text-center">
					                            <button type="button" class="btn btn-info btn-bg btn-120" onclick="int=window.clearInterval(int)">生成汇总列表</button>
					                        </div>
					                        </span>
					                    </div>
					                </div>
					            </div><br />-->
					    	</div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>


<script>
    $(function () {
        $('.caozuo-btn').click(function () {
            layer.open({
                type: 2,
                title: '',
                shade: false,
                maxmin: true,
                shadeClose: true, //点击遮罩关闭层
                area: ['850px', '620px'],
                content: 'hz-layer.html',
            });
        });
        $("#checkAll").click(function () {
            $("table td input").prop('checked',$(this).prop("checked"))
        });
        $('.tab-list').click(function () {
            var index = $(this).index();
            $(this).css({'background':'#288ce2','color':'#fff','font-size':'16px'}).siblings('.tab-list').css({'background':'#ececec','color':'#969696','font-size':'14px'})
            $('.biaodan .tab-content').eq(index).show().siblings('.tab-content').hide()
        });
        var int=self.setInterval("clock()",50)
    function clock()
      {
      var t=new Date()
      document.getElementById("clock").value=t
      }
    });
</script>
</div>
</body>
</html>