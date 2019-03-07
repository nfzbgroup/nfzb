package com.wonders.fzb.base.actions;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;
import org.apache.struts2.util.ServletContextAware;

import com.opensymphony.xwork2.ActionSupport;
import com.wonders.fzb.framework.beans.UserInfo;

/**
 * Action基类
 * @author ZSW
 */
public class BaseAction extends ActionSupport implements ServletContextAware,ServletResponseAware,ServletRequestAware,SessionAware {

	private static final long serialVersionUID = 1L;
	
	protected ServletContext servletContext;
	
	protected HttpServletRequest request;
	
	protected HttpServletResponse response;
	
	protected HttpSession session;
	
	protected Map<String,Object> sessionMap;
	
	private UserInfo loginUser;

	public UserInfo getLoginUser() {
		if(null != session.getAttribute("currentPerson")){
			loginUser = (UserInfo)session.getAttribute("currentPerson");
			if(null == loginUser.getAccount())
				try {
					response.sendRedirect("/index.html");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}
		return loginUser;
	}
	
	public void index(){ }

	@Override
	public void setServletContext(ServletContext paramServletContext) {
		this.servletContext = 	paramServletContext;
	}

	@Override
	public void setSession(Map<String, Object> paramMap) {
		this.sessionMap = paramMap;
	}

	@Override
	public void setServletRequest(HttpServletRequest paramHttpServletRequest) {
		this.request = paramHttpServletRequest;
		this.session = request.getSession();
	}

	@Override
	public void setServletResponse(HttpServletResponse paramHttpServletResponse) {
		this.response = paramHttpServletResponse;
	}
}
