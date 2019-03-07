<%@page language="java" contentType="text/html;charset=UTF-8"%><%@ page import="java.util.*,
		java.net.*,
		java.text.*,
		java.io.*,
		wfc.facility.util.file.*,
		com.wonders.fzb.base.beans.*,
	com.wonders.fzb.base.consts.*,
    com.wonders.fzb.platform.*,
    com.wonders.fzb.platform.beans.*,
    com.wonders.fzb.platform.compose.*,
    com.wonders.fzb.platform.exceptions.*,
    com.wonders.fzb.platform.kit.*,
    com.wonders.fzb.platform.services.*,
	com.wonders.fzb.base.kit.StringKit"
%><%@ include file="/platform/include/usebean.jsp"%><%

byte[] buf=null;
String docId = request.getParameter("docId");
String docType = request.getParameter("docType");
String versionId = request.getParameter("versionId");
if(versionId==null)
	versionId = "1";
String fromTemplate = request.getParameter("fromTemplate"); 
//UserInfo currentUser = (UserInfo) session.getAttribute("currentPerson");
String userId = currentPerson.getUserId();

//if (docId!=null)
//	docId = docId.trim();
//if (versionId!=null)
//	versionId = versionId.trim();//-1时代表新增版本，null时表示不支持版本

//com.wonders.fzb.base.kit.SpringKit ctx = (com.wonders.fzb.base.kit.SpringKit) session.getAttribute("ctx");
ComposeServer composeServer = (ComposeServer)ctx.getANTBean("composeServer");
//try{
//	composeServer.getComposer(docType);
//}catch(Exception e){
//}
Composer composer = composeServer.getComposer(docType);
if(composer==null){
	if (docType!=null)
		docType = new String(docType.getBytes("iso-8859-1"),"UTF-8").trim();
	composer = composeServer.getComposer(docType);
}
if (composer!=null){
	if (CommonConst.YES.equals(fromTemplate)){
		//调用composer的getDocTemplate方法获得doc文档 
		buf = composer.getDocTemplate(docId,docType,session.getServletContext().getRealPath("/"),null);
	}else {
		//调用composer的getDocFile方法获得doc文档 
		buf = composer.getDocFile(docId,userId,versionId,null);
	}
	long fileLength = buf==null?0:buf.length;
	String contentType = "application/octet-stream;charset=utf-8";

    OutputStream os = null;  
    try {  
        	response.reset();
            response.setContentType(contentType);  
            response.setHeader("Content-disposition", "attachment; filename=" + URLEncoder.encode("docstar.doc","utf-8"));  
            response.setHeader("Content-Length", String.valueOf(fileLength)); 
    		os = new BufferedOutputStream(response.getOutputStream());   
            os.write(buf);
            os.flush();
    } catch (Exception e) {  
            e.printStackTrace();  
    } finally {  
            if (os != null)  
            	os.close();  
            //加上下面两行，不会报getOutputStream() has already been called
            out.clear();
            out = pageContext.pushBody();
    }  
}%>