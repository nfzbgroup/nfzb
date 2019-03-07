<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/platform/include/import.jsp"%>
<%@ include file="/platform/include/usebean.jsp"%>
<%@ include file="/platform/include/authcheck.jsp"%>
<%@ include file="/platform/include/html_head.jsp"%>
<%@ include file="/platform/scripts/button_init.inc"%>
<html>
<head>
	<title>签报件登记<%//=sendType%></title>
	<meta http-equiv="Pragma" content="no-cache">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<form name="form1" action="" method="post">
<table align="center" width="600" cellspacing="0" cellpadding="0" style="margin-top:30px;">
<tr>
	<td valign="top" align="center" width="75%" height="30"><span class="title">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbspNTKO测试</span></td>
</tr>
</table>

<br>
<center>
<table border="0" cellspacing="0" cellpadding="0">
	<tr> 
	 <%
	 	String docId = "4aa6845c5cc5115a015cc553ef96000a";
	 	String pageTitle = "";
		String docType = "草案分办单";
	    String fromTemplate = "Y";
	 
        button_text = "编 辑";
        button_name = "word_button";
	    button_url = "javascript:window.open('../platform/composer/compose_doc.jsp?docId="+docId+"&docType="+java.net.URLEncoder.encode(docType,"UTF-8")+"&fromTemplate="+fromTemplate+"&pageTitle="+java.net.URLEncoder.encode(pageTitle,"UTF-8")+"','doc_compose"+docId+"','');";
      %><td><%@ include file="../platform/scripts/button_table.inc"%></td>  
 
    <%
		button_text = "关 闭";
		button_name = "close_button";
		button_url = "window.close();";
		%><td><%@ include file="../platform/scripts/button_table.inc"%></td>
        </tr>
</table>
</center>

<input type="hidden" name="docId" value="">
</form>
</body>
</html>
<%@ include file="/platform/include/html_foot.jsp"%>