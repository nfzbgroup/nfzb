<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*,com.wonders.fzb.framework.beans.Contact,com.wonders.fzb.base.beans.Page"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>通讯录</title>
<link rel="stylesheet" href="${basePath }/platform/contact/css/common.css" type="text/css">
<link rel="stylesheet" href="${basePath }/platform/contact/css/tab.css" type="text/css">
<link rel="stylesheet" href="${basePath }/platform/contact/css/obj.css" type="text/css">
<link rel="stylesheet" href="${basePath }/platform/contact/css/input.css" type="text/css">
<link rel="stylesheet" href="${basePath }/platform/contact/css/list.css" type="text/css">
<link rel="stylesheet" href="${basePath }/platform/contact/css/address-css.css" type="text/css">
<script src="${basePath }/platform/contact/js/common.js"></script>
<script src="${basePath }/platform/contact/js/validate.js"></script>
<script src="${basePath }/platform/contact/js/ValidateFunction.js"></script>
<script type="text/javascript" src="${basePath }/platform/contact/js/jquery-1.11.3.js"></script>
<style>

.demoBg {
	background:#cce6ff;
}
</style>
<script type="text/javascript">
//隔行换色
$(function(){
	//若为table 替换为table tr 即可。

	$(".demo table tr").mouseover(function(){
		$(this).addClass("demoOver");
	}).mouseout(function(){
		$(this).removeClass("demoOver");
	})
	//悬浮选中
	$(".demo table tr:even").addClass("demoBg");
})
</script> 
<%
	String pageNoStr = (String)request.getAttribute("pageNo")==null?"1":(String)request.getAttribute("pageNo");
	String pageSizeStr = (String)request.getAttribute("pageSize")==null?"10":(String)request.getAttribute("pageSize");
	int pageSize = Integer.parseInt(pageSizeStr);
	int pageNo = Integer.parseInt(pageNoStr);
	Page<Contact> retPage = (Page<Contact>)request.getAttribute("retPage");
	if(retPage==null){
		retPage = new Page<Contact>();
	}
	List<Contact> contacts = (List<Contact>)request.getAttribute("contacts");
	if(contacts==null){
		contacts = new ArrayList<Contact>();
	}
%>
</head>
<body style="background:#bfbfbf;">
<form action="${basePath }/contact/getContactDetail.do" name="form1" type="post">
<table width="1225" border="0" align="center" cellpadding="0" cellspacing="0">
        <tr>
        	<td height="5"></td>
        </tr>    

        <tr>
        	<td height="5"></td>
        </tr>
    	<tr>
      		<td width="98%">
      		<table border="0" cellpadding="0" cellspacing="0" align="center" width="900" style="background:#FFF;">
        		<tr>
          			<td>&nbsp;</td>
        		</tr>
        		<tr>  
          			<td align="center">

                  <div class="demo">
                		<table width="1200" border="0" cellspacing="0" cellpadding="0" class="tab_addres">
                     		<tr>
				                <th width="60">姓名</th>
				                <th width="120">部门</th>
			                    <th width="110">职务</th>
								<th width="75">座机</th>
			                    <th width="110">手机</th>
			                    <th width="120">邮箱</th>
			                    <th width="105">地址</th>
								<th width="96">状态</th>
				             </tr>
				             <!--<c:forEach items="${contacts}" var="dt"> </c:forEach>-->
				             <%
				             	List<Contact> contactList = retPage.getResult();
				             	for(int i=0;i<contactList.size();i++){
				             		Contact contact = contactList.get(i);
				             %>
								<tr onClick="viewCommunicatee('<%=contact.getContactId()==null?"":contact.getContactId() %>');" style="cursor:hand"> 
									<td class="p5" align="center"><%=contact.getName()==null?"":contact.getName()%></td>
									<td align="center"><%=contact.getTeamName()==null?"":contact.getTeamName() %></td>
									<td class="f16" align="center"><%=contact.getMemo()==null?"":contact.getMemo() %></td>
									<td class="f16" align="center"><%=contact.getLandLine()==null?"":contact.getLandLine() %></td>
									<td class="f16 p5" align="center"><%=contact.getMobilePhone()==null?"":contact.getMobilePhone() %></td>
									<td class="f16 p5" align="center"><%=contact.getEmail()==null?"":contact.getEmail() %></td>
									<td class="f16 p5" align="center"><%=contact.getAddress()==null?"":contact.getAddress() %></td>
									<%
											String showStatus = "";
											if("Y".equals(contact.getUserStatus())){
												showStatus = "有效";
											}else if("N".equals(contact.getUserStatus())){
												showStatus = "失效";
											} 
										%>
									<td class="f16 p5" align="center"><%=showStatus %></td>
								  </tr>
							<%} %>
                		</table>
                </div>
 			</td>
        </tr>      
        <tr>
        	<td height="30">
        		<table width="100%" border="0" cellspacing="0" cellpadding="0" align="center" style="background:#FFF;">
							<tr>
								<td ><%@include file="/platform/include/pagelinks.jsp"%></td>
							</tr>
				</table>
        	</td>
        </tr>    
      </table></td>
</tr></table>
</form>
</body>
<script language="javascript">
	function viewCommunicatee(id){
		window.open('${basePath}/contact/getPersonDetail.do?contactId='+id,'查看详情','fullscreen=0,toolbar=0,location=0,directories=0,status=0,menubar=0,scrollbars=1,resizable=0,width=1024,height=660,top=0,left=448',true);
	}
</script>
</html>
