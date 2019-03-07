<%--
平台公共的验证登录用户页面<br>

@author Moyunzhe

@session currentPerson GET 判断登录用户是否为系统用户

@req account  NULL 用户帐号
@req password NULL 登录密码
@req addr     MUST 用户登录的ip地址，自动从requst中获取
@req host     MUST 用户登录的主机名，自动从requst中获取

--%>
<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="com.wonders.fzb.zfry.module.platform.exceptions.InvalidUserException"%>
<%@page import="com.wonders.fzb.zfry.module.platform.services.*"%>
<%
	String account = request.getParameter("username");
	if (account != null) {
		account = account.toLowerCase();
	}
	String password = request.getParameter("password");
	String addr = request.getRemoteAddr();
	String host = request.getRemoteHost();
	

	UserInfoService userInfoService = (UserInfoService) ctx.getANTBean("userInfoService");
	TeamInfoService teamInfoService = (TeamInfoService)ctx.getANTBean("teamInfoService");
	if (userInfoService.login(account, addr, host, password) != true) {
		//out.print(currentPerson.login(account, addr, host, password));
		//throw new InvalidUserException("error");
%>	
		<script type="text/javascript">
		alert("用户身份认证失败！请重新登录！");
		window.top.location = "${basePath}/index.html";
		</script>
<%
	} else {
		currentPerson = userInfoService.findUserByAccount(account);
		if((currentPerson.password).equals(currentPerson.account)){
			%>	
			<script type="text/javascript">
			alert("请点击右上角单位信息修改初始密码并维护联系人！");
			</script>
	<%
		}
		currentPerson.userIp = addr;
		currentPerson.lastLoginIp = addr;
		currentPerson.lastLoginHost = host;
		currentPerson.setTeamInfos(teamInfoService.findByUserId(currentPerson.UserInfo));
		session.setAttribute("currentPerson", currentPerson);
		if (currentPerson.getLastLoginTime() == null) {
	%>
<script language=javascript>window.open('\WeGovPlatform\admin\user\password_modify.jsp');</script>
<%
	}
	}
%>
