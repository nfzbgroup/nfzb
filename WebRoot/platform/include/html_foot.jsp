<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%
if(currentPerson==null || currentPerson.getAccount()==null)
{
	%>
	<script type="text/javascript">
	alert("用户身份认证失败！请重新登录！");
	//alert("Login denied! Please try again!");
	window.top.location = "${basePath}/index.html";
	</script>
	<%
}
%>
