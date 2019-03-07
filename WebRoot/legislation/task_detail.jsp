<%@page language="java" contentType="text/html;charset=UTF-8"  pageEncoding="UTF-8"%>
<%@include file="/normaldoc/include/import.jsp"%>
<%@include file="/platform/include/usebean.jsp"%>
<html>
<head>
<title></title>
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

NormalDoc normalDoc =(NormalDoc)request.getAttribute("normalDoc");

String reportNdocId=(String)request.getAttribute("reportNdocId")==null?"":(String)request.getAttribute("reportNdocId");



%>

<body>



<form name="form1"  action="${basePath}/normaldoc/accept_add.do" method="post">
<div class="page-container">
    <div class="pageHeader">
        <div class="pull-left">
            <img src="${basePath}/normaldoc/images/bg-pageHeader-pc.png">
          
            <a>收文登记</a>
           
        </div>
       
    </div>
    
    <div class="pageBody clearfix ">
       
       
            <div class="form-container">  
                <div class="form-title">
                    <h4>收文登记</h4>
                </div>
                <div class="form-content form-horizontal">
                     <div class="form-group">
                        <label class="col-sm-2 control-label">收文日期：</label>
                        <div class="col-sm-4">
                            <input type="text" style="width:100%" class="form-control datePicker form-control-inline"  name="recvDate" onclick="WdatePicker({})"
                            value="<%=normalDoc.getRecvDate()==null?"":dFormat.format(normalDoc.getRecvDate())%>">
                        </div>
                        <label class="col-sm-2 control-label">收文号：</label>
                        <div class="col-sm-4">
							<span>沪府法备案字(</span><input style="width:70px" type="text" class="form-control form-control-inline" placeholder="" name="recvYear"  value="<%=normalDoc.getRecvYear()==null?"":normalDoc.getRecvYear()%>"><span>)第</span>
							<input style="width:50px" type="text" class="form-control form-control-inline" placeholder="" name="recvNum" value="<%=normalDoc.getRecvNum()==null?"":normalDoc.getRecvNum()%>"><span>号</span>
						</div>
                    </div> 
                  
                      
                    <div class="form-group">
                    <label class="col-sm-2 control-label">文件名称：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" placeholder="" name="fileName"
                            value="<%=normalDoc.getFileName()==null?"":normalDoc.getFileName()%>">
                        </div>
                        
                         <label class="col-sm-2 control-label">文号：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control "  name="fileNo"
                            value="<%=normalDoc.getFileNo()==null?"":normalDoc.getFileNo()%>">
                        </div>
                         
                    </div>
                       <div class="form-group">
                    <label class="col-sm-2 control-label">报备单位：</label>
                        <div class="col-sm-4">
                          <select name="submitUnitId">
                            <option value="">请选择类型</option>
                            <option value="NMD_UNITTYPE01_001" <%if("NMD_UNITTYPE01_001".equals(normalDoc.getSubmitUnitId())) {%> selected <%}%>><%=normalDoc.getSubmitUnitName() %></option>
                           
                         </select> 
                           
                        </div>
                    
                         <label class="col-sm-2 control-label">发布日期：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control datePicker" placeholder="" name="publicDate" onclick="WdatePicker({})"
                            value="<%=normalDoc.getPublicDate()==null?"":dFormat.format(normalDoc.getPublicDate())%>">
                        </div>
                         
                    </div>
                     <div class="form-group">
                         <label class="col-sm-2 control-label">生效日期：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control datePicker" placeholder="" name="executeDate" onclick="WdatePicker({})"
                            value="<%=normalDoc.getExecuteDate()==null?"":dFormat.format(normalDoc.getExecuteDate())%>">
                        </div>
                         <label class="col-sm-2 control-label">失效日期：</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control datePicker" placeholder="" name="executeEndDate" onclick="WdatePicker({})"
                            value="<%=normalDoc.getExecuteEndDate()==null?"":dFormat.format(normalDoc.getExecuteEndDate())%>">
                        </div>
                    </div>

                    <div class="form-group module-radio radioListStyle2">
                        <label class="col-sm-3 control-label">2010清理补报备：</label>
                        
                            <div class="col-sm-3">
                                <% if("S".equals(normalDoc.getIsSupply())){%>
                            	<label class="radio-label sld" value="T">
                                <i class="radio-img "></i><span>是</span>
                               
                            </label>
                            <% }else{%>
                            	<label class="radio-label " value="T">
                                <i class="radio-img "></i><span>是</span>
                               
                            </label>
                             <%} %>
                             
                             <% if("F".equals(normalDoc.getIsSupply())){%>
                            	<label class="radio-label sld" value="F">
                                <i class="radio-img "></i><span>否</span>
                               
                            </label>
                            <% }else{%>
                            	<label class="radio-label " value="F">
                                <i class="radio-img "></i><span>否</span>
                               
                            </label>
                             <%} %>
                                <input type="hidden" value="<%=normalDoc.getIsSupply() %>" name="isSupply" >
                            </div>
                       
                       
                    </div> 

                   
                    
                    </div>
                </div>
              <div class="module-btnGroup">
                            <a class="btn-color2 " onClick="javascript:save('send');" >完成</a>
            </div>
        </div>
    </div>
</div>

<input type="hidden" name="op" > 
<input type="hidden" name="reportNdocId" value="<%=reportNdocId%>">

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

//添加保存
function save(op) {

		
		var errorLog='';
		 if(form1.recvDate.value==""){
			 errorLog+="收文日期不能为空 \n";
		 }
		 if (form1.recvYear.value=="") {
				errorLog+="收文年号不能为空! \n";
		}
		 if (form1.recvNum.value=="") {
				errorLog+="收文号不能为空! \n";
		}
		 if(form1.fileName.value==""){
			 errorLog+="文件名称不能为空 \n";
		 }
		  if(form1.fileNo.value==""){
			  errorLog+="文号不能为空 \n";
		 }
		 if(form1.submitUnitId.value==""){
			 errorLog+="报备部门不能为空 \n";
		 }
		  if(form1.publicDate.value==""){
			  errorLog+="发布日期不能为空 \n";
		 }
		 if(form1.executeDate.value==""){
			 errorLog+="生效日期不能为空 \n";
		 }
		 if(form1.executeEndDate.value==""){
			 errorLog+="失效日期不能为空 \n";
		 }
		
		 
		
		 if(errorLog!=""){
			 alert(errorLog);
			 return false;
		 }
		 if(confirm("是否完成")){
			this.form1.op.value = op;
			this.form1.submit();	
		}
	
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