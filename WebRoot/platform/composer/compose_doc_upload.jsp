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
%><%@ include file="/platform/include/import.jsp"%><%@ include file="/platform/include/usebean.jsp"%><%
try {
	 WebFileManager wfm = WebFileManager.getInstance(pageContext);
	 wfm.upload();
	 ByteArrayOutputStream baos = new ByteArrayOutputStream();
	 WebFileRequest wfr = wfm.getRequest();

     String docId = wfr.getParameter("docId");System.out.println("docId============"+docId);
     String docType = wfr.getParameter("docType");System.out.println("docType============"+docType);
     docType = URLDecoder.decode(docType,"UTF-8");System.out.println("docType============"+docType);
     String fromTemplate = wfr.getParameter("fromTemplate");System.out.println("fromTemplate============"+fromTemplate);
     //String versionId = CommonConst.YES.equals(fromTemplate)?"-1":"1";
     String versionId = wfr.getParameter("versionId");System.out.println("versionId============"+versionId);
     if(versionId==null)
    	versionId = CommonConst.YES.equals(fromTemplate)?"-1":"1";
     //UserInfo currentUser = (UserInfo) session.getAttribute(LoginConst.WEGOV_LOGIN_USER);
	 String userId = currentPerson.getUserId();

	 ComposeServer composeServer = (ComposeServer)ctx.getANTBean("composeServer");
	 Composer composer = composeServer.getComposer(docType);
	 byte[] fileContent = null;
	 ArrayList wfl = wfm.getWebFiles();
	 Iterator it = wfl.iterator();
	 String fileName="";//上载文件的名称
	 if(it.hasNext()){
	      WebFile wf = (WebFile)it.next();
	      //fileName = wf.getFileName();
	      //数据读到字节数组输出流中
	      wf.saveFile2Stream(baos);
	      //暂存在buffer中
	      fileContent=baos.toByteArray();
		  composer.save(userId, docId, versionId, null, fileContent);
	 }

     DocProperties properties = new DocProperties();
     Enumeration pen = wfr.getParameterNames();
     while(pen.hasMoreElements()){
    	 String paramName = (String)pen.nextElement();
	     properties.put(paramName,wfr.getParameter(paramName));
     }
	 composer.setDocProperties(docId, userId, properties);
	 
	 response.getWriter().write("SUCCESS");
} catch (Exception e) {
	response.getWriter().write("FAIL");
}
%>