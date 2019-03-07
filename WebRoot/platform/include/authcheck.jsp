<%@page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>

<%
String currentForm=(String)session.getAttribute("currentForm");
PlatformService platformService = (PlatformService)ctx.getANTBean("platformService");
if (currentForm==null && currentPerson!=null && currentPerson.getUserId()!=null){
        session.setAttribute("isDoubleRole",null);
        //currentForm=WeGov.platform.PlatformHelper.getUserLogMode(currentPerson.getUserId());
        currentForm = platformService.getUserLogMode(currentPerson.getUserId());
	if (!"".equals(currentForm)){
	   session.setAttribute("currentForm",currentForm);
	}
	if (platformService.isDoubleModeRole(currentPerson.getUserId())){
	  session.setAttribute("isDoubleRole","true");
	}
}
	String URL = request.getRequestURI();
%>


