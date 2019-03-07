package com.wonders.fzb.platform.actions;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.LinkedList;
import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.wonders.fzb.base.actions.FileManageAction;
import com.wonders.fzb.base.consts.CommonConst;
import com.wonders.fzb.framework.beans.UserInfo;
import com.wonders.fzb.platform.compose.ComposeException;
import com.wonders.fzb.platform.compose.Composer;
import com.wonders.fzb.platform.services.ComposeServer;

@Namespace("/compose")
@Controller
@Scope("prototype")
public class ComposeAction extends FileManageAction {

	/**
	 * NTKO在线保存 上传类 by pengchun
	 */
	private static final long serialVersionUID = -5284141665380434059L;
	
	@Autowired
	@Qualifier("composeServer")
	private ComposeServer composeServer;
	
	
	//NTKO正文上传
	@Action(value = "compose_doc_upload")
	public void compose_doc_upload() throws IOException {
		byte[] fileContent = upload();
		String docId = request.getParameter("docId");
		String docType = request.getParameter("docType");
		docType = URLDecoder.decode(docType,"UTF-8");
		String fromTemplate = request.getParameter("fromTemplate");
		String versionId = request.getParameter("versionId");
		if(versionId==null)
	    	versionId = CommonConst.YES.equals(fromTemplate)?"-1":"1";
		UserInfo currentUser = getLoginUser();
		String userId = currentUser.getUserId();
		
		// ajax返回设置
		PrintWriter out = response.getWriter();
		try {
			Composer composer = composeServer.getComposer(docType);
			composer.save(userId, docId, versionId, null, fileContent);
			out.print("SUCCESS");
			out.flush();
		} catch (Exception e) {
			System.out.println(e.toString());
			out.print("FAIL");
			out.flush();
		}
	}
	
	//NTKO模板加载
	@Action(value = "compose_doc_download",results= {@Result(name=SUCCESS,location="/execlaw/WeGovPlatform/composer/compose_doc_download.jsp")})
	public String compose_doc_download() throws IOException {
		String docId = request.getParameter("docId");
		String docType = request.getParameter("docType");
		docType = URLDecoder.decode(docType,"UTF-8");
		String fromTemplate = request.getParameter("fromTemplate");
		String versionId = request.getParameter("versionId");
		if(versionId==null)
	    	versionId = CommonConst.YES.equals(fromTemplate)?"-1":"1";
		request.setAttribute("docId", docId);
		request.setAttribute("docType", docType);
		request.setAttribute("fromTemplate", fromTemplate);
		request.setAttribute("versionId", versionId);
		return SUCCESS;
	}
}