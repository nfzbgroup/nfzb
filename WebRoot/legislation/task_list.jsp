<%@page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="/normaldoc/include/import.jsp"%>
<%@include file="/platform/include/usebean.jsp"%>
<html>
<head>
<title>title</title>
<%@include file="/platform/include/html_head.jsp"%>
<%@include file="/normaldoc/include/importJava.jsp" %>
<link href="${basePath}/normaldoc/styles/bootstrap.min.css" rel="stylesheet" type="text/css">
<link href="${basePath}/normaldoc/styles/main.css" rel="stylesheet" type="text/css">

  <!--[if lt IE 9]>  
<script src="${basePath}/normaldoc/resources/scripts/html5shiv.min.js"></script>
<script src="${basePath}/normaldoc/resources/scripts/respond.min.js"></script>
<![endif]-->
</head>
<%
SimpleDateFormat dFormat = new SimpleDateFormat("yyyy-MM-dd");
String pageSizeString = (String)request.getAttribute("pageSize")==null?"10":(String)request.getAttribute("pageSize");
String pageNoString = (String)request.getAttribute("pageNo")==null?"1":(String)request.getAttribute("pageNo");
String status = (String)request.getAttribute("status")==null?"N":(String)request.getAttribute("status");
String titleName = (String)request.getAttribute("titleName")==null?"":(String)request.getAttribute("titleName");
String startDate = (String)request.getAttribute("startDate")==null?"":(String)request.getAttribute("startDate");
String endDate = (String)request.getAttribute("endDate")==null?"":(String)request.getAttribute("endDate");
int pageSize = Integer.parseInt(pageSizeString);
int pageNo = Integer.parseInt(pageNoString);
Page retPage = (Page)request.getAttribute("retPage");
%>
<body>
<link href="${basePath}/normaldoc/styles/main.css" rel="stylesheet" type="text/css">
<form name="form1"  action="${basePath}/normaldoc/accept_list.do" method="post">
<div class="page-container">
    <div class="pageHeader">
        <div class="pull-left">
            <img src="${basePath}/normaldoc/images/bg-pageHeader-pc.png">
            
            <a>文件报备接收</a><span>&gt;</span>
            <a class="cur">查询列表</a>
        </div>
      
    </div>

    <div class="pageBody clearfix ">
         <ul class="contentBox-title">
        <%if ("N".equals(status)) {%> 
            <li class="current">
                <a><span>未接收</span></a>
            </li>
          <%}else{%>
			  <li class="" onclick="javascript:search('N');">
                <a><span>未接收</span></a>
            </li>
		<%}%> 
		
		<%if ("Y".equals(status)) {%> 
            <li class="current">
                <a><span>已接收</span></a>
            </li>
          <%}else{%>
			  <li class="" onclick="javascript:search('Y');">
                <a><span>已接收</span></a>
            </li>
		<%}%>  
         
           
        </ul>
        <div class="module-filterBox">
            <div>
                
                <input style="width:28%;" type="text" placeholder="请输如标题" name="titleName" value="<%=titleName%>">
                 <span>起草时间:</span>
                <input type="text" class="datePicker" onclick="WdatePicker({})" placeholder="请选择日期范围" name="startDate" value="<%=startDate%>" >
                <span>至</span>
                <input type="text" class="datePicker" onclick="WdatePicker({})" placeholder="请选择日期范围" name="endDate" value="<%=endDate%>">
                <a class="btn btn-search"  onClick="form1.submit()">搜 索</a>
                
            </div>
        </div>
        <div class="contentBox-list">
            <table class="table-striped-blue tableS2">
                <thead>
                <tr>
                    <th><span>标题</span></th>
                    <th><span>起草时间</span></th>
                    <th><span>操作</span></th>
                </tr>
                </thead>
                <tbody>
                 <%
                    List retList = retPage.getResult();
                   for(int i=0;i<retList.size();i++){
                	   NormalReportDoc normalReportDocBean =(NormalReportDoc)retList.get(i);
                 %>	
                 <tr>
                   <td><p><%=normalReportDocBean.getNdocName()==null?"":normalReportDocBean.getNdocName()%></p></td>
                    <td><%=normalReportDocBean.getIssueDate()==null?"":dFormat.format(normalReportDocBean.getIssueDate())%></td>
                    <td>
                       <%if ("N".equals(status)) {%> 
                       <a onclick="reportDocAdd('<%=normalReportDocBean.getReportNdocId()%>')">办理</a>
                      <%}else{%>
			          <a onclick="reportDocRead('<%=normalReportDocBean.getReportNdocId()%>')">查看</a>
		              <%}%> 
                  
                    </td>
                   </tr>
                 <%}%>
              
              
           
                </tbody>
            </table>
        </div>
        <%@include file="/normaldoc/include/pagelinks.jsp"%>
    </div>
</div>
<input type="hidden" name="status" value="<%=status%>">
</form>

<div class="modal-example modal fade">
    <div class="modal-dialog">
        <div class="modal-content">
        </div>
    </div>
</div>

<p>&nbsp;</p>
</body>

<%@include file="/normaldoc/include/scripts.jsp"%>

<script language="javascript">
//日期控件JS
$.include(["my97"]);


function search(status){
	this.form1.status.value = status;
	this.form1.submit();
}

function reportDocAdd(reportNdocId){
	this.form1.action="${basePath}/normaldoc/accept_process.do?reportNdocId="+reportNdocId;
	this.form1.submit();
	
}

function reportDocRead(reportNdocId){
	this.form1.action="${basePath}/normaldoc/accept_read.do?reportNdocId="+reportNdocId;
	this.form1.submit();
	
}


//关闭窗口
function closewindows(){
	OpenerReload();
	window.close();
}

function OpenerReload(){
	if(window.opener.document.condForm!=null)
		window.opener.document.condForm.submit();
	else if(window.opener.document.form1!=null)
		window.opener.document.form1.submit();
	else if(window.opener.document.conditionForm!=null)
		window.opener.document.conditionForm.submit();
	else
		window.opener.location.reload();
}
</script>
</html>
<%@include file="/normaldoc/include/html_foot.jsp"%>