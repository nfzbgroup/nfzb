<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>通讯录</title>
	<link rel="stylesheet" href="${basePath }/platform/contact/css/common.css" type="text/css">
	<link rel="stylesheet" href="${basePath }/platform/contact/css/tab.css" type="text/css">
	<link rel="stylesheet" href="${basePath }/platform/contact/css/obj.css" type="text/css">
	<link rel="stylesheet" href="${basePath }/platform/contact/css/input.css" type="text/css">
	<link rel="stylesheet" href="${basePath }/platform/contact/css/list.css" type="text/css">
	<link rel="stylesheet" href="${basePath }/platform/contact/css/address-css.css" type="text/css">
</head>
<body>
<table width="56%" border="0" align="center" cellpadding="10" cellspacing="0" >
	<tr>
		<td height="50"></td>
	</tr>
	<tr>
		<td>
			<table width="100%" border="0" cellspacing="1" cellpadding="2"
				style="background: url(${basePath }/platform/contact/images/bbb.jpg)"> 
				<tr>
					<td style="height: 5px;"></td>
				</tr>
				<tr>
					<td><c:forEach items="${requestScope.list}" var="item">
							<div
								style="margin: 3px 10px; height: 30px; line-height: 30px; width: 30%; text-decoration: 5px; float: right; text-align: left; font-size: 12px;">
								<img src="${basePath }/platform/contact/images/qizai.gif" width="16" height="15"
									style="margin: 0 10px; vertical-align: middle;" /> <a
									style="font-size: 18px; color: #0050d5;" href="#"
									onclick="openPersonAndUnit('${item.teamId}','${item.moduleId}')"> ${item.name}
								</a>
							</div>
						
						</c:forEach></td>
				</tr>
				<tr align="right">
					<td style="height: 20px;">
						<input type="button" style="border:none;background: url(${basePath }/platform/contact/images/button.gif)"
							 	  value="添加" title="新增" onclick="window.open('${basePath}/contact/contactAdd.do');">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<script language="JavaScript">
	function openPersonAndUnit(teamId,moduleId) {
		var id = id;
		window
				.open(
						'getContactDetail.do?teamId=' + teamId + '&moduleId=' + moduleId,
						'通讯录单位',
						'fullscreen=0,toolbar=1,location=1,directories=1,status=1,menubar=1,scrollbars=1,resizable=1,width=1024,height=740,top=0,left=0',
						true);
	}
</script>
</body>
</html>

