<%@page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=10,9,8">
<meta name="force-rendering" content="ie-stand">
<meta name="renderer" content="ie-stand" />
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no">
<title></title>
</head>
<body>
	<form name="form1" id="form1" action="${basePath}/portal/userListzishubuding.do" method="post">
		<input type="hidden" name="op" id="op" />
		<div class="examIndex-mainBox" style="margin: 0">
			<ul class="examIndex-tab">
			</ul>
			<div class="examIndex-contentBox" style="display: block;">
				<div class="examIndex-contentBody">
					<select name="moduleType" id="moduleType">
						<option value="all" <s:if test='moduleType=="all"'  > selected="selected" </s:if>>全部</option>
						<option value="execlaw_user" <s:if test='moduleType=="execlaw_user"'  > selected="selected" </s:if>>执法</option>
						<option value="legislate_user" <s:if test='moduleType=="legislate_user"'  > selected="selected" </s:if>>立法</option>
						<option value="normaldoc_user" <s:if test='moduleType=="normaldoc_user"'  > selected="selected" </s:if>>规范</option>
						<option value="review_user" <s:if test='moduleType=="review_user"'  > selected="selected" </s:if>>复议</option>
					</select>
					<table width="1200px" border="1" align="center">
						<s:iterator value="userInfoList" id="info" status="number">
							<tr>
								<td>
									<span>
										<s:property value="#number.index+1" />
									</span>
								</td>
								<td width="10%">
									<a href="${basePath}/execlaw/log.jsp?username=<s:property value="#info.account" />&password=<s:property value="decrypt(#info.password,#info.salt)" />" target="_blank">
										<span>
											<s:property value="moduleName(#info.salt)" />
										</span>
									</a>
								</td>
								<td>
									<span>
										<s:property value="#info.name" />
									</span>
								</td>
								<td>
									<span name="account">
										<s:property value="#info.account" />
									</span>
								</td>
								<td>
									<span name="password">
										<s:property value="decrypt(#info.password,#info.salt)" />
									</span>
								</td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<div class="examIndex-contentFoot">
					<div class="contentFoot-buttonGroup"></div>
				</div>
			</div>
		</div>
		<input type="hidden" id="status" name="status" value="<s:property value="#request.status" />">
	</form>
</body>
<script src="./js/jquery-1.12.0.min.js"></script>
<script>
	$(function() {
		//console.info($("#password"));
		//console.info($("#account"));

		/*$("span[name='account']").each(function() {
			console.info($(this).text());
		});
		 */
		$("#moduleType").change(function() {//code...}); //为Select添加事件，当选择其中一项时触发	
			form1.submit();
		});
	});
</script>
</html>