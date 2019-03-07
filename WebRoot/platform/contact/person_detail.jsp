<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,com.wonders.fzb.framework.beans.Contact"%>
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
<script language="javascript" src="${basePath }/platform/contact/js/common.js"></script>
<script language="javascript" src="${basePath }/platform/contact/js/ValidateFunction.js"></script>
<script language="javascript" src="${basePath }/platform/contact/js/validate.js"></script>
<%
	String msg = (String)request.getAttribute("msg");
	if(msg!=null){
%>
	<script type="text/javascript">
		alert('<%=msg%>');
	</script>
<%
	}
	Contact contact = (Contact)request.getAttribute("contact");
	if(contact==null){
		contact = new Contact();
	}
%>
</head>
<body>
	<form name="form1" enctype="multipart/form-data" method="post" action="${basePath }/contact/personModify.do">
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
			<tr>
				<td align="center" class="td_list_subTitle"></td>
			</tr>
			<tr>
				<td>
					<table width="771"
						style="width: 771px; height: 493px; background: url(${basePath }/platform/contact/images/tongxunlu_b.jpg) top center;"
						border="0" align="center" cellpadding="0" cellspacing="0">
						<tr>
							<td width="394" align="center" valign="top"><table
									width="180" border="0" cellspacing="0" cellpadding="0">
									<tr>
										<td height="60">&nbsp;</td>
									</tr>
									<tr>
										<td
											style="height: 40px; font-size: 14px; line-height: 40px; text-align: center; color: #039; font-weight: bold;">个人联系信息</td>
									</tr>
									<tr>
										<!--  data:image/jpg;base64,可以显示出JPG、PNG  -->
										<td height="273" align="center" valign="middle"><img id="upImg"
											src="<%=contact.getAvatar()==null?"D:\\fzb_file\\person\\default.jpg":"data:image/jpg;base64,"+contact.getAvatar()%>"
											width="180" height="250" onclick="chooseImg();">
											<input type="file" name="uploadFile" id="uploadFile" style="display: none">
										</td>
									</tr>
								</table></td>
							<td width="377" align="center" valign="middle">
								<table width="280" border="0" align="center" cellpadding="0"
									cellspacing="0">
									<tr>
										<td width="30%" class="td_input_addres">姓名</td>
										<td width="70%" class="td_input_addres fblue">
											&nbsp;<input type="text" class="fblue" name="name" value="<%=contact.getName()==null?"":contact.getName()%>" style="background:transparent;border:none" />
										</td>
									</tr>
									<tr>
										<td width="30%" class="td_input_addres">单位</td>
										<td width="70%" class="td_input_addres fblue">
											&nbsp;<input type="text" class="fblue" name="teamName" value="<%=contact.getTeamName()==null?"":contact.getTeamName()%>" style="background:transparent;border:none" />
										</td>
									</tr>
									<tr>
										<td width="30%" class="td_input_addres">职务</td>
										<td width="70%" class="td_input_addres fblue">
											&nbsp;<input type="text" class="fblue" name="memo" value="<%=contact.getMemo()==null?"":contact.getMemo()%>" style="background:transparent;border:none" />
										</td>
									</tr>
									<tr>
										<td width="30%" class="td_input_addres">座机</td>
										<td class="td_input_addres fblue">
											&nbsp;<input type="text" class="fblue" name="landLine" value="<%=contact.getLandLine()==null?"":contact.getLandLine()%>" style="background:transparent;border:none" />
										</td>
									</tr>
									<tr>
										<td width="30%" class="td_input_addres">手机</td>
										<td class="td_input_addres fblue f16">
											&nbsp;<input type="text" class="fblue" name="mobilePhone" value="<%=contact.getMobilePhone()==null?"":contact.getMobilePhone()%>" style="background:transparent;border:none" />
										</td>
									</tr>
									<tr>
										<td width="30%" class="td_input_addres">排序</td>
										<td class="td_input_addres fblue f16">
											&nbsp;<input type="text" class="fblue" name="position" value="<%=contact.getPosition()%>" style="background:transparent;border:none" />
										</td>
									</tr>
									<tr>
										<td width="30%" class="td_input_addres">状态</td>
										<%
											String showStatus = "";
											if("Y".equals(contact.getUserStatus())){
												showStatus = "有效";
											}else if("N".equals(contact.getUserStatus())){
												showStatus = "失效";
											} 
										%>
										<td class="td_input_addres fblue f16">
											&nbsp;<input type="text" class="fblue" name="status" value="<%=showStatus%>" style="background:transparent;border:none" />
										</td>
									</tr>
									<!-- 添加附加功能：修改信息、权限；发送消息等 -->
									<tr>
										<td colspan="2" height="20"></td>
									</tr>
									<tr>
										<td width="90%">
										</td>
										<td width="*" align="right">
											<img alt="" src="${basePath }/platform/contact/images/save_big.gif" title="保存信息" onclick="saveMessage()">
										</td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
		<input type="hidden" name="contactId" value="<%=contact.getContactId() %>" />
		<input type="hidden" name="vartar" id="vartar" value="" />
	</form>
</body>
<script type="text/javascript" src="${basePath }/platform/contact/js/jquery-1.11.3.js"></script>
<script type="text/javascript">
function chooseImg(){
	$('#uploadFile').click();
}
function saveMessage(){
	$('#vartar').val("NotVarTar");
	form1.submit();
}
$(function() {
	$("#uploadFile").change(function() {
		var path = $(this).val().substr($(this).val().lastIndexOf("."));
		if (".jpg" == path||".png" == path) {
			$("#upImg").attr("src", $(this).val());
			if(confirm("您确认修改头像吗？")){
				$("#vartar").val("vartar");
				form1.submit();
			}
		} else {
			alert("仅支持JPG、PNG格式文件。");
			$(this).val("");
		}
	});
});
</script>
</html>