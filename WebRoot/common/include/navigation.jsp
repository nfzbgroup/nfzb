<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target=".navbar-collapse">
				<span class="sr-only">Toggle navigation</span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span> 
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#"><b>法制办平台监管系统</b></a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<%
					String naviga = request.getAttribute("naviga") + "";
				%>
				
				<li <% if(null == naviga || naviga.equals("module")){ %> class='active' <%} %>><a href="${basePath}/logging/module/to_module.do">系统模块管理</a></li>
				<li <% if(null != naviga && naviga.equals("team")){ %> class='active' <%} %>><a href="${basePath}/logging/team/to_list.do">组织机构管理</a></li>
				<li <% if(null != naviga && naviga.equals("user")){ %> class='active' <%} %>><a href="${basePath}/admin/user/to_list.do">用户管理</a></li>
				<li class="dropdown">
                	<a href="javascript:void(0);" class="dropdown-toggle" data-toggle="dropdown">
                   		其他模块<b class="caret"></b>
                	</a>
               		<ul class="dropdown-menu">
               			<li class="dropdown-header">System Manager</li>
                    	<li><a href="#">系统管理员管理</a></li>
                    	<li class="divider"></li>
                    	<li class="dropdown-header">统计查询</li>
                    	<li><a href="#">统计列表&导出</a></li>
                    	<li><a href="#">统计图表&打印</a></li>
                	</ul>
            </li>
			</ul>
			 <form class="navbar-form navbar-right" role="search">
				<div class="input-group">
					<span class="input-group-addon"><span class="glyphicon glyphicon-search"></span></span>
					<input id="findInResult" type="text" class="form-control" placeholder="在结果中查询" />
				</div>
			</form>
		</div>
	</div>
</div>