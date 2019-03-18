<%@page import="com.wonders.fzb.base.beans.Page"%>
<%@ page language="java" contentType="text/html;charset=utf-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ page import="com.wonders.fzb.framework.beans.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML>
<html lang="en" xmlns:th="http://www.thymeleaf.org">

<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<meta name="renderer" content="webkit">
	<meta http-equiv="Cache-Control" content="no-siteapp" />
	<title>上海市政府立法平台</title>
	
	<link href="${basePath}/legislation/assets/css/bootstrap.min.css?v=3.3.5" rel="stylesheet">
	<link href="${basePath}/legislation/assets/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
	<link href="${basePath}/legislation/assets/css/animate.min.css" rel="stylesheet">
	<link href="${basePath}/legislation/assets/css/style.min.css?v=4.0.1" rel="stylesheet">
	<link href="${basePath}/legislation/assets/css/main.css?v=1.0.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
<div id="wrapper">
	<!--右侧部分开始-->
	<div id="page-wrapper" class="gray-bg dashbard-1">
		<div class="row border-bottom">
			<nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
				<div class="navbar-header">
					<h1 class="app-name">上海市政府立法平台</h1>
				</div>
				<ul class="nav navbar-top-links navbar-right">
                        <li class="userimg J_menuItem">
                        	<img class="img-circle" src="${basePath}/legislation/assets/img/timg.jpg"/>
                        </li>
					<li>
						<div>
							<a class="listdemo dropdown-toggle" type="button" id="infolink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
								<%=((UserInfo)session.getAttribute("currentPerson")).getName() %>
								<span class="caret"></span>
							</a>
							<ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
								<li><a href="${basePath}/legislation/login.jsp" style="border-radius:0;">安全退出</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</nav>
		</div>
		<div class="row topline">
			<a class="navbar-minimalize sidebar-toggle btn btn-success" href="#"><i class="fa fa-bars"></i></a>
			<ul class="nav-line">
				<li role="presentation" class="active" data-target="default"><a href="javascript:;"></a></li>				
			</ul>
		</div>
		<div class="row content-new">
		
			
			<!--左侧导航开始-->
			<nav class="navbar-default navbar-static-side" role="navigation" id="left-menu">
				<div class="sidebar-collapse">
					<ul class="nav" id="side-menu">
					
						<!--示例导航开始-->
						<!-- li标签里添加class="active" 会打开菜单 -->
				          <li  cat="default" >
							<a href="/">
								<i class="fa fa-database"></i>
								<span class="nav-label">立法计划管理</span>
								<span class="fa arrow"></span>
							</a>
							
						</li> 
						<li  cat="default" >
							<a href="/">
								<i class="fa fa-database"></i>
								<span class="nav-label">范本库</span>
								<span class="fa arrow"></span>
							</a>
							
						</li> 
						
				          <li  cat="default" class="active">
							<a href="/">
								<i class="fa fa-database"></i>
								<span class="nav-label">规章草案处理</span>
								<span class="fa arrow"></span>
							</a>
							<ul class="nav nav-second-level">
								<li><a class="J_menuItem" href="../legislationProcessTask/draft_deal_info.do?stNodeId=NOD_0000000103">立法办理</a></li>
								<s:iterator value="#request.nodeList" var="task">
									<li><a class="J_menuItem" href="../legislationProcessTask/${task.stInfoUrl}?stNodeId=${task.stNodeId}"><s:property value="#task.stNodeName"/></a></li>
								</s:iterator>
							</ul>
						</li> 
				</ul>
				</div>
			</nav>
			<!--左侧导航结束-->
			<div id="newContent">
				<div class="row content-tabs">
					<button class="roll-nav roll-left J_tabLeft"><i class="fa fa-backward"></i>
					</button>
					<nav class="page-tabs J_menuTabs">
						<div class="page-tabs-content">
							<a href="javascript:;" class="active J_menuTab" data-id="home.html"></a>
						</div>
					</nav>
					<button class="roll-nav roll-right J_tabRight"><i class="fa fa-forward"></i>
					</button>
					<div class="btn-group roll-nav roll-right">
						<button class="dropdown J_tabClose" data-toggle="dropdown">关闭操作<span class="caret"></span>

						</button>
						<ul role="menu" class="dropdown-menu dropdown-menu-right">
							<li class="J_tabCloseAll"><a>关闭全部选项卡</a>
							</li>
							<li class="divider"></li>
							<li class="J_tabCloseOther"><a>关闭其他选项卡</a>
							</li>
						</ul>
					</div>
				</div>
				<div class="row J_mainContent" id="content-main">
					<iframe class="J_iframe" name="iframe0" width="100%" height="100%" src="" frameborder="0" data-id="home.html" seamless></iframe>
				</div>
			</div>
		</div>
	</div>
</div>
<script src="${basePath}/legislation/assets/js/jquery.min.js?v=2.1.4"></script>
<script src="${basePath}/legislation/assets/js/bootstrap.min.js?v=3.3.5"></script>
<script src="${basePath}/legislation/assets/js/plugins/metisMenu/jquery.metisMenu.js"></script>
<script src="${basePath}/legislation/assets/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
<script src="${basePath}/legislation/assets/js/plugins/layer/layer.min.js"></script>
<script src="${basePath}/legislation/assets/js/hplus.min.js?v=4.0.0"></script>
<script type="text/javascript" src="${basePath}/legislation/assets/js/contabs.min.js"></script>
<script src="${basePath}/legislation/assets/js/plugins/pace/pace.min.js"></script>
<script src="${basePath}/legislation/assets/js/main.js"></script>
<script>
	$(function () {
		
		
		$(window).resize(function () {
			$('.content-new,#left-menu').height($('body').height() - 67);
		});
		$('.content-new,#left-menu').height($('body').height() - 67);
	});
</script>
</body>

</html>