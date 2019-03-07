<%@page import="com.wonders.fzb.framework.beans.vo.UserInfoBean"%>
<%@page import="com.wonders.fzb.base.beans.Page,com.wonders.fzb.base.kit.StringKit"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib uri="/wonders/common" prefix="z" %>
<!DOCTYPE html>
<html>
<head>
<s:include value="/common/header.jsp" />
<title>法制办平台监管系统</title>
<link rel="stylesheet" href="../styles/admin.css" />
</head>
<body>
	<!-- navigation -->
	<s:include value="/common/include/navigation.jsp" />
	<br />
	<br />
	<br />
	<div class="container-fluid">	
		<form id="mainFrm" class="form-horizontal" role="form" action="${basePath}/admin/user/to_list.do" method="post">
			<input type="hidden" id="pageNo" name="pageNo"  />
			<div class="form-group">
				<label class="col-sm-2 control-label">用户名</label>
   				 <div class="col-sm-3">
   				 	<div class="input-group">   				 	
   				 	<input class="form-control" name="account" placeholder="用户名"  value='<s:property value="#request.account" />'  />
   				 	<span class="input-group-btn">
   				 		<button class="btn btn-default" type="button" onclick="$(this).parent().prev().val('');">
  				 			<span class="glyphicon glyphicon-remove"></span>
   				 		</button>
   				 	</span>
   				 	</div>
    			</div>
    			
    			<label class="col-sm-2 control-label">单位</label>
   				 <div class="col-sm-3">
					<div class="input-group">
   				 	<input class="form-control" name="unit" placeholder="所属单位"  value="<s:property value='#request.unit' />" />
   				 	<span class="input-group-btn">
   				 		<button class="btn btn-default" type="button" onclick="$(this).parent().prev().val('');">
  				 			<span class="glyphicon glyphicon-remove"></span>
   				 		</button>
   				 	</span>
   				 	</div>
    			</div>
			</div>
			
			 <div class="form-group">
				<label class="col-sm-2 control-label">联系人</label>
   				 <div class="col-sm-3">
   				 	<div class="input-group">
						<input type="text" name="contact"  placeholder="联系人" class="form-control"  value="<s:property value='#request.contact' />"/>
						<span class="input-group-btn">
   				 			<button class="btn btn-default" type="button" onclick="$(this).parent().prev().val('');">
  				 				<span class="glyphicon glyphicon-remove"></span>
   				 			</button>
   				 		</span>
					</div>
    			</div>
    			
    			<label class="col-sm-2 control-label">联系电话</label>
   				 <div class="col-sm-3">
   				 	<div class="input-group">
						<input type="text" name="phone"  placeholder="联系电话" class="form-control"  value="<s:property value='#request.phone'  />"/>
						<span class="input-group-btn">
   				 			<button class="btn btn-default" type="button" onclick="$(this).parent().prev().val('');">
  				 				<span class="glyphicon glyphicon-remove"></span>
   				 			</button>
   				 		</span>
					</div>
    			</div>
			</div>
			
			<div class="form-group">
				<label class="col-sm-2 control-label">归属系统</label>
   				 <div class="col-sm-3">
   				 	<s:select name="condModule"  class="form-control"  list="#request.moduleMap" value="#request.condModule"  listValue="key"  listKey="value"  />
    			</div>
    			
    			 <label class="col-sm-2 control-label">帐号状态</label>
   				 <div class="col-sm-3">
   				 	<s:select name="accountStatus"  class="form-control"  list="#request.accountStatusMap" value="#request.accountStatus"  listValue="key"  listKey="value"  />
    			</div>
			</div> 
			<center>
				<button class="btn btn-default">查询</button>
			</center>
			
		</form>

		<div class="table-responsive">
			<div class="pull-right">
				<button id="add_user"  class="btn btn-primary" type="button">
					<span class="glyphicon glyphicon-plus"></span>&nbsp;添加用户
				</button>
			</div>
			<table class="table table-hover table-striped">
				<thead>
					<tr>
						<th>名字</th>
						<th>帐号</th>
						<th>密码</th>
						<th>系统<b class="up-arrow"></b></th>
						<th>单位<b class="up-arrow"></b></th>
						<th>联系人</th>
						<th>联系电话</th>
						<th>状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<%
						Page<UserInfoBean> userListPage = (Page)request.getAttribute("userList");
						for(UserInfoBean bean : userListPage.getResult()){
					 %>
					 <tr>
					 	<td><%=bean.getName() %></td>
					 	<td><%=bean.getAccount() %></td>
					 	<td><%= StringKit.AESDecode(bean.getPassword(), "fzb_user_"+bean.getSalt()) %> <!-- <button type="button" class="btn btn-link btn-xs" title="查看密码"><span class="glyphicon glyphicon-zoom-in"></span></button> --></td>
					 	<td><%=bean.getModuleName() %></td>
					 	<td><%=bean.getTeamName() %></td>
					 	<td><%=bean.getAbbr() %></td>
					 	<td><%=bean.getPhone() %></td>
					 	<td>
					 		<%
					 			if("y".equals(bean.getAccountStatus())){
					 		 %>
					 				<span class="label label-success">有效</span>
					 		<%
					 			}else{
					 		 %>
					 		 		<span class="label label-warning">禁用</span>
					 		 <%
					 		 	}
					 		  %>
					 	</td>
					 	<td>
					 		<button type="button" class="btn btn-xs btn-info">查看/修改</button>
					 		<div class="btn-group btn-group-xs">
    							<button type="button" class="btn btn-danger">删除用户</button>
    							<%
    								if(bean.getOurId() != null && !"".equals(bean.getOurId())){
    							 %>
    									<button name="btnRemove" type="button" class="btn btn-warning" data-param="<%=bean.getOurId() %>">从系统/单位中移除</button>
    							<%
    								}
    							 %>
							</div>
    						<%
					 			if("y".equals(bean.getAccountStatus())){
					 		 %>
    							<button type="button" class="btn btn-warning btn-xs ">禁用</button>
					 		<%
					 			}else{
					 		 %>
					 		 		<button type="button" class="btn btn-success btn-xs">启用</button>
					 		 <%
					 		 	}
					 		  %>
						</td>
					 </tr>
					 <%} %>
				</tbody>
			</table>
		</div>
		
	</div>
	<s:include value="/common/include/admin_modal.jsp" />
