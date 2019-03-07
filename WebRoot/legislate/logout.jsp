<%@page language="java" contentType="text/html;charset=UTF-8"%>
<%
//request.getSession().invalidate();
session.removeAttribute("currentPerson");
session.removeAttribute("errorCode");
%>
<script>   
      window.top.location="login.jsp";
</script>