<%@page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<% 
int minPage = 1;								//最小页
int maxPage = retPage.getTotalPageCount();	//最大页
int pageSpanSize = 4;							//页码显示范围，即以当前页面为中心，左右各显示的页码数目
int startPage = pageNo-pageSpanSize;			//显示开始页
if(startPage<minPage)
	startPage = minPage;
int endPage = pageNo+pageSpanSize;				//显示结束页

if(endPage>maxPage)
	endPage = maxPage;
%>
<table align="center" style="height:30;" width="95%">
	<tr valign="middle">
		<td  align="right">共 <%=retPage.getTotalSize()%> 条记录</td>
		<td align="center">
			<a href="javascript:{document.form1.pageNo.value=<%=minPage%>;form1.submit();}">首页</a>
						<a href="<%if(pageNo-1<minPage){%>#<%}else{%>javascript:{document.form1.pageNo.value=<%=pageNo-1%>;form1.submit();}<%}%>">上一页</a>
					<%
					for(int j=startPage;j<=endPage;j++){
					%>
						<%if(j==pageNo){%>
						<font color="red"><%=j%></font>
						<%}else{%>
						<a class="pageCurrent" href="javascript:{document.form1.pageNo.value=<%=j%>;form1.submit();}"><%=j%></a>
						<%}%>
					<%
					}
					%>
						<a href="<%if(pageNo+1>maxPage){%>#<%}else{%>javascript:{document.form1.pageNo.value=<%=pageNo+1%>;form1.submit();}<%}%>">下一页</a>
						<a href="javascript:{document.form1.pageNo.value=<%=maxPage%>;form1.submit();}">末页</a>
		</td>
		<td align="left">共 <%=maxPage%> 页 第 <%=pageNo%> 页</td>
	</tr>
</table>
<input type="hidden" name="pageNo" id="pageNoHidden" value="<%=pageNo%>">
<input type="hidden" name="displayIndex" id="displayIndexHidden">