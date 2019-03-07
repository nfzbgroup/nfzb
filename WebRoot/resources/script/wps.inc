<%@ page language="java" contentType="text/html;charset=gb2312"%>
<!-- input:arr /-->
<!-- output:outList /-->

<script language="javascript">
	function roll(pos){
		this.document.form1.pos.value=pos;
		this.document.form1.submit();
	}
</script>
	<!----------------------------------- 分页 start--------------------------------------->

<table width="680" border="0" cellspacing="0" cellpadding="0" height="20">
  <tr>
    <td align="left" width="15%" valign="bottom" ><strong> 共 <%=gw.getRecNum()%> 条信息</strong>
      <input type="hidden" name="pos" value="<%=cur%>"> </td>
    <td align="center" width="*" class="td_title">
      <%
	if(!gw.isFirstPageLink()){
%>
      <a href="javascript:roll('<%=gw.getFirstPagePos()%>');"><img src="<%=request.getContextPath()%>/admin/images/first.gif" width="12" height="15" border="0" align="absmiddle"></a>&nbsp;
      <%
	}
	else{
%>
      <img src="<%=request.getContextPath()%>/admin/images/first1.gif" width="12" height="15" border="0" align="absmiddle">&nbsp;
      <%
	}
	if(gw.isPrePageLink()){
%>
      <a href="javascript:roll('<%=gw.getPrePagePos()%>');"><img src="<%=request.getContextPath()%>/admin/images/previous.gif" width="12" height="15" border="0" align="absmiddle"></a>&nbsp;
      <%
	}else{
%>
      <img src="<%=request.getContextPath()%>/admin/images/previous1.gif" align="absmiddle" width="12" height="15">&nbsp;
      <%
	}
	for(int i=startPage;(i<=endPage && i<totalPageNum+1);i++){
		if(gw.isPageLink(i)) out.print("<a href=\"javascript:roll('"+gw.getHeadPos(i)+"')\">"+i+"&nbsp;</a>");
		else out.print(i+"&nbsp;");
	}
	if(gw.isNextPageLink()){
%>
      <a href="javascript:roll('<%=gw.getNextPagePos()%>');"><img src="<%=request.getContextPath()%>/admin/images/next.gif" title="下一页" align="absmiddle" width="12" height="15" border="0"></a>&nbsp;
      <%
	}
	else{
%>
      <img src="<%=request.getContextPath()%>/admin/images/next1.gif" alt="下一页" align="absmiddle" width="12" height="15" border="0">&nbsp;
      <%
	}
	if(!gw.isLastPageLink()){
%>
      <a href="javascript:roll('<%=gw.getLastPagePos()%>');"><img src="<%=request.getContextPath()%>/admin/images/last.gif" title="末页" align="absmiddle" width="12" height="15" border="0"></a>
      <%
	}
	else{
%>
      <img src="<%=request.getContextPath()%>/admin/images/last1.gif" title="末页" align="absmiddle" width="12" height="15" border="0">
      <%
	}
%>
      </td>
    <td align="center" width="17%" class="td_title"> 转到第&nbsp;<input type="text" name="gotoPage" size="3" maxlength="5" class="text" onKeyDown="javascript:if(event.keyCode==13){this.document.form1.submit();}">&nbsp;页</td>
    <td align="right" width="20%" valign="bottom" class="td_title"><strong>共 <%=totalPageNum%> 页 第 <%=curPage%> 页</strong></td>
  </tr>
</table>
        <!----------------------------------- 分页 end--------------------------------------->
