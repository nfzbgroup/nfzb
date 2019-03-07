<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>简单添加页面</title>
</head>
<body>
<form action="/user/simple_add.do"  method="post">
<center>
<table width="40%"  style="border:1px solid black;">
	<tr>
		<td width="35%">帐号：</td>
		<td width="65%"><s:textfield name="userInfo.account"  placeholder="帐号"/></td>
	</tr>
	<tr>
		<td width="35%">密码：</td>
		<td width="65%"><s:textfield name="userInfo.password"  placeholder="密码"/></td>
	</tr>
	<tr>
		<td width="35%">名字：</td>
		<td width="65%"><s:textfield name="userInfo.name"  placeholder="名字"/></td>
	</tr>
	<tr>
		<td width="35%">单位：</td>
		<td width="65%"><s:select name="teamId"  list="#request.teamList"  listValue="unitName"  listKey="id"  /></td>
	</tr>
	<tr>
		<td width="35%">联系人：</td>
		<td width="65%"><s:textfield name="userInfo.abbrName"  placeholder="联系人"/></td>
	</tr>
	<tr>
		<td width="35%">联系电话：</td>
		<td width="65%"><s:textfield name="userInfo.phone"  placeholder="联系电话"/></td>
	</tr>
</table>
<button type="submit">提交</button>
</center>
</form>
</body>
</html>