</body>
<s:include value="/common/scripts.jsp" />
<script language="javascript">
		$.include(['easysearch']);
		$(function(){
			$("#add_user").click(function(){
				//data-toggle="modal" data-target="#admin-modal" 
				$("#admin-modal-title").text("添加用户");
				$("#admin-modal-view").attr("src","${basePath}/admin/user/to_add.do");
				$("#admin-modal").modal('show').on('hidden.bs.modal', function () {
  						$("#admin-modal-view").attr("src","");
				});
			});
			
			$("[name='btnRemove']").click(function(){
				if(confirm("是确认将该用户移出现有组织/系统？")){
					$.ajax({
						url:"${basePath}/admin/user/remove.do",
						type:"post",
						data:"ourId="+$(this).attr("data-param"),
						success:function(){
							$("#mainFrm").submit();
						}
					});
				}
			});
			
			$('#findInResult').jSearch({ 
	    		selector  : 'table',
	    		child : 'td',
	    		minValLength: 0,
	   			Before: function(){
	    			$('table tr').data('find','');
	    		},
	    		Found : function(elem, event){
	    			$(elem).parent("tr").data('find',true);
	    			$(elem).parent("tr").show();
	   			},
	    		NotFound : function(elem, event){
	    			if (!$(elem).parent("tr").data('find')){
	       				$(elem).parent("tr").hide();
	       				$(elem).parent("tr").next().hide();
	    			}
	   			},
	    		After : function(t){
	        		if (!t.val().length) $('tr').show();
	    		}
			});	
		});
</script>
</html>